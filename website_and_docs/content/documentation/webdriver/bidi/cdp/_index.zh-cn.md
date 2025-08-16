---
title: "Chrome DevTools 协议"
linkTitle: "CDP"
weight: 10
description: >
    使用 Selenium 操作 Chrome DevTools 协议的示例。
    CDP 的支持是临时的，直到 WebDriver BiDi 实现为止。
aliases: [
"/documentation/en/support_packages/chrome_devtools/",
"/documentation/support_packages/chrome_devtools/",
"/documentation/webdriver/bidirectional/chrome_devtools/",
"documentation/webdriver/bidirectional/chrome_devtools/cdp_endpoint/",
"documentation/webdriver/bidirectional/chrome_devtools/bidi_api/",
"documentation/webdriver/bidirectional/chrome_devtools/cdp_api/",
]
---



许多浏览器提供“开发者工具”（DevTools），这是与浏览器集成的一组工具，开发人员可以使用它们来调试网页应用程序并探索网页的性能。Google Chrome 的开发者工具使用一种称为 Chrome DevTools 协议（简称 "CDP"）的协议。顾名思义，该协议并非为测试设计，也没有稳定的 API，因此功能很大程度上取决于浏览器的版本。

Selenium 正在致力于实现一种基于标准的、跨浏览器的、稳定的 CDP 替代方案，称为 [WebDriver BiDi]。在对该新协议的支持完成之前，Selenium 计划在适用的地方提供对 CDP 功能的访问。

### 在 Selenium 中使用 Chrome DevTools 协议

Chrome 和 Edge 提供了发送基本 CDP 命令的方法。
但对于需要双向通信的功能，这种方法无效。你需要知道在何时启用哪些域，以及域、方法和参数的确切名称和类型。

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidi/cdp/CdpTest.java#L22-L27" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/bidi/cdp/test_cdp.py#L2-L7" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/BiDi/CDP/CDPTest.cs#L14-L21" >}}
{{% /tab %}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/cdp/cdp_spec.rb#L9-L13" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}
{{< badge-code >}}
{{% /tab %}}
{{% tab header="Kotlin" %}}
{{< badge-code >}}
{{% /tab %}}
{{< /tabpane >}}


为简化 CDP 的使用并提供对更高级功能的访问，Selenium 绑定会自动为最常见的域生成类和方法。  
不过，CDP 方法和实现可能会因版本而异，因此你需要确保 Chrome 版本和 DevTools 版本相匹配。  
Selenium 在任何时间点支持 Chrome 的最近三个版本，并且尽量同步发布以确保可以访问最新版本。

这种限制给一些绑定带来了额外的挑战，动态生成的 CDP 支持要求用户定期更新代码，以引用正确版本的 CDP。  
在某些情况下，已创建了一个理想化的实现，它应该适用于任何版本的 CDP，而无需用户更改代码，但这并非总是可用。

关于如何在 Selenium 测试中使用 CDP 的示例可以在以下页面找到，但我们想提到一些常被引用但实际价值有限的例子：
* **地理位置** ——几乎所有网站都使用 IP 地址来确定物理位置，因此设置模拟地理位置很少能达到预期效果。
* **覆盖设备指标** ——Chrome 提供了一个很棒的 API 来在 Options 类中设置[移动模拟](https://chromedriver.chromium.org/mobile-emulation)，这通常比尝试使用 CDP 更优越。

