# Encontrando Elementos Web

> Localizando elementos com base nos valores providenciados pelo localizador.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/elements/finders/

---

Um dos aspectos mais fundamentais do uso do Selenium é obter referências de elementos para trabalhar.
O Selenium oferece várias [estratégias de localizador](https://www.selenium.dev/pt-br/documentation/webdriver/elements/locators/index.md) para identificar exclusivamente um elemento.
Há muitas maneiras de usar os localizadores em cenários complexos. Para os propósitos desta documentação,
use a [página de teste de localizadores do Selenium](https://www.selenium.dev/selenium/web/locators_tests/locators.html).

## Primeiro Elemento correspondente
Muitos localizadores irão corresponder a vários elementos na página.
O método de elemento de localização singular retornará uma referência ao
primeiro elemento encontrado dentro de um determinado contexto.

### Avaliando o DOM inteiro
Quando o metodo find element é chamado na instância do driver, ele
retorna uma referência ao primeiro elemento no DOM que corresponde ao localizador fornecido.
Esse valor pode ser guardado e usado para ações futuras do elemento. Na página de teste de localizadores do Selenium, existem
dois elementos com o nome de classe `information`, então este método retorna o primeiro campo de texto.

**Java**

```java
driver.get(LOCATORS_PAGE);
WebElement firstInput = driver.findElement(By.className("information"));
```

**Python**

```python
driver.get(LOCATORS_PAGE)
first_input = driver.find_element(By.CLASS_NAME, "information")
```

**CSharp**

```csharp
driver.Url = LocatorsPage;
IWebElement firstInput = driver.FindElement(By.ClassName("information"));
```

**Ruby**

```ruby
driver.navigate.to locators_page
first_input = driver.find_element(class: 'information')
```

**JavaScript**

```javascript
await driver.get(LOCATORS_PAGE);
const firstInput = await driver.findElement(By.className('information'));
```

**Kotlin**

```kotlin
driver.get(locatorsPage)
val firstInput = driver.findElement(By.className("information"))
```

### Avaliando um subconjunto do DOM
Ao em vez de tentar encontrar um localizador unico no DOM inteiro, normalmente é útil restringir a busca ao escopo de outro elemento
já localizado.

Uma possível solução seria localizar um ancestral do elemento desejado, então invoque o find element nesse objeto:

**Java**

```java
driver.get(LOCATORS_PAGE);
WebElement form = driver.findElement(By.tagName("form"));
WebElement input = form.findElement(By.className("information"));
```

**Python**

```python
driver.get(LOCATORS_PAGE)
form = driver.find_element(By.TAG_NAME, "form")
input_element = form.find_element(By.CLASS_NAME, "information")
```

**CSharp**

```csharp
driver.Url = LocatorsPage;
IWebElement form = driver.FindElement(By.TagName("form"));
IWebElement input = form.FindElement(By.ClassName("information"));
```

**Ruby**

```ruby
driver.navigate.to locators_page
form = driver.find_element(tag_name: 'form')
input_element = form.find_element(class: 'information')
```

**JavaScript**

```javascript
await driver.get(LOCATORS_PAGE);
const form = await driver.findElement(By.tagName('form'));
const input = await form.findElement(By.className('information'));
```

**Kotlin**

```kotlin
driver.get(locatorsPage)
val form = driver.findElement(By.tagName("form"))
val input = form.findElement(By.className("information"))
```

> **Java e C#**<br>
> As classes `WebDriver`, `WebElement` e `ShadowRoot` todas implementam o `SearchContext` interface, que é
> considerada uma _role-based interface_(interface baseada em função). As interfaces baseadas em função permitem determinar se uma determinada
> implementação de driver suporta um recurso específico. Essas interfaces são claramente definidas e tentam
> aderir a ter apenas um único papel de responsabilidade.

### Evaluating the Shadow DOM

The Shadow DOM is an encapsulated DOM tree hidden inside an element. 
With the release of v96 in Chromium Browsers, Selenium can now allow you to access this tree 
with easy-to-use shadow root methods. NOTE: These methods require Selenium 4.0 or greater.

**Java**

```java
WebElement shadowHost = driver.findElement(By.cssSelector("custom-checkbox-element"));
SearchContext shadowRoot = shadowHost.getShadowRoot();
WebElement shadowContent = shadowRoot.findElement(By.cssSelector("input[type=checkbox]"));
```

**Python**

```python
shadow_host = driver.find_element(By.TAG_NAME, 'custom-checkbox-element')
shadow_root = shadow_host.shadow_root
assert shadow_root
shadow_content = shadow_root.find_element(By.CSS_SELECTOR, 'input[type=checkbox]')
```

**CSharp**

```csharp
IWebElement shadowHost = driver.FindElement(By.CssSelector("custom-checkbox-element"));
ISearchContext shadowRoot = shadowHost.GetShadowRoot();
IWebElement shadowContent = shadowRoot.FindElement(By.CssSelector("input[type=checkbox]"));
```

**Ruby**

```ruby
shadow_host = driver.find_element(css: 'custom-checkbox-element')
shadow_root = shadow_host.shadow_root
shadow_content = shadow_root.find_element(css: 'input[type=checkbox]')
```

**JavaScript**

```javascript
const shadowHost = await driver.findElement(By.css('custom-checkbox-element'));
const shadowRoot = await shadowHost.getShadowRoot();
const shadowContent = await shadowRoot.findElement(By.css('input[type=checkbox]'));
```

**Kotlin**

```kotlin
val shadowHost = driver.findElement(By.cssSelector("custom-checkbox-element"))
val shadowRoot = shadowHost.shadowRoot
val shadowContent = shadowRoot.findElement(By.cssSelector("input[type=checkbox]"))
```

### Localizador otimizado
Uma pesquisa aninhada pode não ser a estratégia de localização mais eficaz, pois requer dois
comandos separados a serem emitidos para o navegador.

Para melhorar um pouco o desempenho, podemos usar CSS ou XPath para encontrar esse elemento com um único comando.
Veja as [sugestões de estratégia do localizador](https://www.selenium.dev/pt-br/documentation/test_practices/encouraged/locators/index.md) na nossa sessão de
[Práticas de teste incentivadas](https://www.selenium.dev/pt-br/documentation/test_practices/encouraged/index.md).

Para esse exemplo, utilizaremos um seletor CSS:

**Java**

```java
driver.get(LOCATORS_PAGE);
WebElement input = driver.findElement(By.cssSelector("form .information"));
```

**Python**

```python
driver.get(LOCATORS_PAGE)
input_element = driver.find_element(By.CSS_SELECTOR, "form .information")
```

**CSharp**

```csharp
driver.Url = LocatorsPage;
IWebElement input = driver.FindElement(By.CssSelector("form .information"));
```

**Ruby**

```ruby
driver.navigate.to locators_page
input_element = driver.find_element(css: 'form .information')
```

**JavaScript**

```javascript
await driver.get(LOCATORS_PAGE);
const input = await driver.findElement(By.css('form .information'));
```

**Kotlin**

```kotlin
driver.get(locatorsPage)
val input = driver.findElement(By.cssSelector("form .information"))
```

## Todos os elementos correspondentes
Existem vários casos de uso para a necessidade de obter referências a todos os elementos que correspondem a um localizador, em vez
do que apenas o primeiro. Os métodos plurais find elements retornam uma coleção de referências de elementos.
Se não houver correspondências, uma lista vazia será retornada. Nesse caso,
referências a todos os elementos input serão devolvidas em uma coleção.

**Java**

```java
driver.get(LOCATORS_PAGE);
List<WebElement> inputs = driver.findElements(By.tagName("input"));
```

**Python**

```python
driver.get(LOCATORS_PAGE)
inputs = driver.find_elements(By.TAG_NAME, "input")
```

**CSharp**

```csharp
driver.Url = LocatorsPage;
var inputs = driver.FindElements(By.TagName("input"));
```

**Ruby**

```ruby
driver.navigate.to locators_page
inputs = driver.find_elements(tag_name: 'input')
```

**JavaScript**

```javascript
await driver.get(LOCATORS_PAGE);
const inputs = await driver.findElements(By.tagName('input'));
```

**Kotlin**

```kotlin
driver.get(locatorsPage)
val inputs = driver.findElements(By.tagName("input"))
```

### Obter Elemento
Muitas vezes você obterá uma coleção de elementos, mas quer trabalhar apenas com um elemento específico, o que significa que você
precisa iterar sobre a coleção e identificar o que você deseja.

**Java**

```java
List<WebElement> elements = driver.findElements(By.tagName("p"));
for (WebElement element : elements) {
  System.out.println("Paragraph text:" + element.getText());
}
```

**Python**

```python
elements = driver.find_elements(By.TAG_NAME, 'p')
for element in elements:
    print(f"Paragraph text:{element.text}")
```

**CSharp**

```csharp
var elements = driver.FindElements(By.TagName("p"));
foreach (var element in elements)
{
    System.Console.WriteLine("Paragraph text:" + element.Text);
}
```

**Ruby**

```ruby
driver.navigate.to locators_page
elements = driver.find_elements(tag_name: 'p')
elements.each { |e| puts "Paragraph text:#{e.text}" }
```

**JavaScript**

```javascript
const elements = await driver.findElements(By.tagName('p'));
for (const element of elements) {
  console.log('Paragraph text:' + await element.getText());
}
```

**Kotlin**

```kotlin
val elements = driver.findElements(By.tagName("p"))
for (element in elements) {
    println("Paragraph text:" + element.text)
}
```

## Localizar Elementos em um Elemento

Ele é usado para localizar a lista de WebElements filhos correspondentes dentro do contexto do elemento pai.
Para realizar isso, o WebElement pai é encadeado com o 'findElements' para acessar seus elementos filhos.

**Java**

```java
WebElement form = driver.findElement(By.tagName("form"));
List<WebElement> elements = form.findElements(By.tagName("input"));
for (WebElement e : elements) {
  System.out.println(e.getAttribute("value"));
}
```

**Python**

```python
form = driver.find_element(By.TAG_NAME, 'form')
elements = form.find_elements(By.TAG_NAME, 'input')
for element in elements:
    print(element.get_attribute('value'))
```

**CSharp**

```csharp
IWebElement form = driver.FindElement(By.TagName("form"));
var elements = form.FindElements(By.TagName("input"));
foreach (var e in elements)
{
    System.Console.WriteLine(e.GetAttribute("value"));
}
```

**Ruby**

```ruby
driver.navigate.to locators_page
form = driver.find_element(tag_name: 'form')
elements = form.find_elements(tag_name: 'input')
elements.each { |e| puts e.attribute('value') }
```

**JavaScript**

```javascript
const form = await driver.findElement(By.css('form'));
const elements = await form.findElements(By.css('input'));
for (const e of elements) {
  console.log(await e.getAttribute('value'));
}
```

**Kotlin**

```kotlin
val form = driver.findElement(By.tagName("form"))
val elements = form.findElements(By.tagName("input"))
for (e in elements) {
    println(e.getAttribute("value"))
}
```

## Obter elemento ativo

Ele é usado para rastrear (ou) encontrar um elemento DOM que tem o foco no contexto de navegação atual.

**Java**

```java
driver.findElement(By.cssSelector("#fname")).sendKeys("webElement");
String attr = driver.switchTo().activeElement().getAttribute("name");
```

**Python**

```python
driver.find_element(By.CSS_SELECTOR, '#fname').send_keys('webElement')
attr = driver.switch_to.active_element.get_attribute('name')
```

**CSharp**

```csharp
driver.FindElement(By.CssSelector("#fname")).SendKeys("webElement");
string attr = driver.SwitchTo().ActiveElement().GetAttribute("name");
```

**Ruby**

```ruby
driver.navigate.to locators_page
driver.find_element(css: '#fname').send_keys('webElement')
attr = driver.switch_to.active_element.attribute('name')
```

**JavaScript**

```javascript
await driver.findElement(By.css('#fname')).sendKeys('webElement');
const attr = await driver.switchTo().activeElement().getAttribute('name');
```

**Kotlin**

```kotlin
driver.findElement(By.cssSelector("#fname")).sendKeys("webElement")
val attr = driver.switchTo().activeElement().getAttribute("name")
```
