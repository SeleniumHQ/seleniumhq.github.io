---
title: "A Smattering of Selenium #131"
linkTitle: "A Smattering of Selenium #131"
date: 2012-11-29
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Not sure how widely broadcast this has been...
---

Not sure how widely broadcast this has been (cus, you know, we’re good at communicating and stuff), but if you are using 2.26.0 and Firefox 17 you will get a [nasty bug](http://code.google.com/p/selenium/issues/detail?id=4814). 2.27.0 is in the works to address this (and a couple other things…) so if you _need_ FF right now, keep your install at the [latest 16 release](http://ftp.mozilla.org/pub/mozilla.org/firefox/releases/16.0/).

*   This is pretty decent. Except the usual “Feh! We don’t need humans to test! Automate everything!” bias you see around. Psst kids! Even the poster children for Continuous Deployment actually do Continuous Delivery (humans! shocking!). Oh, and the usual gloss over ‘cluster immune system’ which is the only part of this tend that has an elegant solution.  
    
    **[Continuous Deployment – Lean LA](http://www.slideshare.net/ashmaurya/continuous-deployment-5548801 "Continuous Deployment - Lean LA")** from **[ashmaurya](http://www.slideshare.net/ashmaurya)**
    <iframe src='https://www.slideshare.net/slideshow/embed_code/5548801' width='427' height='350' scrolling='no' allowfullscreen webkitallowfullscreen mozallowfullscreen></iframe>
    
*   [Running Automated Tests in Parallel – Part 1](http://hindsighttesting.com/blog/2012/10/30/running-tests-in-parallel/)‘s new bit to me was how to run groups of jUnit scripts from maven. Sure, I think I knew there was a way to do it but never had to think about it before. And as a bonus, [here is the video that goes with the post](http://oredev.org/2012/sessions/cutting-testing-time-with-parallel-automated-functional-tests)
*   [PHP Sadness](http://phpsadness.com) is kinda amusing from a language trolling perspective. But also actually really useful. Now for a Ruby Sadness and a Python Sadness. Of course, a Perl sadness would just be a one of those ‘try to click this moving box’ widgets…
*   Following up on not automating GMail (aside through standard low-level protocols like IMAP or POP3) is a [MailBox class for processing IMAP email (Gmail from Python example](https://gist.github.com/4149804)
*   I’ll admit that [Writing JMeter test plans in Ruby](http://gridinit.wordpress.com/2012/11/27/writing-jmeter-test-plans-in-ruby/) has me pretty excited
*   But if Ruby/JMeter doesn’t do it for you, then maybe Funload will – [How to stress test your app using Funkload — part 1](http://ziade.org/2011/07/27/how-to-stress-test-your-app-using-funkload-part-1/)
*   Search is one of those things a lot of automation get burned by since things are often going in parallel and not casually waiting for someone else to look for something that was just put in the index half a second ago – [Moving the Marketplaces to Elasticsearch](http://webuild.envato.com/blog/moving-the-marketplaces-to-elasticsearch/)
*   [I Call Them ControlObjects](http://www.thefriendlytester.co.uk/2012/11/i-call-them-controlobjects.html); I call them Elements, or with increasing frequency ‘Unnecessary Designer Porn’ for the rash of js-widgets-that-look-like-standard-html
*   I’m kinda amazed I haven’t had to use the regex part of my brain in awhile, but everyone doing automation really needs to have that section tucked away somewhere – [A Practical Example of Regular Expressions](http://simplythetest.tumblr.com/post/33474441938/a-practical-example-of-regular-expressions)
*   [Low Power Server Monitoring with a Raspberry](http://www.triggeredmessaging.com/blog/server-monitoring-with-a-raspberry-pi-and-graphite) is just cool



