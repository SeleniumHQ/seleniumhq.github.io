# JavaScript アラート、プロンプトおよび確認

URL: https://www.selenium.dev/ja/documentation/webdriver/interactions/alerts/

---

WebDriverは、JavaScriptが提供する3種類のネイティブポップアップメッセージを操作するためのAPIを提供します。
これらのポップアップはブラウザーによってスタイルが設定され、カスタマイズが制限されています。

## アラート

これらの最も単純なものはアラートと呼ばれ、カスタムメッセージと、ほとんどのブラウザーでOKのラベルが付いたアラートを非表示にする単一のボタンを表示します。
ほとんどのブラウザーでは閉じるボタンを押すことで閉じることもできますが、これは常にOKボタンと同じことを行います。
<a onclick="window.alert('Sample alert')">アラートの例を参照してください</a>。

WebDriverはポップアップからテキストを取得し、これらのアラートを受け入れるか、または閉じることができます。

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

## 確認

確認ダイアログボックスはアラートに似ていますが、ユーザーがメッセージをキャンセルすることも選択できる点が異なります。
<a onclick="window.confirm('Are you sure?')">サンプルを確認してください</a>。

この例は、アラートを保存する別の方法も示しています。

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

## プロンプト

プロンプトは確認ボックスに似ていますが、テキスト入力も含まれている点が異なります。
フォーム要素の操作と同様に、WebDriverの送信キーを使用して応答を入力できます。
これにより、プレースホルダーテキストが完全に置き換えられます。
キャンセルボタンを押してもテキストは送信されません。
<a onclick="window.prompt('What is your tool of choice?',navigator.appName)">サンプルプロンプトを参照してください</a>。

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

最近のドライバーでは、デフォルトで `beforeunload` プロンプトを自動的に閉じます。
以前の動作を維持するには、
[unhandledPromptBehavior](https://www.selenium.dev/ja/documentation/webdriver/drivers/options/index.md#unhandledpromptbehavior) を参照してください。
