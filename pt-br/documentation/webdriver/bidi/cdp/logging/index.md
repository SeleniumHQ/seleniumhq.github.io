# Chrome DevTools Logging Features

> Logging features using CDP.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/bidi/cdp/logging/

---

> <p class="lead">
>    <i class="fas fa-language d-4"></i>
>    Page being translated from
>    English to Japanese. Do you speak Japanese? Help us to translate
>    it by sending us pull requests!
> 

> While Selenium 4 provides direct access to the Chrome DevTools Protocol, these
> methods will eventually be removed when WebDriver BiDi implemented.

## Console Logs

**Java**

```java
((HasLogEvents) driver).onLogEvent(consoleEvent(e -> messages.add(e.getMessages().get(0))));
```

**Python**

```python
async with driver.bidi_connection() as session:
    async with Log(driver, session).add_listener(Console.ALL) as messages:
```

**CSharp**

```csharp
using IJavaScriptEngine monitor = new JavaScriptEngine(driver);
var messages = new List<string>();
monitor.JavaScriptConsoleApiCalled += (_, e) =>
{
    lock (messages) messages.Add(e.MessageContent);
};
await monitor.StartEventMonitoring();
```

**Ruby**

```ruby
driver.on_log_event(:console) { |log| logs << log.args.first }
```

**JavaScript**

_Not available in this language._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## JavaScript Exceptions

**Java**

_Not available in this language._

**Python**

```python
async with driver.bidi_connection() as session:
    async with Log(driver, session).add_js_error_listener() as messages:
```

**CSharp**

```csharp
using IJavaScriptEngine monitor = new JavaScriptEngine(driver);
var messages = new List<string>();
monitor.JavaScriptExceptionThrown += (_, e) =>
{
    messages.Add(e.Message);
};
await monitor.StartEventMonitoring();
```

**Ruby**

```ruby
driver.on_log_event(:exception) { |exception| exceptions << exception }
```

**JavaScript**

_Not available in this language._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._
