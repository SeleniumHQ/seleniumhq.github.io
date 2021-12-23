---
title: "最初のSeleniumスクリプトを書く"
linkTitle: "最初のスクリプト"
weight: 3
description: >
    Seleniumスクリプトを作成するための段階的な説明
---

[Seleniumをインストール]({{< ref "install_selenium_library.md" >}})し、
[ドライバーをインストール]({{< ref "install_drivers.md" >}})すると、Seleniumコードを書く準備が整います。

Seleniumが行うことはすべて、ブラウザコマンドを送信して、何かを実行したり、情報の要求を送信したりすることです。 
Seleniumで行うことのほとんどは、次の基本的なコマンドの組み合わせです。

1. ドライバーインスタンスでセッションを開始します

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

2. ブラウザが[ナビゲート]({{< ref "/documentation/webdriver/browser/navigation.md" >}})するコマンドを送信します

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

3. [ブラウザに関する情報]({{< ref "/documentation/webdriver/browser" >}})をリクエストします

    {{< tabpane langEqualsHeader=true >}}
    {{< tab header="Java" >}}
    driver.getTitle(); // => "Google"
    {{< /tab >}}
    {{< tab header="Python" >}}
    driver.title() # => "Google"
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

4. [要素を検索する]({{< ref "/documentation/webdriver/elements" >}})ためのコマンドを送信します

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

5. [要素に対してアクションを実行する]()コマンドを送信します

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
    searchBox.sendKeys("Selenium");
    searchButton.click();
    {{< /tab >}}
    {{< /tabpane >}}

6. [要素に関する情報]()をリクエストします

    {{< tabpane langEqualsHeader=true >}}
    {{< tab header="Java" >}}
    driver.findElement(By.name("q")).getAttribute("value"); // => "Selenium"
    {{< /tab >}}
    {{< tab header="Python" >}}
    driver.find_element(By.NAME, "q").getAttribute() # => "Selenium"
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

7. セッションを終了します 

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

これらの7つを組み合わせて、使う必要のあるライブラリを含む完全なスクリプトにしましょう。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://google.com");
        
        driver.getTitle(); // => "Google"

        WebElement searchBox = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.name("btnK"))
        
        searchBox.sendKeys("Selenium");
        searchButton.click();

        driver.findElement(By.name("q")).getAttribute("value"); // => "Selenium"

        driver.quit();
    }
}
{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By


driver = webdriver.Chrome()

driver.get("http://www.google.com")

driver.title() # => "Google"

search_box = driver.find_element(By.NAME, "q")
search_button = driver.find_element(By.NAME, "btnK")

search_box.send_keys("Selenium")
search_button.click()

driver.find_element(By.NAME, "q").getAttribute() # => "Selenium"

driver.quit()

{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

class HelloSelenium {
    static void Main() {
        var driver = new ChromeDriver();

        driver.Navigate().GoToUrl("https://www.google.com");

        driver.Title; // => "Google"

        var searchBox = driver.FindElement(By.Name("q"));
        var searchButton = driver.FindElement(By.Name("btnK"))

        searchBox.SendKeys("Selenium");
        searchButton.Click();

        driver.FindElement(By.Name("q")).GetAttribute("value"); // => "Selenium"

        driver.Quit();
    }
}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

driver.get 'https://google.com'

driver.title # => 'Google'

search_box = driver.find_element(name: 'q')
search_button = driver.find_element(name: 'btnK')

search_box.send_keys 'Selenium'
search_button.click

driver.find_element(name: 'q').attribute('value') # => "Selenium"

driver.quit
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder, By, Key, until} = require('selenium-webdriver');

(async function helloSelenium() {
    let driver = await new Builder().forBrowser('chrome').build();

    await driver.get('https://www.google.com');

    await driver.getTitle(); // => "Google"

    let searchBox = await driver.findElement(By.name('q'));
    let searchButton = await driver.findElement(By.name('btnK'));

    await searchBox.sendKeys('Selenium');
    await searchButton.click();

    await driver.findElement(By.name('q')).getAttribute("value"); // => 'Selenium'

    await driver.quit();
})();
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()

    driver.get("https://google.com")

    driver.getTitle(); // => "Google"

    val searchBox = driver.findElement(By.name("q"));
    val searchButton = driver.findElement(By.name("btnK"))

    searchBox.sendKeys("Selenium");
    searchButton.click();

    driver.findElement(By.name("q")).getAttribute("value"); // => "Selenium"

    driver.quit()
}
{{< /tab >}}
{{< /tabpane >}}
