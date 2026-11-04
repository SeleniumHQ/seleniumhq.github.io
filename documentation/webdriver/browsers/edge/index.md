# Edge specific functionality

> These are capabilities and features specific to Microsoft Edge browsers.

URL: https://www.selenium.dev/documentation/webdriver/browsers/edge/
Site index: https://www.selenium.dev/llms.txt

---

Microsoft Edge is implemented with Chromium, with the earliest supported version of v79. Similar to Chrome,
the major version number of edgedriver must match the major version of the Edge browser.

## Options

Capabilities common to all browsers are described on the [Options page](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md).

Capabilities unique to Chromium are documented at Google's page for
[Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)

Starting an Edge session with basic defined options looks like this:

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Start browser in a specified location

The `binary` parameter takes the path of an alternate location of browser to use. With this parameter you can
use chromedriver to drive various Chromium based browsers.

Add a browser location to options:

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Add extensions

The `extensions` parameter accepts crx files. As for unpacked directories,
please use the `load-extension` argument instead, as mentioned in
[this post](https://chromedriver.chromium.org/extensions).

Add an extension to options:

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Keeping browser open

Setting the `detach` parameter to true will keep the browser open after the process has ended,
so long as the quit command is not sent to the driver.

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Excluding arguments

MSEdgedriver has several default arguments it uses to start the browser.
If you do not want those arguments added, pass them into `excludeSwitches`.
A common example is to turn the popup blocker back on. A full list of default arguments
can be parsed from the
[Chromium Source Code](https://source.chromium.org/chromium/chromium/src/+/main:chrome/test/chromedriver/chrome_launcher.cc)

Set excluded arguments on options:

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

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
@Test
```

**Note**: Java also allows setting file output by System Property:<br>
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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### Console output

To change the logging output to display in the console as STDOUT:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
File logLocation = getTempFile("logsToConsole", ".log");
```

**Note**: Java also allows setting console output by System Property;<br>
Property key: `EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY`<br>
Property value: `DriverService.LOG_STDOUT` or `DriverService.LOG_STDERR`

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
File logLocation = getTempFile("logsWithLevel", ".log");
System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY, logLocation.getAbsolutePath());
```

**Note**: Java also allows setting log level by System Property:<br>
Property key: `EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY`<br>
Property value: String representation of `ChromiumDriverLogLevel` enum

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
System.setProperty(
    EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY, ChromiumDriverLogLevel.DEBUG.toString());
```

**Note**: Java also allows toggling these features by System Property:<br>
Property keys: `EdgeDriverService.EDGE_DRIVER_APPEND_LOG_PROPERTY` and `EdgeDriverService.EDGE_DRIVER_READABLE_TIMESTAMP`<br>
Property value: `"true"` or `"false"`

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Disabling build check

Edge browser and msedgedriver versions should match, and if they don't the driver will error.
If you disable the build check, you can force the driver to be used with any version of Edge.
Note that this is an unsupported feature, and bugs will not be investigated.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY,
ChromiumDriverLogLevel.WARNING.toString());
```

**Note**: Java also allows disabling build checks by System Property:<br>
Property key: `EdgeDriverService.EDGE_DRIVER_DISABLE_BUILD_CHECK`<br>
Property value: `"true"` or `"false"`

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Internet Explorer Mode

Microsoft Edge can be driven in "Internet Explorer Compatibility Mode", which uses
the Internet Explorer Driver classes in conjunction with Microsoft Edge.
Read the [Internet Explorer page](https://www.selenium.dev/documentation/webdriver/browsers/internet_explorer/index.md) for more details.

## Special Features
Some browsers have implemented additional features that are unique to them.

### Casting

You can drive Chrome Cast devices with Edge, including sharing tabs

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
consoleLogButton.click();
```

**Python**

```python

```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
sleep 1
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Permissions

**Java**

```java
EdgeDriver driver = new EdgeDriver(getDefaultEdgeOptions());
```

**Python**

```python

```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
@driver.navigate.to 'https://www.selenium.dev/selenium/web/'
@driver.add_permission('camera', 'denied')
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### DevTools

See the [Chrome DevTools](https://www.selenium.dev/documentation/webdriver/bidi/cdp/index.md) section for more information about using DevTools in Edge
