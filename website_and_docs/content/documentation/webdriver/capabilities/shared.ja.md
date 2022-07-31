---
title: "共通のCapability"
linkTitle: "共通のCapability"
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
"/ja/documentation/webdriver/capabilities/page_loading_strategy/"
]
---

Selenium WebDriverで新しいセッションを作成するには、ローカルエンドがリモートエンドに基本的なCapabilities（ブラウザの設定情報）を提供する必要があります。
リモートエンドは、一連の同じCapabilityを使用してセッションを作成し、現在のセッション機能を描きます。

WebDriverは、各リモートエンドがCapabilityをサポートする/すべきCapabilityを提供します。
WebDriverがサポートするCapabilityは次のとおりです。

## browserName:

このCapabilityは、特定のセッションの `browserName` を設定するために使います。
指定されたブラウザがリモートエンドにインストールされていない場合、セッションの作成は失敗します。

## browserVersion: 

このCapabilityはオプションです。
これは、リモートエンドで使用可能なブラウザーバージョンを設定するために使います。
たとえば、Chromeバージョン80のみがインストールされているシステムでバージョン75を要求すると、セッションの作成は失敗します。

### ページロード戦略
URLを介して新しいページに移動する場合、デフォルトでは、Seleniumは応答する前にページが完全にロードされるまで待機します。
これは初心者には効果的ですが、多数のサードパーティリソースをロードするページで長い待ち時間を引き起こす可能性があります。
デフォルト以外の戦略を使用すると、このような場合にテストの実行を高速化できますが、ページの要素がロードされてサイズが変更されると、ページ上の要素の位置が変化する不安定さを引き起こします。

次の表で説明するように、ページロード戦略は [document.readyState](//developer.mozilla.org/ja/docs/Web/API/Document/readyState) を問い合わせます。

| 戦略 | Ready State | 注釈 |
| -------- | ----------- | ----- |
| normal | complete | デフォルトで使用され、すべてのリソースがダウンロードされるまで待機します |
| eager | interactive | DOMアクセスの準備はできていますが、画像などの他のリソースがまだ読み込まれている可能性があります |
| none | Any | WebDriverをまったくブロックしません |

## platformName

これにより、リモートエンドのオペレーティングシステムが識別され、 `platformName` を取得するとOS名が返されます。

クラウドベースのプロバイダーでは、 `platformName` を設定すると、リモートエンドのOSが設定されます。

## acceptInsecureCerts

この機能は、セッション中のナビゲーション中に、期限切れ（または）無効な `TLS証明書` が使用されているかどうかを確認します。

機能が `false` に設定されている場合、ナビゲーションでドメイン証明書の問題が発生すると、 
[insecure certificate error](//developer.mozilla.org/ja/docs/Web/WebDriver/Errors/InsecureCertificate)  が返されます。
`true` に設定すると、無効な証明書はブラウザーによって信頼されます。

すべての自己署名証明書は、デフォルトでこの機能によって信頼されます。
一度設定すると、 `acceptInsecureCerts` Capabilityはセッション全体に影響します。

## timeouts

WebDriverの `セッション` には特定の `セッションタイムアウト` 間隔が設定されており、
その間、ユーザーはスクリプトの実行またはブラウザーからの情報の取得の動作を制御できます。

各セッションタイムアウトは、以下で説明するように、異なる `タイムアウト` の組み合わせで構成されます。

### Script Timeout:
現在のブラウジングコンテキストで実行中のスクリプトをいつ中断するかを指定します。
新しいセッションがWebDriverによって作成されると、デフォルトのタイムアウト **30,000** が課されます。

### Page Load Timeout:
現在のブラウジングコンテキストでWebページをロードする必要がある時間間隔を指定します。
新しいセッションがWebDriverによって作成されると、デフォルトのタイムアウト **300,000** が課されます。
ページの読み込みが指定/デフォルトの時間枠を制限する場合、スクリプトは　_TimeoutException_　によって停止されます。

### Implicit Wait Timeout
これは、要素を検索するときに暗黙的な要素の検索戦略を待つ時間を指定します。
新しいセッションがWebDriverによって作成されると、デフォルトのタイムアウト **0** が課されます。

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

## setWindowRect

Indicates whether the remote end supports all of the [resizing and repositioning](https://w3c.github.io/webdriver/#resizing-and-positioning-windows) [commands](https://w3c.github.io/webdriver/#dfn-commands).

## strictFileInteractability

この新しいcapabilityは、厳密な相互作用チェックを _input type = file_ 要素に適用する必要があるかどうかを示します。
厳密な相互作用チェックはデフォルトでオフになっているため、隠しファイルのアップロードコントロールで _Element Send Keys_ 
を使用する場合の動作が変更されます。

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

public class proxyTest {
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
