# IE特有の機能

> これらは、Microsoft Internet Explorerブラウザに特有の機能と機能です。

URL: https://www.selenium.dev/ja/documentation/webdriver/browsers/internet_explorer/

---

As of June 2022, Selenium officially no longer supports standalone Internet Explorer.
The Internet Explorer driver still supports running Microsoft Edge in "IE Compatibility Mode."

## Special considerations

The IE Driver is the only driver maintained by the Selenium Project directly.
While binaries for both the 32-bit and 64-bit
versions of Internet Explorer are available, there are some
[known limitations](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)
with the 64-bit driver. As such it is recommended to use the 32-bit driver.

Additional information about using Internet Explorer can be found on the
[IE Driver Server page](https://www.selenium.dev/ja/documentation/legacy/ie_driver_server/index.md)

## Options

Starting a Microsoft Edge browser in Internet Explorer Compatibility mode with basic defined options looks like this:

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

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

As of Internet Explorer Driver v4.5.0:
* If IE is not present on the system (default in Windows 11), you do not need to 
use the two parameters above. IE Driver will use Edge and will automatically locate it. 
* If IE and Edge are both present on the system, you only need to set attaching to Edge,
IE Driver will automatically locate Edge on your system.

So, if IE is not on the system, you only need:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#moving-examples))._

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

Here are a few common use cases with different capabilities:

### fileUploadDialogTimeout

環境によっては、ファイルアップロードダイアログを開くときにInternet Explorerがタイムアウトする場合があります。 IEDriverのデフォルトのタイムアウトは1000ミリ秒ですが、fileUploadDialogTimeout capabilityを使用してタイムアウトを増やすことができます。

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

この機能を `true` に設定すると、手動またはドライバーによって開始されたものを含め、
InternetExplorerの実行中のすべてのインスタンスのキャッシュ、ブラウザー履歴、およびCookieがクリアされます。
デフォルトでは、`false` に設定されています。

この機能を使用すると、ドライバーがIEブラウザーを起動する前にキャッシュがクリアされるまで待機するため、
ブラウザーの起動中にパフォーマンスが低下します。

このケイパビリティは、ブール値をパラメーターとして受け入れます。

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

InternetExplorerドライバーは、ブラウザーのズームレベルが100％であることを想定しています。
それ以外の場合、ドライバーは例外をスローします。
このデフォルトの動作は、 _ignoreZoomSetting_ を _true_ に設定することで無効にできます。

このケイパビリティは、ブール値をパラメーターとして受け入れます。

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

新しいIEセッションの起動中に _保護モード_ チェックをスキップするかどうか。

設定されておらず、 _保護モード_ 設定がすべてのゾーンで同じでない場合、
ドライバーによって例外がスローされます。

ケイパビリティを `true` に設定すると、テストが不安定になったり、応答しなくなったり、
ブラウザがハングしたりする場合があります。
ただし、これはまだ2番目に良い選択であり、最初の選択は *常に* 
各ゾーンの保護モード設定を手動で実際に設定することです。
ユーザーがこのプロパティを使用している場合、「ベストエフォート」のみがサポートされます。

このケイパビリティは、ブール値をパラメーターとして受け入れます。

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

`true` に設定すると、このケイパビリティはIEDriverServerの診断出力を抑制します。

このケイパビリティは、ブール値をパラメーターとして受け入れます。

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

### IE Command-Line Options

Internet Explorerには、ブラウザーのトラブルシューティングと構成を可能にするいくつかのコマンドラインオプションが含まれています。

次に、サポートされているいくつかのコマンドラインオプションについて説明します。

* _-private_ : IEをプライベートブラウジングモードで起動するために使用されます。
これはIE 8以降のバージョンで機能します。

* _-k_ : Internet Explorerをキオスクモードで起動します。
ブラウザは、アドレスバー、ナビゲーションボタン、またはステータスバーを表示しない最大化されたウィンドウで開きます。

* _-extoff_ : アドオンなしモードでIEを起動します。
このオプションは、ブラウザーのアドオンに関する問題のトラブルシューティングに特に使用されます。
IE 7以降のバージョンで動作します。

注：コマンドライン引数が機能するためには、 __forceCreateProcessApi__ を順番に有効にする必要があります。

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
        println(caps);
    } finally {
        driver.quit()
    }
}

```

### forceCreateProcessApi

CreateProcess APIを使用してInternet Explorerを強制的に起動します。
デフォルト値はfalseです。

IE 8以降の場合、このオプションでは "TabProcGrowth" レジストリの値を0に設定する必要があります。

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

## Service

Service settings common to all browsers are described on the [Service page](https://www.selenium.dev/ja/documentation/webdriver/drivers/service/index.md).

### Log output

Getting driver logs can be helpful for debugging various issues. The Service class lets you
direct where the logs will go. Logging output is ignored unless the user directs it somewhere.

#### File output

To change the logging output to save to a specific file:

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

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### Console output

To change the logging output to display in the console as STDOUT:

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

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Log Level
There are 6 available log levels: `FATAL`, `ERROR`, `WARN`, `INFO`, `DEBUG`, and `TRACE`
If logging output is specified, the default level is `FATAL`

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

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Supporting Files Path

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

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._
