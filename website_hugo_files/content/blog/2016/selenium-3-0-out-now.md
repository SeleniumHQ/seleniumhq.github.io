---
title: "Selenium 3.0: Out Now!"
linkTitle: "Selenium 3.0: Out Now!"
date: 2016-10-13
tags: ["selenium"]
categories: ["general","releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  We are very pleased to announce the release of Selenium 3.0.
---


We are very pleased to announce the release of [Selenium 3.0](http://www.seleniumhq.org/download/). If you’ve been waiting for a stable release since 2.53.1, now’s your chance to update. And if you do, here is what you’ll find:

As [we’ve said before](https://seleniumhq.wordpress.com/2016/10/04/selenium-3-is-coming/), for users of the WebDriver APIs this is a drop-in replacement. You’ll find that modern browsers, such as [Chrome](https://chromedriver.chromium.org/downloads) and [Edge](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) will continue to work just as before, and we’ve taken the opportunity to fix some bugs and improve stability. Selenium Grid users may require updates to their configuration as the json config file format has been updated, as have some of command line parameter options, but the upgrade should also be smooth. 

The major change in Selenium 3.0 is we’re removing the original Selenium Core implementation and replacing it with one backed by WebDriver. This will affect all users of the [Selenium RC](http://seleniumhq.github.io/selenium/docs/api/java/com/thoughtworks/selenium/Selenium.html) APIs. For more information, please see the [previous post](https://seleniumhq.wordpress.com/2016/10/04/selenium-3-is-coming/).

A lot has changed in the 5 years between versions 2 and 3. When we [shipped Selenium 2](https://seleniumhq.wordpress.com/2011/07/08/selenium-2-0/), the Selenium project was responsible for providing the driver for each browser. Now, we are happy to say that all the major browser vendors ship their own implementations ([Apple](https://webkit.org/blog/6900/webdriver-support-in-safari-10/), [Google](https://chromedriver.chromium.org/), [Microsoft](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/), and [Mozilla](https://github.com/mozilla/geckodriver/releases)). Because the browser vendors know their browsers better than anyone, their WebDriver implementations can be tightly coupled to the browser, leading to a better testing experience for you.

The other notable change has been that there is now a [W3C specification](https://www.w3.org/TR/webdriver/) for browser automation, based on the Open Source WebDriver. This has yet to reach “recommendation” status, but the people working on it (including members of the Selenium project!) are now focusing on finishing the text and writing the implementations.

Mozilla has been a front-runner in implementing the W3C WebDriver protocol. On the plus side, this has exposed problems with the spec as it has evolved, but it also means that Firefox support is hard to track as their engineering efforts have been forward looking, rather than on supporting the current wire protocol used by Selenium WebDriver. For now, the best advice we can offer is for you to try the latest release of [geckodriver](https://github.com/mozilla/geckodriver/releases) and Selenium together.

These are exciting times for browser automation! Selenium 3.0 is a major release and we’re looking forward to improving things further, as well as tracking the ongoing work of the W3C spec. Our goal is to keep the changes your tests need to deal with to an absolute minimum, to continue preserving the hard work that’s gone into writing your existing tests. 

As a personal note, I’d like to say thank you to each of the many people that have worked so hard to make Selenium 3 possible. That’s not just the developers and contributors to the Open Source project (past and present), but also the engineers from Google, Microsoft, Mozilla, and Apple, and everyone involved with the W3C spec. I’d also like to say thank you to everyone who’s taken the time to report bugs, our users and our community. The project is great fun to work on and you’re the reason for that. A final thank you is due to the Software Freedom Conservancy, who have provided invaluable help with the logistics of running a large OSS project.

   
Happy hacking, everyone! May your tests run fast and true!