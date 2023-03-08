---
title: "Web元素交互"
linkTitle: "交互"
weight: 2
description: >
   用于操纵表单的高级指令集.

---

仅有五种基本命令可用于元素的操作:
* [点击](https://w3c.github.io/webdriver/#element-click) (适用于任何元素)
* [发送键位](https://w3c.github.io/webdriver/#element-send-keys) (仅适用于文本字段和内容可编辑元素)
* [清除](https://w3c.github.io/webdriver/#element-send-keys) (仅适用于文本字段和内容可编辑元素)
* 提交 (仅适用于表单元素)
* 选择 (参见 [选择列表元素]({{< ref "select_lists.md" >}}))

## 附加验证

这些方法的设计目的是尽量模拟用户体验, 所以,
与 [Actions接口]({{< ref "/documentation/webdriver/actions_api/" >}}) 不同, 
在指定制定操作之前, 
会尝试执行两件事.
1. 如果它确定元素在视口之外, 
   [则会将元素滚动到视图中](https://w3c.github.io/webdriver/#dfn-scrolls-into-view), 
   特别是将元素底部与视口底部对齐.
2. 确保元素在执行操作之前是[可交互的](https://w3c.github.io/webdriver/#interactability) .
   这可能意味着滚动不成功, 
   或者该元素没有以其他方式显示.  
   确定某个元素是否显示在页面上太难了
   [无法直接在webdriver规范中定义](https://w3c.github.io/webdriver/#element-displayedness),
   因此Selenium发送一个带有JavaScript原子的执行命令, 
   检查是否有可能阻止该元素显示.
   如果确定某个元素不在视口中, 不显示, 不可
   [键盘交互](https://w3c.github.io/webdriver/#dfn-keyboard-interactable), 
   或不可
   [指针交互](https://w3c.github.io/webdriver/#dfn-pointer-interactable),
   则返回一个[元素不可交互](https://w3c.github.io/webdriver/#dfn-element-not-interactable) 错误.

## 点击

[元素点击命令](https://w3c.github.io/webdriver/#dfn-element-click) 执行在 [元素中央](https://w3c.github.io/webdriver/#dfn-center-point).
如果元素中央由于某些原因被 [遮挡](https://w3c.github.io/webdriver/#dfn-obscuring) ,
Selenium将返回一个 [元素点击中断](https://w3c.github.io/webdriver/#dfn-element-click-intercepted) 错误.

## 发送键位

[元素发送键位命令](https://w3c.github.io/webdriver/#dfn-element-send-keys)
将录入提供的键位到 [可编辑的](https://w3c.github.io/webdriver/#dfn-editable) 元素.
通常, 这意味着元素是具有 `文本` 类型的表单的输入元素或具有 `内容可编辑` 属性的元素.
如果不可编辑, 则返回
[无效元素状态](https://w3c.github.io/webdriver/#dfn-invalid-element-state) 错误.

[以下](https://www.w3.org/TR/webdriver/#keyboard-actions) 
是WebDriver支持的按键列表.

{{< tabpane >}}
{{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloSelenium {
public static void main(String[] args) {
WebDriver driver = new FirefoxDriver();
try {
// Navigate to Url
driver.get("https://google.com");

      // Enter text "q" and perform keyboard action "Enter"
      driver.findElement(By.name("q")).sendKeys("q" + Keys.ENTER);
    } finally {
      driver.quit();
    }
}
}

{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
driver = webdriver.Firefox()

    # Navigate to url
driver.get("http://www.google.com")

    # Enter "webdriver" text and perform "ENTER" keyboard action
driver.find_element(By.NAME, "q").send_keys("webdriver" + Keys.ENTER)
{{< /tab >}}
{{< tab header="CSharp" >}}
using (var driver = new FirefoxDriver())
{
// Navigate to Url
driver.Navigate().GoToUrl("https://google.com");

// Enter "webdriver" text and perform "ENTER" keyboard action
driver.FindElement(By.Name("q")).SendKeys("webdriver" + Keys.Enter);
}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
    # Navigate to URL
driver.get 'https://google.com'

    # Enter "webdriver" text and perform "ENTER" keyboard action
driver.find_element(name: 'q').send_keys 'webdriver', :return

ensure
driver.quit
end
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder, By, Key} = require('selenium-webdriver');

(async function example() {
let driver = await new Builder().forBrowser('firefox').build();

try {
// Navigate to Url
await driver.get('https://www.google.com');

    // Enter text "webdriver" and perform keyboard action "Enter"
    await driver.findElement(By.name('q')).sendKeys('webdriver', Key.ENTER);
}
finally {
await driver.quit();
}
})();
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.firefox.FirefoxDriver

fun main() {
val driver = FirefoxDriver()
try {
// Navigate to Url
driver.get("https://google.com")

    // Enter text "q" and perform keyboard action "Enter"
    driver.findElement(By.name("q")).sendKeys("q" + Keys.ENTER)
} finally {
driver.quit()
}
}
{{< /tab >}}
{{< /tabpane >}}

## 清除

[元素清除命令](https://w3c.github.io/webdriver/#dfn-element-clear) 
重置元素的内容.
这要求元素 [可编辑](https://w3c.github.io/webdriver/#dfn-editable),
且 [可重置](https://w3c.github.io/webdriver/#dfn-resettable-elements).
通常, 这意味着元素是具有 `文本` 类型的表单的输入元素或具有 `内容可编辑` 属性的元素.
如果不满足这些条件, 将返回
[无效元素状态](https://w3c.github.io/webdriver/#dfn-invalid-element-state) 错误.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class clear {
public static void main(String[] args) {
WebDriver driver = new ChromeDriver();
try {
// Navigate to Url
driver.get("https://www.google.com");
// Store 'SearchInput' element
WebElement searchInput = driver.findElement(By.name("q"));
searchInput.sendKeys("selenium");
// Clears the entered text
searchInput.clear();
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

    # Navigate to url
driver.get("http://www.google.com")
    # Store 'SearchInput' element
SearchInput = driver.find_element(By.NAME, "q")
SearchInput.send_keys("selenium")
    # Clears the entered text
SearchInput.clear()
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System;

namespace SnipetProjectDelete
{
class Program
{
static void Main(string[] args)
{
IWebDriver driver = new ChromeDriver();
try
{
// Navigate to Url
driver.Navigate().GoToUrl(@"https://www.google.com");
// Store 'SearchInput' element
IWebElement searchInput = driver.FindElement(By.Name("q"));
searchInput.SendKeys("selenium");
// Clears the entered text
searchInput.Clear();
}
finally
{
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
driver.get 'https://google.com'
    # store 'search_input' element
search_input = driver.find_element(name: 'q')
search_input.send_keys('selenium')
    # Clears the entered text
search_input.clear
ensure
driver.quit
end
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/elements/interactions.spec.js#L19-L20" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
fun main() {
val driver =  ChromeDriver()
try {
// Navigate to Url
driver.get("https://www.google.com")
// Store 'searchInput' element
val searchInput = driver.findElement(By.name("q"))
searchInput.sendKeys("selenium")
// Clears the entered text
searchInput.clear()
} finally {
driver.quit()
}
}
{{< /tab >}}
{{< /tabpane >}}

## 提交

在Selenium 4中, 
不再通过单独的端点以及脚本执行的方法来实现. 
因此, 建议不要使用此方法, 
而是单击相应的表单提交按钮. 


