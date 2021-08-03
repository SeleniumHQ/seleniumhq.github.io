---
title: "Selenium 2.0: Out Now!"
linkTitle: "Selenium 2.0: Out Now!"
date: 2011-07-08
tags: ["selenium"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  We are very, very pleased to announce the release of Selenium 2.0.
---


We are very, very pleased to announce the [release of Selenium 2.0](http://seleniumhq.org/download/). If you’ve been waiting for a stable release since 1.0.3, now’s the chance to update. And if you do, what will you find?

For users of Selenium 1, this is a drop-in replacement. You’ll find support for modern browsers such as [Firefox 5](http://www.mozilla.com/en-US/firefox/new/) and [IE 9](http://windows.microsoft.com/en-GB/internet-explorer/products/ie/home), as well as a wealth of bug fixes and stability improvements.  That’s one reason to update, but what other reasons are there?

The big feature of this release — and the reason for the new version number — are the new WebDriver APIs for [Python](http://pypi.python.org/pypi/selenium), [Ruby](http://rubygems.org/gems/selenium-webdriver), [Java and C#](http://code.google.com/p/selenium/downloads/list). These have been in development for over four years, and are already widely used, trusted and depended on. The WebDriver APIs have been written by developers familiar with each language, so they feel like they belong there. We’re very proud of them, and hope you enjoy using them.

Support for WebDriver is also baked into [Opera](http://www.opera.com/) and [Chrome](http://www.google.com/chrome), and we’re working closely with [Mozilla](http://www.mozilla.org/) to ensure that their browsers also support it. Looking to the future, WebDriver also works on both Android and iPhone, allowing you to test your sites on the next wave of the Web.

As well as support by the browser vendors, WebDriver also provides excellent emulation of user inputs using something we call “native events”. Normal browser automation frameworks, including older versions of Selenium, simulate user interactions via the Javascript engine of the browser. This approach is error prone as each browser has its own quirks. “Native events” are fired at the OS level instead, avoiding a large amount of browser-specific complexity.

Advanced Selenium users will be pleased to hear that the standalone selenium server also includes support for distributed testing via [Selenium Grid](http://code.google.com/p/selenium/wiki/Grid2). This new Grid implementation supports testing using both the original Selenium API and WebDriver, and has been developed as a collaboration between the current Grid maintainer and an engineer from eBay.

We’re working hard to ensure that Selenium IDE also supports all these new features and APIs. IDE version 1.1.0 should be released next week, with support for exporting to the four main languages supported by WebDriver. Please keep an eye on this blog for announcements! There will also be follow up posts, exploring and explaining each of the new features, and providing you with more information.

Of course, Selenium 2.0 is a major milestone, but we’re not done yet. This release marks the point where we expect our APIs to change very little from now on and where we believe it’s a solid release. Like all software, it has niggles and bugs, and we’ll be focusing on addressing these as your feedback comes in.

As a personal note, I’d like to say thank you to each of the many people that have worked so hard to make this the best Selenium version yet. Not only the developers but also the team working on making our [documentation](http://seleniumhq.org/docs/) clear and easy to read, everyone who’s taken the time to [report bugs](http://code.google.com/p/selenium/issues/list), and also to you; our [users](https://groups.google.com/group/webdriver) and [community](https://groups.google.com/group/selenium-users). The project is great fun to work on, and you’re the reason for that. Thank you!