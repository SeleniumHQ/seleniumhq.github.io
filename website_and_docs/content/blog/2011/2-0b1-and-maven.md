---
title: "2.0b1 and Maven"
linkTitle: "2.0b1 and Maven"
date: 2011-01-25
tags: ["selenium","maven"]
categories: ["general"]
author: Simon Stewart ([@shs96c](https://twitter.com/shs96c))
description: >
  If you’re using Maven and the 2.0b1 release of Selenium, you may be running into some problems getting maven to pick up your tests.
---


If you’re using Maven and the 2.0b1 release of Selenium, you may be running into some problems getting maven to pick up your tests. The underlying problem is that selenium contains support classes for both JUnit and TestNG. The solution is to add this to your pom.xml

```
<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium</artifactId>
  <version>2.0b1</version>
  <exclusions>
    <exclusion>
      <groupId>org.testng</groupId>
      <artifactId>testng</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```
