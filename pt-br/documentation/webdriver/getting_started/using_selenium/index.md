# Organizando e executando o código Selenium

> Escalonamento da execução do Selenium com um IDE e uma biblioteca do Test Runner

URL: https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/using_selenium/

---

Se quiser executar mais do que um punhado de scripts pontuais, precisa de
ser capaz de organizar e trabalhar com seu código. Esta página deve dar a você
ideias de como fazer coisas produtivas com seu código Selenium.

## Usos comuns

A maioria das pessoas usa o Selenium para executar testes automatizados para aplicações web,
mas o Selenium suporta qualquer caso de uso de automação de navegador.

### Tarefas Repetitivas

Talvez seja necessário fazer login em um site e baixar algo ou enviar um formulário.
Você pode criar um script Selenium para ser executado com um serviço em horários pré-definidos.

### Web Scrapping

Está a tentar recolher dados de um site que não tem uma API? O Selenium
permitirá que você faça isso, mas certifique-se de estar familiarizado com os termos de serviço do site
termos de serviço do site, pois alguns sites não permitem isso e outros até bloqueiam o Selenium.

### Testes

Executar o Selenium para testes requer fazer asserções sobre as ações tomadas pelo Selenium.
Então uma boa biblioteca de asserções é necessária. Características adicionais para prover estrutura para testes
requerem o uso de [Executador de teste](#executador-de-teste).

## IDEs

Independentemente de como você usa o código do Selenium,
não será muito eficaz escrevendo ou executando-o sem um bom
ambiente de desenvolvimento integrado. Aqui estão algumas opções comuns...

- [Eclipse](https://www.eclipse.org/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [PyCharm](https://www.jetbrains.com/pycharm/)
- [RubyMine](https://www.jetbrains.com/ruby/)
- [Rider](https://www.jetbrains.com/rider/)
- [WebStorm](https://www.jetbrains.com/webstorm/)
- [VS Code](https://code.visualstudio.com/)

## Executador de teste

Mesmo que não esteja a usar o Selenium para testes, se tiver casos de uso avançado, pode fazer
sentido usar um executor de testes para organizar melhor seu código. Ser capaz de usar hooks antes/depois
e executar coisas em grupos ou em paralelo pode ser muito útil.

### Escolhendo

Há muitos executores de teste diferentes disponíveis.

Todos os exemplos de código nesta documentação podem ser encontrados em (ou estão sendo movidos para) nossos diretórios
que usam test runners e são executados a cada lançamento para garantir que todo o código esteja correto e atualizado.
Aqui está uma lista de executores de teste com links. O primeiro item é o que é usado por este repositório e o que
que será usado para todos os exemplos nesta página.

**Java**

- [JUnit](https://junit.org/junit5/) - Uma estrutura de teste amplamente utilizada para testes Selenium baseados em Java.

- [TestNG](https://testng.org/) - Oferece recursos extras, como execução de testes paralelos e testes parametrizados.

**Python**

- [pytest](https://pytest.org/) -Uma escolha preferida por muitos, graças à sua simplicidade e aos seus poderosos plugins.

- [unittest](https://docs.python.org/3/library/unittest.html) - A estrutura de testes da biblioteca padrão do Python.

**CSharp**

- [NUnit](https://nunit.org/) - Um popular framework de teste unitário para .NET.

- [MS Test](https://docs.microsoft.com/en-us/visualstudio/test/getting-started-with-unit-testing?view=vs-2019) - O Framework de testes unitários da Microsoft.

**Ruby**

- [RSpec](https://rspec.info/) - A biblioteca de testes mais utilizada para executar testes Selenium em Ruby.

- [Minitest](https://github.com/seattlerb/minitest) - Um framework de testes leve que vem com a biblioteca padrão do Ruby.

**JavaScript**

- [Jest](https://jestjs.io/) - Principalmente conhecido como um framework de teste para React, também pode ser utilizado para testes Selenium.

- [Mocha](https://mochajs.org/) - A biblioteca JS mais comum para executar testes Selenium.

**Kotlin**

- [Kotest](https://kotest.io/) - Uma estrutura de testes flexível e abrangente, projetada especificamente para Kotlin.

- [JUnit5](https://junit.org/junit5/) - A estrutura de testes padrão do Java, totalmente compatível com Kotlin.

### Instalando

Isto é muito semelhante ao que foi requerido em [Install a Selenium Library](https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/install_library/index.md).
Este código está apenas a mostrar exemplos do que está a ser usado no nosso projeto de Exemplos de Documentação.

**Java**

**Maven**

**Gradle**

**Python**

Para usá-lo em um projeto, adicione-o ao arquivo `requirements.txt`:

**CSharp**

in the project’s `csproj` especifique a dependência como `PackageReference` em `ItemGroup`:

**Ruby**

Add to project’s gemfile

**JavaScript**

In your project’s `package.json`, adicionar requisito às `dependências`:

**Kotlin**

### Afirmar

**Java**

```java
String title = driver.getTitle();
assertEquals("Web form", title);
```

**Python**

```python
title = driver.title
assert title == "Web form"
```

**CSharp**

```csharp
var title = driver.Title;
Assert.AreEqual("Web form", title);
```

**Ruby**

```ruby
title = @driver.title
expect(title).to eq('Web form')
```

**JavaScript**

```javascript
let title = await driver.getTitle();
assert.equal("Web form", title);
```

**Kotlin**

```kotlin
val title = driver.title
assertEquals("Web form", title)
```

### Configuarar e Desconfigurar

**Java**

<h3 id="set-up">Set Up</h3>

```java
@BeforeEach
public void setup() {
	driver = new ChromeDriver();
}
```
<h3 id="tear-down">Tear Down</h3>

```java
@AfterEach
public final void teardown() {
	if (driver != null) {
		driver.quit();
```

**Python**

<h3 id="set-up">Set Up</h3>

```python
def setup():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/web-form.html")
    return driver
```
<h3 id="tear-down">Tear Down</h3>

```python
def teardown(driver):
    driver.quit()
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

<h3 id="set-up">Set Up</h3>

```ruby
before do
  @driver = Selenium::WebDriver.for :chrome
end
```
<h3 id="tear-down">Tear Down</h3>

```ruby
config.after { @driver&.quit }
```

**JavaScript**

### Set Up

```javascript
before(async function () {
  driver = await new Builder().forBrowser('chrome').build();
});
```

### Tear Down

```javascript
after(async () => await driver.quit());
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Executando

**Java**

<h3 id="maven">Maven</h3>

```shell
mvn clean test
```
<h3 id="gradle">Gradle</h3>

```shell
gradle clean test
```

**Python**

```markdown
pytest
```

**CSharp**

```markdown

```

**Ruby**

```markdown
bundle exec rspec
```

**JavaScript**

<h3 id="mocha">Mocha</h3>

```shell
mocha runningTests.spec.js
```
<h3 id="npx">npx</h3>

```shell
npx mocha runningTests.spec.js
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Examplos

In [First script](https://www.selenium.dev/pt-br/documentation/webdriver/getting_started/first_script/index.md), we saw each of the components of a Selenium script.
Here's an example of that code using a test runner:

**Java**

```java
package dev.selenium.getting_started;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsingSeleniumTest {

	WebDriver driver;

	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
	}

	@Test
	public void eightComponents() {

		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.get("https://www.selenium.dev/selenium/web/web-form.html");

		String title = driver.getTitle();
		assertEquals("Web form", title);

		WebElement textBox = driver.findElement(By.name("my-text"));
		WebElement submitButton = driver.findElement(By.cssSelector("button"));

		textBox.sendKeys("Selenium");
		submitButton.click();

		WebElement message = driver.findElement(By.id("message"));
		String value = message.getText();
		assertEquals("Received!", value);

	}

	@AfterEach
	public final void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
```

**Python**

```python
from selenium import webdriver
from selenium.webdriver.common.by import By

def test_eight_components():
    driver = setup()

    title = driver.title
    assert title == "Web form"

    driver.implicitly_wait(0.5)

    text_box = driver.find_element(by=By.NAME, value="my-text")
    submit_button = driver.find_element(by=By.CSS_SELECTOR, value="button")

    text_box.send_keys("Selenium")
    submit_button.click()

    message = driver.find_element(by=By.ID, value="message")
    value = message.text
    assert value == "Received!"

    teardown(driver)

def setup():
    driver = webdriver.Chrome()
    driver.get("https://www.selenium.dev/selenium/web/web-form.html")
    return driver

def teardown(driver):
    driver.quit()
```

**CSharp**

```csharp
using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.GettingStarted
{
    [TestClass]
    public class UsingSeleniumTest
    {

        [TestMethod]
        public void EightComponents()
        {
            IWebDriver driver = new ChromeDriver();

            driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/web-form.html");

            var title = driver.Title;
            Assert.AreEqual("Web form", title);

            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

            var textBox = driver.FindElement(By.Name("my-text"));
            var submitButton = driver.FindElement(By.TagName("button"));

            textBox.SendKeys("Selenium");
            submitButton.Click();

            var message = driver.FindElement(By.Id("message"));
            var value = message.Text;
            Assert.AreEqual("Received!", value);

            driver.Quit();
        }
    }
}
```

**Ruby**

```ruby
# frozen_string_literal: true

require 'spec_helper'
require 'selenium-webdriver'

RSpec.describe 'Using Selenium' do
  before do
    @driver = Selenium::WebDriver.for :chrome
  end

  it 'uses eight components' do
    @driver.get('https://www.selenium.dev/selenium/web/web-form.html')

    title = @driver.title
    expect(title).to eq('Web form')

    @driver.manage.timeouts.implicit_wait = 500

    text_box = @driver.find_element(name: 'my-text')
    submit_button = @driver.find_element(tag_name: 'button')

    text_box.send_keys('Selenium')
    submit_button.click

    message = @driver.find_element(id: 'message')
    value = message.text
    expect(value).to eq('Received!')
  end
end
```

**JavaScript**

```javascript
const {By, Builder} = require('selenium-webdriver');
const assert = require("assert");

describe('First script', function () {
  let driver;

  before(async function () {
    driver = await new Builder().forBrowser('chrome').build();
  });

  it('First Selenium script with mocha', async function () {
    await driver.get('https://www.selenium.dev/selenium/web/web-form.html');

    let title = await driver.getTitle();
    assert.equal("Web form", title);

    await driver.manage().setTimeouts({implicit: 500});

    let textBox = await driver.findElement(By.name('my-text'));
    let submitButton = await driver.findElement(By.css('button'));

    await textBox.sendKeys('Selenium');
    await submitButton.click();

    let message = await driver.findElement(By.id('message'));
    let value = await message.getText();
    assert.equal("Received!", value);
  });

  after(async () => await driver.quit());
});
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Proximos passos

Pegue no que aprendeu e desenvolva o seu código Selenium!

À medida que encontrar mais funcionalidades de que precisa, leia o resto da nossa
[documentação do WebDriver](https://www.selenium.dev/pt-br/documentation/webdriver/index.md).
