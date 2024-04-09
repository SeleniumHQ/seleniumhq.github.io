---
title: "Fresh browser per test"
linkTitle: "Fresh browser per test"
weight: 11
aliases: [
"/documentation/en/guidelines_and_recommendations/fresh_browser_per_test/",
"/documentation/guidelines/fresh_browser_per_test/"
]
---

Start each test from a clean, known state.
Ideally, spin up a new virtual machine for each test.
If spinning up a new virtual machine is not practical,
at least start a new WebDriver for each test.
Most browser drivers like GeckoDriver and ChromeDriver will start with a clean
known state with a new user profile, by default.

A new browser per test can be achieved by using a test framework's "before each test" hook. This also implies using the "after each test" hook to close the browser

```java
WebDriver driver = new FirefoxDriver();
```

```java
driver.close();
```
