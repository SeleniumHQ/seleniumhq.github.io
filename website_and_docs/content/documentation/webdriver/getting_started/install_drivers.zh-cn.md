---
title: "安装浏览器驱动"
linkTitle: "安装驱动"
weight: 4
needsTranslation: true
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
如Chrome、Firefox、Internet Explorer、Edge、Opera和Safari. 
WebDriver尽量使用浏览器内置的自动化支持
来驱动浏览器.

由于除Internet Explorer之外的所有驱动程序实现
都是由浏览器供应商自己提供的, 
因此标准Selenium发行版中不包括这些驱动程序. 
本节介绍了使用不同浏览器的基本要求.

Read about more advanced options for starting a driver
in our [driver configuration]({{< ref "/documentation/webdriver/drivers.md" >}}) documentation.

## 快速参考

| 浏览器               | 支持的操作系统                     | 维护者              | 下载                                                                    | 问题追溯                                                             |
|-------------------|-----------------------------|------------------|-----------------------------------------------------------------------|------------------------------------------------------------------|
| Chromium/Chrome   | Windows/macOS/Linux         | Google           | [下载](//chromedriver.storage.googleapis.com/index.html)                | [Issues](//bugs.chromium.org/p/chromedriver/issues/list)         |
| Firefox           | Windows/macOS/Linux         | Mozilla          | [下载](//github.com/mozilla/geckodriver/releases)                       | [Issues](//github.com/mozilla/geckodriver/issues)                |
| Edge              | Windows/macOS               | Microsoft        | [下载](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [Issues](https://github.com/MicrosoftDocs/edge-developer/issues) |
| Internet Explorer | Windows                     | Selenium Project | [下载](/downloads)                                                      | [Issues](//github.com/SeleniumHQ/selenium/labels/D-IE)           |
| Safari            | macOS High Sierra and newer | Apple            | 内置                                                                    | [Issues](//bugreport.apple.com/logon)                            |

Note: The Opera driver does not support w3c syntax, so we recommend using chromedriver to work with Opera.
See the code example for [opening an Opera browser]({{< ref "open_browser.md#opera" >}}).

## 使用驱动的三种方式

### 1. 驱动管理软件

大多数机器会自动更新浏览器, 
但驱动程序不会. 
为了确保为浏览器提供正确的驱动程序, 
这里有许多第三方库可为您提供帮助.

{{< tabpane disableCodeBlock=true >}}
{{< tab header="Java" >}}

1. Import [WebDriver Manager](https://github.com/bonigarcia/webdrivermanager)
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
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/java_example/examples/java/src/test/java/dev/selenium/getting_started/InstallDriversTest.java">
See full example on GitHub.</a>
</div>

{{< /tab >}}
{{< tab header="Python" >}}

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
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/java_example/examples/python/tests/getting_started/test_install_drivers.py">
See full example on GitHub.</a>
</div>

{{< /tab >}}
{{< tab header="CSharp" >}}
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
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/java_example/examples/dotnet/SeleniumDocs/GettingStarted/InstallDriversTest.cs">
See full example on GitHub.</a>
</div>

{{< /tab >}}
{{< tab header="Ruby" >}}
1. Add [webdrivers gem](https://github.com/titusfortner/webdrivers) to Gemfile:

```rb
gem 'webdrivers', '~> 5.0'
```

2. Require webdrivers in your project:
```rb
require 'webdrivers'
```

3 Initialize driver as you normally would:
```rb
driver = Selenium::WebDriver.for :chrome
```

<div class="github">
    <a href ="https://github.com/SeleniumHQ/seleniumhq.github.io/blob/java_example/examples/ruby/spec/getting_started/install_drivers_spec.rb">
See full example on GitHub.</a>
</div>

{{< /tab >}}
{{< tab header="JavaScript" >}}
 *There is not a recommended driver manager for JavaScript at this time*
{{< /tab >}}
{{< tab header="Kotlin" >}}

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

{{< tabpane disableCodeBlock=true >}}
{{< tab header="Bash" >}}
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
{{< /tab >}}
{{< tab header="Zsh" >}}
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
{{< /tab >}}
{{< tab header="Windows" >}}
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
{{< /tab >}}
{{< /tabpane >}}

如果`PATH`配置正确, 
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

System.setProperty("webdriver.chrome.driver","/path/to/chromedriver");
ChromeDriver driver = new ChromeDriver();

{{< /tab >}}

{{< tab header="Python" >}}

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

const service = new chrome.ServiceBuilder('/path/to/chromedriver').build();
const driver = new Builder().forBrowser('chrome').setChromeService(service).build();
{{< /tab >}}

{{< tab header="Kotlin" >}}

// Please raise a PR to add code sample

{{< /tab >}}

{{< /tabpane >}}


## Advanced Configuration

More information on how you can change the driver behavior can be found on the
[Configuring driver parameters]({{< ref "/documentation/webdriver/drivers.md" >}}) page.

## Next Step
[Open and close a browser]({{< ref "open_browser.md" >}})
