---
title: "Teclado"
weight: 10
---

Keyboard representa un evento del teclado. Las acciones del teclado
se realizan mediante el uso de una interfaz de bajo nivel
que nos permite proporcionar entradas de un dispositivo virtualizado 
al navegador web.

## sendKeys

El sendKeys escribe una secuencia de teclas en el elemento del DOM
incluso si se encuentra una secuencia de teclas modificadoras.
[Here](https://www.w3.org/TR/webdriver/#keyboard-actions) are the list of 
possible keystrokes that WebDriver Supports.

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
      // Navega a la URL
      driver.get("https://google.com");

      // Inserta el texto "q" y ejecuta la accion del teclado "Enter"
      driver.findElement(By.name("q")).sendKeys("q" + Keys.ENTER);
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
driver = webdriver.Firefox()

# Navega a la URL
driver.get("http://www.google.com")

# Inserta el texto "Webdriver" y ejecuta la accion del teclado "ENTER"
driver.find_element(By.NAME, "q").send_keys("webdriver" + Keys.ENTER)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using (var driver = new FirefoxDriver())
{
  //  Navega a la URL
  driver.Navigate().GoToUrl("https://google.com");

  // Inserta el texto "Webdriver" y ejecuta la accion del teclado "ENTER"
  driver.FindElement(By.Name("q")).SendKeys("webdriver" + Keys.Enter);
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  #  Navega a la URL
  driver.get 'https://google.com'

  # Inserta el texto "Webdriver" y ejecuta la accion del teclado "ENTER"
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
    //  Navega a la URL
    await driver.get('https://www.google.com');

    // Inserta el texto "Webdriver" y ejecuta la accion del teclado "ENTER"
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
import org.openqa.selenium.firefox.FirefoxDriver

fun main() {
  val driver = FirefoxDriver()
  try {
    // Navega a la URL
    driver.get("https://google.com")

    // Inserta el texto "q" y ejecuta la accion del teclado "Enter"
    driver.findElement(By.name("q")).sendKeys("q" + Keys.ENTER)
  } finally {
    driver.quit()
  }
}
  {{< / code-panel >}}
{{< / code-tab >}}

## keyDown

KeyDown se usa para simular la acción de presionar una 
tecla modificadora (CONTROL, SHIFT, ALT)

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new ChromeDriver();
try {
  //  Navega a la URL
  driver.get("https://google.com");

  // Inserta el texto "Webdriver" y ejecuta la accion del teclado "ENTER"
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
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
driver = webdriver.Chrome()

#  Navega a la URL
driver.get("http://www.google.com")

# Inserta el texto "Webdriver" y ejecuta la accion del teclado "ENTER"
driver.find_element(By.NAME, "q").send_keys("webdriver" + Keys.ENTER)

# Ejecuta la acción ctrl + A (modificador CONTROL + Alfabeto A) para seleccionar la página
webdriver.ActionChains(driver).key_down(Keys.CONTROL).send_keys("a").perform()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebDriver driver = new ChromeDriver();
try
{
  //  Navega a la URL
  driver.Navigate().GoToUrl("https://google.com");

  // Inserta el texto "Webdriver" y ejecuta la accion del teclado "ENTER"
  driver.FindElement(By.Name("q")).SendKeys("webdriver" + Keys.Enter);

  // Ejecuta la acción ctrl + A (modificador CONTROL + Alfabeto A) para seleccionar la página
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
  #  Navega a la URL
  driver.get 'https://google.com'

  # Inserta el texto "Webdriver" y ejecuta la accion del teclado "ENTER"
  driver.find_element(name: 'q').send_keys 'webdriver', :return

  # Ejecuta la acción ctrl + A (modificador CONTROL + Alfabeto A) para seleccionar la página
  driver.action.key_down(:control).send_keys('a').perform

ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
(async function example() {
  let driver = await new Builder().forBrowser('chrome').build();

  try {
    //  Navega a la URL
    await driver.get('https://www.google.com');

    // Inserta el texto "Webdriver" y ejecuta la accion del teclado "ENTER"
    await driver.findElement(By.name('q')).sendKeys('webdriver', Key.ENTER);

    // Ejecuta la acción ctrl + A (modificador CONTROL + Alfabeto A) para seleccionar la página
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
    //  Navega a la URL
    driver.get("https://google.com")

    // Inserta el texto "Webdriver" y ejecuta la accion del teclado "ENTER"
    driver.findElement(By.name("q")).sendKeys("webdriver" + Keys.ENTER)
    val action = Actions(driver)

    // Ejecuta la acción ctrl + A (modificador CONTROL + Alfabeto A) para seleccionar la página
    action.keyDown(Keys.CONTROL).sendKeys("a").build().perform();
  } finally {
    driver.quit()
  }
}
  {{< / code-panel >}}
{{< / code-tab >}}

## keyUp

KeyUp se usa para simular la acción de liberar (o) 
desbloqueo de una tecla modificadora (CONTROL, SHIFT, ALT)

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
      //  Navega a la URL
      driver.get("https://google.com");
      Actions action = new Actions(driver);

      // Almacena el WebElement del cuadro de búsqueda de Google 
      WebElement search = driver.findElement(By.name("q"));

      // Ingresa el texto "qwerty" con keyDown en la tecla SHIFT y después de keyUp a la tecla SHIFT (QWERTYqwerty) 
      action.keyDown(Keys.SHIFT).sendKeys(search,"qwerty").keyUp(Keys.SHIFT).sendKeys("qwerty").perform();
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
driver = webdriver.Chrome()

#  Navega a la URL
driver.get("http://www.google.com")

# Almacena el WebElement del cuadro de búsqueda de Google 
search = driver.find_element(By.NAME, "q")

action = webdriver.ActionChains(driver)

# Ingresa el texto "qwerty" con keyDown en la tecla SHIFT y después de keyUp a la tecla SHIFT (QWERTYqwerty)
action.key_down(Keys.SHIFT).send_keys_to_element(search, "qwerty").key_up(Keys.SHIFT).send_keys("qwerty").perform()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
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
        //  Navega a la URL
        driver.Navigate().GoToUrl("https://google.com");

        Actions action = new Actions(driver);
        // Almacena el WebElement del cuadro de búsqueda de Google 
        IWebElement search = driver.FindElement(By.Name("q"));

        // Ingresa el texto "qwerty" con keyDown en la tecla SHIFT y después de keyUp a la tecla SHIFT (QWERTYqwerty)
        action.KeyDown(Keys.Shift).SendKeys(search, "qwerty").KeyUp(Keys.Shift).SendKeys("qwerty").Perform();

      }
      finally {
        driver.Quit();
      }
    }
  }
}

  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome
begin
  #  Navega a la URL
  driver.get 'https://google.com'

  # Almacena el WebElement del cuadro de búsqueda de Google 
  search = driver.find_element(name: 'q')

  # Ingresa el texto "qwerty" con keyDown en la tecla SHIFT y después de keyUp a la tecla SHIFT (QWERTYqwerty)
  driver.action.key_down(:shift).send_keys(search,'qwerty').key_up(:shift).send_keys("qwerty").perform

ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, By, Key} = require('selenium-webdriver');
(async function example() {
  let driver = await new Builder().forBrowser('firefox').build();
  try {
    //  Navega a la URL
    await driver.get('https://www.google.com');

    // Almacena el WebElement del cuadro de búsqueda de Google 
    let search = driver.findElement(By.name('q'));

    // Ingresa el texto "qwerty" con keyDown en la tecla SHIFT y después de keyUp a la tecla SHIFT (QWERTYqwerty)
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
    //  Navega a la URL
    driver.get("https://google.com")

    // Almacena el WebElement del cuadro de búsqueda de Google 
    val search = driver.findElement(By.name("q"))
    val action = Actions(driver)

    // Ingresa el texto "qwerty" con keyDown en la tecla SHIFT y después de keyUp a la tecla SHIFT (QWERTYqwerty)
    action.keyDown(Keys.SHIFT).sendKeys(search, "qwerty").keyUp(Keys.SHIFT).sendKeys("qwerty").build().perform();
  } finally {
    driver.quit()
  }
}
  {{< / code-panel >}}
{{< / code-tab >}}

## clear

Borra el contenido de un elemento editable.
Esto solo se aplica a los elementos que son editables e interactuables,
de lo contrario, Selenium devuelve el error 
(invalid element state (or) Element not interactable)

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class clear {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      //  Navega a la URL
      driver.get("https://www.google.com");
      // Almacena el elemento  'SearchInput'
      WebElement searchInput = driver.findElement(By.name("q"));
      searchInput.sendKeys("selenium");
      // Borra el texto ingresado
      searchInput.clear();
    } finally {
      driver.quit();
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By
driver = webdriver.Chrome()

#  Navega a la URL
driver.get("http://www.google.com")
# Almacena el elemento  'SearchInput'
SearchInput = driver.find_element(By.NAME, "q")
SearchInput.send_keys("selenium")
# Borra el texto ingresado
SearchInput.clear()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
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
        //  Navega a la URL
        driver.Navigate().GoToUrl(@"https://www.google.com");
        // Almacena el elemento  'SearchInput'
        IWebElement searchInput = driver.FindElement(By.Name("q"));
        searchInput.SendKeys("selenium");
        // Borra el texto ingresado
        searchInput.Clear();
      }
      finally
      {
        driver.Quit();
      }
    }
  }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome
begin
  #  Navega a la URL
  driver.get 'https://google.com'
  # Almacena el elemento  'SearchInput't
  search_input = driver.find_element(name: 'q')
  search_input.send_keys('selenium')
  # Borra el texto ingresado
  search_input.clear
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, By} = require('selenium-webdriver');
(async function example() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    //  Navega a la URL
    await driver.get('https://www.google.com');
    // Almacena el elemento  'SearchInput'
    let searchInput = driver.findElement(By.name('q'));
    await searchInput.sendKeys("selenium");
    // Borra el texto ingresado
    await searchInput.clear();
  }
  finally {
    await driver.quit();
  }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
fun main() {
  val driver =  ChromeDriver()
  try {
    //  Navega a la URL
    driver.get("https://www.google.com")
    // Almacena el elemento  'SearchInput'
    val searchInput = driver.findElement(By.name("q"))
    searchInput.sendKeys("selenium")
    // Borra el texto ingresado
    searchInput.clear()
  } finally {
    driver.quit()
  }
}
  {{< / code-panel >}}
{{< / code-tab >}}
