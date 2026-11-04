# Dialogs and alerts

> Reading, accepting, dismissing and typing into JavaScript alerts, confirms and prompts, and deciding what should happen to a dialog your test did not expect.

URL: https://www.selenium.dev/documentation/guides/dialogs/
Site index: https://www.selenium.dev/llms.txt

---

A page calls `alert()`, `confirm()` or `prompt()`, the browser shows a native dialog, and
every subsequent WebDriver command fails until something closes it. If the dialog appears when
your test is not expecting it, the failure is an _unexpected alert open_ error several lines
away from the cause.

## Recommended approach

All three dialogs are handled through the same object: switch to the alert, then read its text,
accept it, dismiss it, or send keys to it. `alert()` can only be accepted; `confirm()` returns
true to the page when accepted and false when dismissed; `prompt()` additionally accepts typed
text before being accepted.

Treat the dialog like any other asynchronous event and wait for it. The bindings ship an
`alertIsPresent` expected condition that returns the alert as soon as it exists, which is both
safer and simpler than switching and catching the error.

For dialogs that may appear when no test code is ready to handle them, set the
`unhandledPromptBehavior` capability when starting the session. `dismiss` and `accept` close
the dialog silently; `dismiss and notify` (the default) and `accept and notify` close it and
raise an error so you find out; `ignore` leaves it open for you to handle. Recent drivers close
`beforeunload` prompts automatically.

With WebDriver BiDi enabled, the browsing context module also emits `userPromptOpened` and
`userPromptClosed` events, so a test can react to a dialog the moment it appears rather than
discovering it on the next command.

Switch to an alert, read its text, and close it:

**Java**

```java
wait.until(ExpectedConditions.alertIsPresent());
Alert alert = driver.switchTo().alert();
Assertions.assertEquals("cheese", alert.getText());
alert.accept();
```

**Python**

```python
wait = WebDriverWait(driver, timeout=2)
alert = wait.until(EC.alert_is_present())
text = alert.text
alert.accept()
```

**Ruby**

```ruby
alert = driver.switch_to.alert

# Get the text of the alert
alert.text

# Press on Cancel button
alert.dismiss
```

**JavaScript**

```javascript
let alert = await driver.switchTo().alert();
let alertText = await alert.getText();
await alert.accept();
```

Type into a prompt before accepting it:

**Java**

```java
wait.until(ExpectedConditions.alertIsPresent());

Alert alert = driver.switchTo().alert();
Assertions.assertEquals("Enter something", alert.getText());

alert.sendKeys("Selenium");
alert.accept();
```

**Python**

```python
wait = WebDriverWait(driver, timeout=2)
alert = wait.until(EC.alert_is_present())
alert.send_keys("Selenium")
text = alert.text
alert.accept()
```

**Ruby**

```ruby
alert = driver.switch_to.alert

# Type a message
alert.send_keys('selenium')

# Press on Ok button
alert.accept
```

**JavaScript**

```javascript
let alert = await driver.switchTo().alert();
//Type your message
await alert.sendKeys(text);
await alert.accept();
```

Decide up front what happens to an unexpected dialog:

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
```

**Python**

```python
options = get_default_chrome_options()
options.unhandled_prompt_behavior = 'accept'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
var chromeOptions = new ChromeOptions();
chromeOptions.UnhandledPromptBehavior = UnhandledPromptBehavior.DismissAndNotify;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.unhandled_prompt_behavior = :accept
```

## Common errors

* [NoAlertPresentException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#noalertpresentexception):
  you switched to an alert before it appeared, or the session's prompt handler already closed it.
  Wait for it with `alertIsPresent`, and check `unhandledPromptBehavior`.
* [UnhandledAlertException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#unhandledalertexception):
  a dialog opened that nothing handled, and the driver closed it before running your command.
  Either handle the dialog in the test or change the prompt handler.

## Reference

* [JavaScript alerts, prompts and confirmations](https://www.selenium.dev/documentation/webdriver/interactions/alerts/index.md)
* [unhandledPromptBehavior](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md#unhandledpromptbehavior)
* [Expected conditions](https://www.selenium.dev/documentation/webdriver/support_features/expected_conditions/index.md)
* [BiDi browsing context events](https://www.selenium.dev/documentation/webdriver/bidi/w3c/browsing_context/index.md#user-prompt-opened-event)
