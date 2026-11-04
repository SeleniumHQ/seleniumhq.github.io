# 要素を探す

> DOM内の1つ以上の特定の要素を識別する方法

URL: https://www.selenium.dev/ja/documentation/webdriver/elements/locators/

---

ロケーターは、ページ上の要素を識別する方法です。 これは、[検索要素](https://www.selenium.dev/ja/documentation/webdriver/elements/finders/index.md) メソッドに渡される引数です。

検出方法とは別にロケーターを宣言するタイミングと理由など、
[ロケーター](https://www.selenium.dev/ja/documentation/test_practices/encouraged/locators/index.md)に関するヒントについては、
[推奨されるテストプラクティス](https://www.selenium.dev/ja/documentation/test_practices/encouraged/index.md) を確認してください。

### 要素選択の方法

WebDriverには標準のロケータが8種類あります。

| ロケータ | 詳細 |
| -------- | ---------- |
| class name | class名に値を含む要素を探す (複合クラス名は使えない) |
| css selector | CSSセレクタが一致する要素を探す |
| id | id属性が一致する要素を探す |
| name | name属性が一致する要素を探す |
| link text | a要素のテキストが一致する要素を探す|
| partial link text | a要素のテキストが部分一致する要素を探す |
| tag name | タグ名が一致する要素を探す |
| xpath | XPathと一致する要素を探す |

## Creating Locators

To work on a web element using Selenium, we need to first locate it on the web page.
Selenium provides us above mentioned ways, using which we can locate element on the 
page. To understand and create locator we will use the following HTML snippet.

```html
<html>
<body>
<style>
.information {
  background-color: white;
  color: black;
  padding: 10px;
}
</style>
<h2>Contact Selenium</h2>

<form>
  <input type="radio" name="gender" value="m" />Male &nbsp;
  <input type="radio" name="gender" value="f" />Female <br>
  <br>
  <label for="fname">First name:</label><br>
  <input class="information" type="text" id="fname" name="fname" value="Jane"><br><br>
  <label for="lname">Last name:</label><br>
  <input class="information" type="text" id="lname" name="lname" value="Doe"><br><br>
  <label for="newsletter">Newsletter:</label>
  <input type="checkbox" name="newsletter" value="1" /><br><br>
  <input type="submit" value="Submit">
</form> 

<p>To know more about Selenium, visit the official page 
<a href ="www.selenium.dev">Selenium Official Page</a> 
</p>

</body>
</html>
```

## class name
HTML elements often feature a class attribute, just like the one in the snippet above. 
In Selenium, we can locate these specific elements using the class name locator.

**Java**

```java
WebElement element = driver.findElement(By.className("information"));
```

**Python**

```python
driver = webdriver.Chrome()
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
element = driver.find_element(By.CLASS_NAME, "information")
```

**CSharp**

```csharp
IWebElement element = driver.FindElement(By.ClassName("information"));
Assert.AreEqual("input", element.TagName);
```

**Ruby**

```ruby
driver.find_element(class: 'information')
```

**JavaScript**

```javascript
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.className('information'));

```

**Kotlin**

```kotlin
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.className("information"))

```

## css selector
CSS is the language used to style HTML pages. We can use css selector locator strategy
to identify the element on the page. If the element has an id, we create the locator
as css = #id. Otherwise the format we follow is css =[attribute=value] .
Let us see an example from above HTML snippet. We will create locator for First Name 
textbox, using css. 

**Java**

```java
WebElement element = driver.findElement(By.cssSelector("#fname"));
```

**Python**

```python
driver = webdriver.Chrome()
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
element = driver.find_element(By.CSS_SELECTOR, "#fname")
```

**CSharp**

```csharp
element = driver.FindElement(By.CssSelector("#fname"));
Assert.AreEqual("Jane", element.GetAttribute("value"));
```

**Ruby**

```ruby
driver.find_element(css: '#fname')
```

**JavaScript**

```javascript
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.css('#fname'));

```

**Kotlin**

```kotlin
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.css("#fname"))

```

## id
We can use the ID attribute available with element in a web page to locate it. 
Ideally the ID property should be unique for a element on the web page. 
We will identify the Last Name field using it. 

**Java**

```java
WebElement element = driver.findElement(By.id("lname"));
```

**Python**

```python
driver = webdriver.Chrome()
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
element = driver.find_element(By.ID, "lname")
```

**CSharp**

```csharp
element = driver.FindElement(By.Id("lname"));
Assert.AreEqual("Doe", element.GetAttribute("value"));
```

**Ruby**

```ruby
driver.find_element(id: 'lname')
```

**JavaScript**

```javascript
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.id('lname'));

```

**Kotlin**

```kotlin
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.id("lname"))

```

## name
We can use the NAME attribute available with element in a web page to locate it. 
Ideally the NAME property should be unique for a element on the web page. 
We will identify the Newsletter checkbox using it. 

**Java**

```java
WebElement element = driver.findElement(By.name("newsletter"));
```

**Python**

```python
driver = webdriver.Chrome()
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
element = driver.find_element(By.NAME, "newsletter")
```

**CSharp**

```csharp
element = driver.FindElement(By.Name("newsletter"));
Assert.AreEqual("input", element.TagName);
```

**Ruby**

```ruby
driver.find_element(name: 'newsletter')
```

**JavaScript**

```javascript
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.name('newsletter'));

```

**Kotlin**

```kotlin
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.name("newsletter"))

```

## link text 
If the element we want to locate is a link, we can use the link text locator
to identify it on the web page. The link text is the visible text of the link. 
In the HTML snippet shared, we have a link available, lets see how will we locate it. 

**Java**

```java
WebElement element = driver.findElement(By.linkText("Selenium Official Page"));
```

**Python**

```python
driver = webdriver.Chrome()
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
element = driver.find_element(By.LINK_TEXT, "Selenium Official Page")
```

**CSharp**

```csharp
element = driver.FindElement(By.LinkText("Selenium Official Page"));
Assert.AreEqual("https://www.selenium.dev/", element.GetAttribute("href"));
```

**Ruby**

```ruby
driver.find_element(link_text: 'Selenium Official Page')
```

**JavaScript**

```javascript
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.linkText('Selenium Official Page'));

```

**Kotlin**

```kotlin
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.linkText("Selenium Official Page"))

```

## partial link text 
For links, we can use the partial link text locator to identify elements 
based on a portion of their visible text, rather than needing the entire string.

**Java**

```java
WebElement element = driver.findElement(By.partialLinkText("Official Page"));
```

**Python**

```python
driver = webdriver.Chrome()
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
element = driver.find_element(By.PARTIAL_LINK_TEXT, "Official Page")
```

**CSharp**

```csharp
element = driver.FindElement(By.PartialLinkText("Official Page"));
Assert.AreEqual("https://www.selenium.dev/", element.GetAttribute("href"));
```

**Ruby**

```ruby
driver.find_element(partial_link_text: 'Official Page')
```

**JavaScript**

```javascript
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.partialLinkText('Official Page'));

```

**Kotlin**

```kotlin
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.partialLinkText("Official Page"))

```

## tag name
We can use the HTML TAG itself of the web element to identify it on the page.
From the above HTML snippet shared, lets identify the link, using its html tag "a". 

**Java**

```java
WebElement element = driver.findElement(By.tagName("a"));
```

**Python**

```python
driver = webdriver.Chrome()
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
element = driver.find_element(By.TAG_NAME, "a")
```

**CSharp**

```csharp
element = driver.FindElement(By.TagName("a"));
Assert.AreEqual("https://www.selenium.dev/", element.GetAttribute("href"));
```

**Ruby**

```ruby
driver.find_element(tag_name: 'a')
```

**JavaScript**

```javascript
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.tagName('a'));

```

**Kotlin**

```kotlin
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.tagName("a"))

```

## xpath 
Think of an HTML document as an XML tree—you can navigate through this structure using XPath.
An absolute XPath starts from the very root of the document (e.g., /html/form/input[1] 
selects the male radio button).
A relative XPath targets elements directly (e.g., //input[@name='fname'] selects 
the first name text box).
Let's see an example to create the XPath locator for the female radio button.

**Java**

```java
WebElement element = driver.findElement(By.xpath("//input[@value='f']"));
```

**Python**

```python
driver = webdriver.Chrome()
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
element = driver.find_element(By.XPATH, "//input[@value='f']")
```

**CSharp**

```csharp
element = driver.FindElement(By.XPath("//input[@value='f']"));
Assert.AreEqual("radio", element.GetAttribute("type"));
```

**Ruby**

```ruby
driver.find_element(xpath: "//input[@value='f']")
```

**JavaScript**

```javascript
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.xpath('//input[@value='f']'));

```

**Kotlin**

```kotlin
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.xpath('//input[@value='f']'))

```

## Utilizing Locators

The FindElement makes using locators a breeze! For most languages, 
all you need to do is utilize `webdriver.common.by.By`, however in 
others it's as simple as setting a parameter in the FindElement function

### By

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
    import org.openqa.selenium.By;
    WebDriver driver = new ChromeDriver();
	driver.findElement(By.className("information"));

```

**Python**

```python
    from selenium.webdriver.common.by import By
    driver = webdriver.Chrome()
	driver.find_element(By.CLASS_NAME, "information")

```

**CSharp**

```csharp
    var driver = new ChromeDriver();
	driver.FindElement(By.ClassName("information"));

```

**Ruby**

```ruby
driver.find_element(class: 'information')
```

**JavaScript**

```javascript
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.className('information'));

```

**Kotlin**

```kotlin
    import org.openqa.selenium.By
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.className("information"))

```

### ByChained

The `ByChained` class enables you to _chain_ two By locators together. For example, instead of having to 
locate a parent element, and then a child element of that parent, you can instead combine those two `FindElement` 
functions into one.

**Java**

```java
By locator = new ByChained(By.id("login-form"), By.tagName("input"));
WebElement usernameInput = driver.findElement(locator);
```

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ByAll

The `ByAll` class enables you to utilize two By locators at once, finding elements that match _either_ of your By locators. 
For example, instead of having to utilize two `FindElement()` functions to find the username and password input fields separately, 
you can instead find them together in one clean `FindElements()`

**Java**

```java
By locator = new ByAll(By.id("password-field"), By.id("username-field"));
List<WebElement> loginInputs = driver.findElements(locator);
```

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 相対ロケーター

**Selenium 4** introduces Relative Locators (previously
called as _Friendly Locators_). These locators are helpful when it is not easy to construct a locator for
the desired element, but easy to describe spatially where the element is in relation to an element that does have
an easily constructed locator.

### How it works

Selenium uses the JavaScript function
[getBoundingClientRect()](https://developer.mozilla.org/en-US/docs/Web/API/Element/getBoundingClientRect)
to determine the size and position of elements on the page, and can use this information to locate neighboring elements.  
find the relative elements.

Relative locator methods can take as the argument for the point of origin, either a previously located element reference,
or another locator. In these examples we'll be using locators only, but you could swap the locator in the final method with
an element object and it will work the same.

Let us consider the below example for understanding the relative locators.

![Relative Locators](https://www.selenium.dev/images/documentation/webdriver/relative_locators.png)

### Available relative locators

#### Above

If the email text field element is not easily identifiable for some reason, but the password text field element is,
we can locate the text field element using the fact that it is an "input" element "above" the password element. 

**Java**

```java
By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("password"));
```

**Python**

```python
email_locator = locate_with(By.TAG_NAME, "input").above({By.ID: "password"})
```

**CSharp**

```csharp
var emailLocator = RelativeBy.WithLocator(By.TagName("input")).Above(By.Id("password"));
```

**Ruby**

```ruby
driver.find_element({relative: {tag_name: 'input', above: {id: 'password'}}})
```

**JavaScript**

```javascript
let emailLocator = locateWith(By.tagName('input')).above(By.id('password'));
```

**Kotlin**

```kotlin
val emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("password"))
```

#### Below

If the password text field element is not easily identifiable for some reason, but the email text field element is,
we can locate the text field element using the fact that it is an "input" element "below" the email element.

**Java**

```java
By passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.id("email"));
```

**Python**

```python
password_locator = locate_with(By.TAG_NAME, "input").below({By.ID: "email"})
```

**CSharp**

```csharp
var passwordLocator = RelativeBy.WithLocator(By.TagName("input")).Below(By.Id("email"));
```

**Ruby**

```ruby
driver.find_element({relative: {tag_name: 'input', below: {id: 'email'}}})
```

**JavaScript**

```javascript
let passwordLocator = locateWith(By.tagName('input')).below(By.id('email'));
```

**Kotlin**

```kotlin
val passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.id("email"))
```

#### Left of

If the cancel button is not easily identifiable for some reason, but the submit button element is,
we can locate the cancel button element using the fact that it is a "button" element to the "left of" the submit element.

**Java**

```java
By cancelLocator = RelativeLocator.with(By.tagName("button")).toLeftOf(By.id("submit"));
```

**Python**

```python
cancel_locator = locate_with(By.TAG_NAME, "button").to_left_of({By.ID: "submit"})
```

**CSharp**

```csharp
var cancelLocator = RelativeBy.WithLocator(By.tagName("button")).LeftOf(By.Id("submit"));
```

**Ruby**

```ruby
driver.find_element({relative: {tag_name: 'button', left: {id: 'submit'}}})
```

**JavaScript**

```javascript
let cancelLocator = locateWith(By.tagName('button')).toLeftOf(By.id('submit'));
```

**Kotlin**

```kotlin
val cancelLocator = RelativeLocator.with(By.tagName("button")).toLeftOf(By.id("submit"))
```

#### Right of

If the submit button is not easily identifiable for some reason, but the cancel button element is,
we can locate the submit button element using the fact that it is a "button" element "to the right of" the cancel element.

**Java**

```java
By submitLocator = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("cancel"));
```

**Python**

```python
submit_locator = locate_with(By.TAG_NAME, "button").to_right_of({By.ID: "cancel"})
```

**CSharp**

```csharp
var submitLocator = RelativeBy.WithLocator(By.tagName("button")).RightOf(By.Id("cancel"));
```

**Ruby**

```ruby
driver.find_element({relative: {tag_name: 'button', right: {id: 'cancel'}}})
```

**JavaScript**

```javascript
let submitLocator = locateWith(By.tagName('button')).toRightOf(By.id('cancel'));
```

**Kotlin**

```kotlin
val submitLocator = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("cancel"))
```

#### Near

If the relative positioning is not obvious, or it varies based on window size, you can use the near method to 
identify an element that is at most `50px` away from the provided locator.
One great use case for this is to work with a form element that doesn't have an easily constructed locator,
but its associated [input label element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/label) does.

**Java**

```java
By emailLocator = RelativeLocator.with(By.tagName("input")).near(By.id("lbl-email"));
```

**Python**

```python
email_locator = locate_with(By.TAG_NAME, "input").near({By.ID: "lbl-email"})
```

**CSharp**

```csharp
var emailLocator = RelativeBy.WithLocator(By.tagName("input")).Near(By.Id("lbl-email"));
```

**Ruby**

```ruby
driver.find_element({relative: {tag_name: 'input', near: {id: 'lbl-email'}}})
```

**JavaScript**

```javascript
let emailLocator = locateWith(By.tagName('input')).near(By.id('lbl-email'));
```

**Kotlin**

```kotlin
val emailLocator = RelativeLocator.with(By.tagName("input")).near(By.id("lbl-email"));
```

### Chaining relative locators

You can also chain locators if needed. Sometimes the element is most easily identified as being both above/below one element and right/left of another.

**Java**

```java
By submitLocator = RelativeLocator.with(By.tagName("button")).below(By.id("email")).toRightOf(By.id("cancel"));
```

**Python**

```python
submit_locator = locate_with(By.TAG_NAME, "button").below({By.ID: "email"}).to_right_of({By.ID: "cancel"})
```

**CSharp**

```csharp
var submitLocator = RelativeBy.WithLocator(By.tagName("button")).Below(By.Id("email")).RightOf(By.Id("cancel"));
```

**Ruby**

```ruby
driver.find_element({relative: {tag_name: 'button', below: {id: 'email'}, right: {id: 'cancel'}}})
```

**JavaScript**

```javascript
let submitLocator = locateWith(By.tagName('button')).below(By.id('email')).toRightOf(By.id('cancel'));
```

**Kotlin**

```kotlin
val submitLocator = RelativeLocator.with(By.tagName("button")).below(By.id("email")).toRightOf(By.id("cancel"))
```
