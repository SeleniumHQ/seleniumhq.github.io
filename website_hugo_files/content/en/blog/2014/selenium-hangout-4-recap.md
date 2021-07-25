---
title: "Selenium Hangout 4 Recap"
linkTitle: "Selenium Hangout 4 Recap"
date: 2014-02-07
tags: ["hangout"]
categories: ["governance"]
author: Dave Haeffner ([@TourDeDave](https://github.com/tourdedave))
description: >
  Here’s a recap from the latest Selenium Hangout panel discussion.
---


Here’s a recap from the latest Selenium Hangout panel discussion. To submit questions for future hangouts, you can message us on Twitter ([@seleniumhangout](https://twitter.com/seleniumhangout)) or e-mail us ([questions@seleniumhangout.com](mailto:questions@seleniumhangout.com)).

{{< youtube 3IEqh7NmaEo >}}

**Panel**  
David Burns ([@AutomatedTester](https://twitter.com/AutomatedTester))  
Kevin Menard ([@nirvdrum](https://twitter.com/nirvdrum))  
Dave Haeffner ([@TourDeDave](https://github.com/tourdedave))

**Timeline**

1:30-16:51: How to handle screenshots across different drivers  
_tl;dr driver specific issues due to a lacking standard and missing API_

*   This is getting addressed in World Wide Web Consortium (W3C) spec which is under development
*   E-mail use-cases you would like to see in the spec to [automatedtester@mozilla.com](mailto:automatedtester@mozilla.com)

17:30-22:40 : Internet Explorer (IE) 9 pitfalls and how to avoid them  
_tl;dr use 32-bit IE (if you’re not) and report issues to Selenium Issues_

*   [Selenium Issue tracker](https://code.google.com/p/selenium/issues/list)
*   [A good blog post on how to submit an acceptable Selenium issue](http://jimevansmusic.blogspot.com/2012/12/not-providing-html-page-is-bogus.html)

22:50-28:30 How do you do image and video comparison testing?  
_tl;dr straight image comparison is a flawed strategy, but there’s a better way — also, Selenium’s not the best tool for the job_

Resources mentioned:

*   [webconsistencytesting.com](http://webconsistencytesting.com/)
*   [Kevin Menard’s GTAC talk](http://www.youtube.com/watch?v=_6fV-6eMSUM)
*   [Michael Tamm’s GTAC video](http://www.youtube.com/watch?v=WY3C6FHqSqQ)
*   Image capture library from Mozilla ([the library](https://github.com/mozilla/eideticker) and [a blog post about it](https://blog.mozilla.org/futurereleases/2012/06/26/mobile-firefox-measuring-how-a-browser-feels/))

28:58-38:15 How do you get started with Selenium?  
_tl;dr pick a language you feel comfortable with, choose an editor that makes you productive, and dig into the available resources_

Recommended resources:

*   [Selenium 2 Testing Tools (David Burns’ book)](http://www.amazon.com/Selenium-Testing-Tools-Beginners-Guide/dp/1849518300)
*   [The Selenium Guidebook (Dave Haeffner’s new book)](http://seleniumguidebook.com)
*   [Free, weekly tip newsletter on how to use Selenium](http://elementalselenium.com) (also by Dave Haeffner)
*   [Selenium HQ](http://seleniumhq.org/)
*   [Selenium Users Group](https://groups.google.com/forum/#!forum/selenium-users)
*   Selenium IRC chat channel ([here’s a write-up on what it is and how to access it](http://elementalselenium.com/tips/20-irc-chat))

38:23-42:50 Selenium 3 Update  
_tl;dr still a work in progress, steadily moving forward, a big update will likely be required_

43:15-44:00 Conference Update  
_tl;dr nothing finalized, hoping to do it internationally, but US is a fallback; still working on options (will know soon)_

44:10-45:20 Mobile Changes to the Selenium project  
_tl;dr Android and iPhone Selenium drivers deprecated in favor of other compatible libraries_

Compatible libraries:

*   [Appium](http://appium.io/)
*   [ios-driver](http://ios-driver.github.io/ios-driver/)
*   [Selendroid](http://selendroid.io/)

45:20-47:30 New Mobile Selenium Drivers  
_tl;dr RIM (BlackBerry Smartphones) and Microsoft (Windows phones)_