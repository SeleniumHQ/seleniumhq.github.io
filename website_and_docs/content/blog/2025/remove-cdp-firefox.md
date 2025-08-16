---
title: "Removing ChromeDevTools Support For Firefox"
linkTitle: "Removing ChromeDevTools Support For Firefox"
date: 2025-02-03
tags: ["selenium"]
categories: ["releases"]
author: Puja Jagani [@pujagani](https://www.linkedin.com/in/pujajagani/)
description: >
  Today we're happy to announce ChromeDevTools support for Firefox is removed and WebDriver BiDi is paving the path forward
---

Selenium has deprecated support for Chrome DevTools Protocol (CDP) for Firefox in the last two versions (4.27 and 4.28). Our typical removal policy is to deprecate support for two versions, allowing users sufficient time to update their codebase, and then remove it from the third version onwards.

Starting with Selenium 4.29.0, CDP support for Firefox has been fully removed—and for good reason. Selenium’s CDP implementation for Firefox was always partial, meaning it never had complete feature parity with Chrome. Meanwhile, Firefox is shifting towards WebDriver BiDi, the future of cross-browser automation. Aligning with this, Firefox has announced that starting with Firefox 129, CDP will no longer be enabled by default. Read more here: [https://fxdx.dev/deprecating-cdp-support-in-firefox-embracing-the-future-with-webdriver-bidi/].

To support this transition, Selenium is removing CDP support for Firefox, as a major portion of WebDriver BiDi functionality is now available across all Selenium language bindings. Selenium is committed to staying in sync with browser vendors and the latest developments in the WebDriver BiDi protocol. This step brings us closer to standardized, browser-agnostic automation.

If you were using CDP in Selenium for Firefox, now is the time to switch to WebDriver BiDi. Start your journey with Selenium’s WebDriver BiDi examples [here](https://www.selenium.dev/documentation/webdriver/bidi/w3c/).