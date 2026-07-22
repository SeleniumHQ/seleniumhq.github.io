---
title: "Seleniumドキュメントのスタイルガイド"
linkTitle: "スタイル"
weight: 6
description: >-
  Seleniumドキュメントとコード例へのコントリビューションに関する規約
---

このドキュメントにコンテンツを追加する方法の詳細な手順については、
[コントリビューションのドキュメント]({{< ref contributing.md >}})を参照してください。

## アラート

アラートは、特定のコンテンツが不足している場所をコントリビュータ候補に示すために追加されています。

{{< highlight html >}}
{{</* alert-content /*/>}}
{{< /highlight >}}

または

{{< highlight html >}}
{{</* alert-content */>}}
必要な具体的なコンテンツに関する追加情報
{{</* /alert-content */>}}
{{< /highlight >}}

これは次のように表示されます:
{{< alert-content >}}
必要な具体的なコンテンツに関する追加情報
{{< /alert-content >}}

## タイトルの大文字小文字

このドキュメントでは、短くするべき `linkTitle` にはタイトルケースを使用し、
より長く説明的にできる `title` には文頭のみ大文字にする形式を使用します。
例えば、`linkTitle` が _Special Heading_ の場合、`title` は
_The importance of a special heading in documentation_ のようになります。

## 行の長さ

プレーンHTMLで書かれているドキュメントのソースを編集するときは、
1行の長さをおおよそ100文字に制限してください。

私たちの一部はこれをさらに進めて、
[_semantic linefeeds_](//rhodesmill.org/brandon/2012/one-sentence-per-line)
と呼ばれる手法を使っています。
これは、一般には読まれないHTMLソースの行を、
文章中の「自然な区切り」で分割する手法です。
言い換えると、文を節と節の自然な区切りで分割します。
各段落の行がすべて右端付近で終わるように調整する代わりに、
考えの区切りがある場所であればどこにでも改行を入れられます。

これにより、gitで共同作業をするときにdiffが非常に読みやすくなりますが、
コントリビュータに使用を強制しているものではありません。

## 翻訳

Seleniumには現在、サポートされている各言語の公式翻訳者がいます。

* `important_documentation.en.md` ファイルにコード例を追加する場合は、
  `important_documentation.ja.md`、`important_documentation.pt-br.md`、
  `important_documentation.zh-cn.md` にも追加してください。
* 英語版のテキストを変更する場合は、そのままPull Requestを作成してください。
  新しいプロセスでは、該当PRで行われた変更に基づいてイシューが作成され、
  needs translation としてタグ付けされます。

## コード例

コードへのすべての参照は言語に依存しない形にし、
コード自体はコードタブ内に配置してください。

### デフォルトのコードタブ

Docsyのコードタブは次のようになります:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
WebDriver driver = new ChromeDriver();
{{< /tab >}}
{{< tab header="Python" >}}
driver = webdriver.Chrome()
{{< /tab >}}
{{< tab header="CSharp" >}}
var driver = new ChromeDriver();
{{< /tab >}}
{{< tab header="Ruby" >}}
driver = Selenium::WebDriver.for :chrome
{{< /tab >}}
{{< tab header="JavaScript" >}}
let driver = await new Builder().forBrowser('chrome').build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
val driver = ChromeDriver()
{{< /tab >}}
{{< /tabpane >}}

上記のタブを生成するには、次のように記述します。
`tabpane` に `langEqualsHeader=true` が含まれていることに注意してください。
これにより、各タブのコードがヘッダー名に合わせて自動的にフォーマットされ、
ページ上の言語付きタブがすべて同じ選択状態になることが保証されます。

    {{</* tabpane langEqualsHeader=true */>}}
      {{</* tab header="Java" */>}}
        WebDriver driver = new ChromeDriver();
      {{</* /tab */>}}
      {{</* tab header="Python" */>}}
        driver = webdriver.Chrome()
      {{</* /tab */>}}
      {{</* tab header="CSharp" */>}}
        var driver = new ChromeDriver();
      {{</* /tab */>}}
      {{</* tab header="Ruby" */>}}
        driver = Selenium::WebDriver.for :chrome
      {{</* /tab */>}}
      {{</* tab header="JavaScript" */>}}
        let driver = await new Builder().forBrowser('chrome').build();
      {{</* /tab */>}}
      {{</* tab header="Kotlin" */>}}
        val driver = ChromeDriver()
      {{</* /tab */>}}
    {{</* /tabpane */>}}

#### GitHubの例を参照する

すべてのコードを最新に保つために、
Seleniumのバージョン更新時に実行して正しいことを確認できるよう、
リポジトリ内にコードを書くことを目標にしています。

すべてのコード例は
[exampleディレクトリ](https://github.com/SeleniumHQ/seleniumhq.github.io/tree/dev/examples)
内に配置してください。

このコードは、`gh-codeblock` ショートコードを使用してドキュメントに自動表示できます。
このショートコードは独自のhtmlを自動生成するため、
言語ヘッダーによる自動フォーマットは行わないようにします。
すべてのタブでこのショートコードを使用している場合は、
`tabpane` に `text=true` を設定し、`langEqualsHeader=true` を削除してください。
一部のタブだけでこのショートコードを使用している場合は、
`tabpane` の `langEqualsHeader=true` は残し、対象の `tab` に `text=true` を追加してください。
なお、`gh-codeblock` の行はまったくインデントできません。

`gh-codeblock` を使う利点の1つは、完全な例へのリンクが追加されることです。
つまり、追加のコンテキストコードを含める必要はなく、必要な行だけを含めれば、
ユーザーはリポジトリに移動して使い方を確認できます。

コードの基本的な比較は次のようになります:

    {{</* tabpane text=true */>}}
    {{</* tab header="Java" */>}}
    {{</* gh-codeblock path="/examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L26-L27" */>}}
    {{</* /tab */>}}
    {{</* tab header="Python" */>}}
    {{</* gh-codeblock path="/examples/python/tests/getting_started/first_script.py#L18-L19" */>}}
    {{</* /tab */>}}
    {{</* tab header="CSharp" */>}}
    {{</* gh-codeblock path="/examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L25-L26" */>}}
    {{</* /tab */>}}
    {{</* tab header="Ruby" */>}}
    {{</* gh-codeblock path="/examples/ruby/spec/getting_started/first_script.rb#L17-L18" */>}}
    {{</* /tab */>}}
    {{</* tab header="JavaScript" */>}}
    {{</* gh-codeblock path="/examples/javascript/test/getting_started/firstScript.spec.js#L22-L23" */>}}
    {{</* /tab */>}}
    {{</* tab header="Kotlin" */>}}
    {{</* gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L31-L32" */>}}
    {{</* /tab */>}}
    {{</* /tabpane */>}}

これは次のように表示されます:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L26-L27" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/getting_started/first_script.py#L18-L19" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/GettingStarted/FirstScript.cs#L25-L26" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/getting_started/first_script.rb#L17-L18" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/firstScript.spec.js#L22-L23" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L31-L32" >}}
{{< /tab >}}
{{< /tabpane >}}

### タブ内でMarkdownを使用する

例にコード（デフォルト）や `gh-codeblock` からのhtml以外のものを含めたい場合は、
まず `text=true` を設定する必要があります。
次に、`tab` のHugo構文を、波括弧内で `<` と `>` ではなく `%` を使う形に変更します:

    {{</* tabpane text=true */>}}
    {{%/* tab header="Java" */%}}
    1. ドライバーを開始する
    {{</* gh-codeblock path="/examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L12" */>}}
    2. ページに移動する
    {{</* gh-codeblock path="/examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L14" */>}}
    3. ドライバーを終了する
    {{</* gh-codeblock path="/examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L29" */>}}
    {{%/* /tab */%}}
    < ... >
    {{</* /tabpane */>}}

これは次のように生成されます:

{{< tabpane text=true >}}
{{% tab header="Java" %}}

1. ドライバーを開始する
   {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L12" >}}
2. ページに移動する
   {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L14" >}}
3. ドライバーを終了する
   {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/getting_started/FirstScript.java#L29" >}}
   {{% /tab %}}
   {{< /tabpane >}}

コードコメントは翻訳されないため、これはコードコメントを書くよりも望ましい方法です。
ドキュメントに必要なコードだけを含め、過度な説明は避けてください。
最後に、プレーンテキストをインデントするとコードブロックとしてレンダリングされるため、
インデントしないようにしてください。
