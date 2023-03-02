---
title: "InvalidSelectorException is changing"
linkTitle: "InvalidSelectorException is changing"
date: 2023-03-02
tags: ["webdriver", "java", "exceptions"]
categories: ["general"]
author: Mohab Mohie ([MohabMohie](https://github.com/MohabMohie/MohabMohie))
description: >
  Headsup! InvalidSelectorException is changing.
---

Kindly be advised that previously in case we used an invalid locator to identify an element the behavior would be inconsistent in our Java binding.
For example, writing the below code block
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
the expected result <b>Before this change</b> would be that the driver would wait until the timeout expires and then throw an InvalidSelectorException.

This doesn't make much sense because a broken / invalid selector would never fix itself, and hence should throw immediately as discussed and agreed during the [TLC - August 17, 2022](https://www.selenium.dev/meetings/2022/tlc-08-17/#proposals)

After this change goes live in our next release after [this PR](https://github.com/SeleniumHQ/selenium/pull/11727) is merged, an InvalidSelectorException would immediately be thrown.

Please note that this may have an impact on backwards compatibility if you are not expecting this exception to be thrown while handling invalid locators.
