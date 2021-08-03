---
title: "Announcing Selenium 2.22"
linkTitle: "Announcing Selenium 2.22"
date: 2012-05-29
tags: ["selenium"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  It’s been a while since the last Selenium release, but I’m happy to announce that Selenium 2.22 is now available for download.
---

It’s been a while since the last Selenium release, but I’m happy to announce that Selenium 2.22 is now [available for download](http://seleniumhq.org/download/). This is a big release for us and features two major changes.

The first is that **Selenium 2.22 is the first version that requires Java 6** in order to run. This has been the case for the Selenium Server for some time, but this is the first time the client code has required Java 6. Since Java 5 was [“end of lifed” in 2009](http://www.oracle.com/technetwork/java/eol-135779.html), we don’t expect this to impact many users.

The second major change is that **we are now providing a standalone IE server for use with the WebDriver API**, similar to the one used by the chrome driver. You can get it from the normal download page. This will allow us to update our IE support independently of the rest of the library (again, mirroring how Chrome is supported) For now, there’s a legacy fallback mode you can use that’ll use the same DLL we’ve always used which can be activated by setting the DesiredCapability “useLegacyInternalServer” to boolean “true” when requesting your IE Driver instance.

Of course, as well as these major changes, there’s the usual host of updates and improvements. We’re continuing to refine the new SafariDriver, and we’re happy to announce native events for Firefox 12. You can check out the other updates in the [CHANGELOG](http://code.google.com/p/selenium/source/browse/trunk/java/CHANGELOG).