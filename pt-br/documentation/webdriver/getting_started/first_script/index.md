# Programe o seu primeiro script Selenium

> Instruções passo a passo para programar um script Selenium

URL: https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/first_script/

---

Assim que você tiver o [Selenium instalado](https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/install_library/index.md), 
você estará pronto para programar códigos Selenium.

## Oito Componentes Básicos
Tudo que o Selenium faz é enviar comandos ao navegador de internet para fazer algo ou solicitar informações dele.
A maior parte do que você irá fazer com o Selenium é uma combinação desses comandos básicos.

Click on the link to "View full example on GitHub" to see the code in context.

### 1. Iniciando uma sessão
Para ter mais detalhes sobre como iniciar uma sessão, leia nossa documentação em [driver sessions](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/index.md)

**Java**

```java
WebDriver driver = new ChromeDriver();
```

**Python**

```python
driver = webdriver.Chrome()
```

**CSharp**

```csharp
IWebDriver driver = new ChromeDriver();
```

**Ruby**

```ruby
driver = Selenium::WebDriver.for :chrome
```

**JavaScript**

```javascript
driver = await new Builder().forBrowser(Browser.CHROME).build();
```

**Kotlin**

```kotlin
driver = ChromeDriver()
```

### 2. Agindo no navegador de internet
Nesse exemplo estamos [navegando](https://www.selenium.dev/pt-br/documentation/webdriver/interactions/navigation/index.md) para uma página web. 

**Java**

```java
driver.get("https://www.selenium.dev/selenium/web/web-form.html");
```

**Python**

```python
driver.get("https://www.selenium.dev/selenium/web/web-form.html")
```

**CSharp**

```csharp
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/web-form.html");
```

**Ruby**

```ruby
driver.get('https://www.selenium.dev/selenium/web/web-form.html')
```

**JavaScript**

```javascript
await driver.get('https://www.selenium.dev/selenium/web/web-form.html');
```

**Kotlin**

```kotlin
driver.get("https://www.selenium.dev/selenium/web/web-form.html")
```

### 3. Solicitando informação do navegador de internet
Existem diversos tipos de [informação sobre o navegador de internet](https://www.selenium.dev/pt-br/documentation/webdriver/interactions/index.md) que você
pode solicitar, incluindo window handles, tamanho / posição do navegador, cookies, alertas e etc.

**Java**

```java
driver.getTitle();
```

**Python**

```python
title = driver.title
```

**CSharp**

```csharp
var title = driver.Title;
```

**Ruby**

```ruby
driver.title
```

**JavaScript**

```javascript
let title = await driver.getTitle();
```

**Kotlin**

```kotlin
val title = driver.title
```

### 4. Estabelecendo uma Estratégia de Espera

Sincronizar o código ao estado atual do navegador é um dos maiores 
desafios 
quando se trabalha com o Selenium, fazer isso de maneira bem feita é um tópico avançado.

Essencialmente, você quer ter certeza absoluta de que o elemento está na página antes de tentar localizá-lo
e o elemento está em um estado interativo antes de você tentar interagir com ele.

Uma espera implícita raramente é a melhor solução, mas é a mais fácil de demonstrar aqui, então
vamos usá-la como um substituto. 

Leia mais sobre [Estratégias de espera](https://www.selenium.dev/pt-br/documentation/webdriver/waits/index.md).

**Java**

```java
driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
```

**Python**

```python
driver.implicitly_wait(0.5)
```

**CSharp**

```csharp
driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);
```

**Ruby**

```ruby
driver.manage.timeouts.implicit_wait = 500
```

**JavaScript**

```javascript
await driver.manage().setTimeouts({implicit: 500});
```

**Kotlin**

```kotlin
driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
```

### 5. Encontrando um elemento
A maioria dos comandos na maior parte das sessões do Selenium são relacionados a elementos e você não pode 
interagir
com um sem o primeiro [encontrando um elemento](https://www.selenium.dev/pt-br/documentation/webdriver/elements/index.md)

**Java**

```java
WebElement textBox = driver.findElement(By.name("my-text"));
WebElement submitButton = driver.findElement(By.cssSelector("button"));
```

**Python**

```python
text_box = driver.find_element(by=By.NAME, value="my-text")
submit_button = driver.find_element(by=By.CSS_SELECTOR, value="button")
```

**CSharp**

```csharp
var textBox = driver.FindElement(By.Name("my-text"));
var submitButton = driver.FindElement(By.TagName("button"));
```

**Ruby**

```ruby
text_box = driver.find_element(name: 'my-text')
submit_button = driver.find_element(tag_name: 'button')
```

**JavaScript**

```javascript
let textBox = await driver.findElement(By.name('my-text'));
let submitButton = await driver.findElement(By.css('button'));
```

**Kotlin**

```kotlin
var textBox = driver.findElement(By.name("my-text"))
val submitButton = driver.findElement(By.cssSelector("button"))
```

### 6. Agindo no elemento
Há apenas um punhado de [ações a serem executadas em um elemento](https://www.selenium.dev/pt-br/documentation/webdriver/elements/interactions/index.md),
mas você irá usá-las com frequência.

**Java**

```java
textBox.sendKeys("Selenium");
submitButton.click();
```

**Python**

```python
text_box.send_keys("Selenium")
submit_button.click()
```

**CSharp**

```csharp
textBox.SendKeys("Selenium");
submitButton.Click();
```

**Ruby**

```ruby
text_box.send_keys('Selenium')
submit_button.click
```

**JavaScript**

```javascript
await textBox.sendKeys('Selenium');
await submitButton.click();
```

**Kotlin**

```kotlin
textBox.sendKeys("Selenium")
submitButton.click()
```

### 7. Solicitando informações do elemento
Elementos podem guardar muitas [informações que podem ser solicitadas](https://www.selenium.dev/pt-br/documentation/webdriver/elements/information/index.md).

**Java**

```java
WebElement message = driver.findElement(By.id("message"));
message.getText();
```

**Python**

```python
message = driver.find_element(by=By.ID, value="message")
text = message.text
```

**CSharp**

```csharp
var message = driver.FindElement(By.Id("message"));
var value = message.Text;
```

**Ruby**

```ruby
message = driver.find_element(id: 'message')
message.text
```

**JavaScript**

```javascript
let message = await driver.findElement(By.id('message'));
let value = await message.getText();
```

**Kotlin**

```kotlin
val message = driver.findElement(By.id("message"))
val value = message.getText()
```

### 8. Encerrando a sessão

Isso encerra o processo do driver, que por padrão também fecha o navegador.
Nenhum outro comando pode ser enviado para esta instância do driver.

See [Quitting Sessions](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/index.md#quitting-sessions).

**Java**

```java
driver.quit();
```

**Python**

```python
driver.quit()
```

**CSharp**

```csharp
driver.Quit();
```

**Ruby**

```ruby
driver.quit
```

**JavaScript**

```javascript
await driver.quit();
```

**Kotlin**

```kotlin
driver.quit()
```

## Running Selenium File

**Java**

```markdown
mvn exec:java -D"exec.mainClass"="dev.selenium.getting_started.FirstScript" -D"exec.classpathScope"=test
```

**Python**

```markdown
pytest path/to/test_script.py
```

**CSharp**

```markdown

```

**Ruby**

```markdown
ruby example_script.rb
```

**JavaScript**

```markdown
node example_script.spec.js
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Próximos Passos

Most Selenium users execute many sessions and need to organize them to minimize duplication and keep the code
more maintainable. Read on to learn about how to put this code into context for your use case with
[Using Selenium](https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/using_selenium/index.md).
