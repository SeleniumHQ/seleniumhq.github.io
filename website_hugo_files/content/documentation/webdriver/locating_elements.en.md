---
title: "Locating elements"
linkTitle: "Locating elements"
weight: 3
---

### Locating one element

One of the most fundamental techniques to learn when using WebDriver is
how to find elements on the page. WebDriver offers a number of built-in selector
types, amongst them finding an element by its ID attribute:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.find_element(By.ID, "cheese")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebElement element = driver.FindElement(By.Id("cheese"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
cheese = driver.find_element(id: 'cheese')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheese = driver.findElement(By.id('cheese'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< /tab >}}
{{< /tabpane >}}

As seen in the example, locating elements in WebDriver is done on the
`WebDriver` instance object. The `findElement(By)` method returns
another fundamental object type, the `WebElement`.

* `WebDriver` represents the browser
* `WebElement` represents a particular DOM node
  (a control, e.g. a link or input field, etc.)

Once you have a reference to a web element that's been “found”,
you can narrow the scope of your search
by using the same call on that object instance:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
WebElement cheddar = cheese.findElement(By.id("cheddar"));
  {{< /tab >}}
  {{< tab header="Python" >}}
cheese = driver.find_element(By.ID, "cheese")
cheddar = cheese.find_elements_by_id("cheddar")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebElement cheese = driver.FindElement(By.Id("cheese"));
IWebElement cheddar = cheese.FindElement(By.Id("cheddar"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
cheese = driver.find_element(id: 'cheese')
cheddar = cheese.find_element(id: 'cheddar')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheese = driver.findElement(By.id('cheese'));
const cheddar = cheese.findElement(By.id('cheddar'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"));
  {{< /tab >}}
  {{< tab header="Python" >}}
cheddar = driver.find_element_by_css_selector("#cheese #cheddar")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.FindElement(By.CssSelector("#cheese #cheddar"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
driver.find_element(css: '#cheese #cheddar')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheddar = driver.findElement(By.css('#cheese #cheddar'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
List<WebElement> muchoCheese = driver.findElements(By.cssSelector("#cheese li"));
  {{< /tab >}}
  {{< tab header="Python" >}}
mucho_cheese = driver.find_elements_by_css_selector("#cheese li")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IReadOnlyList<IWebElement> muchoCheese = driver.FindElements(By.CssSelector("#cheese li"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
mucho_cheese = driver.find_elements(css: '#cheese li')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const muchoCheese = driver.findElements(By.css('#cheese li'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val muchoCheese: List<WebElement>  = driver.findElements(By.cssSelector("#cheese li"))
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement passwordField= driver.findElement(By.id("password"));
WebElement emailAddressField = driver.findElement(with(By.tagName("input"))
                                                  .above(passwordField));
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

passwordField = driver.find_element(By.ID, "password")
emailAddressField = driver.find_element(with_tag_name("input").above(passwordField))
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement passwordField = driver.FindElement(By.Id("password"));
IWebElement emailAddressField = driver.FindElement(WithTagName("input").Above(passwordField));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
password_field= driver.find_element(:id, "password")
email_address_field = driver.find_element(relative: {tag_name: 'input', above:password_field})
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
let passwordField = driver.findElement(By.id('password'));
let emailAddressField = await driver.findElement(withTagName('input').above(passwordField));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val passwordField = driver.findElement(By.id("password"))
val emailAddressField = driver.findElement(with(By.tagName("input")).above(passwordField))
  {{< /tab >}}
{{< /tabpane >}}


### below()

Returns the WebElement, which appears
below to the specified element

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement emailAddressField= driver.findElement(By.id("email"));
WebElement passwordField = driver.findElement(with(By.tagName("input"))
	                                          .below(emailAddressField));
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

emailAddressField = driver.find_element(By.ID, "email")
passwordField = driver.find_element(with_tag_name("input").below(emailAddressField))
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement emailAddressField = driver.FindElement(By.Id("email"));
IWebElement passwordField = driver.FindElement(WithTagName("input").Below(emailAddressField));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
email_address_field= driver.find_element(:id, "email")
password_field = driver.find_element(relative: {tag_name: 'input', below: email_address_field})
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
let emailAddressField = driver.findElement(By.id('email'));
let passwordField = await driver.findElement(withTagName('input').below(emailAddressField));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val emailAddressField = driver.findElement(By.id("email"))
val passwordField = driver.findElement(with(By.tagName("input")).below(emailAddressField))
  {{< /tab >}}
{{< /tabpane >}}


### toLeftOf()

Returns the WebElement, which appears
to left of specified element

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement submitButton= driver.findElement(By.id("submit"));
WebElement cancelButton= driver.findElement(with(By.tagName("button"))
                                            .toLeftOf(submitButton));
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

submitButton = driver.find_element(By.ID, "submit")
cancelButton = driver.find_element(with_tag_name("button").
                                   to_left_of(submitButton))
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement submitButton = driver.FindElement(By.Id("submit"));
IWebElement cancelButton = driver.FindElement(WithTagName("button").LeftOf(submitButton));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
submit_button= driver.find_element(:id, "submit")
cancel_button = driver.find_element(relative: {tag_name: 'button', left:submit_button})
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
let submitButton = driver.findElement(By.id("submit"));
let cancelButton = await driver.findElement(withTagName("button").toLeftOf(submitButton));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val submitButton= driver.findElement(By.id("submit"))
val cancelButton= driver.findElement(with(By.tagName("button")).toLeftOf(submitButton))
  {{< /tab >}}
{{< /tabpane >}}


### toRightOf()

Returns the WebElement, which appears
to right of the specified element

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement cancelButton= driver.findElement(By.id("cancel"));
WebElement submitButton= driver.findElement(with(By.tagName("button")).toRightOf(cancelButton));
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

cancelButton = driver.find_element(By.ID, "cancel")
submitButton = driver.find_element(with_tag_name("button").
                                   to_right_of(cancelButton))
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement cancelButton = driver.FindElement(By.Id("cancel"));
IWebElement submitButton = driver.FindElement(WithTagName("button").RightOf(cancelButton));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
cancel_button = driver.find_element(:id, "cancel")
submit_button = driver.find_element(relative: {tag_name: 'button', right:cancel_button})
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
let cancelButton = driver.findElement(By.id('cancel'));
let submitButton = await driver.findElement(withTagName('button').toRightOf(cancelButton));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val cancelButton= driver.findElement(By.id("cancel"))
val submitButton= driver.findElement(with(By.tagName("button")).toRightOf(cancelButton))
  {{< /tab >}}
{{< /tabpane >}}

### near()

Returns the WebElement, which is
at most `50px` away from the specified element.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement emailAddressLabel= driver.findElement(By.id("lbl-email"));
WebElement emailAddressField = driver.findElement(with(By.tagName("input")).near(emailAddressLabel));
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

emailAddressLabel = driver.find_element(By.ID, "lbl-email")
emailAddressField = driver.find_element(with_tag_name("input").
                                       near(emailAddressLabel))
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement emailAddressLabel = driver.FindElement(By.Id("lbl-email"));
IWebElement emailAddressField = driver.FindElement(WithTagName("input").Near(emailAddressLabel));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
email_address_label = driver.find_element(:id, "lbl-email")
email_address_field = driver.find_element(relative: {tag_name: 'input', near: email_address_label})
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
let emailAddressLabel = driver.findElement(By.id("lbl-email"));
let emailAddressField = await driver.findElement(withTagName("input").near(emailAddressLabel));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val emailAddressLabel = driver.findElement(By.id("lbl-email"))
val emailAddressField = driver.findElement(with(By.tagName("input")).near(emailAddressLabel))
  {{< /tab >}}
{{< /tabpane >}}
