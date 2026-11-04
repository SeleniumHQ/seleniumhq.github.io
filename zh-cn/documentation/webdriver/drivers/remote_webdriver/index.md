# 远程WebDriver

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/remote_webdriver/

---

如果远程计算机上正在运行 [Selenium Grid](https://www.selenium.dev/zh-cn/documentation/grid/index.md), 
则 Selenium 允许您自动化远程计算机上的浏览器.
执行代码的计算机称为客户端计算机, 
具有浏览器和驱动程序的计算机称为远程计算机, 
有时也称为终端节点.
要将 Selenium 测试指向到远程计算机, 
您需要使用 Remote WebDriver 类并传递包含该机器上网格端口的URL.
请参阅网格文档, 了解配置网格的全部方式.

## 基本样例

驱动程序需要知道在远程计算机上向何处发送命令, 
以及启动哪个浏览器.
所以地址和选项实例都是必需的.

**Java**

```java
ChromeOptions options = getDefaultChromeOptions();
driver = new RemoteWebDriver(gridUrl, options);
```

**Python**

```python
options = get_default_chrome_options()
driver = webdriver.Remote(command_executor=grid_url, options=options)
```

**CSharp**

```csharp
var options = new ChromeOptions();
driver = new RemoteWebDriver(GridUrl, options);
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
driver = Selenium::WebDriver.for :remote, url: grid_url, options: options
```

**JavaScript**

```javascript
const options = new Chrome.Options().addArguments('--no-sandbox')
driver = new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).usingServer(gridUrl).build()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 上传 {#uploads}

对于远程WebDriver会话, [上传文件](https://www.selenium.dev/zh-cn/documentation/webdriver/elements/file_upload/index.md) 更为复杂, 
因为要上传的文件可能在执行代码的计算机上, 
但远程计算机上的驱动程序正在其本地文件系统上查找提供的路径.
解决方案是使用本地文件检测器.
设置一个后, Selenium将捆绑文件, 
并将其发送到远程计算机, 以便驱动程序可以看到对它的引用.
默认情况下, 某些实现包含一个基本的本地文件检测器, 
并且所有这些实现都允许自定义文件检测器.

**Java**

Java does not include a Local File Detector by default, so you must always add one to do uploads.

```java
((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
fileInput.sendKeys(uploadFile.getAbsolutePath());
driver.findElement(By.id("file-submit")).click();
```

**Python**

Python adds a local file detector to remote webdriver instances by default, but you can also create your own class.

```python
driver.file_detector = LocalFileDetector()
file_input = driver.find_element(By.CSS_SELECTOR, "input[type='file']")
file_input.send_keys(upload_file)
driver.find_element(By.ID, "file-submit").click()
```

**CSharp**

.NET adds a local file detector to remote webdriver instances by default, but you can also create your own class.

```csharp
((RemoteWebDriver)driver).FileDetector = new LocalFileDetector();
IWebElement fileInput = driver.FindElement(By.CssSelector("input[type=file]"));
fileInput.SendKeys(uploadFile);
driver.FindElement(By.Id("file-submit")).Click();
```

**Ruby**

Ruby adds a local file detector to remote webdriver instances by default, but you can also create your own lambda:

```ruby
driver.file_detector = ->((filename, *)) { filename.include?('selenium') && filename }
file_input = driver.find_element(css: 'input[type=file]')
file_input.send_keys(upload_file)
driver.find_element(id: 'file-submit').click
```

**JavaScript**

JavaScript does not include a Local File Detector by default, so you must always add one to do uploads.

```javascript
await driver.setFileDetector(new remote.FileDetector())
await driver.findElement(By.css('input[type=file]')).sendKeys(uploadFile)
await driver.findElement(By.id('file-submit')).click()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 下载

Chrome、Edge和Firefox都允许您设置下载目录的位置.
但是, 当您在远程计算机上执行此操作时, 位置在远程计算机的本地文件系统上.
Selenium允许您启用下载功能, 将这些文件下载到客户端计算机上.

### 在网格中启用下载

当以节点或独立模式启动网格时, 
你必须添加参数: 
```
--enable-managed-downloads true
``` 

### 在客户端中启用下载

网格使用 `se:downloadsEnabled` 功能来切换是否负责管理浏览器位置.
每个实现在options类中都有一个方法来设置.

**Java**

```java
ChromeOptions options = getDefaultChromeOptions();
options.setEnableDownloads(true);
driver = new RemoteWebDriver(gridUrl, options);
```

**Python**

```python
options = get_default_chrome_options()
options.enable_downloads = True
driver = webdriver.Remote(command_executor=server, options=options)
```

**CSharp**

```csharp
ChromeOptions options = new ChromeOptions
{
    EnableDownloads = true
};

driver = new RemoteWebDriver(GridUrl, options);
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome(enable_downloads: true)
driver = Selenium::WebDriver.for :remote, url: grid_url, options: options
```

**JavaScript**

```javascript
const options = new Chrome.Options().addArguments('--no-sandbox').enableDownloads()
driver = new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).usingServer(gridUrl).build()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 列出可下载文件

请注意, Selenium不会等待文件下载完成, 
因此, 该列表是给定会话目录中当前文件名的即时快照.

**Java**

```java
List<String> files = ((HasDownloads) driver).getDownloadableFiles();
```

**Python**

```python
files = driver.get_downloadable_files()
```

**CSharp**

```csharp
IReadOnlyList<string> names = ((RemoteWebDriver)driver).GetDownloadableFiles();
```

**Ruby**

```ruby
files = driver.downloadable_files
```

**JavaScript**

```javascript
const files = await driver.getDownloadableFiles()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 下载文件

Selenium在列表中查找提供的文件的名称, 
并将其下载到提供的目标目录.

**Java**

```java
((HasDownloads) driver).downloadFile(downloadableFile, targetDirectory);
```

**Python**

```python
driver.download_file(downloadable_file, target_directory)
```

**CSharp**

```csharp
((RemoteWebDriver)driver).DownloadFile(downloadableFile, targetDirectory);
```

**Ruby**

```ruby
driver.download_file(downloadable_file, target_directory)
```

**JavaScript**

```javascript
await driver.downloadFile(downloadableFile, targetDirectory)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 删除已下载的文件

默认情况下, 下载目录在可用会话结束时被删除, 
但您也可以在会话期间删除所有文件.

**Java**

```java
((HasDownloads) driver).deleteDownloadableFiles();
```

**Python**

```python
driver.delete_downloadable_files()
```

**CSharp**

```csharp
((RemoteWebDriver)driver).DeleteDownloadableFiles();
```

**Ruby**

```ruby
driver.delete_downloadable_files
```

**JavaScript**

```javascript
await driver.deleteDownloadableFiles()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 浏览器特定功能

每个[浏览器](https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/index.md) 都实现了仅对该浏览器可用的特殊功能.
每种Selenium实现都实现了在远程会话中使用这些功能的不同方式

**Java**

Java requires you to use the Augmenter class, which allows it to automatically pull in implementations for
all interfaces that match the capabilities used with the RemoteWebDriver

```java
driver = new Augmenter().augment(driver);
```

Of interest, using the `RemoteWebDriverBuilder` automatically augments the driver, so it is a great way
to get all the functionality by default:

```java
RemoteWebDriver.builder()
    .address(gridUrl)
    .oneOf(getDefaultChromeOptions())
    .setCapability("ext:options", Map.of("key", "value"))
    .config(ClientConfig.defaultConfig())
    .build();
```

**Python**

_Not available in this language._

**CSharp**

.NET uses a custom command executor for executing commands that are valid for the given browser in the remote driver.

```csharp
var customCommandDriver = driver as ICustomDriverCommandExecutor;
customCommandDriver.RegisterCustomDriverCommands(FirefoxDriver.CustomCommandDefinitions);

var screenshotResponse = customCommandDriver
    .ExecuteCustomDriverCommand(FirefoxDriver.GetFullPageScreenshotCommand, null);
```

**Ruby**

Ruby uses mixins to add applicable browser specific methods to the Remote WebDriver session; 
the methods should always just work for you.

**JavaScript**

JavaScript returns the browser-specific driver class (such as `chrome.Driver`) directly when connecting to a
Remote Session, so no augmenting step is needed to access browser specific functionality.

```javascript
const options = new Chrome.Options().addArguments('--no-sandbox')
driver = new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).usingServer(gridUrl).build()

assert.ok(driver instanceof Chrome.Driver)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## 追踪客户端请求

此功能仅适用于Java客户端绑定 (Beta版以后).
远程WebDriver客户端向Selenium网格服务器发送请求, 
后者将请求传递给WebDriver.
应该在服务器端和客户端启用跟踪, 
以便端到端地追踪HTTP请求.
两端都应该有一个指向可视化框架的追踪导出器设置.
默认情况下, 
对客户端和服务器都启用追踪.
若设置可视化框架Jaeger UI及Selenium Grid 4, 
请参阅所需版本的[追踪设置](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) .

对于客户端设置, 请执行以下步骤.

### 添加所需依赖

可以使用Maven安装追踪导出器的外部库.
在项目pom.xml中添加 _opentelemetry-exporter-jaeger_
和 _grpc-netty_ 的依赖项：

```xml
  <dependency>
      <groupId>io.opentelemetry</groupId>
      <artifactId>opentelemetry-exporter-jaeger</artifactId>
      <version>1.0.0</version>
    </dependency>
    <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-netty</artifactId>
      <version>1.35.0</version>
    </dependency>
``` 

### 在运行客户端时添加/传递所需的系统属性

**Java**

```java
System.setProperty("otel.traces.exporter", "jaeger");
System.setProperty("otel.exporter.jaeger.endpoint", "http://localhost:14250");
System.setProperty("otel.resource.attributes", "service.name=selenium-java-client");

ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");

WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), capabilities);

driver.get("http://www.google.com");

driver.quit();

```

有关所需Selenium版本
及其外部依赖关系版本等更多信息, 
请参阅[追踪设置](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) .

更多信息请访问:

* OpenTelemetry: https://opentelemetry.io
* 配置OpenTelemetry:
    https://github.com/open-telemetry/opentelemetry-java/tree/main/sdk-extensions/autoconfigure
* Jaeger: https://www.jaegertracing.io
* [Selenium Grid 可观测性](https://www.selenium.dev/zh-cn/documentation/grid/advanced_features/observability/index.md)
