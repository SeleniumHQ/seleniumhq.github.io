---
title: "Http proxies"
weight: 7
---

A proxy server acts as an intermediary for requests between a client and a server.
In simple, the traffic flows through the proxy server on its way to the address you
requested and back.

A proxy server for automation scripts with Selenium could be helpful for:
- Capture network traffic
- Mock backend calls made by the website
- Access the required website under complex network topologies or strict
corporate restrictions/policies.

If you are in a corporate environment, and a browser fails to connect to
a URL, this is most likely because the environment needs a proxy to be
accessed.

Selenium WebDriver provides a way to proxy settings

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
