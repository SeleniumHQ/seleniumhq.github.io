---
title: "Selenium 2.0b3: The Next Gen Browser Release"
linkTitle: "Selenium 2.0b3: The Next Gen Browser Release"
date: 2011-03-21
tags: ["selenium","beta"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  It’s been about 5 weeks since the release of beta 2, so we’re very pleased to announce that Selenium 2.0b3 has just been released.
---

It’s been about 5 weeks since the release of beta 2, so we’re very pleased to announce that Selenium 2.0b3 has just been released simultaneously for Java, .Net, Ruby and Python. You can [download it from Selenium HQ](http://seleniumhq.org/download/) or from the [Google Code site](http://code.google.com/p/selenium/downloads/list). This release focused on providing excellent support for the next generation of browsers, particularly IE 9 and Firefox 4, and we think you’ll like what you’ll find.

*   Restructured documentation at [Selenium HQ](http://seleniumhq.org/docs/)
*   An improved user interaction API
    *   Including experimental IME support on Windows.
*   Alerts and prompts handling for IE.
*   Marked the following APIs as obsolete in .Net:
    *   IRenderedWebElement.Hover()
    *   IOptions.Speed
*   Even more improvements to the Java webdriver-backed selenium
    *   We’ll document the migration path before 2.0b4 is out!
*   A significantly faster [Android Driver](http://selenium.googlecode.com/files/android-server-2.0b3.apk)

As well as these changes, there’s also the regular clutch of bug fixes and tweaks. For the number crunchers, there were a total of [331 changes](http://code.google.com/p/selenium/source/list?num=331&start=11749) that landed in the 5 weeks since the last release, with the 5 most active contributors working on each of the different languages supported by Selenium.

In addition to the enormous thanks that go to the developers, I’d like to add a big thank you to the Mozilla engineers who chipped in on the IRC channel at the last minute to help us work through some issues with Core. We’d not have been able to get this release out when we did without their help. Thank you Mozilla!

The next release will be focused on stabilization work, reducing our bug count and adding support for Grid 2.0.