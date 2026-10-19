---
title: "JavaScript アラート、プロンプトおよび確認"
linkTitle: "JavaScript アラート、プロンプトおよび確認"
weight: 2
aliases: [
"/documentation/webdriver/js_alerts_prompts_and_confirmations/",
"/documentation/webdriver/browser/alerts"
]
---

WebDriverは、JavaScriptが提供する3種類のネイティブポップアップメッセージを操作するためのAPIを提供します。
これらのポップアップはブラウザーによってスタイルが設定され、カスタマイズが制限されています。

## アラート

これらの最も単純なものはアラートと呼ばれ、カスタムメッセージと、ほとんどのブラウザーでOKのラベルが付いたアラートを非表示にする単一のボタンを表示します。
ほとんどのブラウザーでは閉じるボタンを押すことで閉じることもできますが、これは常にOKボタンと同じことを行います。
<a onclick="window.alert('Sample alert')">アラートの例を参照してください</a>。

WebDriverはポップアップからテキストを取得し、これらのアラートを受け入れるか、または閉じることができます。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L36-L41" >}}
{{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L13-L19" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L43-L53" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L15-L22" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L19-L21" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L24-L34" >}}
{{< /tab >}}
{{< /tabpane >}}

## 確認

確認ダイアログボックスはアラートに似ていますが、ユーザーがメッセージをキャンセルすることも選択できる点が異なります。
<a onclick="window.confirm('Are you sure?')">サンプルを確認してください</a>。

この例は、アラートを保存する別の方法も示しています。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L131-L138" >}}
{{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L27-L33" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L63-L76" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L28-L35" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L30-L32" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L43-L56" >}}
{{< /tab >}}
{{< /tabpane >}}

## プロンプト

プロンプトは確認ボックスに似ていますが、テキスト入力も含まれている点が異なります。
フォーム要素の操作と同様に、WebDriverの送信キーを使用して応答を入力できます。
これにより、プレースホルダーテキストが完全に置き換えられます。
キャンセルボタンを押してもテキストは送信されません。
<a onclick="window.prompt('What is your tool of choice?',navigator.appName)">サンプルプロンプトを参照してください</a>。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L79-L84" >}}
{{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L41-L48" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L87-L97" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L41-L48" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L42-L45" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L66-L76" >}}
{{< /tab >}}
{{< /tabpane >}}

## beforeunload

最近のドライバーでは、デフォルトで `beforeunload` プロンプトを自動的に閉じます。
以前の動作を維持するには、
[unhandledPromptBehavior]({{< ref "../drivers/options#unhandledpromptbehavior" >}}) を参照してください。
