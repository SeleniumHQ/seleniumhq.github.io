---
title: "Grid エンドポイント"
linkTitle: "Grid エンドポイント"
weight: 3
aliases: [
"/documentation/ja/grid/grid_4/grid_endpoints/",
"/ja/documentation/grid/advanced_features/grid_endpoints/"
]
---

## Grid

### Grid ステータス

Grid ステータスは Grid の現在の状態を提供します。 登録されている全てのノードの詳細で構成されます。
各ノードのステータスには、ノードの稼働状況、セッション、およびスロットに関する情報が含まれます。

```shell
cURL GET 'http://localhost:4444/status'
```

スタンドアロンモードでは、Grid URL は スタンドアロンサーバーのアドレスになります。

ハブ&ノードモードでは、Grid URL は ハブのアドレスになります。

完全分散モードでは、Grid URL は ルーターのアドレスになります。

上記すべてのモードのデフォルトの URL は http://localhost:4444 です。

## ディストリビューター

### ノード削除

ノードを Grid から削除するには、以下の cURL コマンドを使用します。
このコマンドは、そのノード上で実行中のセッションを停止させるものではありません。
ノードは明示的に強制終了されない限り、そのまま動作し続けます。
ディストリビューターはそのノードを認識しなくなるため、マッチする新しいセッションのリクエストは はその Node に転送されません。

スタンドアロンモードでは、ディストリビューターの URL はスタンドアロンサーバーのアドレスとなります。

ハブ&ノードモードでは、ディストリビューターの URL は ハブのアドレスになります。

```shell
cURL --request DELETE 'http://localhost:4444/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET: <secret> '
```

完全分散モードでは、ディストリビューター URL は ディストリビューターのアドレスになります。

```shell
cURL --request DELETE 'http://localhost:5553/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```

Grid の設定時に登録用の secret を設定していない場合は次のようにします:

```shell
cURL --request DELETE 'http://<Distributor-URL>/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET;'
```

### ノードのドレイン

ノードドレインコマンドはノードをグレースフルシャットダウンするために利用します。
ドレインは実行中のセッションがすべて完了した後にノードを停止します。
新規のセッションは受け付けません。

スタンドアロンモードでは、ディストリビューターの URL はスタンドアロンサーバーのアドレスとなります。

ハブ&ノードモードでは、ディストリビューターの URL は ハブのアドレスになります。

```shell
cURL --request POST 'http://localhost:4444/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET: <secret> '
```

完全分散モードでは、ディストリビューター URL は ディストリビューターのアドレスになります。

```shell
cURL --request POST 'http://localhost:5553/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET: <secret>'
```

Grid の設定時に登録用の secret を設定していない場合は次のようにします:

```shell
cURL --request POST 'http://<Distributor-URL>/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET;'
```

## ノード

この節でのエンドポイントは、ハブ&ノードモードとノードが独立して動作する完全分散型 Grid モードに適用されます。
ノードが 1 つの場合、デフォルトのノード URL は http://localhost:5555 です。
複数のノードがある場合は、[Grid ステータス](#grid-ステータス) を使ってすべてのノードの詳細とノードアドレスを取得してください。

### ステータス

ノードステータスは基本的にノードのヘルスチェックのためのものです。
ディストリビューターは定期的にノードの状態を ping で取得し、それに応じて Grid モデルを更新します。
ステータスには稼働状況、セッション、およびスロットに関する情報が含まれます。

```shell
cURL --request GET 'http://localhost:5555/status'
```

### ドレイン

ディストリビューターは [ドレイン](#ノードのドレイン)コマンドを適切なノードに渡します。
ノードを直接ドレインするには以下の cURL コマンドを使います。
どちらのエンドポイントも有効であり、同じ結果になります。
ドレインは、ノードを停止する前に進行中のセッションを終了させます。

```shell
cURL --request POST 'http://localhost:5555/se/grid/node/drain' --header 'X-REGISTRATION-SECRET: <secret>'
```

Grid の設定時に登録用の secret を設定していない場合は次のようにします:

```shell
cURL --request POST 'http://<node-URL>/se/grid/node/drain' --header 'X-REGISTRATION-SECRET;'
```

### セッションオーナーのチェック

あるセッションがノードに属しているかどうかをチェックするには、以下の cURL コマンドを使います。

```shell
cURL --request GET 'http://localhost:5555/se/grid/node/owner/<session-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```

Grid の設定時に登録用の secret を設定していない場合は次のようにします:

```shell
cURL --request GET 'http://<node-URL>/se/grid/node/owner/<session-id>' --header 'X-REGISTRATION-SECRET;'
```

もしセッションがノードに属していたら true を返し、そうでなければ false が返ります。

### セッションの削除

セッションを削除すると、WebDriver セッションが終了し、ドライバがアクティブなセッションマップから削除されます。
削除されたセッション ID を使用するリクエストや、ドライバのインスタンスを再利用しようとすると、エラーとなります。

```shell
cURL --request DELETE 'http://localhost:5555/se/grid/node/session/<session-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```

Grid の設定時に登録用の secret を設定していない場合は次のようにします:

```shell
cURL --request DELETE 'http://<node-URL>/se/grid/node/session/<session-id>' --header 'X-REGISTRATION-SECRET;'
```

## 新規セッションキュー

### 新規セッションキューのクリア

新規セッションキューには、新規セッションリクエストが格納されます。
キューをクリアするには、以下に挙げる cURL コマンドを使用します。
キューを消去すると、キューにあるすべてのリクエストを拒否します。
サーバーは各リクエストのそれぞれのクライアントにエラーレスポンスを返します。
クリアコマンドの結果は、削除されたリクエストの数です。

スタンドアロンモードでは、キューの URL はスタンドアロンサーバーのアドレスとなります。

ハブ&ノードモードでは、キューの URL は ハブのアドレスになります。

```shell
cURL --request DELETE 'http://localhost:4444/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET: <secret>'
```

完全分散モードでは、キューの URL は 新規セッションキューのアドレスになります。

```shell
cURL --request DELETE 'http://localhost:5559/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET: <secret>'
```

Grid の設定時に登録用の secret を設定していない場合は次のようにします:

```shell
cURL --request DELETE 'http://<URL>/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET;'
```

### 新規セッションリクエストの取得

新規セッションキューには、新規セッションリクエストが格納されます。
キューにある現在のリクエストを取得するには、以下に挙げる cURL コマンドを使用します。
レスポンスはキュー内のリクエストの数とリクエストのペイロードを返します。

スタンドアロンモードでは、キューの URL はスタンドアロンサーバーのアドレスとなります。

ハブ&ノードモードでは、キューの URL は ハブのアドレスになります。

```shell
cURL --request GET 'http://localhost:4444/se/grid/newsessionqueue/queue'
```

完全分散モードでは、キューの URL は 新規セッションキューのアドレスになります。

```shell
cURL --request GET 'http://localhost:5559/se/grid/newsessionqueue/queue'
```
