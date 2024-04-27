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
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L14-L18" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_navigation.py#L6" >}}
{{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/NavigationTest.cs#L17-L20" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Convenient way
driver.get 'https://selenium.dev'

    # Longer Way
driver.navigate.to 'https://selenium.dev'
  {{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L16-L20" >}}
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
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L22-L23" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_navigation.py#L11" >}}
{{< /tab >}}
   {{< tab header="CSharp" >}}
 {{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/NavigationTest.cs#L24-L25" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.back{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L24-L25" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().back() {{< /tab >}}
{{< /tabpane >}}

## 次へ

ブラウザーの次へボタンを押す。


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L27-L28" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_navigation.py#L15" >}}
{{< /tab >}}
  {{< tab header="CSharp" >}}
 {{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/NavigationTest.cs#L29-L30" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.forward{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L29-L30" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().forward(){{< /tab >}}
{{< /tabpane >}}

## 更新

現在のページを更新する。


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L32-L33" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_navigation.py#L19" >}}
{{< /tab >}}
  {{< tab header="CSharp" >}}
 {{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/NavigationTest.cs#L34-L35" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}driver.navigate.refresh{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L34-L35" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().refresh(){{< /tab >}}
{{< /tabpane >}}
