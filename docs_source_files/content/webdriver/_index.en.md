---
title: "WebDriver"
chapter: true
weight: 5
---

# WebDriver

The biggest change in Selenium recently
has been the inclusion of the WebDriver API.
Driving a browser natively, as a user would, either locally
or on a remote machine using the Selenium server,
marks a leap forward in terms of browser automation.

Selenium WebDriver fits the same role as RC did,
and has incorporated the original 1.x bindings.
It refers to both the language bindings
and the implementations of the individual browser controlling code.
This is commonly referred to as just _WebDriver_
or sometimes as _Selenium 2_.

Selenium 1.0 + WebDriver = Selenium 2.0

* WebDriver is designed in a simpler
  and more concise programming interface
  along with addressing some limitations in the Selenium-RC API.

* WebDriver is a compact object-oriented API
  compared to Selenium 1.0.

* It drives the browser much more effectively
  and overcomes the limitations of Selenium 1
  that affected our functional test coverage,
  like the file upload or download, pop-ups, and dialogs barrier.

* WebDriver overcomes the limitation of Selenium RC's 
  [single-host origin policy](//en.wikipedia.org/wiki/Same-origin_policy).
