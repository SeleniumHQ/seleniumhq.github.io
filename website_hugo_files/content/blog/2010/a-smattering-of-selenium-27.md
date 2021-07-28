---
title: "A Smattering of Selenium #27"
linkTitle: "A Smattering of Selenium #27"
date: 2010-09-20
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Seems I skipped a week, but that’s okay since there hasn’t been much in terms of volume...
---

Seems I skipped a week, but that’s okay since there hasn’t been much in terms of volume (or maybe my clever search filter is a bit overly clever…).  

*   The big news I think is that there is now a Se driver for Node.js called [Soda](http://sodajs.com/). I’m not sure I like the syntax, but I also don’t claim to know Node so it could be idiomatically correct which is more important than my blessing.
*   Hot on the heels of the Soda announcement was the announcement that [it has built-in support for Sauce Labs’ OnDemand](http://saucelabs.com/blog/index.php/2010/09/cross-browser-testing-with-soda-and-selenium/) Se-in-the-cloud service
*   TestNG is often the java runner of choice for Se (for its built-in parallelization stuff) and it now has a [Selenium page right in the official documentation](http://testng.org/doc/selenium.html).
*   Drupal sites have long used Se to automate maintenance tasks and such, but now there is [Drunit](http://drupal.org/project/drunit) which hopes to replace SimpleTest as the way to use PHPUnit and Se-RC in the Drupal community.
*   The August SFSE meetup was more of a crowd-sourced content affair with participants voting up questions that they wanted to hear Se inventor Jason Huggins answer. It then turned into a crowd-sourced answer affair with Simon Stewart (Se-WebDriver), Kevin Menard (Se-Grid) and myself (Se-IDE and general opinionated tester) getting dragged in periodically. The [video is now online](http://saucelabs.com/blog/index.php/2010/09/sfse-video-selenium-best-practices-with-jason-huggins/) for your viewing enjoyment.
*   I’ll be writing about this more this week I’m sure, but [Tracking Selenium Commands Using C# and SQL Server 2008](http://multitiered.wordpress.com/2010/09/13/tracking-selenium-commands-using-c-and-sql-server-2008/) has actually made me rethink my position on what reporting should be coming out of your automation run. No mean feat that.
*   [Three Keys to Automation](http://www.stickyminds.com/sitewide.asp?ObjectId=2084&Function=edetail&ObjectType=) is an old article from Bret Pettichord (he of WATIR fame) that somehow crossed my path this week but is as relevant today as it was 10 years ago
*   [SMW System Testing with Selenium](http://semantic-mediawiki.org/wiki/SMW_System_Testing_with_Selenium) is a wiki page (unsurprisingly) about testing the Semantic MediaWiki with Se including slides from a talk on it. Good to see the world domination plans of becoming the default testing framework on projects progressing so nicely.
*   [Testers, Orgs, and the demand for Java jobs](http://blog.versionone.com/blog/versionone/0/0/testers-orgs-and-the-demand-for-java-jobs) nicely echos what I say when I am out at clients who are starting out with Se (or automation in general) – _Testers don’t need to know how to write the code for your product, but they need to know how to best automate their tools and tests which will require understanding and comfort with code._. Also just as important from a product perspective is _Let’s get rid of the titles of ‘developer’ and ‘tester.’_.
*   Not related to Se directly is [Dancer](http://perldancer.org/quickstart) which is the Perl equivalent of Ruby On Rails. If you need a quick site for proof-of-concept or experimentation with Se, this might be the framework for you.
*   For the PHP readers, I present [a discussion on integrating Se with Behat](http://github.com/everzet/Behat/issues#issue/1)