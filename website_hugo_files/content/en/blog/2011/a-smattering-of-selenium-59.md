---
title: "A Smattering of Selenium #59"
linkTitle: "A Smattering of Selenium #59"
date: 2011-09-26
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Its been a month and a half since the last one of these, and the volume of links I have collected illustrates that.
---

Its been a month and a half since the last one of these, and the volume of links I have collected illustrates that. So this week is now a cleanup week.

*   [How Browsers Work: Behind the Scenes of Modern Web Browsers](http://www.html5rocks.com/en/tutorials/internals/howbrowserswork/) seems to be one of those articles that people who automate browsers for a living should be familiar with.
*   [100% Test Coverage](http://cleancoder.posterous.com/100-test-coverage) is always the goal.
*   [Continuous Deployment and Data Visualization](http://marlenacompton.com/?p=2417) reminds us that if some data is good, more is often better.
*   This is for JUnit, but the ideas apply to any runner.
    
    **[JUnit Kung Fu: Getting More Out of Your Unit Tests](http://www.slideshare.net/wakaleo/junit-kung-fu-getting-more-out-of-your-unit-tests "JUnit Kung Fu: Getting More Out of Your Unit Tests")**
    
    <iframe src='https://www.slideshare.net/slideshow/embed_code/5261070' width='425' height='348' scrolling='no' allowfullscreen webkitallowfullscreen mozallowfullscreen></iframe>
    
    View more presentations from [wakaleo](http://www.slideshare.net/wakaleo)
    
*   [Database Cleaner is a set of strategies for cleaning your database in Ruby.](https://github.com/bmabey/database_cleaner) I think you should just let your database get dirty (since that’s what happens in production) but I’ll give that there are scenarios where that’s not desirable.
*   [The Zen of UI Testing with Selenium, Hudson and Sauce Labs](http://blogs.mulesoft.org/the-zen-of-ui-testing-with-selenium-hudson-and-saucelabs/) illustrates a nice switch in the @Before method to run either locally or in the cloud (in this case with Sauce Labs). Limiting your framework to be always in the cloud or always behind your firewall is silly these days.
*   [What’s wrong with Ruby’s test doubles?](http://dougu.tumblr.com/post/6144302027/whats-wrong-with-rubys-test-doubles) has good overview of the types of test doubles there are. Front-end automation should be generous use of the ‘Stub’ brand I think.
*   Automation is programming. And Page Objects are OO. So [How to Design Classes](http://www.ccs.neu.edu/home/matthias/htdc.html) is pretty darn interesting.
*   The [Samuel L Ipsum](http://slipsum.com/) _really_ needs an API so we can use it as a random string generator for automation.
*   For one project I’m working on, I am validating whats in the browser with a JSON feed. [JSON Formatter](http://jsonformatter.curiousconcept.com/) has been a saviour the last week.