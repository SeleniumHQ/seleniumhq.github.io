---
title: "RCからWebDriverへの移行"
linkTitle: "RCからWebDriverへの移行"
weight: 2
aliases: [
"/documentation/ja/legacy_docs/migrating_from_rc_to_webdriver/",
"/ja/documentation/legacy/migrating_from_rc_to_webdriver/"
]

aliases: []
---


## Selenium WebDriverに移行する方法


Selenium 2を採用する際によくある質問は、「既存のテストセットに新しいテストを追加するときに正しいことは何ですか？」ということです。
フレームワークを初めて使用するユーザーは、新しいWebDriver APIを使用してテストを作成することから始めることができます。
しかし、既存のテストスイートを既に持っているユーザーはどうでしょうか？
このガイドは、既存のテストを新しいAPIに移行し、WebDriverが提供する新機能を使用してすべての新しいテストを作成する方法を示すことを目的としています。

ここで紹介する方法は、1回の大規模なプッシュですべてをやり直す必要のない、WebDriver APIへの断片的な移行について説明しています。
これは、既存のテストの移行により多くの時間を割り当てることができることを意味します。
これにより、どこに労力を費やすかを決定しやすくなります。

このガイドは、移行を行うための最良のサポートがあるため、Javaを使用して書かれています。
他の言語用のより優れたツールを提供するため、このガイドはそれらの言語を含むように拡張されます。

## WebDriverに移行する理由


一連のテストをあるAPIから別のAPIに移動するには、多大な労力が必要です。
なぜあなたとあなたのチームはこの動きを検討するのですか？
WebDriverを使用するためにSeleniumテストを移行することを検討する必要があるいくつかの理由を以下に示します。

* 小さくコンパクトなAPI。
  WebDriverのAPIは、元のSelenium RC APIよりもオブジェクト指向です。
  これにより、作業が容易になります。
* ユーザー操作のより良いエミュレーション。
  可能な場合、WebDriverはWebページと対話するためにネイティブイベントを使用します。
  これは、ユーザーがサイトやアプリを操作する方法をより厳密に模倣しています。
  さらに、WebDriverは、サイトとの複雑な相互作用をモデル化できる高度なユーザーインタラクションAPIを提供します。
* ブラウザーベンダーによるサポート。
  Opera、Mozilla、GoogleはすべてWebDriverの開発に積極的に参加しており、それぞれにフレームワークの改善に取り組んでいるエンジニアがいます。
  多くの場合、これはWebDriverのサポートがブラウザー自体に組み込まれていることを意味します。
  テストは可能な限り高速で安定して実行されます。


## はじめる前に


移行プロセスを可能な限り簡単にするために、すべてのテストが最新のSeleniumリリースで正しく実行されることを確認してください。
これは当たり前のように聞こえるかもしれませんが、言ってもらうのが最善です！


## はじめに


移行を開始する最初の手順は、Seleniumのインスタンスの取得方法を変更することです。
Selenium RCを使用する場合、これは次のように行われます。

```java
Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.yoursite.com");
selenium.start();
```

これは次のように置き換える必要があります。

```java
WebDriver driver = new FirefoxDriver();
Selenium selenium = new WebDriverBackedSelenium(driver, "http://www.yoursite.com");
```

## 次のステップ


テストがエラーなしで実行されたら、次の段階は実際のテストコードを移行してWebDriver APIを使用することです。
コードがどれだけ適切に抽象化されているかによって、これは短いプロセスまたは長いプロセスになります。
どちらの場合でも、アプローチは同じであり、簡単に要約できます。
編集するときに新しいAPIを使用するようにコードを変更します。

基になるWebDriver実装をSeleniumインスタンスから抽出する必要がある場合は、WrapsDriverにキャストするだけです。

```java
WebDriver driver = ((WrapsDriver) selenium).getWrappedDriver();
```

これにより、通常どおりSeleniumインスタンスの受け渡しを続けることができますが、必要に応じてWebDriverインスタンスのラップを解除できます。

ある時点で、コードベースは主に新しいAPIを使用します。
この時点で、WebDriverを使用して関係を反転し、オンデマンドでSeleniumインスタンスをインスタンス化できます。

```java
Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);
```

## 一般的な問題


幸いなことに、この移行を最初に行ったのはあなたではないので、他の人が経験した一般的な問題とその解決方法を以下に示します。

### クリックと入力がより完全に


Selenium RCテストの一般的なパターンは、以下のとおりです。

```java
selenium.type("name", "exciting tex");
selenium.keyDown("name", "t");
selenium.keyPress("name", "t");
selenium.keyUp("name", "t");
```


これは、ユーザーがページと対話した場合に通常発生するすべてのイベントも発生せずに、"type"が識別された要素のコンテンツを単に置き換えるという事実に依存しています。
"key*" の最後の直接呼び出しにより、JSハンドラーが期待どおりに起動します。

WebDriverBackedSeleniumを使用する場合、フォームフィールドに入力した結果は "exciting texttt" になります。
期待したものではありません！
これは、WebDriverがユーザーの動作をより正確にエミュレートするため、ずっとイベントを発火させていたためです。

この同じ事実により、Selenium 1テストよりも早くページの読み込みが発生する場合があります。
"StaleElementException"がWebDriverによってスローされた場合、これが発生したことを確認できます。

### WaitForPageToLoadがすぐに戻る

ページの読み込みが完了したことを発見するのは難しい仕事です。
"ロードイベントが発生したとき"、"すべてのAJAXリクエストが完了したとき"、"ネットワークトラフィックがないとき"、"document.readyStateが変更されたとき"、または他の全体的な何かを意味しますか？

WebDriverは元のSeleniumの動作をシミュレートしようとしますが、これはさまざまな理由で常に完全に機能するとは限りません。
最も一般的な理由は、ページの読み込みがまだ開始されていないことと、ページ呼び出しがメソッド呼び出し間で完了したことの違いを見分けることが難しいことです。
これは、ページの読み込みが完了する前（または開始される前）に制御がテストに返されることを意味する場合があります。

これに対する解決策は、特定の何かを待つことです。
一般的に、これは次にやり取りしたい要素、または特定の値に設定されるJavascript変数のためのものです。
例えば、

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
WebElement element= wait.until(visibilityOfElementLocated(By.id("some_id")));
```

"visibilityOfElementLocated" は次のように実装されます。

```java
public ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
  return new ExpectedCondition<WebElement>() {
    public WebElement apply(WebDriver driver) {
      WebElement toReturn = driver.findElement(locator);
      if (toReturn.isDisplayed()) {
        return toReturn;
      }
      return null;
    }
  };
}
```
 
これは複雑に見えるかもしれませんが、ほとんどすべての定型コードです。
唯一の興味深い点は、 "apply" メソッドが "null" でもBoolean.FALSEでもないものを返すまで、 "ExpectedCondition" が繰り返し評価されることです。

もちろん、これらの "wait" 呼び出しをすべて追加すると、コードが混乱する可能性があります。
その場合で、ニーズが単純な場合は、暗黙的な待機の使用を検討してください。

```java
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
```

これにより、要素が見つかるたびに、要素が存在しない場合は、存在するか、30秒が経過するまで位置が再試行されます。

### XPathまたはCSSセレクターによる検索は常に機能するとは限りませんが、Selenium1では機能します

Selenium 1では、xpathがブラウザ自体の機能ではなく、バンドルされたライブラリを使用するのが一般的でした。
代替手段がない限り、WebDriverは常にネイティブブラウザーメソッドを使用します。
つまり、一部のブラウザでは複雑なxpath式が壊れる場合があります。

Selenium 1のCSSセレクターは、Sizzleライブラリを使用して実装されました。
これにより、CSS Selector仕様のスーパーセットが実装され、どこで境界を越えたかが常に明確になるとは限りません。
WebDriverBackedSeleniumを使用していて、要素の検索にCSSセレクターの代わりにSizzleロケーターを使用している場合、コンソールに警告が記録されます。
特に要素を見つけることができないためにテストが失敗する場合、これらを探すのに時間をかける価値があります。

### Browserbotはありません

Selenium RCはSelenium Coreに基づいていたため、Javascriptを実行すると、Selenium Coreの一部にアクセスして作業を簡単にすることができました。
WebDriverはSelenium Coreに基づいていないため、これは不可能です。
Selenium Coreを使用しているかどうかをどのように確認できますか？
シンプル！ "getEval" または同様の呼び出しが、評価されたJavascriptで "selenium" または "browserbot" を使用しているかどうかを確認してください。

browserbotを使用して、テストの現在のウィンドウまたはドキュメントへのハンドルを取得している可能性があります。
幸いなことに、WebDriverは常に現在のウィンドウのコンテキストでJSを評価するため、"ウィンドウ"または"ドキュメント"を直接使用できます。

または、Browserbotを使用して要素を見つけることもできます。
WebDriverでは、これを行うためのイディオムは、最初に要素を見つけ、それを引数としてJavascriptに渡すことです。
従って、

```java
String name = selenium.getEval(
    "selenium.browserbot.findElement('id=foo', browserbot.getCurrentWindow()).tagName");
```

このようになります。

```java
WebElement element = driver.findElement(By.id("foo"));
String name = (String) ((JavascriptExecutor) driver).executeScript(
    "return arguments[0].tagName", element);
```

渡された "element" 変数が、JS標準の "arguments" 配列の最初の項目としてどのように表示されるかに注目してください。

### Executing Javascript Doesn't Return Anything


WebDriverのJavascriptExecutorは、すべてのJSをラップし、匿名式として評価します。
これは、 "return" キーワードを使用する必要があることを意味します。

```java
String title = selenium.getEval("browserbot.getCurrentWindow().document.title");
```

このようになります。

```java
((JavascriptExecutor) driver).executeScript("return document.title;");
```
    
