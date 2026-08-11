---
title: "Safari特有の機能"
linkTitle: "Safari"
weight: 10
description: >-
    これらは、Apple Safariブラウザ固有の機能です。
aliases: [
"/documentation/capabilities/safari"
]
---

Chromium および Firefox のドライバーとは異なり、safaridriver はオペレーティングシステムとともに
インストールされています。
Safari で自動化を有効にするには、ターミナルで次のコマンドを実行します。

```shell
safaridriver --enable
```

## オプション

すべてのブラウザに共通する機能は、[オプションページ]({{< ref "../drivers/options.md" >}})で説明しています。

Safari 固有の機能については、Apple の
[About WebDriver for Safari](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari#2957227)
のページを参照してください。

基本的な定義済みオプションで Safari セッションを開始する場合は、次のようになります。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/SafariTest.java#24-L25" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_safari.py#L9-L10" >}}
{{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/SafariTest.cs#L22-L23" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/safari_spec.rb#L8-L9" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/browser/safariSpecificCap.spec.js#L8-L11" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
val options = SafariOptions()
val driver = SafariDriver(options)
{{< /tab >}}
{{< /tabpane >}}

### モバイル

iOS 上の Safari を自動化する場合は、[Appium プロジェクト](//appium.io/)を参照してください。


<span id="service"></span>
## サービス

すべてのブラウザに共通するサービス設定は、[サービスページ]({{< ref "../drivers/service.md" >}})で
説明しています。

### ログ出力

Safari では、他のブラウザとは異なり、ログの出力先やログレベルを選択できません。利用できるのは、
ログを有効または無効にする設定だけです。ログを有効にした場合は、
`~/Library/Logs/com.apple.WebDriver/` に保存されます。

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/SafariTest.java#L31" >}}
**注**: Java では、システムプロパティでコンソール出力も設定できます。\
プロパティキー: `SafariDriverService.SAFARI_DRIVER_LOGGING`\
プロパティ値: `"true"` または `"false"`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.26" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_safari.py#L17" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/safari_spec.rb#L20" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## Safari Technology Preview

Apple は、ブラウザの開発版である [Safari Technology Preview](https://developer.apple.com/safari/technology-preview/) を
提供しています。
コードでこのバージョンを使用するには：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/SafariTest.java#L39-L40" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_safari.py#L25-L30" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/safari_spec.rb#L38-L39" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
