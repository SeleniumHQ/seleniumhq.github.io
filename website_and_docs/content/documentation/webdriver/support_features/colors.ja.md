---
title: "色を扱う"
linkTitle: "色を扱う"
weight: 3
aliases: [
"/documentation/support_packages/working_with_colours/",
"/documentation/webdriver/additional_features/working_with_colours/"
]
---


テストの一部として何かの色を検証したい場合があります。
問題は、ウェブ上の色の定義が一定ではないことです。
色のHEX表現を色のRGB表現と比較する簡単な方法、または色のRGBA表現を色のHSLA表現と比較する簡単な方法があったらいいのではないでしょうか？

心配しないでください。解決策があります。:  _Color_ クラスです！

まず、クラスをインポートする必要があります。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L7" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L2" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L8" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L7" >}}
  {{< /tab >}}
{{< /tabpane >}}

これで、カラーオブジェクトの作成を開始できます。
すべての色オブジェクトは、色の文字列表現から作成する必要があります。
サポートされている色表現は、以下のとおりです。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L13-L19" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L6-L12" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L11-L17" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L13-L19" >}}
  {{< /tab >}}
{{< /tabpane >}}

Colorクラスは、 [http://www.w3.org/TR/css3-color/#html4](//www.w3.org/TR/css3-color/#html4) で指定されているすべての基本色定義もサポートしています。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L32-L34" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L24-L26" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L32-L34" >}}
  {{< /tab >}}
{{< /tabpane >}}

要素に色が設定されていない場合、ブラウザは "透明" の色の値を返すことがあります。
Colorクラスもこれをサポートしています。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L43" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L34" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L43" >}}
  {{< /tab >}}
{{< /tabpane >}}

レスポンスが正しく解析され、有効なColorオブジェクトに変換されることを認識して、要素を安全にクエリしてその色/背景色を取得できるようになりました。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L54-L55" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L43-L44" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L32-L33" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L53-L54" >}}
  {{< /tab >}}
{{< /tabpane >}}

そして、色オブジェクトを直接比較できます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L59" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L48" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L37" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L58" >}}
  {{< /tab >}}
{{< /tabpane >}}

または、色を次の形式のいずれかに変換し、静的に検証することができます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L61-L63" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L50-L52" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L39-L41" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L60-L62" >}}
  {{< /tab >}}
{{< /tabpane >}}

色はもはや問題ではありません。
