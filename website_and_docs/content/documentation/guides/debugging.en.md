---
title: "Debugging a failing test"
linkTitle: "Debugging"
weight: 12
description: >
  A short procedure for working out why a Selenium test failed: read the exception, gather
  evidence, and narrow the cause before changing anything.
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

The [common errors]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md" >}}) page
covers each error in detail. The ones you will meet most often:

* [NoSuchElementException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#nosuchelementexception" >}})
* [StaleElementReferenceException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#staleelementreferenceexception" >}})
* [ElementClickInterceptedException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#elementclickinterceptedexception" >}})
* [TimeoutException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#timeoutexception" >}})
* [SessionNotCreatedException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#sessionnotcreatedexception" >}})
* [Unable to locate driver]({{< ref "/documentation/webdriver/troubleshooting/errors/driver_location.md" >}})

## Reference

* [Waiting]({{< ref "waiting.md" >}}), [Finding elements]({{< ref "finding_elements.md" >}}) and
  [Frames and windows]({{< ref "frames_and_windows.md" >}}): the three guides behind most failures
* [Screenshots]({{< ref "screenshots.md" >}}): capturing the page on failure
* [Console and logs]({{< ref "logging.md" >}}): Selenium's log and the browser console
* [Running in CI]({{< ref "ci.md" >}}): failures that only happen on the build server
* [Test practices]({{< ref "/documentation/test_practices/_index.md" >}}): the discouraged patterns that cause flaky tests
