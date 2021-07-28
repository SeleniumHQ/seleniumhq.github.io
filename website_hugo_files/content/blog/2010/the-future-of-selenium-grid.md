---
title: "The Future of Selenium Grid"
linkTitle: "The Future of Selenium Grid"
date: 2010-04-27
tags: ["selenium","grid"]
categories: ["general"]
author: Kevin Menard ([@nirvdrum](https://twitter.com/nirvdrum))
description: >
  As you likely know by now, after years of stewardship Philippe has stepped down as maintainer of Selenium Grid and has named me the new maintainer.
---

As you likely know by now, after years of stewardship Philippe has stepped down as maintainer of Selenium Grid and has named me the [new maintainer.](http://ph7spot.com/blog/new-selenium-grid-maintainer) In this post, I hope to address the most immediate questions about what this means for the future of Selenium Grid.

Background
----------

I realize many of you may not know who I am. I’ve been using Selenium in some capacity for close to five years now. I love working on open source software and do so primarily through my membership in the Apache Software Foundation and through [my GitHub account](http://github.com/nirvdrum/). I hang out on the #selenium channel with the handle “nirvdrum” and have a [personal page](http://nirvdrum.com/) that includes some articles I’ve written and other things I’m involved with. Perhaps more importantly, I also rely on Selenium Grid for [my latest start-up](http://mogotest.com/) so I’m very keen on seeing the project mature and evolve.

Project Resources
-----------------

The new canonical project repository on GitHub is the [nirvdrum fork](http://github.com/nirvdrum/selenium-grid/). However, within in the next month or so the project will be migrated from git and GitHub to subversion and the [Selenium project on Google Code](http://code.google.com/p/selenium/). As much as I personally prefer GitHub to Google Code, it makes the most sense from a community perspective for all Selenium projects to be hosted in the same location. The move will be made after Selenium Grid stabilizes a bit more. The [1.0.6 release](/blog/2010/selenium-grid-1-0-6-released/) was the first step in that process, but there may be one or two more minor patch releases. After the move, I’ll try to maintain a synchronized mirror of the code on GitHub so those that prefer to develop patches with git can continue to do so.

The canonical issue tracker is the [Selenium issue tracker](http://code.google.com/p/selenium/issues/list) on Google Code. We will not be using either JIRA or GitHub Issues. I’ll try to migrate what I can over to the new tracker, but if I miss anything, please re-open the issue over there.

Project Evolution
-----------------

Selenium Grid currently only works with Selenium 1.x, and consequently the 1.x compatibility interface in Selenium 2. There is currently no support for WebDriver. However, I fully intend to steer the product in that direction, while maintaining support for existing Selenium Grid installations (i.e., based on Selenium 1.x).

Since the project is moving to Google Code, all of the existing Selenium committers will have the ability to commit to the codebase. This should make the project’s evolution a much more collaborative effort. I’ll kickstart the process and likely lead the development effort, but the role of maintainer will be diminished (in a positive way) by allowing the entire core team equal access to the project.

Others are welcome to chime as well. Selenium Grid development discussion will be taking place on the Selenium developers list. Please continue to use the Selenium users list for usage and support inquiries.

