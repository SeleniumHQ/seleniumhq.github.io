# Browser Options

> These capabilities are shared by all browsers.

URL: https://www.selenium.dev/documentation/webdriver/drivers/options/
Site index: https://www.selenium.dev/llms.txt

---

In Selenium 3, capabilities were defined in a session by using Desired Capabilities classes.
As of Selenium 4, you must use the browser options classes. 
For remote driver sessions, a browser options instance is required as it determines which browser will be used.

These options are described in the w3c specification for [Capabilities](https://w3c.github.io/webdriver/#capabilities).

Each browser has [custom options](https://www.selenium.dev/documentation/webdriver/browsers/index.md) that may be defined in addition to the ones defined in the specification.

## browserName

Browser name is set by default when using an Options class instance.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String name = chromeOptions.getBrowserName();
```

**Python**

```python
options = get_default_chrome_options()
assert options.capabilities['browserName'] == 'chrome'
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## browserVersion

This capability is optional, this is used to set the available browser version at remote end. 
In recent versions of Selenium, if the version is not found on the system,
it will be automatically downloaded by [Selenium Manager](https://www.selenium.dev/documentation/selenium_manager/index.md)

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String version = "latest";
chromeOptions.setBrowserVersion(version);
```

**Python**

```python
options = get_default_chrome_options()
options.browser_version = 'stable'
assert options.capabilities['browserVersion'] == 'stable'
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options.browser_version = 'latest'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## pageLoadStrategy

Three types of page load strategies are available.

The page load strategy queries the 
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
as described in the table below:

| Strategy | Ready State | Notes |
| -------- | ----------- | ----- |
| normal | complete | Used by default, waits for all resources to download |
| eager | interactive | DOM access is ready, but other resources like images may still be loading |
| none | Any | Does not block WebDriver at all |

The `document.readyState` property of a document describes the loading state of the current document.

When navigating to a new page via URL, by default, WebDriver will hold off on completing a navigation 
method (e.g., driver.navigate().get()) until the document ready state is complete. This _does not 
necessarily mean that the page has finished loading_, especially for sites like Single Page Applications 
that use JavaScript to dynamically load content after the Ready State returns complete. Note also 
that this behavior does not apply to navigation that is a result of clicking an element or submitting a form.

If a page takes a long time to load as a result of downloading assets (e.g., images, css, js) 
that aren't important to the automation, you can change from the default parameter of `normal` to
`eager` or `none` to speed up the session. This value applies to the entire session, so make sure 
that your [waiting strategy](https://www.selenium.dev/documentation/webdriver/waits/index.md) is sufficient to minimize 
flakiness.

### normal (default)

WebDriver waits until the [load](https://developer.mozilla.org/en-US/docs/Web/API/Window/load_event) 
event fire is returned.

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'normal'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Normal;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :normal
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('normal'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
  val chromeOptions = ChromeOptions()
  chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL)
  val driver = ChromeDriver(chromeOptions)
  try {
    driver.get("https://www.google.com")
  }
  finally {
    driver.quit()
  }
}
```

### eager

WebDriver waits until [DOMContentLoaded](https://developer.mozilla.org/en-US/docs/Web/API/Document/DOMContentLoaded_event) 
event fire is returned.

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'eager'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
var chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Eager;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :eager
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('eager'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
  val chromeOptions = ChromeOptions()
  chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER)
  val driver = ChromeDriver(chromeOptions)
  try {
    driver.get("https://www.google.com")
  }
  finally {
    driver.quit()
  }
}
```

### none

WebDriver only waits until the initial page is downloaded.

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'none'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
[TestMethod]
public void SetPageLoadStrategyNone()
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :none
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('none'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
  val chromeOptions = ChromeOptions()
  chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE)
  val driver = ChromeDriver(chromeOptions)
  try {
    driver.get("https://www.google.com")
  }
  finally {
    driver.quit()
  }
}
```

## platformName

This identifies the operating system at the remote-end, 
fetching the `platformName` returns the OS name. 

In cloud-based providers, 
setting `platformName` sets the OS at the remote-end.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String platform = "OS X 10.6";
chromeOptions.setPlatformName(platform);
```

**Python**

```python
options = get_default_chrome_options()
options.platform_name = 'any'
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.firefox
options.platform_name = 'Windows 10'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## acceptInsecureCerts

This capability checks whether an expired (or) 
invalid `TLS Certificate` is used while navigating 
during a session.

If the capability is set to `false`, an 
[insecure certificate error](//developer.mozilla.org/en-US/docs/Web/WebDriver/Errors/InsecureCertificate) 
will be returned as navigation encounters any domain 
certificate problems. If set to `true`, invalid certificate will be 
trusted by the browser.

All self-signed certificates will be trusted by this capability by default. 
Once set, `acceptInsecureCerts` capability will have an 
effect for the entire session.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setAcceptInsecureCerts(true);
```

**Python**

```python
options = get_default_chrome_options()
options.accept_insecure_certs = True
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.accept_insecure_certs = true
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setAcceptInsecureCerts(true))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## timeouts

A WebDriver `session` is imposed with a certain `session timeout`
interval, during which the user can control the behaviour
of executing scripts or retrieving information from the browser.

Each session timeout is configured with
combination of different `timeouts` as described below:

### Script Timeout
Specifies when to interrupt an executing script in
a current browsing context. The default timeout **30,000**
is imposed when a new session is created by WebDriver.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setScriptTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'script': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {script: 40_000}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Page Load Timeout
Specifies the time interval in which web page
needs to be loaded in a current browsing context.
The default timeout **300,000** is imposed when a
new session is created by WebDriver. If page load limits
a given/default time frame, the script will be stopped by
_TimeoutException_.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setPageLoadTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'pageLoad': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {page_load: 400_000}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Implicit Wait Timeout
This specifies the time to wait for the
implicit element location strategy when
locating elements. The default timeout **0**
is imposed when a new session is created by WebDriver.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setImplicitWaitTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'implicit': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {implicit: 1}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## unhandledPromptBehavior

Specifies the state of current session's `user prompt handler`. 
Defaults to **dismiss and notify state**

### User Prompt Handler

This defines what action must take when a 
user prompt encounters at the remote-end. This is defined by 
`unhandledPromptBehavior` capability and has the following states:

* dismiss
* accept
* dismiss and notify
* accept and notify
* ignore

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
```

**Python**

```python
options = get_default_chrome_options()
options.unhandled_prompt_behavior = 'accept'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
var chromeOptions = new ChromeOptions();
chromeOptions.UnhandledPromptBehavior = UnhandledPromptBehavior.DismissAndNotify;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.unhandled_prompt_behavior = :accept
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

NOTE: For `beforeunload` prompts specifically, ChromeDriver needs `ignore` set in addition to
[BiDi](https://www.selenium.dev/documentation/webdriver/bidi/index.md) enabled to keep the prompt open.

## setWindowRect

Indicates whether the remote end supports all of the [resizing and repositioning](https://w3c.github.io/webdriver/#resizing-and-positioning-windows) [commands](https://w3c.github.io/webdriver/#dfn-commands).

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setCapability(CapabilityType.SET_WINDOW_RECT, true);
```

**Python**

```python
options = webdriver.FirefoxOptions()
options.set_window_rect = True # Full support in Firefox
driver = webdriver.Firefox(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.firefox
options.set_window_rect = true
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## strictFileInteractability

This new capability indicates if strict interactability checks 
should be applied to _input type=file_ elements. As strict interactability 
checks are off by default, there is a change in behaviour 
when using _Element Send Keys_ with hidden file upload controls.

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setCapability(CapabilityType.STRICT_FILE_INTERACTABILITY, true);
```

**Python**

```python
options = get_default_chrome_options()
options.strict_file_interactability = True
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.strict_file_interactability = true
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## proxy

A proxy server acts as an intermediary for
requests between a client and a server. In simple terms,
the traffic flows through the proxy server
on its way to the address you requested and back.

A proxy server for automation scripts
with Selenium could be helpful for:

* Capture network traffic
* Mock backend calls made by the website
* Access the required website under complex network
  topologies or strict corporate restrictions/policies.

If you are in a corporate environment, and a
browser fails to connect to a URL, this is most
likely because the environment needs a proxy to be accessed.

Selenium WebDriver provides a way to proxy settings:

**Java**

```java
Proxy proxy = new Proxy();
proxy.setHttpProxy("myproxy.com:8080");
ChromeOptions options = getDefaultChromeOptions();
options.setCapability("proxy", proxy);
```

**Python**

```python
options = get_default_chrome_options()
options.proxy = Proxy({ 'proxyType': ProxyType.MANUAL, 'httpProxy' : 'http.proxy:1234'})
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
ChromeOptions options = new ChromeOptions();
Proxy proxy = new Proxy();
proxy.Kind = ProxyKind.Manual;
proxy.IsAutoDetect = false;
proxy.SslProxy = "myproxy.com:8080";
options.Proxy = proxy;
options.AddArgument("ignore-certificate-errors");
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.proxy = Selenium::WebDriver::Proxy.new(http: 'myproxy.com:8080')
```

**JavaScript**

```javascript
const options = new chrome.Options()
options.setProxy(proxy.manual({ http: 'myproxy.com:8080' }))
```

**Kotlin**

```kotlin
val proxy = Proxy()
proxy.setHttpProxy("myproxy.com:8080")
val options = ChromeOptions()
options.setCapability("proxy", proxy)
```
