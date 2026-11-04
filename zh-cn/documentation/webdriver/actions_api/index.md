# Actions接口

> 用于向 Web 浏览器提供虚拟化设备输入操作的低级接口.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/

---

除了高级[元素交互](https://www.selenium.dev/zh-cn/documentation/webdriver/elements/interactions/index.md)之外, 
[Actions 接口](https://w3c.github.io/webdriver/#dfn-actions) 
还提供了对指定输入设备
可以执行的确切操作的精细控制.
Selenium为3种输入源提供了接口：
键盘设备的键输入, 鼠标, 笔或触摸设备的输入, 以及滚轮设备的滚轮输入
(在Selenium 4.2中引入).
Selenium允许您构建分配给特定输入的独立操作命令,
会将他们链接在一起,
并调用关联的执行方法以一次执行它们.

## Action构造器

在从遗留JSON Wire协议迁移到
新的W3C WebDriver协议的过程中, 
低级的操作构建块变得特别详细. 
它非常强大, 
但每个输入设备都有多种使用方法, 
如果您需要管理多个设备, 
则负责确保他们之间的同步正确.  

值得庆幸的是, 
您可能不需要学习如何直接使用低级命令, 
因为您可能要执行的几乎所有操作, 
都已提供了相应的简便方法, 
这些方法可以为您组合较低级别的命令. 
请分别参阅相应的[键盘](https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/keyboard/index.md), 
[鼠标](https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/mouse/index.md), 
[笔](https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/pen/index.md) 
和[滚轮](https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/wheel/index.md) 页面. 

## 暂停

指针移动和滚轮滚动
允许用户设置操作的持续时间, 
但有时您只需要在操作之间等待一下, 
即可正常工作.

**Java**

```java
WebElement clickable = driver.findElement(By.id("clickable"));
new Actions(driver)
        .moveToElement(clickable)
        .pause(Duration.ofSeconds(1))
        .clickAndHold()
        .pause(Duration.ofSeconds(1))
        .sendKeys("abc")
        .perform();
```

**Python**

```python
clickable = driver.find_element(By.ID, "clickable")
ActionChains(driver)\
    .move_to_element(clickable)\
    .pause(1)\
    .click_and_hold()\
    .pause(1)\
    .send_keys("abc")\
    .perform()
```

**CSharp**

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

```csharp
IWebElement clickable = driver.FindElement(By.Id("clickable"));
new Actions(driver)
    .MoveToElement(clickable)
    .Pause(TimeSpan.FromSeconds(1))
    .ClickAndHold()
    .Pause(TimeSpan.FromSeconds(1))
    .SendKeys("abc")
    .Perform();
```

**Ruby**

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

```ruby
clickable = driver.find_element(id: 'clickable')
driver.action
      .move_to(clickable)
      .pause(duration: 1)
      .click_and_hold
      .pause(duration: 1)
      .send_keys('abc')
      .perform
```

**JavaScript**

```javascript
const clickable = await driver.findElement(By.id('clickable'))
await driver.actions()
  .move({ origin: clickable })
  .pause(1000)
  .press()
  .pause(1000)
  .sendKeys('abc')
  .perform()
```

**Kotlin**

```kotlin
Actions(driver)
    .moveToElement(clickable)
    .pause(Duration.ofSeconds(1))
    .clickAndHold()
    .pause(Duration.ofSeconds(1))
    .sendKeys("abc")
    .perform()
```

## 释放所有Actions

需要注意的重要一点是, 
驱动程序会记住整个会话中所有输入项的状态. 
即使创建actions类的新实例, 
按下的键和指针的位置
也将处于以前执行的操作离开它们的任何状态. 

有一种特殊的方法来释放所有当前按下的键和指针按钮. 
此方法在每种语言中的实现方式不同, 
因为它不会使用perform方法执行.

**Java**

```java
((RemoteWebDriver) driver).resetInputState();
```

**Python**

```python
ActionBuilder(driver).clear_actions()
```

**CSharp**

```csharp
((WebDriver)driver).ResetInputState();
```

**Ruby**

```ruby
driver.action.release_actions
```

**JavaScript**

```javascript
await driver.actions().clear()
```

**Kotlin**

```kotlin

```

---

Section pages:

- [键盘操作](https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/keyboard/index.md): 一种适用于任何与网页交互的按键输入设备的表现形式.
- [鼠标操作](https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/mouse/index.md): 任何用于与网页进行交互的指针设备的表示形式.
- [触控笔操作](https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/pen/index.md): 一种用于与网页交互的类似笔尖的指针输入设备的表示.
- [滚轮操作](https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/wheel/index.md): 一种用于与网页交互的滚轮输入设备的示意.
