---
title: "Status of Selenium Manager in October 2023"
linkTitle: "Status of Selenium Manager in October 2023"
date: 2023-10-17
tags: ["selenium", "manager", "edge", "chromium"]
categories: ["releases"]
author: Boni García ([@boni_gg](https://twitter.com/boni_gg))
description: >
   This blog post summarizes the novelties introduced in the latest two versions of Selenium Manager (i.e., 0.4.13 and 0.4.14).
---

Selenium Manager continues its development plan. As usual, in the latest releases, i.e., 0.4.13 and 0.4.14 (shipped with Selenium 4.13 and 4.14, respectively), we have fixed the problems reported so far. In these releases, the issues were related to the extraction of the Firefox binary from the self-extracting archive (SFX) in Windows and the advanced configuration through the configuration file (`se-config.toml`) and environment variables (e.g., `SE_BROWSER`). Moreover, these recent releases include new features, as explained below.

### Search for the best driver possible in the cache
By default, Selenium Manager needs to request online endpoints (such as [Chrome for Testing JSON API](https://github.com/GoogleChromeLabs/chrome-for-testing#json-api-endpoints) or [Firefox product-details JSON API](https://wiki.mozilla.org/Release_Management/Product_details)
to discover, download, and maintain the proper drivers and browsers that Selenium requires. The downloaded artifacts are stored in the cache (by default, `~/.cache/selenium`) and reused from there. 

To make the driver resolution procedure more robust, as of version 0.4.13, Selenium Manager includes a new feature for locating the drivers in the cache when some error happens. This way, when a network request (or other function) fails in Selenium Manager, it tries to locate the driver in the cache. This characteristic aims to provide a best-effort solution for creating a Selenium session properly, which is the final objective of Selenium Manager. Also, this feature will help to provide a better experience for locating drivers for Selenium Grid.

### Locating Selenium Manager binary with an environment variable
The next feature related to Selenium Manager 0.4.13 has been implemented in the Selenium bindings (i.e., Java, JavaScript, Python, .Net, and Ruby). As of Selenium 4.13.0, the Selenium bindings allow locating the Selenium Manager binary using an environment variable called `SE_MANAGER_PATH`. This way, if this variable is set, the bindings will use its value as the Selenium Manager path in the local filesystem. This feature will allow users to provide a custom compilation of Selenium Manager, for instance, if the default binaries (compiled for Windows, Linux, and macOS) are incompatible with a given system (e.g., ARM64 in Linux).

### Automated Edge management
Selenium Manager 0.4.14 includes automated Edge management. This browser is the last we have in mind for this feature, after Chrome and Firefox.

This feature has been implemented in the same way that Chrome and Firefox for macOS and Linux. In other words, Selenium Manager allows to automatically manage (i.e., discover, downloads, and cache) the latest Edge versions (i.e., stable, beta, dev, canary) and old versions (e.g., 115, 116, etc.). The downloaded binaries, as usual, are stored in the Selenium cache. The following output commands showcase this feature in macOS (first snipped) and Linux (second snippet):

```
./selenium-manager --browser edge --debug --force-browser-download

DEBUG	msedgedriver not found in PATH
DEBUG	Checking edge releases on https://edgeupdates.microsoft.com/api/products
DEBUG	Required browser: edge 117.0.2045.40
DEBUG	Downloading edge 117.0.2045.40 from https://msedge.sf.dl.delivery.mp.microsoft.com/filestreamingservice/files/6e65d9ef-0bb9-4636-8d9e-2b1b9d16149d/MicrosoftEdge-117.0.2045.40.pkg
DEBUG	edge 117.0.2045.40 has been downloaded at /Users/boni/.cache/selenium/edge/mac64/117.0.2045.40/Microsoft Edge.app/Contents/MacOS/Microsoft Edge
DEBUG	Reading msedgedriver version from https://msedgedriver.azureedge.net/LATEST_RELEASE_117_MACOS
DEBUG	Required driver: msedgedriver 117.0.2045.40
DEBUG	Downloading msedgedriver 117.0.2045.40 from https://msedgedriver.azureedge.net/117.0.2045.40/edgedriver_mac64.zip
INFO	Driver path: /Users/boni/.cache/selenium/msedgedriver/mac64/117.0.2045.40/msedgedriver
INFO	Browser path: /Users/boni/.cache/selenium/edge/mac64/117.0.2045.40/Microsoft Edge.app/Contents/MacOS/Microsoft Edge
```

```
./selenium-manager --browser edge --debug --browser-version beta

DEBUG	msedgedriver not found in PATH
DEBUG	edge not found in PATH
DEBUG	edge beta not found in the system
DEBUG	Checking edge releases on https://edgeupdates.microsoft.com/api/products
DEBUG	Required browser: edge 118.0.2088.11
DEBUG	Downloading edge 118.0.2088.11 from https://packages.microsoft.com/repos/edge/pool/main/m/microsoft-edge-beta/microsoft-edge-beta_118.0.2088.11-1_amd64.deb
DEBUG	edge 118.0.2088.11 has been downloaded at /home/user/.cache/selenium/edge/linux64/118.0.2088.11/msedge
DEBUG	Reading msedgedriver version from https://msedgedriver.azureedge.net/LATEST_RELEASE_118_LINUX
DEBUG	Required driver: msedgedriver 118.0.2088.11
DEBUG	Downloading msedgedriver 118.0.2088.11 from https://msedgedriver.azureedge.net/118.0.2088.11/edgedriver_linux64.zip
INFO	Driver path: /home/user/.cache/selenium/msedgedriver/linux64/118.0.2088.11/msedgedriver
INFO	Browser path: /home/user/.cache/selenium/edge/linux64/118.0.2088.11/msedge
```

Nevertheless, this feature cannot be implemented similarly for Windows. The reason is that the Edge installer for Windows is distributed as a Microsoft Installer (MSI) file, designed to be executed with administrator rights. We tried to extract the Edge binaries from that MSI file. Still, it seems not possible (see [Stack Overflow post that summarized the problem](https://stackoverflow.com/questions/77132922/extract-parse-resources-from-portable-executable-pe-file)). All in all, the only solution we found is to install Edge in Windows using the MSI installer, and so, administrator grants are required.

This way, when Edge is attempted to be installed with Selenium Manager in Windows with a non-administrator session, a warning message will be displayed as follows:

```
./selenium-manager --debug --browser edge --browser-version beta

DEBUG   msedgedriver not found in PATH
DEBUG   edge not found in PATH
DEBUG   edge beta not found in the system
WARN    There was an error managing edge (edge can only be installed in Windows with administrator permissions); using driver found in the cache
INFO    Driver path: C:\Users\boni\.cache\selenium\msedgedriver\win64\118.0.2088.17\msedgedriver.exe
```

But when Selenium Manager is executed with administrator grants in Windows, it will be able to automatically discover, download, and install Edge (stable, beta, dev, canary, and older versions):

```
./selenium-manager --debug --browser edge --browser-version beta

DEBUG   msedgedriver not found in PATH
DEBUG   edge not found in PATH
DEBUG   edge beta not found in the system
DEBUG   Checking edge releases on https://edgeupdates.microsoft.com/api/products
DEBUG   Required browser: edge 118.0.2088.17
DEBUG   Downloading edge 118.0.2088.17 from https://msedge.sf.dl.delivery.mp.microsoft.com/filestreamingservice/files/7adec542-f34c-4dea-8e2a-f8c6fab4d2f3/MicrosoftEdgeBetaEnterpriseX64.msi
DEBUG   Installing MicrosoftEdgeBetaEnterpriseX64.msi
DEBUG   edge 118.0.2088.17 is available at C:\Program Files (x86)\Microsoft\Edge Beta\Application\msedge.exe
DEBUG   Required driver: msedgedriver 118.0.2088.17
DEBUG   msedgedriver 118.0.2088.17 already in the cache
INFO    Driver path: C:\Users\boni\.cache\selenium\msedgedriver\win64\118.0.2088.17\msedgedriver.exe
INFO    Browser path: C:\Program Files (x86)\Microsoft\Edge Beta\Application\msedge.exe
```

### Chromium support
Chromium is released as portable binaries, distributed as zip files for Windows, Linux, and macOS (see [Chromium download page](https://www.chromium.org/getting-involved/download-chromium/)). Nevertheless, there is a case in which Chromium is actually installed in the system. This happens in Linux systems when installing Chromium through package managers like `atp` or `snap`, for instance, as follows:

```
sudo snap install chromium
```

Therefore, as of 0.4.14, Selenium Manager looks for the Chromium binaries in the PATH when Chrome is not discovered. The following snippet showcases how this feature works in a Linux machine in which Chrome is not available, but Chromium has been installed through `snap`:

```
./selenium-manager --browser chrome --debug
DEBUG   chromedriver not found in PATH
DEBUG   Found chromium in PATH: /snap/bin/chromium
DEBUG   Running command: /snap/bin/chromium --version
DEBUG   Output: "Chromium 117.0.5938.149 snap"
DEBUG   Detected browser: chrome 117.0.5938.149
DEBUG   Required driver: chromedriver 117.0.5938.149
DEBUG   chromedriver 117.0.5938.149 already in the cache
INFO   Driver path: /home/user/.cache/selenium/chromedriver/linux64/117.0.5938.149/chromedriver
INFO   Browser path: /snap/bin/chromium
```

### Next steps
We are close to implementing all the features initially planned for Selenium Manager. You can trace the status of the development activities in the [Selenium Manager project dashboard](https://github.com/orgs/SeleniumHQ/projects/5).