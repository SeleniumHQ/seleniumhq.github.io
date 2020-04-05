---
title: "要素を探す"
weight: 3
---


### 一つの要素を探す

ページ上で要素を探す方法は、WebDriverを使う上で最初に学ばなければならない技術です。WebDriverは多数のセレクタを標準で用意しています。その中で、id属性を使って要素を探す方法が次のコードです。

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));  
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.find_element_by_id("cheese")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement element = driver.FindElement(By.Id("cheese"));  
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: 'cheese')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = driver.findElement(By.id('cheese'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< / code-panel >}}
{{< / code-tab >}}

例を見ての通り、WebDriverで要素を特定するには、`WebDriver`クラスのインスタンスを使います。`findElement(By)`メソッドは`WebElement`という別の基本的なオブジェクトを返します。

* `WebDriver`はブラウザをあらわす
* `WebElement`は特定のDOMノード（コントロール、例えばリンクやインプットフィールドなど）をあらわす

一度「見つかった」Web要素への参照を取得すれば、そのインスタンスで同じメソッドを呼ぶことで検索の範囲を狭めることができます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
WebElement cheddar = cheese.findElement(By.id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheese = driver.find_element_by_id("cheese")
cheddar = cheese.find_elements_by_id("cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement cheese = driver.FindElement(By.Id("cheese"));
IWebElement cheddar = cheese.FindElement(By.Id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: 'cheese')
cheddar = cheese.find_element(id: 'cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = driver.findElement(By.id('cheese'));
const cheddar = cheese.findElement(By.id('cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

これは _WebDriver_ と _WebElement_ クラスの両方が[_SearchContext_](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/SearchContext.html)インターフェイスを実装しているため可能になっています。
これはWebDriverでは _ロールベースインターフェイス (role-based interface)_ と呼ばれています。
ロールベースインターフェイスは、どのドライバー実装がどの機能をサポートしているかどうかを判断する助けになります。これらのインターフェイスは明確に定義され、単一の役割の責任のみを持つことを守っています。
WebDriverの設計と、どんな役割がどのドライバでサポートされているかは[Some Other Section Which Must Be Named](#)で読むことができます。
<!-- TODO: A new section needs to be created for the above.-->

その結果、 上で使っていた _By_ インターフェイスはいくつものロケータストラテジをサポートしています。
ネストした探索はcheeseを探す方法としてもっとも効率的なものではないかもしれません。
なぜなら、この方法は二つに分割されたコマンドをブラウザに発行するからです。具体的には、まずDOMから"cheese"というidの要素を探し出し、それから狭まった範囲で"cheddar"という要素を探しています。

パフォーマンスをわずかに向上させるために、より効果的なロケータを使ってみましょう。
WebDriverはCSSロケータによる要素の探索をサポートしています。
これは先ほどの二つのロケータを1回の検索に組み合わせることができます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheddar = driver.find_element_by_css_selector("#cheese #cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.FindElement(By.CssSelector("#cheese #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.find_element(css: '#cheese #cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheddar = driver.findElement(By.css('#cheese #cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

### 複数の要素を探す

今作業しているドキュメントに、私たちが一番好きなチーズについての順序付きリストがあるとします。

```html
<ol id=cheese>
 <li id=cheddar>…
 <li id=brie>…
 <li id=rochefort>…
 <li id=camembert>…
</ul>
```

チーズがたくさんある方が良いのは疑いの余地がなく、また一個一個取らなければなければならないのは面倒です。
なので、チーズを取得する上位のテクニックは、複数形の`findElements(By)`を使うことです。
このメソッドはWeb要素のコレクションを返します。
もし一つの要素しか見つからなかった場合も、（一つの要素だけの）コレクションを返します。
もしロケータにマッチする要素が一つもなかった場合は、空のリストが返ります。

{{< code-tab >}}
  {{< code-panel language="java" >}}
List<WebElement> muchoCheese = driver.findElements(By.cssSelector("#cheese li"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
mucho_cheese = driver.find_elements_by_css_selector("#cheese li")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IReadOnlyList<IWebElement> muchoCheese = driver.FindElements(By.CssSelector(“#cheese li”));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
mucho_cheese = driver.find_elements(css: '#cheese li')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const muchoCheese = driver.findElements(By.css('#cheese li'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val muchoCheese: List<WebElement>  = driver.findElements(By.cssSelector("#cheese li"))
  {{< / code-panel >}}
{{< / code-tab >}}

### 要素選択の方法

WebDriverには標準のロケータが8種類あります。

| ロケータ | 詳細 |
| -------- | ---------- |
| class name | class名に値を含む要素を探す (複合クラス名は使えない) |
| css selector | CSSセレクタが一致する要素を探す |
| id | id属性が一致する要素を探す |
| name | name属性が一致する要素を探す |
| link text | a要素のテキストが一致する要素を探す|
| partial link text | a要素のテキストが部分一致する要素を探す |
| tag name | タグ名が一致する要素を探す |
| xpath | XPathと一致する要素を探す |

### セレクタを使うときのコツ

一般に、HTMLのid属性が利用可能でユニークかつ一貫している場合、ページで要素を探す方法として適しています。
idは動作がとても速い傾向があり、複雑なDOMトラバースに伴う処理を省略できます。

ユニークなidが使えない場合、きれいに書かれたCSSセレクタが要素を探す方法として適しています。
XPathはCSSセレクタと同様に動作しますが、シンタックスは複雑で大抵の場合デバッグが困難です。
XPathはとても柔軟ですが、ブラウザベンダは性能テストを通常行っておらず、非常に動作が遅い傾向があります。

link textセレクタとpartial _linkText_ セレクタはa要素でしか動作しないという欠点があります。
加えて、これらはWebDriverの内部でXPathの呼び出しに置き換えられます。

タグ名によるロケータは危険な方法になり得ます。
大抵の場合ページ上には同じタグ名の要素が複数あります。タグ名は要素のコレクションを返す _findElements(By)_ メソッドを使う時にもっとも役に立ちます。

ロケータは可能な限り簡潔に、読みやすい状態を保つことを推奨します。
WebDriverでDOM構造のトラバースを行うのは重い処理となります。
検索の範囲を狭めた方がより良い結果を得られます。

**Selenium 4** brings Relative Locators which are previously 
called as _Friendly Locators_. This functionality was 
added to help you locate elements that are nearby other elements.
The Available Relative Locators are:

* *above*
* *below*
* *toLeftOf*
* *toRightOf*
* *near*