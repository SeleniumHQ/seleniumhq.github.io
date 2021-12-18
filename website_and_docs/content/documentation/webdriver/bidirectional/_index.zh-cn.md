---
title: "双向功能"
linkTitle: "双向"
weight: 16
aliases: [
"/documentation/zh-cn/webdriver/bidi_apis/",
"/zh-cn/documentation/webdriver/bidi_apis/"
]
---

Selenium is working with browser vendors to create the
[WebDriver BiDirectional Protocol](https://w3c.github.io/webdriver-bidi/)
as a means to provide a stable, cross-browser API that uses the bidirectional
functionality useful for both browser automation generally and testing specifically.
Before now, users seeking this functionality have had to rely on
with all of its frustrations and limitations.

The traditional webdriver model of strict request/response commands will be supplemented
with the ability to stream events from the user agent to the controlling software via WebSockets,
better matching the evented nature of the browser DOM.

Because it's a bad idea to tie your tests to a specific version of a specific browser,
the Selenium project recommends using WebDriver BiDi wherever possible.
However, until the spec is complete there are many useful things that the CDP offers.
To help keep your tests independent and portable, Selenium offers some useful helper classes.
At the moment, these use the CDP, but when we shall be using WebDriver Bidi as soon as possible
