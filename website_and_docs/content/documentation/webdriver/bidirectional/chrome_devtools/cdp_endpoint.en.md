---
title: "Chrome DevTools Protocol Endpoint"
linkTitle: "CDP Endpoint"
weight: 2
description: >
  Google provides a `/cdp/execute` endpoint that can be accessed directly. Each Selenium binding provides a method that
  allows you to pass the CDP domain as a String, and the required parameters as a simple Map.
---

{{% pageinfo color="warning" %}}
These methods will eventually be removed. It is recommended to use the [WebDriver-BiDi]({{< ref "../webdriver_bidi/" >}}) [WebDriver Bidi APIs]({{< ref "bidi_api.md" >}}) 
methods where possible to ensure future compatibility.
{{% /pageinfo %}}


## Usage

Generally you should prefer the use of the [CDP API]({{< ref "cdp_api.md" >}}) over this approach,
but sometimes the syntax is cleaner or significantly more simple.

Limitations include:
* It only works for use cases that only require setting or getting information; 
any actual asynchronous interactions require another implementation 
* You have to know the exactly correct "magic strings" for domains and keys
* It is possible that an update to Chrome will change the required parameters

## Examples

### Set Cookie

An alternate implementation can be found at [CDP API Set Cookie]({{< ref "cdp_api#set-cookie" >}})

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/CdpEndpointTest.java#L26-L34" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidirectional/test_cdp_endpoint.py#L7-L11" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/CdpEndpointTest.cs#L25-L33" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/cdp_endpoint_spec.rb#L9-L13" >}}
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

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/CdpEndpointTest.java#L45-L49" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidirectional/test_cdp_endpoint.py#L22-L23" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/CdpEndpointTest.cs#L46-L49" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/cdp_endpoint_spec.rb#L24-L25" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

### Basic Authentication

An alternate implementation can be found at [CDP API Basic Authentication]({{< ref "cdp_api#basic-authentication" >}})

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/CdpEndpointTest.java#L63-L70" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidirectional/test_cdp_endpoint.py#L32-L36" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/CdpEndpointTest.cs#L66-L74" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/cdp_endpoint_spec.rb#L36-L40" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}
