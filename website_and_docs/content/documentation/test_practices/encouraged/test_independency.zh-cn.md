---
title: "测试的独立性"
linkTitle: "测试的独立性"
weight: 9
aliases: [
"/documentation/zh-cn/guidelines_and_recommendations/test_independency/",
"/zh-cn/documentation/guidelines/test_independency/"
]
---

将每个测试编写为独立的单元.
以不依赖于其他测试完成的方式编写测试:

例如有一个内容管理系统, 
您可以借助其创建一些自定义内容, 
这些内容在发布后作为模块显示在您的网站上, 
并且CMS和应用程序之间的同步可能需要一些时间.

测试模块的一种错误方法是在测试中创建并发布内容, 
然后在另一测试中检查该模块. 
这是不可取的, 因为发布后内容可能无法立即用于其他测试.

与之相反的事, 您可以创建在受影响的测试中打开和关闭的打桩内容, 并将其用于验证模块. 
而且, 对于内容的创建, 您仍然可以进行单独的测试.
