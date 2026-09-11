---
title: "Web要素の検索"
linkTitle: "検索"
weight: 2
aliases: [
"/documentation/ja/webdriver/locating_elements/",
"/ja/documentation/webdriver/locating_elements/"
]
description: >
  提供されたロケーターの値に基づいて要素を検索します。
---

Seleniumを使用する最も基本的な側面の1つは、操作する要素の参照を取得することです。 
Seleniumは、要素を一意に識別するための多数の組み込み[ロケーター戦略]({{< ref "locators.md" >}})を提供します。 
非常に高度なシナリオでロケーターを使用する方法はたくさんあります。 
このドキュメントの目的のために、[Seleniumロケーターテストページ](https://www.selenium.dev/selenium/web/locators_tests/locators.html)を使用します。

## 最初に一致する要素

多くのロケーターは、ページ上の複数の要素と一致します。 
単数の find elementメソッドは、指定されたコンテキスト内で最初に見つかった要素への参照を返します。

### DOM全体の評価

ドライバーインスタンスで要素の検索メソッドが呼び出されると、提供されたロケーターと一致するDOMの最初の要素への参照が返されます。 
この値は保存して、将来の要素アクションに使用できます。 
Seleniumロケーターテストページには、クラス名が `information` の要素が2つあるため、このメソッドは最初のテキスト入力を返します。

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L22-L23">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L7-L8">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L15-L16">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L10-L11" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L9-L10">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L19-L20">}}
  {{< /tab >}}
{{< /tabpane >}}


### DOMのサブセットの評価

DOM全体で一意のロケーターを見つけるのではなく、検索を別の検索された要素のスコープに絞り込むと便利なことがよくあります。

1つの解決策は、目的の要素の祖先を見つけて、そのオブジェクトでfind要素を呼び出すことです。

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L31-L33">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L14-L16">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L25-L27">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L17-L19" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L18-L20">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L27-L29">}}
  {{< /tab >}}
{{< /tabpane >}}

{{% pageinfo color="info" %}}
**Java and C#**<br>
`WebDriver` 、 `WebElement` 、および `ShadowRoot` クラスはすべて、 _ロールベースのインターフェイス_ と見なされる `SearchContext` インターフェイスを実装します。 
ロールベースのインターフェイスを使用すると、特定のドライバーの実装が特定の機能をサポートしているかどうかを判断できます。
これらのインターフェースは明確に定義されており、責任の役割を1つだけ持つように努めています。
{{% /pageinfo %}}

### Evaluating the Shadow DOM

The Shadow DOM is an encapsulated DOM tree hidden inside an element. 
With the release of v96 in Chromium Browsers, Selenium can now allow you to access this tree 
with easy-to-use shadow root methods. NOTE: These methods require Selenium 4.0 or greater.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
{{< tab header="Java" >}}
WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"));
SearchContext shadowRoot = shadowHost.getShadowRoot();
WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#shadow_content"));
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L39-L42">}}
{{< /tab >}}
{{< tab header="CSharp" >}}
var shadowHost = _driver.FindElement(By.CssSelector("#shadow_host"));
var shadowRoot = shadowHost.GetShadowRoot();
var shadowContent = shadowRoot.FindElement(By.CssSelector("#shadow_content"));
{{< /tab >}}
{{< tab header="Ruby" >}}
shadow_host = @driver.find_element(css: '#shadow_host')
shadow_root = shadow_host.shadow_root
shadow_content = shadow_root.find_element(css: '#shadow_content')
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 最適化されたロケーター

ネストされたルックアップは、ブラウザに2つの別々のコマンドを発行する必要があるため、最も効果的なロケーション戦略ではない可能性があります。

パフォーマンスをわずかに向上させるために、CSSまたはXPathのいずれかを使用して、単一のコマンドでこの要素を見つけることができます。 
[推奨されるテストプラクティス]({{< ref "/documentation/test_practices/encouraged" >}})の章で、[ロケーター戦略]({{< ref "/documentation/test_practices/encouraged/locators" >}})の提案を参照してください。

この例では、CSSセレクターを使用します。

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L41-L42">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L22-L23">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L36-L37">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L25-L26" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L28-L29">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L36-L37">}}
  {{< /tab >}}
{{< /tabpane >}}


## 一致するすべての要素

最初の要素だけでなく、ロケーターに一致するすべての要素への参照を取得する必要があるユースケースがいくつかあります。 
複数の要素の検索メソッドは、要素参照のコレクションを返します。 
一致するものがない場合は、空のリストが返されます。 
この場合、すべてのinput要素への参照がコレクションに返されます。

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L50-L51">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L29-L30">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L46-L47">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L32-L33" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L37-L38">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L44-L45">}}
  {{< /tab >}}
{{< /tabpane >}}

### 要素の取得
多くの場合、要素のコレクションを取得しますが、特定の要素を操作したいので、コレクションを繰り返し処理して、
必要な要素を特定する必要があります。

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L61-L64">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L51-L53">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L58-L62">}}
  {{< /tab >}}
   {{< tab header="Ruby" >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L40-L42" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L47-L50">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L53-L56">}}
  {{< /tab >}}
{{< /tabpane >}}

## 要素から要素を検索

これは、親要素のコンテキスト内で一致する子のWebElementのリストを見つけるために利用されます。 
これを実現するために、親WebElementは'findElements'と連鎖して子要素にアクセスします。

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L74-L78">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L61-L64">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L73-L78">}}
  {{< /tab >}}
   {{< tab header="Ruby" >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L48-L51" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L59-L63">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L64-L68">}}
  {{< /tab >}}
{{< /tabpane >}}

## アクティブな要素を取得する

これは、現在のブラウジングコンテキストでフォーカスを持っているDOM要素を追跡（または）検索するために使用されます。

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L88-L89">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L72-L73">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L89-L90">}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
  {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L58-L60" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L72-L73">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L76-L77">}}
  {{< /tab >}}
{{< /tabpane >}}


