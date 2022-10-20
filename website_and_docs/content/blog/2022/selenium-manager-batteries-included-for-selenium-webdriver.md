---
title: "Selenium Manager: batteries included for Selenium WebDriver"
linkTitle: "Selenium Manager: batteries included for Selenium WebDriver"
date: 2022-10-20
tags: ["selenium", "manager", "chromedriver", "geckodriver", "edgedriver"]
categories: ["releases"]
author: Boni García ([@boni_gg](https://twitter.com/boni_gg))
description: >
   Selenium 4.6.0 is shipped with batteries included, thanks to Selenium Manager.
---

### Driver management in Selenium WebDriver
As you probably know, Selenium WebDriver (sometimes called simply _Selenium_, as its umbrella project) is a popular open source library that allows controlling web browsers (e.g., Chrome, Firefox, Edge, etc.) programmatically using different languages (such as Java, JavaScript, Python, Ruby, or C#). Internally, Selenium WebDriver uses the native support implemented by each browser to carry out the automation process. For this reason, we need to place a component called _driver_ (e.g., chromedriver, geckodriver, msedgedriver, etc.) between the script using the Selenium WebDriver API and the browser.

This fact, translated to source code, means that even an elementary _hello world_ Selenium script must take care of the proper driver for a given browser. For example, consider the following JUnit 5 test:

```
class ChromeTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @Test
    void test() {
        // TODO: Use `driver` to control Chrome
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
```

Although Java is not your favorite language, this code can be easily understood. In short, before the test logic, we create a `ChromeDriver` object to control a Chrome browser. After the test logic (empty in this example), we close the browser. Easy, right? Well, maybe not so much. If you try to run this code directly, you will face the following error:

```
java.lang.IllegalStateException: The path to the driver executable The path to the driver executable must be set by the webdriver.chrome.driver system property; for more information, see https://chromedriver.chromium.org/. The latest version can be downloaded from https://chromedriver.chromium.org/downloads
```

To solve it, you need to download the proper driver ([chromedriver](https://chromedriver.chromium.org/) in this case) and make it available for the test. But doing that manually, the real problem is the driver maintenance since Chrome (and the rest of the major browsers) is _evergreen_, which means that it automatically upgrades itself to the next stable version when available. This browser upgrade will affect the Selenium script since, eventually, a given driver release will no longer be compatible with the local browser. As a result, in the case of Chrome, the Selenium script will fail with the message "_this version of chromedriver only supports chrome version N_" (being N is the latest version of Chrome supported by a particular version of chromedriver). As reported periodically in [StackOverflow](https://stackoverflow.com/search?q=this+version+of+chromedriver+only+supports+Chrome), this is a recurrent problem for Selenium users.

### The driver _managers_
To solve this problem, several third-party projects of the Selenium ecosystem emerged. For instance, [WebDriverManager](https://bonigarcia.dev/webdrivermanager/) for Java, [webdriver-manager](https://pypi.org/project/webdriver-manager/) for Python, [webdrivers](https://github.com/titusfortner/webdrivers) for Ruby, or [WebDriverManager.Net](https://github.com/rosolko/WebDriverManager.Net) for C#. These tools are sometimes called _managers_ for Selenium WebDriver, and they carry out, to some extent, the driver management process in an automated way.

### Batteries included
The Selenium project launched the first official [Selenium survey](https://www.selenium.dev/blog/2021/selenium-survey-results/) on January 2021. One of the most evident results of this survey is that most Selenium users want to get rid of the problem of driver management. In other words, Selenium should have some kind of _batteries included_ in it. For this reason, the Selenium project created *Selenium Manager*. This first beta version of Selenium Manager was released on September 2022, and it has been built with the lessons learned from the development of WebDriverManager (first released in 2015).

Selenium Manager is a CLI (Command-Line Interface) tool developed in Rust. You can find its source code on the public GitHub [Selenium repository](https://github.com/SeleniumHQ/selenium/tree/trunk/rust). Moreover, Selenium Manager is built into the CI system of Selenium, i.e., GitHub Actions. If you want to play with it, you can download its development versions (compiled for Windows, Linux, and macOS) from [here](https://github.com/SeleniumHQ/selenium/actions/workflows/build-selenium-manager.yml). Once you have the proper binary of Selenium Manager on your machine, you can execute it from the shell as follows:

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

Now let's go back to the previous test. Try to run it, but use Selenium 4.6.0 (or above) this time. No more `The path to the driver executable must be set...` error. Boom.

### Next steps
The next challenge in the Selenium Manager roadmap is the automatic management (i.e., download and installation) of browsers (such as Chrome, Firefox, or Edge). And beyond that, the Selenium Manager aims to ease the development with Selenium as much as possible. One possible direction is to create scaffolding projects (for different languages, frameworks, etc.). Do you have an idea or feedback? Please, join the [Selenium Slack channel](https://join.slack.com/t/seleniumhq/shared_invite/zt-vv33sc0w-VKKQop3WDV_lfrLXGGHvDw) and share it with us!
