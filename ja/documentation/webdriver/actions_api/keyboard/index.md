# Keyboard actions

> A representation of any key input device for interacting with a web page.

URL: https://www.selenium.dev/ja/documentation/webdriver/actions_api/keyboard/

---

There are only 2 actions that can be accomplished with a keyboard:
pressing down on a key, and releasing a pressed key.
In addition to supporting ASCII characters, each keyboard key has
a representation that can be pressed or released in designated sequences.

## Keys

In addition to the keys represented by regular unicode, 
unicode values have been assigned to other keyboard keys for use with Selenium. 
Each language has its own way to reference these keys; the full list can be found
[here](https://www.w3.org/TR/webdriver/#keyboard-actions).

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

## Key down

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

## Key up

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

## Send keys

This is a convenience method in the Actions API that combines keyDown and keyUp commands in one action.
Executing this command differs slightly from using the element method, but
primarily this gets used when needing to type multiple characters in the middle of other actions.

### Active Element

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

### Designated Element

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

## Copy and Paste

Here's an example of using all of the above methods to conduct a copy / paste action.
Note that the key to use for this operation will be different depending on if it is a Mac OS or not.
This code will end up with the text: `SeleniumSelenium!`

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
