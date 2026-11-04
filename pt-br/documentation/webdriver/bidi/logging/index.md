# WebDriver BiDi Logging Features

> These features are related to logging. Because "logging" can refer to so many different things, these methods are made available via a "script" namespace.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/bidi/logging/

---

Remember that to use WebDriver BiDi, you must enable it in Options. 
For more details, see [Enabling BiDi](https://www.selenium.dev/pt-br/documentation/webdriver/bidi/index.md)

## Console Message Handlers

Record or take actions on `console.log` events.

### Add Handler

**Java**

```java
String id = ((RemoteWebDriver) driver).script().addConsoleMessageHandler(future::complete);
```

**Python**

```python
driver.script.add_console_message_handler(log_entries.append)
```

**CSharp**

_Not available in this language._

**Ruby**

```ruby
log_entries = []
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._

### Remove Handler

You need to store the ID returned when adding the handler to delete it.

**Java**

```java
String id = ((RemoteWebDriver) driver).script().addConsoleMessageHandler(logs::add);
((RemoteWebDriver) driver).script().removeConsoleMessageHandler(id);
```

**Python**

```python
id = driver.script.add_console_message_handler(log_entries.append)
driver.script.remove_console_message_handler(id)
```

**CSharp**

_Not available in this language._

**Ruby**

```ruby
log_entries = []
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._

## JavaScript Exception Handlers

Record or take actions on JavaScript exception events.

### Add Handler

**Java**

```java
String id = ((RemoteWebDriver) driver).script().addJavaScriptErrorHandler(future::complete);
```

**Python**

```python
driver.script.add_javascript_error_handler(log_entries.append)
```

**CSharp**

_Not available in this language._

**Ruby**

```ruby
log_entries = []
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._

### Remove Handler

You need to store the ID returned when adding the handler to delete it.

**Java**

```java
String id = ((RemoteWebDriver) driver).script().addJavaScriptErrorHandler(logs::add);
((RemoteWebDriver) driver).script().removeJavaScriptErrorHandler(id);
```

**Python**

```python
id = driver.script.add_javascript_error_handler(log_entries.append)
driver.script.remove_javascript_error_handler(id)
```

**CSharp**

_Not available in this language._

**Ruby**

```ruby
log_entries = []
```

**JavaScript**

_Not available in this language._

**Kotlin**

_Not available in this language._
