---
title: "Selenium 브라우저 자동화 프로젝트"
---

# Selenium 브라우저 자동화 프로젝트

Selenium은 웹 브라우저의 자동화를 가능하게 하고 지원하는 다양한 도구와 라이브러리를 포함한 프로젝트입니다.

브라우저와의 사용자 간의 상호 작용을 테스트하는 확장 기능, 브라우저 할당 확장을 위한 배포 서버, 모든 주요 웹 브라우저에 적용 가능한 코드를 작성할 수 있는 
[W3C WebDriver 사양](//www.w3.org/TR/webdriver/)
구현을 위한 인프라를 제공합니다.

이 프로젝트는 자신의 수천 시간을 투자해준 기여자들이, [누구나 자유롭게]({{< ref "/front_matter/copyright_and_attributions.ko.md#license" >}}) 사용하고, 즐기고, 개선할 수 있도록 소스 코드를 제공해주었기에 가능했습니다.

Selenium은 브라우저 공급 업체, 엔지니어, 매니아를 모아 웹 플랫폼 자동화에 대한 공개 토론을 진행합니다. 이 프로젝트 [매년 컨퍼런스](//seleniumconf.com/)를 개최하여 커뮤니티를 육성하고 가르침을 전합니다.

Selenium의 핵심은 _[WebDriver]({{< ref "/webdriver/_index.md" >}})_ 입니다. 
이는 다양한 브라우저에서 호환 가능한 지시사항을 작성할 수 있는 인터페이스라 할 수 있습니다. 다음은 가장 간단한 지시사항 예시입니다:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import java.time.Duration;

public class HelloSelenium {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
    driver.find_element(By.NAME, "q").send_keys("cheese" + Keys.RETURN)
    first_result = wait.until(presence_of_element_located((By.CSS_SELECTOR, "h3>div")))
    print(first_result.get_attribute("textContent"))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Support.UI;

class HelloSelenium {
  static void Main() {
    using(IWebDriver driver = new FirefoxDriver()) {
      WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
      driver.Navigate().GoToUrl("https://www.google.com/ncr");
      driver.FindElement(By.Name("q")).SendKeys("cheese" + Keys.Enter);
      wait.Until(webDriver => webDriver.FindElement(By.CssSelector("h3>div")).Displayed);
      IWebElement firstResult = driver.FindElement(By.CssSelector("h3>div"));
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
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

fun main() {
    val driver = FirefoxDriver()
    val wait = WebDriverWait(driver, Duration.ofSeconds(10))
    try {
        driver.get("https://google.com/ncr")
        driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER)
        val firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3>div")))
        println(firstResult.getAttribute("textContent"))
    } finally {
        driver.quit()
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}


이 코드를 실행할 때의 동작 원리에 대한 전체적인 설명은 _[퀵 투어]({{< ref "/getting_started/quick.ko.md" >}})_ 를 참고하십시오.
Selenium 설치 방법과 자동화 툴로써 활용하는 방안에 대해 더 알아보고 싶다면 [narrative documentation]({{< ref "/introduction/_index.md" >}}) 를 참고하십시오. 이를 통해 Selenium을 테스트 자동화 툴로써 성공적으로 활용하여, 간단한 테스트를 제작하고 다양한 브라우저와 운영체제 환경에서 실행할 수 있을 것입니다.

## 시작하기

Selenium을 처음 사용해보신다면,
빠른 이해를 도울 수 있는 몇 가지 문서가 있습니다.

* [퀵 투어]({{< ref "/getting_started/quick.ko.md" >}})
  * [WebDriver]({{< ref "/getting_started/quick.ko.md#webdriver" >}})
  * [IDE]({{< ref "/getting_started/quick.ko.md#ide" >}})
  * [Grid]({{< ref "/getting_started/quick.ko.md#grid" >}})

