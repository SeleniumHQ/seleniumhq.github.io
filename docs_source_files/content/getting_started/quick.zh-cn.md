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

## Remote Control

[_Remote Control_](https://www.seleniumhq.org/docs/05_selenium_rc.jsp)
也被称为 Selenium 1。
Selenium RC 是 Selenium WebDriver 出现之前最突出的 Selenium 工具。
Selenium RC 将使用一个代理服务器，并将 JavaScript 注入到浏览器中，以便能够控制它。
考虑到 Selenium RC 在浏览器上的侵入性，
您永远不能确定您测试的应用程序是否与您想要推送的应用程序相同。
Selenium 2 APIs 包含有 Selenium RC APIs，
但是 Selenium 3 会完全摆脱 Selenium RC APIs。
如果您还在使用 Selenium RC，则必须
[_迁移_](https://www.seleniumhq.org/docs/03_webdriver.jsp#migrating-from-selenium-1-0)
到 Selenium WebDriver.

## IDE

_[IDE](https://www.seleniumhq.org/selenium-ide)_ 是一个 Firefox 插件，
可以用来记录 Firefox 本身的测试步骤。
Selenium IDE 可用于快速生成各种编程语言（C#、Java、Python 和 Ruby）的测试代码。
考虑到通过 Selenium IDE 生成的代码的可维护性，除了熟悉元素定位器或生成丢弃的代码之外，
不推荐将其用于其他任何用途。
我们确信，一旦您习惯了 WebDriver API，您将永远不会使用 Selenium IDE。

## Grid

在 WebDriver 测试开发后不久，您可能需要在多个浏览器和操作系统组合上运行测试。
这就是 _[Grid]({{< ref "/grid/_index.md" >}})_ 应用的地方。

## HTML Runner

此工具允许您从命令行运行 Test Suites。
Test Suites 是从 Selenium IDE 或兼容工具导出的 HTML。
_[HTML Runner]({{< ref "/getting_started/html-runner.zh-cn.md" >}})_
