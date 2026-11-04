# Logging Selenium commands

> Getting information about Selenium execution.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/troubleshooting/logging/

---

Turning on logging is a valuable way to get extra information that might help you determine
why you might be having a problem.

## Getting a logger

**Java**

Java logs are typically created per class. You can work with the default logger to
work with all loggers. To filter out specific classes, see [Filtering](/pt-br/documentation/webdriver/troubleshooting/logging/index.md#logger-filtering)

Get the root logger:

```java
public void logging() throws IOException {
```

Java Logging is not exactly straightforward, and if you are just looking for an easy way
to look at the important Selenium logs,
take a look at the [Selenium Logger project](https://github.com/titusfortner/selenium-logger#selenium-logger)

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
  > Check our [contribution guidelines](https://www.selenium.dev/pt-br/documentation/about/contributing/index.md) if you'd like to help.

## Logger level
Logger level helps to filter out logs based on their severity.

**Java**

Java has 7 logger levels: `SEVERE`, `WARNING`, `INFO`, `CONFIG`, `FINE`, `FINER`, and `FINEST`.
The default is `INFO`.

You have to change both the level of the logger and the level of the handlers on the root logger:

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
  > Check our [contribution guidelines](https://www.selenium.dev/pt-br/documentation/about/contributing/index.md) if you'd like to help.

### Actionable items

Things are logged as warnings if they are something the user needs to take action on. This is often used
for deprecations. For various reasons, Selenium project does not follow standard Semantic Versioning practices.
Our policy is to mark things as deprecated for 3 releases and then remove them, so deprecations
may be logged as warnings.

**Java**

Java logs actionable content at logger level `WARN`

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

Because these items can get annoying, we’ve provided an easy way to turn them off, see [filtering section](/pt-br/documentation/webdriver/troubleshooting/logging/index.md#logger-filtering) below.

**JavaScript**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/pt-br/documentation/about/contributing/index.md) if you'd like to help.

**Kotlin**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/pt-br/documentation/about/contributing/index.md) if you'd like to help.

### Useful information

This is the default level where Selenium logs things that users should be aware of but do not need to take actions on.
This might reference a new method or direct users to more information about something

**Java**

Java logs useful information at logger level `INFO`

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
  > Check our [contribution guidelines](https://www.selenium.dev/pt-br/documentation/about/contributing/index.md) if you'd like to help.

### Debugging Details

The debug log level is used for information that may be needed for diagnosing issues and troubleshooting problems.

**Java**

Java logs most debug content at logger level `FINE`

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
  > Check our [contribution guidelines](https://www.selenium.dev/pt-br/documentation/about/contributing/index.md) if you'd like to help.

## Logger output

Logs can be displayed in the console or stored in a file. Different languages have different defaults.

**Java**

By default all logs are sent to `System.err`. To direct output to a file, you need to add a handler:

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
  > Check our [contribution guidelines](https://www.selenium.dev/pt-br/documentation/about/contributing/index.md) if you'd like to help.

## Logger filtering

**Java**

Java logging is managed on a per class level, so
instead of using the root logger (`Logger.getLogger("")`), set the level you want to use on a per-class
basis:

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
  > Check our [contribution guidelines](https://www.selenium.dev/pt-br/documentation/about/contributing/index.md) if you'd like to help.

**Kotlin**

> **Note:** This section needs additional and/or updated content.
  >
  > Check our [contribution guidelines](https://www.selenium.dev/pt-br/documentation/about/contributing/index.md) if you'd like to help.
