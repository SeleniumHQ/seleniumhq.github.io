---
title: "Selenium 2.0b2 Released"
linkTitle: "Selenium 2.0b2 Released"
date: 2011-02-15
tags: ["selenium","beta"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  We’ve just released Selenium 2.0b2.
---

We’ve just released Selenium 2.0b2. If you’re the impatient sort who loves to have the latest and greatest, head over to the [download site](http://code.google.com/p/selenium/downloads/list) and get it while it’s hot. If you’re a Python user, then all you need to do is a simple “pip install -U selenium”. Ruby users can, as ever, simply run “gem install selenium-webdriver”. Maven users need to wait just a little bit longer: we’re going to be checking the release in ASAP.

Between beta 1 and beta 2, we held a week-long Bug Bash, during which we closed a significant number of bugs. From a user’s perspective, other highlights include:

*   A more stable, capable iPhone driver.
*   Updated [Android driver](http://code.google.com/p/selenium/downloads/detail?name=selenium-server-2.0b2.zip&can=2&q=).
*   Improved python bindings for Selenium WebDriver. The namespace is now “selenium.webdriver”
*   Added “[Selenium.getCssCount](http://selenium.googlecode.com/svn/trunk/docs/api/java/com/thoughtworks/selenium/Selenium.html#getCssCount(java.lang.String))” to mirror “Selenium.getXpathCount”
*   “[WebElement.getText()](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html#getText())” performs more consistently across different browsers.
*   Mono users can use the .Net bindings
*   Continued to improve the [WebDriverBackedSelenium](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriverBackedSelenium.html). If you’re looking to migrate from Selenium 1 to Selenium 2, and want to take your time, this is a useful stepping stone.
*   Reworked the Advanced User Interactions APIs. The big change is that the WebDriver APIs no longer rely on classes from the AWT.
*   .Net users now have more support classes, to make writing tests less tiresome.
*   The remote webdriver makes better use of sockets, which improves stability and scalability on Windows.
*   Started to add support for driving multiple IE instances. This is considered experimental, but we’d love to hear it’s working for you!

If you’re interested in the guts of Selenium 2 and how it worked, then you might find these interesting:

*   Continued reworking the IE and iPhone drivers to use the Automation Atoms.
*   Reworked the structure of the source tree to be more language focused.
*   We have the skeleton of a webdriver-backed selenium for Python.

As you can see, this is a big release. Beta 3 should be out a lot more quickly, and will be focusing on improving support for IE 9 and Firefox 4. Over the course of the 2.0b3 development, we shall also be removing as many deprecated methods as possible, so be sure to remove deprecation warnings from your builds when using 2.0b2!