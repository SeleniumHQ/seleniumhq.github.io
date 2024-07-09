---
title: "Chrome DevTools Protocol API"
linkTitle: "CDP API"
weight: 4
description: >
  Each of the Selenium bindings dynamically generates classes and methods for the various CDP domains and features;
  these are tied to specific versions of Chrome.
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

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Because Java requires using all the parameters example, the Map approach used in
[CDP Endpoint Set Cookie]({{< ref "cdp_endpoint#set-cookie" >}}) might be more simple.
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/CdpApiTest.java#L47-L65" >}}
{{% /tab %}}
{{% tab header="Python" %}}
Because Python requires using async methods for this example, the synchronous approach found in
[CDP Endpoint Set Cookie]({{< ref "cdp_endpoint#set-cookie" >}}) might be easier.
{{< gh-codeblock path="/examples/python/tests/bidirectional/chrome_devtools/test_cdp_api.py#L10-L18" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
Due to the added complexity in .NET of obtaining the domains and executing with awaits, the
[CDP Endpoint Set Cookie]({{< ref "cdp_endpoint#set-cookie" >}}) might be easier.
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/CdpApiTest.cs#L25-L37" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/cdp_api_spec.rb#L9-L12" >}}
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

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/CdpApiTest.java#L77-L81" >}}
{{% /tab %}}
{{% tab header="Python" %}}
Because Python requires using async methods for this example, the synchronous approach found in
[CDP Endpoint Performance Metrics]({{< ref "cdp_endpoint#performance-metrics" >}}) might be easier.
{{< gh-codeblock path="/examples/python/tests/bidirectional/chrome_devtools/test_cdp_api.py#L30-L33" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
Due to the added complexity in .NET of obtaining the domains and executing with awaits, the
[CDP Endpoint Performance Metrics]({{< ref "cdp_endpoint#performance-metrics" >}}) might be easier.
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/CdpApiTest.cs#L49-L56" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/cdp_api_spec.rb#L23-L25" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

### Basic authentication

Alternate implementations can be found at 
[CDP Endpoint Basic Authentication]({{< ref "cdp_endpoint#basic-authentication" >}}) 
and [BiDi API Basic Authentication]({{< ref "bidi_api#basic-authentication" >}})

{{< tabpane text=true >}}
{{% tab header="Java" %}}
The [BiDi API Basic Authentication]({{< ref "bidi_api#basic-authentication" >}}) implementation should be preferred
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/CdpApiTest.java#L94-L101" >}}
{{% /tab %}}
{{% tab header="Python" %}}
Because Python requires using async methods for this example, the synchronous approach found in
[CDP Endpoint Basic Authentication]({{< ref "cdp_endpoint#basic-authentication" >}}) might be easier.
{{< gh-codeblock path="/examples/python/tests/bidirectional/chrome_devtools/test_cdp_api.py#L43-L49" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
Due to the added complexity in .NET of obtaining the domains and executing with awaits, the
[CDP Endpoint Basic Authentication]({{< ref "cdp_endpoint#basic-authentication" >}}) might be easier.
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Bidirectional/ChromeDevtools/CdpApiTest.cs#L70-L83" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
The [BiDi API Basic Authentication]({{< ref "bidi_api#basic-authentication" >}}) implementation should be preferred
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/cdp_api_spec.rb#L36-L40" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

### Console logs

Because reading console logs requires setting an event listener,
this cannot be done with a CDP Endpoint implementation
Alternate implementations can be found at
[BiDi API Console logs and errors]({{< ref "bidi_api#console-logs-and-errors" >}})
and [WebDriver BiDi Console logs]({{< ref "../webdriver_bidi/log#console-logs" >}})

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Use the [WebDriver BiDi Console logs]({{< ref "../webdriver_bidi/log#console-logs" >}}) implementation
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/CdpApiTest.java#L114-L121" >}}
{{% /tab %}}
{{% tab header="Python" %}}
Use the [BiDi API Console logs and errors]({{< ref "bidi_api#console-logs-and-errors" >}}) implementation
{{< badge-code >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
Use the [BiDi API Console logs and errors]({{< ref "bidi_api#console-logs-and-errors" >}}) implementation
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
The [BiDi API Console logs and errors]({{< ref "bidi_api#console-logs-and-errors" >}}) implementation should be preferred
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/cdp_api_spec.rb#L50-L55" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

### JavaScript exceptions

Similar to console logs, but this listens for actual javascript exceptions not just logged errors
Alternate implementations can be found at
[BiDi API JavaScript exceptions]({{< ref "bidi_api#javascript-exceptions" >}})
and [WebDriver BiDi JavaScript exceptions]({{< ref "../webdriver_bidi/log#javascript-exceptions" >}})

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Use the [WebDriver BiDi JavaScript exceptions]({{< ref "../webdriver_bidi/log#javascript-exceptions" >}}) implementation
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/CdpApiTest.java#L133-L138" >}}
{{% /tab %}}
{{% tab header="Python" %}}
Use the [BiDi API JavaScript exceptions]({{< ref "bidi_api#javascript-exceptions" >}}) implementation
{{< badge-code >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
Use the [BiDi API JavaScript exceptions]({{< ref "bidi_api#javascript-exceptions" >}}) implementation
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
Use the [BiDi API JavaScript exceptions]({{< ref "bidi_api#javascript-exceptions" >}}) implementation
{{< badge-code >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

### Download complete

Wait for a download to finish before continuing.
Because getting download status requires setting a listener, this cannot be done with a CDP Endpoint implementation.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/chrome_devtools/CdpApiTest.java#L150-L162" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidirectional/chrome_devtools/cdp_api_spec.rb#L66-L72" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}
