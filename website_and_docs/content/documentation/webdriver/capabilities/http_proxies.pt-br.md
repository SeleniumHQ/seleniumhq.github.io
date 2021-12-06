---
title: "Proxies Http"
linkTitle: "Proxies Http"
weight: 7
aliases: [
"/documentation/pt-br/webdriver/http_proxies/",
"/pt-br/documentation/webdriver/http_proxies/"
]
---

Um servidor proxy atua como intermediário para
solicitações entre um cliente e um servidor. De forma simples,
o tráfego flui através do servidor proxy
a caminho do endereço que você solicitou e de volta.

Um servidor proxy para scripts de automação
com Selenium pode ser útil para:

* Capturar o tráfego da rede
* Simular chamadas de back-end feitas pelo site
* Acessar o site necessário em uma rede complexa
topologias ou restrições / políticas corporativas estritas.

Se você estiver em um ambiente corporativo, e um
navegador não consegue se conectar a um URL, isso é
provavelmente porque o ambiente precisa de um proxy para ser acessado.

O Selenium WebDriver fornece uma maneira de configurações de proxy:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class proxyTest {
  public static void main(String[] args) {
    Proxy proxy = new Proxy();
    proxy.setHttpProxy("<HOST:PORT>");
    ChromeOptions options = new ChromeOptions();
    options.setCapability("proxy", proxy);
    WebDriver driver = new ChromeDriver(options);
    driver.get("https://www.google.com/");
    driver.manage().window().maximize();
    driver.quit();
  }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

PROXY = "<HOST:PORT>"
webdriver.DesiredCapabilities.FIREFOX['proxy'] = {
    "httpProxy": PROXY,
    "ftpProxy": PROXY,
    "sslProxy": PROXY,
    "proxyType": "MANUAL",

}

with webdriver.Firefox() as driver:
    # Open URL
    driver.get("https://selenium.dev")

  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

public class ProxyTest{
  public static void Main() {
    ChromeOptions options = new ChromeOptions();
    Proxy proxy = new Proxy();
    proxy.Kind = ProxyKind.Manual;
    proxy.IsAutoDetect = false;
    proxy.SslProxy = "<HOST:PORT>";
    options.Proxy = proxy;
    options.AddArgument("ignore-certificate-errors");
    IWebDriver driver = new ChromeDriver(options);
    driver.Navigate().GoToUrl("https://www.selenium.dev/");
  }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# este código foi escrito com Selenium 4

proxy = Selenium::WebDriver::Proxy.new(http: '<HOST:PORT>')
cap   = Selenium::WebDriver::Remote::Capabilities.chrome(proxy: proxy)

driver = Selenium::WebDriver.for(:chrome, capabilities: cap)
driver.get('http://google.com')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
let webdriver = require('selenium-webdriver');
let chrome = require('selenium-webdriver/chrome');
let proxy = require('selenium-webdriver/proxy');
let opts = new chrome.Options();

(async function example() {
  opts.setProxy(proxy.manual({http: '<HOST:PORT>'}));
  let driver = new webdriver.Builder()
    .forBrowser('chrome')
    .setChromeOptions(opts)
    .build();
  try {
    await driver.get("https://selenium.dev");
  }
  finally {
   await driver.quit();
  }
}());
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.Proxy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class proxyTest {
    fun main() {

        val proxy = Proxy()
        proxy.setHttpProxy("<HOST:PORT>")
        val options = ChromeOptions()
        options.setCapability("proxy", proxy)
        val driver: WebDriver = ChromeDriver(options)
        driver["https://www.google.com/"]
        driver.manage().window().maximize()
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}
