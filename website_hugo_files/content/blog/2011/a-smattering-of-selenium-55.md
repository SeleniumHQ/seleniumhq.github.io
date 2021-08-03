---
title: "A Smattering of Selenium #55"
linkTitle: "A Smattering of Selenium #55"
date: 2011-07-18
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Last week we released Se-IDE 1.1.0 which now features WebDriver formats and Se 2.1.0 was released about an hour ago.
---

Last week we released Se-IDE 1.1.0 which now features WebDriver formats and Se 2.1.0 was released about an hour ago. Simon will post something shortly-ish on what’s changed.

*   I haven’t messed around with HTML5 goodies yes, but [fake-html5](http://code.google.com/p/fake-html5/) seems like it could be interesting.
*   The Yii framework [has grown WebDriver support](http://www.yiiframework.com/extension/webdriver-test/). Now if only there were not two competing PHP implementations for them to have to choose from…
*   If you are using Python’s native packaging system to share your framework, then [Lies, More Lies and Python Packaging Documentation on \`package\_data\`](http://blog.codekills.net/2011/07/15/lies,-more-lies-and-python-packaging-documentation-on--package_data-) could spare you some headache
*   This presentation kinda needs someone in front of it to make it fully understandable, but they chose great photos so I’m including it.
    
    **[Selenium for Designers](http://www.slideshare.net/fabio.fabbrucci/selenium-for-designers "Selenium for Designers")**
    
    View more [presentations](http://www.slideshare.net/) from [Fabio Fabbrucci](http://www.slideshare.net/fabio.fabbrucci)
    
*   [Sikuli on Selenium- A demonstration of automation using selenium and Sikuli (such as flash uploader)](http://mubbashir11.blogspot.com/2011/07/sikuli-on-selenium-demonstration-of.html) uses Sikuli where one might normally have used AutoIT but can’t get a handle onto the window. ….And with that use case for Sikuli arrives.
*   Ever wondered what you get when you cross alcohol and Se? Wonder no more.  
    
*   Visual Studio seems like overkill for Python work, but if thats your cup o’ tea then [Python Tools for Visual Studio](http://pytools.codeplex.com/) is for you.
*   Creating users for the duration of a run is a problem a lot of systems have. But not with Facebook which has a [Test Users](https://developers.facebook.com/docs/test_users/) API it seems. Don’t forget that you can use this idea internally in your apps too.
*   With the release of Selenium 2, the project is focuses not on being a browser _test_ platform, but a browser _automation_ one. The difference can be subtle but one area of big difference is in terms of network information details. Selenium RC has support for it, but Selenium WebDriver does not. And of course the latter is the future of the project. 99% of the time, you really _don’t_ need the network information, but in that other 1% the official response to the problem is use something like [the BrowserMob Proxy](https://github.com/lightbody/browsermob-proxy) which also had [Ruby bindings](https://github.com/jarib/browsermob-proxy-rb) released this week.