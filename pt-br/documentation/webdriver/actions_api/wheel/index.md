# Ações de Roda de Rolagem

> "Uma representação de um dispositivo de entrada de roda de rolagem para interagir com uma página da web."

URL: https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/wheel/

---

_Requires [Selenium v4.2](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.2.0) or later._

_Only works in Chromium ([browser support](https://wpt.fyi/results/webdriver/tests/perform_actions/wheel.py))._

Existem 5 cenários para rolagem em uma página.

## Rolagem até o Elemento

Este é o cenário mais comum. Diferentemente dos métodos tradicionais de clique e envio de teclas, a classe de ações não rolará automaticamente o elemento de destino para a visualização, portanto, este método precisará ser usado se os elementos não estiverem dentro da janela de visualização.

Este método recebe um elemento da web como único argumento.

Independentemente de o elemento estar acima ou abaixo da tela de visualização atual, a janela de visualização será rolada de forma que a parte inferior do elemento esteja na parte inferior da tela.

**Java**

```java
WebElement iframe = driver.findElement(By.tagName("iframe"));
new Actions(driver)
        .scrollToElement(iframe)
        .perform();
```

**Python**

```python
iframe = driver.find_element(By.TAG_NAME, "iframe")
ActionChains(driver)\
    .scroll_to_element(iframe)\
    .perform()
```

**CSharp**

```csharp
IWebElement iframe = driver.FindElement(By.TagName("iframe"));
new Actions(driver)
    .ScrollToElement(iframe)
    .Perform();
```

**Ruby**

```ruby
iframe = driver.find_element(tag_name: 'iframe')
driver.action
      .scroll_to(iframe)
      .perform
```

**JavaScript**

```javascript
const iframe = await driver.findElement(By.css("iframe"))
await driver.actions()
  .scroll(0, 0, 0, 0, iframe)
  .perform()
```

**Kotlin**

```kotlin
val iframe = driver.findElement(By.tagName("iframe"))
Actions(driver)
        .scrollToElement(iframe)
        .perform()
```

## Rolar por uma Quantidade Especificada

Este é o segundo cenário mais comum para a rolagem. Passe um valor delta x e um valor delta y para o quanto rolar nas direções direita e para baixo. Valores negativos representam esquerda e para cima, respectivamente.

**Java**

```java
WebElement footer = driver.findElement(By.tagName("footer"));
int deltaY = footer.getRect().y;
new Actions(driver)
        .scrollByAmount(0, deltaY)
        .perform();
```

**Python**

```python
footer = driver.find_element(By.TAG_NAME, "footer")
delta_y = footer.rect['y']
ActionChains(driver)\
    .scroll_by_amount(0, delta_y)\
    .perform()
```

**CSharp**

```csharp
IWebElement footer = driver.FindElement(By.TagName("footer"));
int deltaY = footer.Location.Y;
new Actions(driver)
    .ScrollByAmount(0, deltaY)
    .Perform();
```

**Ruby**

```ruby
footer = driver.find_element(tag_name: 'footer')
delta_y = footer.rect.y
driver.action
      .scroll_by(0, delta_y)
      .perform
```

**JavaScript**

```javascript
const footer = await driver.findElement(By.css("footer"))
const deltaY = (await footer.getRect()).y

await driver.actions()
  .scroll(0, 0, 0, deltaY)
  .perform()
```

**Kotlin**

```kotlin
val footer = driver.findElement(By.tagName("footer"))
val deltaY = footer.getRect().y
Actions(driver)
        .scrollByAmount(0, deltaY)
        .perform()
```

## Rolagem a partir de um Elemento por uma Quantidade Especificada"

Este cenário é efetivamente uma combinação dos dois métodos mencionados anteriormente.

Para executar isso, use o método "Rolar a Partir de", que recebe 3 argumentos. O primeiro representa o ponto de origem, que designamos como o elemento, e os dois seguintes são os valores delta x e delta y.

Se o elemento estiver fora da janela de visualização, ele será rolado para a parte inferior da tela e, em seguida, a página será rolada pelos valores delta x e delta y fornecidos.

**Java**

```java
WebElement iframe = driver.findElement(By.tagName("iframe"));
WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(iframe);
new Actions(driver)
        .scrollFromOrigin(scrollOrigin, 0, 200)
        .perform();
```

**Python**

```python
iframe = driver.find_element(By.TAG_NAME, "iframe")
scroll_origin = ScrollOrigin.from_element(iframe)
ActionChains(driver)\
    .scroll_from_origin(scroll_origin, 0, 200)\
    .perform()
```

**CSharp**

```csharp
IWebElement iframe = driver.FindElement(By.TagName("iframe"));
WheelInputDevice.ScrollOrigin scrollOrigin = new WheelInputDevice.ScrollOrigin
{
    Element = iframe
};
new Actions(driver)
    .ScrollFromOrigin(scrollOrigin, 0, 200)
    .Perform();
```

**Ruby**

```ruby
iframe = driver.find_element(tag_name: 'iframe')
scroll_origin = Selenium::WebDriver::WheelActions::ScrollOrigin.element(iframe)
driver.action
      .scroll_from(scroll_origin, 0, 200)
      .perform
```

**JavaScript**

```javascript
const iframe = await driver.findElement(By.css("iframe"))

await driver.actions()
  .scroll(0, 0, 0, 200, iframe)
  .perform()
```

**Kotlin**

```kotlin
val iframe = driver.findElement(By.tagName("iframe"))
val scrollOrigin = WheelInput.ScrollOrigin.fromElement(iframe)
Actions(driver)
        .scrollFromOrigin(scrollOrigin, 0, 200)
        .perform()
```

## Rolagem a partir de um Elemento com um Deslocamento

Este cenário é usado quando você precisa rolar apenas uma parte da tela que está fora da janela de visualização ou dentro da janela de visualização, mas a parte da tela que deve ser rolada está a uma distância conhecida de um elemento específico.

Isso utiliza novamente o método "Rolar a Partir", e além de especificar o elemento, é especificado um deslocamento para indicar o ponto de origem da rolagem. O deslocamento é calculado a partir do centro do elemento fornecido.

Se o elemento estiver fora da janela de visualização, primeiro ele será rolado até a parte inferior da tela. Em seguida, a origem da rolagem será determinada adicionando o deslocamento às coordenadas do centro do elemento, e, finalmente, a página será rolada pelos valores delta x e delta y fornecidos.

Observe que se o deslocamento a partir do centro do elemento estiver fora da janela de visualização, isso resultará em uma exceção.

**Java**

```java
WebElement footer = driver.findElement(By.tagName("footer"));
WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(footer, 0, -50);
new Actions(driver)
        .scrollFromOrigin(scrollOrigin,0, 200)
        .perform();
```

**Python**

```python
footer = driver.find_element(By.TAG_NAME, "footer")
scroll_origin = ScrollOrigin.from_element(footer, 0, -50)
ActionChains(driver)\
    .scroll_from_origin(scroll_origin, 0, 200)\
    .perform()
```

**CSharp**

```csharp
IWebElement footer = driver.FindElement(By.TagName("footer"));
var scrollOrigin = new WheelInputDevice.ScrollOrigin
{
    Element = footer,
    XOffset = 0,
    YOffset = -50
};
new Actions(driver)
    .ScrollFromOrigin(scrollOrigin, 0, 200)
    .Perform();
```

**Ruby**

```ruby
footer = driver.find_element(tag_name: 'footer')
scroll_origin = Selenium::WebDriver::WheelActions::ScrollOrigin.element(footer, 0, -50)
driver.action
      .scroll_from(scroll_origin, 0, 200)
      .perform
```

**JavaScript**

```javascript
const footer = await driver.findElement(By.css("footer"))

await driver.actions()
  .scroll(0, -50, 0, 200, footer)
  .perform()
```

**Kotlin**

```kotlin
val footer = driver.findElement(By.tagName("footer"))
val scrollOrigin = WheelInput.ScrollOrigin.fromElement(footer, 0, -50)
Actions(driver)
        .scrollFromOrigin(scrollOrigin,0, 200)
        .perform()
```

## Rolar a partir de um Deslocamento de Origem (Elemento) por uma Quantidade Especificada

O cenário final é usado quando você precisa rolar apenas uma parte da tela que já está dentro da janela de visualização.

Isso utiliza novamente o método "Rolar a Partir", mas a janela de visualização é designada em vez de um elemento. Um deslocamento é especificado a partir do canto superior esquerdo da janela de visualização atual. Após determinar o ponto de origem, a página será rolada pelos valores delta x e delta y fornecidos.

Observe que se o deslocamento a partir do canto superior esquerdo da janela de visualização sair da tela, isso resultará em uma exceção.

**Java**

```java
WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10);
new Actions(driver)
        .scrollFromOrigin(scrollOrigin, 0, 200)
        .perform();
```

**Python**

```python
scroll_origin = ScrollOrigin.from_viewport(10, 10)

ActionChains(driver)\
    .scroll_from_origin(scroll_origin, 0, 200)\
    .perform()
```

**CSharp**

```csharp
var scrollOrigin = new WheelInputDevice.ScrollOrigin
{
    Viewport = true,
    XOffset = 10,
    YOffset = 10
};
new Actions(driver)
    .ScrollFromOrigin(scrollOrigin, 0, 200)
    .Perform();
```

**Ruby**

```ruby
scroll_origin = Selenium::WebDriver::WheelActions::ScrollOrigin.viewport(10, 10)
driver.action
      .scroll_from(scroll_origin, 0, 200)
      .perform
```

**JavaScript**

```javascript
await driver.actions()
  .scroll(10, 10, 0, 200)
  .perform()
```

**Kotlin**

```kotlin
val scrollOrigin = WheelInput.ScrollOrigin.fromViewport(10, 10)
Actions(driver)
        .scrollFromOrigin(scrollOrigin, 0, 200)
        .perform()
```
