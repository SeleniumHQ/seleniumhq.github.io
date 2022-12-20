---
title: "Chrome固有の機能"
linkTitle: "Chrome"
weight: 4
description: >-
    これらは、Google Chrome ブラウザに固有のCapabilityです。
aliases: [
"/ja/documentation/capabilities/chromium"
]
---

デフォルトでは、Selenium 4 は Chrome v75 以降と互換性があります。 
Chromeブラウザのバージョンと chromedriverのバージョンは、メジャーバージョンと一致する必要があることに注意してください。

## Options

全てのブラウザに共通のCapabilityについては、[オプション ページ]({{< ref "../drivers/options.md" >}})で説明しています。

Chrome に固有のCapabilityは、Google の[Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)ページにあります。

基本的な定義済みオプションを使用してChromeセッションを開始すると、次のようになります。

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L18-L19" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L6-L7" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L12-L13" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L7-L8" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openChromeTest.spec.js#L10-L14">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

さまざまなCapabilityを備えた一般的な使用例をいくつか示します。

### 引数

`args` パラメータは、ブラウザの起動時に使用される[コマンドラインスイッチ](https://peter.sh/experiments/chromium-command-line-switches/)のリストです。  
一般的に使用される引数には、`--start-maximized` および `user-data-dir=/tmp/temp_profile` が含まれます。

オプションに引数を追加します。

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L8-L11">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 指定したロケーションでブラウザを起動する

`binary`パラメーターは、使用するブラウザの別のロケーションのパスを取ります。 
このパラメーターを使用すると、chromedriver を使用して、さまざまな Chromium ベースのブラウザを駆動できます。

オプションにブラウザのロケーションを追加します。

{{< alert-code />}}

### 拡張機能を追加する

`extensions` パラメーターはcrxファイルを受け入れます

オプションに拡張機能を追加します。

{{< alert-code />}}

### ブラウザを開いたままにする

`detach` パラメータをtrueに設定すると、ドライバープロセスが終了した後もブラウザを開いたままにできます。

オプションにバイナリを追加します。

{{< alert-code />}}

### 引数を除外する

Chrome はさまざまな引数を追加します。
これらの引数を追加したくない場合は、それらを `excludeSwitches` に渡します。 
一般的な例は、ポップアップブロッカーをオンに設定することです。

オプションに除外された引数を設定します。

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L18-L21">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## キャスティング

タブの共有など、Chrome Castデバイスを操作できます。

{{< alert-code />}}

## ネットワークの状態

さまざまなネットワークの状態をシミュレートできます。

{{< alert-code />}}

## ログ

{{< alert-code />}}

## パーミッション

{{< alert-code />}}

## デベロッパー ツール

Chromeデベロッパーツールの使用に関する詳細については、[Chromeデベロッパー ツール]({{< ref "../bidirectional/chrome_devtools.md" >}})セクションを参照してください。
