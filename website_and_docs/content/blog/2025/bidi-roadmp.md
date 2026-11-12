---
title: "Roadmap for adopting WebDriver Bidi"
linkTitle: "Proposed roadmap for adopting WebDriver Bidi and deprecating WebDriver Classic"
date: 2025-03-11
tags: ["selenium"]
categories: []
author: Diego Molina [@diemol](https://www.diemol.com)
description: >
  A roadmap for how and when Selenium will adopt WebDriver Bidi and deprecate WebDriver Classic.
---

Roadmap

In order to automate browsers, Selenium builds upon open standards
developed by the W3C. The first of these is [W3C
WebDriver](https://w3c.github.io/webdriver/), which was derived from
the original wire protocol used by Selenium 2, and this has been
powering our tests for many years. It works by using a
"request/response" model: the Selenium APIs send a command, the
browser executes that, and sends back a response which we process,
before we send another command. At any one time, there's only one
command being executed.

However, the Web evolves, and so does the WebDriver standard. The next
evolution of the W3C standard for browser automation is called
[WebDriver Bidi](https://w3c.github.io/webdriver-bidi/). This is a
more sophisticated protocol, leaning heavily on the lessons learned
from the [Chrome DevTools
Protocol](https://chromedevtools.github.io/devtools-protocol/) (CDP),
and the key difference is that rather than following a
"request/response" model, Bidi now uses an event-based model. While
this allows the Selenium APIs to ask the browser to execute actions,
the browser can now send information back at any time to our
APIs. This allows a more dynamic style of testing than we've been able
to achieve with the original WebDriver protocol.

One goal of Bidi is that it should be possible to use it for
everything that the original spec (now commonly referred to as
"WebDriver Classic" or just "Classic") could do. It seems natural that
if this is the case, we should consider phasing out the Classic
protocol and only relying on Bidi, just as we phased out support for
the original "[JSON Wire
Protocol](https://www.selenium.dev/documentation/legacy/json_wire_protocol/)". Just
as with that transition, we will do this carefully so that our users
(you\!) shouldn't notice the change.

How will we go about this? While this plan can still change, the approach we are taking is:

1. Focus our development time on Bidi.  
2. Use Bidi to add extra features, such as network interception and capturing JS logs.  
3. Start to use Bidi in preference to Classic where possible (for example, for navigating to new URLs, or for finding elements)  
4. Once the Bidi spec becomes a [Candidate Recommendation](https://www.w3.org/standards/types/#x4-2-candidate-recommendation), cease development of the Classic implementation and mark it deprecated.  
5. Once the Bidi spec becomes a Recommendation, start phasing out Classic support. This will mean that in the next major Selenium release (that is, Selenium 5), only the Java bindings will retain optional legacy Classic support.  
   

The developers of the Selenium project have already started the first
two steps, and we shall soon start on the third.

The Bidi spec is now moving forwards quite rapidly, and the first
public working draft has been published. There are multiple
implementations of the spec already available, and while it’s being
adopted by Selenium, it’s also being used by other tools such as
Puppeteer, Cypress, and even Playwright.
