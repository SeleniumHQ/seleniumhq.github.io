---
title: "可観測性"
linkTitle: "可観測性"
weight: 1
aliases: ["/documentation/ja/grid/grid_4/advanced_features/observability/"]
---

## 目次

- [Selenium Grid](#selenium-grid)
- [可観測性](#可観測性)
  - [分散トレーシング](#分散トレーシング)
  - [イベントロギング](#イベントロギング)
- [Grid の可観測性](#Grid-の可観測性)
  - [トレースの可視化](#トレースの可視化)
  - [イベントログの活用](#イベントログの活用)
- [参考](#参考)

## Selenium Grid

Grid は、さまざまなブラウザとオペレーティングシステムの組み合わせでテストを実行することにより、テストのスケーリングと分散を支援します。

## 可観測性

可観測性(Observability) には、トレース、メトリクス、ログの 3 つの柱があります。
Selenium Grid 4 は完全分散型に設計されているため、可観測性を確保することで内部を理解し、デバッグすることが容易になります。

## 分散トレーシング

1 つのリクエストやトランザクションは、複数のサービスやコンポーネントにまたがります。
トレースは、各サービスがリクエストを実行する際に、リクエストのライフサイクルをトラックします。これは、エラーシナリオのデバッグに有用です。
トレースで使用される用語は次のとおりです:

**トレース**
トレースでは、複数のサービスを通じてリクエストの出発点から最終地点までを追跡することができます。
このリクエストの旅は、デバッグ、エンドツーエンドフローの監視、障害の特定に役立ちます。
トレースは、エンドツーエンドのリクエストフローを描きます。
各トレースは識別子としてユニークな ID を持っています。

**スパン**

各トレースは、スパンと呼ばれる時間で区切られたオペレーションで構成されています。
スパンには開始時刻と終了時刻があり、サービスによって実行される操作を表します。
スパンの粒度は実装方法に依存します。各スパンは一意の識別子を持ちます。
トレース内のすべてのスパンは、同じトレース ID を持ちます。

**スパン属性**
スパン属性は各スパンの付加的な情報を提供するキーと値のペアです。

**イベント**
イベントは、スパン内のタイムスタンプ付きログです。
既存のスパンに追加のコンテキストを提供します。
イベントには、イベント属性としてキーと値のペアも含まれます。

## イベントロギング

アプリケーションのデバッグには、ロギングが欠かせません。
ログの記録は多くの場合、人間が読める形式で行われます。
しかし、機械がログを検索・分析するためには、明確に定義されたフォーマットである必要があります。
構造化ロギングは、固定フォーマットで一貫してログを記録する一般的な方法です。
一般的には次のようなフィールドが含まれます。

- タイムスタンプ
- ログレベル
- ロガークラス
- ログメッセージ (これはさらに、ログが記録された操作に関するフィールドに分解されます)

ログとイベントは密接に関連しています。
イベントは、1 つの処理を行うための情報を全てカプセル化します。
ログは基本的にイベントのサブセットです。
重要なのは、どちらもデバッグを支援することです。
詳細については、以下のリソースを参照してください。

1.  [https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/](https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/)
2.  [https://charity.wtf/2019/02/05/logs-vs-structured-events/](https://charity.wtf/2019/02/05/logs-vs-structured-events/)

## Grid の可観測性

Selenium サーバーは OpenTelemetry を使ってトレースできるようになっています。
サーバへのすべてのリクエストは、最初から最後までトレースされます。
各トレースは、リクエストがサーバ内で実行されるときの一連のスパンから構成されます。
Selenium サーバのスパンのほとんどは、2 つのイベントから構成されています。

1. 通常イベント- 単一の処理に関するすべての情報を記録し、処理が正常に完了したことを知らせます。
2. エラーイベント- エラーが発生するまでのすべての情報を記録し、エラー情報を記録します。例外イベントをマークします。

Selenium サーバーを起動する:

1.  [スタンドアロン](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#standalone-mode)
2.  [ハブ・ノード](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#hub-and-node)
3.  [完全分散モード](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#fully-distributed)
4.  [Docker](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#using-docker)

## トレースの可視化

すべてのスパン、イベント、およびそれぞれの属性がトレースの一部となります。
トレースは、上記のすべてのモードでサーバを実行している間動作します。
トレースはデフォルトで Selenium サーバで有効になっています。

Selenium サーバーは、2 つのエクスポーター経由でトレースをエクスポートします。

1. コンソール - すべてのトレースと、それに含まれるスパンを FINE レベルでログに出力します。デフォルトでは、Selenium サーバーは INFO レベル以上のログを出力します。
   **log-level** フラグを使うと、Selenium Grid jar を実行する際に任意のログレベルを指定することができます。

```shell
java -jar selenium-server-4.0.0-<selenium-version>.jar standalone --log-level FINE
```

2. Jaeger UI - OpenTelemetry は、コード内のトレースを計測するための API と SDK を提供します。一方、Jaeger はトレースのバックエンドで、トレースのテレメトリデータを収集し、データのクエリ、フィルタリング、ビジュアライズの機能を提供します。

Jaeger UI を用いたトレースの可視化の詳細な手順を確認するには、次のコマンドを実行してください:

```shell
java -jar selenium-server-4.0.0-<selenium-version>.jar info tracing
```

[非常に参考になる例と、Jaeger にトレースを送信するスクリプトです](https://github.com/manoj9788/tracing-selenium-grid)

## イベントログの活用

トレースを可視化しない場合でも、イベントロギングではトレースを有効にする必要があります。
**デフォルトでは、トレースは有効です。コンソールでログを見るために、追加のパラメータを渡す必要はありません。**
スパン内のすべてのイベントは FINE レベルでログに記録されます。エラーイベントは、WARN レベルでログに記録されます。

全てのイベントは次のフィールドを持ちます:

| フィールド   | フィールド名    | 概要                                                                                                                                                                                         |
| ------------ | --------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| イベント時刻 | eventId         | イベントのタイムスタンプ(エポックナノ秒)。                                                                                                                                                   |
| トレース ID  | tracedId        | 各トレースはトレース ID で一意に識別されます。                                                                                                                                               |
| スパン ID    | spanId          | トレース内の各スパンは、スパン ID により一意に識別されます。                                                                                                                                 |
| スパン種別   | spanKind        | スパン種別は、スパンの種類を示すスパンのプロパティです。スパンの処理の性質を識別するのに役立ちます。                                                                                         |
| イベント名   | eventName       | ログメッセージにマッピングされます。                                                                                                                                                         |
| イベント属性 | eventAttributes | イベントログの核となるもので、実行された操作に基づいて JSON フォーマットのキーと値のペアが用意されています。また、ロガークラスを表示するために、ハンドラークラスアトリビュートも含まれます。 |

サンプルログ

    FINE [LoggingOptions$1.lambda$export$1] - {
      "traceId": "fc8aef1d44b3cc8bc09eb8e581c4a8eb",
      "spanId": "b7d3b9865d3ddd45",
      "spanKind": "INTERNAL",
      "eventTime": 1597819675128886121,
      "eventName": "Session request execution complete",
      "attributes": {
        "http.status_code": 200,
        "http.handler_class": "org.openqa.selenium.grid.router.HandleSession",
        "http.url": "\u002fsession\u002fdd35257f104bb43fdfb06242953f4c85",
        "http.method": "DELETE",
        "session.id": "dd35257f104bb43fdfb06242953f4c85"
      }
    }

上記のフィールドに加えて、[OpenTelemetry の仕様](https://github.com/open-telemetry/opentelemetry-specification/blob/master/specification/trace/semantic_conventions/exceptions.md)に基づきエラーログは以下のフィールドで構成されます:

| フィールド       | フィールド名         | 概要                                                                                    |
| ---------------- | -------------------- | --------------------------------------------------------------------------------------- |
| 例外タイプ       | exception.type       | 例外クラス名。                                                                          |
| 例外メッセージ   | exception.message    | 例外の原因。                                                                            |
| スタックトレース | exception.stacktrace | 例外が発生した時点のコールスタックを表示します。 例外の発生源を把握するのに役立ちます。 |

サンプルエラーログ

    WARN [LoggingOptions$1.lambda$export$1] - {
      "traceId": "7efa5ea57e02f89cdf8de586fe09f564",
      "spanId": "914df6bc9a1f6e2b",
      "spanKind": "INTERNAL",
      "eventTime": 1597820253450580272,
      "eventName": "exception",
      "attributes": {
        "exception.type": "org.openqa.selenium.ScriptTimeoutException",
        "exception.message": "Unable to execute request: java.sql.SQLSyntaxErrorException: Table 'mysql.sessions_mappa' doesn't exist ..." (full message will be printed),
        "exception.stacktrace": "org.openqa.selenium.ScriptTimeoutException: java.sql.SQLSyntaxErrorException: Table 'mysql.sessions_mappa' doesn't exist\nBuild info: version: '4.0.0-alpha-7', revision: 'Unknown'\nSystem info: host: 'XYZ-MacBook-Pro.local', ip: 'fe80:0:0:0:10d5:b63a:bdc6:1aff%en0', os.name: 'Mac OS X', os.arch: 'x86_64', os.version: '10.13.6', java.version: '11.0.7'\nDriver info: driver.version: unknown ...." (full stack will be printed),
        "http.handler_class": "org.openqa.selenium.grid.distributor.remote.RemoteDistributor",
        "http.url": "\u002fsession",
        "http.method": "POST"
      }
    }

注: ログは読みやすさのためプリティプリントされています。Selenimu サーバーではぷるティプリントはオフになっています。

以上がトレースとログをセットアップするための手順です。

## 参考

1. [Understanding Tracing](https://lightstep.com/blog/opentelemetry-101-what-is-tracing/)
2. [OpenTelemetry Tracing API Specification](https://github.com/open-telemetry/opentelemetry-specification/blob/master/specification/trace/api.md#status)
3. [Selenium Wiki](https://github.com/SeleniumHQ/selenium/wiki)
4. [Structured logs vs events](https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/)
5. [Jaeger framework](https://github.com/jaegertracing/jaeger)
