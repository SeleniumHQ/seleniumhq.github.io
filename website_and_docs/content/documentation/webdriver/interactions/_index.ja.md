---
title: "Browser interactions"
linkTitle: "Interactions"
weight: 10
aliases: [
"/documentation/ja/webdriver/browser_manipulation/",
"/ja/documentation/webdriver/browser_manipulation/",
"ja/documentation/webdriver/browser/"
]
---

## ブラウザーの情報

### タイトルの取得

ブラウザーから現在のページタイトルを読むことができます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.getTitle();{{< /tab >}}
  {{< tab header="Python" >}}driver.title{{< /tab >}}
  {{< tab header="CSharp" >}}driver.Title;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.title{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getTitle();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.title{{< /tab >}}
{{< /tabpane >}}


### 現在のURLを取得

ブラウザーのアドレスバーから現在のURLを読むには、次を使用します。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}driver.getCurrentUrl();{{< /tab >}}
{{< tab header="Python" >}}driver.current_url{{< /tab >}}
{{< tab header="CSharp" >}}driver.Url;{{< /tab >}}
{{< tab header="Ruby" >}}driver.current_url{{< /tab >}}
{{< tab header="JavaScript" >}}await driver.getCurrentUrl();{{< /tab >}}
{{< tab header="Kotlin" >}}driver.currentUrl{{< /tab >}}
{{< /tabpane >}}
