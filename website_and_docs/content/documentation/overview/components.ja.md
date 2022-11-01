---
title: "コンポーネントを理解する"
linkTitle: "コンポーネントを理解する"
weight: 1
aliases: [
"/documentation/ja/webdriver/understanding_the_components/",
"/ja/documentation/webdriver/understanding_the_components"
]
---

WebDriverを使ってテストスイートを構築するには、多くの異なるコンポーネントを理解し、効率的に使用する必要があります。
ソフトウェアのすべてがそうであるように、人によっては同じ概念に異なる用語を使用します。
以下は、本説明での用語の使用方法の内訳です。

### 用語

* **API:** アプリケーション プログラミング インターフェイス。これはWebDriverを操作するために使用する"コマンド"をまとめたものです。
* **ライブラリ:** APIとそれらを実装する必要なコードを含むコードモジュール。
ライブラリは各言語バインディング向けのものです。例 .jar
files for Java, .dll files for .NET, など.
* **ドライバー:** 実際のブラウザを制御します。
ほとんどのドライバーはブラウザベンダー自身が作成します。ドライバーは一般的にブラウザ自体を備えたシステムで実行される実行可能モジュールであり、テストスイートを実行するシステムにはありません。（ただし、それらは同じシステムであっても構いません。）_注: 一部の人々はドライバーをプロキシと呼んでいます。_
* **フレームワーク:** WebDriverスイートのサポートとして使用する追加ライブラリ。これらのフレームワークは、JUnitやNUnitなどのテストフレームワークです。また、CucumberまたはRobotiumといった自然言語機能をサポートするフレームワークでもあります。フレームワークは、テスト対象のシステムの操作や構成、データ作成、テストオラクルなどに記述、利用されます。

### 部品構成
最低限、WebDriverはドライバーを経由してブラウザーと通信します。
コミュニケーションは双方向です：WebDriverは、ドライバーを経由してブラウザーにコマンドを渡し、同じルートを経由して情報を受け取ります。

{{< figure src="/images/documentation/webdriver/basic_comms.png" class="img-responsive text-center" alt="基本通信">}}

ドライバーは、ChromeDriver for GoogleのChrome/Chromium、MozillaのFirefox用GeckoDriverなどブラウザー固有のものです。
ドライバーはブラウザと同じシステムで動きます。これは、テスト自体を実行するところが同じシステムである場合とそうでない場合があります。

上記の簡単な例は _直接_ 通信です。ブラウザへのコミュニケーションは、Selenium ServerまたはRemoteWebDriverを経由した _リモート_ 通信もできます。RemoteWebDriverは、ドライバーおよびブラウザと同じシステムで実行されます。

{{< figure src="/images/documentation/webdriver/remote_comms.png" class="img-responsive text-center" alt="リモート通信">}}

リモート通信は、ホストシステム上のドライバーと順に通信するSelenium ServerまたはSelenium Gridを使用して行うこともできます。

{{< figure src="/images/documentation/webdriver/remote_comms_server.png" class="img-responsive text-center" alt="SeleniumGridを用いたリモート通信">}}

## どのフレームワークに適しているか

WebDriverには1つのジョブしかありません:　上記の任意のメソッドを経由してブラウザと通信します。WebDriverはテストに関することを知りません:　WebDriverは物事を比較する方法、成功または失敗を確認する方法を知りません、そして、レポートや Given/When/Then 文法に関しても確実に知りません。

ここで、さまざまなフレームワークが登場します。
最低限必要なのは言語バインディングに一致するテストフレームワーク、例えば NUnit for .NET, JUnitfor Java, RSpec for Ruby などです。

テストフレームワークは、WebDriverおよびテストの関連手順の実行を担当します。
それは下記図に似ていると考えることができます。

{{< figure src="/images/documentation/webdriver/test_framework.png" class="img-responsive text-center" alt="テストフレームワーク">}}

上図でCucumberなどの自然言語のフレームワーク/ツールがテストフレームワークボックスの一部として存在する場合があります、またはテストフレームワークを独自の実装で完全に密閉する場合があります。
