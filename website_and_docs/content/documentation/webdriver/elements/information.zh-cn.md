---
title: "关于网络元素的信息"
linkTitle: "信息"
weight: 4
description: >
  元素相关的知识.
---

您可以查询有关特定元素的许多详细信息。

## 是否显示

此方法用于检查连接的元素是否正确显示在网页上. 返回一个 `Boolean` 值，
如果连接的元素显示在当前的浏览器上下文中，则为True，否则返回false。

此功能[于W3C规范中提及](https://w3c.github.io/webdriver/#element-displayedness)，
但由于[无法覆盖所有潜在条件](https://www.youtube.com/watch?v=LAD_XPGP_kk)而无法定义。
因此，Selenium不能期望驱动程序直接实现这种功能，现在依赖于直接执行大量JavaScript函数。
这个函数对一个元素的性质和在树中的关系做了许多近似的判断，以返回一个值。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
// Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html");

// Get boolean value for is element display
boolean isEmailVisible = driver.findElement(By.name("email_input")).isDisplayed();
{{< /tab >}}
{{< tab header="Python" >}}
# Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

# Get boolean value for is element display
is_email_visible = driver.find_element(By.NAME, "email_input").is_displayed()
{{< /tab >}}
{{< tab header="CSharp" >}}
//Navigate to the url
driver.Url = "https://www.selenium.dev/selenium/web/inputs.html";

//Get boolean value for is element display
Boolean is_email_visible = driver.FindElement(By.Name("email_input")).Displayed;
{{< /tab >}}
{{< tab header="Ruby" >}}
# Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html");

#fetch display status
val = driver.find_element(name: 'email_input').displayed?
{{< /tab >}}
{{< tab header="JavaScript" >}}
// Navigate to url
await driver.get("https://www.selenium.dev/selenium/web/inputs.html");

// Resolves Promise and returns boolean value
let result =  await driver.findElement(By.name("email_input")).isDisplayed();
{{< /tab >}}
{{< tab header="Kotlin" >}}
//navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns true if element is displayed else returns false
 val flag = driver.findElement(By.name("email_input")).isDisplayed()
{{< /tab >}}
{{< /tabpane >}}

{{< alert-code >}}
for element displayedness
{{< /alert-code >}}

## 是否启用

此方法用于检查所连接的元素在网页上是启用还是禁用状态。
返回一个布尔值，如果在当前浏览上下文中是 **启用** 状态，则返回 **true**，否则返回 **false**。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
  //navigates to url
  driver.get("https://www.google.com/");

  //returns true if element is enabled else returns false
  boolean value = driver.findElement(By.name("btnK")).isEnabled();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # Navigate to url
driver.get("http://www.google.com")

    # Returns true if element is enabled else returns false
value = driver.find_element(By.NAME, 'btnK').is_enabled()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://google.com");

// Store the WebElement
IWebElement element = driver.FindElement(By.Name("btnK"));

// Prints true if element is enabled else returns false
System.Console.WriteLine(element.Enabled);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Navigate to url
driver.get 'http://www.google.com/'

    # Returns true if element is enabled else returns false
ele = driver.find_element(name: 'btnK').enabled?
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Navigate to url
await driver.get('https://www.google.com');

// Resolves Promise and returns boolean value
let element =  await driver.findElement(By.name("btnK")).isEnabled();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
 //navigates to url
 driver.get("https://www.google.com/")

 //returns true if element is enabled else returns false
 val attr = driver.findElement(By.name("btnK")).isEnabled()
  {{< /tab >}}
{{< /tabpane >}}

## 是否被选定

此方法确认相关的元素是否 _已选定_，常用于复选框、单选框、输入框和选择元素中。

该方法返回一个布尔值，如果在当前浏览上下文中 **选择了** 引用的元素，则返回 **True**，否则返回 **False**。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
//navigates to url
driver.get("https://the-internet.herokuapp.com/checkboxes");

//returns true if element is checked else returns false
boolean value = driver.findElement(By.cssSelector("input[type='checkbox']:first-of-type")).isSelected();
{{< /tab >}}
{{< tab header="Python" >}}
# Navigate to url
driver.get("https://the-internet.herokuapp.com/checkboxes")

# Returns true if element is checked else returns false
value = driver.find_element(By.CSS_SELECTOR, "input[type='checkbox']:first-of-type").is_selected()
{{< /tab >}}
{{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://the-internet.herokuapp.com/checkboxes");

// Returns true if element ins checked else returns false
bool value = driver.FindElement(By.CssSelector("input[type='checkbox']:last-of-type")).Selected;
{{< /tab >}}
{{< tab header="Ruby" >}}
# Navigate to url
driver.get 'https://the-internet.herokuapp.com/checkboxes'

# Returns true if element is checked else returns false
ele = driver.find_element(css: "input[type='checkbox']:last-of-type").selected?
{{< /tab >}}
{{< tab header="JavaScript" >}}
// Navigate to url
await driver.get('https://the-internet.herokuapp.com/checkboxes');

// Returns true if element ins checked else returns false
let res = await driver.findElement(By.css("input[type='checkbox']:last-of-type")).isSelected();
{{< /tab >}}
{{< tab header="Kotlin" >}}
//navigates to url
driver.get("https://the-internet.herokuapp.com/checkboxes")

//returns true if element is checked else returns false
val attr =  driver.findElement(By.cssSelector("input[type='checkbox']:first-of-type")).isSelected()
{{< /tab >}}
{{< /tabpane >}}

## 获取元素标签名

此方法用于获取在当前浏览上下文中具有焦点的被引用元素的[TagName](https://www.w3.org/TR/webdriver/#dfn-get-element-tag-name)。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
//navigates to url
driver.get("https://www.example.com");

//returns TagName of the element
String value = driver.findElement(By.cssSelector("h1")).getTagName();
{{< /tab >}}
{{< tab header="Python" >}}
# Navigate to url
driver.get("https://www.example.com")

# Returns TagName of the element
attr = driver.find_element(By.CSS_SELECTOR, "h1").tag_name
{{< /tab >}}
{{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.example.com");

// Returns TagName of the element
string attr = driver.FindElement(By.CssSelector("h1")).TagName;
{{< /tab >}}
{{< tab header="Ruby" >}}
# Navigate to url
driver.get 'https://www.example.com'

# Returns TagName of the element
attr = driver.find_element(css: "h1").tag_name
{{< /tab >}}
{{< tab header="JavaScript" >}}
// Navigate to URL
await driver.get('https://www.example.com');

// Returns TagName of the element
let value = await driver.findElement(By.css('h1')).getTagName();
{{< /tab >}}
{{< tab header="Kotlin" >}}
//navigates to url
driver.get("https://www.example.com")

//returns TagName of the element
val attr =  driver.findElement(By.cssSelector("h1")).getTagName()
{{< /tab >}}
{{< /tabpane >}}

## 位置和大小

用于获取参照元素的尺寸和坐标。

提取的数据主体包含以下详细信息：
* 元素左上角的X轴位置
* 元素左上角的y轴位置
* 元素的高度
* 元素的宽度

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
// Navigate to url
driver.get("https://www.example.com");

// Returns height, width, x and y coordinates referenced element
Rectangle res =  driver.findElement(By.cssSelector("h1")).getRect();

// Rectangle class provides getX,getY, getWidth, getHeight methods
System.out.println(res.getX());
{{< /tab >}}
{{< tab header="Python" >}}
# Navigate to url
driver.get("https://www.example.com")

# Returns height, width, x and y coordinates referenced element
res = driver.find_element(By.CSS_SELECTOR, "h1").rect
{{< /tab >}}
{{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://example.com");

var res = driver.FindElement(By.CssSelector("h1"));
// Return x and y coordinates referenced element
System.Console.WriteLine(res.Location);
// Returns height, width
System.Console.WriteLine(res.Size);
{{< /tab >}}
{{< tab header="Ruby" >}}
# Navigate to url
driver.get 'https://www.example.com'

# Returns height, width, x and y coordinates referenced element
res = driver.find_element(css: "h1").rect
{{< /tab >}}
{{< tab header="JavaScript" >}}
// Navigate to url
await driver.get('https://www.example.com');

// Returns height, width, x and y coordinates referenced element
let element =  await driver.findElement(By.css("h1")).getRect();
{{< /tab >}}
{{< tab header="Kotlin" >}}
// Navigate to url
driver.get("https://www.example.com")

// Returns height, width, x and y coordinates referenced element
val res = driver.findElement(By.cssSelector("h1")).rect

// Rectangle class provides getX,getY, getWidth, getHeight methods
println(res.getX())
{{< /tab >}}
{{< /tabpane >}}

## 获取元素CSS值

获取当前浏览上下文中元素的特定计算样式属性的值。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}

// Navigate to Url
driver.get("https://www.example.com");

// Retrieves the computed style property 'color' of linktext
String cssValue = driver.findElement(By.linkText("More information...")).getCssValue("color");

{{< /tab >}}
{{< tab header="Python" >}}

# Navigate to Url
driver.get('https://www.example.com')

# Retrieves the computed style property 'color' of linktext
cssValue = driver.find_element(By.LINK_TEXT, "More information...").value_of_css_property('color')

{{< /tab >}}
{{< tab header="CSharp" >}}

// Navigate to Url
driver.Navigate().GoToUrl("https://www.example.com");

// Retrieves the computed style property 'color' of linktext
String cssValue = driver.FindElement(By.LinkText("More information...")).GetCssValue("color");

{{< /tab >}}
{{< tab header="Ruby" >}}

# Navigate to Url
driver.get 'https://www.example.com'

# Retrieves the computed style property 'color' of linktext
cssValue = driver.find_element(:link_text, 'More information...').css_value('color')

{{< /tab >}}
{{< tab header="JavaScript" >}}
// Navigate to Url
await driver.get('https://www.example.com');

// Retrieves the computed style property 'color' of linktext
let cssValue = await driver.findElement(By.linkText("More information...")).getCssValue('color');
{{< /tab >}}
{{< tab header="Kotlin" >}}

// Navigate to Url
driver.get("https://www.example.com")

// Retrieves the computed style property 'color' of linktext
val cssValue = driver.findElement(By.linkText("More information...")).getCssValue("color")

{{< /tab >}}
{{< /tabpane >}}

## 文本内容

获取特定元素渲染后的文本内容。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
// Navigate to url
driver.get("https://example.com");

// Retrieves the text of the element
String text = driver.findElement(By.cssSelector("h1")).getText();
{{< /tab >}}
{{< tab header="Python" >}}
# Navigate to url
driver.get("https://www.example.com")

# Retrieves the text of the element
text = driver.find_element(By.CSS_SELECTOR, "h1").text
{{< /tab >}}
{{< tab header="CSharp" >}}
// Navigate to url
driver.Url="https://example.com";

// Retrieves the text of the element
String text = driver.FindElement(By.CssSelector("h1")).Text;
{{< /tab >}}
{{< tab header="Ruby" >}}
# Navigate to url
driver.get 'https://www.example.com'

# Retrieves the text of the element
text = driver.find_element(:css, 'h1').text
{{< /tab >}}
{{< tab header="JavaScript" >}}
// Navigate to URL
await driver.get('http://www.example.com');

// retrieves the text of the element
let text = await driver.findElement(By.css('h1')).getText();
{{< /tab >}}
{{< tab header="Kotlin" >}}
// Navigate to URL
driver.get("https://www.example.com")

// retrieves the text of the element
val text = driver.findElement(By.cssSelector("h1")).getText()
{{< /tab >}}
{{< /tabpane >}}

## 获取特性或属性

获取与 DOM 属性关联的运行时的值。
它返回与该元素的 DOM 特性或属性关联的数据。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html");

//identify the email text box
WebElement emailTxt = driver.findElement(By.name(("email_input")));

//fetch the value property associated with the textbox
String valueInfo = eleSelLink.getAttribute("value");
  {{< /tab >}}
  {{< tab header="Python" >}}
# Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

# Identify the email text box
email_txt = driver.find_element(By.NAME, "email_input")

# Fetch the value property associated with the textbox
value_info = email_txt.get_attribute("value")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
 //Navigate to the url
driver.Url="https://www.selenium.dev/selenium/web/inputs.html";

//identify the email text box
IWebElement emailTxt = driver.FindElement(By.Name(("email_input")));

//fetch the value property associated with the textbox
String valueInfo = eleSelLink.GetAttribute("value");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Navigate to the url
driver.get("https://www.selenium.dev/selenium/web/inputs.html");

#identify the email text box
email_element=driver.find_element(name: 'email_input')

#fetch the value property associated with the textbox
emailVal = email_element.attribute("value");
  {{< /tab >}}
    {{< tab header="JavaScript" >}}
// Navigate to the Url
await driver.get("https://www.selenium.dev/selenium/web/inputs.html");

// identify the email text box
const emailElement = await driver.findElements(By.xpath('//input[@name="email_input"]'));

//fetch the attribute "name" associated with the textbox
const nameAttribute = await emailElement.getAttribute("name");
    {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Navigate to URL
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

//fetch the value property associated with the textbox
val attr = driver.findElement(By.name("email_input")).getAttribute("value")
  {{< /tab >}}
{{< /tabpane >}}
