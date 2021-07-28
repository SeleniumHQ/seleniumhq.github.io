---
title: "Selenium 2.0rc1: The Grid Release"
linkTitle: "Selenium 2.0rc1: The Grid Release"
date: 2011-06-01
tags: ["selenium","beta"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  We’re very happy to announce the first Release Candidate for Selenium 2, available for Java, C#, Ruby and Python.
---


We’re very happy to announce the first Release Candidate for Selenium 2, available for Java, C#, Ruby and Python. The API has been stabilised and the functionality needed for the final 2.0 release is mostly in. We’re going to be working hard to get there as soon as possible, but now’s the perfect time to test the waters and provide us with any feedback you may have! [Grab the downloads from the site](http://code.google.com/p/selenium/downloads/list)!

Highlights:

*   [Grid 2](http://code.google.com/p/selenium/wiki/Grid2): A major feature of this release is Grid 2, an implementation of the Selenium Grid that supports WebDriver’s wire protocol, allowing tests using Selenium WebDriver to be distributed through it. There are some [docs to help you get started](http://code.google.com/p/selenium/wiki/Grid2) on the wiki.
*   New [ChromeDriver](http://code.google.com/p/selenium/wiki/ChromeDriver): Following a complete rewrite of the ChromeDriver, Selenium 2 is now supported natively by the Chrome browser itself. In order to use this, you must download the [chromedriver executable](http://code.google.com/p/selenium/downloads/list) from the Selenium project site.
*   [OperaDriver](http://www.opera.com/developer/tools/operadriver/) support: We’ve bundled the most excellent OperaDriver into the release to make it easy to get started testing with [Opera](http://www.opera.com/browser/).
*   Support for native events in Firefox 4.
*   [Advanced User Interactions](http://code.google.com/p/selenium/wiki/AdvancedUserInteractions): An API that allows you to model complex user interactions, such as clicking on an element, holding the shift key, clicking on three more, and then dragging the four elements to a final destination. The entry point to this API is the [Actions](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/interactions/Actions.html) class.

We’ve also deleted all methods that were deprecated in 2.0b3 and have marked a number of methods and classes (notably [RenderedWebElement](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/RenderedWebElement.html) and [WebElement.getValue](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html#getValue())) deprecated. These will be deleted in the next release.

Known issues:

*   Native events on Linux may not work properly on tests that include alerts and prompts.
*   Mouse actions using the Advanced User Interactions API may not work properly for elements that have to be scrolled into view.

We plan on making our releases more frequent in the run up to 2.0final and polish off the bugs and issues. Stay tuned! This is going to be fun 🙂