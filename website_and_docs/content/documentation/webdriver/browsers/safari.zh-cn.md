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
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/SafariTest.java#21-L22" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_safari.py#L10-L11" >}}
{{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/SafariTest.cs#L14-L15" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/safari_spec.rb#L7-L8" >}}
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

## Safari Technology Preview

Apple provides a development version of their browser — [Safari Technology Preview](https://developer.apple.com/safari/technology-preview/)
To use this version in your code:

{{< alert-code />}}
