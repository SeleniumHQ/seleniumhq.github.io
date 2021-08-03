---
title: "Selenium 3 is Coming"
linkTitle: "Selenium 3 is Coming"
date: 2016-10-04
tags: ["selenium","beta"]
categories: ["general","releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Selenium 3 is coming!
---


[Selenium 3](http://www.seleniumhq.org/download/) is coming! As I write this, we think that “beta 4” will be the last beta before the official 3.0 release. I’m here to tell you about what’s changed, and what impact this will have on your testing.

### TL;DR:

*   WebDriver users will just find bug fixes and a drop-in replacement for 2.x.
*   Selenium Grid users will also find bug fixes and a simple update.
*   The WebDriver APIs are now the only APIs actively supported by the Selenium project.
*   The Selenium RC APIs have been moved to a “legacy” package.
*   The original code powering Selenium RC has been replaced with something backed by WebDriver, which is also contained in the “legacy” package.
*   By a quirk of timing, Mozilla have made changes to Firefox that mean that from Firefox 48 you must use their geckodriver to use that browser, regardless of whether you’re using Selenium 2 or 3.

### In more depth:

When we [released Selenium 2.0](https://seleniumhq.wordpress.com/2011/07/08/selenium-2-0/) in 2011, we introduced the new WebDriver APIs, and encouraged everyone to start moving to them. If you’re using the WebDriver APIs, then Selenium 3.0 is a simple drop-in upgrade. We’ve not changed any of the public WebDriver APIs, and the code is essentially the same as the last 2.x release. If you’re using Selenium Grid, the same applies: in most cases, you can just drop in the new JAR (or update your maven dependency to 3.0.0), and you’re done.

If the update to Selenium 3 is such a non-event, why did we call this Selenium 3.0? To answer this question, I first need to provide some history, and tell you a little about how Selenium works under the hood. The very first version of Selenium was “just” a very complicated Javascript framework, running in the browser and interpreting the table-based tests you may be familiar with if you use Selenium IDE. We call this “Selenium Core”. This Javascript framework formed the basis of the original implementation of Selenium RC (the oldest set of Selenium APIs, where all the method and functions were on the “[Selenium](http://seleniumhq.github.io/selenium/docs/api/java/com/thoughtworks/selenium/Selenium.html)” interface, and which have been deprecated for some time now). Over time, the needs of modern web testing have grown ever more complicated and sophisticated, and Selenium Core is now less capable of meeting these needs than it was before.

With Selenium 3.0, we are deleting the original Selenium Core implementation. If you use the old RC interfaces, we provide an alternative implementation that’s backed by WebDriver. This is the same “webdriver-backed selenium” that has been available as part of Selenium 2 since its release. Because the underlying technology has changed from Selenium Core to WebDriver, you may find some places where your existing tests using RC run into issues. Our experience with migrating suites is that it’s normally a systemic issue that can be fixed with a minimal engineering effort (that is, the problem is normally isolated to a few places, and these can be rewritten to avoid problems)

We’re also removing the original Selenium RC APIs from the main downloads. If you’re a Java user, and need to use them to support existing tests, then you’ll need a dependency to “org.seleniumhq.selenium:selenium-leg-rc:3.0.0” (or later!). It’s strongly recommended that you do not do this unless you absolutely need to.  
If you’re someone who runs tests exported from IDE in the table format, there is now a new test runner that the project has made available for you to use that can be [downloaded](http://www.seleniumhq.org/download/) from the project’s website. It takes the same arguments as the old runner, and we’ve done our best to ensure the output of tests remains the same too.

At the same time as the Selenium project is shipping Selenium 3.0, Mozilla are changing the internals of Firefox in a way that makes it more stable and secure, but which also makes the community provided Firefox Driver no longer work. As such, if you use Firefox for your testing, you’ll need to use the [geckodriver](https://github.com/mozilla/geckodriver/releases), which is an executable similar to the [chromedriver](https://chromedriver.chromium.org/) and the [Microsoft WebDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) for Edge. You’ll need to start using geckodriver even if you’re using Selenium 2 — the change is in the browser, not Selenium. Please be aware that geckodriver is alpha software, based on the evolving W3C WebDriver standard: everyone’s working flat out to give you the best testing experience they can, but there are undoubtedly some bumps in the road when it comes to testing with Firefox.

This release marks the culmination of a lot of hard work by the Selenium committers and community. I’d like to thank everyone who has been part of this process, and the Selenium users around the world who have done so much to make the project as successful as it is.