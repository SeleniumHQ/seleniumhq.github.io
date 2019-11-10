---
title: "快速浏览"
weight: 1
---

Selenium 不仅仅是一个工具或 API，它还包含许多工具。

## WebDriver

_[WebDriver]({{< ref "/webdriver/_index.md" >}})_ 也被称为 Selenium 2。
如果您开始使用桌面网站测试自动化，那么您将使用 WebDriver APIs。
WebDriver 使用浏览器供应商提供的浏览器自动化 API 来控制浏览器和运行测试。
这就像真正的用户正在操作浏览器一样。
由于 WebDriver 不要求使用应用程序代码编译其 API，因此它本质上不具有侵入性。
因此，您测试的应用程序与实时推送的应用程序相同。

## IDE

_[IDE](https://selenium.dev/selenium-ide)_ 是一个 Firefox 插件，
可以用来记录 Firefox 本身的测试步骤。
Selenium IDE 可用于快速生成各种编程语言（C#、Java、Python 和 Ruby）的测试代码。
考虑到通过 Selenium IDE 生成的代码的可维护性，除了熟悉元素定位器或生成丢弃的代码之外，
不推荐将其用于其他任何用途。
我们确信，一旦您习惯了 WebDriver API，您将永远不会使用 Selenium IDE。

## Grid

在 WebDriver 测试开发后不久，您可能需要在多个浏览器和操作系统组合上运行测试。
这就是 _[Grid]({{< ref "/grid/_index.md" >}})_ 应用的地方。

