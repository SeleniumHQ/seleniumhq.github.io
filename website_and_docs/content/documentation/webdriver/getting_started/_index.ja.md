---
title: "入門"
linkTitle: "入門"
weight: 2
needsTranslation: true
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

Seleniumのセットアップは、他の商用ツールのセットアップとはかなり異なります。
Seleniumコードの記述を開始する前に、次のことを行う必要があります
選択した言語、つまりブラウザーの言語バインディングライブラリをインストールします
使用したい、そのブラウザのドライバ。

***以下のリンクをたどって、Selenium WebDriverを起動してください。***

ローコード/録音および再生ツールから始めたい場合は、確認してください
[Selenium IDE](https://selenium.dev/selenium-ide)

物事がうまくいったら、テストをスケールアップしたい場合は、[Selenium Grid]({{< ref "/documentation/grid" >}}).
