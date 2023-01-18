---
title: "Edge 特定功能"
linkTitle: "Edge"
weight: 5
description: >-
    这些是特定于微软Edge浏览器的功能和特性.
---

微软Edge是用Chromium实现的，最早支持版本是v79.
与Chrome类似, Edge驱动的主版本号必须与Edge浏览器的主要版本匹配.

在 [Chrome 页面]({{< ref "chrome.md" >}}) 上找到的所有capabilities和选项也适用于Edge.

## 选项

使用基本定义的选项启动 Edge 会话如下所示:

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L18-L19" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_edge.py#L6-L7" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/EdgeTest.cs#L12-L13" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L7-L8" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openEdgeTest.spec.js#L11-L16">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Internet Explorer 兼容模式

微软Edge可以被"Internet Explorer兼容模式"驱动, 
该模式使用Internet Explorer驱动类与微软Edge结合使用.
阅读 [Internet Explorer 页面]({{< ref "internet_explorer.md" >}}) 了解更多详情.
