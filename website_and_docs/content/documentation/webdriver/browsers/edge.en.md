---
title: "Edge specific functionality"
linkTitle: "Edge"
weight: 5
description: >-
    These are capabilities and features specific to Microsoft Edge browsers.
---

Microsoft Edge is implemented with Chromium, with the earliest supported version of v79. Similar to Chrome,
the major version number of edgedriver must match the major version of the Edge browser.

All capabilities and options found on the [Chrome page]({{< ref "chrome.md" >}}) work for Edge as well.

## Options

Starting an Edge session with basic defined options looks like this:

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L18-L19" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L6-L7" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L12-L13" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L7-L8" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openEdgeTest.spec.js#L11-L16">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Internet Explorer Compatibility Mode

Microsoft Edge can be driven in "Internet Explorer Compatibility Mode," which uses
the Internet Explorer Driver classes in conjunction with Microsoft Edge.
Read the [Internet Explorer page]({{< ref "internet_explorer.md" >}}) for more details.
