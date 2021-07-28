---
title: "A Smattering of Selenium #17"
linkTitle: "A Smattering of Selenium #17"
date: 2010-06-28
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Here are the posts dealing with Se, and/or automation in general that caught my eye and interest.
---

Here are the posts dealing with Se, and/or automation in general that caught my eye and interest.  

*   [Selenium Unit Test Reuse](http://www.codekoala.com/blog/2010/selenium-unit-test-reuse/) illustrates one way of iterating through environments and browsers in a script without using Se-Grid. I’ve done similar tricks to this to some success — the problem is usually reporting though, but you are all clever folks so I’m sure you could add it in.
*   Adding a custom header is sometimes required when writing a script, but its not really that documented so Kevin wrote [How to Perform Basic Authentication in Selenium](http://mogotest.com/blog/2010/06/23/how-to-perform-basic-auth-in-selenium) which combines how to do basic auth _and_ header injection.
*   Not running your scripts inside CI yet? Shame on you! Hudson creator Kohsuke Kawaguchi [gave a talk at Digg](http://about.digg.com/blog/digg-technical-talks-kohsuke-kawaguchi) which they recorded. I haven’t watched it yet, but Se gets a mention according to the notes at the bottom.
*   Haven’t heard of the HAR (HTTP ARchive) format? Don’t worry, I hadn’t either until [a bit of a discussion about the captureNetworkTraffic](http://groups.google.com/group/selenium-developers/browse_thread/thread/aafbe61c46ffa0df) was had and its future in Se
*   The Faker/Sham combination is established in the Ruby (and Perl) world as the way to generate random data of a prescribed format. Anthony has release a similar module for Python with some pythonic twists called [Picka](http://github.com/antlong/Picka) (as in ‘Pick a card, any card’ erm, well, name or address actually)
*   Looking for an example of Page Objects in C#? I was last week and Dave convienently enough posted a [page object pattern tutorial](http://www.theautomatedtester.co.uk/tutorials/selenium/page-object-pattern.htm)
*   [Adieu to QTP. Now for a closer look at Selenium](http://www.testjutsu.com/adieu-to-qtp-now-for-a-closer-look-at-selenium) makes the list just because it uses ‘QTP’ and ‘Chinese bile farm’ in the same sentence.
*   Browsermob opensourced [sep4j](http://github.com/lightbody/browsermob-sep4j) (which I think stands for ‘Se Parallel for Java’) which is a _Collection of utilities for Java-based projects to enable Selenium test parallelization using Selenium Grid or Sauce  
    Labs._
*   Richard presented at the LJC Unconference on ‘Agile Acceptance Testing with Cucumber, Cuke4Duke, Groovy & Selenium’ and has [posted his slides](http://www.rapaul.com/2010/06/26/agile-acceptance-testing-slides/)
*   Speaking of acceptance tests, Gojko has a post on the [Anatomy of a good acceptance test](http://gojko.net/2010/06/16/anatomy-of-a-good-acceptance-test/)
*   Last thing on acceptance tests for this week is actually multiple things. Markus recently did a Weekend Testers event using RobotFramework and ParkCalc and has started a series of posts about it: [Getting Started](http://blog.shino.de/2010/06/20/parkcalc-automation-getting-started/), [ParkCalc automation – Refactoring a data-driven test](http://blog.shino.de/2010/06/24/parkcalc-automation-refactoring-a-data-driven-test/) and [ParkCalc automation – Refactoring a keyword-driven test](http://blog.shino.de/2010/06/26/parkcalc-automation-refactoring-a-keyword-driven-test/) are the first three posts in it. Hopefully there will be more too.
*   And I’ll finish with one of my posts from this week which was on how to [deal with pesky file downloads in Se](http://element34.ca/blog/dealing-with-file-downloads-with-selenium). (hint: don’t use Se for downloading files)