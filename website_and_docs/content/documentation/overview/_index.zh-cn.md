---
title: "概述"
linkTitle: "概述"
weight: 1
description: >
  Selenium适合你吗? 请参见不同项目组件的概述.
aliases: ["/documentation/zh-cn/introduction/"]
---


Selenium 不仅仅是一个工具或 API, 它还包含许多工具. 

## WebDriver

如果您开始使用桌面网站测试自动化, 那么您将使用 WebDriver APIs. 
[WebDriver]({{< ref "/webdriver.md" >}}) 使用浏览器供应商提供的浏览器自动化 API 来控制浏览器和运行测试. 
这就像真正的用户正在操作浏览器一样. 
由于 WebDriver 不要求使用应用程序代码编译其 API, 因此它本质上不具有侵入性. 
因此, 您测试的应用程序与实时推送的应用程序相同. 

## Selenium IDE

[Selenium IDE](https://selenium.dev/selenium-ide) (Integrated Development Environment 集成开发环境) 
是用来开发 Selenium 测试用例的工具. 这是一个易于使用的 Chrome 和 Firefox 
浏览器扩展, 通常是开发测试用例最有效率的方式. 它使用现有的 Selenium 命令记录用户在浏览器中的操作,
参数由元素的上下文确定. 这不仅节省了开发时间, 而且是学习 Selenium 脚本语法的一种很好的方法. 


## Grid

Selenium Grid允许您在不同平台的不同机器上运行测试用例. 
可以本地控制测试用例的操作, 
当测试用例被触发时, 
它们由远端自动执行.

当开发完WebDriver测试之后, 
您可能需要在多个浏览器和操作系统的组合上运行测试. 
这就是 [Grid]({{< ref "/grid.md" >}}) 的用途所在.
