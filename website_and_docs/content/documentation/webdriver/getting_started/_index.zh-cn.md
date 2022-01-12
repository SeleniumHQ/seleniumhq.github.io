---
title: "入门指南"
linkTitle: "入门指南"
weight: 2
needsTranslation: true
description: >
  如果你是Selenium的新手, 我们有一些资源帮助你快速入门.
aliases: [
"/documentation/zh-cn/getting_started/", 
"/documentation/zh-cn/getting_started/quick/",
"/documentation/zh-cn/selenium_installation/",
"/documentation/zh-cn/getting_started_with_webdriver/",
"/zh-cn/documentation/getting_started/"
]
---


Selenium 通过使用 _WebDriver_ 支持市场上所有主流浏览器的自动化。
Webdriver 是一个 API 和协议，它定义了一个语言中立的接口，用于控制 web 浏览器的行为。
每个浏览器都有一个特定的 WebDriver 实现，称为驱动程序。
驱动程序是负责委派给浏览器的组件，并处理与 Selenium 和浏览器之间的通信。

这种分离是有意识地努力让浏览器供应商为其浏览器的实现负责的一部分。
Selenium 在可能的情况下使用这些第三方驱动程序，
但是在这些驱动程序不存在的情况下，它也提供了由项目自己维护的驱动程序。

Selenium 框架通过一个面向用户的界面将所有这些部分连接在一起，
该界面允许透明地使用不同的浏览器后端，
从而实现跨浏览器和跨平台自动化。

Selenium 设置与其他商业工具的设置完全不同。
要在自动化项目中使用 Selenium，您需要为您选择的语言安装语言绑定库。
此外，对于要自动运行并运行测试的浏览器，您将需要 WebDriver 二进制文件。


安装Selenium可分为三个步骤:

1. [安装Selenium类库]({{< ref "install_library.md" >}}) 为你最喜爱的编程语言
2. [配置浏览器驱动]({{< ref "install_drivers.md" >}}) 用以驱动你的浏览器 (例如GeckoDriver用于Firefox)
3. (可选) 设置和配置 [Selenium Grid]({{< ref "/grid" >}}) 如果你想要扩展你的测试

如果您希望从低代码/录制和播放工具开始, 请检查
[Selenium IDE](https://selenium.dev/selenium-ide)

完成安装后，可以在你的文档
[starting page](/zh-cn/documentation) 中运行. 
然后前往
[WebDriver]({{< ref "/webdriver.md" >}}) 部分
了解更多关于
使用Selenium实现浏览器自动化的信息.
