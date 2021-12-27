---
title: "Legacy Firefox Driver"
linkTitle: "Firefox Driver"
weight: 4
description: >
    The legacy Firefox Driver was developed as a browser extension by the Selenium Developers.
    Firefox updated their security model, so it no longer works. You now need to use geckodriver.
aliases: [
"/documentation/legacy/firefox_driver/"
]
---

This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver) \
You can read more about [geckodriver](https://firefox-source-docs.mozilla.org/testing/geckodriver/).

## About

Firefox driver is included in the selenium-server-stanalone.jar available in the downloads. 
The driver comes in the form of an xpi (firefox extension) which is added to the firefox 
profile when you start a new instance of FirefoxDriver.

### Pros

* Runs in a real browser and supports Javascript
* Faster than the InternetExplorerDriver

### Cons

* Slower than the HtmlUnitDriver


### Important System Properties

The following system properties (read using `System.getProperty()` and set using `System.setProperty()` in Java code or the "`-DpropertyName=value`" command line flag) are used by the FirefoxDriver:

| **Property** | **What it means** |
|:-------------|:------------------|
| webdriver.firefox.bin | The location of the binary used to control firefox. |
| webdriver.firefox.marionette | Boolean value, if set on standalone-server will ignore any "marionette" desired capability requested and force firefox to use GeckoDriver (true) or Legacy Firefox Driver (false) |
| webdriver.firefox.profile | The name of the profile to use when starting firefox. This defaults to webdriver creating an anonymous profile |
| webdriver.firefox.useExisting | **Never use in production** Use a running instance of firefox if one is present |
| webdriver.firefox.logfile | Log file to dump firefox stdout/stderr to |

Normally the Firefox binary is assumed to be in the default location for your particular operating system:

| **OS** | **Expected Location of Firefox** |
|:-------|:---------------------------------|
| Linux  | firefox (found using "which")    |
| Mac    | /Applications/Firefox.app/Contents/MacOS/firefox-bin |
| Windows | %PROGRAMFILES%\Mozilla Firefox\firefox.exe |

By default, the Firefox driver creates an anonymous profile

### Running with firebug

Download the firebug xpi file from mozilla and start the profile as follows:
```
   File file = new File("firebug-1.8.1.xpi");
   FirefoxProfile firefoxProfile = new FirefoxProfile();
   firefoxProfile.addExtension(file);
   firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.8.1"); // Avoid startup screen

   WebDriver driver = new FirefoxDriver(firefoxProfile);
```


## FirefoxDriver Internals
(Previously located: https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver-Internals)


The FirefoxDriver is largely written in the form of a Firefox extension. Language bindings control the driver by connecting over a socket and sending commands (described in the JsonWireProtocol page) in UTF-8. The extension makes use of the XPCOM primitives offered by Firefox in order to do its work. The important thing to notice is that the command names map directly on to methods exposed on the "FirefoxDriver.prototype" in the javascript code.

### Working on the FirefoxDriver Code

Firstly, make sure that there's no old version of the FirefoxDriver installed:

* Start firefox's profile manager: `firefox -ProfileManager`
* Delete the existing WebDriver profile if there is one. Delete the files too (it's an option that's offered when you delete the profile in the profile manager)

Secondly, take a look at the [Mozilla Developer Center](http://developer.mozilla.org/en/docs/Extensions), particularly the section to do with [setting up an extension development environment](http://developer.mozilla.org/en/docs/Setting_up_extension_development_environment). You should now be ready to edit code. It's best to create a test around the area of code that you're working on, and to run this using the `SingleTestSuite`. The FirefoxDriver logs errors to Firefox's error console ("Tools->Error Console"), so if a test fails, that's a great place to start looking.

To actually log information to the console, use the "`Utils.dumpn()`" method in your javascript code. If you find that you'd like to examine an object in detail, use the "`Utils.dump()`" method, which will report which interfaces an object implements, as well as outputting as much information as it can to the console..

### Flow of Control: Starting Firefox

The following steps are performed when instantiating an instance of the FirefoxDriver:

1. Grab the "locking port"
