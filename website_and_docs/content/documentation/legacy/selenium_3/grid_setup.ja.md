---
title: "独自のグリッドを設定する"
linkTitle: "独自のグリッドを設定する"
weight: 4
description: >
    Quick start guide for setting up Grid 3.
aliases: [
"/documentation/ja/grid/grid_3/setting_up_your_own_grid/",
"/ja/documentation/legacy/grid_3/setting_up_your_own_grid/"
]
---


Selenium Gridを使用するには、ノード用の独自のインフラストラクチャを維持する必要があります。
これは面倒で時間のかかる作業になる可能性があるため、多くの組織はこのインフラストラクチャを提供するためにAmazon EC2やGoogle ComputeなどのIaaSプロバイダーを使用しています。

他の選択肢として、クラウドのサービスとしてSelenium Gridを提供するSauce LabsやTesting Botなどのプロバイダーの使うこともできます。
独自のハードウェアでノードを実行することも確かに可能です。
この章では、独自のノードインフラストラクチャを備えた独自のグリッドを実行するオプションについて詳しく説明します。

## クイックスタート

この例では、Selenium 2グリッドハブを起動し、WebDriverノードとSelenium 1 RCレガシーノードの両方を登録する方法を示します。 また、Javaからグリッドを呼び出す方法も示します。
ここでは、ハブとノードが同じマシンで実行されていますが、もちろん、selenium-server-standaloneを複数のマシンにコピーできます。

`selenium-server-standalone`パッケージには、グリッドの実行に必要なハブ、WebDriver、およびレガシーRCが含まれています。 _ant_ はもう必要ありません。
`selenium-server-standalone.jar`は [https://selenium.dev/downloads/](https://selenium.dev/downloads/) からダウンロードできます。

### ステップ1：ハブを開始する

ハブは、テストリクエストを受信し、それらを適切なノードに配布する中心点です。
配布は機能ベースで行われます。
つまり、一連の機能を必要とするテストは、その機能セットまたは機能のサブセットを提供するノードにのみ配布されます。

テストのDesiredCapabilitiesは、 _任意の_ を意味するため、ハブはDesiredCapabilitiesの設定に完全に一致するノードを見つけることを保証できません。

コマンドプロンプトを開き、`selenium-server-standalone.jar`ファイルをコピーしたディレクトリに移動します。 ハブを起動するには、`-role hub`フラグをスタンドアロンサーバーに渡します。

```shell
java -jar selenium-server-standalone.jar -role hub
```

ハブはデフォルトでポート4444をリッスンします。 ブラウザーウィンドウを開いて [http://localhost:4444/grid/console](http://localhost:4444/grid/console) に移動すると、ハブのステータスを表示できます。

デフォルトのポートを変更するには、コマンドを実行するときにリッスンするポートを表す整数を持つオプションの `-port` フラグを追加できます。
また、JSON構成ファイル（以下を参照）に表示される他のすべてのオプションは、可能なコマンドラインフラグです。

確かに上記の簡単なコマンドだけでうまくいくことができますが、より高度な構成が必要な場合は、JSON形式の構成ファイルを指定して、開始時にハブを構成することもできます。
JSON形式の構成ファイルを指定して開始時にハブを構成する方法は以下のとおりです。

```shell
java -jar selenium-server-standalone.jar -role hub -hubConfig hubConfig.json -debug
```

以下に、 `hubConfig.json` ファイルの例を示します。
ステップ2でノード構成ファイルを提供する方法について詳しく説明します。

```json
{
  "_comment" : "Configuration for Hub - hubConfig.json",
  "host": ip,
  "maxSession": 5,
  "port": 4444,
  "cleanupCycle": 5000,
  "timeout": 300000,
  "newSessionWaitTimeout": -1,
  "servlets": [],
  "prioritizer": null,
  "capabilityMatcher": "org.openqa.grid.internal.utils.DefaultCapabilityMatcher",
  "throwOnCapabilityNotPresent": true,
  "nodePolling": 180000,
  "platform": "WINDOWS"}
```


### ステップ2：ノードを起動する

新しいWebDriver機能を備えたグリッドを実行するか、Selenium 1 RC機能を備えたグリッドを実行するか、または両方を同時に実行するかに関係なく、同じ `selenium-server-standalone.jar` ファイルを使用してノードを起動します。

```shell
java -jar selenium-server-standalone.jar -role node -hub http://localhost:4444
```

`-port` フラグでポートが指定されていない場合、空いているポートが選択されます。
1台のマシンで複数のノードを実行できますが、実行する場合は、システムメモリリソースとスクリーンショットの問題をテストで確認する必要があることに注意する必要があります。

#### オプションを使用したノード構成

前述のように、下位互換性のために、"wd"および"rc"ロールは"node"ロールの有効なサブセットのままです。
ただし、これらのロールは、対応するAPIへのリモート接続の種類を制限し、"node"はRCとWebDriverの両方のリモート接続を許可します。

コマンドラインでもJVMプロパティを（ _-jar引数の前に_ `-D`フラグを使用して）渡すと、これらが取得され、ノードに伝播されます。

`-Dwebdriver.chrome.driver=chromedriver.exe`


#### JSONを使用したノード構成

JSON設定ファイルで構成されたグリッドノードを起動することもできます。

```shell
java -Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone.jar -role node -nodeConfig node1Config.json
```

そして、これは `nodeConfig.json` ファイルの例です。

```json
{
  "capabilities": [
    {
      "browserName": "firefox",
      "acceptSslCerts": true,
      "javascriptEnabled": true,
      "takesScreenshot": false,
      "firefox_profile": "",
      "browser-version": "27",
      "platform": "WINDOWS",
      "maxInstances": 5,
      "firefox_binary": "",
      "cleanSession": true
    },
    {
      "browserName": "chrome",
      "maxInstances": 5,
      "platform": "WINDOWS",
      "webdriver.chrome.driver": "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe"
    },
    {
      "browserName": "internet explorer",
      "maxInstances": 1,
      "platform": "WINDOWS",
      "webdriver.ie.driver": "C:/Program Files (x86)/Internet Explorer/iexplore.exe"
    }
  ],
  "configuration": {
    "_comment" : "Configuration for Node",
    "cleanUpCycle": 2000,
    "timeout": 30000,
    "proxy": "org.openqa.grid.selenium.proxy.WebDriverRemoteProxy",
    "port": 5555,
    "host": ip,
    "register": true,
    "hubPort": 4444,
    "maxSession": 5
  }
}
```

`-host` フラグに関する注意

ハブとノードの両方で、`-host`フラグが指定されていない場合、デフォルトで`0.0.0.0`を使用します。
これにより、マシンのすべてのパブリック（非ループバック）IPv4インターフェイスにバインドされます。
特別なネットワーク構成または追加のネットワークインターフェイスを作成するコンポーネントがある場合は、`-host`フラグにハブ/ノードが別のマシンから到達できる値を設定することをお勧めします。

#### ポートを指定する

ハブで使用されるデフォルトのTCP / IPポートは4444です。
ポートを変更する必要がある場合は、上記の構成を使用してください。

## トラブルシューティング

### ログファイルを使用する
高度なトラブルシューティングのために、システムメッセージを記録するログファイルを指定できます。 -log引数を使用してSelenium GRIDハブまたはノードを起動します。
以下の例をご覧ください。

```shell
java -jar selenium-server-standalone.jar -role hub -log log.txt
```

お気に入りのテキストエディターを使用してログファイル（上記の例ではlog.txt）を開き、問題が発生した場合に"エラー"ログを見つけます。

### `-debug` 引数を使用する

-debug引数を使用して、デバッグログをコンソールに出力することもできます。
`-debug` 引数を使用してSeleniumグリッドハブまたはノードを起動します。
以下の例をご覧ください。

```shell
java -jar selenium-server-standalone.jar -role hub -debug
```

## 警告

Selenium Gridは、適切なファイアウォールアクセス許可を使用して外部アクセスから保護する必要があります。

グリッドを保護しないと、次の1つ以上が発生する可能性があります。

* グリッドインフラストラクチャへのオープンアクセスを提供します。
* サードパーティが内部Webアプリケーションおよびファイルにアクセスすることを許可します。
* サードパーティにカスタムバイナリの実行を許可します。

[Detectify](//labs.detectify.com) に関するこのブログ投稿をご覧ください。
これは、公開されたグリッドが悪用される可能性のある概要を示しています。 [Don't Leave your Grid Wide Open](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/)

## Docker Selenium
[Docker](//www.docker.com/) は、コンテナと呼ばれる単位でSelenium Gridインフラストラクチャをプロビジョニングおよびスケーリングする便利な方法を提供します。
コンテナは、さまざまなマシンで信頼性と再現性のある方法で、すべての依存関係を含む目的のアプリケーションを実行するために必要なすべてを含むソフトウェアの標準化されたユニットです。

Seleniumプロジェクトは、ダウンロードして実行して作業用グリッドを迅速に起動および実行できる一連のDockerイメージを保持しています。 ノードはFirefoxとChromeの両方で使用できます。
グリッドのプロビジョニング方法の詳細は、 [Docker Selenium](//github.com/SeleniumHQ/docker-selenium) リポジトリ内にあります。

### 前提条件
グリッドを実行するための唯一の要件は、Dockerをインストールして動作させることです。
[Dockerのインストール](//www.docker.com/products/docker-desktop).
