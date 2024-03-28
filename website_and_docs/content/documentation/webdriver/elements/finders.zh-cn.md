---
title: "查找网页元素"
linkTitle: "查找器"
weight: 2
needsTranslation: true
aliases: [
"/documentation/zh-cn/webdriver/locating_elements/",
"/zh-cn/documentation/webdriver/locating_elements/"
]
description: >
  通过定位器的值确定元素的位置.
---

获得元素的引用是使用`Selenium`最重要且最基本的应用。`Selenium`提供内置定位器策略用来唯一地标识一个元素。在非常复杂的场景中有很多种方式使用定位器。为了表达这篇文档的目的，让我们探讨下这段`HTML`片段。

```html
<ol id="vegetables">
 <li class="potatoes">…
 <li class="onions">…
 <li class="tomatoes"><span>Tomato is a Vegetable</span>…
</ol>
<ul id="fruits">
  <li class="bananas">…
  <li class="apples">…
  <li class="tomatoes"><span>Tomato is a Fruit</span>…
</ul>
```

## 匹配第一个元素 

在网页中许多定位器会匹配到多个元素。单个元素查找方法将会返回在给定上下文范围内找到的第一个元素的索引。

### 计算整个`DOM`

当查找元素方法被驱动实例调用时，它会返回使用提供的定位器在文档中匹配到的第一个元素的引用。这个值可以存储和以后的元素操作中使用。在我们的`HTML`示例上，有两个元素的样式类名是`tomatoes`，所以这个方法会返回`vegetables`列表中的元素。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement vegetable = driver.findElement(By.className("tomatoes"));
  {{< /tab >}}
  {{< tab header="Python" >}}
vegetable = driver.find_element(By.CLASS_NAME, "tomatoes")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var vegetable = driver.FindElement(By.ClassName("tomatoes"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
vegetable = driver.find_element(class: 'tomatoes')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const vegetable = await driver.findElement(By.className('tomatoes'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val vegetable: WebElement = driver.findElement(By.className("tomatoes"))
  {{< /tab >}}
{{< /tabpane >}}


### 计算`DOM`的一个子集

而不是在整个文档中查找一个唯一的定位器，在另一个已经定位元素范围内缩小查找范围是常常有用的。在示例上有两个元素使用了`tomatoes`的样式类名，获得第二个元素的引用就有些困难了。

一种解决方案是使用唯一属性定位元素，这个元素是需要被定位元素的父元素而不是不需要被定位元素的父元素，然后用对象调用查找元素方法：

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement fruits = driver.findElement(By.id("fruits"));
WebElement fruit = fruits.findElement(By.className("tomatoes"));
  {{< /tab >}}
  {{< tab header="Python" >}}
fruits = driver.find_element(By.ID, "fruits")
fruit = fruits.find_element(By.CLASS_NAME,"tomatoes")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebElement fruits = driver.FindElement(By.Id("fruits"));
IWebElement fruit = fruits.FindElement(By.ClassName("tomatoes"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
fruits = driver.find_element(id: 'fruits')
fruit = fruits.find_element(class: 'tomatoes')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const fruits = await driver.findElement(By.id('fruits'));
const fruit = fruits.findElement(By.className('tomatoes'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val fruits = driver.findElement(By.id("fruits"))
val fruit = fruits.findElement(By.className("tomatoes"))
  {{< /tab >}}
{{< /tabpane >}}

{{% pageinfo color="info" %}}
**Java and C#**<br>
`WebDriver`, `WebElement` and `ShadowRoot` classes all implement a `SearchContext` interface, which is
considered a _role-based interface_. Role-based interfaces allow you to determine whether a particular
driver implementation supports a given feature. These interfaces are clearly defined and try 
to adhere to having only a single role of responsibility.
{{% /pageinfo %}}

### 优化定位器

嵌套查找可能不是最高效的定位策略，因为它会向浏览器发送两条独立的命令。

为了尽量提高查找性能，我们可以使用一个命令，`CSS`或者`XPath`中的其中一个查找这个元素。详情见[Locator strategy suggestions]({{< ref "/documentation/test_practices/encouraged/locators" >}})中的[Encouraged test practices]({{< ref "/documentation/test_practices/encouraged" >}})部分。

这个示例中，我们使用`CSS`选择器：

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement fruit = driver.findElement(By.cssSelector("#fruits .tomatoes"));
  {{< /tab >}}
  {{< tab header="Python" >}}
fruit = driver.find_element(By.CSS_SELECTOR,"#fruits .tomatoes")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var fruit = driver.FindElement(By.CssSelector("#fruits .tomatoes"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
fruit = driver.find_element(css: '#fruits .tomatoes')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const fruit = await driver.findElement(By.css('#fruits .tomatoes'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val fruit = driver.findElement(By.cssSelector("#fruits .tomatoes"))
  {{< /tab >}}
{{< /tabpane >}}


## 匹配所有元素

有几个用例需要获得匹配定位器的所有元素的引用，而不只是获得第一个匹配到元素。查找多个元素的方法会返回一个元素引用集合。如果没有匹配，这返回一个空的列表。在这个用例中，将返回所有`fruits`和`vegetable`的列表项元素引用的集合。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
List<WebElement> plants = driver.findElements(By.tagName("li"));
  {{< /tab >}}
  {{< tab header="Python" >}}
plants = driver.find_elements(By.TAG_NAME, "li")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IReadOnlyList<IWebElement> plants = driver.FindElements(By.TagName("li"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
plants = driver.find_elements(tag_name: 'li')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const plants = await driver.findElements(By.tagName('li'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val plants: List<WebElement> = driver.findElements(By.tagName("li"))
  {{< /tab >}}
{{< /tabpane >}}

### 获得元素

常常你想从获得的元素集合中获得指定的元素，意思是你需要遍历集合和识别出你想要的那个。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
List<WebElement> elements = driver.findElements(By.tagName("li"));

for (WebElement element : elements) {
    System.out.println("Paragraph text:" + element.getText());
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Firefox()

    # Navigate to Url
driver.get("https://www.example.com")

    # Get all the elements available with tag name 'p'
elements = driver.find_elements(By.TAG_NAME, 'p')

for e in elements:
    print(e.text)
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
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
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}

## 从单个元素中查找多个元素

它用于在父元素上下文内查找匹配子元素的列表，实现这个，父元素使用`findElements`访问子元素。

{{< tabpane langEqualsHeader=true >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
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
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}

## 获得活动元素

它使用跟踪查找`DOM`元素，这种元素在当前浏览的上下文内处于焦点。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
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
  {{< /tab >}}
  {{< tab header="Python" >}}
  from selenium import webdriver
  from selenium.webdriver.common.by import By

  driver = webdriver.Chrome()
  driver.get("https://www.google.com")
  driver.find_element(By.CSS_SELECTOR, '[name="q"]').send_keys("webElement")

    # Get attribute of current active element
  attr = driver.switch_to.active_element.get_attribute("title")
  print(attr)
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
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
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = await new Builder().forBrowser('chrome').build();
      await driver.get('https://www.google.com');
      await  driver.findElement(By.css('[name="q"]')).sendKeys("webElement");

      // Get attribute of current active element
      let attr = await driver.switchTo().activeElement().getAttribute("title");
      console.log(`${attr}`)
  })();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}


