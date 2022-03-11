---
title: "Selenium 4 Release Candidate"
linkTitle: "Selenium 4 Release Candidate"
date: 2021-09-06
tags: ["selenium", "status"]
categories: ["general", "releases"]
author: Diego Molina ([@diegofmolina](https://twitter.com/diegofmolina))
description: >
  We're very happy to announce the first release candidate of Selenium 4.
---


We're very happy to announce the first release candidate of Selenium
4. We're shipping this for .Net, Java, Python, Ruby, and JavaScript,
so if you're using any of those languages, go and grab it from your
package manager of choice!

This release is the result of a lot of work by the Selenium team 
project, but most importantly, all the Selenium community who 
tried our beta releases, and gave us great and valuable feedback.


{{< tweet user="titusfortner" id="1433114357047627785" >}}

{{< tweet user="jimevansmusic" id="1433140517819322369" >}}

{{< tweet user="AutomatedTester" id="1433377616065667074" >}}

{{< tweet user="shs96c" id="1433474873972641793" >}}

{{< tweet user="diegofmolina" id="1434820167360339968" >}}


One thing you may want to do to get ready for the update (which you
can do before updating the dependency itself!) is to update the
drivers you need. In particular, please update [geckodriver][] to
0.29.1 or later.

Most of the new features in Selenium 4 are mentioned in this [blog entry][se4], 
but the main highlights are:

  * Relative locators, for finding elements using terms that make
    sense to us humans.
  * The ability to intercept network traffic
  * Authentication with basic or digest authentication.

If this sounds interesting, please download the release candidate from your
favourite package manager (maven, nuget, npm, pip, or the gem), or
directly from the [Selenium site][download].

[download]: /downloads
[geckodriver]: https://github.com/mozilla/geckodriver/releases
[se4]: /blog/2020/what-is-coming-in-selenium-4-new-tricks/
