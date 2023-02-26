---
title: "Understanding Common Errors"
linkTitle: "Errors"
weight: 2
description: >
  How to get deal with various problems in your Selenium code.
aliases: [
"/exceptions/",
"/exceptions/invalid_selector_exception.html",
"/exceptions/no_such_element.html",
"/exceptions/stale_element_reference.html",
]
---

## Invalid Selector Exception

CSS and XPath Selectors are sometimes difficult to get correct.

### Likely Cause

The CSS or XPath selector you are trying to use has invalid characters or an invalid query.

### Possible Solutions

Run your selector through a validator service:
* [CSS Validator](http://csslint.net/)
* [xPath Validator](http://www.freeformatter.com/xpath-tester.html)

Or use a browser extension to get a known good value:
* [SelectorsHub](https://selectorshub.com/selectorshub/)

## No Such Element Exception

The element can not be found at the exact moment you attempted to locate it.

### Likely Cause

* You are looking for the element in the wrong place (perhaps a previous action was unsuccessful).
* You are looking for the element at the wrong time (the element has not shown up in the DOM, yet)
* The locator has changed since you wrote the code

### Possible Solutions

* Make sure you are on the page you expect to be on, and that previous actions in your code completed correctly
* Make sure you are using a proper [Waiting Strategy]({{< ref "/documentation/webdriver/waits" >}})
* Update the locator with the browser's devtools console or use a browser extension like:
  * [SelectorsHub](https://selectorshub.com/selectorshub/)

## Stale Element Reference Exception 

An element goes stale when it was previously located, but can not be currently accessed.
Elements do not get relocated automatically; the driver creates a reference ID for the element and
has a particular place it expects to find it in the DOM. If it can not find the element
in the current DOM, any action using that element will result in this exception.

### Common Causes

This can happen when:

* You have refreshed the page, or the DOM of the page has dynamically changed.
* You have navigated to a different page.
* You have switched to another window or into or out of a frame or iframe.

### Common Solutions

**The DOM has changed**

When the page is refreshed or items on the page have moved around, there is still
an element with the desired locator on the page, it is just no longer accessible
by the element object being used, and the element must be relocated before it can be used again.
This is often done in one of two ways:

* Always relocate the element every time you go to use it. The likelihood of
the element going stale in the microseconds between locating and using the element
is small, though possible. The downside is that this is not the most efficient approach,
especially when running on a remote grid.

* Wrap the Web Element with another object that stores the locator, and caches the
located Selenium element. When taking actions with this wrapped object, you can
attempt to use the cached object if previously located, and if it is stale, exception
can be caught, the element relocated with the stored locator, and the method re-tried.
This is more efficient, but it can cause problems if the locator you're using
references a different element (and not the one you want) after the page has changed.

**The Context has changed**

Element objects are stored for a given context, so if you move to a different context —
like a different window or a different frame or iframe — the element reference will
still be valid, but will be temporarily inaccessible. In this scenario, it won't
help to relocate the element, because it doesn't exist in the current context.
To fix this, you need to make sure to switch back to the correct context before using the element. 

**The Page has changed**

This scenario is when you haven't just changed contexts, you have navigated to another page
and have destroyed the context in which the element was located. 
You can't just relocate it from the current context,
and you can't switch back to an active context where it is valid. If this is the reason
for your error, you must both navigate back to the correct location and relocate it.
