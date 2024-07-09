---
title: "Gridを始める"
linkTitle: "Gridを始める"
weight: 2
needsTranslation: true
description: >
  Selenium Gridの導入方法
aliases: [
"/documentation/ja/grid/grid_4/setting_up_your_own_grid/",
"/ja/documentation/grid/setting_up_your_own_grid/"
]
---

## クイックスタート

1. 事前条件

   - Java 11 もしくはそれ以上がインストールされていること
   - ブラウザーがインストールされていること
   - ブラウザードライバー
     - [Selenium Manager]({{< ref "../selenium_manager/" >}}) will configure the drivers automatically if you add `--selenium-manager true`.
     - [`PATH` が通っているインストール済みのもの]({{< ref "../webdriver/troubleshooting/errors/driver_location.md#use-the-path-environment-variable" >}})
   - [最新の](https://github.com/SeleniumHQ/selenium/releases/latest) Selenium Server jar ファイルをダウンロードしていること

1. Grid の起動

   - `java -jar selenium-server-<version>.jar standalone`

1. あなたの WebDriver テストの対象を [http://localhost:4444](http://localhost:4444) に向ける

1. (必要があれば) ブラウザで[http://localhost:4444](http://localhost:4444)を開いて実行中のテストや利用可能な capabilities を確認する。

さらにオプションを知りたい場合は以降のセクションに進んでください。

## Grid コンポーネントロール

Grid は 6 つの異なる[コンポーネント]({{< ref "components.md" >}})で構成され、様々な方法でデプロイすることができます。

必要に応じて、それぞれ個別に起動する（分散）か、ハブ&ノードのグループに分けるか、
全てを一つのマシンで起動する（**スタンドアロン**）かを選べます。

### **スタンドアロン**

**スタンドアロン**は全ての Grid[コンポーネント]({{< ref "components.md" >}})を 1 つに連結します。
**スタンドアロン**モードはシングルプロセスで動き、Grid の全機能を利用することができます。
**スタンドアロン**は単一のマシン上でのみ動かすことができます。

**スタンドアロン**は Selenium Grid を起動する最も簡単な方法でもあります。
デフォルトではサーバーは[http://localhost:4444](http://localhost:4444) で `RemoteWebDriver` リクエストをリッスンします。
サーバーはデフォルトでシステム[パス]({{< ref "../webdriver/troubleshooting/errors/driver_location.md#use-the-path-environment-variable" >}})上の利用可能なドライバーを検出します。

```shell
java -jar selenium-server-<version>.jar standalone
```

**スタンドアロン**で Grid のを起動したら、WebDriver テストの対象を[http://localhost:4444](http://localhost:4444)に向けてください。

**スタンドアロン**の一般的なユースケースは:

- `RemoteWebDriver` を使用したローカルでの開発やデバッグ
- コードをプッシュする前の簡易なテスト実行
- CI/CD 向けの Grid のセットアップ(GitHub Actions, Jenkins など)

### ハブ&ノード

**ハブ&ノード**は最も利用されているロールです。その理由は:

- 様々なマシンを Grid に統合できます
  - 様々な OS やブラウザーバージョンを持つマシンなど
- WebDriver テストのエントリーポイントを持っています
- Grid を停止せずにキャパシティのスケールアップ・ダウンが可能です

#### ハブ

ハブは以下の[コンポーネント]({{< ref "components.md" >}})で構成されています。
ルーター、ディストリビューター、セッションマップ、新規セッションキュー、イベントバス。

```shell
java -jar selenium-server-<version>.jar hub
```

デフォルトでは、サーバーは[http://localhost:4444](http://localhost:4444)にて `RemoteWebDriver` リクエストを待ち受けます。

#### ノード

**ノード**は起動時にシステムの[パス]({{< ref "../webdriver/troubleshooting/errors/driver_location.md#use-the-path-environment-variable" >}})
が通っている利用可能なドライバーを検出します。

次のコマンドは**ノード**が**ハブ**と同じマシン上で動作していることを前提としています。

```shell
java -jar selenium-server-<version>.jar node
```

##### 同一マシン上での複数ノード

ノード 1

```shell
java -jar selenium-server-<version>.jar node --port 5555
```

ノード 2

```shell
java -jar selenium-server-<version>.jar node --port 6666
```

##### 異なるマシンでノードとハブを動かす

**ハブ**と**ノード**は HTTP と[**イベントバス**]({{< ref "components.md#event-bus" >}})を介して通信します (**イベントバス**は**ハブ**の一部として存在します)。
**ノード**は**イベントバス**を通じてメッセージを送信し、登録処理を開始します。
**ハブ**がメッセージを受け取り、**ノード**の存在を確かめるため HTTP を使って**ノード**にアクセスします。

**ハブ**がデフォルトのポートを使用していれば、 `--hub` フラグで**ノード**を登録することができます。

```shell
java -jar selenium-server-<version>.jar node --hub http://<hub-ip>:4444
```

**ハブ**がデフォルトのポートを使用していない場合、`--publish-events` と `--subscribe-events` のフラグが必要です。

例えば**ハブ**が `8886` `8887` `8888` ポートを利用している場合、

```shell
java -jar selenium-server-<version>.jar hub --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887 --port 8888
```

**ノード**はこれらのポートを登録する際に使用します。

```shell
java -jar selenium-server-<version>.jar node --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887
```

### 分散

分散 Grid を利用すると、各コンポーネントは別々に起動され異なるマシン上で動作します。

{{% alert color="primary" %}}
コンポーネント間で通信をするために、全てのポートを適切に公開することが重要です。
{{% /alert %}}

1. **イベントバス**は Grid コンポーネント間での内部通信を可能にします。

デフォルトポートは `4442`, `4443`, `5557` です。

```shell
java -jar selenium-server-<version>.jar event-bus --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --port 5557
```

2. **新規セッションキュー**は新規セッションリクエストをキューに積み、ディストリビューターがリクエストを取得できるようにします。

デフォルトポートは `5559` です。

```shell
java -jar selenium-server-<version>.jar sessionqueue --port 5559
```

3. **セッションマップ**はセッション ID とそのセッションが実行中の**ノード**のマップを持ちます。

デフォルトの**セッションマップ**のポートは `5556` です。
**セッションマップ**は**イベントバス**と通信します。

```shell
java -jar selenium-server-<version>.jar sessions --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --port 5556
```

4. **ディストリビューター**は**新規セッションキュー**に新規セッションリクエストを問い合わせ、 capabilities がマッチする**ノード**にアサインします。**ノード**は、**ハブ&ノード**構成の Grid における**ハブ**の登録と同じように、**ディストリビューター**に登録します。

デフォルトの**ディストリビューター**のポートは `5553` です。
**ディストリビューター**は**新規セッションキュー**、**セッションマップ**、**イベントバス**、**ノード**と通信します。

```shell
java -jar selenium-server-<version>.jar distributor --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --sessions http://<sessions-ip>:5556 --sessionqueue http://<new-session-queue-ip>:5559 --port 5553 --bind-bus false
```

5. **ルーター**は**新規セッションリクエスト**をキューに、既存セッションのリクエストをそのセッションが実行中の**ノード**に転送します。

デフォルトの**ルーター**のポートは `4444` です。
**ルーター**は**新規セッションキュー**、**セッションマップ**、**ディストリビューター**と通信します。

```shell
java -jar selenium-server-<version>.jar router --sessions http://<sessions-ip>:5556 --distributor http://<distributor-ip>:5553 --sessionqueue http://<new-session-queue-ip>:5559 --port 4444
```

6. ノード

デフォルトの**ノード**のポートは `5555` です。

```shell
java -jar selenium-server-<version>.jar node --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443
```

## テストメタデータ

テストにメタデータを追加して、[GraphQL]({{< ref "advanced_features/graphql_support.md" >}}) 経由で使用するか、Selenium Grid UI 経由でその一部( `se:name` など) を可視化します。

メタデータは capability に`se:`プリフィックスをつけることで追加できます。
Java での簡単な例を紹介します。

```java
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setCapability("browserVersion", "100");
chromeOptions.setCapability("platformName", "Windows");
// Showing a test name instead of the session id in the Grid UI
chromeOptions.setCapability("se:name", "My simple test");
// Other type of metadata can be seen in the Grid UI by clicking on the
// session info or via GraphQL
chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");
WebDriver driver = new RemoteWebDriver(new URL("http://gridUrl:4444"), chromeOptions);
driver.get("http://www.google.com");
driver.quit();
```

## Selenium Grid のクエリ

Grid 起動後、ステータスを問い合わせる方法は、
Grid UI と API 呼び出しの主に 2 通りあります。

Grid UI は、お好みのブラウザで[http://localhost:4444](http://localhost:4444)
にアクセスすることで見られます。

API 呼び出しは[http://localhost:4444/status](http://localhost:4444/status)のエンドポイントか、
[GraphQL]({{< ref "advanced_features/graphql_support.md" >}})が利用できます。

{{% pageinfo color="primary" %}}
このページで紹介するコマンドの例は、わかりやすくするために
コンポーネントがローカルで動作していると仮定しています。
より詳細な例と使用方法は[コンポーネント]({{< ref "components.md" >}})の章を参照してください。
{{% /pageinfo %}}

## Java 11 の HTTP クライアントを利用する {{% badge-version version="4.5" %}}

デフォルトでは Grid は[AsyncHttpClient](https://github.com/AsyncHttpClient/async-http-client)を使用します。
AsyncHttpClient は Netty を使ったオープンソースのライブラリで、非同期での HTTP リクエストを実現します。
さらに WebSocket をサポートするため Grid に適しています。

しかし、AsyncHttpClient は 2021 年からあまり活発にメンテナンスされていません。
そして Java 11+ではビルトインの HTTP と WebSocket のクライアントを提供しています。
現在 Selenium はサポートする最小バージョンを Java11 にアップグレードする計画をしていますが、
それにはかなりの労力が必要です。メジャーリリースに合わせることはユーザー体験にとって重要です。

Java11 のクライアントを利用するには`selenium-http-jdk-client`jar ファイルをダウンロードし、
`--ext`フラグで Grid の jar のクラスパスに通す必要があります。

jar ファイルは[repo1.maven.org](https://repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-http-jdk-client/)から直接ダウンロードできます。
Grid を起動する方法は以下の通りです:

```bash
java -Dwebdriver.http.factory=jdk-http-client -jar selenium-server-<version>.jar --ext selenium-http-jdk-client-<version>.jar standalone
```

`selenium-http-jdk-client`をダウンロードする別の方法として[Coursier](https://get-coursier.io/docs/cli-installation)を使う方法があります。

```bash
java -Dwebdriver.http.factory=jdk-http-client -jar selenium-server-<version>.jar --ext $(coursier fetch -p org.seleniumhq.selenium:selenium-http-jdk-client:<version>) standalone
```

ハブ&ノードか分散モードで動かす場合、`-Dwebdriver.http.factory=jdk-http-client`と
`--ext`フラグの設定が各コンポーネントに必要になります。

## Grid サイズ

Grid ロールの選択は、どのような OS やブラウザをサポートする必要があるかによって決まります。
どの OS、ブラウザをサポートするか、どのくらいの並列セッションを実行するか、マシンの数とそれらの性能（CPU、RAM）に依存します。

セッションを並列で作成するのは、**ディストリビューター**が利用可能なプロセッサーに依存します。たとえば、
マシンに 4 つの CPU がある場合、**ディストリビューター**は同時に最大 4 つのセッションしか作成できません。

デフォルトでは、ノードがサポートする同時セッションの最大数は、利用可能な CPU の数によって制限されます。
たとえば、ノードのマシンに 8CPU がある場合、同時に最大 8 つのブラウザセッションを実行できます（ただし、Safari は常に 1 つです）。
また各ブラウザセッションは約 1GB の RAM を使用することが期待されます。

一般的にノードはできるだけ小さくすることが推奨されます。32CPU と 32GB の RAM を持つマシンで
32 の同時ブラウザセッションを実行するよりも、 32 の小さな プロセスをよりよく分離するために、
32 の小さなノードを持つことが推奨されます。これによって、もしノードに障害が発生しても、分離されて処理されます。
Docker はこの方法を実現するための優れたツールです。

デフォルト値(ブラウザあたり 1CPU/1GB RAM)は推奨値であり、あなたの用途に沿わない可能性があることに注意してください。
この値は参考値としての推奨であり、継続的にパフォーマンスを測定することで、あなたの環境にとって理想的な値を見つけることができるでしょう。

Grid のサイズは、サポートされる同時セッションの数と、ノードの数に関連しており、
万能なサイズというものはありません。以下のサイズは概算で、環境が違えば変わる可能性があります。
例えば、120 台のノードを持つハブ&ノードの場合、ハブが十分なリソースを持っていればうまく機能するかもしれません。
また、この数値は確定したものではありません。フィードバックをお待ちしています。

### Small

5 台以下の**ノード**で、**スタンドアロン**か**ハブ&ノード**

### Middle

6〜60 台の**ノード**で、**ハブ&ノード**

### Large

60〜100 台の**ノード**で、**ハブ&ノード**、あるいは 100 台以上の**ノード**で**分散**

## 警告

Grid を保護しないと、以下のような問題が発生する可能性があります。

- Grid インフラストラクチャへのオープンアクセスを許容してしまう。
- サードパーティが内部 Web アプリケーションやファイルにアクセスすることを許可してしまう。
- サードパーティにカスタムバイナリの実行を許可してしまう。

[Detectify](//labs.detectify.com) のブログで公開されてしまった Grid が
どのように悪用されるかを紹介しています: [Don't Leave your Grid Wide Open](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/)

## 参考文献

- [Components]({{< ref "components.md" >}}): 内部のコンポーネントの仕組みを知る
- [Configuration]({{< ref "/configuration" >}}): Grid の設定をカスタマイズする
- [Architecture]({{< ref "architecture.md" >}}): Grid のコンセプトを理解する
- [Advanced Features]({{< ref "/advanced_features" >}}): Grid のさらなる可能性を探る
