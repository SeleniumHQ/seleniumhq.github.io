---
title: "Toml オプション"
linkTitle: "Toml オプション"
weight: 3
description: Tomlファイルを使用したGridの設定例.
aliases: [
"/ja/documentation/grid/configuring_components/toml_options/"
]
---

[CLI オプション]({{< ref "cli_options.md" >}}) に記載されている全てのオプションは
[TOML](https://github.com/toml-lang/toml) ファイルでも設定ができます。
このページでは異なる Grid コンポーネントの設定例を紹介します。

{{% pageinfo color="primary" %}}
オプションが変更、または追加されたが文書化されていない場合、
このドキュメントは古くなる可能性があることに注意してください。
もしそのような状況を見つけたら、["構成ヘルプ"]({{< ref "help.md" >}})を確認し、
ドキュメントを更新するプルリクエストを気軽に送ってください。
{{% /pageinfo %}}

## 概要

Selenium Grid は[TOML](https://github.com/toml-lang/toml)フォーマットの設定ファイルを使用します。
設定ファイルはセクションで構成され、各セクションはオプションとその値が設定されています。

詳しい使い方は[TOML ドキュメント](https://toml.io/ja/)を参照してください。
パースエラーに遭遇した場合、[TOML リンター](https://www.toml-lint.com/)を使って検証してください。

一般的な設定の構成は以下のパターンです:

```toml
[section1]
option1="value"

[section2]
option2=["value1","value2"]
option3=true
```

TOML ファイルで設定された Grid コンポーネントを起動するには以下のように起動できます:

```
java -jar selenium-server-<version>.jar <component> --config /path/to/file/<file-name>.toml
```

### スタンドアロン

ポート 4449 で動作し、新規セッションリクエストのタイムアウトが 500 秒のスタンドアロンサーバー。

```toml
[server]
port = 4449

[sessionqueue]
session-request-timeout = 500
```

### 特定のブラウザとセッションの上限

Firefox と Chrome のみがデフォルトで有効になっているスタンドアロンサーバー、またはノード

```toml
[node]
drivers = ["chrome", "firefox"]
max-sessions = 3
```

### ドライバーのカスタマイズと設定

Firefox Beta や Nightly のような、異なるブラウザのバージョンを持つことができるカスタマイズされた
ドライバを用いた、スタンドアロン、またはノード。

```toml
[node]
detect-drivers = false
[[node.driver-configuration]]
max-sessions = 100
display-name = "Firefox Nightly"
stereotype = "{\"browserName\": \"firefox\", \"browserVersion\": \"93\", \"platformName\": \"MAC\", \"moz:firefoxOptions\": {\"binary\": \"/Applications/Firefox Nightly.app/Contents/MacOS/firefox-bin\"}}"
[[node.driver-configuration]]
display-name = "Chrome Beta"
stereotype = "{\"browserName\": \"chrome\", \"browserVersion\": \"94\", \"platformName\": \"MAC\", \"goog:chromeOptions\": {\"binary\": \"/Applications/Google Chrome Beta.app/Contents/MacOS/Google Chrome Beta\"}}"
[[node.driver-configuration]]
display-name = "Chrome Dev"
stereotype = "{\"browserName\": \"chrome\", \"browserVersion\": \"95\", \"platformName\": \"MAC\", \"goog:chromeOptions\": {\"binary\": \"/Applications/Google Chrome Dev.app/Contents/MacOS/Google Chrome Dev\"}}"
webdriver-executable = '/path/to/chromedriver/95/chromedriver'
```

### Docker を利用したスタンドアロン、またはノード

Docker コンテナでセッションを実行できるスタンドアロン、またはノードサーバー。
ドライバを検出を無効にし、最大 2 つの同時セッションを持ちます。
ステレオタイプは、Docker イメージにマッピングされる必要があり、
Docker デーモンが http/tcp で公開されている必要があります。
また、`devices`プロパティを用いて、ホスト上でアクセス可能なデバイスファイルを、コンテナで利用できるようにすることも可能です。
Docker デバイスをマッピングする詳しい方法は[docker](https://docs.docker.com/engine/reference/commandline/run/#add-host-device-to-container---device)
を参照してください。

```toml
[node]
detect-drivers = false
max-sessions = 2

[docker]
configs = [
    "selenium/standalone-chrome:93.0", "{\"browserName\": \"chrome\", \"browserVersion\": \"91\"}",
    "selenium/standalone-firefox:92.0", "{\"browserName\": \"firefox\", \"browserVersion\": \"92\"}"
]
#Optionally define all device files that should be mapped to docker containers
#devices = [
#    "/dev/kvm:/dev/kvm"
#]
url = "http://localhost:2375"
video-image = "selenium/video:latest"
```

### WebDriver をサポートするサービスエンドポイントへのコマンド中継

WebDriver をサポートする外部サービスを Selenium Grid に接続すると便利です。
例えばクラウドプロバイダーや Appium サーバーなどです。
Grid はローカルに存在しないプラットフォームやバージョンなどを幅広くカバーできるようになります。

以下は Appium サーバーを Grid に接続する例です。

```toml
[node]
detect-drivers = false

[relay]
# Default Appium/Cloud server endpoint
url = "http://localhost:4723/wd/hub"
status-endpoint = "/status"
# Stereotypes supported by the service. The initial number is "max-sessions", and will allocate
# that many test slots to that particular configuration
configs = [
  "5", "{\"browserName\": \"chrome\", \"platformName\": \"android\", \"appium:platformVersion\": \"11\"}"
]
```

### Basic 認証の有効化

ルーター、ハブ、スタンドアロンにユーザー名とパスワードを設定することで、
Basic 認証で Grid を保護することができます。
このユーザーとパスワードは、Grid UI を読み込む時や
新しいセッションを開始する時に必要になります。

```toml
[router]
username = "admin"
password = "myStrongPassword"
```

Java でユーザーとパスワードを使ってセッションを開始する方法の例です。

```java
URL gridUrl = new URL("http://admin:myStrongPassword@localhost:4444");
RemoteWebDriver webDriver = new RemoteWebDriver(gridUrl, new ChromeOptions());
```

### 特定のノードにマッチするカスタム capabilities の設定

**重要**: カスタム capabilities は全てのノードで設定する必要があります。
また全てのセッションリクエストで常に含まれる必要があります。

```toml
[node]
detect-drivers = false

[[node.driver-configuration]]
display-name = "firefox"
stereotype = '{"browserName": "firefox", "platformName": "macOS", "browserVersion":"96", "networkname:applicationName":"node_1", "nodename:applicationName":"app_1" }'
max-sessions = 5
```

Java でノードにマッチさせる方法の例です。

```java
FirefoxOptions options = new FirefoxOptions();
options.setCapability("networkname:applicationName", "node_1");
options.setCapability("nodename:applicationName", "app_1");
options.setBrowserVersion("96");
options.setPlatformName("macOS");
WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
driver.get("https://selenium.dev");
driver.quit();
```

### Enabling Managed downloads by the Node.

The Node can be instructed to manage downloads automatically. This will cause the Node to save all files that were downloaded for a particular session into a temp directory, which can later be retrieved from the node.
To turn this capability on, use the below configuration:

```toml
[node]
enable-managed-downloads = true
```

Refer to the [CLI section]({{< ref "cli_options.md#complete-sample-code-in-java" >}}) for a complete example.