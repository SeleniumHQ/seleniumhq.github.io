---
title: "Architectural Overview and Design Principles"
linkTitle: "Architecture"
weight: 1
description: >
    The original design principles behind the WebDriver implementations.
---

This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Architectural-Overview)

## Focus on the User

The WebDriver APIs are focused on driving the browser from the user's point of view. 
For this reason, you won't find methods such as "fireEvent", and this is why "getText" 
attempts to return the text as a user would see it. This is also why it's important 
that installation of webdriver be as simple as possible.

## Use a "Best Fit" Language

The key idea behind the various implementations of WebDriver is that each browser has 
a language that is most natural to use when attempting to drive it. All of the drivers 
are built with the idea that as much as possible should be done in this "best fit" 
language, while the implementation that the user sees is a thin wrapper around this. 
This may be represented as:

| **Browser** | **"Best Fit" Language** |
|:------------|:------------------------|
| Firefox     | Javascript in an XPCOM component |
| Internet Explorer | C++ mainly using the IE Automation APIs, but occasionally using other features of Windows |

In addition, some of the languages that WebDriver is offered in (notably Java) have 
something that supports simulating a browser. These are generally modeled using composition.

## A Layered Design

In order to be useful, WebDriver must not make the user learn all the different 
implementation languages --- they don't care and they shouldn't have to. In order to be 
easy to write and maintain, as much logic as possible needs to be done in the "best fit" 
language of the browser. This naturally leads to a design where the API presented to the 
user is a thin wrapper around the core of each driver.

One obvious benefit to this design is that writing a language binding for WebDriver 
becomes a matter of bridging to the "best fit" language. Another benefit is that it 
becomes very easy to work on a single driver, given you understand the "best fit" 
language. For example, it's possible to work on the FirefoxDriver completely 
independently of the InternetExplorerDriver. Better, once a feature in a driver is 
working in one binding language (for example, Java), it should be easy to add that 
support to other binding languages.

![Language bindings wrapping a driver written in its "best fit" language](/images/documentation/legacy/language_bindings.png)

More detailed information about each driver, how to write language bindings and its 
implementation can be found on their pages:

* [FirefoxDriver Internals]({{< ref "../selenium_2/firefox_driver#firefoxdriver-internals" >}})
* [HtmlUnitDriver]({{< ref "../selenium_2/htmlunit_driver" >}})
* [InternetExplorerDriver Internals]({{< ref "/documentation/ie_driver_server/internals" >}})

## Reducing the Cost of Change

As even a cursory read of the architectural documents suggest, there are multiple, 
largely independent implementations of webdriver, as well as the original Selenium 1.x 
implementation. The goal for reducing the cost of change on the project is to share as 
much code as possible. Fortunately, a lot of the tasks we want to perform are "read only", 
querying the current state of the DOM. It makes sense to write these shared functions 
in Javascript.

In order to do this, we are working on [Automation Atoms]({{< ref "atoms" >}}) (atoms). 
These take the form of a Javascript library that makes use of Google's Closure. 
There are two use cases for the atoms:

1. A "monolithic driver", such as Selenium 1.x or the Firefox driver, where the bulk of 
   the driver is written in JS.
1. Augmenting an existing driver written in another language (eg: the 
   InternetExplorerDriver), where we want to use tiny fragments of highly compressed JS 
   in order to reduce execution and parsing time.

Closure comes with the Closure compiler, which allows us to take a Javascript library and 
compile it to minimized and optimized Javascript, as offering the ability to generate an 
unoptimized version. We shall be making use of this capability to support both use cases.
