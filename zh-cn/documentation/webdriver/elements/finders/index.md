# 查询网络元素

> 根据提供的定位值定位元素.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/elements/finders/

---

使用 Selenium 最基本的特点之一是获取可用于操作的元素引用。
Selenium 提供了许多内置的 [定位策略](https://www.selenium.dev/zh-cn/documentation/webdriver/elements/locators/index.md)，用于唯一标识元素。
在更复杂的场景中，可以用多种方式使用这些定位器。为了本篇文档的目的，
请使用 [Selenium 定位器测试页面](https://www.selenium.dev/selenium/web/locators_tests/locators.html)。

## 第一个匹配的元素

许多定位器会匹配页面上的多个元素。
单个的 find element 方法会返回在给定上下文中找到的第一个元素的引用。

### 在整个 DOM 中查找

当在 driver 实例上调用 find element 方法时，
它会返回 DOM 中与所提供定位器匹配的第一个元素的引用。
该引用可以被保存并用于后续的元素操作。
在 Selenium 定位器测试页面上，有两个 class 名称为 `information` 的元素，
因此此方法会返回第一个文本输入框。

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

### 在 DOM 的子集内评估

与其在整个 DOM 中寻找唯一的定位器，
通常更有用的是将搜索范围缩小到另一个已定位元素的作用域内。

一种解决办法是先定位目标元素的祖先，
然后在该对象上调用 `find element`：

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

> **Java 和 C#**<br>
> `WebDriver`、`WebElement` 和 `ShadowRoot` 类都实现了 `SearchContext` 接口，  
> 该接口被视为一种 _基于角色的接口_。基于角色的接口可以让你判断特定的驱动实现是否支持某项功能。  
> 这些接口定义清晰，并尽量遵循单一职责原则。

### 评估 Shadow DOM {#evaluating-the-shadow-dom}

Shadow DOM 是隐藏在元素内部的封装 DOM 树。  
自 Chromium 浏览器在 v96 发布后，Selenium 已支持通过易用的 shadow root 方法访问该树。注意：这些方法需要 Selenium 4.0 或更高版本。

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

### 优化后的定位器

嵌套查找可能不是最有效的定位策略，
因为它需要向浏览器发送两次独立的命令。

为略微提升性能，我们可以使用 CSS 或 XPath，在一次命令中定位到该元素。
请参阅本节中关于[定位策略建议](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/locators/index.md)的说明
以及[推荐的测试实践](https://www.selenium.dev/zh-cn/documentation/test_practices/encouraged/index.md)。

在本例中，我们将使用 CSS 选择器：

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

## 所有匹配的元素

在某些情况下，需要获取与定位器匹配的所有元素的引用，而不是仅获取第一个。
复数形式的 `find elements` 方法会返回一组元素引用。如果没有匹配项，则返回空列表。
在本例中，将返回所有 input 元素的引用集合。

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

### 获取元素
有时你会得到一组元素，但想操作其中某个特定元素，
这意味着需要遍历该集合并找到目标元素。

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

## 从元素查找子元素

用于在父元素的上下文中查找匹配的子 WebElement 列表。
为此，可在父 WebElement 上链式调用 `findElements` 来访问子元素。

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

## 获取活动元素

用于跟踪或查找当前浏览上下文中具有焦点的 DOM 元素。

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
