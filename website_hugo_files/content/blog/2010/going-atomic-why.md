---
title: "Going Atomic: Why?"
linkTitle: "Going Atomic: Why?"
date: 2010-08-16
tags: ["selenium","decisions"]
categories: ["technical"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  This is the first in a series of technical posts by me about the internals of Selenium WebDriver.
---

This is the first in a series of technical posts by me about the internals of Selenium WebDriver. If you’re not interested in technical nitty-gritty, then feel free to step away now.

Still here? Excellent.

Let’s take a step back to just before the Selenium and WebDriver projects merged. There were, very obviously, two separate codebases. Looking closer and with a slightly different perspective, there were more than this. We used the test suites for webdriver to define the behaviour for multiple, largely independent, driver codebases. The [IE driver](http://code.google.com/p/selenium/source/browse/#svn/trunk/jobbie) was written in C, the HtmlUnit driver in Java and the [Firefox driver](http://code.google.com/p/selenium/source/browse/#svn/trunk/firefox) is largely Javascript, and so on.

This means that there was a lot of “congruent code”: code that performed the same function but was implemented in a different way. The natural result of this was there was the possibility for behaviour to diverge between drivers. Worse, it meant that when a bug was found, we had to check it in every browser, and it wasn’t certain that an individual could actually fix the code. After all, not everyone is comfortable writing in all the languages we use on the project, or is au fait with all the technologies. For an Open Source project like Selenium, this is a major problem: we rely on a relatively small core of key developers backed up with a far larger team of individuals submitting small changes and fixes. Anything that makes it harder for us to function effectively as a development community is a Bad Thing.

So, we wanted a way off the island; a mechanism that would make it easy to share code between the various drivers and selenium core, that allowed us to fix a bug in one place only and have that fix ripple out to every driver that made use of this mechanism. More importantly, it had to be easy to use, and for someone not familiar with a raft of languages and technologies to quickly get started with.

What would this mechanism look like? Well, there’s a few things that feed into this, but the most important one, was that a majority of the code we’d think of merging was querying the state of the browser (“find an element”, “get the value of this attribute”) and, as Jason Huggins would point out to me at the drop of a hat, the natural language for querying the state of a browser is Javascript. One of the nice things with Javascript is that it’s possible to get a nice, fast development cycle going in it. Just modify a test, save and then hit “refresh” in the browser. That’s kind of attractive. Better still, there are a lot of developers familiar with Javascript.

So, we decided to use Javascript.

Because this shared code was to be composed of the smallest useful fragments of functionality required for browser automation we decided to refer to them as “Browser Automation Atoms”, or “atoms” for short. Rather than write them from scratch, the easiest thing to do was to extract them from the existing code — this is stuff that’s been battle-tested, so we know it’s robust.

There was one very obvious fly in the ointment: not every driver is written in Javascript. Although we have a [mechanism available](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/JavascriptExecutor.html) in every browser for executing JS, it’s wildly inefficient to dump an enormous lump of code on to the JS engine of the browser whenever you want to query the DOM. After all, most of the code would not be needed, and not all JS engines have been created equal. Some are blazingly fast. Others, not so much.

It would also be nice to break the code up into manageably-sized modules, rather than being in a single, monolithic file, which implies some clever “module loading” capability. Except this code isn’t always going to be executing inside an environment where writing “script” tags to load additional scripts is possible. You can’t do that in the guts of a firefox extension, though you can load files other ways. However we tie modules together will need to cope with that.

Ah! These opposing requirements: small modules containing the functions we want to use, no extraneous code, and for everything to be in a single file in order to minimize the pain of loading additional modules. That doesn’t sound like a very compatible list. How we resolved those differences is the topic of my next post….