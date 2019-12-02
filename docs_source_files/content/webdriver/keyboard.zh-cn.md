---
title: "Keyboard"
weight: 10
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Chinese. Do you speak Chinese? Help us to translate
it by sending us pull requests!
{{% /notice %}}

Keyboard represents a KeyBoard event. KeyBoard actions are performed by using low-level
interface which allows us to provide virtualized device input to the web browser.

## sendKeys

The sendKeys types a key sequence in DOM element even if modifier key sequence is encountered.

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
driver = webdriver.Firefox()

# Navigate to url
driver.get("http://www.google.com")

# Enter "webdriver" text and perform "ENTER" keyboard action
driver.find_element_by_name("q").send_keys("webdriver"+Keys.ENTER)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using (var driver = new FirefoxDriver())
{
    // Navigate to Url
    driver.Navigate().GoToUrl("https://google.com");

    // Enter "webdriver" text and perform "ENTER" keyboard action
    driver.FindElement(By.Name("q")).SendKeys("webdriver" + Keys.Enter);
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.ChromeDriver

class HelloSelenium {

    fun main() {
        driver = ChromeDriver()
        try {
            // Navigate to Url
            driver.get("https://google.com")

            // Enter text "q" and perform keyboard action "Enter"
            driver.findElement(By.name("q")).sendKeys("q" + Keys.ENTER)
        } finally {
            driver.quit()
        }
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}

## keyDown

The keyDown is used to simulate action of pressing a modifier key(CONTROL, SHIFT, ALT)

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.google.com")

# Enter "webdriver" text and perform "ENTER" keyboard action
driver.find_element_by_name("q").send_keys("webdriver"+Keys.ENTER)

# Perform action ctrl + A (modifier CONTROL + Alphabet A) to select the page
webdriver.ActionChains(driver).key_down(Keys.CONTROL).send_keys("a").perform()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
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
        action.keyDown(Keys.CONTROL).sendKeys("a").build().perform();
    } finally {
        driver.quit()
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}

## keyUp

The keyUp is used to simulate key-up (or) key-release action of a modifier key(CONTROL, SHIFT, ALT)

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.google.com")

# Store google search box WebElement
search = driver.find_element_by_name("q")

action = webdriver.ActionChains(driver)

# Enters text "qwerty" with keyDown SHIFT key and after keyUp SHIFT key (QWERTYqwerty)
action.key_down(Keys.SHIFT).send_keys_to_element(search, "qwerty").key_up(Keys.SHIFT).send_keys("qwerty").perform()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
    // Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
    # Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, By, Key} = require('selenium-webdriver');
(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    try {
        // Navigate to Url
        await driver.get('https://www.google.com');

        // Store google search box WebElement
        let search = await driver.findElement(By.name('q'));

        // Enters text "qwerty" with keyDown SHIFT key and after keyUp SHIFT key (QWERTYqwerty)
        await driver.actions().click(search).keyDown(Key.SHIFT).sendKeys("qwerty").keyUp(Key.SHIFT).sendKeys("qwerty").perform();
    }
    finally {
        await driver.quit();
    }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
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
        action.keyDown(Keys.SHIFT).sendKeys(search, "qwerty").keyUp(Keys.SHIFT).sendKeys("qwerty").build().perform();
    } finally {
        driver.quit()
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}