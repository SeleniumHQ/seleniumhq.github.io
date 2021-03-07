---
title: "O Projeto Selenium de Automação de Navegadores"
---

# O Projeto Selenium de Automação de Navegadores

Selenium é uma ferramenta de teste automatizada gratuita (código aberto) usada para validar aplicativos da web para desktop e móveis em diferentes navegadores e plataformas. Você pode usar várias linguagens de programação como Java, C #, Python etc. para criar Scripts de Teste Selenium, que habilitam e suportam a automação de navegadores da web para desktop e móveis.

Ele fornece extensões para emular a interação do usuário com os navegadores,
um servidor de distribuição para escalonar a alocação do navegador,
e a infraestrutura para implementações da [Especificação W3C WebDriver](//www.w3.org/TR/webdriver/)
que permite escrever código intercambiável para todos os principais navegadores da web.

Este projeto é possível graças a colaboradores voluntários
que dedicam milhares de horas de seu próprio tempo,
e disponibilizaram o código-fonte [disponível gratuitamente]({{<ref "/front_matter/copyright_and_attributions.pt-br.md#license">}})
para qualquer um usar, aproveitar e melhorar.

Selenium reúne criadores de navegadores, engenheiros e entusiastas
para promover uma discussão aberta sobre a automação da plataforma da web.
O projeto organiza [uma conferência anual](//seleniumconf.com/)
para ensinar e nutrir a comunidade.

No núcleo do Selenium está _[WebDriver]({{<ref "/webdriver/_index.md">}})_,
uma interface para escrever conjuntos de instruções que podem ser executados alternadamente em muitos
navegadores. Aqui está uma das instruções mais simples que você pode fazer:

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

#Este exemplo requer o Selenium WebDriver 3.13 ou mais novo
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

class HelloSelenium {
  static void Main() {
    using(IWebDriver driver = new FirefoxDriver()) {
      WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
      driver.Navigate().GoToUrl("https://www.google.com/ncr");
      driver.FindElement(By.Name("q")).SendKeys("cheese" + Keys.Enter);
      wait.Until(driver =>driver.FindElement(By.CssSelector("h3>div")).Displayed);
      IWebElement firstResult = driver.FindElement(By.CssSelector("h3>div"));
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


Veja o _[Tour rápido]({{<ref "/getting_started/quick.pt-br.md">}})_ para uma explicação completa
do que acontece nos bastidores quando você executa este código.
Você deve continuar para a [documentação narrativa]({{<ref "/introduction/_index.md">}})
para entender como você pode [instalar]({{<ref "/selenium_installation/_index.md">}}) e
usar o Selenium como uma ferramenta de automação de teste com sucesso,
e escalar testes simples como este para executar
em ambientes grandes e distribuídos em vários navegadores,
em vários sistemas operacionais diferentes.

## Introdução

Se você é um usuário novo de Selenium,
temos alguns recursos que podem te ajudar
a agilizar o seu aprendizado.

* [Tour rápido]({{< ref "/getting_started/quick.pt-br.md" >}})
  * [WebDriver]({{< ref "/getting_started/quick.pt-br.md#webdriver" >}})
  * [IDE]({{< ref "/getting_started/quick.pt-br.md#ide" >}})
  * [Grid]({{< ref "/getting_started/quick.pt-br.md#grid" >}})

