---
title: "Going Atomic: How"
linkTitle: "Going Atomic: How"
date: 2010-09-05
tags: ["selenium","decisions"]
categories: ["technical"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  This is the second of my technical posts. Again, if you’re interested in the internal workings of Selenium 2, then please skip straight to something else.
---

This is the second of my technical posts. Again, if you’re interested in the internal workings of Selenium 2, then please skip straight to something else. If you’re interested in how and why we made some of the technical decisions on the project, keep reading….

We left our intrepid heroes in a tight spot: they’d decided to write a shared library of code, to be used by the various webdriver implementations and selenium core, but the requirements for doing this seemed to be at odds with it actually happening.

Fortunately, at about the same time we started down this path, Google Open Sourced the [Closure compiler](closure-compiler.googlecode.com). This is a Javascript compiler that takes as input a set of Javascript files, and which outputs Javascript. It can be configured to either pass the code through untouched into a single file, or it can compile a script aggressively, removing unused code-paths and minifying the output as much as possible. The Closure compiler is used on a lot of Google products, so we know that it’s reliable and consistent.

In order to get the best out of the Closure compiler, we’re writing the atoms using the [Closure library](closure-library.googlecode.com). This isn’t as well known as some of the other JS libraries out there, but it’s solid, well tested and is being actively developed. It also features an easy-to-use extension of JsUnit, which makes writing tests a far simpler task than might otherwise be the case, and it has an easy to use mechanism for modularizing code.

So, given that we could compile a single Javascript function (and it’s dependencies) into a minified fragment of JS, we were all set, right? Not quite.

The problem is that the atoms are being extracted from two frameworks that have a different way of viewing the world. As an example, Selenium 1’s “[getAttribute](http://selenium.googlecode.com/svn/trunk/docs/api/java/com/thoughtworks/selenium/Selenium.html#getAttribute(java.lang.String))” method only returns the value of a particular attribute, whereas WebDriver’s “[getAttribute](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html#getAttribute(java.lang.String))” method will return the value of either a property or an attribute (because sometimes it’s hard to remember whether something is an attribute or a property of an element)

As with all problems in computer science, an extra level of indirection is used to solve this issue.

We’re busy implementing the expected behaviour of both [WebDriver’s](http://code.google.com/p/selenium/source/browse/#svn/trunk/common/src/js/webdriver) and [Selenium’s](http://code.google.com/p/selenium/source/browse/#svn/trunk/common/src/js/selenium%3Fstate%3Dclosed) API on top of the atoms.

There is, of course, the obvious question about how we get this carefully compressed JS into a driver. One option would be to include the raw Javascript as files in each language binding, and pull them in as required. That’s possible, but it would make each language binding bloated, and would introduce a lot of duplication. The alternative is to push the atoms as far into the driver as possible, and this is what we do. As part of the build process for webdriver, we take the compressed JS and convert it into a form that can be consumed by a particular driver. For example, for the IE driver, we convert them into constants in a [C header file](http://code.google.com/p/selenium/source/browse/trunk/jobbie/src/cpp/InternetExplorerDriver/atoms.h). These constants can then be referred to by the driver and converted back into a script than be executed via the same mechanism that is used by “executeScript”.

What do we gain from this seemingly baroque approach? Other than the ability to share the same code between drivers? Many things. The cost of maintenance drops dramatically as we can fix a bug in one place and have that fix be picked up by every driver. Because we’re working in pure JS and just querying the DOM, we can run the unit tests in a browser whilst we’re developing the code. This leads to a very tight feedback cycle. It also makes it easier for developers not familiar with the code to take a look at how we do things, and send us patches (always appreciated!) Finally, we can ensure a consistency of result.

Right, any questions?