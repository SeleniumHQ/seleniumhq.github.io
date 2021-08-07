---
title: "Hacking with IntelliJ"
linkTitle: "Hacking with IntelliJ"
date: 2017-02-08
tags: ["jetbrains","intellij"]
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Jetbrains have a programme for Open Source projects which allows them to receive IntelliJ IDEA licenses.
---


[Jetbrains](https://www.jetbrains.com/) have a [programme for Open Source projects](https://www.jetbrains.com/buy/opensource/) which allows them to receive [IntelliJ IDEA](https://www.jetbrains.com/idea/) licenses. As part of that programme, which the Selenium project has participated in for many years, they’ve asked us to provide a fair and balanced review of IntelliJ. I’ll attempt to do that, and I’ll try and state my biases up-front so you’re aware of them.

I’ve been using Jetbrain’s IntelliJ to hack on the Selenium code since I started working on it slightly over ten years ago. It’s still my favourite IDE for my Java work, and it’s plenty of fun to use. For some time, I’ve been using the (free) Community Edition, which is ample for many coding needs.

Most of my work is in Java, and that’s where I know IDEA best. I dabble in Ruby and Python, and I’ve written a reasonably large amount of Javascript, all in IDEA.

The Pros:

In common with other good IDEs, IDEA has the ability to work seamlessly with many different languages. If you’re a polyglot programmer, being able to stay in the same tool for much of your work makes life a lot easier. On the Selenium project, we use Java, C#, Ruby, Python, and Javascript extensively. I don’t do any C#, and I mainly focus on Java, but the support for JS, Ruby, and Python is lovely and seems to work well. The built in type detection and code navigation features are impressive (particularly for untyped languages such as JS)

Of course, the feature that made IDEA so awesome in the first place is the range of refactoring options it offers. These are great, and always have been. One nice feature I’ve noticed as we move to a Java 8 future (finally!) is that it offers suggestions to help migrate to new features where they make sense (and, I’ll be honest, sometimes when they don’t). It’s made making use of lambdas a lot easier.

For a while, IDEA was becoming slower and more bloated, but I’m pleased to see that, partly thanks to the work of developers from [Facebook](http://facebook.com/), the latest releases feel snappier and handle larger projects more efficiently. One thing I appreciate is how open Jetbrains were to receiving patches to their core product: it displays a level of respect for external contributors that I feel is important (of course, I would think that: I work on OSS for fun!)

There’s a nice wide range of plugins available for IDEA. I’ve hooked up the Buck plugin and made use of it. Without an extensions API, this plugin wouldn’t have been possible, but having them there is incredibly useful and makes the IDE even more capable.

Finally for the plus points of the IDE, I love that the IDE tracks new versions of Java relatively closely — it’s fun to see what new language features we’ll be able to use in the future!

The Cons:

Although it’s a fine product, there are some niggles to be had.

Most annoyingly, the built in code analysis doesn’t always warn that some Java classes won’t compile. The most recent example was where IDEA didn’t flag that some lambdas couldn’t be used since the choice of method to use was ambiguous. This may be because the Java language continues its slothful way forward, and the compiler improves with each release — certainly these same files compiled just fine with older Java releases.

When an error does happen, I’ve yet to find the magic setting to allow IDEA to keep going as far as possible. One of the features I like about Eclipse is that it’ll compile as much as it can, even if there are invalid source files. When doing TDD, this allows you to move just a little bit faster as unit tests can run and pass so long as they don’t touch faulty code. I dearly wish this same capability was present in IDEA!

On the Selenium project, we use [Buck](http://buckbuild.com/) for our builds. The Buck plugin doesn’t (yet!) allow me to build and run tests within the IDE, yet Buck performs some steps that can’t be repeated by the IDE that are required for a successful build. IDEA offers the ability to run an [Ant](http://ant.apache.org/) step before a build is run, and it would be extremely useful if this was generalised to “any shell command”. Most of the time, it’s fine, but it’s irksome to forget to run things!

On the whole, I love IntelliJ an awful lot. It’s a fast and capable IDE, and the company behind it supports OSS. What’s not to love?