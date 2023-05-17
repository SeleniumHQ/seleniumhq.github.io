---
title: "Logging Selenium commands"
linkTitle: "Logging"
weight: 4
description: >
  Getting information about Selenium execution.
---

Turning on logging is a valuable way to get extra information that might help you determine
why you might be having a problem.

## Getting a logger

{{< tabpane langEqualsHeader=true text=true >}}
  {{% tab header="Java" %}}
Java logs are typically created per class. You can work with the default logger to 
work with all loggers. To filter out specific classes, see [Filtering](#logger-filtering)

Get the root logger:
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/troubleshooting/LoggingTest.java#L20" >}}

Java Logging is not exactly straightforward, and if you are just looking for an easy way 
to look at the important Selenium logs, 
take a look at the [Selenium Logger project](https://github.com/titusfortner/selenium-logger#selenium-logger)
  {{% /tab %}}
  {{% tab header="Python" %}}
Python logs are typically created per module. You can match all submodules by referencing the top
level module. So to work with all loggers in selenium module, you can do this:
{{< gh-codeblock path="/examples/python/tests/troubleshooting/test_logging.py#L8" >}}
  {{% /tab %}}
  {{% tab header="CSharp" %}}
```text
.NET does not currently have a Logging implementation
```
  {{% /tab %}}
  {{% tab header="Ruby" %}}
If you want to see as much debugging as possible in all the classes,
  you can turn on debugging globally in Ruby by setting `$DEBUG = true`.

For more fine-tuned control, Ruby Selenium created its own Logger class to wrap the default `Logger` class.
This implementation provides some interesting additional features. 
Obtain the logger directly from the `#logger`class method on the `Selenium::WebDriver` module:

{{< gh-codeblock path="/examples/ruby/spec/troubleshooting/logging_spec.rb#L11" >}}
  {{% /tab %}}
  {{% tab header="JavaScript" %}}
```javascript
const logging = require('selenium-webdriver/lib/logging')
logger = logging.getLogger('webdriver')
```
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

## Logger level
Logger level helps to filter out logs based on their severity.

{{< tabpane langEqualsHeader=true text=true >}}
  {{% tab header="Java" %}}
  Java has 7 logger levels: `SEVERE`, `WARNING`, `INFO`, `CONFIG`, `FINE`, `FINER`, and `FINEST`.
The default is `INFO`. 

You have to change both the level of the logger and the level of the handlers on the root logger:
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/troubleshooting/LoggingTest.java#L22-L25" >}}
  {{% /tab %}}
  {{% tab header="Python" %}}
  Python has 6 logger levels: `CRITICAL`, `ERROR`, `WARNING`, `INFO`, `DEBUG`, and `NOTSET`. 
  The default is `WARNING`
 
To change the level of the logger:
{{< gh-codeblock path="/examples/python/tests/troubleshooting/test_logging.py#L10" >}}
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
  {{% /tab %}}
  {{% tab header="CSharp" %}}
```text
.NET does not currently have a Logging implementation
```
  {{% /tab %}}
  {{% tab header="Ruby" %}}
  Ruby logger has 5 logger levels: `:debug`, `:info`, `:warn`, `:error`, `:fatal`. 
  As of Selenium v4.9.1, The default is `:info`.  
  
To change the level of the logger:
{{< gh-codeblock path="/examples/ruby/spec/troubleshooting/logging_spec.rb#L13" >}}
  {{% /tab %}}
  {{% tab header="JavaScript" %}}
  JavaScript has 9 logger levels: `OFF`, `SEVERE`, `WARNING`, `INFO`, `DEBUG`, `FINE`, `FINER`, `FINEST`, `ALL`. 
  The default is `OFF`.

  To change the level of the logger:

```javascript
logger.setLevel(logging.Level.INFO)
```  
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

### Actionable items

Things are logged as warnings if they are something the user needs to take action on. This is often used
for deprecations. For various reasons, Selenium project does not follow standard Semantic Versioning practices.
Our policy is to mark things as deprecated for 3 releases and then remove them, so deprecations
may be logged as warnings.

{{< tabpane langEqualsHeader=true text=true >}}
  {{% tab header="Java" %}}
Java logs actionable content at logger level `WARN`

Example:
```text
May 08, 2023 9:23:38 PM dev.selenium.troubleshooting.LoggingTest logging
WARNING: this is a warning
```
  {{% /tab %}}
  {{% tab header="Python" %}}
Python logs actionable content at logger level — `WARNING`
Details about deprecations are logged at this level.

Example:
```text
WARNING  selenium:test_logging.py:23 this is a warning
```
  {{% /tab %}}
  {{% tab header="CSharp" %}}
```text
.NET does not currently have a Logging implementation
```
  {{% /tab %}}
  {{% tab header="Ruby" %}}
Ruby logs actionable content at logger level — `:warn`.
Details about deprecations are logged at this level.

For example:
```text
2023-05-08 20:53:13 WARN Selenium [:example_id] this is a warning 
```
  Because these items can get annoying, we've provided an easy way to turn them off, see [filtering section](#logger-filtering) below.
  {{% /tab %}}
  {{< tab header="JavaScript" >}}
  {{< alert-content />}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

### Useful information

This is the default level where Selenium logs things that users should be aware of but do not need to take actions on.
This might reference a new method or direct users to more information about something

{{< tabpane langEqualsHeader=true text=true >}}
  {{% tab header="Java" %}}
  Java logs useful information at logger level `INFO`

Example:
```text
May 08, 2023 9:23:38 PM dev.selenium.troubleshooting.LoggingTest logging
INFO: this is useful information
```
  {{% /tab %}}
  {{% tab header="Python" %}}
Python logs useful information at logger level — `INFO`

Example:
```text
INFO     selenium:test_logging.py:22 this is useful information
```
  {{% /tab %}}
  {{% tab header="CSharp" %}}
```text
.NET does not currently have a Logging implementation
```
  {{% /tab %}}
  {{% tab header="Ruby" %}}
Ruby logs useful information at logger level — `:info`.

Example:
```text
2023-05-08 20:53:13 INFO Selenium [:example_id] this is useful information 
```

  {{% /tab %}}
  {{% tab header="JavaScript" %}}
  Logs useful information at level: `INFO`
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

### Debugging Details

The debug log level is used for information that may be needed for diagnosing issues and troubleshooting problems.

{{< tabpane langEqualsHeader=true text=true >}}
  {{% tab header="Java" %}}
Java logs most debug content at logger level `FINE`

Example:
```text
May 08, 2023 9:23:38 PM dev.selenium.troubleshooting.LoggingTest logging
FINE: this is detailed debug information
```
  {{% /tab %}}
  {{% tab header="Python" %}}
Python logs debugging details at logger level — `DEBUG`

Example:
```text
DEBUG    selenium:test_logging.py:24 this is detailed debug information
```
  {{% /tab %}}
  {{% tab header="CSharp" %}}
```text
.NET does not currently have a Logging implementation
```
  {{% /tab %}}
  {{% tab header="Ruby" %}}
Ruby only provides one level for debugging, so all details are at logger level — `:debug`.

Example:
```text
2023-05-08 20:53:13 DEBUG Selenium [:example_id] this is detailed debug information 
```
  {{% /tab %}}
  {{% tab header="JavaScript" %}}
  Logs debugging details at level: `FINER` and `FINEST`
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

## Logger output

Logs can be displayed in the console or stored in a file. Different languages have different defaults.

{{< tabpane langEqualsHeader=true text=true >}}
  {{% tab header="Java" %}}
By default all logs are sent to `System.err`. To direct output to a file, you need to add a handler:

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/troubleshooting/LoggingTest.java#L27-L28" >}}
  {{% /tab %}}
  {{% tab header="Python" %}}
  By default all logs are sent to `sys.stderr`. To direct output somewhere else, you need to add a
handler with either a `StreamHandler` or a `FileHandler`:
{{< gh-codeblock path="/examples/python/tests/troubleshooting/test_logging.py#L12-L14" >}}
  {{% /tab %}}
  {{% tab header="CSharp" %}}
```text
.NET does not currently have a Logging implementation
```
  {{% /tab %}}
  {{% tab header="Ruby" %}}
  By default, logs are sent to the console in `stdout`.  
  To store the logs in a file:

{{< gh-codeblock path="/examples/ruby/spec/troubleshooting/logging_spec.rb#L15" >}}
  {{% /tab %}}
  {{% tab header="JavaScript" %}}
JavaScript does not currently support sending output to a file.  

To send logs to console output:
```javascript
logging.installConsoleHandler()
```
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

## Logger filtering

{{< tabpane langEqualsHeader=true text=true >}}
{{% tab header="Java" %}}
Java logging is managed on a per class level, so 
instead of using the root logger (`Logger.getLogger("")`), set the level you want to use on a per-class
basis:
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/troubleshooting/LoggingTest.java#L30-L31" >}}
  {{% /tab %}}
  {{< tab header="Python" >}}
Because logging is managed by module, instead of working with just "selenium", you can specify
different levels for different modules:
{{< gh-codeblock path="/examples/python/tests/troubleshooting/test_logging.py#L16-L17" >}}
  {{< /tab >}}
  {{% tab header="CSharp" %}}
```text
.NET does not currently have a Logging implementation
```
  {{% /tab %}}
  {{% tab header="Ruby" %}}
Ruby's logger allows you to opt in ("allow") or opt out ("ignore") of log messages based on their IDs.
Everything that Selenium logs includes an ID. You can also turn on or off all deprecation notices by
using `:deprecations`.

These methods accept one or more symbols or an array of symbols:
{{< gh-codeblock path="/examples/ruby/spec/troubleshooting/logging_spec.rb#17" >}}
or
{{< gh-codeblock path="/examples/ruby/spec/troubleshooting/logging_spec.rb#L18" >}}
  {{% /tab %}}
  {{< tab header="JavaScript" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}
