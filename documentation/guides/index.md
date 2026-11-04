# Guides

> Task-based guides: start from the problem you have and follow the links into the reference pages that solve it.

URL: https://www.selenium.dev/documentation/guides/
Site index: https://www.selenium.dev/llms.txt

---

The rest of this documentation is organised by API surface: elements, interactions, waits,
BiDi, and so on. That is the right shape for looking something up once you know what it is
called, but most people arrive with a problem rather than a method name.

The guides in this section are organised by task instead. Each one states the problem,
describes the approach the Selenium project recommends, shows the shortest working example
for each language binding, lists the errors you are most likely to hit along the way, and
links to the reference pages that cover the detail. They do not replace the reference pages;
they are the fastest route into them.

If you are new to Selenium altogether, start with
[Getting started](https://www.selenium.dev/documentation/webdriver/getting_started/index.md) and come back here
once you have a first script running.

---

Section pages:

- [Waiting for things to happen](https://www.selenium.dev/documentation/guides/waiting/index.md): Synchronising your code with the page using explicit waits and expected conditions, and why a fixed sleep is the wrong tool.
- [Finding elements reliably](https://www.selenium.dev/documentation/guides/finding_elements/index.md): Choosing a locator strategy that survives the next release of the application, and avoiding the ones that do not.
- [Authentication and sessions](https://www.selenium.dev/documentation/guides/authentication/index.md): Getting past a login once, answering HTTP basic authentication prompts, and reusing a signed-in state across tests without driving the login form every time.
- [Files: upload and download](https://www.selenium.dev/documentation/guides/files/index.md): Uploading a file through a form without touching the operating system's file dialog, and why downloading through the browser is not the right way to test a download.
- [Dialogs and alerts](https://www.selenium.dev/documentation/guides/dialogs/index.md): Reading, accepting, dismissing and typing into JavaScript alerts, confirms and prompts, and deciding what should happen to a dialog your test did not expect.
- [Frames and windows](https://www.selenium.dev/documentation/guides/frames_and_windows/index.md): Reaching elements inside an iframe, following a link that opens a new tab or window, and getting back to where you started.
- [Screenshots and PDFs](https://www.selenium.dev/documentation/guides/screenshots/index.md): Capturing the viewport, a single element or the whole page as an image, and printing a page to PDF.
- [Network interception and mocking](https://www.selenium.dev/documentation/guides/network/index.md): Watching the requests a page makes, blocking or failing some of them, and answering authentication challenges, using the W3C WebDriver BiDi network module.
- [Console and logs](https://www.selenium.dev/documentation/guides/logging/index.md): Capturing the browser's console output and JavaScript errors with BiDi, and turning up Selenium's own logging to see what the bindings and the driver are doing.
- [Running in CI](https://www.selenium.dev/documentation/guides/ci/index.md): Running a Selenium suite on a build server: headless browsers, driver management without manual downloads, and when to move to Grid or Docker.
- [Running in Docker and Grid](https://www.selenium.dev/documentation/guides/docker_and_grid/index.md): Moving the browser off the machine that runs the tests, with Selenium Grid and the project's Docker images, so a suite can run in parallel and against several browsers.
- [Debugging a failing test](https://www.selenium.dev/documentation/guides/debugging/index.md): A short procedure for working out why a Selenium test failed: read the exception, gather evidence, and narrow the cause before changing anything.
- [Migrating from Selenium 3](https://www.selenium.dev/documentation/guides/migrating_from_selenium_3/index.md): What changes when a Selenium 3 suite moves to Selenium 4, and where to find the step-by-step upgrade guide.
