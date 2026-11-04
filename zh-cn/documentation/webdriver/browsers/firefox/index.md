# Firefox specific functionality

> These are capabilities and features specific to Mozilla Firefox browsers.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/browsers/firefox/

---

Selenium 4 requires Firefox 78 or greater. It is recommended to always use the latest version of geckodriver.

## Options

Capabilities common to all browsers are described on the [Options page](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/options/index.md).

Capabilities unique to Firefox can be found at Mozilla's page for [firefoxOptions](https://developer.mozilla.org/en-US/docs/Web/WebDriver/Capabilities/firefoxOptions)

Starting a Firefox session with basic defined options looks like this:

**Java**

```java
  driver = new FirefoxDriver(options);
}
```

**Python**

```python
options = webdriver.FirefoxOptions()
driver = webdriver.Firefox(options=options)
```

**CSharp**

```csharp
public void BasicOptions()
{
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.firefox
@driver = Selenium::WebDriver.for :firefox, options: options
```

**JavaScript**

```javascript
driver = new Builder()
  .forBrowser(Browser.FIREFOX)
  .setFirefoxOptions(options)
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

Here are a few common use cases with different capabilities:

### Arguments

The `args` parameter is for a list of Command line switches used when starting the browser.  
Commonly used args include `-headless` and `"-profile", "/path/to/profile"`

Add an argument to options:

**Java**

```java

```

**Python**

```python
options.add_argument("-headless")
```

**CSharp**

```csharp
var options = new FirefoxOptions();
```

**Ruby**

```ruby
options.args << '-headless'
```

**JavaScript**

```javascript
.setFirefoxOptions(options.addArguments('--headless'))
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Start browser in a specified location

The `binary` parameter takes the path of an alternate location of browser to use. For example, with this parameter you can
use geckodriver to drive Firefox Nightly instead of the production version when both are present on your computer.

Add a browser location to options:

**Java**

```java

```

**Python**

```python
options.binary_location = firefox_bin
```

**CSharp**

```csharp
var options = new FirefoxOptions();
```

**Ruby**

```ruby
options.binary = firefox_location
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Profiles

There are several ways to work with Firefox profiles

<div>

**Java**

```java
    profile.setPreference("javascript.enabled", "False");
    options.setProfile(profile);

    driver = new FirefoxDriver(options);
  }
}
```

**Python**

```python
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.firefox.firefox_profile import FirefoxProfile

options = Options()
firefox_profile = FirefoxProfile()
firefox_profile.set_preference("javascript.enabled", False)
options.profile = firefox_profile

driver = webdriver.Firefox(options=options)
```

**CSharp**

```csharp
var options = new FirefoxOptions();
var profile = new FirefoxProfile();
options.Profile = profile;
var driver = new RemoteWebDriver(options);

```

**Ruby**

```ruby
    profile = Selenium::WebDriver::Firefox::Profile.new
    profile['browser.download.dir'] = '/tmp/webdriver-downloads'
    options = Selenium::WebDriver::Firefox::Options.new(profile: profile)
    ```

**JavaScript**

```javascript
const { Builder } = require("selenium-webdriver");
const firefox = require('selenium-webdriver/firefox');

const options = new firefox.Options();
let profile = '/path to custom profile';
options.setProfile(profile);
const driver = new Builder()
    .forBrowser('firefox')
    .setFirefoxOptions(options)
    .build();

```

**Kotlin**

```kotlin
val options = FirefoxOptions()
options.profile = FirefoxProfile()
driver = RemoteWebDriver(options)

```

</div>

**Note**: Whether you create an empty `FirefoxProfile` or point it to the directory of your own profile, Selenium
will create a temporary directory to store either the data of the new profile or a copy of your existing one. Every
time you run your program, a different temporary directory will be created. These directories are not cleaned up
explicitly by Selenium, they should eventually get removed by the operating system. However, if you want to remove
the copy manually (e.g. if your profile is large in size), the path of the copy is exposed by the `FirefoxProfile`
object. Check the language specific implementation to see how to retrieve that location.

If you want to use an existing Firefox profile, you can pass in the path to that profile. Please refer to the official
[Firefox documentation](https://support.mozilla.org/en-US/kb/profiles-where-firefox-stores-user-data#w_how-do-i-find-my-profile)
for instructions on how to find the directory of your profile.

## Service

Service settings common to all browsers are described on the [Service page](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/service/index.md).

### Log output

Getting driver logs can be helpful for debugging various issues. The Service class lets you
direct where the logs will go. Logging output is ignored unless the user directs it somewhere.

#### File output

To change the logging output to save to a specific file:

**Java**

```java
new GeckoDriverService.Builder().withLogFile(logLocation).build();
```

**Note**: Java also allows setting file output by System Property:<br>
Property key: `GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY`<br>
Property value: String representing path to log file

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.FirefoxService(log_output=log_path, service_args=['--log', 'debug'])
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

#### Console output

To change the logging output to display in the console:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
new GeckoDriverService.Builder().withLogOutput(System.out).build();
```

**Note**: Java also allows setting console output by System Property;<br>
Property key: `GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY`<br>
Property value: `DriverService.LOG_STDOUT` or `DriverService.LOG_STDERR`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.FirefoxService(log_output=subprocess.STDOUT)
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

### Log level
There are 7 available log levels: `fatal`, `error`, `warn`, `info`, `config`, `debug`, `trace`.
If logging is specified the level defaults to `info`.

Note that `-v` is equivalent to `-log debug` and `-vv` is equivalent to `log trace`,
so this examples is just for setting the log level generically:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
new GeckoDriverService.Builder().withLogLevel(FirefoxDriverLogLevel.DEBUG).build();
```

**Note**: Java also allows setting log level by System Property:<br>
Property key: `GeckoDriverService.GECKO_DRIVER_LOG_LEVEL_PROPERTY`<br>
Property value: String representation of `FirefoxDriverLogLevel` enum

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.FirefoxService(log_output=log_path, service_args=['--log', 'debug'])
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.args += %w[--log debug]
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Truncated Logs

The driver logs everything that gets sent to it, including string representations of large binaries, so
Firefox truncates lines by default. To turn off truncation:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
new GeckoDriverService.Builder().withTruncatedLogs(false).build();
```

**Note**: Java also allows setting log level by System Property:<br>
Property key: `GeckoDriverService.GECKO_DRIVER_LOG_NO_TRUNCATE`<br>
Property value: `"true"` or `"false"`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.FirefoxService(service_args=['--log-no-truncate', '--log', 'debug'], log_output=log_path)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.args << '--log-no-truncate'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Profile Root

The default directory for profiles is the system temporary directory. If you do not have access to that directory,
or want profiles to be created some place specific, you can change the profile root directory:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
new GeckoDriverService.Builder().withProfileRoot(profileDirectory).build();
```

**Note**: Java also allows setting log level by System Property:<br>
Property key: `GeckoDriverService.GECKO_DRIVER_PROFILE_ROOT`<br>
Property value: String representing path to profile root directory

**Python**

```python
service = webdriver.FirefoxService(service_args=['--profile-root', temp_dir])
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.args += ['--profile-root', root_directory]
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Special Features

### Add-ons

Unlike Chrome, Firefox extensions are not added as part of capabilities as mentioned in
[this issue](https://github.com/mozilla/geckodriver/issues/1476),
they are created after starting the driver.

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/remote_webdriver/index.md) page.

#### Installation

A signed xpi file you would get from [Mozilla Addon page](https://addons.mozilla.org/en-US/firefox/) 

**Java**

```java

```

**Python**

```python
driver.install_addon(addon_path_xpi)
```

**CSharp**

```csharp
string extensionFilePath = Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example.xpi");
```

**Ruby**

```ruby
driver.install_addon(extension_file_path)
```

**JavaScript**

```javascript
let id = await driver.installAddon(xpiPath);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### Uninstallation

Uninstalling an addon requires knowing its id. The id can be obtained from the return value when installing the add-on.

**Java**

```java

```

**Python**

```python
driver.uninstall_addon(id)
```

**CSharp**

_Requires [Selenium v4.5](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.5.0) or later._

```csharp
string extensionId = driver.InstallAddOnFromFile(Path.GetFullPath(extensionFilePath));
```

**Ruby**

```ruby
driver.uninstall_addon(extension_id)
```

**JavaScript**

```javascript
await driver.uninstallAddon(id);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### Unsigned installation

When working with an unfinished or unpublished extension, it will likely not be signed. As such, it can only
be installed as "temporary." This can be done by passing in either a zip file or a directory, here's an 
example with a directory:

**Java**

```java

```

**Python**

```python
driver.install_addon(addon_path_dir, temporary=True)
```

**CSharp**

_Requires [Selenium v4.5](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.5.0) or later._

```csharp
string extensionDirPath = Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example/");
```

**Ruby**

_Requires [Selenium v4.5](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.5.0) or later._

```ruby
driver.install_addon(extension_dir_path, true)
```

**JavaScript**

```javascript
let id = await driver.installAddon(xpiPath, true);
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Full page screenshots

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/remote_webdriver/index.md) page.

**Java**

```java

```

**Python**

```python
driver.save_full_page_screenshot("full_page_screenshot.png")
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
screenshot = driver.save_full_page_screenshot(File.join(dir, 'screenshot.png'))
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Context

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver](https://www.selenium.dev/zh-cn/documentation/webdriver/drivers/remote_webdriver/index.md) page.

**Java**

```java
driver.setContext(FirefoxCommandContext.CHROME);
driver.executeScript("console.log('Inside Chrome context');");
```

**Python**

```python
with driver.context(driver.CONTEXT_CHROME):
    driver.execute_script("console.log('Inside Chrome context');")
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
driver.context = 'content'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Note**: As of Firefox 138, geckodriver needs to be started with the argument `--allow-system-access` to switch the context to `CHROME`.
