---
title: "HtmlUnit Driver"
linkTitle: "HtmlUnit Driver"
weight: 5
description: >
    The headless, pure Java WebDriver implementation based on HtmlUnit.
---

This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/HtmlUnitDriver) \
HtmlUnitDriver is now maintained by the HtmlUnit project in its own
[repository](https://github.com/SeleniumHQ/htmlunit-driver).

This is currently the fastest and most lightweight implementation of WebDriver. 
As the name suggests, this is based on HtmlUnit.

### Pros

* Fastest implementation of WebDriver
* A pure Java solution and so it is platform independent.
* Supports Javascript

### Cons

* Emulates other browser's JS behaviour (see below)

## Javascript in the HtmlUnitDriver

None of the popular browsers uses the javascript engine used by HtmlUnit 
([Rhino](https://github.com/mozilla/rhino)). If you test javascript using HtmlUnit the 
results may differ significantly from those browsers.

When we say "javascript" we actually mean "javascript and the DOM". Although the DOM is 
[defined by the W3C](https://www.w3.org/DOM/) each browser out there has its own quirks 
and differences in their implementation of the DOM and in how javascript interacts with it. 
HtmlUnit has an impressively complete implementation of the DOM and has good support for 
using javascript, but it is no different from any other browser: it has its own quirks and 
differences from both the W3C standard and the DOM implementations of the major browsers, 
despite its ability to [mimic other browsers](https://htmlunit.sourceforge.io/javascript.html).

With WebDriver, we had to make a choice; do we enable HtmlUnit's javascript capabilities 
and run the risk of teams running into problems that only manifest themselves there, or do 
we leave javascript disabled, knowing that there are more and more sites that rely on 
javascript? We took the conservative approach, and by default have disabled support when 
we use HtmlUnit. With each release of both WebDriver and HtmlUnit, we reassess this 
decision: we hope to enable javascript by default on the HtmlUnit at some point.

## Enabling Javascript

If you can't wait, enabling Javascript support is very easy:

```java
HtmlUnitDriver driver = new HtmlUnitDriver();
driver.setJavascriptEnabled(true);
```

or

```java
HtmlUnitDriver driver = new HtmlUnitDriver(true);
```

This will cause the HtmlUnitDriver to emulate IE's Javascript handling by default.

## Emulating a Specific Browser

Notwithstanding other considerations above, it is possible to get HtmlUnitDriver to 
emulate a specific browser. You should not really be doing this, as web-applications are 
better coded to be neutral of which reasonably recent browser you are using. There are two 
more constructors for HtmlUnitDriver that take allow us to indicate a browser to emulate. 
One takes a browser version directly:

```java
HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_3);
```

The other uses a broader capabilities mechanism:

```java
HtmlUnitDriver driver = new HtmlUnitDriver(capabilities);
```
