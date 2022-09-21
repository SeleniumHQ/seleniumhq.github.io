---
title: "Actions接口"
linkTitle: "Actions接口"
weight: 14
description: >
    用于向 Web 浏览器提供虚拟化设备输入操作的低级接口.
---

除了高级[元素交互]({{< ref "/documentation/webdriver/elements/interactions.md" >}})之外, 
[Actions 接口](https://w3c.github.io/webdriver/#dfn-actions) 
还提供了对指定输入设备
可以执行的确切操作的精细控制.
Selenium为3种输入源提供了接口：
键盘设备的键输入, 鼠标, 笔或触摸设备的输入, 以及滚轮设备的滚轮输入
(在Selenium 4.2中引入).
Selenium允许您构建分配给特定输入的独立操作命令,
会将他们链接在一起,
并调用关联的执行方法以一次执行它们.

## Action构造器

在从遗留JSON Wire协议迁移到
新的W3C WebDriver协议的过程中, 
低级的操作构建块变得特别详细. 
它非常强大, 
但每个输入设备都有多种使用方法, 
如果您需要管理多个设备, 
则负责确保他们之间的同步正确.  

值得庆幸的是, 
您可能不需要学习如何直接使用低级命令, 
因为您可能要执行的几乎所有操作, 
都已提供了相应的简便方法, 
这些方法可以为您组合较低级别的命令. 
请分别参阅相应的[键盘]({{< ref "keyboard" >}}), 
[鼠标]({{< ref "mouse" >}}), 
[笔]({{< ref "pen" >}}) 
和[滚轮]({{< ref "wheel" >}}) 页面. 

## 暂停

指针移动和滚轮滚动
允许用户设置操作的持续时间, 
但有时您只需要在操作之间等待一下, 
即可正常工作.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/ActionsTest.java#L21-L28" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_actions.py#L13-L20" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/ActionsTest.cs#L18-L25" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/actions_spec.rb#L12-L19" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/actionsTest.spec.js#L20-L27" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/ActionsTest.kt#L22-L29" >}}
{{< /tab >}}
{{< /tabpane >}}

## 释放所有Actions

需要注意的重要一点是, 
驱动程序会记住整个会话中所有输入项的状态. 
即使创建actions类的新实例, 
按下的键和指针的位置
也将处于以前执行的操作离开它们的任何状态. 

有一种特殊的方法来释放所有当前按下的键和指针按钮. 
此方法在每种语言中的实现方式不同, 
因为它不会使用perform方法执行.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/ActionsTest.java#L46" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_actions.py#L37" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/ActionsTest.cs#L44" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/actions_spec.rb#L36" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/actionsTest.spec.js#L44" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/ActionsTest.kt#L47" >}}
{{< /tab >}}
{{< /tabpane >}}
