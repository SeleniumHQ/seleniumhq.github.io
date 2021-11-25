---
title: "Acciones del ratón en detalle"
linkTitle: "Acciones del ratón en detalle"
weight: 3
description: >
  El ratón representa eventos del ratón. Las acciones del ratón son realizadas
  a través de una interfaz de bajo nivel la cual nos permite introducir acciones de
  dispositivos virtualizados al navegador web.
aliases: ["/documentation/es/support_packages/mouse_and_keyboard_actions_in_detail/"]
---

## clickAndHold

Moverá el ratón al elemento y hará clic (sin soltar) en medio del elemento dado.

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
      // Navega a la URL
      driver.get("https://google.com");

      // Guarda el elemento web del botón 'Sign in'
      WebElement searchBtn = driver.findElement(By.linkText("Sign in"));
      Actions actionProvider = new Actions(driver);
      // Realiza la acción click-and-hold en el elemento
      actionProvider.clickAndHold(searchBtn).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

# Navega a la URL
driver.get("http://www.google.com")

# Guarda el elemento web del botón 'Sign in'
searchBtn = driver.find_element(By.LINK_TEXT, "Sign in")

# Realiza la acción click-and-hold en el elemento
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
    public static void Main(string[] agrs)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navega a la URL
        driver.Navigate().GoToUrl("https://google.com");
        // Guarda el elemento web del botón 'Sign in'
        IWebElement searchBtn = driver.FindElement(By.LinkText("Sign in"));
        Actions actionProvider = new Actions(driver);
        // Realiza la acción click-and-hold en el elemento
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
  # Navega a la URL
  driver.get 'https://www.google.com'
  # Guarda el elemento web del botón 'Sign in'
  sign_in = driver.find_element(link_text: 'Sign in')
  # Realiza la acción click-and-hold en el elemento
  driver.action.click_and_hold(sign_in).perform
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder, By} = require('selenium-webdriver');

(async function clickAndHold() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    // Navega a la URL
    await driver.get('https://www.google.com');
    // Guarda el elemento web del botón 'Sign in'
    let searchBtn = driver.findElement(By.linkText("Sign in"));
    const actions = driver.actions({async: true});
    // Realiza la acción click-and-hold en el elemento
    await actions.move({origin:searchBtn}).press().perform();
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
        // Navega a la URL
        driver.get("https://google.com")
        // Guarda el elemento web del botón 'Sign in'
        val searchBtn = driver.findElement(By.linkText("Sign in"))
        val actionProvider = Actions(driver)
        // Realiza la acción click-and-hold en el elemento
        actionProvider.clickAndHold(searchBtn).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## contextClick
Este método en primer lugar realiza un movimiento del ratón a la localización del
elemento y realiza un clic contextual (clic derecho) en el elemento dado.

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
      // Navega a la URL
      driver.get("https://google.com");

      // Guarda el elemento web del botón 'Sign in'
      WebElement searchBtn = driver.findElement(By.linkText("Sign in"));
      Actions actionProvider = new Actions(driver);
      // Realiza la acción context-click en el elemento
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

# Navega a la URL
driver.get("http://www.google.com")

# Guarda el elemento web del botón 'Sign in'
searchBtn = driver.find_element(By.LINK_TEXT, "Sign in")

# Realiza la acción context-click en el elemento
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
    public static void Main(string[] agrs)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navega a la URL
        driver.Navigate().GoToUrl("https://google.com");
        // Guarda el elemento web del botón 'Sign in'
        IWebElement searchBtn = driver.FindElement(By.LinkText("Sign in"));
        Actions actionProvider = new Actions(driver);
        // Realiza la acción context-click en el elemento
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
  # Navega a la URL
  driver.get 'https://www.google.com'
  #  Guarda el elemento web del botón 'Sign in'
  sign_in = driver.find_element(link_text: 'Sign in')
  # Realiza la acción context-click en el elemento
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
    // Navega a la URL
    await driver.get('https://www.google.com');
    // Guarda el elemento web del botón 'Sign in'
    let searchBtn = driver.findElement(By.linkText("Sign in"));
    const actions = driver.actions({async: true});
    // Realiza la acción context-click en el elemento
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
        // Navega a la URL
        driver.get("https://google.com")
        // Guarda el elemento web del botón 'Sign in'
        val searchBtn = driver.findElement(By.linkText("Sign in"))
        val actionProvider = Actions(driver)
        // Realiza la acción context-click en el elemento
        actionProvider.contextClick(searchBtn).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## doubleClick
Moverá el ratón al elemento y realizará un clic doble en medio del elemento dado.

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
      // Navega a la URL
      driver.get("https://google.com");

      // Guarda el elemento web del botón 'Sign in'
      WebElement searchBtn = driver.findElement(By.linkText("Sign in"));
      Actions actionProvider = new Actions(driver);
      // Realiza la acción double-click en el elemento
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

# Navega a la URL
driver.get("http://www.google.com")

# Guarda el elemento web del botón 'Sign in'
searchBtn = driver.find_element(By.LINK_TEXT, "Sign in")

# Realiza la acción double-click en el elemento
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
    public static void Main(string[] agrs)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navega a la URL
        driver.Navigate().GoToUrl("https://google.com");
        // Guarda el elemento web del botón 'Sign in'
        IWebElement searchBtn = driver.FindElement(By.LinkText("Sign in"));
        Actions actionProvider = new Actions(driver);
        // Realiza la acción double-click en el elemento
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
  # Navega a la URL
  driver.get 'https://www.google.com'
  # Guarda el elemento web del botón 'Sign in'
  sign_in = driver.find_element(link_text: 'Sign in')
  # Realiza la acción double-click en el elemento
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
    // Navega a la URL
    await driver.get('https://www.google.com');
    // Guarda el elemento web del botón 'Sign in'
    let searchBtn = driver.findElement(By.linkText("Sign in"));
    const actions = driver.actions({async: true});
    // Realiza la acción double-click en el elemento
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
        // Navega a la URL
        driver.get("https://google.com")
        // Guarda el elemento web del botón 'Sign in'
        val searchBtn = driver.findElement(By.linkText("Sign in"))
        val actionProvider = Actions(driver)
        // Realiza la acción double-click en el elemento
        actionProvider.doubleClick(searchBtn).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## moveToElement
Este método mueve el ratón en medio del elemento dado. El elemento ademas es
desplazado hacia la vista al realizar la acción.

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
      // Navega a la URL
      driver.get("https://google.com");

      // Guarda el elemento web del enlace 'Gmail'
      WebElement gmailLink = driver.findElement(By.linkText("Gmail"));
      Actions actionProvider = new Actions(driver);
      // Realiza la acción move hacia el elemento
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

# Navega a la URL
driver.get("http://www.google.com")

# Guarda el elemento web del enlace 'Gmail'
gmailLink = driver.find_element(By.LINK_TEXT, "Gmail")

# Realiza la acción move hacia el elemento
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
    public static void Main(string[] agrs)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navega a la URL
        driver.Navigate().GoToUrl("https://google.com");
        // Guarda el elemento web del enlace 'Gmail'
        IWebElement gmailLink = driver.FindElement(By.LinkText("Gmail"));
        Actions actionProvider = new Actions(driver);
        // Realiza la acción move hacia el elemento
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
  # Navega a la URL
  driver.get 'https://www.google.com'
  # Guarda el elemento web del enlace 'Gmail'
  gmail_link = driver.find_element(link_text: 'Gmail')
  # Realiza la acción move hacia el elemento
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
    // Navega a la URL
    await driver.get('https://www.google.com');
    // Guarda el elemento web del enlace 'Gmail'
    let gmailLink = driver.findElement(By.linkText("Gmail"));
    const actions = driver.actions({async: true});
    // Realiza la acción move hacia el elemento
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
        // Navega a la URL
        driver.get("https://google.com")
        // Guarda el elemento web del enlace 'Gmail'
        val gmailLink = driver.findElement(By.linkText("Gmail"))
        val actionProvider = Actions(driver)
        // Realiza la acción move hacia el elemento
        actionProvider.moveToElement(gmailLink).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## moveByOffset:

Este método mueve el ratón desde su posición actual (o desde 0,0) hasta un 
desplazamiento dado. Si las coordenadas están fuera de la vista de la ventana,
entonces el ratón terminará fuera de la ventana del navegador.

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
      // Navega a la URL
      driver.get("https://google.com");

      // Guarda el elemento web del enlace 'Gmail'
      WebElement gmailLink = driver.findElement(By.linkText("Gmail"));
      // Captura el desplazamiento de las posiciones x e y del elemento
      int xOffset = gmailLink.getRect().getX();
      int yOffset = gmailLink.getRect().getY();
      Actions actionProvider = new Actions(driver);
      // Realiza la acción move hacia la posición del desplazamiento
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

# Navega a la URL
driver.get("http://www.google.com")

# Guarda el elemento web del enlace 'Gmail'
gmailLink = driver.find_element(By.LINK_TEXT, "Gmail")
# Captura el desplazamiento de las posiciones x e y del elemento
xOffset = 100
yOffset = 100
# Realiza la acción move hacia la posición del desplazamiento
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
    public static void Main(string[] agrs)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navega a la URL
        driver.Navigate().GoToUrl("https://google.com");
        // Guarda el elemento web del enlace 'Gmail'
        IWebElement gmailLink = driver.FindElement(By.LinkText("Gmail"));
        // Captura el desplazamiento de las posiciones x e y del elemento
        int xOffset = 100;
        int yOffset = 100;
        Actions actionProvider = new Actions(driver);
        // Realiza la acción move hacia la posición del desplazamiento
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
  # Navega a la URL
  driver.get 'https://www.google.com'
  # Guarda el elemento web del enlace 'Gmail'
  gmail_link = driver.find_element(link_text: 'Gmail')
  # Captura el desplazamiento de las posiciones x e y del elemento
  x_offset = gmail_link.rect.x
  y_offset = gmail_link.rect.y
  # Realiza la acción move hacia la posición del desplazamiento
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
    // Navega a la URL
    await driver.get('https://www.google.com');
    // Guarda el elemento web del enlace 'Gmail'
    let gmailLink = driver.findElement(By.linkText("Gmail"));
    // Captura el desplazamiento de las posiciones x e y del elemento
    let offset = await gmailLink.getRect();
    let x = await offset.x;
    let y = await offset.y;
    const actions = driver.actions({async: true});
    // Realiza la acción move hacia la posición del desplazamiento
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
        // Navega a la URL
        driver.get("https://google.com")
        // Guarda el elemento web del enlace 'Gmail'
        val gmailLink = driver.findElement(By.linkText("Gmail"))
        // Captura el desplazamiento de las posiciones x e y del elemento
        val xOffset = gmailLink.rect.getX()
        val yOffset = gmailLink.rect.getY()
        val actionProvider = Actions(driver)
        // Realiza la acción move hacia la posición del desplazamiento
        actionProvider.moveByOffset(xOffset, yOffset).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## dragAndDrop

Este método en primer lugar realiza una acción click-and-hold en el elemento de
origen, después lo mueve a la localización del elemento de destino y por ultimo
suelta el clic. 

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
      // Navega a la URL
      driver.get("https://crossbrowsertesting.github.io/drag-and-drop");
      // Guarda 'sourceEle' como elemento de origen
      WebElement sourceEle = driver.findElement(By.id("draggable"));
      // Guarda 'targetEle' como elemento de destino
      WebElement targetEle = driver.findElement(By.id("droppable"));
      Actions actionProvider = new Actions(driver);
      // Realiza la acción dragAndDrop desde el origen hacia el destino
      actionProvider.dragAndDrop(sourceEle, targetEle).build().perform();
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

# Navega a la URL
driver.get("https://crossbrowsertesting.github.io/drag-and-drop")

# Guarda 'sourceEle' como elemento de origen
sourceEle = driver.find_element(By.ID, "draggable")
# Guarda 'targetEle' como elemento de destino
targetEle  = driver.find_element(By.ID, "droppable")
# Realiza la acción dragAndDrop desde el origen hacia el destino
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
    public static void Main(string[] agrs)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navega a la URL
        driver.Navigate().GoToUrl("https://crossbrowsertesting.github.io/drag-and-drop");
        // Guarda 'sourceEle' como elemento de origen
        IWebElement sourceEle = driver.FindElement(By.Id("draggable"));
        // Guarda 'targetEle' como elemento de destino
        IWebElement targetEle = driver.FindElement(By.Id("droppable"));
        Actions actionProvider = new Actions(driver);
        // Realiza la acción dragAndDrop desde el origen hacia el destino
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
  # Navega a la URL
  driver.get 'https://crossbrowsertesting.github.io/drag-and-drop'
  # Guarda 'source_ele' como elemento de origen
  source_ele = driver.find_element(id: 'draggable')
  # Guarda 'target_ele' como elemento de destino
  target_ele = driver.find_element(id: 'droppable')
  # Realiza la acción dragAndDrop desde el origen hacia el destino
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
    // Navega a la URL
    await driver.get('https://crossbrowsertesting.github.io/drag-and-drop');
    // Guarda 'sourceEle' como elemento de origen
    let sourceEle = driver.findElement(By.id("draggable"));
    // Guarda 'targetEle' como elemento de destino
    let targetEle = driver.findElement(By.id("droppable"));
    const actions = driver.actions({async: true});
    // Realiza la acción dragAndDrop desde el origen hacia el destino
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
        // Navega a la URL
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop")
        // Guarda 'sourceEle' como elemento de origen
        val sourceEle = driver.findElement(By.id("draggable"))
        // Guarda 'targetEle' como elemento de destino
        val targetEle = driver.findElement(By.id("droppable"))
        val actionProvider = Actions(driver)
        // Realiza la acción dragAndDrop desde el origen hacia el destino
        actionProvider.dragAndDrop(sourceEle, targetEle).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## dragAndDropBy

Este metodo en primer lugar realiza un click-and-hold en el elemento origen,
mueve el ratón al desplacamiento dado y luego suelta el raton.
 
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
      // Navega a la URL
      driver.get("https://crossbrowsertesting.github.io/drag-and-drop");
      // Guarda 'sourceEle' como elemento de origen
      WebElement sourceEle = driver.findElement(By.id("draggable"));
      // Guarda 'targetEle' como elemento de destino y obtiene las coordenadas
      WebElement targetEle = driver.findElement(By.id("droppable"));
      int targetEleXOffset = targetEle.getLocation().getX();
      int targetEleYOffset = targetEle.getLocation().getY();
      Actions actionProvider = new Actions(driver);
      // Realiza la acción de  dragAndDropBy hacia el desplazamiento destino
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

# Navega a la URL
driver.get("https://crossbrowsertesting.github.io/drag-and-drop")

# Guarda 'sourceEle' como elemento de origen
sourceEle = driver.find_element(By.ID, "draggable")
# Guarda 'targetEle' como elemento de destino y obtiene las coordenadas
targetEle  = driver.find_element(By.ID, "droppable")
targetEleXOffset = targetEle.location.get("x")
targetEleYOffset = targetEle.location.get("y")

# Realiza la acción de  dragAndDropBy hacia el desplazamiento destino
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
    public static void Main(string[] agrs)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navega a la URL
        driver.Navigate().GoToUrl("https://crossbrowsertesting.github.io/drag-and-drop");
        // Guarda 'sourceEle' como elemento de origen
        IWebElement sourceEle = driver.FindElement(By.Id("draggable"));
        // Guarda 'targetEle' como elemento de destino y obtiene las coordenadas
        IWebElement targetEle = driver.FindElement(By.Id("droppable"));
        int targetEleXOffset = targetEle.Location.X;
        int targetEleYOffset = targetEle.Location.Y;
        Actions actionProvider = new Actions(driver);
        // Realiza la acción de  dragAndDropBy hacia el desplazamiento destino
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
  # Navega a la URL
  driver.get 'https://crossbrowsertesting.github.io/drag-and-drop'
  # Guarda 'source_ele' como elemento de origen
  source_ele = driver.find_element(id: 'draggable')
  target_ele = driver.find_element(id: 'droppable')
  # Guarda 'target_ele' como elemento de destino y obtiene las coordenadas
  x_offset = target_ele.rect.x
  y_offset = target_ele.rect.y
  # Realiza la acción de  dragAndDropBy hacia el desplazamiento destino
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
    // Navega a la URL
    await driver.get('https://crossbrowsertesting.github.io/drag-and-drop');
    // Guarda 'sourceEle' como elemento de origen
    let sourceEle = driver.findElement(By.id("draggable"));
    // Guarda 'targetEle' como elemento de destino y obtiene las coordenadas
    let targetEle = driver.findElement(By.id("droppable"));
    let offset = await targetEle.getRect();
    let x = await offset.x;
    let y = await offset.y;
    const actions = driver.actions({async: true});
    // Realiza la acción de  dragAndDropBy hacia el desplazamiento destino
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
        // Navega a la URL
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop")
        // Guarda 'sourceEle' como elemento de origen
        val sourceEle = driver.findElement(By.id("draggable"))
        // Guarda 'targetEle' como elemento de destino y obtiene las coordenadas
        val targetEle = driver.findElement(By.id("droppable"))
        val targetEleXOffset = targetEle.location.getX()
        val targetEleYOffset = targetEle.location.getY()
        val actionProvider = Actions(driver)
        // Realiza la acción de  dragAndDropBy hacia el desplazamiento destino
        actionProvider.dragAndDropBy(sourceEle, targetEleXOffset, targetEleYOffset).build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

## release

Esta acción suelta el botón izquierdo del ratón. Si se le pasa un WebElement,
soltará el botón izquierdo del ratón en el elemento dado.

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
      // Navega a la URL
      driver.get("https://crossbrowsertesting.github.io/drag-and-drop");
      // Guarda 'sourceEle' como elemento de origen
      WebElement sourceEle = driver.findElement(By.id("draggable"));
      // Guarda 'targetEle' como elemento de destino
      WebElement targetEle = driver.findElement(By.id("droppable"));
      Actions actionProvider = new Actions(driver);
      actionProvider.clickAndHold(sourceEle).moveToElement(targetEle).build().perform();
      // Realiza la acción release
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

# Navega a la URL
driver.get("https://crossbrowsertesting.github.io/drag-and-drop")

# Guarda 'sourceEle' como elemento de origen
sourceEle = driver.find_element(By.ID, "draggable")
# Guarda 'targetEle' como elemento de destino
targetEle  = driver.find_element(By.ID, "droppable")
webdriver.ActionChains(driver).click_and_hold(sourceEle).move_to_element(targetEle).perform()
# Realiza la acción release
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
    public static void Main(string[] agrs)
    {
      IWebDriver driver = new ChromeDriver();
      try
      {
        // Navega a la URL
        driver.Navigate().GoToUrl("https://crossbrowsertesting.github.io/drag-and-drop");
        // Guarda 'sourceEle' como elemento de origen
        IWebElement sourceEle = driver.FindElement(By.Id("draggable"));
        // Guarda 'targetEle' como elemento de destino
        IWebElement targetEle = driver.FindElement(By.Id("droppable"));
        Actions actionProvider = new Actions(driver);
        actionProvider.ClickAndHold(sourceEle).MoveToElement(targetEle).Build().Perform();
        // Realiza la acción release       
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
  # Navega a la URL
  driver.get 'https://crossbrowsertesting.github.io/drag-and-drop'
  source_ele = driver.find_element(id: 'draggable')
  target_ele = driver.find_element(id: 'droppable')
  driver.action.click_and_hold(source_ele).move_to(target_ele).perform
  # Realiza la acción release
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
    // Navega a la URL
    await driver.get('https://crossbrowsertesting.github.io/drag-and-drop');
    // Guarda 'sourceEle' como elemento de origen
    let sourceEle = driver.findElement(By.id("draggable"));
    // Guarda 'targetEle' como elemento de destino
    let targetEle = driver.findElement(By.id("droppable"));
    const actions = driver.actions({async: true});
    await actions.move({origin:sourceEle}).press().perform();
    // Realiza la acción release
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
        // Navega a la URL
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop")
        // Guarda 'sourceEle' como elemento de origen
        val sourceEle = driver.findElement(By.id("draggable"))
        //Guarda 'targetEle' como elemento de destino
        val targetEle = driver.findElement(By.id("droppable"))
        val actionProvider = Actions(driver)
        actionProvider.clickAndHold(sourceEle).moveToElement(targetEle).build().perform()
        // Realiza la acción release
        actionProvider.release().build().perform()
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}
