---
title: "IntelliJ"
linkTitle: "IntelliJ"
date: 2015-02-08
tags: ["jetbrains","intellij"]
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  Every year, Jetbrains are kind enough to donate an OSS license for IntelliJ to the Selenium project.
---


Every year, Jetbrains are kind enough to donate an OSS license for IntelliJ to the Selenium project. As part of that process, they’ve asked that we review the product and (kudos to them!) have been clear that they hope we’re open and honest. So, I’ll be open and honest.

When I tell people that I’m a professional Java developer, people in some circles make sympathetic noises and (sometimes) jokingly refer to how painful my coding life must be. After all, there are several far trendier and hipper languages, from Ruby, various flavours of Javascript, Python, Haskell, and even other languages running on the JVM such as Scala and Clojure. I tend to agree that Java is a relatively unexciting language as it’s generally practiced — Java 8 contains a wealth of goodies that lots of people won’t be using for years since they’ve still got to support Java 6(!) apps. Where I disagree with the detractors is that using Java is something to feel sorry for a developer for: Java on its own isn’t much fun, Java and IntelliJ is one of my favourite programming experiences.

I’ve been using Java since the (very) late 90s, and have been using IntelliJ off-and-on since 2003 or so. In the intervening just-over-a-decade, what started as a tool that crossed the Rubicon of “being able to do refactoring” has matured. It has literally changed the way I write code: I now use the “Introduce Variable” refactoring to avoid needing to do initial assignments of values to variables as a matter of course. Indeed, with IntelliJ, I frequently stop thinking about the programming language and start thinking about the structure of the solution. Its refactorings make exploring large scale changes easy and entirely reliable, and once the restructurings are complete, I can jump to symbols with ease.

Code exploration is aided by the simple and quick ways IntelliJ can find usages, and it’s simple to find unused code as method declarations get highlighted in a different shade to used ones. The integrated debugger is sufficiently capable that, coupled with unit tests, it’s normally pretty easy to figure out why some odd behaviour is happening. And, speaking of unit tests, the UI is clear and (I find) intuitive and easy to use.

And those users of fancy-pants languages such as Clojure, Ruby, Python and Javascript (and PHP) can get plugins that extend IntelliJ’s capabilities and insight into those languages. Although it’s been a long time since I’ve had to deal with Spring and JEE, when I do IJ has my back, grokking the config files. The maven and gradle integration appears to work too, though Selenium uses CrazyFun and is migrating to Buck, so I’ve seldom any need to

It’s not all wonder and joy. On large, multi-module codebases, IntelliJ seems to spend too long building caches. Activity Monitor on the Mac suggests it’s doing this in a single threaded manner, which is wasteful on a multicored machine. Switching away from IJ, doing something on the command line involving source control and then switching back is a sure-fire way to make it rebuild the caches, making it unresponsive. Extending IntelliJ by writing plugins is a black art — the documentation is scattered and appears out of date, making getting started on writing one hard.

Overall, though, I love IntelliJ. On the Selenium project, it’s the IDE of choice, and I’ve been incredibly productive in it. Thank you, Jetbrains, for a wonderful tool.