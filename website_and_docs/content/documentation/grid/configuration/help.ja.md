---
title: "構成ヘルプ"
linkTitle: "構成ヘルプ"
weight: 1
description: Gridの設定に利用可能なオプション
aliases: [
"/documentation/ja/grid/grid_4/configuring_components/config_help/",
"/ja/documentation/grid/configuring_components/config_help/"
]
---

{{% pageinfo color="primary" %}}
ヘルプコマンドは、現在のコード実装に基づいて情報を表示します。
したがって、ドキュメントが更新されない場合に備えて、正確な情報を提供します。
それは、新しいバージョンのグリッド 4 の構成について学習する最も簡単な方法です。
{{% /pageinfo %}}

## Info コマンド

info コマンドは、次のトピックに関する詳細なドキュメントを提供します。

- Selenium の構成
- セキュリティ
- セッションマップの設定
- トレース

### 構成ヘルプ

クイック設定のヘルプと概要は、以下を実行することで提供されます。

```shell
java -jar selenium-server-<version>.jar info config
```

### セキュリティ

安全な通信とノード登録のためのグリッドサーバーの設定の詳細を取得するには、以下を実行します。

```shell
java -jar selenium-server-<version>.jar info security
```

### セッションマップの設定

デフォルトでは、グリッドはローカルセッションマップを使用してセッション情報を保存します。
グリッドは、Redis や JDBC-SQL がサポートするデータベースなどの追加のストレージオプションをサポートしています。
別のセッションストレージをセットアップするには、次のコマンドを使用してセットアップ手順を取得します。

```shell
java -jar selenium-server-<version>.jar info sessionmap
```

### OpenTelemetry と Jaeger を使用したトレースの設定

デフォルトでは、トレースは有効になっています。
トレースをエクスポートして Jaeger 経由で視覚化するには、次のコマンドを使用して手順を実行します。

```shell
java -jar selenium-server-<version>.jar info tracing
```

## SeleniumGrid コマンドを一覧表示する

```shell
java -jar selenium-server-<version>.jar --config-help
```

使用可能なすべてのコマンドとそれぞれの説明が表示されます。

## コンポーネントヘルプコマンド

Selenium ロールの後に–help config オプションを渡して、コンポーネント固有の構成情報を取得します。

### スタンドアロン

```shell
java -jar selenium-server-<version>.jar standalone --help
```

### ハブ

```shell
java -jar selenium-server-<version>.jar hub --help
```

### セッション

```shell
java -jar selenium-server-<version>.jar sessions --help
```

### 新規セッションキュー

```shell
java -jar selenium-server-<version>.jar sessionqueue --help
```

### ディストリビューター

```shell
java -jar selenium-server-<version>.jar distributor --help
```

### ルーター

```shell
java -jar selenium-server-<version>.jar router --help
```

### ノード

```shell
java -jar selenium-server-<version>.jar node --help
```
