# リモートWebDriver

URL: https://www.selenium.dev/ja/documentation/webdriver/drivers/remote_webdriver/

---

Seleniumは、リモートコンピュータ上でブラウザを自動化することができます。これには、リモートコンピュータ上で [Selenium Grid](https://www.selenium.dev/ja/documentation/grid/index.md) が実行されている必要があります。コードを実行するコンピュータはクライアントコンピュータと呼ばれ、ブラウザとドライバーがあるコンピュータはリモートコンピュータまたは時々エンドノードと呼ばれます。Seleniumテストをリモートコンピュータに向けるには、Remote WebDriverクラスを使用し、そのマシンのグリッドのポートを含むURLを渡す必要があります。グリッドの設定方法については、グリッドのドキュメントを参照してください。 

## 基本的な例

ドライバーは、コマンドを送信する場所と、リモートコンピュータ上で開始するブラウザを知る必要があります。そのため、アドレスとオプションインスタンスの両方が必要です。

**Java**

```java
ChromeOptions options = getDefaultChromeOptions();
driver = new RemoteWebDriver(gridUrl, options);
```

**Python**

```python
options = get_default_chrome_options()
driver = webdriver.Remote(command_executor=grid_url, options=options)
```

**CSharp**

```csharp
var options = new ChromeOptions();
driver = new RemoteWebDriver(GridUrl, options);
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
driver = Selenium::WebDriver.for :remote, url: grid_url, options: options
```

**JavaScript**

```javascript
const options = new Chrome.Options().addArguments('--no-sandbox')
driver = new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).usingServer(gridUrl).build()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## アップロード {#uploads}

[ファイルのアップロード]() は、リモートWebDriverセッションではより複雑です。アップロードしたいファイルはコードを実行しているコンピュータ上にあることが多いですが、リモートコンピュータ上のドライバーはそのローカルファイルシステム上で指定されたパスを探しています。この解決策として、ローカルファイルディテクターを使用します。これを設定すると、Seleniumはファイルをパッケージ化し、リモートマシンに送信するため、ドライバーはその参照を認識できるようになります。一部のバインディングでは、デフォルトで基本的なローカルファイルディテクターが含まれており、すべてのバインディングでカスタムファイルディテクターを設定できます。

**Java**

Javaにはデフォルトでローカルファイルディテクターが含まれていないため、アップロードを行う際には必ず追加する必要があります。

```java
((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
fileInput.sendKeys(uploadFile.getAbsolutePath());
driver.findElement(By.id("file-submit")).click();
```

**Python**

Pythonでは、リモートWebDriverインスタンスにデフォルトでローカルファイルディテクターが追加されますが、独自のクラスを作成することも可能です。

```python
driver.file_detector = LocalFileDetector()
file_input = driver.find_element(By.CSS_SELECTOR, "input[type='file']")
file_input.send_keys(upload_file)
driver.find_element(By.ID, "file-submit").click()
```

**CSharp**

.NETでは、リモートWebDriverインスタンスにデフォルトでローカルファイルディテクターが追加されますが、独自のクラスを作成することも可能です。

```csharp
((RemoteWebDriver)driver).FileDetector = new LocalFileDetector();
IWebElement fileInput = driver.FindElement(By.CssSelector("input[type=file]"));
fileInput.SendKeys(uploadFile);
driver.FindElement(By.Id("file-submit")).Click();
```

**Ruby**

Rubyでは、リモートWebDriverインスタンスにデフォルトでローカルファイルディテクターが追加されますが、独自のラムダを作成することも可能です。

```ruby
driver.file_detector = ->((filename, *)) { filename.include?('selenium') && filename }
file_input = driver.find_element(css: 'input[type=file]')
file_input.send_keys(upload_file)
driver.find_element(id: 'file-submit').click
```

**JavaScript**

JavaScriptにはデフォルトでローカルファイルディテクターが含まれていないため、アップロードを行う際には必ず追加する必要があります。

```javascript
await driver.setFileDetector(new remote.FileDetector())
await driver.findElement(By.css('input[type=file]')).sendKeys(uploadFile)
await driver.findElement(By.id('file-submit')).click()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## ダウンロード

Chrome、Edge、およびFirefoxでは、それぞれダウンロードディレクトリの場所を設定できます。 ただし、リモートコンピュータでこれを行う場合、その場所はリモートコンピュータのローカルファイルシステム上にあります。Seleniumを使用すると、クライアントコンピュータにこれらのファイルをダウンロードできるように設定することが可能です。

### グリッドでのダウンロードを有効化

クライアントに関係なく、ノードまたはスタンドアロンモードでグリッドを起動する際には、次のフラグを追加する必要があります:
```
--enable-managed-downloads true
``` 

### クライアントでのダウンロードを有効化

グリッドは、`se:downloadsEnabled` 機能を使用して、ブラウザの場所を管理する責任を持つかどうかを切り替えます。各バインディングには、これを設定するためのオプションクラスのメソッドがあります。

**Java**

```java
ChromeOptions options = getDefaultChromeOptions();
options.setEnableDownloads(true);
driver = new RemoteWebDriver(gridUrl, options);
```

**Python**

```python
options = get_default_chrome_options()
options.enable_downloads = True
driver = webdriver.Remote(command_executor=server, options=options)
```

**CSharp**

```csharp
ChromeOptions options = new ChromeOptions
{
    EnableDownloads = true
};

driver = new RemoteWebDriver(GridUrl, options);
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome(enable_downloads: true)
driver = Selenium::WebDriver.for :remote, url: grid_url, options: options
```

**JavaScript**

```javascript
const options = new Chrome.Options().addArguments('--no-sandbox').enableDownloads()
driver = new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).usingServer(gridUrl).build()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ダウンロード可能なファイルの一覧

Seleniumはファイルのダウンロードが完了するのを待たないため、リストは指定されたセッションのディレクトリに現在存在するファイル名の即時スナップショットであることに注意してください。

**Java**

```java
List<String> files = ((HasDownloads) driver).getDownloadableFiles();
```

**Python**

```python
files = driver.get_downloadable_files()
```

**CSharp**

```csharp
IReadOnlyList<string> names = ((RemoteWebDriver)driver).GetDownloadableFiles();
```

**Ruby**

```ruby
files = driver.downloadable_files
```

**JavaScript**

```javascript
const files = await driver.getDownloadableFiles()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ファイルをダウンロード

Seleniumは、提供されたファイルの名前をリストの中で探し、指定されたターゲットディレクトリにダウンロードします。

**Java**

```java
((HasDownloads) driver).downloadFile(downloadableFile, targetDirectory);
```

**Python**

```python
driver.download_file(downloadable_file, target_directory)
```

**CSharp**

```csharp
((RemoteWebDriver)driver).DownloadFile(downloadableFile, targetDirectory);
```

**Ruby**

```ruby
driver.download_file(downloadable_file, target_directory)
```

**JavaScript**

```javascript
await driver.downloadFile(downloadableFile, targetDirectory)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### ダウンロードしたファイルの削除

デフォルトでは、ダウンロードディレクトリは該当するセッションの終了時に削除されますが、セッション中にすべてのファイルを削除することもできます。

**Java**

```java
((HasDownloads) driver).deleteDownloadableFiles();
```

**Python**

```python
driver.delete_downloadable_files()
```

**CSharp**

```csharp
((RemoteWebDriver)driver).DeleteDownloadableFiles();
```

**Ruby**

```ruby
driver.delete_downloadable_files
```

**JavaScript**

```javascript
await driver.deleteDownloadableFiles()
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## ブラウザ特有の機能

各 [ブラウザ](https://www.selenium.dev/ja/documentation/webdriver/browsers/index.md) は、そのブラウザにのみ利用可能な特別な機能を実装しています。各Seleniumバインディングは、リモートセッションでそれらの機能を使用するための異なる方法を実装しています。

**Java**

Javaでは、Augmenterクラスを使用する必要があります。これにより、RemoteWebDriverで使用される機能に一致するすべてのインターフェースの実装を自動的に取り込むことができます。

```java
driver = new Augmenter().augment(driver);
```

興味深いことに、RemoteWebDriverBuilderを使用すると、ドライバーが自動的に拡張されるため、デフォルトで全ての機能を取得するのに最適な方法です。

```java
RemoteWebDriver.builder()
    .address(gridUrl)
    .oneOf(getDefaultChromeOptions())
    .setCapability("ext:options", Map.of("key", "value"))
    .config(ClientConfig.defaultConfig())
    .build();
```

**Python**

_Not available in this language._

**CSharp**

.NETでは、リモートドライバーで指定されたブラウザに対して有効なコマンドを実行するために、カスタムコマンドエグゼキュータを使用します。

```csharp
var customCommandDriver = driver as ICustomDriverCommandExecutor;
customCommandDriver.RegisterCustomDriverCommands(FirefoxDriver.CustomCommandDefinitions);

var screenshotResponse = customCommandDriver
    .ExecuteCustomDriverCommand(FirefoxDriver.GetFullPageScreenshotCommand, null);
```

**Ruby**

Rubyでは、ミキシンを使用してリモートWebDriverセッションに適用可能なブラウザ特有のメソッドを追加します。これらのメソッドは常にそのまま機能するはずです。

**JavaScript**

JavaScriptは、リモートセッションに接続する際にブラウザ固有のドライバークラス(`chrome.Driver` など)を直接返すため、その機能にアクセスするための拡張(オーグメント)手順は不要です。

```javascript
const options = new Chrome.Options().addArguments('--no-sandbox')
driver = new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).usingServer(gridUrl).build()

assert.ok(driver instanceof Chrome.Driver)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## クライアントのリクエストをトレースする

この機能は、Java クライアント バインディング (ベータ版以降) でのみ利用できます。 
Remote WebDriver クライアントは Selenium Grid サーバーにリクエストを送信し、
Selenium Grid サーバーはリクエストを WebDriver に渡します。 
HTTP リクエストをエンド ツー エンドでトレースするには、サーバー側とクライアント側でトレースを有効にする必要があります。 
両端には、視覚化フレームワークを指すトレース エクスポーターのセットアップが必要です。 
デフォルトでは、トレースはクライアントとサーバーの両方で有効になっています。 
視覚化フレームワークの Jaeger UI と Selenium Grid 4 を設定するには、目的のバージョンの 
[トレースのセットアップ](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) を参照してください。

クライアント側のセットアップについては、以下の手順に従ってください。

### 必要な依存関係を追加する

トレーシング エクスポーターの外部ライブラリのインストールは、Maven を使って実行できます。 
プロジェクト pom.xml に _opentelemetry-exporter-jaeger_ および _grpc-netty_ の依存関係を追加します。

```xml
  <dependency>
      <groupId>io.opentelemetry</groupId>
      <artifactId>opentelemetry-exporter-jaeger</artifactId>
      <version>1.0.0</version>
    </dependency>
    <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-netty</artifactId>
      <version>1.35.0</version>
    </dependency>
``` 

### クライアントの実行中に必要なシステムプロパティを追加/渡す

**Java**

```java
System.setProperty("otel.traces.exporter", "jaeger");
System.setProperty("otel.exporter.jaeger.endpoint", "http://localhost:14250");
System.setProperty("otel.resource.attributes", "service.name=selenium-java-client");

ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");

WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), capabilities);

driver.get("http://www.google.com");

driver.quit();

```

ご希望のSeleniumのバージョンに必要な外部依存関係のバージョンの詳細については、
[トレースのセットアップ](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) を参照してください。

詳細については、下記URLを参照してください。

* OpenTelemetry: https://opentelemetry.io
* OpenTelemetryの構成:
    https://github.com/open-telemetry/opentelemetry-java/tree/main/sdk-extensions/autoconfigure
* Jaeger: https://www.jaegertracing.io
* [Selenium Grid 可観測性](https://www.selenium.dev/ja/documentation/grid/advanced_features/observability/index.md)
