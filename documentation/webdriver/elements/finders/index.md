# Finding web elements

> Locating the elements based on the provided locator values.

URL: https://www.selenium.dev/documentation/webdriver/elements/finders/
Site index: https://www.selenium.dev/llms.txt

---

One of the most fundamental aspects of using Selenium is obtaining element references to work with.
Selenium offers a number of built-in [locator strategies](https://www.selenium.dev/documentation/webdriver/elements/locators/index.md) to uniquely identify an element.
There are many ways to use the locators in very advanced scenarios. For the purposes of this documentation,
use the [Selenium locator test page](https://www.selenium.dev/selenium/web/locators_tests/locators.html).

## First matching element 

Many locators will match multiple elements on the page. The singular find element method will return a reference to the
first element found within a given context.

### Evaluating entire DOM

When the find element method is called on the driver instance, it 
returns a reference to the first element in the DOM that matches with the provided locator. 
This value can be stored and used for future element actions. On the Selenium locator test page, there are
two elements with the class name `information`, so this method returns the first text input.

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

### Evaluating a subset of the DOM

Rather than finding a unique locator in the entire DOM, it is often useful to narrow the search to the scope
of another located element.

One solution is to locate an ancestor of the desired element, then call find element on that object:

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

> **Java and C#**<br>
> `WebDriver`, `WebElement` and `ShadowRoot` classes all implement a `SearchContext` interface, which is
> considered a _role-based interface_. Role-based interfaces allow you to determine whether a particular
> driver implementation supports a given feature. These interfaces are clearly defined and try 
> to adhere to having only a single role of responsibility.

### Evaluating the Shadow DOM

The Shadow DOM is an encapsulated DOM tree hidden inside an element. 
With the release of v96 in Chromium Browsers, Selenium can now allow you to access this tree with 
easy-to-use shadow root methods. NOTE: These methods require Selenium 4.0 or greater.

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

### Optimized locator

A nested lookup might not be the most effective location strategy since it requires two
separate commands to be issued to the browser.

To improve the performance slightly, we can use either CSS or XPath to find this element in a single command.
See the [Locator strategy suggestions](https://www.selenium.dev/documentation/test_practices/encouraged/locators/index.md) in our 
[Encouraged test practices](https://www.selenium.dev/documentation/test_practices/encouraged/index.md) section.

For this example, we'll use a CSS selector:

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

## All matching elements

There are several use cases for needing to get references to all elements that match a locator, rather
than just the first one. The plural find elements methods return a collection of element references. 
If there are no matches, an empty list is returned. In this case, 
references to all input elements will be returned in a collection.

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

### Get element
Often you get a collection of elements but want to work with a specific element, which means you
need to iterate over the collection and identify the one you want.

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

## Find Elements From Element

It is used to find the list of matching child WebElements within the context of parent element.
To achieve this, the parent WebElement is chained with 'findElements' to access child elements

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

## Get Active Element

It is used to track (or) find DOM element which has the focus in the current browsing context.

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
