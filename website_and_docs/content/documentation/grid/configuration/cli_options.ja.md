---
title: "Selenium GridのCLI オプション"
linkTitle: "CLI オプション"
weight: 2
description: 全てのGridコンポーネントのCLIオプション詳細
aliases: [
"/ja/documentation/grid/configuring_components/cli_options/"
]
---

Grid の設定には、さまざまなセクションが用意されています。
各セクションには、コマンドライン引数で設定可能なオプションがあります。コマンドライン引数で設定できます。

コンポーネントとセクションの対応は以下の通りです。

{{% pageinfo color="primary" %}}
オプションが変更、または追加されたが文書化されていない場合、
このドキュメントは古くなる可能性があることに注意してください。
もしそのような状況を見つけたら、["構成ヘルプ"]({{< ref "help.md" >}})を確認し、
ドキュメントを更新するプルリクエストを気軽に送ってください。
{{% /pageinfo %}}

## セクション

<table class="table table-bordered text-md-center d-md-table-cell">
<thead>
  <tr>
    <th></th>
    <th>スタンドアロン</th>
    <th>ハブ</th>
    <th>ノード</th>
    <th>ディストリビューター</th>
    <th>ルーター</th>
    <th>セッション</th>
    <th>新規セッションキュー</th>
  </tr>
</thead>
<tbody>
  <tr>
    <th><a href="#distributor">Distributor</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#docker">Docker</a></th>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#events">Events</a></th>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
  </tr>
  <tr>
    <th><a href="#logging">Logging</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
  </tr>
  <tr>
    <th><a href="#network">Network</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#node">Node</a></th>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#router">Router</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#relay">Relay</a></th>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th><a href="#server">Server</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
  </tr>
  <tr>
    <th><a href="#sessionqueue">SessionQueue</a></th>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
  </tr>
  <tr>
    <th><a href="#sessions">Sessions</a></th>
    <td></td>
    <td></td>
    <td></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td><i class="fas fa-check"></i></td>
    <td></td>
  </tr>
</tbody>
</table>

### Distributor

| オプション                     | 型      | 値/例                                                               | 概要                                                                                                                                                                                                  |
| ------------------------------ | ------- | ------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `--healthcheck-interval`       | int     | `120`                                                               | 全てのノードに対してヘルスチェックを実行する頻度（秒）を指定します。これにより、サーバーは全てのノードに対して正常に ping を送信できるようになります。                                                |
| `--distributor`                | uri     | `http://localhost:5553`                                             | ディストリビューターの URL。                                                                                                                                                                          |
| `--distributor-host`           | string  | `localhost`                                                         | ディストリビューターがリッスンするホスト名。                                                                                                                                                          |
| `--distributor-implementation` | string  | `org.openqa.selenium.grid.distributor.local.LocalDistributor`       | デフォルトでないディストリビューター実装の完全なクラス名。                                                                                                                                            |
| `--distributor-port`           | int     | `5553`                                                              | ディストリビューターがリッスンするポート番号。                                                                                                                                                        |
| `--reject-unsupported-caps`    | boolean | `false`                                                             | Grid がサポートしていない capabilities をリクエストされた時、ディストリビューターがリクエストを即座に今日できるようにします。これはオンデマンドでノードを立ち上げをしない Grid の設定に適しています。 |
| `--slot-matcher`               | string  | `org.openqa.selenium.grid.data.DefaultSlotMatcher`                  | デフォルト以外で使用するスロットマッチャーの完全なクラス名。これはノードが特定のセッションをサポートできるかを判断するために使用されます。                                                            |
| `--slot-selector`              | string  | `org.openqa.selenium.grid.distributor.selector.DefaultSlotSelector` | デフォルト以外のスロットセレクターの完全なクラス名。これは、ノードがマッチした後ノード内のスロットを選択するために使用されます。                                                                      |

### Docker

| オプション             | 型       | 値/例                                                             | 概要                                                                                                                                                                                             |
| ---------------------- | -------- | ----------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `--docker-assets-path` | string   | `/opt/selenium/assets`                                            | アセットが保存される絶対パス。                                                                                                                                                                   |
| `--docker-`            | string[] | `selenium/standalone-firefox:latest '{"browserName": "firefox"}'` | イメージとステレオタイプの capabilities を対応付ける Docker 設定 (例 `-D selenium/standalone-firefox:latest '{"browserName": "firefox"}')                                                        |
| `--docker-devices`     | string[] | `/dev/kvm:/dev/kvm`                                               | コンテナに対してデバイスを公開します。各デバイスマッピングは、ホストとコンテナの両方のデバイスへのパスを、コロンで区切って保つ必要があります。例: /device/path/in/host:/device/path/in/container |
| `--docker-host`        | string   | `localhost`                                                       | Docker デーモンが動作しているホスト名。                                                                                                                                                          |
| `--docker-port`        | int      | `2375`                                                            | Docker デーモンが動作しているポート名。                                                                                                                                                          |
| `--docker-url`         | string   | `http://localhost:2375`                                           | Docker デーモンに接続するための URL。                                                                                                                                                            |
| `--docker-video-image` | string   | `selenium/video:latest`                                           | ビデオレコーディングが有効になっているときに利用される Docker イメージ。                                                                                                                         |

### Events

| オプション                | 型      | 値/例                                              | 概要                                                                                                                                                                                                                                                                                          |
| ------------------------- | ------- | -------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `--bind-bus`              | boolean | `false`                                            | 接続をバインドするかコネクトするかを指定します。<br> true の場合、コンポーネントはイベントバスにバインドされます（イベントバスもコンポーネントによって起動されます、通常はディストリビューターとハブによって起動されます）。<br> false の場合、コンポーネントがイベントバスにコネクトします。 |
| `--events-implementation` | string  | `org.openqa.selenium.events.zeromq.ZeroMqEventBus` | デフォルトでないイベントバス実装の完全なクラス名。                                                                                                                                                                                                                                            |
| `--publish-events`        | string  | `tcp://*:4442`                                     | イベントをイベントバスに配信するための接続文字列。                                                                                                                                                                                                                                            |
| `--subscribe-events`      | string  | `tcp://*:4443`                                     | イベントをイベントバスから購読するための接続文字列。                                                                                                                                                                                                                                          |

### Logging

| オプション               | 型      | 値/例                                                                                                                                                                     | 概要                                                                                                                                               |
| ------------------------ | ------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------- |
| `--http-logs`            | boolean | `false`                                                                                                                                                                   | http ログを有効にします。http ログを記録するには、トレースを有効にする必要があります。                                                             |
| `--log-encoding`         | string  | `UTF-8`                                                                                                                                                                   | ログのエンコーディング。                                                                                                                           |
| `--log`                  | string  | Windows パスの例: <br>`'\path\to\file\gridlog.log'` <br> or <br> `'C:\path\path\to\file\gridlog.log'`<br><br>Linux/Unix/MacOS パスの例:<br> `'/path/to/file/gridlog.log'` | ログを出力するファイル。OS のファイルパスと互換性があることを確認してください。                                                                    |
| `--log-level`            | string  | `“INFO”`                                                                                                                                                                  | ログレベル。デフォルトは INFO です。 ログレベルはこちらを参照してください。 https://docs.oracle.com/javase/7/docs/api/java/util/logging/Level.html |
| `--plain-logs`           | boolean | `true`                                                                                                                                                                    | プレーンなログを使用します。                                                                                                                       |
| `--structured-logs`      | boolean | `false`                                                                                                                                                                   | 構造化ログを使用します。                                                                                                                           |
| `--tracing`              | boolean | `true`                                                                                                                                                                    | トレースを有効にします。                                                                                                                           |
| `--log-timestamp-format` | string  | `HH:mm:ss.SSS`                                                                                                                                                            | ログのタイムスタンプ形式を設定できます。                                                                                                           |

### Network

| オプション       | 型      | 値/例   | 概要                                                                                              |
| ---------------- | ------- | ------- | ------------------------------------------------------------------------------------------------- |
| `--relax-checks` | boolean | `false` | 受信リクエストのオリジンヘッダーとコンテンツタイプに対する、厳格な W3C 準拠の検証をを緩和します。 |

### Node

| オプション                    | 型       | 値/例                                                                                                                                                                                                                                                        | 概要                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| ----------------------------- | -------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `--detect-drivers`            | boolean  | `true`                                                                                                                                                                                                                                                       | 現在のシステム上で利用可能なドライバーを自動で検出してノードに追加します。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| `--driver-configuration`      | string[] | `display-name="Firefox Nightly" max-sessions=2 webdriver-path="/usr/local/bin/geckodriver" stereotype='{"browserName": "firefox", "browserVersion": "86", "moz:firefoxOptions": {"binary":"/Applications/Firefox Nightly.app/Contents/MacOS/firefox-bin"}}'` | ノードがサポートするドライバーの一覧。可読性向上のため TOML ファイルで設定することを推奨します。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| `--driver-factory`            | string[] | `org.openqa.selenium.example.LynxDriverFactory '{"browserName": "lynx"}'`                                                                                                                                                                                    | 完全修飾クラス名と、そのクラスが対応するブラウザの設定とのマッピング。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| `--driver-implementation`     | string[] | `"firefox"`                                                                                                                                                                                                                                                  | チェックされるドライバー。指定された場合、自動設定はスキップされます。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| `--node-implementation`       | string   | `"org.openqa.selenium.grid.node.local.LocalNodeFactory"`                                                                                                                                                                                                     | デフォルトでないノード実装の完全なクラス名。これはセッションのライフサイクルを管理するために使用されます。                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| `--grid-url`                  | string   | `https://grid.example.com`                                                                                                                                                                                                                                   | Grid 全体のパブリックな URL (通常ハブかルーターのアドレスです)。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| `--heartbeat-period`          | int      | `60`                                                                                                                                                                                                                                                         | ノードが生存していることを知らせるため、ノードがディストリビューターに送るハードビートを、どのくらいの頻度（秒）で送るか。                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| `--max-sessions`              | int      | `8`                                                                                                                                                                                                                                                          | 最大同時接続セッション数。デフォルトは利用可能なプロセッサーの数です。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| `--override-max-sessions`     | boolean  | `false`                                                                                                                                                                                                                                                      | 利用可能なプロセッサーの数は、推奨される最大セッション数（プロセッサーごとに 1 つのブラウザセッション）です。このフラグを true に設定すると、推奨される最大値を上書きすることができます。セッションの安定性と信頼性が損なわれ、ホストがリソースを使い果たす可能性があります。                                                                                                                                                                                                                                                                                                       |
| `--register-cycle`            | int      | `10`                                                                                                                                                                                                                                                         | ノードがディストリビューターに初回登録を試みる頻度(秒)。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| `--register-period`           | int      | `120`                                                                                                                                                                                                                                                        | ノードが初めてディストリビューターに初回登録を試みるのにかかる時間(秒)。この時間が経過すると、ノードは再登録を試みない。                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| `--session-timeout`           | int      | `300`                                                                                                                                                                                                                                                        | X をセッションタイムアウト(秒)としたとき、 ノード は、過去 X 秒間に何の活動もなかったセッションを自動的に終了させます。 これにより他のテストが利用できるようスロットを解放します。                                                                                                                                                                                                                                                                                                                                                                                                  |
| `--vnc-env-var`               | string   | `START_XVFB`                                                                                                                                                                                                                                                 | VNC ストリームが利用可能かどうかを判断するために利用する環境変数。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| `--no-vnc-port`               | int      | `7900`                                                                                                                                                                                                                                                       | VNC が利用可能な場合、ローカルの noVNC ストリームを取得できるポートを設定します。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| `--drain-after-session-count` | int      | `1`                                                                                                                                                                                                                                                          | X 個のセッションが実行された後に、ノードをドレインしてシャットダウンします。 Kubernetes のような環境で有用です。 0 より大きい値を指定すると、この機能が有効になります。                                                                                                                                                                                                                                                                                                                                                                                                             |
| `--hub`                       | string   | `http://localhost:4444`                                                                                                                                                                                                                                      | ハブ・ノード構成におけるハブのアドレスを指定します。ホスト名か IP アドレスが指定できます。この場合、ハブは `http://hostname:4444` とみなされ、 `--grid-url` は同じものになります。 `--publish-events` は `tcp://hostname:4442` 、`--subscribe-events` は `tcp://hostname:4443` となります。 `hostname` にポート番号が含まれている場合は、それが `--grid-url` に使用されますが、イベントバスの URI は変更されません。これらのデフォルト値は、適切なフラグを設定することでオーバーライドすることができます。ホスト名にプロトコル(`https`のような)が含まれる場合もそれが利用されます。 |
| `--enable-cdp`                | boolean  | `true`                                                                                                                                                                                                                                                       | Grid 内で CDP プロキシーを有効にします。もしネットワークが web socket を許可していない場合、Grid 管理者は CDP を無効にできます。デフォルトは true です。                                                                                                                                                                                                                                                                                                                                                                                                                            |
| `--enable-managed-downloads`| boolean | `false` | This causes the Node to auto manage files downloaded for a given session on the Node. |
| `--selenium-manager`| boolean | `false` | When drivers are not available on the current system, use Selenium Manager. False by default. |

### Relay

| オプション                  | 型       | 値/例                                                                                                             | 概要                                                                                                        |
| --------------------------- | -------- | ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| `--service-url`             | string   | `http://localhost:4723`                                                                                           | Appium サーバーやクラウドサービスなど、WebDriver コマンドをサポートするサービスに接続するための URL です。  |
| `--service-host`            | string   | `localhost`                                                                                                       | WebDriver コマンドをサポートしてるサービスが稼働しているホスト名。                                          |
| `--service-port`            | int      | `4723`                                                                                                            | WebDriver コマンドをサポートしてるサービスが稼働しているポート番号。                                        |
| `--service-status-endpoint` | string   | `/status`                                                                                                         | WebDriver サービスの状態を問い合わせるエンドポイント、オプショナルです。HTTP 200 レスポンスが期待されます。 |
| `--service-configuration`   | string[] | `max-sessions=2 stereotype='{"browserName": "safari", "platformName": "iOS", "appium:platformVersion": "14.5"}}'` | 呼び出しの中継先となるサービスの設定。可読性向上のため、TOML ファイルで設定することを推奨します。           |

### Router

| オプション   | 型     | 値/例              | 概要                                                                                                                         |
| ------------ | ------ | ------------------ | ---------------------------------------------------------------------------------------------------------------------------- |
| `--password` | string | `myStrongPassword` | クライアントがサーバーに接続する際に使用するパスワード。このパスワードとユーザー名の両方が設定されていないと使用できません。 |
| `--username` | string | `admin`            | クライアントがサーバーに接続する際に使用するユーザー名。このユーザー名とパスワードの両方が設定されていないと使用できません。 |
| `--sub-path` | string | `my_company/selenium_grid` | A sub-path that should be considered for all user facing routes on the Hub/Router/Standalone. |

### Server

| オプション            | 型      | 値/例                | 概要                                                                                                                                                                                                                                                                  |
| --------------------- | ------- | -------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `--allow-cors`        | boolean | `true`               | Selenium サーバーが任意のホストからのウェブブラウザ接続を許可するかどうか。                                                                                                                                                                                           |
| `--host`              | string  | `localhost`          | サーバーの IP もしくはホスト名、通常自動的に決定されます。                                                                                                                                                                                                            |
| `--bind-host`         | boolean | `true`               | サーバがホストアドレス/ホスト名にバインドするか、あるいは到達可能な URL を知らせるためだけに使用するかを指定します。複雑なネットワーク構成で、サーバが現在の IP やホスト名ではなく、 外部の IP やホスト名で自分自身を公開する場合に有用です (例: Docker コンテナ内)。 |
| `--https-certificate` | path    | `/path/to/cert.pem`  | HTTPS のためのサーバー証明書。詳細は "java -jar selenium-server.jar info security" を実行してください。                                                                                                                                                               |
| `--https-private-key` | path    | `/path/to/key.pkcs8` | HTTPS のための秘密鍵。 詳細は "java -jar selenium-server.jar info security" を実行してください。                                                                                                                                                                      |
| `--max-threads`       | int     | `24`                 | リスナースレッドの最大数。デフォルトは、有効なプロセッサーの \* 3 です。                                                                                                                                                                                              |
| `--port`              | int     | `4444`               | リッスンポート。このパラメータは異なるコンポーネントによって使用されるため、デフォルトはありません。例えば、ルータ/ハブ/スタンドアロンは 4444 を使用し、ノードは 5555 を使用します。                                                                                  |

### SessionQueue

| オプション                  | 型     | 値/例                   | 概要                                                                                                                                |
| --------------------------- | ------ | ----------------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| `--sessionqueue`            | uri    | `http://localhost:1237` | 新規セッションキューサーバーのアドレス。                                                                                            |
| `-sessionqueue-host`        | string | `localhost`             | 新規セッションキューがリッスンするホスト。                                                                                          |
| `--sessionqueue-port`       | int    | `1234`                  | 新規セッションキューがリッスンするポート                                                                                            |
| `--session-request-timeout` | int    | `300`                   | タイムアウト(秒)。 新規セッションリクエストはキューに追加され、設定された時間以上キューに残っているリクエストはタイムアウトします。 |
| `--session-retry-interval`  | int    | `5`                     | リトライ間隔(秒)。すべてのスロットがビジーな場合、 新規セッションリクエストはこの時間の間隔をおいてからリトライされます。           |

### Sessions

| オプション        | 型     | 値/例                   | 概要                                           |
| ----------------- | ------ | ----------------------- | ---------------------------------------------- |
| `--sessions`      | uri    | `http://localhost:1234` | セッションマップサーバーのアドレス。           |
| `--sessions-host` | string | `localhost`             | セッションマップサーバーがリッスンするホスト。 |
| `--sessions-port` | int    | `1234`                  | セッションマップサーバーがリッスンするポート。 |

## 設定例

上記のオプションはすべて、Grid コンポーネントを起動する際に使用することができます。
Grid の適切な設定を模索するのに利用してください。

{{% pageinfo color="primary" %}}
[TOML ファイル]({{< ref "toml_options.md" >}}) を使用して Grid を設定することをおすすめします。
設定ファイルは読みやすく、コード管理できます。

必要に応じて TOML ファイルと CLI オプションを併用することができます。
{{% /pageinfo %}}

### コマンドラインフラグ

コマンドラインフラグとしてオプションを渡すには、適切なコンポーネントを特定し以下のテンプレートのようにします。

```
java -jar selenium-server-<version>.jar <component> --<option> value
```

#### スタドアロン、最大セッションとメインポートを設定する

```
java -jar selenium-server-<version>.jar standalone --max-sessions 4 --port 4444
```

#### ハブ、新規セッションリクエストのタイムアウト、メインポートを設定し、トレースを無効にする

```
java -jar selenium-server-<version>.jar hub --session-request-timeout 500 --port 3333 --tracing false
```

#### ノード、最大 4 セッション、デバッグログ、ポート 7777, FireFox と Edge のみ

```
java -jar selenium-server-<version>.jar node --max-sessions 4 --log-level "fine" --port 7777 --driver-implementation "firefox" --driver-implementation "edge"
```

#### ディストリビューター、セッションマップ・新規セッションキューの URL を指定、バスを無効にする

```
java -jar selenium-server-<version>.jar distributor --sessions http://localhost:5556 --sessionqueue http://localhost:5559 --bind-bus false
```

#### 特定ノードにカスタム capabilities を設定する

**重要:** カスタム capabilities は全てのノードに設定される必要があります。
また全てのセッションリクエストに含まれなければいけません。

##### ハブの起動

```
java -jar selenium-server-<version>.jar hub
```

##### customcap に `true` をセットしてノード A を起動する

```
java -jar selenium-server-<version>.jar node --detect-drivers false --driver-configuration display-name="Chrome (custom capability true)" max-sessions=1 stereotype='{"browserName":"chrome","gsg:customcap":true}' --port 6161
```

##### customcap に `false` をセットしてノード B を起動する

```
java -jar selenium-server-<version>.jar node --detect-drivers false --driver-configuration display-name="Chrome (custom capability true)" max-sessions=1 stereotype='{"browserName":"chrome","gsg:customcap":false}' --port 6262
```

##### ノード A とマッチ

```java
ChromeOptions options = new ChromeOptions();
options.setCapability("gsg:customcap", true);
WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
driver.get("https://selenium.dev");
driver.quit();
```

ノード B とマッチさせるにはカスタム capability を `false` に設定します。

#### Enabling Managed downloads by the Node

At times a test may need to access files that were downloaded by it on the Node. 
To retrieve such files, following can be done.

##### Start the Hub
```
java -jar selenium-server-<version>.jar hub
```

##### Start the Node with manage downloads enabled
```
java -jar selenium-server-<version>.jar node --enable-managed-downloads true
```
##### Set the capability at the test level

Tests that want to use this feature should set the capability `"se:downloadsEnabled"`to `true` 

```java
options.setCapability("se:downloadsEnabled", true);
```

##### How does this work

* The Grid infrastructure will try to match a session request with `"se:downloadsEnabled"` against ONLY those nodes which were started with `--enable-managed-downloads true`
* If a session is matched, then the Node automatically sets the required capabilities to let the browser know, as to where should a file be downloaded. 
* The Node now allows a user to: 
    * List all the files that were downloaded for a specific session and 
    * Retrieve a specific file from the list of files.
* The directory into which files were downloaded for a specific session gets automatically cleaned up when the session ends (or) timesout due to inactivity.

**Note: Currently this capability is ONLY supported on:** 

* `Edge`
* `Firefox` and
* `Chrome` browser

##### Listing files that can be downloaded for current session:

* The endpoint to `GET` from is `/session/<sessionId>/se/files`.
* The session needs to be active in order for the command to work.
* The raw response looks like below:

```json
{
  "value": {
    "names": [
      "Red-blue-green-channel.jpg"
    ]
  }
}
```

In the response the list of file names appear under the key `names`.


##### Dowloading a file:

* The endpoint to `POST` from is `/session/<sessionId>/se/files` with a payload of the form `{"name": "fileNameGoesHere}`
* The session needs to be active in order for the command to work.
* The raw response looks like below:

```json
{
  "value": {
    "filename": "Red-blue-green-channel.jpg",
    "contents": "Base64EncodedStringContentsOfDownloadedFileAsZipGoesHere"
  }
}
```

* The response blob contains two keys,
    * `filename` - The file name that was downloaded.
    * `contents` - Base64 encoded zipped contents of the file.
* The file contents are Base64 encoded and they need to be unzipped.

##### List files that can be downloaded

The below mentioned `curl` example can be used to list all the files that were downloaded by the current session in the Node, and which can be retrieved locally.

```bash
curl -X GET "http://localhost:4444/session/90c0149a-2e75-424d-857a-e78734943d4c/se/files"
```

A sample response would look like below:

```json
{
  "value": {
    "names": [
      "Red-blue-green-channel.jpg"
    ]
  }
}
```

##### Retrieve a downloaded file

Assuming the downloaded file is named `Red-blue-green-channel.jpg`, and using `curl`, the 
file could be downloaded with the following command:

```bash
curl -H "Accept: application/json" \
-H "Content-Type: application/json; charset=utf-8" \
-X POST -d '{"name":"Red-blue-green-channel.jpg"}' \
"http://localhost:4444/session/18033434-fa4f-4d11-a7df-9e6d75920e19/se/files"
```

A sample response would look like below:

```json
{
  "value": {
    "filename": "Red-blue-green-channel.jpg",
    "contents": "UEsDBBQACAgIAJpagVYAAAAAAAAAAAAAAAAaAAAAUmVkLWJsAAAAAAAAAAAAUmVkLWJsdWUtZ3JlZW4tY2hhbm5lbC5qcGdQSwUGAAAAAAEAAQBIAAAAcNkAAAAA"
  }
}
```

##### Complete sample code in Java

Below is an example in Java that does the following:

* Sets the capability to indicate that the test requires automatic managing of downloaded files. 
* Triggers a file download via a browser.
* Lists the files that are available for retrieval from the remote node (These are essentially files that were downloaded in the current session)
* Picks one file and downloads the file from the remote node to the local machine.

```java
import static java.util.Collections.singletonMap;
import static org.openqa.selenium.remote.http.Contents.string;

import java.io.File;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.json.TypeToken;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.Contents;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpMethod;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;

public class DownloadsSample {

  public static void main(String[] args) throws Exception {
    // Assuming the Grid is running locally.
    URL gridUrl = new URL("http://localhost:4444");
    RemoteWebDriver driver = new RemoteWebDriver(gridUrl, firefoxOptions());
    try {
      demoFileDownloads(driver, gridUrl);
    } finally {
      driver.quit();
    }
  }

  private static void demoFileDownloads(RemoteWebDriver driver, URL gridUrl) throws Exception {
    // Make sure the following directory exists on your machine
    File dirToCopyTo = new File("/usr/downloads/file");
    driver.get("http://the-internet.herokuapp.com/download");
    WebElement element = driver.findElement(By.cssSelector(".example a"));
    element.click();

    // The download happens in a remote Node, which makes it difficult to know when the file
    // has been completely downloaded. For demonstration purposes, this example uses a
    // 10-second sleep which should be enough time for a file to be downloaded.
    // We strongly recommend to avoid hardcoded sleeps, and ideally, to modify your
    // application under test so it offers a way to know when the file has been completely
    // downloaded.
    TimeUnit.SECONDS.sleep(10);

    //This is the endpoint which will provide us with list of files to download and also to
    //let us download a specific file.
    String uri = String.format("/session/%s/se/files", driver.getSessionId());

    String fileToDownload = null;

    try (HttpClient client = HttpClient.Factory.createDefault().createClient(gridUrl)) {
      // To list all files that are were downloaded on the remote node for the current session
      // we trigger GET request.
      HttpRequest request = new HttpRequest(HttpMethod.GET, uri);
      HttpResponse response = client.execute(request);
      String text = string(response);
      Type responseType = new TypeToken<Map<String, Map<String, List<String>>>>() {
      }.getType();
      Map<String, Map<String, List<String>>> map = new Json().toType(text, responseType);
      Map<String, List<String>> parsedResponse = map.get("value");
      for (String eachFile : parsedResponse.get("names")) {
        if (fileToDownload == null) {
          // Let's say there were "n" files downloaded for the current session, we would like
          // to retrieve ONLY the first file.
          fileToDownload = eachFile;
        }
      }
    }
    try (HttpClient client = HttpClient.Factory.createDefault().createClient(gridUrl)) {
      // To retrieve a specific file from one or more files that were downloaded by the current session
      // on a remote node, we use a POST request.

      HttpRequest request = new HttpRequest(HttpMethod.POST, uri);
      request.setContent(Contents.asJson(singletonMap("name", fileToDownload)));
      HttpResponse response = client.execute(request);
      String text = string(response);
      Type responseType = new TypeToken<Map<String, Map<String, String>>>() {
      }.getType();

      Map<String, Map<String, String>> map = new Json().toType(text, responseType);
      Map<String, String> parsedResponse = map.get("value");
      // The returned map would contain 2 keys,
      // filename - This represents the name of the file (same as what was provided by the test)
      // contents - Base64 encoded String which contains the zipped file.
      String encodedContents = parsedResponse.get("contents");

      //The file contents would always be a zip file and has to be unzipped.
      Zip.unzip(encodedContents, dirToCopyTo);
      System.out.println("The file which was "
          + "downloaded in the node is now available in the directory: "
          + dirToCopyTo.getAbsolutePath());
    }
  }

  private static FirefoxOptions firefoxOptions() {
    FirefoxOptions options = new FirefoxOptions();
    // This capability tells the Grid, that this test should be routed ONLY to a node that can
    // auto manage downloads.
    options.setCapability("se:downloadsEnabled", true);
    // Options specific for Firefox to avoid prompting a dialog for downloads. They might
    // change in the future, so please refer to the Firefox documentation for up to date details.
    options.addPreference("browser.download.manager.showWhenStarting", false);
    options.addPreference("browser.helperApps.neverAsk.saveToDisk",
        "images/jpeg, application/pdf, application/octet-stream");
    options.addPreference("pdfjs.disabled", true);
    return options;
  }
}
```
