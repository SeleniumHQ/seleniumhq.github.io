---
title: "Finding web elements"
linkTitle: "Finders"
weight: 2
aliases: [
"/documentation/en/webdriver/locating_elements/",
"/documentation/webdriver/locating_elements/"
]
description: >
  Locating the elements based on the provided locator values.
---

One of the most fundamental aspects of using Selenium is obtaining element references to work with.
Selenium offers a number of built-in [locator strategies]({{< ref "locators.md" >}}) to uniquely identify an element.
There are many ways to use the locators in very advanced scenarios. For the purposes of this documentation,
use the [Selenium locator test page](https://www.selenium.dev/selenium/web/locators_tests/locators.html).

## First matching element 

Many locators will match multiple elements on the page. The singular find element method will return a reference to the
first element found within a given context.

### Evaluating entire DOM

When the find element method is called on the driver instance, it 
returns a reference to the first element in the DOM that matches with the provided locator. 
This value can be stored and used for future element actions. On the Selenium locator test page, there are
two elements with the class name `information`, so this method returns the first text input.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L22-L23">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L7-L8">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L15-L16">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L10-L11" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L9-L10">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L19-L20">}}
  {{< /tab >}}
{{< /tabpane >}}


### Evaluating a subset of the DOM

Rather than finding a unique locator in the entire DOM, it is often useful to narrow the search to the scope
of another located element.

One solution is to locate an ancestor of the desired element, then call find element on that object:

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L31-L33">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L14-L16">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L25-L27">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L17-L19" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L18-L20">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L27-L29">}}
  {{< /tab >}}
{{< /tabpane >}}

{{% pageinfo color="info" %}}
**Java and C#**<br>
`WebDriver`, `WebElement` and `ShadowRoot` classes all implement a `SearchContext` interface, which is
considered a _role-based interface_. Role-based interfaces allow you to determine whether a particular
driver implementation supports a given feature. These interfaces are clearly defined and try 
to adhere to having only a single role of responsibility.
{{% /pageinfo %}}

### Evaluating the Shadow DOM

The Shadow DOM is an encapsulated DOM tree hidden inside an element. 
With the release of v96 in Chromium Browsers, Selenium can now allow you to access this tree with 
easy-to-use shadow root methods. NOTE: These methods require Selenium 4.0 or greater.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
{{< tab header="Java" >}}
WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"));
SearchContext shadowRoot = shadowHost.getShadowRoot();
WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#shadow_content"));
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L39-L42">}}
{{< /tab >}}
{{< tab header="CSharp" >}}
var shadowHost = _driver.FindElement(By.CssSelector("#shadow_host"));
var shadowRoot = shadowHost.GetShadowRoot();
var shadowContent = shadowRoot.FindElement(By.CssSelector("#shadow_content"));
{{< /tab >}}
{{< tab header="Ruby" >}}
shadow_host = @driver.find_element(css: '#shadow_host')
shadow_root = shadow_host.shadow_root
shadow_content = shadow_root.find_element(css: '#shadow_content')
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Optimized locator

A nested lookup might not be the most effective location strategy since it requires two
separate commands to be issued to the browser.

To improve the performance slightly, we can use either CSS or XPath to find this element in a single command.
See the [Locator strategy suggestions]({{< ref "/documentation/test_practices/encouraged/locators" >}}) in our 
[Encouraged test practices]({{< ref "/documentation/test_practices/encouraged" >}}) section.

For this example, we'll use a CSS selector:

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L41-L42">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L22-L23">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L36-L37">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L25-L26" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L28-L29">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L36-L37">}}
  {{< /tab >}}
{{< /tabpane >}}


## All matching elements

There are several use cases for needing to get references to all elements that match a locator, rather
than just the first one. The plural find elements methods return a collection of element references. 
If there are no matches, an empty list is returned. In this case, 
references to all input elements will be returned in a collection.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L50-L51">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L29-L30">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L46-L47">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L32-L33" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L37-L38">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L44-L45">}}
  {{< /tab >}}
{{< /tabpane >}}

### Get element
Often you get a collection of elements but want to work with a specific element, which means you
need to iterate over the collection and identify the one you want.


{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L61-L64">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L51-L53">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L58-L62">}}
  {{< /tab >}}
   {{< tab header="Ruby" >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L40-L42" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L47-L50">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L53-L56">}}
  {{< /tab >}}
{{< /tabpane >}}

## Find Elements From Element

It is used to find the list of matching child WebElements within the context of parent element.
To achieve this, the parent WebElement is chained with 'findElements' to access child elements

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L74-L78">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L61-L64">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L73-L78">}}
  {{< /tab >}}
   {{< tab header="Ruby" >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L48-L51" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L59-L63">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L64-L68">}}
  {{< /tab >}}
{{< /tabpane >}}

## Get Active Element

It is used to track (or) find DOM element which has the focus in the current browsing context.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L88-L89">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L72-L73">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L89-L90">}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
  {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L58-L60" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L72-L73">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L76-L77">}}
  {{< /tab >}}
{{< /tabpane >}}


