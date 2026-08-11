---
title: "Fresh browser per test"
linkTitle: "Fresh browser per test"
weight: 11
aliases: [
"/documentation/en/guidelines_and_recommendations/fresh_browser_per_test/",
"/documentation/guidelines/fresh_browser_per_test/",
"/documentation/ja/guidelines_and_recommendations/fresh_browser_per_test/",
"/documentation/pt-br/guidelines_and_recommendations/fresh_browser_per_test/",
"/documentation/zh-cn/guidelines_and_recommendations/fresh_browser_per_test/",
"/ja/documentation/guidelines/fresh_browser_per_test/",
"/ja/documentation/test_practices/encouraged/fresh_browser_per_test/",
"/pt-br/documentation/guidelines/fresh_browser_per_test/",
"/pt-br/documentation/test_practices/encouraged/fresh_browser_per_test/",
"/zh-cn/documentation/guidelines/fresh_browser_per_test/",
"/zh-cn/documentation/test_practices/encouraged/fresh_browser_per_test/"
]
---

Start each test from a clean, known state.
Ideally, spin up a new virtual machine for each test.
If spinning up a new virtual machine is not practical,
at least start a new WebDriver for each test.
Most browser drivers like GeckoDriver and ChromeDriver will start with a clean
known state with a new user profile, by default.
```java
WebDriver driver = new FirefoxDriver();
```
