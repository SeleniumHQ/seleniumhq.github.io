---
title: "最初のSeleniumスクリプトを書く"
linkTitle: "最初のスクリプト"
weight: 8
needsTranslation: true
description: >
    Seleniumスクリプトを作成するための段階的な説明
---

[Seleniumをインストール]({{< ref "install_library.md" >}})し、
すると、Seleniumコードを書く準備が整います。

## 8つの基本コンポーネント

Seleniumが行うことはすべて、ブラウザコマンドを送信して、何かを実行したり、情報の要求を送信したりすることです。 
Seleniumで行うことのほとんどは、次の基本的なコマンドの組み合わせです。

[GitHub で完全な例を表示] へのリンクをクリックして、コンテキスト内のコードを表示します。

### 1. ドライバーインスタンスでセッションを開始します

セッションの開始の詳細については、次のドキュメントをお読みください [driver sessions]({{< ref "../drivers/" >}})

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L12" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L4" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L11" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L3" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L8" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L16" >}}
{{< /tab >}}
{{< /tabpane >}}

### 2. Take action on browser
こちらの例では、[ナビゲート]({{< ref "/documentation/webdriver/interactions/navigation.md" >}}) してウェブページに移動しています。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L14" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L6" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L13" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L5" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L9" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L18" >}}
{{< /tab >}}
{{< /tabpane >}}

### 3. [ブラウザに関する情報]({{< ref "/documentation/webdriver/interactions" >}})をリクエストします

ブラウザに関する [情報]({{< ref "/documentation/webdriver/interactions" >}}) として、ウィンドウハンドル、ブラウザのサイズ/位置、クッキー、アラートなど、さまざまな種類のデータをリクエストできます。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#16" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L8" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L15" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L7" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L11" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L20" >}}
{{< /tab >}}
{{< /tabpane >}}

### 4. 待機戦略の確立

コードをブラウザの現在の状態と同期させることは、最大の課題の 1 つです
Seleniumを使用して、それをうまく行うことは高度なトピックです。

基本的には、要素を見つける前に、その要素がページ上にあることを確認する必要があります
また、要素は、操作を試みる前に対話可能な状態にあります。

暗黙的な待機が最善の解決策になることはめったにありませんが、ここで示すのが最も簡単なので、
プレースホルダーとして使用します。

[待機戦略] についてさらに読む({{< ref "/documentation/webdriver/waits.md" >}}).

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L18" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L10" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L17" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L9" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L14" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L23" >}}
{{< /tab >}}
{{< /tabpane >}}

### 5. [要素を検索する]({{< ref "/documentation/webdriver/elements" >}})ためのコマンドを送信します
ほとんどのSeleniumセッションにおけるコマンドの大部分は要素に関連しており、[要素を見つける]({{< ref "/documentation/webdriver/elements" >}}) ことなしにはそれと対話することができません。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L20-L21" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L12-L13" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L19-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L11-L12" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L16-L17" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L25-L26" >}}
{{< /tab >}}
{{< /tabpane >}}

### 6. 要素に対してアクションを実行する

要素に対して行う [アクション]({{< ref "/documentation/webdriver/elements/interactions.md" >}})はわずかしかありませんが、それらは頻繁に使用されます。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L23-L24" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L15-L16" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L22-L23" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L14-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L19-L20" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L28-L29" >}}
{{< /tab >}}
{{< /tabpane >}}

### 7. 要素に関する情報をリクエストします

要素には [リクエスト可能な情報]({{< ref "/documentation/webdriver/elements/information" >}}) が多く保存されています。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L27" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L19" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L26" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L18" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L23" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L32" >}}
{{< /tab >}}
{{< /tabpane >}}

### 8. セッションを終了します 

これにより、ドライバー プロセスが終了し、既定ではブラウザーも閉じます。このドライバー インスタンスにこれ以上コマンドを送信することはできません。

[セッションの終了]({{< ref "../drivers/#quitting-sessions" >}}) を参照.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L29" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/first_script.py#L21" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L28" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script.rb#L20" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L28" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L35" >}}
{{< /tab >}}
{{< /tabpane >}}


## Seleniumファイルの実行

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/README.md#L60" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/README.md#L35" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/README.md#L36" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/README.md#L36" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## 次のステップ


ほとんどのSeleniumユーザーは多くのセッションを実行し、重複を最小限に抑え、コードをより保守しやすくするために整理する必要があります。このコードをユースケースのコンテキストに配置する方法については、以下をお読みください [Seleniumの使用]({{< ref "using_selenium.md" >}})。
