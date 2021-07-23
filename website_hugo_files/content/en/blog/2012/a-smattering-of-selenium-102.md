---
title: "A Smattering of Selenium #102"
linkTitle: "A Smattering of Selenium #102"
date: 2012-07-17
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Apparently my body isn’t quite on left coast time…
---

Apparently my body isn’t quite on left coast time…

*   [Butter Performance](http://blog.streak.com/2012/07/butter-performance.html?m=1) is a nice experience report on making a \[big\] html table behave. All sorts of headaches this would cause for automation.
*   I’m now starting to think more about caching since some of that information is included in HAR files. So if you are automating against a Rails site, [Advanced Caching in Rails: Revised](http://www.broadcastingadam.com/2012/07/advanced_caching_revised/) could be of values
*   [Scaling lessons learned at Dropbox, part 1](http://eranki.tumblr.com/post/27076431887/scaling-lessons-learned-at-dropbox-part-1) is another fun experience report including a bit of hints at Dropbox’s own version of Netflix’s Chaos Monkey
*   [Finding the broken links in a webpage using Selenium](http://veera-myseleniumblog.blogspot.ca/2012/01/finding-broken-links-in-webpage-using.html) is Se-RC, but the idea is sound. Notice how they are _not_ using Se to follow the link. This is why Se-RC and WebDriver is soo much more powerful than Se-IDE.
*   Mozilla’s WebQA team launched a new blog, and then immediately posted a flurry of things. Not to see if they can keep up the pace!
    *   [WebDriver’s implicit wait and deleting elements](https://blog.mozilla.org/webqa/2012/07/12/webdrivers-implicit-wait-and-deleting-elements/)
    *   [WebDriver and Py.Test parametrize](https://blog.mozilla.org/webqa/2012/07/12/webdriver-and-py-test-parametrize/)
    *   [How to WebDriverWait](https://blog.mozilla.org/webqa/2012/07/12/how-to-webdriverwait/)
*   [How Many Build Agents Does My Project Need? (a.k.a. “The $16,000 Question”)](http://blogs.atlassian.com/2012/07/how-many-bamboo-build-agents/) is Bamboo specific, but the idea is sound and should apply equally to other CI containers
*   I can not tell you how much I dislike JS Widgets. Here is how to use WebDriver with [DHTMLX ComboBox](https://gist.github.com/3106277)
*   [RSpec-2.8 is released!](http://blog.davidchelimsky.net/2012/01/04/rspec-28-is-released/) is about 6 months late to be ‘news’, but _–order random_ should be an option on all runners
*   [Speed Up the Development of Calabash-Android Tests](http://www.dary.de/2012/07/speed-up-the-development-of-calabash-android-tests/) talks about how they extended irb to have a shell which will run things on their device. This seems like an stealable idea; run Se Server in a terminal, execute a script which doesn’t close the browser, run commands against it. Just thinking out loud…
*   [expecter](http://pypi.python.org/pypi/expecter) is now on my shortlist to play with. But what is the soft-assert version of an expect? A hope?