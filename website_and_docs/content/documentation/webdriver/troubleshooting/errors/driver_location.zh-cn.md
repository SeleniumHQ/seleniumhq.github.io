---
title: "Unable to Locate Driver Error"
linkTitle: "Driver Location"
weight: 4
description: >
  Troubleshooting missing path to driver executable.
aliases: [
"/documentation/zh-cn/selenium_installation/installing_webdriver_binaries/",
"/documentation/zh-cn/webdriver/driver_requirements/",
"/zh-cn/documentation/getting_started/installing_browser_drivers/",
"/zh-cn/documentation/webdriver/getting_started/install_drivers/",
]
---

Historically, this is the most common error beginning Selenium users get 
when trying to run code for the first time:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
The path to the driver executable must 
be set by the webdriver.chrome.driver system property; 
for more information, see https://chromedriver.chromium.org/. 
The latest version can be downloaded from https://chromedriver.chromium.org/downloads
{{< /tab >}}
{{< tab header="Python" >}}
The executable chromedriver needs to be available in the path.
{{< /tab >}}
{{< tab header="CSharp" >}}
The file geckodriver does not exist. The driver can be downloaded at https://github.com/mozilla/geckodriver/releases"
{{< /tab >}}
{{< tab header="Ruby" >}}
Unable to locate the chromedriver executable;
{{< /tab >}}
{{< /tabpane >}}

## Likely cause

Through WebDriver, Selenium supports all major browsers.
In order to drive the requested browser, Selenium needs to 
send commands to it via an executable driver. 
This error means the necessary driver could not be
found by any of the means Selenium attempts to use.

## Possible solutions

There are several ways to ensure Selenium gets the driver it needs.

### Use the latest version of Selenium

As of Selenium 4.6, Selenium downloads the correct driver for you.
You shouldn't need to do anything. If you are using the latest version
of Selenium and you are getting an error,
please [turn on logging]({{< ref "../logging.md" >}})
and [file a bug report](//github.com/seleniumhq/selenium/issues) with that information.

If you want to read more information about how Selenium manages driver downloads for you,
you can read about the [Selenium Manager]({{< ref "/documentation/selenium_manager.md" >}}).

### Use the `PATH` environment variable

This option first requires manually [downloading the driver](#download-the-driver).

This is a flexible option to change location of drivers without having to update your code, 
and will work on multiple machines without requiring that each machine put the 
drivers in the same place.

You can either place the drivers in a directory that is already listed in `PATH`, 
or you can place them in a directory and add it to `PATH`.

{{< tabpane text=true persist=disabled >}}
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

### Specify the location of the driver

If you cannot upgrade to the latest version of Selenium, you
do not want Selenium to download drivers for you, and you can't figure
out the environment variables, you can specify the location of the driver in the Service object.

You first need to [download the desired driver](#download-the-driver),
then create an instance of the applicable `Service` class and 
[set the path]({{< ref "../../drivers/service/#driver-location" >}}).

Specifying the location in the code itself has the advantage of not needing 
to figure out Environment Variables on your system, but has the drawback of 
making the code less flexible.

### Driver management libraries

Before Selenium managed drivers itself, other projects were created to
do so for you. 

If you can't use Selenium Manager because you are using
an older version of Selenium (please upgrade),
or need an advanced feature not yet implemented by Selenium Manager,
you might try one of these tools to keep your drivers automatically updated:

* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) (Java)
* [WebDriver Manager](https://github.com/SergeyPirogov/webdriver_manager) (Python)
* [WebDriver Manager Package](https://github.com/rosolko/WebDriverManager.Net) (.NET)
* [webdrivers gem](https://github.com/titusfortner/webdrivers) (Ruby)


## Download the driver

| 浏览器               | 支持的操作系统                     | 维护者              | 下载                                                                    | 问题追溯                                                             |
|-------------------|-----------------------------|------------------|-----------------------------------------------------------------------|------------------------------------------------------------------|
| Chromium/Chrome   | Windows/macOS/Linux         | Google           | [下载](//chromedriver.chromium.org/downloads)                | [Issues](//bugs.chromium.org/p/chromedriver/issues/list)         |
| Firefox           | Windows/macOS/Linux         | Mozilla          | [下载](//github.com/mozilla/geckodriver/releases)                       | [Issues](//github.com/mozilla/geckodriver/issues)                |
| Edge              | Windows/macOS/Linux         | Microsoft        | [下载](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Issues](https://github.com/MicrosoftDocs/edge-developer/issues) |
| Internet Explorer | Windows                     | Selenium Project | [下载](/downloads)                                                      | [Issues](//github.com/SeleniumHQ/selenium/labels/D-IE)           |
| Safari            | macOS High Sierra and newer | Apple            | 内置                                                                    | [Issues](//bugreport.apple.com/logon)                            |

备注：Opera驱动不再适用于Selenium的最新功能，目前官方不支持。
