---
title: "Log"
linkTitle: "Log"
weight: 1
aliases: [
  "/documentation/en/webdriver/bidirectional/bidirectional_w3c/log",
]
---

This section contains the APIs related to logging. 

## Console logs

Listen to the `console.log` events and register callbacks to process the event.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/LogTest.java#L31-L49" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/logging_spec.rb#L9-18" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/logInspector.spec.js#L21-40" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_logging.py#L7-15" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## JavaScript exceptions

Listen to the JS Exceptions
and register callbacks to process the exception details.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/LogTest.java#L69-L84" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/logging_spec.rb#L31-40" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/logInspector.spec.js#L42-L57" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_logging.py#L31-39" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Listen to JS Logs

Listen to all JS logs at all levels and register callbacks to process the log.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/LogTest.java#L51-L67" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}