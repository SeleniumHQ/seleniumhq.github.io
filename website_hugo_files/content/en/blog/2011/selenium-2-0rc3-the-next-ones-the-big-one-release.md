---
title: "Selenium 2.0rc3: The “Next One’s The Big One” Release"
linkTitle: "Selenium 2.0rc3: The “Next One’s The Big One” Release"
date: 2011-06-27
tags: ["selenium","beta"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  When we pushed the 2.0rc1 live, we really hoped that the next release would be 2.0 final.
---

When we pushed the 2.0rc1 live, we really hoped that the next release would be 2.0 final. We very quickly got some feedback that encouraged us to push a 2.0rc2. Now, after just under three weeks, we’re launching a third and final release candidate. You can download it from the [Selenium HQ](http://seleniumhq.org/download/) site or directly from [Google Code.](http://code.google.com/p/selenium/downloads/list)

We think we’ve addressed many of the common issues, added some polish and added a host of bug fixes and minor changes, and we hope to hear your feedback! The following headline changes have been made in Selenium 2rc3:

*   The deprecated RenderedWebElement interface has now been removed. Most of the functionality has been moved to either [WebElement](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html) or to the [Actions](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/interactions/Actions.html) class.
*   The deprecated WebElement.getValue() method has been removed. Use [WebElement.getAttribute(“value”)](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html#getAttribute(java.lang.String)) instead.
*   After some debate in the team, “[WebElement.setSelected](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html#setSelected())” and “[WebElement.toggle](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html#toggle())” have been deprecated. They will be removed in the final release.
*   Thanks to the hard work of Mozilla engineers, we now offer Firefox 5 support.
*   The [Opera driver](http://www.opera.com/developer/tools/operadriver/), developed by the lovely chaps at Opera Software, is bundled with this release.
*   Improvements in the way that mouse interactions are simulated, particularly when elements are outside the visible area of the page.

As with almost all releases, there are still some issues left to resolve, but we’re working hard to make Selenium 2.0 as good as it can be.