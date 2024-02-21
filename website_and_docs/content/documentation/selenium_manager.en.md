---
title: "Selenium Manager (Beta)"
linkTitle: "Selenium Manager"
weight: 3
description: >
    Selenium Manager is a command-line tool implemented in Rust that provides automated driver and browser management for Selenium. Selenium bindings use this tool by default, so you do not need to download it or add anything to your code or do anything else to use it.
---

## Motivation
***TL;DR:*** *Selenium Manager is the official driver manager of the Selenium project, and it is shipped out of the box with every Selenium release.*

Selenium uses the native support implemented by each browser to carry out the automation process. For this reason, Selenium users need to place a component called _driver_ (chromedriver, geckodriver, msedgedriver, etc.) between the script using the Selenium API and the browser. For many years, managing these drivers was a manual process for Selenium users. This way, they had to download the required driver for a browser (chromedriver for Chrome, geckodriver for Firefox, etc.) and place it in the `PATH` or export the driver path as a system property (Java, JavaScript, etc.). But this process was cumbersome and led to maintainability issues.

Let's consider an example. Imagine you manually downloaded the required chromedriver for driving your Chrome with Selenium. When you did this process, the stable version of Chrome was 113, so you downloaded chromedriver 113 and put it in your `PATH`. At that moment, your Selenium script executed correctly. But the *problem* is that Chrome is *evergreen*. This name refers to Chrome's ability to upgrade automatically and silently to the next stable version when available. This feature is excellent for end-users but potentially dangerous for browser automation. Let's go back to the example to discover it. Your local Chrome eventually updates to version 115. And that moment, your Selenium script is broken due to the incompatibility between the manually downloaded driver (113) and the Chrome version (115). Thus, your Selenium script fails with the following error message: *"session not created: This version of ChromeDriver only supports Chrome version 113"*.

This problem is the primary reason for the existence of the so-called *driver managers* (such as [WebDriverManager](https://bonigarcia.dev/webdrivermanager/) for Java, 
[webdriver-manager](https://pypi.org/project/webdriver-manager/) for Python, [webdriver-manager](https://www.npmjs.com/package/webdriver-manager) for JavaScript, [WebDriverManager.Net](https://github.com/rosolko/WebDriverManager.Net) for C#, and [webdrivers](https://github.com/titusfortner/webdrivers) for Ruby). All these projects were an inspiration and a clear sign that the community needed this feature to be built in Selenium. Thus, the Selenium project has created *Selenium Manager*, the official driver manager for Selenium, shipped out of the box with each Selenium release as of version 4.6.

## Usage
***TL;DR:*** *Selenium Manager is used by the Selenium bindings when the drivers (chromedriver, geckodriver, etc.) are unavailable.*

Driver management through Selenium Manager is *opt-in* for the Selenium bindings. Thus, users can continue managing their drivers manually (putting the driver in the `PATH` or using system properties) or rely on a third-party *driver manager* to do it automatically. Selenium Manager only operates as a fallback: if no driver is provided, Selenium Manager will come to the rescue.

Selenium Manager is a CLI (command line interface) tool implemented in Rust to allow cross-platform execution and compiled for Windows, Linux, and macOS. The Selenium Manager binaries are shipped with each Selenium release. This way, each Selenium binding language invokes Selenium Manager to carry out the automated driver and browser management explained in the following sections.

## Automated driver management
***TL;DR:*** *Selenium Manager automatically discovers, downloads, and caches the drivers required by Selenium when these drivers are unavailable.*

The primary feature of Selenium Manager is called *automated driver management*. Let's consider an example to understand it. Suppose we want to driver Chrome with Selenium (see the doc about how to [start a session with Selenium](https://www.selenium.dev/documentation/webdriver/getting_started/first_script/#1-start-the-session)). Before the session begins, and when the driver is unavailable, Selenium Manager manages chromedriver for us. We use the term *management* for this feature (and not just *download*) since this process is broader and implies different steps:

1. Browser version discovery. Selenium Manager discovers the browser version (e.g., Chrome, Firefox, Edge) installed in the machine that executes Selenium. This step uses shell commands (e.g., `google-chrome --version`).
2. Driver version discovery. With the discovered browser version, the proper driver version is resolved. For this step, the online metadata/endpoints maintained by the browser vendors (e.g., [chromedriver](https://chromedriver.chromium.org/downloads), [geckodriver](https://github.com/mozilla/geckodriver/releases), or [msedgedriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)) are used.
3. Driver download. The driver URL is obtained with the resolved driver version; with that URL, the driver artifact is downloaded, uncompressed, and stored locally.
4. Driver cache. Uncompressed driver binaries are stored in a local cache folder (`~/.cache/selenium`). The next time the same driver is required, it will be used from there if the driver is already in the cache.

## Automated browser management
***TL;DR:*** *Selenium Manager automatically discovers, downloads, and caches the browsers driven with Selenium (Chrome, Firefox, and Edge) when these browsers are not installed in the local system.*

As of Selenium 4.11.0, Selenium Manager also implements *automated browser management*. With this feature, Selenium Manager allows us to discover, download, and cache the different browser releases, making them seamlessly available for Selenium. Internally, Selenium Manager uses an equivalent management procedure explained in the section before, but this time, for browser releases.

The browser automatically managed by Selenium Manager are:

- Chrome. Based on [Chrome for Testing (CfT)](https://googlechromelabs.github.io/chrome-for-testing/), as of Selenium 4.11.0.
- Firefox. Based on [public Firefox releases](https://ftp.mozilla.org/pub/firefox/releases/), as of Selenium 4.12.0.
- Edge. Based on [Edge downloads](https://www.microsoft.com/en-us/edge/download), as of Selenium 4.14.0.

Let's consider again the typical example of driving Chrome with Selenium. And this time, suppose Chrome is not installed on the local machine when [starting a new session](https://www.selenium.dev/documentation/webdriver/getting_started/first_script/#1-start-the-session)). In that case, the current stable CfT release will be discovered, downloaded, and cached (in `~/.cache/selenium/chrome`) by Selenium Manager. 

But there is more. In addition to the stable browser version, Selenium Manager also allows downloading older browser versions (in the case of CfT, starting in version 113, the first version published as CfT). To set a browser version with Selenium, we use a browser option called [browserVersion](https://www.selenium.dev/documentation/webdriver/drivers/options/#browserversion).

Let's consider another simple example. Suppose we set `browserVersion` to `114` using [Chrome options](https://www.selenium.dev/documentation/webdriver/browsers/chrome/). In this case, Selenium Manager will check if Chrome 114 is already installed. If it is, it will be used. If not, Selenium Manager will manage (i.e., discover, download, and cache) CfT 114. And in either case, the chromedriver is also managed. Finally, Selenium will start Chrome to be driven programmatically, as usual.

But there is even more. In addition to fixed browser versions (e.g., `113`, `114`, `115`, etc.), we can use the following labels for `browserVersion`:

- `stable`: Current CfT version.
- `beta`: Next version to stable.
- `dev`: Version in development at this moment.
- `canary`: Nightly build for developers.
- `esr`: Extended Support Release (only for Firefox).

When these labels are specified, Selenium Manager first checks if a given browser is already installed (`beta`, `dev`, etc.), and when it is not detected, the browser is automatically managed.

### Edge in Windows
Automated Edge management by Selenium Manager in Windows is different from other browsers. Both Chrome and Firefox (and Edge in macOS and Linux) are downloaded automatically to the local cache (`~/.cache/selenium`) by Selenium Manager. Nevertheless, the same cannot be done for Edge in Windows. The reason is that the Edge installer for Windows is distributed as a Microsoft Installer (MSI) file, designed to be executed with administrator rights. This way, when Edge is attempted to be installed with Selenium Manager in Windows with a non-administrator session, a warning message will be displayed by Selenium Manager as follows:

```
edge can only be installed in Windows with administrator permissions
```

Therefore, administrator permissions are required to install Edge in Windows automatically through Selenium Manager, and Edge is eventually installed in the usual program files folder (e.g., `C:\Program Files (x86)\Microsoft\Edge`).

## Data collection
Selenium Manager will report anonymised usage [statistics](https://plausible.io/privacy-focused-web-analytics) to [Plausible](https://plausible.io/manager.selenium.dev). This allows the Selenium team to understand more about how Selenium is being used so that we can better focus our development efforts. The data being collected is:

| Data | Purpose |
| -----|---------|
| Selenium version | This allows the Selenium developers to safely deprecate and remove features, as well as determine which new features may be available to you |
| Language binding | Programming language used to execute Selenium scripts (Java, JavaScript, Python, .Net, Ruby) |
| OS and architecture Selenium Manager is running on | The Selenium developers can use this information to help prioritise bug reports, and to identify if there are systemic OS-related issues |
| Browser and browser version | Helping for prioritising bug reports |
| Rough geolocation | Derived from the IP address you connect from. This is useful for determining where we need to focus our documentation efforts |

Selenium Manager sends these data to Plausible once a day. This period is based on the TTL value (see [configuration](https://www.selenium.dev/documentation/selenium_manager/#configuration)).

### Opting out of data collection
**Data collection is on by default.** To disable it, set the `SE_AVOID_STATS` environment variable to `true`. You may also disable data collection in the configuration file (see below) by setting `avoid-stats = true`.

## Configuration
***TL;DR:*** *Selenium Manager should work silently and transparently for most users. Nevertheless, there are scenarios (e.g., to specify a custom cache path or setup globally a proxy) where custom configuration can be required.*

Selenium Manager is a CLI tool. Therefore, under the hood, the Selenium bindings call Selenium Manager by invoking shell commands. Like any other CLI tool, arguments can be used to specify specific capabilities in Selenium Manager. The different arguments supported by Selenium Manager can be checked by running the following command:

```
$ ./selenium-manager --help
```

In addition to CLI arguments, Selenium Manager allows two additional mechanisms for configuration:

- Configuration file. Selenium Manager uses a file called `se-config.toml` located in the Selenium cache (by default, at `~/.cache/selenium`) for custom configuration values. This TOML file contains a key-value collection used for custom configuration.
- Environmental variables. Each configuration key has its equivalence in environmental variables by converting each key name to uppercase, replacing the dash symbol (`-`) with an underscore (`_`), and adding the prefix `SE_`.

The configuration file is honored by Selenium Manager when it is present, and the corresponding CLI parameter is not specified. Besides, the environmental variables are used when neither of the previous options (CLI arguments and configuration file) is specified. In other words, the order of preference for Selenium Manager custom configuration is as follows:

1. CLI arguments.
2. Configuration file.
3. Environment variables.

Notice that the Selenium bindings use the CLI arguments to specify configuration values, which in turn, are defined in each binding using [browser options](https://www.selenium.dev/documentation/webdriver/drivers/options/).

The following table summarizes all the supported arguments supported by Selenium Manager and their correspondence key in the configuration file and environment variables.

| CLI argument| Configuration file | Env variable | Description |
|-------------|--------------------|--------------|-------------|
|`--browser BROWSER`|`browser = "BROWSER"`|`SE_BROWSER=BROWSER`|Browser name: `chrome`, `firefox`, `edge`, `iexplorer`, `safari`, `safaritp`, or `webview2`|
|`--driver <DRIVER>`|`driver = "DRIVER"`|`SE_DRIVER=DRIVER`|Driver name: `chromedriver`, `geckodriver`, `msedgedriver`, `IEDriverServer`, or `safaridriver`|
|`--browser-version <BROWSER_VERSION>`|`browser-version = "BROWSER_VERSION"`|`SE_BROWSER_VERSION=BROWSER_VERSION`|Major browser version (e.g., `105`, `106`, etc. Also: `beta`, `dev`, `canary` -or `nightly`-, and `esr` -in Firefox- are accepted)|
|`--driver-version <DRIVER_VERSION>`|`driver-version = "DRIVER_VERSION"`|`SE_DRIVER_VERSION=DRIVER_VERSION`|Driver version (e.g., `106.0.5249.61, 0.31.0`, etc.)|
|`--browser-path <BROWSER_PATH>`|`browser-path = "BROWSER_PATH"`|`SE_BROWSER_PATH=BROWSER_PATH`|Browser path (absolute) for browser version detection (e.g., `/usr/bin/google-chrome`, `/Applications/Google Chrome.app/Contents/MacOS/Google Chrome`, `C:\Program Files\Google\Chrome\Application\chrome.exe`)|
|`--driver-mirror-url <DRIVER_MIRROR_URL>`|`driver-mirror-url = "DRIVER_MIRROR_URL"`|`SE_DRIVER_MIRROR_URL=DRIVER_MIRROR_URL`|Mirror URL for driver repositories|
|`--browser-mirror-url <BROWSER_MIRROR_URL>`|`browser-mirror-url = "BROWSER_MIRROR_URL"`|`SE_BROWSER_MIRROR_URL=BROWSER_MIRROR_URL`|Mirror URL for browser repositories|
|`--output <OUTPUT>`|`output = "OUTPUT"`|`SE_OUTPUT=OUTPUT`|Output type: `LOGGER` (using `INFO`, `WARN`, etc.), `JSON` (custom JSON notation), or `SHELL` (Unix-like). Default: `LOGGER`|
|`--os <OS>`|`os = "OS"`|`SE_OS=OS`|Operating system for drivers and browsers (i.e., `windows`, `linux`, or `macos`)|
|`--arch <ARCH>`|`arch = "ARCH"`|`SE_ARCH=ARCH`|System architecture for drivers and browsers (i.e., `x32`, `x64`, or `arm64`)|
|`--proxy <PROXY>`|`proxy = "PROXY"`|`SE_PROXY=PROXY`|HTTP proxy for network connection (e.g., `myproxy:port`, `myuser:mypass@myproxy:port`)|
|`--timeout <TIMEOUT>`|`timeout = TIMEOUT`|`SE_TIMEOUT=TIMEOUT`|Timeout for network requests (in seconds). Default: `300`|
|`--offline`|`offline = true`|`SE_OFFLINE=true`|Offline mode (i.e., disabling network requests and downloads)|
|`--force-browser-download`|`force-browser-download = true`|`SE_FORCE_BROWSER_DOWNLOAD=true`|Force to download browser, e.g., when a browser is already installed in the system, but you want Selenium Manager to download and use it|
|`--avoid-browser-download`|`avoid-browser-download = true`|`SE_AVOID_BROWSER_DOWNLOAD=true`|Avoid to download browser, e.g., when a browser is supposed to be downloaded by Selenium Manager, but you prefer to avoid it|
|`--debug`|`debug = true`|`SE_DEBUG=true`|Display `DEBUG` messages|
|`--trace`|`trace = true`|`SE_TRACE=true`|Display `TRACE` messages|
|`--cache-path <CACHE_PATH>`|`cache-path="CACHE_PATH"`|`SE_CACHE_PATH=CACHE_PATH`|Local folder used to store downloaded assets (drivers and browsers), local metadata, and configuration file. See next section for details. Default: `~/.cache/selenium`. For Windows paths in the TOML configuration file, double backslashes are required (e.g., `C:\\custom\\cache`).|
|`--ttl <TTL>`|`ttl = TTL`|`SE_TTL=TTL`|Time-to-live in seconds. See next section for details. Default: `3600` (1 hour)|
|`--language-binding <LANGUAGE>`|`language-binding = "LANGUAGE"`|`SE_LANGUAGE_BINDING=LANGUAGE`|Language that invokes Selenium Manager (e.g., Java, JavaScript, Python, DotNet, Ruby)|
|`--avoid-stats`|`avoid-stats = true`|`SE_AVOID_STATS=true`|Avoid sends usage statistics to plausible.io. Default: `false`|

In addition to the configuration keys specified in the table before, there are some special cases, namely:

- Browser version. In addition to `browser-version`, we can use the specific configuration keys to specify custom versions per supported browser. This way, the keys `chrome-version`, `firefox-version`, `edge-version`, etc., are supported. The same applies to environment variables (i.e., `SE_CHROME_VERSION`, `SE_FIREFOX_VERSION`, `SE_EDGE_VERSION`, etc.).
- Driver version. Following the same pattern, we can use `chromedriver-version`, `geckodriver-version`,  `msedgedriver-version`,  etc. (in the configuration file), and `SE_CHROMEDRIVER_VERSION`, `SE_GECKODRIVER_VERSION`, `SE_MSEDGEDRIVER_VERSION`,  etc. (as environment variables).
- Browser path. Following the same pattern, we can use `chrome-path`, `firefox-path`,  `edge-path`,  etc. (in the configuration file), and `SE_CHROME_PATH`, `SE_FIREFOX_PATH`, `SE_EDGE_PATH`,  etc. (as environment variables). The Selenium bindings also allow to specify a custom location of the browser path using options, namely: [Chrome](https://www.selenium.dev/documentation/webdriver/browsers/chrome/#start-browser-in-a-specified-location)), [Edge](https://www.selenium.dev/documentation/webdriver/browsers/edge/#start-browser-in-a-specified-location), or [Firefox](https://www.selenium.dev/documentation/webdriver/browsers/firefox/#start-browser-in-a-specified-location).
- Driver mirror. Following the same pattern, we can use `chromedriver-mirror-url`, `geckodriver-mirror-url`,  `msedgedriver-mirror-url`,  etc. (in the configuration file), and `SE_CHROMEDRIVER_MIRROR_URL`, `SE_GECKODRIVER_MIRROR_URL`, `SE_MSEDGEDRIVER_MIRROR_URL`,  etc. (as environment variables).
- Browser mirror. Following the same pattern, we can use `chrome-mirror-url`, `firefox-mirror-url`,  `edge-mirror-url`,  etc. (in the configuration file), and `SE_CHROME_MIRROR_URL`, `SE_FIREFOX_MIRROR_URL`, `SE_EDGE_MIRROR_URL`,  etc. (as environment variables).

## Caching
***TL;DR:*** *The drivers and browsers managed by Selenium Manager are stored in a local folder (`~/.cache/selenium`).*

The cache in Selenium Manager is a local folder (`~/.cache/selenium` by default) in which the downloaded assets (drivers and browsers) are stored. For the sake of performance, when a driver or browser is already in the cache (i.e., there is a *cache hint*), Selenium Manager uses it from there.

In addition to the downloaded drivers and browsers, two additional files live in the cache's root:

- Configuration file (`se-config.toml`). This file is optional and, as explained in the previous section, allows to store custom configuration values for Selenium Manager. This file is maintained by the end-user and read by Selenium Manager.
- Metadata file (`se-metadata.json`). This file contains versions discovered by Selenium Manger making network requests (e.g., using the [CfT JSON endpoints](https://github.com/GoogleChromeLabs/chrome-for-testing#json-api-endpoints)) and the time-to-live (TTL) in which they are valid. Selenium Manager automatically maintains this file.

The TTL in Selenium Manager is inspired by the TTL for DNS, a well-known mechanism that refers to how long some values are cached before they are automatically refreshed. In the case of Selenium Manager, these values are the versions found by making network requests for driver and browser version discovery. By default, the TTL is `3600` seconds (i.e., 1 hour) and can be tuned using configuration values or disabled by setting this configuration value to `0`.

The TTL mechanism is a way to improve the overall performance of Selenium. It is based on the fact that the discovered driver and browser versions (e.g., the proper chromedriver version for Chrome 115 is 115.0.5790.170) will likely remain the same in the short term. Therefore, the discovered versions are written in the metadata file and read from there instead of making the same consecutive network request. This way, during the driver version discovery (step 2 of the automated driver management process previously introduced), Selenium Manager first reads the file metadata. When a *fresh* resolution (i.e., a driver/browser version valid during a TTL) is found, that version is used (saving some time in making a new network request). If not found or the TTL has expired, a network request is made, and the result is stored in the metadata file.

Let's consider an example. A Selenium binding asks Selenium Manager to resolve chromedriver. Selenium Manager detects that Chrome 115 is installed, so it makes a network request to the CfT endpoints to discover the proper chromedriver version (115.0.5790.170, at that moment). This version is stored in the metadata file and considered valid during the next hour (TTL). If Selenium Manager is asked to resolve chromedriver during that time (which is likely to happen in the execution of a test suite), the chromedriver version is discovered by reading the metadata file instead of making a new request to the CfT endpoints. After one hour, the chromedriver version stored in the cache will be considered as *stale*, and Selenium Manager will refresh it by making a new network request to the corresponding endpoint.

Selenium Manager includes two additional arguments two handle the cache, namely:

- `--clear-cache`: To remove the cache folder.
- `--clear-metadata`: To remove the metadata file.

## Versioning
Selenium Manager follows the same versioning schema as Selenium. Nevertheless, we use the major version 0 for Selenium Manager releases because it is still in beta. For example, the Selenium Manager binaries shipped with Selenium 4.12.0 corresponds to version 0.4.12.

## Getting Selenium Manager
For most users, direct interaction with Selenium Manager is not required since the Selenium bindings use it internally. Nevertheless, if you want to *play* with Selenium Manager or use it for your use case involving driver or browser management, you can get the Selenium Manager binaries in different ways:

- From the Selenium repository. The Selenium Manager source code is stored in the main Selenium repo under the folder [rust](https://github.com/SeleniumHQ/selenium/tree/trunk/rust). Moreover, you can find the compiled versions for Windows, Linux, and macOS in the [Selenium Manager Artifacts](https://github.com/SeleniumHQ/selenium_manager_artifacts) repo. The stable Selenium Manager binaries (i.e., those distributed in the latest stable Selenium version) are linked in this [file](https://github.com/SeleniumHQ/selenium/blob/trunk/common/selenium_manager.bzl).
- From the build workflow. Selenium Manager is compiled using a [GitHub Actions workflow]((https://github.com/SeleniumHQ/selenium/actions/workflows/ci-rust.yml)). This workflow creates binaries for Windows, Linux, and macOS. You can download these binaries from these workflow executions.
- From the cache. As of version 4.15.0 of the Selenium Java bindings, the Selenium Manager binary is extracted and copied to the cache folder. For instance, the Selenium Manager binary shipped with Selenium 4.15.0 is stored in the folder `~/.cache/selenium/manager/0.4.15`).

## Examples
Let's consider a typical example: we want to manage chromedriver automatically. For that, we invoke Selenium Manager as follows (notice that the flag `--debug` is optional, but it helps us to understand what Selenium Manager is doing):

```
$ ./selenium-manager --browser chrome --debug
DEBUG   chromedriver not found in PATH
DEBUG   chrome detected at C:\Program Files\Google\Chrome\Application\chrome.exe
DEBUG   Running command: wmic datafile where name='C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe' get Version /value
DEBUG   Output: "\r\r\n\r\r\nVersion=116.0.5845.111\r\r\n\r\r\n\r\r\n\r"
DEBUG   Detected browser: chrome 116.0.5845.111
DEBUG   Discovering versions from https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json
DEBUG   Required driver: chromedriver 116.0.5845.96
DEBUG   Downloading chromedriver 116.0.5845.96 from https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/116.0.5845.96/win64/chromedriver-win64.zip
INFO    Driver path: C:\Users\boni\.cache\selenium\chromedriver\win64\116.0.5845.96\chromedriver.exe
INFO    Browser path: C:\Program Files\Google\Chrome\Application\chrome.exe
```

In this case, the local Chrome (in Windows) is detected by Selenium Manager. Then, using its version and the CfT endpoints, the proper chromedriver version (115, in this example) is downloaded to the local cache. Finally, Selenium Manager provides two results: i) the driver path (downloaded) and ii) the browser path (local).

Let's consider another example. Now we want to use Chrome beta. Therefore, we invoke Selenium Manager specifying that version label as follows (notice that the CfT beta is discovered, downloaded, and stored in the local cache):

```
$ ./selenium-manager --browser chrome --browser-version beta --debug
DEBUG   chromedriver not found in PATH
DEBUG   chrome not found in PATH
DEBUG   chrome beta not found in the system
DEBUG   Discovering versions from https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json
DEBUG   Required browser: chrome 117.0.5938.22
DEBUG   Downloading chrome 117.0.5938.22 from https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/117.0.5938.22/win64/chrome-win64.zip
DEBUG   chrome 117.0.5938.22 has been downloaded at C:\Users\boni\.cache\selenium\chrome\win64\117.0.5938.22\chrome.exe
DEBUG   Discovering versions from https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json
DEBUG   Required driver: chromedriver 117.0.5938.22
DEBUG   Downloading chromedriver 117.0.5938.22 from https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/117.0.5938.22/win64/chromedriver-win64.zip
INFO    Driver path: C:\Users\boni\.cache\selenium\chromedriver\win64\117.0.5938.22\chromedriver.exe
INFO    Browser path: C:\Users\boni\.cache\selenium\chrome\win64\117.0.5938.22\chrome.exe
```

## Selenium Grid
Selenium Manager allows you to configure the drivers automatically when setting up Selenium Grid. To that aim, you need to include the argument `--selenium-manager true` in the command to start Selenium Grid. For more details, visit the [Selenium Grid starting page](https://www.selenium.dev/documentation/grid/getting_started/).

Moreover, Selenium Manager also allows managing Selenium Grid releases automatically. For that, the argument `--grid` is used as follows:

```
$ ./selenium-manager --grid
```

After this command, Selenium Manager discovers the latest version of Selenium Grid, storing the `selenium-server.jar` in the local cache.

Optionally, the argument `--grid` allows to specify a Selenium Grid version (`--grid <GRID_VERSION>`).

## Known Limitations

### Connectivity issues
Selenium Manager requests remote endpoints (like Chrome for Testing (CfT), among others) to discover and download drivers and browsers from online repositories. When this operation is done in a corporate environment with a proxy or firewall, it might lead to connectivity problems like the following:

```
error sending request for url (https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json)
```

```
error trying to connect: dns error: failed to lookup address information
```

```
error trying to connect: An existing connection was forcibly closed by the remote host. (os error 10054)
```

When that happens, consider the following solutions:

- Use the proxy capabilities of Selenium (see [documentation](https://www.selenium.dev/documentation/webdriver/drivers/options/#proxy)). Alternatively, use the environment variable `SE_PROXY` to set the proxy URL or use the configuration file (see [configuration](https://www.selenium.dev/documentation/selenium_manager/#configuration)).
- Review your network setup to enable the remote requests and downloads required by Selenium Manager.

### Custom package managers
If you are using a Linux package manager (Anaconda, snap, etc) that requires a specific driver be used for your browsers,
you'll need to either specify the 
[driver location](https://www.selenium.dev/documentation/webdriver/drivers/service/#driver-location), 
the [browser location](https://www.selenium.dev/documentation/webdriver/browsers/chrome/#start-browser-in-a-specified-location),
or both, depending on the requirements.

### Alternative architectures
Selenium supports all five architectures managed by Google's Chrome for Testing, and all six drivers provided for Microsoft Edge.

Each release of the Selenium bindings comes with three separate Selenium Manager binaries — one for Linux, Windows, and Mac. 
* The Mac version supports both x64 and aarch64 (Intel and Apple).
* The Windows version should work for both x86 and x64 (32-bit and 64-bit OS). 
* The Linux version has only been verified to work for x64.

Reasons for not supporting more architectures:
1. Neither Chrome for Testing nor Microsoft Edge supports additional architectures, so Selenium Manager would need to 
manage something unofficial for it to work. 
2. We currently build the binaries from existing GitHub actions runners, which do not support these architectures
3. Any additional architectures would get distributed with all Selenium releases, increasing the total build size

If you are running Linux on arm64/aarch64, 32-bit architecture, or a Raspberry Pi, Selenium Manager will not work for you.
The biggest issue for people is that they used to get custom-built drivers and put them on PATH and have them work.
Now that Selenium Manager is responsible for locating drivers on PATH, this approach no longer works, and users
need to use a `Service` class and [set the location directly](https://www.selenium.dev/documentation/webdriver/drivers/service/#driver-location).
There are a number of advantages to having Selenium Manager look for drivers on PATH instead of managing that logic 
in each of the bindings, so that's currently a trade-off we are comfortable with.

However, as of Selenium 4.13.0, the Selenium bindings allow locating the Selenium Manager binary using an environment variable called `SE_MANAGER_PATH`. If this variable is set, the bindings will use its value as the Selenium Manager path in the local filesystem. This feature will allow users to provide a custom compilation of Selenium Manager, for instance, if the default binaries (compiled for Windows, Linux, and macOS) are incompatible with a given system (e.g., ARM64 in Linux).

### Browser dependencies
When automatically managing browsers in Linux, Selenium Manager relies on the releases published by the browser vendors (i.e., Chrome, Firefox, and Edge). These releases are portable in most cases. Nevertheless, there might be cases in which existing libraries are required. In Linux, this problem might be experienced when trying to run Firefox, e.g., as follows:

```
libdbus-glib-1.so.2: cannot open shared object file: No such file or directory
Couldn't load XPCOM.
```

If that happens, the solution is to install that library, for instance, as follows:

```
sudo apt-get install libdbus-glib-1-2
```

A similar issue might happen when trying to execute Chrome for Testing in Linux:

```
error while loading shared libraries: libatk-1.0.so.0: cannot open shared object file: No such file or directory
```

In this case, the library to be installed is the following:

```
sudo apt-get install libatk-bridge2.0-0
```

## Roadmap
You can trace the work in progress in the [Selenium Manager project dashboard](https://github.com/orgs/SeleniumHQ/projects/5). Moreover, you can check the new features shipped with each Selenium Manager release in its [changelog file](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md).
