---
title: "入門"
linkTitle: "入門"
weight: 2
description: >
  If you are new to Selenium, we have a few resources that can help you get up to speed right away.
aliases: [
"/documentation/ja/getting_started/", 
"/documentation/ja/getting_started/quick/",
"/documentation/ja/selenium_installation/",
"/documentation/ja/getting_started_with_webdriver/",
"/ja/documentation/getting_started/"
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Japanese. Do you speak Japanese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Seleniumは市場で主要なブラウザの全てを _WebDriver_ を使うことでサポートしています。
WebDriverとはAPI群とプロトコルです。これらはウェブブラウザの動作をコントロールするための言語中立なインターフェイスを定義しています。
それぞれのブラウザは特定のWebDriverの実装を持っており、これらは *driver* と呼ばれます。
driverはブラウザに委譲する責務を持つコンポーネントであり、Seleniumとブラウザ間の通信を処理します。

この分離は、ブラウザベンダーに自分たちのブラウザでの実装の責任を持たせるための意図的な努力のひとつです。
Seleniumは可能な場合これらのサードパーティ製のdriverを使いますが、それが現実的でない場合のためにプロジェクトでメンテナンスしているdriverも提供しています。

Seleniumフレームワークはこれら全ての要素をユーザ向けのインターフェイスを通して結びつけます。このインターフェイスは異なるブラウザバックエンドを透過的に使えるようにし、クロスブラウザ・クロスプラットフォームの自動化を可能にします。

Seleniumのセットアップは他の商用ツールと少し違います。自動化プロジェクトでSeleniumを使うためには、選択した言語の言語バインディングライブラリをインストールする必要があります。加えて、自動化でテストを実行したいブラウザのWebDriverバイナリも必要となります。

Installing Selenium can be divided in three steps:

1. [Installing the Selenium library]({{< ref "install_selenium_library.md" >}}) for your desired programming language
2. [Set up the browser driver]({{< ref "install_drivers.md" >}}) to automate your browser (e.g. GeckoDriver for Firefox)
3. (Optional) Set up and configure [Selenium Grid]({{< ref "/grid.md" >}}) if you want to scale up your tests

If you wish to start with a low-code/record and playback tool, please check 
[Selenium IDE](https://selenium.dev/selenium-ide)

After completing the setup, you can run the code snippet shown at the 
[starting page](/ja/documentation) in our docs. Then head to the 
[WebDriver]({{< ref "/webdriver.md" >}}) section to learn more about
browser automation with Selenium.
