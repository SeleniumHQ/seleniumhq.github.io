# Alertas, prompts e confirmações JavaScript

URL: https://www.selenium.dev/pt-br/documentation/webdriver/interactions/alerts/

---

WebDriver fornece uma API para trabalhar com os três tipos nativos de
mensagens pop-up oferecidas pelo JavaScript. Esses pop-ups são estilizados pelo
navegador e oferecem personalização limitada.

## Alertas

O mais simples deles é referido como um alerta, que mostra um
mensagem personalizada e um único botão que dispensa o alerta, rotulado
na maioria dos navegadores como OK. Ele também pode ser dispensado na maioria dos navegadores
pressionando o botão Fechar, mas isso sempre fará a mesma coisa que
o botão OK. <a onclick="window.alert('Sample alert')"> Veja um exemplo de alerta </a>.

O WebDriver pode obter o texto do pop-up e aceitar ou dispensar esses
alertas.

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

## Confirmação

Uma caixa de confirmação é semelhante a um alerta, exceto que o usuário também pode escolher
cancelar a mensagem. <a onclick="window.confirm('Are you sure?')"> Veja
uma amostra de confirmação </a>.

Este exemplo também mostra uma abordagem diferente para armazenar um alerta:

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

Os prompts são semelhantes às caixas de confirmação, exceto que também incluem um texto de
entrada. Semelhante a trabalhar com elementos de formulário, você pode
usar o sendKeys do WebDriver para preencher uma resposta. Isso substituirá
completamente o espaço de texto de exemplo. Pressionar o botão Cancelar não enviará nenhum texto.
<a onclick="window.prompt('What is your tool of choice?', navigator.appName)">
Veja um exemplo de prompt </a>.

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

Drivers recentes descartam automaticamente os prompts de `beforeunload` por padrão.
Para manter o comportamento antigo,
veja [unhandledPromptBehavior](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/options/index.md#unhandledpromptbehavior).
