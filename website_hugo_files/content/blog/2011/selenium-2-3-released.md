---
title: "Selenium 2.3 Released"
linkTitle: "Selenium 2.3 Released"
date: 2011-08-02
tags: ["selenium"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Continuing our new tradition of weekly releases, we’re very pleased to announce the release of Selenium 2.3.
---

Continuing our new tradition of weekly releases, we’re very pleased to announce the [release of Selenium 2.3](http://seleniumhq.org/download/). The release notes for this version are pretty slim:

*   Better detection of clickable areas in Firefox.
*   Merge of Google-contributed code into the underlying javascript libraries used by the drivers.

We’ve also fixed bugs, one of which was being a nuisance for users of IBM’s JRE. The complete changelog can be found in the zipped downloads or in [our source tree](http://selenium.googlecode.com/svn/trunk/java/CHANGELOG).

If you’re a Chrome user, then it’s a great idea to head over to the [Chromium project’s download page](http://code.google.com/p/chromium/downloads/list) to pick up the executable used by the ChromeDriver. It’s recently been updated, and now includes support for handling alerts and prompts! Thanks, Google!