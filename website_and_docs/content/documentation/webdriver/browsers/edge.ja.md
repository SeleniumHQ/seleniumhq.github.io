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
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L25-L26" >}}
{{< /tab >}}
{{% tab header="Python" %}}
Note that Python must specify service class to use [Driver Manager]({{< ref "../getting_started/install_drivers.md" >}})
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L8-L10" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L16-L17" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L8-L9" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openEdgeTest.spec.js#L11-L16">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Internet Explorer Compatibility モード

Microsoft Edge は、Internet Explorer ドライバークラスを Microsoft Edgeと組み合わせて使用する 
"Internet Explorer 互換モード"で動かすことができます。 
詳細については、[Internet Explorerページ]({{< ref "internet_explorer.md" >}})を参照してください。
