---
title: "滚轮操作"
linkTitle: "滚轮"
weight: 6
needsTranslation: true
description: >
    用于与网页交互的滚轮输入设备的呈现.
---

滚轮操作将要伴随 Selenium 4.2 发布

## Scroll to element 

Scrolls to the element by scrolling the viewport. This way the element is at the bottom.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;

public class scrollToElement {

  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html");
      WebElement element = driver.findElement(By.id("closepopup"));

      Actions actions = new Actions(driver);
      actions.scroll(0,
                     0,
                     0,
                     0,
                     PointerInput.Origin.fromElement(element))
        .perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()
driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html")
element = driver.find_element(By.ID, "closepopup")

ActionChains(driver).scroll(0, 0, 0, 0, origin=element).perform()

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'

begin
  driver = Selenium::WebDriver.for :chrome
  driver.get 'https://crossbrowsertesting.github.io/selenium_example_page.html'
  element = driver.find_element(:id, 'closepopup')
  driver.action.scroll_to(element).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder, By } = require('selenium-webdriver');

(async function scrollToElement() {
  let driver = await new Builder().forBrowser('chrome').build();

  try {
    await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');
    element = await driver.findElement(By.id('closepopup'));
    await driver.actions().scroll(0, 0, 0, 0, element).perform();
  }
  finally {
    await driver.quit();
  }
})();

  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.PointerInput

fun main() {
    val driver: WebDriver = ChromeDriver()
    try {
        driver["https://crossbrowsertesting.github.io/selenium_example_page.html"]
        val element = driver.findElement(By.id("closepopup"))
        val actions = Actions(driver)
        actions.scroll(
            0,
            0,
            0,
            0,
            PointerInput.Origin.fromElement(element)
        ).perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## Scroll by given amount from element 

Scrolls to the element by scrolling the viewport. This way the element is at the bottom. 
Scrolls the viewport further by the given amount i.e. horizontal and vertical offsets.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;

public class scrollByGivenAmountFromElement {

  public static void main(String[] args) throws Exception {
    WebDriver driver = new ChromeDriver();
    try {
      driver.manage().window().setSize(new Dimension(500, 400));
      driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html");
      WebElement element = driver.findElement(By.linkText("Go To Page 2"));

      Actions actions = new Actions(driver);
      actions.scroll(0,
                     0,
                     0,
                     300,
                     PointerInput.Origin.fromElement(element))
        .perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()
driver.set_window_size(500, 400)
driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html")

element = driver.find_element(By.LINK_TEXT, "Go To Page 2")

ActionChains(driver).scroll(0, 0, 0, 300, origin=element).perform()

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'

begin
  driver = Selenium::WebDriver.for :chrome
  target_size = Selenium::WebDriver::Dimension.new(500, 400)
  driver.manage.window.size = target_size
  driver.get 'https://crossbrowsertesting.github.io/selenium_example_page.html'
  element = driver.find_element(:link_text, 'Go To Page 2')
  driver.action.scroll_to(element, 0, 300).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder, By } = require('selenium-webdriver');

(async function scollByGivenAmountFromElement() {
  let driver = await new Builder().forBrowser('chrome').build();

  try {
    await driver.manage().window().setRect({ width: 500, height: 400 });
    await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');
    element = await driver.findElement(By.linkText('Go To Page 2'));

    await driver.actions().scroll(0, 0, 0, 300, element).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.PointerInput

fun main() {
    val driver: WebDriver = ChromeDriver()
    try {
        driver.manage().window().size = Dimension(500, 400)
        driver["https://crossbrowsertesting.github.io/selenium_example_page.html"]
        val element = driver.findElement(By.linkText("Go To Page 2"))
        val actions = Actions(driver)
        actions.scroll(
            0,
            0,
            0,
            300,
            PointerInput.Origin.fromElement(element)
        ).perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## Scroll by given amount 

Scrolls the viewport by the given amount i.e. horizontal and vertical offsets.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;

public class scrollByGivenAmountFromViewPort {

  public static void main(String[] args) throws Exception {
    WebDriver driver = new ChromeDriver();
    try {
      driver.manage().window().setSize(new Dimension(500, 400));
      driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html");

      Actions actions = new Actions(driver);
      actions.scroll(0,
                     0,
                     0,
                     200,
                     PointerInput.Origin.viewport())
        .perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver import ActionChains

driver = webdriver.Chrome()
driver.set_window_size(500, 400)
driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html")

ActionChains(driver).scroll(0, 0, 0, 200).perform()

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'

begin
  driver = Selenium::WebDriver.for :chrome
  target_size = Selenium::WebDriver::Dimension.new(500, 400)
  driver.manage.window.size = target_size
  driver.get 'https://crossbrowsertesting.github.io/selenium_example_page.html'

  driver.action.scroll_by(0, 200).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Origin } = require('selenium-webdriver');
const { Builder } = require('selenium-webdriver');

(async function scollByGivenAmountFromViewport() {
  let driver = await new Builder().forBrowser('chrome').build();

  try {
    await driver.manage().window().setRect({ width: 500, height: 400 });
    await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');

    await driver.actions().scroll(0, 0, 0, 200, Origin.VIEWPORT).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.PointerInput

fun main() {
    val driver: WebDriver = ChromeDriver()
    try {
        driver.manage().window().size = Dimension(500, 400)
        driver["https://crossbrowsertesting.github.io/selenium_example_page.html"]
        val actions = Actions(driver)
        actions.scroll(
            0,
            0,
            0,
            200,
            PointerInput.Origin.viewport()
        ).perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## Scroll from a offset of origin (viewport) by given amount

The origin is the where the cursor is placed before the scroll is executed. 
For example, the position on the screen where the cursor is before scrolling a mouse wheel. 
For origin as viewport, the origin offset is calculated from the upper left corner of the viewport.
Starting from this origin, the viewport is scrolled by the given amount 
i.e. horizontal and vertical offsets.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;

public class scrollFromOffsetOfOriginViewportByGivenAmount {

  public static void main(String[] args) throws Exception {
    WebDriver driver = new ChromeDriver();
    try {
      driver.manage().window().setSize(new Dimension(600, 600));
      driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html");
      WebElement textarea = driver.findElement(By.name("textarea"));

      Actions actions = new Actions(driver);

      textarea.sendKeys(
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
        "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
        "cccccccccccccccccccccccccccccccc" +
        "dddddddddddddddddddddddddddddddd" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

      actions.scroll(20,
                     200,
                     0,
                     -50,
                     PointerInput.Origin.viewport())
        .perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()
driver.set_window_size(600, 600)
driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html")

textarea = driver.find_element(By.NAME, "textarea")

textarea.send_keys("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                   "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                   "cccccccccccccccccccccccccccccccc" +
                   "dddddddddddddddddddddddddddddddd" +
                   "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")

ActionChains(driver).scroll(20, 200, 0, -50).perform()

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'

begin
  driver = Selenium::WebDriver.for :chrome
  target_size = Selenium::WebDriver::Dimension.new(600, 600)
  driver.manage.window.size = target_size
  driver.get 'https://crossbrowsertesting.github.io/selenium_example_page.html'
  textarea = driver.find_element(:name, 'textarea')

  textarea.send_keys 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa' +
  'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb' +
  'cccccccccccccccccccccccccccccccc' +
  'dddddddddddddddddddddddddddddddd' + 
  'eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee'

  origin = Selenium::WebDriver::WheelActions::ScrollOrigin.viewport(20, 200)
  driver.action.scroll(0, -50, origin: origin).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Origin } = require('selenium-webdriver');
const { Builder, By } = require('selenium-webdriver');

(async function scollByGivenAmountFromElement() {
  let driver = await new Builder().forBrowser('chrome').build();

  try {
    await driver.manage().window().setRect({ width: 600, height: 600 });
    await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');
    textarea = await driver.findElement(By.name('textarea'));
   
    await textarea.sendKeys('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa' +
      'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb' +
      'cccccccccccccccccccccccccccccccc' +
      'dddddddddddddddddddddddddddddddd' +
      'eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee');

    await driver.actions().scroll(20, 200, 0, -50, Origin.VIEWPORT).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.PointerInput

fun main() {
    val driver: WebDriver = ChromeDriver()
    try {
        driver.manage().window().size = Dimension(600, 600)
        driver["https://crossbrowsertesting.github.io/selenium_example_page.html"]
        val textarea = driver.findElement(By.name("textarea"))
        val actions = Actions(driver)
        textarea.sendKeys(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                    "cccccccccccccccccccccccccccccccc" +
                    "dddddddddddddddddddddddddddddddd" +
                    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
        )
        actions.scroll(
            20,
            200,
            0,
            -50,
            PointerInput.Origin.viewport()
        ).perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## Scroll from a offset of origin (element) by given amount

The origin is the where the cursor is placed before the scroll is executed. 
For example, the position on the screen where the cursor is before scrolling a mouse wheel. 
For origin as element, the origin offset is calculated from the center of the element.
Starting from this origin, the viewport is scrolled by the given amount 
i.e. horizontal and vertical offsets.

{{< tabpane langEqualsHeader=true >}}
   {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;

public class scrollFromOffsetOfOriginElementByGivenAmount {

  public static void main(String[] args) throws Exception {
    WebDriver driver = new ChromeDriver();
    try {
      driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html");
      WebElement textarea = driver.findElement(By.name("textarea"));
      WebElement submit = driver.findElement(By.id("submitbtn"));

      Actions actions = new Actions(driver);

      textarea.sendKeys(
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
        "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
        "cccccccccccccccccccccccccccccccc" +
        "dddddddddddddddddddddddddddddddd" +
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

      actions.scroll(0,
                     -50,
                     0,
                     -50,
                     PointerInput.Origin.fromElement(submit))
        .perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()
driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html")

textarea = driver.find_element(By.NAME, "textarea")
submit = driver.find_element(By.ID, "submitbtn")

textarea.send_keys("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                   "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                   "cccccccccccccccccccccccccccccccc" +
                   "dddddddddddddddddddddddddddddddd" +
                   "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")

ActionChains(driver).scroll(0, -50, 0, -50, origin=submit).perform()

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'

begin
  driver = Selenium::WebDriver.for :chrome
  driver.get 'https://crossbrowsertesting.github.io/selenium_example_page.html'
  textarea = driver.find_element(:name, 'textarea')
  submit = driver.find_element(:id, 'submitbtn')

  textarea.send_keys 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa' +
  'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb' +
  'cccccccccccccccccccccccccccccccc' +
  'dddddddddddddddddddddddddddddddd' + 
  'eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee'

  origin = Selenium::WebDriver::WheelActions::ScrollOrigin.element(submit, 0, -50)
  driver.action.scroll(0, -50, origin: origin).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder, By } = require('selenium-webdriver');

(async function scollByGivenAmountFromElement() {
  let driver = await new Builder().forBrowser('chrome').build();

  try {
    await driver.get('https://crossbrowsertesting.github.io/selenium_example_page.html');
    textarea = await driver.findElement(By.name('textarea'));
    submit = await driver.findElement(By.id('submitbtn'));

    await textarea.sendKeys('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa' +
      'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb' +
      'cccccccccccccccccccccccccccccccc' +
      'dddddddddddddddddddddddddddddddd' +
      'eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee');

    await driver.actions().scroll(0, -50, 0, -50, submit).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.PointerInput

fun main() {
    val driver: WebDriver = ChromeDriver()
    try {
        driver["https://crossbrowsertesting.github.io/selenium_example_page.html"]
        val textarea = driver.findElement(By.name("textarea"))
        val submit = driver.findElement(By.id("submitbtn"))
        val actions = Actions(driver)
        textarea.sendKeys(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                    "cccccccccccccccccccccccccccccccc" +
                    "dddddddddddddddddddddddddddddddd" +
                    "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
        )
        actions.scroll(
            0,
            -50,
            0,
            -50,
            PointerInput.Origin.fromElement(submit)
        ).perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}