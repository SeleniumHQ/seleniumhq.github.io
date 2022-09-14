---
title: "Firefox specific functionality"
linkTitle: "Firefox"
weight: 6
description: >-
    These are capabilities and features specific to Mozilla Firefox browsers.
aliases: [
"/documentation/pt-br/capabilities/firefox"
]
---

Selenium 4 requires Firefox 78 or greater. It is recommended to always use the latest version of geckodriver.

## Options

Capabilities common to all browsers are described on the [Options page]({{< ref "../drivers/options.md" >}}).

Capabilities unique to Firefox can be found at Mozilla's page for [firefoxOptions](https://developer.mozilla.org/en-US/docs/Web/WebDriver/Capabilities/firefoxOptions)

Starting a Firefox session with basic defined options looks like this:

{{< tabpane langEqualsHeader=true code=false >}}
  {{< tab header="Java" code=true github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/java/src/test/java/dev/selenium/getting_started/OpenBrowserTest.java#L43-L46">}}
  FirefoxOptions options = new FirefoxOptions();
  driver = new FirefoxDriver(options);

  driver.quit();
  {{< /tab >}}
  {{< tab header="Python" code=true github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/python/tests/getting_started/test_open_browser.py#L26-L29" >}}
  options = FirefoxOptions()
  driver = webdriver.Firefox(options=options)

  driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" code=true github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/dotnet/SeleniumDocs/GettingStarted/OpenBrowserTest.cs#L36-L39" >}}
  var options = new FirefoxOptions();
  var driver = new FirefoxDriver(options);
  
  driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" code=true github="SeleniumHQ/seleniumhq.github.io/blob/dev/examples/ruby/spec/getting_started/open_browser_spec.rb#L26-L29" >}}
  options = Selenium::WebDriver::Options.firefox
  driver = Selenium::WebDriver.for :firefox, options: options

  driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" code=false >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openFirefoxTest.spec.js">}}
  {{< /tab >}}
  {{< tab header="Kotlin" code=true >}}
  val options = FirefoxOptions()
  val driver = FirefoxDriver(options)
  
  driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

Here are a few common use cases with different capabilities:

### Arguments

The `args` parameter is for a list of Command line switches used when starting the browser.  
Commonly used args include `-headless` and `"-profile", "/path/to/profile"`

Add an argument to options:

{{< alert-code />}}

### Start browser in a specified location

The `binary` parameter takes the path of an alternate location of browser to use. For example, with this parameter you can
use geckodriver to drive Firefox Nightly instead of the production version when both are present on your computer.

Add a browser location to options:

{{< alert-code />}}

### Profiles

There are several ways to work with Firefox profiles

<div>
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
FirefoxProfile profile = new FirefoxProfile();
FirefoxOptions options = new FirefoxOptions();
options.setProfile(profile);
driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.firefox.firefox_profile import FirefoxProfile
options=Options()
firefox_profile = FirefoxProfile()
firefox_profile.set_preference("javascript.enabled", False)
options.profile = firefox_profile
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new FirefoxOptions();
var profile = new FirefoxProfile();
options.Profile = profile;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
profile = Selenium::WebDriver::Firefox::Profile.new
profile['browser.download.dir'] = "/tmp/webdriver-downloads"
options = Selenium::WebDriver::Firefox::Options.new(profile: profile)
driver = Selenium::WebDriver.for :firefox, options: options
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder } = require("selenium-webdriver");
const firefox = require('selenium-webdriver/firefox');

const options = new firefox.Options();
let profile = '/path to custom profile';
options.setProfile(profile);
const driver = new Builder()
    .forBrowser('firefox')
    .setFirefoxOptions(options)
    .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = FirefoxOptions()
options.profile = FirefoxProfile()
driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}
</div>

## Add-ons

Unlike Chrome, Firefox extensions are not added as part of capabilities, they are created after starting the driver.

### Installation

A signed xpi file you would get from [Mozilla Addon page](https://addons.mozilla.org/en-US/firefox/) 

{{< alert-code />}}

### Unsigned installation

When working with an unfinished or unpublished extension, it will likely not be signed. As such, it can only
be installed as "temporary." This can be done by passing in either a zip file or a directory, here's an 
example with a directory:

{{< alert-code />}}

### Uninstallation

Uninstalling an addon requires knowing its id. The id can be obtained from the return value when installing the add-on.

{{< alert-code />}}

## Full page screenshots

{{< alert-code />}}

## Context

{{< alert-code />}}
