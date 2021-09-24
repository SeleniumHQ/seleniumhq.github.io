---
title: "Chrome Devtools with Remote WebDriver"
linkTitle: "Chrome Devtools with Remote WebDriver"
weight: 5
aliases: ["/documentation/en/support_packages/chrome_devtools_remote/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Spanish. Do you speak Spanish? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Selenium 4 alpha versions have much awaited native support for Chrome DevTools 
Protocol through "DevTools" interface. This helps us getting Chrome Development 
properties such as Application Cache, Fetch, Network, Performance, Profiler, 
Resource Timing, Security and Target CDP domains etc.

Chrome DevTools is a set of web developer tools built directly into the Google 
Chrome browser. DevTools can help you edit pages on-the-fly and diagnose 
problems quickly, which ultimately helps you build better websites, faster.

The examples below demonstrate CDP support with the Grid using the Remote WebDriver.

## Emulate Geo Location:

Some applications have different features and functionalities across different 
locations. Automating such applications is difficult because it is hard to emulate 
the geo locations in the browser using Selenium. But with the help of Devtools, 
we can easily emulate them. Below code snippet demonstrates that.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.chrome.ChromeOptions;
  import org.openqa.selenium.devtools.DevTools;
  import org.openqa.selenium.devtools.HasDevTools;
  // Replace the version to match the Chrome version
  import org.openqa.selenium.devtools.v93.emulation.Emulation;
  import org.openqa.selenium.remote.Augmenter;
  import org.openqa.selenium.remote.RemoteWebDriver;

  public void geoLocationTest() {
   ChromeOptions chromeOptions = new ChromeOptions();
   WebDriver driver = new RemoteWebDriver(
   new URL("<grid-url>"), 
   chromeOptions);
   driver = new Augmenter().augment(driver);
   
   DevTools devTools = ((HasDevTools) driver).getDevTools();
   devTools.createSession();

   Optional<Number> latitude  = Optional.of(50.2334);
   Optional<Number> longitude = Optional.of(0.2334);
   Optional<Number> accuracy = Optional.of(1);

   devTools.send(Emulation.setGeolocationOverride(latitude, longitude, accuracy));

   driver.get("<your site url>");
   driver.quit();
 }  
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
    driver.get("<your site url>")

    async with driver.bidi_connection() as session:
        cdpSession = session.session
        await cdpSession.execute(devtools.emulation.set_geolocation_override(latitude=41.8781,longitude=-87.6298,accuracy=100))
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

        driver.Url = "<your site url>";
        }
    }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}

# Please raise a PR to add code sample

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
    1,
    coordinates
  );
 await driver.quit();
}

executeCDPCommands(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

# Please raise a PR to add code sample

  {{< /tab >}}
{{< /tabpane >}}