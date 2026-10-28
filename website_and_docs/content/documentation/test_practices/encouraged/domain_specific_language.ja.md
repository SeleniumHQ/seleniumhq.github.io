---
title: "ドメイン固有言語（DSL）"
linkTitle: "ドメイン固有言語（DSL）"
weight: 4
aliases: [
"/documentation/guidelines/domain_specific_language/"
]
---

ドメイン固有言語（DSL）は、問題を解決するための表現手段をユーザーに提供するシステムです。
それによって、ユーザーは、プログラマーの言葉でなく、自分の言葉でシステムとやりとりすることができます。

通常、ユーザーはサイトの外観を気にしません。
装飾、アニメーション、グラフィックスは気にしません。
彼らはあなたのシステムを使用して、新しい従業員を最小限の難しさでプロセスに押し込みたいと考えています。
彼らはアラスカへの旅行を予約したい。
ユニコーンを設定して割引価格で購入したいのです。
テスターとしてのあなたの仕事は、この考え方を"とらえる"ことにできるだけ近づくことです。
それを念頭に置いて、テストスクリプト（ユーザーの唯一のプレリリースの代理人）がユーザーを"代弁し"、表現するように、作業中のアプリケーションの"モデリング"に取り掛かります。

Seleniumでは、DSLは通常、APIをシンプルで読みやすいように記述したメソッドで表されます。
開発者と利害関係者（ユーザー、製品所有者、ビジネスインテリジェンススペシャリストなど）との伝達が可能になります。

## 利点

* **Readable:** ビジネス関係者はそれを理解できます。
* **Writable:** 書きやすく、不要な重複を避けます。
* **Extensible:** 機能は（合理的に）契約と既存の機能を壊すことなく追加できます。
* **Maintainable:** 実装の詳細をテストケースから除外することにより、AUT* の変更に対して十分に隔離されます。

## Java

Javaの妥当なDSLメソッドの例を次に示します。
簡潔にするために、`driver`オブジェクトが事前に定義されており、メソッドで使用可能であることを前提としています。

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/test_practices/DomainSpecificLanguageExample.java#L21-L43" >}}

このメソッドは、テストコードから入力フィールド、ボタン、クリック、さらにはページの概念を完全に抽象化します。
このアプローチを使用すると、テスターはこのメソッドを呼び出すだけで済みます。
これにより、メンテナンスの利点が得られます。
ログインフィールドが変更された場合、テストではなく、このメソッドを変更するだけで済みます。

```java
public void loginTest() {
    loginAsUser("cbrown", "cl0wn3");

    // Now that we're logged in, do some other stuff--since we used a DSL to support
    // our testers, it's as easy as choosing from available methods.
    do.something();
    do.somethingElse();
    Assert.assertTrue("Something should have been done!", something.wasDone());

    // Note that we still haven't referred to a button or web control anywhere in this
    // script...
}
```

繰り返しになります。
主な目標の1つは、 テストが **UIの問題ではなく、手元の問題** に対処できるAPIを作成することです。
UIはユーザーにとって二次的な関心事です。ユーザーはUIを気にせず、ただ仕事をやりたいだけです。
テストスクリプトは、ユーザーがやりたいことと知りたいことの長々としたリストのように読む必要があります。
テストでは、UIがどのようにそれを実行するように要求するかについて、気にするべきではありません。

***AUT**: Application under test（テスト対象アプリケーション）

