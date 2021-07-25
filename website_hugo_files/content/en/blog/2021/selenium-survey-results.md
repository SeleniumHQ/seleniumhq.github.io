---
title: "Results of the first ever selenium survey"
linkTitle: "Results of the first ever selenium survey"
date: 2021-01-12
tags: ["webdriver", "survey"]
categories: ["general"]
author: David Burns ([@AutomatedTester](https://twitter.com/AutomatedTester))
description: >
  Summary of the Selenium survey that was collected
---


Below is a summary of the Selenium survey that was collected.

## Batteries included
### Browser Management

Unsurprisingly, people find having to manage browsers a task they wish they didn’t have to do and wish that Selenium did this. 59.5% of respondents want Selenium to manage the browsers for them. This, though the question didn’t ask this, is to include the browser drivers.

### Frameworks

The results show an interesting view into framework usage. 61% of users use a framework. When we look closer at some of the responses there could be a little language bias in there. Some responses in the “both” category are “depends on the language” or “depends on the project”. Frameworks, from a couple responses seem to be around JavaScript mostly.

## How tests are run

One of the questions that was the most surprising was that people dislike running their tests in headless. 57% of people don’t want headless. This is surprising since puppeteer and playwright are up and coming tools that default to headless.

### Browsers

People tend to favour evergreen, or the latest version from auto updating browsers, with over 55% of people wanting that. When we look at the data closer then people tend to do evergreen and then a specific version for IE.

People are also, overwhelmingly I should add, wanting to test on multiple browsers. 78% of people test with multiple browsers. This goes against the Chrome only support that we see on social media. The most used browsers for testing are (Percentage of respondents testing with browser):

* Google Chrome (98%)

* Firefox (67%)

* Microsoft Edge (33%)

* Safari (29%)

* IE (13.5%)

One thing that is clear is that Testing against multiple browsers is hard. 51.6%  say it’s hard and a further 27.3% being 3 out of 5. This is likely where some frameworks are becoming popular as it removes some of the need to understand the asynchronicity of browsers. This still has its problems in puppeteer and playwright as they add similar wait libraries to what Selenium has.

### Mobile Browsers

One of the most surprising things to have come out of survey was the want to use mobile browsers more.

Of the 410 respondents 367 want to test on mobile. Interestingly, this goes well past just Chrome for Android and Safari for iOS.

Below is a list of browsers and percentage of users wanting to use it. Questions were designed for Android unless it said iOS. See note at the end of the section.

* Firefox for Android: 38%

* Chrome: 91%*

* Microsoft Edge: 14%

* Samsung Internet: 16.6%

* Safari on iOS: 69%

*Note that the questionaire only mentioned Chrome so it could be a conflation between platforms here. 1 Person used the other field to add Chrome for iOS.

### Language support

Language support is what one would suspect with most users using Java or python.

* java: 67%

* python: 31%

* ruby:  6.1%

* .NET: 17.6%

* JavaScript: 21.4%

* PHP: 1.5%

## Improvements

After reviewing the “What one thing would you improve about Selenium I was able to narrow most of the responses down to similar types.

Below is a breakdown of the issues that people would like to see improved.

{{<figure src="/images/blog/2021/breakdown_of_answers.png" alt="Breakdown of answers of areas needing improvement. Main 3 areas are flakiness, documentation, Browser Management, Shadow DOM">}}

## Happiness

Of the 410 respondents, 219 had something nice to say about Selenium and were thankful for the product being around.

Some of the main reasons are simplicity of APIs, good backwards compatibility, and it being open source
