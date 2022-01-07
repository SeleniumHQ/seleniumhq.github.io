---
title: "Style guide for Selenium documentation"
linkTitle: "Style"
needsTranslation: true
description: >-
    Conventions for contributing to the Selenium documenation and code examples
weight: 3
---

## タイトルの大文字化

_A Very Fine Heading_ などのタイトルの大文字化は避け、代わりに _A very fine heading_ を選択してください。
大げさな大文字表記、またはタイトルケースは、多くの場合、正書法の慣習に対する誤解または無視を示します。
ヘッダーを開始するための最初の大文字を1つ持つセンテンスケースとして知られているものを好みます。

## 行の長さ

プレーンHTMLで記述されたドキュメントのソースを編集するときは、行の長さを約72文字に制限してください。

これをさらに一歩進めて、いわゆる[セマンティックラインフィード](//rhodesmill.org/brandon/2012/one-sentence-per-line)
と呼ばれるものを使用します。
これは、一般の人には読まれないHTMLソース行を散文の「自然な区切り」で分割する手法です。
つまり、文は句間の自然な区切りで分割されます。
すべての段落が右マージンの近くで終了するように各段落の行を混乱させるのではなく、
アイデアが途切れる場所であればどこでも改行を追加できます。

これにより、gitを使用して共同作業するときにdiffを非常に読みやすくすることができますが、
使用するコントリビューターに強制するものではありません。

## Translations

ドキュメントは複数の言語に翻訳されており、翻訳は英語版に基づいて行われます。
ファイルに変更を加えたときは、他の翻訳済みファイル全てに**必ず**同様の変更を加えてください。
ただし、変更内容によって異なります。以下に例を示します:

* `browser_manipulation.en.md`ファイルにコード例を加えた場合、`browser_manipulation.es.md`、 `browser_manipulation.ef.md`及びすべての翻訳ファイルに追加してください。
`browser_manipulation.ja.md`
* 翻訳の改善を行う場合は、各言語のファイルのみを変更してください。
* 新しい言語向けの翻訳を追加したい場合、適切な接尾辞を付けてファイルを追加してください。プルリクエストを送信するために全てを翻訳する必要はありません、イテレーティブに行うことができます。`config.toml`ファイルで必要な設定値の確認を忘れないでください。
* 英語版の文章に変更を加えたい場合は、翻訳されたファイルの同じ箇所をあなたの変更で（英語で）書き換えたうえで、以下の注意書きをファイルの先頭に追加してください。


```
{{%/* pageinfo color="warning" */%}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to {LANGUAGE}. Do you speak {LANGUAGE}? Help us to translate
   it by sending us pull requests!
</p>
{{%/* /pageinfo */%}}
```

## Code examples

All references to code should be language independent,
and the code itself should be placed inside code tabs.

### Default Code Tabs

The Docsy code tabs look like this:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
WebDriver driver = new ChromeDriver();
driver.get("https://selenium.dev");
driver.quit();
{{< /tab >}}
{{< tab header="Python" >}}
driver = webdriver.Chrome()
driver.get("http://selenium.dev")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" >}}
var driver = new ChromeDriver();
driver.Navigate().GoToUrl("https://selenium.dev");
driver.Quit();
{{< /tab >}}
{{< tab header="Ruby" >}}
driver = Selenium::WebDriver.for :chrome
driver.get 'https://selenium.dev'
driver.quit
{{< /tab >}}
{{< tab header="JavaScript" >}}
let driver = await new Builder().forBrowser('chrome').build();
await driver.get('https://selenium.dev');
await driver.quit();
{{< /tab >}}
{{< tab header="Kotlin" >}}
val driver = ChromeDriver()
driver.get("https://selenium.dev")
driver.quit()
}
{{< /tab >}}
{{< /tabpane >}}

To generate the above tabs, this is what you need to write.
Note that the tabpane includes `langEqualsHeader=true`.
This auto-formats the code in each tab to match the header name.

    {{</* tabpane langEqualsHeader=true */>}}
      {{</* tab header="Java" */>}}
        WebDriver driver = new ChromeDriver();
        driver.get("https://selenium.dev");
        driver.quit();
      {{</* /tab */>}}
      {{</* tab header="Python" */>}}
        driver = webdriver.Chrome()
        driver.get("http://selenium.dev")
        driver.quit()
      {{</* /tab */>}}
      {{</* tab header="CSharp" */>}}
        var driver = new ChromeDriver();
        driver.Navigate().GoToUrl("https://selenium.dev");
        driver.Quit();
      {{</* /tab */>}}
      {{</* tab header="Ruby" */>}}
        driver = Selenium::WebDriver.for :chrome
        driver.get 'https://selenium.dev'
        driver.quit
      {{</* /tab */>}}
      {{</* tab header="JavaScript" */>}}
        let driver = await new Builder().forBrowser('chrome').build();
        await driver.get('https://selenium.dev');
        await driver.quit();
      {{</* /tab */>}}
      {{</* tab header="Kotlin" */>}}
        val driver = ChromeDriver()
        driver.get("https://selenium.dev")
        driver.quit()
      }
      {{</* /tab */>}}
    {{</* /tabpane */>}}

### Disabling Code Block

If you want your example to include both text and code, you
need to disable the default of everything being put in a code block

Maybe you want something like this:

{{< tabpane disableCodeBlock=true >}}
{{< tab header="Java" >}}
1. Start the driver
  ```java
    WebDriver driver = new ChromeDriver();
  ```
2. Navigate to a page
  ```java
  driver.get("https://selenium.dev");
  ```
3. Quit the driver
  ```java
  driver.quit();
  ``` 
{{< /tab >}}
{{< /tabpane >}}

For this you need to use `disableCodeBlock=true` instead of `langEqualsHeader=true`

You need to specify which parts are code and which are not yourself now, like this:

    {{</* tabpane disableCodeBlock=true */>}}
    {{</* tab header="Java" */>}}
    1. Start the driver
      ```java
        WebDriver driver = new ChromeDriver();
      ```
    2. Navigate to a page
      ```java
      driver.get("https://selenium.dev");
      ```
    3. Quit the driver
      ```java
      driver.quit();
      ``` 
    {{</* /tab */>}}
    < ... >
    {{</* /tabpane */>}}

### Code Comments

Minimize code comments because they are difficult to translate.
Further, try to avoid over-explaining each line of code unless it is
directly related to the page you are writing.
