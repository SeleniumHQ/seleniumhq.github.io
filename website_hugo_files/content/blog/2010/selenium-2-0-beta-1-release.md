---
title: "Selenium 2.0 beta 1 Release"
linkTitle: "Selenium 2.0 beta 1 Release"
date: 2010-12-24
tags: ["selenium","beta"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  We recently released the first of the betas for Selenium 2.
---

We recently released the first of the betas for Selenium 2. It’s available for Java, C# and Ruby. If you’ve been holding off trying Selenium 2 because of the alpha label, then the biggest improvement you’ll see is with the new WebDriver APIs, but there’s a lot more!

*   A promise of relatively stable APIs
*   For Firefox only right now, an API for dealing with [alerts, prompts and confirms](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriver.TargetLocator.html#alert()).
*   A brand new IE driver.
*   Better [selenium emulation](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebDriverBackedSelenium.html) when using webdriver
*   And a better implementation of webdriver’s API backed by the [traditional Selenium technology](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/SeleneseCommandExecutor.html).
*   Ubiquitous use of [Sizzle](http://sizzlejs.com/) for emulating CSS selectors where native CSS selectors aren’t supported
*   The [advanced user interactions](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/interactions/package-frame.html) API
*   An update to the AndroidDriver’s Android app.

Of course, we’d love this release to be completely bug free, but this is, after all, a beta, so there are some known issues:

*   The selenium 1.x emulation using IE is a little flaky.
*   The ChromeDriver is not as capable as the others.
*   Anything in our [bug list](http://code.google.com/p/selenium/issues/list).

You can download it from here:

[http://code.google.com/p/selenium/downloads/list](http://code.google.com/p/selenium/downloads/list)

You can read the [javadocs](http://selenium.googlecode.com/svn/trunk/docs/api/java/index.html) and the [ruby docs](http://selenium.googlecode.com/svn/trunk/docs/api/rb/index.html).

An obvious question is “When will the beta end?” The short answer is when we’ve implemented the alerts and prompts and advanced user interactions APIs in all supported browsers. We expect there to be some flex in some APIs (removing deprecated methods, and within the advanced user interactions API) but what you have here is basically what you’re going to get when we hit 2.0 final. I have no idea how long this will take, but if you’re interested in helping out, [let us know!](http://groups.google.com/group/selenium-developers)

Thanks are due to all the committers who have worked so hard to get this code into shape. Thanks are also due to all the people who have taken the time to file bugs, ask for features and participated on the mailing lists. Thank you to you too, for going out and trying this new beta of Selenium 2.

You rock. 🙂