---
title: "Das Selenium Browser Automations Projekt"
---


# Das Selenium Browser Automations Projekt

Selenium ist ein Sammlung von Projekten für einige Werkzeuge und Programmbibliotheken
die es ermöglichen Webbrowser zu automatisieren.

Selenium bietet Erweiterungen um Benutzereingaben in Browsern zu simulieren,
einen Server um eine vielzahl von Browsern anzusprechen und eine Infrastruktur
um die [W3C WebDriver Spezifikation](//www.w3.org/TR/webdriver/) welche 
es ermöglicht die meistgenutzen Browser mit dem gleichen Programmcode
zu steuern.

Das Projekt wird durch viele Freiwillige Unterstützer möglich gemacht, sie haben
tausende von Stunden ihrer Freizeit investiert und haben den Programmcode für 
jeden [öffentlich zugänglich gemacht]({{< ref "/front_matter/copyright_and_attributions.de.md#license" >}})
für jeden um diesen zu verwenden und ihn zu verbessern.

Selenium fördert Disskussionen in Bezug Automation der Webplattform mit Browserhersteller, 
Ingeneure und Enthusiasten. Jedes Jahr wird vom Projekt eine [Konferenz](//seleniumconf.com/) 
organisiert um Wissen zu vermitteln und die Community zu fördern.


Das Herzstück von Selenium ist der _[WebDriver]({{< ref "/webdriver/_index.md" >}})_. Es 
handelt sich um ein Interface um das es ermöglicht Befehle für eine Vielzahl von Browsern
auszuführen. Hier ein einfaches Beispiel welche Möglichkeiten geboten werden: 


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


Sie Dir die _[Kurzeinführung]({{< ref "/getting_started/quick.de.md" >}})_ an um detailierte
Informationen zu erhalten was im Hintergrund passiert wenn Du diesen Code ausführst.
Du solltest mit der [Einführung]({{< ref "/introduction/_index.md" >}}) fortsetzen
um die Installation zu verstehen und um Selenium erfolgreich einzusetzen, Testsets in größeren
Umfang einzusetzen auf vielen verschiedenen Browsern und unterschiedlichen Betriebssystemen.


## Erste Schritte

Wenn Selenium für Dich Neuland ist, haben wir hier ein paar Quellen die Dir weiterhelfen
möglichst schnell beginnen zu können.


* [Kurze Einführung]({{< ref "/getting_started/quick.de.md" >}})
  * [WebDriver]({{< ref "/getting_started/quick.de.md#webdriver" >}})
  * [IDE]({{< ref "/getting_started/quick.de.md#ide" >}})
  * [Grid]({{< ref "/getting_started/quick.de.md#grid" >}})

