"""Run `dotnet test` and re-run only the tests that failed.

Works like `pytest --reruns`: the full suite runs once, then each retry
runs only the tests that are still failing, read from the TRX results.
If the results of the full run cannot be read (build error, crashed test
host), the next attempt runs the full suite again. If the results of a
retry cannot be read, the same tests are retried: every other test
already passed in the full run.

Tests that only pass on a retry are reported as GitHub warnings so
flaky tests stay visible.
"""
import argparse
import os
import subprocess
import sys
import xml.etree.ElementTree as ET

TRX_NS = {"t": "http://microsoft.com/schemas/VisualStudio/TeamTest/2010"}


def run_tests(project_dir, results_dir, attempt, tests=None):
    trx_name = f"attempt-{attempt}.trx"
    command = [
        "dotnet", "test",
        "--logger", f"trx;LogFileName={trx_name}",
        "--results-directory", results_dir,
    ]
    if tests:
        command += ["--filter", "|".join(f"FullyQualifiedName={name}" for name in sorted(tests))]

    print(f"::group::Attempt {attempt}: {'failed tests: ' + str(len(tests)) if tests else 'all tests'}", flush=True)
    result = subprocess.run(command, cwd=project_dir)
    print("::endgroup::", flush=True)
    return result.returncode, os.path.join(results_dir, trx_name)


def test_outcomes(trx_path):
    """Return {fully qualified test name: outcome}, or None if the results can't be read."""
    try:
        root = ET.parse(trx_path).getroot()
    except (OSError, ET.ParseError):
        return None

    names = {}
    for unit_test in root.iterfind("t:TestDefinitions/t:UnitTest", TRX_NS):
        method = unit_test.find("t:TestMethod", TRX_NS)
        if method is not None:
            names[unit_test.get("id")] = f"{method.get('className')}.{method.get('name')}"

    outcomes = {}
    for result in root.iterfind("t:Results/t:UnitTestResult", TRX_NS):
        name = names.get(result.get("testId"))
        if name is None:
            return None
        # A data-driven test has one result per row; any failed row fails the test.
        if outcomes.get(name) != "Failed":
            outcomes[name] = result.get("outcome")
    return outcomes


def pending_tests(outcomes, requested):
    """Tests still to run: failures of a full run, or requested tests that did not pass."""
    if requested is None:
        return {name for name, outcome in outcomes.items() if outcome == "Failed"}
    return {name for name in requested if outcomes.get(name) != "Passed"}


def main():
    parser = argparse.ArgumentParser(description="Run dotnet test, retrying only failed tests")
    parser.add_argument("project_dir", help="Directory to run dotnet test in")
    parser.add_argument("--retries", type=int, default=2, help="Number of retries for failed tests")
    args = parser.parse_args()

    results_dir = os.path.abspath(os.path.join(args.project_dir, "TestResults"))

    returncode, trx_path = run_tests(args.project_dir, results_dir, 0)
    if returncode == 0:
        return 0

    outcomes = test_outcomes(trx_path)
    pending = None if outcomes is None else pending_tests(outcomes, None)
    seen_failures = set(pending or [])
    for attempt in range(1, args.retries + 1):
        if pending is not None and not pending:
            # Non-zero exit with no failed tests: retrying a filter would run nothing.
            pending = None
        requested = pending
        returncode, trx_path = run_tests(args.project_dir, results_dir, attempt, requested)
        if returncode == 0 and requested is None:
            break
        outcomes = test_outcomes(trx_path)
        if outcomes is None:
            # Unreadable results: retry the same tests (or the full suite after a full run).
            pending = requested
        else:
            pending = pending_tests(outcomes, requested)
            seen_failures |= {name for name, outcome in outcomes.items() if outcome == "Failed"}
        if returncode == 0 and pending == set():
            break
    else:
        for name in sorted(pending or []):
            print(f"::error title=Failed test::{name} still failing after {args.retries} retries", flush=True)
        return returncode or 1

    for name in sorted(seen_failures):
        print(f"::warning title=Flaky test::{name} failed, then passed on retry", flush=True)
    return 0


if __name__ == "__main__":
    sys.exit(main())
