---
title: "Elemento web"
weight: 9
---

WebElement representa un elemento del DOM. Los WebElements se pueden
encontrar buscando desde la raíz del documento utilizando una instancia de 
WebDriver o buscando en otra WebElement. 

El API WebDriver proporciona métodos integrados para encontrar los 
elementos web que son basados en diferentes propiedades como ID, 
Nombre, Clase, XPath, Selectores CSS, Texto de enlace, etc.

## Find Element

Se utiliza para encontrar un elemento y devuelve la primera
referencia única de WebElement que coincide, que puede usarse para 
acciones futuras con el elemento

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new FirefoxDriver();

driver.get("http://www.google.com");

// Obtén el elemento cuadro de búsqueda del webElement 'q' utilizando Find Element
WebElement searchBox = driver.findElement(By.name("q"));

searchBox.sendKeys("webdriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Firefox()

driver.get("http://www.google.com")

# Get search box element from webElement 'q' using Find Element
search_box = driver.find_element(By.NAME, "q")

search_box.send_keys("webdriver")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebDriver driver = new FirefoxDriver();

driver.Url = "http://www.google.com";

// Obtén el elemento cuadro de búsqueda del webElement 'q' utilizando Find Element
IWebElement searchbox = driver.FindElement(By.Name("q"));

searchbox.SendKeys("webdriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  # Navega a la URL
  driver.get 'https://google.com'

  # Obtén el elemento cuadro de búsqueda del webElement 'q' utilizando Find Element
  search_bar = driver.find_element(name: 'q')

  # Ejecuta una acción utilizando WebElement
  search_bar.send_keys 'Webdriver'
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let {Builder, By} = require('selenium-webdriver');
driver = new Builder().forBrowser('firefox').build();

(async function test(){

// Navega a la URL
await driver.get('http://www.google.com');

// Obtén el elemento cuadro de búsqueda del webElement 'q' utilizando Find Element
let searchBar = driver.findElement(By.name('q'));

// Ejecuta una acción utilizando WebElemen
await searchBar.sendKeys('Webdriver');

})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val driver = FirefoxDriver()

driver.get("http://www.google.com")

// Obtén el elemento cuadro de búsqueda del webElement 'q' utilizando Find Element
val searchBox = driver.findElement(By.name("q"))

searchBox.sendKeys("webdriver")
  {{< / code-panel >}}
{{< / code-tab >}}

## Find Elements

Similar a 'Find Element', pero devuelve una lista 
de elementos web coincidentes. 
Para usar un WebElement particular de la lista, 
debes recorrerla lista de elementos para realizar 
acciones con el elemento seleccionado.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

public class findElementsExample {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get("https://example.com");
            // Obtén todos los elementos con el nombre de etiqueta 'p'
            List<WebElement> elements = driver.findElements(By.tagName("p"));
            for (WebElement element : elements) {
                System.out.println("Paragraph text:" + element.getText());
            }
        } finally {
            driver.quit();
        }
    }
}
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Firefox()

# Navigate to Url
driver.get("https://www.example.com")

# Get all the elements available with tag name 'p'
elements = driver.find_elements(By.TAG_NAME, 'p')

for e in elements:
    print(e.text)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using System.Collections.Generic;

namespace FindElementsExample {
 class FindElementsExample {
  public static void Main(string[] args) {
   IWebDriver driver = new FirefoxDriver();
   try {
    // Navega a la Url
    driver.Navigate().GoToUrl("https://example.com");

    // Obtén todos los elementos con el nombre de etiqueta 'p'
    IList < IWebElement > elements = driver.FindElements(By.TagName("p"));
    foreach(IWebElement e in elements) {
     System.Console.WriteLine(e.Text);
    }

   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  # Navega a la Url
  driver.get 'https://www.example.com'

  # Obtén todos los elementos con el nombre de etiqueta 'p'
  elements = driver.find_elements(:tag_name,'p')

  elements.each { |e|
    puts e.text
  }
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder, By} = require('selenium-webdriver');
(async function example() {
    let driver = await new Builder().forBrowser('firefox').build();
    try {
        // Navega a la URL
        await driver.get('https://www.example.com');

        // Get all the elements available with tag 'p'
        let elements = await driver.findElements(By.css('p'));
        for(let e of elements) {
            console.log(await e.getText());
        }
    }
    finally {
        await driver.quit();
    }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.firefox.FirefoxDriver

fun main() {
    val driver = FirefoxDriver()
    try {
        driver.get("https://example.com")
        // Obtén todos los elementos con el nombre de etiqueta 'p'
        val elements = driver.findElements(By.tagName("p"))
        for (element in elements) {
            println("Paragraph text:" + element.text)
        }
    } finally {
        driver.quit()
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}

## Find Element desde Element

Se utiliza para encontrar un elemento hijo dentro
del contexto del elemento padre.
Para lograr esto, el WebElement primario se encadena
con 'findElement' para acceder a elementos secundarios.

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebDriver driver = new FirefoxDriver();
driver.get("http://www.google.com");
WebElement searchForm = driver.findElement(By.tagName("form"));
WebElement searchBox = searchForm.findElement(By.name("q"));
searchBox.sendKeys("webdriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Firefox()
driver.get("http://www.google.com")
search_form = driver.find_element(By.TAG_NAME, "form")
search_box = search_form.find_element(By.NAME, "q")
search_box.send_keys("webdriver")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebDriver driver = new FirefoxDriver();
driver.Url = "http://www.google.com";
IWebElement searchForm = driver.FindElement(By.TagName("form"));
IWebElement searchbox = searchForm.FindElement(By.Name("q"));
searchbox.SendKeys("webdriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :firefox
begin
  # Navega a la URL
  driver.get 'https://google.com'

  # Obtén y almacena el elemento DOM `<form>`
  search_form = driver.find_element(name: 'f')

  # Obtén el elemento de caja de búsqueda del elemento `form`
  search_bar = search_form.find_element(name: 'q')

  # Ejecuta una acción usando WebElement
  search_bar.send_keys 'Webdriver'
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let {Builder, By} = require('selenium-webdriver');
driver = new Builder().forBrowser('firefox').build();

(async function test(){

//Navega a la URL
await driver.get('http://www.google.com');

//Obtén y alamacena el elemento DOM `<form>`
let searchForm = driver.findElement(By.name('f'));

//Obtén el elemento de caja de búsqueda del elemento `form`
let searchBar = searchForm.findElement(By.name('q'));

//Ejecuta una acción usando WebElement
await searchBar.sendKeys('Webdriver');

})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val driver = FirefoxDriver()
driver.get("http://www.google.com")
val searchForm = driver.findElement(By.tagName("form"))
val searchBox = searchForm.findElement(By.name("q"))
searchBox.sendKeys("webdriver")
  {{< / code-panel >}}
{{< / code-tab >}}

## Find Elements desde Element

Se utiliza para encontrar una lista de elementos 
hijos dentro del contexto del elemento padre.
Para lograr esto, el WebElement primario se encadena 
con 'findElements'para acceder a elementos secundarios.

{{< code-tab >}}
  {{< code-panel language="java" >}}
  import org.openqa.selenium.By;
  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.WebElement;
  import org.openqa.selenium.chrome.ChromeDriver;
  import java.util.List;

  public class findElementsFromElement {
      public static void main(String[] args) {
          WebDriver driver = new ChromeDriver();
          try {
              driver.get("https://example.com");

              // Obten el elemento con el nombre de etiqueta 'div'
              WebElement element = driver.findElement(By.tagName("div"));

              // Obtén todos los elementos con el nombre de etiqueta 'p'
              List<WebElement> elements = element.findElements(By.tagName("p"));
              for (WebElement e : elements) {
                  System.out.println(e.getText());
              }
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
driver.get("https://www.example.com")

# Get element with tag name 'div'
element = driver.find_element(By.TAG_NAME, 'div')

# Get all the elements available with tag name 'p'
elements = element.find_elements(By.TAG_NAME, 'p')
for e in elements:
    print(e.text)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System.Collections.Generic;

namespace FindElementsFromElement {
 class FindElementsFromElement {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    driver.Navigate().GoToUrl("https://example.com");

    // Obten el elemento con el nombre de etiqueta 'div'
    IWebElement element = driver.FindElement(By.TagName("div"));

    // Obtén todos los elementos con el nombre de etiqueta 'p'
    IList < IWebElement > elements = element.FindElements(By.TagName("p"));
    foreach(IWebElement e in elements) {
     System.Console.WriteLine(e.Text);
    }
   } finally {
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
    # Navega a la URL
    driver.get 'https://www.example.com'

    # Obten el elemento con el nombre de etiqueta 'div'
    element = driver.find_element(:tag_name,'div')

    # Obtén todos los elementos con el nombre de etiqueta 'p'
    elements = element.find_elements(:tag_name,'p')

    elements.each { |e|
      puts e.text
    }
  ensure
    driver.quit
  end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = new Builder()
          .forBrowser('chrome')
          .build();

      await driver.get('https://www.example.com');

      // Obten el elemento con el nombre de etiqueta 'div'
      let element = driver.findElement(By.css("div"));

      // Obtén todos los elementos con el nombre de etiqueta 'p'
      let elements = await element.findElements(By.css("p"));
      for(let e of elements) {
          console.log(await e.getText());
      }
  })();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
  import org.openqa.selenium.By
  import org.openqa.selenium.chrome.ChromeDriver

  fun main() {
      val driver = ChromeDriver()
      try {
          driver.get("https://example.com")

          // Obten el elemento con el nombre de etiqueta 'div'
          val element = driver.findElement(By.tagName("div"))

          // Obtén todos los elementos con el nombre de etiqueta 'p'
          val elements = element.findElements(By.tagName("p"))
          for (e in elements) {
              println(e.text)
          }
      } finally {
          driver.quit()
      }
  }
  {{< / code-panel >}}
{{< / code-tab >}}

## Get Active Element

Se utiliza para rastrear (o) encontrar el elemento DOM 
que tiene el foco en el contexto de navegación actual.

{{< code-tab >}}
  {{< code-panel language="java" >}}
  import org.openqa.selenium.*;
  import org.openqa.selenium.chrome.ChromeDriver;

  public class activeElementTest {
    public static void main(String[] args) {
      WebDriver driver = new ChromeDriver();
      try {
        driver.get("http://www.google.com");
        driver.findElement(By.cssSelector("[name='q']")).sendKeys("webElement");

        // Obtener el atributo del elemento activo actual
        String attr = driver.switchTo().activeElement().getAttribute("title");
        System.out.println(attr);
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
  driver.get("https://www.google.com")
  driver.find_element(By.CSS_SELECTOR, '[name="q"]').send_keys("webElement")

  # Obtener el atributo del elemento activo actual
  attr = driver.switch_to.active_element.get_attribute("title")
  print(attr)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
    using OpenQA.Selenium;
    using OpenQA.Selenium.Chrome;

    namespace ActiveElement {
     class ActiveElement {
      public static void Main(string[] args) {
       IWebDriver driver = new ChromeDriver();
       try {
        // Navega a la URL
        driver.Navigate().GoToUrl("https://www.google.com");
        driver.FindElement(By.CssSelector("[name='q']")).SendKeys("webElement");

        // Obtener el atributo del elemento activo actual
        string attr = driver.SwitchTo().ActiveElement().GetAttribute("title");
        System.Console.WriteLine(attr);
       } finally {
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
    driver.get 'https://www.google.com'
    driver.find_element(css: '[name="q"]').send_keys('webElement')

    # Obtener el atributo del elemento activo actual
    attr = driver.switch_to.active_element.attribute('title')
    puts attr
  ensure
    driver.quit
  end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
  const {Builder, By} = require('selenium-webdriver');

  (async function example() {
      let driver = await new Builder().forBrowser('chrome').build();
      await driver.get('https://www.google.com');
      await  driver.findElement(By.css('[name="q"]')).sendKeys("webElement");

      // Obtener el atributo del elemento activo actual
      let attr = await driver.switchTo().activeElement().getAttribute("title");
      console.log(`${attr}`)
  })();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
  import org.openqa.selenium.By
  import org.openqa.selenium.chrome.ChromeDriver

  fun main() {
      val driver = ChromeDriver()
      try {
          driver.get("https://www.google.com")
          driver.findElement(By.cssSelector("[name='q']")).sendKeys("webElement")

          // Obtener el atributo del elemento activo actual
          val attr = driver.switchTo().activeElement().getAttribute("title")
          print(attr)
      } finally {
          driver.quit()
      }
  }
  {{< / code-panel >}}
{{< / code-tab >}}

## Is Element Enabled

Este método se utiliza para comprobar si el elemento conectado
está habilitado o deshabilitado en una página web.
Devuelve un valor booleano, **True** si el elemento conectado es
**habilitado** en el contexto de navegación actual, 
de lo contrario, devuelve **false**.

{{< code-tab >}}
  {{< code-panel language="java" >}}
  //navigates to url 
  driver.get("https://www.google.com/");
  
  //returns true if element is enabled else returns false
  boolean value = driver.findElement(By.name("btnK")).isEnabled();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Navigate to url
driver.get("http://www.google.com")
   
# Returns true if element is enabled else returns false
value = driver.find_element(By.NAME, 'btnK').is_enabled()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://google.com");

// Store the WebElement
IWebElement element = driver.FindElement(By.Name("btnK"));

// Prints true if element is enabled else returns false
System.Console.WriteLine(element.Enabled);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Navigate to url
driver.get 'http://www.google.com/'

# Returns true if element is enabled else returns false
ele = driver.find_element(name: 'btnK').enabled?
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Navigate to url
await driver.get('https://www.google.com');

// Resolves Promise and returns boolean value
let element =  await driver.findElement(By.name("btnK")).isEnabled();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
 //navigates to url 
 driver.get("https://www.google.com/")
 
 //returns true if element is enabled else returns false
 val attr = driver.findElement(By.name("btnK")).isEnabled()
  {{< / code-panel >}}
{{< / code-tab >}}

## Is Element Selected

This method determines if the referenced Element 
is _Selected_ or not. This method is widely used on 
Check boxes, radio buttons, input elements, and option elements.

Returns a boolean value, **True** if referenced element is 
**selected** in the current browsing context else returns **false**.

{{< code-tab >}}
  {{< code-panel language="java" >}}
 //navigates to url 
 driver.get("https://the-internet.herokuapp.com/checkboxes");
  
 //returns true if element is checked else returns false
 boolean value = driver.findElement(By.cssSelector("input[type='checkbox']:first-of-type")).isSelected();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Navigate to url
driver.get("https://the-internet.herokuapp.com/checkboxes")

# Returns true if element is checked else returns false
value = driver.find_element(By.CSS_SELECTOR, "input[type='checkbox']:first-of-type").is_selected()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://the-internet.herokuapp.com/checkboxes");
  
// Returns true if element ins checked else returns false
bool value = driver.FindElement(By.CssSelector("input[type='checkbox']:last-of-type")).Selected;
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Navigate to url
driver.get 'https://the-internet.herokuapp.com/checkboxes'

# Returns true if element is checked else returns false
ele = driver.find_element(css: "input[type='checkbox']:last-of-type").selected?
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Navigate to url
await driver.get('https://the-internet.herokuapp.com/checkboxes');

// Returns true if element ins checked else returns false
let res = await driver.findElement(By.css("input[type='checkbox']:last-of-type")).isSelected();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
 //navigates to url 
 driver.get("https://the-internet.herokuapp.com/checkboxes")
 
 //returns true if element is checked else returns false
 val attr =  driver.findElement(By.cssSelector("input[type='checkbox']:first-of-type")).isSelected()
  {{< / code-panel >}}
{{< / code-tab >}}

## Get Element TagName

It is used to fetch the [TagName](https://www.w3.org/TR/webdriver/#dfn-get-element-tag-name) 
of the referenced Element which has the focus in the current browsing context.

{{< code-tab >}}
  {{< code-panel language="java" >}}
 //navigates to url 
 driver.get("https://www.example.com");

 //returns TagName of the element
 String value = driver.findElement(By.cssSelector("h1")).getTagName();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Navigate to url
driver.get("https://www.example.com")

# Returns TagName of the element
attr = driver.find_element(By.CSS_SELECTOR, "h1").tag_name
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Navigate to Url
driver.Navigate().GoToUrl("https://www.example.com");
  
// Returns TagName of the element
string attr = driver.FindElement(By.CssSelector("h1")).TagName;
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Navigate to url
driver.get 'https://www.example.com'

# Returns TagName of the element
attr = driver.find_element(css: "h1").tag_name
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Navigate to URL
await driver.get('https://www.example.com');

// Returns TagName of the element
let value = await driver.findElement(By.css('h1')).getTagName();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
 //navigates to url 
 driver.get("https://www.example.com")
 
 //returns TagName of the element
 val attr =  driver.findElement(By.cssSelector("h1")).getTagName()
  {{< / code-panel >}}
{{< / code-tab >}}

## Get Element Rect

It is used to fetch the dimensions and coordinates 
of the referenced element.

The fetched data body contain the following details:
* X-axis position from the top-lef corner of the element
* y-axis position from the top-lef corner of the element
* Height of the element
* Width of the element

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Navigate to url
driver.get("https://www.example.com");

// Returns height, width, x and y coordinates referenced element
Rectangle res =  driver.findElement(By.cssSelector("h1")).getRect();

// Rectangle class provides getX,getY, getWidth, getHeight methods
System.out.println(res.getX());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Navigate to url
driver.get("https://www.example.com")
    
# Returns height, width, x and y coordinates referenced element
res = driver.find_element(By.CSS_SELECTOR, "h1").rect
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
 // Please raise a PR for code sample
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Navigate to url
driver.get 'https://www.example.com'
  
# Returns height, width, x and y coordinates referenced element
res = driver.find_element(css: "h1").rect
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Navigate to url
await driver.get('https://www.example.com');

// Returns height, width, x and y coordinates referenced element
let element =  await driver.findElement(By.css("h1")).getRect();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Navigate to url
driver.get("https://www.example.com")

// Returns height, width, x and y coordinates referenced element
val res = driver.findElement(By.cssSelector("h1")).rect

// Rectangle class provides getX,getY, getWidth, getHeight methods
println(res.getX())
  {{< / code-panel >}}
{{< / code-tab >}}