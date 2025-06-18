---
title: "Removing Legacy Protocol Support"
linkTitle: "Removing Legacy Protocol Support"
date: 2022-05-20
tags: ["webdriver", "java", "grid"]
categories: ["general"]
author: Titus Fortner ([@titusfortner](https://twitter.com/titusfortner))
description: >
  Selenium 4.9 will only support W3C compliant WebDriver syntax
---

The Selenium team prides itself on how seriously it takes backwards compatibility. 
A lot of care has gone into the Java bindings, especially, to ensure very few breaking changes over the years. 
There is Selenium code written 15 years ago that can still run with Selenium 4 libraries! 
Providing this exceptional amount of support comes with a large maintenance burden, though, 
and we need to be able to properly meet the needs of the vast majority of our userbase.

TL/DR:
* Support for the legacy [JSON Wire Protocol](https://www.selenium.dev/documentation/legacy/json_wire_protocol/) 
will be removed from Java Selenium 4.9 (other languages have already removed this support)
* Protocol conversions will stop in Selenium 4.9 Grid
* You can ensure your sessions are W3C compliant by using 
[Browser Options classes](https://www.selenium.dev/documentation/webdriver/getting_started/upgrade_to_selenium_4/#after) 
instead of the deprecated Desired Capabilities classes (and avoid using "set capability" methods directly)
* If you rely on the current protocol conversion functionality, and it works for you, 
you can continue to use it with Selenium Grid 4.8

By far the biggest challenge in the past seven years of Selenium development has been 
transitioning the underlying implementation from the legacy [JSON Wire Protocol](https://www.selenium.dev/documentation/legacy/json_wire_protocol/) 
to the new standardized [W3C WebDriver Protocol](https://w3c.github.io/webdriver/). 
Because the WebDriver specification was being actively developed at the time,
Selenium 3 supported both protocols simultaneously.
We implemented "handshake" code to determine which protocol to use. 
When starting a session, Selenium would package the provided capabilities in both legacy and W3C formats, 
send them to the driver/server, and use whichever protocol was returned. 
This worked well for the most part. For many users, no changes were necessary to get
W3C compliant sessions from new browsers (Firefox 47+ and Chrome 75+). 

For the Java bindings, it was decided to take this approach one step further. 
Instead of just sending along what was provided in both formats, 
the code converted capabilities from the legacy protocol to the W3C protocol on behalf of the user. 
The Selenium Grid makes use of this code, so, regardless of which client language sends legacy capabilities to it, 
the Grid translates it to the W3C protocol for communication with the driver and then 
re-translates the results back to the legacy protocol.
Because the code must make some assumptions and guesses for this to work, there are a lot of frustrating edge cases.

For Selenium 4.0, the Ruby, JavaScript, and .NET bindings, each removed the handshake code, 
so no legacy commands are used. Due to some issues that couldn't be resolved before freezing the 3.x code, 
Python is waiting until Selenium 4.9 to remove its handshake code. 
The Selenium team intended to continue to support both protocols in the 
Grid and Java bindings throughout the 4.x releases, but running test suites written for Selenium 2 
on the Selenium 4 Grid resulted in a larger than expected number of failures. 
Half a dozen bugs were discovered, some with a large effect.

The code in question is sufficiently complex that fixing the bugs 
without causing new problems would be a significant challenge. 
As such, we’ve decided to start simplifying the code base again by explicitly 
not supporting capabilities and commands from the legacy protocol in any of the Selenium codebase.
If you are using the latest version of Java Selenium, everything that will be removed
has already been marked deprecated, so double-check your usage to ensure there aren't any surprises.

If you’re interested in the nitty-gritty details of some of the  issues we found
exploring this problem, you can look at the discussion in
[Selenium Issue #10374](https://github.com/SeleniumHQ/selenium/issues/10374).

For more information on ensuring compatibility with the Grid, please follow our
[Selenium 4 Upgrade Guide](https://www.selenium.dev/documentation/webdriver/getting_started/upgrade_to_selenium_4/)
