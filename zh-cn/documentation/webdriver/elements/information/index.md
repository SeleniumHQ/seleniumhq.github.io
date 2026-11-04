# 关于网络元素的信息

> 元素相关的知识.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/elements/information/

---

您可以查询有关特定元素的许多详细信息。

## 是否显示

此方法用于检查连接的元素是否正确显示在网页上. 返回一个 `Boolean` 值，
如果连接的元素显示在当前的浏览器上下文中，则为True，否则返回false。

此功能[于W3C规范中提及](https://w3c.github.io/webdriver/#element-displayedness)，
但由于[无法覆盖所有潜在条件](https://www.youtube.com/watch?v=LAD_XPGP_kk)而无法定义。
因此，Selenium不能期望驱动程序直接实现这种功能，现在依赖于直接执行大量JavaScript函数。
这个函数对一个元素的性质和在树中的关系做了许多近似的判断，以返回一个值。

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

## 是否启用

此方法用于检查所连接的元素在网页上是启用还是禁用状态。
返回一个布尔值，如果在当前浏览上下文中是 **启用** 状态，则返回 **true**，否则返回 **false**。

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

## 是否被选定

此方法确认相关的元素是否 _已选定_，常用于复选框、单选框、输入框和选择元素中。

该方法返回一个布尔值，如果在当前浏览上下文中 **选择了** 引用的元素，则返回 **True**，否则返回 **False**。

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

## 获取元素标签名

此方法用于获取在当前浏览上下文中具有焦点的被引用元素的[TagName](https://www.w3.org/TR/webdriver/#dfn-get-element-tag-name)。

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

## 位置和大小

用于获取参照元素的尺寸和坐标。

提取的数据主体包含以下详细信息：
* 元素左上角的X轴位置
* 元素左上角的y轴位置
* 元素的高度
* 元素的宽度

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

## 获取元素CSS值

获取当前浏览上下文中元素的特定计算样式属性的值。

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

## 文本内容

获取特定元素渲染后的文本内容。

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

## 获取特性或属性

获取与 DOM 属性关联的运行时的值。
它返回与该元素的 DOM 特性或属性关联的数据。

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
