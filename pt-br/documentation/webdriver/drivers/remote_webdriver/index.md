# WebDriver Remoto

URL: https://www.selenium.dev/pt-br/documentation/webdriver/drivers/remote_webdriver/

---

> <p class="lead">
>    <i class="fas fa-language d-4"></i> 
>    Page being translated from 
>    English to Portuguese. Do you speak Portuguese? Help us to translate
>    it by sending us pull requests!
> 

Selenium lets you automate browsers on remote computers if
there is a [Selenium Grid](https://www.selenium.dev/pt-br/documentation/grid/index.md) running on them. The computer that
executes the code is referred to as the client computer, and the computer with the browser and driver is 
referred to as the remote computer or sometimes as an end-node.
To direct Selenium tests to the remote computer, you need to use a Remote WebDriver class
and pass the URL including the port of the grid on that machine. Please see the grid documentation
for all the various ways the grid can be configured.

## Basic Example

The driver needs to know where to send commands to and which browser to start on the Remote computer. So an address
and an options instance are both required.

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Uploads

[Uploading a file]() is more complicated for Remote WebDriver sessions because the file you want to 
upload is likely on the computer executing the code, but the driver on the
remote computer is looking for the provided path on its local file system.
The solution is to use a Local File Detector. When one is set, Selenium will bundle
the file, and send it to the remote machine, so the driver can see the reference to it.
Some bindings include a basic local file detector by default, and all of them allow 
for a custom file detector.

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Downloads

Chrome, Edge and Firefox each allow you to set the location of the download directory.
When you do this on a remote computer, though, the location is on the remote computer's local file system. 
Selenium allows you to enable downloads to get these files onto the client computer.

### Enable Downloads in the Grid

Regardless of the client, when starting the grid in node or standalone mode, 
you must add the flag: 
```
--enable-managed-downloads true
``` 

### Enable Downloads in the Client

The grid uses the `se:downloadsEnabled` capability to toggle whether to be responsible for managing the browser location.
Each of the bindings have a method in the options class to set this.

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### List Downloadable Files

Be aware that Selenium is not waiting for files to finish downloading, 
so the list is an immediate snapshot of what file names are currently in the directory for the given session.

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Download a File

Selenium looks for the name of the provided file in the list and downloads it to the provided target directory.

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Delete Downloaded Files

By default, the download directory is deleted at the end of the applicable session,
but you can also delete all files during the session.

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Browser specific functionalities

Each [browser](https://www.selenium.dev/pt-br/documentation/webdriver/browsers/index.md) has implemented special functionality that is available only to that browser.
Each of the Selenium bindings has implemented a different way to use those features in a Remote Session

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

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Tracing client requests

This feature is only available for Java client binding (Beta onwards). The Remote WebDriver client sends requests to the Selenium Grid server, which passes them to the WebDriver. Tracing should be enabled at the server and client-side to trace the HTTP requests end-to-end. Both ends should have a trace exporter setup pointing to the visualization framework.
By default, tracing is enabled for both client and server.
To set up the visualization framework Jaeger UI and Selenium Grid 4, please refer to [Tracing Setup](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) for the desired version.

For client-side setup, follow the steps below.

### Add the required dependencies

Installation of external libraries for tracing exporter can be done using Maven.
Add the _opentelemetry-exporter-jaeger_ and _grpc-netty_ dependency in your project pom.xml:

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

### Add/pass the required system properties while running the client

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

Please refer to [Tracing Setup](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) for more information on external dependencies versions required for the desired Selenium version.

More information can be found at:

* OpenTelemetry: https://opentelemetry.io
* Configuring OpenTelemetry:
  https://github.com/open-telemetry/opentelemetry-java/tree/main/sdk-extensions/autoconfigure
* Jaeger: https://www.jaegertracing.io
* [Selenium Grid Observability](https://www.selenium.dev/pt-br/documentation/grid/advanced_features/observability/index.md)
