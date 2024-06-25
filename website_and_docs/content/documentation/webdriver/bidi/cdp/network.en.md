---
title: "Chrome DevTools Network Features"
linkTitle: "Network"
weight: 4
description: >
  Network features using CDP.
---

{{% pageinfo color="warning" %}}
While Selenium 4 provides direct access to the Chrome DevTools Protocol, these
methods will eventually be removed when WebDriver BiDi implemented.
{{% /pageinfo %}}


## Basic authentication

Some applications make use of browser authentication to secure pages.
It used to be common to handle them in the URL, but browsers stopped supporting this.
With this code you can insert the credentials into the header when necessary

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidi/cdp/NetworkTest.java#L41-L43" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidi/cdp/test_network.py#L13-15" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/BiDi/CDP/NetworkTest.cs#L25-L32" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/cdp/network_spec.rb#L9-L11" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{< /tabpane >}}


## Network Interception

Both requests and responses can be recorded or transformed.

#### Response information

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidi/cdp/NetworkTest.java#L56-L65" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/BiDi/CDP/NetworkTest.cs#L46-L51" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/cdp/network_spec.rb#L20-L24" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}

#### Response transformation

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidi/cdp/NetworkTest.java#L75-L85" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/BiDi/CDP/NetworkTest.cs#L62-L73" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/cdp/network_spec.rb#L31-L35" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}


#### Request interception

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidi/cdp/NetworkTest.java#L97-L110" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/BiDi/CDP/NetworkTest.cs#L85-L97" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/cdp/network_spec.rb#L42-L46" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}


## Performance Metrics

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidi/cdp/NetworkTest.java#L125-L126" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidi/cdp/test_logging.py#L26-L28" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/BiDi/CDP/NetworkTest.cs#L114-L118" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/cdp/network_spec.rb#L56-L57" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}


## Setting Cookies

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidi/cdp/NetworkTest.java#L142-L157" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidi/cdp/test_logging.py#L37-L44" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/BiDi/CDP/NetworkTest.cs#L136-L143" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/cdp/network_spec.rb#L68-L71" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}


## Waiting for Downloads

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidi/cdp/NetworkTest.java#L171-L176" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/cdp/network_spec.rb#L82-L88" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}
