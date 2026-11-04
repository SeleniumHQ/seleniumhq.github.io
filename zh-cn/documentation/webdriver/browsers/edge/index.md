# Edge 特定功能

> 这些是特定于微软Edge浏览器的功能和特性.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/edge/

---

微软Edge是用Chromium实现的, 最早支持版本是v79.
与Chrome类似, Edge驱动的主版本号必须与Edge浏览器的主要版本匹配.

在 [Chrome 页面](https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/chrome/index.md) 上找到的所有capabilities和选项也适用于Edge.

## 选项

所有浏览器的共有功能在 [Options 页面](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/options/index.md).

Chromium独有的功能记录在谷歌的
[Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)

使用基本定义的选项启动 Edge 会话如下所示:

**Java**

```java

@Test
```

**Python**

```python
options = webdriver.EdgeOptions()
options.add_argument("--no-sandbox")
driver = webdriver.Edge(options=options)
```

**CSharp**

```csharp
catch (IOException)
{
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.edge
options.args << '--no-sandbox'
@driver = Selenium::WebDriver.for :edge, options: options
```

**JavaScript**

```javascript
let options = new edge.Options().addArguments('--no-sandbox');
driver = new Builder()
  .forBrowser(Browser.EDGE)
  .setEdgeOptions(options)
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 参数

`args` 参数用于列出启动浏览器时使用的命令行开关.
有两个很好的资源可用于研究这些参数:
* [Chrome Flags for Tooling](https://github.com/GoogleChrome/chrome-launcher/blob/main/docs/chrome-flags-for-tools.md)
* [List of Chromium Command Line Switches](https://peter.sh/experiments/chromium-command-line-switches/)

常用参数包括 `--start-maximized` 、 `--headless=new` 和 `--user-data-dir=...`

为options添加参数:

**Java**

```java
public void arguments() {
```

**Python**

```python

```

**CSharp**

```csharp
driver = new EdgeDriver(options);
```

**Ruby**

```ruby
options.args << '--start-maximized'
```

**JavaScript**

```javascript
.setEdgeOptions(getDefaultEdgeOptions().addArguments('--headless=new'))
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 在指定位置启动浏览器

`binary` 参数包含要使用的浏览器备用位置的路径.
使用此参数, 您可以使用 chromedriver 驱动各种基于 Chromium 的浏览器.

在options中添加浏览器位置:

**Java**

```java
public void setBrowserLocation() {
```

**Python**

```python

```

**CSharp**

```csharp
driver = new EdgeDriver(options);
```

**Ruby**

```ruby
options.binary = edge_location
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 添加扩展

`extensions`参数接受 crx 文件.
至于已解压的目录、中提到, 
请使用[本文](https://chromedriver.chromium.org/extensions)中提及的 `load-extension`.

在options中添加扩展:

**Java**

```java
Path path = Paths.get("src/test/resources/extensions/webextensions-selenium-example.crx");
```

**Python**

```python

```

**CSharp**

```csharp

```

**Ruby**

```ruby
options.add_extension(extension_file_path)
```

**JavaScript**

```javascript
.setEdgeOptions(getDefaultEdgeOptions().addExtensions(['./test/resources/extensions/webextensions-selenium-example.crx']))
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 保持浏览器打开

将 `detach` 参数设置为 true后, 
只要不向driver发送退出命令, 
就可以在进程结束后保持浏览器打开.

**Java**

**Note**: This is already the default behavior in Java.

**Python**

```python

```

**CSharp**

**Note**: This is already the default behavior in .NET.

**Ruby**

```ruby
options.detach = true
```

**JavaScript**

```javascript
.setEdgeOptions(getDefaultEdgeOptions().detachDriver(true))
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 排除参数

MSEdgedriver 有几个用于启动浏览器的默认参数.
如果不希望添加这些参数, 可将它们传递到 `excludeSwitches` 中.
一个常见的例子就是重新打开弹出窗口拦截器.
默认参数的完整列表参考
[Chromium Source Code](https://source.chromium.org/chromium/chromium/src/+/main:chrome/test/chromedriver/chrome_launcher.cc)

在options中设置排除参数:

**Java**

```java
public void excludeSwitches() {
```

**Python**

```python

```

**CSharp**

```csharp
Assert.AreEqual("Content injected by webextensions-selenium-example", injected.Text);
```

**Ruby**

```ruby
options.exclude_switches << 'disable-popup-blocking'
```

**JavaScript**

```javascript
.setEdgeOptions(getDefaultEdgeOptions().excludeSwitches('enable-automation'))
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 服务

创建默认服务对象, 设置驱动程序位置和端口的示例可以参考
[Driver服务](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/service/index.md) 页面.

### 日志输出

获取驱动程序日志有助于调试问题。
服务类可让您配置日志的输出。
日志输出会被忽略, 除非用户显示指定.

#### 文件输出

更改日志输出以保存到特定文件:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
@Test
```

**注意**: Java同样允许在系统属性中配置文件输出:<br>
Property key: `EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY`<br>
Property value: String representing path to log file

**Python**

```python
def test_log_to_file(log_path):
```

**CSharp**

```csharp
}
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

#### 控制台输出

要更改日志输出, 使其在控制台中显示为标准输出:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
File logLocation = getTempFile("logsToConsole", ".log");
```

**注意**: Java同样允许在系统属性中配置控制台输出:<br>
属性键: `EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY`<br>
属性值: `DriverService.LOG_STDOUT` 或 `DriverService.LOG_STDERR`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
def test_log_to_stdout(capfd):
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

有 6 种可用的日志级别: `ALL`, `DEBUG`, `INFO`, `WARNING`, `SEVERE`, 以及 `OFF`.
请注意,  `--verbose` 等同于 `--log-level=ALL` , 而 `--silent` 等同于 `--log-level=OFF`,
因此, 本例只是一般性地设置日志级别:

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
File logLocation = getTempFile("logsWithLevel", ".log");
System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY, logLocation.getAbsolutePath());
```

**注意**: Java同样允许在系统属性中配置日志级别:<br>
属性键: `EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY`<br>
属性值: String representation of `ChromiumDriverLogLevel` enum

**Python**

```python
def test_log_level(log_path):
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
有 2 项功能只有在记录到文件时才可用:
* 追加日志
* 可读时间戳

要使用它们, 还需要明确指定日志路径和日志级别.
日志输出将由driver而非进程管理, 因此可能会出现细微差别.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
System.setProperty(
    EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY, ChromiumDriverLogLevel.DEBUG.toString());
```

**注意**: Java同样允许在系统属性中配置开闭这些功能:<br>
属性键: `EdgeDriverService.EDGE_DRIVER_APPEND_LOG_PROPERTY` 以及 `EdgeDriverService.EDGE_DRIVER_READABLE_TIMESTAMP`<br>
属性值: `"true"` 或 `"false"`

**Python**

```python
def test_log_features(log_path):
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

Edge 浏览器和 msedgedriver 版本应该匹配, 
如果不匹配, 驱动程序就会出错.
如果禁用构建检查, 则可以强制驱动程序与任何版本的 Edge 一起使用.
请注意, 这是一项不受支持的功能, 而且不会对错误进行调查.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY,
ChromiumDriverLogLevel.WARNING.toString());
```

**注意**: Java同样允许在系统属性中配置禁用构建检查:<br>
属性键:  `EdgeDriverService.EDGE_DRIVER_DISABLE_BUILD_CHECK`<br>
属性值:  `"true"` 或 `"false"`

**Python**

```python
def test_build_checks(log_path):
```

**CSharp**

```csharp

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

## Internet Explorer 兼容模式

微软Edge可以被"Internet Explorer兼容模式"驱动,
该模式使用Internet Explorer驱动类与微软Edge结合使用.
阅读 [Internet Explorer 页面](https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/internet_explorer/index.md) 了解更多详情.

## 特殊功能
某些浏览器实现了其特有的附加功能.

### Cast

您可以使用 Edge 驱动 Chrome Cast 设备, 包括共享标签页

**Java**

```java
EdgeDriver driver = new EdgeDriver(getDefaultEdgeOptions());

List<Map<String, String>> sinks = driver.getCastSinks();
if (!sinks.isEmpty()) {
  String sinkName = sinks.get(0).get("name");
  driver.startTabMirroring(sinkName);
```

**Python**

```python
try:
    sinks = driver.get_sinks()
    if sinks:
        sink_name = sinks[0]['name']
        driver.start_tab_mirroring(sink_name)
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

### 网络状况

您可以模拟各种网络状况.

**Java**

```java
@Test
public void setNetworkConditions() {
  driver = new EdgeDriver(getDefaultEdgeOptions());

  ChromiumNetworkConditions networkConditions = new ChromiumNetworkConditions();
  networkConditions.setOffline(false);
  networkConditions.setLatency(java.time.Duration.ofMillis(20)); // 20 ms of latency
```

**Python**

```python

network_conditions = {
    "offline": False,
    "latency": 20,  # 20 ms of latency
    "download_throughput": 2000 * 1024 / 8,  # 2000 kbps
    "upload_throughput": 2000 * 1024 / 8,    # 2000 kbps
}
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
consoleLogButton.click();
```

**Python**

```python

```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
sleep 1
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 权限

**Java**

```java
EdgeDriver driver = new EdgeDriver(getDefaultEdgeOptions());
```

**Python**

```python

```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
@driver.navigate.to 'https://www.selenium.dev/selenium/web/'
@driver.add_permission('camera', 'denied')
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 开发者工具

有关在 Edge 中使用 DevTools 的更多信息, 请参阅 [Chrome DevTools] 部分.
