---
title: sendKeys
linkTitle: sendKeys
weight: 10
description: WebDriver API for sending keyboard input to elements
---

## sendKeys

The `sendKeys` method is used to simulate typing into an element.
It can be used to enter text into input fields or to send special
keyboard keys such as Enter or Tab.

---

## Description

`sendKeys()` sends one or more sequences of keystrokes to the target
element. The element must be visible and enabled for the operation
to succeed.

Special keys can be sent using the `Keys` class provided by Selenium.

---

## Examples

### Java

```java
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

WebDriver driver = new ChromeDriver();
WebElement input = driver.findElement(By.name("q"));
input.sendKeys("Selenium WebDriver");
input.sendKeys(Keys.ENTER);
