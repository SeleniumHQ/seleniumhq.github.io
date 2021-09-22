---
title: "Chrome Devtools"
linkTitle: "Chrome Devtools"
weight: 5
aliases: ["/documentation/en/support_packages/chrome_devtools/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
  While Selenium 4 provides direct access to Chrome DevTools Protocol, it is highly encouraged that
you use the <a href="../../webdriver/bidi_apis">WebDriver Bidi APIs</a> instead.
</p>
{{% /pageinfo %}}

Chrome DevTools is a set of web developer tools built directly into the Google
Chrome browser. The implementation is not designed for testing, nor to have a stable API,
so functionality is highly dependent on the version of the browser.
If there is DevTools functionality that is not yet covered by a Bidi API, though, you can
use DevTools directly to access properties such as Application Cache, Fetch, Network, Performance, Profiler, 
Resource Timing, Security and Target CDP domains, etc. Here are a couple examples.

## Emulate Geo Location

Some applications have different features and functionalities across different 
locations. Automating such applications is difficult because it is hard to emulate 
the geo locations in the browser using Selenium. But with the help of Devtools, 
we can easily emulate them. Below code snippet demonstrates that.

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

## Override Device Mode

Using Selenium's integration with CDP, one can override the current device 
mode and simulate a new mode. Width, height, mobile, and deviceScaleFactor 
are required parameters. Optional parameters include scale, screenWidth, 
screenHeight, positionX, positionY, dontSetVisible, screenOrientation, viewport, and displayFeature.

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
