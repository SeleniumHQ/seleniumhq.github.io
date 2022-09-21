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
  {{< tab header="Java" >}}driver.getTitle();{{< /tab >}}
  {{< tab header="Python" >}}driver.title{{< /tab >}}
  {{< tab header="CSharp" >}}driver.Title;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.title{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getTitle();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.title{{< /tab >}}
{{< /tabpane >}}


### Get current URL

You can read the current URL from the browser's address bar using:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}driver.getCurrentUrl();{{< /tab >}}
{{< tab header="Python" >}}driver.current_url{{< /tab >}}
{{< tab header="CSharp" >}}driver.Url;{{< /tab >}}
{{< tab header="Ruby" >}}driver.current_url{{< /tab >}}
{{< tab header="JavaScript" >}}await driver.getCurrentUrl();{{< /tab >}}
{{< tab header="Kotlin" >}}driver.currentUrl{{< /tab >}}
{{< /tabpane >}}
