---
title: "Chrome开发工具"
linkTitle: "Chrome开发工具"
weight: 5
aliases: ["/documentation/zh-cn/support_packages/chrome_devtools/"]
---

在Selenium 4 alpha版本通过对Chrome开发工具协议（Chrome DevTools Protocol）
的支持添加了大家期待已久的源生Chrome开发工具“DevTools”调用。这将帮助我们获取Chrome开发属性集例如：
应用程序缓存、获取、网络、性能、探查器、资源计时、安全性和目标CDP域等。

Chrome开发工具是在谷歌Chrome浏览其中内置的网页开发工具集。开发工具可以帮助你快速编辑页面和诊断问题，最终帮助你更快地建立更好的网站。

## 模拟 Geo location 定位:

有一些应用在不同的定位下有不同的特性和功能。想通过Selenium在浏览器中模拟geo locations定位是从而实现应用的自动化是非常困难的。
但是通过开发工具的帮助，我们可以非常简单的模拟它们。下面的代码判断演示了这一点。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public void geoLocationTest(){
  ChromeDriver driver = new ChromeDriver();
  Map coordinates = new HashMap()
  {{
      put("latitude", 50.2334);
      put("longitude", 0.2334);
      put("accuracy", 1);
  }};    
  driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
  driver.get("<your site url>");
}  
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
from selenium.webdriver.chrome.service import Service

def geoLocationTest():
driver = webdriver.Chrome()
Map_coordinates = dict({
"latitude": 41.8781,
"longitude": -87.6298,
"accuracy": 100
})
driver.execute_cdp_cmd("Emulation.setGeolocationOverride", Map_coordinates)
driver.get("<your site url>")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using System.Threading.Tasks;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.DevTools;
// Replace the version to match the Chrome version
using OpenQA.Selenium.DevTools.V87.Emulation;

namespace dotnet_test {
  class Program {
    public static void Main(string[] args) {
      GeoLocation().GetAwaiter().GetResult();
    }

    public static async Task GeoLocation() {
      ChromeDriver driver = new ChromeDriver();
      DevToolsSession devToolsSession = driver.CreateDevToolsSession();
      var geoLocationOverrideCommandSettings = new SetGeolocationOverrideCommandSettings();

      geoLocationOverrideCommandSettings.Latitude = 51.507351;
      geoLocationOverrideCommandSettings.Longitude = -0.127758;
      geoLocationOverrideCommandSettings.Accuracy = 1;

      await devToolsSession
        .GetVersionSpecificDomains<OpenQA.Selenium.DevTools.V87.DevToolsSessionDomains>()
        .Emulation
        .SetGeolocationOverride(geoLocationOverrideCommandSettings);

        driver.Url = "<your site url>";
        }
    }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

begin
  # Latitude and longitude of Tokyo, Japan
  coordinates = { latitude: 35.689487,
                  longitude: 139.691706,
                  accuracy: 100 }
  driver.execute_cdp('Emulation.setGeolocationOverride', coordinates)
  driver.get 'https://www.google.com/search?q=selenium'
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder } = require("selenium-webdriver");

async function geoLocationTest() {
  const driver = await new Builder().forBrowser("chrome").build();
  await driver.get("http://www.google.com");
  const pageCdpConnection = await driver.createCDPConnection("page");
  //Latitude and longitude of Tokyo, Japan
  const coordinates = {
    latitude: 35.689487,
    longitude: 139.691706,
    accuracy: 100,
  };
  await pageCdpConnection.execute(
    "Emulation.setGeolocationOverride",
    1,
    coordinates
  );
}

geoLocationTest(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.devtools.DevTools

fun main() {
    val driver =  ChromeDriver()
    val coordinates : HashMap<String, Any> = HashMap<String, Any> ()
    coordinates.put("latitude", 50.2334)
    coordinates.put("longitude", 0.2334)
    coordinates.put("accuracy", 1)
    driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates)
    driver.get("https://www.google.com")
}
  {{< /tab >}}
{{< /tabpane >}}

## 注册基本认证

一些应用要求某些页面基于认证过的状态,
而大多数时候为了保持简单,
开发者使用基本认证.
通过Selenium和开发者工具的集成,
您可以在出现自动认证的时候自动进行输入.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
Predicate<URI> uriPredicate = uri -> uri.getHost().contains("your-domain.com");

((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("admin", "password"));
driver.get("https://your-domain.com/login");
  {{< /tab >}}
  {{< tab header="Python" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="CSharp" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const pageCdpConnection = await driver.createCDPConnection('page')

await driver.register('username', 'password', pageCdpConnection)
await driver.get(server.url())
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val uriPredicate =
    Predicate { uri: URI ->
        uri.host.contains("your-domain.com")
    }
(driver as HasAuthentication).register(uriPredicate, UsernameAndPassword.of("admin", "password"))
driver.get("https://your-domain.com/login")
  {{< /tab >}}
{{< /tabpane >}}

## 监听页面元素的事件

通过Selenium和开发者工具的集成,
可以监听DOM事件并注册回调以处理DOM事件.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="Python" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="CSharp" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cdpConnection = await driver.createCDPConnection('page')
await driver.logMutationEvents(cdpConnection, function(event) {
  assert.equal(event['attribute_name'], 'style')
  assert.equal(event['current_value'], '')
  assert.equal(event['old_value'], 'display:none;')
})

await driver.get(test.Pages.dynamicPage)

let element = driver.findElement({id: 'reveal'})
await element.click()
let revealed = driver.findElement({id: 'revealed'});
await driver.wait(until.elementIsVisible(revealed), 5000);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
{{< /tabpane >}}

## 监听页面JS异常

通过Selenium和开发者工具的集成,
可以监听JS异常并注册回调以处理异常详细信息.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public void jsExceptionsExample() {
    ChromeDriver driver = new ChromeDriver();
    DevTools devTools = driver.getDevTools();
    devTools.createSession();

    List<JavascriptException> jsExceptionsList = new ArrayList<>();
    Consumer<JavascriptException> addEntry = jsExceptionsList::add;
    devTools.getDomains().events().addJavascriptExceptionListener(addEntry);

    driver.get("<your site url>");

    WebElement link2click = driver.findElement(By.linkText("<your link text>"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
        link2click, "onclick", "throw new Error('Hello, world!')");
    link2click.click();

    for (JavascriptException jsException : jsExceptionsList) {
        System.out.println("JS exception message: " + jsException.getMessage());
        System.out.println("JS exception system information: " + jsException.getSystemInformation());
        jsException.printStackTrace();
    }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="CSharp" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cdpConnection = await driver.createCDPConnection('page')
await driver.onLogException(cdpConnection, function(event) {
  assert.equal(event['exceptionDetails']['stackTrace']['callFrames'][0]['functionName'], 'onmouseover')
})
await driver.get(test.Pages.javascriptPage)
let element = driver.findElement({id: 'throwing-mouseover'})
await element.click()
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
fun kotlinJsErrorListener() {
    val driver = ChromeDriver()
    val devTools = driver.devTools
    devTools.createSession()

    val logJsError = { j: JavascriptException -> print("Javascript error: '" + j.localizedMessage + "'.") }
    devTools.domains.events().addJavascriptExceptionListener(logJsError)

    driver.get("https://www.google.com")

    val link2click = driver.findElement(By.name("q"))
    (driver as JavascriptExecutor).executeScript(
        "arguments[0].setAttribute(arguments[1], arguments[2]);",
        link2click, "onclick", "throw new Error('Hello, world!')"
    )
    link2click.click()

    driver.quit()
}
  {{< /tab >}}
{{< /tabpane >}}

## 监听页面的console.log事件

通过Selenium和开发者工具的集成,
可以监听console.log事件并注册回调以处理该事件.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v87.log.Log;

public void consoleLogTest() {
ChromeDriver driver = new ChromeDriver();
DevTools devTools = driver.getDevTools();
devTools.createSession();

    devTools.send(Log.enable());
    devTools.addListener(Log.entryAdded(),
            logEntry -> {
                System.out.println("log: "+logEntry.getText());
                System.out.println("level: "+logEntry.getLevel());
            });
}
  {{< /tab >}}
  {{< tab header="Python" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="CSharp" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cdpConnection = await driver.createCDPConnection('page')
await driver.onLogEvent(cdpConnection, function(event) {
  assert.equal(event['args'][0]['value'], 'here')
})
await driver.executeScript('console.log("here")')
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
fun kotlinConsoleLogExample() {
    val driver = ChromeDriver()
    val devTools = driver.devTools
    devTools.createSession()

    val logConsole = { c: ConsoleEvent -> print("Console log message is: " + c.messages)}
    devTools.domains.events().addConsoleListener(logConsole)

    driver.get("https://www.google.com")

    val executor = driver as JavascriptExecutor
    executor.executeScript("console.log('Hello World')")

    val input = driver.findElement(By.name("q"))
    input.sendKeys("Selenium 4")
    input.sendKeys(Keys.RETURN)
    driver.quit()
}
  {{< /tab >}}
{{< /tabpane >}}

## Override Device Mode

Using Selenium's integration with CDP, one can override the current device mode and simulate a 
new mode. Width, height, mobile, and deviceScaleFactor are required parameters. Optional 
parameters include scale, screenWidth, screenHeight, positionX, positionY, dontSetVisible, 
screenOrientation, viewport, and displayFeature.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public void deviceSimulationTest() {
    ChromeDriver driver = (ChromeDriver) Driver.getDriver();
    tools = driver.getDevTools();
    tools.createSession();

    Map deviceMetrics = new HashMap()
    {{
        put("width", 600);
        put("height", 1000);
        put("mobile", true);
        put("deviceScaleFactor", 50);
    }};

    driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
    driver.get("https://www.google.com");
}
{{< /tab >}}
{{< tab header="Python" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="CSharp" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="Ruby" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="JavaScript" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="Kotlin" >}}
fun kotlinOverridDeviceMode() {
  val driver = ChromeDriver()

  val deviceMetrics: Map<String, Any> = object : HashMap<String, Any>() {
    init {
        put("width", 600)
        put("height", 1000)
        put("mobile", true)
        put("deviceScaleFactor", 50)
    }
  }

  driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics)
  driver.get("https://www.google.com")
  driver.quit()
}
{{< /tab >}}
{{< /tabpane >}}

## Collect Performance Metrics

Using Selenium's integration with CDP, one can collect various performance metrics while navigating the application.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public void performanceMetricsExample() {
    ChromeDriver driver = new ChromeDriver();
    DevTools devTools = driver.getDevTools();
    devTools.createSession();
    devTools.send(Performance.enable(Optional.empty()));
    List<Metric> metricList = devTools.send(Performance.getMetrics());

    driver.get("https://google.com");
    driver.quit();

    for(Metric m : metricList) {
        System.out.println(m.getName() + " = " + m.getValue());
    }
}
{{< /tab >}}
{{< tab header="Python" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="CSharp" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="Ruby" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="JavaScript" >}}
# Please raise a PR to add code sample
{{< /tab >}}
{{< tab header="Kotlin" >}}
val driver = ChromeDriver()
val devTools = driver.devTools
devTools.createSession()
devTools.send(Performance.enable(Optional.empty()))
val metricList: List<Metric> = devTools.send(Performance.getMetrics())

driver["https://google.com"]
driver.quit()

for (m in metricList) {
    println(m.name.toString() + " = " + m.value)
}
{{< /tab >}}
{{< /tabpane >}}
