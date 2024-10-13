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

## InvalidSelectorException

CSS and XPath Selectors are sometimes difficult to get correct.

### Likely Cause

The CSS or XPath selector you are trying to use has invalid characters or an invalid query.

### Possible Solutions

Run your selector through a validator service:
* [CSS Validator](http://csslint.net/)
* [xPath Validator](http://www.freeformatter.com/xpath-tester.html)

Or use a browser extension to get a known good value:
* [SelectorsHub](https://selectorshub.com/selectorshub/)

## NoSuchElementException

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

## StaleElementReferenceException 

An element goes stale when it was previously located, but can not be currently accessed.
Elements do not get relocated automatically; the driver creates a reference ID for the element and
has a particular place it expects to find it in the DOM. If it can not find the element
in the current DOM, any action using that element will result in this exception.

### Likely Cause

This can happen when:

* You have refreshed the page, or the DOM of the page has dynamically changed.
* You have navigated to a different page.
* You have switched to another window or into or out of a frame or iframe.

### Possible Solutions

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

This exception occurs when Selenium tries to click an element, but the click would instead be received 
by a different element. Before Selenium will click an element, it checks if the element is visible, 
unobscured by any other elements, and enabled - if the element is obscured, it will raise this exception.

### Likely Cause

**UI Elements Overlapping** 

Elements on the UI are typically placed next to each other, but occasionally elements may overlap. For example, 
a navbar always staying at the top of your window as you scroll a page. If that navbar happens to be covering 
an element we are trying to click, Selenium might believe it to be visible and enabled, but when you try to click 
it will throw this exception. Pop-ups and Modals are also common offenders here.

**Animations** 

Elements with animations have the potential to cause this exception as well - it is recommended to wait for 
animations to cease before attempting to click an element.

### Possible Solutions

**Use Explicit Waits** 

[Explicit Waits]({{< ref "/documentation/webdriver/waits" >}}) will likely be your best friend in these instances. 
A great way is to use `ExpectedCondition.ToBeClickable()` with `WebDriverWait` to wait until the right moment.

**Scroll the Element into View** 

In instances where the element is out of view, but Selenium still registers the element as visible 
(e.g. navbars overlapping a section at the top of your screen), you can use the `WebDriver.executeScript()` 
method to execute a javascript function to scroll (e.g. `WebDriver.executeScript('window.scrollBy(0,-250)')`) 
or you can utilize the Actions class with `Actions.moveToElement(element)`.

## InvalidSessionIdException

Sometimes the session you're trying to access is different than what's currently available

### Likely Cause

This usually occurs when the session has been deleted (e.g. `driver.quit()`) or if the session has changed, 
like when the last tab/browser has closed (e.g. `driver.close()`)

### Possible Solutions

Check your script for instances of `driver.close()` and `driver.quit()`, and any other possible causes 
of closed tabs/browsers. It could be that you are locating an element before you should/can.

## ElementNotVisibleException

This exception is thrown when the element you are trying to interact with _is_ present in the DOM, but is not visible. 

### Likely Cause

This can occur in several situations:
* Another element is blocking your intended element
* The element is disabled/invisible to the user

### Possible Solutions

This issue cannot always be resolved on the user's end, however when it can it is usually solved by the following: 
using an explicit wait, or interacting with the page in such a way to make the element visible 
(scrolling, clicking a button, etc.)