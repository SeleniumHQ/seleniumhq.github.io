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

While the specification is in works, the browser vendors are parallely implementing
the [WebDriver BiDirectional Protocol](https://w3c.github.io/webdriver-bidi/).
Refer [web-platform-tests dashboard](https://wpt.fyi/results/webdriver/tests/bidi?label=experimental&label=master&aligned&view=subtest)
to see how far along the browser vendors are.
Selenium is trying to keep up with the browser vendors and has started implementing W3C BiDi APIs.
The goal is to ensure APIs are W3C compliant and uniform among the different language bindings.

However, until the specification and corresponding Selenium implementation is complete there are many useful things that
CDP offers. Selenium offers some useful helper classes that use CDP.