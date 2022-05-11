---
title: "Teclado"
linkTitle: "Teclado"
weight: 2
needsTranslation: true
description: >
  A representation of any key input device for interacting with a web page.
aliases: [
"/documentation/pt-br/webdriver/keyboard/",
"/pt-br/documentation/webdriver/keyboard/"
]
---

Keyboard representa um evento do teclado. As ações do teclado são
executadas usando uma interface de baixo nível que nos permite fornecer
entrada de dispositivo virtualizado para o navegador da web.

## Keys

In addition to the keys represented by regular unicode,
unicode values have been assigned to other keyboard keys for use with Selenium.
Each language has its own way to reference these keys; the full list can be found
[here](https://www.w3.org/TR/webdriver/#keyboard-actions).

## Key down

O keyDown é usado para simular a ação de pressionar uma tecla modificadora (CONTROL, SHIFT, ALT)

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebDriver driver = new ChromeDriver();
try {
  // Navigate to Url
  driver.get("https://google.com");

  // Enter "webdriver" text and perform "ENTER" keyboard action
  driver.findElement(By.name("q")).sendKeys("webdriver" + Keys.ENTER);

  Actions actionProvider = new Actions(driver);
  Action keydown = actionProvider.keyDown(Keys.CONTROL).sendKeys("a").build();
  keydown.perform();
} finally {
  driver.quit();
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
driver = webdriver.Chrome()

    # Navigate to url
driver.get("http://www.google.com")

    # Enter "webdriver" text and perform "ENTER" keyboard action
driver.find_element(By.NAME, "q").send_keys("webdriver" + Keys.ENTER)

    # Perform action ctrl + A (modifier CONTROL + Alphabet A) to select the page
webdriver.ActionChains(driver).key_down(Keys.CONTROL).send_keys("a").perform()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebDriver driver = new ChromeDriver();
try
{
  // Navigate to Url
  driver.Navigate().GoToUrl("https://google.com");

  // Enter "webdriver" text and perform "ENTER" keyboard action
  driver.FindElement(By.Name("q")).SendKeys("webdriver" + Keys.Enter);

  // Perform action ctrl + A (modifier CONTROL + Alphabet A) to select the page
  Actions actionProvider = new Actions(driver);
  IAction keydown = actionProvider.KeyDown(Keys.Control).SendKeys("a").Build();
  keydown.Perform();
}
finally
{
  driver.Quit();
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome
begin
    # Navigate to URL
  driver.get 'https://google.com'

    # Enter "webdriver" text and perform "ENTER" keyboard action
  driver.find_element(name: 'q').send_keys 'webdriver', :return

    # Perform action ctrl + A (modifier CONTROL + Alphabet A) to select the page
  driver.action.key_down(:control).send_keys('a').perform

ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/actionsApi/keyboard/keyDown.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
  val driver = ChromeDriver()
  try {
    // Navigate to Url
    driver.get("https://google.com")

    // Enter "webdriver" text and perform "ENTER" keyboard action
    driver.findElement(By.name("q")).sendKeys("webdriver" + Keys.ENTER)
    val action = Actions(driver)

    // Perform action ctrl + A (modifier CONTROL + Alphabet A) to select the page
    action.keyDown(Keys.CONTROL).sendKeys("a").build().perform()
  } finally {
    driver.quit()
  }
}
  {{< /tab >}}
{{< /tabpane >}}

## Key up

O keyUp é usado para simular a liberação de uma tecla (ou) de uma tecla modificadora (CONTROL, SHIFT, ALT)

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class HelloSelenium {
  public static void main(String[] args) {
    WebDriver driver = new FirefoxDriver();
    try {
      // Navigate to Url
      driver.get("https://google.com");
      Actions action = new Actions(driver);

      // Store google search box WebElement
      WebElement search = driver.findElement(By.name("q"));

      // Enters text "qwerty" with keyDown SHIFT key and after keyUp SHIFT key (QWERTYqwerty)
      action.keyDown(Keys.SHIFT).sendKeys(search,"qwerty").keyUp(Keys.SHIFT).sendKeys("qwerty").perform();
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
driver = webdriver.Chrome()

    # Navigate to url
driver.get("http://www.google.com")

    # Store google search box WebElement
search = driver.find_element(By.NAME, "q")

action = webdriver.ActionChains(driver)

    # Enters text "qwerty" with keyDown SHIFT key and after keyUp SHIFT key (QWERTYqwerty)
action.key_down(Keys.SHIFT).send_keys_to_element(search, "qwerty").key_up(Keys.SHIFT).send_keys("qwerty").perform()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Interactions;

namespace HelloSelenium
{
  class HelloSelenium
  {
    public static void Main(string[] args)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navigate to Url
        driver.Navigate().GoToUrl("https://google.com");

        Actions action = new Actions(driver);
        // Store google search box WebElement
        IWebElement search = driver.FindElement(By.Name("q"));

        // Enters text "qwerty" with keyDown SHIFT key and after keyUp SHIFT key (QWERTYqwerty)
        action.KeyDown(Keys.Shift).SendKeys(search, "qwerty").KeyUp(Keys.Shift).SendKeys("qwerty").Perform();

      }
      finally {
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

    # Store google search box WebElement
  search = driver.find_element(name: 'q')

    # Enters text "qwerty" with keyDown SHIFT key and after keyUp SHIFT key (QWERTYqwerty)
  driver.action.key_down(:shift).send_keys(search,'qwerty').key_up(:shift).send_keys("qwerty").perform

ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/actionsApi/keyboard/keyUp.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
  val driver = ChromeDriver()
  try {
    // Navigate to Url
    driver.get("https://google.com")

    // Store google search box WebElement
    val search = driver.findElement(By.name("q"))
    val action = Actions(driver)

    // Enters text "qwerty" with keyDown SHIFT key and after keyUp SHIFT key (QWERTYqwerty)
    action.keyDown(Keys.SHIFT).sendKeys(search, "qwerty").keyUp(Keys.SHIFT).sendKeys("qwerty").build().perform()
  } finally {
    driver.quit()
  }
}
  {{< /tab >}}
{{< /tabpane >}}

## Send keys

This is a convenience method in the Actions API that combines keyDown and keyUp commands in one action.
Executing this command differs slightly from using the element method.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
WebDriver driver = new FirefoxDriver();
try {

// Navigate to the url
driver.get("https://google.com");

// Create an object of Action class
Actions action = new Actions(driver);

// Find google search box element
WebElement search = driver.findElement(By.name("q"));

// Send value by action class to the search box
action.sendKeys(search, "Selenium").perform();

// Perform Keyboard action by Action class
action.sendKeys(Keys.ENTER).perform();

} finally {
driver.quit();
}

{{< /tab >}}
{{< tab header="Python" >}}

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
driver.get("https://www.selenium.dev/selenium/docs/api/py/genindex.html")

search = driver.find_element(By.NAME, "q")

action = webdriver.ActionChains(driver)
action.move_to_element(search).click().send_keys("send_keys", Keys.ENTER).perform()

{{< /tab >}}
{{< tab header="CSharp" >}}

# Help us with a PR for code sample

{{< /tab >}}
{{< tab header="Ruby" >}}

# Help us with a PR for code sample

{{< /tab >}}
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/actionsApi/keyboard/sendKeysAction.js">}}
  {{< /tab >}}
{{< tab header="Kotlin" >}}

# Help us with a PR for code sample

{{< /tab >}}
{{< /tabpane >}}

{{< alert-code >}}
for sendKeys by Action
{{< /alert-code >}}
