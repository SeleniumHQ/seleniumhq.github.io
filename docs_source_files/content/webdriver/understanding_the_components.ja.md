---
title: "Understanding the components"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

Building a test suite using WebDriver will require you to understand and
effectively use a number of different components. As with everything in
software, different people use different terms for the same idea. Below is
a breakdown of how terms are used in this description.

### Terminology

* **API:** Application Programming Interface. This is the set of "commands"
you use to manipulate WebDriver.
* **Library:** A code module which contains the APIs and the code necessary
to implement them. Libraries are specific to each language binding, eg .jar
files for Java, .dll files for .NET, etc.
* **Driver:** Responsible for controlling the actual browser. Most drivers
are created by the browser vendors themselves. Drivers are generally
executable modules that run on the system with the browser itself,
not on the system executing the test suite. (Although those may be the
same system.) _NOTE: Some people refer to the drivers as proxies._
* **Framework:** An additional library used as a support for WebDriver
suites. These frameworks may be test frameworks such as JUnit or NUnit.
They may also be frameworks supporting natural language features such
as Cucumber or Robotium. Frameworks may also be written and used for
things such as manipulating or configuring the system under test, data
creation, test oracles, etc.


### The Parts and Pieces
At its minimum, WebDriver talks to a browser through a driver. Communication
is two way: WebDriver passes commands to the browser through the driver, and
receives information back via the same route.

![Basic communication](/images/basic_comms.png?width=400px)

The driver is specific to the browser, such as ChromeDriver for Google's
Chrome/Chromium, GeckoDriver for Mozilla's Firefox, etc. The driver runs on
the same system as the browser. This may, or may not be, the same system where
the tests themselves are executing.

This simple example above is _direct_ communication. Communication to the
browser may also be _remote_ communication through Selenium Server or
RemoteWebDriver. RemoteWebDriver runs on the same system as the driver
and the browser.

![Remote communication](/images/remote_comms.png?width=400px)

Remote communication can also take place using Selenium Server or Selenium
Grid, both of which in turn talk to the driver on the host system

![Remote communication with Grid](/images/remote_comms_server.png?width=400px)

## Where Frameworks fit in

WebDriver has one job and one job only: communicate with the browser via any
of the methods above. WebDriver doesn't know a thing about testing: it doesn't
know how to compare things, assert pass or fail, and it certainly doesn't know
a thing about reporting or Given/When/Then grammar.

This is where various frameworks come in to play. At a minimum you'll need a
test framework that matches the language bindings, eg NUnit for .NET, JUnit
for Java, RSpec for Ruby, etc.

The test framework is responsible for running and executing your WebDriver
and related steps in your tests. As such, you can think of it looking akin
to the following image.

![Test framework](/images/test_framework.png?width=400px)

Natural language frameworks/tools such as Cucumber may exist as part of that
Test Framework box in the figure above, or they may wrap the Test Framework
entirely in their own implementation.
