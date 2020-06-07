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
 # Need PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// need PR
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
