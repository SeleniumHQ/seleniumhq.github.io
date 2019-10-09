---
title: "Le Projet d'Automatisation de Navigateur Selenium"
---

# Le Projet d'Automatisation de Navigateur Selenium

Selenium est projet englobant un éventail d'outils et de librairies
permettant l'automtisation des navigateurs internet.

Il fournit des extensions afin d'émuler des interactions utilisateur avec les navigateurs, 
un serveur de distribution permettant la mise à l'échelle de l'allocation de navigateur 
ainsi que l'infrastructure pour l'implémentation de la [spécification W3C WebDriver](//www.w3.org/TR/webdriver/)
permettant l'écriture de code interchangeable pour tous les principaux navigateurs.

Ce projet est rendu possible grâce au contributeurs volontaires 
ayant investi des milliers d'heures de leur temps 
et rendu le code source [disponible librement](attr.md#license)
à quiconque souhaitant l'utiliser et l'améliorer ou simplement s'amuser.

Selenium rassemble distributeurs de navigateur, ingénieurs et entousiastes
pour favoriser une discussion ouverte autour de l'automatisation de la platerforme web.
Le projet organise [une conférence annuelle](//seleniumconf.com/) afin d'entretenir cette communauté.

Au coeur de Selenium se trouve _[WebDriver]({{< ref "/webdriver/_index.md" >}})_, 
une interface permettant d'écrire des instructions pouvant être exécutées indifférement par de nombreux navigateurs.
Voici par exemple une des plus simples instructions disponibles:

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

#Cet exemple requiert Selenium WebDriver 3.13 ou plus récent
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


Suivez le _[Quick Tour]({{< ref "/getting_started/quick.fr.md" >}})_ 
pour une explication complète de ce qu'il se passe derrière ces exemples de code. 
Il est conseillé de poursuivre cette [documentation narrative]({{< ref "/introduction/_index.md" >}}) 
afin de voir comment [installer]({{< ref "/selenium_installation/_index.md" >}}) 
et utiliser Selenium en tant qu'outil d'automatisation de test, 
ainsi que pour mettre à l'echelle l'exécution de tels tests sur de larges environnements distribués, 
avec de multiples navigateurs et des systèmes d'exploitation différents.

## Getting started

Si Selenium est nouveau pour vous,
nous vous proposons quelques ressources qui
pourront vous aider à vous mettre à niveau très rapidement.

* [Tour rapide]({{< ref "/getting_started/quick.fr.md" >}})
  * [WebDriver]({{< ref "/getting_started/quick.fr.md#webdriver" >}})
  * [Remote Control]({{< ref "/getting_started/quick.fr.md#remote-control" >}})
  * [IDE]({{< ref "/getting_started/quick.fr.md#ide" >}})
  * [Grid]({{< ref "/getting_started/quick.fr.md#grid" >}})
  * [HTML Runner]({{< ref "/getting_started/quick.fr.md#html-runner" >}})

