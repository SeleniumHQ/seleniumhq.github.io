# Ações API

> Uma interface de baixo nível para fornecer ações de entrada de dispositivo virtualizadas para o navegador da web..

URL: https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/

---

Além das [interações de alto nível](https://www.selenium.dev/pt-br/documentation/webdriver/elements/interactions/index.md), a [API de Ações](https://w3c.github.io/webdriver/#dfn-actions) oferece controle detalhado sobre o que dispositivos de entrada designados podem fazer. O Selenium fornece uma interface para 3 tipos de fontes de entrada: entrada de teclado para dispositivos de teclado, entrada de ponteiro para mouse, caneta ou dispositivos de toque, e entrada de roda para dispositivos de roda de rolagem (introduzida no Selenium 4.2). O Selenium permite que você construa comandos de ação individuais atribuídos a entradas específicas, encadeie-os e chame o método de execução associado para executá-los todos de uma vez.

## Construtor de Ações

Na transição do antigo Protocolo JSON Wire para o novo Protocolo W3C WebDriver, os componentes de construção de ações de baixo nível se tornaram especialmente detalhados. Isso é extremamente poderoso, mas cada dispositivo de entrada possui várias maneiras de ser utilizado e, se você precisa gerenciar mais de um dispositivo, é responsável por garantir a sincronização adequada entre eles.

Felizmente, provavelmente você não precisa aprender a usar os comandos de baixo nível diretamente, uma vez que quase tudo o que você pode querer fazer foi fornecido com um método de conveniência que combina os comandos de nível inferior para você. Todos esses métodos estão documentados nas páginas de [teclado](https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/keyboard/index.md), [mouse](https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/mouse/index.md), [caneta](https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/pen/index.md) e [roda](https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/wheel/index.md).

## Pausa

Movimentos de ponteiro e rolagem da roda permitem que o usuário defina uma duração para a ação, mas às vezes você só precisa esperar um momento entre as ações para que as coisas funcionem corretamente.

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

## Liberar Todas as Ações

Um ponto importante a ser observado é que o driver lembra o estado de todos os itens de entrada ao longo de uma sessão. Mesmo se você criar uma nova instância de uma classe de ações, as teclas pressionadas e a posição do ponteiro permanecerão no estado em que uma ação previamente executada os deixou.

Existe um método especial para liberar todas as teclas pressionadas e botões do ponteiro atualmente pressionados. Esse método é implementado de maneira diferente em cada uma das linguagens porque não é executado com o método de execução (perform).

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

- [Ações de Teclado](https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/keyboard/index.md): Uma representação de qualquer dispositivo de entrada de teclado para interagir com uma página da web.
- [Ações do Mouse](https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/mouse/index.md): Uma representação de qualquer dispositivo de ponteiro para interagir com uma página da web.
- [Ações de Caneta](https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/pen/index.md): Uma representação de uma entrada de ponteiro do tipo caneta stylus para interagir com uma página da web.
- [Ações de Roda de Rolagem](https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/wheel/index.md): "Uma representação de um dispositivo de entrada de roda de rolagem para interagir com uma página da web."
