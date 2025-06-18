---
title: "Update imports to use BiDi Java"
linkTitle: "Update imports to use BiDi Java"
date: 2024-03-14
tags: ["selenium"]
categories: ["general"]
author: Puja Jagani [@pujagani](https://www.linkedin.com/in/pujajagani/)
description: >
   This blog post discusses the rationale behind the breaking change in Java BiDi implementation and the changes users will have to make.
---

## What part of the code base is impacted?
Selenium WebDriver BiDi APIs in Java bindings are impacted.

## What is impacted by the breaking change?
The WebDriver BiDi APIs stay as they are, so you can continue to use them. However, the import statements need to be updated. 

## What is the breaking change?
The import statements need to be updated when using the BiDi APIs.

### Before Selenium 4.19:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.bidi.LogInspector;
import org.openqa.selenium.bidi.BrowsingContextInspector;
import org.openqa.selenium.bidi.Input;
import org.openqa.selenium.bidi.Script;
import org.openqa.selenium.bidi.Network;
{{< /tab >}}
{{< /tabpane >}}

### After Selenium 4.19 and above:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.bidi.module.LogInspector;
import org.openqa.selenium.bidi.module.BrowsingContextInspector;
import org.openqa.selenium.bidi.module.Input;
import org.openqa.selenium.bidi.module.Script;
import org.openqa.selenium.bidi.module.Network;
{{< /tab >}}
{{< /tabpane >}}

## Why the breaking change?
Selenium is actively working to implement the [W3C BiDi](https://w3c.github.io/webdriver-bidi). The long-term goal of W3C BiDi is to port all W3C WebDriver Classic APIs to use the WebDriver BiDi APIs under the hood.
When [browsingContext.locateNodes](https://w3c.github.io/webdriver-bidi/#command-browsingContext-locateNodes) command, which is the BiDi counterpart of [findElements](https://www.w3.org/TR/webdriver2/#find-elements) command, was introduced, the major goal was to ensure that the 'locateNodes' command returns a [WebElement](https://github.com/SeleniumHQ/selenium/blob/trunk/java/src/org/openqa/selenium/WebElement.java). This would be make the porting smoother in the future and allows users to continue calling APIs of the WebElement.

During the implementation, a circular dependency was encountered in the underlying build tool [Bazel](https://bazel.build/about/intro).
The solution to this was to follow the [best practices of Bazel](https://bazel.build/configure/best-practices#packages).

So, the W3C BiDi related classes of a module were grouped into Bazel [package](https://bazel.build/concepts/build-ref#packages). The classes that themselves call commands or events were all grouped under a package named 'module'.
Thus, following the recommended practice and avoiding Bazel's circular dependency proved to be a win-win solution.

## Summary
The W3C BiDi protocol is in under development, and parallelly browsers and clients are working to add the complementary APIs. While Selenium works on implementing it, the protocol is constantly changing, with new modules or APIs being added or existing ones being updated. While the team strives to avoid breaking changes and deprecate APIs for at least 2 versions before removal, it can be challenging to adhere to this for some changes, such as the one described in this blog post.