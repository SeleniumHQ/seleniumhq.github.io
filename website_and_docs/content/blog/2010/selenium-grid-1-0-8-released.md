---
title: "Selenium Grid 1.0.8 Released"
linkTitle: "Selenium Grid 1.0.8 Released"
date: 2010-06-10
tags: ["selenium","grid"]
categories: ["releases"]
author: Kevin Menard ([@nirvdrum](https://twitter.com/nirvdrum))
description: >
  We’re pleased to announce the release of Selenium Grid 1.0.8
---

We’re pleased to announce the release of Selenium Grid 1.0.8. This release fixes multithreaded issues with the Selenium Grid hub that appeared randomly under heavy load. If you’ve ever seen a log message about HttpClient being accessed by multiple threads, you definitely want to upgrade. Even if you haven’t, this release is highly recommended for all.

You can [download it now](http://release.seleniumhq.org/selenium-grid/selenium-grid-1.0.8-bin.zip) or [view the changelog](http://github.com/nirvdrum/selenium-grid/blob/master/ChangeLog). The list of changes is:

*   Multi-threaded issue with access to HttpClient has been resolved, fixing random crashes on a heavily loaded grids

Many thanks go out to Chris Gulley for identifying and fixing the problem. As always, patches and bug reports are appreciated:

*   [Code repository](http://github.com/nirvdrum/selenium-grid/)
*   [Issue tracker](http://code.google.com/p/selenium/issues/list)

I mentioned in the release notes for 1.0.7 that barring any major issues, 1.1 would be the next release. This fix was major enough to warrant an intermediate release. We are still planning to put out 1.1 as the next feature release.

If you have any questions about Selenium Grid, please use either the user or the developer list, as is most appropriate for the nature of your question. Ongoing discussion about grid development should take place on the developer list.

