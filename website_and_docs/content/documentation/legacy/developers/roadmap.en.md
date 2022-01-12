---
title: "Snapshot of Roadmaps for Selenium Releases"
linkTitle: "Roadmap"
weight: 15
description: >
    The list of plans and things to accomplish before a release
---

## Preparation for Selenium 2
Date unknown
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/RoadMap/eef12bca5fdc865449ad2d1735ee08e40ba0bd2b)

The following issues need to be resolved before the final release:

| **Issue** | **Summary** | **HtmlUnitDriver Progress** | **FirefoxDriver Progress** | **InternetExplorerDriver Progress** | **ChromeDriver Progress** |
|:----------|:------------|:----------------------------|:---------------------------|:------------------------------------|:--------------------------|
| [27](http://code.google.com/p/webdriver/issues/detail?id=27)  | Handle alerts in Javascript-enabled browsers | n/a                         | Started                    | Started                             | Not Started               |
| [32](http://code.google.com/p/webdriver/issues/detail?id=32) | User guide  | Started                     | | | |
| [34](http://code.google.com/p/webdriver/issues/detail?id=34)  | Support HTTP Basic and Digest Authentication | Not Started                 | | | |
| [35](http://code.google.com/p/webdriver/issues/detail?id=35)  | [Selenium](http://www.openqa.org/selenium-rc) emulation | Done for Java and C#        | | | |
| [36](http://code.google.com/p/webdriver/issues/detail?id=36) | Support for drag and drop behaviour | n/a                         | Done                       | Done                                | Started                   |
| none      | Example tests | Not Started                 | | | |

A final release will be made once these are implemented in Firefox, IE and at least one webkit-based browser.

### The Future

The following are also planned:

* **JsonWireProtocol** --- The formalisation of the current RemoteWebDriver wire protocol in [JSON](http://www.json.org/).

## Preparation for Selenium 3
As of Mar 16, 2015
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Shipping-Selenium-3)

### User Visible Changes
* Migrate all drivers to use the status strings rather than status codes in responses
* Update client bindings to also cope with that
* Write a new runner for the html-suite tests
* Segment the build to remove RC

### Clean up

* Using WebDriver after quit() should be an IllegalStateException
* Actions to have a single end point
* Capabilities to be the same as the spec
* Multiple calls to WebDriver.quit() should still be safe.
* Clean up WebDriver constructors, pulling heavy initialization logic into a Builder class
* Migrate to Netty or webbit server
* Delete unnecessary cruft
* Land a cleaner end point for the rc emulation

## Preparation for Selenium 4
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/RoadMap)
As of April 12, 2017

- Finish the [W3C WebDriver Spec](https://w3c.github.io/webdriver/webdriver-spec.html)
- Implement the local end requirements for the spec in selenium
- Implement protocol conversion in the standalone server
- Ship 4.0
- 
