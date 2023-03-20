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
  <p>Levels are: <i>`:debug`, `:info`, `:warn`, `:error`, `:fatal`.</i> Default is <i>`:warn`</i>.</p>
  <p>To change the level of the logger:</p>
  <pre>
  Selenium::WebDriver.logger.level = :fatal</pre>
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  <p>Levels are: <i>`OFF`, `SEVERE`, `WARNING`, `INFO`, `DEBUG`, `FINE`, `FINER`, `FINEST`, `ALL`</i>. Default is <i>`OFF`</i>.</p>
  <p>To change the level of the logger:</p>
  <pre>
  logger.setLevel(logging.Level.INFO)</pre>
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

**WARN**

Warnings include everything we want users to be aware of by default. This is mostly used
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

**INFO**

This is where the most useful information gets logged. Selenium logs the endpoints and payloads
sent to and received from the driver or server. This is a great way to see what Selenium is actually
doing under the hood, and can be used to determine if it is Selenium code or driver code that
is causing a problem. (Unfortunately, we can't blame the driver if Selenium is sending incorrect syntax).

Different languages have different level to log requests and response.

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
  Logs request and response at level: <i>`:info`</i>
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  Logs request and response at level: <i>`FINER`</i>
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}

**DEBUG**

This is less useful information where we log things about the servers and the sockets, and header information, etc.
Debug mode is set if either `$DEBUG` is true or `ENV['DEBUG']` has a value.

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
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  <p>Send logs to console output:</p>
  <pre>
  logging.installConsoleHandler()</pre>
  <br>
  {{< alert-content >}}
  Store logs in a file.
  {{< /alert-content >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< alert-content >}}{{< /alert-content >}}
  {{< /tab >}}
{{< /tabpane >}}
