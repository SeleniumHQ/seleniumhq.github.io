---
title: "Chrome DevTools Protocol"
linkTitle: "Chrome DevTools Protocol"
weight: 5
aliases: [
"/documentation/ja/support_packages/chrome_devtools/",
"/ja/documentation/support_packages/chrome_devtools/"
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
    Page being translated from English to Japanese. 
    Do you speak Japanese? Help us to translate
    it by sending us pull requests!
</p>
{{% /pageinfo %}}

{{% pageinfo color="warning" %}}
While Selenium 4 provides direct access to the Chrome DevTools Protocol (CDP), it is
highly encouraged that you use the [WebDriver Bidi APIs]({{< ref "bidi_api.md" >}}) instead.
{{% /pageinfo %}}

Many browsers provide "DevTools" -- a set of tools that are integrated with the browser that 
developers can use to debug web apps and explore the performance of their pages. Google Chrome's 
DevTools make use of a protocol called the Chrome DevTools Protocol (or "CDP" for short). 
As the name suggests, this is not designed for testing, nor to have a stable API, so functionality 
is highly dependent on the version of the browser.

WebDriver Bidi is the next generation of the W3C WebDriver protocol and aims to provide a stable API 
implemented by all browsers, but it's not yet complete. Until it is, Selenium provides access to 
the CDP for those browsers that implement it (such as Google Chrome, or Microsoft Edge, and 
Firefox), allowing you to enhance your tests in interesting ways. Some examples of what you can 
do with it are given below.

## Emulate Geo Location

Some applications have different features and functionalities across different 
locations. Automating such applications is difficult because it is hard to emulate 
the geo-locations in the browser using Selenium. But with the help of Devtools, 
we can easily emulate them. Below code snippet demonstrates that.

{{< tabpane langEqualsHeader=true code=false >}}
  {{< tab header="Java" code=true >}}
ChromeDriver driver = new ChromeDriver();
DevTools devTools = driver.getDevTools();
devTools.createSession();
devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043),
                                               Optional.of(13.4501),
                                               Optional.of(1)));
driver.get("https://my-location.org/");
driver.quit();
  {{< /tab >}}
  {{< tab header="Python" code=true >}}
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
  {{< tab header="CSharp" code=true >}}
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
  {{< tab header="Ruby" code=true >}}
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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/emulateGeoLocation.spec.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" code=true >}}
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

## Emulate Geo Location with the Remote WebDriver:

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

## Override Device Mode

Using Selenium's integration with CDP, one can override the current device 
mode and simulate a new mode. Width, height, mobile, and deviceScaleFactor 
are required parameters. Optional parameters include scale, screenWidth, 
screenHeight, positionX, positionY, dontSetVisible, screenOrientation, viewport, and displayFeature.

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
from selenium import webdriver

driver = webdriver.Chrome()

driver.get('https://www.duckduckgo.com')
driver.execute_cdp_cmd('Performance.enable', {})
t = driver.execute_cdp_cmd('Performance.getMetrics', {})
print(t)
driver.quit()
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

