---
title: "ブラウザーオプション"
linkTitle: "オプション"
weight: 2
description: >-
  これらのCapabilityはすべてのブラウザで共通です。
aliases: [
"/documentation/ja/driver_idiosyncrasies/shared_capabilities/",
"/ja/documentation/webdriver/capabilities/shared_capabilities/",
"/documentation/ja/webdriver/http_proxies/",
"/ja/documentation/webdriver/http_proxies/",
"/ja/documentation/webdriver/capabilities/http_proxies/",
"/documentation/ja/webdriver/page_loading_strategy/",
"/ja/documentation/webdriver/page_loading_strategy/",
"/ja/documentation/webdriver/capabilities/page_loading_strategy/",
"/ja/documentation/capabilitis/shared/",
]
---

Selenium 3 では、Capabilitiesは Desired Capabilities クラスを使用してセッションで定義していました。 
Selenium 4 以降、ブラウザ オプション クラスを使用する必要があります。 
リモート ドライバー セッションの場合、使用するブラウザーを決めるため、ブラウザーオプションインスタンスが必要です。

これらのオプションは、[Capabilities](https://w3c.github.io/webdriver/#capabilities) の w3c仕様で説明しています。

各ブラウザには、w3c仕様で定義しているものに加えて定義可能な [カスタム オプション]({{< ref "../browsers/" >}}) があります。

## browserName

Browser name is set by default when using an Options class instance.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## browserVersion

This capability is optional, this is used to set the available browser version at remote end.
In recent versions of Selenium, if the version is not found on the system,
it will be automatically downloaded by [Selenium Manager]({{< ref "../../selenium_manager" >}})

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


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
この値はセッション全体に適用されるため、 [待機戦略]({{< ref "/documentation/webdriver/waits.md" >}}) 
が不安定さを最小限に抑えるのに十分であることを確認してください。

### normal (デフォルト)

WebDriver は [load](https://developer.mozilla.org/ja/docs/Web/API/Window/load_event) 
イベント検知するまで待機します。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/drivers/OptionsTest.java#L14-L16">}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/drivers/test_options.py#L7-L9">}}
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
  class pageLoadStrategy {
    public static void Main(string[] args) {
      var chromeOptions = new ChromeOptions();
      chromeOptions.PageLoadStrategy = PageLoadStrategy.Normal;
      IWebDriver driver = new ChromeDriver(chromeOptions);
      try {
        driver.Navigate().GoToUrl("https://example.com");
      } finally {
        driver.Quit();
      }
    }
  }
}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/drivers/options_spec.rb#L10-L11">}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L28-L34">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
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
{{< /tab >}}
{{< /tabpane >}}

### eager

WebDriver は、[DOMContentLoaded](https://developer.mozilla.org/ja/docs/Web/API/Document/DOMContentLoaded_event) 
イベントを検知するまで待機します。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/drivers/OptionsTest.java#L27-L29">}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/drivers/test_options.py#L17-L18">}}
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
  class pageLoadStrategy {
    public static void Main(string[] args) {
      var chromeOptions = new ChromeOptions();
      chromeOptions.PageLoadStrategy = PageLoadStrategy.Eager;
      IWebDriver driver = new ChromeDriver(chromeOptions);
      try {
        driver.Navigate().GoToUrl("https://example.com");
      } finally {
        driver.Quit();
      }
    }
  }
}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/drivers/options_spec.rb#L19-L20">}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L8-L14">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
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
{{< /tab >}}
{{< /tabpane >}}

### none

WebDriver は、最初のページがダウンロードされるまで待機します。

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/drivers/OptionsTest.java#L40-L42">}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/drivers/test_options.py#L27-L28">}}
{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace pageLoadStrategy {
  class pageLoadStrategy {
    public static void Main(string[] args) {
      var chromeOptions = new ChromeOptions();
      chromeOptions.PageLoadStrategy = PageLoadStrategy.None;
      IWebDriver driver = new ChromeDriver(chromeOptions);
      try {
        driver.Navigate().GoToUrl("https://example.com");
      } finally {
        driver.Quit();
      }
    }
  }
}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/drivers/options_spec.rb#L28-L29">}}
{{< /tab >}}
{{< tab header="JavaScript" text=true  >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L18-L24">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
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
{{< /tab >}}
{{< /tabpane >}}


## platformName

これにより、リモートエンドのオペレーティングシステムが識別され、 `platformName` を取得するとOS名が返されます。

クラウドベースのプロバイダーでは、 `platformName` を設定すると、リモートエンドのOSが設定されます。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## acceptInsecureCerts

この機能は、セッション中のナビゲーション中に、期限切れ（または）無効な `TLS証明書` が使用されているかどうかを確認します。

機能が `false` に設定されている場合、ナビゲーションでドメイン証明書の問題が発生すると、 
[insecure certificate error](//developer.mozilla.org/ja/docs/Web/WebDriver/Errors/InsecureCertificate)  が返されます。
`true` に設定すると、無効な証明書はブラウザーによって信頼されます。

すべての自己署名証明書は、デフォルトでこの機能によって信頼されます。
一度設定すると、 `acceptInsecureCerts` Capabilityはセッション全体に影響します。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## timeouts

WebDriverの `セッション` には特定の `セッションタイムアウト` 間隔が設定されており、
その間、ユーザーはスクリプトの実行またはブラウザーからの情報の取得の動作を制御できます。

各セッションタイムアウトは、以下で説明するように、異なる `タイムアウト` の組み合わせで構成されます。

### Script Timeout:
現在のブラウジングコンテキストで実行中のスクリプトをいつ中断するかを指定します。
新しいセッションがWebDriverによって作成されると、デフォルトのタイムアウト **30,000** が課されます。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Page Load Timeout:
現在のブラウジングコンテキストでWebページをロードする必要がある時間間隔を指定します。
新しいセッションがWebDriverによって作成されると、デフォルトのタイムアウト **300,000** が課されます。
ページの読み込みが指定/デフォルトの時間枠を制限する場合、スクリプトは　_TimeoutException_　によって停止されます。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Implicit Wait Timeout
これは、要素を検索するときに暗黙的な要素の検索戦略を待つ時間を指定します。
新しいセッションがWebDriverによって作成されると、デフォルトのタイムアウト **0** が課されます。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## setWindowRect

リモート エンドがすべての　[サイズ変更および再配置](https://w3c.github.io/webdriver/#resizing-and-positioning-windows)
[コマンド](https://w3c.github.io/webdriver/#dfn-commands) をサポートするかどうかを示します。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## strictFileInteractability

この新しいcapabilityは、厳密な相互作用チェックを _input type = file_ 要素に適用する必要があるかどうかを示します。
厳密な相互作用チェックはデフォルトでオフになっているため、隠しファイルのアップロードコントロールで _Element Send Keys_ 
を使用する場合の動作が変更されます。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< badge-code >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## proxy

プロキシサーバーは、クライアントとサーバー間の要求の仲介役として機能します。
簡単に言えば、トラフィックはプロキシサーバーを経由して、要求したアドレスに戻り、戻ってきます。

Seleniumを使用した自動化スクリプト用のプロキシサーバーは、

* ネットワークトラフィックをキャプチャする
* ウェブサイトによって行われた模擬バックエンドを呼び出す
* 複雑なネットワークトポロジーまたは厳格な企業の制限/ポリシーの下で、必要なWebサイトにアクセスします。

企業環境でブラウザがURLへの接続に失敗した場合、環境にアクセスするにはプロキシが必要であることが原因であることが最も可能性が高いです。

Selenium WebDriverは設定をプロキシする方法を提供します。


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ProxyTest {
  public static void main(String[] args) {
    Proxy proxy = new Proxy();
    proxy.setHttpProxy("<HOST:PORT>");
    ChromeOptions options = new ChromeOptions();
    options.setCapability("proxy", proxy);
    WebDriver driver = new ChromeDriver(options);
    driver.get("https://www.google.com/");
    driver.manage().window().maximize();
    driver.quit();
  }
}
{{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver

PROXY = "<HOST:PORT>"
webdriver.DesiredCapabilities.FIREFOX['proxy'] = {
"httpProxy": PROXY,
"ftpProxy": PROXY,
"sslProxy": PROXY,
"proxyType": "MANUAL",

}

with webdriver.Firefox() as driver:
# Open URL
    driver.get("https://selenium.dev")

{{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

public class ProxyTest{
public static void Main() {
ChromeOptions options = new ChromeOptions();
Proxy proxy = new Proxy();
proxy.Kind = ProxyKind.Manual;
proxy.IsAutoDetect = false;
proxy.SslProxy = "<HOST:PORT>";
options.Proxy = proxy;
options.AddArgument("ignore-certificate-errors");
IWebDriver driver = new ChromeDriver(options);
driver.Navigate().GoToUrl("https://www.selenium.dev/");
}
}
{{< /tab >}}
{{< tab header="Ruby" >}}
# this code was written with Selenium 4

proxy = Selenium::WebDriver::Proxy.new(http: '<HOST:PORT>')
cap   = Selenium::WebDriver::Remote::Capabilities.chrome(proxy: proxy)

driver = Selenium::WebDriver.for(:chrome, capabilities: cap)
driver.get('http://google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
let webdriver = require('selenium-webdriver');
let chrome = require('selenium-webdriver/chrome');
let proxy = require('selenium-webdriver/proxy');
let opts = new chrome.Options();

(async function example() {
opts.setProxy(proxy.manual({http: '<HOST:PORT>'}));
let driver = new webdriver.Builder()
.forBrowser('chrome')
.setChromeOptions(opts)
.build();
try {
await driver.get("https://selenium.dev");
}
finally {
await driver.quit();
}
}());
{{< /tab >}}
{{< tab header="Kotlin" >}}
import org.openqa.selenium.Proxy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class proxyTest {
fun main() {

        val proxy = Proxy()
        proxy.setHttpProxy("<HOST:PORT>")
        val options = ChromeOptions()
        options.setCapability("proxy", proxy)
        val driver: WebDriver = ChromeDriver(options)
        driver["https://www.google.com/"]
        driver.manage().window().maximize()
        driver.quit()
    }
}
{{< /tab >}}
{{< /tabpane >}}
