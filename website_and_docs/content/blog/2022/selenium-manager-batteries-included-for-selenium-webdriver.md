---
title: "Selenium Manager: batteries included for Selenium WebDriver"
linkTitle: "Selenium Manager: batteries included for Selenium WebDriver"
date: 2022-10-26
tags: ["selenium", "manager", "chromedriver", "geckodriver", "edgedriver"]
categories: ["releases"]
author: Boni García ([@boni_gg](https://twitter.com/boni_gg))
description: >
   Get coding with Selenium faster than ever with a new beta feature in Selenium 4.6! Selenium wants to manage its infrastructure requirements, so you don't have to.
---

### Driver management in Selenium WebDriver
Selenium WebDriver (sometimes called simply _Selenium_, as its umbrella project) uses the native support implemented by each browser to carry out the automation process. For this reason, we need to place a component called _driver_ (e.g., chromedriver, geckodriver, msedgedriver, etc.) between the script using the Selenium WebDriver API and the browser. This fact, translated to source code, means that even an elementary Selenium script must take care of the proper driver for a given browser. For example, consider the following Java statement:

```
WebDriver driver = new ChromeDriver();
```

Although Java is not your favorite language, this line is straightforward. Basically, we create a `ChromeDriver` object to control a Chrome browser. Easy, right? Well, maybe not so much. If you try to run this code directly, you will face the following error:

```
java.lang.IllegalStateException: The path to the driver executable The path to the driver executable must be set by the webdriver.chrome.driver system property; for more information, see https://chromedriver.chromium.org/. The latest version can be downloaded from https://chromedriver.chromium.org/downloads
```

To solve it, you need to download the proper driver ([chromedriver](https://chromedriver.chromium.org/) in this case) and make it available for the Selenium WebDriver API. But doing that manually, the real problem is the driver maintenance since Chrome (and the rest of the major browsers) is _evergreen_, which means that it automatically upgrades itself to the next stable version when available. This browser upgrade will affect the Selenium script since, eventually, a given driver release will no longer be compatible with the local browser. As a result, in the case of Chrome, the Selenium script will fail with the message "_this version of chromedriver only supports chrome version N_" (being N is the latest version of Chrome supported by a particular version of chromedriver). As reported periodically in [StackOverflow](https://stackoverflow.com/search?q=this+version+of+chromedriver+only+supports+Chrome), this is a recurrent problem for Selenium users.

### Batteries included
The Selenium project launched the first official [Selenium survey](https://www.selenium.dev/blog/2021/selenium-survey-results/) on January 2021. One of the most evident results of this survey is that most Selenium users want to get rid of the problem of driver management. In other words, Selenium should have some kind of _batteries included_ in it. Another clue about this need was the fact that the [driver installation](https://www.selenium.dev/documentation/webdriver/getting_started/install_drivers/) page was by far the most visited in the Selenium documentation.

Thus, the Selenium project has created a tool to manage the required driver (and browser) infrastructure automatically: *Selenium Manager*. Selenium Manager is a CLI (Command-Line Interface) tool developed in Rust. We chose Rust as the language since the idea was to create a standalone application that resolves the driver management problem and then call Selenium Manager from the binding languages (Java, JavaScript, Python, Ruby, or C#). 

This first beta version of Selenium Manager was released on September 2022. You can find the Selenium Manager source code on the public GitHub [Selenium repository](https://github.com/SeleniumHQ/selenium/tree/trunk/rust). As a standalone application, it can be executed from the shell as follows:

```
$ ./selenium-manager --help
selenium-manager 1.0.0-M1
Automated driver management for Selenium

Usage: selenium-manager [OPTIONS]
Options:
  -b, --browser <BROWSER>
          Browser name (chrome, firefox, or edge) [default: ]
  -d, --driver <DRIVER>
          Driver name (chromedriver, geckodriver, or msedgedriver) [default: ]
  -v, --driver-version <DRIVER_VERSION>
          Driver version (e.g., 106.0.5249.61, 0.31.0, etc.) [default: ]
  -B, --browser-version <BROWSER_VERSION>
          Major browser version (e.g., 105, 106, etc.) [default: ]
  -D, --debug
          Display DEBUG messages
  -T, --trace
          Display TRACE messages
  -c, --clear-cache
          Clear driver cache
  -h, --help
          Print help information
  -V, --version
          Print version information
```

Selenium-Manager provides a robust mechanism to detect the version of the local browser to be controlled with Selenium WebDriver, download the proper driver, and make it available for Selenium WebDriver. For instance, the command required to manage chromedriver is the following:

```
$ ./selenium-manager --browser chrome
INFO	/home/boni/.cache/selenium/chromedriver/linux64/106.0.5249.61/chromedriver
```

If everything is correct, the last line contains the path to the driver. To understand what is happening under the hood, you can enable the `DEBUG` traces as follows:

```
$ ./selenium-manager --browser chrome --debug
DEBUG	Using shell command to find out chrome version
DEBUG	Running sh command: "google-chrome --version"
DEBUG	Output { status: ExitStatus(unix_wait_status(0)), stdout: "Google Chrome 106.0.5249.91 \n", stderr: "" }
DEBUG	The version of chrome is 106.0.5249.91
DEBUG	Detected browser: chrome 106
DEBUG	Reading chromedriver version from https://chromedriver.storage.googleapis.com/LATEST_RELEASE_106
DEBUG	starting new connection: https://chromedriver.storage.googleapis.com/
DEBUG	Required driver: chromedriver 106.0.5249.61
DEBUG	starting new connection: https://chromedriver.storage.googleapis.com/
DEBUG	File extracted to /home/boni/.cache/selenium/chromedriver/linux64/106.0.5249.61/chromedriver (13158208 bytes)
INFO	/home/boni/.cache/selenium/chromedriver/linux64/106.0.5249.61/chromedriver
```

### Seamless integration with Selenium
But the good thing is that you can use Selenium Manager effortlessly. Simply update your Selenium project to release `4.6.0`. Since that version, each official Selenium binding (Java, JavaScript, Python, Ruby, C++) is already shipped with _batteries included_. In other words, each Selenium binding uses Selenium Manager internally out of the box. This way, when the driver is not found, Selenium Manager is used to resolving the driver automatically.

Now let's go back to the previous `ChromeDriver` instantiation. Try to run it, but use Selenium 4.6.0 (or above) this time. No more `The path to the driver executable must be set...` error. Boom.

### Next steps
The next challenge in the Selenium Manager roadmap is the automatic management (i.e., download and installation) of browsers (such as Chrome, Firefox, or Edge). And beyond that, the Selenium Manager aims to ease the development with Selenium as much as possible. One possible direction is to create scaffolding projects (for different languages, frameworks, etc.). Do you have an idea or feedback? Please, join the [Selenium Slack channel](https://join.slack.com/t/seleniumhq/shared_invite/zt-vv33sc0w-VKKQop3WDV_lfrLXGGHvDw) and share it with us!
