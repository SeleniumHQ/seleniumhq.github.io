---
title: "独自のグリッドを設定する"
weight: 2
---

## Selenium4でのグリッド設定のさまざまなモード
* スタンドアロン
* ハブとノード
* 分散
* Docker

## スタンドアロンモード
新しいSeleniumServer Jarには、グリッドを実行するために必要なすべてのものが含まれています。
Seleniumグリッドを起動するのも最も簡単なモードです。
デフォルトでは、サーバーは http://localhost:4444 でリッスンします。
これは、RemoteWebDriverテストを指定する必要があるURLです。
サーバーは、システムパスから使用できる使用可能なドライバーを検出します。

```shell
java -jar selenium-server-4.0.0-alpha-7.jar standalone
```

## ハブとノードモード

### ハブを開始する
```shell
java -jar selenium-server-4.0.0-alpha-7.jar hub
```

### ノードを登録する

```shell
java -jar selenium-server-4.0.0-alpha-7.jar node --detect-drivers true
```

### Seleniumグリッドをクエリする

Selenium 4では、必要なデータを簡単にクエリしてまったく同じものを取得する新しい方法であるGraphQLを追加しました。

```shell
curl -X POST -H "Content-Type: application/json" --data '{ "query": "{grid{uri}}" }' -s http://localhost:4444/graphql | jq .
```
<br><br>

## 分散モード

* Step 1: まず、イベントバスを開始します。
これは、後続のステップで他のグリッドコンポーネントへの通信パスとして機能します。

    ```shell
    java -jar selenium-server-4.0.0-alpha-7.jar  event-bus
    ```

* Step 2: セッションマップを開始します。
これは、セッションIDをセッションが実行されているノードにマッピングする役割を果たします。

    ```shell
        java -jar selenium-server-4.0.0-alpha-7.jar sessions
    ```

* Step 3: 新しいセッションキューを起動すると、新しいセッション要求がローカルキューに追加されます。
ディストリビューターはキューからリクエストを受け取ります。

    ```shell
        java -jar selenium-server-4.0.0-alpha-7.jar sessionqueuer
    ```

* Step 4: ディストリビューターを起動します。
すべてのノードは、ディストリビュータープロセスの一部として接続されます。
セッションの作成要求が呼び出されたときに、ノードを割り当てる必要があります。

    ```shell
        java -jar selenium-server-4.0.0-alpha-7.jar distributor --sessions http://localhost:5556 --sessionqueuer http://localhost:5559 --bind-bus false
    ```

* Step 5: 次に、Webに公開するアドレスであるルーターを起動します。

    ```shell
        java -jar selenium-server-4.0.0-alpha-7.jar router --sessions http://localhost:5556 --distributor http://localhost:5553 --sessionqueuer http://localhost:5559
    ```

* Step 6: 最後に、ノードを追加します。

    ```shell
        java -jar selenium-server-4.0.0-alpha-7.jar node --detect-drivers true
    ```

## Dockerイメージを介してスタンドアロングリッドを開始する

  次のコマンドでノードを起動できます。

```shell
    java -jar selenium-server-4.0.0-alpha-1.jar node -D selenium/standalone-firefox:latest '{"browserName": "firefox"}'
```

  Seleniumサーバーを起動し、Dockerに新しいインスタンスを作成することを委任できます。

```shell
     java -jar selenium-server-4.0.0-alpha-7.jar standalone -D selenium/standalone-firefox:latest '{"browserName": "firefox"}' --detect-drivers false
```

## 警告

Selenium Gridは、適切なファイアウォールアクセス許可を使用して外部アクセスから保護する必要があります。

グリッドを保護しないと、次の1つ以上が発生する可能性があります。

* グリッドインフラストラクチャへのオープンアクセスを提供します。
* サードパーティが内部Webアプリケーションおよびファイルにアクセスすることを許可します。
* サードパーティにカスタムバイナリの実行を許可します。

[Detectify](//labs.detectify.com) に関するこのブログ投稿をご覧ください。
これは、公開されたグリッドが悪用される可能性のある概要を示しています。 [Don't Leave your Grid Wide Open](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/)
