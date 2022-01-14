---
title: "Backing Selenium with WebDriver"
linkTitle: "Emulations"
weight: 3
description: >
    The Java and .NET versions of Selenium 2 provided implementations of the original Selenium API
---
(Previously located: https://github.com/SeleniumHQ/selenium/wiki/Selenium-Emulation)

## Backing Selenium with WebDriver
The Java and .NET versions of WebDriver provide implementations of the existing Selenium API. In Java, it is used like so:

```
// You may use any WebDriver implementation. Firefox is used here as an example
WebDriver driver = new FirefoxDriver();

// A "base url", used by selenium to resolve relative URLs
String baseUrl = "http://www.google.com";

// Create the Selenium implementation
Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);

// Perform actions with selenium
selenium.open("http://www.google.com");
selenium.type("name=q", "cheese");
selenium.click("name=btnG");

// And get the underlying WebDriver implementation back. This will refer to the
// same WebDriver instance as the "driver" variable above.
WebDriver driverInstance = ((WebDriverBackedSelenium) selenium).getUnderlyingWebDriver();
```

## Pros

* Allows for WebDriver and Selenium to live side-by-side.
* Provides a simple mechanism for a managed migration from the existing Selenium API to WebDriver's.
* Does not require the standalone Selenium RC server to be run

## Cons

* Does not implement every method
    * But we'd love feedback!
* Does also emulate Selenium Core
    * So more advanced Selenium usage (that is, using "browserbot" or other built-in Javascript methods from Selenium Core) may need work
* Some methods may be slower due to underlying implementation differences
* Does not support Selenium's "user extensions" (_i.e._, user-extensions.js)

### Notes
After creating a `WebDriverBackedSelenium` instance with a given Driver, one does not have to call `start()` - as the creation of the Driver already started the session. At the end of the test, `stop()` should be called **instead** of the Driver's `quit()` method.

This is more similar to WebDriver's behaviour - as creating a Driver instance starts a session, yet it has to be terminated explicitly with a call to `quit()`.

## Backing Selenium with RemoteWebDriver
Starting with release 2.19, `WebDriverBackedSelenium` can be used from any language supported by WebDriver and Selenium.

For example, in Python:
```
driver = RemoteWebDriver(desired_capabilities = DesiredCapabilities.FIREFOX)
selenium = DefaultSelenium('localhost', '4444', '*webdriver', 'http://www.google.com')
selenium.start(driver = driver)
```

Provided you keep a reference to the original WebDriver and Selenium objects you created, you can use even the two APIs interchangeably.  The magic is the "`*webdriver`" browser name passed to the Selenium instance, and that you pass the WebDriver instance when calling `start()`.

In languages where DefaultSelenium doesn't have `start(driver)`, you can connect the WebDriver and Selenium objects together yourself, by supplying the WebDriver session ID to the Selenium object.

For example, in C#:
```

RemoteWebDriver driver = new RemoteWebDriver(DesiredCapabilities.Firefox());
string sessionId = (string) driver.Capabilities.GetCapability("webdriver.remote.sessionid");
DefaultSelenium selenium = new DefaultSelenium("localhost", 4444, "*webdriver", "http://www.google.com");
selenium.Start("webdriver.remote.sessionid=" + sessionId);
```

## Backing WebDriver with Selenium

WebDriver doesn't support as many browsers as Selenium does, so in order to provide that support while still using the webdriver API, you can make use of the `SeleneseCommandExecutor` It is done like this:

```
Capabilities capabilities = new DesiredCapabilities()
capabilities.setBrowserName("safari");
CommandExecutor executor = new SeleneseCommandExecutor("http:localhost:4444/", "http://www.google.com/", capabilities);
WebDriver driver = new RemoteWebDriver(executor, capabilities);
```

There are currently some major limitations with this approach, notably that `findElements` doesn't work as expected. Also, because we're using Selenium Core for the heavy lifting of driving the browser, you are limited by the Javascript sandbox.
