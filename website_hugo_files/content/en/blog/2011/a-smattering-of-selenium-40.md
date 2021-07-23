---
title: "A Smattering of Selenium #40"
linkTitle: "A Smattering of Selenium #40"
date: 2011-02-07
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  You would think by now that I wouldn’t be surprised by the number of links I collect in a week.
---

You would think by now that I wouldn’t be surprised by the number of links I collect in a week.

*   The big news last week is that we (Selenium) have successfully avoided the whole Hudson/Jenkins drama by joining the [Software Freedom Conservancy](http://sfconservancy.org/).
    *   [Se Announcement](https://seleniumhq.wordpress.com/2011/02/02/selenium-joins-the-software-freedom-conservancy/)
    *   [Conservancy Announcement](http://sfconservancy.org/news/2011/feb/02/selenium-joins)
*   Speaking of Hudson/Jenkins, here is how [to upgrade a Hudson install to Jenkinss](http://wiki.jenkins-ci.org/display/JENKINS/Upgrading+from+Hudson+to+Jenkins)
*   I don’t like a lot of the messaging of the whole ‘Lean Startup’ scene, but they have some things to steal though. [Is Deploying to Production 50x/Day a GOOD Idea?](http://www.thehackerchickblog.com/2011/02/continuous-deployment-for-continuous-learning.html) lists some
    *   Immune Systems
    *   Visibility of Changes
    *   Release is a Marketing Term
*   [Finding Usability Bugs with Automated Tests](http://queue.acm.org/detail.cfm?id=1925091) covers automation to discover Layout and Navigation accessibility and usability problems through automation
*   [Watir Day](http://watir.com/watir-day/) it the day before Selenium Conf; come hang out and learn about our Ruby sibling
*   [How to use javascript-xpath](http://stackoverflow.com/questions/2536652/how-to-use-javascript-xpath) is one of those rare SO questions I stumble on that actually provides insight to a rare corner of the API
*   The [PHPUnit docs](http://www.phpunit.de/manual/current/en/writing-tests-for-phpunit.html#writing-tests-for-phpunit.data-providers) have been updated to include an example of a Data Provider that returns an Iterator object. This caused me a half day of pain so is getting a link. (Data Providers are awesome btw. Not just in PHPUnit, but in xUnit frameworks.)
*   The first half of [Regular Expressions and Pattern Matching with BrowserMob and Selenium](http://blog.browsermob.com/2011/02/regular-expressions-and-pattern-matching-with-browsermob-and-selenium/) is only going to be useful to you if you are a BrowserMob VU user, but the second half is interesting or _very_ important depending on how crazy your site is to automate. If you are using XPath and not doing starts-with, ends-with or contains you _are_ writing brittle locators.
*   [Starting Test Automation for a Legacy Project](http://www.infoq.com/news/2011/01/testing-legacy-application) is a summary of a thread on the Agile Testing mailing list
*   The demo code in [DDD9 – Slides and thoughts](http://www.theautomatedtester.co.uk/blog/2011/ddd9-slides-and-thoughts.html) has examples of using Page Objects for C#
*   [splinter](http://splinter.cobrateam.info/) seems to be one of the first projects to wrap around / build upon Se2