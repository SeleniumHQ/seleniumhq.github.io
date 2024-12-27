---
title: "ドライバーセッション"
linkTitle: "ドライバー"
weight: 3
---

セッションの開始と停止は、ブラウザーを開いたり閉じたりするためのものです。

## セッションの作成

新しいセッションの作成は、W3C コマンド [New session](https://w3c.github.io/webdriver/#new-session) に対応しています。

セッションは、新しいDriverクラスオブジェクトを初期化することによって自動的に作成されます。

各言語では、次のいずれかのクラス (または同等のもの) の引数を使用してセッションを作成することができます。

* [オプション]({{< ref "options.md" >}}) 作成を希望するセッションの種類 ; ローカルにはデフォルト値を使用しますが、リモートには必須です。
* 何らかの形の[HTTP Client configuration]({{< ref "http_client.md" >}})  (実装は言語によって異なります)
* [リスナー]({{< ref "listeners.md" >}})

### ローカルドライバー

ローカルドライバーを起動するための主な一意の引数には、ローカルコンピューターで必要なドライバーサービスを起動するための情報が含まれます。

* [Service]({{< ref "service.md" >}})オブジェクトはローカルドライバーにのみ適用され、ブラウザーのドライバーに関する情報を提供します。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/OptionsTest.java#L23" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_options.py#L9" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/BaseTest.cs#L42" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/options_spec.rb#L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/drivers/service.spec.js#L32-L36" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### リモートドライバー

リモートドライバーを起動するための主な一意の引数には、コードを実行する場所に関する情報を含みます。
詳細は、[リモートドライバー]({{< ref "remote_webdriver.md" >}})をご覧ください。

## セッションの終了

セッションの終了に対するW3Cコマンドは、[セッションの削除](https://w3c.github.io/webdriver/#delete-session)です。

重要:  `quit` メソッドは `close` メソッドとは異なり、
セッションを終了するには常に `quit` を使用することをお勧めします。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L29" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_options.py#L11" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L28" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/options_spec.rb#L16" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L28" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L35" >}}
{{< /tab >}}
{{< /tabpane >}}
