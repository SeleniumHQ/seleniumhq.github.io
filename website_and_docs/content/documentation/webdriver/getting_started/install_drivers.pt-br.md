---
title: "Installing browser drivers"
linkTitle: "Installing browser drivers"
weight: 2
needsTranslation: true
description: >
  Setting up your browser ready to be automated.
aliases: [
"/documentation/pt-br/selenium_installation/installing_webdriver_binaries/",
"/documentation/pt-br/webdriver/driver_requirements/",
"/pt-br/documentation/getting_started/installing_browser_drivers/"
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Portuguese. Do you speak Portuguese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Through WebDriver, Selenium supports all major browsers on the market
such as Chrom(ium), Firefox, Internet Explorer, Edge, Opera, and Safari.
Where possible, WebDriver drives the browser
using the browser's built-in support for automation.

Since all the driver implementations except for Internet Explorer are provided by the
browser vendors themselves, they are not included in the standard Selenium distribution.
This section explains the basic requirements for getting you started with the different browsers.

Read about more advanced options in our [Capabilities](/documentation/webdriver/capabilities) documentation.

## Quick Reference

| Browser | Supported OS | Maintained by | Download | Issue Tracker |
| ------- | ------------ | ------------- | -------- | ------------- |
| Chromium/Chrome | Windows/macOS/Linux | Google | [Downloads](//chromedriver.storage.googleapis.com/index.html) | [Issues](//bugs.chromium.org/p/chromedriver/issues/list) |
| Firefox | Windows/macOS/Linux | Mozilla | [Downloads](//github.com/mozilla/geckodriver/releases) | [Issues](//github.com/mozilla/geckodriver/issues) |
| Edge | Windows/macOS | Microsoft | [Downloads](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | Via Browser |
| Internet Explorer | Windows | Selenium Project | [Downloads](/downloads) | [Issues](//github.com/SeleniumHQ/selenium/labels/D-IE) |
| Safari | macOS High Sierra and newer | Apple | Built in | [Issues](//bugreport.apple.com/logon) |
| Opera | Windows/macOS/Linux | Opera | [Downloads](//github.com/operasoftware/operachromiumdriver/releases) | [Issues](//github.com/operasoftware/operachromiumdriver/issues) |


## Three Ways to Use Drivers

### 1. Driver Management Software

Most machines automatically update the browser, but the driver does not. To make sure you get
the correct driver for your browser, there are many third party libraries to assist you.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}

// Use WebDriver Manager: https://github.com/bonigarcia/webdrivermanager

// Import WebDriver Manager:
import io.github.bonigarcia.wdm.WebDriverManager;

// Call setup() method for the browser driver you want:
WebDriverManager.chromedriver().setup();

// Initialize your driver as you normally would:
ChromeDriver driver = new ChromeDriver();

{{< /tab >}}
{{< tab header="Python" >}}

# Use Webdriver Manager for Python: https://github.com/SergeyPirogov/webdriver_manager

# Import code:
from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service

# Use the `install()` method to set `executabe_path` in a new `Service` instance:
service = Service(executable_path=ChromeDriverManager().install())

# Pass in the `Service` instance with the `service` keyword:
driver = webdriver.Chrome(service=service)

{{< /tab >}}
{{< tab header="CSharp" >}}
// Use WebDriver Manager Package: https://github.com/rosolko/WebDriverManager.Net

// Import the dependencies:
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;

// Use the `SetUpDriver()` which requires a config class:
new DriverManager().SetUpDriver(new ChromeConfig());

// Initialize your driver as you normally would:
var driver = new ChromeDriver()

{{< /tab >}}
{{< tab header="Ruby" >}}
# Use webdrivers gem: https://github.com/titusfortner/webdrivers

# Add gem to Gemfile:
gem 'webdrivers', '~> 5.0'

# Require webdrivers in your project:
require 'webdrivers'

# Initialize driver as you normally would:
driver = Selenium::WebDriver.for :chrome

{{< /tab >}}
{{< tab header="JavaScript" >}}
// There is not a recommended driver manager for JavaScript at this time
{{< /tab >}}
{{< tab header="Kotlin" >}}
// Use WebDriverManager: https://github.com/bonigarcia/webdrivermanager

// Import the library
import io.github.bonigarcia.wdm.WebDriverManager

// Call the setup method before initializing the driver as you normally would:
fun chrome(): WebDriver {
WebDriverManager.chromedriver().setup()
return ChromeDriver()
}
{{< /tab >}}
{{< /tabpane >}}

### 2. The `PATH` Environment Variable
This option first requires manually downloading the driver (See [Quick Reference Section](#quick-reference) for links).

This is a flexible option to change location of drivers without having to update your code, and will work
on multiple machines without requiring that each machine put the drivers in the same place.

You can either place the drivers in a directory that is already listed in `PATH`, or you can place them in a directory
and add it to `PATH`.

* To see what directories are already on `PATH`, open a Command Prompt / Terminal and type:

{{< tabpane  >}}
{{< tab header="Mac / Linux" >}}
echo $PATH
{{< /tab >}}
{{< tab header="Windows" >}}
echo %PATH%
{{< /tab >}}
{{< /tabpane >}}

<br />
* If the directory you want to place the drivers is not already on PATH, you need to add it: 

{{< tabpane  >}}
{{< tab header="Mac / Linux" >}}
export PATH=$PATH:/opt/WebDriver/bin >> ~/.profile
{{< /tab >}}
{{< tab header="Windows" >}}
setx PATH "%PATH%;C:\WebDriver\bin"
{{< /tab >}}
{{< /tabpane >}}

<br />
* You can test if it has been added correctly by starting the driver:
 ```shell
  chromedriver
  ```
* If your `PATH` is configured correctly,
you will see some output relating to the startup of the driver:

```
Starting ChromeDriver 95.0.4638.54 (d31a821ec901f68d0d34ccdbaea45b4c86ce543e-refs/branch-heads/4638@{#871}) on port 9515
Only local connections are allowed.
Please see https://chromedriver.chromium.org/security-considerations for suggestions on keeping ChromeDriver safe.
ChromeDriver was started successfully.
```

You can regain control of your command prompt by pressing <kbd>Ctrl+C</kbd>

### 3. Hard Coded Location

Similar to Option 2 above, you need to manually download the driver (See [Quick Reference Section](#quick-reference) for links).
Specifying the location in the code itself has the advantage of not needing to figure out Environment Variables on
your system, but has the drawback of making the code much less flexible.

{{< tabpane langEqualsHeader=true >}}

{{< tab header="Java" >}}

System.setProperty("webdriver.chrome.driver","/opt/WebDriver/bin/chromedriver");
ChromeDriver driver = new ChromeDriver();

{{< /tab >}}

{{< tab header="Python" >}}

service = Service(executable_path="/opt/WebDriver/bin/chromedriver")
driver = webdriver.Chrome(service=service)

{{< /tab >}}

{{< tab header="CSharp" >}}

var driver = new ChromeDriver(@"C:\WebDriver\bin");

{{< /tab >}}

{{< tab header="Ruby" >}}

service = Selenium::WebDriver::Service.chrome(path: '/opt/WebDriver/bin/chromedriver')
driver = Selenium::WebDriver.for :chrome, service: service

{{< /tab >}}

{{< tab header="JavaScript" >}}
const {Builder} = require('selenium-webdriver');

const service = new chrome.ServiceBuilder('/opt/WebDriver/bin/chromedriver').build();
const driver = new Builder().forBrowser('chrome').setChromeService(service).build();
{{< /tab >}}

{{< tab header="Kotlin" >}}

// Please raise a PR to add code sample

{{< /tab >}}

{{< /tabpane >}}


## Starting Browsers

### Chromium/Chrome

By default, Selenium 4 is compatible with Chrome v75 and greater. Note that the version of Chrome and the
version of chromedriver must match the major version. See the [Quick Reference Section](#quick-reference) for the
applicable download link.

Examples for how to start Chrome are provided in the previous section detailing
the [Three Ways to Use Drivers](#three-ways-to-use-drivers).

### Edge

Microsoft Edge is implemented with Chromium, with the earliest supported version of v79. Similar to Chrome,
the version of Edge and the version of edgedriver must match the major version.
See the [Quick Reference Section](#quick-reference) for the applicable download link.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

WebDriver driver = new EdgeDriver();
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver import Edge

driver = Edge()
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;

IWebDriver driver = new EdgeDriver();
{{< /tab >}}
{{< tab header="Ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :edge
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder} = require('selenium-webdriver');

var driver = new Builder().forBrowser('edge').build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Edge.EdgeDriver

val driver: WebDriver = EdgeDriver()
{{< /tab >}}
{{< /tabpane >}}

### Firefox

Selenium 4 requires Firefox 78 or greater. It is recommended to always use the latest version of geckodriver.
See the [Quick Reference Section](#quick-reference) for the applicable download link.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

WebDriver driver = new FirefoxDriver();
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver import Firefox

driver = Firefox()
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;

IWebDriver driver = new FirefoxDriver();
{{< /tab >}}
{{< tab header="Ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :firefox
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder} = require('selenium-webdriver');

var driver = new Builder().forBrowser('firefox').build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Firefox.FirefoxDriver

val driver: WebDriver = FirefoxDriver()
{{< /tab >}}
{{< /tabpane >}}


### Internet Explorer

The Selenium project aims to support the same releases that
[Microsoft considers current](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer).
Older releases may work, but will not be supported. Note that Internet Explorer 11 will end support for certain
operating systems, including Windows 10 on June 15, 2022.
Edge has an IE Compatibility mode that will continue to be supported.

The IE Driver is the only driver maintained by the Selenium Project directly.
While binaries for both the 32-bit and 64-bit
versions of Internet Explorer are available, there are some
[limitations](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)
with the 64-bit driver. As such it is recommended to use the 32-bit driver.
It should be noted that as Internet Explorer
preferences are saved against the logged in user's account, some additional setup is required.

Additional information about using Internet Explorer can be found
[on the Selenium wiki](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver), and
you can see the [Quick Reference Section](#quick-reference) for the applicable download link.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

WebDriver driver = new InternetExplorerDriver();
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver import Ie

driver = Ie()
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

IWebDriver driver = new InternetExplorerDriver();
{{< /tab >}}
{{< tab header="Ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :ie
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder} = require('selenium-webdriver');

var driver = new Builder().forBrowser('internet explorer').build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.ie.InternetExplorerDriver

val driver: WebDriver = InternetExplorerDriver()
{{< /tab >}}
{{< /tabpane >}}

### Opera

Current releases of Opera are built on top of the Chromium engine,
and WebDriver is now supported via the closed-source
[Opera Chromium Driver](//github.com/operasoftware/operachromiumdriver/releases).

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

WebDriver driver = new OperaDriver();
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver import Opera

driver = Opera()
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Opera;

IWebDriver driver = new OperaDriver();
{{< /tab >}}
{{< tab header="Ruby" >}}
# Not currently implemented
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder} = require("selenium-webdriver");

var driver = new Builder().forBrowser('opera').build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.opera.OperaDriver

val driver: WebDriver = OperaDriver()
{{< /tab >}}
{{< /tabpane >}}

### Safari

Unlike Chromium and Firefox drivers, the safaridriver is installed with the Operating System.
To enable automation on Safari, run the following command from the terminal:

```shell
safaridriver --enable
```

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

WebDriver driver = new SafariDriver();
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver import Safari

driver = Safari()
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Safari;

IWebDriver driver = new SafariDriver();
{{< /tab >}}
{{< tab header="Ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :safari
{{< /tab >}}
{{< tab header="JavaScript" >}}
const {Builder} = require('selenium-webdriver');

var driver = new Builder().forBrowser('safari').build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.safari.SafariDriver

val driver: WebDriver = SafariDriver()
{{< /tab >}}
{{< /tabpane >}}

Those looking to automate Safari on iOS should look to the [Appium project](//appium.io/).

