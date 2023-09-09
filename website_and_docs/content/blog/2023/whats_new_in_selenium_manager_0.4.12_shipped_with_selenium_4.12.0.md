---
title: "What's new in Selenium Manager 0.4.12, shipped with Selenium 4.12.0"
linkTitle: "What's new in Selenium Manager 0.4.12, shipped with Selenium 4.12.0"
date: 2023-08-28
tags: ["selenium", "manager", "firefox"]
categories: ["releases"]
author: Boni García ([@boni_gg](https://twitter.com/boni_gg))
description: >
   Selenium Manager 0.4.12 is shipped with Selenium 4.12.0. This release aims to stabilize the features provided so far, delivering a new relevant characteristic: automated browser management for Firefox.
---

A new release of Selenium Manager is out. For this release, we made a relevant decision concerning the Selenium Manager versioning format. From now on, Selenium Manager will follow the same version as Selenium. Nevertheless, since Selenium Manager is still in beta, its major version is *0*. Thus, Selenium **4.12.0** is shipped with Selenium Manager **0.4.12**.

First, we made a substantial effort to stabilize the already available features on Selenium Manager. This way, the version includes several bug-fixing related to automated driver management or caching. You can find the details of the changes implemented in Selenium Manager 0.4.12 in the (newly created) [changelog file](https://github.com/SeleniumHQ/selenium/blob/trunk/rust/CHANGELOG.md). 

Besides, for this release, we made a significant update to the [documentation page of Selenium Manager](https://www.selenium.dev/documentation/selenium_manager/). This page contains all the fine-grained information related to automated driver and browser management, configuration, etc. Also, it has several **TL;DR** summarizing the main ideas for the eager reader.

### Automated Firefox management
After shipping automated browser management based on [Chrome for Testing](https://googlechromelabs.github.io/chrome-for-testing/) on the previous release, Selenium Manager 0.4.12 continues the job by providing automated **Firefox** management. This way, Selenium Manager 0.4.12 allows us to manage the different Firefox releases (for Windows, Linux, and macOS), making them seamlessly available for Selenium.

The procedure is the same as with Chrome. When Firefox is unavailable in the machine running Selenium, it is automatically discovered, downloaded, and cached by Selenium. If no version is specified, the latest stable Firefox release is managed by Selenium Manager. Besides, the bindings can use a browser option called [browserVersion](https://www.selenium.dev/documentation/webdriver/drivers/options/#browserversion) to specify a particular Firefox release (e.g., 114, 115, etc.). Finally, the label `stable` allows us to manage the current stable Firefox release explicitly, and the labels `beta`, `dev`, and `nightly` as used for unstable Firefox releases.

This feature is possible thanks to the remarkable work of the Firefox team by maintaining current and old releases in their [public repository](https://ftp.mozilla.org/pub/firefox/releases/). Moreover, the Firefox version discovery in Selenium Manager is made thanks to the availability of the [product-details JSON API](https://wiki.mozilla.org/Release_Management/Product_details), also maintained by the Firefox team.

### Improved configuration
Custom setup is sometimes necessary for browser automation. For that reason, Selenium Manager already provides different features for [rich configuration](https://www.selenium.dev/documentation/selenium_manager/#configuration). Selenium Manager 0.4.12 extends this feature by delivering a new configuration argument called `--cache-path`. This argument allows changing the path of the local folder used to store downloaded assets (drivers and browsers) by Selenium Manager (by default, `~/.cache/selenium`). As usual, this argument can also be changed by including an entry in the configuration file or using an environment variable (`SE_CACHE_PATH`). Regarding the former, the name of the configuration file has been renamed to `se-config.toml` in Selenium Manager 0.4.12. As usual, if you use this configuration file, it must be placed in the root of the cache folder.

### Other changes
A minor change in Selenium Manager 0.4.12 is related to the metadata file, now called `se-metadata.json`. As usual, this file is stored in the root of the cache folder. This file contains versions discovered by Selenium Manager making network requests and the *time-to-live* (TTL) in which they are valid. Since the TTL for browsers and drivers is now the same concept, Selenium Manager unifies these two configuration elements (previously, `browser_ttl` and `driver_ttl`) in a single one called `ttl` (with a default value of 3600 seconds, i.e., 1 hour). For further details, visit the Selenium Manager page about [caching](https://www.selenium.dev/documentation/selenium_manager/#caching).

Last but not least, the Selenium Manager binary compiled for macOS is *universal*, in the sense that it can be executed both in *x64* and *arm64* architectures out of the box. Previously, the binary was compiled for *x64*, and so, [Rosetta](https://support.apple.com/en-us/HT211861) should be available in *arm64* macOS machines (i.e., M1 or M2). With the new Selenium Manager macOS binary, Rosetta is no longer mandatory.

### Next steps
The next release of Selenium Manager will continue delivering automated browser management, this time for **Edge**, and other features. As usual, you can trace the work in progress in the [Selenium Manager project dashboard](https://github.com/orgs/SeleniumHQ/projects/5).