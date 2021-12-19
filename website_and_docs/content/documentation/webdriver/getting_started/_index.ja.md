---
title: "入門"
linkTitle: "入門"
weight: 2
description: >
  Seleniumを初めて使用する場合は、すぐに習得するのに役立つリソースがいくつかあります。
aliases: [
"/documentation/ja/getting_started/", 
"/documentation/ja/getting_started/quick/",
"/documentation/ja/selenium_installation/",
"/documentation/ja/getting_started_with_webdriver/",
"/ja/documentation/getting_started/"
]
---

Seleniumは市場で主要なブラウザの全てを _WebDriver_ を使うことでサポートしています。
WebDriverとはAPI群とプロトコルです。これらはウェブブラウザの動作をコントロールするための言語中立なインターフェイスを定義しています。
それぞれのブラウザは特定のWebDriverの実装を持っており、これらは *driver* と呼ばれます。
driverはブラウザに委譲する責務を持つコンポーネントであり、Seleniumとブラウザ間の通信を処理します。

この分離は、ブラウザベンダーに自分たちのブラウザでの実装の責任を持たせるための意図的な努力のひとつです。
Seleniumは可能な場合これらのサードパーティ製のdriverを使いますが、それが現実的でない場合のためにプロジェクトでメンテナンスしているdriverも提供しています。

Seleniumフレームワークはこれら全ての要素をユーザ向けのインターフェイスを通して結びつけます。このインターフェイスは異なるブラウザバックエンドを透過的に使えるようにし、クロスブラウザ・クロスプラットフォームの自動化を可能にします。

Seleniumのセットアップは他の商用ツールと少し違います。自動化プロジェクトでSeleniumを使うためには、選択した言語の言語バインディングライブラリをインストールする必要があります。加えて、自動化でテストを実行したいブラウザのWebDriverバイナリも必要となります。

Seleniumのインストールは、次の3つのステップに分類することができます。

1. 希望するプログラミング言語の[Seleniumライブラリをインストール]({{< ref "install_selenium_library.md" >}})する。
2. ブラウザを自動化するように[ブラウザードライバを設定]({{< ref "install_drivers.md" >}})する。（例：Firefox用のGeckoDriver）
3. （オプション）テストをスケールアップする場合は、[Selenium Grid]({{< ref "/grid.md" >}})をセットアップして構成する。

ローコード/記録および再生ツールから始めたい場合は、[Selenium IDE](https://selenium.dev/selenium-ide) をチェックしてください。

セットアップが完了したら、ドキュメントの[トップページ](/ja/documentation)に表示されているコードスニペットを実行できます。 
次に、[WebDriver]({{< ref "/webdriver.md" >}})の章に移動して、Seleniumを使用したブラウザーの自動化について詳しく学びます。
