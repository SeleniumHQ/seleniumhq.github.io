---
title: "Chrome开发工具协议"
linkTitle: "Chrome开发工具"
weight: 5
aliases: [
"/documentation/zh-cn/support_packages/chrome_devtools/",
"/zh-cn/documentation/support_packages/chrome_devtools/"
]
---

{{% pageinfo color="warning" %}}
虽然Selenium 4提供了对Chrome DevTools Protocol (CDP) 的直接访问，
但是仍非常鼓励您使用 
[WebDriver Bidi APIs]({{< ref "bidi_api.md" >}}) 
代替.
{{% /pageinfo %}}

许多浏览器都提供"开发工具" -- 一组与浏览器集成的工具,
开发人员可以用其调试web应用程序并探索其页面的性能. 
谷歌浏览器开发工具
使用一种称为Chrome DevTools Protocol (简称"CDP") 的协议. 
顾名思义, 这不是为测试而设计的,
而并没有一个稳定的API,
所以它的功能高度依赖于浏览器的版本. 

WebDriver Bidi是W3C WebDriver的下一代协议,
旨在提供由所有浏览器实现稳定的API, 但尚未完成.
在此之前, Selenium提供了通过CDP实现的方式
(诸如Google Chrome或Microsoft Edge, 以及Firefox), 
允许您以有趣的方式增强测试.
下面给出了实际使用的例子.

## 模拟地理位置

一些应用程序在不同的位置具有不同的特性和功能.
自动化此类应用程序很难,
因为很难使用Selenium在浏览器中模拟地理位置.
但是在Devtools的帮助下,
我们可以轻易模拟他们.
下面的代码片段演示了这一点.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
ChromeDriver driver = new ChromeDriver();
DevTools devTools = driver.getDevTools();
devTools.createSession();
devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043),
                                               Optional.of(13.4501),
                                               Optional.of(1)));
driver.get("https://my-location.org/");
driver.quit();
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
  {{< tab header="JavaScript" disableCodeBlock=true >}}
    {{< gh-codeblock path="/examples/javascript/bidirectional/emulateGeoLocation.js">}}
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

## 通过远程WebDriver模拟地理位置

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
ChromeOptions chromeOptions = new ChromeOptions();
WebDriver driver = new RemoteWebDriver(new URL("<grid-url>"), chromeOptions);
driver = new Augmenter().augment(driver);

DevTools devTools = ((HasDevTools) driver).getDevTools();
devTools.createSession();

devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043),
                                               Optional.of(13.4501),
                                               Optional.of(1)));
 
driver.get("https://my-location.org/");
driver.quit();
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver
#Replace the version to match the Chrome version
import selenium.webdriver.common.devtools.v93 as devtools

async def geoLocationTest():
    chrome_options = webdriver.ChromeOptions()
    driver = webdriver.Remote(
        command_executor='<grid-url>',
        options=chrome_options
    )

    async with driver.bidi_connection() as session:
        cdpSession = session.session
        await cdpSession.execute(devtools.emulation.set_geolocation_override(latitude=41.8781,longitude=-87.6298,accuracy=100))
    driver.get("https://my-location.org/")
    driver.quit()
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
      ChromeOptions chromeOptions = new ChromeOptions();
      RemoteWebDriver driver = new RemoteWebDriver(new Uri("<grid-url>"), chromeOptions);
      DevToolsSession devToolsSession = driver.CreateDevToolsSession();
      var geoLocationOverrideCommandSettings = new SetGeolocationOverrideCommandSettings();

      geoLocationOverrideCommandSettings.Latitude = 51.507351;
      geoLocationOverrideCommandSettings.Longitude = -0.127758;
      geoLocationOverrideCommandSettings.Accuracy = 1;

      await devToolsSession
        .GetVersionSpecificDomains<OpenQA.Selenium.DevTools.V87.DevToolsSessionDomains>()
        .Emulation
        .SetGeolocationOverride(geoLocationOverrideCommandSettings);

        driver.Url = "https://my-location.org/";
        }
    }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}

driver = Selenium::WebDriver.for(
:remote, 
:url => "<grid-url>",
:capabilities => :chrome)

begin
  # Latitude and longitude of Tokyo, Japan
  coordinates = { latitude: 35.689487,
                  longitude: 139.691706,
                  accuracy: 100 }
  devToolsSession = driver.devtools
  devToolsSession.send_cmd('Emulation.setGeolocationOverride', coordinates)
  driver.get 'https://my-location.org/'
  puts res
ensure
  driver.quit
end

  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const webdriver = require('selenium-webdriver');
const BROWSER_NAME = webdriver.Browser.CHROME;

async function getDriver() {
  return new webdriver.Builder()
  .usingServer('<grid-url>')
  .forBrowser(BROWSER_NAME)
  .build();
}

async function executeCDPCommands () {
 let driver = await getDriver();

 await driver.get("<your site url>");
 
 const cdpConnection = await driver.createCDPConnection('page');
  //Latitude and longitude of Tokyo, Japan
  const coordinates = {
    latitude: 35.689487,
    longitude: 139.691706,
    accuracy: 100,
  };
  await cdpConnection.execute(
    "Emulation.setGeolocationOverride",
    coordinates
  );
 await driver.quit();
}

executeCDPCommands(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.devtools.HasDevTools
// Replace the version to match the Chrome version
import org.openqa.selenium.devtools.v91.emulation.Emulation
import org.openqa.selenium.remote.Augmenter
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL
import java.util.Optional

fun main() {
    val chromeOptions = ChromeOptions()
    var driver: WebDriver = RemoteWebDriver(URL("<grid-url>"), chromeOptions)
    driver = Augmenter().augment(driver)

    val devTools = (driver as HasDevTools).devTools
    devTools.createSession()

    devTools.send(
        Emulation.setGeolocationOverride(
            Optional.of(52.5043),
            Optional.of(13.4501),
            Optional.of(1)
        )
    )

    driver["https://my-location.org/"]
    driver.quit()
}

  {{< /tab >}}
{{< /tabpane >}}

## 覆盖设备模式

使用Selenium与CDP的集成,
可以覆盖当前设备模式并模拟新模式. 
Width, height, mobile和deviceScaleFactor是必需的参数. 
可选参数包括scale, screenWidth,
screenHeight, positionX, positionY,
dontSetVisible, screenOrientation, viewport和displayFeature.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
ChromeDriver driver = new ChromeDriver();
DevTools devTools = driver.getDevTools();
devTools.createSession();
// iPhone 11 Pro dimensions
devTools.send(Emulation.setDeviceMetricsOverride(375,
                                                 812,
                                                 50,
                                                 true,
                                                 Optional.empty(),
                                                 Optional.empty(),
                                                 Optional.empty(),
                                                 Optional.empty(),
                                                 Optional.empty(),
                                                 Optional.empty(),
                                                 Optional.empty(),
                                                 Optional.empty(),
                                                 Optional.empty()));
driver.get("https://selenium.dev/");
driver.quit();
{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver

driver = webdriver.Chrome()
// iPhone 11 Pro dimensions
set_device_metrics_override = dict({
"width": 375,
"height": 812,
"deviceScaleFactor": 50,
"mobile": True
})
driver.execute_cdp_cmd('Emulation.setDeviceMetricsOverride', set_device_metrics_override)
driver.get("<your site url>")
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.DevTools;
using System.Threading.Tasks;
using OpenQA.Selenium.DevTools.V91.Emulation;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;
using DevToolsSessionDomains = OpenQA.Selenium.DevTools.V91.DevToolsSessionDomains;

namespace Selenium4Sample {
public class ExampleDevice {

    protected IDevToolsSession session;
    protected IWebDriver driver;
    protected DevToolsSessionDomains devToolsSession;

    public async Task DeviceModeTest() {
      new DriverManager().SetUpDriver(new ChromeConfig());
      ChromeOptions chromeOptions = new ChromeOptions();
      //Set ChromeDriver
      driver = new ChromeDriver();
      //Get DevTools
      IDevTools devTools = driver as IDevTools;
      //DevTools Session
      session = devTools.GetDevToolsSession();

      var deviceModeSetting = new SetDeviceMetricsOverrideCommandSettings();
      deviceModeSetting.Width = 600;
      deviceModeSetting.Height = 1000;
      deviceModeSetting.Mobile = true;
      deviceModeSetting.DeviceScaleFactor = 50;

      await session
            .GetVersionSpecificDomains < OpenQA.Selenium.DevTools.V91.DevToolsSessionDomains > ()
            .Emulation
            .SetDeviceMetricsOverride(deviceModeSetting);

      driver.Url = "<your site url>";
    }
}
}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

begin
  metrics = { width: 300,
              height: 200,
              mobile: true,
              deviceScaleFactor: 50 }
  driver.execute_cdp('Emulation.setDeviceMetricsOverride', metrics)
  driver.get 'https://www.google.com'
ensure
  driver.quit
end
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder} = require('selenium-webdriver');
const firefox = require('selenium-webdriver/firefox');
const options = new firefox.Options();
// enable debugger for CDP
options.enableDebugger();

(async function example() {
  try {
    let driver = await new Builder().forBrowser('firefox').setFirefoxOptions(options).build();
    const pageCdpConnection = await driver.createCDPConnection('page');
    const metrics = {
      width: 300,
      height: 200,
      deviceScaleFactor: 50,
      mobile: true,
    };
    await pageCdpConnection.execute(
      "Emulation.setDeviceMetricsOverride",
      metrics
    );
    await driver.get("https://www.google.com");
    await driver.quit();
  } catch (e) {
    console.log(e);
  }
})();
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

Collect various performance metrics while navigating the application.

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
// File must contain the following using statements
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.DevTools;

// We must use a version-specific set of domains
using OpenQA.Selenium.DevTools.V94.Performance;

public async Task PerformanceMetricsExample()
{
    IWebDriver driver = new ChromeDriver();
    IDevTools devTools = driver as IDevTools;
    DevToolsSession session = devTools.GetDevToolsSession();
    await session.SendCommand<EnableCommandSettings>(new EnableCommandSettings());
    var metricsResponse =
        await session.SendCommand<GetMetricsCommandSettings, GetMetricsCommandResponse>(
            new GetMetricsCommandSettings());

    driver.Navigate().GoToUrl("http://www.google.com");
    driver.Quit();

    var metrics = metricsResponse.Metrics;
    foreach (Metric metric in metrics)
    {
        Console.WriteLine("{0} = {1}", metric.Name, metric.Value);
    }
}
{{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.duckduckgo.com'
  driver.execute_cdp('Performance.enable', {})
  metrics = driver.execute_cdp('Performance.getMetrics', {})
  puts metrics
ensure
  driver.quit
end
{{< /tab >}}
{{< tab header="JavaScript" >}}
await driver.get("https://www.duckduckgo.com");

await driver.sendAndGetDevToolsCommand('Performance.enable')

let result = await driver.sendAndGetDevToolsCommand('Performance.getMetrics')
console.log(result)

await driver.quit();
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

