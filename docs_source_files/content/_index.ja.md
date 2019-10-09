---
title: "Seleniumブラウザー自動化プロジェクト"
---

# Seleniumブラウザー自動化プロジェクト

Seleniumはブラウザー自動化を可能にし、それを支えるツール群とライブラリー群プロジェクトです。

ユーザーとブラウザーのやり取りのエミュレーション、ブラウザーの割当を増強したり縮減する分散型サーバー、そしてすべてのメジャーなブラウザー用に置換可能なコードの実装を可能にする[W3C WebDriver 仕様](//www.w3.org/TR/webdriver/)インフラの提供します。

このプロジェクトは多くの有志貢献者の何千時間に及ぶ個々の時間を費やした事とソースコード[自由に利用可能](attr.md#license)を誰にでも利用、楽しめ、そして改良できることによって実現しました。

Seleniumはウェブプラットフォームの自動化のより開かれた議論をするためブラウザーベンダー、エンジニア、愛好家をまとめます。このプロジェクトはコミュニティーを導きと育成のために[年次カンファレンス](//seleniumconf.com/)開催します。

Seleniumの中核は[_WebDriver_]({{< ref "/webdriver/_index.md" >}})であり、様々なブラウザーを変えてインストラクション集を実行できるインターフェースです。これは作りえる一番基本的な
インストラクションの一つです:

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

#この例にはSelenium WebDriver 3.13 または新しいのが必要です
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
            IWebElement firstResult = wait.Until(ExpectedConditions.ElementExists(By.CssSelector("h3>a")));
            Console.WriteLine(firstResult.Text);
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
  first_result = wait.until { driver.find_element(css: 'h3>a') }
  puts first_result.text
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, By, Key, until} = require('selenium-webdriver');

(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    try {
        await driver.get('https://www.google.com/ncr');
        await driver.findElement(By.name('q')).sendKeys('cheese', Key.RETURN);
        let firstResult = await driver.wait(until.elementLocated(By.css('h3>a')),10000);
        console.log(await firstResult.getText());
    } finally {
        await driver.quit();
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


上記のコードを実行した時、舞台裏で何が起きているかの詳しい説明には[_クイックツアー_]({{< ref "/getting_started/quick.ja.md" >}})を参照してください。
どのように[インストール]({{< ref "/selenium_installation/_index.md" >}})するのか、そしてSeleniumをテスト自動化ツールとして上手く利用し、上記の様な基本的なテストを大きいスケールの分散型環境で複数のブラウザー、様々オペレーティングシステムで実行するため拡張方法を理解するため[順序順ドキュメンテーション]({{< ref "/introduction/_index.md" >}})に進んでください。

## 入門

もしSeleniumが初めてでしたら、素早く知識を得るためのリソースを用意しました。

* [クイックツアー]({{< ref "/getting_started/quick.ja.md" >}})
  * [WebDriver]({{< ref "/getting_started/quick.ja.md#webdriver" >}})
  * [Remote Control]({{< ref "/getting_started/quick.ja.md#remote-control" >}})
  * [IDE]({{< ref "/getting_started/quick.ja.md#ide" >}})
  * [Grid]({{< ref "/getting_started/quick.ja.md#grid" >}})
  * [HTML Runner]({{< ref "/getting_started/quick.ja.md#html-runner" >}})
