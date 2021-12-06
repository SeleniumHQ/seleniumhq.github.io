---
title: "文件下载"
linkTitle: "文件下载"
weight: 2
aliases: [
"/documentation/zh-cn/worst_practices/file_downloads/",
"/zh-cn/documentation/worst_practices/file_downloads/"
] 
---


虽然可以通过在Selenium的控制下单击浏览器的链接来开始下载, 但是API并不会暴露下载进度, 因此这是一种不理想的测试下载文件的方式.
因为下载文件并非模拟用户与Web平台交互的重要方面. 取而代之的是, 应使用Selenium(以及任何必要的cookie)查找链接, 并将其传递给例如[libcurl](//curl.haxx.se/libcurl/)这样的HTTP请求库.


[HtmlUnit driver](https://github.com/SeleniumHQ/htmlunit-driver) 
可以通过实现[AttachmentHandler](https://htmlunit.sourceforge.io/apidocs/com/gargoylesoftware/htmlunit/attachment/AttachmentHandler.html) 
接口将附件作为输入流进行访问来下载附件. 
可以将AttachmentHandler添加到
[HtmlUnit](https://htmlunit.sourceforge.io/) WebClient.
