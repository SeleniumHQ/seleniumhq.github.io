---
title: "Browser Automation Atoms"
linkTitle: "Atoms"
weight: 3
description: >
    Shared JavaScript building blocks used across Selenium driver implementations.
---

This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Automation-Atoms)

Browser Automation Atoms are building blocks intended to be used by Selenium 
implementations. By using the same pieces throughout the codebase, rather than 
reimplementing required functionality in multiple places, the project can reduce the 
number of bugs found, and can simplify the process of adding new functionality and drivers.

## Using Atoms

There are two ways to make use of the automation atoms.

1. Using Google Closure
1. As compiled fragments

The first of these approaches is useful when constructing a monolithic application, 
such as Selenium Core. The second approach is used in those drivers that are implemented 
natively and wish to use the atoms to interrogate the DOM (such as the InternetExplorerDriver).

## Testing Atoms

If you're developing the Atoms, then the easiest way to test them is:

```
# Start the server
./go debug-server &
```

Now point a browser at http://localhost:2310/javascript/atoms/test and select the test 
you'd like to run. As you edit the atoms, so long as you don't change the "goog.require" 
or "goog.provides" statements then you can just refresh the page to see the tests run 
with your changes. If you do change either of those settings, kill the server and then run

```
./go calcdeps
./go debug-server &
```

## Atoms Summary

Each of the atoms listed here is exposed via the list **method name**, which delegates 
directly through to the **implementation method**. For information about the order in 
which arguments are to be passed, and for additional documentation, view the jsdoc of 
the implementation method.

| **Method Name** | **Description** | **Implementation Method** |
|:----------------|:----------------|:--------------------------|
| clear           |                 |                           |
| click           |                 |                           |
| executeScript   |                 |                           |
| findElement     | Find the first matching element in the DOM | bot.locators.findElement  |
| findElements    | Find all matching elements in the DOM | bot.locators.findElements |
| fire            | Fire a specific, synthesized event | bot.events.fire           |
| getAttribute    | Get the value of an attribute or property of an element | bot.dom.getAttribute      |
| getLocation     | Get the absolute location of an element in the DOM | bot.dom.getLocation       |
| getSize         | Get the size of an element | bot.dom.getSize           |
| isDisplayed     |                 |                           |
| isEnabled       |                 |                           |
| isSelected      | Would a user consider this element selected? | bot.dom.isSelected        |
| setSelected     |                 |                           |
| submit          |                 |                           |
| toggle          |                 |                           |
| type            |                 |                           |

The canonical list of atoms that are implemented for the webdriver APIs are located in 
this [build file](https://github.com/SeleniumHQ/selenium/blob/trunk/javascript/webdriver/atoms/BUILD.bazel). 
The names of the fragment rules relate to the names of the methods.
