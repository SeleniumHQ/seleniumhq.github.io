---
title: "使用定位器的提示"
linkTitle: "定位器"
weight: 8
description: >
   何时使用哪些定位器以及如何在代码中最好地管理它们.
---

这里有一些 [支持的定位策略]({{< ref "/documentation/webdriver/elements/locators.md" >}}) 的例子 .

一般来说，如果 HTML 的 id 是可用的、唯一的且是可预测的，那么它就是在页面上定位元素的首选方法。它们的工作速度非常快，可以避免复杂的 DOM 遍历带来的大量处理。

如果没有唯一的 id，那么最好使用写得好的 CSS 选择器来查找元素。XPath 和 CSS 选择器一样好用，但是它语法很复杂，并且经常很难调试。尽管 XPath 选择器非常灵活，但是他们通常未经过浏览器厂商的性能测试，并且运行速度很慢。

基于链接文本和部分链接文本的选择策略有其缺点，即只能对链接元素起作用。此外，它们在 WebDriver 内部调用 [querySelectorAll](https://www.w3.org/TR/webdriver/#link-text) 选择器。

标签名可能是一种危险的定位元素的方法。页面上经常出现同一标签的多个元素。这在调用 _findElements(By)_ 方法返回元素集合的时候非常有用。

建议您尽可能保持定位器的紧凑性和可读性。使用 WebDriver 遍历 DOM 结构是一项性能花销很大的操作，搜索范围越小越好。
