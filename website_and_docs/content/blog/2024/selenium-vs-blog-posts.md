---
title: "Selenium Vs … blog posts"
linkTitle: "Selenium Vs … blog posts"
date: 2024-01-09
tags: ["selenium"]
categories: ["general"]
author: David Burns ([@AutomatedTester](https://twitter.com/automatedtester))
description: >
   This blog post discusses the click bait posts out there comparing selenium, cypress, and playwright. How none of these are meaningful or helpful.
---

The easiest way to clickbait a blog post about automated testing is to compare Selenium against another tool in the space with a catchy title especially when it talks down about the incumbent.

Unfortunately, this can lead to muddying the waters of which features are available in any of the products out there especially when we compare apples to apples.

Selenium has always been a great tool for browser automation. Fortunately for the project, it has become the tool of choice for testing web applications for nearly 2 decades. The area this project has focused on is building out the hard parts of browser automation that are increasingly difficult. Stable APIs and scalability of the infrastructure to run Selenium has always been the priority of the project. It hasn’t focused on how people test with it because there are very good test frameworks out there and having to do it for 5 different languages is a non-trivial amount of engineering effort.

However, some particular misconceptions regularly reappear across these blog posts.

## It’s too hard to set up browsers and drivers compared to Playwright and Cypress

This used to be true in the past as you had to download the drivers. This wasn’t too bad for GeckoDriver and SafariDriver as they could handle browser upgrades gracefully. On the other hand, you need to update the drivers for Chromium-based browser for every new release.

For over a year now, Selenium handles this automatically. If it can’t find a ChromeDriver or EdgeDriver, it will download it using [Selenium Manager](https://www.selenium.dev/blog/2022/introducing-selenium-manager/). Since its first release it has improved a lot and it is now probably the best in class since the latest versions of Selenium will even download browsers if it can and use that. Compared to Playwright and Cypress you don’t need to update your dependency on Selenium to update browsers and drivers, you still use the same browsers as your customers, and switching versions becomes a breeze: you don’t also have to change the test framework you’re using. And, let's not forget that it uses the browser that [Google recommends you use for testing](https://developer.chrome.com/blog/chrome-for-testing/).

## Setting up a test runner is hard work where Playwright and Cypress have it built in…

Well… maybe? Setting up E2E test frameworks with Selenium isn’t as difficult as some might suggest. The hard part really is making sure that the driver is in the right place and we’ve solved that as discussed above. Once that’s done, Selenium’s approach allows you to use whichever test runner you’re most comfortable with. If you’d like a “batteries included” approach, with Selenium tightly integrated with the test runner, then one of the many projects that use Selenium, such as [SeleniumBase, Nightwatch, Serenity, and so on](https://www.selenium.dev/ecosystem/#frameworks), might be the right tool for you.

One thing to note is that Playwright is the only multi-language browser automation framework like Selenium. However, if you don’t use TypeScript or JavaScript you will still need to do the setup of the test runner yourself. Some testing frameworks have plugins that automatically set up the fixtures you might need. In the JavaScript/TypeScript space if you really need a built-in test runner there are downstream projects like NightwatchJS and tangential projects like WebdriverIO. Downstream projects use our libraries and tangential projects have their own libraries but still follow the WebDriver standard.

## Playwright and Cypress can do network interception and allow me to write event-driven code unlike Selenium

Selenium has been able to offer this since Selenium 4 came out. It’s so good that even [Playwright suggest you use it for scaling your tests](https://playwright.dev/docs/selenium-grid). The Selenium Project won’t be removing this anytime soon as we are dependent on WebDriver BiDi specification being implemented for those features to replace them. Even then Selenium has a history of trying to make sure that upgrades don’t break anything without sufficient warning. It’s why each language provides high-level wrappers, such as the `NetworkInterceptor`, that isolate your tests from the underlying technology being used.

## Summary

As we have seen from the above Selenium is still as good as the products out there. One thing that is different for Selenium from Cypress or Playwright is that we’re a volunteer-driven project and not commercially backed. Want to help us out? Why not write a blog post about how you’re using the features above or post on social media how these features make your lives easier?
