---
title: "A Smattering of Selenium #39"
linkTitle: "A Smattering of Selenium #39"
date: 2011-01-31
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Hey look! All caught up — only took a month…
---

Hey look! All caught up — only took a month…

*   My opinions on Continuous Deployment are pretty widely known, but the IMVU folks certainly have a lot of neat tricks to ‘borrow’. Such as [Buildbot and Intermittent Tests](http://engineering.imvu.com/2011/01/19/buildbot-and-intermittent-tests/)
*   Dealing with an API that returns XML? Your scripts don’t care about the readibility, but it helps you as the human if it is formatted pretty. [xml formatter](http://www.shell-tools.net/index.php?op=xml%5Fformat) is a glorious time save in that case.
*   Who would have predicted this… Perl stuff
    *   [Modern Perl](http://onyxneon.com/books/modern_perl/) is a free Creative Commons book on Perl (with a dead-tree version also available)
    *   I had thought the [Se-RC style bindings](http://search.cpan.org/dist/Test-WWW-Selenium/) had been abandoned, but heard [second hand](http://twitter.com/davehodg/statuses/30312545696878592), they are alive and well.
    *   [Webdriver Remote Driver](https://github.com/aivaturi/Selenium-Remote-Driver) is the start of work on a Se2 driver. Now to get the two projects working together.
    *   And the reason for the Perl stuff is [Hudson and Selenium](http://www.davehodgkinson.com/blog/2011/01/hudson-and-selenium/)
*   Since Se is using Sizzle now for locators, comes [a tip](http://twitter.com/envatowebdev/statuses/30833435383701504) – _never do things like $(‘form \*’). This is crazy costly, because Sizzle works from right to left. Will grab all elems first._. Not sure of the accuracy, but it makes sense.
*   Part of the debate when doing BDD and TDD is the overlap that \[naturally\] occurs; [Duplication between BDD and Unit tests](http://gojko.net/2011/01/28/duplication-between-bdd-and-unit-tests/) addresses it, partly be reframing the question.
*   [webkitdriver](http://code.google.com/p/webkitdriver/) is a project that _aims to provide a WebDriver implementation for a light-weight in memory Web Browser_
*   This week’s Selenium _killer_ is [PhantomJS](http://ariya.blogspot.com/2011/01/phantomjs-minimalistic-headless-webkit.html)
*   Achievement parodies are always amusing; here is on for [Visual Studio](http://blog.whiletrue.com/2011/01/what-if-visual-studio-had-achievements/) — what would the Selenium ones look like?
*   Koans are a trendy way to learn / practice a language. Here is a [Koan-a-copia of them](http://www.rubygeek.com/2011/01/22/koan-a-copia/)
*   Want onto the speaking circuit? The 2011 [Verify/ATI Conference is asking for presentations](http://www.verifyati.com/index.php?option=com_jforms&view=form&id=1&Itemid=85)