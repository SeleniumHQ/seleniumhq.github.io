---
title: "Atoms have come to Selenium IDE"
linkTitle: "Atoms have come to Selenium IDE"
date: 2010-12-09
tags: ["ide","webdriver"]
categories: ["general"]
author: Adam Goucher ([@adamgoucher](https://twitter.com/adamgoucher))
description: >
  As mentioned in Simon’s Going Atomic: Why? and Going Atomic: How, part of the merging of Selenium and WebDriver is to share common code between the two.
---

As mentioned in Simon’s [Going Atomic: Why?](/blog/2010/going-atomic-why/) and [Going Atomic: How](/blog/2010/going-atomic-how/), part of the merging of Selenium and WebDriver is to share common code between the two. And as of the 1.0.10 release of Selenium IDE, this merged code, ‘atoms’ is now included.  
  
Some early users of this release are noticing that this the atom that drives assertAttribute and verifyAttribute is causing their scripts to break. This post explains the ‘why’ and suggests how to fix them as well.  
  
The HTML spec has the notion of a _boolean attribute_ which is one that does something just by being there rather than by its value; _checked_ and _selected_ are two such examples of this. Optionally, you could give these boolean attributes a value that is the same as their name (checked=”checked”) and your page would still validate, but really, the browsers only care that the attribute exist.  
  
Prior to 1.0.10, it was not uncommon for people to do something like

```
  assertAttribute | checkbox@checked | checked
```

which fetches the value of the checked attribute on the checkbox with the id or name of checkbox and fails the script is the value is not checked.  
  
In 1.0.10 though, that will fail _even if it is checked_. This is because checked is a boolean attribute and will now return either true or throw an exception saying the attribute doesn’t exist. Redoing the above step check that the boolean attribute is set results in:

```
  assertAttribute | checkbox@checked | true
```

Thankfully, most of the real-world interaction with boolean attributes is focused on checked, disabled, hidden and selected and in these cases using assertAttribute is not really the right thing to do. Instead, the Selenium API provides wrappers for you to use.<br />

<table>
  <tr>
    <td>checked</td>
    <td>assertChecked(locator), assertNotChecked(locator), verifyChecked(locator), verifyNotChecked(locator)</td>
  </tr>
  <tr>
    <td>disabled</td>
    <td>assertEditable(locator), assertNotEditable(locator), verifyEditable(locator), verifyNotEditable(locator)</td>
  </tr>
  <tr>
    <td>hidden</td>
    <td>assertVisible(locator), assertNotVisible(locator), verifyVisible(locator), verifyNotVisible(locator)</td>
  </tr>
  <tr>
    <td>selected</td>
    <td>assertSelected*(locator, pattern), assertNotSelected*(locator, pattern), verifySelected*(locator, pattern), verifyNotSelected*(locator, pattern)</td>
  </tr>
</table>

Checking for the absence of a boolean attribute that doesn’t have a nice wrapper is a bit of a pain though. In this example I am checking that the boolean attribute _multiple_ is not on the select element with the name elephants.

```
  assertEval | this.browserbot.findElement("css=select[name=elephants]").getAttribute("multiple"); | null
```

If you find yourself doing this very often, I suggest you wrap it up in a user-extension inside a [plugin](http://seleniumhq.org/projects/ide/plugins.html) as something like

```
  assertBooleanAttribute | css=select[name=elephants]@multiple | false
  assertBooleanAttribute | css=select[name=elephants]@multiple | true
```

Hopefully this addresses some of the eventual ‘why the heck did my script break!?!?!’ problems that will no double crop up once Selenium IDE 1.0.10 gets wider distribution.

