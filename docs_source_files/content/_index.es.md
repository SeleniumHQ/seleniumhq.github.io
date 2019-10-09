---
title: "El Proyecto para Automatización de Navegadores Selenium"
---

# El Proyecto para Automatización de Navegadores Selenium

Selenium es un proyecto padre que cobija a una gama de herramientas y bibliotecas que permiten y soportan la automatización de los navegadores web.

Proporciona extensiones para emular la interacción del usuario con los navegadores, un servidor de distribución para escalar la asignación del navegador y la infraestructura para implementaciones de la 
[Especificación W3C del WebDriver](//www.w3.org/TR/webdriver/) que le permite escribir código intercambiable para todos los principales navegadores web.

Este proyecto es posible gracias a los colaboradores voluntarios que han dedicado miles de horas de su propio tiempo y han hecho que el código fuente [esté disponible gratuitamente](licencia attr.md #) para que cualquiera pueda usarlo, disfrutarlo y mejorarlo.

Selenium reúne a proveedores de navegadores, ingenieros y entusiastas para promover una discusión abierta sobre la automatización de la plataforma web.
El proyecto organiza [una conferencia anual](//seleniumconf.com/) para enseñar y nutrir a la comunidad.

En el núcleo de Selenium se encuentra el _[WebDriver]({{< ref "/webdriver/_index.md" >}})_, una interfaz para escribir conjuntos de instrucciones que se pueden ejecutar indistintamente en muchos navegadores. Aquí está una de las instrucciones más simples que puede hacer:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class HelloSelenium {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
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

#Este ejemplo requiere Selenium WebDriver 3.13 o posterior
with webdriver.Firefox() as driver:
    wait = WebDriverWait(driver, 10)
    driver.get("https://google.com/ncr")
    driver.find_element_by_name("q").send_keys("cheese" + Keys.RETURN)
    first_result = wait.until(presence_of_element_located((By.CSS_SELECTOR, "h3>div")))
    print(first_result.get_attribute("textContent"))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Support.UI;
using SeleniumExtras.WaitHelpers;

class HelloSelenium
{
    static void Main()
    {
        using (IWebDriver driver = new FirefoxDriver())
        {
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
            driver.Navigate().GoToUrl("https://www.google.com/ncr");
            driver.FindElement(By.Name("q")).SendKeys("cheese" + Keys.Enter);
            IWebElement firstResult = wait.Until(ExpectedConditions.ElementExists(By.CssSelector("h3>div")));
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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.WebDriverWait
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated

class HelloSelenium {

    public main(args: Array<String>) {
        val driver = new FirefoxDriver()
        val wait = new WebDriverWait(driver, 10)
        try {
            driver.get("https://google.com/ncr")
            driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER)
            val firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3>div")))
            System.out.println(firstResult.getAttribute("textContent"))
        } finally {
            driver.quit()
        }
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}


Consulte la _[Guía rápida]({{< ref "/getting_started/quick.es.md" >}})_ para obtener una explicación completa de lo que sucede detrás de escena cuando ejecuta este código.
Debe continuar con la [documentación narrativa]({{< ref "/introduction/_index.md" >}}) para comprender cómo puede [instalar]({{< ref "/selenium_installation/_index.md" >}}) y utilizar con éxito Selenium como herramienta de automatización de pruebas, y escalar pruebas simples como esta para ejecutar en entornos grandes y distribuidos en múltiples navegadores, en varios sistemas operativos diferentes.


## Empezando

Si es nuevo en Selenium, tenemos algunos recursos que pueden ayudarlo a ponerse al día de inmediato.

* [Guía rápida]({{< ref "/getting_started/quick.es.md" >}})
  * [El WebDriver]({{< ref "/getting_started/quick.es.md#webdriver" >}})
  * [Control remoto]({{< ref "/getting_started/quick.es.md#remote-control" >}})
  * [IDE]({{< ref "/getting_started/quick.es.md#ide" >}})
  * [Grid]({{< ref "/getting_started/quick.es.md#grid" >}})
  * [HTML Runner]({{< ref "/getting_started/quick.es.md#html-runner" >}})

