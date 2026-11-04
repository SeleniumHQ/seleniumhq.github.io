# Safari specific functionality

> These are capabilities and features specific to Apple Safari browsers.

URL: https://www.selenium.dev/documentation/webdriver/browsers/safari/
Site index: https://www.selenium.dev/llms.txt

---

Unlike Chromium and Firefox drivers, the safaridriver is installed with the Operating System.
To enable automation on Safari, run the following command from the terminal:

```shell
safaridriver --enable
```

## Options

Capabilities common to all browsers are described on the [Options page](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md).

Capabilities unique to Safari can be found at Apple's page [About WebDriver for Safari](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari#2957227)

Starting a Safari session with basic defined options looks like this:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
SafariOptions options = new SafariOptions();
driver = new SafariDriver(options);
```

**Python**

```python
options = webdriver.SafariOptions()
driver = webdriver.Safari(options=options)
```

**CSharp**

```csharp
var options = new SafariOptions();
driver = new SafariDriver(options);
```

**Ruby**

```ruby
it 'basic options' do
  options = Selenium::WebDriver::Options.safari
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.SAFARI)
  .setSafariOptions(options)
  .build();
```

**Kotlin**

```kotlin
  val options = SafariOptions()
  val driver = SafariDriver(options)
```

### Mobile
Those looking to automate Safari on iOS should look to the [Appium project](//appium.io/).

## Service

Service settings common to all browsers are described on the [Service page](https://www.selenium.dev/documentation/webdriver/drivers/service/index.md).

### Logging

Unlike other browsers, Safari doesn't let you choose where logs are output, or change levels. The one option
available is to turn logs off or on. If logs are toggled on, they can be found at:`~/Library/Logs/com.apple.WebDriver/`.

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
.withLogging(true)
```

**Note**: Java also allows setting console output by System Property;<br>
Property key: `SafariDriverService.SAFARI_DRIVER_LOGGING`<br>
Property value: `"true"` or `"false"`

**Python**

_Requires [Selenium v4.26](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.26.0) or later._

```python
service = webdriver.SafariService(enable_logging=True)
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby

```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Safari Technology Preview

Apple provides a development version of their browser — [Safari Technology Preview](https://developer.apple.com/safari/technology-preview/)

**Java**

```java
options.setUseTechnologyPreview(true);
driver = new SafariDriver(options);
```

**Python**

```python
options = webdriver.SafariOptions()
options.use_technology_preview = True
service = webdriver.SafariService(
    executable_path='/Applications/Safari Technology Preview.app/Contents/MacOS/safaridriver'
)
driver = webdriver.Safari(options=options, service=service)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
                                                'have no support for Safari Technology Preview' do
it 'sets the technology preview' do
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._
