---
title: "Chrome DevTools Protocol API"
linkTitle: "CDP API"
weight: 4
description: >
  Each Selenium binding dynamically creates the classes and generates the methods for each new version of Chrome.
---

{{% pageinfo color="warning" %}}
While Selenium 4 provides direct access to the Chrome DevTools Protocol (CDP), these
methods will eventually be removed. It is recommended to use the [WebDriver Bidi APIs]({{< ref "bidi_api.md" >}}) 
methods where possible to ensure future compatibility.
{{% /pageinfo %}}

## Usage

If your use case has been implemented by [WebDriver Bidi]({{< ref "../webdriver_bidi/" >}}) or
the [BiDi API]({{< ref "bidi_api.md" >}}), you should use those implementations instead of this one.
Generally you should prefer this approach over executing with the [CDP Endpoint]({{< ref "cdp_endpoint.md" >}}),
especially in Ruby.


## Examples

### Set Cookie

An alternate implementation can be found at [CDP Endpoint Set Cookie]({{< ref "cdp_endpoint#set-cookie" >}})

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
Because Java requires using all the parameters example, the Map approach used in
[CDP Endpoint Set Cookie]({{< ref "cdp_endpoint#set-cookie" >}}) might be more simple.
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/CdpApiTest.java#L39-L57" >}}
{{% /tab %}}
{{% tab header="Python" %}}
Because Python requires using async methods for this example, the synchronous approach found in
[CDP Endpoint Set Cookie]({{< ref "cdp_endpoint#set-cookie" >}}) might be easier.
{{< gh-codeblock path="/examples/python/tests/bidirectional/test_cdp_api.py#L10-L17" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
Due to the added complexity in .NET of obtaining the domains and executing with awaits, the
[CDP Endpoint Set Cookie]({{< ref "cdp_endpoint#set-cookie" >}}) might be easier.
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/CdpApiTest.cs#L25-L39" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/cdp_api_spec.rb#L9-L12" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

### Performance Metrics

An alternate implementation can be found at [CDP Endpoint Performance Metrics]({{< ref "cdp_endpoint#performance-metrics" >}})

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/CdpApiTest.java#L68-L73" >}}
{{% /tab %}}
{{% tab header="Python" %}}
Because Python requires using async methods for this example, the synchronous approach found in
[CDP Endpoint Performance Metrics]({{< ref "cdp_endpoint#performance-metrics" >}}) might be easier.
{{< gh-codeblock path="/examples/python/tests/bidirectional/test_cdp_api.py#L29-L32" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
Due to the added complexity in .NET of obtaining the domains and executing with awaits, the
[CDP Endpoint Performance Metrics]({{< ref "cdp_endpoint#performance-metrics" >}}) might be easier.
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/CdpApiTest.cs#L51-L60" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/cdp_api_spec.rb#L23-L24" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

### Basic Authentication

An alternate implementation can be found at 
[CDP Endpoint Basic Authentication]({{< ref "cdp_endpoint#basic-authentication" >}})

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/CdpApiTest.java#L86-L94" >}}
{{% /tab %}}
{{% tab header="Python" %}}
Because Python requires using async methods for this example, the synchronous approach found in
[CDP Endpoint Basic Authentication]({{< ref "cdp_endpoint#basic-authentication" >}}) might be easier.
{{< gh-codeblock path="/examples/python/tests/bidirectional/test_cdp_api.py#L42-L46" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
Due to the added complexity in .NET of obtaining the domains and executing with awaits, the
[CDP Endpoint Basic Authentication]({{< ref "cdp_endpoint#basic-authentication" >}}) might be easier.
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/CdpApiTest.cs#L73-L87" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/cdp_endpoint_spec.rb#L35-L38" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

### Console Logs

Because reading console logs requires setting an event listener,
this cannot be done with a CDP Endpoint implementation

{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/CdpApiTest.java#L107-L118" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/cdp_endpoint_spec.rb#L46-L51" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}
