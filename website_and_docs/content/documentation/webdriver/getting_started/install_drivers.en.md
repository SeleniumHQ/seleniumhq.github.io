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

## Four Ways to Use Drivers

### 1. Selenium Manager (Beta)

{{< badge-version version="4.6" >}}

Selenium Manager helps you to get a working environment to run Selenium out of the box
(no additional downloads! no additional configurations!). 
Selenium Manager attempts to obtain the most correct driver for any browser 
supported by Selenium in a performant way. 
Selenium Manager is currently "opt-in," which means
that it is only used if code would otherwise fail. 
That means if you manage drivers by one of the approaches below, Selenium Manager
will not be used. 

### 2. Driver Management Software

Before Selenium Manager was created, many users turned to other projects to automatically
manage their drivers. Most of the functionality of these libraries exists natively in
the latest version of Selenium.

If you can't use Selenium Manager because you are using 
an older version of Selenium (please upgrade), 
or need an advanced feature not yet implemented by Selenium Manager, 
you might try one of these tools:

* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) (Java)
* [WebDriver Manager](https://github.com/SergeyPirogov/webdriver_manager) (Python)
* [WebDriver Manager Package](https://github.com/rosolko/WebDriverManager.Net) (.NET)
* [webdrivers gem](https://github.com/titusfortner/webdrivers) (Ruby)

### 3. The `PATH` Environment Variable
Note: we highly recommend removing drivers from `PATH` and using [Selenium Manager](#1-selenium-manager--beta-) if possible.

This option first requires manually downloading the driver (See [Quick Reference Section](#quick-reference) for links).

This is a flexible option to change location of drivers without having to update your code, and will work
on multiple machines without requiring that each machine put the drivers in the same place.

You can either place the drivers in a directory that is already listed in `PATH`, or you can place them in a directory
and add it to `PATH`.

{{< tabpane text=true persistLang=false >}}
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
You can test if it has been added correctly by checking the version of the driver:
```shell
chromedriver --version
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
You can test if it has been added correctly by checking the version of the driver:
```shell
chromedriver --version
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
You can test if it has been added correctly by checking the version of the driver:
```shell
chromedriver.exe --version
```
{{% /tab %}}
{{< /tabpane >}}

If your `PATH` is configured correctly above,
you will see the version printed like:

```shell
ChromeDriver 111.0.5563.64 (c710e93d5b63b7095afe8c2c17df34408078439d-refs/branch-heads/5563@{#995})
```

If it is not found, you'll see:
```shell
chromedriver.exe : The term 'chromedriver.exe' is not recognized as the name of a cmdlet, function, script file, or operable program
```
or
```shell
chromedriver: command not found
```

### 4. Hard Coded Location
Note: we highly recommend not directly referencing the drivers and using [Selenium Manager](#1-selenium-manager--beta-) if possible.

Similar to Option 3 above, you need to manually download the driver (See [Quick Reference Section](#quick-reference) for links).
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

## Quick Reference

| Browser | Supported OS | Maintained by | Download | Issue Tracker |
| ------- | ------------ | ------------- | -------- | ------------- |
| Chromium/Chrome | Windows/macOS/Linux | Google | [Downloads](//chromedriver.chromium.org/downloads) | [Issues](//bugs.chromium.org/p/chromedriver/issues/list) |
| Firefox | Windows/macOS/Linux | Mozilla | [Downloads](//github.com/mozilla/geckodriver/releases) | [Issues](//github.com/mozilla/geckodriver/issues) |
| Edge | Windows/macOS/Linux | Microsoft | [Downloads](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Issues](//github.com/MicrosoftEdge/EdgeWebDriver/issues) |
| Internet Explorer | Windows | Selenium Project | [Downloads](/downloads) | [Issues](//github.com/SeleniumHQ/selenium/labels/D-IE) |
| Safari | macOS High Sierra and newer | Apple | Built in | [Issues](//bugreport.apple.com/logon) |

Note: The Opera driver no longer works with the latest functionality of Selenium and is currently officially unsupported.

## Next Step
[Create your first Selenium script]({{< ref "first_script.md" >}})
