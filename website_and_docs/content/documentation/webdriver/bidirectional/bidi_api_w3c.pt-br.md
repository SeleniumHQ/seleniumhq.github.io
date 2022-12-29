---
title: "BiDirectional API (W3C compliant)"
linkTitle: "BiDi API (W3C compliant)"
weight: 12
---

The following list of APIs will be growing as the [WebDriver BiDirectional Protocol](https://w3c.github.io/webdriver-bidi/) grows
and browser vendors implement the same. 
Additionally, Selenium will also try to support real-world use cases that internally uses a combination of W3C BiDi protocol APIs.
If there is additional functionality you'd like to see, please raise a
[feature request](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=&template=feature.md).

## Listen to `console.log` events

Listen to the `console.log` events and register callbacks to process the event.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/log/LogInspectorTest.java#L40-55" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Listen to JS Exceptions

Listen to the JS Exceptions
and register callbacks to process the exception details.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/log/LogInspectorTest.java#L79-90" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Listen to JS Logs

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/log/LogInspectorTest.java#L61-73" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}