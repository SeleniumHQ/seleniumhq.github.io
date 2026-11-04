# Chrome固有の機能

> これらは、Google Chromeブラウザに特有の機能と機能です。

URL: https://www.selenium.dev/ja/documentation/webdriver/browsers/chrome/

---

これらは、Google Chromeブラウザに特有の機能と機能です。
デフォルトでは、Selenium 4はChrome v75以上と互換性があります。Chromeブラウザのバージョンとchromedriverのバージョンは、メジャーバージョンが一致する必要があることに注意してください。

## Options

すべてのブラウザに共通する機能は [オプション ページ](https://www.selenium.dev/ja/documentation/webdriver/drivers/options/index.md)に記載されています。

ChromeおよびChromiumに特有の機能は、Googleの [Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)のページにドキュメントされています。

基本的に定義されたオプションでChromeセッションを開始する場合は、次のようになります：

**Java**

```java
ChromeOptions options = getDefaultChromeOptions();
driver = new ChromeDriver(options);
```

**Python**

```python
options = webdriver.ChromeOptions()
options.add_argument("--no-sandbox")
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
var options = new ChromeOptions();
driver = new ChromeDriver(options);
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
@driver = Selenium::WebDriver.for :chrome, options: options
```

**JavaScript**

```javascript
const Options = new Chrome.Options();
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(Options)
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 引数

`args` パラメータは、ブラウザを起動する際に使用するコマンドラインスイッチのリストです。これらの引数を調査するための優れたリソースが2つあります：
* [Chromeツール用フラグ](https://github.com/GoogleChrome/chrome-launcher/blob/main/docs/chrome-flags-for-tools.md)
* [Chromiumコマンドラインスイッチの一覧](https://peter.sh/experiments/chromium-command-line-switches/)

一般的に使用されるargsには以下が含まれます：`--start-maximized`, `--headless=new` and `--user-data-dir=...`

オプションに引数を追加：

**Java**

```java
options.addArguments("--start-maximized");
```

**Python**

```python
options.add_argument("--start-maximized")
```

**CSharp**

```csharp
options.AddArgument("--start-maximized");
```

**Ruby**

```ruby
options.args << '--start-maximized'
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.addArguments('--headless=new'))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 指定したロケーションでブラウザを起動する

`binary`パラメーターは、使用するブラウザの別のロケーションのパスを取ります。 
このパラメーターを使用すると、chromedriver を使用して、さまざまな Chromium ベースのブラウザを駆動できます。

オプションにブラウザのロケーションを追加します。

**Java**

```java
options.setBinary(getChromeLocation());
```

**Python**

```python
options.binary_location = chrome_bin
```

**CSharp**

```csharp
options.BinaryLocation = await GetChromeLocationAsync();
```

**Ruby**

```ruby
options.binary = chrome_location
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setChromeBinaryPath(`Path to chrome binary`))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 拡張機能を追加する

`extensions` パラメーターはcrxファイルを受け入れます

The `extensions` パラメータはcrxファイルを受け入れます。解凍されたディレクトリについては、代わりに `load-extension` 引数を使用してください。[この投稿](https://chromedriver.chromium.org/extensions)で述べたように。

オプションに拡張機能を追加します。

**Java**

```java
ChromeOptions options = getDefaultChromeOptions();
options.enableBiDi();
options.addArguments("--remote-debugging-pipe");
options.addArguments("--enable-unsafe-extension-debugging");
driver = new ChromeDriver(options);

Path path = Paths.get("src/test/resources/extensions/selenium-example");
WebExtension extension = new WebExtension(driver);
ExtensionPath extensionPath = new ExtensionPath(path.toString());
InstallExtensionParameters parameters = new InstallExtensionParameters(extensionPath);
extension.install(parameters);
```

**Python**

```python
options.add_extension(extension_file_path)
```

**CSharp**

```csharp
var options = new ChromeOptions();
options.UseWebSocketUrl = true;
options.AddArgument("--remote-debugging-pipe");
options.AddArgument("--enable-unsafe-extension-debugging");

driver = new ChromeDriver(options);

var baseDir = AppDomain.CurrentDomain.BaseDirectory;
var extensionDir = Path.GetFullPath(Path.Combine(baseDir, "../../../Extensions/webextensions-selenium-example"));

var bidi = await driver.AsBiDiAsync();
await bidi.WebExtension.InstallAsync(new ExtensionPath(extensionDir));
```

**Ruby**

```ruby
options.add_extension(extension_file_path)
```

**JavaScript**

```javascript
const options = new Chrome.Options();
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.addExtensions(['./test/resources/extensions/webextensions-selenium-example.crx']))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ブラウザを開いたままにする

`detach` パラメータをtrueに設定すると、ドライバープロセスが終了した後もブラウザを開いたままにできます。

**Java**

**注意**: これはすでにJavaのデフォルトの動作です。

**Python**

```python
options.add_experimental_option("detach", True)
```

**CSharp**

**注意**: これはすでに.NETのデフォルトの動作です。

**Ruby**

```ruby
options.detach = true
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.detachDriver(true))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### 引数を除外する

Chrome はさまざまな引数を追加します。
これらの引数を追加したくない場合は、それらを `excludeSwitches` に渡します。 
一般的な例は、ポップアップブロッカーをオンに設定することです。

デフォルトの引数の完全なリストは、
[Chromium Source Code](https://source.chromium.org/chromium/chromium/src/+/main:chrome/test/chromedriver/chrome_launcher.cc)から解析できます。

オプションに除外された引数を設定します。

**Java**

```java
options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
```

**Python**

```python
options.add_experimental_option('excludeSwitches', ['disable-popup-blocking'])
```

**CSharp**

```csharp
options.AddExcludedArgument("disable-popup-blocking");
```

**Ruby**

```ruby
options.exclude_switches << 'disable-popup-blocking'
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.excludeSwitches('enable-automation'))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## サービス

デフォルトのServiceオブジェクトを作成するための例や、ドライバーの場所とポートを設定する方法は、[Driver Service](https://www.selenium.dev/ja/documentation/webdriver/drivers/service/index.md)ページにあります。

### ログ出力

ドライバーログを取得することは、問題のデバッグに役立ちます。Serviceクラスを使用すると、ログの出力先を指定できます。ユーザーがどこかにログを指示しない限り、ログ出力は無視されます。

#### ファイル出力

ログ出力を特定のファイルに保存するように変更するには：

**Java**

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withLogFile(logLocation).build();
```

**注意**: Javaでは、システムプロパティによってファイル出力を設定することもできます：<br>
プロパティキー: `ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY`<br>
プロパティ値: ログファイルへのパスを表す文字列

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(log_output=log_path)
```

**CSharp**

```csharp
service.LogPath = GetLogLocation();
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

ログ出力をコンソールにSTDOUTとして表示するように変更するには：

**Java**

_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withLogOutput(System.out).build();
```

**注意**: Javaでは、システムプロパティによってコンソール出力を設定することもできます。<br>
プロパティキー: `ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY`<br>
プロパティ値: `DriverService.LOG_STDOUT` または `DriverService.LOG_STDERR`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(log_output=subprocess.STDOUT)
```

**CSharp**

_Not available in this language._

**Ruby**

`$stdout` と `$stderr` はどちらも有効な値です。
_Requires [Selenium v4.10](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.10.0) or later._

```ruby
service.log = $stdout
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ログレベル
利用可能なログレベルは6つあります：`ALL`, `DEBUG`, `INFO`, `WARNING`, `SEVERE`, そして `OFF`。`--verbose` は `--log-level=ALL` と同等であり、`--silent` は `--log-level=OFF`と同等であることに注意してください。このため、この例ではログレベルを一般的に設定しています：

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withLogLevel(ChromiumDriverLogLevel.DEBUG).build();
```

**注意**: Javaでは、システムプロパティによってログレベルを設定することもできます：<br>
プロパティキー: `ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY`<br>
プロパティ値: `ChromiumDriverLogLevel` 列挙型の文字列表現

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(service_args=['--log-level=DEBUG'], log_output=subprocess.STDOUT)
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

### ログファイル機能
ファイルにログを記録する際にのみ利用できる2つの機能があります：
* ログの追加
* 読みやすいタイムスタンプ

これらを使用するには、ログパスとログレベルも明示的に指定する必要があります。ログ出力はプロセスではなくドライバーによって管理されるため、若干の違いが見られる場合があります。

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withAppendLog(true).withReadableTimestamp(true).build();
```

**注意**: Javaでは、これらの機能をシステムプロパティによって切り替えることもできます：<br>
プロパティキー: `ChromeDriverService.CHROME_DRIVER_APPEND_LOG_PROPERTY` および`ChromeDriverService.CHROME_DRIVER_READABLE_TIMESTAMP`<br>
プロパティ値: `"true"` または `"false"`

**Python**

```python
service = webdriver.ChromeService(service_args=['--append-log', '--readable-timestamp'], log_output=log_path)
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

ChromedriverとChromeブラウザのバージョンは一致する必要があり、一致しない場合、ドライバーはエラーを返します。ビルドチェックを無効にすると、任意のバージョンのChromeでドライバーを強制的に使用できます。ただし、これはサポートされていない機能であり、バグは調査されません。

**Java**

_Requires [Selenium v4.8](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.8.0) or later._

```java
ChromeDriverService service =
    new ChromeDriverService.Builder().withBuildCheckDisabled(true).build();
```

**注意**: Javaでは、システムプロパティによってビルドチェックを無効にすることもできます：<br>
プロパティキー: `ChromeDriverService.CHROME_DRIVER_DISABLE_BUILD_CHECK`<br>
プロパティ値: `"true"` または `"false"`

**Python**

_Requires [Selenium v4.11](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.11.0) or later._

```python
service = webdriver.ChromeService(service_args=['--disable-build-check'], log_output=subprocess.STDOUT)
```

**CSharp**

```csharp
service.DisableBuildCheck = true;
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

## 特別な機能

一部のブラウザは、それぞれに特有の追加機能を実装しています。

### キャスティング

Chrome Castデバイスを操作することができ、タブの共有も含まれます。

**Java**

```java
List<Map<String, String>> sinks = driver.getCastSinks();
if (!sinks.isEmpty()) {
  String sinkName = sinks.get(0).get("name");
  driver.startTabMirroring(sinkName);
  driver.stopCasting(sinkName);
}
```

**Python**

```python
sinks = driver.get_sinks()
if sinks:
    sink_name = sinks[0]['name']
    driver.start_tab_mirroring(sink_name)
    driver.stop_casting(sink_name)
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

### ネットワークの状態

さまざまなネットワークの状態をシミュレートできます。

以下の例はローカルWebDriver用です。リモートWebDriverについては、[リモートWebDriver](https://www.selenium.dev/ja/documentation/webdriver/drivers/remote_webdriver/index.md)ページを参照してください。

**Java**

```java
ChromiumNetworkConditions networkConditions = new ChromiumNetworkConditions();
networkConditions.setOffline(false);
networkConditions.setLatency(java.time.Duration.ofMillis(20)); // 20 ms of latency
networkConditions.setDownloadThroughput(2000 * 1024 / 8); // 2000 kbps
networkConditions.setUploadThroughput(2000 * 1024 / 8);   // 2000 kbps

((ChromeDriver) driver).setNetworkConditions(networkConditions);
```

**Python**

```python
network_conditions = {
    "offline": False,
    "latency": 20,  # 20 ms of latency
    "download_throughput": 2000 * 1024 / 8,  # 2000 kbps
    "upload_throughput": 2000 * 1024 / 8,    # 2000 kbps
}
driver.set_network_conditions(**network_conditions)
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
LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
```

**Python**

```python
logs = driver.get_log("browser")
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
logs = @driver.logs.get(:browser)
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### パーミッション

**Java**

```java
driver.setPermission("camera", "denied");
```

**Python**

```python
driver.set_permissions('camera', 'denied')
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
@driver.add_permission('camera', 'denied')
@driver.add_permissions('clipboard-read' => 'denied', 'clipboard-write' => 'prompt')
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### デベロッパー ツール

Chromeデベロッパーツールの使用に関する詳細については、[Chromeデベロッパー ツール](https://www.selenium.dev/ja/documentation/webdriver/bidi/cdp/index.md) セクションを参照してください。
