---
title: "Moving to Trunk"
linkTitle: "Moving to Trunk"
date: 2020-07-01
tags: ["decisions"]
categories: ["technical"]
author: David Burns ([@AutomatedTester](https://twitter.com/AutomatedTester))
description: >
  Why we are getting rid of `master` branch in favour of `trunk`
---


Since the project started we have been following [trunk based development](https://trunkbaseddevelopment.com/). This was a very natural fit when we were using SVN over a decade ago on Google Code.

As Google Code shut down we moved to GitHub and the git model of doing things. We moved there mostly due to the gravity that GitHub had created in Open Source projects.

This meant that we followed the standard use of `master` as our trunk to work off. Now that GitHub, and services that use GitHub, have improved support for non-master branches as default we are moving our default branch to `trunk`. It describes how we, as a project, work and is a more inclusive term.

If you have pull requests based on `master` we will see about moving that over to the `trunk` branch ourselves. If we can't, we may ask you to help with the rebasing.

We, as a project, want to make our community inclusive and this is just one step in making sure we are. Other steps we taking are improving our Code of Conduct and Community Guidelines.

Join us on Slack or IRC if you wish to discuss this further.
