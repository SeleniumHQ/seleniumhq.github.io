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
  {{< tab header="Ruby" >}}
  <p>Ruby uses a custom implementation of the default `Logger` class with some interesting additional features.</p>
  <pre>
  logger = Selenium::WebDriver.logger</pre>
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  <pre>
  const logging = require('selenium-webdriver/lib/logging')
  logger = logging.getLogger('webdriver')</pre>
  {{< /tab >}}
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
  {{< tab header="Ruby" >}}
  <p>Levels are: <code>:debug</code>, <code>:info</code>, <code>:warn</code>, <code>:error</code>, <code>:fatal</code>. Default is <code>:warn</code>.</p>
  <p>To change the level of the logger:</p>
  <pre>
  Selenium::WebDriver.logger.level = :info</pre>
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  <p>Levels are: <code>OFF</code>, <code>SEVERE</code>, <code>WARNING</code>, <code>INFO</code>, <code>DEBUG</code>, <code>FINE</code>, <code>FINER</code>, <code>FINEST</code>, <code>ALL</code>. Default is <code>OFF</code>.</p>
  <p>To change the level of the logger:</p>
  <pre>
  logger.setLevel(logging.Level.INFO)</pre>
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

**Actionable Items**

Things are logged as warnings if they are something the user needs to take action on. This is mostly used
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
  {{< tab header="Ruby" >}}
  <p>Ruby logs deprecations as warnings, specifying what is changing, what needs to be
  used instead. It may include additional messages, and always includes an ID.</p>

  <p>For example:</p>
  <blockquote>2022-12-24 16:07:09 WARN Selenium [DEPRECATION] [:jwp_caps] \`Capabilities#version=\` is deprecated. Use \`Capabilities#browser_version=\` instead.</blockquote>

  <p>Because these items can get annoying, we've provided an easy way to turn them off.</p>

  <p>To turn off a specific warning, set the ID to ignore:</p>
  <pre>
  Selenium::WebDriver.logger.ignore(:jwp_caps)</pre>
  <br>
  <p>It accepts an `Array` to turn off multiple IDs:</p>
  <pre>
  Selenium::WebDriver.logger.ignore(%i[jwp_caps pause pauses])</pre>
  <br>
  <p>To turn off all *deprecation* notices:</p>
  <pre>
  Selenium::WebDriver.logger.ignore(:deprecations)</pre>
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< alert-content >}}{{< /alert-content >}}
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
  {{< tab header="Ruby" >}}
  Logs useful information at level: <code>:info</code>
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  Logs useful information at level: <code>FINER</code>
  {{< /tab >}}
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
  {{< tab header="Ruby" >}}
  Logs debugging details at level: <code>:info</code> and <code>:debug</code>
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  Logs debugging details at level: <code>FINER</code> and <code>FINEST</code>
  {{< /tab >}}
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
  {{< tab header="Ruby" >}}
  <p>By default, logs are sent to the console in `stdout`.</p>
  <p>To store the logs in a file:</p>
  <pre>
  Selenium::WebDriver.logger.output = '/path/to/selenium.log'</pre>
  <br>
  <p>Sample Output:</p>
  <blockquote>
  2023-02-08 12:31:23 INFO Selenium -> POST session/8b83ff54712d0a247937d045f8c8e171/url<br>
  2023-02-08 12:31:23 INFO Selenium    >>> http://127.0.0.1:9515/session/8b83ff54712d0a247937d045f8c8e171/url | {"url":"https://www.selenium.dev/selenium/web/web-form.html"}<br>
  2023-02-08 12:31:23 INFO Selenium <- {"value":null}</blockquote>
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  <p>Send logs to console output:</p>
  <pre>
  logging.installConsoleHandler()</pre>
  <br>
  <p>Sample Output:</p>
  <blockquote>
  [2023-03-21T22:28:20Z] [FINER] [webdriver.http.Executor] >>> POST /session/0208994573cca3250a1066424c1b915c/url<br>
  [2023-03-21T22:28:22Z] [FINER] [webdriver.http.Executor] >>>
  POST /session/0208994573cca3250a1066424c1b915c/url HTTP/1.1<br>
  accept: application/json; charset=utf-8

  {"url":"https://www.selenium.dev/selenium/web/web-form.html"}<br>
  <<<
  HTTP/1.1 200
  content-length: 14
  content-type: application/json; charset=utf-8
  cache-control: no-cache

  {"value":null}</blockquote>
  {{< alert-content >}}
  Store logs in a file.
  {{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}
