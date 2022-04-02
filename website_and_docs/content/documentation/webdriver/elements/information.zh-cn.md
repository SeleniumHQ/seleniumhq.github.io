---
title: "关于网络元素的信息"
linkTitle: "信息"
needsTranslation: true
weight: 4
description: >
  元素相关的知识.
---

There are a number of details you can query about a specific element.

## Is Displayed

This method is used to check if the connected Element is
displayed on a webpage. Returns a `Boolean` value,
True if the connected element is displayed in the current
browsing context else returns false.

This functionality is [mentioned in](https://w3c.github.io/webdriver/#element-displayedness), but not defined by
the w3c specification due to the
[impossibility of covering all potential conditions](https://www.youtube.com/watch?v=hTa1KI6fQpg).
As such, Selenium cannot expect drivers to implement
this functionality directly, and now relies on
executing a large JavaScript function directly.
This function makes many approximations about an element's
nature and relationship in the tree to return a value.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
// Navigate to the url
driver.get('https://www.google.com');

// Get boolean value for is element display
boolean isButtonVisible = driver.findElement(By.css("[name='login']")).isDisplayed();
{{< /tab >}}
{{< tab header="Python" >}}
# Help us with a PR for code sample
{{< /tab >}}
{{< tab header="CSharp" >}}
// Help us with a PR for code sample
{{< /tab >}}
{{< tab header="Ruby" >}}
# Help us with a PR for code sample
{{< /tab >}}
{{< tab header="JavaScript" >}}
// Navigate to url
await driver.get('https://www.google.com');

// Resolves Promise and returns boolean value
let result =  await driver.findElement(By.css("[name='btnK']")).isDisplayed();
{{< /tab >}}
{{< tab header="Kotlin" >}}
// Help us with a PR for code sample
{{< /tab >}}
{{< /tabpane >}}

{{< alert-code >}}
for isDisplayed() method
{{< /alert-code >}}


## Is Element Selected

此方法确定是否 _已选择_ 引用的元素.
此方法广泛用于复选框, 单选按钮, 输入元素和选项元素.

返回一个布尔值,
如果在当前浏览上下文中 **已选择** 引用的元素,
则返回 **True**, 否则返回 **False**.

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

## Get Element TagName

此方法用于获取在当前浏览上下文中
具有焦点的被引用元素的
[TagName](https://www.w3.org/TR/webdriver/#dfn-get-element-tag-name) .

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

## Get Element Rect

用于获取参考元素的尺寸和坐标.

提取的数据主体包含以下详细信息:
* 元素左上角的X轴位置
* 元素左上角的y轴位置
* 元素的高度
* 元素宽度

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

获取当前浏览上下文中元素的特定计算样式属性的值.

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
cssValue = driver.findElement(By.LINK_TEXT, "More information...").value_of_css_property('color')

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

## 获取元素文本

获取特定元素渲染后的文本.

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

## Attributes and Properties

### Attribute

### DOM Attribute

### DOM Property

