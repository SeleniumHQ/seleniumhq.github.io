---
title: "A Smattering of Selenium #44"
linkTitle: "A Smattering of Selenium #44"
date: 2011-03-07
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  What started out as the week of Capybara rounded itself out fairly nicely
---

What started out as the week of Capybara rounded itself out fairly nicely

*   I’m not a fan of using 3rd party abstraction layers (1st party are full win though) but Capybara has a large marketshare so [Capybara (and Selenium) with RSpec & Rails 3: quick tutorial](http://opinionated-programmer.com/2011/02/capybara-and-selenium-with-rspec-and-rails-3) is likely going to be of interest to people.
*   And while on the subject of Capybara, here is a post on [Checking Invisible Elements](http://testobsessed.com/2011/03/01/checking-invisible-elements/) in it.
*   Keeping with the Capybara theme, we have [Configuring User Agents with Capybara + Selenium Webdriver](http://blog.plataformatec.com.br/2011/03/configuring-user-agents-with-capybara-selenium-webdriver/)
*   [Unit tests are overrated](http://devlicio.us/blogs/krzysztof_kozmic/archive/2011/02/28/unit-tests-are-overrated.aspx) is a nice reminder to change _all_ the tests in a system for a change — not just the unit ones. (C’mon, how many of us haven’t been burned and/or responsible for this.)
*   [People-driven Test Automation](http://www.stickyminds.com/sitewide.asp?Function=edetail&ObjectType=COL&ObjectId=16692&tth=DYN&tt=siteemail&iDyn=2) includes this nice blurb
    
    _Successful test automation uses good tests that provide clear test results. A pass or no-pass result must be confident and trustworthy. By focusing on what the system should do rather than how it does it, this can be achieved easily and without breaking existing test cases whenever the logic of the system is changed._
    
*   [Automate Kingdoms Camelot using Selenium](http://wordpressvideos.tv/wordpress-plugins/automate-kingdoms-camelot-using-selenium) reminds me of the days when I had the MUD we played in fully scripted.
*   [How to include dynaTrace in your Selenium Tests](https://community.dynatrace.com/community/display/PUB/How+to+include+dynaTrace+in+your+Selenium+Tests) is an interesting idea. Would be even better using Page Objects that were controlled externally so you don’t have to modify working scripts to enable/disable timings. But that’s an exercise for the reader to do. (Oh, and if their marketing firm wasn’t tweeting about it every day, that would be keen too.)
*   [#SFSE Video: Selenium Problem Solving Sessions](http://saucelabs.com/blog/index.php/2011/03/sfse-video-selenium-problem-solving-sessions/) has, amung other things, a demo of the dynaTrace thing.
*   [User-Extensions With Selenium RC Using Ruby Client Driver (selenium-client)](http://bernardlago.wordpress.com/2011/03/02/user-extensions-with-selenium-rc-using-ruby-client-driver-selenium-client/) is slightly out of date (use selenium-webdriver, not selenium-client) but aside from that, has an important missing bit of the user-extension documentation — how to use them from inside a script.
*   I haven’t watched it, but [From Continuous Integration to DevOps: Co-Evolution of Agile and Automation](http://www.anthillpro.com/html/resources/webinars/From_Continuous_Integration_to_DevOps_Co-Evolution_of_Agile_and_Automation.html) seems interesting. I actually used the phrase “Selenium is the gateway drug to DevOps” the other week.
*   [Jenkins, RVM, and Selenium](http://blog.8thlight.com/articles/2011/3/2/jenkins-rvm-and-selenium) is really about Cucumber, but…
*   [Setting up Selenium server on a headless Jenkins CI build machine](http://www.labelmedia.co.uk/blog/posts/setting-up-selenium-server-on-a-headless-jenkins-ci-build-machine.html) is similar to the 8th light one, but for Selenese
*   [Selenium 2 with Firebug run on FF](http://www.sujitnayak.com/?p=527) shows how to add an extension to a WebDriver profile. Now to write a script for [all these plugins](http://moolya.com/blog/2011/03/04/addon-mindmap-for-testers-from-moolya/)
*   [Building Custom Test Frameworks](http://www.bryancook.net/2011/03/building-custom-test-frameworks.html) is a bit of a cautionary tale
*   The whole where-is-this-binary-hiding-on-this-machine-problem on windows has bit people a number of times, [Test Automation: Checking for Bit-ness](http://www.testingmentor.com/imtesty/2011/03/03/test-automation-checking-for-bit-ness) suggests a solution (in C#)
*   [Functional Tests with Selenium 2.0 and cargo-maven-plugin](http://english.valdemarjr.net/2011/03/04/functional-tests-with-selenium-2-0-and-cargo-maven-plugin/) updates some common documentation around launching the Se server and tests from inside Maven