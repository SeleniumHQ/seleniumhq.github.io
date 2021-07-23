---
title: "A Smattering of Selenium #65"
linkTitle: "A Smattering of Selenium #65"
date: 2011-10-18
tags: ["smattering"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  Trying something new; queuing up the catch-up post while I have time to catch-up.
---

Trying something new; queuing up the catch-up post while I have time to catch-up.

*   I’ve been lead to believe that [Functional Testing with Arquillian](http://community.jboss.org/en/arquillian/blog/2011/09/17/functional-testing-with-arquillian) is a Big Deal&tm;. Of course, I just see a lot of Java…
*   [If one thread raises an exception, why aren’t other threads’ finally blocks called?](http://stackoverflow.com/questions/7700929/python-multiprocessing-map-if-one-thread-raises-an-exception-why-arent-other) is an interesting question with a nice answer. If you start running scripts in parallel, this, and the GIL is something to keep in mind.
*   My favourite part of posts like [Using Test Oracles to Ensure Your Tests’ True Validity](http://blogs.telerik.com/jimholmes/posts/11-10-15/using-test-oracles-to-ensure-your-tests-rsquo-true-validity.aspx) is they always have a ‘this is how I learned this the hard way’ story in them.
*   I’m getting more and more convinced that the route to WebDriver powah! is through the JavascriptExecutor. As illustrated in [setContext(..) is back for Selenium2 😛](http://paulhammant.com/2011/09/30/setContext-back-for-selenium2/)
*   One important trick to maven is knowing how / where to bind server control to. [Testing webapp startup on Jenkins using Maven, Tomcat and Web Driver](http://blog.synyx.de/2011/10/testing-webapp-startup-on-jenkins-with-maven-tomcat-and-web-driver/) might be a bit grand a title, and is using HTMLUnit (why?! do your customers use that browser? really?) but illustrates the maven part well.
*   Since we’re in the HTML (findin yur bugz) whilst automating anyways, [You’re Doing it Wrong: Common HTML Tag Misuses](http://line25.com/articles/youre-doing-it-wrong-common-html-tag-misuse) is something to keep at the back of your mind.
*   And since we’re critiquing the HTML, why not how the database schema is designed and its impacts on API design. [Identifiers are not numbers](http://www.brunton-spall.co.uk/post/2011/09/24/identifiers-are-not-numbers/)
*   [Patchwork](https://github.com/antecedent/patchwork) is a library that lets you monkey patch in PHP. Haven’t found a need for it yet, but I keep thinking I will.
*   [Making Facebook Self-Healing](https://www.facebook.com/notes/facebook-engineering/making-facebook-self-healing/10150275248698920) is just darn cool. And unfortunately most things in the Immunization/Healing space are highly customized and not really candidates for open sourcing.
*   [Taking screenshots with Selenium WebDriver](http://www.jimmycollins.org/blog/?p=483) in C#