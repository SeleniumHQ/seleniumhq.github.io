---
title: "IE 特定功能"
linkTitle: "Internet Explorer"
weight: 8
description: >-
    这些是 Microsoft Internet Explorer 浏览器特有的功能和特性.
aliases: [
"/zh-cn/documentation/capabilities/internet_explorer"
]
---

自2022年6月起, Selenium 正式不再支持独立的 Internet Explorer.
Internet Explorer 驱动程序仍支持在 "IE 兼容模式" 下运行 Microsoft Edge.

## 特别注意事项

IE 驱动程序是 Selenium 项目直接维护的唯一驱动程序.
虽然 32 位和 64 位版本的版本的二进制文件, 
但有一些64位驱动程序的
[已知限制](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html).
因此, 建议使用 32 位驱动程序.

有关使用 Internet Explorer 的其他信息, 请参见
[IE 驱动程序服务器页面]({{< ref "/documentation/ie_driver_server/" >}})

## 选项

在 Internet Explorer 兼容模式下启动 Microsoft Edge 浏览器, 
并使用基本定义的选项, 
看起来就像这样:


{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#38-L41" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L11-L14" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L35-L38" >}}
{{% /tab %}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L17-L20" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


截至 Internet Explorer 驱动程序 v4.5.0:
* 如果系统中没有 IE（Windows 11 中的默认设置）, 则无需使用上述两个参数.
  使用上述两个参数.IE 驱动程序将使用 Edge, 并自动对其进行定位.
* 如果系统上同时存在 IE 和 Edge, 则只需设置附加到 Edge、
  IE 驱动程序将自动在系统中定位 Edge.

因此, 如果系统中没有 IE, 您只需要:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#46-L47" >}}
{{< /tab >}}
{{% tab header="Python" text=true %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L21-L22" >}}
{{% /tab %}}
{{% tab header="CSharp" text=true %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L44-L45" >}}
{{% /tab %}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L24-L25" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let driver = await new Builder()
.forBrowser('internet explorer')
.setIEOptions(options)
.build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-examples >}}
val options = InternetExplorerOptions()
val driver = InternetExplorerDriver(options)
{{< /tab >}}
{{< /tabpane >}}


以下是几种具有不同功能的常见用例:

### fileUploadDialogTimeout

在某些环境中, 当打开文件上传对话框时, Internet Explorer可能会超时. IEDriver的默认超时为1000毫秒, 但您可以使用fileUploadDialogTimeout功能来增加超时时间.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.waitForUploadDialogUpTo(Duration.ofSeconds(2));
WebDriver driver = new RemoteWebDriver(options); 
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L28-L29" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.FileUploadDialogTimeout = TimeSpan.FromMilliseconds(2000);
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L29" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().fileUploadDialogTimeout(2000);
let driver = await Builder()
          .setIeOptions(options)
          .build();  
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.waitForUploadDialogUpTo(Duration.ofSeconds(2))
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### ensureCleanSession

设置为 `true`时, 
此功能将清除InternetExplorer所有正在运行实例的
 _缓存, 浏览器历史记录和Cookies_ 
 (包括手动启动或由驱动程序启动的实例) .
默认情况下, 此设置为 `false`.

使用此功能将导致启动浏览器时性能下降, 
因为驱动程序将等待直到缓存清除后再启动IE浏览器.   

此功能接受一个布尔值作为参数.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.destructivelyEnsureCleanSession();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L38-L39" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.EnsureCleanSession = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L35" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ensureCleanSession(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.destructivelyEnsureCleanSession()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### ignoreZoomSetting

InternetExplorer驱动程序期望浏览器的缩放级别为100%,
否则驱动程序将可能抛出异常.
通过将 _ignoreZoomSetting_ 设置为 _true_,
可以禁用此默认行为.

此功能接受一个布尔值作为参数. 
 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.ignoreZoomSettings();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L48-L49" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.IgnoreZoomLevel = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L41" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ignoreZoomSetting(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.ignoreZoomSettings()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### ignoreProtectedModeSettings

启动新的IE会话时是否跳过 _保护模式_ 检查.

如果未设置, 
并且所有区域的 _保护模式_ 设置都不同, 
则驱动程序将可能引发异常.

如果将功能设置为 `true`, 
则测试可能会变得不稳定, 无响应, 或者浏览器可能会挂起.
但是, 到目前为止, 
这仍然是第二好的选择, 
并且第一选择应该 *始终* 是手动实际设置每个区域的保护模式设置.
如果用户正在使用此属性, 
则只会给予 "尽力而为" 的支持.

此功能接受一个布尔值作为参数. 
 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.introduceFlakinessByIgnoringSecurityDomains();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L58-L59" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.IntroduceInstabilityByIgnoringProtectedModeSettings = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L47" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().introduceFlakinessByIgnoringProtectedModeSettings(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.introduceFlakinessByIgnoringSecurityDomains()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### silent

设置为 `true`时, 
此功能将禁止IEDriverServer的诊断输出.

此功能接受一个布尔值作为参数. 
 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.setCapability("silent", true);
WebDriver driver = new InternetExplorerDriver(options);
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L68-L69" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.AddAdditionalInternetExplorerOption("silent", true);
IWebDriver driver = new InternetExplorerDriver(options);
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L53" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder,By, Capabilities} = require('selenium-webdriver');
let caps = Capabilities.ie();
caps.set('silent', true);

(async function example() {
    let driver = await new Builder()
        .forBrowser('internet explorer')
        .withCapabilities(caps)
        .build();
    try {
        await driver.get('http://www.google.com/ncr');
    }
    finally {
        await driver.quit();
    }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.setCapability("silent", true)
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps)
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

### IE 命令行选项

Internet Explorer包含几个命令行选项, 
使您可以进行故障排除和配置浏览器.

下面介绍了一些受支持的命令行选项

* _-private_ : 用于在私有浏览模式下启动IE.
这适用于IE 8和更高版本.

* _-k_ : 在kiosk模式下启动Internet Explorer. 
浏览器在一个最大化的窗口中打开, 
该窗口不显示地址栏, 导航按钮或状态栏.

* _-extoff_ : 在无附加模式下启动IE.
此选项专门用于解决浏览器加载项问题.
在IE 7和更高版本中均可使用.

注意: __forceCreateProcessApi__ 
应该启用命令行参数才能正常工作.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class ieTest {
    public static void main(String[] args) {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.useCreateProcessApiToLaunchIe();
        options.addCommandSwitches("-k");
        InternetExplorerDriver driver = new InternetExplorerDriver(options);
        try {
            driver.get("https://google.com/ncr");
            Capabilities caps = driver.getCapabilities();
            System.out.println(caps);
        } finally {
            driver.quit();
        }
    }
}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L76-L79" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

namespace ieTest {
 class Program {
  static void Main(string[] args) {
   InternetExplorerOptions options = new InternetExplorerOptions();
   options.ForceCreateProcessApi = true;
   options.BrowserCommandLineArguments = "-k";
   IWebDriver driver = new InternetExplorerDriver(options);
   driver.Url = "https://google.com/ncr";
  }
 }
}
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L58" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.addBrowserCommandSwitches('-k');
options.addBrowserCommandSwitches('-private');
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.useCreateProcessApiToLaunchIe()
    options.addCommandSwitches("-k")
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps)
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

### forceCreateProcessApi

强制使用CreateProcess API启动Internet Explorer.
默认值为false. 

对于IE 8及更高版本, 
此选项要求将 "TabProcGrowth" 注册表值设置为0.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class ieTest {
    public static void main(String[] args) {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.useCreateProcessApiToLaunchIe();
        InternetExplorerDriver driver = new InternetExplorerDriver(options);
        try {
            driver.get("https://google.com/ncr");
            Capabilities caps = driver.getCapabilities();
            System.out.println(caps);
        } finally {
            driver.quit();
        }
    }
}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L87-L90" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

namespace ieTest {
 class Program {
  static void Main(string[] args) {
   InternetExplorerOptions options = new InternetExplorerOptions();
   options.ForceCreateProcessApi = true;
   IWebDriver driver = new InternetExplorerDriver(options);
   driver.Url = "https://google.com/ncr";
  }
 }
}
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L63" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.useCreateProcessApiToLaunchIe()
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps)
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}



## 服务

[Service page]({{< ref "../drivers/service.md" >}})
描述了所有浏览器通用的服务设置.

### 日志输出

获取驱动程序日志有助于调试各种问题.
服务类可让您指示日志的去向.
除非用户指定了日志输出的位置, 
否则日志输出将被忽略.

#### 文件输出

更改日志输出以保存到特定文件:


{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L53" >}}
**Note**: Java also allows setting file output by System Property:\
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY`\
Property value: String representing path to log file
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L97-L99" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L82" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### 控制台输出


要更改日志输出, 使其在控制台中显示为标准输出:



{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L67" >}}
**Note**: Java also allows setting console output by System Property;\
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY`\
Property value: `DriverService.LOG_STDOUT` or `DriverService.LOG_STDERR`
{{% /tab %}}
{{< tab header="Python" text=true >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L109-L111" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L91" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


### 日志级别
有 6 种可用的日志级别:`FATAL`、`ERROR`、`WARN`、`INFO`、`DEBUG` 和 `TRACE`
如果指定了日志输出, 默认级别为`FATAL`.



{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L82" >}}
**Note**: Java also allows setting log level by System Property:\
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGLEVEL_PROPERTY`\
Property value: String representation of `InternetExplorerDriverLogLevel.DEBUG.toString()` enum
{{% /tab %}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L121-L123" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L85" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L102" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


### 辅助文件路径


{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L94" >}}
**Note**: Java also allows setting log level by System Property:\
Property key: `InternetExplorerDriverService.IE_DRIVER_EXTRACT_PATH_PROPERTY`\
Property value: String representing path to supporting files directory
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L133-L135" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L98" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L112" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

