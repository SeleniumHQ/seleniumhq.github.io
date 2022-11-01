---
title: "Seleniumブラウザー自動化プロジェクト"
linkTitle: "ドキュメント"
cascade:
- type: docs
aliases: ["/documentation/ja/"]
---

Seleniumはブラウザー自動化を可能にし、それを支えるツール群とライブラリー群プロジェクトです。

ユーザーとブラウザーのやり取りのエミュレーション、ブラウザーの割当を増強したり縮減する分散型サーバー、そしてすべてのメジャーなブラウザー用に置換可能なコードの実装を可能にする[W3C WebDriver 仕様](//www.w3.org/TR/webdriver/)インフラの提供します。

このプロジェクトは多くの有志貢献者の何千時間に及ぶ個々の時間を費やした事とソースコード[自由に利用可能]({{< ref "/copyright.md#license" >}})を誰にでも利用、楽しめ、そして改良できることによって実現しました。

Seleniumはウェブプラットフォームの自動化のより開かれた議論をするためブラウザーベンダー、エンジニア、愛好家をまとめます。このプロジェクトはコミュニティーを導きと育成のために[年次カンファレンス](//seleniumconf.com/)開催します。

Seleniumの中核は[WebDriver]({{< ref "/webdriver.md" >}})であり、様々なブラウザーを変えてインストラクション集を実行できるインターフェースです。これは作りえる一番基本的な
インストラクションの一つです:


{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/hello/HelloSelenium.java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/hello/hello_selenium.py" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Hello/HelloSelenium.cs" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/hello/hello_selenium_spec.rb" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/hello/helloSelenium.js" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/hello/HelloSelenium.kt" >}}
{{< /tab >}}
{{< /tabpane >}}



[概要]({{< ref "overview" >}})を参照して、さまざまなプロジェクトコンポーネントを確認し、
Seleniumが適切なツールであるかどうかを判断してください。

[入門]({{< ref "webdriver/getting_started" >}})に進んで、
Seleniumをインストールし、テスト自動化ツールとして正常に使用する方法を理解し、
このような単純なテストをスケーリングして、複数のブラウザー、
複数の異なるオペレーティングシステムの大規模な分散環境で実行する必要があります。



