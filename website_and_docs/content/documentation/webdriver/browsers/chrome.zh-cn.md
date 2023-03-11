---
title: "Chrome specific functionality"
linkTitle: "Chrome"
weight: 4
description: >-
    These are capabilities and features specific to Google Chrome browsers.
aliases: [
"/zh-cn/documentation/capabilities/chromium"
]
---

默认情况下，Selenium 4与Chrome v75及更高版本兼容. 但是请注意Chrome浏览器的版本与chromedriver的主版本需要匹配.

## Options

所有浏览器的通用功能请看这 [Options page]({{< ref "../drivers/options.md" >}}).

Chrome浏览器的特有功能可以在谷歌的页面找到: [Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)

基于默认选项的Chrome浏览器会话看起来是这样:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L18-L19" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L6-L7" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L9-L10" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L51-L55">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

下面是一些不同功能的常见示例:

### 参数

`args` 参数是启动浏览器时输入的[浏览器命令行参数](https://peter.sh/experiments/chromium-command-line-switches/).
常用的参数包括 `--start-maximized` 和 `--headless=new`

添加一个参数到选项中:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L24-L26" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L21-L22" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L16-L18" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L9-L12">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 从指定位置启动浏览器

`binary` 参数接收一个使用浏览器的备用路径,通过这个参数你可以使用chromedriver 去驱动各种基于Chromium 内核的浏览器.

添加一个浏览器地址到选项中:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L41-L44">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 添加扩展程序

`extensions` 参数接受crx文件. As for unpacked directories,
please use the `load-extension` argument instead, as mentioned in
[this post](https://chromedriver.chromium.org/extensions).

添加一个扩展程序到选项中:

{{< alert-code />}}

### 保持浏览器的打开状态

将 `detach` 参数设置为true将在驱动过程结束后保持浏览器的打开状态.

添加一个布尔值到选项中:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L31-L33" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L12-L13">}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L21" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#29-L32">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 排除的参数

Chrome 添加了各种参数，如果你不希望添加某些参数，可以将其传入 `excludeSwitches`.
一个常见的例子是重新打开弹出窗口阻止程序.

设置排除参数至选项中:

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L30-L31" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L28" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L19-L22">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Casting

你可以驱动 Chrome Cast 设备，包括共享选项卡

{{< alert-code />}}

## 网络条件

您可以模拟各种网络条件.

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}}) page.

{{< alert-code />}}

## Logs

{{< alert-code />}}

## Permissions

{{< alert-code />}}

## DevTools

See the [Chrome DevTools]({{< ref "../bidirectional/chrome_devtools.md" >}}) section for more information about using Chrome DevTools
