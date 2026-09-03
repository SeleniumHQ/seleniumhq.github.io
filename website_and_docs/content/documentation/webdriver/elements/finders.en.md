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

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
WebElement firstInput = driver.findElement(By.className("information"));
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L7-L8">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
var firstInput = driver.FindElement(By.ClassName("information"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L10-L11" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
const firstInput = await driver.findElement(By.className('information'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
val firstInput: WebElement = driver.findElement(By.className("information"))
  {{< /tab >}}
{{< /tabpane >}}


### Evaluating a subset of the DOM

Rather than finding a unique locator in the entire DOM, it is often useful to narrow the search to the scope
of another located element.

One solution is to locate an ancestor of the desired element, then call find element on that object:

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
WebElement form = driver.findElement(By.tagName("form"));
WebElement input = form.findElement(By.className("information"));
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L14-L16">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
IWebElement form = driver.FindElement(By.TagName("form"));
IWebElement input = form.FindElement(By.ClassName("information"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L17-L19" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
const form = await driver.findElement(By.tagName('form'));
const input = await form.findElement(By.className('information'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
val form = driver.findElement(By.tagName("form"))
val input = form.findElement(By.className("information"))
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

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
WebElement input = driver.findElement(By.cssSelector("form .information"));
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L22-L23">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
var input = driver.FindElement(By.CssSelector("form .information"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L25-L26" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
const input = await driver.findElement(By.css('form .information'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
val input = driver.findElement(By.cssSelector("form .information"))
  {{< /tab >}}
{{< /tabpane >}}


## All matching elements

There are several use cases for needing to get references to all elements that match a locator, rather
than just the first one. The plural find elements methods return a collection of element references. 
If there are no matches, an empty list is returned. In this case, 
references to all input elements will be returned in a collection.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
List<WebElement> inputs = driver.findElements(By.tagName("input"));
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L29-L30">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
IReadOnlyList<IWebElement> inputs = driver.FindElements(By.TagName("input"));
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L32-L33" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
const inputs = await driver.findElements(By.tagName('input'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
val inputs: List<WebElement> = driver.findElements(By.tagName("input"))
  {{< /tab >}}
{{< /tabpane >}}

### Get element
Often you get a collection of elements but want to work with a specific element, which means you
need to iterate over the collection and identify the one you want.


{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
List<WebElement> elements = driver.findElements(By.tagName("p"));

for (WebElement element : elements) {
    System.out.println("Paragraph text:" + element.getText());
}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L51-L53">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using System.Collections.Generic;

namespace FindElementsExample {
 class FindElementsExample {
  public static void Main(string[] args) {
   IWebDriver driver = new FirefoxDriver();
   try {
    // Navigate to Url
    driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

    // Get all the elements available with tag name 'p'
    IList < IWebElement > elements = driver.FindElements(By.TagName("p"));
    foreach(IWebElement e in elements) {
     System.Console.WriteLine(e.Text);
    }

   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< /tab >}}
   {{< tab header="Ruby" text=true >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L40-L42" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');
(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    try {
        // Navigate to Url
        await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');

        // Get all the elements available with tag 'p'
        let elements = await driver.findElements(By.css('p'));
        for(let e of elements) {
            console.log(await e.getText());
        }
    }
    finally {
        await driver.quit();
    }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.firefox.FirefoxDriver

fun main() {
    val driver = FirefoxDriver()
    try {
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
        // Get all the elements available with tag name 'p'
        val elements = driver.findElements(By.tagName("p"))
        for (element in elements) {
            println("Paragraph text:" + element.text)
        }
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## Find Elements From Element

It is used to find the list of matching child WebElements within the context of parent element.
To achieve this, the parent WebElement is chained with 'findElements' to access child elements

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
  import org.openqa.selenium.By;
  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.WebElement;
  import org.openqa.selenium.chrome.ChromeDriver;
  import java.util.List;

  public class findElementsFromElement {
      public static void main(String[] args) {
          WebDriver driver = new ChromeDriver();
          try {
              driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

              // Get the form element
              WebElement element = driver.findElement(By.tagName("form"));

              // Get all input elements in the form
              List<WebElement> elements = element.findElements(By.tagName("input"));
              for (WebElement e : elements) {
                  System.out.println(e.getAttribute("value"));
              }
          } finally {
              driver.quit();
          }
      }
  }
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L61-L64">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System.Collections.Generic;

namespace FindElementsFromElement {
 class FindElementsFromElement {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");

    // Get the form element
    IWebElement element = driver.FindElement(By.TagName("form"));

    // Get all input elements in the form
    IList < IWebElement > elements = element.FindElements(By.TagName("input"));
    foreach(IWebElement e in elements) {
     System.Console.WriteLine(e.GetAttribute("value"));
    }
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< /tab >}}
   {{< tab header="Ruby" text=true >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L48-L51" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = new Builder()
          .forBrowser('chrome')
          .build();

      await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');

      // Get the form element
      let element = driver.findElement(By.css("form"));

      // Get all input elements in the form
      let elements = await element.findElements(By.css("input"));
      for(let e of elements) {
          console.log(await e.getAttribute("value"));
      }
  })();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  import org.openqa.selenium.By
  import org.openqa.selenium.chrome.ChromeDriver

  fun main() {
      val driver = ChromeDriver()
      try {
          driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")

          // Get the form element
          val element = driver.findElement(By.tagName("form"))

          // Get all input elements in the form
          val elements = element.findElements(By.tagName("input"))
          for (e in elements) {
              println(e.getAttribute("value"))
          }
      } finally {
          driver.quit()
      }
  }
  {{< /tab >}}
{{< /tabpane >}}

## Get Active Element

It is used to track (or) find DOM element which has the focus in the current browsing context.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
  {{< tab header="Java" >}}
  import org.openqa.selenium.*;
  import org.openqa.selenium.chrome.ChromeDriver;

  public class activeElementTest {
    public static void main(String[] args) {
      WebDriver driver = new ChromeDriver();
      try {
        driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
        driver.findElement(By.cssSelector("#fname")).sendKeys("webElement");

        // Get attribute of current active element
        String attr = driver.switchTo().activeElement().getAttribute("name");
        System.out.println(attr);
      } finally {
        driver.quit();
      }
    }
  }
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L72-L73">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    using OpenQA.Selenium;
    using OpenQA.Selenium.Chrome;

    namespace ActiveElement {
     class ActiveElement {
      public static void Main(string[] args) {
       IWebDriver driver = new ChromeDriver();
       try {
        // Navigate to Url
        driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/locators_tests/locators.html");
        driver.FindElement(By.CssSelector("#fname")).SendKeys("webElement");

        // Get attribute of current active element
        string attr = driver.SwitchTo().ActiveElement().GetAttribute("name");
        System.Console.WriteLine(attr);
       } finally {
        driver.Quit();
       }
      }
     }
    }
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
  {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L58-L60" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = await new Builder().forBrowser('chrome').build();
      await driver.get('https://www.selenium.dev/selenium/web/locators_tests/locators.html');
      await driver.findElement(By.css('#fname')).sendKeys("webElement");

      // Get attribute of current active element
      let attr = await driver.switchTo().activeElement().getAttribute("name");
      console.log(`${attr}`)
  })();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  import org.openqa.selenium.By
  import org.openqa.selenium.chrome.ChromeDriver

  fun main() {
      val driver = ChromeDriver()
      try {
          driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
          driver.findElement(By.cssSelector("#fname")).sendKeys("webElement")

          // Get attribute of current active element
          val attr = driver.switchTo().activeElement().getAttribute("name")
          print(attr)
      } finally {
          driver.quit()
      }
  }
  {{< /tab >}}
{{< /tabpane >}}


