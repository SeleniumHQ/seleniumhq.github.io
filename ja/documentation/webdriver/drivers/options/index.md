# ブラウザーオプション

> これらの機能はすべてのブラウザで共有されています。

URL: https://www.selenium.dev/ja/documentation/webdriver/drivers/options/

---

Selenium 3 では、Capabilitiesは Desired Capabilities クラスを使用してセッションで定義していました。 
Selenium 4 以降、ブラウザ オプション クラスを使用する必要があります。 
リモート ドライバー セッションの場合、使用するブラウザーを決めるため、ブラウザーオプションインスタンスが必要です。

これらのオプションは、[Capabilities](https://w3c.github.io/webdriver/#capabilities) の w3c仕様で説明しています。

各ブラウザには、w3c仕様で定義しているものに加えて定義可能な [カスタム オプション](https://www.selenium.dev/ja/documentation/webdriver/browsers/index.md) があります。

## browserName

オプションクラスのインスタンスを使用すると、ブラウザ名はデフォルトで設定されます。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String name = chromeOptions.getBrowserName();
```

**Python**

```python
options = get_default_chrome_options()
assert options.capabilities['browserName'] == 'chrome'
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## browserVersion

この機能はオプションであり、リモート側で使用可能なブラウザのバージョンを設定するために使用されます。最近のSeleniumのバージョンでは、システムにバージョンが見つからない場合、[Selenium Manager](https://www.selenium.dev/ja/documentation/selenium_manager/index.md) によって自動的にダウンロードされます。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String version = "latest";
chromeOptions.setBrowserVersion(version);
```

**Python**

```python
options = get_default_chrome_options()
options.browser_version = 'stable'
assert options.capabilities['browserVersion'] == 'stable'
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options.browser_version = 'latest'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## pageLoadStrategy

3種類のページ読み込み戦略を利用できます。

ページ読み込み戦略は、次の表で説明しています。

| 戦略 | 準備完了状態 | 注釈 |
| -------- | ----------- | ----- |
| normal | complete | デフォルトで使用され、すべてのリソースをダウンロードするのを待ちます |
| eager | interactive | DOM アクセスの準備は整っていますが、画像などの他のリソースはまだロード中の可能性があります |
| none | Any | WebDriver をまったくブロックしません |

ドキュメントの [document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState) 
プロパティは、現在のドキュメントの読み込み状態を示します。

URL 経由で新しいページに移動する場合、デフォルトでは、WebDriver は、ドキュメントの準備完了状態が完了するまで、
ナビゲーション メソッド (driver.navigate().get() など) の完了を保留します。 
_これは必ずしもページの読み込みが完了したことを意味するわけではありません。_ 
特に、Ready State が完了した後に JavaScript を使用してコンテンツを動的に読み込むシングル ページ アプリケーションのようなサイトの場合はそうです。 
また、この動作は、要素のクリックまたはフォームの送信の結果であるナビゲーションには適用されないことに注意してください。

自動化にとって重要ではないアセット (画像、css、js など) をダウンロードした結果、ページの読み込みに時間がかかる場合は、
デフォルトのパラメーターである `normal` を `eager` または `none` に変更して、セッションの読み込みを高速化できます。 
この値はセッション全体に適用されるため、 [待機戦略](https://www.selenium.dev/ja/documentation/webdriver/waits/index.md) 
が不安定さを最小限に抑えるのに十分であることを確認してください。

### normal (デフォルト)

WebDriver は [load](https://developer.mozilla.org/ja/docs/Web/API/Window/load_event) 
イベント検知するまで待機します。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'normal'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Normal;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :normal
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('normal'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
  val chromeOptions = ChromeOptions()
  chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL)
  val driver = ChromeDriver(chromeOptions)
  try {
    driver.get("https://www.google.com")
  }
  finally {
    driver.quit()
  }
}
```

### eager

WebDriver は、[DOMContentLoaded](https://developer.mozilla.org/ja/docs/Web/API/Document/DOMContentLoaded_event) 
イベントを検知するまで待機します。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'eager'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
var chromeOptions = new ChromeOptions();
chromeOptions.PageLoadStrategy = PageLoadStrategy.Eager;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :eager
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('eager'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
  val chromeOptions = ChromeOptions()
  chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER)
  val driver = ChromeDriver(chromeOptions)
  try {
    driver.get("https://www.google.com")
  }
  finally {
    driver.quit()
  }
}
```

### none

WebDriver は、最初のページがダウンロードされるまで待機します。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
options = get_default_chrome_options()
options.page_load_strategy = 'none'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
[TestMethod]
public void SetPageLoadStrategyNone()
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.page_load_strategy = :none
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setPageLoadStrategy('none'))
  .build();

await driver.get('https://www.selenium.dev/selenium/web/blank.html');
await driver.quit();
```

**Kotlin**

```kotlin
import org.openqa.selenium.PageLoadStrategy
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main() {
  val chromeOptions = ChromeOptions()
  chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE)
  val driver = ChromeDriver(chromeOptions)
  try {
    driver.get("https://www.google.com")
  }
  finally {
    driver.quit()
  }
}
```

## platformName

これにより、リモートエンドのオペレーティングシステムが識別され、 `platformName` を取得するとOS名が返されます。

クラウドベースのプロバイダーでは、 `platformName` を設定すると、リモートエンドのOSが設定されます。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
String platform = "OS X 10.6";
chromeOptions.setPlatformName(platform);
```

**Python**

```python
options = get_default_chrome_options()
options.platform_name = 'any'
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.firefox
options.platform_name = 'Windows 10'
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## acceptInsecureCerts

この機能は、セッション中のナビゲーション中に、期限切れ（または）無効な `TLS証明書` が使用されているかどうかを確認します。

機能が `false` に設定されている場合、ナビゲーションでドメイン証明書の問題が発生すると、 
[insecure certificate error](//developer.mozilla.org/ja/docs/Web/WebDriver/Errors/InsecureCertificate)  が返されます。
`true` に設定すると、無効な証明書はブラウザーによって信頼されます。

すべての自己署名証明書は、デフォルトでこの機能によって信頼されます。
一度設定すると、 `acceptInsecureCerts` Capabilityはセッション全体に影響します。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setAcceptInsecureCerts(true);
```

**Python**

```python
options = get_default_chrome_options()
options.accept_insecure_certs = True
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.accept_insecure_certs = true
```

**JavaScript**

```javascript
let driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options.setAcceptInsecureCerts(true))
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## timeouts

WebDriverの `セッション` には特定の `セッションタイムアウト` 間隔が設定されており、
その間、ユーザーはスクリプトの実行またはブラウザーからの情報の取得の動作を制御できます。

各セッションタイムアウトは、以下で説明するように、異なる `タイムアウト` の組み合わせで構成されます。

### Script Timeout:
現在のブラウジングコンテキストで実行中のスクリプトをいつ中断するかを指定します。
新しいセッションがWebDriverによって作成されると、デフォルトのタイムアウト **30,000** が課されます。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setScriptTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'script': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {script: 40_000}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Page Load Timeout:
現在のブラウジングコンテキストでWebページをロードする必要がある時間間隔を指定します。
新しいセッションがWebDriverによって作成されると、デフォルトのタイムアウト **300,000** が課されます。
ページの読み込みが指定/デフォルトの時間枠を制限する場合、スクリプトは　_TimeoutException_　によって停止されます。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setPageLoadTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'pageLoad': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {page_load: 400_000}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Implicit Wait Timeout
これは、要素を検索するときに暗黙的な要素の検索戦略を待つ時間を指定します。
新しいセッションがWebDriverによって作成されると、デフォルトのタイムアウト **0** が課されます。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
Duration duration = Duration.of(5, ChronoUnit.SECONDS);
chromeOptions.setImplicitWaitTimeout(duration);
```

**Python**

```python
options = get_default_chrome_options()
options.timeouts = { 'implicit': 5000 }
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.timeouts = {implicit: 1}
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## unhandledPromptBehavior

現在のセッションの `ユーザープロンプトハンドラー` の状態を指定します。
デフォルトでは、 **dismiss and notify (却下して通知する) 状態** となります。

### User Prompt Handler

これは、リモートエンドでユーザープロンプトが表示されたときに実行する必要があるアクションを定義します。
これは、 `unhandledPromptBehavior` Capabilityによって定義され、次の状態があります。

* dismiss (却下)
* accept (受入)
* dismiss and notify (却下して通知)
* accept and notify (受け入れて通知)
* ignore (無視)

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
```

**Python**

```python
options = get_default_chrome_options()
options.unhandled_prompt_behavior = 'accept'
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
var chromeOptions = new ChromeOptions();
chromeOptions.UnhandledPromptBehavior = UnhandledPromptBehavior.DismissAndNotify;
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.unhandled_prompt_behavior = :accept
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

注：`beforeunload` プロンプトに関しては、
プロンプトを開いたままにするために、[BiDi](https://www.selenium.dev/ja/documentation/webdriver/bidi/index.md) を有効にすることに加えて、
ChromeDriver で `ignore` を設定する必要があります。

## setWindowRect

リモート エンドがすべての　[サイズ変更および再配置](https://w3c.github.io/webdriver/#resizing-and-positioning-windows)
[コマンド](https://w3c.github.io/webdriver/#dfn-commands) をサポートするかどうかを示します。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setCapability(CapabilityType.SET_WINDOW_RECT, true);
```

**Python**

```python
options = webdriver.FirefoxOptions()
options.set_window_rect = True # Full support in Firefox
driver = webdriver.Firefox(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.firefox
options.set_window_rect = true
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## strictFileInteractability

この新しいcapabilityは、厳密な相互作用チェックを _input type = file_ 要素に適用する必要があるかどうかを示します。
厳密な相互作用チェックはデフォルトでオフになっているため、隠しファイルのアップロードコントロールで _Element Send Keys_ 
を使用する場合の動作が変更されます。

**Java**

```java
ChromeOptions chromeOptions = getDefaultChromeOptions();
chromeOptions.setCapability(CapabilityType.STRICT_FILE_INTERACTABILITY, true);
```

**Python**

```python
options = get_default_chrome_options()
options.strict_file_interactability = True
driver = webdriver.Chrome(options=options)
```

**CSharp**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.strict_file_interactability = true
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## proxy

プロキシサーバーは、クライアントとサーバー間の要求の仲介役として機能します。
簡単に言えば、トラフィックはプロキシサーバーを経由して、要求したアドレスに戻り、戻ってきます。

Seleniumを使用した自動化スクリプト用のプロキシサーバーは、

* ネットワークトラフィックをキャプチャする
* ウェブサイトによって行われた模擬バックエンドを呼び出す
* 複雑なネットワークトポロジーまたは厳格な企業の制限/ポリシーの下で、必要なWebサイトにアクセスします。

企業環境でブラウザがURLへの接続に失敗した場合、環境にアクセスするにはプロキシが必要であることが原因であることが最も可能性が高いです。

Selenium WebDriverは設定をプロキシする方法を提供します。

**Java**

```java
Proxy proxy = new Proxy();
proxy.setHttpProxy("myproxy.com:8080");
ChromeOptions options = getDefaultChromeOptions();
options.setCapability("proxy", proxy);
```

**Python**

```python
options = get_default_chrome_options()
options.proxy = Proxy({ 'proxyType': ProxyType.MANUAL, 'httpProxy' : 'http.proxy:1234'})
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
ChromeOptions options = new ChromeOptions();
Proxy proxy = new Proxy();
proxy.Kind = ProxyKind.Manual;
proxy.IsAutoDetect = false;
proxy.SslProxy = "myproxy.com:8080";
options.Proxy = proxy;
options.AddArgument("ignore-certificate-errors");
```

**Ruby**

```ruby
options = Selenium::WebDriver::Options.chrome
options.proxy = Selenium::WebDriver::Proxy.new(http: 'myproxy.com:8080')
```

**JavaScript**

```javascript
const options = new chrome.Options()
options.setProxy(proxy.manual({ http: 'myproxy.com:8080' }))
```

**Kotlin**

```kotlin
val proxy = Proxy()
proxy.setHttpProxy("myproxy.com:8080")
val options = ChromeOptions()
options.setCapability("proxy", proxy)
```
