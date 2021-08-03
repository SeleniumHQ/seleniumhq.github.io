---
title: "Chrome Devtools"
linkTitle: "Chrome Devtools"
weight: 5
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to German. Do you speak German? Help us to translate
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

## Emulate Geo Location:

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
// Please raise a PR to add code sample  
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

## Register Basic Auth:

Some applications require to keep some pages behind an auth and most of the time 
to keep things simple, a developer uses Basic Auth. With Selenium and devtools 
integration, you can automate the input of basic auth credentials whenever they arise.

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
# Please raise a PR to add code sample
  {{< /tab >}}
{{< /tabpane >}}

## Listen to DOM events on a web page

Using Selenium's integration with CDP, one can listen to the DOM events and 
register callbacks to process the DOM event.

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

## Listen to JS Exceptions on a web page

Using Selenium's integration with CDP, one can listen to the JS Exceptions 
and register callbacks to process the exception details.

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

## Listen to console.log events on a web page

Using Selenium's integration with CDP, one can listen to the `console.log` 
events and register callbacks to process the event.

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

Using Selenium's integration with CDP, one can collect various performance 
metrics while navigating the application.

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
# Please raise a PR to add code sample
{{< /tab >}}
{{< /tabpane >}}
