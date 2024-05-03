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

## ElementClickInterceptedException

This exception occurs when an attempt to click on an element is intercepted by another element on the page. Essentially, the element you're trying to interact with is either hidden or obscured by another element, such as a popup, overlay, or some other UI element.

When Selenium tries to click on an element, it checks whether the element is clickable, visible, and not obscured by other elements. If it finds that the element is not clickable due to being overlapped by another element, it raises the ElementClickInterceptedException.

### Common Causes

**Overlapping UI Elements:** One of the common causes for this exception to occur is when another element in a web page partially or fully covers the element we are trying to interact with. These overlapping elements can be buttons, images, divs, etc, with a higher “z-index” value.

**Element Visibility:** If the element is not visible when we try to interact, we can encounter this issue. Elements with display: none; or visibility: hidden; styles are not clickable until they become visible.

**Animations and Transitions:** Most web pages include animations or transitions that modify or move the elements. We may encounter this exception if we try to click an element in the middle of an animation or transition.

**Dynamic Content Loading:** The web pages that dynamically load the content using AJAX or JavaScript may not need an element when we try to access it.

**Popup Windows or Modals:** When clicking an element triggers the opening of a popup window or modal dialog.

**Slow Page Loading:** If the web page is slow to load, and you attempt to click an element before it’s fully loaded and ready for interaction.


### Common Solutions

**Use Explicit Waits:** Use explicit waits with conditions like ExpectedConditions.elementToBeClickable to ensure the element is ready for interaction before clicking it. 

**Scroll to Element:** There could be cases when the desired element is not in the browser’s viewport, and Selenium is unable to find it. To fix this, scroll to the element using JavaScript or Selenium’s built-in scrolling functionality and then try to click on it.

**Check for Overlapping Elements:** You can inspect the web page’s HTML DOM structure and check if other elements overlap the intended element. If you see an overlap, make the necessary changes to the test script.

**Increase Wait Time:** Sometimes, the page may take longer to load or render elements, and when the Selenium script tries to access it, it fails. Use the explicit wait to give the page more time so that all the elements are fully loaded and present for interaction.

**Use Retry Mechanism:** Implement a retry mechanism to click the element multiple times with short pauses between attempts. Sometimes, the issue may be intermittent, and retrying can help.