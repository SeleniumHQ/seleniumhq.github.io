---
title: "Locating elements"
weight: 3
---

### Locating one element

One of the most fundamental techniques to learn when using WebDriver is
how to find elements on the page. WebDriver offers a number of built-in selector
types, amongst them finding an element by its ID attribute:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.find_element(By.ID, "cheese")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement element = driver.FindElement(By.Id("cheese"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: 'cheese')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = driver.findElement(By.id('cheese'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< / code-panel >}}
{{< / code-tab >}}

As seen in the example, locating elements in WebDriver is done on the
`WebDriver` instance object. The `findElement(By)` method returns
another fundamental object type, the `WebElement`.

* `WebDriver` represents the browser
* `WebElement` represents a particular DOM node
  (a control, e.g. a link or input field, etc.)

Once you have a reference to a web element that's been “found”,
you can narrow the scope of your search
by using the same call on that object instance:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
WebElement cheddar = cheese.findElement(By.id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheese = driver.find_element(By.ID, "cheese")
cheddar = cheese.find_elements_by_id("cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement cheese = driver.FindElement(By.Id("cheese"));
IWebElement cheddar = cheese.FindElement(By.Id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: 'cheese')
cheddar = cheese.find_element(id: 'cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = driver.findElement(By.id('cheese'));
const cheddar = cheese.findElement(By.id('cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

You can do this because both the _WebDriver_ and _WebElement_ types
implement the [_SearchContext_](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/SearchContext.html)
interface. In WebDriver, this is known as a _role-based interface_.
Role-based interfaces allow you to determine whether a particular
driver implementation supports a given feature. These interfaces are
clearly defined and try to adhere to having only a single role of
responsibility.  You can read more about WebDriver's design and what
roles are supported in which drivers in the [Some Other Section Which
Must Be Named](#).
<!-- TODO: A new section needs to be created for the above.-->

Consequently, the _By_ interface used above also supports a
number of additional locator strategies.  A nested lookup might not be
the most effective cheese location strategy since it requires two
separate commands to be issued to the browser; first searching the DOM
for an element with ID “cheese”, then a search for “cheddar” in a
narrowed context.

To improve the performance slightly, we should try to use a more
specific locator: WebDriver supports looking up elements
by CSS locators, allowing us to combine the two previous locators into
one search:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheddar = driver.find_element_by_css_selector("#cheese #cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.FindElement(By.CssSelector("#cheese #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.find_element(css: '#cheese #cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheddar = driver.findElement(By.css('#cheese #cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

### Locating multiple elements

It is possible that the document we are working with may turn out to have an
ordered list of the cheese we like the best:

```html
<ol id=cheese>
 <li id=cheddar>…
 <li id=brie>…
 <li id=rochefort>…
 <li id=camembert>…
</ol>
```

Since more cheese is undisputably better, and it would be cumbersome
to have to retrieve each of the items individually, a superior
technique for retrieving cheese is to make use of the pluralized
version `findElements(By)`. This method returns a collection of web
elements. If only one element is found, it will still return a
collection (of one element). If no element matches the locator, an
empty list will be returned.

{{< code-tab >}}
  {{< code-panel language="java" >}}
List<WebElement> muchoCheese = driver.findElements(By.cssSelector("#cheese li"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
mucho_cheese = driver.find_elements_by_css_selector("#cheese li")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IReadOnlyList<IWebElement> muchoCheese = driver.FindElements(By.CssSelector("#cheese li"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
mucho_cheese = driver.find_elements(css: '#cheese li')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const muchoCheese = driver.findElements(By.css('#cheese li'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val muchoCheese: List<WebElement>  = driver.findElements(By.cssSelector("#cheese li"))
  {{< / code-panel >}}
{{< / code-tab >}}

### Element selection strategies

There are eight different built-in element location strategies in WebDriver:

| Locator | Description |
| -------- | ---------- |
| class name | Locates elements whose class name contains the search value (compound class names are not permitted) |
| css selector | Locates elements matching a CSS selector |
| id | Locates elements whose ID attribute matches the search value |
| name | Locates elements whose NAME attribute matches the search value |
| link text | Locates anchor elements whose visible text matches the search value |
| partial link text | Locates anchor elements whose visible text contains the search value. If multiple elements are matching, only the first one will be selected. |
| tag name | Locates elements whose tag name matches the search value |
| xpath | Locates elements matching an XPath expression |

### Tips on using selectors

In general, if HTML IDs are available, unique, and consistently
predictable, they are the preferred method for locating an element on
a page. They tend to work very quickly, and forego much processing
that comes with complicated DOM traversals.

If unique IDs are unavailable, a well-written CSS selector is the
preferred method of locating an element. XPath works as well as CSS
selectors, but the syntax is complicated and frequently difficult to
debug. Though XPath selectors are very flexible, they are typically
not performance tested by browser vendors and tend to be quite slow.

Selection strategies based on _linkText_ and _partialLinkText_ have
drawbacks in that they only work on link elements. Additionally, they
call down to XPath selectors internally in WebDriver.

Tag name can be a dangerous way to locate elements. There are
frequently multiple elements of the same tag present on the page.
This is mostly useful when calling the _findElements(By)_ method which
returns a collection of elements.

The recommendation is to keep your locators as compact and
readable as possible. Asking WebDriver to traverse the DOM structure
is an expensive operation, and the more you can narrow the scope of
your search, the better.

## Relative Locators

**Selenium 4** brings Relative Locators which are previously
called as _Friendly Locators_. This functionality was
added to help you locate elements that are nearby other elements.
The Available Relative Locators are:

* *above*
* *below*
* *toLeftOf*
* *toRightOf*
* *near*

_findElement_ method now accepts a new method `withTagName()`
which returns a RelativeLocator.

**NOTE**: Java bindings now support `with(By)` instead of `withTagName()` allowing users to pick
locator of their choice like _By.id_, _By.cssSelector_  etc.
This feature landed in **Selenium4 - beta3**

### How does it work

Selenium uses the JavaScript function
[getBoundingClientRect()](https://developer.mozilla.org/en-US/docs/Web/API/Element/getBoundingClientRect)
to find the relative elements. This function returns
properties of an element such as
right, left, bottom, and top.

Let us consider the below example for understanding the relative locators.

![Relative Locators](/images/relative_locators.png?width=400px)

### above()

Returns the WebElement, which appears
above to the specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement passwordField= driver.findElement(By.id("password"));
WebElement emailAddressField = driver.findElement(with(By.tagName("input"))
                                                  .above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

passwordField = driver.find_element(By.ID, "password")
emailAddressField = driver.find_element(with_tag_name("input").above(passwordField))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement passwordField = driver.FindElement(By.Id("password"));
IWebElement emailAddressField = driver.FindElement(WithTagName("input")
                                                   .Above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
password_field= driver.find_element(:id, "password")
email_address_field = driver.find_element(relative: {tag_name: 'input', above:password_field})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let passwordField = driver.findElement(By.id('password'));
let emailAddressField = await driver.findElement(withTagName('input').above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val passwordField = driver.findElement(By.id("password"))
val emailAddressField = driver.findElement(with(By.tagName("input")).above(passwordField))
  {{< / code-panel >}}
{{< / code-tab >}}


### below()

Returns the WebElement, which appears
below to the specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement emailAddressField= driver.findElement(By.id("email"));
WebElement passwordField = driver.findElement(with(By.tagName("input"))
	                                          .below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

emailAddressField = driver.find_element(By.ID, "email")
passwordField = driver.find_element(with_tag_name("input").below(emailAddressField))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;  

IWebElement emailAddressField = driver.FindElement(By.Id("email"));
IWebElement passwordField = driver.FindElement(WithTagName("input")
                                               .Below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
email_address_field= driver.find_element(:id, "email")
password_field = driver.find_element(relative: {tag_name: 'input', below: email_address_field})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let emailAddressField = driver.findElement(By.id('email'));
let passwordField = await driver.findElement(withTagName('input').below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val emailAddressField = driver.findElement(By.id("email"))
val passwordField = driver.findElement(with(By.tagName("input")).below(emailAddressField))
  {{< / code-panel >}}
{{< / code-tab >}}


### toLeftOf()

Returns the WebElement, which appears
to left of specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement submitButton= driver.findElement(By.id("submit"));
WebElement cancelButton= driver.findElement(with(By.tagName("button"))
                                            .toLeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

submitButton = driver.find_element(By.ID, "submit")
cancelButton = driver.find_element(with_tag_name("button").
                                   to_left_of(submitButton))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement submitButton = driver.FindElement(By.Id("submit"));
IWebElement cancelButton = driver.FindElement(WithTagName("button")
                                              .LeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
submit_button= driver.find_element(:id, "submit")
cancel_button = driver.find_element(relative: {tag_name: 'button', left:submit_button})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let submitButton = driver.findElement(By.id("submit"));
let cancelButton = await driver.findElement(withTagName("button").toLeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val submitButton= driver.findElement(By.id("submit"))
val cancelButton= driver.findElement(with(By.tagName("button")).toLeftOf(submitButton))
  {{< / code-panel >}}
{{< / code-tab >}}


### toRightOf()

Returns the WebElement, which appears
to right of the specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement cancelButton= driver.findElement(By.id("cancel"));
WebElement submitButton= driver.findElement(with(By.tagName("button"))
                                            .toRightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

cancelButton = driver.find_element(By.ID, "cancel")
submitButton = driver.find_element(with_tag_name("button").
                                   to_right_of(cancelButton))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement cancelButton = driver.FindElement(By.Id("cancel"));
IWebElement submitButton = driver.FindElement(WithTagName("button")
                                              .RightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cancel_button = driver.find_element(:id, "cancel")
submit_button = driver.find_element(relative: {tag_name: 'button', right:cancel_button})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let cancelButton = driver.findElement(By.id('cancel'));
let submitButton = await driver.findElement(withTagName('button').toRightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cancelButton= driver.findElement(By.id("cancel"))
val submitButton= driver.findElement(with(By.tagName("button")).toRightOf(cancelButton))
  {{< / code-panel >}}
{{< / code-tab >}}

### near()

Returns the WebElement, which is
at most `50px` away from the specified element.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement emailAddressLabel= driver.findElement(By.id("lbl-email"));
WebElement emailAddressField = driver.findElement(with(By.tagName("input"))
                                                  .near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

emailAddressLabel = driver.find_element(By.ID, "lbl-email")
emailAddressField = driver.find_element(with_tag_name("input").
                                       near(emailAddressLabel))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement emailAddressLabel = driver.FindElement(By.Id("lbl-email"));
IWebElement emailAddressField = driver.FindElement(WithTagName("input")
                                                   .Near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
email_address_label = driver.find_element(:id, "lbl-email")
email_address_field = driver.find_element(relative: {tag_name: 'input', near: email_address_label})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let emailAddressLabel = driver.findElement(By.id("lbl-email"));
let emailAddressField = await driver.findElement(withTagName("input").near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val emailAddressLabel = driver.findElement(By.id("lbl-email"))
val emailAddressField = driver.findElement(with(By.tagName("input")).near(emailAddressLabel))
  {{< / code-panel >}}
{{< / code-tab >}}
