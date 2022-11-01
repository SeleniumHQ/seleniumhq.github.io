---
title: "Install browser drivers"
linkTitle: "Install Drivers"
weight: 4
description: >
  Setting up your system to allow a browser to be automated.
aliases: [
"/documentation/en/selenium_installation/installing_webdriver_binaries/",
"/documentation/en/webdriver/driver_requirements/",
"/documentation/getting_started/installing_browser_drivers/"
]
---

Through WebDriver, Selenium supports all major browsers on the market
such as Chrome/Chromium, Firefox, Internet Explorer, Edge, and Safari.
Where possible, WebDriver drives the browser
using the browser's built-in support for automation.

Since all the driver implementations except for Internet Explorer are provided by the
browser vendors themselves, they are not included in the standard Selenium distribution.
This section explains the basic requirements for getting started with the different browsers.

Read about more advanced options for starting a driver 
in our [driver configuration]({{< ref "/documentation/webdriver/drivers/" >}}) documentation.

## Quick Reference

| Browser | Supported OS | Maintained by | Download | Issue Tracker |
| ------- | ------------ | ------------- | -------- | ------------- |
| Chromium/Chrome | Windows/macOS/Linux | Google | [Downloads](//chromedriver.chromium.org/downloads) | [Issues](//bugs.chromium.org/p/chromedriver/issues/list) |
| Firefox | Windows/macOS/Linux | Mozilla | [Downloads](//github.com/mozilla/geckodriver/releases) | [Issues](//github.com/mozilla/geckodriver/issues) |
| Edge | Windows/macOS | Microsoft | [Downloads](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Issues](//github.com/MicrosoftEdge/EdgeWebDriver/issues) |
| Internet Explorer | Windows | Selenium Project | [Downloads](/downloads) | [Issues](//github.com/SeleniumHQ/selenium/labels/D-IE) |
| Safari | macOS High Sierra and newer | Apple | Built in | [Issues](//bugreport.apple.com/logon) |

Note: The Opera driver no longer works with the latest functionality of Selenium and is currently officially unsupported.

## Three Ways to Use Drivers

### 1. Driver Management Software

Most machines automatically update the browser, but the driver does not. To make sure you get 
the correct driver for your browser, there are many third party libraries to assist you. 

{{< tabpane code=false langEqualsHeader=true >}}
{{% tab header="Java" %}}

1. Import [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
```java
import io.github.bonigarcia.wdm.WebDriverManager;
```
2. Calling `setup()` automatically puts the correct browser driver where the code will see it:
```java
WebDriverManager.chromedriver().setup();
```
3. Just initialize the driver as you normally would:
```java 
ChromeDriver driver = new ChromeDriver();
```

<div class="github">
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/InstallDriversTest.java">
See full example on GitHub.</a>
</div>

{{% /tab %}}
{{% tab header="Python" %}}

1. Import [WebDriver Manager for Python](https://github.com/SergeyPirogov/webdriver_manager)

```py
from webdriver_manager.chrome import ChromeDriverManager
```

2. Use `install()` to get the location used by the manager and pass it into service class

```py
service = Service(executable_path=ChromeDriverManager().install())
```

3. Use `Service` instance when initializing the driver: 
```py
driver = webdriver.Chrome(service=service)
```

<div class="github">
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_install_drivers.py">
See full example on GitHub.</a>
</div>

{{% /tab %}}
{{% tab header="CSharp" %}}
**Important:** This package does not currently work for IEDriverServer v4+

1. Import [WebDriver Manager Package](https://github.com/rosolko/WebDriverManager.Net)

```csharp
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;
```

2. Use the `SetUpDriver()` which requires a config class:

```csharp
new DriverManager().SetUpDriver(new ChromeConfig());
```

3. Initialize your driver as you normally would:

```csharp
var driver = new ChromeDriver()
```

<div class="github">
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/InstallDriversTest.cs">
See full example on GitHub.</a>
</div>

{{% /tab %}}
{{% tab header="Ruby" %}}
1. Add [webdrivers gem](https://github.com/titusfortner/webdrivers) to Gemfile:

```rb
gem 'webdrivers', '~> 5.0'
```

2. Require webdrivers in your project:
```rb
require 'webdrivers'
```

3. Initialize driver as you normally would:
```rb
driver = Selenium::WebDriver.for :chrome
```

<div class="github">
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/install_drivers_spec.rb">
See full example on GitHub.</a>
</div>

{{% /tab %}}
{{% tab header="JavaScript" %}}
 *There is not a recommended driver manager for JavaScript at this time*
{{% /tab %}}
{{% tab header="Kotlin" %}}

1. Import [WebDriver Manager](https://github.com/bonigarcia/webdrivermanager)
```java
import io.github.bonigarcia.wdm.WebDriverManager;
```
2. Call the setup method before initializing the driver as you normally would:
```java
fun chrome(): WebDriver {
    WebDriverManager.chromedriver().setup()
    return ChromeDriver()
}
```

{{% /tab %}}
{{< /tabpane >}}

### 2. The `PATH` Environment Variable
This option first requires manually downloading the driver (See [Quick Reference Section](#quick-reference) for links).

This is a flexible option to change location of drivers without having to update your code, and will work
on multiple machines without requiring that each machine put the drivers in the same place.

You can either place the drivers in a directory that is already listed in `PATH`, or you can place them in a directory
and add it to `PATH`.

{{< tabpane code=false persistLang=false >}}
{{% tab header="Bash" %}}
To see what directories are already on `PATH`, open a Terminal and execute:
```shell
echo $PATH
```
If the location to your driver is not already in a directory listed,
you can add a new directory to PATH:
```shell
echo 'export PATH=$PATH:/path/to/driver' >> ~/.bash_profile
source ~/.bash_profile
```
You can test if it has been added correctly by starting the driver:
```shell
chromedriver
```
{{% /tab %}}
{{% tab header="Zsh" %}}
To see what directories are already on `PATH`, open a Terminal and execute:
```shell
echo $PATH
```
If the location to your driver is not already in a directory listed,
you can add a new directory to PATH:
```shell
echo 'export PATH=$PATH:/path/to/driver' >> ~/.zshenv
source ~/.zshenv
```
You can test if it has been added correctly by starting the driver:
```shell
chromedriver
```
{{% /tab %}}
{{% tab header="Windows" %}}
To see what directories are already on `PATH`, open a Command Prompt and execute:
```shell
echo %PATH%
```
If the location to your driver is not already in a directory listed,
you can add a new directory to PATH:
```shell
setx PATH "%PATH%;C:\WebDriver\bin"
```
You can test if it has been added correctly by starting the driver:
```shell
chromedriver.exe
```
{{% /tab %}}
{{< /tabpane >}}

If your `PATH` is configured correctly above,
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

System.setProperty("webdriver.chrome.driver","/path/to/chromedriver");
ChromeDriver driver = new ChromeDriver();

{{< /tab >}}

{{< tab header="Python" >}}

from selenium.webdriver.chrome.service import Service
from selenium import webdriver

service = Service(executable_path="/path/to/chromedriver")
driver = webdriver.Chrome(service=service)

{{< /tab >}}

{{< tab header="CSharp" >}}

var driver = new ChromeDriver(@"C:\WebDriver\bin");

{{< /tab >}}

{{< tab header="Ruby" >}}

service = Selenium::WebDriver::Service.chrome(path: '/path/to/chromedriver')
driver = Selenium::WebDriver.for :chrome, service: service

{{< /tab >}}

{{< tab header="JavaScript" >}}
const {Builder} = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');

const service = new chrome.ServiceBuilder('/path/to/chromedriver');
const driver = new Builder().forBrowser('chrome').setChromeService(service).build();
{{< /tab >}}

{{< tab header="Kotlin" >}}
import org.openqa.selenium.chrome.ChromeDriver

fun main(args: Array<String>) {
    System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver")
    val driver = ChromeDriver()
}
{{< /tab >}}
{{< /tabpane >}}

## Next Step
[Create your first Selenium script]({{< ref "first_script.md" >}})
