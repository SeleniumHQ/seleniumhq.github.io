---
title: "Browser interactions"
linkTitle: "Interactions"
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
{{< tab header="Java" >}}driver.getTitle();{{< /tab >}}
{{< tab header="Python" >}}driver.title{{< /tab >}}
{{< tab header="CSharp" >}}driver.Title;{{< /tab >}}
{{< tab header="Ruby" >}}driver.title{{< /tab >}}
{{< tab header="JavaScript" >}}await driver.getTitle();{{< /tab >}}
{{< tab header="Kotlin" >}}driver.title{{< /tab >}}
{{< /tabpane >}}

### 获取当前 URL

您可以从浏览器的地址栏读取当前的 URL，使用:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}driver.getCurrentUrl();{{< /tab >}}
{{< tab header="Python" >}}driver.current_url{{< /tab >}}
{{< tab header="CSharp" >}}driver.Url;{{< /tab >}}
{{< tab header="Ruby" >}}driver.current_url{{< /tab >}}
{{< tab header="JavaScript" >}}await driver.getCurrentUrl();{{< /tab >}}
{{< tab header="Kotlin" >}}driver.currentUrl{{< /tab >}}
{{< /tabpane >}}
