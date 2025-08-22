---
title: "コマンドリスナー"
linkTitle: "リスナー"
weight: 2
aliases: [
  "/documentation/webdriver/drivers/listeners",
]
---

これにより、特定のSeleniumコマンドが送信されるたびにカスタムアクションを実行することができます。


## 定義

カスタムリスナーは、SeleniumのAbstractEventListenerを継承し、AbstractEventListenerのメソッドをオーバーライドする必要があります。
イベントを印刷してログに記録するための簡単な例を示します：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/support/test_listener.py#L1-L33" >}}
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

## 使用（しよう）

EventFiringWebDriverを使ってイベントリスナードライバーを作成する：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/support/test_listener.py#L87-L96" >}}
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

