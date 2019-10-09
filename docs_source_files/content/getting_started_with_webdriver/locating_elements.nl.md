---
title: "Localisatie van elementen"
weight: 3
---

{{% notice info %}}
### Localisatie van elementen
<i class="fas fa-language"></i> Page being translated from 
English to Dutch. Do you speak Dutch? Help us to translate
it by sending us pull requests!
{{% /notice %}}

### Localisatie van elementen

Een van de meest fundamentele technieken die nodig zijn om WebDriver onder
de knie te krijgen, is het lokaliseren van elementen op een pagina.
WebDriver beschikt over een aantal ingebouwde selector types, een van hen
is het lokaliseren van een element op basis van zijn ID:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));  
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.find_element_by_id("cheese")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement element = driver.FindElement(By.Id("cheese"));  
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.find_element(id: "cheese")  
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = await driver.findElement(By.id('cheese'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< / code-panel >}}
{{< / code-tab >}}

In het bovenstaande voorbeeld kan je zien dat het lokaliseren van de elementen
gedaan word op een instantie van het `WebDriver` object. De methode
`findElement(By)` geeft een ander fundamenteel object type terug: `WebElement`.

* `WebDriver` stelt de browser voor
* `WebElement` stelt een bepaalde DOM node voor (een link, input veld etc)

Wanneer een web element gevonden is, kan je op basis van dat element een nieuwe
opzoeking starten. Deze zal dan binnen de scope van het gevonden element gedaan
worden:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
WebElement cheddar = cheese.findElement(By.id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheese = driver.find_element_by_id("cheese")
cheddar = cheese.find_elements_by_id("cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement cheese = driver.FindElement(By.Id("cheese"));
IWebElement cheddar = cheese.FindElement(By.Id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: "cheese")
cheddar = cheese.find_elements(id: "cheddar")
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = await driver.findElement(By.id('cheese'));
const cheddar = await cheese.findElement(By.id('cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

Dit is mogelijk omdat zowel _WebDriver_ als _WebElement_ de [_SearchContext_](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/SearchContext.html>SearchContext)
interface implementeren. Binnen WebDriver staat dit bekend als een _role-based interface_.


. In WebDriver, this is known as a _role-based interface_.
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
mucho_cheese = driver.find_elements(css: "#cheese #cheddar")
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheddar = await driver.findElement(By.css('#cheese #cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

### Locating multiple elements

It's possible that the document we are working with may turn out to have an
ordered list of the cheese we like the best:

```html
<ol id=cheese>
 <li id=cheddar>…
 <li id=brie>…
 <li id=rochefort>…
 <li id=camembert>…
</ul>
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
IReadOnlyList<IWebElement> muchoCheese = driver.FindElements(By.CssSelector(“#cheese li”));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
mucho_cheese = driver.find_elements(css: "#cheese li")
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const muchoCheese = await driver.findElements(By.css('#cheese li'));
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
| partial link text | Locates anchor elements whose visible text matches the search value |
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

Selection strategies based on link text and partial link text have
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
