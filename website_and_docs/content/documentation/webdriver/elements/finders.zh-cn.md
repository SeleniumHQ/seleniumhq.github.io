---
title: "查询网络元素"
linkTitle: "查询器"
weight: 2
aliases: [
"/documentation/zh-cn/webdriver/locating_elements/",
"/zh-cn/documentation/webdriver/locating_elements/"
]
description: >
  根据提供的定位值定位元素.
---


使用 Selenium 最基本的特点之一是获取可用于操作的元素引用。
Selenium 提供了许多内置的 [定位策略]({{< ref "locators.md" >}})，用于唯一标识元素。
在更复杂的场景中，可以用多种方式使用这些定位器。为了本篇文档的目的，
我们来考虑下面的 HTML 片段：

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

## 第一个匹配的元素

许多定位器会匹配页面上的多个元素。
单个的 find element 方法会返回在给定上下文中找到的第一个元素的引用。

### 在整个 DOM 中查找

当在 driver 实例上调用 find element 方法时，
它会返回 DOM 中与所提供定位器匹配的第一个元素的引用。
该引用可以被保存并用于后续的元素操作。
在上面的示例 HTML 中，有两个 class 名称为 "tomatoes" 的元素，
因此此方法会返回位于 "vegetables" 列表中的那个元素。

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
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L10" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
const vegetable = await driver.findElement(By.className('tomatoes'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val vegetable: WebElement = driver.findElement(By.className("tomatoes"))
  {{< /tab >}}
{{< /tabpane >}}



### 在 DOM 的子集内评估

与其在整个 DOM 中寻找唯一的定位器，
通常更有用的是将搜索范围缩小到另一个已定位元素的作用域内。
在上面的示例中，有两个 class 名为 "tomatoes" 的元素，
因此要获取第二个元素的引用会更具挑战性。

一种解决办法是先定位一个具有唯一属性的元素，
该元素是目标元素的祖先但不是非目标元素的祖先，
然后在该对象上调用 `find element`：

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
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L14-L15" >}}
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
`WebDriver`、`WebElement` 和 `ShadowRoot` 类都实现了 `SearchContext` 接口，  
该接口被视为一种 _基于角色的接口_。基于角色的接口可以让你判断特定的驱动实现是否支持某项功能。  
这些接口定义清晰，并尽量遵循单一职责原则。  
{{% /pageinfo %}}

### 评估 Shadow DOM

Shadow DOM 是隐藏在元素内部的封装 DOM 树。  
自 Chromium 浏览器在 v96 发布后，Selenium 已支持通过易用的 shadow root 方法访问该树。注意：这些方法需要 Selenium 4.0 或更高版本。

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
{{< tab header="Java" >}}
WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"));
SearchContext shadowRoot = shadowHost.getShadowRoot();
WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#shadow_content"));
{{< /tab >}}
{{< tab header="Python" >}}
shadow_host = driver.find_element(By.CSS_SELECTOR, '#shadow_host')
shadow_root = shadow_host.shadow_root
shadow_content = shadow_root.find_element(By.CSS_SELECTOR, '#shadow_content')
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

### 优化后的定位器

嵌套查找可能不是最有效的定位策略，
因为它需要向浏览器发送两次独立的命令。

为略微提升性能，我们可以使用 CSS 或 XPath，在一次命令中定位到该元素。
请参阅本节中关于[定位策略建议]({{< ref "/documentation/test_practices/encouraged/locators" >}})的说明
以及[推荐的测试实践]({{< ref "/documentation/test_practices/encouraged" >}})。

在本例中，我们将使用 CSS 选择器：

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
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L19" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
const fruit = await driver.findElement(By.css('#fruits .tomatoes'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val fruit = driver.findElement(By.cssSelector("#fruits .tomatoes"))
  {{< /tab >}}
{{< /tabpane >}}



## 所有匹配的元素

在某些情况下，需要获取与定位器匹配的所有元素的引用，而不是仅获取第一个。
复数形式的 `find elements` 方法会返回一组元素引用。如果没有匹配项，则返回空列表。
在本例中，将返回所有水果和蔬菜列表项的引用集合。

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
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L23" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
const plants = await driver.findElements(By.tagName('li'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val plants: List<WebElement> = driver.findElements(By.tagName("li"))
  {{< /tab >}}
{{< /tabpane >}}

### 获取元素
有时你会得到一组元素，但想操作其中某个特定元素，
这意味着需要遍历该集合并找到目标元素。


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
   {{< tab header="Ruby" text=true >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L27-L28" >}}
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

## 从元素查找子元素

用于在父元素的上下文中查找匹配的子 WebElement 列表。
为此，可在父 WebElement 上链式调用 `findElements` 来访问子元素。

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
##get elements from parent element using TAG_NAME

    # Get element with tag name 'div'
element = driver.find_element(By.TAG_NAME, 'div')

    # Get all the elements available with tag name 'p'
elements = element.find_elements(By.TAG_NAME, 'p')
for e in elements:
    print(e.text)

##get elements from parent element using XPATH
##NOTE: in order to utilize XPATH from current element, you must add "." to beginning of path

    # Get first element of tag 'ul'
element = driver.find_element(By.XPATH, '//ul')

    # get children of tag 'ul' with tag 'li'
elements  = element.find_elements(By.XPATH, './/li')
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
   {{< tab header="Ruby" text=true >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L32-L34" >}}
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

## 获取活动元素

用于跟踪或查找当前浏览上下文中具有焦点的 DOM 元素。

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
  {{< tab header="Ruby" text=true >}}
  {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L38-L39" >}}
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


