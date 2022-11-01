---
title: "Selenium components"
linkTitle: "Components"
weight: 1
aliases: [
"/documentation/en/webdriver/understanding_the_components/",
"/documentation/webdriver/understanding_the_components"
]
---

Building a test suite using WebDriver will require you to understand and 
effectively use several components. As with everything in 
software, different people use different terms for the same idea. Below is
a breakdown of how terms are used in this description.

### Terminology

* **API:** Application Programming Interface. This is the set of "commands" 
you use to manipulate WebDriver.
* **Library:** A code module that contains the APIs and the code necessary
to implement them. Libraries are specific to each language binding, eg .jar 
files for Java, .dll files for .NET, etc.
* **Driver:** Responsible for controlling the actual browser. Most drivers 
are created by the browser vendors themselves. Drivers are generally 
executable modules that run on the system with the browser itself,
not the system executing the test suite. (Although those may be the 
same system.) NOTE: _Some people refer to the drivers as proxies._
* **Framework:** An additional library that is used as a support for WebDriver 
suites. These frameworks may be test frameworks such as JUnit or NUnit. 
They may also be frameworks supporting natural language features such 
as Cucumber or Robotium. Frameworks may also be written and used for 
tasks such as manipulating or configuring the system under test, data 
creation, test oracles, etc.


### The Parts and Pieces
At its minimum, WebDriver talks to a browser through a driver. Communication
is two-way: WebDriver passes commands to the browser through the driver, and 
receives information back via the same route.

{{< figure src="/images/documentation/webdriver/basic_comms.png" class="img-responsive text-center" alt="Basic Communication">}}

The driver is specific to the browser, such as ChromeDriver for Google's 
Chrome/Chromium, GeckoDriver for Mozilla's Firefox, etc. The driver runs on 
the same system as the browser. This may or may not be the same system where 
the tests themselves are executed.

This simple example above is _direct_ communication. Communication to the 
browser may also be _remote_ communication through Selenium Server or 
RemoteWebDriver. RemoteWebDriver runs on the same system as the driver 
and the browser.

{{< figure src="/images/documentation/webdriver/remote_comms.png" class="img-responsive text-center" alt="Remote Communication">}}

Remote communication can also take place using Selenium Server or Selenium 
Grid, both of which in turn talk to the driver on the host system

{{< figure src="/images/documentation/webdriver/remote_comms_server.png" class="img-responsive text-center" alt="Remote Communication with Grid">}}

## Where Frameworks fit in

WebDriver has one job and one job only: communicate with the browser via any
of the methods above. WebDriver does not know a thing about testing: it does not
know how to compare things, assert pass or fail, and it certainly does not know
a thing about reporting or Given/When/Then grammar.

This is where various frameworks come into play. At a minimum, you will need a 
test framework that matches the language bindings, e.g., NUnit for .NET, JUnit 
for Java, RSpec for Ruby, etc.

The test framework is responsible for running and executing your WebDriver 
and related steps in your tests. As such, you can think of it looking akin 
to the following image.

{{< figure src="/images/documentation/webdriver/test_framework.png" class="img-responsive text-center" alt="Test Framework">}}

Natural language frameworks/tools such as Cucumber may exist as part of that 
Test Framework box in the figure above, or they may wrap the Test Framework 
entirely in their custom implementation.
