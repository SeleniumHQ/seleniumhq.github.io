---
title: "Selenium Grid 1.0.7 Released"
linkTitle: "Selenium Grid 1.0.7 Released"
date: 2010-05-11
tags: ["selenium","grid"]
categories: ["releases"]
author: Kevin Menard ([@nirvdrum](https://twitter.com/nirvdrum))
description: >
  Following up on the Selenium Grid 1.0.6 release, we’re pleased to announce the release of Selenium Grid 1.0.7
---

Following up on the Selenium Grid 1.0.6 release, which was mostly a bug fix release, we’re pleased to announce the release of Selenium Grid 1.0.7. This is another bug fix release that focuses primarily on how Selenium Grid handles browser sessions.

You can [download it now](http://release.seleniumhq.org/selenium-grid/selenium-grid-1.0.7-bin.zip) or [view the changelog](http://github.com/nirvdrum/selenium-grid/blob/master/ChangeLog). The list of changes are:

*   Hub now instructs remote controls to shutdown a session if the hub is going to remove it due to being idle for too long
*   [Added ability to specify timeout period when waiting for remote controls to become available](http://selenium-grid.seleniumhq.org/configuring-and-tuning.html#changing_maximum_wait_time_for_new_session). Now the client can timeout gracefully and decide what it would like to do next
*   Handle common case of session ID not being found, either because it timed out or was already closed; rather than raising a NullPointerException we now raise a NoSuchSessionException
*   Truncate long command response bodies to 128 characters
*   Retry remote control checks up to three times before deciding that a remote control is unreliable or unresponsive. This prevents aggressive unregistration of hosts that are busy or suffer from network issues

As always, patches and bug reports are appreciated:

*   [Code repository](http://github.com/nirvdrum/selenium-grid/)
*   [Issue tracker](http://code.google.com/p/selenium/issues/list)

Barring any critical issues, the next release of Selenium Grid will be 1.1 and will feature an enhanced Web view as well as a status API. After that, we will be moving from GitHub to Google Code to live alongside the other Selenium projects.

If you have any questions about Selenium Grid, please use either the user or the developer list, as is most appropriate for the nature of your question. Ongoing discussion about grid development should take place on the developer list.