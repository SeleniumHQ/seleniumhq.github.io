---
title: "爬取链接"
linkTitle: "爬取链接"
weight: 7
aliases: [
"/documentation/zh-cn/worst_practices/link_spidering/",
"/zh-cn/documentation/worst_practices/link_spidering/"
] 
---

建议您不要使用WebDriver来通过链接进行爬网，
并非因为无法完成，而是因为它绝对不是最理想的工具。
WebDriver需要一些时间来启动，并且可能要花几秒钟到一分钟的时间，
具体取决于测试的编写方式，仅仅是为了获取页面并遍历DOM.

除了使用WebDriver之外，
您还可以通过执行 [curl](//curl.haxx.se/) 命令或
使用诸如BeautifulSoup之类的库来节省大量时间，
因为这些方法不依赖于创建浏览器和导航至页面.
通过不使用WebDriver可以节省大量时间.

