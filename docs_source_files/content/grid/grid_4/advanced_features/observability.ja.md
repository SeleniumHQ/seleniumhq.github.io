---
title: "可観測性"
weight: 2
---

## 目次
 - [Selenium Grid](#selenium-grid)
 - [可観測性](#可観測性)
	 - [分散トレース](#分散トレース) 	
	 -  [イベントログ](#イベントログ)
  - [グリッドの可観測性](#グリッドの可観測性)
	  - [トレースの視覚化](#トレースの視覚化)
	  - [イベントログの活用](#イベントログの活用)
  - [参考文献](#参考文献)

## Selenium Grid

グリッドは、さまざまなブラウザーとオペレーティングシステムの組み合わせでテストを実行することにより、テストのスケーリングと割り当てを支援します。

## 可観測性

可観測性には、トレース、メトリック、ログの3つの柱があります。
Selenium Grid 4は完全に分散されるように設計されているため、可観測性により、内部の理解とデバッグが容易になります。

## 分散トレース
1つのリクエストまたはトランザクションは、複数のサービスとコンポーネントにまたがっています。 
トレースは、各サービスがリクエストを実行するときにリクエストのライフサイクルを追跡します。 エラーシナリオでのデバッグに役立ちます。
トレースコンテキストで使用されるいくつかの重要な用語は次のとおりです。

**トレース**
トレースを使用すると、発信元から最終的な宛先まで、複数のサービスを介してリクエストをトレースできます。 
このリクエストの過程は、デバッグ、エンドツーエンドフローの監視、および障害の特定に役立ちます。
トレースは、エンドツーエンドのリクエストフローを示しています。 
各トレースには、識別子として一意のIDがあります。

**スパン**
各トレースは、スパンと呼ばれる時限操作で構成されています。
スパンには開始時刻と終了時刻があり、サービスによって実行される操作を表します。 
スパンの粒度は、計測方法によって異なります。 各スパンには一意の識別子があります。 
トレース内のすべてのスパンは同じトレースIDを持ちます。

**スパン属性**
スパン属性は、各スパンに関する追加情報を提供するキーと値のペアです。

**イベント**
イベントは、スパン内のタイムスタンプ付きログです。
それらは、既存のスパンに追加のコンテキストを提供します。
イベントには、イベント属性としてキーと値のペアも含まれます。

## イベントログ

アプリケーションをデバッグするには、ロギングが不可欠です。 
ロギングは、多くの場合、人間が読める形式で行われます。 
ただし、マシンがログを検索および分析するには、明確に定義された形式である必要があります。 
構造化ロギングは、ログを固定形式で一貫して記録する一般的な方法です。 通常、次のようなフィールドが含まれます。
 * Timestamp
 * Logging level
 * Logger class
 * Log message (これはさらに、ログが記録された操作に関連するフィールドに分類されます)

ログとイベントは密接に関連しています。 
イベントは、単一の作業単位を実行するために利用可能なすべての可能な情報をカプセル化します。 
ログは基本的にイベントのサブセットです。 重要なのは、どちらもデバッグに役立つことです。 
詳細については、次のリソースを参照してください。

 1. [https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/](https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/)
 2. [https://charity.wtf/2019/02/05/logs-vs-structured-events/](https://charity.wtf/2019/02/05/logs-vs-structured-events/)

## グリッドの可観測性

Seleniumサーバーには、OpenTelemetryを使用したトレースが装備されています。 
サーバーへのすべての要求は、最初から最後まで追跡されます。 
各トレースは、サーバー内で要求が実行されるときの一連のスパンで構成されます。 
Seleniumサーバーのほとんどのスパンは、次の2つのイベントで構成されています。
1. Normal event - 作業単位に関するすべての情報を記録し、作業が正常に完了したことを示します。
2. Error event - エラーが発生するまですべての情報を記録してから、エラー情報を記録します。 例外イベントを記録します。

Seleniumサーバーの実行
 1. [Standalone](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#standalone-mode)
 2. [Hub and Node](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#hub-and-node)
 3. [Fully Distributed](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#fully-distributed)
 4. [Docker](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#using-docker)

## トレースの視覚化
すべてのスパン、イベント、およびそれぞれの属性は、トレースの一部です。 
トレースは、上記のすべてのモードでサーバーを実行しているときに機能します。

デフォルトでは、トレースはSeleniumサーバーで有効になっています。 
Seleniumサーバーは2つのエクスポーターを介してトレースをエクスポートします。
1. コンソール - すべてのトレースとそれに含まれるスパンをFINEレベルでログに記録します。 デフォルトでは、SeleniumサーバーはINFOレベル以上でログを印刷します。 **ログレベル** フラグを使用して、Selenium Grid jar/sの実行中に選択したログレベルを渡すことができます。

```shell
java -jar selenium-server-4.0.0-<selenium-version>.jar standalone --log-level FINE
```
2. Jaeger UI - OpenTelemetryは、コード内のトレースを計測するためのAPIとSDKを提供します。
Jaegerはトレースバックエンドですが、トレーステレメトリデータの収集と、データのクエリ、フィルタリング、および視覚化機能の提供を支援します。

Jaeger UIを使用してトレースを視覚化する詳細な手順は、次のコマンドを実行することで取得できます。

```shell
java -jar selenium-server-4.0.0-<selenium-version>.jar info tracing
```

[サーバーを実行してトレースをJaegerに送信するための非常に良い例とスクリプト](https://github.com/manoj9788/tracing-selenium-grid)

## イベントログの活用
トレースをエクスポートして視覚化したくない場合でも、イベントログに対してトレースを有効にする必要があります。
**デフォルトでは、トレースは有効になっています。** 
**コンソールでログを表示するために、追加のパラメーターを渡す必要はありません。** 
スパン内のすべてのイベントは、FINEレベルでログに記録されます。
エラーイベントはWARNレベルでログに記録されます。

すべてのイベントログには次のフィールドがあります。

| フィールド | フィールド値 | 説明 |
|-|-|-|
| Event time | eventId | エポックナノ秒単位のイベントレコードのタイムスタンプ |
| Trace Id  | tracedId | 各トレースは、トレースIDによって一意に識別されます。 |
| Span Id  | spanId | トレース内の各スパンは、スパンIDによって一意に識別されます。 |
| Span Kind | spanKind | スパンの種類は、スパンのタイプを示すスパンのプロパティです。 それは、スパンによって実行される作業単位の性質を理解するのに役立ちます。 |
| Event name | eventName | これはログメッセージにマップされます。 |
| Event attributes | eventAttributes | これは、実行された操作に基づいてイベントログの核心を形成し、JSON形式のキーと値のペアを持ちます。 これには、ロガークラスを表示するためのハンドラークラス属性も含まれます。|

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
    
上記のフィールドに加えて、 [OpenTelemetryの仕様] に基づくエラーログは、次のとおりです。

| フィールド | フィールド値 | 説明 |
|-|-|-|
| Exception type  | exception.type | 例外クラス名 |
| Exception message  | exception.message | 例外の理由 |
| Exception stacktrace | exception.stacktrace | 例外がスローされた時点での呼び出しスタックを出力します。 例外の原因を理解するのに役立ちます。 |
 

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

注：ログは読みやすくするために上にきれいに印刷されています。 Seleniumサーバーでは、ログのプリティプリントがオフになっています。

上記の手順で、トレースとログを表示できるように設定する必要があります。

## 参考文献 
1. [Understanding Tracing](https://lightstep.com/blog/opentelemetry-101-what-is-tracing/)
2. [OpenTelemetry Tracing API Specification](https://github.com/open-telemetry/opentelemetry-specification/blob/master/specification/trace/api.md#status)
3. [Selenium Wiki](https://github.com/SeleniumHQ/selenium/wiki)
4. [Structured logs vs events](https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/)
5. [Jaeger framework](https://github.com/jaegertracing/jaeger)