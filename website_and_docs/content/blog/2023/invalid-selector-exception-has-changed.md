---
title: "InvalidSelectorException has changed"
linkTitle: "InvalidSelectorException has changed"
date: 2023-04-21
tags: ["webdriver", "java", "dotnet", "exceptions"]
categories: ["general"]
author: Mohab Mohie ([MohabMohie](https://github.com/MohabMohie/MohabMohie))
description: >
  ⚠️ `InvalidSelectorException` now extends from `WebDriverException`
---

Before Selenium 4.8.2 in Java and C#, when an invalid locator was used to identify an element, the resulting behavior would be 
inconsistent in our bindings.

For example, let's check the following code:

```java
ArrayList<Class<? extends Exception>> expectedExceptions = new ArrayList<>();
        expectedExceptions.add(org.openqa.selenium.NoSuchElementException.class);
        expectedExceptions.add(org.openqa.selenium.StaleElementReferenceException.class);
        expectedExceptions.add(org.openqa.selenium.ElementNotInteractableException.class);
        expectedExceptions.add(org.openqa.selenium.InvalidElementStateException.class);
        
return new FluentWait<>(driver)
      .withTimeout(Duration.ofMillis(ELEMENT_IDENTIFICATION_TIMEOUT))
      .pollingEvery(Duration.ofMillis(ELEMENT_IDENTIFICATION_POLLING_DELAY))
      .ignoreAll(expectedExceptions)
      .until(nestedDriver -> {
         nestedDriver.findElement(By.xpath("invalid-xpath")).click;
      });
```

The expected result *before this change* would be that the driver waits until the timeout expires and then throw an `InvalidSelectorException`.

This doesn't make much sense because a broken/invalid selector would never fix itself, and hence should throw immediately. 

This was discussed and agreed during the [TLC meeting on August 17, 2022](https://www.selenium.dev/meetings/2022/tlc-08-17/#proposals),
and implemented through the pull request [11727](https://github.com/SeleniumHQ/selenium/pull/11727) and the following 
[commit](https://github.com/SeleniumHQ/selenium/commit/f28144eb72ae1df18f267a5250db6b9b41dc1fdc).

With the changes mentioned above, an invalid selector will throw an `InvalidSelectorException` immediately.

Please note that this may have an impact on backwards compatibility if you are not expecting this exception to be thrown while 
handling invalid locators.

Stay tuned for updates by following [SeleniumHQ](https://twitter.com/seleniumhq)!

Happy testing!

