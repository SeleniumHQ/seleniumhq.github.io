---
title: "Selenium 浏览器自动化项目"
---

# Selenium 浏览器自动化项目

Selenium 是支持 web 浏览器自动化的一系列工具和库的综合项目。

它提供了扩展来模拟用户与浏览器的交互，用于扩展浏览器分配的分发服务器，以及用于实现 [W3C WebDriver 规范](//www.w3.org/TR/webdriver/) 的基础结构，该 规范 允许您为所有主要 Web 浏览器编写可互换的代码。

这个项目是由志愿者贡献者实现的，他们投入了自己数千小时的时间，并使源代码[免费提供](attr.md#license)给任何人使用、享受和改进。

Selenium 汇集了浏览器供应商，工程师和爱好者，以进一步围绕 Web 平台自动化进行公开讨论。
该项目组织了[一次年度会议](//seleniumconf.com/)，以教学和培养社区。

Selenium 的核心是 [_WebDriver_]({{< ref "/webdriver/_index.md" >}})，这是一个编写指令集的接口，可以在许多浏览器中互换运行。
这里有一个最简单的说明：

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class HelloSelenium {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            driver.get("https://google.com/ncr");
            driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
            WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3>div")));
            System.out.println(firstResult.getAttribute("textContent"));
        } finally {
            driver.quit();
        }
    }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support.expected_conditions import presence_of_element_located

#This example requires Selenium WebDriver 3.13 or newer
with webdriver.Firefox() as driver:
    wait = WebDriverWait(driver, 10)
    driver.get("https://google.com/ncr")
    driver.find_element_by_name("q").send_keys("cheese" + Keys.RETURN)
    first_result = wait.until(presence_of_element_located((By.CSS_SELECTOR, "h3>div")))
    print(first_result.get_attribute("textContent"))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Support.UI;
using SeleniumExtras.WaitHelpers;

class HelloSelenium
{
    static void Main()
    {
        using (IWebDriver driver = new FirefoxDriver())
        {
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
            driver.Navigate().GoToUrl("https://www.google.com/ncr");
            driver.FindElement(By.Name("q")).SendKeys("cheese" + Keys.Enter);
            IWebElement firstResult = wait.Until(ExpectedConditions.ElementExists(By.CssSelector("h3>div")));
            Console.WriteLine(firstResult.GetAttribute("textContent"));
        }
    }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :firefox
wait = Selenium::WebDriver::Wait.new(timeout: 10)

begin
  driver.get 'https://google.com/ncr'
  driver.find_element(name: 'q').send_keys 'cheese', :return
  first_result = wait.until { driver.find_element(css: 'h3>div') }
  puts first_result.attribute('textContent')
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, By, Key, until} = require('selenium-webdriver');

(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');

        // Enter text "cheese" and perform keyboard action "Enter"
        await driver.findElement(By.name('q')).sendKeys('cheese', Key.ENTER);

        let firstResult = await driver.wait(until.elementLocated(By.css('h3>div')), 10000);

        console.log(await firstResult.getAttribute('textContent'));
    }
    finally{
        driver.quit();
    }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.WebDriverWait
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated

class HelloSelenium {

    public main(args: Array<String>) {
        val driver = new FirefoxDriver()
        val wait = new WebDriverWait(driver, 10)
        try {
            driver.get("https://google.com/ncr")
            driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER)
            val firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3>div")))
            System.out.println(firstResult.getAttribute("textContent"))
        } finally {
            driver.quit()
        }
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}

请参阅 _[快速浏览]({{< ref "/getting_started/quick.zh-cn.md" >}})_ 以获得运行此代码时幕后发生的事情的完整解释。
您应该继续阅读[介绍]({{< ref "/introduction/_index.md" >}})，了解如何[安装]({{< ref "/selenium_installation/_index.md" >}})并成功地使用 Selenium 作为测试自动化工具，并将这样的简单测试扩展到多个浏览器上的大型分布式环境中，在多个不同的操作系统上运行。

## 入门指南

如果您刚刚接触 Selenium，我们有一些资源可以帮助您快速上手。

* [快速浏览]({{< ref "/getting_started/quick.zh-cn.md" >}})
  * [WebDriver]({{< ref "/getting_started/quick.zh-cn.md#webdriver" >}})
  * [Remote Control]({{< ref "/getting_started/quick.zh-cn.md#remote-control" >}})
  * [IDE]({{< ref "/getting_started/quick.zh-cn.md#ide" >}})
  * [Grid]({{< ref "/getting_started/quick.zh-cn.md#grid" >}})
  * [HTML Runner]({{< ref "/getting_started/quick.zh-cn.md#html-runner" >}})
