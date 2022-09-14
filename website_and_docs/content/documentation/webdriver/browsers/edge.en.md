---
title: "Edge specific functionality"
linkTitle: "Edge"
weight: 5
description: >-
    These are capabilities and features specific to Microsoft Edge browsers.
---

Microsoft Edge is implemented with Chromium, with the earliest supported version of v79. Similar to Chrome,
the major version number of edgedriver must match the major version of the Edge browser.

All capabilities and options found on the [Chrome page]({{< ref "chrome.md" >}}) work for Edge as well.

## Options

Starting an Edge session with basic defined options looks like this:

{{< tabpane langEqualsHeader=true code=false >}}
  {{< tab header="Java" code=true github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L35-L38">}}
  EdgeOptions options = new EdgeOptions();
  driver = new EdgeDriver(options);

  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" code=true github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L18-L21" >}}
  options = EdgeOptions()
  driver = webdriver.Edge(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" code=true github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L27-L30" >}}
  var options = new EdgeOptions();
  var driver = new EdgeDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" code=true github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L17-L20" >}}
  options = Selenium::WebDriver::Options.edge
  driver = Selenium::WebDriver.for :edge, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openEdgeTest.spec.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" code=true >}}
  val options = EdgeOptions()
  val driver = EdgeDriver(options)

  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

### Internet Explorer Compatibility Mode

Microsoft Edge can be driven in "Internet Explorer Compatibility Mode," which uses
the Internet Explorer Driver classes in conjunction with Microsoft Edge.
Read the [Internet Explorer page]({{< ref "internet_explorer.md" >}}) for more details.
