---
title: "構成ヘルプ"
weight: 1
---

ヘルプコマンドは、現在のコード実装に基づいて情報を表示します。
したがって、ドキュメントが更新されない場合に備えて、正確な情報を提供します。
それは、新しいバージョンのグリッド4の構成について学習する最も簡単な方法です。

## Info コマンド

infoコマンドは、次のトピックに関する詳細なドキュメントを提供します。

* Seleniumの構成
* セキュリティ
* セッションマップの設定
* トレース

### 構成ヘルプ

クイック設定のヘルプと概要は、以下を実行することで提供されます。

```shell
java -jar selenium-server-4.0.0-alpha-7.jar info config
```

### セキュリティ

安全な通信とノード登録のためのグリッドサーバーの設定の詳細を取得するには、以下を実行します。

```shell
java -jar selenium-server-4.0.0-alpha-7.jar info security
```

### セッションマップの設定

デフォルトでは、グリッドはローカルセッションマップを使用してセッション情報を保存します。
グリッドは、RedisやJDBC-SQLがサポートするデータベースなどの追加のストレージオプションをサポートしています。
別のセッションストレージをセットアップするには、次のコマンドを使用してセットアップ手順を取得します。

```shell
java -jar selenium-server-4.0.0-alpha-7.jar info sessionmap
```

### OpenTelemetryとJaegerを使用したトレースの設定

デフォルトでは、トレースは有効になっています。
トレースをエクスポートしてJaeger経由で視覚化するには、次のコマンドを使用して手順を実行します。

```shell
java -jar selenium-server-4.0.0-alpha-7.jar info tracing
```

## SeleniumGridコマンドを一覧表示する  
 
```shell
java -jar selenium-server-4.0.0-alpha-7.jar --config-help
```

使用可能なすべてのコマンドとそれぞれの説明が表示されます。

## コンポーネントヘルプコマンド

Seleniumロールの後に–help configオプションを渡して、コンポーネント固有の構成情報を取得します。

### スタンドアロン 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar standalone --help
```
### ハブ 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar hub --help
```

### セッション 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar sessions --help
```

### 新しいセッションキューラー

```shell
java -jar selenium-server-4.0.0-alpha-7.jar sessionqueuer --help
```

### ディストリビューター 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar distributor --help
```

### ルーター 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar router --help
```

### ノード 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar node --help
```