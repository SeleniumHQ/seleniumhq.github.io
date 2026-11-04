# Outdated Selenium patterns and their current replacements

> Selenium 2 and 3 idioms that coding agents and old tutorials still produce, with the current form of each.

URL: https://www.selenium.dev/documentation/ai_agents/outdated_patterns/
Site index: https://www.selenium.dev/llms.txt

---

Coding agents and old tutorials reproduce Selenium 2 and Selenium 3 idioms,
because that is what most of the published material describes.
Each section below shows one outdated pattern,
when it stopped being the right way to write Selenium,
why, and the current form.
The outdated form is always marked with an `Outdated — do not use` comment.

This page is the reference behind the rules in
[Using AI coding agents with Selenium](https://www.selenium.dev/documentation/ai_agents/index.md).
Use it to recognise bad output in a review.

## DesiredCapabilities

**Outdated since Selenium 4**, which uses the W3C WebDriver standard by default and removes
support for the legacy protocol.
Java Grid also stopped accepting session requests that use the `desiredCapabilities` keyword in 4.17.

Free-form capability maps predate the W3C standard.
Non-standard capabilities now have to be vendor-prefixed,
and an unprefixed one can stop the session from starting.
The browser Options classes produce a valid payload by construction.
See the [upgrade guide](https://www.selenium.dev/documentation/webdriver/troubleshooting/upgrade_to_selenium_4/index.md)
and [Browser options](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md).

**Java**

```java
// Outdated — do not use
DesiredCapabilities caps = DesiredCapabilities.chrome();
caps.setCapability("pageLoadStrategy", "eager");
WebDriver driver = new ChromeDriver(caps);
```

**Python**

```python
# Outdated — do not use
caps = DesiredCapabilities.CHROME.copy()
caps["pageLoadStrategy"] = "eager"
driver = webdriver.Chrome(desired_capabilities=caps)
```

Current form:

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

## Manual driver downloads and driver managers

**Superseded in Selenium 4.6**, when Selenium Manager began shipping with every Selenium release.
Since 4.11 Selenium Manager can also download the browser.

Setting a path to `chromedriver`, putting a driver on the `PATH` by hand,
or adding a third-party driver manager adds a second, slower source of truth
for which driver version to use.
There is nothing to write:
start the driver and Selenium Manager finds or downloads the matching one.
See [Selenium Manager](https://www.selenium.dev/documentation/selenium_manager/index.md).

**Java**

```java
// Outdated — do not use
WebDriverManager.chromedriver().setup();
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
WebDriver driver = new ChromeDriver();
```

**Python**

```python
# Outdated — do not use
from webdriver_manager.chrome import ChromeDriverManager

driver = webdriver.Chrome(ChromeDriverManager().install())
```

Current form:

**Java**

```java
WebDriver driver = new ChromeDriver();
```

**Python**

```python
driver = webdriver.Chrome()
```

## sleep()

**Never good practice.**
Fixed sleeps were bad advice before Selenium 4 and are bad advice now.

A fixed sleep is either too short, and the test fails,
or too long, and the suite crawls.
Wait for the condition the next line depends on instead.
See [Waiting strategies](https://www.selenium.dev/documentation/webdriver/waits/index.md).

**Java**

```java
// Outdated — do not use
driver.findElement(By.id("adder")).click();
Thread.sleep(5000);
WebElement added = driver.findElement(By.id("box0"));
```

**Python**

```python
# Outdated — do not use
driver.find_element(By.ID, "adder").click()
time.sleep(5)
added = driver.find_element(By.ID, "box0")
```

Current form:

**Java**

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
wait.until(d -> revealed.isDisplayed());
```

**Python**

```python
wait = WebDriverWait(driver, timeout=2)
wait.until(lambda _ : revealed.is_displayed())
```

## Absolute XPath copied from DevTools

**Never good practice.**
An absolute path describes where an element sits in today's markup,
so it breaks on the next front-end change.

Prefer an `id` or `name`, then a CSS selector on a stable attribute.
When an element has no usable locator but is easy to describe by position,
use a relative locator.
See [Locator strategies](https://www.selenium.dev/documentation/webdriver/elements/locators/index.md).

**Java**

```java
// Outdated — do not use
driver.findElement(By.xpath("/html/body/div[2]/div[1]/form/div[3]/input"));
```

**Python**

```python
# Outdated — do not use
driver.find_element(By.XPATH, "/html/body/div[2]/div[1]/form/div[3]/input")
```

Current form, with a stable locator:

**Java**

```java
WebElement element = driver.findElement(By.id("lname"));
```

**Python**

```python
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
```

Current form, with a relative locator:

**Java**

```java
By passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.id("email"));
```

**Python**

```python
password_locator = locate_with(By.TAG_NAME, "input").below({By.ID: "email"})
```

See [Relative locators](https://www.selenium.dev/documentation/webdriver/elements/locators/index.md#relative-locators)
for the full set of relative locator methods.

## find_element_by_* methods

**Superseded in Selenium 4.**
The Python bindings announced the removal of `find_element_by_*` and `find_elements_by_*`
after Selenium 4.2.
The Java `findElementBy*` helpers were removed in Selenium 4.

Every one of them is a special case of `find_element` with a `By` strategy.
See the
[Python locators blog post](https://www.selenium.dev/blog/2022/python-locators-se4/)
and the [upgrade guide](https://www.selenium.dev/documentation/webdriver/troubleshooting/upgrade_to_selenium_4/index.md).

**Java**

```java
// Outdated — do not use
driver.findElementById("lname");
```

**Python**

```python
# Outdated — do not use
driver.find_element_by_id("lname")
```

Current form:

**Java**

```java
WebElement element = driver.findElement(By.id("lname"));
```

**Python**

```python
driver.get("https://www.selenium.dev/selenium/web/locators_tests/locators.html")
```

## executable_path

**Deprecated in Selenium 4** in favour of a `Service` object.

In Python, the `executable_path` argument to the driver constructor is the outdated form.
A path belongs on a `Service`.
Most projects do not need one at all, because Selenium Manager finds the driver;
see [driver management](#manual-driver-downloads-and-driver-managers) above.
See the [upgrade guide](https://www.selenium.dev/documentation/webdriver/troubleshooting/upgrade_to_selenium_4/index.md)
and [Driver service](https://www.selenium.dev/documentation/webdriver/drivers/service/index.md).

```python
# Outdated — do not use
driver = webdriver.Chrome(executable_path="/path/to/chromedriver", options=options)
```

Current form, when a specific driver binary is really needed:

**Python**

```python
service = webdriver.ChromeService(executable_path=chromedriver_bin)

driver = webdriver.Chrome(service=service, options=options)
```

## CDP-only calls

**Temporary by design.**
The [CDP pages](https://www.selenium.dev/documentation/webdriver/bidi/cdp/index.md) describe Selenium's Chrome DevTools Protocol
support as temporary until WebDriver BiDi is implemented.
Firefox CDP support was deprecated in 4.27 and 4.28 and fully removed in 4.29.

The Chrome DevTools Protocol is Chromium-only and has no stable API between browser versions.
[WebDriver BiDi](https://www.selenium.dev/documentation/webdriver/bidi/w3c/index.md) is the W3C standard that replaces it,
and it works across browsers.

**Java**

```java
// Outdated — do not use
DevTools devTools = ((HasDevTools) driver).getDevTools();
devTools.createSession();
devTools.send(Log.enable());
devTools.addListener(Log.entryAdded(), entry -> System.out.println(entry.getText()));
```

**Python**

```python
# Outdated — do not use
driver.execute_cdp_cmd("Log.enable", {})
```

Current form, listening to console logs with BiDi:

**Java**

```java
try (LogInspector logInspector = new LogInspector(driver)) {
    CompletableFuture<ConsoleLogEntry> future = new CompletableFuture<>();
    logInspector.onConsoleEntry(future::complete);

    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
    driver.findElement(By.id("consoleLog")).click();

    ConsoleLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

    Assertions.assertEquals("Hello, world!", logEntry.getText());
```

**Python**

```python
@pytest.mark.driver_type("bidi")
def test_add_console_log_handler(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    log_entries = []

    driver.script.add_console_message_handler(log_entries.append)

    driver.find_element(By.ID, "consoleLog").click()
    WebDriverWait(driver, 5).until(lambda _: log_entries)
    assert log_entries[0].text == "Hello, world!"
```
