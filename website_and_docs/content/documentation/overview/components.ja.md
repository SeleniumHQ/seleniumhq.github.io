---
title: "コンポーネントを理解する"
linkTitle: "コンポーネントを理解する"
weight: 1
aliases: [
"/documentation/ja/webdriver/understanding_the_components/",
"/ja/documentation/webdriver/understanding_the_components"
]
---

WebDriverを使ってテストスイートを構築するには、多くのコンポーネントを理解し、効率的に使用する必要があります。
ソフトウェア全般と同様に、人によっては同じ概念に異なる用語を使用します。
ここでは、用語を以下の意味で使用します。

### 用語

* **API:** アプリケーションプログラミングインターフェイス。WebDriverを操作するために使用する「コマンド」の集合です。
* **ライブラリ:** APIと、それらを実装するために必要なコードを含むコードモジュール。
ライブラリは言語バインディングごとに用意されています。たとえば、Java用の .jar ファイルや .NET用の .dll ファイルなどです。
* **ドライバー:** 実際のブラウザを制御する役割を担います。
ほとんどのドライバーはブラウザベンダー自身によって作成されます。ドライバーは通常、ブラウザが稼働するシステム上で実行される実行可能モジュールであり、テストスイートを実行するシステムとは別のものです。（ただし、両者が同じシステムの場合もあります。）_注: ドライバーをプロキシと呼ぶ人もいます。_
* **フレームワーク:** WebDriverスイートを補助するために使用する追加ライブラリ。これらのフレームワークは、JUnitやNUnitなどのテストフレームワークである場合があります。また、CucumberやRobot Frameworkといった自然言語の機能をサポートするフレームワークである場合もあります。テスト対象システムの操作や構成、データ作成、テストオラクルなどのために、フレームワークを作成して使用することもあります。

### 構成要素
WebDriverは最小構成では、ドライバーを介してブラウザと通信します。
通信は双方向です。WebDriverはドライバーを介してブラウザにコマンドを渡し、同じ経路で情報を受け取ります。

{{< figure src="/images/documentation/webdriver/basic_comms.png" class="img-responsive text-center" alt="基本通信">}}

ドライバーは、GoogleのChrome/Chromium用のChromeDriverや、MozillaのFirefox用のGeckoDriverなど、ブラウザ固有のものです。
ドライバーはブラウザと同じシステム上で実行されます。これは、テスト自体を実行するシステムと同一の場合も、異なる場合もあります。

この単純な例では、_直接_ 通信を行います。ブラウザとの通信は、Selenium ServerまたはRemoteWebDriverを介した _リモート_ 通信にすることもできます。RemoteWebDriverは、ドライバーおよびブラウザと同じシステム上で実行されます。

{{< figure src="/images/documentation/webdriver/remote_comms.png" class="img-responsive text-center" alt="リモート通信">}}

リモート通信は、Selenium ServerまたはSelenium Gridを使用して行うこともできます。どちらもホストシステム上のドライバーと通信します。

{{< figure src="/images/documentation/webdriver/remote_comms_server.png" class="img-responsive text-center" alt="Selenium Gridを用いたリモート通信">}}

## フレームワークの位置づけ

WebDriverには、上記のいずれかの方法でブラウザと通信するという、ただ1つの役割があります。WebDriverはテストについて何も知りません。値を比較する方法も、成否をアサートする方法も、レポートや Given/When/Then の文法も知りません。

ここで、さまざまなフレームワークが登場します。
最低限、使用する言語バインディングに対応したテストフレームワークが必要です。たとえば、.NET用のNUnit、Java用のJUnit、Ruby用のRSpecなどです。

テストフレームワークは、WebDriverとテストに関連する手順の実行を担当します。
したがって、次の図のような構成だと考えることができます。

{{< figure src="/images/documentation/webdriver/test_framework.png" class="img-responsive text-center" alt="テストフレームワーク">}}

上図では、Cucumberなどの自然言語を扱うフレームワーク/ツールが、テストフレームワークのボックスの一部として存在する場合があります。または、独自の実装の中でテストフレームワーク全体をラップする場合もあります。
