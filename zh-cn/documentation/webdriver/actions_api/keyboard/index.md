# 键盘操作

> 一种适用于任何与网页交互的按键输入设备的表现形式.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/keyboard/

---

只有 2 个操作可以使用键盘完成:
按下某个键，以及释放一个按下的键.
除了支持 ASCII 字符外，每个键盘按键还具有
可以按特定顺序按下或释放的表现形式.

## 按键

除了由常规unicode表示的按键，
其他键盘按键被分配了一些unicode值以用于操作Selenium
每种语言都有自己的方式来援引这些按键;
[这里](https://www.w3.org/TR/webdriver/#keyboard-actions)
可以找到完整列表

**Java**

Use the [Java Keys enum](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/java/src/org/openqa/selenium/Keys.java#L28)

**Python**

Use the [Python Keys class](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/py/selenium/webdriver/common/keys.py#L23)

**CSharp**

Use the [.NET static Keys class](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/dotnet/src/webdriver/Keys.cs#L28)

**Ruby**

Use the [Ruby KEYS constant](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/rb/lib/selenium/webdriver/common/keys.rb#L28)

**JavaScript**

Use the [JavaScript KEYS constant](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/javascript/node/selenium-webdriver/lib/input.js#L44)

**Kotlin**

Use the [Java Keys enum](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/java/src/org/openqa/selenium/Keys.java#L28)

## 按下按键

**Java**

```java
new Actions(driver)
        .keyDown(Keys.SHIFT)
        .sendKeys("a")
        .perform();
```

**Python**

```python
ActionChains(driver)\
    .key_down(Keys.SHIFT)\
    .send_keys("abc")\
    .perform()
```

**CSharp**

```csharp
.KeyDown(Keys.Shift)
.SendKeys("a")
.Perform();
```

**Ruby**

```ruby
driver.action
      .key_down(:shift)
      .send_keys('a')
      .perform
```

**JavaScript**

```javascript
await driver.actions()
  .keyDown(Key.SHIFT)
  .sendKeys('a')
  .perform()
```

**Kotlin**

```kotlin
.keyDown(Keys.SHIFT)
.sendKeys("a")
.perform()
```

## 释放按键

**Java**

```java
new Actions(driver)
        .keyDown(Keys.SHIFT)
        .sendKeys("a")
        .keyUp(Keys.SHIFT)
        .sendKeys("b")
        .perform();
```

**Python**

```python
ActionChains(driver)\
    .key_down(Keys.SHIFT)\
    .send_keys("a")\
    .key_up(Keys.SHIFT)\
    .send_keys("b")\
    .perform()
```

**CSharp**

```csharp
new Actions(driver)
    .KeyDown(Keys.Shift)
    .SendKeys("a")
    .KeyUp(Keys.Shift)
    .SendKeys("b")
    .Perform();
```

**Ruby**

```ruby
driver.action
      .key_down(:shift)
      .send_keys('a')
      .key_up(:shift)
      .send_keys('b')
      .perform
```

**JavaScript**

```javascript
await driver.actions()
  .keyDown(Key.SHIFT)
  .sendKeys('a')
  .keyUp(Key.SHIFT)
  .sendKeys('b')
  .perform()
```

**Kotlin**

```kotlin
.keyDown(Keys.SHIFT)
.sendKeys("a")
.keyUp(Keys.SHIFT)
.sendKeys("b")
.perform()
```

## 键入

这是Actions API的一种便捷方法, 
它将 keyDown 和 keyUp 命令组合在一个操作中.
执行此命令与使用 element 方法略有不同, 
但这主要用于，需要在其他操作之间键入多个字符时使用.

### 活跃元素

**Java**

```java
new Actions(driver)
        .sendKeys("abc")
        .perform();
```

**Python**

```python
ActionChains(driver)\
    .send_keys("abc")\
    .perform()
```

**CSharp**

```csharp

new Actions(driver)
    .SendKeys("abc")
```

**Ruby**

```ruby
driver.action
      .send_keys('abc')
      .perform
```

**JavaScript**

```javascript
await driver.actions()
  .sendKeys('abc')
  .perform()
```

**Kotlin**

```kotlin
.sendKeys("abc")
.perform()
```

### 指定元素

**Java**

```java
new Actions(driver)
        .sendKeys(textField, "Selenium!")
        .perform();
```

**Python**

```python
text_input = driver.find_element(By.ID, "textInput")
ActionChains(driver)\
    .send_keys_to_element(text_input, "abc")\
    .perform()
```

**CSharp**

```csharp
driver.FindElement(By.TagName("body")).Click();

IWebElement textField = driver.FindElement(By.Id("textInput"));
new Actions(driver)
```

**Ruby**

```ruby
text_field = driver.find_element(id: 'textInput')
driver.action
      .send_keys(text_field, 'Selenium!')
      .perform
```

**JavaScript**

_Requires [Selenium v4.5.0](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.5.0.0) or later._

```javascript
const textField = await driver.findElement(By.id('textInput'))

await driver.actions()
  .sendKeys(textField, 'abc')
  .perform()
```

**Kotlin**

```kotlin
Actions(driver)
        .sendKeys(textField, "Selenium!")
        .perform()
```

## 复制粘贴

下面是使用上述所有方法执行复制/粘贴操作的示例.
请注意, 用于此操作的键位会有所不同, 具体取决于它是否是 Mac OS.
此代码将以文本收尾: `SeleniumSelenium!`

**Java**

```java
Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

WebElement textField = driver.findElement(By.id("textInput"));
new Actions(driver)
        .sendKeys(textField, "Selenium!")
        .sendKeys(Keys.ARROW_LEFT)
        .keyDown(Keys.SHIFT)
        .sendKeys(Keys.ARROW_UP)
        .keyUp(Keys.SHIFT)
        .keyDown(cmdCtrl)
        .sendKeys("xvv")
        .keyUp(cmdCtrl)
        .perform();

Assertions.assertEquals("SeleniumSelenium!", textField.getAttribute("value"));
```

**Python**

```python
cmd_ctrl = Keys.COMMAND if sys.platform == 'darwin' else Keys.CONTROL

ActionChains(driver)\
    .send_keys("Selenium!")\
    .send_keys(Keys.ARROW_LEFT)\
    .key_down(Keys.SHIFT)\
    .send_keys(Keys.ARROW_UP)\
    .key_up(Keys.SHIFT)\
    .key_down(cmd_ctrl)\
    .send_keys("xvv")\
    .key_up(cmd_ctrl)\
    .perform()
```

**CSharp**

```csharp

var capabilities = ((WebDriver)driver).Capabilities;
String platformName = (string)capabilities.GetCapability("platformName");

String cmdCtrl = platformName.Contains("mac") ? Keys.Command : Keys.Control;

new Actions(driver)
    .SendKeys("Selenium!")
    .SendKeys(Keys.ArrowLeft)
    .KeyDown(Keys.Shift)
    .SendKeys(Keys.ArrowUp)
```

**Ruby**

```ruby
driver.action
      .send_keys('Selenium!')
      .send_keys(:arrow_left)
      .key_down(:shift)
      .send_keys(:arrow_up)
      .key_up(:shift)
      .key_down(cmd_ctrl)
      .send_keys('xvv')
      .key_up(cmd_ctrl)
      .perform
```

**JavaScript**

```javascript
const cmdCtrl = platform.includes('darwin') ? Key.COMMAND : Key.CONTROL

await driver.actions()
  .click(textField)
  .sendKeys('Selenium!')
  .sendKeys(Key.ARROW_LEFT)
  .keyDown(Key.SHIFT)
  .sendKeys(Key.ARROW_UP)
  .keyUp(Key.SHIFT)
  .keyDown(cmdCtrl)
  .sendKeys('xvv')
  .keyUp(cmdCtrl)
  .perform()
```

**Kotlin**

```kotlin

val textField = driver.findElement(By.id("textInput"))
Actions(driver)
        .sendKeys(textField, "Selenium!")
        .sendKeys(Keys.ARROW_LEFT)
        .keyDown(Keys.SHIFT)
        .sendKeys(Keys.ARROW_UP)
        .keyUp(Keys.SHIFT)
        .keyDown(cmdCtrl)
        .sendKeys("xvv")
        .keyUp(cmdCtrl)
        .perform()
```
