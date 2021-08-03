---
title: "A Smattering of Selenium #128"
linkTitle: "A Smattering of Selenium #128"
date: 2012-11-19
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  …as I avoid writing code that deals with dynamically constructed tables.
---

…as I avoid writing code that deals with dynamically constructed tables. Without any sort of unique locator. Of course.

*   I would contend that the ‘right’ solution to this problem is to use a CI container and have it email you, but if you are using Java and not using CI, then [Automatically Email the Reports After Selenium Test Execution](http://assertselenium.com/2012/11/13/emailable-reports-for-selenium-scripts/) could be valuable.
*   I feel like I have already linked to [Webdriver StaleElementReferenceException](http://www.jeromemueller.ch/archives/311/webdriver-staleelementreferenceexception) but the archive search is, erm, not great, so here it is again. Notice the solutions to what are all synchronization problem is _not_ to turn on implicit waits.
*   A lot of the reason for lighting up a browser is to be able to do input with the app; [The CHECKS Pattern Language of Information Integrity](http://c2.com/ppr/checks.html) is useful reading in this regard
*   While I wait for MS to send me a free Surface to play with (\*hint\*), [Testing Applications by Using the Surface Simulator Automation API](http://msdn.microsoft.com/en-us/library/ee804820(v=Surface.10).aspx) seems like they have at least given the problem some thought. Of course, it starts with a hand-holding of how to use an IDE. How very Android of them.
*   [Inspect the State of Your Running iOS App’s UI With Symbiote](http://blog.thepete.net/blog/2011/05/01/inspect-state-of-our-running-ios-apps/) — _Essentially Symbiote is Firebug for your native iOS app._; you gotta love a good elevator pitch
*   I don’t the Opera kids get enough credit for what they’re doing (and did first iirc) – [Introducing mobile browser automation](http://dev.opera.com/articles/view/introducing-mobile-browser-automation/)
*   If you brain didn’t hurt yet, it will now. [What’s the difference if “<meta http-equiv="X-UA-Compatible" content="IE=edge">" exists or not?](http://stackoverflow.com/questions/6771258/whats-the-difference-if-meta-http-equiv-x-ua-compatible-content-ie-edge)
*   OAuth2 seems to have died a public flaming death, so some smart folks created [oz](https://github.com/hueniverse/oz). There is some snark I can’t find about not liking a fork so creating a new one and now you have n+1 problems or something… _All_ auth system suck BTW.
*   [Ivoire](https://github.com/Julian/Ivoire) has some RSpec goodies — but in Python
*   What? You’re not using data-\* attributes yet? Shame on you…