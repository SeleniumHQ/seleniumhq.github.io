# WebDriver BiDi 日志功能

> 这些功能与日志记录有关。 由于"logging"可以指代许多不同的事物, 因此这些方法通过"script"命名空间提供.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/logging/

---

请记住, 要使用 WebDriver BiDi,
您必须在选项中启用它. 
更多详情, 请参阅 [启用 BiDi](https://www.selenium.dev/zh-cn/documentation/webdriver/bidi/index.md) .

## 控制台消息处理程序

记录或对 `console.log` 事件采取行动.

### 添加处理程序

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

### 删除处理程序

您需要存储添加处理程序时返回的 ID 以便将其删除.

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

## JavaScript 异常处理程序

记录或对 JavaScript 异常事件采取行动.

### 添加处理程序

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

### 删除处理程序

您需要存储添加处理程序时返回的 ID 以便将其删除.

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
