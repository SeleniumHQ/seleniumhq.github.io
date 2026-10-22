---
title: "Console and logs"
linkTitle: "Logging"
weight: 9
description: >
  Capturing the browser's console output and JavaScript errors with BiDi, and turning up
  Selenium's own logging to see what the bindings and the driver are doing.
---

Something went wrong, and the only evidence is a failed assertion. There are three separate
streams of information that could explain it: what the page wrote to the browser console,
what exception JavaScript threw, and what Selenium itself was sending to and receiving from
the driver. By default you see none of them.

## Recommended approach

**Browser console and JavaScript errors.** With WebDriver BiDi enabled, the log module streams
console entries and uncaught JavaScript exceptions to your test as they happen. Register a
handler before the action you want to observe, then assert on the entries it collects. This
is the cross-browser, standards-based replacement for the old Chromium-only log retrieval,
and it catches errors the page swallowed before a user would have seen them.

**Selenium's own logging.** Each binding logs through its language's standard logging
facility: `java.util.logging` in Java, the `logging` module in Python, Selenium's `Log` class
in .NET, and `Selenium::WebDriver.logger` in Ruby. Raising the level to debug shows every
command sent to the driver and every response, which is usually enough to tell whether the
problem is in your code, the driver or the browser. Logs go to the console by default; add a
file handler to keep them with the rest of your test artifacts.

**Driver logs.** The browser driver (chromedriver, geckodriver, and so on) keeps its own log,
configured through the driver's service class. Reach for it when the browser fails to start or
crashes mid-session.

Collect console messages with BiDi:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/LogTest.java#L30-L39" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_logging.py#L11-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/logInspector.spec.js#L23-L31" >}}
{{< /tab >}}
{{< /tabpane >}}

Collect JavaScript exceptions with BiDi:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/LogTest.java#L74-L79" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_logging.py#L35-L39" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/logInspector.spec.js#L44-L52" >}}
{{< /tab >}}
{{< /tabpane >}}

Turn up Selenium's own logging:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/troubleshooting/LoggingTest.java#L32-L35" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/troubleshooting/test_logging.py#L5-L7" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Troubleshooting/LoggingTest.cs#L19" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/troubleshooting/logging_spec.rb#L11-L13" >}}
{{< /tab >}}
{{< /tabpane >}}

## Common errors

* [JavascriptException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#javascriptexception" >}}):
  a script you executed through WebDriver threw. The BiDi log module shows the page's own
  errors, which this exception does not cover.
* [UnsupportedCommandException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#unsupportedcommandexception" >}}):
  BiDi was not enabled for the session, so the log module is unavailable.

## Reference

* [BiDi log]({{< ref "/documentation/webdriver/bidi/w3c/log.md" >}}): console entries and JavaScript exceptions
* [Logging Selenium commands]({{< ref "/documentation/webdriver/troubleshooting/logging.md" >}}): levels, output and filtering per binding
* [Driver service]({{< ref "/documentation/webdriver/drivers/service.md" >}}): where the driver's own log goes
* [Enabling BiDi]({{< ref "/documentation/webdriver/bidi/_index.md#enabling-bidi-in-selenium" >}})
