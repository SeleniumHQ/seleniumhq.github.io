---
title: "リモートWebDriverクライアント"
weight: 2
---

リモートWebDriverクライアントを実行するには、まずRemoteWebDriverに接続する必要があります。
これを行うには、テストを実行しているサーバーのアドレスをURLに指定します。
設定をカスタマイズするために、desired capabilitiesを設定します。
以下は、Firefoxでテストを実行しているリモートWebサーバー _www.example.com_ を指定してリモートWebDriverオブジェクトをインスタンス化する例です。

{{< code-tab >}}
  {{< code-panel language="java" >}}
FirefoxOptions firefoxOptions = new FirefoxOptions();
WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), firefoxOptions);
driver.get("http://www.google.com");
driver.quit();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

firefox_options = webdriver.FirefoxOptions()
driver = webdriver.Remote(
    command_executor='http://www.example.com',
    options=firefox_options
)
driver.get("http://www.google.com")
driver.quit() 
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
 FirefoxOptions firefoxOptions = new FirefoxOptions();
 IWebDriver driver = new RemoteWebDriver(new Uri("http://www.example.com"), firefoxOptions);
 driver.Navigate().GoToUrl("http://www.google.com");
 driver.Quit();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'

driver = Selenium::WebDriver.for :remote, url: "http://www.example.com", desired_capabilities: :firefox
driver.get "http://www.google.com"
driver.close
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const { Builder, Capabilities } = require("selenium-webdriver");
var capabilities = Capabilities.firefox();
(async function helloSelenium() {
    let driver = new Builder()        
        .usingServer("http://example.com")   
        .withCapabilities(capabilities)
        .build();
    try {
        await driver.get('http://www.google.com');
    } finally {
        await driver.quit();
    }
})(); 
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
firefoxOptions = FirefoxOptions()
driver: WebDriver = new RemoteWebDriver(new URL("http://www.example.com"), firefoxOptions)
driver.get("http://www.google.com")
driver.quit()
  {{< / code-panel >}}
{{< / code-tab >}}

テスト設定をさらにカスタマイズするために、他のdesired capabilitiesを追加できます。

## ブラウザーオプション

例えば、Chromeバージョン67を使用して、Windows XPでChromeを実行する場合は、このようになるかと思います。

{{< code-tab >}}
  {{< code-panel language="java" >}}
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setCapability("browserVersion", "67");
chromeOptions.setCapability("platformName", "Windows XP");
WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), chromeOptions);
driver.get("http://www.google.com");
driver.quit();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium import webdriver

chrome_options = webdriver.ChromeOptions()
chrome_options.set_capability("browserVersion", "67")
chrome_options.set_capability("platformName", "Windows XP")
driver = webdriver.Remote(
    command_executor='http://www.example.com',
    options=chrome_options
)
driver.get("http://www.google.com")
driver.quit()  
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var chromeOptions = new ChromeOptions();
chromeOptions.BrowserVersion = "67";
chromeOptions.PlatformName = "Windows XP";
IWebDriver driver = new RemoteWebDriver(new Uri("http://www.example.com"), chromeOptions);
driver.Navigate().GoToUrl("http://www.google.com");
driver.Quit();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
caps = Selenium::WebDriver::Remote::Capabilities.chrome
caps.platform = Windows XP
caps.version = 67

driver = Selenium::WebDriver.for :remote, :url => "http://www.example.com", :desired_capabilities => caps
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const { Builder } = require("selenium-webdriver");
const chrome = require("selenium-webdriver/chrome");
let opts = new chrome.Options();
opts.setAcceptInsecureCerts(true);
opts.setBrowserVersion('67');
opts.setPlatform('Windows XP');
(async function helloSelenium() {
    let driver = new Builder()
        .usingServer("http://example.com")
        .forBrowser('chrome')
        .setChromeOptions(opts)
        .build();
    try {
        await driver.get('http://www.google.com');
    }
    finally {
        await driver.quit();
    }
})(); 
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val chromeOptions = ChromeOptions()
chromeOptions.setCapability("browserVersion", "67")
chromeOptions.setCapability("platformName", "Windows XP")
val driver: WebDriver = new RemoteWebDriver(new URL("http://www.example.com"), chromeOptions)
driver.get("http://www.google.com")
driver.quit()
  {{< / code-panel >}}
{{< / code-tab >}}


## ローカルファイルDetector

ローカルファイルDetectorを使用すると、クライアントマシンからリモートサーバーにファイルを転送できます。 
例えば、テストでファイルをWebアプリケーションにアップロードする必要がある場合、リモートWebDriverは実行時にローカルマシンからリモートWebサーバーにファイルを自動的に転送できます。 
これにより、テストを実行しているリモートマシンからファイルをアップロードできます。 
デフォルトでは有効になっておらず、次の方法で有効にできます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.setFileDetector(new LocalFileDetector());
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.remote.file_detector import LocalFileDetector

driver.file_detector = LocalFileDetector()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
var allowsDetection = this.driver as IAllowsFileDetection;
if (allowsDetection != null)
{
   allowsDetection.FileDetector = new LocalFileDetector();
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
@driver.file_detector = lambda do |args|
  # args => ["/path/to/file"]
  str = args.first.to_s
  str if File.exist?(str)
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
var remote = require('selenium-webdriver/remote');
driver.setFileDetector(new remote.FileDetector);   
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.fileDetector = LocalFileDetector()
  {{< / code-panel >}}
{{< / code-tab >}}

上記のコードを定義したら、次の方法でテストにファイルをアップロードできます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
WebElement upload = driver.findElement(By.id("myfile"));
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload")

driver.find_element(By.ID, "myfile").send_keys("/Users/sso/the/local/path/to/darkbulb.jpg")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.Navigate().GoToUrl("http://sso.dev.saucelabs.com/test/guinea-file-upload");
IWebElement upload = driver.FindElement(By.Id("myfile"));
upload.SendKeys(@"/Users/sso/the/local/path/to/darkbulb.jpg");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
@driver.navigate.to "http://sso.dev.saucelabs.com/test/guinea-file-upload"
    element = @driver.find_element(:id, 'myfile')
    element.send_keys "/Users/sso/SauceLabs/sauce/hostess/maitred/maitred/public/images/darkbulb.jpg"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload");
var upload = driver.findElement(By.id("myfile"));
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg");  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.get("http://sso.dev.saucelabs.com/test/guinea-file-upload")
val upload: WebElement = driver.findElement(By.id("myfile"))
upload.sendKeys("/Users/sso/the/local/path/to/darkbulb.jpg")
  {{< / code-panel >}}
{{< / code-tab >}}

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

### Beta 1 

#### 必要な依存関係を追加する

トレーシング エクスポーターの外部ライブラリのインストールは、Maven を使って実行できます。 
プロジェクト pom.xml に _opentelemetry-exporter-jaeger_ および _grpc-netty_ の依存関係を追加します。

```xml
  <dependency>
      <groupId>io.opentelemetry</groupId>
      <artifactId>opentelemetry-exporter-jaeger</artifactId>
      <version>0.14.0</version>
    </dependency>
    <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-netty</artifactId>
      <version>1.34.1</version>
    </dependency>
``` 

 
#### クライアントの実行中に必要なシステムプロパティを追加/渡す

{{< code-tab >}}
  {{< code-panel language="java" >}}

System.setProperty("JAEGER_SERVICE_NAME", "selenium-java-client");
System.setProperty("JAEGER_AGENT_HOST","localhost");
System.setProperty("JAEGER_AGENT_PORT","14250");

ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");

WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), capabilities);

driver.get("http://www.google.com");

driver.quit();

  {{< / code-panel >}}
{{< / code-tab >}}

### Beta 2 onwards 

#### 必要な依存関係を追加する

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
 
#### クライアントの実行中に必要なシステムプロパティを追加/渡す

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("otel.traces.exporter", "jaeger");
System.setProperty("otel.exporter.jaeger.endpoint", "http://localhost:14250");
System.setProperty("otel.resource.attributes", "service.name=selenium-java-client");

ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");

WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), capabilities);

driver.get("http://www.google.com");

driver.quit();

  {{< / code-panel >}}
{{< / code-tab >}}

ご希望のSeleniumのバージョンに必要な外部依存関係のバージョンの詳細については、
[トレースのセットアップ](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) を参照してください。

詳細については、下記URLを参照してください。

* OpenTelemetry: https://opentelemetry.io
* Configuring OpenTelemetry:
    https://github.com/open-telemetry/opentelemetry-java/tree/main/sdk-extensions/autoconfigure
* Jaeger: https://www.jaegertracing.io
* [Selenium Grid 可観測性]({{< ref "grid/grid_4/advanced_features/observability.ja.md" >}}) 

