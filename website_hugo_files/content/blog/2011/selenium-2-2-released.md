---
title: "Selenium 2.2 Released"
linkTitle: "Selenium 2.2 Released"
date: 2011-07-26
tags: ["selenium"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  The feedback from the last release was heard loud and clear: little and often it is!
---


The feedback from the last release was heard loud and clear: little and often it is!

We’re proud to announce the release of [Selenium 2.2](http://seleniumhq.org/download/). What’s new this time? For many users, this is simply a bug fix release as there are no new major features. One thing you might appreciate is [better exceptions](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/IllegalLocatorException.html) being thrown when xpath searches return something other than a web element when using the WebDriver APIs, and we’re continuing to tweak the emulation of user events.

If you’re a .Net user, there is now an official [NuGet package](http://nuget.org/List/Packages/Selenium.WebDriver), and if you’re a maven user then rest assured the release is heading to the central repo as quickly as we can manage.
