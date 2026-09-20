---
title: "The two-week Flutter: Why Chrome’s New Cadence is a 'Non-Event' for Selenium Users"
linkTitle: "Chrome Moves to two-week releases"
date: 2026-03-10
tags: ["selenium"]
categories: ["general"]
author: David Burns [@automatedtester](https://www.linkedin.com/in/theautomatedtester//)
description: >
   This blog post discusses the move of Chrome going to do a 14 day release cycle and how it's mostly a non-event for Selenium users.
---


If you’ve been following the Chromium blog, you’ll have seen the news: Chrome is moving to a two-week release cycle. Starting in September 2026, the pace of the web is effectively doubling. In the old days of manual browser driver management, a faster release cadence meant more frequent SessionNotCreatedException errors and more time spent hunting for the right chromedriver binary to match your updated browser.

But I’m here to tell you: Don’t panic. If you are using a modern version of Selenium (v4.11 or newer), this change is effectively a "non-event." Thanks to Selenium Manager, the days of manually synchronizing your browser and driver are over.

## The Problem: The Versioning Treadmill

Historically, automation engineers were stuck in a reactive loop. Chrome would auto-update in the background, your tests would fail because the driver on your PATH was stale, and you'd spend your morning manually downloading a .zip file.

As the release cycle moves to every two weeks, the "manual" cost of maintenance becomes unsustainable. You shouldn't be a "Binary Manager"; you should be a Test Engineer.

## The Solution: Selenium Manager & Chrome for Testing (CfT)

A few years ago, the Selenium project introduced Selenium Manager, a tool bundled with every Selenium release. It works in tandem with Google’s Chrome for Testing (CfT)—a dedicated flavor of Chrome specifically for automation that doesn't "stealth update" and has its own versioned endpoints.

When your code starts a session, Selenium Manager silently handles the heavy lifting:

Detection: It checks which version of Chrome is currently on your machine.

Resolution: It discovers the matching ChromeDriver version via the CfT endpoints.

Acquisition: If Chrome isn't found (or you need a specific version), it downloads the correct Chrome for Testing binary and the driver for you.

Caching: It stores these in `~/.cache/selenium` so you aren't re-downloading them every time.

### What this looks like in practice

You don't need to change your code to handle the two-week updates. Follow our [docs](https://www.selenium.dev/documentation/selenium_manager/#using-selenium-manager-from-the-bindings) on how to use it, including if you need to pin your tests to a specific version of Chrome.

## The Bottom Line

The web is moving faster, and browser vendors are shipping features and security patches at an incredible rate. Our goal at Selenium is to ensure that the infrastructure of your tests remains invisible.

The move to a two-week cycle is a testament to how far web engineering has come. With Selenium Manager, you can enjoy the benefits of a modern browser without the headache of manual maintenance.

Keep your tests green and your drivers automated.
