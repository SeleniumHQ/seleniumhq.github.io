---
title: "Web element"
weight: 9
---

Represents a DOM element. WebElements can be found by searching from the
document root using a WebDriver instance, or by searching under another
WebElement:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new FirefoxDriver();
driver.get("http://www.google.com");
WebElement searchForm = driver.findElement(By.tagName("form"));
WebElement searchbox = driver.findElement(By.name("q"));
searchbox.sendKeys("webdriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver = Firefox()
driver.get("http://www.google.com")
search_form = driver.find_element_by_tag_name("form")
search_box = search_form.find_element_by_name("q")
search_box.send_keys("webdriver")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# We don't have a Ruby code sample yet -  Help us out and raise a PR  
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
{{< / code-tab >}}


