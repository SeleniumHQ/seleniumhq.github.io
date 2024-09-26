---
title: "浏览器交互"
linkTitle: "交互"
weight: 10
aliases: [
"/documentation/zh-cn/webdriver/browser_manipulation/",
"/zh-cn/documentation/webdriver/browser_manipulation/",
"/zh-cn/documentation/webdriver/browser/"
]
---

## 获取浏览器信息

### 获取标题

从浏览器中读取当前页面的标题:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/InteractionsTest.java#L15" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_interactions.py#L7" >}}
{{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/InteractionsTest.cs#L37" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/interactions/browser_spec.rb#L8" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/interactionsIndex.spec.js#L20" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}driver.title{{< /tab >}}
{{< /tabpane >}}

### 获取当前 URL

您可以从浏览器的地址栏读取当前的 URL，使用:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/InteractionsTest.java#L26" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_interactions.py#L10" >}}
{{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/InteractionsTest.cs#L41" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/interactions/browser_spec.rb#L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/interactionsIndex.spec.js#L24" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}driver.currentUrl{{< /tab >}}
{{< /tabpane >}}
