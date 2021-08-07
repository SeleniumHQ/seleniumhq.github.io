---
title: "A Smattering of Selenium #45"
linkTitle: "A Smattering of Selenium #45"
date: 2011-03-28
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  So of course by now everyone has seen Selenium 2.0b3: The Next Gen Browser Release and upgraded their rigs.
---

A Smattering of Selenium #45

So of course by now everyone has seen [Selenium 2.0b3: The Next Gen Browser Release](https://seleniumhq.wordpress.com/2011/03/21/selenium-2-0b3-the-next-gen-browser-release/) and upgraded their rigs. Expect Selenium IDE at some point in the next week with support for FF4 — if you just. can’t. wait. then you could try the bleeding edge for yourself. [Logging any bugs](http://code.google.com/p/selenium/issues/list) you find; of course.

Oh, and there is the whole [Selenium Conference](http://www.seleniumconf.com/) next week.

But aside from that, here are the things I have collected.

*   [headless dotnet browser testing with selenium2](http://computeristsolutions.com/blog/posts/2011/3/headless-dotnet-browser-testing-with-selenium2) using Jenkins and [NSSM](https://iain.cx/src/nssm/) (the Non-Sucking Service Manager)
*   [Frameworks are evil](http://ecomba.org/blog/2011/03/08/frameworks-are-evil/) includes this gem: _The problem comes when you are blindly following what a framework gives you and you forget your better design skills (like OO or functional skills) to just follow blindly recipes from a given framework._. Exactly.
*   [Testing jQuery Autocomplete using Capybara](http://jackhq.tumblr.com/post/3728330919/testing-jquery-autocomplete-using-capybara) or more accurately _Using Selenium + Capybara + jQuery to select an option from an AutoComplete_
*   If you are wrapping your scripts in Cucumber or RobotFramework (or similar), then you owe it to your team to read [Putting Cucumber where it’s not supposed to go will hurt!](http://antonymarcano.com/blog/2011/03/cucumber-pains/). Especially the 4th adn 5th paragraphs.
*   Not new, but [FEST](http://docs.codehaus.org/display/FEST/Selenium) appears to open up the Applet space to Se. Not that Applets are really in use much these days. I could have used this in 2007 though.
*   [Alfajor](https://github.com/idealistdev/alfajor/) seems to be a python based metaframework supporting Windmill, WebDriver and a few others.
*   [Crawljax](http://crawljax.com/) is an AJAX capable web crawler
*   [Wait with WaitForCondition](http://blog.browsermob.com/2011/03/selenium-tips-wait-with-waitforcondition/) discusses some advanced synchronization tricks
*   [Capybara (and Selenium) with RSpec & Rails 3: quick tutorial](http://opinionated-programmer.com/2011/02/capybara-and-selenium-with-rspec-and-rails-3/) is a quick tutorial
*   [lettuce\_webdriver](http://pypi.python.org/pypi/lettuce_webdriver) is, naturally, the Python port of Cucumber
*   [An Update on Our Selenium-Automated AMO Tests (and Automation in General)](http://weblogs.mozillazine.org/stephend/archives/2011/03/i_gave_a_talk_a.html) — AMO is addons.mozilla.org for the acronym impaired.
*   [A website appears before you! Adventures of a clicky thing](http://trishkhoo.com/?p=298) is not Se, but still interesting.
*   [Desafios com o Selenium IDE](http://www.eliasnogueira.info/arquivos_blog/selenium/desafio/) is in Portuguese, but from what I understand he is trying to build a koans style site for Se-IDE.
*   From PyCon is [API Design anti-patterns](http://pycon.blip.tv/file/4878793/) and [API Design: Lessons Learned](http://blip.tv/file/4883290)
*   [Exceptional Ruby Notes](http://avdi.org/devblog/exceptional-ruby/) has some links to how ruby actually handles exceptions
*   [Parallelism is not concurrency](https://existentialtype.wordpress.com/2011/03/17/parallelism-is-not-concurrency/) — in case you were wondering
*   Se committer and IE WebDriver re-writer Jim Evans [was on Hanselminutes](http://www.hanselminutes.com/default.aspx?showID=276)
*   [Vision Test for ZTL](http://blog.zkoss.org/index.php/2011/03/22/vision-test-for-ztl/) shows how they do image comparison.
*   [and do does github!](https://github.com/cameronmcefee/Image-Diff-View-Modes/commit/8e95f70c9c47168305970e91021072673d7cdad8)
*   [Selenium Test Day with Mozilla WebQA](http://www.theautomatedtester.co.uk/blog/2011/selenium-test-day-webqa-29032011.html) is _TOMORROW_ – join in to learn / share some tricks with Mozilla
*   [An Introduction To Selenium IDE](http://blog.softwaretestingclub.com/2011/03/an-introduction-to-selenium-ide/) is the first in a series of articles that I might not normally link to but I read drafts of and know where they are heading. Either that or I am completely confused
*   [Step Away from the Tools](http://lizkeogh.com/2011/03/04/step-away-from-the-tools/) is an important reminder for those who tend to live in their tools.
*   [Selenium IDE plugin for the Play! framework](http://logician.free.fr/index.php/2011/03/23/selenium-ide-plugin-for-the-play-framework)
*   [How the Comodo certificate fraud calls CA trust into question](http://arstechnica.com/security/news/2011/03/how-the-comodo-certificate-fraud-calls-ca-trust-into-question.ars) is interesting in itself, but also has a nice explanation of how SSL works. Again, if you are not using certificate-based authentication in your scripts, you do not need SSL turned on outside of production, but should you persist then you should understand what is happening.
*   [Without stabilisers – why writing your own test harnesses really is an option](http://a-sisyphean-task.blogspot.com/2011/03/without-stabilisers-why-writing-your.html) is another argument for writing your own harness. Yes, you should do this. (Or at least customize the heck out of an existing one)