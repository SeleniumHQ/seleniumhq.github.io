# JavaScript 警告框,提示框和确认框

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/interactions/alerts/

---

WebDriver提供了一个API, 用于处理JavaScript提供的三种类型的原生弹窗消息. 这些弹窗由浏览器提供限定的样式.

## Alerts 警告框

其中最基本的称为警告框, 它显示一条自定义消息, 以及一个用于关闭该警告的按钮, 在大多数浏览器中标记为"确定"(OK). 在大多数浏览器中, 也可以通过按"关闭"(close)按钮将其关闭, 但这始终与“确定”按钮具有相同的作用. <a onclick="window.alert('Sample alert')">查看样例警告框</a>.

WebDriver可以从弹窗获取文本并接受或关闭这些警告.

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

## Confirm 确认框

确认框类似于警告框, 不同之处在于用户还可以选择取消消息. <a onclick="window.confirm('Are you sure?')">查看样例确认框</a>.

此示例还呈现了警告的另一种实现:

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

## Prompt 提示框

提示框与确认框相似, 不同之处在于它们还包括文本输入. 与处理表单元素类似, 您可以使用WebDriver的sendKeys来填写响应. 这将完全替换占位符文本. 按下取消按钮将不会提交任何文本.
<a onclick="window.prompt('What is your tool of choice?',navigator.appName)">
查看样例提示框</a>.

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

较新版本的驱动默认会自动关闭 `beforeunload` 提示框。
如果需要恢复旧的行为，
请参阅 [unhandledPromptBehavior](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/options/index.md#unhandledpromptbehavior)。
