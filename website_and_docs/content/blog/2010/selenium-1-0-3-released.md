---
title: "Selenium 1.0.3 Released"
linkTitle: "Selenium 1.0.3 Released"
date: 2010-02-24
tags: ["selenium","selenium-rc"]
categories: ["releases"]
author: Patrick Lightbody ([@plightbo](https://twitter.com/plightbo))
description: >
  Hot off the heals of 1.0.2, we’re releasing Selenium Remote Control 1.0.3!
---


Hot off the heals of [1.0.2](/blog/2010/selenium-1.0.2-released-firefox-3.6-and-snow-leopard-support/), 
we’re releasing Selenium Remote Control 1.0.3. You can [download it now](http://selenium.googlecode.com/files/selenium-remote-control-1.0.3.zip).

There is no functional difference between this version and 1.0.2, other than it is packaged up a little nicer and we’ve clarified the relationship between selenium-server and the client drivers. That is: we are **not** releasing new client drivers with future 1.x releases. The reason is that we locked down the API in version 1.0.1 and so there is no need to push out the same code each time. As such, when you download 1.0.3, you’ll see all the client drivers are labeled version 1.0.1, which is expected.

This release also is zipped up in a way that is compatible with all operating systems. The 1.0.2 release had some reported issues on Windows that have been fixed.

Finally, we also had many requests from [Maven](http://maven.apache.org/) users. While we no longer use Maven to build Selenium, we are including pom releases for both the standalone server (ie: selenium-server.jar) and the “coreless” server (ie: selenium-server-coreless.jar). We hope they will be in the central repository shortly.

