---
title: "RemoteWebDriver BiDirectional API (CDP implementation)"
linkTitle: "RemoteWebDriver BiDi API (CDP implementation)"
weight: 1
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i>
   Page being translated from
   English to Chinese. Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

The following examples demonstrate how to leverage BiDi APIs with [Remote WebDriver](/documentation/webdriver/remote_webdriver/).

## Register Basic Auth

Some applications make use of browser authentication to secure pages.
With Selenium, you can automate the input of basic auth credentials whenever they arise.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/BidiApiRemoteWebdriverTest.java#L72-90" >}}
{{< /tab >}}
{{< /tabpane >}}

## Mutation Observation

Mutation Observation is the ability to capture events via
WebDriver BiDi when there are DOM mutations on a specific
element in the DOM.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/BidiApiRemoteWebdriverTest.java#L101-124" >}}
{{< /tab >}}
{{< /tabpane >}}

## Listen to `console.log` events

Listen to the `console.log` events and register callbacks to process the event.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/BidiApiRemoteWebdriverTest.java#L139-155" >}}
{{< /tab >}}
{{< /tabpane >}}

## Actions causing JS exceptions

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/BidiApiRemoteWebdriverTest.java#L164-171" >}}
{{< /tab >}}
{{< /tabpane >}}

## Network Interception

If you want to capture network events coming into the browser and you want manipulate them you are able to do
it with the following examples.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/BidiApiRemoteWebdriverTest.java#L188-L198" >}}
{{< /tab >}}
{{< /tabpane >}}

