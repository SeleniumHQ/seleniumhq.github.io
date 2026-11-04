# Console and logs

> Capturing the browser's console output and JavaScript errors with BiDi, and turning up Selenium's own logging to see what the bindings and the driver are doing.

URL: https://www.selenium.dev/documentation/guides/logging/
Site index: https://www.selenium.dev/llms.txt

---

Something went wrong, and the only evidence is a failed assertion. There are three separate
streams of information that could explain it: what the page wrote to the browser console,
what exception JavaScript threw, and what Selenium itself was sending to and receiving from
the driver. By default you see none of them.

## Recommended approach

**Browser console and JavaScript errors.** With WebDriver BiDi enabled, the log module streams
console entries and uncaught JavaScript exceptions to your test as they happen. Register a
handler before the action you want to observe, then assert on the entries it collects. This
is the cross-browser, standards-based replacement for the old Chromium-only log retrieval,
and it catches errors the page swallowed before a user would have seen them.

**Selenium's own logging.** Each binding logs through its language's standard logging
facility: `java.util.logging` in Java, the `logging` module in Python, Selenium's `Log` class
in .NET, and `Selenium::WebDriver.logger` in Ruby. Raising the level to debug shows every
command sent to the driver and every response, which is usually enough to tell whether the
problem is in your code, the driver or the browser. Logs go to the console by default; add a
file handler to keep them with the rest of your test artifacts.

**Driver logs.** The browser driver (chromedriver, geckodriver, and so on) keeps its own log,
configured through the driver's service class. Reach for it when the browser fails to start or
crashes mid-session.

Collect console messages with BiDi:

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
driver.script.add_console_message_handler(log_entries.append)

driver.find_element(By.ID, "consoleLog").click()
WebDriverWait(driver, 5).until(lambda _: log_entries)
assert log_entries[0].text == "Hello, world!"
```

**JavaScript**

```javascript
const inspector = await LogInspector(driver)
await inspector.onConsoleEntry(function (log) {
  logEntry = log
})

await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
await driver.findElement({id: 'consoleLog'}).click()

assert.equal(logEntry.text, 'Hello, world!')
```

Collect JavaScript exceptions with BiDi:

**Java**

```java
driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
driver.findElement(By.id("jsException")).click();

JavascriptLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

Assertions.assertEquals("Error: Not working", logEntry.getText());
```

**Python**

```python
driver.script.add_javascript_error_handler(log_entries.append)

driver.find_element(By.ID, "jsException").click()
WebDriverWait(driver, 5).until(lambda _: log_entries)
assert log_entries[0].text == "Error: Not working"
```

**JavaScript**

```javascript
const inspector = await LogInspector(driver)
await inspector.onJavascriptException(function (log) {
  logEntry = log
})

await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
await driver.findElement({id: 'jsException'}).click()

assert.equal(logEntry.text, 'Error: Not working')
```

Turn up Selenium's own logging:

**Java**

```java
Logger logger = Logger.getLogger("");
logger.setLevel(Level.FINE);
Arrays.stream(logger.getHandlers()).forEach(handler -> {
    handler.setLevel(Level.FINE);
```

**Python**

```python
logger = logging.getLogger('selenium')

logger.setLevel(logging.DEBUG)
```

**CSharp**

```csharp
Log.SetLevel(LogEventLevel.Trace);
```

**Ruby**

```ruby
logger = Selenium::WebDriver.logger

logger.level = :debug
```

## Common errors

* [JavascriptException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#javascriptexception):
  a script you executed through WebDriver threw. The BiDi log module shows the page's own
  errors, which this exception does not cover.
* [UnsupportedCommandException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#unsupportedcommandexception):
  BiDi was not enabled for the session, so the log module is unavailable.

## Reference

* [BiDi log](https://www.selenium.dev/documentation/webdriver/bidi/w3c/log/index.md): console entries and JavaScript exceptions
* [Logging Selenium commands](https://www.selenium.dev/documentation/webdriver/troubleshooting/logging/index.md): levels, output and filtering per binding
* [Driver service](https://www.selenium.dev/documentation/webdriver/drivers/service/index.md): where the driver's own log goes
* [Enabling BiDi](https://www.selenium.dev/documentation/webdriver/bidi/index.md#enabling-bidi-in-selenium)
