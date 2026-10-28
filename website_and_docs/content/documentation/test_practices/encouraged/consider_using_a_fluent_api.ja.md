---
title: "Fluent APIの使用を検討する"
linkTitle: "Fluent APIの使用を検討する"
weight: 10
aliases: [
"/documentation/guidelines/consider_using_a_fluent_api/"
]
---


マーチン・ファウラーは["Fluent API"](//www.martinfowler.com/bliki/FluentInterface.html)という用語を作り出しました。
Seleniumは既に、`FluentWait`クラスでこのようなものを実装しています。
これは、標準の<code>Wait</code>クラスの代替としてのものです。
ページオブジェクトでFluent APIデザインパターンを有効にしてから、次のようなコードスニペットを使用してGoogle検索ページを照会できます。

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/test_practices/FluentApiExample.java#L44-L46" >}}

この流暢な動作を持つGoogleページオブジェクトクラスは次のようになります。

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/test_practices/FluentApiExample.java#L11-L35" >}}
