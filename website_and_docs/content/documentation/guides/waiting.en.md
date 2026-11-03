---
title: "Waiting for things to happen"
linkTitle: "Waiting"
weight: 1
description: >
  Synchronising your code with the page using explicit waits and expected conditions, and why a
  fixed sleep is the wrong tool.
---

Your script clicks a button, and the next line fails because the element it needs has not
appeared yet. Run it again and it passes. Modern applications update the page asynchronously,
so the browser and your code are in a race, and the loser is a flaky test.

## Recommended approach

Use an _explicit wait_: a loop that polls the application for a specific condition and
continues as soon as that condition is true, or raises a timeout error if it never is. You
decide what to wait for at each point where synchronisation matters, so the wait is exactly as
long as it needs to be and no longer.

Most bindings ship a library of ready-made _expected conditions_ (element visible, title
contains, alert present, and so on) that you can pass to the wait instead of writing your own
lambda. Each of them is a function that is called repeatedly until it returns a truthy value.

A fixed sleep is the first thing many people reach for, and it is nearly always wrong. It is
either too short, and the test fails on a slow machine, or too long, and every test in the
suite pays the full price every time. Likewise, do not combine an implicit wait with explicit
waits: the two timeouts add up in ways that are hard to predict.

This example waits for an element that is revealed a couple of seconds after a click:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L67-L68" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/waits/test_waits.py#L41-L42" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L56-L57" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/waits/waits_spec.rb#L42-L43" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/waits/waits.spec.js#L52" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/waits/WaitsTest.kt#L36-L37" >}}
{{< /tab >}}
{{< /tabpane >}}

The same wait expressed with a built-in expected condition:

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/support/test_expected_conditions.py#L14-L15" >}}
{{< /tab >}}
{{< /tabpane >}}

## Common errors

* [NoSuchElementException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#nosuchelementexception" >}}):
  the element was looked up before it existed. Wait for it to be present or visible first.
* [StaleElementReferenceException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#staleelementreferenceexception" >}}):
  the element was found, then the page re-rendered it. Locate it again, inside the wait if necessary.
* [TimeoutException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#timeoutexception" >}}):
  the condition never became true. Check the condition and the locator before raising the timeout.
* [ElementClickInterceptedException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#elementclickinterceptedexception" >}}):
  something (often an overlay or spinner) is still covering the element. Wait for it to disappear.

## Reference

* [Waiting strategies]({{< ref "/documentation/webdriver/waits.md" >}}): implicit and explicit waits, and how to customise them
* [Expected conditions]({{< ref "/documentation/webdriver/support_features/expected_conditions.md" >}})
* [Page load strategy]({{< ref "/documentation/webdriver/drivers/options.md#pageloadstrategy" >}}): what navigation already waits for
* [Timeouts]({{< ref "/documentation/webdriver/drivers/options.md#timeouts" >}}): the session-level timeout capabilities
