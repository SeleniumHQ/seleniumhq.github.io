---
title: "Safari specific functionality"
linkTitle: "Safari"
weight: 10
description: >-
    These are capabilities and features specific to Apple Safari browsers.
aliases: [
"/documentation/zh-cn/capabilities/safari"
]
---

Unlike Chromium and Firefox drivers, the safaridriver is installed with the Operating System.
To enable automation on Safari, run the following command from the terminal:

```shell
safaridriver --enable
```

## Options

Capabilities common to all browsers are described on the [Options page]({{< ref "../drivers/options.md" >}}).

Capabilities unique to Safari can be found at Apple's page [About WebDriver for Safari](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari#2957227)

Starting a Safari session with basic defined options looks like this:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L90-L94">}}
  SafariOptions options = new SafariOptions();
  driver = new SafariDriver(options);
  
  driver.quit();
{{< /tab >}}
  {{< tab header="Python" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L61-L63" >}}
  driver = webdriver.Safari()

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L83-L86" >}}
  var options = new SafariOptions();
  var driver = new SafariDriver(options);

  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L65-L68" >}}
  options = Selenium::WebDriver::Options.safari
  driver = Selenium::WebDriver.for :safari, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  const { Builder } = require("selenium-webdriver");
  const safari = require('selenium-webdriver/safari');

  let options = new safari.Options();
  let driver = await new Builder()
    .forBrowser('safari')
    .setSafariOptions(options)
    .build();

  await driver.quit();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  val options = SafariOptions()
  val driver = SafariDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

### Mobile
Those looking to automate Safari on iOS should look to the [Appium project](//appium.io/).
