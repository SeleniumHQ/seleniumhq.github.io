---
title: "Selenium's Continuous Integration Implementation"
linkTitle: "CI Tool"
weight: 6
description: >
  We used to have a Jenkins CI tool that executed unit tests and ran integration tests on Sauce Labs.
  We moved all of the tests to Travis, and now execute everything with Github Actions.
---
This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Continuous-Integration)

## General architecture

We have a number of Google Compute Engine virtual machines running Ubuntu, currently hosted at {0..29}.ci.seleniumhq.org - they have publicly addressable DNS set up to point [ab](ab.md).{0..29}.ci.seleniumhq.org pointing at them as well, so that cookie tests can do subdomain lookups.

One of these machines, ci.seleniumhq.org, is running jenkins. If you want a login on jenkins, get in touch with juangj.  The Build All Java job polls SCM for changes, and does the following:
* Does a clean build of the 'release' target, any tests which are going to be run, and any artifacts (e.g. the IEDriverServer executable) which will be required to run those tests
* Tars up the entire built working directory and publishes it to http://ci.seleniumhq.org/selenium-trunk-r${REVISION}.tgz - this is used later by test runs
* Publishes the selenium-server-standalone jar to http://ci.seleniumhq.org/selenium-server-standalone-r${REVISION}.tgz - this is copied down directly by [SauceLabs](http://saucelabs.com) when running tests.
* Zips up the IEDriverServer and publishes it to http://ci.seleniumhq.org/IEDriverServer-Win32-r${REVISION}.zip - this is copied down directly by [SauceLabs](http://saucelabs.com) to run IE tests
  This machine is backed by a 1TB persistent disk, which can hold many build artifacts, but they should be cleared out occasionally (particularly when moving disk between zones).

When this build is successful, it triggers downstream builds for each OS/browser/test combination we care about.  It also triggers a downstream clean build to ensure our maven poms are still in order ("Maven build").

Apart from "Maven build" which runs on the same build node as the compile (a beefy, 8-CPU machine with 32GB RAM), all downstream builds run on separate build nodes.

The downstream builds are configured using environment variables, as per the [SauceDriver](https://github.com/SeleniumHQ/selenium/blob/master/java/client/test/org/openqa/selenium/testing/drivers/SauceDriver.java) class.  The downstream builds download the selenium-trunk tar from the build master, and then run tests (which should already have been compiled by the Build All Java rule).  Two of these downstream builds are special; "HtmlUnit Java Tests" and "Small Tests" just run headless locally.  The others use [SauceLabs](http://saucelabs.com).

A note about networking: The build nodes are set up on an internal network 10.1.0/24, so network communication between them is incredibly fast and free.

When a non-headless browser test is running, the test-file servlet hosts the test files on ports determined by an environment variable (231${EXECUTOR\_NUMBER} and 241${EXECUTOR\_NUMBER} - EXECUTOR\_NUMBER is currently always equal to 0).  The hostname used by tests is set by an environment variable ([ab](ab.md).${NODE\_NAME}.ci.seleniumhq.org where NODE\_NAME in {0..29}).  A browser is requested from [SauceLabs](http://saucelabs.com) using our credentials (stored in jenkins-wide environment variables, set on the System Configuration page).  Jenkins is currently set to run three test-classes at a time in parallel, per test run, again on the System Configuration page.

The tests are run, and the results get notified to IRC.

Thanks to [SauceLabs](http://saucelabs.com) and [Google](http://cloud.google.com/products/compute-engine.html) for donating the infrastructure to run all of these tests.

## FAQ

### I want to run my tests on Sauce like Jenkins does (my tests are failing on CI, but work fine on my machine!)

See the [SauceDriver](Sauce.md) page

### I want to add a new browser (Firefox has released a new version!)

Jenkins doesn't have a great concept of templates.  I (dawagner) have some selenium scripts which automate the UI of Jenkins, to create new jobs using canned settings.  If you want to do it manually, here are roughly the steps to take:
* Find the most similar config(s) you want to copy.  If it's a new Firefox release, find the latest firefox (which should have roughly 6 builds associated with it: Javascript + Java {Windows,Linux} **{Native,Synthesized}
* For each of those builds, create a New Job (menu on the left hand side of the home page, when logged in)
* Name the job in the style of the others.  Select "Copy existing job", and enter the job you're copying.
* Scroll through the job it's pre-populated.  Replace the version numbers, browser name, and any other details that need replacing.  For firefox updates, there are currently three places you should be replacing the number (the "browser\_version" field, and two in the Build Execute Shell)
* Save
* Go to the Build All Java task, configure it, add your new build to the "Projects to build" field where there are many others listed.**

If it's a firefox update, you probably also want to delete an existing build.
