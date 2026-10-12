---
title: "Running in CI"
linkTitle: "CI"
weight: 10
description: >
  Running a Selenium suite on a build server: headless browsers, driver management without
  manual downloads, and when to move to Grid or Docker.
---

The suite passes on your laptop and fails on the build server. The server has no display, a
different browser version, no driver on the `PATH`, and a locked-down network. Each of those
is a separate failure with a separate fix, and the error messages rarely say which one you
have hit.

## Recommended approach

**No display.** Run the browser headless. Chrome and Edge take the `--headless=new` argument,
Firefox takes `-headless`. The page renders the same way, so only the browser options change.
Keep headless behind a configuration flag so you can still watch a test locally.

**No driver.** Do not download driver binaries in your pipeline. Selenium Manager, which
ships with Selenium 4.6 and later, detects the installed browser, downloads the matching
driver, and caches it in `~/.cache/selenium`; cache that directory between runs. Some hosted
runners pre-install drivers on the `PATH`, and a stale one there takes precedence over
Selenium Manager, so remove it or keep it current.

**Locked-down network.** Selenium Manager needs to reach the browser vendors' download
endpoints the first time. Behind a proxy, set it with the `SE_PROXY` environment variable or
the Selenium Manager configuration file.

**Scale.** When one runner starting one browser per test becomes too slow, point the same
tests at a [Selenium Grid]({{< ref "docker_and_grid.md" >}}) and run them in parallel without
changing the test code.

Collect a screenshot and the Selenium log on every failure; a CI failure without artifacts
is a re-run waiting to happen.

Start a headless browser:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L43" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L19" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L45" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L17" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L9-L12" >}}
{{< /tab >}}
{{< /tabpane >}}

A minimal GitHub Actions workflow, shown for Python, is a starting point only. The hosted
Ubuntu runner already has Chrome and Firefox installed; the only Selenium-specific step is
installing the binding:

```yaml
name: tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-python@v5
        with:
          python-version: "3.12"
      - run: pip install selenium pytest
      - run: pytest
```

The same shape applies to every binding: check out, install the language toolchain, install
the Selenium package, run the standard test command.

## Common errors

* [Unable to locate driver]({{< ref "/documentation/webdriver/troubleshooting/errors/driver_location.md" >}}):
  Selenium Manager could not find or download a driver. Check the browser is installed and the
  network allows the download.
* [SessionNotCreatedException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#sessionnotcreatedexception" >}}):
  the driver on the `PATH` does not match the installed browser, or the browser cannot start
  without a display. Remove stale drivers and run headless.
* [InvalidArgumentException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#invalidargumentexception" >}}):
  a browser option is wrong for this browser or version, for example a user data directory that is already in use by another session.

## Reference

* [Browser options]({{< ref "/documentation/webdriver/drivers/options.md" >}}) and the per-browser pages for
  [Chrome]({{< ref "/documentation/webdriver/browsers/chrome.md" >}}),
  [Edge]({{< ref "/documentation/webdriver/browsers/edge.md" >}}) and
  [Firefox]({{< ref "/documentation/webdriver/browsers/firefox.md" >}})
* [Selenium Manager]({{< ref "/documentation/selenium_manager.md" >}}): caching, proxies and offline use
* [Grid getting started]({{< ref "/documentation/grid/getting_started.md" >}})
* [Test practices]({{< ref "/documentation/test_practices/_index.md" >}}): keeping tests independent so they can run in parallel
