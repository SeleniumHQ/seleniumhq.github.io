---
title: "Selenium 2.9 Released"
linkTitle: "Selenium 2.9 Released"
date: 2011-10-20
tags: ["selenium"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  We’re pleased to announce the release of Selenium 2.9.
---

We’re pleased to announce the release of [Selenium 2.9](http://seleniumhq.org/download/). As well as improving the stability of Grid under high loads, we’ve added an experimental (and temporary!) feature that allows the Firefox driver to use a different mechanism for determining when a page is loaded. This is designed to handle the case where the driver appears to hang, as it’s waiting for all incoming requests to complete before continuing. How to enable this mode is listed in the [changelog](http://code.google.com/p/selenium/source/browse/trunk/java/CHANGELOG).

This release also includes an updated [Android driver](http://code.google.com/p/selenium/downloads/detail?name=android-server-2.9.apk), allowing your tests to run on Ice Cream Sandwich devices.