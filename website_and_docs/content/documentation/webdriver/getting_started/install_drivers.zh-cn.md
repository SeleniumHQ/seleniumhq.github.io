---
title: "安装浏览器驱动"
linkTitle: "安装驱动"
weight: 2
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
如Chrom(ium)、Firefox、Internet Explorer、Edge、Opera和Safari. 
WebDriver尽量使用浏览器内置的自动化支持
来驱动浏览器.

由于除Internet Explorer之外的所有驱动程序实现
都是由浏览器供应商自己提供的, 
因此标准Selenium发行版中不包括这些驱动程序. 
本节介绍了使用不同浏览器的基本要求.

阅读我们的[Capabilities](/documentation/webdriver/capabilities)
文档中的更多高级选项.

## 快速参考

| 浏览器               | 支持的操作系统                     | 维护者              | 下载                                                                    | 问题追溯                                                            |
|-------------------|-----------------------------|------------------|-----------------------------------------------------------------------|-----------------------------------------------------------------|
| Chromium/Chrome   | Windows/macOS/Linux         | Google           | [下载](//chromedriver.storage.googleapis.com/index.html)                | [Issues](//bugs.chromium.org/p/chromedriver/issues/list)        |
| Firefox           | Windows/macOS/Linux         | Mozilla          | [下载](//github.com/mozilla/geckodriver/releases)                       | [Issues](//github.com/mozilla/geckodriver/issues)               |
| Edge              | Windows/macOS               | Microsoft        | [下载](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | 通过浏览器                                                           |
| Internet Explorer | Windows                     | Selenium Project | [下载](/downloads)                                                      | [Issues](//github.com/SeleniumHQ/selenium/labels/D-IE)          |
| Safari            | macOS High Sierra and newer | Apple            | 内置                                                                    | [Issues](//bugreport.apple.com/logon)                           |
| Opera             | Windows/macOS/Linux         | Opera            | [下载](//github.com/operasoftware/operachromiumdriver/releases)         | [Issues](//github.com/operasoftware/operachromiumdriver/issues) |


## 使用驱动的三种方式

### 1. 驱动管理软件

大多数机器会自动更新浏览器, 
但驱动程序不会. 
为了确保为浏览器提供正确的驱动程序, 
这里有许多第三方库可为您提供帮助.

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

### 2. `PATH` 环境变量
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

{{< tabpane  >}}
{{< tab header="Mac / Linux" >}}
echo $PATH
{{< /tab >}}
{{< tab header="Windows" >}}
echo %PATH%
{{< /tab >}}
{{< /tabpane >}}

<br />
* 如果要放置驱动程序的目录不在Path上, 
则需要添加: 

{{< tabpane  >}}
{{< tab header="Mac / Linux" >}}
export PATH=$PATH:/opt/WebDriver/bin >> ~/.profile
{{< /tab >}}
{{< tab header="Windows" >}}
setx PATH "%PATH%;C:\WebDriver\bin"
{{< /tab >}}
{{< /tabpane >}}

<br />
* 您可以通过启动驱动程序
来测试它是否已正确添加: 

```shell
  chromedriver
  ```
* 如果`PATH`配置正确, 
  您将看到一些与驱动程序启动相关的输出: 

```
Starting ChromeDriver 95.0.4638.54 (d31a821ec901f68d0d34ccdbaea45b4c86ce543e-refs/branch-heads/4638@{#871}) on port 9515
Only local connections are allowed.
Please see https://chromedriver.chromium.org/security-considerations for suggestions on keeping ChromeDriver safe.
ChromeDriver was started successfully.
```

想要重新控制命令提示符可以按下 <kbd>Ctrl+C</kbd>

### 3. 硬编码位置

与上面的选项2类似, 
您需要手动下载驱动程序(有关链接, 请参阅[快速参考](#快速参考) 部分). 
在代码中指定位置本身的优点是
不需要指出系统上的环境变量, 
但缺点是使代码的灵活性大大降低. 

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


## 启动浏览器

### Chromium/Chrome

默认情况下, 
Selenium 4与Chrome v75及更高版本兼容. 
请注意, Chrome和chromedriver的版本必须与主版本匹配. 
有关适用的下载链接, 
请参阅[快速参考](#快速参考)部分. 

上一节详细介绍了如何启动Chrome的示例
[使用驱动的三种方式](#使用驱动的三种方式). 

### Edge

Microsoft Edge是基于Chromium实现的, 最小支持的版本是v79. 
与Chrome类似, 
Edge版本和edgedriver版本必须与主版本匹配. 
有关适用的下载链接, 
请参阅[快速参考](#快速参考). 

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

Selenium 4需要Firefox 78或更高版本. 
建议始终使用geckodriver的最新版本. 
有关适用的下载链接, 请参阅[快速参考](#快速参考). 

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

Selenium项目旨在支持与
[微软当前考量](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer). 
相同的发布版本. 
旧版本可以工作, 但不受支持. 
请注意, InternetExplorer 11将在2022年6月15日, 
终止某些操作系统（包括Windows 10）的支持, 
Edge的IE兼容模式将继续受到支持. 

IE驱动程序是Selenium项目直接维护的唯一驱动程序. 
而Internet Explorer的32位和64位版本的二进制文件是可用的, 
使用64位驱动程序有一些
[限制](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html). 
因此, 建议使用32位驱动程序. 
应该注意的是, 作为Internet Explorer
首选项是针对登录用户的帐户保存的, 需要进行一些额外的设置. 


[在Selenium的Wiki上](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver), 
可以找到有关使用Internet Explorer的其他信息. 
有关适用的下载链接, 请参见[快速参考](#快速参考). 


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

当前版本的Opera构建在Chromium引擎之上, 
现在通过封闭源代码支持WebDriver
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

与Chromium和Firefox驱动程序不同, 
safaridriver是随操作系统一起安装的. 
要在Safari上启用自动化, 
请从终端运行以下命令: 

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

那些希望在iOS上实现Safari自动化的人
应该看看[Appium project](//appium.io/). 
