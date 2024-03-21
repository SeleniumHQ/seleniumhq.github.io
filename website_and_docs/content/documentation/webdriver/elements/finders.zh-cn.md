---
title: "查询网络元素"
linkTitle: "查询器"
weight: 2
needsTranslation: true
aliases: [
"/documentation/zh-cn/webdriver/locating_elements/",
"/zh-cn/documentation/webdriver/locating_elements/"
]
description: >
  根据提供的定位值定位元素.
---

使用Selenium的一个最基本的方面是通过元素查询器获得元素引用。
Selenium提供了许多内置的[定位器策略]({{< ref "locators.md" >}})来唯一标识元素。
有很多方法可以在非常高级的场景中使用定位器。作为样例，让我们来看看这个HTML代码段：


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

## 第一个匹配条件的元素 

同一个页面上可能有多个元素符合定位器匹配条件。单次`find element`方法调用将只会返回一个指向在给定上下文中找到的第一个元素的引用。


### 在整个DOM中查找元素

当在驱动实例上调用`find element`方法时，它会返回DOM中与所提供的定位器匹配的第一个元素的引用。这个引用可以被保存用于之后对此元素的操作。
在上面的HTML示例中，有两个元素的类名都是 "tomatoes"，他们分别在两个列表中。以下代码将返回第一个列表，即 "vegetables "列表中的"tomatos"元素引用。

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


### 在部分DOM（DOM子集）中查找元素

以上代码无法获取在样例第二个列表中的"tomato"元素。如果知道目标元素所属位置且希望获取指定范围里的元素而不是整个上下文中的第一个，可以进行部分搜索。


一种方案是先查找两个目标同名元素的不同名父节点，从而获取两个元素所属的不同部分的父节点元素。查找的父节点应该各自拥有唯一属性，且这个父节点不能是目标元素本身。在此之上，再次对得到的父节点运行`find element`查找目标元素。

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
**Java 和 C#**<br>
`WebDriver`, `WebElement` 和 `ShadowRoot` 三个类都实现了 `SearchContext`接口。这个接口是 _基于角色的接口_。

基于角色的接口允许您确定特定驱动程序实现是否支持给定功能。这些接口明确定义了所属角色的信息，每个对象尽量遵守只负责一个角色。
{{% /pageinfo %}}

### 定位器优化

上述嵌套查找会导致客户端向驱动发送多条不同的请求。这可能导致效率问题。

为了略微提高性能，我们可以使用 CSS 或 XPath 在单个命令中查找该元素。请查看在[最佳实践]({{< ref "/documentation/test_practices/encouraged" >}})中的[使用定位器的提示]({{< ref "/documentation/test_practices/encouraged/locators" >}})部分。

如下所示，我们通过CSS选择器查找元素：

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


## 获取所有符合条件元素的列表

某些情况下需要获取当前查询DOM下所有符合定位器条件的元素的引用，而不是只有第一个。查找多个元素（`findElements`）方法返回一个元素引用列表。如果没有匹配的元素，则返回空列表。

在以下代码中，所有水果和蔬菜列表内项目元素的引用将以集合的形式返回。

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

### 从元素集合中获取元素
你可以通过`findElements`获取元素集合后处理所有元素或者其中一个元素。由于返回的是一个集合，因此你可以通过遍历从而逐个处理或者筛选目标元素。


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

## 由父节点查找子节点元素

此功能用于在父元素的上下文中查找匹配的子WebElements列表。为实现此功能，父 WebElement 与 "findElements "连锁，以访问子元素。

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

## 获取当前活动（得到焦点）的元素

用于查找（或跟踪）当前上下文中拥有焦点的元素。

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


