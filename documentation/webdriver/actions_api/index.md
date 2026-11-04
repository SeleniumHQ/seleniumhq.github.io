# Actions API

> A low-level interface for providing virtualized device input actions to the web browser.

URL: https://www.selenium.dev/documentation/webdriver/actions_api/
Site index: https://www.selenium.dev/llms.txt

---

In addition to the high-level [element interactions](https://www.selenium.dev/documentation/webdriver/elements/interactions/index.md), 
the [Actions API](https://w3c.github.io/webdriver/#dfn-actions) provides granular control over
exactly what designated input devices can do. Selenium provides an interface for 3 kinds of input sources: 
a key input for keyboard devices, a pointer input for a mouse, pen or touch devices, 
and wheel inputs for scroll wheel devices (introduced in Selenium 4.2). 
Selenium allows you to construct individual action commands assigned to specific
inputs and chain them together and call the associated perform method to execute them all at once.

## Action Builder

In the move from the legacy JSON Wire Protocol to the new W3C WebDriver Protocol,
the low level building blocks of actions became especially detailed. It is extremely
powerful, but each input device has a number of ways to use it and if you need to 
manage more than one device, you are responsible for ensuring proper synchronization between them.

Thankfully, you likely do not need to learn how to use the low level commands directly, since
almost everything you might want to do has been given a convenience method that combines the 
lower level commands for you. These are all documented in 
[keyboard](https://www.selenium.dev/documentation/webdriver/actions_api/keyboard/index.md), [mouse](https://www.selenium.dev/documentation/webdriver/actions_api/mouse/index.md), [pen](https://www.selenium.dev/documentation/webdriver/actions_api/pen/index.md), and [wheel](https://www.selenium.dev/documentation/webdriver/actions_api/wheel/index.md) pages.

## Pause

Pointer movements and Wheel scrolling allow the user to set a duration for the action, but sometimes you just need
to wait a beat between actions for things to work correctly.

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

## Release All Actions

An important thing to note is that the driver remembers the state of all the input
items throughout a session. Even if you create a new instance of an actions class, the depressed keys and
the location of the pointer will be in whatever state a previously performed action left them.

There is a special method to release all currently depressed keys and pointer buttons.
This method is implemented differently in each of the languages because
it does not get executed with the perform method.

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

- [Keyboard actions](https://www.selenium.dev/documentation/webdriver/actions_api/keyboard/index.md): A representation of any key input device for interacting with a web page.
- [Mouse actions](https://www.selenium.dev/documentation/webdriver/actions_api/mouse/index.md): A representation of any pointer device for interacting with a web page.
- [Pen actions](https://www.selenium.dev/documentation/webdriver/actions_api/pen/index.md): A representation of a pen stylus kind of pointer input for interacting with a web page.
- [Scroll wheel actions](https://www.selenium.dev/documentation/webdriver/actions_api/wheel/index.md): A representation of a scroll wheel input device for interacting with a web page.
