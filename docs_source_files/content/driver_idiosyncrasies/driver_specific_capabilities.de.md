---
title: "Driver spezifische Capabilities"
weight: 2
---

## Firefox

### Definerte Capabilities in den `FirefoxOptions`

`FirefoxOptions` ist die neue Methode um Capabilities für den Firefoxbrowser 
zu definieren und sollte bevorzugt verwendet werden statt den DesiredCapabilities
and should generally be used in preference to DesiredCapabilities.

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


### Erstellen eines benutzerdefinierten Profils

Wie hier demonstriert ist es möglich ein benutzerdefiniertes 
Profil für Firefox zu erstellen.

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

### Timeout betreffend Dateiupload

Unter Umständen kann es vorkommen das der Internet Explorer in ein
Timout läuft während der Dateiupload-Dialog geöffnet wird. Der IEDriver
hat ein standardmässiges Timeout von 1000ms, jedoch dieses kann 
erhöht werden indem die capability mit dem Namen fileUploadDialogTimeout
definiert wird.

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

Ist die Capability auf `true` gesetzt, wird der _Cache, 
Browser History and Cookies_ für alle laufenden Instanzen des
InternetExplorer inklusive aller die manuell oder vom WebDriver 
gestartet werden. Standardmäßig ist der Wert mit _false_ vorbelegt.

Die Verwendung dieser Capability führt zu Performanceeinbußen
während des starten des Browsers, da der driver wartet bis
der Cache geleert wird bevor der InternetExplorer gestartet wird.

Es wird ein Wert vom Typ Boolean erwartet.

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

Der InternetExplorer erwartet das das Browser Zoomlevel auf 100% 
eingestellt ist, andernfalls wird eine Fehlermeldung (=Exception)
ausgelöst. Dieses Standardverhalten kann deaktiviert werden in dem
die Einstellung _ignoreZoomSetting_ auf _true_ gesetzt wird.

Die Capability erwartet einen Booleanwert als Parameter.

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

Legt fest ob die Prüfung des _Protected Mode_ beim Starten
einer neuen IE Sitzung übersprungen werden soll.

Der driver löst eine Exception aus falls der _Proteced Mode_
nicht definiert wurde oder nicht für alle Umgebungen gleich ist.

Wenn diese capability auf `true` gesetzt wird, können Tests
instabil werden, bzw. kann der Browser abstürzen oder nicht
reagieren. Diese Einstellung ist nicht zu bevorzugen und sollte
nur als zweite Wahl genutzt werden. Mit Sicherheit sollte
der Protected Mode für jede Zone *immer* manuell definiert werden.
Falls jemand diese Einstellung nutzt kann kein umfassender Support
garantiert werden.

Die capability erwartet einen Booleanwert als Parameter.
 
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

Ist diese capability auf `true` gesetzt, werden alle 
Diagnosemeldungen des IEDriverServer unterdrückt.

Als Parameter wird ein Booleanwert erwartet.
 
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
InternetExplorerOptions options = new InternetExplorerOptions();
options.AddAdditionalInternetExplorerOption("silent", true);
IWebDriver driver = new InternetExplorerDriver(options); 
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

Der Internet Explorer akzeptiert eine Reihe von Kommandozeilenparameter
die die Fehlersuche erleichtern und um den Browser zu konfigurieren.

Folgend sind einige der Kommandozeilenparameter angeführt

* _-private_ : Wird genutzt um den IE im Inkognitomodus zu starten. Diese Option wird ab IE Version 8 unterstützt.

* _-k_ : Startet den Internet Explorer im Kiosk Modus.  
Der Browser öffnet sich mit maximerten Fenster, indem die Navigationsleiste, das Adressfeld und die Statusbar verborgen sind. 

* _-extoff_ : Startet den IE ohne Addons.
Diese Option wird verwendet um Problemen mit Browser Addons vorzubeugen. Dies wird ab Version 7 unterstützt.  

Bemerkung: __forceCreateProcessApi__ sollte aktiviert werden damit die Kommandozeilenparameter unterstützt werden.

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
  {{< / code-panel >}}
{{< / code-tab >}}

### forceCreateProcessApi

Forciert beim Starten des Internet Explorers die Verwendung der 
CreateProcess API. Standardwert ist false (=deaktiviert).

Ab IE Version 8 ist für diese Einstellung es notwendig den 
"TabProcGrowth" Wert auf 0 zu setzen. 

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
  {{< / code-panel >}}
{{< / code-tab >}}