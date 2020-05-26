---
title: "Capabilities spécifiques du Driver"
weight: 2
---

## Firefox

### Définir les Capabilities à l'aide de "FirefoxOptions"

`FirefoxOptions` est la nouvelle façon 
de définir les capacités de Firefox
navigateur et doit généralement être 
utilisé de préférence à DesiredCapabilities.

{{< code-tab >}}
  {{< code-panel language="java" >}}
FirefoxOptions options = new FirefoxOptions();
options.addPreference("network.proxy.type", 0);
driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.firefox.options import Options
options = Options()
options.headless = True
driver = webdriver.Firefox(options=options)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var options = new FirefoxOptions();
options.Proxy.Kind = ProxyKind.Direct;
var driver = new FirefoxDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
opts = Selenium::WebDriver::Firefox::Options.new(args: ['-headless'])
driver = Selenium::WebDriver.for(:firefox, options: opts)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const { Builder } = require("selenium-webdriver");
const firefox = require('selenium-webdriver/firefox');

const options = new firefox.Options();
options.headless();
const driver = new Builder()
    .forBrowser('firefox')
    .setFirefoxOptions(options)
    .build();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val options = new FirefoxOptions()
options.addPreference("network.proxy.type", 0)
driver = RemoteWebDriver(options)
  {{< / code-panel >}}
{{< / code-tab >}}


### Définition d'un profil personnalisé

Il est possible de créer un profil 
personnalisé pour Firefox comme illustré ci-dessous.

{{< code-tab >}}
  {{< code-panel language="java" >}}
FirefoxProfile profile = new FirefoxProfile();
FirefoxOptions options = new FirefoxOptions();
options.setProfile(profile);
driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.firefox.firefox_profile import FirefoxProfile
options=Options()
firefox_profile = FirefoxProfile()
firefox_profile.set_preference("javascript.enabled", False)
options.profile = firefox_profile
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var options = new FirefoxOptions();
var profile = new FirefoxProfile();
options.Profile = profile;
var driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
profile = Selenium::WebDriver::Firefox::Profile.new
profile['browser.download.dir'] = "/tmp/webdriver-downloads"
options = Selenium::WebDriver::Firefox::Options.new(profile: profile)
driver = Selenium::WebDriver.for :firefox, options: options
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const { Builder } = require("selenium-webdriver");
const firefox = require('selenium-webdriver/firefox');

const options = new firefox.Options();
let profile = '/path to custom profile';
options.setProfile(profile);
const driver = new Builder()
    .forBrowser('firefox')
    .setFirefoxOptions(options)
    .build();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val options = FirefoxOptions()
options.profile = FirefoxProfile()
driver = RemoteWebDriver(options)
  {{< / code-panel >}}
{{< / code-tab >}}

## Internet Explorer

### fileUploadDialogTimeout

Dans certains environnements, Internet Explorer 
peut expirer lors de l'ouverture du
Boîte de dialogue Téléchargement de fichier. 
IEDriver a un délai d'expiration par 
défaut de 1000 ms, mais vous
peut augmenter le délai d'expiration 
à l'aide de la fonction fileUploadDialogTimeout.

{{< code-tab >}}
  {{< code-panel language="java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.waitForUploadDialogUpTo(Duration.ofSeconds(2));
WebDriver driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.file_upload_dialog_timeout = 2000
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var options = new InternetExplorerOptions();
options.FileUploadDialogTimeout = TimeSpan.FromMilliseconds(2000);
var driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.file_upload_dialog_timeout = 2000
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().fileUploadDialogTimeout(2000);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val options = InternetExplorerOptions()
options.waitForUploadDialogUpTo(Duration.ofSeconds(2))
val driver = RemoteWebDriver(options)
  {{< / code-panel >}}
{{< / code-tab >}}

### ensureCleanSession

When set to `true`, this capability clears the _Cache, 
Browser History and Cookies_ for all running instances 
of InternetExplorer including those started manually 
or by the driver. By default, it is set to `false`.

Using this capability will cause performance drop while 
launching the browser, as the driver will wait until the cache 
gets cleared before launching the IE browser.  

This capability accepts a Boolean value as parameter.

{{< code-tab >}}
  {{< code-panel language="java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.destructivelyEnsureCleanSession();
WebDriver driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ensure_clean_session = True
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var options = new InternetExplorerOptions();
options.EnsureCleanSession = true;
var driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ensure_clean_session = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ensureCleanSession(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val options = InternetExplorerOptions()
options.destructivelyEnsureCleanSession()
val driver = RemoteWebDriver(options)
  {{< / code-panel >}}
{{< / code-tab >}}

### ignoreZoomSetting

InternetExplorer driver expects the browser zoom level to be 100%, 
else the driver will throw an exception. This default behaviour 
can be disabled by setting the _ignoreZoomSetting_ to _true_.
 
This capability accepts a Boolean value as parameter.

{{< code-tab >}}
  {{< code-panel language="java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.ignoreZoomSettings();
WebDriver driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ignore_zoom_level = True
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var options = new InternetExplorerOptions();
options.IgnoreZoomLevel = true;
var driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ignore_zoom_level = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ignoreZoomSetting(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val options = InternetExplorerOptions()
options.ignoreZoomSettings()
val driver = RemoteWebDriver(options)
  {{< / code-panel >}}
{{< / code-tab >}}

### ignoreProtectedModeSettings

Whether to skip the _Protected Mode_ check while launching 
a new IE session.

If not set and _Protected Mode_ settings are not same for 
for all zones, an exception will be thrown by the driver.

If capability is set to `true`, tests may 
become flaky, unresponsive, or browsers may hang.
However, this is still by far a second-best choice, 
and the first choice should *always* be to actually 
set the Protected Mode settings of each zone manually. 
If a user is using this property, 
only a "best effort" at support will be given.

This capability accepts a Boolean value as parameter.
 
{{< code-tab >}}
  {{< code-panel language="java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.introduceFlakinessByIgnoringSecurityDomains();
WebDriver driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ignore_protected_mode_settings = True
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var options = new InternetExplorerOptions();
options.IntroduceInstabilityByIgnoringProtectedModeSettings = true;
var driver = new RemoteWebDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ignore_protected_mode_settings = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().introduceFlakinessByIgnoringProtectedModeSettings(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val options = InternetExplorerOptions()
options.introduceFlakinessByIgnoringSecurityDomains()
val driver = RemoteWebDriver(options)
  {{< / code-panel >}}
{{< / code-tab >}}

### silent

When set to `true`, this capability suppresses the
diagnostic output of the IEDriverServer.

This capability accepts a Boolean value as parameter.
 
{{< code-tab >}}
  {{< code-panel language="java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.setCapability("silent", true);
WebDriver driver = new InternetExplorerDriver(options);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.set_capability("silent", True)
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
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
  {{< / code-panel >}}
{{< / code-tab >}}

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

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.add_argument('-private')
options.force_create_process_api = True
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.addBrowserCommandSwitches('-k');
options.addBrowserCommandSwitches('-private');
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

fun main() {
    val options = InternetExplorerOptions();
    options.useCreateProcessApiToLaunchIe();
    options.addCommandSwitches("-k");
    val driver = InternetExplorerDriver(options);
    try {
        driver.get("https://google.com/ncr");
        val caps = driver.getCapabilities();
        println(caps);
    } finally {
        driver.quit();
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}

### forceCreateProcessApi

Forces launching Internet Explorer 
using the CreateProcess API. The default value is false.

For IE 8 and above, this option requires the 
"TabProcGrowth" registry value to be set to 0.

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.force_create_process_api = True
driver = webdriver.Ie(options=options)

# Navigate to url
driver.get("http://www.google.com")

driver.quit()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR to add code sample
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

fun main() {
    val options = InternetExplorerOptions();
    options.useCreateProcessApiToLaunchIe();
    val driver = InternetExplorerDriver(options);
    try {
        driver.get("https://google.com/ncr");
        val caps = driver.getCapabilities();
        println(caps)
    } finally {
        driver.quit();
    }
}

  {{< / code-panel >}}
{{< / code-tab >}}