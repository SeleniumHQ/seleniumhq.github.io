---
title: "Selenium 2.0a6 Released"
linkTitle: "Selenium 2.0a6 Released"
date: 2010-10-08
tags: ["selenium","beta"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  We are extremely pleased to announce the release of Selenium 2.0a6!
---

We are extremely pleased to announce the release of Selenium 2.0a6! Head over to the [downloads page](http://code.google.com/p/selenium/downloads/list) to get it while it’s hot, or wait just a little bit longer for it to appear in a maven repo near you. The .Net version will also be updated soon too, and the python and [ruby libraries](http://rubygems.org/gems/selenium-webdriver) have been having smaller, more frequent releases all this time.

You’ll be pleased to hear that the Selenium 1.0 APIs have remained constant, so what’s changed? Here, in no particular order are the major changes you’ll find in 2.0a6:

*   Android support: you can now [download the APK](http://code.google.com/p/selenium/downloads/detail?name=android-server-2.0a6.apk) and run webdriver tests using Android 1.6 to 2.2.
*   Firefox 4 support.
*   Experimental IE9 support
*   New APIs for dealing with [HTML5 elements](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/html5/package-summary.html) (best implemented, for now, by the mobile webdrivers)
*   A richer .Net API
*   A move to [Sizzle](http://sizzlejs.com/) for locating elements using CSS in browsers that don’t have a native API for that.
*   Far better support for running your existing Selenium RC tests using WebDriver, helping you make a managed migration to the newer APIs.

There are also lots of nice touches for the more technically inclined, including the ability to re-use instances of [FirefoxProfiles](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/firefox/FirefoxProfile.html), better configurability when requesting a remote webdriver instance, better resource management and more shared code between the Selenium and WebDriver implementations.

Thank you to everyone who has taken the time to report a bug on our [issue tracker](http://code.google.com/p/selenium/issues/list), or raised problems on one of our [mailing](https://groups.google.com/group/webdriver) [lists](http://www.google.com/url?q=http://groups.google.com/group/selenium-users), or shown up for some of the banter on the IRC channel: without your involvement, the project wouldn’t be half as much fun, and wouldn’t be as capable as it is. Thanks are also due to the development team, who have poured an enormous amount of work into this release (538 revisions in under 90 days, or about 6 check-ins each and every day)

Hope you like it!