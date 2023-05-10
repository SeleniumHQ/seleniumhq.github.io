---
title: "双向协议"
linkTitle: "双向协议"
weight: 16
aliases: [
"/documentation/zh-cn/webdriver/bidi_apis/",
"/zh-cn/documentation/webdriver/bidi_apis/",
"/zh-cn/documentation/webdriver/bidirectional/bidi_api_remotewebdriver"
]
---

Selenium正在与浏览器供应商合作创建
[WebDriver双向协议](https://w3c.github.io/webdriver-bidi/) ,
作为一种提供稳定的跨浏览器API的方法,
该API使用双向协议处理
各种浏览器的通用自动化以及特定测试的需求.
在此之前, 寻求此功能的用户
必须忍受当前实现的全部问题和局限.


严格限制请求响应命令的传统WebDriver模型, 
将从user agent转变为基于WebSockets的软件控制, 
通过这样完善流事件的能力, 
以便更好地匹配浏览器DOM的事件性质.

因为将测试受限于特定浏览器的特定版本是个坏主意, 
Selenium项目建议尽可能使用WebDriver BiDi.
然而, 在规范完成之前, CDP提供了许多有用的东西.
为了帮助保持测试的独立性和可移植性, 
Selenium提供了一些有用的辅助类.
目前, 这些应用程序使用CDP, 
但我们将尽快提供WebDriver Bidi的实现.