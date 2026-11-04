# IE 特定功能

> 这些是 Microsoft Internet Explorer 浏览器特有的功能和特性.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/internet_explorer/

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
[IE 驱动程序服务器页面](https://www.selenium.dev/zh-cn/documentation/legacy/ie_driver_server/index.md)

## 选项

在 Internet Explorer 兼容模式下启动 Microsoft Edge 浏览器, 
并使用基本定义的选项, 
看起来就像这样:

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.attachToEdgeChrome();
options.withEdgeExecutablePath(getEdgeLocation());
driver = new InternetExplorerDriver(options);
```

**Python**

```python
options = webdriver.IeOptions()
options.attach_to_edge_chrome = True
options.edge_executable_path = edge_bin
driver = webdriver.Ie(options=options)
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
options.AttachToEdgeChrome = true;
options.EdgeExecutablePath = GetEdgeLocation();
_driver = new InternetExplorerDriver(options);
```

**Ruby**

```ruby
options = Selenium::WebDriver::IE::Options.new
options.attach_to_edge_chrome = true
options.edge_executable_path = edge_location
@driver = Selenium::WebDriver.for :ie, options: options
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

截至 Internet Explorer 驱动程序 v4.5.0:
* 如果系统中没有 IE（Windows 11 中的默认设置）, 则无需使用上述两个参数.
  使用上述两个参数.IE 驱动程序将使用 Edge, 并自动对其进行定位.
* 如果系统上同时存在 IE 和 Edge, 则只需设置附加到 Edge、
  IE 驱动程序将自动在系统中定位 Edge.

因此, 如果系统中没有 IE, 您只需要:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
driver = new InternetExplorerDriver(options);
```

**Python**

```python
options = webdriver.IeOptions()
driver = webdriver.Ie(options=options)
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
_driver = new InternetExplorerDriver(options);
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.ie
@driver = Selenium::WebDriver.for :ie, options: options
```

**JavaScript**

```javascript
let driver = await new Builder()
.forBrowser('internet explorer')
.setIEOptions(options)
.build();
```

**Kotlin**

```kotlin
val options = InternetExplorerOptions()
val driver = InternetExplorerDriver(options)
```

以下是几种具有不同功能的常见用例:

### fileUploadDialogTimeout

在某些环境中, 当打开文件上传对话框时, Internet Explorer可能会超时. IEDriver的默认超时为1000毫秒, 但您可以使用fileUploadDialogTimeout功能来增加超时时间.

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.waitForUploadDialogUpTo(Duration.ofSeconds(2));
WebDriver driver = new RemoteWebDriver(options); 

```

**Python**

```python
options = webdriver.IeOptions()
options.file_upload_timeout = 2000
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
options.FileUploadDialogTimeout = TimeSpan.FromMilliseconds(2000);
var driver = new RemoteWebDriver(options);

```

**Ruby**

```ruby
@options.file_upload_dialog_timeout = 2000
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().fileUploadDialogTimeout(2000);
let driver = await Builder()
          .setIeOptions(options)
          .build();  

```

**Kotlin**

```kotlin
val options = InternetExplorerOptions()
options.waitForUploadDialogUpTo(Duration.ofSeconds(2))
val driver = RemoteWebDriver(options)

```

### ensureCleanSession

设置为 `true`时, 
此功能将清除InternetExplorer所有正在运行实例的
 _缓存, 浏览器历史记录和Cookies_ 
 (包括手动启动或由驱动程序启动的实例) .
默认情况下, 此设置为 `false`.

使用此功能将导致启动浏览器时性能下降, 
因为驱动程序将等待直到缓存清除后再启动IE浏览器.   

此功能接受一个布尔值作为参数.

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.destructivelyEnsureCleanSession();
WebDriver driver = new RemoteWebDriver(options);

```

**Python**

```python
options = webdriver.IeOptions()
options.ensure_clean_session = True
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
options.EnsureCleanSession = true;
var driver = new RemoteWebDriver(options);

```

**Ruby**

```ruby
@options.ensure_clean_session = true
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ensureCleanSession(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 

```

**Kotlin**

```kotlin
val options = InternetExplorerOptions()
options.destructivelyEnsureCleanSession()
val driver = RemoteWebDriver(options)

```

### ignoreZoomSetting

InternetExplorer驱动程序期望浏览器的缩放级别为100%,
否则驱动程序将可能抛出异常.
通过将 _ignoreZoomSetting_ 设置为 _true_,
可以禁用此默认行为.

此功能接受一个布尔值作为参数. 

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.ignoreZoomSettings();
WebDriver driver = new RemoteWebDriver(options);

```

**Python**

```python
options = webdriver.IeOptions()
options.ignore_zoom_level = True
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
options.IgnoreZoomLevel = true;
var driver = new RemoteWebDriver(options);

```

**Ruby**

```ruby
@options.ignore_zoom_level = true
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ignoreZoomSetting(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 

```

**Kotlin**

```kotlin
val options = InternetExplorerOptions()
options.ignoreZoomSettings()
val driver = RemoteWebDriver(options)

```

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

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.introduceFlakinessByIgnoringSecurityDomains();
WebDriver driver = new RemoteWebDriver(options);

```

**Python**

```python
options = webdriver.IeOptions()
options.ignore_protected_mode_settings = True
```

**CSharp**

```csharp
var options = new InternetExplorerOptions();
options.IntroduceInstabilityByIgnoringProtectedModeSettings = true;
var driver = new RemoteWebDriver(options);

```

**Ruby**

```ruby
@options.ignore_protected_mode_settings = true
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().introduceFlakinessByIgnoringProtectedModeSettings(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 

```

**Kotlin**

```kotlin
val options = InternetExplorerOptions()
options.introduceFlakinessByIgnoringSecurityDomains()
val driver = RemoteWebDriver(options)

```

### silent

设置为 `true`时, 
此功能将禁止IEDriverServer的诊断输出.

此功能接受一个布尔值作为参数. 

**Java**

```java
InternetExplorerOptions options = new InternetExplorerOptions();
options.setCapability("silent", true);
WebDriver driver = new InternetExplorerDriver(options);

```

**Python**

```python
service = webdriver.IeService(service_args=["--silent"])
driver = webdriver.Ie(service=service)
```

**CSharp**

```csharp
InternetExplorerOptions options = new InternetExplorerOptions();
options.AddAdditionalInternetExplorerOption("silent", true);
IWebDriver driver = new InternetExplorerDriver(options);

```

**Ruby**

```ruby
@options.silent = true
```

**JavaScript**

```javascript
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

```

**Kotlin**

```kotlin
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

```

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

**Java**

```java
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

```

**Python**

```python
options = webdriver.IeOptions()
options.add_argument("-private")

driver = webdriver.Ie(options=options)
```

**CSharp**

```csharp
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

```

**Ruby**

```ruby
@options.add_argument('-k')
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.addBrowserCommandSwitches('-k');
options.addBrowserCommandSwitches('-private');
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();

```

**Kotlin**

```kotlin
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

```

### forceCreateProcessApi

强制使用CreateProcess API启动Internet Explorer.
默认值为false. 

对于IE 8及更高版本, 
此选项要求将 "TabProcGrowth" 注册表值设置为0.

**Java**

```java
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

```

**Python**

```python
options = webdriver.IeOptions()
options.force_create_process_api = True

driver = webdriver.Ie(options=options)
```

**CSharp**

```csharp
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

```

**Ruby**

```ruby
@options.force_create_process_api = true
```

**JavaScript**

```javascript
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();

```

**Kotlin**

```kotlin
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

```

## 服务

[Service page](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/service/index.md)
描述了所有浏览器通用的服务设置.

### 日志输出

获取驱动程序日志有助于调试各种问题.
服务类可让您指示日志的去向.
除非用户指定了日志输出的位置, 
否则日志输出将被忽略.

#### 文件输出

更改日志输出以保存到特定文件:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
.withLogFile(getLogLocation())
```

**Note**: Java also allows setting file output by System Property:<br>
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY`<br>
Property value: String representing path to log file

**Python**

```python
service = webdriver.IeService(log_output=log_path, log_level="INFO")

driver = webdriver.Ie(service=service)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.log = file_name
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### 控制台输出

要更改日志输出, 使其在控制台中显示为标准输出:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
.withLogOutput(System.out)
```

**Note**: Java also allows setting console output by System Property;<br>
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY`<br>
Property value: `DriverService.LOG_STDOUT` or `DriverService.LOG_STDERR`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.IeService(log_output=subprocess.STDOUT)

driver = webdriver.Ie(service=service)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.log = $stdout
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 日志级别
有 6 种可用的日志级别:`FATAL`、`ERROR`、`WARN`、`INFO`、`DEBUG` 和 `TRACE`
如果指定了日志输出, 默认级别为`FATAL`.

**Java**

```java
.withLogLevel(InternetExplorerDriverLogLevel.WARN)
```

**Note**: Java also allows setting log level by System Property:<br>
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGLEVEL_PROPERTY`<br>
Property value: String representation of `InternetExplorerDriverLogLevel.DEBUG.toString()` enum

**Python**

```python
service = webdriver.IeService(log_output=log_path, log_level="WARN")

driver = webdriver.Ie(service=service)
```

**CSharp**

```csharp
if (_tempPath == null || !File.Exists(_tempPath))
```

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.args << '-log-level=WARN'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 辅助文件路径

**Java**

```java
.withExtractPath(getTempDirectory())
```

**Note**: Java also allows setting log level by System Property:\
Property key: `InternetExplorerDriverService.IE_DRIVER_EXTRACT_PATH_PROPERTY`\
Property value: String representing path to supporting files directory

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.IeService(service_args=["–extract-path=" + temp_dir])

driver = webdriver.Ie(service=service)
```

**CSharp**

```csharp
}
```

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.args << "–extract-path=#{root_directory}"
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._
