# JavaScript alerts, prompts and confirmations

URL: https://www.selenium.dev/documentation/webdriver/interactions/alerts/
Site index: https://www.selenium.dev/llms.txt

---

WebDriver provides an API for working with the three types of native
popup messages offered by JavaScript. These popups are styled by the
browser and offer limited customisation.

## Alerts

The simplest of these is referred to as an alert, which shows a
custom message, and a single button which dismisses the alert, labelled
in most browsers as OK. It can also be dismissed in most browsers by
pressing the close button, but this will always do the same thing as
the OK button. <a onclick="window.alert('Sample alert')">See an example alert</a>.

WebDriver can get the text from the popup and accept or dismiss these
alerts.

**Java**

```java

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertsTest extends BaseTest {

    @BeforeEach
```

**Python**

```python
element = driver.find_element(By.LINK_TEXT, "See an example alert")
element.click()

wait = WebDriverWait(driver, timeout=2)
alert = wait.until(EC.alert_is_present())
text = alert.text
alert.accept()
```

**CSharp**

```csharp
// Click the link to activate the alert
driver.FindElement(By.LinkText("See an example alert")).Click();

// Wait for the alert to be displayed and store it in a variable
IAlert alert = wait.Until(d => d.SwitchTo().Alert());

// Store the alert text in a variable
string text = alert.Text;

// Press the OK button
alert.Accept();
```

**Ruby**

```ruby
# Store the alert reference in a variable
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

**Kotlin**

```kotlin
// Click the link to activate the alert
driver.findElement(By.linkText("See an example alert")).click()

// Wait for the alert to be displayed and store it in a variable
val alert = wait.until(ExpectedConditions.alertIsPresent())

// Store the alert text in a variable
val text = alert.text

// Press the OK button
alert.accept()
```

## Confirm

A confirm box is similar to an alert, except the user can also choose
to cancel the message. <a onclick="window.confirm('Are you sure?')">See
a sample confirm</a>.

This example also shows a different approach to storing an alert:

**Java**

```java
    Assertions.assertEquals("Slow", alert.getText());

    alert.accept();

}

@Test
```

**Python**

```python
element = driver.find_element(By.LINK_TEXT, "See a sample confirm")
driver.execute_script("arguments[0].click();", element)

wait = WebDriverWait(driver, timeout=2)
alert = wait.until(EC.alert_is_present())
text = alert.text
alert.dismiss()
```

**CSharp**

```csharp
// Click the link to activate the alert
IWebElement link = driver.FindElement(By.LinkText("See a sample confirm"));
((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", link);

// Wait for the alert to be displayed
wait.Until(d => d.SwitchTo().Alert());

// Store the alert in a variable
IAlert alert = driver.SwitchTo().Alert();

// Store the alert in a variable for reuse
string text = alert.Text;

// Press the Cancel button
alert.Dismiss();
```

**Ruby**

```ruby
# Store the alert reference in a variable
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
await alert.dismiss();
```

**Kotlin**

```kotlin
// Click the link to activate the alert
val link = driver.findElement(By.linkText("See a sample confirm"))
(driver as JavascriptExecutor).executeScript("arguments[0].click();", link)

// Wait for the alert to be displayed
wait.until(ExpectedConditions.alertIsPresent())

// Store the alert in a variable
val alert = driver.switchTo().alert()

// Store the alert in a variable for reuse
val text = alert.text

// Press the Cancel button
alert.dismiss()
```

## Prompt

Prompts are similar to confirm boxes, except they also include a text
input. Similar to working with form elements, you can use WebDriver's
send keys to fill in a response. This will completely replace the placeholder
text. Pressing the cancel button will not submit any text.
<a onclick="window.prompt('What is your tool of choice?',navigator.appName)">
See a sample prompt</a>.

**Java**

```java
//Wait for the alert to be displayed and store it in a variable
wait.until(ExpectedConditions.alertIsPresent());

Alert alert = driver.switchTo().alert();
Assertions.assertEquals("Enter something", alert.getText());
```

**Python**

```python
element = driver.find_element(By.LINK_TEXT, "See a sample prompt")
driver.execute_script("arguments[0].click();", element)

wait = WebDriverWait(driver, timeout=2)
alert = wait.until(EC.alert_is_present())
alert.send_keys("Selenium")
text = alert.text
alert.accept()
```

**CSharp**

```csharp
// Click the link to activate the alert
IWebElement link = driver.FindElement(By.LinkText("See a sample prompt"));
((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", link);

// Wait for the alert to be displayed and store it in a variable
IAlert alert = wait.Until(d => d.SwitchTo().Alert());

// Store the alert text in a variable for reuse
string text = alert.Text;

// Type your message
alert.SendKeys("Selenium");

// Press the OK button
alert.Accept();
```

**Ruby**

```ruby
# Store the alert reference in a variable
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

**Kotlin**

```kotlin
// Click the link to activate the alert
val link = driver.findElement(By.linkText("See a sample prompt"))
(driver as JavascriptExecutor).executeScript("arguments[0].click();", link)

// Wait for the alert to be displayed and store it in a variable
val alert = wait.until(ExpectedConditions.alertIsPresent())

// Store the alert text in a variable for reuse
val text = alert.text

// Type your message
alert.sendKeys("Selenium")

// Press the OK button
alert.accept()
```

## beforeunload

Recent drivers automatically dismiss `beforeunload` prompts by default. To keep the old behavior,
see [unhandledPromptBehavior](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md#unhandledpromptbehavior).
