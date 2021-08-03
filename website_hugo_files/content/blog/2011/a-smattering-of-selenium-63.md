---
title: "A Smattering of Selenium #63"
linkTitle: "A Smattering of Selenium #63"
date: 2011-10-03
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Watir to WebDriver: Unit Test Frameworks – Well, its ‘big’ news.
---

*   [Watir to WebDriver: Unit Test Frameworks](https://www.facebook.com/notes/facebook-engineering/watir-to-webdriver-unit-test-frameworks/10150314152278920) – Well, its ‘big’ news. Of course, Watir can use WebDriver so they didn’t _have_ to port their scripts to a different API (I sure hope they have the API well abstracted away from their scripts). And of course the title of the post implies that Facebook thinks that Watir/Selenium scripts are ‘Unit’ tests which is suspect at best…
*   [Selenium tests for Jenkins](https://github.com/rtyler/jenkins-selenium-tests) — Yup; Jenkins now has a set of Se scripts for it. Fork, modify and sent pull requests.
*   [Thucydides](http://thucydides-webtests.com/) appears to be another full-feature framework built around WebDriver
*   [NativeDriver and iOS: First Impressions](http://electronicingenuity.com/nativedriver-ios-first-impressions) looks to be the first of a series of posts about automating mobile apps
*   [Managing Locator Builders in Selenium IDE](http://blog.reallysimplethoughts.com/2011/09/30/managing-locator-builders-in-selenium-ide/) introduces a new feature that we snuck into the most recent version of Se-IDE. Still needs a bit of polish, but we’re getting there.
*   [The evils of \`except:\`](http://blog.codekills.net/2011/09/29/the-evils-of--except--) is a good reminder about naked excepts in your code. Doubly true in automation where you _really_ want to have exceptions bubbling to the top.
*   [Adding Mozmill tests to the Selenium IDE build system](http://blargon7.com/2011/09/adding-mozmill-tests-to-the-selenium-ide-build-system/) walks you through how to add Mozmill tests to a CI environment. A lot of Se-IDE plugins are really only testable through Mozmill.
*   So who is going to buy me a [Keep Calm and Continue Testing](http://www.bengarvey.com/2011/09/23/37signals-sent-me-a-gift-for-pwning-their-leaderboards/) t-shirt.
*   And while we’re at it, who is going to add Se to the [Ubuntu Software Center](http://developer.ubuntu.com/)
*   This issue’s debate is [At Least Three Good Reasons for Testers to Learn to Program](http://www.developsense.com/blog/2011/09/at-least-three-good-reasons-for-testers-to-learn-to-program/) vs. [At Least 3 Reasons for Software Testers NOT to Learn to Code](http://www.zacharyspencer.com/2011/09/at-least-3-reasons-for-software-testers-not-to-learn-to-code/). Of course, if you are doing Se stuff, that alone is reason to learn to code — automation _is_ programming.