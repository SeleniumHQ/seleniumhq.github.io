---
title: "网络元素"
weight: 9
---


WebElement表示DOM元素.
可以通过使用WebDriver实例从文档根节点进行搜索, 
或者在另一个WebElement下进行搜索来找到WebElement.

WebDriver API提供了内置方法来查找基于不同属性的WebElement
(例如ID, Name, Class, XPath, CSS选择器, 链接文本等).

## Find Element

此方法用于查找元素并返回第一个匹配的单个WebElement引用,
该元素可用于进一步的元素操作.

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

## Find Elements

与"Find Element"相似, 但返回的是匹配WebElement列表.
要使用列表中的特定WebElement, 
您需要遍历元素列表以对选定元素执行操作.

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

## Find Element From Element

此方法用于在父元素的上下文中查找子元素.
为此, 父WebElement与"findElement"链接并访问子元素.


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

## Find Elements From Element

此方法用于在父元素的上下文中查找匹配子WebElement的列表.
为此, 父WebElement与"findElements"链接并访问子元素.

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

## Get Active Element

此方法用于追溯或查找当前页面上下文中具有焦点的DOM元素.

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

此方法用于检查网页上连接的元素是否被启用或禁用.
返回一个布尔值, 
如果在当前浏览上下文中`启用`了连接的元素, 则返回True；
否则返回`false` .

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

此方法确定是否 _已选择_ 引用的元素.
此方法广泛用于复选框, 单选按钮, 输入元素和选项元素.

返回一个布尔值, 
如果在当前浏览上下文中 **已选择** 引用的元素, 
则返回 **True**, 否则返回 **False**.

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

此方法用于获取在当前浏览上下文中
具有焦点的被引用元素的
[TagName](https://www.w3.org/TR/webdriver/#dfn-get-element-tag-name) .

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

用于获取参考元素的尺寸和坐标. 

提取的数据主体包含以下详细信息:
* 元素左上角的X轴位置
* 元素左上角的y轴位置
* 元素的高度
* 元素宽度

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