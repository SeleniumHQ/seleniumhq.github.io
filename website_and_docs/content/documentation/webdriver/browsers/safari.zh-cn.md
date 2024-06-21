---
title: "Safari 特定功能"
linkTitle: "Safari"
weight: 10
description: >-
    这些是特定于Apple Safari浏览器的功能和特性.
aliases: [
"/zh-cn/documentation/capabilities/safari"
]
---

与Chromium和Firefox驱动不同, safari驱动随操作系统安装.
要在 Safari 上启用自动化, 请从终端运行以下命令:

```shell
safaridriver --enable
```

## 选项

所有浏览器通用的Capabilities在[选项页]({{< ref "../drivers/options.md" >}}).

Safari独有的Capabilities可以在Apple的页面[关于Safari的WebDriver](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari#2957227) 上找到

使用基本定义的选项启动 Safari 会话如下所示:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/SafariTest.java#24-L25" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_safari.py#L9-L10" >}}
{{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/SafariTest.cs#L22-L23" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/safari_spec.rb#L8-L9" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/browser/safariSpecificCap.spec.js#L10-L12" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
val options = SafariOptions()
val driver = SafariDriver(options)
{{< /tab >}}
{{< /tabpane >}}

### 移动端
那些希望在iOS上自动化Safari的人可以参考 [Appium project](//appium.io/).


## Service

Service settings common to all browsers are described on the [Service page]({{< ref "../drivers/service.md" >}}).

### Logging

Unlike other browsers, Safari doesn't let you choose where logs are output, or change levels. The one option
available is to turn logs off or on. If logs are toggled on, they can be found at:`~/Library/Logs/com.apple.WebDriver/`.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/SafariTest.java#L31" >}}
**Note**: Java also allows setting console output by System Property;\
Property key: `SafariDriverService.SAFARI_DRIVER_LOGGING`\
Property value: `"true"` or `"false"`
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_safari.py#L17" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/safari_spec.rb#L20" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## Safari Technology Preview

Apple provides a development version of their browser — [Safari Technology Preview](https://developer.apple.com/safari/technology-preview/)
To use this version in your code:

{{< tabpane text=true >}}
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
{{< gh-codeblock path="examples/ruby/spec/browsers/safari_spec.rb#L36-L37" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
