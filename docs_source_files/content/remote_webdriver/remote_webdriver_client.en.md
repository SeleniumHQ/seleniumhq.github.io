---
title: "Remote WebDriver client"
weight: 2
---

To run a remote WebDriver client, we first need to connect to the RemoteWebDriver.
We do this by pointing the URL to the address of the server running our tests.
In order to customize our configuration, we set desired capabilities.
Below is an example of instantiating a remote WebDriver object
pointing to our remote web server, _www.example.com_,
running our tests on Firefox.

{{< code-tab >}}
  {{< code-panel language="java" >}}
FirefoxOptions firefoxOptions = new FirefoxOptions();
WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), firefoxOptions);
driver.get("http://www.google.com");
driver.quit();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# We don't have a Python code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :remote, url: "http://www.example.com", desired_capabilities: :firefox
driver.get "http://www.google.com"
driver.close
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
firefoxOptions = FirefoxOptions()
driver: WebDriver = new RemoteWebDriver(new URL("http://www.example.com"), firefoxOptions)
driver.get("http://www.google.com")
driver.quit()
  {{< / code-panel >}}
{{< / code-tab >}}


To further customize our test configuration, we can add other desired capabilities.


## Desired capabilities

Desired capabilities can be expanded further.
All remote Webdriver capabilities are sent through JsonWireProtocol.
For a list of configurable capabilities, and more information on JsonWireProtocol,
please visit the documentation
[here](//github.com/SeleniumHQ/selenium/wiki/DesiredCapabilities).

For example, suppose you wanted to run Chrome on Windows XP,
using Chrome version 67:

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
# We don't have a Python code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.platform = Windows XP
caps.version = 67

driver = Selenium::WebDriver.for :remote, :url => "http://www.example.com", :desired_capabilities => caps
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val chromeOptions = ChromeOptions()
chromeOptions.setCapability("browserVersion", "67");
chromeOptions.setCapability("platformName", "Windows XP");
val driver: WebDriver = new RemoteWebDriver(new URL("http://www.example.com"), chromeOptions)
driver.get("http://www.google.com")
driver.quit();
  {{< / code-panel >}}
{{< / code-tab >}}


## Local file detector

The Local File Detector allows the transfer of files from the client
machine to the remote server.  For example, if a test needs to upload a
file to a web application, a remote WebDriver can automatically transfer
the file from the local machine to the remote web server during
runtime. This allows the file to be uploaded from the remote machine
running the test. It is not enabled by default and can be enabled in
the following way:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.setFileDetector(new LocalFileDetector());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# We don't have a Python code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
@driver.file_detector = lambda do |args|
  # args => ["/path/to/file"]
  str = args.first.to_s
  str if File.exist?(str)
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.setFileDetector(new LocalFileDetector())
  {{< / code-panel >}}
{{< / code-tab >}}

Once the above code is defined, you can upload a file in your test in the following way:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
WebElement upload = driver.findElement(By.id("myfile"));
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# We don't have a Python code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
@driver.navigate.to "http://sso.dev.saucelabs.com/test/guinea-file-upload"
    element = @driver.find_element(:id, 'myfile')
    element.send_keys "/Users/sso/SauceLabs/sauce/hostess/maitred/maitred/public/images/darkbulb.jpg"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload")
val upload: WebElement = driver.findElement(By.id("myfile"))
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg")
  {{< / code-panel >}}
{{< / code-tab >}}
