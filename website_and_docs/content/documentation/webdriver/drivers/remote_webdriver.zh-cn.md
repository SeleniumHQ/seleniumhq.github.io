---
title: "远程WebDriver"
linkTitle: "远程WebDriver"
weight: 10
aliases: [
"/documentation/zh-cn/remote_webdriver/",
"/documentation/zh-cn/remote_webdriver/remote_webdriver_client/",
"/zh-cn/documentation/webdriver/remote_webdriver/",
]

---

您可以如本地一样, 使用远程WebDriver.
主要区别在于需要配置远程WebDriver, 以便可以在不同的计算机上运行测试.

远程WebDriver由两部分组成：客户端和服务端. 客户端是您的WebDriver测试，而服务端仅仅是可以被托管于任何现代Java EE应用服务器的Java Servlet.

要运行远程WebDriver客户端, 我们首先需要连接到RemoteWebDriver.
为此, 我们将URL指向运行测试的服务器的地址. 
为了自定义我们的配置, 我们设置了既定的功能. 
下面是一个实例化样例, 
其指向我们的远程Web服务器 _www.example.com_ 的远程WebDriver对象, 
并在Firefox上运行测试.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
FirefoxOptions firefoxOptions = new FirefoxOptions();
WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), firefoxOptions);
driver.get("http://www.google.com");
driver.quit();
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

firefox_options = webdriver.FirefoxOptions()
driver = webdriver.Remote(
    command_executor='http://www.example.com',
    options=firefox_options
)
driver.get("http://www.google.com")
driver.quit() 
  {{< /tab >}}
  {{< tab header="CSharp" >}}
 FirefoxOptions firefoxOptions = new FirefoxOptions();
 IWebDriver driver = new RemoteWebDriver(new Uri("http://www.example.com"), firefoxOptions);
 driver.Navigate().GoToUrl("http://www.google.com");
 driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :remote, url: "http://www.example.com", desired_capabilities: :firefox
driver.get "http://www.google.com"
driver.close
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
firefoxOptions = FirefoxOptions()
driver: WebDriver = new RemoteWebDriver(new URL("http://www.example.com"), firefoxOptions)
driver.get("http://www.google.com")
driver.quit()
  {{< /tab >}}
{{< /tabpane >}}


为了进一步自定义测试配置, 我们可以添加其他既定的功能.


## 浏览器选项

例如, 假设您想使用Chrome版本67
在Windows XP上运行Chrome:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setCapability("browserVersion", "67");
chromeOptions.setCapability("platformName", "Windows XP");
WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), chromeOptions);
driver.get("http://www.google.com");
driver.quit();
  {{< /tab >}}
  {{< tab header="Python" >}}
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
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var chromeOptions = new ChromeOptions();
chromeOptions.BrowserVersion = "67";
chromeOptions.PlatformName = "Windows XP";
IWebDriver driver = new RemoteWebDriver(new Uri("http://www.example.com"), chromeOptions);
driver.Navigate().GoToUrl("http://www.google.com");
driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.platform = Windows XP
caps.version = 67

driver = Selenium::WebDriver.for :remote, :url => "http://www.example.com", :desired_capabilities => caps
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
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
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val chromeOptions = ChromeOptions()
chromeOptions.setCapability("browserVersion", "67")
chromeOptions.setCapability("platformName", "Windows XP")
val driver: WebDriver = new RemoteWebDriver(new URL("http://www.example.com"), chromeOptions)
driver.get("http://www.google.com")
driver.quit()
  {{< /tab >}}
{{< /tabpane >}}


## 本地文件检测器

本地文件检测器允许将文件从客户端计算机传输到远程服务器. 
例如, 如果测试需要将文件上传到Web应用程序, 
则远程WebDriver可以在运行时
将文件从本地计算机自动传输到远程Web服务器. 
这允许从运行测试的远程计算机上载文件. 
默认情况下未启用它, 可以通过以下方式启用:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
driver.setFileDetector(new LocalFileDetector());
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.remote.file_detector import LocalFileDetector

driver.file_detector = LocalFileDetector()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var allowsDetection = this.driver as IAllowsFileDetection;
if (allowsDetection != null)
{
   allowsDetection.FileDetector = new LocalFileDetector();
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
@driver.file_detector = lambda do |args|
  # args => ["/path/to/file"]
  str = args.first.to_s
  str if File.exist?(str)
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
var remote = require('selenium-webdriver/remote');
driver.setFileDetector(new remote.FileDetector);   
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.fileDetector = LocalFileDetector()
  {{< /tab >}}
{{< /tabpane >}}

定义上述代码后, 
您可以通过以下方式在测试中上传文件:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
WebElement upload = driver.findElement(By.id("myfile"));
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload")

driver.find_element(By.ID, "myfile").send_keys("/Users/sso/the/local/path/to/darkbulb.jpg")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl("http://sso.dev.saucelabs.com/test/guinea-file-upload");
IWebElement upload = driver.FindElement(By.Id("myfile"));
upload.SendKeys(@"/Users/sso/the/local/path/to/darkbulb.jpg");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
@driver.navigate.to "http://sso.dev.saucelabs.com/test/guinea-file-upload"
    element = @driver.find_element(:id, 'myfile')
    element.send_keys "/Users/sso/SauceLabs/sauce/hostess/maitred/maitred/public/images/darkbulb.jpg"
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
var upload = driver.findElement(By.id("myfile"));
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");  
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload")
val upload: WebElement = driver.findElement(By.id("myfile"))
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg")
  {{< /tab >}}
{{< /tabpane >}}

## 追踪客户端请求

此功能仅适用于Java客户端绑定 (Beta版以后).
远程WebDriver客户端向Selenium网格服务器发送请求, 
后者将请求传递给WebDriver.
应该在服务器端和客户端启用跟踪, 
以便端到端地追踪HTTP请求.
两端都应该有一个指向可视化框架的追踪导出器设置.
默认情况下, 
对客户端和服务器都启用追踪.
若设置可视化框架Jaeger UI及Selenium Grid 4, 
请参阅所需版本的[追踪设置](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) .

对于客户端设置, 请执行以下步骤.

#### 添加所需依赖

可以使用Maven安装追踪导出器的外部库.
在项目pom.xml中添加 _opentelemetry-exporter-jaeger_
和 _grpc-netty_ 的依赖项：

```xml
  <dependency>
      <groupId>io.opentelemetry</groupId>
      <artifactId>opentelemetry-exporter-jaeger</artifactId>
      <version>1.0.0</version>
    </dependency>
    <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-netty</artifactId>
      <version>1.35.0</version>
    </dependency>
``` 
 
#### 在运行客户端时添加/传递所需的系统属性

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
System.setProperty("otel.traces.exporter", "jaeger");
System.setProperty("otel.exporter.jaeger.endpoint", "http://localhost:14250");
System.setProperty("otel.resource.attributes", "service.name=selenium-java-client");

ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");

WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), capabilities);

driver.get("http://www.google.com");

driver.quit();

  {{< /tab >}}
{{< /tabpane >}}

有关所需Selenium版本
及其外部依赖关系版本等更多信息, 
请参阅[追踪设置](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) .

更多信息请访问:

* OpenTelemetry: https://opentelemetry.io
* 配置OpenTelemetry:
    https://github.com/open-telemetry/opentelemetry-java/tree/main/sdk-extensions/autoconfigure
* Jaeger: https://www.jaegertracing.io
* [Selenium Grid 可观测性]({{< ref "observability.md" >}}) 

