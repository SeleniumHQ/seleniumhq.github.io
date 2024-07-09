---
title: "定位策略"
linkTitle: "定位器"
weight: 4
needsTranslation: true
aliases: [
"/zh-cn/documentation/webdriver/relative_locators/"
]
description: >
  在DOM中标识一个或多个特定元素的方法.
---

定位器是在页面上标识元素的一种方法。它是传送给
[查找元素]({{< ref "finders.md" >}}) 方法的参数。

查看 [鼓励测试练习]({{< ref "/documentation/test_practices/encouraged" >}}) 寻找
[定位器]({{< ref "/documentation/test_practices/encouraged/locators.md" >}})的小技巧， 包含在查找方法中，不同时间，不同原因下，单独声明的定位器的使用方法。



### 元素选择策略

在 WebDriver 中有 8 种不同的内置元素定位策略：

| 定位器 Locator | 描述 |
| -------- | ---------- |
| class name | 定位class属性与搜索值匹配的元素（不允许使用复合类名） |
| css selector | 定位 CSS 选择器匹配的元素 |
| id | 定位 id 属性与搜索值匹配的元素 |
| name | 定位 name 属性与搜索值匹配的元素 |
| link text | 定位link text可视文本与搜索值完全匹配的锚元素 |
| partial link text | 定位link text可视文本部分与搜索值部分匹配的锚点元素。如果匹配多个元素，则只选择第一个元素。 |
| tag name | 定位标签名称与搜索值匹配的元素 |
| xpath | 定位与 XPath 表达式匹配的元素 |

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

<form action="/action_page.php">
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
The HTML page web element can have attribute class. We can see an example in the
above shown HTML snippet. We can identify these elements using the class name locator
available in Selenium. 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    WebDriver driver = new ChromeDriver();
	driver.findElement(By.className("information"));
  {{< /tab >}}
  {{< tab header="Python" >}}
    driver = webdriver.Chrome()
	driver.find_element(By.CLASS_NAME, "information")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    var driver = new ChromeDriver();
	driver.FindElement(By.ClassName("information"));
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L7" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.className('information'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.className("information"))
  {{< /tab >}}
{{< /tabpane >}} 

## css selector
CSS is the language used to style HTML pages. We can use css selector locator strategy
to identify the element on the page. If the element has an id, we create the locator
as css = #id. Otherwise the format we follow is css =[attribute=value] .
Let us see an example from above HTML snippet. We will create locator for First Name 
textbox, using css. 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    WebDriver driver = new ChromeDriver();
	driver.findElement(By.cssSelector("#fname"));
  {{< /tab >}}
  {{< tab header="Python" >}}
    driver = webdriver.Chrome()
	driver.find_element(By.CSS_SELECTOR, "#fname")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    var driver = new ChromeDriver();
	driver.FindElement(By.CssSelector("#fname"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L11" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.css('#fname'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.css("#fname"))
  {{< /tab >}}
{{< /tabpane >}} 

## id
We can use the ID attribute available with element in a web page to locate it. 
Generally the ID property should be unique for a element on the web page. 
We will identify the Last Name field using it. 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    WebDriver driver = new ChromeDriver();
	driver.findElement(By.id("lname"));
  {{< /tab >}}
  {{< tab header="Python" >}}
    driver = webdriver.Chrome()
	driver.find_element(By.ID, "lname")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    var driver = new ChromeDriver();
	driver.FindElement(By.Id("lname"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L15" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.id('lname'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.id("lname"))
  {{< /tab >}}
{{< /tabpane >}} 


## name
We can use the NAME attribute available with element in a web page to locate it. 
Generally the NAME property should be unique for a element on the web page. 
We will identify the Newsletter checkbox using it. 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    WebDriver driver = new ChromeDriver();
	driver.findElement(By.name("newsletter"));
  {{< /tab >}}
  {{< tab header="Python" >}}
    driver = webdriver.Chrome()
	driver.find_element(By.NAME, "newsletter")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    var driver = new ChromeDriver();
	driver.FindElement(By.Name("newsletter"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L19" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.name('newsletter'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.name("newsletter"))
  {{< /tab >}}
{{< /tabpane >}} 

## link text 
If the element we want to locate is a link, we can use the link text locator
to identify it on the web page. The link text is the text displayed of the link. 
In the HTML snippet shared, we have a link available, lets see how will we locate it. 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    WebDriver driver = new ChromeDriver();
	driver.findElement(By.linkText("Selenium Official Page"));
  {{< /tab >}}
  {{< tab header="Python" >}}
    driver = webdriver.Chrome()
	driver.find_element(By.LINK_TEXT, "Selenium Official Page")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    var driver = new ChromeDriver();
	driver.FindElement(By.LinkText("Selenium Official Page"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L23" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.linkText('Selenium Official Page'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.linkText("Selenium Official Page"))
  {{< /tab >}}
{{< /tabpane >}} 

## partial link text 
If the element we want to locate is a link, we can use the partial link text locator
to identify it on the web page. The link text is the text displayed of the link. 
We can pass partial text as value.
In the HTML snippet shared, we have a link available, lets see how will we locate it. 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    WebDriver driver = new ChromeDriver();
	driver.findElement(By.partialLinkText("Official Page"));
  {{< /tab >}}
  {{< tab header="Python" >}}
    driver = webdriver.Chrome()
	driver.find_element(By.PARTIAL_LINK_TEXT, "Official Page")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    var driver = new ChromeDriver();
	driver.FindElement(By.PartialLinkText("Official Page"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L27" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.partialLinkText('Official Page'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.partialLinkText("Official Page"))
  {{< /tab >}}
{{< /tabpane >}} 

## tag name
We can use the HTML TAG itself as a locator to identify the web element on the page.
From the above HTML snippet shared, lets identify the link, using its html tag "a". 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    WebDriver driver = new ChromeDriver();
	driver.findElement(By.tagName("a"));
  {{< /tab >}}
  {{< tab header="Python" >}}
    driver = webdriver.Chrome()
	driver.find_element(By.TAG_NAME, "a")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    var driver = new ChromeDriver();
	driver.FindElement(By.TagName("a"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L31" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.tagName('a'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.tagName("a"))
  {{< /tab >}}
{{< /tabpane >}} 

## xpath 

A HTML document can be considered as a XML document, and then we can use xpath
which will be the path traversed to reach the element of interest to locate the element.
The XPath could be absolute xpath, which is created from the root of the document.
Example - /html/form/input[1]. This will return the male radio button.
Or the xpath could be relative. Example- //input[@name='fname']. This will return the
first name text box. Let us create locator for female radio button using xpath. 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    WebDriver driver = new ChromeDriver();
	driver.findElement(By.xpath("//input[@value='f']"));
  {{< /tab >}}
  {{< tab header="Python" >}}
    driver = webdriver.Chrome()
	driver.find_element(By.XPATH, "//input[@value='f']")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    var driver = new ChromeDriver();
	driver.FindElement(By.Xpath("//input[@value='f']"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L35" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
    let driver = await new Builder().forBrowser('chrome').build();
	const loc = await driver.findElement(By.xpath('//input[@value='f']'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    val driver = ChromeDriver()
	val loc: WebElement = driver.findElement(By.xpath('//input[@value='f']'))
  {{< /tab >}}
{{< /tabpane >}} 



## Relative Locators

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

{{< figure src="/images/documentation/webdriver/relative_locators.png" class="img-responsive text-center" alt="Relative Locators">}}

### Available relative locators

#### Above

If the email text field element is not easily identifiable for some reason, but the password text field element is,
we can locate the text field element using the fact that it is an "input" element "above" the password element. 

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("password"));
{{< /tab >}}
{{< tab header="Python" >}}
email_locator = locate_with(By.TAG_NAME, "input").above({By.ID: "password"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var emailLocator = RelativeBy.WithLocator(By.TagName("input")).Above(By.Id("password"));
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L40" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let emailLocator = locateWith(By.tagName('input')).above(By.id('password'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("password"))
{{< /tab >}}
{{< /tabpane >}}

#### Below

If the password text field element is not easily identifiable for some reason, but the email text field element is,
we can locate the text field element using the fact that it is an "input" element "below" the email element.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.id("email"));
{{< /tab >}}
{{< tab header="Python" >}}
password_locator = locate_with(By.TAG_NAME, "input").below({By.ID: "email"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var passwordLocator = RelativeBy.WithLocator(By.TagName("input")).Below(By.Id("email"));
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L44" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let passwordLocator = locateWith(By.tagName('input')).below(By.id('email'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.id("email"))
{{< /tab >}}
{{< /tabpane >}}

#### Left of

If the cancel button is not easily identifiable for some reason, but the submit button element is,
we can locate the cancel button element using the fact that it is a "button" element to the "left of" the submit element.
    
{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By cancelLocator = RelativeLocator.with(By.tagName("button")).toLeftOf(By.id("submit"));
{{< /tab >}}
{{< tab header="Python" >}}
cancel_locator = locate_with(By.TAG_NAME, "button").to_left_of({By.ID: "submit"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var cancelLocator = RelativeBy.WithLocator(By.tagName("button")).LeftOf(By.Id("submit"));
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L48" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let cancelLocator = locateWith(By.tagName('button')).toLeftOf(By.id('submit'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val cancelLocator = RelativeLocator.with(By.tagName("button")).toLeftOf(By.id("submit"))
{{< /tab >}}
{{< /tabpane >}}

#### Right of

If the submit button is not easily identifiable for some reason, but the cancel button element is,
we can locate the submit button element using the fact that it is a "button" element "to the right of" the cancel element.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By submitLocator = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("cancel"));
{{< /tab >}}
{{< tab header="Python" >}}
submit_locator = locate_with(By.TAG_NAME, "button").to_right_of({By.ID: "cancel"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var submitLocator = RelativeBy.WithLocator(By.tagName("button")).RightOf(By.Id("cancel"));
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L52" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let submitLocator = locateWith(By.tagName('button')).toRightOf(By.id('cancel'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val submitLocator = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("cancel"))
{{< /tab >}}
{{< /tabpane >}}

#### Near

If the relative positioning is not obvious, or it varies based on window size, you can use the near method to 
identify an element that is at most `50px` away from the provided locator.
One great use case for this is to work with a form element that doesn't have an easily constructed locator,
but its associated [input label element](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/label) does.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By emailLocator = RelativeLocator.with(By.tagName("input")).near(By.id("lbl-email"));
{{< /tab >}}
{{< tab header="Python" >}}
email_locator = locate_with(By.TAG_NAME, "input").near({By.ID: "lbl-email"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var emailLocator = RelativeBy.WithLocator(By.tagName("input")).Near(By.Id("lbl-email"));
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L56" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let emailLocator = locateWith(By.tagName('input')).near(By.id('lbl-email'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val emailLocator = RelativeLocator.with(By.tagName("input")).near(By.id("lbl-email"));
{{< /tab >}}
{{< /tabpane >}}

### Chaining relative locators

You can also chain locators if needed. Sometimes the element is most easily identified as being both above/below one element and right/left of another.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
By submitLocator = RelativeLocator.with(By.tagName("button")).below(By.id("email")).toRightOf(By.id("cancel"));
{{< /tab >}}
{{< tab header="Python" >}}
submit_locator = locate_with(By.TAG_NAME, "button").below({By.ID: "email"}).to_right_of({By.ID: "cancel"})
{{< /tab >}}
{{< tab header="CSharp" >}}
var submitLocator = RelativeBy.WithLocator(By.tagName("button")).Below(By.Id("email")).RightOf(By.Id("cancel"));
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/elements/locators_spec.rb#L60" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let submitLocator = locateWith(By.tagName('button')).below(By.id('email')).toRightOf(By.id('cancel'));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val submitLocator = RelativeLocator.with(By.tagName("button")).below(By.id("email")).toRightOf(By.id("cancel"))
{{< /tab >}}
{{< /tabpane >}}
