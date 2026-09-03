---
title: "Web要素の検索"
linkTitle: "検索"
weight: 2
aliases: [
"/documentation/ja/webdriver/locating_elements/",
"/ja/documentation/webdriver/locating_elements/"
]
description: >
  提供されたロケーターの値に基づいて要素を検索します。
---

Seleniumを使用する最も基本的な側面の1つは、操作する要素の参照を取得することです。 
Seleniumは、要素を一意に識別するための多数の組み込み[ロケーター戦略]({{< ref "locators.md" >}})を提供します。 
非常に高度なシナリオでロケーターを使用する方法はたくさんあります。 
このドキュメントの目的のために、[Seleniumロケーターテストページ](https://www.selenium.dev/selenium/web/locators_tests/locators.html)を使用します。

## 最初に一致する要素

多くのロケーターは、ページ上の複数の要素と一致します。 
単数の find elementメソッドは、指定されたコンテキスト内で最初に見つかった要素への参照を返します。

### DOM全体の評価

ドライバーインスタンスで要素の検索メソッドが呼び出されると、提供されたロケーターと一致するDOMの最初の要素への参照が返されます。 
この値は保存して、将来の要素アクションに使用できます。 
Seleniumロケーターテストページには、クラス名が `information` の要素が2つあるため、このメソッドは最初のテキスト入力を返します。

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


### DOMのサブセットの評価

DOM全体で一意のロケーターを見つけるのではなく、検索を別の検索された要素のスコープに絞り込むと便利なことがよくあります。

1つの解決策は、目的の要素の祖先を見つけて、そのオブジェクトでfind要素を呼び出すことです。

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
`WebDriver` 、 `WebElement` 、および `ShadowRoot` クラスはすべて、 _ロールベースのインターフェイス_ と見なされる `SearchContext` インターフェイスを実装します。 
ロールベースのインターフェイスを使用すると、特定のドライバーの実装が特定の機能をサポートしているかどうかを判断できます。
これらのインターフェースは明確に定義されており、責任の役割を1つだけ持つように努めています。
{{% /pageinfo %}}

### Evaluating the Shadow DOM

The Shadow DOM is an encapsulated DOM tree hidden inside an element. 
With the release of v96 in Chromium Browsers, Selenium can now allow you to access this tree 
with easy-to-use shadow root methods. NOTE: These methods require Selenium 4.0 or greater.

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

### 最適化されたロケーター

ネストされたルックアップは、ブラウザに2つの別々のコマンドを発行する必要があるため、最も効果的なロケーション戦略ではない可能性があります。

パフォーマンスをわずかに向上させるために、CSSまたはXPathのいずれかを使用して、単一のコマンドでこの要素を見つけることができます。 
[推奨されるテストプラクティス]({{< ref "/documentation/test_practices/encouraged" >}})の章で、[ロケーター戦略]({{< ref "/documentation/test_practices/encouraged/locators" >}})の提案を参照してください。

この例では、CSSセレクターを使用します。

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


## 一致するすべての要素

最初の要素だけでなく、ロケーターに一致するすべての要素への参照を取得する必要があるユースケースがいくつかあります。 
複数の要素の検索メソッドは、要素参照のコレクションを返します。 
一致するものがない場合は、空のリストが返されます。 
この場合、すべてのinput要素への参照がコレクションに返されます。

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

### 要素の取得
多くの場合、要素のコレクションを取得しますが、特定の要素を操作したいので、コレクションを繰り返し処理して、
必要な要素を特定する必要があります。

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

## 要素から要素を検索

これは、親要素のコンテキスト内で一致する子のWebElementのリストを見つけるために利用されます。 
これを実現するために、親WebElementは'findElements'と連鎖して子要素にアクセスします。

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

## アクティブな要素を取得する

これは、現在のブラウジングコンテキストでフォーカスを持っているDOM要素を追跡（または）検索するために使用されます。

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


