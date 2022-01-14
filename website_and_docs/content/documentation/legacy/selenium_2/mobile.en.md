---
title: "WebDriver For Mobile Browsers"
linkTitle: "Mobile"
weight: 13
description: >
    Describes how Selenium 2 supported Android and iOS before Appium was created
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Untrusted-SSL-Certificates)

## Introduction

We provide mobile drivers for two major mobile platforms: Android and iOS (iPhone & iPad).

They can be run on real devices and in an Android emulator or in the iOS Simulator, as appropriate. They are packaged as an app. The app needs to be installed on the emulator or device. The app embeds a [RemoteWebDriver server](https://github.com/SeleniumHQ/selenium/wiki/RemoteWebDriverServer) and a light-weight HTTP server which receive, and respond to, requests from WebDriver Clients i.e. from your automated tests.

The connection between the server on the mobile platform and your tests uses an IP connection. The connection may need to be configured. For Android you can connect establish an IP connection over USB.

In some cases your existing WebDriver tests may run successfully e.g. where a common web site serves mobile and desktop users and where the UI is relatively straight-forward. However in other cases you may end up needing to create specific tests for the mobile site; particularly when the site provides specific capabilities, user interfaces, etc. for mobile browsers.

Even when a common web site serves both desktop and mobile browsers, you may want to consider writing specific tests that incorporate factors such as the screen-size of the mobile devices, and different ways users are likely to interact with your web site or web app.

## Getting Started

[Android Setup](https://github.com/SeleniumHQ/selenium/wiki/AndroidDriver)

[iPhone & iPad Setup](https://github.com/SeleniumHQ/selenium/wiki/IPhoneDriver)

## Additional Mobile Platforms
There are several related opensource projects that include support for other Mobile platforms. These include:

[Blackberry WebDriver](http://code.google.com/p/webdriver-blackberry/), for BlackBerry 5.0 and onward.

[Headless WebKit WebDriver](http://code.google.com/p/webkitdriver/). Many mobile browsers are WebKit based. Headless WebKit provides a fast light-weight solution.

These projects don't appear to be active, however they may provide a starting point for future work on these platforms.
