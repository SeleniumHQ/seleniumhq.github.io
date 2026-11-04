# Working with IFrames and frames

URL: https://www.selenium.dev/pt-br/documentation/webdriver/interactions/frames/

---

Frames são um meio obsoleto de construir um layout de site a partir de
vários documentos no mesmo domínio. É improvável que você trabalhe com eles
a menos que você esteja trabalhando com um webapp pré-HTML5. Iframes permitem
a inserção de um documento de um domínio totalmente diferente, e são
ainda comumente usado.

Se você precisa trabalhar com frames ou iframes, o WebDriver permite que você
trabalhe com eles da mesma maneira. Considere um botão dentro de um iframe.
Se inspecionarmos o elemento usando as ferramentas de desenvolvimento do navegador, podemos
ver o seguinte:

```html
<div id="modal">
  <iframe id="buttonframe" name="myframe"  src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
```

Se não fosse pelo iframe, esperaríamos clicar no botão
usando algo como:

**Java**

```java
//This won't work
driver.findElement(By.tagName("button")).click();

```

**Python**

```python
    # This Wont work
driver.find_element(By.TAG_NAME, 'button').click()

```

**CSharp**

```csharp
//This won't work
driver.FindElement(By.TagName("button")).Click();

```

**Ruby**

```ruby
    # This won't work
driver.find_element(:tag_name,'button').click

```

**JavaScript**

```javascript
// This won't work
await driver.findElement(By.css('button')).click();

```

**Kotlin**

```kotlin
//This won't work
driver.findElement(By.tagName("button")).click()

```

No entanto, se não houver botões fora do iframe, você pode
em vez disso, obter um erro _no such element_. Isso acontece porque o Selenium é
ciente apenas dos elementos no documento de nível superior. Para interagir com
o botão, precisamos primeiro mudar para o quadro, de forma semelhante
a como alternamos janelas. WebDriver oferece três maneiras de mudar para
um frame.

## Usando um WebElement

Alternar usando um WebElement é a opção mais flexível. Você pode
encontrar o quadro usando seu seletor preferido e mudar para ele.

**Java**

```java
//switch To IFrame using Web Element
WebElement iframe = driver.findElement(By.id("iframe1"));
//Switch to the frame
driver.switchTo().frame(iframe);
assertEquals(true, driver.getPageSource().contains("We Leave From Here"));
//Now we can type text into email field
WebElement emailE = driver.findElement(By.id("email"));
emailE.sendKeys("admin@selenium.dev");
emailE.clear();
```

**Python**

```python
# --- Switch to iframe using WebElement ---
iframe = driver.find_element(By.ID, "iframe1")
driver.switch_to.frame(iframe)
assert "We Leave From Here" in driver.page_source

email_element = driver.find_element(By.ID, "email")
email_element.send_keys("admin@selenium.dev")
email_element.clear()
driver.switch_to.default_content()
```

**CSharp**

```csharp
//switch To IFrame using Web Element
IWebElement iframe = driver.FindElement(By.Id("iframe1"));
//Switch to the frame
driver.SwitchTo().Frame(iframe);
Assert.AreEqual(true, driver.PageSource.Contains("We Leave From Here"));
//Now we can type text into email field
IWebElement emailE = driver.FindElement(By.Id("email"));
emailE.SendKeys("admin@selenium.dev");
emailE.Clear();
```

**Ruby**

```ruby
iframe = driver.find_element(:id, 'iframe1')
driver.switch_to.frame(iframe)
expect(driver.page_source).to include('We Leave From Here')

email_element = driver.find_element(:id, 'email')
email_element.send_keys('admin@selenium.dev')
email_element.clear
driver.switch_to.default_content
```

**JavaScript**

```javascript
// Store the web element
const iframe = driver.findElement(By.css('#modal > iframe'));

// Switch to the frame
await driver.switchTo().frame(iframe);

// Now we can click the button
await driver.findElement(By.css('button')).click();

```

**Kotlin**

```kotlin
//Store the web element
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

//Switch to the frame
driver.switchTo().frame(iframe)

//Now we can click the button
driver.findElement(By.tagName("button")).click()

```

## Usando um *name* ou ID
Se o seu frame ou iframe tiver um atributo id ou name, ele pode ser
usado alternativamente. Se o name ou ID não for exclusivo na página, o
primeiro encontrado será utilizado.

**Java**

```java
//switch To IFrame using name or id
WebElement iframe1=driver.findElement(By.name("iframe1-name"));
//Switch to the frame
driver.switchTo().frame(iframe1);
assertEquals(true, driver.getPageSource().contains("We Leave From Here"));
WebElement email = driver.findElement(By.id("email"));
//Now we can type text into email field
email.sendKeys("admin@selenium.dev");
email.clear();
```

**Python**

```python
# --- Switch to iframe using name or ID ---
iframe1=driver.find_element(By.NAME, "iframe1-name")  # (This line doesn't switch, just locates)
driver.switch_to.frame(iframe1)
assert "We Leave From Here" in driver.page_source

email = driver.find_element(By.ID, "email")
email.send_keys("admin@selenium.dev")
email.clear()
driver.switch_to.default_content()
```

**CSharp**

```csharp
//switch To IFrame using name or id
IWebElement iframe1=driver.FindElement(By.Name("iframe1-name"));
//Switch to the frame
driver.SwitchTo().Frame(iframe1);
Assert.AreEqual(true, driver.PageSource.Contains("We Leave From Here"));
IWebElement email = driver.FindElement(By.Id("email"));
//Now we can type text into email field
email.SendKeys("admin@selenium.dev");
email.Clear();
```

**Ruby**

```ruby
iframe1 = driver.find_element(:name, 'iframe1-name')
driver.switch_to.frame(iframe1)
expect(driver.page_source).to include('We Leave From Here')

email = driver.find_element(:id, 'email')
email.send_keys('admin@selenium.dev')
email.clear
driver.switch_to.default_content
```

**JavaScript**

```javascript
// Using the ID
await driver.switchTo().frame('buttonframe');

// Or using the name instead
await driver.switchTo().frame('myframe');

// Now we can click the button
await driver.findElement(By.css('button')).click();

```

**Kotlin**

```kotlin
//Using the ID
driver.switchTo().frame("buttonframe")

//Or using the name instead
driver.switchTo().frame("myframe")

//Now we can click the button
driver.findElement(By.tagName("button")).click()

```

## Usando um índice

Também é possível usar o índice do frame, podendo ser
consultado usando _window.frames_ em JavaScript.

**Java**

```java
//switch To IFrame using index
driver.switchTo().frame(0);
```

**Python**

```python
driver.switch_to.frame(0)
assert "We Leave From Here" in driver.page_source
```

**CSharp**

```csharp
//switch To IFrame using index
driver.SwitchTo().Frame(0);
```

**Ruby**

```ruby
driver.switch_to.frame(0)
expect(driver.page_source).to include('We Leave From Here')
```

**JavaScript**

```javascript
// Switches to the second frame
await driver.switchTo().frame(1);
```

**Kotlin**

```kotlin
// Switches to the second frame
driver.switchTo().frame(1)
```

## Deixando um frame

Para deixar um iframe ou frameset, volte para o conteúdo padrão
como a seguir:

**Java**

```java
//leave frame
driver.switchTo().defaultContent();
```

**Python**

```python
driver.switch_to.default_content()
assert "This page has iframes" in driver.page_source
```

**CSharp**

```csharp
//leave frame
driver.SwitchTo().DefaultContent();
```

**Ruby**

```ruby
driver.switch_to.default_content
expect(driver.page_source).to include('This page has iframes')
```

**JavaScript**

```javascript
// Return to the top level
await driver.switchTo().defaultContent();
```

**Kotlin**

```kotlin
// Return to the top level
driver.switchTo().defaultContent()
```
