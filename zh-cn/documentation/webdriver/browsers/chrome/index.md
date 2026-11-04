# Chrome 特定功能

> 特定于 Google Chrome 浏览器的功能和特性.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/chrome/

---

默认情况下，Selenium 4与Chrome v75及更高版本兼容. 但是请注意Chrome浏览器的版本与chromedriver的主版本需要匹配.

## Options

所有浏览器的通用功能请看这 [Options page](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/options/index.md).

Chrome浏览器的特有功能可以在谷歌的页面找到: [Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)

基于默认选项的Chrome浏览器会话看起来是这样:

**Java**

```java
ChromeOptions options = getDefaultChromeOptions();
driver = new ChromeDriver(options);
```

**Python**

```python
options = webdriver.ChromeOptions()
options.add_argument("--no-sandbox")
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
var options = new ChromeOptions();
driver = new ChromeDriver(options);
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
@driver = Selenium::WebDriver.for :chrome, options: options
```

**JavaScript**

```javascript
const Options = new Chrome.Options();
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(Options)
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

下面是一些不同功能的常见示例:

### 参数

`args` 参数用于启动浏览器时要使用的命令行开关列表.
有两个很好的资源可以用于研究这些参数:
* [Chrome Flags for Tooling](https://github.com/GoogleChrome/chrome-launcher/blob/main/docs/chrome-flags-for-tools.md)
* [List of Chromium Command Line Switches](https://peter.sh/experiments/chromium-command-line-switches/)

常用的参数包括 `--start-maximized`, `--headless=new` 以及 `--user-data-dir=...`

向选项添加参数:

**Java**

```java
options.addArguments("--start-maximized");
```

**Python**

```python
options.add_argument("--start-maximized")
```

**CSharp**

```csharp
options.AddArgument("--start-maximized");
```

**Ruby**

```ruby
options.args << '--start-maximized'
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.addArguments('--headless=new'))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 从指定位置启动浏览器

`binary` 参数接收一个使用浏览器的备用路径,通过这个参数你可以使用chromedriver 去驱动各种基于Chromium 内核的浏览器.

添加一个浏览器地址到选项中:

**Java**

```java
options.setBinary(getChromeLocation());
```

**Python**

```python
options.binary_location = chrome_bin
```

**CSharp**

```csharp
options.BinaryLocation = await GetChromeLocationAsync();
```

**Ruby**

```ruby
options.binary = chrome_location
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setChromeBinaryPath(`Path to chrome binary`))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 添加扩展程序

`extensions` 参数接受crx文件. 至于解压的目录,
请使用 `load-extension` 参数代替,
正如 [这篇文章](https://chromedriver.chromium.org/extensions) 所示.

添加一个扩展程序到选项中:

**Java**

```java
ChromeOptions options = getDefaultChromeOptions();
options.enableBiDi();
options.addArguments("--remote-debugging-pipe");
options.addArguments("--enable-unsafe-extension-debugging");
driver = new ChromeDriver(options);

Path path = Paths.get("src/test/resources/extensions/selenium-example");
WebExtension extension = new WebExtension(driver);
ExtensionPath extensionPath = new ExtensionPath(path.toString());
InstallExtensionParameters parameters = new InstallExtensionParameters(extensionPath);
extension.install(parameters);
```

**Python**

```python
options.add_extension(extension_file_path)
```

**CSharp**

```csharp
var options = new ChromeOptions();
options.UseWebSocketUrl = true;
options.AddArgument("--remote-debugging-pipe");
options.AddArgument("--enable-unsafe-extension-debugging");

driver = new ChromeDriver(options);

var baseDir = AppDomain.CurrentDomain.BaseDirectory;
var extensionDir = Path.GetFullPath(Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example"));

var bidi = await driver.AsBiDiAsync();
await bidi.WebExtension.InstallAsync(new ExtensionPath(extensionDir));
```

**Ruby**

```ruby
options.add_extension(extension_file_path)
```

**JavaScript**

```javascript
const options = new Chrome.Options();
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.addExtensions(['./test/resources/extensions/webextensions-selenium-example.crx']))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 保持浏览器的打开状态

将 `detach` 参数设置为true将在驱动过程结束后保持浏览器的打开状态.

添加一个布尔值到选项中:

**Java**

**Note**: This is already the default behavior in Java.

**Python**

```python
options.add_experimental_option("detach", True)
```

**CSharp**

**Note**: This is already the default behavior in .NET.

**Ruby**

```ruby
options.detach = true
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.detachDriver(true))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 排除的参数

Chrome 添加了各种参数，如果你不希望添加某些参数，可以将其传入 `excludeSwitches`.
一个常见的例子是重新打开弹出窗口阻止程序.
默认参数的完整列表可以参考
[Chromium 源码](https://source.chromium.org/chromium/chromium/src/+/main:chrome/test/chromedriver/chrome_launcher.cc)

设置排除参数至选项中:

**Java**

```java
options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
```

**Python**

```python
options.add_experimental_option('excludeSwitches', ['disable-popup-blocking'])
```

**CSharp**

```csharp
options.AddExcludedArgument("disable-popup-blocking");
```

**Ruby**

```ruby
options.exclude_switches << 'disable-popup-blocking'
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.excludeSwitches('enable-automation'))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 服务

创建默认 Service 对象的示例,
以及用于设置驱动程序位置和端口
可以参考 [驱动服务](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/service/index.md) 页面.

### 日志输出

获取驱动程序日志有助于调试问题.
使用 Service 类, 可以指明日志的路径.
除非用户将其定向到某个位置, 否则将忽略日志记录输出.

#### 文件输出

更改日志记录输出以保存到特定文件:

**Java**

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withLogFile(logLocation).build();
```

**注意**: Java 还允许通过系统属性设置文件输出:<br>
属性键: `ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY`<br>
属性值: 表示日志文件路径的字符串

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(log_output=log_path)
```

**CSharp**

```csharp
service.LogPath = GetLogLocation();
```

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.log = file_name
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### 命令行输出

更改日志记录输出以在控制台中显示为标准输出:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withLogOutput(System.out).build();
```

**注意**: Java 还允许通过系统属性设置控制台输出;<br>
属性键: `ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY`<br>
属性值: `DriverService.LOG_STDOUT` 或 `DriverService.LOG_STDERR`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(log_output=subprocess.STDOUT)
```

**CSharp**

_Not available in this language._

**Ruby**

`$stdout` and `$stderr` are both valid values
_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.log = $stdout
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 日志级别
共有六种日志级别: `ALL`, `DEBUG`, `INFO`, `WARNING`, `SEVERE`, 以及 `OFF`.
注意 `--verbose` 等效于 `--log-level=ALL` 以及 `--silent` 等效于 `--log-level=OFF`,
因此, 此示例只是通用地设置日志级别:

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withLogLevel(ChromiumDriverLogLevel.DEBUG).build();
```

**注意**: Java 还允许通过系统属性设置日志级别:<br>
属性键: `ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY`<br>
属性值: `ChromiumDriverLogLevel` 枚举的字面值

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(service_args=['--log-level=DEBUG'], log_output=subprocess.STDOUT)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.args << '--log-level=DEBUG'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 日志文件功能
有 2 个功能仅在写入文件时可用:
* 追加日志
* 可读时间戳

要使用它们, 您还需要显式指定日志路径和日志级别.
日志输出将由驱动程序管理,
而不是由进程管理, 因此可能会看到细微的差异.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withAppendLog(true).withReadableTimestamp(true).build();
```

**注意**: Java 还允许通过系统属性切换这些功能:<br>
属性键: `ChromeDriverService.CHROME_DRIVER_APPEND_LOG_PROPERTY` 以及 `ChromeDriverService.CHROME_DRIVER_READABLE_TIMESTAMP`<br>
属性值: `"true"` 或 `"false"`

**Python**

```python
service = webdriver.ChromeService(service_args=['--append-log', '--readable-timestamp'], log_output=log_path)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.args << '--append-log'
service.args << '--readable-timestamp'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 禁用构建检查

Chromedriver 和 Chrome 浏览器版本应该匹配, 如果它们不匹配, 驱动程序将出错.
如果您停用构建检查功能, 则可以强制将驱动程序与任何版本的 Chrome 一起使用.
请注意, 这是一项不受支持的功能, 并且不会调查 bug.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withBuildCheckDisabled(true).build();
```

**注意**: Java 还允许通过系统属性禁用构建检查:<br>
属性键: `ChromeDriverService.CHROME_DRIVER_DISABLE_BUILD_CHECK`<br>
属性值: `"true"` 或 `"false"`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(service_args=['--disable-build-check'], log_output=subprocess.STDOUT)
```

**CSharp**

```csharp
service.DisableBuildCheck = true;
```

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.args << '--disable-build-check'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 特殊功能

### Casting

你可以驱动 Chrome Cast 设备，包括共享选项卡

**Java**

```java
List<Map<String, String>> sinks = driver.getCastSinks();
if (!sinks.isEmpty()) {
  String sinkName = sinks.get(0).get("name");
  driver.startTabMirroring(sinkName);
  driver.stopCasting(sinkName);
}
```

**Python**

```python
sinks = driver.get_sinks()
if sinks:
    sink_name = sinks[0]['name']
    driver.start_tab_mirroring(sink_name)
    driver.stop_casting(sink_name)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
sinks = @driver.cast_sinks
unless sinks.empty?
  device_name = sinks.first['name']
  @driver.start_cast_tab_mirroring(device_name)
  expect { @driver.stop_casting(device_name) }.not_to raise_exception
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 网络条件

您可以模拟各种网络条件.

以下示例适用于本地 webdrivers. 针对远程 webdrivers,
请参考
[Remote WebDriver](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/remote_webdriver/index.md) 页面.

**Java**

```java
ChromiumNetworkConditions networkConditions = new ChromiumNetworkConditions();
networkConditions.setOffline(false);
networkConditions.setLatency(java.time.Duration.ofMillis(20)); // 20 ms of latency
networkConditions.setDownloadThroughput(2000 * 1024 / 8); // 2000 kbps
networkConditions.setUploadThroughput(2000 * 1024 / 8);   // 2000 kbps

((ChromeDriver) driver).setNetworkConditions(networkConditions);
```

**Python**

```python
network_conditions = {
    "offline": False,
    "latency": 20,  # 20 ms of latency
    "download_throughput": 2000 * 1024 / 8,  # 2000 kbps
    "upload_throughput": 2000 * 1024 / 8,    # 2000 kbps
}
driver.set_network_conditions(**network_conditions)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
@driver.network_conditions = {offline: false, latency: 100, throughput: 200}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 日志

**Java**

```java
LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
```

**Python**

```python
logs = driver.get_log("browser")
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
logs = @driver.logs.get(:browser)
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 权限

**Java**

```java
driver.setPermission("camera", "denied");
```

**Python**

```python
driver.set_permissions('camera', 'denied')
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
@driver.add_permission('camera', 'denied')
@driver.add_permissions('clipboard-read' => 'denied', 'clipboard-write' => 'prompt')
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### DevTools

详见 [Chrome DevTools](https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/cdp/index.md)  部分以获取有关使用Chrome DevTools的更多信息
