---
title: "Acting on Elements"
linkTitle: "Actions"
weight: 2
aliases: ["/documentation/en/webdriver/keyboard/"]
---

There are only 4 basic commands that can be executed on an action:
* click
* send keys (only applies to text fields)
* clear (only applies to text fields)
* submit (only applies to forms)

## Click

## Clear

## Send Keys

The sendKeys types a key sequence in DOM element even if modifier key sequence is encountered.
[Here](https://www.w3.org/TR/webdriver/#keyboard-actions) are the list of 
possible keystrokes that WebDriver Supports.

{{< tabpane langEqualsHeader=true >}}
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

## Clear
Clears the content of an editable element. 
This is only applied for the elements which is editable and interactable, 
otherwise Selenium returns the error (invalid element state (or) Element not interactable)

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
const {Builder, By} = require('selenium-webdriver');
(async function example() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'SearchInput' element
    let searchInput = driver.findElement(By.name('q'));
    await searchInput.sendKeys("selenium");
    // Clears the entered text
    await searchInput.clear();
  }
  finally {
    await driver.quit();
  }
})();
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
