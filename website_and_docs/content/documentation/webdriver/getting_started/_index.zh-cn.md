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

Selenium setup is quite different from the setup of other commercial tools.
Before you can start writing Selenium code, you have to 
install the language bindings libraries for your language of choice, the browser you
want to use, and the driver for that browser.

***Follow the links below to get up and going with Selenium WebDriver.***

If you wish to start with a low-code/record and playback tool, please check
[Selenium IDE](https://selenium.dev/selenium-ide)

Once you get things working, if you want to scale up your tests, check out the 
[Selenium Grid]({{< ref "/documentation/grid" >}}).
