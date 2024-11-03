---
title: "ドライバーサービスクラス"
linkTitle: "サービス"
weight: 3
---

サービスクラスは、ドライバーの起動と停止を管理するためのものです。リモートWebDriverセッションでは使用できません。

サービスクラスを使用すると、ドライバーに関する情報（場所や使用するポートなど）を指定できます。また、コマンドラインに渡される引数を指定することもできます。便利な引数のほとんどは、ログに関連しています。

## デフォルトサービスインスタンス

デフォルトサービスインスタンスを使用してドライバーを起動するには：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L15-L16" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L5-L6" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L14-L15" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L14-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## ドライバーの場所

**注意:** Selenium 4.6以上を使用している場合、ドライバーの場所を設定する必要はありません。Seleniumを更新できない場合や、特別な使用ケースがある場合は、ドライバーの場所を指定する方法は次のとおりです：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L25-L26" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L15" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.9" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L23" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L22" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## ドライバーのポート

ドライバーを特定のポートで実行したい場合は、次のように指定できます：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/ServiceTest.java#L33" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/drivers/test_service.py#L23" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/ServiceTest.cs#L32" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/service_spec.rb#L29" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

<span id="setting-log-output"></span>
## ログ記録

ログ記録機能はブラウザによって異なります。ほとんどのブラウザでは、ログの場所とレベルを指定できます。各ブラウザのページを確認してください：
* [Chrome]({{< ref "../browsers/chrome#service" >}})
* [Edge]({{< ref "../browsers/edge#service" >}})
* [Firefox]({{< ref "../browsers/firefox#service" >}})
* [Internet Explorer]({{< ref "../browsers/internet_explorer#service" >}})
* [Safari]({{< ref "../browsers/safari#service" >}})
