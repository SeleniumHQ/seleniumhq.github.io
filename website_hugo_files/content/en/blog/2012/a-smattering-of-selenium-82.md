---
title: "A Smattering of Selenium #82"
linkTitle: "A Smattering of Selenium #82"
date: 2012-03-13
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Someone explain to me why I’m in Toronto and not Florida?
---

Someone explain to me why I’m in Toronto and not Florida?

*   Couldn’t make it to Pycon last week? There [are videos](http://pyvideo.org/category/17/pycon-us-2012) of the session!
*   [Release Histories for all Major Browsers](http://www.impressivewebs.com/release-history-major-browsers/) is just interesting from a historical perspective
*   [Interacting with web pages using Selenium WebDriver for C#](http://www.jimmycollins.org/blog/?p=583) is pretty basic but does include the all-important security settings to make IE behave-ish
*   [page-object 0.6.2 released](http://www.cheezyworld.com/2012/02/23/page-object-0-6-2-released/) is not just a release announcement but has some nice examples of page objects in ruby
*   [autoenv](https://github.com/kennethreitz/autoenv) is a magic hack for having auto-firing scripts at a directory level. Gotta love projects that advertise as being pretentious
*   [Four Principles of Low-Risk Software Releases](http://www.informit.com/articles/printerfriendly.aspx?p=1833567) is important.
*   [Microsoft Details Its Browser Performance Testing](http://www.pcmag.com/article2/0,2817,2400346,00.asp) is good if only for the photos of their lab
*   [Sharing a WebDriver across TestFixtures](http://www.tomdupont.net/2012/02/sharing-webdriver-across-testfixtures.html) seems like a bad idea, but solves his specific problem. (Could it be solved with a specified profile rather than a fresh one every time?)
*   [Browser Specific TestFixtures Sharing Tests](http://www.tomdupont.net/2012/02/browser-specific-testfixtures-sharing.html) is from the same person as above and shows how to abstract different browsers from the script by moving the browser choice up to the suite level. Not sure how this would scale though…
*   I would really like to see less ‘howto’ blog posts that repeat the same basic information and more experience reports like [deja vu: code, culture, and QA](http://chrismcmahonsblog.blogspot.com/2012/02/deja-vu-code-culture-and-qa.html). Oh, and it serves as a nice reminder that automation is more than just regression; its about producing information that starts conversations
*   [Page Object Pattern and PageFactory](http://www.intexsoft.com/blog/item/34-selenium-webdriver-page-object-pattern-and-pagefactory.html) doesn’t look too bad — aside from not having a code formatting plugin in their blog
*   You never know when [Using Travis CI for building and testing Firefox addons](http://www.theautomatedtester.co.uk/blog/2012/using-travis-ci-for-building-and-testing-firefox-addons.html) will be useful
*   [capybara-firebug](https://github.com/jfirebaugh/capybara-firebug) is a _a dead-simple way to run Capybara-based Cucumber scenarios or RSpec request specs with Firebug enabled under the selenium driver_
*   [prickle](https://github.com/ExtractMethod/prickle) looks like it is implicit waits for capybara
*   [How to Use RVM](http://screencasts.org/episodes/how-to-use-rvm) is a screencast on, erm, well, how to use rvm
*   Of course, if you are on Windows then rvm won’t work for you. You need [pik](https://github.com/vertiginous/pik)
*   [Testify](http://tutorialzine.com/2011/11/lets-write-some-tests-testify-php/) is a microtesting framework for PHP that is trying to make their script JS-like
*   [Every Little Things Capistrano Does Is Magic](http://www.rubyfleebie.com/every-little-things-capistrano-does-is-magic/) aside from making my have a Police song rattle through my brain explains _some of the not so obvious parts of capistrano_
*   It has been far too long since I made a pirate reference here, so [An HR Lesson From Steve Jobs: If You Want Change Agents, Hire Pirates](http://www.fastcodesign.com/1665840/an-hr-lesson-from-steve-jobs-if-you-want-change-agents-hire-pirates). I should have named my company ‘International Automation Pirates’. Ah hindsight.
*   [JSErrorCollector](https://github.com/mguillem/JSErrorCollector) solves for Firefox something I think is missing from Se — which is the collection of JS error messages from the browser. (And it is missing from it for a reason; browsers don’t always expose it. Maybe we can get that into the W3C spec…)