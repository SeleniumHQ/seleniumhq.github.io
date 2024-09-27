---
title: "理解常见的异常"
linkTitle: "异常"
weight: 2
description: >
  如何处理Selenium代码中的各种问题.
---

## 无效选择器的异常 (InvalidSelectorException)

某些时候难以获得正确的CSS以及XPath选择器。

### 潜在原因

您尝试使用的CSS或XPath选择器包含无效字符或无效查询。

### 可行方案

通过验证器服务运行选择器：
* [CSS 验证器](http://csslint.net/)
* [xPath 验证器](http://www.freeformatter.com/xpath-tester.html)

或者使用浏览器扩展程序来获取已知的良好值：
* [SelectorsHub](https://selectorshub.com/selectorshub/)

## 没有这样元素的异常 (NoSuchElementException)

在您尝试找到该元素的当前时刻无法定位元素。

### 潜在原因

* 您在错误的位置寻找元素 (也许以前的操作不成功)
* 您在错误的时间寻找元素 (该元素尚未显示在 DOM 中)
* 自您编写代码以来定位器已变更

### 可行方案

* 确保您位于期望的页面上，并且代码中的前置操作已正确完成
* 确保您使用的是正确的 [等待策略]({{< ref "/documentation/webdriver/waits" >}})
* 使用浏览器的devtools控制台更新定位器或使用浏览器扩展程序，例如:
  * [SelectorsHub](https://selectorshub.com/selectorshub/)

## 过时元素引用的异常 (StaleElementReferenceException)

当成功定位到元素时，
WebDriver会为其设置一个引用ID作为标记，
如果由于上下文环境发生变化，
导致之前元素的位置发生了变化或者无法找到了，
WebDriver并不会自动重新定位，
任何使用之前元素所做的操作将报错该异常。

### 常见因素

以下情况可能发生此异常:

* 您已刷新页面，或者页面的 DOM 已动态更改。
* 您已导航到其他页面。
* 您已切换到另一个窗口，或者进入/移出某个 `frame` / `iframe`。

### 常见方案

**DOM已变更**

当页面刷新或页面上的项目各处移动时，
页面上仍然有一个具有所需定位器的元素，
它只是不再被正在使用的元素对象访问，
并且必须重新定位该元素才能再次使用。

这往往通过以下两种方式之一完成：

* 每次使用时都要重新定位元素。
尽管有可能元素在定位和使用元素之间的微秒内，
发生变化的可能性很小。
缺点是这不是最有效的方法，
尤其是在 `Remote Grid`上运行时。

* 用另一个存储定位器的对象包装 Web 元素，并缓存定位的 Selenium 元素。 
对该包装对象执行操作时，您可以尝试使用之前找到的缓存对象，
如果它是发生了变化，则可以捕获异常，
使用存储的定位器重新定位元素，并重试该方法。
这样效率更高，但如果您使用的定位器在页面更改后引用了不同的元素（而不是您想要的元素），则可能会导致问题。

**上下文已变更**

元素对象是针对特定的上下文存储的，
因此如果您切换到不同的上下文，
比如不同的 `Window` 或不同的 `frame` 或 `iframe` 元素引用仍然有效，
但暂时无法访问。在这种情况下，
重新定位元素无济于事，因为它在当前上下文中不存在。

要解决此问题，您需要确保在使用该元素之前切换回正确的上下文。

**页面已变更**

这种情况发生在您不仅更改了上下文，
而且导航到另一个页面并破坏了元素所在的上下文。
您无法仅从当前上下文重新定位它，
也无法切换回元素有效的活动上下文。
如果这是您的错误原因，
您必须回到正确的位置并重新定位元素。

## ElementClickInterceptedException

This exception occurs when Selenium tries to click an element, but the click would instead 
be received by a different element. Before Selenium will click an element, it checks if the 
element is visible, unobscured by any other elements, and enabled - if the element is obscured, 
it will raise this exception.

### Likely Cause

**UI Elements Overlapping** 

Elements on the UI are typically placed next to each other, but occasionally elements may overlap. 
For example, a navbar always staying at the top of your window as you scroll a page. If that navbar 
happens to be covering an element we are trying to click, Selenium might believe it to be visible 
and enabled, but when you try to click it will throw this exception. Pop-ups and Modals are also 
common offenders here.

**Animations** 

Elements with animations have the potential to cause this exception as well - it is recommended 
to wait for animations to cease before attempting to click an element.

### Possible Solutions

**Use Explicit Waits** 

[Explicit Waits]({{< ref "/documentation/webdriver/waits" >}}) will likely be your best friend 
in these instances. A great way is to use `ExpectedCondition.ToBeClickable()` 
with `WebDriverWait` to wait until the right moment.

**Scroll the Element into View** 

In instances where the element is out of view, but Selenium still registers the element as visible 
(e.g. navbars overlapping a section at the top of your screen), you can use the 
`WebDriver.executeScript()` method to execute a javascript function to scroll 
(e.g. `WebDriver.executeScript('window.scrollBy(0,-250)')`) or you can utilize the 
Actions class with `Actions.moveToElement(element)`.

## 无效SessionId异常
有时您尝试访问的会话与当前可用的会话不同。

### 可能原因
通常发生在会话被删除时（例如：`driver.quit()`）或会话发生更改时，例如最后一个标签页/浏览器已关闭（例如：`driver.close()`）。

### 可能的解决方案
检查脚本中是否有 `driver.close()` 和 `driver.quit()` 的实例，以及其他可能导致标签页/浏览器关闭的原因。可能是您在应该/能够定位元素之前就尝试定位了该元素。
