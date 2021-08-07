---
title: "A Smattering of Selenium #60"
linkTitle: "A Smattering of Selenium #60"
date: 2011-09-27
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  This instalment of catch-up week is brought to you by the letters C and I.
---

This instalment of catch-up week is brought to you by the letters C and I.

*   The [pre-scm-buildstep](https://wiki.jenkins-ci.org/display/JENKINS/pre-scm-buildstep) plugin for jenkins adds a useful step into the job workflow.
*   Understanding your tools is important. Here is an explanation of Jenkings [Action and its subtypes](http://blog.cloudbees.com/2011/08/jenkins-internal-action-and-its.html)
*   You think _your_ CI setup is impressive? Check out [the Apache Foundation’s Jenkins server](https://builds.apache.org/)
*   The easiest way to get CI going is with the ubiquitious but completely unoffically Ant JUnit XML format. [Publishing Python unit test results in Jenkins](http://www.stevetrefethen.com/blog/Publishing-Python-unit-test-results-in-Jenkins.aspx) discusses a python package a bit for it.
*   To me, using a headless browser like HTMLUnit makes very little sense. Now, a headful browser on a headless machine — that makes sense. [Setting up Jenkins CI to run selenium tests and record video in three easy steps](http://iafonov.github.com/blog/setup-jenkins-to-run-headless-selenium.html) explains how to do this in Ruby.
*   [Travis CI – Selenium setup](http://about.travis-ci.org/docs/user/selenium-setup/) shows the similar thing minus the recording of a video for the Travis CI environment.
*   So far it seems that one should just subscribe to the Multunus blog and be done with it. Or at least [the continuous delivery category](http://www.multunus.com/blog/all/continuous-delivery/).
*   Think your web app deployment is ‘hard’? [Continuous Delivery: How do we deliver in 3 clicks to 7000 machines?](http://blog.octo.com/en/continuous-delivery-how-do-we-deliver-in-3-clicks-to-7000-machines/) discusses a .NET client application. Now go count your blessings and get that remote svn export scripted.
*   [Won’t somebody please think of the systems?](http://theagileadmin.com/2011/09/12/wont-somebody-please-think-of-the-systems/) looks at CI with a bit of an ITIL lens
*   [Distributed Check-in Tokens: Pass-The-Puppy](http://blog.stevemoyer.net/2011/09/distributed-check-in-tokens-pass-puppy.html) presents a quick technical solution for not breaking the build through tokens. You could of course talk to each other but there are sometimes time zones conspiring against you.