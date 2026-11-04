# Edge固有の機能

> これらは、Microsoft Edgeブラウザに固有のCapabilityです。

URL: https://www.selenium.dev/ja/documentation/webdriver/browsers/edge/

---

Microsoft EdgeはChromiumで実装されており、サポートされている最も古いバージョンはv79です。
Chromeと同様に、edgedriverのメジャー バージョン番号は、Edgeブラウザのメジャーバージョンと一致する必要があります。

[Chromeページ](https://www.selenium.dev/ja/documentation/webdriver/browsers/chrome/index.md) にあるすべての機能とオプションは、Edgeでも機能します。

## オプション

すべてのブラウザに共通する機能は[オプション ページ](https://www.selenium.dev/ja/documentation/webdriver/drivers/options/index.md)に記載されています。

Chromiumに特有の機能は、Googleの[Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)ページに文書化されています。

基本的な定義済みオプションを使用して Edgeセッションを開始すると、次のようになります。

**Java**

```java

```

**Python**

```python
options = webdriver.EdgeOptions()
options.add_argument("--no-sandbox")
driver = webdriver.Edge(options=options)
```

**CSharp**

```csharp
catch (IOException)
{
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.edge
options.args << '--no-sandbox'
@driver = Selenium::WebDriver.for :edge, options: options
```

**JavaScript**

```javascript
let options = new edge.Options().addArguments('--no-sandbox');
driver = new Builder()
  .forBrowser(Browser.EDGE)
  .setEdgeOptions(options)
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 引数

`args` パラメータは、ブラウザを起動する際に使用されるコマンドラインスイッチのリストです。これらの引数を調査するための優れたリソースが2つあります：
* [ツール用のChromeフラグ](https://github.com/GoogleChrome/chrome-launcher/blob/main/docs/chrome-flags-for-tools.md)
* [Chromiumコマンドラインスイッチのリスト](https://peter.sh/experiments/chromium-command-line-switches/)

一般的に使用される引数には、`--start-maximized` および `--headless=new` が含まれます。 and `--user-data-dir=...`

オプションに引数を追加します。

**Java**

```java
public void arguments() {
```

**Python**

```python

```

**CSharp**

```csharp
driver = new EdgeDriver(options);
```

**Ruby**

```ruby
options.args << '--start-maximized'
```

**JavaScript**

```javascript
.setEdgeOptions(getDefaultEdgeOptions().addArguments('--headless=new'))
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 指定された場所でブラウザを起動する

`binary` パラメータは、使用するブラウザの代替位置のパスを指定します。このパラメータを使用すると、chromedriverを利用してさまざまなChromiumベースのブラウザを操作できます。

オプションにブラウザの場所を追加する：

**Java**

```java
public void setBrowserLocation() {
```

**Python**

```python

```

**CSharp**

```csharp
driver = new EdgeDriver(options);
```

**Ruby**

```ruby
options.binary = edge_location
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 拡張機能を追加する

`extensions` パラメータは、crxファイルを受け入れます。展開されたディレクトリについては、`load-extension` 引数を使用してください。このことは[この投稿](https://chromedriver.chromium.org/extensions)で言及されています。

オプションに拡張機能を追加する：

**Java**

```java
Path path = Paths.get("src/test/resources/extensions/webextensions-selenium-example.crx");
```

**Python**

```python

```

**CSharp**

```csharp

```

**Ruby**

```ruby
options.add_extension(extension_file_path)
```

**JavaScript**

```javascript
.setEdgeOptions(getDefaultEdgeOptions().addExtensions(['./test/resources/extensions/webextensions-selenium-example.crx']))
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ブラウザを開いたままにする

`detach` パラメータをtrue に設定すると、プロセスが終了した後でもブラウザが開いたままになります。ただし、quit コマンドがドライバーに送信されない限り、ブラウザは開いたままになります。

**Java**

**注意**: これはすでにJavaのデフォルトの動作です。

**Python**

```python

```

**CSharp**

**注意**: これはすでに.NETのデフォルトの動作です。

**Ruby**

```ruby
options.detach = true
```

**JavaScript**

```javascript
.setEdgeOptions(getDefaultEdgeOptions().detachDriver(true))
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 引数を除外する

MSEdgedriverには、ブラウザを起動するために使用されるいくつかのデフォルト引数があります。それらの引数を追加したくない場合は、`excludeSwitches`に渡してください。一般的な例は、ポップアップブロッカーを再度オンにすることです。デフォルト引数の完全なリストは[Chromium Source Code](https://source.chromium.org/chromium/chromium/src/+/main:chrome/test/chromedriver/chrome_launcher.cc)から解析できます。

オプションに除外された引数を設定する:

**Java**

```java
public void excludeSwitches() {
```

**Python**

```python

```

**CSharp**

```csharp
Assert.AreEqual("Content injected by webextensions-selenium-example", injected.Text);
```

**Ruby**

```ruby
options.exclude_switches << 'disable-popup-blocking'
```

**JavaScript**

```javascript
.setEdgeOptions(getDefaultEdgeOptions().excludeSwitches('enable-automation'))
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## サービス

デフォルトのサービスオブジェクトを作成するための例や、ドライバの場所とポートを設定する例は、[Driver Service](https://www.selenium.dev/ja/documentation/webdriver/drivers/service/index.md) ページにあります。

### ログ出力

ドライバのログを取得することは、問題をデバッグするのに役立ちます。サービスクラスを使用すると、ログの出力先を指定できます。ユーザーがどこかにログを指示しない限り、ログ出力は無視されます。

#### ファイル出力

特定のファイルに保存するようにログ出力を変更するには、以下のようにします:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
@Test
```

**注意**: Javaでもシステムプロパティを使用してファイル出力を設定できます:<br>
プロパティキー: `EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY`<br>
プロパティ値: ログファイルのパスを表す文字列

**Python**

```python
def test_log_to_file(log_path):
```

**CSharp**

```csharp
}
```

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.log = file_name
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

#### コンソール出力

ログ出力をコンソールにSTDOUTとして表示するには:

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
File logLocation = getTempFile("logsToConsole", ".log");
```

**注**: Javaでは、システムプロパティを使用してコンソール出力を設定することもできます。<br>
プロパティキー: `EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY`<br>
プロパティ値:`DriverService.LOG_STDOUT` または  `DriverService.LOG_STDERR`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
def test_log_to_stdout(capfd):
```

**CSharp**

_Not available in this language._

**Ruby**

`$stdout` と `$stderr`はどちらも有効な値です。
_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.log = $stdout
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ログレベル
利用可能なログレベルは6つあります：`ALL`, `DEBUG`, `INFO`, `WARNING`, `SEVERE`および  `OFF`。`--verbose` は `--log-level=ALL` と同等であり、`--silent` は`--log-level=OFF`と同等です。したがって、この例ではログレベルを一般的に設定しています：

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
File logLocation = getTempFile("logsWithLevel", ".log");
System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY, logLocation.getAbsolutePath());
```

**注意**: Javaでは、システムプロパティを使用してログレベルを設定することもできます：<br>
プロパティキー: `EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY`<br>
プロパティ値:`ChromiumDriverLogLevel` 列挙型の文字列表現

**Python**

```python
def test_log_level(log_path):
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.args << '--log-level=DEBUG'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ログファイルの機能
ファイルにログを記録する際にのみ利用可能な2つの機能があります：
* ログの追加
* 読みやすいタイムスタンプ

これらを使用するには、ログパスとログレベルも明示的に指定する必要があります。ログ出力はプロセスではなくドライバによって管理されるため、若干の違いが見られることがあります。

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
System.setProperty(
    EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY, ChromiumDriverLogLevel.DEBUG.toString());
```

**注意**: Javaでは、これらの機能をSystem Propertyによって切り替えることもできます：<br>
プロパティキー:`EdgeDriverService.EDGE_DRIVER_APPEND_LOG_PROPERTY` および `EdgeDriverService.EDGE_DRIVER_READABLE_TIMESTAMP`<br>
プロパティ値: `"true"` または  `"false"`

**Python**

```python
def test_log_features(log_path):
```

**CSharp**

_Not available in this language._

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.args << '--append-log'
service.args << '--readable-timestamp'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ビルドチェックの無効化

Edge ブラウザとmsedgedriverのバージョンは一致する必要があり、一致しない場合はドライバにエラーが表示されます。ビルドチェックを無効にすると、任意のバージョンのEdgeでドライバを強制的に使用できます。
この機能はサポートされていないことに注意してください。バグは調査されません。

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY,
ChromiumDriverLogLevel.WARNING.toString());
```

**注**: Javaでは、システムプロパティを使用してビルドチェックを無効にすることもできます：<br>
プロパティキー:`EdgeDriverService.EDGE_DRIVER_DISABLE_BUILD_CHECK`<br>
プロパティ値: `"true"` または  `"false"`

**Python**

```python
def test_build_checks(log_path):
```

**CSharp**

```csharp

```

**Ruby**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```ruby
service.args << '--disable-build-check'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Internet Explorer Compatibility モード

Microsoft Edge は、Internet Explorer ドライバークラスを Microsoft Edgeと組み合わせて使用する 
"Internet Explorer 互換モード"で動かすことができます。 
詳細については、[Internet Explorerページ](https://www.selenium.dev/ja/documentation/webdriver/browsers/internet_explorer/index.md)を参照してください。

## 特別な機能
一部のブラウザは、それぞれ特有の追加機能を実装しています。

### キャスティング

Edge を使用して Chrome Cast デバイスを操作し、タブを共有することができます。

**Java**

```java
EdgeDriver driver = new EdgeDriver(getDefaultEdgeOptions());

List<Map<String, String>> sinks = driver.getCastSinks();
if (!sinks.isEmpty()) {
  String sinkName = sinks.get(0).get("name");
  driver.startTabMirroring(sinkName);
```

**Python**

```python
try:
    sinks = driver.get_sinks()
    if sinks:
        sink_name = sinks[0]['name']
        driver.start_tab_mirroring(sink_name)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
sinks = @driver.cast_sinks
unless sinks.empty?
  device_name = sinks.first['name']
  @driver.start_cast_tab_mirroring(device_name)
  expect { @driver.stop_casting(device_name) }.not_to raise_exception
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ネットワーク条件

さまざまなネットワーク条件をシミュレートすることができます。

**Java**

```java
@Test
public void setNetworkConditions() {
  driver = new EdgeDriver(getDefaultEdgeOptions());

  ChromiumNetworkConditions networkConditions = new ChromiumNetworkConditions();
  networkConditions.setOffline(false);
  networkConditions.setLatency(java.time.Duration.ofMillis(20)); // 20 ms of latency
```

**Python**

```python

network_conditions = {
    "offline": False,
    "latency": 20,  # 20 ms of latency
    "download_throughput": 2000 * 1024 / 8,  # 2000 kbps
    "upload_throughput": 2000 * 1024 / 8,    # 2000 kbps
}
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
@driver.network_conditions = {offline: false, latency: 100, throughput: 200}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ログ

**Java**

```java
consoleLogButton.click();
```

**Python**

```python

```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
sleep 1
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 権限

**Java**

```java
EdgeDriver driver = new EdgeDriver(getDefaultEdgeOptions());
```

**Python**

```python

```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
@driver.navigate.to 'https://www.selenium.dev/selenium/web/'
@driver.add_permission('camera', 'denied')
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### DevTools

EdgeでDevToolsを使用する際の詳細については、[Chrome DevTools]セクションを参照してください。
