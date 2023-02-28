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


## Javascript

JS has implemented logging APIs. It has also added LogManager to maintain a collection of loggers.

### 1. Getting a logger can be done in two ways:

```
const logging = require('selenium-webdriver/lib/logging')
```

First method using manager
```
mgr = new logging.LogManager()
logger = mgr.getLogger('')
```

Second method without manager
```
logger = logging.getLogger('')
```

Loggers class uses hierarchical, dot-delimited namespaces (e.g. "" > "webdriver" > "webdriver.logging"). For example, the following code will give a hierarchy of loggers:
```
logger = mgr.getLogger('foo.bar.baz')
```

"" (parent of) > "foo" (parent of) > "foo.bar" (parent of) > "foo.bar.baz" (=logger)


### 2. Logger Level:

The basic levels for JS loggers are: `OFF`, `SEVERE`, `WARNING`, `INFO`, `DEBUG`, `FINE`, `FINER`, `FINEST`, `ALL`.
To log messages at the given level pass the level in the `log()` method:
```
logger.log(logging.Level.INFO, 'This is an info message')
```

You can also use the instance methods to target logs of a particular level:
```
logger.finest('this is the finest message')
logger.finer('this is a finer message')
logger.info('this is an info message')
logger.warning('this is a warning message')
logger.severe('this is a severe message')
```

By default the logger's level is `Level.OFF`
To change the level of the logger:
```
logger.setLevel(logging.Level.INFO)
```

### 3. Log Output:

As opposed to ruby which by default sends logs to console in stdout, JS has the option to add a handler. The handler will be invoked for each message logged with this instance, or any of its descendants.
```
logger.addHandler(callback)
```

JS has provided a method to add console handler to the given logger. The console handler will log all messages using the JavaScript Console API:
```
logging.addConsoleHandler(logger)
```

Similarly, to add console handler to the root logger:
```
logging.installConsoleHandler()
```

### 4. Logging Preference (remote logging API):

JS gives the ability to define log preference for a remote WebDriver session. There are 5 common log types: `BROWSER`, `CLIENT`, `DRIVER`, `PERFORMANCE`, `SERVER`

To set a log level for a particular log type:
```
let prefs = new logging.Preferences()
prefs.setLevel(logging.Type.BROWSER, logging.Level.FINE)
```

-----

{{< alert-content >}}
Descriptions of how to set and use logging in Java, Python, JavaScript, and .NET
{{< /alert-content >}}
