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

* [オプション]({{< ref "options.md" >}}) 作成を希望するセッションの種類 ;  ローカルにはデフォルト値を使用しますが、リモートには必須です。
* 何らかの形の[HTTP Client configuration]({{< ref "http_client.md" >}})  (実装は言語によって異なります)
* [リスナー]({{< ref "listeners.md" >}})
  
### ローカルドライバー

ローカルドライバーを起動するための主な一意の引数には、ローカルコンピューターで必要なドライバーサービスを起動するための情報が含まれます。


* [Service]({{< ref "service.md" >}})オブジェクトはローカルドライバーにのみ適用され、ブラウザーのドライバーに関する情報を提供します。

{{< tabpane text=true >}}
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
{{< badge-code >}}
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
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
