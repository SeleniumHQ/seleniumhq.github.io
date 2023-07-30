---
title: "What's new in Selenium Manager with Selenium 4.11.0"
linkTitle: "What's new in Selenium Manager with Selenium 4.11.0"
date: 2027-07-31
tags: ["selenium", "manager", "chrome", "chromedriver", "cft"]
categories: ["releases"]
author: Boni García ([@boni_gg](https://twitter.com/boni_gg))
description: >
   Selenium 4.11.0 ships very relevant new features of Selenium Manager: support of Chrome for Testing (CfT) endpoints for chromedriver management and automated Chrome management (based also on CfT).
---

As of version [4.6.0](https://www.selenium.dev/blog/2022/introducing-selenium-manager/), all releases of Selenium (Java, JavaScript, Python, Ruby, and .Net) are shipped with **Selenium Manager**. [Selenium Manager](https://www.selenium.dev/documentation/selenium_manager/) is a binary tool (implemented in Rust) that provides automated driver management for Selenium. [Selenium Manager](https://www.selenium.dev/documentation/selenium_manager/) is still in beta, although it is becoming a relevant component of Selenium. 

So far, the main feature of Selenium Manager is called *automated driver management*. I use the term *management* for this feature (and not just *download*) since this process is broader and implies different steps:

1. Browser version discovery. Selenium Manager discovers the browser version (e.g., Chrome, Firefox, Edge) installed in the machine that executes Selenium. For this step, shell commands are used (e.g., `google-chrome --version`).
2. Driver version discovery. With the discovered browser version, the proper driver version is resolved. For this step, the online metadata maintained by the browser vendors (e.g., [chromedriver](https://chromedriver.chromium.org/downloads), [geckodriver](https://github.com/mozilla/geckodriver/releases), or [msedgedriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)) are used.
3. Driver download. With the resolved driver version, the driver URL is obtained, and with that URL, the driver artifact is downloaded, uncompressed, and stored locally in a cache folder (`~/.cache/selenium`). The next time the same driver is required, it is used from the cache.

### Entering Chrome for Testing (CfT)

The Chrome team started a very relevant initiative for the testing community in 2023: [Chrome for Testing (CfT)](https://googlechromelabs.github.io/chrome-for-testing/). CfT is a reduced release of Chrome primarily addressed to the testing use case.

One of the key differences between a regular Chrome release and CfT is that Chrome is *evergreen*, i.e., it upgrades automatically and silently to the next stable version when available. On the other hand, CfT is not *evergreen*, to allow pined browsers for testing. CfT releases are portable binaries (for Windows, Linux, and macOS) for different versions, including the stable, beta, dev, and canary channels. These releases can be programmatically discovered using the [CfT JSON endpoints](https://github.com/GoogleChromeLabs/chrome-for-testing#json-api-endpoints). 

As of version 114, the chromedriver team has stopped publishing the chromedriver releases and metadata using their traditional [chromedriver download repository](https://chromedriver.chromium.org/downloads). This way, and as of version 115, chromedriver releases can only be discovered programmatically using the abovementioned [CfT JSON endpoints](https://github.com/GoogleChromeLabs/chrome-for-testing#json-api-endpoints).

This change has been very relevant for the so-called driver managers. Luckily, Selenium already supports this new way of chromedriver discovery. The last version of Selenium Manager uses the CfT endpoints for chromedriver management. Therefore, if you are using Selenium Manager and Chrome, you must be updated to Selenium 4.11.0 to continue managing chromedriver automatically.

### Automated browser management

Moreover, as of Selenium 4.11.0, Selenium Manager implements automated browser management based on CfT. In other words, Selenium Manager uses the CfT endpoints to discover, download, and cache the different CfT releases, making them seamlessly available for Selenium. Let's consider the following line of code (this is Java, although the behavior is the same in all the Selenium binding languages):

```
WebDriver driver = new ChromeDriver();
```

So far, Selenium Manager manages chromedriver for us. This way, all the complexity related to CfT endpoints, driver versions, etc., is transparent, and we can rely on Selenium Manager to discover, download, and cache the proper driver for us.

In addition, and as a significant novelty starting on Selenium 4.11.0, if Chrome is not installed on the local machine when executing the previous line, the current stable CfT release is discovered, downloaded, and cached (in `~/.cache/selenium/chrome`). But there is more. In addition to the stable CfT version, Selenium Manager also allows downloading of older versions of CfT (starting in version 113, which is the first version published as CfT). 

Let's consider a simple example. The following lines will download CfT 114 and its proper chromedriver version. Then Selenium will start Chrome to be driven programmatically, as usual:

```
ChromeOptions options = new ChromeOptions();
options.setBrowserVersion("114");
WebDriver driver = new ChromeDriver(options);
```

But there is even more. In addition to fixed browser versions (e.g., `113`, `114`, `115`, etc.), we can use the following labels for browser versions:

- `stable`: Current CfT version.
- `beta`: Next version to stable.
- `dev`: Version in development at this moment.
- `canary`: Nightly build for developers.

### Under the hood

Selenium Manager is a CLI (command line interface) tool implemented in Rust and compiled for Windows, Linux, and macOS. The Selenium Manager binaries are shipped with each Selenium release. This way, each Selenium binding language invokes Selenium Manager to carry out the automated driver and browser management previously explained. 

For most users, Selenium Manager should work silently and transparently. But if you want to *play* with Selenium Manager or use it for your own use case (e.g., to download drivers or CfT releases), you can get the Selenium Manager binaries from the [Selenium main repository](https://github.com/SeleniumHQ/selenium/tree/trunk/common/manager). 

For instance, to manage Chrome/chromedriver, the Selenium Manager command we need to invoke from the shell is the following (notice that the flag `--debug` is optional, but it helps us to understand what Selenium Manager is doing):

```
> selenium-manager --browser chrome --debug
DEBUG   Checking chromedriver in PATH
DEBUG   Running command: chromedriver --version
DEBUG   Output: ""
DEBUG   chromedriver not found in PATH
DEBUG   chrome detected at C:\Program Files\Google\Chrome\Application\chrome.exe
DEBUG   Using shell command to find out chrome version
DEBUG   Running command: wmic datafile where name='C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe' get Version /value
DEBUG   Output: "\r\r\n\r\r\nVersion=115.0.5790.110\r\r\n\r\r\n\r\r\n\r"
DEBUG   Detected browser: chrome 115.0.5790.110
DEBUG   Reading metadata from https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json
DEBUG   Required driver: chromedriver 115.0.5790.102
DEBUG   Driver URL: https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/115.0.5790.102/win64/chromedriver-win64.zip
INFO    Driver path: C:\Users\boni\.cache\selenium\chromedriver\win64\115.0.5790.102\chromedriver.exe
INFO    Browser path: C:\Program Files\Google\Chrome\Application\chrome.exe
```

In this case, the local Chrome (in Windows) is detected by Selenium Manager. Then, using its version and the CfT endpoints, the proper chromedriver version (115, in this example) is downloaded to the local cache. Finally, Selenium Manager provides two results: i) the driver path (downloaded) and ii) the browser path (local).

Let's consider another example. Now we want to use Chrome beta. Therefore, we invoke Selenium Manager specifying that version label as follows (notice that the CfT beta is discovered, downloaded, and stored in the local cache):

```
> selenium-manager --browser chrome --debug --browser-version beta
DEBUG   Checking chromedriver in PATH
DEBUG   Running command: chromedriver --version
DEBUG   Output: ""
DEBUG   chromedriver not found in PATH
DEBUG   Checking chrome in PATH
DEBUG   Running command: where chrome
DEBUG   Output: ""
DEBUG   chrome not found in PATH
DEBUG   chrome has not been discovered in the system
DEBUG   Reading metadata from https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json
DEBUG   Required browser: chrome 116.0.5845.49
DEBUG   Downloading chrome 116.0.5845.49 from https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/116.0.5845.49/win64/chrome-win64.zip
DEBUG   chrome 116.0.5845.49 has been downloaded at C:\Users\boni\.cache\selenium\chrome\win64\116.0.5845.49\chrome.exe
DEBUG   Reading metadata from https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json
DEBUG   Required driver: chromedriver 116.0.5845.49
DEBUG   Driver URL: https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/116.0.5845.49/win64/chromedriver-win64.zip
INFO    Driver path: C:\Users\boni\.cache\selenium\chromedriver\win64\116.0.5845.49\chromedriver.exe
INFO    Browser path: C:\Users\boni\.cache\selenium\chrome\win64\116.0.5845.49\chrome.exe
```

### Troubleshooting in the bindings

If you want to get the Selenium Manager debugging information when using the Selenium bindings languages, you can enable it as follows (in Java):

```
Arrays.stream(Logger.getLogger("").getHandlers()).forEach(handler -> {
    handler.setLevel(Level.FINE);
});
Logger.getLogger(SeleniumManager.class.getName()).setLevel(Level.FINE);
```

For the equivalence in other bindings languages, visit the [Selenium troubleshooting page](https://www.selenium.dev/documentation/webdriver/troubleshooting/).

### Next steps

The following features to be implemented in Selenium Manager will continue the automated browser management mechanism, this time for **Firefox** and **Edge**. Stay tuned!

