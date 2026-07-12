---
title: "BiDirectional API (W3C compliant)"
linkTitle: "BiDi API (W3C compliant)"
weight: 12
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language d-4"></i>
   Page being translated from
   English to Japanese. Do you speak Japanese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

This section contains the APIs related to logging. 

## Console logs

Listen to the `console.log` events and register callbacks to process the event.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/LogTest.java#L33-L39" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_logging.py#L6-L15" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/logInspector.spec.js#L23-37" >}}
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
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/LogTest.java#L74-L79" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_logging.py#L30-L39" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/logInspector.spec.js#L44-54" >}}
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
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/LogTest.java#L56-L61" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
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