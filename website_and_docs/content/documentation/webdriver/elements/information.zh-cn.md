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
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L20-L25" >}}
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
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L16-L17">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
//navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns true if element is displayed else returns false
 val flag = driver.findElement(By.name("email_input")).isDisplayed()
{{< /tab >}}
{{< /tabpane >}}


## 是否启用

此方法用于检查所连接的元素在网页上是启用还是禁用状态。
返回一个布尔值，如果在当前浏览上下文中是 **启用** 状态，则返回 **true**，否则返回 **false**。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L27-L30" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    # Navigate to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Returns true if element is enabled else returns false
value = driver.find_element(By.NAME, 'button_input').is_enabled()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

// Store the WebElement
IWebElement element = driver.FindElement(By.Name("button_input"));

// Prints true if element is enabled else returns false
System.Console.WriteLine(element.Enabled);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Navigate to url
driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Returns true if element is enabled else returns false
ele = driver.find_element(name: 'button_input').enabled?
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L23-L24">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
 //navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns true if element is enabled else returns false
 val attr = driver.findElement(By.name("button_input")).isEnabled()
  {{< /tab >}}
{{< /tabpane >}}

## 是否被选定

此方法确认相关的元素是否 _已选定_，常用于复选框、单选框、输入框和选择元素中。

该方法返回一个布尔值，如果在当前浏览上下文中 **选择了** 引用的元素，则返回 **True**，否则返回 **False**。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L32-L35" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    # Navigate to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Returns true if element is checked else returns false
value = driver.find_element(By.NAME, "checkbox_input").is_selected()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

// Returns true if element ins checked else returns false
bool value = driver.FindElement(By.Name("checkbox_input")).Selected;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Navigate to url
driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Returns true if element is checked else returns false
ele = driver.find_element(name: "checkbox_input").selected?
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L30-L31">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
 //navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns true if element is checked else returns false
 val attr =  driver.findElement(By.name("checkbox_input")).isSelected()
  {{< /tab >}}
{{< /tabpane >}}

## 获取元素标签名

此方法用于获取在当前浏览上下文中具有焦点的被引用元素的[TagName](https://www.w3.org/TR/webdriver/#dfn-get-element-tag-name)。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L37-L40" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    # Navigate to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Returns TagName of the element
attr = driver.find_element(By.NAME, "email_input").tag_name
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

// Returns TagName of the element
string attr = driver.FindElement(By.Name("email_input")).TagName;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Navigate to url
driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Returns TagName of the element
attr = driver.find_element(name: "email_input").tag_name
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L37-L38">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
 //navigates to url
 driver.get("https://www.selenium.dev/selenium/web/inputs.html")

 //returns TagName of the element
 val attr =  driver.findElement(By.name("email_input")).getTagName()
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
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L42-L46" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    # Navigate to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    # Returns height, width, x and y coordinates referenced element
res = driver.find_element(By.NAME, "range_input").rect
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");

var res = driver.FindElement(By.Name("range_input"));
// Return x and y coordinates referenced element
System.Console.WriteLine(res.Location);
// Returns height, width
System.Console.WriteLine(res.Size);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Navigate to url
driver.get 'https://www.selenium.dev/selenium/web/inputs.html'

    # Returns height, width, x and y coordinates referenced element
res = driver.find_element(name: "range_input").rect
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L45">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
// Navigate to url
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

// Returns height, width, x and y coordinates referenced element
val res = driver.findElement(By.name("range_input")).rect

// Rectangle class provides getX,getY, getWidth, getHeight methods
println(res.getX())
  {{< /tab >}}
{{< /tabpane >}}

## 获取元素CSS值

获取当前浏览上下文中元素的特定计算样式属性的值。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L49-L51" >}}
{{< /tab >}}
  {{< tab header="Python" >}}

    # Navigate to Url
driver.get('https://www.selenium.dev/selenium/web/colorPage.html')

    # Retrieves the computed style property 'color' of linktext
cssValue = driver.find_element(By.ID, "namedColor").value_of_css_property('background-color')

  {{< /tab >}}
  {{< tab header="CSharp" >}}

// Navigate to Url
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/colorPage.html");

// Retrieves the computed style property 'color' of linktext
String cssValue = driver.FindElement(By.Id("namedColor")).GetCssValue("background-color");

  {{< /tab >}}
  {{< tab header="Ruby" >}}

    # Navigate to Url
driver.get 'https://www.selenium.dev/selenium/web/colorPage.html'

    # Retrieves the computed style property 'color' of linktext
cssValue = driver.find_element(:id, 'namedColor').css_value('background-color')

  {{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L76-L78">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

// Navigate to Url
driver.get("https://www.selenium.dev/selenium/web/colorPage.html")

// Retrieves the computed style property 'color' of linktext
val cssValue = driver.findElement(By.id("namedColor")).getCssValue("background-color")

  {{< /tab >}}
{{< /tabpane >}}


## 文本内容

获取特定元素渲染后的文本内容。


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L54-L57" >}}
{{< /tab >}}
  {{< tab header="Python" >}}
    # Navigate to url
driver.get("https://www.selenium.dev/selenium/web/linked_image.html")

    # Retrieves the text of the element
text = driver.find_element(By.ID, "justanotherlink").text
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Navigate to url
driver.Url="https://www.selenium.dev/selenium/web/linked_image.html";

// Retrieves the text of the element
String text = driver.FindElement(By.Id("justanotherlink")).Text;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Navigate to url
driver.get 'https://www.selenium.dev/selenium/web/linked_image.html'

    # Retrieves the text of the element
text = driver.find_element(:id, 'justanotherlink').text
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L84-L86">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
// Navigate to URL
driver.get("https://www.selenium.dev/selenium/web/linked_image.html")

// retrieves the text of the element
val text = driver.findElement(By.id("justanotherlink")).getText()
  {{< /tab >}}
{{< /tabpane >}}

## 获取特性或属性

获取与 DOM 属性关联的运行时的值。
它返回与该元素的 DOM 特性或属性关联的数据。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/elements/InformationTest.java#L60-L65" >}}
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
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/elements/information.spec.js#L55-L59">}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}
// Navigate to URL
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

//fetch the value property associated with the textbox
val attr = driver.findElement(By.name("email_input")).getAttribute("value")
  {{< /tab >}}
{{< /tabpane >}}
