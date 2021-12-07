---
title: "性能测试"
linkTitle: "性能测试"
weight: 6
aliases: [
"/documentation/zh-cn/worst_practices/performance_testing/",
"/zh-cn/documentation/worst_practices/performance_testing/"
] 
---

通常不建议使用Selenium和WebDriver进行性能测试. 
并非因为不能做, 只是缺乏针对此类工作的优化, 
因而难以得到乐观的结果.

对于用户而言, 在用户上下文中执行性能测试似乎是自然而然的选择, 
但是WebDriver的测试会受到许多外部和内部的影响而变得脆弱, 
这是您无法控制的. 例如, 
浏览器的启动速度, HTTP服务器的速度, 
托管JavaScript或CSS的第三方服务器的响应
以及WebDriver实现本身检测的损失. 
这些因素的变化会影响结果. 
很难区分网站自身与外部资源之间的性能差异, 
并且也很难明确浏览器中使用WebDriver对性能的影响, 
尤其是在注入脚本时.

另一个潜在的吸引点是"节省时间"-同时执行功能和性能测试. 
但是, 功能和性能测试分别具有截然不同的目标. 
要测试功能, 测试人员可能需要耐心等待加载, 
但这会使性能测试结果蒙上阴影, 反之亦然.

为了提高网站的性能, 您需要不依赖于环境的差异来分析整体性能, 
识别不良代码的实践, 
对单个资源 (即CSS或JavaScript) 的性能进行细分
以了解需要改进的地方. 
有很多性能测试工具已经可以完成这项工作, 
并且提供了报告和分析结果, 甚至可以提出改进建议.

例如一种易于使用的 (开源) 软件包是: [JMeter](//jmeter.apache.org/)
