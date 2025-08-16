---
title: "Chrome DevTools Logging Features"
linkTitle: "Logging"
weight: 2
description: >
  Logging features using CDP.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language d-4"></i>
   Page being translated from
   English to Chinese. Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

{{% pageinfo color="warning" %}}
While Selenium 4 provides direct access to the Chrome DevTools Protocol, these
methods will eventually be removed when WebDriver BiDi implemented.
{{% /pageinfo %}}


## Console Logs

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidi/cdp/LoggingTest.java#L31" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidi/cdp/test_logs.py#L11-12" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/BiDi/CDP/LoggingTest.cs#L19-L25" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/cdp/logging_spec.rb#L12" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

## JavaScript Exceptions

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidi/cdp/test_logs.py#L22-L23" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/BiDi/CDP/LoggingTest.cs#L41-L47" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/cdp/logging_spec.rb#L26" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}
