---
title: "快速浏览"
weight: 1
---

Selenium 不仅仅是一个工具或 API，它还包含许多工具。

## WebDriver

如果您开始使用桌面网站测试自动化，那么您将使用 WebDriver APIs。
_[WebDriver]({{< ref "/webdriver/_index.md" >}})_ 使用浏览器供应商提供的浏览器自动化 API 来控制浏览器和运行测试。
这就像真正的用户正在操作浏览器一样。
由于 WebDriver 不要求使用应用程序代码编译其 API，因此它本质上不具有侵入性。
因此，您测试的应用程序与实时推送的应用程序相同。

## Selenium IDE

_[Selenium IDE](https://selenium.dev/selenium-ide)_ (Integrated Development Environment 集成开发环境) 
是用来开发 Selenium 测试用例的工具。这是一个易于使用的 Chrome 和 Firefox 
浏览器扩展，通常是开发测试用例最有效率的方式。它使用现有的 Selenium 命令记录用户在浏览器中的操作,
参数由元素的上下文确定。这不仅节省了开发时间，而且是学习 Selenium 脚本语法的一种很好的方法。

## Grid

Selenium Grid allows you to run test cases in different 
machines across different platforms. The control of 
triggering the test cases is on the local end, and 
when the test cases are triggered, they are automatically 
executed by the remote end.

After the development of the WebDriver tests, you may face 
the need of running your tests on multiple browser and 
operating system combinations.
This is where _[Grid]({{< ref "/grid/_index.md" >}})_ comes into the picture.
