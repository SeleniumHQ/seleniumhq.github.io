---
title: "デザインパターンと開発戦略"
linkTitle: "デザイン戦略"
weight: 1
---
(以前の場所: https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests)

## 概要
時間の経過とともに、プロジェクトは多数のテストが積み上がる傾向があります。 
テストの総数が増えると、コードベースに変更を加えることが難しくなります。
アプリケーションが正常に機能していても、1回の"単純な"変更で多数のテストが失敗する可能性があります。 
これらの問題が避けられない場合もありますが、問題が発生した場合は、できるだけ早く稼働を再開する必要があります。 
次のデザインパターンと戦略は、テストの作成と保守を容易にするためにWebDriverで以前に使用されています。 
それらもあなたにとって役に立つかもしれません。

[DomainDrivenDesign]({{< ref "encouraged/domain_specific_language.md" >}})：アプリのエンドユーザーの言語でテストを表現します。   
[PageObjects]({{< ref "encouraged/page_object_models.md" >}})：WebアプリのUIの単純な抽象化  
LoadableComponent：PageObjectsをコンポーネントとしてモデリングします。   
BotStyleTests：PageObjectsが推奨するオブジェクトベースのアプローチではなく、コマンドベースのアプローチを使用してテストを自動化します。  

## ロード可能なコンポーネント

### それは何ですか？

LoadableComponentは、PageObjectsの作成の負担を軽減することを目的としたベースクラスです。 
これは、ページがロードされることを保証する標準的な方法を提供し、ページのロードの失敗のデバッグを容易にするフックを提供することによってこれを行います。 
これを使用して、テストの定型コードの量を減らすことができます。これにより、テストの保守が面倒になります。

現在、Selenium 2の一部として出荷されるJavaの実装がありますが、使用されるアプローチは、どの言語でも実装できるほど単純です。

### 簡単な使用方法

モデル化するUIの例として、[新しいissue](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&projects=&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+)のページをご覧ください。 
テスト作成者の観点から、これは新しい問題を提出できるサービスを提供します。 
基本的なページオブジェクトは次のようになります。

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L14-L76" >}}

これをLoadableComponentに変換するには、これを基本型として設定するだけです。

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L161" >}}

この署名は少し変わっているように見えますが、それは、このクラスがEditIssueページをロードするLoadableComponentを表すことを意味します。

このベースクラスを拡張することにより、2つの新しいメソッドを実装する必要があります。

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L181-L190" >}}

`load` メソッドはページに移動するために使用され、　`isLoaded` メソッドは正しいページにいるかどうかを判断するために使用されます。 
このメソッドはブール値を返す必要があるように見えますが、代わりにJUnitのAssertクラスを使用して一連のアサーションを実行します。 
アサーションは好きなだけ少なくても多くてもかまいません。 
これらのアサーションを使用することで、クラスのユーザーにテストのデバッグに使用できる明確な情報を提供することができます。

少し手直しすると、PageObjectは次のようになります。

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L161-L236" >}}

それは私たちをあまり信じられなかったようですよね？ 
これまでに行ったことの1つは、ページに移動する方法に関する情報をページ自体にカプセル化することです。
つまり、この情報はコードベース全体に散らばっていません。 
これは、テストで下記を実行できることも意味します。

```java
EditIssue page = new EditIssue(driver).get();
```

この呼び出しにより、ドライバーは必要に応じてページに移動します。

### ネストされたコンポーネント

LoadableComponentsは、他のLoadableComponentsと組み合わせて使用すると、より便利になります。 
この例を使用すると、 "edit issue" ページをプロジェクトのWebサイト内のコンポーネントとして表示できます（結局のところ、そのサイトのタブからアクセスします）。 
また、issue を報告するにはログインする必要があります。 
これをネストされたコンポーネントのツリーとしてモデル化できます。

```
 + ProjectPage
 +---+ SecuredPage
     +---+ EditIssue
```

これはコードではどのように見えますか？ 
まず、各論理コンポーネントには独自のクラスがあります。 
それぞれの "load" メソッドは、親クラスを "get" します。 
上記のEditIssueクラスに加えて、最終結果は次のようになります。

ProjectPage.java:

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L97-L118" >}}

and SecuredPage.java:

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L120-L159" >}}

EditIssueの "load" メソッドは次のようになります。

```java
  @Override
  protected void load() {
    securedPage.get();

    driver.get("https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&projects=&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+");
  }
```

これは、コンポーネントがすべて相互に "ネストされている" ことを示しています。 
EditIssueで `get()` を呼び出すと、そのすべての依存関係も読み込まれます。 
使用例：

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L266-L289" >}}

テストで [Guiceberry](https://github.com/zorzella/guiceberry) などのライブラリを使用している場合は、PageObjectsの設定の前文を省略して、わかりやすく読みやすいテストを作成できます。

## ボットパターン

(以前の場所: https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests)

PageObjectsは、テストでの重複を減らすための便利な方法ですが、チームが快適にフォローできるパターンであるとは限りません。 
別のアプローチは、より "コマンドのような" スタイルのテストに従うことです。

"ボット" は、生のSeleniumAPIに対するアクション指向の抽象化です。 
つまり、コマンドがアプリに対して正しいことをしていないことがわかった場合、コマンドを簡単に変更できます。 
例として：

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L238-L264" >}}

これらの抽象化が構築され、テストでの重複が特定されると、ボットの上にPageObjectsを階層化することができます。

## Example

{{< tabpane text=true >}}
{{< tab header="Python" >}}

**Action Bot**、**Loadable Component**、および **Page Object** を実装した `python + pytest + selenium` の例です。

A `pytest` fixture `chrome_driver`.

{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L6-L26" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


"**Action Bot**" implementation.

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L28-L65" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


"**Loadable Component** definition.

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L67-L80" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


"**Loadable Component** and **Page Object**" implementation. 

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L82-L172" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

Test cases implementation.

{{< tabpane text=true >}}
{{< tab header="Python" >}}

Test cases implementation with `pytest`.

{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L174-L240" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
