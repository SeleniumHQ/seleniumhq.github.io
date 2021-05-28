---
title: "Chrome开发工具"
weight: 10
---

在Selenium 4 alpha版本通过对Chrome开发工具协议（Chrome DevTools Protocol）的支持添加了大家期待已久的源生Chrome开发工具“DevTools”调用。这将帮助我们获取Chrome开发属性集例如：应用程序缓存、获取、网络、性能、探查器、资源计时、安全性和目标CDP域等。

Chrome开发工具是在谷歌Chrome浏览其中内置的网页开发工具集。开发工具可以帮助你快速编辑页面和诊断问题，最终帮助你更快地建立更好的网站。

## 模拟 Geo location 定位:

有一些应用在不同的定位下有不同的特性和功能。想通过Selenium在浏览器中模拟geo locations定位是从而实现应用的自动化是非常困难的。但是通过开发工具的帮助，我们可以非常简单的模拟它们。下面的代码判断演示了这一点。

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

## 注册基本认证

一些应用要求某些页面基于认证过的状态, 
而大多数时候为了保持简单, 
开发者使用基本认证. 
通过Selenium和开发者工具的集成, 
您可以在出现自动认证的时候自动进行输入.

{{< code-tab >}}
  {{< code-panel language="java" >}}
Predicate<URI> uriPredicate = uri -> uri.getHost().contains("your-domain.com");

((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("admin", "password"));
driver.get("https://your-domain.com/login");
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

## 监听页面元素的事件

通过Selenium和开发者工具的集成,
可以监听DOM事件并注册回调以处理DOM事件.

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

## 监听页面JS异常

通过Selenium和开发者工具的集成,
可以监听JS异常并注册回调以处理异常详细信息.

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

## 监听页面的console.log事件

通过Selenium和开发者工具的集成,
可以监听console.log事件并注册回调以处理该事件.

{{< code-tab >}}
  {{< code-panel language="java" >}}
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

## Override Device Mode

Using Selenium's integration with CDP, one can override the current device mode and simulate a new mode. Width, height, mobile, and deviceScaleFactor are required parameters. Optional parameters include scale, screenWidth, screenHeight, positionX, positionY, dontSetVisible, screenOrientation, viewport, and displayFeature.

{{< code-tab >}}
{{< code-panel language="java" >}}
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
# Please raise a PR to add code sample
{{< / code-panel >}}
{{< code-panel language="kotlin" >}}
# Please raise a PR to add code sample
{{< / code-panel >}}
{{< / code-tab >}}
