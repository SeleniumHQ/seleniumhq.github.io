---
title: "Seleniumブラウザー自動化プロジェクト"
linkTitle: "ドキュメント"
cascade:
- type: docs
aliases: ["/documentation/ja/"]
---

Seleniumは、ブラウザーの自動化を可能にし、それを支えるさまざまなツールとライブラリーから成るプロジェクトです。

Seleniumは、ユーザーとブラウザーのやり取りをエミュレートするための拡張機能、ブラウザーの割り当てを拡張するための分散サーバー、そしてすべての主要なブラウザーで
同じコードを使えるようにする[W3C WebDriver仕様](//www.w3.org/TR/webdriver/)の実装のためのインフラストラクチャーを提供します。

このプロジェクトは、何千時間もの時間を費やしてきたボランティアの貢献者によって支えられています。
ソースコードは、誰でも利用し、楽しみ、改善できるよう[自由に公開]({{< ref "copyright.md#license" >}})されています。

Seleniumは、ウェブプラットフォームの自動化について開かれた議論を促進するために、ブラウザーベンダー、エンジニア、愛好家を結び付けています。
このプロジェクトは、コミュニティを育成・支援するために[年次カンファレンス](//seleniumconf.com/)を開催しています。

Seleniumの中核となる[WebDriver]({{< ref "webdriver.md" >}})は、多くのブラウザーで同じように実行できる命令セットを記述するためのインターフェースです。
以下に、最も簡単な例の一つを示します。


{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/hello/HelloSelenium.java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/hello/hello_selenium.py" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/HelloSelenium.cs" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/hello/hello_selenium.rb" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/hello/helloSelenium.js" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/hello/HelloSelenium.kt" >}}
{{< /tab >}}
{{< /tabpane >}}



[概要]({{< ref "overview" >}})を参照して、プロジェクトのさまざまなコンポーネントを確認し、Seleniumが適切なツールであるかどうかを判断してください。

[入門]({{< ref "webdriver/getting_started" >}})に進み、Seleniumのインストール方法と、テスト自動化ツールとして適切に使用する方法を学びましょう。
また、このような単純なテストをスケールさせ、複数のブラウザーと複数のオペレーティングシステムを使用する大規模な分散環境で実行する方法も確認してください。
