---
title: "Actions接口"
linkTitle: "Actions接口"
weight: 14
description: >
    用于向web浏览器提供虚拟设备输入的底层接口.
---

与执行额外验证的高级
[元素交互]({{< ref "/documentation/webdriver/elements/interactions.md" >}}) 不同,
[Actions接口](https://w3c.github.io/webdriver/#dfn-actions) 
提供了对输入设备的细粒度控制.

Selenium提供3种输入源:
键盘设备的键位输入,
鼠标、笔或触摸设备的指针输入,
以及支持滚轮的滚轮输入.
