---
title: "Chrome Devtools"
weight: 10
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Spanish. Do you speak Spanish? Help us to translate
it by sending us pull requests!
{{% /notice %}}

Selenium 4 alpha versions have much awaited native support for Chrome Dev Protocol through "DevTools" interface. This helps us getting Chrome Development properties such as Application Cache, Fetch, Network, Performance, Profiler, Resource Timing, Security and Target CDP domains etc.

Chrome DevTools is a set of web developer tools built directly into the Google Chrome browser. DevTools can help you edit pages on-the-fly and diagnose problems quickly, which ultimately helps you build better websites, faster.

## Emulate Geo Location:

Some applications have different features and functionalities across different locations. Automating such applications is difficult because it is hard to emulate the geo locations in the browser using Selenium. But with the help of Devtools, we can easily emulate them. Below code snippet demonstrates that.

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// Please raise a PR to add code sample  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
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
  {{< / code-panel >}}
{{< / code-tab >}}

## Register Basic Auth:

Some applications require to keep some pages behind an auth and most of the time to keep things simple, a developer uses Basic Auth.
With Selenium and devtools integration, you can automate the input of basic auth credentials whenever they arise.

{{< code-tab >}}
  {{< code-panel language="java" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const pageCdpConnection = await driver.createCDPConnection('page')

await driver.register('username', 'password', pageCdpConnection)
await driver.get(server.url())
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
{{< / code-tab >}}

## Listen to DOM events on a web page

Using Selenium's integration with CDP, one can listen to the DOM events and register callbacks to process the DOM event.

{{< code-tab >}}
  {{< code-panel language="java" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
{{< / code-tab >}}

## Listen to JS Exceptions on a web page

Using Selenium's integration with CDP, one can listen to the JS Exceptions and register callbacks to process the exception details.

{{< code-tab >}}
  {{< code-panel language="java" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cdpConnection = await driver.createCDPConnection('page')
await driver.onLogException(cdpConnection, function(event) {
  assert.equal(event['exceptionDetails']['stackTrace']['callFrames'][0]['functionName'], 'onmouseover')
})
await driver.get(test.Pages.javascriptPage)
let element = driver.findElement({id: 'throwing-mouseover'})
await element.click()
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
{{< / code-tab >}}

## Listen to console.log events on a web page

Using Selenium's integration with CDP, one can listen to the `console.log` events and register callbacks to process the event.

{{< code-tab >}}
  {{< code-panel language="java" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cdpConnection = await driver.createCDPConnection('page')
await driver.onLogEvent(cdpConnection, function(event) {
  assert.equal(event['args'][0]['value'], 'here')
})
await driver.executeScript('console.log("here")')
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
{{< / code-tab >}}
