---
title: "HtmlUnit Remote: Acquiring Remote HtmlUnitDriver Session in Selenium 4 Grid"
linkTitle: "HtmlUnit Remote: Acquiring Remote HtmlUnitDriver Session in Selenium 4 Grid"
date: 2024-08-19
tags: ["Grid", "HtmlUnitDriver"]
categories: ["Grid"]
author: Scott Babcock [@sbabcoc](https://www.github.com/sbabcoc)
images:
  - "/images/blog/2024/html-unit-remote.jpg"
description: >
   This post describes 'HtmlUnit Remote', a wrapper for HtmlUnitDriver that enables Selenium 4 Grid to manage remote instances of this "headless" browser.
---

# HTMLUNIT REMOTE
[![Maven Central](https://img.shields.io/maven-central/v/com.nordstrom.ui-tools/htmlunit-remote.svg)](https://central.sonatype.com/search?q=com.nordstrom.ui-tools+htmlunit-remote&core=gav)

The [HtmlUnit Remote](https://github.com/seleniumhq-community/htmlunit-remote) project implements a [W3C WebDriver protocol](https://www.w3.org/TR/webdriver2) wrapper for [HtmlUnitDriver](https://github.com/SeleniumHQ/htmlunit-driver), which enables **Selenium 4 Grid** to supply remote sessions of this headless browser.

### Background

To eliminate behavioral differences between local and remote configurations, the [Selenium Foundation](https://github.com/sbabcoc/Selenium-Foundation) framework always acquires browser sessions from a **Grid** instance, managing its own local grid instance when not configured to use an existing grid. **Selenium 3 Grid** could be configured to supply **HtmlUnitDriver** sessions, supported by special-case handling within the Node server itself. This handling was not carried over into **Selenium 4 Grid**, which was completely re-engineered with new architecture and vastly expanded capabilities.

The lack of **HtmlUnitDriver** support in **Selenium 4 Grid** necessitated reconfiguring the **Selenium Foundation** project unit tests from using this Java-only managed artifact to using a standard browser like Chrome, an external dependency that requires additional resources and imposes additional risks of failure.

The driver service implemented by **HtmlUnit Remote** enables **Selenium 4 Grid** to supply **HtmlUnitDriver** sessions.

### Project Rationale

My initial objective for creating **HtmlUnit Remote** was to retain feature parity in **Selenium Foundation** for the set of browsers supported with **Selenium 3** and **Selenium 4**. Although I could configure my unit tests to target a conventional browser, I also wanted to avoid additional external dependencies with associated risks.

Once I began investigating the features and functionality I would need to enable **Selenium 4 Grid** to supply **HtmlUnitDriver** sessions, I recognized an additional benefit this project could provide - comprehensive standardized configurability.

### HtmlUnitDriver Configuration

All remote drivers are configured via a standard **Selenium** feature - the [Capabilities](https://github.com/SeleniumHQ/selenium/blob/trunk/java/src/org/openqa/selenium/Capabilities.java) object. Prior to the **HtmlUnit Remote** project, many of the options of [HtmlUnit](https://www.htmlunit.org/) could not be accessed or modified via the **Capabilities** API. These were only available via custom **HtmlUnitDriver** methods, and the way that non-standard capabilities had been added to the **Capabilities** object didn't conform to the **W3C** specification.

This meant that the initial phase of the **HtmlUnit Remote** project was to implement a comprehensive W3C-compliant configuration object - the **HtmlUnitDriverOptions** class. This class extends [AbstractDriverOptions](https://github.com/SeleniumHQ/selenium/blob/trunk/java/src/org/openqa/selenium/remote/AbstractDriverOptions.java), adding driver-specific capabilities under an extension named `garg:htmlunitOptions`. Support for this class provides full configurability of all **HtmlUnitDriver** options through the standard **Capabilities** API.

This standardized configuration API has been incorporated directly into **HtmlUnitDriver**, providing the core implementation for manipulating every driver setting. To maintain backward compatibility, all of the existing constructors and configuration methods have been retained, reimplemented to use this new core API.

### W3C Remote Protocol Wrapper

With full standardized configurability in place, the next step was to create a server that implements the [W3C WebDriver protocol](https://www.w3.org/TR/webdriver2). The **HtmlUnitDriverServer** functions as a remote protocol wrapper around one or more **HtmlUnitDriver** sessions, performing the following tasks:
* Create and manage driver sessions
* Route driver commands to specified driver sessions
* Package driver method results into HTTP responses

### HtmlUnit Remote Packaging

Rather than bulk up the existing driver with remote-specific features, **HtmlUnitDriverServer** and associated facilities are packaged in a companion `htmlunit-remote` artifact. In addition to the server, this artifact defines a driver information provider (**HtmlUnitDriverInfo**), a driver service (**HtmlUnitDriverService**), and a custom slot matcher (**HtmlUnitSlotMatcher**).

### Connecting to the Grid

Next up is **HtmlUnitDriverInfo**, which specifies the basic characteristics of the driver and provides a method that creates a driver session with specified capabilities. This class implements the standard [WebDriverInfo](https://github.com/SeleniumHQ/selenium/blob/trunk/java/src/org/openqa/selenium/WebDriverInfo.java) interface.

With availability of **HtmlUnitDriver** advertised by this information provider, **Selenium 4 Grid** nodes can be configured to provide driver sessions:

##### htmlunit.toml
```
[node]
detect-drivers = false
[[node.driver-configuration]]
display-name = "HtmlUnit"
stereotype = "{\"browserName\": \"htmlunit\"}"

[distributor]
slot-matcher = "org.openqa.selenium.htmlunit.remote.HtmlUnitSlotMatcher"
```
The `selenium-server` JAR doesn't include the **HtmlUnitDriver** artifacts; these need to be specified as extensions to the grid class path via the `--ext` option:

```
java -jar selenium-server-<version>.jar --ext htmlunit-remote-<version>-grid-extension.jar standalone --config htmlunit.toml
```
The `grid-extension` artifact provides all of the specifications and service providers required to enable **Selenium 4 Grid** to supply remote sessions of **HtmlUnitDriver**. This artifact combines `htmlunit-remote` with `htmlunit3-driver`, `htmlunit`, and all of their unique dependencies.

### Implementation Details

**HtmlUnit Remote** provides the following elements:
* **HtmlUnitDriverInfo** - This class informs **Selenium 4 Grid** that **HtmlUnitDriver** is available and provides a method to create new driver instances.
* **HtmlUnitSlotMatcher** - This custom slot matcher extends **DefaultSlotMatcher**, indicating a match if both the slot stereotype and requested browser capabilities specify `htmlunit` as the browser name.
* **HtmlUnitDriverService** - This class manages a server that hosts instances of **HtmlUnitDriver**.
* **HtmlUnitDriverServer** - This is the server class that hosts **HtmlUnitDriver** instances, enabling remote operation via the [W3C WebDriver protocol](https://www.w3.org/TR/webdriver2).

In operation, **HtmlUnitDriverService** is instantiated by **Selenium 4 Grid** node servers that are configured to support **HtmlUnitDriver**. Unlike other driver services, which launch a new process for each created driver session, **HtmlUnitDriverService** starts a single in-process server that hosts all of the driver sessions it creates.

_This is a guest blog post by [Scott Babcock](https://www.github.com/sbabcoc)_
