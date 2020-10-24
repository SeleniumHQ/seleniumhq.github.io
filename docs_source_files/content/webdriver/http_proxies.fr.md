---
title: "Proxys HTTP"
weight: 7
---

Un serveur proxy sert d'intermédiaire pour les demandes entre
un client et un serveur. En toute simplicité, le trafic circule
via le serveur proxy en route vers l'adresse
vous avez demandé et retour.

Un serveur proxy pour les scripts d'automatisation avec
Le Selenium pourrait être utile pour:

* Capturez le trafic réseau
* Mock backend calls made by the website
* Accéder au site Web demandé sous un réseau complexe
topologies ou restrictions / politiques d'entreprise strictes.

Si vous êtes dans un environnement d'entreprise et
le navigateur ne parvient pas à se connecter à une URL, c'est
très probablement parce que l'environnement a besoin d'un
proxy à accéder.

Selenium WebDriver permet d'accéder aux paramètres du proxy

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
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

  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# this code was written with Selenium 4

proxy = Selenium::WebDriver::Proxy.new(http: '<HOST:PORT>')
cap   = Selenium::WebDriver::Remote::Capabilities.chrome(proxy: proxy)

driver = Selenium::WebDriver.for(:chrome, capabilities: cap)
driver.get('http://google.com')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
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
  {{< / code-panel >}}
{{< / code-tab >}}
