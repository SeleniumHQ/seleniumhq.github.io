---
title: "Announcing Selenium 2.19: the Prancing Unicorn release"
linkTitle: "Announcing Selenium 2.19: the Prancing Unicorn release"
date: 2012-02-08
tags: ["selenium"]
categories: ["releases"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  You might be pleased to hear that Selenium 2.19 has been released (download it from here!).
---

You might be pleased to hear that Selenium 2.19 has been released ([download it from here!](http://seleniumhq.org/download/)). There’s one big user facing changing that we’d like to tell you about: the webdriver-backed selenium can now be used in supported languages.

By providing this capability, it’s possible to migrate from RC to the WebDriver APIs without rewriting all your tests in one fell swoop (which must be a Good Thing, right?) An example of how to use it in Python would be:

```python
driver = RemoteWebDriver(desired_capabilities = DesiredCapabilities.FIREFOX)
selenium = DefaultSelenium('localhost', 4444', '*webdriver', 'http://www.google.com')
selenium.start(driver = driver)
```

Provided you keep a reference to the original webdriver and selenium objects you created you can use the two APIs interchangeably.  You’ll see that the magic is the “\*webdriver” browser name passed to the selenium instance, and that we pass the webdriver instance when calling start().

We hope you like it!

PS: I have no idea why this is the Prancing Unicorn release, but it’s been a while since we named one 🙂