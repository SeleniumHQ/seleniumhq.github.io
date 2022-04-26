---
title: "编写第一个Selenium脚本"
linkTitle: "第一个脚本"
weight: 8
description: >
    逐步构建一个Selenium脚本的说明
---

当你完成 [Selenium安装]({{< ref "install_library.md" >}}) and
[驱动安装]({{< ref "install_drivers.md" >}}) 后, 便可以开始书写Selenium脚本了.

## 八个基本组成部分

Selenium所做的一切, 
就是发送给浏览器命令,
用以执行某些操作或为信息发送请求.
您将使用Selenium执行的大部分操作,
都是以下基本命令的组合:

### 1. 使用驱动实例开启会话

有关启动会话的更多详细信息, 
请阅读我们关于[打开和关闭浏览器]({{< ref "open_browser.md" >}})的文档

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

### 2. 在浏览器上执行操作

在本例中, 我们
[导航]({{< ref "/documentation/webdriver/browser/navigation.md" >}}) 
到一个网页. 

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

### 3. 请求 [浏览器信息]({{< ref "/documentation/webdriver/browser" >}})

您可以请求一系列关于[浏览器的信息]({{< ref "/documentation/webdriver/browser" >}}) , 
包括窗口句柄、浏览器尺寸/位置、cookie、警报等.

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

### 4. 建立等待策略

将代码与浏览器的当前状态同步
是Selenium面临的最大挑战之一, 
做好它是一个高级主题. 

基本上, 您希望在尝试定位元素之前, 
确保该元素位于页面上, 
并且在尝试与该元素交互之前, 
该元素处于可交互状态.

隐式等待很少是最好的解决方案, 
但在这里最容易演示, 
所以我们将使用它作为占位符. 

阅读更多关于[等待策略]({{< ref "/documentation/webdriver/waits.md" >}})
的信息. 

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

### 5. 发送命令 [查找元素]({{< ref "/documentation/webdriver/elements" >}})
大多数Selenium会话中的主要命令都与元素相关, 
如果不先[找到元素]({{< ref "/documentation/webdriver/elements" >}}), 
就无法与之交互.

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

### 6. 操作元素
对于一个元素, 
只有少数几个[操作]({{< ref "/documentation/webdriver/elements/interactions.md" >}})可以执行, 
但您将经常使用它们. 

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

### 7. 获取元素信息
元素存储了很多[被请求的信息]({{< ref "/documentation/webdriver/elements/information" >}}). 
请注意, 我们需要重新定位搜索框, 
因为自从我们第一次找到它以来, 
DOM已经发生了变化. 

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

### 8. 结束会话 

这将结束驱动程序进程, 
默认情况下, 该进程也会关闭浏览器. 
无法向此驱动程序实例发送更多命令. 

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

## 组合所有事情

让我们将这8个部分组合成一个完整的脚本, 
包括需要使用的库:

按照选项卡底部的链接查看代码示例, 
因为它将使用测试运行程序而不是独立文件执行.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
    public static void main(String[] args) {
        driver = new ChromeDriver();

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

## 接下来的步骤

利用你所学的知识, 
构建你的Selenium代码. 

当您发现需要更多功能时, 
请阅读我们的[WebDriver文档]({{< ref "/documentation/webdriver/" >}})的其余部分. 
