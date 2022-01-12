---
title: "Getting started"
linkTitle: "Getting Started"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
aliases: [
"/documentation/en/getting_started/", 
"/documentation/en/getting_started/quick/",
"/documentation/en/selenium_installation/",
"/documentation/en/getting_started_with_webdriver/",
"/documentation/getting_started/"
]
---

Selenium supports automation of all the major browsers in the market
through the use of _WebDriver_.
WebDriver is an API and protocol that defines a language-neutral interface
for controlling the behaviour of web browsers.
Each browser is backed by a specific WebDriver implementation, called a *driver*.
The driver is the component responsible for delegating down to the browser,
and handles communication to and from Selenium and the browser.

This separation is part of a conscious effort to have browser vendors
take responsibility for the implementation for their browsers.
Selenium makes use of these third party drivers where possible,
but also provides its own drivers maintained by the project
for the cases when this is not a reality.

The Selenium framework ties all of these pieces together
through a user-facing interface that enables the different browser backends
to be used transparently,
enabling cross-browser and cross-platform automation.

Selenium setup is quite different from the setup of other commercial tools.
Before you can start writing Selenium code, you have to 
install the language bindings libraries for your language of choice, the browser you
want to use, and the driver for that browser.

***Follow the links below to get up and going with Selenium WebDriver.***

If you wish to start with a low-code/record and playback tool, please check
[Selenium IDE](https://selenium.dev/selenium-ide)

Once you get things working, if you want to scale up your tests, check out the 
[Selenium Grid]({{< ref "/documentation/grid" >}}).
