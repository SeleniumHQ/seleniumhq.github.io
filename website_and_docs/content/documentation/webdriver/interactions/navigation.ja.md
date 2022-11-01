---
title: "ブラウザー ナビゲーション"
linkTitle: "ナビゲーション"
weight: 1
aliases: [
"/ja/documentation/webdriver/browser/navigation/"
]
---

## ナビゲート

ブラウザーを起動した後に最初に行うことは、Webサイトを開くことです。これは1行で実現できます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Convenient
driver.get("https://selenium.dev");

//Longer way
driver.navigate().to("https://selenium.dev");
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.get("https://selenium.dev")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.Navigate().GoToUrl(@"https://selenium.dev");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Convenient way
driver.get 'https://selenium.dev'

    # Longer Way
driver.navigate.to 'https://selenium.dev'
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
await driver.get('https://selenium.dev');
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Convenient
driver.get("https://selenium.dev")

//Longer way
driver.navigate().to("https://selenium.dev")
  {{< /tab >}}
{{< /tabpane >}}

## 戻る

ブラウザーの戻るボタンを押す。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().back();{{< /tab >}}
  {{< tab header="Python" >}}driver.back(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Back();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.back{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().back();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().back() {{< /tab >}}
{{< /tabpane >}}

## 次へ

ブラウザーの次へボタンを押す。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().forward();{{< /tab >}}
  {{< tab header="Python" >}}driver.forward(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Forward();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.forward{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().forward();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().forward(){{< /tab >}}
{{< /tabpane >}}

## 更新

現在のページを更新する。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}driver.navigate().refresh();{{< /tab >}}
  {{< tab header="Python" >}}driver.refresh(){{< /tab >}}
  {{< tab header="CSharp" >}}driver.Navigate().Refresh();{{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.refresh{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.navigate().refresh();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().refresh(){{< /tab >}}
{{< /tabpane >}}
