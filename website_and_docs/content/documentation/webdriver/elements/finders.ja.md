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
このドキュメントの目的のために、このHTMLスニペットについて考えてみましょう。

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

## 最初に一致する要素

多くのロケーターは、ページ上の複数の要素と一致します。 
単数の find elementメソッドは、指定されたコンテキスト内で最初に見つかった要素への参照を返します。

### DOM全体の評価

ドライバーインスタンスで要素の検索メソッドが呼び出されると、提供されたロケーターと一致するDOMの最初の要素への参照が返されます。 
この値は保存して、将来の要素アクションに使用できます。 
上記のHTMLの例では、クラス名が "tomatoes" の要素が2つあるため、このメソッドは "vegetables" リストの要素を返します。

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
const vegetable = driver.findElement(By.className('tomatoes'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val vegetable: WebElement = driver.findElement(By.className("tomatoes"))
  {{< /tab >}}
{{< /tabpane >}}


### DOMのサブセットの評価

DOM全体で一意のロケーターを見つけるのではなく、検索を別の検索された要素のスコープに絞り込むと便利なことがよくあります。 
上記の例では、クラス名が "トマト" の2つの要素があり、2番目の要素の参照を取得するのは少し困難です。

1つの解決策は、目的の要素の祖先であり、不要な要素の祖先ではない一意の属性を持つ要素を見つけて、そのオブジェクトでfind要素を呼び出すことです。

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
const fruits = driver.findElement(By.id('fruits'));
const fruit = fruits.findElement(By.className('tomatoes'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val fruits = driver.findElement(By.id("fruits"))
val fruit = fruits.findElement(By.className("tomatoes"))
  {{< /tab >}}
{{< /tabpane >}}

{{% pageinfo color="info" %}}
**Java and C#**<br>
`WebDriver` 、 `WebElement` 、および `ShadowRoot` クラスはすべて、 _ロールベースのインターフェイス_ と見なされる `SearchContext` インターフェイスを実装します。 
ロールベースのインターフェイスを使用すると、特定のドライバーの実装が特定の機能をサポートしているかどうかを判断できます。
これらのインターフェースは明確に定義されており、責任の役割を1つだけ持つように努めています。
{{% /pageinfo %}}

### 最適化されたロケーター

ネストされたルックアップは、ブラウザに2つの別々のコマンドを発行する必要があるため、最も効果的なロケーション戦略ではない可能性があります。

パフォーマンスをわずかに向上させるために、CSSまたはXPathのいずれかを使用して、単一のコマンドでこの要素を見つけることができます。 
[推奨されるテストプラクティス]({{< ref "/documentation/test_practices/encouraged" >}})の章で、[ロケーター戦略]({{< ref "/documentation/test_practices/encouraged/locators" >}})の提案を参照してください。

この例では、CSSセレクターを使用します。

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
const fruit = driver.findElement(By.css('#fruits .tomatoes'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val fruit = driver.findElement(By.cssSelector("#fruits .tomatoes"))
  {{< /tab >}}
{{< /tabpane >}}


## 一致するすべての要素

最初の要素だけでなく、ロケーターに一致するすべての要素への参照を取得する必要があるユースケースがいくつかあります。 
複数の要素の検索メソッドは、要素参照のコレクションを返します。 
一致するものがない場合は、空のリストが返されます。 
この場合、すべてのfruitsとvegetableのリストアイテムへの参照がコレクションに返されます。

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
const plants = driver.findElements(By.tagName('li'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val plants: List<WebElement> = driver.findElements(By.tagName("li"))
  {{< /tab >}}
{{< /tabpane >}}

### 要素の取得
多くの場合、要素のコレクションを取得しますが、特定の要素を操作したいので、コレクションを繰り返し処理して、
必要な要素を特定する必要があります。

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

## 要素から要素を検索

これは、親要素のコンテキスト内で一致する子のWebElementのリストを見つけるために利用されます。 
これを実現するために、親WebElementは'findElements'と連鎖して子要素にアクセスします。

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

## アクティブな要素を取得する

これは、現在のブラウジングコンテキストでフォーカスを持っているDOM要素を追跡（または）検索するために使用されます。

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


