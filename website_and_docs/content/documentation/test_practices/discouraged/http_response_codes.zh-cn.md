---
title: "HTTP响应码"
linkTitle: "HTTP响应码"
weight: 3
aliases: [
"/documentation/zh-cn/worst_practices/http_response_codes/",
"/zh-cn/documentation/worst_practices/http_response_codes/"
] 
---

对于Selenium RC中的某些浏览器配置，
Selenium充当了浏览器和自动化站点之间的代理.
这意味着可以捕获或操纵通过Selenium传递的所有浏览器流量.
 `captureNetworkTraffic()` 方法旨在捕获浏览器和自动化站点之间的所有网络流量，包括HTTP响应码.

Selenium WebDriver是一种完全不同的浏览器自动化实现，
它更喜欢表现得像用户一样，这种方式来自于基于WebDriver编写测试的方式.
在自动化功能测试中，检查状态码并不是测试失败的特别重要的细节, 之前的步骤更重要.

浏览器将始终呈现HTTP状态代码，例如404或500错误页面. 
遇到这些错误页面时，一种“快速失败”的简单方法是
在每次加载页面后检查页面标题或可信赖点的内容（例如 `<h1>` 标签）. 
如果使用的是页面对象模型，则可以将此检查置于类构造函数中或类似于期望的页面加载的位置. 
有时，HTTP代码甚至可能出现在浏览器的错误页面中，
您可以使用WebDriver读取此信息并改善调试输出.

检查网页本身的一种理想实践是符合WebDriver的呈现以及用户的视角.

如果您坚持，捕获HTTP状态代码的高级解决方案是复刻Selenium RC的行为去使用代理. 
WebDriver API提供了为浏览器设置代理的功能，
并且有许多代理可以通过编程方式来操纵发送到Web服务器和从Web服务器接收的请求的内容. 
使用代理可以决定如何响应重定向响应代码. 
此外，并非每个浏览器都将响应代码提供给WebDriver，
因此选择使用代理可以使您拥有适用于每个浏览器的解决方案.
