# Debugging a failing test

> A short procedure for working out why a Selenium test failed: read the exception, gather evidence, and narrow the cause before changing anything.

URL: https://www.selenium.dev/documentation/guides/debugging/
Site index: https://www.selenium.dev/llms.txt

---

A test that used to pass now fails, or passes locally and fails in CI, or fails one run in
five. The temptation is to add a sleep, re-run, and move on. That hides the cause, and the
test comes back.

## Recommended approach

**Read the exception first.** The W3C WebDriver specification defines a fixed set of errors,
and each one points at a specific cause. _No such element_ means the locator matched nothing
in the current frame at that moment. _Stale element reference_ means the page replaced the
element after you found it. _Element click intercepted_ means something was drawn on top of
it. _Session not created_ means the browser never started. The common errors page lists every
one with its likely cause and fixes; start there before reading any code.

**Gather evidence at the moment of failure.** Add a hook to your test framework that, on
failure, saves a screenshot alongside the Selenium log. The screenshot shows what the page
looked like; the log shows what Selenium sent and what the driver replied. With BiDi enabled
you can also collect the browser console and any JavaScript exceptions, which often explain a
page that looks right but does not respond.

**Narrow the cause.** Ask, in order:

1. Is it timing? Most intermittent failures are. Replace any sleep with an explicit wait for
   the condition the next line depends on, and check that an implicit wait is not also set.
2. Is it the locator? Try it in the browser's developer tools, and check whether the element
   is inside a frame or a shadow root.
3. Is it the environment? A different browser version, a stale driver on the `PATH`, no
   display on the build server, or a blocked download endpoint all produce failures that never
   happen locally.
4. Is it the application? Then the test has found a bug, which is what it was for.

**Reproduce before you fix.** Run the test on its own, then in the full suite. A test that
only fails in the suite is sharing state with another test, usually through a reused browser
or a cookie left behind.

## Common errors

The [common errors](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md) page
covers each error in detail. The ones you will meet most often:

* [NoSuchElementException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#nosuchelementexception)
* [StaleElementReferenceException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#staleelementreferenceexception)
* [ElementClickInterceptedException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#elementclickinterceptedexception)
* [TimeoutException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#timeoutexception)
* [SessionNotCreatedException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#sessionnotcreatedexception)
* [Unable to locate driver](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/driver_location/index.md)

## Reference

* [Waiting](https://www.selenium.dev/documentation/guides/waiting/index.md), [Finding elements](https://www.selenium.dev/documentation/guides/finding_elements/index.md) and
  [Frames and windows](https://www.selenium.dev/documentation/guides/frames_and_windows/index.md): the three guides behind most failures
* [Screenshots](https://www.selenium.dev/documentation/guides/screenshots/index.md): capturing the page on failure
* [Console and logs](https://www.selenium.dev/documentation/guides/logging/index.md): Selenium's log and the browser console
* [Running in CI](https://www.selenium.dev/documentation/guides/ci/index.md): failures that only happen on the build server
* [Test practices](https://www.selenium.dev/documentation/test_practices/index.md): the discouraged patterns that cause flaky tests
