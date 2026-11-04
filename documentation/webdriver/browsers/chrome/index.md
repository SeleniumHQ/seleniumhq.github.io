# Chrome specific functionality

> These are capabilities and features specific to Google Chrome browsers.

URL: https://www.selenium.dev/documentation/webdriver/browsers/chrome/
Site index: https://www.selenium.dev/llms.txt

---

By default, Selenium 4 is compatible with Chrome v75 and greater. Note that the version of
the Chrome browser and the version of chromedriver must match the major version.

## Options

Capabilities common to all browsers are described on the [Options page](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md).

Capabilities unique to Chrome and Chromium are documented at Google's page for 
[Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)

Starting a Chrome session with basic defined options looks like this:

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Arguments

The `args` parameter is for a list of command line switches to be used when starting the browser.
There are two excellent resources for investigating these arguments:
* [Chrome Flags for Tooling](https://github.com/GoogleChrome/chrome-launcher/blob/main/docs/chrome-flags-for-tools.md)
* [List of Chromium Command Line Switches](https://peter.sh/experiments/chromium-command-line-switches/)

Commonly used args include `--start-maximized`, `--headless=new` and `--user-data-dir=...`

Add an argument to options:

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Start browser in a specified location

The `binary` parameter takes the path of an alternate location of browser to use. With this parameter you can
use chromedriver to drive various Chromium based browsers.

Add a browser location to options:

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Add extensions

The `extensions` parameter accepts crx files. As for unpacked directories,
please use the `load-extension` argument instead, as mentioned in
[this post](https://chromedriver.chromium.org/extensions).

Add an extension to options:

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Keeping browser open

Setting the `detach` parameter to true will keep the browser open after the process has ended,
so long as the quit command is not sent to the driver.

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Excluding arguments

Chromedriver has several default arguments it uses to start the browser.
If you do not want those arguments added, pass them into `excludeSwitches`.
A common example is to turn the popup blocker back on. A full list of default arguments
can be parsed from the 
[Chromium Source Code](https://source.chromium.org/chromium/chromium/src/+/main:chrome/test/chromedriver/chrome_launcher.cc)

Set excluded arguments on options:

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Service

Examples for creating a default Service object, and for setting driver location and port 
can be found on the [Driver Service](https://www.selenium.dev/documentation/webdriver/drivers/service/index.md) page.

### Log output

Getting driver logs can be helpful for debugging issues. The Service class lets you
direct where the logs will go. Logging output is ignored unless the user directs it somewhere.

#### File output

To change the logging output to save to a specific file:

**Java**

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withLogFile(logLocation).build();
```

**Note**: Java also allows setting file output by System Property:<br>
Property key: `ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY`<br>
Property value: String representing path to log file

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### Console output

To change the logging output to display in the console as STDOUT:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withLogOutput(System.out).build();
```

**Note**: Java also allows setting console output by System Property;<br>
Property key: `ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY`<br>
Property value: `DriverService.LOG_STDOUT` or `DriverService.LOG_STDERR`

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Log level
There are 6 available log levels: `ALL`, `DEBUG`, `INFO`, `WARNING`, `SEVERE`, and `OFF`.
Note that `--verbose` is equivalent to `--log-level=ALL` and `--silent` is equivalent to `--log-level=OFF`,
so this example is just setting the log level generically:

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withLogLevel(ChromiumDriverLogLevel.DEBUG).build();
```

**Note**: Java also allows setting log level by System Property:<br>
Property key: `ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY`<br>
Property value: String representation of `ChromiumDriverLogLevel` enum

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Log file features
There are 2 features that are only available when logging to a file:
* append log
* readable timestamps

To use them, you need to also explicitly specify the log path and log level.
The log output will be managed by the driver, not the process, so minor differences may be seen.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withAppendLog(true).withReadableTimestamp(true).build();
```

**Note**: Java also allows toggling these features by System Property:<br>
Property keys: `ChromeDriverService.CHROME_DRIVER_APPEND_LOG_PROPERTY` and `ChromeDriverService.CHROME_DRIVER_READABLE_TIMESTAMP`<br>
Property value: `"true"` or `"false"`

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Disabling build check

Chromedriver and Chrome browser versions should match, and if they don't the driver will error.
If you disable the build check, you can force the driver to be used with any version of Chrome.
Note that this is an unsupported feature, and bugs will not be investigated.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withBuildCheckDisabled(true).build();
```

**Note**: Java also allows disabling build checks by System Property:<br>
Property key: `ChromeDriverService.CHROME_DRIVER_DISABLE_BUILD_CHECK`<br>
Property value: `"true"` or `"false"`

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Special Features
Some browsers have implemented additional features that are unique to them.

### Casting

You can drive Chrome Cast devices, including sharing tabs

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
sinks = @driver.cast_sinks
unless sinks.empty?
  device_name = sinks.first['name']
  @driver.start_cast_tab_mirroring(device_name)
  expect { @driver.stop_casting(device_name) }.not_to raise_exception
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Network conditions

You can simulate various network conditions.

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
@driver.network_conditions = {offline: false, latency: 100, throughput: 200}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Logs

**Java**

```java
LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
```

**Python**

```python
logs = driver.get_log("browser")
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
logs = @driver.logs.get(:browser)
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Permissions

**Java**

```java
driver.setPermission("camera", "denied");
```

**Python**

```python
driver.set_permissions('camera', 'denied')
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
@driver.add_permission('camera', 'denied')
@driver.add_permissions('clipboard-read' => 'denied', 'clipboard-write' => 'prompt')
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### DevTools

See the [Chrome DevTools](https://www.selenium.dev/documentation/webdriver/bidi/cdp/index.md) section for more information about using Chrome DevTools
