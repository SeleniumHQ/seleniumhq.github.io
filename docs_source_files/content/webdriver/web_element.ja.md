---
title: "Web element"
weight: 9
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

Represents a DOM element. WebElements can be found by searching from the
document root using a WebDriver instance, or by searching under another
WebElement:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new FirefoxDriver();
driver.get("http://www.google.com");
WebElement searchForm = driver.findElement(By.tagName("form"));
WebElement searchBox = searchForm.findElement(By.name("q"));
searchBox.sendKeys("webdriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver = Firefox()
driver.get("http://www.google.com")
search_form = driver.find_element_by_tag_name("form")
search_box = search_form.find_element_by_name("q")
search_box.send_keys("webdriver")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebDriver driver = new FirefoxDriver();
driver.Url = "http://www.google.com";
IWebElement searchForm = driver.FindElement(By.TagName("form"));
IWebElement searchbox = driver.FindElement(By.Name("q"));
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
driver = new Builder().forBrowser('chrome').build();

(async function test(){

//Navigate to url
await driver.get('http://www.google.com');

//Get and store DOM element '<form>'
let searchForm = await driver.findElement(By.name('f'));

//Get search box element from webElement 'form'
let searchBar = await searchForm.findElement(By.name('q'));

//Perform action using WebElement
await searchBar.sendKeys('Webdriver');

})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val driver = ChromeDriver()
driver.get("http://www.google.com")
val searchForm = driver.findElement(By.tagName("form"))
val searchBox = searchForm.findElement(By.name("q"))
searchBox.sendKeys("webdriver")
  {{< / code-panel >}}
{{< / code-tab >}}
