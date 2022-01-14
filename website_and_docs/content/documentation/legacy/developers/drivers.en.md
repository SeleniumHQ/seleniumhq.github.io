---
title: "Adding new drivers to Selenium 2 code"
linkTitle: "Drivers"
weight: 4
description: >
    Instructions for how to create tests for new drivers for Selenium 2.
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Writing-New-Drivers) \

## Introduction

WebDriver has a comprehensive suite of tests that describe the expected behavior of a new implementation. We'll assume that you're implementing the driver in Java for the sake of simplicity, but you can take a look at any of the existing implementations for how we handle more complex builds or other languages once you've read this.

## Writing a New WebDriver Implementation

### Create New Top-Level Directories

Create a new top-level folder, parallel to "common" and "firefox", named after your browser. In this, create a "src/java" and a "test/java" directory. It should be obvious what goes where.

### Set Up a Test Suite

Copy one of the existing test suites to your test tree, and modify it for your new browser. This will probably cause you to modify the "Ignore.java" class, which is to be expected, and to add a holding class for your implementation in the source tree. You **must** include the "common" directory in order to pick up all the tests. For now, as long as nothing causes a fatal crash, leave the tests as they are.

Once you've added the test suite, add a "build.desc" CrazyFunBuild file in the top level of your project. Model it after the one in the "htmlunit" directory. You should then be able to run your tests from the command line using the "go" script.

At this point, we expect total and catastrophic failure when tests are being run.

### Start Implementing

If your browser runs out of process, it is _strongly encouraged_ to make use of the JsonWireProtocol. This will make the client-side (the APIs that users use) relatively cheap to implement, and means that you get Java, C#, Ruby and Python support for significantly less effort since you can extend the remote client.

## Implementation Tips

### Where to Start

As mentioned, has a suite of tests. The suggested order to make these pass is roughly:

1. ElementFindingTest --- needed because element location is key
1. PageLoadingTest
1. ChildrenFindingTest --- more finding elements
1. FormHandlingTest
1. FrameSwitchingTest
1. ExecutingJavascriptTest
1. JavascriptEnabledDriverTest

At this point, you'll have a reasonably complete working driver. After that, it's probably best to get the user interactions correct:

1. CorrectEventFiringTest
1. TypingTest

Before spelunking into the cutting-edge stuff:

1. AlertsTest

It's not necessary to get every test working in a class before moving on. I tend to go as far down a class as I can, and then switch to the next class on the list when the going gets tough. This allows you to maintain reasonable velocity and still cover the basics.

### Running a Single Test

It's far from ideal, but the method we use is to modify the SingleTestSuite class in the common project, and then modify the module it's run from via the IDE's UI (that is, just go into the launch configuration (in IDEA) and modify the module used: don't move the file!) This class should be self-explanatory.

### Ignoring Tests

At some point you'll want to stop running tests on an ad-hoc basis and make use of a continuous build product to ensure that you're not introducing regressions. At this point, the process is to run the tests from the command line. This will generate a list of failing tests. Go through each of these tests and add or modify the "@Ignore" associated with the test. Re-run the tests. It may take a few iterations, but your build will eventually go green. Nice.

The build makes use of ant behind the scenes and stores logs in "build/build\_log.xml" and the test logs in "build/test\_logs"
