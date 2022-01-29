---
title: "O Projeto Selenium de Automação de Navegadores"
linkTitle: "Documentação"
cascade:
- type: docs
aliases: ["/documentation/pt-br/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Portuguese. Do you speak Portuguese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium é um projeto que abrange uma variedade de ferramentas e bibliotecas
que permitem e suportam a automação de navegadores da web.

Ele fornece extensões para emular a interação do usuário com os navegadores,
um servidor de distribuição para escalonar a alocação do navegador,
e a infraestrutura para implementações da [Especificação W3C WebDriver](//www.w3.org/TR/webdriver/)
que permite escrever código intercambiável para todos os principais navegadores da web.

Este projeto é possível graças a colaboradores voluntários
que dedicam milhares de horas de seu próprio tempo,
e disponibilizaram o código-fonte [disponível gratuitamente]({{< ref "copyright.md#license" >}})
para qualquer um usar, aproveitar e melhorar.

Selenium reúne criadores de navegadores, engenheiros e entusiastas
para promover uma discussão aberta sobre a automação da plataforma da web.
O projeto organiza [uma conferência anual](//seleniumconf.com/)
para ensinar e nutrir a comunidade.

No núcleo do Selenium está [WebDriver]({{< ref "/webdriver.md" >}}),
uma interface para escrever conjuntos de instruções que podem ser executados alternadamente em muitos
navegadores. Aqui está uma das instruções mais simples que você pode fazer:


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://selenium.dev");

        driver.quit();
    }
}
{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver


driver = webdriver.Chrome()

driver.get("http://selenium.dev")

driver.quit()

{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

class HelloSelenium {
    static void Main() {
        var driver = new ChromeDriver();

        driver.Navigate().GoToUrl("https://selenium.dev");

        driver.Quit();
    }
}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

driver.get 'https://selenium.dev'

driver.quit
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder, By, Key, until} = require('selenium-webdriver');

(async function helloSelenium() {
    let driver = await new Builder().forBrowser('chrome').build();

    await driver.get('https://selenium.dev');

    await driver.quit();
})();
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()

    driver.get("https://selenium.dev")

    driver.quit()
}
{{< /tab >}}
{{< /tabpane >}}


Consulte a [Visão Geral]({{< ref "overview" >}}) para verificar os diferentes componentes do projeto
e decidir se o Selenium é a ferramenta certa para você.

Você deve continuar no Guia de [Introdução]({{< ref "webdriver/getting_started" >}})
para entender como instalar o Selenium e usá-lo com sucesso como uma 
ferramenta de automação de teste e dimensionar testes simples como esse para serem executados em ambientes grandes 
e distribuídos em vários navegadores e em vários sistemas operacionais diferentes.
