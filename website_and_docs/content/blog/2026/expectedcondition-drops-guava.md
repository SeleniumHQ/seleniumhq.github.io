---
title: "Upcoming Breaking Change: ExpectedCondition Drops Guava's Function Interface"
linkTitle: "ExpectedCondition Drops Guava's Function Interface"
date: 2026-09-21
tags: ["selenium", "java"]
categories: ["general"]
author: Diego Molina [@diemol](https://www.diemol.com)
description: >
  Starting with Selenium 4.51, `ExpectedCondition` will stop implementing Guava's `Function`
  interface. Here's who is affected and how to migrate ahead of time.
---

Starting with **Selenium 4.51**, `org.openqa.selenium.support.ui.ExpectedCondition` will stop
implementing Guava's `com.google.common.base.Function` interface. This is a breaking change for
a small set of users, and we want to give everyone time to prepare before it ships.

We are **not** shipping this immediately. This post is the heads-up; the change is targeted for
the Selenium 4.51 release, giving everyone time to check their code beforehand.

## Why This Matters

Selenium's Java client bindings have depended on Guava for years, largely for a handful of
small utilities. That dependency adds weight to every project that pulls in Selenium and can
collide with a project's own Guava version. We've been working to remove Guava from the client
bindings piece by piece — see [#12737](https://github.com/SeleniumHQ/selenium/issues/12737) —
and `ExpectedCondition` is one of the last remaining spots where it leaks into a public API.

## What's Changing

Today, `ExpectedCondition<T>` extends both interfaces:

```java
public interface ExpectedCondition<T extends @Nullable Object>
    extends com.google.common.base.Function<WebDriver, T>,
        java.util.function.Function<WebDriver, T> {}
```

After the change, it will extend only the JDK interface:

```java
public interface ExpectedCondition<T extends @Nullable Object>
    extends java.util.function.Function<WebDriver, T> {}
```

## Who Is Affected

If you call `ExpectedConditions` factory methods and pass the result straight to
`WebDriverWait.until(...)`, or if you implement `ExpectedCondition` with a lambda, **you are not
affected** — nothing about that usage changes.

You *are* affected if your code explicitly types a variable, field, or method parameter as
Guava's `Function`, for example:

```java
com.google.common.base.Function<WebDriver, WebElement> condition =
    ExpectedConditions.elementToBeClickable(By.id("submit"));
```

That assignment relies on `ExpectedCondition` implementing Guava's `Function`, which will no
longer compile once this change ships.

## How to Check Now

Search your codebase for `com.google.common.base.Function` anywhere it's used together with
Selenium's `ExpectedCondition`/`ExpectedConditions`:

```bash
grep -rn "com.google.common.base.Function" --include="*.java" .
```

## How to Migrate

Replace the Guava type with the JDK equivalent — the abstract method signature (`apply`) is
identical, so it's a drop-in substitution:

```java
// Before
com.google.common.base.Function<WebDriver, WebElement> condition = ...;

// After
java.util.function.Function<WebDriver, WebElement> condition = ...;
```

In most cases, you can also drop the explicit type entirely and let it be inferred, or reference
`ExpectedCondition<WebElement>` directly.

## Related Issues

- [#12737](https://github.com/SeleniumHQ/selenium/issues/12737) — remove guava from client
  bindings
- [#13739](https://github.com/SeleniumHQ/selenium/pull/13739) — implementation for this specific
  change

If you run into anything unexpected while checking your code against this, please comment on
[#13739](https://github.com/SeleniumHQ/selenium/pull/13739) before it merges.
