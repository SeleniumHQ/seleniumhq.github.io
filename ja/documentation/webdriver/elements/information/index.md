# Web要素に関する情報

> 要素について学ぶことができること。

URL: https://www.selenium.dev/ja/documentation/webdriver/elements/information/

---

特定の要素についてクエリできる詳細情報がいくつかあります。

## 表示されているかどうか

This method is used to check if the connected Element is
displayed on a webpage. Returns a `Boolean` value,
True if the connected element is displayed in the current
browsing context else returns false.

This functionality is [mentioned in](https://w3c.github.io/webdriver/#element-displayedness), but not defined by
the w3c specification due to the
[impossibility of covering all potential conditions](https://www.youtube.com/watch?v=LAD_XPGP_kk).
As such, Selenium cannot expect drivers to implement
this functionality directly, and now relies on
executing a large JavaScript function directly.
This function makes many approximations about an element's
nature and relationship in the tree to return a value.

**Java**

```java
boolean isEmailVisible = driver.findElement(By.name("email_input")).isDisplayed();
assertTrue(isEmailVisible);
```

**Python**

```python
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

# isDisplayed
is_email_visible = driver.find_element(By.NAME, "email_input").is_displayed()
```

**CSharp**

```csharp
// Navigate to Url
driver.Url= "https://www.selenium.dev/selenium/web/inputs.html";
// isDisplayed        
// Get boolean value for is element display
bool isEmailVisible = driver.FindElement(By.Name("email_input")).Displayed;
```

**Ruby**

```ruby
displayed_value = driver.find_element(name: 'email_input').displayed?
```

**JavaScript**

```javascript
// Resolves Promise and returns boolean value
let result =  await driver.findElement(By.name("email_input")).isDisplayed();
```

**Kotlin**

```kotlin
//navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns true if element is displayed else returns false 
 val flag = driver.findElement(By.name("email_input")).isDisplayed()
```

## 要素が有効か

このメソッドは、接続された要素がWebページで有効または無効になっているかどうかを確認するために使います。
ブール値を返し、現在のブラウジングコンテキストで接続されている要素が
**有効（enabled）** になっている場合は **True** 、そうでない場合は **false** を返します。

**Java**

```java
boolean isEnabledButton = driver.findElement(By.name("button_input")).isEnabled();
assertTrue(isEnabledButton);
```

**Python**

```python
is_enabled_button = driver.find_element(By.NAME, "button_input").is_enabled()
```

**CSharp**

```csharp
// isEnabled
// returns true if element is enabled else returns false
bool isEnabledButton = driver.FindElement(By.Name("button_input")).Enabled;
```

**Ruby**

```ruby
enabled_value = driver.find_element(name: 'email_input').enabled?
```

**JavaScript**

```javascript
// Resolves Promise and returns boolean value
let element =  await driver.findElement(By.name("button_input")).isEnabled();
```

**Kotlin**

```kotlin
 //navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns true if element is enabled else returns false 
 val attr = driver.findElement(By.name("button_input")).isEnabled()

```

## 要素が選択されているかどうか

このメソッドは、参照された要素が選択されているかどうかを判断します。
このメソッドは、チェックボックス、ラジオボタン、入力要素、およびオプション要素で広く使われています。

ブール値を返し、現在のブラウジングコンテキストで参照された要素が **選択されている** 場合は **True** 、そうでない場合は **false** を返します。

**Java**

```java
boolean isSelectedCheck = driver.findElement(By.name("checkbox_input")).isSelected();
assertTrue(isSelectedCheck);
```

**Python**

```python
is_selected_check = driver.find_element(By.NAME, "checkbox_input").is_selected()
```

**CSharp**

```csharp
// isSelected
// returns true if element is checked else returns false
bool isSelectedCheck = driver.FindElement(By.Name("checkbox_input")).Selected;
```

**Ruby**

```ruby
selected_value = driver.find_element(name: 'email_input').selected?
```

**JavaScript**

```javascript
// Returns true if element is checked else returns false
let isSelected = await driver.findElement(By.name("checkbox_input")).isSelected();
```

**Kotlin**

```kotlin
//navigates to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

//returns true if element is checked else returns false
val attr =  driver.findElement(By.name("checkbox_input")).isSelected()
```

## 要素のタグ名を取得

これは、現在のブラウジングコンテキストにフォーカスがある参照された要素の
[TagName](https://www.w3.org/TR/webdriver/#dfn-get-element-tag-name) を取得するために使います。

**Java**

```java
String tagNameInp = driver.findElement(By.name("email_input")).getTagName();
assertEquals("input", tagNameInp);
```

**Python**

```python
tag_name_inp = driver.find_element(By.NAME, "email_input").tag_name
```

**CSharp**

```csharp
// TagName
// returns TagName of the element
string tagNameInp = driver.FindElement(By.Name("email_input")).TagName;
```

**Ruby**

```ruby
tag_name = driver.find_element(name: 'email_input').tag_name
```

**JavaScript**

```javascript
// Returns TagName of the element
let value = await driver.findElement(By.name('email_input')).getTagName();
```

**Kotlin**

```kotlin
//navigates to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

//returns TagName of the element
val attr =  driver.findElement(By.name("email_input")).getTagName()
```

## 要素矩形を取得

参照される要素の寸法と座標を取得するために使います。

取得データのbodyには、次の詳細が含まれます。
* 要素の左上隅からのx軸の位置
* 要素の左上隅からのy軸の位置
* 要素の高さ
* 要素の幅

**Java**

```java
Rectangle res = driver.findElement(By.name("range_input")).getRect();
// Rectangle class provides getX,getY, getWidth, getHeight methods
assertEquals(10, res.getX());
```

**Python**

```python
rect = driver.find_element(By.NAME, "range_input").rect
```

**CSharp**

```csharp
// Get Location and Size
// Get Location
IWebElement rangeElement = driver.FindElement(By.Name("range_input"));
Point point = rangeElement.Location;
```

**Ruby**

```ruby
size = driver.find_element(name: 'email_input').size
```

**JavaScript**

```javascript
let object = await driver.findElement(By.name('range_input')).getRect();
```

**Kotlin**

```kotlin
// Navigate to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

// Returns height, width, x and y coordinates referenced element
val res = driver.findElement(By.name("range_input")).rect

// Rectangle class provides getX,getY, getWidth, getHeight methods
println(res.getX())

```

## 要素のCSSの値を取得

現在のブラウジングコンテキスト内の要素の指定された計算したスタイル属性の値を取得します。

**Java**

```java
String cssValue = driver.findElement(By.name("color_input")).getCssValue("font-size");
assertEquals(cssValue, "13.3333px");
```

**Python**

```python
css_value = driver.find_element(By.NAME, "color_input").value_of_css_property(
    "font-size"
)
```

**CSharp**

```csharp
// Retrieves the computed style property 'font-size' of field
string cssValue = driver.FindElement(By.Name("color_input")).GetCssValue("font-size");
```

**Ruby**

```ruby
css_value = driver.find_element(name: 'email_input').css_value('background-color')
```

**JavaScript**

```javascript
await driver.get('https://www.selenium.dev/selenium/web/colorPage.html');
// Returns background color of the element
let value = await driver.findElement(By.id('namedColor')).getCssValue('background-color');
```

**Kotlin**

```kotlin
// Navigate to Url
driver.get("https://www.selenium.dev/selenium/web/colorPage.html")

// Retrieves the computed style property 'color' of linktext
val cssValue = driver.findElement(By.id("namedColor")).getCssValue("background-color")
```

## 要素テキストを取得

指定された要素のレンダリングされたテキストを取得します。

**Java**

```java
String text = driver.findElement(By.tagName("h1")).getText();
assertEquals(text, "Testing Inputs");
```

**Python**

```python
text = driver.find_element(By.TAG_NAME, "h1").text
```

**CSharp**

```csharp
// GetText
// Retrieves the text of the element
string text = driver.FindElement(By.TagName("h1")).Text;
```

**Ruby**

```ruby
text = driver.find_element(xpath: '//h1').text
```

**JavaScript**

```javascript
await driver.get('https://www.selenium.dev/selenium/web/linked_image.html');
// Returns text of the element
let text = await driver.findElement(By.id('justanotherLink')).getText();
```

**Kotlin**

```kotlin
// Navigate to URL
driver.get("https://www.selenium.dev/selenium/web/linked_image.html")

// retrieves the text of the element
val text = driver.findElement(By.id("justanotherlink")).getText()
```

## Fetching Attributes or Properties

Fetches the run time value associated with a 
DOM attribute. It returns the data associated 
with the DOM attribute or property of the element. 

**Java**

```java
WebElement emailTxt = driver.findElement(By.name(("email_input")));
// fetch the value property associated with the textbox
String valueInfo = emailTxt.getAttribute("value");
assertEquals(valueInfo, "admin@localhost");
```

**Python**

```python
# FetchAttributes
email_txt = driver.find_element(By.NAME, "email_input")
value_info = email_txt.get_attribute("value")
```

**CSharp**

```csharp
// FetchAttributes
// identify the email text box
IWebElement emailTxt = driver.FindElement(By.Name("email_input"));
// fetch the value property associated with the textbox
string valueInfo = emailTxt.GetAttribute("value");
```

**Ruby**

```ruby
attribute_value = driver.find_element(name: 'number_input').attribute('value')
```

**JavaScript**

```javascript
// identify the email text box
const emailElement = await driver.findElement(By.xpath('//input[@name="email_input"]'));

//fetch the attribute "name" associated with the textbox
const nameAttribute = await emailElement.getAttribute("name");
```

**Kotlin**

```kotlin
// Navigate to URL
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

//fetch the value property associated with the textbox
val attr = driver.findElement(By.name("email_input")).getAttribute("value")
```
