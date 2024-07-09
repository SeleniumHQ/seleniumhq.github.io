---
title: "Chrome DevTools Protocol Endpoint"
linkTitle: "CDP Endpoint"
weight: 2
description: >
  Google provides a `/cdp/execute` endpoint that can be accessed directly. Each Selenium binding provides a method that
  allows you to pass the CDP domain as a String, and the required parameters as a simple Map.
---

{{% pageinfo color="warning" %}}
These methods will eventually be removed. It is recommended to use the [WebDriver-BiDi]({{< ref "../webdriver_bidi/" >}}) or [WebDriver Bidi APIs]({{< ref "bidi_api.md" >}}) 
methods where possible to ensure future compatibility.
{{% /pageinfo %}}


## Usage

Generally you should prefer the use of the [CDP API]({{< ref "cdp_api.md" >}}) over this approach,
but sometimes the syntax is cleaner or significantly more simple.

Limitations include:
* It only works for use cases that are limited to setting or getting information; 
any actual asynchronous interactions require another implementation 
* You have to know the exactly correct "magic strings" for domains and keys
* It is possible that an update to Chrome will change the required parameters

## Examples

### Set Cookie

An alternate implementation can be found at [CDP API Set Cookie]({{< ref "cdp_api#set-cookie" >}})

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/CdpEndpointTest.java#L26-32" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidirectional/chrome_devtools/test_cdp_endpoint.py#L7-L12" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/CdpEndpointTest.cs#L25-L33" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
The [CDP API Set Cookie]({{< ref "cdp_api#set-cookie" >}}) implementation should be preferred
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/cdp_endpoint_spec.rb#L9-L14" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

### Performance Metrics

An alternate implementation can be found at [CDP API Performance Metrics]({{< ref "cdp_api#performance-metrics" >}})

{{< tabpane text=true >}}
{{% tab header="Java" %}}
The [CDP API Performance Metrics]({{< ref "cdp_api#performance-metrics" >}}) implementation should be preferred
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/CdpEndpointTest.java#L43-L46" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidirectional/chrome_devtools/test_cdp_endpoint.py#L23-L25" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/CdpEndpointTest.cs#L46-L49" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
The [CDP API Performance Metrics]({{< ref "cdp_api#performance-metrics" >}}) implementation should be preferred
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/cdp_endpoint_spec.rb#L25-L27" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

### Basic authentication

Alternate implementations can be found at [CDP API Basic Authentication]({{< ref "cdp_api#basic-authentication" >}})
and [BiDi API Basic Authentication]({{< ref "bidi_api#basic-authentication" >}})

{{< tabpane text=true >}}
{{% tab header="Java" %}}
The [BiDi API Basic Authentication]({{< ref "bidi_api#basic-authentication" >}}) implementation should be preferred
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/CdpEndpointTest.java#L60-L66" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidirectional/chrome_devtools/test_cdp_endpoint.py#L34-L39" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/CdpEndpointTest.cs#L65-L73" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
The [BiDi API Basic Authentication]({{< ref "bidi_api#basic-authentication" >}}) implementation should be preferred
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/cdp_endpoint_spec.rb#L38-L43" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}
