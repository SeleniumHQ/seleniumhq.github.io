"""Run `dotnet test` and re-run only the tests that failed.

Works like `pytest --reruns`: the full suite runs once, then each retry
runs only the tests that are still failing, read from the TRX results.
If the failing tests cannot be read (build error, crashed test host),
the next attempt runs the full suite again.

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


def failed_tests(trx_path):
    """Return the fully qualified names of failed tests, or None if unknown."""
    if not os.path.exists(trx_path):
        return None
    root = ET.parse(trx_path).getroot()

    names = {}
    for unit_test in root.iterfind("t:TestDefinitions/t:UnitTest", TRX_NS):
        method = unit_test.find("t:TestMethod", TRX_NS)
        if method is not None:
            names[unit_test.get("id")] = f"{method.get('className')}.{method.get('name')}"

    failed = set()
    for result in root.iterfind("t:Results/t:UnitTestResult", TRX_NS):
        if result.get("outcome") == "Failed":
            name = names.get(result.get("testId"))
            if name is None:
                return None
            failed.add(name)
    return failed


def main():
    parser = argparse.ArgumentParser(description="Run dotnet test, retrying only failed tests")
    parser.add_argument("project_dir", help="Directory to run dotnet test in")
    parser.add_argument("--retries", type=int, default=2, help="Number of retries for failed tests")
    args = parser.parse_args()

    results_dir = os.path.abspath(os.path.join(args.project_dir, "TestResults"))

    returncode, trx_path = run_tests(args.project_dir, results_dir, 0)
    if returncode == 0:
        return 0

    first_failures = failed_tests(trx_path)
    failures = first_failures
    for attempt in range(1, args.retries + 1):
        if failures is not None and not failures:
            # Non-zero exit with no failed tests: retrying a filter would run nothing.
            failures = None
        returncode, trx_path = run_tests(args.project_dir, results_dir, attempt, failures)
        if returncode == 0:
            for name in sorted(first_failures or []):
                print(f"::warning title=Flaky test::{name} failed, then passed on retry", flush=True)
            return 0
        failures = failed_tests(trx_path)

    for name in sorted(failures or []):
        print(f"::error title=Failed test::{name} failed after {args.retries} retries", flush=True)
    return returncode


if __name__ == "__main__":
    sys.exit(main())
