# 记录 Selenium 命令

> 获取Selenium的执行信息.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/troubleshooting/logging/

---

启用日志记录是获取额外信息的宝贵方法, 
这些信息可能有助于您确定
遇到问题的原因.

## 获取一个logger

**Java**

Java 日志通常是按类创建的.
您可以通过默认logger来使用所有loggers.
为了过滤特定类, 请参考 [过滤器](/zh-cn/documentation/webdriver/troubleshooting/logging/index.md#logger-%e8%bf%87%e6%bb%a4)

获取根logger:

```java
public void logging() throws IOException {
```

Java日志并不简单直接,
如果您只是在寻找一种简单的方法
查看重要的Selenium日志,
请参阅 [Selenium Logger 项目](https://github.com/titusfortner/selenium-logger#selenium-logger)

**Python**

Python logs are typically created per module. You can match all submodules by referencing the top
level module. So to work with all loggers in selenium module, you can do this:

```python
logger = logging.getLogger('selenium')
```

You must also create and add a log handler (`StreamHandler`, `FileHandler`, etc).

To save logs to a file, you can do this:

```py
log_path = '/path/to/log'
handler = logging.FileHandler(log_path)
logger.addHandler(handler)
```

To display logs in the console, you can do this:

```py
handler = logging.StreamHandler()
logger.addHandler(handler)
```

**CSharp**

.NET logger is managed with a static class, so all access to logging is managed simply by referencing `Log` from the `OpenQA.Selenium.Internal.Logging` namespace.

**Ruby**

If you want to see as much debugging as possible in all the classes,
you can turn on debugging globally in Ruby by setting `$DEBUG = true`.

For more fine-tuned control, Ruby Selenium created its own Logger class to wrap the default `Logger` class.
This implementation provides some interesting additional features.
Obtain the logger directly from the `#logger`class method on the `Selenium::WebDriver` module:

```ruby
logger = Selenium::WebDriver.logger
```

**JavaScript**

```javascript
const logging = require('selenium-webdriver/lib/logging')
logger = logging.getLogger('webdriver')
```

**Kotlin**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/zh-cn/documentation/about/contributing/index.md) if you'd like to help.

## 日志级别
Logger级别有助于根据日志的严重性过滤日志.

**Java**

Java有七种logger级别: `SEVERE`, `WARNING`, `INFO`, `CONFIG`, `FINE`, `FINER`, 以及 `FINEST`.
默认是 `INFO`.

您必须更改logger的级别和根logger上的处理程序的级别:

```java
Logger logger = Logger.getLogger("");
logger.setLevel(Level.FINE);
Arrays.stream(logger.getHandlers()).forEach(handler -> {
    handler.setLevel(Level.FINE);
```

**Python**

Python has 6 logger levels: `CRITICAL`, `ERROR`, `WARNING`, `INFO`, `DEBUG`, and `NOTSET`.
The default is `WARNING`

To change the level of the logger:

```python
logger.setLevel(logging.DEBUG)
```

Things get complicated when you use PyTest, though. By default, PyTest hides logging unless the test
fails. You need to set 3 things to get PyTest to display logs on passing tests.

To always output logs with PyTest you need to run with additional arguments.
First, `-s` to prevent PyTest from capturing the console.
Second, `-p no:logging`, which allows you to override the default PyTest logging settings so logs can
be displayed regardless of errors.

So you need to set these flags in your IDE, or run PyTest on command line like:

```bash
pytest -s -p no:logging
```

Finally, since you turned off logging in the arguments above, you now need to add configuration to
turn it back on:

```py
logging.basicConfig(level=logging.WARN)
```

**CSharp**

.NET has 6 logger levels: `Error`, `Warn`, `Info`, `Debug`, `Trace` and `None`. The default level is `Warn`.

To change the level of the logger:

```csharp
{
```

**Ruby**

Ruby logger has 5 logger levels: `:debug`, `:info`, `:warn`, `:error`, `:fatal`.
As of Selenium v4.9.1, The default is `:info`.

To change the level of the logger:

```ruby
logger.level = :debug
```

**JavaScript**

JavaScript has 9 logger levels: `OFF`, `SEVERE`, `WARNING`, `INFO`, `DEBUG`, `FINE`, `FINER`, `FINEST`, `ALL`.
The default is `OFF`.

To change the level of the logger:

```javascript
logger.setLevel(logging.Level.INFO)
```

**Kotlin**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/zh-cn/documentation/about/contributing/index.md) if you'd like to help.

### Actionable items

对于需要用户后续行动的操作, 会将其记录为警告. 
这经常用于被弃用的内容. 
由于各种原因, Selenium项目不遵循标准的语义版本控制实践. 
我们的政策是将 3 个版本的内容标记为已弃用后, 
再删除它们, 因此弃用可能记录为警告.

**Java**

Java 日志可操作的内容在logger级别 `WARN`

Example:

```text
May 08, 2023 9:23:38 PM dev.selenium.troubleshooting.LoggingTest logging
WARNING: this is a warning
```

**Python**

Python logs actionable content at logger level — `WARNING`
Details about deprecations are logged at this level.

Example:

```text
WARNING  selenium:test_logging.py:23 this is a warning
```

**CSharp**

.NET logs actionable content at logger level `Warn`.

Example:

```text
11:04:40.986 WARN LoggingTest: this is a warning
```

**Ruby**

Ruby logs actionable content at logger level — `:warn`.
Details about deprecations are logged at this level.

For example:

```text
2023-05-08 20:53:13 WARN Selenium [:example_id] this is a warning 
```

Because these items can get annoying, we’ve provided an easy way to turn them off, see [filtering section](/zh-cn/documentation/webdriver/troubleshooting/logging/index.md#logger-filtering) below.

**JavaScript**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/zh-cn/documentation/about/contributing/index.md) if you'd like to help.

**Kotlin**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/zh-cn/documentation/about/contributing/index.md) if you'd like to help.

### Useful information

这是默认级别, Selenium 记录用户应该注意但不需要对其执行操作的内容. 
这可能会引用新方法或将用户定向到有关某些内容的详细信息

**Java**

Java 日志有用的信息在logger 级别 `INFO`

Example:

```text
May 08, 2023 9:23:38 PM dev.selenium.troubleshooting.LoggingTest logging
INFO: this is useful information
```

**Python**

Python logs useful information at logger level — `INFO`

Example:

```text
INFO     selenium:test_logging.py:22 this is useful information
```

**CSharp**

.NET logs useful information at logger level `Info`.

Example:

```text
11:04:40.986 INFO LoggingTest: this is useful information
```

**Ruby**

Ruby logs useful information at logger level — `:info`.

Example:

```text
2023-05-08 20:53:13 INFO Selenium [:example_id] this is useful information 
```

**JavaScript**

Logs useful information at level: `INFO`

**Kotlin**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/zh-cn/documentation/about/contributing/index.md) if you'd like to help.

### Debugging Details

调试日志级别用于诊断问题和解决问题可能需要的信息.

**Java**

Java日志的大多数调试信息在logger 级别 `FINE`

Example:

```text
May 08, 2023 9:23:38 PM dev.selenium.troubleshooting.LoggingTest logging
FINE: this is detailed debug information
```

**Python**

Python logs debugging details at logger level — `DEBUG`

Example:

```text
DEBUG    selenium:test_logging.py:24 this is detailed debug information
```

**CSharp**

.NET logs most debug content at logger level `Debug`.

Example:

```text
11:04:40.986 DEBUG LoggingTest: this is detailed debug information
```

**Ruby**

Ruby only provides one level for debugging, so all details are at logger level — `:debug`.

Example:

```text
2023-05-08 20:53:13 DEBUG Selenium [:example_id] this is detailed debug information 
```

**JavaScript**

Logs debugging details at level: `FINER` and `FINEST`

**Kotlin**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/zh-cn/documentation/about/contributing/index.md) if you'd like to help.

## Logger 输出

日志可以显示在控制台中, 也可以存储在文件中. 
不同的语言有不同的默认值. 

**Java**

默认情况下, 所有日志都发送到 `System.err`.
要将输出定向到文件, 您需要添加一个处理程序:

```java

Path output = artifactsDir().resolve("selenium.xml");
```

**Python**

By default all logs are sent to `sys.stderr`. To direct output somewhere else, you need to add a
handler with either a `StreamHandler` or a `FileHandler`:

```python
handler = logging.FileHandler(log_path)
logger.addHandler(handler)
```

**CSharp**

By default all logs are sent to `System.Console.Error` output. To direct output somewhere else, you need to add a handler with a `FileLogHandler`:

```csharp

```

**Ruby**

By default, logs are sent to the console in `stdout`.<br>
To store the logs in a file:

```ruby
logger.output = file_name
```

**JavaScript**

JavaScript does not currently support sending output to a file.

To send logs to console output:

```javascript
logging.installConsoleHandler()
```

**Kotlin**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/zh-cn/documentation/about/contributing/index.md) if you'd like to help.

## Logger 过滤

**Java**

Java 日志记录是按类级别管理的,
因此不要使用根logger  (`Logger.getLogger("")`),
而是在每个类的基础上设置要使用的级别:

```java
logger.addHandler(handler);
```

**Python**

Because logging is managed by module, instead of working with just "selenium", you can specify
different levels for different modules:

```python
logging.getLogger('selenium.webdriver.remote').setLevel(logging.WARN)
logging.getLogger('selenium.webdriver.common').setLevel(logging.DEBUG)
```

**CSharp**

.NET logging is managed on a per class level, set the level you want to use on a per-class basis:

```csharp

Log.SetLevel(typeof(RemoteWebDriver), LogEventLevel.Debug);
```

**Ruby**

Ruby’s logger allows you to opt in (“allow”) or opt out (“ignore”) of log messages based on their IDs.
Everything that Selenium logs includes an ID. You can also turn on or off all deprecation notices by
using `:deprecations`.

These methods accept one or more symbols or an array of symbols:

```ruby
logger.ignore(:jwp_caps, :logger_info)
```

or

```ruby
logger.allow(%i[selenium_manager example_id])
```

**JavaScript**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/zh-cn/documentation/about/contributing/index.md) if you'd like to help.

**Kotlin**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/zh-cn/documentation/about/contributing/index.md) if you'd like to help.
