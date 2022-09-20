---
title: "Browser Options"
linkTitle: "Options"
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

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Japanese. Do you speak Japanese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

In Selenium 3, capabilities were defined in a session by using Desired Capabilities classes.
As of Selenium 4, you must use the browser options classes.
For remote driver sessions, a browser options instance is required as it determines which browser will be used.

These options are described in the w3c specification for [Capabilities](https://w3c.github.io/webdriver/#capabilities).

Each browser has ({{< ref "../browsers/" >}}) that may be defined in addition to the ones defined in the specification.

## browserName

このCapabilityは、特定のセッションの `browserName` を設定するために使います。
指定されたブラウザがリモートエンドにインストールされていない場合、セッションの作成は失敗します。

## browserVersion

このCapabilityはオプションです。
これは、リモートエンドで使用可能なブラウザーバージョンを設定するために使います。
たとえば、Chromeバージョン80のみがインストールされているシステムでバージョン75を要求すると、セッションの作成は失敗します。

## pageLoadStrategy

Three types of page load strategies are available.

The page load strategy queries the 
[document.readyState](//developer.mozilla.org/en-US/docs/Web/API/Document/readyState)
as described in the table below:

| Strategy | Ready State | Notes |
| -------- | ----------- | ----- |
| normal | complete | Used by default, waits for all resources to download |
| eager | interactive | DOM access is ready, but other resources like images may still be loading |
| none | Any | Does not block WebDriver at all |

The `document.readyState` property of a document describes the loading state of the current document.

When navigating to a new page via URL, by default, WebDriver will hold off on completing a navigation 
method (e.g., driver.navigate().get()) until the document ready state is complete. This _does not 
necessarily mean that the page has finished loading_, especially for sites like Single Page Applications 
that use JavaScript to dynamically load content after the Ready State returns complete. Note also 
that this behavior does not apply to navigation that is a result of clicking an element or submitting a form.

If a page takes a long time to load as a result of downloading assets (e.g., images, css, js) 
that aren't important to the automation, you can change from the default parameter of `normal` to
`eager` or `none` to speed up the session. This value applies to the entire session, so make sure 
that your [waiting strategy]({{< ref "/documentation/webdriver/waits.md" >}}) is sufficient to minimize 
flakiness.


### normal (default)

WebDriver waits until the [load](https://developer.mozilla.org/en-US/docs/Web/API/Window/load_event) 
event fire is returned.

{{< tabpane langEqualsHeader=true code=false >}}
{{< tab header="Java" code=true >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
  public static void main(String[] args) {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://google.com");
    } finally {
      driver.quit();
    }
  }
}
{{< /tab >}}
{{< tab header="Python" code=true >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'normal'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" code=true >}}
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
{{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='normal'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L27-L33">}}
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
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

WebDriver waits until [DOMContentLoaded](https://developer.mozilla.org/en-US/docs/Web/API/Document/DOMContentLoaded_event) 
event fire is returned.

{{< tabpane langEqualsHeader=true code=false >}}
{{< tab header="Java" code=true >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
  public static void main(String[] args) {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://google.com");
    } finally {
      driver.quit();
    }
  }
}
{{< /tab >}}
{{< tab header="Python" code=true >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'eager'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" code=true >}}
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
{{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='eager'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L7-L13">}}
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
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

WebDriver only waits until the initial page is downloaded.

{{< tabpane langEqualsHeader=true code=false >}}
{{< tab header="Java" code=true >}}
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageLoadStrategy {
  public static void main(String[] args) {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
    WebDriver driver = new ChromeDriver(chromeOptions);
    try {
      // Navigate to Url
      driver.get("https://google.com");
    } finally {
      driver.quit();
    }
  }
}
{{< /tab >}}
{{< tab header="Python" code=true >}}
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
options = Options()
options.page_load_strategy = 'none'
driver = webdriver.Chrome(options=options)
driver.get("http://www.google.com")
driver.quit()
{{< /tab >}}
{{< tab header="CSharp" code=true >}}
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
{{< tab header="Ruby" code=true >}}
require 'selenium-webdriver'
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.page_load_strategy='none'

driver = Selenium::WebDriver.for :chrome, :desired_capabilities => caps
driver.get('https://www.google.com')
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/capabilities/pageLoading.spec.js#L17-L23">}}
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
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
