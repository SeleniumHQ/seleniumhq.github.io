---
title: "The Road to Selenium 3"
linkTitle: "The Road to Selenium 3"
date: 2013-08-28
tags: ["selenium"]
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Selenium 2 was released in July 2011...
---

Selenium 2 was [released in July 2011](http://seleniumhq.wordpress.com/2011/07/08/selenium-2-0/). It’s now two years old, and what a couple of years it’s been! The WebDriver APIs, which were the major addition in Selenium 2, are now the basis for a [W3C standard](http://www.w3.org/TR/webdriver/), and there are implementations written and supported by Google, Mozilla and Opera. There have been 34 releases, with official support for Java, C#, Python, Ruby and Javascript, and the community has stepped in to provide bindings for Perl, PHP and others. There have been 57 different people authoring changes in the code base, and countless more participating in the online forums, offering help and advice.

While all this has been happening, the world has moved on, and now it’s time for the Selenium project to look to the future. It’s with great pleasure that I can now say that we’re working towards Selenium 3.

We aim for Selenium 3 to be “a tool for user-focused automation of mobile and web apps”.

What does this mean? For mobile users, the Selenium project will be hosting a suite of tests to facilitate interoperability between the many different projects available that are extending the WebDriver API to also cope with mobile. Developers from projects such as [Appium](http://appium.io/), [ios-driver](http://ios-driver.github.io/ios-driver/) and [selendroid](http://selendroid.io/) will be working on the suite of tests to enable this.

We’ll also be working on making the technology behind Selenium as stable and capable as possible. For this reason, Selenium 3 will see the removal of the original Selenium Core implementations, and consequently we’ll be deprecating the RC APIs too. The old versions will still be available as a separate download, but active development will cease, except for very urgent fixes. We will still be providing an implementation of the RC APIs backed by WebDriver, so you can continue running your existing tests, but now would be a great time to make the move to using the WebDriver APIs directly.

For those of you exporting your tests from IDE and running the HTML suites, we’ll provide an alternative runner that allows you to continue running those tests too, though it’ll be backed by the same “WebDriver-backed” RC implementation as offered by the main download. Again, the original implementation will be available as a download, but it will no longer be actively developed once we release 3.0.

Our current plan is to start shipping 3.0 by Christmas this year: it’s going to be a lot of fun!