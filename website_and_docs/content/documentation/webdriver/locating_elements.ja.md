---
title: "要素を探す"
linkTitle: "要素を探す"
weight: 3
aliases: ["/documentation/ja/webdriver/locating_elements/"]
---


### 一つの要素を探す

ページ上で要素を探す方法は、WebDriverを使う上で最初に学ばなければならない技術です。WebDriverは多数のセレクタを標準で用意しています。その中で、id属性を使って要素を探す方法が次のコードです。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.find_element(By.ID, "cheese")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebElement element = driver.FindElement(By.Id("cheese"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
cheese = driver.find_element(id: 'cheese')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheese = driver.findElement(By.id('cheese'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< /tab >}}
{{< /tabpane >}}

例を見ての通り、WebDriverで要素を特定するには、`WebDriver`クラスのインスタンスを使います。`findElement(By)`メソッドは`WebElement`という別の基本的なオブジェクトを返します。

* `WebDriver`はブラウザをあらわす
* `WebElement`は特定のDOMノード（コントロール、例えばリンクやインプットフィールドなど）をあらわす

一度「見つかった」Web要素への参照を取得すれば、そのインスタンスで同じメソッドを呼ぶことで検索の範囲を狭めることができます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
WebElement cheddar = cheese.findElement(By.id("cheddar"));
  {{< /tab >}}
  {{< tab header="Python" >}}
cheese = driver.find_element(By.ID, "cheese")
cheddar = cheese.find_elements_by_id("cheddar")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebElement cheese = driver.FindElement(By.Id("cheese"));
IWebElement cheddar = cheese.FindElement(By.Id("cheddar"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
cheese = driver.find_element(id: 'cheese')
cheddar = cheese.find_element(id: 'cheddar')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheese = driver.findElement(By.id('cheese'));
const cheddar = cheese.findElement(By.id('cheddar'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"));
  {{< /tab >}}
  {{< tab header="Python" >}}
cheddar = driver.find_element_by_css_selector("#cheese #cheddar")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.FindElement(By.CssSelector("#cheese #cheddar"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
driver.find_element(css: '#cheese #cheddar')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheddar = driver.findElement(By.css('#cheese #cheddar'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< /tab >}}
{{< /tabpane >}}

### 複数の要素を探す

今作業しているドキュメントに、私たちが一番好きなチーズについての順序付きリストがあるとします。

```html
<ol id=cheese>
 <li id=cheddar>…
 <li id=brie>…
 <li id=rochefort>…
 <li id=camembert>…
</ol>
```

チーズがたくさんある方が良いのは疑いの余地がなく、また一個一個取らなければなければならないのは面倒です。
なので、チーズを取得する上位のテクニックは、複数形の`findElements(By)`を使うことです。
このメソッドはWeb要素のコレクションを返します。
もし一つの要素しか見つからなかった場合も、（一つの要素だけの）コレクションを返します。
もしロケータにマッチする要素が一つもなかった場合は、空のリストが返ります。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
List<WebElement> muchoCheese = driver.findElements(By.cssSelector("#cheese li"));
  {{< /tab >}}
  {{< tab header="Python" >}}
mucho_cheese = driver.find_elements_by_css_selector("#cheese li")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IReadOnlyList<IWebElement> muchoCheese = driver.FindElements(By.CssSelector("#cheese li"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
mucho_cheese = driver.find_elements(css: '#cheese li')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const muchoCheese = driver.findElements(By.css('#cheese li'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val muchoCheese: List<WebElement>  = driver.findElements(By.cssSelector("#cheese li"))
  {{< /tab >}}
{{< /tabpane >}}

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

## レラティブ（相対）ロケーター

**Selenium 4** は、以前は _Friendly Locators_ と呼ばれていたレラティブ ロケーターをもたらします。
この機能は、他の要素の近くにある要素を見つけるのに役立つように追加されました。
使用可能なレラティブ ロケーターは次のとおりです。

* `above`
* `below`
* `toLeftOf`
* `toRightOf`
* `near`

`findElement` メソッドは、レラティブ ロケーター を返す新しいメソッド `with(By)` を受け入れるようになりました。

### どのように機能するか

Seleniumは、JavaScript関数 [getBoundingClientRect()](https://developer.mozilla.org/en-US/docs/Web/API/Element/getBoundingClientRect) を使用して相対要素を見つけます。
この関数は、right、left、bottom、topなどの要素のプロパティを返します。

レラティブ ロケーターを理解するために、以下の例を考えてみましょう。

{{< figure src="/images/documentation/webdriver/relative_locators.png" class="img-responsive text-center" alt="Relative Locators">}}

### above()

指定された要素の上に表示されるWebElementを返します。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement passwordField = driver.findElement(By.id("password"));
WebElement emailAddressField = driver.findElement(with(By.tagName("input"))
.above(passwordField));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

passwordField = driver.find_element(By.ID, "password")
emailAddressField = driver.find_element(locate_with(By.TAG_NAME, "input").above(passwordField))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement passwordField = driver.FindElement(By.Id("password"));
IWebElement emailAddressField = driver.FindElement(RelativeBy(By.TagName("input")).Above(passwordField));
{{< /tab >}}
{{< tab header="Ruby" >}}
password_field= driver.find_element(:id, "password")
email_address_field = driver.find_element(relative: {tag_name: 'input', above:password_field})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let passwordField = driver.findElement(By.id('password'));
let emailAddressField = await driver.findElement(locateWith(By.tagName('input')).above(passwordField));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val passwordField = driver.findElement(By.id("password"))
val emailAddressField = driver.findElement(with(By.tagName("input")).above(passwordField))
{{< /tab >}}
{{< /tabpane >}}


### below()

指定された要素の下に表示されるWebElementを返します。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement emailAddressField = driver.findElement(By.id("email"));
WebElement passwordField = driver.findElement(with(By.tagName("input"))
.below(emailAddressField));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

emailAddressField = driver.find_element(By.ID, "email")
passwordField = driver.find_element(locate_with(By.TAG_NAME, "input").below(emailAddressField))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement emailAddressField = driver.FindElement(By.Id("email"));
IWebElement passwordField = driver.FindElement(RelativeBy(By.TagName("input")).Below(emailAddressField));
{{< /tab >}}
{{< tab header="Ruby" >}}
email_address_field = driver.find_element(:id, "email")
password_field = driver.find_element(relative: {tag_name: 'input', below: email_address_field})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let emailAddressField = driver.findElement(By.id('email'));
let passwordField = await driver.findElement(locateWith(By.tagName('input')).below(emailAddressField));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val emailAddressField = driver.findElement(By.id("email"))
val passwordField = driver.findElement(with(By.tagName("input")).below(emailAddressField))
{{< /tab >}}
{{< /tabpane >}}


### toLeftOf()

指定された要素の左側に表示されるWebElementを返します。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement submitButton = driver.findElement(By.id("submit"));
WebElement cancelButton = driver.findElement(with(By.tagName("button"))
.toLeftOf(submitButton));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

submitButton = driver.find_element(By.ID, "submit")
cancelButton = driver.find_element(locate_with(By.TAG_NAME, "button").
to_left_of(submitButton))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement submitButton = driver.FindElement(By.Id("submit"));
IWebElement cancelButton = driver.FindElement(RelativeBy(By.TagName("button")).LeftOf(submitButton));
{{< /tab >}}
{{< tab header="Ruby" >}}
submit_button= driver.find_element(:id, "submit")
cancel_button = driver.find_element(relative: {tag_name: 'button', left:submit_button})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let submitButton = driver.findElement(By.id('submit'));
let cancelButton = await driver.findElement(locateWith(By.tagName('button')).toLeftOf(submitButton));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val submitButton = driver.findElement(By.id("submit"))
val cancelButton = driver.findElement(with(By.tagName("button")).toLeftOf(submitButton))
{{< /tab >}}
{{< /tabpane >}}


### toRightOf()

指定された要素の右側に表示されるWebElementを返します。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement cancelButton = driver.findElement(By.id("cancel"));
WebElement submitButton = driver.findElement(with(By.tagName("button")).toRightOf(cancelButton));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

cancelButton = driver.find_element(By.ID, "cancel")
submitButton = driver.find_element(locate_with(By.TAG_NAME, "button").
to_right_of(cancelButton))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement cancelButton = driver.FindElement(By.Id("cancel"));
IWebElement submitButton = driver.FindElement(RelativeBy(By.TagName("button")).RightOf(cancelButton));
{{< /tab >}}
{{< tab header="Ruby" >}}
cancel_button = driver.find_element(:id, "cancel")
submit_button = driver.find_element(relative: {tag_name: 'button', right:cancel_button})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let cancelButton = driver.findElement(By.id('cancel'));
let submitButton = await driver.findElement(locateWith(By.tagName('button')).toRightOf(cancelButton));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val cancelButton = driver.findElement(By.id("cancel"))
val submitButton = driver.findElement(with(By.tagName("button")).toRightOf(cancelButton))
{{< /tab >}}
{{< /tabpane >}}

### near()

指定した要素から最大 `50px` 離れたWebElementを返します。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement emailAddressLabel = driver.findElement(By.id("lbl-email"));
WebElement emailAddressField = driver.findElement(with(By.tagName("input")).near(emailAddressLabel));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

emailAddressLabel = driver.find_element(By.ID, "lbl-email")
emailAddressField = driver.find_element(locate_with(By.TAG_NAME, "input").
near(emailAddressLabel))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement emailAddressLabel = driver.FindElement(By.Id("lbl-email"));
IWebElement emailAddressField = driver.FindElement(RelativeBy(By.TagName("input")).Near(emailAddressLabel));
{{< /tab >}}
{{< tab header="Ruby" >}}
email_address_label = driver.find_element(:id, "lbl-email")
email_address_field = driver.find_element(relative: {tag_name: 'input', near: email_address_label})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let emailAddressLabel = driver.findElement(By.id("lbl-email"));
let emailAddressField = await driver.findElement(locateWith(By.tagName("input")).near(emailAddressLabel));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val emailAddressLabel = driver.findElement(By.id("lbl-email"))
val emailAddressField = driver.findElement(with(By.tagName("input")).near(emailAddressLabel))
{{< /tab >}}
{{< /tabpane >}}
