---
title: "Remote WebDriver client"
linkTitle: "Remote WebDriver"
weight: 10
description: >
    Using the RemoteWebDriver client in Selenium 2.
---

This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/RemoteWebDriver) \
For current information, read about [Remote WebDriver]({{< ref "/documentation/webdriver/drivers/remote_webdriver" >}}).

This is information about using the client implementation of the RemoteWebDriver. This is the code that is used within your tests. For information on how to set up the server-side, please take a look at the [Remote Server]({{< ref "remote_server" >}}) page.

## Installing

Download the "selenium-server.zip" and unpack. Put the JARs on the CLASSPATH. This will give you the remote webdriver client, which is generally what you need. Please consult the [Remote Server]({{< ref "remote_server" >}}) page for information on how to set up the server-side of the remote webdriver.

## Pros

  * Separates where the tests are running from where the browser is.
  * Allows tests to be run with browsers not available on the current OS (because the browser can be elsewhere)

## Cons

  * Requires an external servlet container to be running
  * You may find problems with line endings when getting text from the remote server
  * Introduces extra latency to tests, particularly when exceptions are thrown.

## Using

This is probably best demonstrated with some code:

```java
// We could use any driver for our tests...
DesiredCapabilities capabilities = new DesiredCapabilities();

// ... but only if it supports javascript
capabilities.setJavascriptEnabled(true);

// Get a handle to the driver. This will throw an exception
// if a matching driver cannot be located
WebDriver driver = new RemoteWebDriver(capabilities);

// Query the driver to find out more information
Capabilities actualCapabilities = ((RemoteWebDriver) driver).getCapabilities();

// And now use it
driver.get("http://www.google.com");
```

_This section is out of date. Please refer to screenshots [here](https://www.selenium.dev/documentation/webdriver/interactions/windows/#takescreenshot)_
One nice feature of the remote webdriver is that exceptions often have an attached screen shot, encoded as a Base64 PNG. In order to get this screenshot, you need to write code similar to:

```java
public String extractScreenShot(WebDriverException e) {
  Throwable cause = e.getCause();
  if (cause instanceof ScreenshotException) {
    return ((ScreenshotException) cause).getBase64EncodedScreenshot();
  }
  return null;
}
```

## RemoteWebDriver Modes

The remote webdriver comes in two flavours:

  * **Client mode**: where the language bindings connect to the remote instance. This is the way that the FirefoxDriver, OperaDriver and the RemoteWebDriver client normally work.
  * **Server mode**: where the language bindings are responsible for setting up the server, which the driver running in the browser can connect to. The ChromeDriver works in this way.

We realise that these terms are confusing, so please feel free to suggest something better!
