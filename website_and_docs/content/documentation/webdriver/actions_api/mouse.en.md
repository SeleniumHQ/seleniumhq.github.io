---
title: "Mouse actions"
linkTitle: "Mouse"
weight: 4
needsTranslation: true
description: >
  A representation of any pointer device for interacting with a web page.
aliases: [
"/documentation/en/support_packages/mouse_and_keyboard_actions_in_detail/",
"/documentation/support_packages/mouse_and_keyboard_actions_in_detail/"
]
---

## Click and hold

It will move to the element and clicks (without releasing) in the middle of the given element.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class clickAndHold {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://google.com");

      // Store 'google search' button web element
      WebElement searchBtn = driver.findElement(By.linkText("Sign in"));
      Actions actionProvider = new Actions(driver);
      // Perform click-and-hold action on the element
      actionProvider.clickAndHold(searchBtn).build().perform();
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

    # Store 'google search' button web element
searchBtn = driver.find_element(By.LINK_TEXT, "Sign in")

    # Perform click-and-hold action on the element
webdriver.ActionChains(driver).click_and_hold(searchBtn).perform()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Interactions;

namespace SeleniumApp
{
  public class ClickAndHold
  {
    public static void Main(string[] args)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navigate to Url
        driver.Navigate().GoToUrl("https://google.com");
        // Store 'google search' button web element
        IWebElement searchBtn = driver.FindElement(By.LinkText("Sign in"));
        Actions actionProvider = new Actions(driver);
        // Perform click-and-hold action on the element
        actionProvider.ClickAndHold(searchBtn).Build().Perform();
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
    # Navigate to Url
  driver.get 'https://www.google.com'
    # Store 'Sign In' button web element
  sign_in = driver.find_element(link_text: 'Sign in')
    # Perform click-and-hold action on the element
  driver.action.click_and_hold(sign_in).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/actionsApi/mouse/clickAndHold.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://google.com")
        // Store 'google search' button web element
        val searchBtn = driver.findElement(By.linkText("Sign in"))
        val actionProvider = Actions(driver)
        // Perform click-and-hold action on the element
        actionProvider.clickAndHold(searchBtn).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## Context click
This method firstly performs a mouse-move to the location of the element and performs the context-click (right click) on the given element.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class contextClick {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://google.com");

      // Store 'google search' button web element
      WebElement searchBtn = driver.findElement(By.linkText("Sign in"));
      Actions actionProvider = new Actions(driver);
      // Perform context-click action on the element
      actionProvider.contextClick(searchBtn).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

    # Navigate to url
driver.get("http://www.google.com")

    # Store 'google search' button web element
searchBtn = driver.find_element(By.LINK_TEXT, "Sign in")

    # Perform context-click action on the element
webdriver.ActionChains(driver).context_click(searchBtn).perform()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Interactions;

namespace SeleniumApp
{
  public class ContextClick
  {
    public static void Main(string[] args)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navigate to Url
        driver.Navigate().GoToUrl("https://google.com");
        // Store 'google search' button web element
        IWebElement searchBtn = driver.FindElement(By.LinkText("Sign in"));
        Actions actionProvider = new Actions(driver);
        // Perform context-click action on the element
        actionProvider.ContextClick(searchBtn).Build().Perform();
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
    # Navigate to Url
  driver.get 'https://www.google.com'
    # Store 'Sign In' button web element
  sign_in = driver.find_element(link_text: 'Sign in')
    # Perform context-click action on the element
  driver.action.context_click(sign_in).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');

(async function contextClick() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'google search' button web element
    let searchBtn = driver.findElement(By.linkText("Sign in"));
    const actions = driver.actions({async: true});
    // Perform context-click action on the element
    await actions.contextClick(searchBtn).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://google.com")
        // Store 'google search' button web element
        val searchBtn = driver.findElement(By.linkText("Sign in"))
        val actionProvider = Actions(driver)
        // Perform context-click action on the element
        actionProvider.contextClick(searchBtn).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## Double click
It will move to the element and performs a double-click in the middle of the given element.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class doubleClick {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://google.com");

      // Store 'google search' button web element
      WebElement searchBtn = driver.findElement(By.linkText("Sign in"));
      Actions actionProvider = new Actions(driver);
      // Perform double-click action on the element
      actionProvider.doubleClick(searchBtn).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

    # Navigate to url
driver.get("http://www.google.com")

    # Store 'google search' button web element
searchBtn = driver.find_element(By.LINK_TEXT, "Sign in")

    # Perform double-click action on the element
webdriver.ActionChains(driver).double_click(searchBtn).perform()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Interactions;

namespace SeleniumApp
{
  public class DoubleClick
  {
    public static void Main(string[] args)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navigate to Url
        driver.Navigate().GoToUrl("https://google.com");
        // Store 'google search' button web element
        IWebElement searchBtn = driver.FindElement(By.LinkText("Sign in"));
        Actions actionProvider = new Actions(driver);
        // Perform double-click action on the element
        actionProvider.DoubleClick(searchBtn).Build().Perform();
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
    # Navigate to Url
  driver.get 'https://www.google.com'
    # Store 'Sign In' button web element
  sign_in = driver.find_element(link_text: 'Sign in')
    # Perform double-click action on the element
  driver.action.double_click(sign_in).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');

(async function doubleClick() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'google search' button web element
    let searchBtn = driver.findElement(By.linkText("Sign in"));
    const actions = driver.actions({async: true});
    // Perform double-click action on the element
    await actions.doubleClick(searchBtn).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://google.com")
        // Store 'google search' button web element
        val searchBtn = driver.findElement(By.linkText("Sign in"))
        val actionProvider = Actions(driver)
        // Perform double-click action on the element
        actionProvider.doubleClick(searchBtn).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## Move to element
This method moves the mouse to the middle of the element. The element is also scrolled into the view on performing this action.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class moveToElement {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://google.com");

      // Store 'Gmail' anchor web element
      WebElement gmailLink = driver.findElement(By.linkText("Gmail"));
      Actions actionProvider = new Actions(driver);
      // Performs mouse move action onto the element
      actionProvider.moveToElement(gmailLink).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

    # Navigate to url
driver.get("http://www.google.com")

    # Store 'google search' button web element
gmailLink = driver.find_element(By.LINK_TEXT, "Gmail")

    # Performs mouse move action onto the element
webdriver.ActionChains(driver).move_to_element(gmailLink).perform()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Interactions;

namespace SeleniumApp
{
  public class MoveToElement
  {
    public static void Main(string[] args)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navigate to Url
        driver.Navigate().GoToUrl("https://google.com");
        // Store 'google search' button web element
        IWebElement gmailLink = driver.FindElement(By.LinkText("Gmail"));
        Actions actionProvider = new Actions(driver);
        // Performs mouse move action onto the element
        actionProvider.MoveToElement(gmailLink).Build().Perform();
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
    # Navigate to Url
  driver.get 'https://www.google.com'
    # Store 'Gmail' anchor web element
  gmail_link = driver.find_element(link_text: 'Gmail')
    # Performs mouse move action onto the element
  driver.action.move_to(gmail_link).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');

(async function moveToElement() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'Gmail' anchor web element
    let gmailLink = driver.findElement(By.linkText("Gmail"));
    const actions = driver.actions({async: true});
    // Performs mouse move action onto the element
    await actions.move({origin:gmailLink}).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://google.com")
        // Store 'Gmail' anchor web element
        val gmailLink = driver.findElement(By.linkText("Gmail"))
        val actionProvider = Actions(driver)
        // Performs mouse move action onto the element
        actionProvider.moveToElement(gmailLink).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## Move by offset

This method moves the mouse from its current position (or 0,0) by the given offset. If the coordinates are outside the view window, then the mouse will end up outside the browser window.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class moveByOffset {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://google.com");

      // Store 'Gmail' anchor web element
      WebElement gmailLink = driver.findElement(By.linkText("Gmail"));
      // Capture x and y offset positions of element
      int xOffset = gmailLink.getRect().getX();
      int yOffset = gmailLink.getRect().getY();
      Actions actionProvider = new Actions(driver);
      // Performs mouse move action onto the offset position
      actionProvider.moveByOffset(xOffset, yOffset).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

    # Navigate to url
driver.get("http://www.google.com")

    # Store 'google search' button web element
gmailLink = driver.find_element(By.LINK_TEXT, "Gmail")
    # Set x and y offset positions of element
xOffset = 100
yOffset = 100
    # Performs mouse move action onto the element
webdriver.ActionChains(driver).move_by_offset(xOffset,yOffset).perform()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Interactions;

namespace SeleniumApp
{
  public class MoveByOffset
  {
    public static void Main(string[] args)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navigate to Url
        driver.Navigate().GoToUrl("https://google.com");
        // Store 'google search' button web element
        IWebElement gmailLink = driver.FindElement(By.LinkText("Gmail"));
        // Set x and y offset positions of element
        int xOffset = 100;
        int yOffset = 100;
        Actions actionProvider = new Actions(driver);
        // Performs mouse move action onto the offset position
        actionProvider.MoveByOffset(xOffset, yOffset).Build().Perform();
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
    # Navigate to Url
  driver.get 'https://www.google.com'
    # Store 'Gmail' anchor web element
  gmail_link = driver.find_element(link_text: 'Gmail')
    # Capture x and y offset positions of element
  x_offset = gmail_link.rect.x
  y_offset = gmail_link.rect.y
    # Performs mouse move action onto the offset position
  driver.action.move_to_location(x_offset, y_offset).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');

(async function moveByOffset() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://www.google.com');
    // Store 'Gmail' anchor web element
    let gmailLink = driver.findElement(By.linkText("Gmail"));
    // Capture offset positions of element
    let offset = await gmailLink.getRect();
    let x = await offset.x;
    let y = await offset.y;
    const actions = driver.actions({async: true});
    // Performs mouse move action onto the element
    await actions.move({x:parseInt(x),y:parseInt(y)}).pause(3000).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://google.com")
        // Store 'Gmail' anchor web element
        val gmailLink = driver.findElement(By.linkText("Gmail"))
        // Capture x and y offset positions of element
        val xOffset = gmailLink.rect.getX()
        val yOffset = gmailLink.rect.getY()
        val actionProvider = Actions(driver)
        // Performs mouse move action onto the element
        actionProvider.moveByOffset(xOffset, yOffset).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## dragAndDrop

This method firstly performs a click-and-hold on the source element, 
moves to the location of the target element and then releases the mouse.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class dragAndDrop {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://crossbrowsertesting.github.io/drag-and-drop");
      // Store 'box A' as source element
      WebElement sourceEle = driver.findElement(By.id("draggable"));
      // Store 'box B' as source element
      WebElement targetEle = driver.findElement(By.id("droppable"));
      Actions actionProvider = new Actions(driver);
      // Performs drag and drop action of sourceEle onto the targetEle
      actionProvider.dragAndDrop(sourceEle, targetEle).build().perform();
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
driver.get("https://crossbrowsertesting.github.io/drag-and-drop")

    # Store 'box A' as source element
sourceEle = driver.find_element(By.ID, "draggable")
    # Store 'box B' as source element
targetEle  = driver.find_element(By.ID, "droppable")
    # Performs drag and drop action of sourceEle onto the targetEle
webdriver.ActionChains(driver).drag_and_drop(sourceEle,targetEle).perform()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Interactions;

namespace SeleniumApp
{
  public class DragAndDrop
  {
    public static void Main(string[] args)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navigate to Url
        driver.Navigate().GoToUrl("https://crossbrowsertesting.github.io/drag-and-drop");
        // Store 'box A' as source element
        IWebElement sourceEle = driver.FindElement(By.Id("draggable"));
        // Store 'box B' as source element
        IWebElement targetEle = driver.FindElement(By.Id("droppable"));
        Actions actionProvider = new Actions(driver);
        // Performs drag and drop action of sourceEle onto the targetEle
        actionProvider.DragAndDrop(sourceEle, targetEle).Build().Perform();
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
    # Navigate to Url
  driver.get 'https://crossbrowsertesting.github.io/drag-and-drop'
    # Store 'box A' as source element
  source_ele = driver.find_element(id: 'draggable')
    # Store 'box B' as source element
  target_ele = driver.find_element(id: 'droppable')
    # Performs drag and drop action of sourceEle onto the targetEle
  driver.action.drag_and_drop(source_ele, target_ele).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');

(async function dragAndDrop() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://crossbrowsertesting.github.io/drag-and-drop');
    // Store 'box A' as source element
    let sourceEle = driver.findElement(By.id("draggable"));
    // Store 'box B' as source element
    let targetEle = driver.findElement(By.id("droppable"));
    const actions = driver.actions({async: true});
    // Performs drag and drop action of sourceEle onto the targetEle
    await actions.dragAndDrop(sourceEle, targetEle).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop")
        // Store 'box A' as source element
        val sourceEle = driver.findElement(By.id("draggable"))
        // Store 'box B' as source element
        val targetEle = driver.findElement(By.id("droppable"))
        val actionProvider = Actions(driver)
        // Performs drag and drop action of sourceEle onto the targetEle
        actionProvider.dragAndDrop(sourceEle, targetEle).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## dragAndDropBy

This method firstly performs a click-and-hold on the source element, moves to the given offset and then releases the mouse.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class dragAndDropBy {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://crossbrowsertesting.github.io/drag-and-drop");
      // Store 'box A' as source element
      WebElement sourceEle = driver.findElement(By.id("draggable"));
      // Store 'box B' as source element
      WebElement targetEle = driver.findElement(By.id("droppable"));
      int targetEleXOffset = targetEle.getLocation().getX();
      int targetEleYOffset = targetEle.getLocation().getY();
      Actions actionProvider = new Actions(driver);
      // Performs dragAndDropBy onto the  target element offset position
      actionProvider.dragAndDropBy(sourceEle, targetEleXOffset, targetEleYOffset).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

    # Navigate to url
driver.get("https://crossbrowsertesting.github.io/drag-and-drop")

    # Store 'box A' as source element
sourceEle = driver.find_element(By.ID, "draggable")
    # Store 'box B' as source element
targetEle  = driver.find_element(By.ID, "droppable")
targetEleXOffset = targetEle.location.get("x")
targetEleYOffset = targetEle.location.get("y")

    # Performs dragAndDropBy onto the target element offset position
webdriver.ActionChains(driver).drag_and_drop_by_offset(sourceEle, targetEleXOffset, targetEleYOffset).perform()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Interactions;

namespace SeleniumApp
{
  public class DragAndDropToOffset
  {
    public static void Main(string[] args)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navigate to Url
        driver.Navigate().GoToUrl("https://crossbrowsertesting.github.io/drag-and-drop");
        // Store 'box A' as source element
        IWebElement sourceEle = driver.FindElement(By.Id("draggable"));
        // Store 'box B' as source element
        IWebElement targetEle = driver.FindElement(By.Id("droppable"));
        int targetEleXOffset = targetEle.Location.X;
        int targetEleYOffset = targetEle.Location.Y;
        Actions actionProvider = new Actions(driver);
        // Performs drag and drop action of sourceEle onto the targetEle
        actionProvider.DragAndDropToOffset(sourceEle, targetEleXOffset, targetEleYOffset).Build().Perform();
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
    # Navigate to Url
  driver.get 'https://crossbrowsertesting.github.io/drag-and-drop'
    # Store 'box A' as source element
  source_ele = driver.find_element(id: 'draggable')
  target_ele = driver.find_element(id: 'droppable')
    # Capture x and y offset positions of element
  x_offset = target_ele.rect.x
  y_offset = target_ele.rect.y
    # Performs dragAndDropBy onto the  target element offset position
  driver.action.drag_and_drop_by(source_ele, x_offset, y_offset).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');

(async function dragAndDropBy() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://crossbrowsertesting.github.io/drag-and-drop');
    // Store 'box A' as source element
    let sourceEle = driver.findElement(By.id("draggable"));
    // Store 'box B' as source element
    let targetEle = driver.findElement(By.id("droppable"));
    let offset = await targetEle.getRect();
    let x = await offset.x;
    let y = await offset.y;
    const actions = driver.actions({async: true});
    // Performs dragAndDropBy onto the  target element offset position
    await actions.dragAndDrop(sourceEle, {x:parseInt(x), y:parseInt(y)}).perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop")
        // Store 'box A' as source element
        val sourceEle = driver.findElement(By.id("draggable"))
        // Store 'box B' as source element
        val targetEle = driver.findElement(By.id("droppable"))
        val targetEleXOffset = targetEle.location.getX()
        val targetEleYOffset = targetEle.location.getY()
        val actionProvider = Actions(driver)
        // Performs dragAndDropBy onto the  target element offset position
        actionProvider.dragAndDropBy(sourceEle, targetEleXOffset, targetEleYOffset).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## release

This action releases the depressed left mouse button. If WebElement is passed, 
it will release depressed left mouse button on the given WebElement

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class release {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      // Navigate to Url
      driver.get("https://crossbrowsertesting.github.io/drag-and-drop");
      // Store 'box A' as source element
      WebElement sourceEle = driver.findElement(By.id("draggable"));
      // Store 'box B' as source element
      WebElement targetEle = driver.findElement(By.id("droppable"));
      Actions actionProvider = new Actions(driver);
      actionProvider.clickAndHold(sourceEle).moveToElement(targetEle).build().perform();
      // Performs release event
      actionProvider.release().build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

    # Navigate to url
driver.get("https://crossbrowsertesting.github.io/drag-and-drop")

    # Store 'box A' as source element
sourceEle = driver.find_element(By.ID, "draggable")
    # Store 'box B' as source element
targetEle  = driver.find_element(By.ID, "droppable")

    # Performs dragAndDropBy onto the target element offset position
webdriver.ActionChains(driver).click_and_hold(sourceEle).move_to_element(targetEle).perform()
    # Performs release event
webdriver.ActionChains(driver).release().perform()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Interactions;

namespace SeleniumApp
{
  public class Release
  {
    public static void Main(string[] args)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navigate to Url
        driver.Navigate().GoToUrl("https://crossbrowsertesting.github.io/drag-and-drop");
        // Store 'box A' as source element
        IWebElement sourceEle = driver.FindElement(By.Id("draggable"));
        // Store 'box B' as source element
        IWebElement targetEle = driver.FindElement(By.Id("droppable"));
        Actions actionProvider = new Actions(driver);
        actionProvider.ClickAndHold(sourceEle).MoveToElement(targetEle).Build().Perform();
        // Performs release event              
        actionProvider.Release().Build().Perform();
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
    # Navigate to Url
  driver.get 'https://crossbrowsertesting.github.io/drag-and-drop'
  source_ele = driver.find_element(id: 'draggable')
  target_ele = driver.find_element(id: 'droppable')
  driver.action.click_and_hold(source_ele).move_to(target_ele).perform
    # Performs release event
  driver.action.release.perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');

(async function release() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navigate to Url
    await driver.get('https://crossbrowsertesting.github.io/drag-and-drop');
    // Store 'box A' as source element
    let sourceEle = driver.findElement(By.id("draggable"));
    // Store 'box B' as source element
    let targetEle = driver.findElement(By.id("droppable"));
    const actions = driver.actions({async: true});
    await actions.move({origin:sourceEle}).press().perform();
    // Performs release event on target element
    await actions.move({origin:targetEle}).release().perform();
  }
  finally {
    await driver.quit();
  }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.interactions.Actions

fun main() {
    val driver =  ChromeDriver()
    try {
        // Navigate to Url
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop")
        // Store 'box A' as source element
        val sourceEle = driver.findElement(By.id("draggable"))
        // Store 'box B' as source element
        val targetEle = driver.findElement(By.id("droppable"))
        val actionProvider = Actions(driver)
        actionProvider.clickAndHold(sourceEle).moveToElement(targetEle).build().perform()
        // Performs release event
        actionProvider.release().build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

