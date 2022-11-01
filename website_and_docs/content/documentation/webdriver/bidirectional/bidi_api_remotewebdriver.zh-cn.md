---
title: "RemoteWebDriver BiDirectional API"
linkTitle: "RemoteWebDriver BiDi API"
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

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/augmenter/CdpRemoteWebDriverTest.java#L82-105" >}}
{{< /tab >}}
{{< /tabpane >}}

## Mutation Observation

Mutation Observation is the ability to capture events via
WebDriver BiDi when there are DOM mutations on a specific
element in the DOM.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/augmenter/CdpRemoteWebDriverTest.java#L110-148" >}}
{{< /tab >}}
{{< /tabpane >}}

## Listen to `console.log` events

Listen to the `console.log` events and register callbacks to process the event.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/augmenter/CdpRemoteWebDriverTest.java#L153-178" >}}
{{< /tab >}}
{{< /tabpane >}}

## Actions causing JS exceptions

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/augmenter/CdpRemoteWebDriverTest.java#L183-202" >}}
{{< /tab >}}
{{< /tabpane >}}

## Network Interception

If you want to capture network events coming into the browser and you want manipulate them you are able to do
it with the following examples.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/augmenter/CdpRemoteWebDriverTest.java#L207-L223" >}}
{{< /tab >}}
{{< /tabpane >}}

