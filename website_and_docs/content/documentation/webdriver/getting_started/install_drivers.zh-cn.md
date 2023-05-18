---
title: "安装浏览器驱动"
linkTitle: "安装驱动"
weight: 4
description: >
  设置您的浏览器用于自动化.
aliases: [
"/documentation/zh-cn/selenium_installation/installing_webdriver_binaries/",
"/documentation/zh-cn/webdriver/driver_requirements/",
"/zh-cn/documentation/getting_started/installing_browser_drivers/"
]
---

通过WebDriver, 
Selenium支持市场上所有主要浏览器, 
如Chrome、Firefox、Internet Explorer、Edge和Safari. 
WebDriver尽量使用浏览器内置的自动化支持
来驱动浏览器.

由于除Internet Explorer之外的所有驱动程序实现
都是由浏览器供应商自己提供的, 
因此标准Selenium发行版中不包括这些驱动程序. 
本节介绍了使用不同浏览器的基本要求.

在我们的[驱动程序配置]({{< ref "/documentation/webdriver/drivers/" >}}) 文档中
阅读有关启动驱动程序的更多高级选项.

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from English to Chinese. 
   Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

## 四种使用驱动的方法

### 1. Selenium Manager <small>(Beta)</small>

{{< badge-version version="4.6" >}}

Selenium Manager helps you to get a working environment to run Selenium out of the box
(no additional downloads! no additional configurations!).
Selenium Manager attempts to obtain the most correct driver for any browser
supported by Selenium in a performant way.
Selenium Manager is currently "opt-in," which means
that it is only used if code would otherwise fail.
That means if you manage drivers by one of the approaches below, Selenium Manager
will not be used.

### 2. 驱动管理软件

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

### 3. `PATH` 环境变量
Note: we highly recommend removing drivers from `PATH` and using [Selenium Manager](#1-selenium-manager--beta-) if possible.

此选项首先需要手动下载驱动程序
(有关链接, 请参阅[快速参考](#快速参考) 部分).

这是一个灵活的选项, 
可以在不更新代码的情况下更改驱动程序的位置, 
并且可以在多台机器上工作, 
而不需要每台机器将驱动程序放在同一位置. 

您可以将驱动程序放置在路径中已列出的目录中, 
也可以将其放置在目录中并将其添加到`PATH`.

* 要查看`PATH`上已有哪些目录, 
请打开命令提示符/终端并键入:  

{{< tabpane text=true persistLang=false >}}
{{% tab header="Bash" %}}

要查看`PATH`上已经有哪些目录, 请打开Terminal并执行
```shell
echo $PATH
```
如果驱动程序的位置不在列出的目录中, 
可以将新目录添加到PATH:
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
要查看`PATH`上已经有哪些目录, 请打开Terminal并执行:
```shell
echo $PATH
```
如果驱动程序的位置不在列出的目录中, 
可以将新目录添加到PATH:
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

要查看`PATH`上已经有哪些目录, 请打开命令提示符并执行:
```shell
echo %PATH%
```
如果驱动程序的位置不在列出的目录中, 
可以将新目录添加到PATH:
```shell
setx PATH "%PATH%;C:\WebDriver\bin"
```
You can test if it has been added correctly by checking the version of the driver:
```shell
chromedriver.exe --version
```
{{% /tab %}}
{{< /tabpane >}}

如果`PATH`配置正确,
you will see the version printed like:

```shell
ChromeDriver 111.0.5563.64 (c710e93d5b63b7095afe8c2c17df34408078439d-refs/branch-heads/5563@{#995})
```

If it is not found, you'll see:
```shell
chromedriver.exe : The term 'chromedriver.exe' is not recognized as the name of a cmdlet, function, script file, or operable program
```

### 4. 硬编码位置
Note: we highly recommend not directly referencing the drivers and using [Selenium Manager](#1-selenium-manager--beta-) if possible.

与上面的选项3类似, 
您需要手动下载驱动程序(有关链接, 请参阅[快速参考](#快速参考) 部分). 
在代码中指定位置本身的优点是
不需要指出系统上的环境变量, 
但缺点是使代码的灵活性大大降低. 

{{< tabpane langEqualsHeader=true >}}

{{< tab header="Java" >}}

System.setProperty("webdriver.chrome.driver","/path/to/chromedriver");
ChromeDriver driver = new ChromeDriver();

{{< /tab >}}

{{< tab header="Python" >}}

from selenium import webdriver
from selenium.webdriver.chrome.service import Service

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

## 快速参考

| 浏览器               | 支持的操作系统                     | 维护者              | 下载                                                                    | 问题追溯                                                             |
|-------------------|-----------------------------|------------------|-----------------------------------------------------------------------|------------------------------------------------------------------|
| Chromium/Chrome   | Windows/macOS/Linux         | Google           | [下载](//chromedriver.chromium.org/downloads)                | [Issues](//bugs.chromium.org/p/chromedriver/issues/list)         |
| Firefox           | Windows/macOS/Linux         | Mozilla          | [下载](//github.com/mozilla/geckodriver/releases)                       | [Issues](//github.com/mozilla/geckodriver/issues)                |
| Edge              | Windows/macOS/Linux         | Microsoft        | [下载](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Issues](https://github.com/MicrosoftDocs/edge-developer/issues) |
| Internet Explorer | Windows                     | Selenium Project | [下载](/downloads)                                                      | [Issues](//github.com/SeleniumHQ/selenium/labels/D-IE)           |
| Safari            | macOS High Sierra and newer | Apple            | 内置                                                                    | [Issues](//bugreport.apple.com/logon)                            |

备注：Opera驱动不再适用于Selenium的最新功能，目前官方不支持。


## 下一步
[创建你的第一个Selenium脚本]({{< ref "first_script.md" >}})
