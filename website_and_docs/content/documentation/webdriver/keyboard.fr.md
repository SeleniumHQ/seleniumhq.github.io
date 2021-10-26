---
title: "Clavier"
linkTitle: "Clavier"
weight: 10
aliases: ["/documentation/fr/webdriver/keyboard/"]
---

Le clavier représente un événement KeyBoard. Les actions du clavier sont effectuées à l'aide de bas niveau
interface qui nous permet de fournir une entrée d'appareil virtualisé au navigateur Web.

## sendKeys

SendKeys tape une séquence de touches dans l'élément DOM même 
si une séquence de touches de modification est rencontrée.
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

## keyDown

Le keyDown est utilisé pour simuler l'action d'appuyer sur une touche de modification (CONTROL, SHIFT, ALT)

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
  {{< tab header="JavaScript" >}}
const {Builder, By, Key} = require('selenium-webdriver');

(async function example() {
  let driver = await new Builder().forBrowser('chrome').build();

  try {
    // Navigate to Url
    await driver.get('https://www.google.com');

    // Enter text "webdriver" and perform keyboard action "Enter"
    await driver.findElement(By.name('q')).sendKeys('webdriver', Key.ENTER);

    // Perform action ctrl + A (modifier CONTROL + Alphabet A) to select the page
    await driver.actions().keyDown(Key.CONTROL).sendKeys('a').perform();
  }
  finally {
    await driver.quit();
  }
})();
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

## keyUp

Le keyUp est utilisé pour simuler l'action de relâchement (ou) de relâchement de touche d'une touche de modification (CONTROL, SHIFT, ALT)

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
  {{< tab header="JavaScript" >}}
const {Builder, By, Key} = require('selenium-webdriver');
(async function example() {
  let driver = await new Builder().forBrowser('firefox').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');

    // Store google search box WebElement
    let search = driver.findElement(By.name('q'));

    // Enters text "qwerty" with keyDown SHIFT key and after keyUp SHIFT key (QWERTYqwerty)
    await driver.actions().click(search).keyDown(Key.SHIFT).sendKeys("qwerty").keyUp(Key.SHIFT).sendKeys("qwerty").perform();
  }
  finally {
    await driver.quit();
  }
})();
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

## clear

Efface le contenu d'un élément modifiable.
Ceci n'est appliqué qu'aux éléments modifiables et interactifs,
sinon le sélénium renvoie l'erreur (état d'élément invalide (ou) élément non interactif)

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
