---
title: "Remote WebDriver Client"
weight: 2
---

Um einen remote WebDriver Client zu starten, ist es notwendig eine Verbindung
zum Remote WebDriver aufzubauen. Das geschiet, indem eine Verbindung zu einer
URL aufgebaut wird, der die Testdurchführung vornimmt. Die gewünschte Konfiguration
wird mithilfe der Capabilities (= Einstellungen) definiert. Im folgenden Beispiel 
wird ein remote WebDriver Objekt instanziert, der mit dem remote Webserver, 
_www.example.com_, verbunden wird, um dort die Tests mit einem Firefox Browser auszuführen.

{{< code-tab >}}
  {{< code-panel language="java" >}}
FirefoxOptions firefoxOptions = new FirefoxOptions();
WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), firefoxOptions);
driver.get("http://www.google.com");
driver.quit();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

firefox_options = webdriver.FirefoxOptions()
driver = webdriver.Remote(
    command_executor='http://www.example.com',
    options=firefox_options
)
driver.get("http://www.google.com")
driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
 FirefoxOptions firefoxOptions = new FirefoxOptions();
 IWebDriver driver = new RemoteWebDriver(new Uri("http://www.example.com"), firefoxOptions);
 driver.Navigate().GoToUrl("http://www.google.com");
 driver.Quit();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :remote, url: "http://www.example.com", desired_capabilities: :firefox
driver.get "http://www.google.com"
driver.close
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const { Builder, Capabilities } = require("selenium-webdriver");
var capabilities = Capabilities.firefox();
(async function helloSelenium() {
    let driver = new Builder()        
        .usingServer("http://example.com")   
        .withCapabilities(capabilities)
        .build();
    try {
        await driver.get('http://www.google.com');
    } finally {
        await driver.quit();
    }
})(); 
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
firefoxOptions = FirefoxOptions()
driver: WebDriver = new RemoteWebDriver(new URL("http://www.example.com"), firefoxOptions)
driver.get("http://www.google.com")
driver.quit()
  {{< / code-panel >}}
{{< / code-tab >}}

Um die Testkonfiguration weiter anzupassen, können weitere Einstellungen 
hinzugefügt werden.

## Browser Optionen

Angenommen es ist gewünscht Chrome unter Windows XP in der 
Version 67 zu verwenden:


{{< code-tab >}}
  {{< code-panel language="java" >}}
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setCapability("browserVersion", "67");
chromeOptions.setCapability("platformName", "Windows XP");
WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), chromeOptions);
driver.get("http://www.google.com");
driver.quit();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

chrome_options = webdriver.ChromeOptions()
chrome_options.set_capability("browserVersion", "67")
chrome_options.set_capability("platformName", "Windows XP")
driver = webdriver.Remote(
    command_executor='http://www.example.com',
    options=chrome_options
)
driver.get("http://www.google.com")
driver.quit()  
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var chromeOptions = new ChromeOptions();
chromeOptions.BrowserVersion = "67";
chromeOptions.PlatformName = "Windows XP";
IWebDriver driver = new RemoteWebDriver(new Uri("http://www.example.com"), chromeOptions);
driver.Navigate().GoToUrl("http://www.google.com");
driver.Quit();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.platform = Windows XP
caps.version = 67

driver = Selenium::WebDriver.for :remote, :url => "http://www.example.com", :desired_capabilities => caps
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const { Builder } = require("selenium-webdriver");
const chrome = require("selenium-webdriver/chrome");
let opts = new chrome.Options();
opts.setAcceptInsecureCerts(true);
opts.setBrowserVersion('67');
opts.setPlatform('Windows XP');
(async function helloSelenium() {
    let driver = new Builder()
        .usingServer("http://example.com")
        .forBrowser('chrome')
        .setChromeOptions(opts)
        .build();
    try {
        await driver.get('http://www.google.com');
    }
    finally {
        await driver.quit();
    }
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val chromeOptions = ChromeOptions()
chromeOptions.setCapability("browserVersion", "67")
chromeOptions.setCapability("platformName", "Windows XP")
val driver: WebDriver = new RemoteWebDriver(new URL("http://www.example.com"), chromeOptions)
driver.get("http://www.google.com")
driver.quit()
  {{< / code-panel >}}
{{< / code-tab >}}


## Detektor für lokale Dateien

Der Detektor erlaubt Dateien vom Client zum remote Server zu übertragen.
Es kann z.B. notwendig sein eine Datei in einer Webapplikation hochzuladen.
Der remote WebDriver kann automatisch die Datei vom lokalen Rechner zum 
remote Rechner hochladen, während der Testdurchführung. Dadurch ist es möglich
die Datei vom remote Rechner, der die Tests durchführt, hochzuladen.
Standardmässig die dies nicht aktiviert, kann jedoch wie folgt aktiviert 
werden: 


{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.setFileDetector(new LocalFileDetector());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.remote.file_detector import LocalFileDetector

driver.file_detector = LocalFileDetector()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var allowsDetection = this.driver as IAllowsFileDetection;
if (allowsDetection != null)
{
   allowsDetection.FileDetector = new LocalFileDetector();
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
@driver.file_detector = lambda do |args|
  # args => ["/path/to/file"]
  str = args.first.to_s
  str if File.exist?(str)
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
var remote = require('selenium-webdriver/remote');
driver.setFileDetector(new remote.FileDetector);  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.fileDetector = LocalFileDetector()
  {{< / code-panel >}}
{{< / code-tab >}}

Dieser Programmcode, ermöglicht es eine Datei während den Tests
hochzuladen, indem der folgende Programmcode verwendet wird:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
WebElement upload = driver.findElement(By.id("myfile"));
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload")

driver.find_element(By.ID, "myfile").send_keys("/Users/sso/the/local/path/to/darkbulb.jpg")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.Navigate().GoToUrl("http://sso.dev.saucelabs.com/test/guinea-file-upload");
IWebElement upload = driver.FindElement(By.Id("myfile"));
upload.SendKeys(@"/Users/sso/the/local/path/to/darkbulb.jpg");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
@driver.navigate.to "http://sso.dev.saucelabs.com/test/guinea-file-upload"
    element = @driver.find_element(:id, 'myfile')
    element.send_keys "/Users/sso/SauceLabs/sauce/hostess/maitred/maitred/public/images/darkbulb.jpg"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
var upload = driver.findElement(By.id("myfile"));
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload")
val upload: WebElement = driver.findElement(By.id("myfile"))
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg")
  {{< / code-panel >}}
{{< / code-tab >}}
