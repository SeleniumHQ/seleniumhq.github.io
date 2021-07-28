---
title: "A Smattering of Selenium #21"
linkTitle: "A Smattering of Selenium #21"
date: 2010-07-26
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  I thought it had been a slow week — until I looked at how many browser windows I had open.
---

I thought it had been a slow week — until I looked at how many browser windows I had open. I wonder if that was why things were feeling a bit sluggish…  

*   Some bits of history from Simon Stewart
    *   [First announcement of Selenium](http://www.io.com/~wazmo/blog/archives/2004_10.html#000224) (outside of Thoughtworks)
    *   [First announcement of WebDriver](http://groups.google.com/group/webdriver/browse_thread/thread/f2f44ac9ccb42e66) (again, outside of Thoughtworks)
    *   [The ‘merger’ announcement](http://groups.google.com/group/webdriver/browse_thread/thread/b089f9a193e4eec9) (aka Selenium 2)
*   More history lessons on [Cloud Computing Show #35](http://cloudcomputingshow.blogspot.com/2010/07/cloud-computing-show-35.html) where Jason recounts the origin of Se (and other stuff)
*   I still maintain that Continuous Deployment _to production_ is near unethical, but a lot of the tricks that companies are using to support it are pretty cool. [Continuous Deployment, Code Review and Pre-Tested Commits on Digg4](http://about.digg.com/blog/continuous-deployment-code-review-and-pre-tested-commits-digg4) details some that Digg uses. Only cursory mention of Se, but once you have Se running inside a CI server of some sort, these thoughts become interesting-er.
*   [The Kitchen Sink – why not all tests need automating](http://a-sisyphean-task.blogspot.com/2010/05/kitchen-sink-why-not-all-tests-need.html) has things like _Just because we can automate a test doesn’t mean that we always should_. As a consultant, I need to get this point across to clients all the time. Every. Single. Day.
*   At the risk of being too meta, there is now [Selendion](http://code.google.com/p/selendion/) which looks like it drives Concordian with Se
*   [Frank](http://blog.thepete.net/2010/07/frank-automated-acceptance-tests-for.html) is billing itself as ‘Selenium for iOS’. Except that I believe Se2 already has support for that.
*   [Moving on to the second example](http://mountaintroll.blogspot.com/2010/07/moving-on-to-second-example.html) is part of a series of posts about exploration of Se2 using Ruby. This particular post is the ruby-fication of the second example of the WebDriver tutorial.
*   [Debugging XPath and CSS locators with Selenium IDE](http://blog.codecentric.de/en/2010/07/debugging-xpath-and-css-locators-with-selenium-ide/) originated with a question on the RobotFramework-Users mailing list and ended up being a screencast. We should do more of those.
*   [Boomerang](http://yahoo.github.com/boomerang/doc/) seems like a project that someone should write a tutorial on integrating with Se
*   [Review: TDD Screencasts](http://odoe.net/blog/?p=87) is a review of Kent Beck’s new TDD Screencast series (I really should buy this). While yes, we typically don’t do TDD using Se for speed reasons, this paragraph resonated and since I do the list, I’m including it.  
      
    _A great message I got from the screencasts is that your tests should tell a story. You compose that narrative in steps, so as to not trip yourself up. Many times you are probably required to develop against a backend service or directly with a database. So that’s how the story starts. Hello Mr. Database, are you there? May I put something in you?_
*   If you are a PHP developer and using NetBeans, then [Testing with PHPUnit and Selenium](http://netbeans.org/kb/docs/php/phpunit.html) might be of interest.
*   [Integrating Selenium Tests into CruiseControl.Net via NUnit](http://multitiered.wordpress.com/2010/07/25/integrating-selenium-tests-into-cruisecontrol-net-via-nunit/) is what it claims
*   [Avoiding Brittle Element Selection with Selenium2](http://www.rapaul.com/2010/07/18/avoiding-brittle-element-selection-with-selenium2/) tackles a constant topic using Se2 — but it can be applied to Se1 as well