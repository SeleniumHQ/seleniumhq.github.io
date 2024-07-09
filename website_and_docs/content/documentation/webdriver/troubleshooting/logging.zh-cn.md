---
title: "记录 Selenium 命令"
linkTitle: "日志"
weight: 4
description: >
  获取Selenium的执行信息.
---

启用日志记录是获取额外信息的宝贵方法, 
这些信息可能有助于您确定
遇到问题的原因.

## 获取一个logger

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
Java 日志通常是按类创建的. 
您可以通过默认logger来使用所有loggers.
为了过滤特定类, 请参考 [过滤器](#logger-过滤)

获取根logger:
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/troubleshooting/LoggingTest.java#L31" >}}

Java日志并不简单直接,
如果您只是在寻找一种简单的方法
查看重要的Selenium日志, 
请参阅 [Selenium Logger 项目](https://github.com/titusfortner/selenium-logger#selenium-logger)
  {{% /tab %}}
  {{% tab header="Python" %}}
Python logs are typically created per module. You can match all submodules by referencing the top
level module. So to work with all loggers in selenium module, you can do this:
{{< gh-codeblock path="/examples/python/tests/troubleshooting/test_logging.py#L5" >}}
  {{% /tab %}}
  {{% tab header="CSharp" %}}
.NET logger is managed with a static class, so all access to logging is managed simply by referencing `Log` from the `OpenQA.Selenium.Internal.Logging` namespace.
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

## 日志级别
Logger级别有助于根据日志的严重性过滤日志.

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
  Java有七种logger级别: `SEVERE`, `WARNING`, `INFO`, `CONFIG`, `FINE`, `FINER`, 以及 `FINEST`.
默认是 `INFO`. 

您必须更改logger的级别和根logger上的处理程序的级别:
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/troubleshooting/LoggingTest.java#L32-L35" >}}
  {{% /tab %}}
  {{% tab header="Python" %}}
  Python has 6 logger levels: `CRITICAL`, `ERROR`, `WARNING`, `INFO`, `DEBUG`, and `NOTSET`. 
  The default is `WARNING`
 
To change the level of the logger:
{{< gh-codeblock path="/examples/python/tests/troubleshooting/test_logging.py#L7" >}}
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
.NET has 6 logger levels: `Error`, `Warn`, `Info`, `Debug`, `Trace` and `None`. The default level is `Info`.

To change the level of the logger:
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Troubleshooting/LoggingTest.cs#L18" >}}
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

对于需要用户后续行动的操作, 会将其记录为警告. 
这经常用于被弃用的内容. 
由于各种原因, Selenium项目不遵循标准的语义版本控制实践. 
我们的政策是将 3 个版本的内容标记为已弃用后, 
再删除它们, 因此弃用可能记录为警告.

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
Java 日志可操作的内容在logger级别 `WARN`

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
.NET logs actionable content at logger level `Warn`.

Example:
```text
11:04:40.986 WARN LoggingTest: this is a warning
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

这是默认级别, Selenium 记录用户应该注意但不需要对其执行操作的内容. 
这可能会引用新方法或将用户定向到有关某些内容的详细信息

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
  Java 日志有用的信息在logger 级别 `INFO`

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
.NET logs useful information at logger level `Info`.

Example:
```text
11:04:40.986 INFO LoggingTest: this is useful information
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

调试日志级别用于诊断问题和解决问题可能需要的信息.

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
Java日志的大多数调试信息在logger 级别 `FINE`

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
.NET logs most debug content at logger level `Debug`.

Example:
```text
11:04:40.986 DEBUG LoggingTest: this is detailed debug information
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

## Logger 输出

日志可以显示在控制台中, 也可以存储在文件中. 
不同的语言有不同的默认值. 

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
默认情况下, 所有日志都发送到 `System.err`.
要将输出定向到文件, 您需要添加一个处理程序:

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/troubleshooting/LoggingTest.java#L37-L38" >}}
  {{% /tab %}}
  {{% tab header="Python" %}}
  By default all logs are sent to `sys.stderr`. To direct output somewhere else, you need to add a
handler with either a `StreamHandler` or a `FileHandler`:
{{< gh-codeblock path="/examples/python/tests/troubleshooting/test_logging.py#L9-L10" >}}
  {{% /tab %}}
  {{% tab header="CSharp" %}}
By default all logs are sent to `System.Console.Error` output. To direct output somewhere else, you need to add a handler with a `FileLogHandler`:
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Troubleshooting/LoggingTest.cs#L20" >}}
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

## Logger 过滤

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Java 日志记录是按类级别管理的, 
因此不要使用根logger  (`Logger.getLogger("")`), 
而是在每个类的基础上设置要使用的级别:

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/troubleshooting/LoggingTest.java#L40-L41" >}}
  {{% /tab %}}
  {{< tab header="Python" >}}
Because logging is managed by module, instead of working with just "selenium", you can specify
different levels for different modules:
{{< gh-codeblock path="/examples/python/tests/troubleshooting/test_logging.py#L12-L13" >}}
  {{< /tab >}}
  {{% tab header="CSharp" %}}
.NET logging is managed on a per class level, set the level you want to use on a per-class basis:
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Troubleshooting/LoggingTest.cs#L22-L23" >}}
  {{% /tab %}}
  {{% tab header="Ruby" %}}
Ruby's logger allows you to opt in ("allow") or opt out ("ignore") of log messages based on their IDs.
Everything that Selenium logs includes an ID. You can also turn on or off all deprecation notices by
using `:deprecations`.

These methods accept one or more symbols or an array of symbols:
{{< gh-codeblock path="/examples/ruby/spec/troubleshooting/logging_spec.rb#L17" >}}
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
