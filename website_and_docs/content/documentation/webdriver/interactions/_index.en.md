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
  {{< tab header="Java" >}}driver.getTitle();{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_interactions.py#L7" >}}
{{< /tab >}}
  {{< tab header="CSharp" >}}driver.Title;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.title{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/interactionsIndex.spec.js#L20" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.title{{< /tab >}}
{{< /tabpane >}}


### Get current URL

You can read the current URL from the browser's address bar using:

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
{{< tab header="Java" >}}driver.getCurrentUrl();{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_interactions.py#L10" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}driver.Url;{{< /tab >}}
{{< tab header="Ruby" >}}driver.current_url{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/interactionsIndex.spec.js#L24" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}driver.currentUrl{{< /tab >}}
{{< /tabpane >}}
