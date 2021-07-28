---
title: "Android and iOS Support"
linkTitle: "Android and iOS Support"
date: 2013-12-24
tags: ["selenium", "mobile"]
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  TL;DR: We’re retiring Selenium’s own AndroidDriver and iPhoneDriver in favour of any of Selendroid, iosdriver and Appium.
---


TL;DR: We’re retiring Selenium’s own AndroidDriver and iPhoneDriver in favour of any of [Selendroid](http://selendroid.io/), [iosdriver](http://ios-driver.github.io/ios-driver/) and [Appium](http://appium.io/). If you’re using one of Selenium’s own mobile drivers, please evaluate one of these alternatives.

The longer version:

In 2007, Steve Jobs [announced the iPhone](http://www.apple.com/pr/library/2007/01/09Apple-Reinvents-the-Phone-with-iPhone.html) and changed the mobile Web from a curiosity to something the mainstream wanted and used. Current trends suggest that mobile Web usage will surpass desktop usage in the not too distant future. Which is a long way of saying the mobile Web is going to be a big part of the future of your sites and that it’d be an extremely wise idea to test them on mobile devices.

The Selenium project responded to the rise of the mobile web by working to produce WebDriver implementations for both iOS and Android. The first lines of the iPhoneDriver (which also worked on the iPad) were added to the project early in 2009. The AndroidDriver was added in June 2010, and was primarily developed by engineers at Google. To this day you can download the official Android SDK and find “[Google WebDriver](http://android-developers.blogspot.com/2011/10/introducing-android-webdriver.html)” as one of the optional extras you can download.

After the initial work on the mobile drivers, something interesting happened. Experimental extensions and modifications to the drivers were made outside of the selenium project. The first one of these that I was involved with was “[nativedriver](https://code.google.com/p/nativedriver/)“. This took the novel approach of allowing users to interact with the native UI of the phone, be it Android or iOS, using the familiar WebDriver APIs. The first time I saw it, I thought it was madness, but the engineers working on it soon convinced me that it made sense. And guess what? They were right.

Sadly, after proving the idea was viable and workable, the NativeDriver project ran out of steam, but it set the scene for three projects that have taken the idea and run with it to create remarkably capable pieces of mobile testing software: Selendroid, iosdriver and Appium. All three of these allow a tester familiar with the WebDriver APIs to test mobile apps on iOS and Android. Not only native ones, but also hybrid or pure web-based ones too. They’ve recently been joined by the [Windows Phone WebDriver](http://winphonewebdriver.codeplex.com/), which allows testing of mobile web apps on WinPhone 8.

All of these projects have something in common: they’re far more active, more capable and have pushed further than the equivalent code in the main selenium project. In fact, some of the members of the selenium team that contributed to both AndroidDriver and iPhoneDriver are now also working on those other projects. There’s [work being done](http://sauceio.com/index.php/2013/09/the-mobile-json-wire-protocol-workshop/) to [maintain interoperability](https://code.google.com/p/selenium/source/browse?repo=mobile) between the different drivers, allowing users to chose which framework is most appropriate for their needs without fear of their tests needing major rework.

This means that keeping the existing Android and iPhone drivers within the Selenium project isn’t helping our users. The alternatives are better, and keeping “official” drivers within the project muddies the water. Worse, the selenium developers are slow at making fixes to those drivers, which is incredibly frustrating for everyone involved. Because of this, the Selenium project has deleted the code for those drivers from its repository and we recommend you evaluate and use one of the alternatives.

Of course, the code will still live in our repo’s history, so if you’d like to build them yourself, then it’s still possible. The last version with the iPhoneDriver is [ef9d578](https://code.google.com/p/selenium/source/detail?r=ef9d5787e5e136ecb4a31b0cf53a1fd17e252cf3), and the last one with the Android source is [00a3c7d](https://code.google.com/p/selenium/source/detail?r=00a3c7df9fb4109e1f99b5f7bee5f5df74fb876a). We’ve uploaded a version of the AndroidDriver built from that revision to the downloads page to save you having to do so yourself.

These changes do not mean that we don’t support mobile as a project. It just means that we support the best implementations of mobile WebDriver, and those aren’t written as part of the Selenium project.