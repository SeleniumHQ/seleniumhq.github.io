---
title: "The Current Status of Selenium 1 and Selenium 2"
linkTitle: "The Current Status of Selenium 1 and Selenium 2"
date: 2010-07-21
tags: ["selenium","status"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  In the beginning there was Se1, and it was good. But it could have been better — in ways that WebDriver was starting to be good at.
---

In the beginning there was Se1, and it was good. But it could have been better — in ways that WebDriver was starting to be good at. Thus the brilliant idea was hatched to merge the two projects.

And then the confusion began. Let’s see if I can start to address some of it via a ficticious conversation that consolidates the Se-user list and #selenium irc channel.

_There are a couple annoying bugs in Se-RC 1.03; when is the 1.0.4 release?_

Se-RC 1.0.4 is planned for sometime towards the end of July 2010

_I’ve heard rumours that 1.0.4 the to be the final release?_

Yes. 1.0.4 is _planned_ on being the final 1.x release

_That’s crazy talk! I can’t use a .0 or ‘alpha’ release for my mission critical application_

Actually, its not all _that_ crazy — and needs a bit more explanation. Se2 is truly a merger of the two projects, in fact 2.0a1 was literally the Se code from the OpenQA repository and the WebDriver code its Google Code repository merged into a [new one](http://code.google.com/p/selenium). This meant that from the first release of the 2.x series, it has contained 100% of the 1.x code which means 100% backwards compatibility. Later releases in the 2.x series have been driven primarily by evolutions of the code that came from WebDriver, _not from Se 1.x_.

_OK, so 100% of Se 1.x is in 2.x; I get that. But how are you making sure that fixes to one get into the other?_

Here is another ‘secret’ — don’t tell anyone, but there hasn’t been any pure 1.x development since the merging of the codebases. Every 1.x release since the merger has really been a 2.x release — but all packaged up to make it look like a 1.x release. This is why observant people have noticed a log message that looks something like _11:09:37.507 INFO – v2.0 \[a4\], with Core v2.0 \[a4\]_ when they start up their 1.x server.

_So you’ve been releasing _alpha_ code disguised as a stable release? Jerks!_

Woah! Relax! Recall what I said above about it being backwards compatible by default. The ‘alpha’ tag is there because the API for the new code is still being developed and features flushed out. The 1.x code is however, still stable and still production quality.

_Alright, I take back calling you folks jerks, but I really don’t like the alpha tag. When will it be out of ‘alpha’?_

There is only one or two more features to implement (like handling alerts) in the WebDriver code and some cleanup before the betas start. But hope for a 2.0.0 final by the end of the year. And while we’re on the topic of ‘alpha’ vs. ‘beta’, this the team’s working definitions of each.

*   _Alpha_ – APIs can, and likely will change. Possibly in dramatic ways.
*   _Beta_ – With the APIs set, make sure they work with the major browsers

_‘Major Browsers’ eh, what exactly does that mean?_

Right now it means Firefox, Internet Explorer and at least one WebKit based one (Safari or Chrome)

In short… it is a requirement of Se2 that the server be backwards compatible with Se-RC 1.x and that has already been accomplished by building the code from a common source repository. This means that if you are using Se-RC, you can switch out the server for a 2.x one and have no impact on the execution of the scripts. _Plus_ you can start to experiment with the new stuff that came over from WebDriver.