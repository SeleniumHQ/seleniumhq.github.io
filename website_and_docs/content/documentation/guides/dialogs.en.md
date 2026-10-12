---
title: "Dialogs and alerts"
linkTitle: "Dialogs"
weight: 5
description: >
  Reading, accepting, dismissing and typing into JavaScript alerts, confirms and prompts, and
  deciding what should happen to a dialog your test did not expect.
---

A page calls `alert()`, `confirm()` or `prompt()`, the browser shows a native dialog, and
every subsequent WebDriver command fails until something closes it. If the dialog appears when
your test is not expecting it, the failure is an _unexpected alert open_ error several lines
away from the cause.

## Recommended approach

All three dialogs are handled through the same object: switch to the alert, then read its text,
accept it, dismiss it, or send keys to it. `alert()` can only be accepted; `confirm()` returns
true to the page when accepted and false when dismissed; `prompt()` additionally accepts typed
text before being accepted.

Treat the dialog like any other asynchronous event and wait for it. The bindings ship an
`alertIsPresent` expected condition that returns the alert as soon as it exists, which is both
safer and simpler than switching and catching the error.

For dialogs that may appear when no test code is ready to handle them, set the
`unhandledPromptBehavior` capability when starting the session. `dismiss` and `accept` close
the dialog silently; `dismiss and notify` (the default) and `accept and notify` close it and
raise an error so you find out; `ignore` leaves it open for you to handle. Recent drivers close
`beforeunload` prompts automatically.

With WebDriver BiDi enabled, the browsing context module also emits `userPromptOpened` and
`userPromptClosed` events, so a test can react to a dialog the moment it appears rather than
discovering it on the next command.

Switch to an alert, read its text, and close it:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L53-L56" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L16-L19" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L16-L22" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L19-L21" >}}
{{< /tab >}}
{{< /tabpane >}}

Type into a prompt before accepting it:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L80-L86" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L44-L48" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L42-L48" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L42-L45" >}}
{{< /tab >}}
{{< /tabpane >}}

Decide up front what happens to an unexpected dialog:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/drivers/OptionsTest.java#L142-L143" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/drivers/test_options.py#L51-L53" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Drivers/OptionsTest.cs#L59-L60" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/drivers/options_spec.rb#L60-L61" >}}
{{< /tab >}}
{{< /tabpane >}}

## Common errors

* [NoAlertPresentException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#noalertpresentexception" >}}):
  you switched to an alert before it appeared, or the session's prompt handler already closed it.
  Wait for it with `alertIsPresent`, and check `unhandledPromptBehavior`.
* [UnhandledAlertException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#unhandledalertexception" >}}):
  a dialog opened that nothing handled, and the driver closed it before running your command.
  Either handle the dialog in the test or change the prompt handler.

## Reference

* [JavaScript alerts, prompts and confirmations]({{< ref "/documentation/webdriver/interactions/alerts.md" >}})
* [unhandledPromptBehavior]({{< ref "/documentation/webdriver/drivers/options.md#unhandledpromptbehavior" >}})
* [Expected conditions]({{< ref "/documentation/webdriver/support_features/expected_conditions.md" >}})
* [BiDi browsing context events]({{< ref "/documentation/webdriver/bidi/w3c/browsing_context.md#user-prompt-opened-event" >}})
