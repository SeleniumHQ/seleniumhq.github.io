---
title: "Logging Selenium commands"
linkTitle: "Logging"
weight: 4
description: >
  Getting information about Selenium execution.
---

Each language adopts a distinctly different approach to logging information about the activity
of the program.

## Ruby

Ruby uses a custom implementation of the default `Logger` class with some interesting additional features.

### Logger output

By default, logs are sent to the console in `stdout`.
if you want to store the logs in a file, add this to your code:
```ruby
Selenium::WebDriver.logger.output = '/path/to/selenium.log'
```

### Logger level

The basic levels for the Ruby logger are: `:debug`, `:info`, `:warn`, `:error`, `:fatal`

Selenium uses `:info` and `:debug` similar to "verbose" and "very verbose", so the default
is `:warn`.

To change the level of the logger:
```ruby
Selenium::WebDriver.logger.level = :fatal
```

**WARN**

Warnings include everything we want users to be aware of by default. This is mostly used
for deprecations. For various reasons, Selenium project does not follow standard Semantic Versioning practices.
Our policy is to mark things as deprecated for 3 releases and then remove them.
As such, Ruby logs deprecations as warnings, specifying what is changing, what needs to be
used instead. It may include additional messages, and always includes an ID.

For example:
> 2022-12-24 16:07:09 WARN Selenium [DEPRECATION] [:jwp_caps] \`Capabilities#version=\` is deprecated. Use \`Capabilities#browser_version=\` instead.

Because these items can get annoying, we've provided an easy way to turn them off.

To turn off a specific warning, set the ID to ignore:
```ruby
Selenium::WebDriver.logger.ignore(:jwp_caps)
```
It accepts an `Array` to turn off multiple IDs:
```ruby
Selenium::WebDriver.logger.ignore(%i[jwp_caps pause pauses])
```
To turn off all *deprecation* notices:
```ruby
Selenium::WebDriver.logger.ignore(:deprecations)
```

**INFO**

This is where the most useful information gets logged. Selenium logs the endpoints and payloads
sent to and received from the driver or server. This is a great way to see what Selenium is actually
doing under the hood, and can be used to determine if it is Selenium code or driver code that
is causing a problem. (Unfortunately, we can't blame the driver if Selenium is sending incorrect syntax).

**DEBUG**

This is less useful information where we log things about the servers and the sockets, and header information, etc.
Debug mode is set if either `$DEBUG` is true or `ENV['DEBUG']` has a value.

-----

{{< alert-content >}}
Descriptions of how to set and use logging in Java, Python, JavaScript, and .NET
{{< /alert-content >}}
