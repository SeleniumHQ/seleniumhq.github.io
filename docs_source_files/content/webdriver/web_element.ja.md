---
title: "Web要素"
weight: 9
---

Web要素はDOM要素を表します。
Web要素は、WebDriverインスタンスを使用してドキュメントルートから検索するか、別のWeb要素の下で検索することで見つけることができます。

WebDriver APIは、ID、名前、クラス、XPath、CSSセレクター、リンクテキストなどのさまざまなプロパティに基づいたWeb要素を見つけるための組み込みメソッドを提供します。

## 要素の検索

要素を検索するために使用され、最初の一致する単一のWeb要素の参照を返します。
これは、将来の要素アクションに使用できます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new FirefoxDriver();

driver.get("http://www.google.com");

// Get search box element from webElement 'q' using Find Element
WebElement searchBox = driver.findElement(By.name("q"));

searchBox.sendKeys("webdriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Firefox()

driver.get("http://www.google.com")

# Get search box element from webElement 'q' using Find Element
search_box = driver.find_element(By.NAME, "q")

search_box.send_keys("webdriver")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebDriver driver = new FirefoxDriver();

driver.Url = "http://www.google.com";

// Get search box element from webElement 'q' using Find Element
IWebElement searchbox = driver.FindElement(By.Name("q"));

searchbox.SendKeys("webdriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  # Navigate to URL
  driver.get 'https://google.com'

  # Get search box element from webElement 'q' using Find Element
  search_bar = driver.find_element(name: 'q')

  # Perform action using WebElement
  search_bar.send_keys 'Webdriver'
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let {Builder, By} = require('selenium-webdriver');
driver = new Builder().forBrowser('firefox').build();

(async function test(){

//Navigate to url
await driver.get('http://www.google.com');

// Get search box element from webElement 'q' using Find Element
let searchBar = driver.findElement(By.name('q'));

//Perform action using WebElement
await searchBar.sendKeys('Webdriver');

})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val driver = FirefoxDriver()

driver.get("http://www.google.com")

// Get search box element from webElement 'q' using Find Element
val searchBox = driver.findElement(By.name("q"))

searchBox.sendKeys("webdriver")
  {{< / code-panel >}}
{{< / code-tab >}}

## 複数の要素の検索

'要素の検索'に似ていますが、一致するWeb要素のリストを返します。
リストから特定のWeb要素を使用するには、要素のリストをループして、選択した要素に対してアクションを実行する必要があります。

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

public class findElementsExample {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get("https://example.com");
            // Get all the elements available with tag name 'p'
            List<WebElement> elements = driver.findElements(By.tagName("p"));
            for (WebElement element : elements) {
                System.out.println("Paragraph text:" + element.getText());
            }
        } finally {
            driver.quit();
        }
    }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Firefox()

# Navigate to Url
driver.get("https://www.example.com")

# Get all the elements available with tag name 'p'
elements = driver.find_elements(By.TAG_NAME, 'p')

for e in elements:
    print(e.text)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using System.Collections.Generic;

namespace FindElementsExample {
 class FindElementsExample {
  public static void Main(string[] args) {
   IWebDriver driver = new FirefoxDriver();
   try {
    // Navigate to Url
    driver.Navigate().GoToUrl("https://example.com");

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
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  # Navigate to URL
  driver.get 'https://www.example.com'

  # Get all the elements available with tag name 'p'
  elements = driver.find_elements(:tag_name,'p')

  elements.each { |e|
    puts e.text
  }
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, By} = require('selenium-webdriver');
(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    try {
        // Navigate to Url
        await driver.get('https://www.example.com');

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
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.firefox.FirefoxDriver

fun main() {
    val driver = FirefoxDriver()
    try {
        driver.get("https://example.com")
        // Get all the elements available with tag name 'p'
        val elements = driver.findElements(By.tagName("p"))
        for (element in elements) {
            println("Paragraph text:" + element.text)
        }
    } finally {
        driver.quit()
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}

### Find Elements using relative locators

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from
English. Do you speak Japanese? Help us to translate
it by sending us pull requests!
{{% /notice %}}

Note: This feature is available in Selenium 4 or later

These feature allows you to find Elements in the DOM based off the position
of another element using relative locations like `above`, `below`, `toLeftOf`,
and `toRightOf`. The Elements should be returned in proximity order.


{{< code-tab >}}
  {{< code-panel language="java" >}}
  import org.openqa.selenium.By;
  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.WebElement;
  import org.openqa.selenium.chrome.ChromeDriver;
  import java.util.List;

  import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

  public class findElementsUsingRelativeLocators {
      public static void main(String[] args) {
          WebDriver driver = new ChromeDriver();
          try {
              driver.get("https://example.com");

              // Get element with tag name 'div'
              WebElement fromElement = driver.findElement(By.tagName("div"));

              // Get all the elements available with tag name 'p' above
              // WebElement `fromElement`
              List<WebElement> elements = driver.findElements(
                withTagName("p").above(fromElement)
                );
              for (WebElement e : elements) {
                  System.out.println(e.getText());
              }
          } finally {
              driver.quit();
          }
      }
  }
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import with_tag_name

driver = webdriver.Chrome()
driver.get("https://www.example.com")

# Get element with tag name 'div'
form_element = driver.find_element(By.TAG_NAME, 'div')

# Get all the elements available with tag name 'p' above
# WebElement `fromElement`'
elements = elements = driver.find_elements(with_tag_name("p").above(form_element))
for e in elements:
    print(e.text)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System.Collections.Generic;

namespace FindElementsUsingRelativeLocators {
 class FindElementsUsingRelativeLocators {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    driver.Navigate().GoToUrl("https://example.com");

    // Get element with tag name 'div'
    IWebElement fromElement = driver.FindElement(By.TagName("div"));

    // Get all the elements available with tag name 'p' above
    // WebElement `fromElement'
    ReadOnlyCollection<IWebElement> elements = driver.FindElements(
      RelativeBy.WithTagName("p").Above(fromElement)
      );
    foreach(IWebElement e in elements) {
     System.Console.WriteLine(e.Text);
    }
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
  require 'selenium-webdriver'
  driver = Selenium::WebDriver.for :chrome
  begin
    # Navigate to URL
    driver.get 'https://www.example.com'

    # Get element with tag name 'div'
    from_element = driver.find_element(:tag_name,'div')

    # Get all the elements available with tag name 'p' above
    # WebElement `fromElement`'
    elements = element.find_elements(
      relative: {tag_name: 'p', above: from_element}
    )

    elements.each { |e|
      puts e.text
    }
  ensure
    driver.quit
  end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = new Builder()
          .forBrowser('chrome')
          .build();

      await driver.get('https://www.example.com');

      // Get element with tag name 'div'
      let fromElement = await driver.findElement(By.css("div"));

      // Get all the elements available with tag name 'p' above
      // WebElement `fromElement'
      let elements = await driver.findElements(
        withTagName('p').above(fromElement)
      );
      for(let e of elements) {
          console.log(await e.getText());
      }
  })();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
  import org.openqa.selenium.By
  import org.openqa.selenium.chrome.ChromeDriver

  fun main() {
      val driver = ChromeDriver()
      try {
          driver.get("https://example.com")

          // Get element with tag name 'div'
          val fromElement = driver.findElement(By.tagName("div"))

           // Get all the elements available with tag name 'p' above
          // WebElement `fromElement''
          val elements = driver.findElements(
                withTagName("p").above(fromElement)
                )
          for (e in elements) {
              println(e.text)
          }
      } finally {
          driver.quit()
      }
  }
  {{< / code-panel >}}
{{< / code-tab >}}

## 要素から要素の検索

親要素のコンテキスト内で子要素を見つけるために使用します。
これを実現するには、親Web要素を'findElement'と連鎖して、子要素にアクセスします。

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new FirefoxDriver();
driver.get("http://www.google.com");
WebElement searchForm = driver.findElement(By.tagName("form"));
WebElement searchBox = searchForm.findElement(By.name("q"));
searchBox.sendKeys("webdriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Firefox()
driver.get("http://www.google.com")
search_form = driver.find_element(By.TAG_NAME, "form")
search_box = search_form.find_element(By.NAME, "q")
search_box.send_keys("webdriver")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebDriver driver = new FirefoxDriver();
driver.Url = "http://www.google.com";
IWebElement searchForm = driver.FindElement(By.TagName("form"));
IWebElement searchbox = searchForm.FindElement(By.Name("q"));
searchbox.SendKeys("webdriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  # Navigate to URL
  driver.get 'https://google.com'

  # Get and store DOM element '<form>'
  search_form = driver.find_element(name: 'f')

  # Get search box element from webElement 'form'
  search_bar = search_form.find_element(name: 'q')

  # Perform action using WebElement
  search_bar.send_keys 'Webdriver'
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let {Builder, By} = require('selenium-webdriver');
driver = new Builder().forBrowser('firefox').build();

(async function test(){

//Navigate to url
await driver.get('http://www.google.com');

//Get and store DOM element '<form>'
let searchForm = driver.findElement(By.name('f'));

//Get search box element from webElement 'form'
let searchBar = searchForm.findElement(By.name('q'));

//Perform action using WebElement
await searchBar.sendKeys('Webdriver');

})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val driver = FirefoxDriver()
driver.get("http://www.google.com")
val searchForm = driver.findElement(By.tagName("form"))
val searchBox = searchForm.findElement(By.name("q"))
searchBox.sendKeys("webdriver")
  {{< / code-panel >}}
{{< / code-tab >}}

## 要素から複数の要素の検索

親要素のコンテキスト内で一致する子Web要素のリストを見つけるために使用します。
これを実現するために、親Web要素は’findElements'と連鎖して子要素にアクセスします。

{{< code-tab >}}
  {{< code-panel language="java" >}}
  import org.openqa.selenium.By;
  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.WebElement;
  import org.openqa.selenium.chrome.ChromeDriver;
  import java.util.List;

  public class findElementsFromElement {
      public static void main(String[] args) {
          WebDriver driver = new ChromeDriver();
          try {
              driver.get("https://example.com");

              // Get element with tag name 'div'
              WebElement element = driver.findElement(By.tagName("div"));

              // Get all the elements available with tag name 'p'
              List<WebElement> elements = element.findElements(By.tagName("p"));
              for (WebElement e : elements) {
                  System.out.println(e.getText());
              }
          } finally {
              driver.quit();
          }
      }
  }
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()
driver.get("https://www.example.com")

# Get element with tag name 'div'
element = driver.find_element(By.TAG_NAME, 'div')

# Get all the elements available with tag name 'p'
elements = element.find_elements(By.TAG_NAME, 'p')
for e in elements:
    print(e.text)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System.Collections.Generic;

namespace FindElementsFromElement {
 class FindElementsFromElement {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    driver.Navigate().GoToUrl("https://example.com");

    // Get element with tag name 'div'
    IWebElement element = driver.FindElement(By.TagName("div"));

    // Get all the elements available with tag name 'p'
    IList < IWebElement > elements = element.FindElements(By.TagName("p"));
    foreach(IWebElement e in elements) {
     System.Console.WriteLine(e.Text);
    }
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
  require 'selenium-webdriver'
  driver = Selenium::WebDriver.for :chrome
  begin
    # Navigate to URL
    driver.get 'https://www.example.com'

    # Get element with tag name 'div'
    element = driver.find_element(:tag_name,'div')

    # Get all the elements available with tag name 'p'
    elements = element.find_elements(:tag_name,'p')

    elements.each { |e|
      puts e.text
    }
  ensure
    driver.quit
  end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = new Builder()
          .forBrowser('chrome')
          .build();

      await driver.get('https://www.example.com');

      // Get element with tag name 'div'
      let element = driver.findElement(By.css("div"));

      // Get all the elements available with tag name 'p'
      let elements = await element.findElements(By.css("p"));
      for(let e of elements) {
          console.log(await e.getText());
      }
  })();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
  import org.openqa.selenium.By
  import org.openqa.selenium.chrome.ChromeDriver

  fun main() {
      val driver = ChromeDriver()
      try {
          driver.get("https://example.com")

          // Get element with tag name 'div'
          val element = driver.findElement(By.tagName("div"))

          // Get all the elements available with tag name 'p'
          val elements = element.findElements(By.tagName("p"))
          for (e in elements) {
              println(e.text)
          }
      } finally {
          driver.quit()
      }
  }
  {{< / code-panel >}}
{{< / code-tab >}}

## アクティブな要素の取得

現在のブラウジングコンテキストにフォーカスがあるDOM要素を追跡（または）検索するために使用します。

{{< code-tab >}}
  {{< code-panel language="java" >}}
  import org.openqa.selenium.*;
  import org.openqa.selenium.chrome.ChromeDriver;

  public class activeElementTest {
    public static void main(String[] args) {
      WebDriver driver = new ChromeDriver();
      try {
        driver.get("http://www.google.com");
        driver.findElement(By.cssSelector("[name='q']")).sendKeys("webElement");

        // Get attribute of current active element
        String attr = driver.switchTo().activeElement().getAttribute("title");
        System.out.println(attr);
      } finally {
        driver.quit();
      }
    }
  }
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
  from selenium import webdriver
  from selenium.webdriver.common.by import By

  driver = webdriver.Chrome()
  driver.get("https://www.google.com")
  driver.find_element(By.CSS_SELECTOR, '[name="q"]').send_keys("webElement")

  # Get attribute of current active element
  attr = driver.switch_to.active_element.get_attribute("title")
  print(attr)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
    using OpenQA.Selenium;
    using OpenQA.Selenium.Chrome;

    namespace ActiveElement {
     class ActiveElement {
      public static void Main(string[] args) {
       IWebDriver driver = new ChromeDriver();
       try {
        // Navigate to Url
        driver.Navigate().GoToUrl("https://www.google.com");
        driver.FindElement(By.CssSelector("[name='q']")).SendKeys("webElement");

        // Get attribute of current active element
        string attr = driver.SwitchTo().ActiveElement().GetAttribute("title");
        System.Console.WriteLine(attr);
       } finally {
        driver.Quit();
       }
      }
     }
    }
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
  require 'selenium-webdriver'
  driver = Selenium::WebDriver.for :chrome
  begin
    driver.get 'https://www.google.com'
    driver.find_element(css: '[name="q"]').send_keys('webElement')

    # Get attribute of current active element
    attr = driver.switch_to.active_element.attribute('title')
    puts attr
  ensure
    driver.quit
  end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = await new Builder().forBrowser('chrome').build();
      await driver.get('https://www.google.com');
      await  driver.findElement(By.css('[name="q"]')).sendKeys("webElement");

      // Get attribute of current active element
      let attr = await driver.switchTo().activeElement().getAttribute("title");
      console.log(`${attr}`)
  })();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
  import org.openqa.selenium.By
  import org.openqa.selenium.chrome.ChromeDriver

  fun main() {
      val driver = ChromeDriver()
      try {
          driver.get("https://www.google.com")
          driver.findElement(By.cssSelector("[name='q']")).sendKeys("webElement")

          // Get attribute of current active element
          val attr = driver.switchTo().activeElement().getAttribute("title")
          print(attr)
      } finally {
          driver.quit()
      }
  }
  {{< / code-panel >}}
{{< / code-tab >}}

## Is Element Enabled

This method is used to check if the connected Element
is enabled or disabled on a webpage.
Returns a boolean value, **True** if the connected element is
**enabled** in the current browsing context else returns **false**.

{{< code-tab >}}
  {{< code-panel language="java" >}}
  //navigates to url
  driver.get("https://www.google.com/");

  //returns true if element is enabled else returns false
  boolean value = driver.findElement(By.name("btnK")).isEnabled();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Navigate to url
driver.get("http://www.google.com")

# Returns true if element is enabled else returns false
value = driver.find_element(By.NAME, 'btnK').is_enabled()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://google.com");

// Store the WebElement
IWebElement element = driver.FindElement(By.Name("btnK"));

// Prints true if element is enabled else returns false
System.Console.WriteLine(element.Enabled);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Navigate to url
driver.get 'http://www.google.com/'

# Returns true if element is enabled else returns false
ele = driver.find_element(name: 'btnK').enabled?
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Navigate to url
await driver.get('https://www.google.com');

// Resolves Promise and returns boolean value
let element =  await driver.findElement(By.name("btnK")).isEnabled();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
 //navigates to url
 driver.get("https://www.google.com/")

 //returns true if element is enabled else returns false
 val attr = driver.findElement(By.name("btnK")).isEnabled()
  {{< / code-panel >}}
{{< / code-tab >}}

## Is Element Selected

This method determines if the referenced Element
is _Selected_ or not. This method is widely used on
Check boxes, radio buttons, input elements, and option elements.

Returns a boolean value, **True** if referenced element is
**selected** in the current browsing context else returns **false**.

{{< code-tab >}}
  {{< code-panel language="java" >}}
 //navigates to url
 driver.get("https://the-internet.herokuapp.com/checkboxes");

 //returns true if element is checked else returns false
 boolean value = driver.findElement(By.cssSelector("input[type='checkbox']:first-of-type")).isSelected();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Navigate to url
driver.get("https://the-internet.herokuapp.com/checkboxes")

# Returns true if element is checked else returns false
value = driver.find_element(By.CSS_SELECTOR, "input[type='checkbox']:first-of-type").is_selected()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://the-internet.herokuapp.com/checkboxes");

// Returns true if element ins checked else returns false
bool value = driver.FindElement(By.CssSelector("input[type='checkbox']:last-of-type")).Selected;
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Navigate to url
driver.get 'https://the-internet.herokuapp.com/checkboxes'

# Returns true if element is checked else returns false
ele = driver.find_element(css: "input[type='checkbox']:last-of-type").selected?
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Navigate to url
await driver.get('https://the-internet.herokuapp.com/checkboxes');

// Returns true if element ins checked else returns false
let res = await driver.findElement(By.css("input[type='checkbox']:last-of-type")).isSelected();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
 //navigates to url
 driver.get("https://the-internet.herokuapp.com/checkboxes")

 //returns true if element is checked else returns false
 val attr =  driver.findElement(By.cssSelector("input[type='checkbox']:first-of-type")).isSelected()
  {{< / code-panel >}}
{{< / code-tab >}}

## Get Element TagName

It is used to fetch the [TagName](https://www.w3.org/TR/webdriver/#dfn-get-element-tag-name)
of the referenced Element which has the focus in the current browsing context.

{{< code-tab >}}
  {{< code-panel language="java" >}}
 //navigates to url
 driver.get("https://www.example.com");

 //returns TagName of the element
 String value = driver.findElement(By.cssSelector("h1")).getTagName();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Navigate to url
driver.get("https://www.example.com")

# Returns TagName of the element
attr = driver.find_element(By.CSS_SELECTOR, "h1").tag_name
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.example.com");

// Returns TagName of the element
string attr = driver.FindElement(By.CssSelector("h1")).TagName;
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Navigate to url
driver.get 'https://www.example.com'

# Returns TagName of the element
attr = driver.find_element(css: "h1").tag_name
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Navigate to URL
await driver.get('https://www.example.com');

// Returns TagName of the element
let value = await driver.findElement(By.css('h1')).getTagName();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
 //navigates to url
 driver.get("https://www.example.com")

 //returns TagName of the element
 val attr =  driver.findElement(By.cssSelector("h1")).getTagName()
  {{< / code-panel >}}
{{< / code-tab >}}

## Get Element Rect

It is used to fetch the dimensions and coordinates
of the referenced element.

The fetched data body contain the following details:
* X-axis position from the top-left corner of the element
* y-axis position from the top-left corner of the element
* Height of the element
* Width of the element

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Navigate to url
driver.get("https://www.example.com");

// Returns height, width, x and y coordinates referenced element
Rectangle res =  driver.findElement(By.cssSelector("h1")).getRect();

// Rectangle class provides getX,getY, getWidth, getHeight methods
System.out.println(res.getX());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Navigate to url
driver.get("https://www.example.com")

# Returns height, width, x and y coordinates referenced element
res = driver.find_element(By.CSS_SELECTOR, "h1").rect
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://example.com");

var res = driver.FindElement(By.CssSelector("h1"));
// Return x and y coordinates referenced element
System.Console.WriteLine(res.Location);
// Returns height, width
System.Console.WriteLine(res.Size);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Navigate to url
driver.get 'https://www.example.com'

# Returns height, width, x and y coordinates referenced element
res = driver.find_element(css: "h1").rect
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Navigate to url
await driver.get('https://www.example.com');

// Returns height, width, x and y coordinates referenced element
let element =  await driver.findElement(By.css("h1")).getRect();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Navigate to url
driver.get("https://www.example.com")

// Returns height, width, x and y coordinates referenced element
val res = driver.findElement(By.cssSelector("h1")).rect

// Rectangle class provides getX,getY, getWidth, getHeight methods
println(res.getX())
  {{< / code-panel >}}
{{< / code-tab >}}

## Get Element CSS Value

Retrieves the value of specified computed style property
of an element in the current browsing context.

{{< code-tab >}}
  {{< code-panel language="java" >}}

// Navigate to Url
driver.get("https://www.example.com");

// Retrieves the computed style property 'color' of linktext
String cssValue = driver.findElement(By.linkText("More information...")).getCssValue("color");

  {{< / code-panel >}}
  {{< code-panel language="python" >}}

# Navigate to Url
driver.get('https://www.example.com')

# Retrieves the computed style property 'color' of linktext
cssValue = driver.findElement(By.LINK_TEXT, "More information...").value_of_css_property('color')

  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}

// Navigate to Url
driver.Navigate().GoToUrl("https://www.example.com");

// Retrieves the computed style property 'color' of linktext
String cssValue = driver.FindElement(By.LinkText("More information...")).GetCssValue("color");

  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}

# Navigate to Url
driver.get 'https://www.example.com'

# Retrieves the computed style property 'color' of linktext
cssValue = driver.find_element(:link_text, 'More information...').css_value('color')

  {{< / code-panel >}}
    {{< code-panel language="javascript" >}}
// Navigate to Url
await driver.get('https://www.example.com');

// Retrieves the computed style property 'color' of linktext
let cssValue = await driver.findElement(By.linkText("More information...")).getCssValue('color');
    {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}

// Navigate to Url
driver.get("https://www.example.com")

// Retrieves the computed style property 'color' of linktext
val cssValue = driver.findElement(By.linkText("More information...")).getCssValue("color")

  {{< / code-panel >}}
{{< / code-tab >}}

## Get Element Text

Retrieves the rendered text of the specified element.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Navigate to url
driver.get("https://example.com");

// Retrieves the text of the element
String text = driver.findElement(By.cssSelector("h1")).getText();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Navigate to url
driver.get("https://www.example.com")

# Retrieves the text of the element
text = driver.find_element(By.CSS_SELECTOR, "h1").text
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please provide a pr for code sample
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Navigate to url
driver.get 'https://www.example.com'

# Retrieves the text of the element
text = driver.find_element(:css, 'h1').text
  {{< / code-panel >}}
    {{< code-panel language="javascript" >}}
// Navigate to URL
await driver.get('http://www.example.com');

// retrieves the text of the element
let text = await driver.findElement(By.css('h1')).getText();
    {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Navigate to URL
driver.get("https://www.example.com")

// retrieves the text of the element
val text = driver.findElement(By.cssSelector("h1")).getText()
  {{< / code-panel >}}
{{< / code-tab >}}
