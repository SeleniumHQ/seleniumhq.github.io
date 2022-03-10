---
title: "Tips on working with locators"
linkTitle: "Locators"
weight: 8
description: >
    When to use which locators and how best to manage them in your code.
---

Take a look at examples of the [supported locator strategies]({{< ref "/documentation/webdriver/elements/locators.md" >}}).

In general, if HTML IDs are available, unique, and consistently
predictable, they are the preferred method for locating an element on
a page. They tend to work very quickly, and forego much processing
that comes with complicated DOM traversals.

If unique IDs are unavailable, a well-written CSS selector is the
preferred method of locating an element. XPath works as well as CSS
selectors, but the syntax is complicated and frequently difficult to
debug. Though XPath selectors are very flexible, they are typically
not performance tested by browser vendors and tend to be quite slow.

Selection strategies based on _linkText_ and _partialLinkText_ have
drawbacks in that they only work on link elements. Additionally, they
call down to [querySelectorAll](https://www.w3.org/TR/webdriver/#link-text) selectors internally in WebDriver.

Tag name can be a dangerous way to locate elements. There are
frequently multiple elements of the same tag present on the page.
This is mostly useful when calling the _findElements(By)_ method which
returns a collection of elements.

The recommendation is to keep your locators as compact and
readable as possible. Asking WebDriver to traverse the DOM structure
is an expensive operation, and the more you can narrow the scope of
your search, the better.
