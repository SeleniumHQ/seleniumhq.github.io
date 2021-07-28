---
title: "Selenium 2.0a5 Released"
linkTitle: "Selenium 2.0a5 Released"
date: 2010-07-14
tags: ["selenium","beta"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  I’m pleased to announce the release of Selenium 2.0a5, available for immediate download.
---

I’m pleased to announce the release of Selenium 2.0a5, available for [immediate download](http://code.google.com/p/selenium/downloads/list). This release brings a host of changes under the hood, and represents the efforts of many contributors. Highlights include:

*   New [interfaces](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/html5/package-frame.html) for dealing with HTML 5 elements.
*   An API for “[implicit waits](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriver.Timeouts.html)“: quietly waiting until an element is present before continuing with a test. You can use them like this: `driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS)`
*   A revamped Firefox driver.
*   More shared code between Selenium and WebDriver.
*   You can now pass firefox profiles to the remote webdriver (this includes extensions and proxy settings!)
*   Improved .Net bindings: lots of updates to help bring them more in-line with the Java equivalents.

Waiting in the wings for release soon is an AndroidDriver, which opens up the world of testing webapps on Android devices through the Selenium WebDriver API.

If you’re a pythonista or rubyist, you’ve not been left out of this bonaza of new hotness. There have been regular updates for these languages, which can be installed via “easy\_install -U selenium” or “gem install selenium-webdriver” depending on your language of choice.

Hopefully the next alpha will be the last before we plunge bravely into the betas. Exciting times are ahead!