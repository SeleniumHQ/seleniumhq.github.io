---
title: "Firefox 55 and Selenium IDE"
linkTitle: "Firefox 55 and Selenium IDE"
date: 2017-08-09
tags: ["selenium", "ide"]
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  The bad news: from Firefox 55 onwards, Selenium IDE will no longer work.
---


The bad news: **from Firefox 55 onwards, Selenium IDE will no longer work**.

The reasons for this are complex, but boil down to two main causes:

1.  Browsers are complicated pieces of software that are constantly evolving. Mozilla has been working hard to make Firefox [faster and more stable](https://blog.mozilla.org/blog/2017/06/13/faster-better-firefox/), while still retaining the flexibility and ease of extension that we’ve come to know and love. As part of that process, Firefox is switching extensions from the original “[XPI](https://blog.mozilla.org/addons/2016/11/23/add-ons-in-2017/)” format, to a newer, more widely adopted “[Web Extension](https://developer.mozilla.org/en-US/Add-ons/WebExtensions)” mechanism. 
2.  The Selenium project lacks someone with the time and energy to move the IDE forwards to take advantage of the new technologies.

[Selenium](http://www.seleniumhq.org/) is one of the most widely used pieces of testing software there is. Despite this, the team of people regularly contributing is small: since the start of the year, there are only 11 people who have made more than 10 commits, with two people accounting for more than half of those. Since 2016, only one person has been maintaining the IDE.

Selenium is an Open Source project. None of the core contributors — not the IDE maintainer, not the language binding owners — are paid to work on work on it. They do it because they love working on the code, and they typically do it in their “copious free time”. The IDE maintainer has had almost none of that to spare. We should all be thanking that committer for his time and effort. Thank you, Samit!

So what can we do to move forward? The first thing is that there are now a wealth of tools that are stepping up to fill the gap. You should go and have a look at them. The second thing is that there is an effort to rebuild IDE using modern APIs, to be usable across more than just Firefox. The fine people at [Applitools](https://applitools.com/) are helping with this effort.

The third thing? That’s you. **You could help us**.

If you believe that a friendly UI for quickly recording and playing back tests is a useful Open Source tool, then please come and join us! The main technical discussions are happening on the #selenium IRC channel. If you’d prefer [Slack](https://seleniumhq.herokuapp.com/), you can join us on that too. Or there’s the ever useful [selenium-developers](https://groups.google.com/forum/#!forum/selenium-developers) mailing list. Come onboard. We’d love your help, and IDE is a wonderful thing to contribute to!