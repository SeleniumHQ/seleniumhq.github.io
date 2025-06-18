---
title: "故障排除协助"
linkTitle: "故障排除"
weight: 20
description: >
  如何管理 WebDriver 的问题.
---

Selenium错误的根本原因并不总是很明显.

1. 最常见的Selenium相关错误, 是源自未及时同步的结果.
   请阅读 [等待策略]({{< ref "../waits" >}}).
   当遇到一个问题, 如果不确定是否因为同步策略,
   您可以尝试*暂时*硬编码一个较大的休眠时间,
   您将明确添加显式等待是否有帮助.

2. 请注意, 报告给项目的许多错误,
   实际上是由Selenium向其发送命令的基础驱动程序所引起的.
   您可以通过执行 [浏览器]({{< ref "../browsers/" >}}) 中的
   多个命令来解决驱动程序问题.

3. 如果您对如何执行有疑惑,
   请查看 [支持选项](/support/) 获取帮助的方法.

4. 如果您认为您发现了Selenium代码的问题,
   请在Github上提交 [问题报告](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+).


