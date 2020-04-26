---
title: "Selenium, el proyecto para automatizar navegadores"
---

# Selenium, el proyecto para automatizar navegadores

Selenium es un proyecto que alberga un abanico de herramientas y librerías que
permiten y apoyan la automatización de navegadores web.

Proporciona extensiones que permiten emular las interacciones que realizan los
usuarios con los navegadores, un servidor que permite distribuir la asignación
de navegadores de forma escalable, y la infraestructura necesaria para las 
implementaciones de la [especificación del WebDriver del W3C](//www.w3.org/TR/webdriver/), 
el cual permite escribir código intercambiable para los navegadores web mas usados.

Este proyecto es posible gracias a los colaboradores voluntarios, los cuales
han dedicado miles de horas de su propio tiempo haciendo así que el código fuente
esté [disponible de manera gratuita]({{< ref "/front_matter/copyright_and_attributions.es.md#license" >}})
para que cualquiera pueda usarlo, disfrutarlo y mejorarlo.

Selenium conecta a proveedores de navegadores web, ingenieros y entusiastas para 
promover un debate abierto sobre la automatización de plataformas web.
El proyecto organiza [una conferencia anual](//seleniumconf.com/) con el fin de
enseñar y nutrir a la comunidad.

El corazón de Selenium es el [WebDriver]({{< ref "/webdriver/_index.md" >}}), una
interfaz que permite escribir conjuntos de instrucciones que se pueden ejecutar de
manera indistinta en muchos navegadores.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import java.time.Duration;

public class HelloSelenium {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

#Este ejemplo necesita una versión de Selenium WebDriver igual o mayor a la 3.13
with webdriver.Firefox() as driver:
    wait = WebDriverWait(driver, 10)
    driver.get("https://google.com/ncr")
    driver.find_element(By.NAME, "q").send_keys("cheese" + Keys.RETURN)
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

        // Añade el texto "cheese" y efectúa la acción de la tecla "Enter"
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
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

fun main() {
    val driver = FirefoxDriver()
    val wait = WebDriverWait(driver, Duration.ofSeconds(10))
    try {
        driver.get("https://google.com/ncr")
        driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER)
        val firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3>div")))
        println(firstResult.getAttribute("textContent"))
    } finally {
        driver.quit()
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}


Puedes ver este [tour rápido]({{< ref "/getting_started/quick.es.md" >}}) para una 
explicación completa de lo que sucede entre bastidores cuando se ejecuta el código.
Es recomendable continuar con el 
[planteamiento que ofrece esta documentación]({{< ref "/introduction/_index.md" >}})
para entender como se puede [instalar]({{< ref "/selenium_installation/_index.md" >}})
y usar correctamente Selenium como herramienta de automatización de pruebas, y como se
puede escalar pruebas simples como el ejemplo anterior, en grandes entornos con múltiples
navegadores en diferentes sistemas operativos.

## Empezando

Si eres nuevo con Selenium, tenemos una serie de
recursos que te pueden ayudar a ponerte al día de inmediato

* [Tour rápido]({{< ref "/getting_started/quick.es.md" >}})
  * [WebDriver]({{< ref "/getting_started/quick.es.md#webdriver" >}})
  * [IDE]({{< ref "/getting_started/quick.es.md#ide" >}})
  * [Grid]({{< ref "/getting_started/quick.es.md#grid" >}})

