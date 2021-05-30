---
title: "驱动要求"
weight: 2
---

通过 WebDriver，Selenium 支持市面上所有主流的浏览器，如 Chrom(ium)、Firefox、
Internet Explorer、Opera 和 Safari。 尽管并非所有浏览器都对远程控制提供官方支持，
但 WebDriver 尽可能使用浏览器的内置自动化支持来驱动浏览器。

WebDriver 的目标是尽可能模拟真实用户与浏览器的交互。  
在不同的浏览器中，这可能有不同的级别。有关不同驱动程序特性的详细信息，
请参见 _[驱动程序特性]({{<ref"/driver_idiosyncrasies/_index.md">}})_。


尽管所有的驱动程序共享一个面向用户的界面来控制浏览器，但它们设置浏览器会话的方式略有不同。
由于许多驱动程序实现是由第三方提供的，所以它们不包括在标准的 Selenium 发行版中。

驱动程序实例化、配置文件管理和各种特定于浏览器的设置都是具体参数的例子，这些参数根据浏览器有
不同的需求。本节介绍了使用不同浏览器的基本要求。

### 将可执行文件添加到 PATH 中
大多数驱动程序需要 Selenium 额外的可执行文件才能与浏览器通信。您可以在启动 WebDriver 
之前手动指定可执行文件的存放位置，但这会使测试的可移植性降低，因为可执行文件必须位于每台
计算机上的同一位置，或包含在测试代码存储库中。

通过将包含 WebDriver 二进制文件的文件夹添加到系统 path 环境变量中，Selenium 
将能够找到其他二进制文件，而无需您的测试代码来定位驱动程序的确切位置。

* 创建一个目录来放置可执行文件，例如 _C:\WebDriver\bin_ 或 _/opt/WebDriver/bin_
* 将目录添加到您的 path 中：
  * 在 Windows 上 - 以管理员身份打开命令提示符，然后运行以下命令将目录永久添加到计算机上所有用户的路径中：
```shell
setx /m path "%path%;C:\WebDriver\bin\"
```
  * 在 macOS 和 Linux 上的 Bash 用户 - 在终端中：
```shell
export PATH=$PATH:/opt/WebDriver/bin >> ~/.profile
```

* 现在您可以测试更改了。关闭所有打开的命令提示符，然后打开一个新的提示符。
输入您在上一步创建的文件夹中的某一个二进制文件的名称，例如：
  ```shell
  chromedriver
  ```
* 如果您的 `PATH` 配置正确，您将看到一些有关驱动程序启动的输出：
```text
Starting ChromeDriver 2.25.426935 (820a95b0b81d33e42712f9198c215f703412e1a1) on port 9515
Only local connections are allowed.
```

您可以通过按 <kbd>Ctrl + C</kbd> 重新获得对命令提示符的控制。

### 快速参考

| 浏览器 | 支持的操作系统 | 维护者 | 下载 | 问题追踪 |
| ------- | ------------ | ------------- | -------- | ------------- |
| Chromium/Chrome | Windows/macOS/Linux | 谷歌 | [下载](//chromedriver.storage.googleapis.com/index.html) | [问题](//bugs.chromium.org/p/chromedriver/issues/list) |
| 火狐 | Windows/macOS/Linux | Mozilla | [下载](//github.com/mozilla/geckodriver/releases) | [问题](//github.com/mozilla/geckodriver/issues) |
| Edge | Windows 10 | 微软 | [下载](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [问题](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&amp;q=webdriver) |
| Internet Explorer | Windows | Selenium 项目组 | [下载](//selenium-release.storage.googleapis.com/index.html) | [问题](//github.com/SeleniumHQ/selenium/labels/D-IE) |
| Safari | macOS El Capitan 及更高版本 | 苹果 | 内置 | [问题](//bugreport.apple.com/logon) |
| Opera | Windows/macOS/Linux | Opera | [下载](//github.com/operasoftware/operachromiumdriver/releases) | [问题](//github.com/operasoftware/operachromiumdriver/issues) |

### Chromium/Chrome

要驱动 Chrome 或 Chromium，您必须下载
 [chromedriver](//sites.google.com/a/chromium.org/chromedriver/downloads)
 并将其放在系统路径上的文件夹中。

在 Linux 或 macOS 上，这意味着修改 `PATH` 环境变量。通过执行以下命令，您可以看到由冒号分隔的目录组成的系统路径：

```shell
$ echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
```

要在路径上包含 chromedriver(如果还没有的话)，请确保包含 chromedriver 二进制文件的父目录。
下面的行将设置当前 `PATH` 环境变量的内容，在冒号后面添加一个额外的路径:

```shell
$ export PATH="$PATH:/path/to/chromedriver"
```

当您的路径上有 chromedriver 时，您应该可以从任何目录执行 _chromedriver_ 可执行文件。

要实例化 Chrome/Chromium 会话，您可以执行以下操作：

{{<code-tab>}}
  {{<code-panel language="java">}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

WebDriver driver = new ChromeDriver();
  {{</ code-panel>}}
  {{<code-panel language="python">}}
#简单赋值
from selenium.webdriver import Chrome

driver = Chrome()

#或者使用上下文管理器
from selenium.webdriver import Chrome

with Chrome() as driver:
    #你自己的代码放在这个缩进中
  {{</ code-panel>}}
  {{<code-panel language="csharp">}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

IWebDriver driver = new ChromeDriver();
  {{</ code-panel>}}
  {{<code-panel language="ruby">}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :chrome
  {{</ code-panel>}}
  {{<code-panel language="javascript">}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {let driver = await new Builder().forBrowser('chrome').build();
    //你的代码放在这个块中
})();
  {{</ code-panel>}}
  {{<code-panel language="kotlin">}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

val driver: WebDriver = ChromeDriver()
  {{</ code-panel>}}
{{</ code-tab>}}

请记住，您必须设置 chromedriver 可执行文件的路径。可以使用下行代码实现：

{{<code-tab>}}
  {{<code-panel language="java">}}
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
  {{</ code-panel>}}
  {{<code-panel language="python">}}
Chrome(executable_path='/path/to/chromedriver')
  {{</ code-panel>}}
  {{<code-panel language="csharp">}}
new ChromeDriver("/path/to/chromedriver");
  {{</ code-panel>}}
  {{<code-panel language="ruby">}}
Selenium::WebDriver::Chrome.driver_path = "/path/to/chromedriver"
  {{</ code-panel>}}
  {{<code-panel language="javascript">}}
chrome.setDefaultService(new chrome.ServiceBuilder('path/to/chromedriver').build());
  {{</ code-panel>}}
  {{<code-panel language="kotlin">}}
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver")
  {{</ code-panel>}}
{{</ code-tab>}}

chromedriver 被实现为 WebDriver 远程服务器，该服务器通过公开 Chrome 的内部自动化代理接口来指示浏览器该怎么做。

### 火狐浏览器

从 Selenium 3 开始，Mozilla 通过 [geckodriver](//github.com/mozilla/geckodriver) 
接管了火狐驱动程序的实现。火狐的新驱动程序被称为 geckodriver，可与 Firefox 48 及更高版本一起使用。
由于正在开发 Firefox WebDriver，因此 Firefox 版本越新，支持越好。

由于 geckodriver 是默认新的启动火狐浏览器的方式，您可以像 Selenium 2 那样实例化火狐浏览器:

{{<code-tab>}}
  {{<code-panel language="java">}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

WebDriver driver = new FirefoxDriver();
  {{</ code-panel>}}
  {{<code-panel language="python">}}
#简单赋值
from selenium.webdriver import Firefox

driver = Firefox()
#或者使用上下文管理器
from selenium.webdriver import Firefox

with Firefox() as driver:
   #你自己的代码放在这个缩进中
  {{</ code-panel>}}
  {{<code-panel language="csharp">}}
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;

IWebDriver driver = new FirefoxDriver();
  {{</ code-panel>}}
  {{<code-panel language="ruby">}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :firefox
  {{</ code-panel>}}
  {{<code-panel language="javascript">}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {let driver = await new Builder().forBrowser('firefox').build();
   // 你自己的代码放在这个块中
})();
  {{</ code-panel>}}
  {{<code-panel language="kotlin">}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Firefox.FirefoxDriver

val driver: WebDriver = FirefoxDriver()
  {{</ code-panel>}}
{{</ code-tab>}}

如果您不想使用 PATH 设置 geckodriver 的位置，请在代码中设置 geckodriver 的位置：

{{<code-tab>}}
  {{<code-panel language="java">}}
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");
  {{</ code-panel>}}
  {{<code-panel language="python">}}
Firefox(executable_path='/path/to/geckodriver')
  {{</ code-panel>}}
  {{<code-panel language="csharp">}}
new FirefoxDriver("/path/to/geckodriver");
  {{</ code-panel>}}
  {{<code-panel language="ruby">}}
Selenium::WebDriver::Firefox.driver_path = "/path/to/geckodriver"
  {{</ code-panel>}}
  {{<code-panel language="javascript">}}
const firefox = require('selenium-webdriver/firefox');

const serviceBuilder = new firefox.ServiceBuilder("/path/to/geckodriver");

(async function myFunction() {
    let driver = await new Builder()
        .forBrowser('firefox')
        .setFirefoxService(serviceBuilder)
        .build();
        //your code inside this block
})();
  {{</ code-panel>}}
  {{<code-panel language="kotlin">}}
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver")
  {{</ code-panel>}}
{{</ code-tab>}}

也可以在运行时设置属性：

```shell
mvn test -Dwebdriver.gecko.driver=/path/to/geckodriver
```

当前，可以通过安装 Firefox [47.0.1](//ftp.mozilla.org/pub/firefox/releases/47.0.1/)
或 [45 ESR](//ftp.mozilla.org/pub/firefox/releases/45.0esr/) 并指定 **marionette** 
功能为 **false** 来还原到功能更强大的旧版 Firefox 驱动程序。Firefox 的更高版本不再兼容。

### Edge

Edge 是微软最新的浏览器，内置在 Windows 10 和 Windows Server 2016 中。Edge 的更新与 Windows 
的主要更新捆绑在一起，因此您需要下载一个与您当前安装的 Windows 版本的版本号匹配的二进制文件。
[Edge 开发者网站](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) 
包含指向所有可用的二进制文件的链接。针对 EdgeDriver 实现的 bug 可以在
 [微软网站](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&q=webdriver)
 中提出。如果您想针对 Edge 运行测试，但没有 Windows 10，微软在
  [Edge 开发者网站](//developer.microsoft.com/en-us/microsoft-edge/tools/vms/) 
  上为测试人员提供了免费的虚拟机。

{{<code-tab>}}
  {{<code-panel language="java">}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

WebDriver driver = new EdgeDriver();
  {{</ code-panel>}}
  {{<code-panel language="python">}}
#简单赋值
from selenium.webdriver import Edge

driver = Edge()
#或使用上下文管理器
from selenium.webdriver import Edge

with Edge() as driver:
   #你的代码放在这个缩进中
  {{</ code-panel>}}
  {{<code-panel language="csharp">}}
using OpenQA.Selenium;
using OpenQA.Selenium.Edge;

IWebDriver driver = new EdgeDriver();
  {{</ code-panel>}}
  {{<code-panel language="ruby">}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :edge
  {{</ code-panel>}}
  {{<code-panel language="javascript">}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {let driver = await new Builder().forBrowser('MicrosoftEdge').build();
   // 你的代码放在这个块中
})();
  {{</ code-panel>}}
  {{<code-panel language="kotlin">}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver

val driver: WebDriver = EdgeDriver()
  {{</ code-panel>}}
{{</ code-tab>}}

如果您的路径中没有 Edge 驱动程序，您可以使用以下行设置路径:

{{<code-tab>}}
  {{<code-panel language="java">}}
System.setProperty("webdriver.edge.driver", "C:/path/to/MicrosoftWebDriver.exe");
  {{</ code-panel>}}
  {{<code-panel language="python">}}
Edge(executable_path='/path/to/MicrosoftWebDriver.exe')
  {{</ code-panel>}}
  {{<code-panel language="csharp">}}
new EdgeDriver("/path/to/MicrosoftWebDriver.exe");
  {{</ code-panel>}}
  {{<code-panel language="ruby">}}
Selenium::WebDriver::Edge.driver_path = "C:/path/to/MicrosoftWebDriver.exe"
  {{</ code-panel>}}
  {{<code-panel language="javascript">}}
const {Builder} = require("selenium-webdriver");
const edge = require('selenium-webdriver/edge');
let service = new edge.ServiceBuilder("/path/to/msedgedriver.exe");
(async function test() {
    let driver = await new Builder()
                .setEdgeService(service)
                .forBrowser('MicrosoftEdge')
                .build();
})();
  {{</ code-panel>}}
  {{<code-panel language="kotlin">}}
System.setProperty("webdriver.edge.driver", "C:/path/to/MicrosoftWebDriver.exe")
  {{</ code-panel>}}
{{</ code-tab>}}

### IE 浏览器

在 Windows 10 之前，IE 一直是微软的默认浏览器，尽管它仍被包含在 Windows 10 中。
Internet Explorer Driver 是唯一的 Selenium 项目组旨在支持
[微软认为当前](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer)
版本的驱动程序。旧版本可能可以工作，但将不受支持。

虽然 Selenium 项目为 32 位和 64 位版本的 IE 提供了二进制文件，但是 IE 10 和 11 在 64 位驱动程序上
有一些[限制](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)，
但是使用 32 位驱动程序仍然可以很好地工作。应该注意的是，由于 IE 浏览器的首选项是根据登录用户的帐户保存的，
因此需要进行一些[额外的设置](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver#required-configuration)。

{{<code-tab>}}
  {{<code-panel language="java">}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

WebDriver driver = new InternetExplorerDriver();
  {{</ code-panel>}}
  {{<code-panel language="python">}}
#简单赋值
from selenium.webdriver import Ie

driver = Ie()
#或使用上下文管理器
from selenium.webdriver import Ie

with Ie() as driver:
   #你的代码放在这个缩进中
  {{</ code-panel>}}
  {{<code-panel language="csharp">}}
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

IWebDriver driver = new InternetExplorerDriver();
  {{</ code-panel>}}
  {{<code-panel language="ruby">}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :internet_explorer
  {{</ code-panel>}}
  {{<code-panel language="javascript">}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {let driver = await new Builder().forBrowser('internet explorer').build();
   // 你的代码放在这个块中
})();
  {{</ code-panel>}}
  {{<code-panel language="kotlin">}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.ie.InternetExplorerDriver

val driver: WebDriver = InternetExplorerDriver()
  {{</ code-panel>}}
{{</ code-tab>}}

如果 IE 浏览器驱动程序不在您的路径中，您可以使用以下行设置路径：

{{<code-tab>}}
  {{<code-panel language="java">}}
System.setProperty("webdriver.ie.driver", "C:/path/to/IEDriver.exe");
  {{</ code-panel>}}
  {{<code-panel language="python">}}
Ie(executable_path='/path/to/IEDriverServer.exe')
  {{</ code-panel>}}
  {{<code-panel language="csharp">}}
new InternetExplorerDriver("C:/path/to/IEDriver.exe");
  {{</ code-panel>}}
  {{<code-panel language="ruby">}}
Selenium::WebDriver::IE.driver_path = "C:/path/to/IEDriver.exe"
  {{</ code-panel>}}
  {{<code-panel language="javascript">}}
const {Builder} = require("selenium-webdriver");
const ie = require('selenium-webdriver/ie');
let service = new ie.ServiceBuilder("/path/to/IEDriverServer.exe");
(async function test() {
    let driver = await new Builder()
                .setIeService(service)
                .forBrowser('internet explorer')
                .build();
})();
  {{</ code-panel>}}
  {{<code-panel language="kotlin">}}
System.setProperty("webdriver.ie.driver", "C:/path/to/IEDriver.exe")
  {{</ code-panel>}}
{{</ code-tab>}}

微软还为 [Windows 7 和 8.1 中的 IE 11](//www.microsoft.com/en-gb/download/details.aspx?id=44069) 
提供了一个 WebDriver 二进制版本。自 2014 年以来就没有更新过，它是基于 W3 规范的草案版本。
[Jim Evans](//jimevansmusic.blogspot.co.uk/2014/09/using-internet-explorer-webdriver.html)
写了一篇关于微软实现的优秀文章。

### Opera

Opera 的当前版本是建立在 Chromium 引擎之上的，而 WebDriver 现在是通过闭源的 
[Opera Chromium 驱动程序](//github.com/operasoftware/operachromiumdriver/releases)来支持的，
它可以被[添加到您的路径](#将可执行文件添加到-path-中)或作为系统属性。

实例化一个驱动程序会话类似于 Firefox 和 Chromium：

{{<code-tab>}}
  {{<code-panel language="java">}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

WebDriver driver = new OperaDriver();
  {{</ code-panel>}}
  {{<code-panel language="python">}}
#简单赋值
from selenium.webdriver import Opera

driver = Opera()
#或使用上下文管理器
from selenium.webdriver import Opera

with Opera() as driver:
   #你的代码放在这个缩进中
  {{</ code-panel>}}
  {{<code-panel language="csharp">}}
using OpenQA.Selenium;
using OpenQA.Selenium.Opera;

IWebDriver driver = new OperaDriver();
  {{</ code-panel>}}
  {{<code-panel language="ruby">}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :opera
  {{</ code-panel>}}
  {{<code-panel language="javascript">}}
const {Builder} = require("selenium-webdriver");
const opera = require('selenium-webdriver/opera');
(async function test() {
    let driver = await new Builder()
        .forBrowser('opera')
        .build();
})();
  {{</ code-panel>}}
  {{<code-panel language="kotlin">}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.opera.OperaDriver

val driver: WebDriver = OperaDriver()
  {{</ code-panel>}}
{{</ code-tab>}}

### Safari

High Sierra and later:
* 首先从终端运行以下命令
  时间并在提示符下键入密码以授权WebDriver
```shell
safaridriver --enable
```

El Capitan and Sierra:

* 启用 Safari 首选项中的 Developer 菜单
* 从 “开发” 菜单中选择 “允许远程自动化” 选项
* 第一次运行时在终端输入以下命令，并在提示符处输入密码以授权 WebDriver

```shell
/usr/bin/safaridriver -p 1337</
```

你可以使用以下代码开始一个驱动程序会话:

{{<code-tab>}}
  {{<code-panel language="java">}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

WebDriver driver = new SafariDriver();
  {{</ code-panel>}}
  {{<code-panel language="python">}}
#简单赋值
from selenium.webdriver import Safari

driver = Safari()
#或使用上下文管理器
from selenium.webdriver import Safari

with Safari() as driver:
   #你的代码放在这个缩进中
  {{</ code-panel>}}
  {{<code-panel language="csharp">}}
using OpenQA.Selenium;
using OpenQA.Selenium.Safari;

IWebDriver driver = new SafariDriver();
  {{</ code-panel>}}
  {{<code-panel language="ruby">}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :safari
  {{</ code-panel>}}
  {{<code-panel language="javascript">}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {let driver = await new Builder().forBrowser('safari').build();
   // 你的代码放在这个块中
})();
  {{</ code-panel>}}
  {{<code-panel language="kotlin">}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.safari.SafariDriver

val driver: WebDriver = SafariDriver()
  {{</ code-panel>}}
{{</ code-tab>}}

那些希望在 iOS 上实现 Safari 自动化的人应该关注[Appium 项目](//appium.io/)。
虽然 Safari 之前也支持 Windows，但苹果早就放弃了对它的支持，这让它成为了一个糟糕的测试平台。

## 模拟浏览器

### HtmlUnit

HtmlUnit 是一个 “针对 Java 程序的无图形界面浏览器”。它为 HTML 文档建模，并提供一个 API，
允许您调用页面、填写表单、单击链接等。它支持 JavaScript，能够使用 AJAX 库，根据使用的配置模拟 
Chrome、Firefox 或 IE。它已经迁移到一个[新位置](http://htmlunit.sourceforge.net/gettingStarted.html) 了。
源文件保存在 svn 上。


### PhantomJS

PhantomJS 是一款基于 Webkit 的无头浏览器，尽管它的版本比谷歌 Chrome 或 Safari 要老得多。虽然曾经很流行，
但现在明智的做法是避免 PhantomJS。这个项目 
[从 8 月 5 日起](//groups.google.com/forum/#!topic/phantomjs/9aI5d-LDuNE) 
就一直没有被维护过，所以尽管网络会继续变化，PhantomJS 也不会更新。在谷歌宣布可以无头运行 
Chrome 之后，现在 Mozilla 的火狐浏览器也提供了这个功能。
