---
title: "A Smattering of Selenium #130"
linkTitle: "A Smattering of Selenium #130"
date: 2012-11-27
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Can’t get enough Se bloggage?
---
Can’t get enough Se bloggage? Have a look at [Overview of Selenium Blogs](http://itkosmopolit.wordpress.com/2012/11/23/overview-of-selenium-blogs/) — though I must say there has to be something wrong with the Alexa algorithm if I am that far down the list. And behind both David and Alister. 🙂

*   [Must Have Git Aliases: Advanced Examples](http://durdn.com/blog/2012/11/22/must-have-git-aliases-advanced-examples/) reminds me of someone I used to work with who likely couldn’t function on a clean unix system, but with all his aliases was mind blowing to watch
*   [Writing a Selenium Test Framework for a Django Site (Part 3)](http://techblog.safaribooksonline.com/2012/11/23/writing-a-selenium-test-framework-for-a-django-site-part-3/) has the usual myths and FUD around XPath and embeds locators in the script method but is the first thing I’ve seen that uses the Color class in Python so I’ll overlook those things. This time.
*   [How to solve Selenium focus issues](https://makandracards.com/makandra/12661-how-to-solve-selenium-focus-issues) introduced me to jQuery’s :focus pseudoselector
*   I’m sure there is something to be learned from [Use Rails until it hurts](http://evan.tiggerpalace.com/articles/2012/11/21/use-rails-until-it-hurts/). Especially from the paragraph above the twitter inclusions
*   [Checking an image is actually visible using WebDriver](http://watirmelon.com/2012/11/27/checking-an-image-is-actually-visible-using-webdriver/) is something I plan on stealing.
*   [Android 4.0 in VirtualBox](http://www.kirsle.net/blog/kirsle/android-4-0-in-virtualbox) seems like a cool way to build out an android build farm
*   [Autoloading in PHP and the PSR-0 Standard](http://phpmaster.com/autoloading-and-the-psr-0-standard/) has some Symfony specific bits, but is generally useful
*   [Extending Selenium with jQuery](http://blogs.gnome.org/danni/2012/11/19/extending-selenium-with-jquery/) is something else I intend to steal
*   From the same person is [Combining py.test and Selenium to test webapps](http://blogs.gnome.org/danni/2012/11/15/combining-py-test-and-selenium-to-test-webapps/) which shows off some of what py.test can do with fixtures. Not how I would do it, but cool none the less.
*   Let me say this again; the officially blessed solution for getting response codes is to route your calls through a proxy (like the BrowserMob Proxy) and then ask it what they were. But if you are bound and determined to craft ‘solutions’ that only work in a single browser, or a single language, or require changing your application then [How To Get The HTTP Status Code In Selenium WebDriver](http://www.ninthavenue.com.au/how-to-get-the-http-status-code-in-selenium-webdriver) will make good breakfast reading. North American time that is…