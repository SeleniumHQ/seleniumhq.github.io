---
title: "Http 프록시"
weight: 7
---

프록시 서버는 클라이언트와 서버 간 요청(request)을 중개하는 역할을 합니다. 
즉, 트래픽이 대상 주소를 오갈 때 프록시 서버를 거쳐서 이동한다는 것입니다.

Selenium 자동화 스크립트를 위해 프록시 서버를 구성하면 다음과 같은 이점이 
있습니다.

* 네트워크 트래픽을 들여다보고 확인할 수 있습니다.
* 웹 사이트의 서버 호출을 모사(mock)할 수 있습니다.
* 복잡하게 구성된 네트워크 환경에서나 엄격한 규칙과 정책이 있는 회사에서 
대상 웹 사이트에 접근할 수 있습니다.

회사 내에서 어떤 URL에 접근하는 데 실패했다면, 그 이유는 대개 프록시가 
필요한 환경이기 때문일 겁니다.

Selenium WebDriver로 다음과 같이 프록시를 설정할 수 있습니다.

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
    # URL 열기
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
# 이 코드는 Selenium 4를 사용하여 작성되었습니다.

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
