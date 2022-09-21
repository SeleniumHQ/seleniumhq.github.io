---
title: "Selenium 浏览器自动化项目"
linkTitle: "文档"
cascade:
- type: docs
aliases: ["/documentation/zh-cn/"]
---

Selenium 是支持 web 浏览器自动化的一系列工具和库的综合项目。

它提供了扩展来模拟用户与浏览器的交互，用于扩展浏览器分配的分发服务器，
以及用于实现 [W3C WebDriver 规范](//www.w3.org/TR/webdriver/) 的基础结构，
该 规范 允许您为所有主要 Web 浏览器编写可互换的代码。

这个项目是由志愿者贡献者实现的，他们投入了自己数千小时的时间，
并使源代码[免费提供]({{< ref "/copyright.md#license" >}})给任何人使用、享受和改进。

Selenium 汇集了浏览器供应商，工程师和爱好者，以进一步围绕 Web 平台自动化进行公开讨论。
该项目组织了[一次年度会议](//seleniumconf.com/)，以教学和培养社区。

Selenium 的核心是 [WebDriver]({{< ref "/webdriver.md" >}})，这是一个编写指令集的接口，可以在许多浏览器中互换运行。
这里有一个最简单的说明：



{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/hello/HelloSelenium.java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/hello/hello_selenium.py" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Hello/HelloSelenium.cs" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/hello/hello_selenium_spec.rb" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/hello/helloSelenium.js" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/hello/HelloSelenium.kt" >}}
{{< /tab >}}
{{< /tabpane >}}


请参阅 [概述]({{< ref "overview" >}}) 
以检查不同的项目组件,
并确定Selenium是否适合您.

您应该继续阅读 [开始]({{< ref "webdriver/getting_started" >}}),
以了解如何安装Selenium,
将其成功用作测试自动化工具,
并将这样的简单测试扩展为
在大型分布式环境,
以及不同操作系统上的环境上
运行多个浏览器的测试.

