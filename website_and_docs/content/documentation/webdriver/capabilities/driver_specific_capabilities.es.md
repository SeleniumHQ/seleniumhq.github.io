---
title: "Capacidades específicas de los Drivers"
linkTitle: "Capacidades específicas de los Drivers"
weight: 2
aliases: ["/documentation/es/driver_idiosyncrasies/driver_specific_capabilities/"]
---

## Firefox

### Define las capacidades usando `FirefoxOptions`

`FirefoxOptions` es la nueva forma de definir capacidades para el navegador 
Firefox y debe de ser usado de manera general antes que DesriredCapabilities.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
FirefoxOptions options = new FirefoxOptions();
options.addPreference("network.proxy.type", 0);
driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.firefox.options import Options
options = Options()
options.headless = True
driver = webdriver.Firefox(options=options)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new FirefoxOptions();
options.Proxy.Kind = ProxyKind.Direct;
var driver = new FirefoxDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
opts = Selenium::WebDriver::Firefox::Options.new(args: ['-headless'])
driver = Selenium::WebDriver.for(:firefox, options: opts)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder } = require("selenium-webdriver");
const firefox = require('selenium-webdriver/firefox');

const options = new firefox.Options();
options.headless();
const driver = new Builder()
    .forBrowser('firefox')
    .setFirefoxOptions(options)
    .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = new FirefoxOptions()
options.addPreference("network.proxy.type", 0)
driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}


### Fijando un perfil a medida

Es posible crear perfiles a medida para Firefox, como se puede ver a continuación.

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

## Internet Explorer

### Timeout ventana de selección de archivos (fileUploadDialogTimeout)

En algunos entornos, Internet Explorer puede devolver un timeout cuando se abra
una ventana de selección de archivos (_file upload dialog_). Por defecto el IEDriver
tiene un timeout de 1000ms, pero puedes incrementarlo usando la capacidad
_fileUploadDialogTimeout_.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.waitForUploadDialogUpTo(Duration.ofSeconds(2));
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.file_upload_dialog_timeout = 2000
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.FileUploadDialogTimeout = TimeSpan.FromMilliseconds(2000);
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.file_upload_dialog_timeout = 2000
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().fileUploadDialogTimeout(2000);
let driver = await Builder()
          .setIeOptions(options)
          .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.waitForUploadDialogUpTo(Duration.ofSeconds(2))
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### ensureCleanSession

When set to `true`, this capability clears the _Cache, 
Browser History and Cookies_ for all running instances 
of InternetExplorer including those started manually 
or by the driver. By default, it is set to `false`.

Using this capability will cause performance drop while 
launching the browser, as the driver will wait until the cache 
gets cleared before launching the IE browser.  

This capability accepts a Boolean value as parameter.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.destructivelyEnsureCleanSession();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ensure_clean_session = True
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.EnsureCleanSession = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ensure_clean_session = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ensureCleanSession(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.destructivelyEnsureCleanSession()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### ignoreZoomSetting

InternetExplorer driver expects the browser zoom level to be 100%, 
else the driver will throw an exception. This default behaviour 
can be disabled by setting the _ignoreZoomSetting_ to _true_.
 
This capability accepts a Boolean value as parameter.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.ignoreZoomSettings();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ignore_zoom_level = True
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.IgnoreZoomLevel = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ignore_zoom_level = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ignoreZoomSetting(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.ignoreZoomSettings()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### ignoreProtectedModeSettings

Whether to skip the _Protected Mode_ check while launching 
a new IE session.

If not set and _Protected Mode_ settings are not same for 
all zones, an exception will be thrown by the driver.

If capability is set to `true`, tests may 
become flaky, unresponsive, or browsers may hang.
However, this is still by far a second-best choice, 
and the first choice should *always* be to actually 
set the Protected Mode settings of each zone manually. 
If a user is using this property, 
only a "best effort" at support will be given.

This capability accepts a Boolean value as parameter.
 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.introduceFlakinessByIgnoringSecurityDomains();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ignore_protected_mode_settings = True
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.IntroduceInstabilityByIgnoringProtectedModeSettings = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ignore_protected_mode_settings = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().introduceFlakinessByIgnoringProtectedModeSettings(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.introduceFlakinessByIgnoringSecurityDomains()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### silent

When set to `true`, this capability suppresses the
diagnostic output of the IEDriverServer.

This capability accepts a Boolean value as parameter.
 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.setCapability("silent", true);
WebDriver driver = new InternetExplorerDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.set_capability("silent", True)
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.AddAdditionalInternetExplorerOption("silent", true);
IWebDriver driver = new InternetExplorerDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Please raise a PR to add code sample
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder,By, Capabilities} = require('selenium-webdriver');
let caps = Capabilities.ie();
caps.set('silent', true);

(async function example() {
    let driver = await new Builder()
        .forBrowser('internet explorer')
        .withCapabilities(caps)
        .build();
    try {
        await driver.get('http://www.google.com/ncr');
    }
    finally {
        await driver.quit();
    }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.setCapability("silent", true)
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps)
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

### IE Command-Line Options

Internet Explorer includes several command-line options 
that enable you to troubleshoot and configure the browser.

The following describes few supported command-line options 

* _-private_ : Used to start IE in private browsing mode. This works for IE 8 and later versions.

* _-k_ : Starts Internet Explorer in kiosk mode. 
The browser opens in a maximized window that does not display the address bar, the navigation buttons, or the status bar.

* _-extoff_ : Starts IE in no add-on mode. 
This option specifically used to troubleshoot problems with browser add-ons. Works in IE 7 and later versions.

Note: __forceCreateProcessApi__ should to enabled in-order for command line arguments to work.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class ieTest {
    public static void main(String[] args) {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.useCreateProcessApiToLaunchIe();
        options.addCommandSwitches("-k");
        InternetExplorerDriver driver = new InternetExplorerDriver(options);
        try {
            driver.get("https://google.com/ncr");
            Capabilities caps = driver.getCapabilities();
            System.out.println(caps);
        } finally {
            driver.quit();
        }
    }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.add_argument('-private')
options.force_create_process_api = True
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

namespace ieTest {
 class Program {
  static void Main(string[] args) {
   InternetExplorerOptions options = new InternetExplorerOptions();
   options.ForceCreateProcessApi = true;
   options.BrowserCommandLineArguments = "-k";
   IWebDriver driver = new InternetExplorerDriver(options);
   driver.Url = "https://google.com/ncr";
  }
 }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
options = Selenium::WebDriver::IE::Options.new
options.force_create_process_api = true
options.add_argument('-k')
driver = Selenium::WebDriver.for(:ie, options: options)

begin
  # Navigate to URL
  driver.get 'https://google.com'
  puts(driver.capabilities.to_json)
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.addBrowserCommandSwitches('-k');
options.addBrowserCommandSwitches('-private');
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.useCreateProcessApiToLaunchIe()
    options.addCommandSwitches("-k")
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps)
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

### forceCreateProcessApi

Forces launching Internet Explorer 
using the CreateProcess API. The default value is false.

For IE 8 and above, this option requires the 
"TabProcGrowth" registry value to be set to 0.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class ieTest {
    public static void main(String[] args) {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.useCreateProcessApiToLaunchIe();
        InternetExplorerDriver driver = new InternetExplorerDriver(options);
        try {
            driver.get("https://google.com/ncr");
            Capabilities caps = driver.getCapabilities();
            System.out.println(caps);
        } finally {
            driver.quit();
        }
    }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.force_create_process_api = True
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

namespace ieTest {
 class Program {
  static void Main(string[] args) {
   InternetExplorerOptions options = new InternetExplorerOptions();
   options.ForceCreateProcessApi = true;
   IWebDriver driver = new InternetExplorerDriver(options);
   driver.Url = "https://google.com/ncr";
  }
 }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
options = Selenium::WebDriver::IE::Options.new
options.force_create_process_api = true
driver = Selenium::WebDriver.for(:ie, options: options)

begin
  # Navigate to URL
  driver.get 'https://google.com'
  puts(driver.capabilities.to_json)
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.useCreateProcessApiToLaunchIe()
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps)
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}
