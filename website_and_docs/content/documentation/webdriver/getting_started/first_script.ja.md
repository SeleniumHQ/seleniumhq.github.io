---
title: "最初のSeleniumスクリプトを書く"
linkTitle: "最初のスクリプト"
weight: 8
needsTranslation: true
description: >
    Seleniumスクリプトを作成するための段階的な説明
---

[Seleniumをインストール]({{< ref "install_library.md" >}})し、
[ドライバーをインストール]({{< ref "install_drivers.md" >}})すると、Seleniumコードを書く準備が整います。

## Eight Basic Components

Seleniumが行うことはすべて、ブラウザコマンドを送信して、何かを実行したり、情報の要求を送信したりすることです。 
Seleniumで行うことのほとんどは、次の基本的なコマンドの組み合わせです。

### 1. ドライバーインスタンスでセッションを開始します

For more details on starting a session read our documentation on [opening and closing a browser]({{< ref "open_browser.md" >}})

{{< tabpane langEqualsHeader=true >}}
    {{< tab header="Java" >}}
    WebDriver driver = new ChromeDriver();
    {{< /tab >}}
    {{< tab header="Python" >}}
    driver = webdriver.Chrome()
    {{< /tab >}}
    {{< tab header="CSharp" >}}
    var driver = new ChromeDriver();
    {{< /tab >}}
    {{< tab header="Ruby" >}}
    driver = Selenium::WebDriver.for :chrome
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
    let driver = await new Builder().forBrowser('chrome').build();
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
    val driver = ChromeDriver()
    {{< /tab >}}
{{< /tabpane >}}

### 2. Take action on browser
In this example we are ブラウザが[ナビゲート]({{< ref "/documentation/webdriver/browser/navigation.md" >}})するコマンドを送信します

{{< tabpane langEqualsHeader=true >}}
    {{< tab header="Java" >}}
    driver.get("https://google.com");
    {{< /tab >}}
    {{< tab header="Python" >}}
    driver.get("http://www.google.com")
    {{< /tab >}}
    {{< tab header="CSharp" >}}
    driver.Navigate().GoToUrl("https://www.google.com");
    {{< /tab >}}
    {{< tab header="Ruby" >}}
    driver.get 'https://google.com'
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
    await driver.get('https://www.google.com');
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
    driver.get("https://google.com")
    {{< /tab >}}
{{< /tabpane >}}

### 3. [ブラウザに関する情報]({{< ref "/documentation/webdriver/browser" >}})をリクエストします

There are a bunch of types of [information about the browser]({{< ref "/documentation/webdriver/browser" >}}) you
can request, including window handles, browser size / position, cookies, alerts, etc.

{{< tabpane langEqualsHeader=true >}}
    {{< tab header="Java" >}}
    driver.getTitle(); // => "Google"
    {{< /tab >}}
    {{< tab header="Python" >}}
    driver.title # => "Google"
    {{< /tab >}}
    {{< tab header="CSharp" >}}
    driver.Title; // => "Google"
    {{< /tab >}}
    {{< tab header="Ruby" >}}
    driver.title # => 'Google'
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
    await driver.getTitle(); // => "Google"
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
    driver.getTitle() // => "Google"
    {{< /tab >}}
{{< /tabpane >}}

### 4. Establish Waiting Strategy

Synchronizing the code with the current state of the browser is one of the biggest challenges
with Selenium, and doing it well is an advanced topic.

Essentially you want to make sure that the element is on the page before you attempt to locate it
and the element is in an interactable state before you attempt to interact with it.

An implicit wait is rarely the best solution, but it's the easiest to demonstrate here, so
we'll use it as a placeholder.

Read more about [Waiting strategies]({{< ref "/documentation/webdriver/waits.md" >}}).

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
{{< /tab >}}
{{< tab header="Python" >}}
driver.implicitly_wait(0.5)
{{< /tab >}}
{{< tab header="CSharp" >}}
driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);
{{< /tab >}}
{{< tab header="Ruby" >}}
driver.manage.timeouts.implicit_wait = 500
{{< /tab >}}
{{< tab header="JavaScript" >}}
driver.manage().setTimeouts({implicit: 0.5 })
{{< /tab >}}
{{< tab header="Kotlin" >}}
driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
{{< /tab >}}
{{< /tabpane >}}


### 5. [要素を検索する]({{< ref "/documentation/webdriver/elements" >}})ためのコマンドを送信します
The majority of commands in most Selenium sessions are element related, and you can't interact
with one without first [finding an element]({{< ref "/documentation/webdriver/elements" >}})

{{< tabpane langEqualsHeader=true >}}
    {{< tab header="Java" >}}
    WebElement searchBox = driver.findElement(By.name("q"));
    WebElement searchButton = driver.findElement(By.name("btnK"));
    {{< /tab >}}
    {{< tab header="Python" >}}
    search_box = driver.find_element(By.NAME, "q")
    search_button = driver.find_element(By.NAME, "btnK")
    {{< /tab >}}
    {{< tab header="CSharp" >}}
    var searchBox = driver.FindElement(By.Name("q"));
    var searchButton = driver.FindElement(By.Name("btnK"))
    {{< /tab >}}
    {{< tab header="Ruby" >}}
    search_box = driver.find_element(name: 'q')
    search_button = driver.find_element(name: 'btnK')
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
    let searchBox = await driver.findElement(By.name('q'));
    let searchButton = await driver.findElement(By.name('btnK'));
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
    val searchBox = driver.findElement(By.name("q"));
    val searchButton = driver.findElement(By.name("btnK"))
    {{< /tab >}}
{{< /tabpane >}}

### 6. 要素に対してアクションを実行する
There are only a handful of [actions to take on an element]({{< ref "/documentation/webdriver/elements/interactions.md" >}}),
but you will use them frequently. 

{{< tabpane langEqualsHeader=true >}}
    {{< tab header="Java" >}}
    searchBox.sendKeys("Selenium");
    searchButton.click();
    {{< /tab >}}
    {{< tab header="Python" >}}
    search_box.send_keys("Selenium")
    search_button.click()
    {{< /tab >}}
    {{< tab header="CSharp" >}}
    searchBox.SendKeys("Selenium");
    searchButton.Click();
    {{< /tab >}}
    {{< tab header="Ruby" >}}
    search_box.send_keys 'Selenium'
    search_button.click
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
    await searchBox.sendKeys('Selenium');
    await searchButton.click();
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
    searchBox.sendKeys("Selenium")
    searchButton.click()
    {{< /tab >}}
{{< /tabpane >}}

### 7. 要素に関する情報をリクエストします
Elements store a lot of [information that can be requested]({{< ref "/documentation/webdriver/elements/information" >}}).
Notice that we need to relocate the search box because the DOM has changed since we first located it.  

{{< tabpane langEqualsHeader=true >}}
    {{< tab header="Java" >}}
    driver.findElement(By.name("q")).getAttribute("value"); // => "Selenium"
    {{< /tab >}}
    {{< tab header="Python" >}}
    driver.find_element(By.NAME, "q").get_attribute("value") # => "Selenium"
    {{< /tab >}}
    {{< tab header="CSharp" >}}
    driver.FindElement(By.Name("q")).GetAttribute("value"); // => "Selenium"
    {{< /tab >}}
    {{< tab header="Ruby" >}}
    driver.find_element(name: 'q').attribute('value') # => "Selenium"
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
    await driver.findElement(By.name('q')).getAttribute("value"); // => 'Selenium'
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
    driver.findElement(By.name("q")).getAttribute("value"); // => "Selenium"
    {{< /tab >}}
{{< /tabpane >}}

### 8. セッションを終了します 

This ends the driver process, which by default closes the browser as well. 
No more commands can be sent to this driver instance. 

{{< tabpane langEqualsHeader=true >}}
    {{< tab header="Java" >}}
    driver.quit();
    {{< /tab >}}
    {{< tab header="Python" >}}
    driver.quit()
    {{< /tab >}}
    {{< tab header="CSharp" >}}
    driver.Quit();
    {{< /tab >}}
    {{< tab header="Ruby" >}}
    driver.quit
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
    await driver.quit();
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
    driver.quit()
    {{< /tab >}}
{{< /tabpane >}}

## Putting everything together

これらの8つを組み合わせて、使う必要のあるライブラリを含む完全なスクリプトにしましょう。

Follow the link at the bottom of the tab to see an example of the code as it would be executed
with a test runner instead of a standalone file.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://google.com");
        
        driver.getTitle(); // => "Google"

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        
        WebElement searchBox = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.name("btnK"));
        
        searchBox.sendKeys("Selenium");
        searchButton.click();
        
        searchBox = driver.findElement(By.name("q"));
        searchBox.getAttribute("value"); // => "Selenium"
        
        driver.quit();
    }
}
{{< /tab >}}
{{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_first_script.py" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By


driver = webdriver.Chrome()

driver.get("https://www.google.com")

driver.title # => "Google"

driver.implicitly_wait(0.5)

search_box = driver.find_element(By.NAME, "q")
search_button = driver.find_element(By.NAME, "btnK")

search_box.send_keys("Selenium")
search_button.click()

driver.find_element(By.NAME, "q").get_attribute("value") # => "Selenium"

driver.quit()

{{< /tab >}}
{{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/FirstScriptTest.cs" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

class HelloSelenium {
    static void Main() {
        var driver = new ChromeDriver();

        driver.Navigate().GoToUrl("https://www.google.com");

        driver.Title; // => "Google"

        driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

        var searchBox = driver.FindElement(By.Name("q"));
        var searchButton = driver.FindElement(By.Name("btnK"))

        searchBox.SendKeys("Selenium");
        searchButton.Click();

        driver.FindElement(By.Name("q")).GetAttribute("value"); // => "Selenium"

        driver.Quit();
    }
}
{{< /tab >}}
{{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/first_script_spec.rb" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

driver.get 'https://google.com'

driver.title # => 'Google'

driver.manage.timeouts.implicit_wait = 500

search_box = driver.find_element(name: 'q')
search_button = driver.find_element(name: 'btnK')

search_box.send_keys 'Selenium'
search_button.click

driver.find_element(name: 'q').attribute('value') # => "Selenium"

driver.quit
{{< /tab >}}
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/getting_started/firstScript.js">}}
  {{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()

    driver.get("https://google.com")

    driver.getTitle(); // => "Google"

    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

    val searchBox = driver.findElement(By.name("q"));
    val searchButton = driver.findElement(By.name("btnK"))

    searchBox.sendKeys("Selenium");
    searchButton.click();

    driver.findElement(By.name("q")).getAttribute("value"); // => "Selenium"

    driver.quit()
}
{{< /tab >}}
{{< /tabpane >}}

## Next Steps

Take what you've learned and build out your Selenium code. 

As you find more functionality that you need, read up on the rest of our
[WebDriver documentation]({{< ref "/documentation/webdriver/" >}}).
