---
title: "Edge固有の機能"
linkTitle: "Edge"
weight: 5
description: >-
    これらは、Microsoft Edgeブラウザに固有のCapabilityです。
---

Microsoft EdgeはChromiumで実装されており、サポートされている最も古いバージョンはv79です。
Chromeと同様に、edgedriverのメジャー バージョン番号は、Edgeブラウザのメジャーバージョンと一致する必要があります。

[Chromeページ]({{< ref "chrome.md" >}}) にあるすべての機能とオプションは、Edgeでも機能します。

## オプション

基本的な定義済みオプションを使用して Edgeセッションを開始すると、次のようになります。

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L18-L19" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_edge.py#L6-L7" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/EdgeTest.cs#L12-L13" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L9-L10" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openEdgeTest.spec.js#L11-L16">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 引数

`args` パラメータは、ブラウザの起動時に使用される[コマンドラインスイッチ](https://peter.sh/experiments/chromium-command-line-switches/)のリストです。  
一般的に使用される引数には、`--start-maximized` および `--headless=new` が含まれます。

オプションに引数を追加します。

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L24-L26" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_edge.py#L12-L13" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/EdgeTest.cs#L24-L26" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L14-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Internet Explorer Compatibility モード

Microsoft Edge は、Internet Explorer ドライバークラスを Microsoft Edgeと組み合わせて使用する 
"Internet Explorer 互換モード"で動かすことができます。 
詳細については、[Internet Explorerページ]({{< ref "internet_explorer.md" >}})を参照してください。
