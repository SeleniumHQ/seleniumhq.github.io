---
title: "Browser interactions"
linkTitle: "Interactions"
weight: 10
aliases: [
"/documentation/en/webdriver/browser_manipulation/",
"/documentation/webdriver/browser_manipulation/",
"/documentation/webdriver/browser/",
]
---

## Get browser information

### Get title

You can read the current page title from the browser:

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
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


### Get current URL

You can read the current URL from the browser's address bar using:

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
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
