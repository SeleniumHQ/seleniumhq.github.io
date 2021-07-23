---
title: "Selenium 2.6 Released"
linkTitle: "Selenium 2.6 Released"
date: 2011-09-16
tags: ["selenium"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  If you’ve been watching this blog carefully you’ll have noticed that the last release announcement we made was for 2.3...
---

If you’ve been watching this blog carefully you’ll have noticed that the last release announcement we made was for 2.3, so it may come as a surprise that we’re announcing that [2.6 has been released](http://seleniumhq.org/download/) (even on Maven!). Don’t worry: 2.4 and 2.5 were released on time and without muss or fuss. 2.6, on the other hand has been almost three weeks brewing.

Selenium 2.6 introduces a raft of improvements and stability fixes. Kristian Rosenvold has been working wonders on [Grid 2.0](http://code.google.com/p/selenium/wiki/Grid2), addressing many reported issues and cleaning up the implementation. In the [finest tradition of the project](http://code.google.com/p/selenium/issues/detail?id=14), I now [owe him a dinner](http://code.google.com/p/selenium/issues/detail?id=2475) for his hard work. Thank you, Kristian!

For those of you not using Grid, as well as the normal suite of bug fixes, Selenium 2.6 now supports all versions of Firefox from 3.0 up to 7. For those of you using Java, there is an [ExpectedConditions](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html) class that supplies many useful criteria when using the [Wait](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/support/ui/Wait.html) and [WebDriverWait](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/support/ui/WebDriverWait.html) classes. The packaged version of the [OperaDriver](http://www.opera.com/developer/tools/operadriver/) has also been bumped to 0.7.2, which works hand-in-hand with Opera 11.5 and above.

We’ve also spent a considerable amount of time and effort working out the kinks in the [Advanced User Interactions API](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/interactions/package-frame.html). We’d love to hear how you’re using it, and what the gaps are that you can see. For more details about what’s changed, have a look at the [release notes](http://code.google.com/p/selenium/source/browse/trunk/java/CHANGELOG).

The release frequency has dropped recently, but we’re planning to head back to weekly releases from here on in. 2.7 is just around the corner!