# Browsing Context

URL: https://www.selenium.dev/ja/documentation/webdriver/bidi/w3c/input/

---

> <p class="lead">
>    <i class="fas fa-language d-4"></i>
>    Page being translated from
>    English to Japanese. Do you speak Japanese? Help us to translate
>    it by sending us pull requests!
> 

This section contains the APIs related to input commands. 

## Perform Actions

**Java**

_Requires [Selenium v4.17](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.17.0) or later._

```java
Actions selectThreeOptions =
        actions.click(options.get(1)).keyDown(Keys.SHIFT).click(options.get(3)).keyUp(Keys.SHIFT);

input.perform(windowHandle, selectThreeOptions.getSequences());
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.17](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.17.0) or later._

```javascript
const actions = driver.actions().click(options[1]).keyDown(Key.SHIFT).click(options[3]).keyUp(Key.SHIFT).getSequences()

await input.perform(browsingContextId, actions)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Release Actions

**Java**

_Requires [Selenium v4.17](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.17.0) or later._

```java
Actions sendLowercase =
        new Actions(driver).keyDown(inputTextBox, "a").keyDown(inputTextBox, "b");

input.perform(windowHandle, sendLowercase.getSequences());
((JavascriptExecutor) driver).executeScript("resetEvents()");

input.release(windowHandle);
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.17](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.17.0) or later._

```javascript
await input.release(browsingContextId)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._
