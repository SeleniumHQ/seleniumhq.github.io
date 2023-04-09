---
title: "Logging Selenium commands"
linkTitle: "Logging"
weight: 4
description: >
  Getting information about Selenium execution.
---

Each language adopts a distinctly different approach to logging information about the activity
of the program.

### 1. Getting a logger

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{% tab header="Ruby" %}}
  Ruby uses a custom implementation of the default `Logger` class with some interesting additional features.
    
    logger = Selenium::WebDriver.logger
  {{% /tab %}}
  {{% tab header="JavaScript" %}}
    const logging = require('selenium-webdriver/lib/logging')
    logger = logging.getLogger('webdriver')
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

### 2. Logger Level
Logger level helps to filter out logs based on their severity.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{% tab header="Ruby" %}}
  Levels are: `:debug`, `:info`, `:warn`, `:error`, `:fatal`. Default is `:warn`.  
  To change the level of the logger:
  
    Selenium::WebDriver.logger.level = :info
  {{% /tab %}}
  {{% tab header="JavaScript" %}}
  Levels are: `OFF`, `SEVERE`, `WARNING`, `INFO`, `DEBUG`, `FINE`, `FINER`, `FINEST`, `ALL`. Default is `OFF`.  
  To change the level of the logger:
  
    logger.setLevel(logging.Level.INFO)
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

**Actionable Items**

Things are logged as warnings if they are something the user needs to take action on. This is often used
for deprecations. For various reasons, Selenium project does not follow standard Semantic Versioning practices.
Our policy is to mark things as deprecated for 3 releases and then remove them.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{% tab header="Ruby" %}}
  Ruby logs deprecations as warnings, specifying what is changing, what needs to be
  used instead. It may include additional messages, and always includes an ID.

  For example:
  > 2022-12-24 16:07:09 WARN Selenium [DEPRECATION] [:jwp_caps] \`Capabilities#version=\` is deprecated. Use \`Capabilities#browser_version=\` instead.

  Because these items can get annoying, we've provided an easy way to turn them off.  
  
  To turn off a specific warning, set the ID to ignore:
  
    Selenium::WebDriver.logger.ignore(:jwp_caps)
  
  It accepts an `Array` to turn off multiple IDs:
  
    Selenium::WebDriver.logger.ignore(%i[jwp_caps pause pauses])
  
  To turn off all *deprecation* notices:
  
    Selenium::WebDriver.logger.ignore(:deprecations)
  {{% /tab %}}
  {{< tab header="JavaScript" >}}
  {{< alert-content />}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

**Useful Information**

This is the default level where Selenium logs things that users should be aware of but do not need to take actions on.
It presents information such as requests and responses between driver and server, payload, etc.

Different languages have different level to log information.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{% tab header="Ruby" %}}
  Because the Ruby `Logger` class only has one "debug" level, Selenium is currently using the `:info` level as a general debug mode, and `:debug` as a lower level debug mode.
  To bring things in line with other languages, we are considering <a href="https://github.com/SeleniumHQ/selenium/issues/11797">changing this behavior</a>.
  For now, both info and warnings are handled at the default `:warn` level.
  {{% /tab %}}
  {{% tab header="JavaScript" %}}
  Logs useful information at level: `INFO`
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

**Debugging Details**

The debug log level is used for information that may be needed for diagnosing issues and troubleshooting problems.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{% tab header="Ruby" %}}
  Logs debugging details at level: `:info` and `:debug`
  {{% /tab %}}
  {{% tab header="JavaScript" %}}
  Logs debugging details at level: `FINER` and `FINEST`
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

### 3. Log Output:
Logs can be displayed on `stdout` or stored in a file.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
  {{% tab header="Ruby" %}}
  By default, logs are sent to the console in `stdout`.  
  To store the logs in a file:
  
    Selenium::WebDriver.logger.output = '/path/to/selenium.log'
  
  Sample Output:
  
    2023-02-08 12:31:23 INFO Selenium -> POST session/8b83ff54712d0a247937d045f8c8e171/url
    2023-02-08 12:31:23 INFO Selenium    >>> http://127.0.0.1:9515/session/8b83ff54712d0a247937d045f8c8e171/url | {"url":"https://www.selenium.dev/selenium/web/web-form.html"}
    2023-02-08 12:31:23 INFO Selenium <- {"value":null}
  {{% /tab %}}
  {{% tab header="JavaScript" %}}
  Send logs to console output:

    logging.installConsoleHandler()
  
  Sample Output:
  
    [2023-03-21T22:28:20Z] [FINER] [webdriver.http.Executor] >>> POST /session/0208994573cca3250a1066424c1b915c/url
    [2023-03-21T22:28:22Z] [FINER] [webdriver.http.Executor] >>>
    POST /session/0208994573cca3250a1066424c1b915c/url HTTP/1.1
    accept: application/json; charset=utf-8

    {"url":"https://www.selenium.dev/selenium/web/web-form.html"}
    <<<
    HTTP/1.1 200
    content-length: 14
    content-type: application/json; charset=utf-8
    cache-control: no-cache

    {"value":null}
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}
