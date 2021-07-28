---
title: "Mobile WebDriver"
linkTitle: "Mobile WebDriver"
date: 2013-08-28
tags: ["selenium", "mobile"]
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Although the WebDriver APIs started life as just a mechanism for automating web browsers, over the past few years it has been extended to also work on mobile devices.
---

Although the WebDriver APIs started life as just a mechanism for automating web browsers, over the past few years it has been extended to also work on mobile devices. Projects such as Appium, iosdriver, and Selendroid have all shown that this approach works, and works well. On the Web, if you start using Selenium WebDriver with one browser (Firefox, for example), it’s easy to switch out the browser for another one (such as Internet Explorer or Chrome). It’d be nice to have a similar option for mobile, switching from one automation framework for Android to another.

As part of the Selenium 3 work, we have started working on a test suite to help ensure this level of interop between [appium](http://appium.io/) and [iosdriver](http://ios-driver.github.io/ios-driver/), and appium and [selendroid](http://selendroid.io/). To kick start the process, the primary authors of each of those tools, as well as others including David Burns representing the [Marionette](https://developer.mozilla.org/en-US/docs/Marionette) project (Mozilla’s implementation of WebDriver for Firefox and Firefox OS) and Simon Stewart, the lead of the [Selenium](http://seleniumhq.org/) project, have spent the past two days locked in a small room in Mozilla HQ, London. They’ve taken this time to work out the areas where each of their projects didn’t align and agreed on a way to ensure a level of interoperability. There was only a minimal quantity of blood and tears, but plenty of hard work.

The [agenda](https://docs.google.com/document/d/1rnE13aGCaRiri01hti7j1jWDuPvQHT8aao4bHhEGz8Y/edit) for the past two days can be found here, and the [minutes](https://docs.google.com/document/d/1yXXsQo3z7lUVl3ZthAx39h4xBlF62x7q_NZd3NA9jnU/edit) are also available.

As we speak, work has started on a shared test suite, hosted in a [repo](https://code.google.com/p/selenium/source/checkout?repo=mobile) in the selenium project’s Google Code page. Please, feel free to come along and join in!