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
请使用 [Selenium 定位器测试页面](https://www.selenium.dev/selenium/web/locators_tests/locators.html)。

## 第一个匹配的元素

许多定位器会匹配页面上的多个元素。
单个的 find element 方法会返回在给定上下文中找到的第一个元素的引用。

### 在整个 DOM 中查找

当在 driver 实例上调用 find element 方法时，
它会返回 DOM 中与所提供定位器匹配的第一个元素的引用。
该引用可以被保存并用于后续的元素操作。
在 Selenium 定位器测试页面上，有两个 class 名称为 `information` 的元素，
因此此方法会返回第一个文本输入框。

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



### 在 DOM 的子集内评估

与其在整个 DOM 中寻找唯一的定位器，
通常更有用的是将搜索范围缩小到另一个已定位元素的作用域内。

一种解决办法是先定位目标元素的祖先，
然后在该对象上调用 `find element`：

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

### 优化后的定位器

嵌套查找可能不是最有效的定位策略，
因为它需要向浏览器发送两次独立的命令。

为略微提升性能，我们可以使用 CSS 或 XPath，在一次命令中定位到该元素。
请参阅本节中关于[定位策略建议]({{< ref "/documentation/test_practices/encouraged/locators" >}})的说明
以及[推荐的测试实践]({{< ref "/documentation/test_practices/encouraged" >}})。

在本例中，我们将使用 CSS 选择器：

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



## 所有匹配的元素

在某些情况下，需要获取与定位器匹配的所有元素的引用，而不是仅获取第一个。
复数形式的 `find elements` 方法会返回一组元素引用。如果没有匹配项，则返回空列表。
在本例中，将返回所有 input 元素的引用集合。

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

### 获取元素
有时你会得到一组元素，但想操作其中某个特定元素，
这意味着需要遍历该集合并找到目标元素。


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

## 从元素查找子元素

用于在父元素的上下文中查找匹配的子 WebElement 列表。
为此，可在父 WebElement 上链式调用 `findElements` 来访问子元素。

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

## 获取活动元素

用于跟踪或查找当前浏览上下文中具有焦点的 DOM 元素。

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


