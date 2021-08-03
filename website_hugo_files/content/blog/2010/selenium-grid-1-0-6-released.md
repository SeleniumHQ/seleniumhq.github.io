---
title: "Selenium Grid 1.0.6 Released"
linkTitle: "Selenium Grid 1.0.6 Released"
date: 2010-04-13
tags: ["selenium","grid"]
categories: ["releases"]
author: Kevin Menard ([@nirvdrum](https://twitter.com/nirvdrum))
description: >
  Following up on the Selenium Grid 1.0.5 release, we’re pleased to announce the release of Selenium Grid 1.0.6
---

Following up on the Selenium Grid 1.0.5 release, which added [self-healing features](http://selenium-grid.seleniumhq.org/self-healing.html) to ease grid management, we’re pleased to announce the release of Selenium Grid 1.0.6. This is a bug fix release for 1.0.5 with some minor new additions.

You can [download it now](http://release.seleniumhq.org/selenium-grid/selenium-grid-1.0.6-bin.zip) or [view the changelog](http://github.com/nirvdrum/selenium-grid/blob/master/ChangeLog). The summary of changes are:

*   Fixed some concurrent modification issues that may have resulted in NullPointerExceptions
*   Fixed issue with shutting down workers with Rakefile
*   Added PID file support for hub and worker started via Rakefile, making it easier to use a process monitor such as monit

In case you missed the announcement, Philippe is [no longer maintaining](http://ph7spot.com/blog/selenium-grid-needs-a-new-maintainer) Selenium Grid. We thank him for his years of effort and helping the project get to where it is today. This is my first release as the [new Selenium Grid maintainer](http://ph7spot.com/blog/new-selenium-grid-maintainer). I will be getting out a new post shortly that lays out the future for the project _\[UPDATE: The [promised post](/blog/2010/the-future-of-selenium-grid/) is live\]_. In the meanwhile, please note the new location of several key resources:

*   [Code repository](http://github.com/nirvdrum/selenium-grid/)
*   [Issue tracker](http://code.google.com/p/selenium/issues/list)

If you have any questions about Selenium Grid, please use either the user or the developer list, as is most appropriate for the nature of your question. Ongoing discussion about grid development should take place on the developer list. We look forward to hearing what you think about how we can improve the project.
