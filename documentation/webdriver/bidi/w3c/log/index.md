# Log

URL: https://www.selenium.dev/documentation/webdriver/bidi/w3c/log/
Site index: https://www.selenium.dev/llms.txt

---

This section contains the APIs related to logging.
## Console logs
Listen to the `console.log` events and register callbacks to process the event.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java

driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
driver.findElement(By.id("consoleLog")).click();

ConsoleLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

Assertions.assertEquals("Hello, world!", logEntry.getText());
```

**Python**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

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

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

```javascript
const inspector = await LogInspector(driver)
await inspector.onConsoleEntry(function (log) {
  logEntry = log
})

await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
await driver.findElement({id: 'consoleLog'}).click()

assert.equal(logEntry.text, 'Hello, world!')
assert.equal(logEntry.realm, null)
assert.equal(logEntry.type, 'console')
assert.equal(logEntry.level, 'info')
assert.equal(logEntry.method, 'log')
assert.equal(logEntry.stackTrace.callFrames[0].functionName, 'helloWorld')
assert.equal(logEntry.args.length, 1)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## JavaScript exceptions
Listen to the JS Exceptions
and register callbacks to process the exception details.

**Java**

```java
driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
driver.findElement(By.id("jsException")).click();

JavascriptLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

Assertions.assertEquals("Error: Not working", logEntry.getText());
```

**Python**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```python
@pytest.mark.driver_type("bidi")
def test_add_js_exception_handler(driver):
    driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
    log_entries = []

    driver.script.add_javascript_error_handler(log_entries.append)

    driver.find_element(By.ID, "jsException").click()
    WebDriverWait(driver, 5).until(lambda _: log_entries)
    assert log_entries[0].text == "Error: Not working"
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

```javascript
const inspector = await LogInspector(driver)
await inspector.onJavascriptException(function (log) {
  logEntry = log
})

await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
await driver.findElement({id: 'jsException'}).click()

assert.equal(logEntry.text, 'Error: Not working')
assert.equal(logEntry.type, 'javascript')
assert.equal(logEntry.level, 'error')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Listen to JS Logs
Listen to all JS logs at all levels and register callbacks to process the log.

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
driver.findElement(By.id("jsException")).click();

JavascriptLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

Assertions.assertEquals("Error: Not working", logEntry.getText());
```

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._
