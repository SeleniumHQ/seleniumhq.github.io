---
title: "WebDriver 入门"
chapter: true
weight: 4
---

# WebDriver 入门

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

有关驱动程序的更多详细信息，请参阅
[驱动特性]({{< ref "/driver_idiosyncrasies/_index.md" >}}).
