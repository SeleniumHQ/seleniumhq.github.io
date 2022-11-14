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

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/SafariTest.java#21-L22" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L10-L11" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/SafariTest.cs#L14-L15" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/safari_spec.rb#L7-L8" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
  let options = new safari.Options();
  let driver = await new Builder()
    .forBrowser('safari')
    .setSafariOptions(options)
    .build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
  val options = SafariOptions()
  val driver = SafariDriver(options)
{{< /tab >}}
{{< /tabpane >}}

### 移动端
那些希望在iOS上自动化Safari的人可以参考 [Appium project](//appium.io/).
