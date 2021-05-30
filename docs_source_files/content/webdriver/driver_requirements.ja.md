---
title: "ドライバー要件"
weight: 2
---

SeleniumはWebDriverを経由して、Chrom(ium)、Firefox、Internet Explorer、Opera、Safariなど、市場のすべての主要なブラウザーをサポートします。
すべてのブラウザーがリモートコントロールを公式にサポートしているわけではありませんが、可能であれば、ブラウザーの自動化のビルトインサポートを使用して、WebDriverはブラウザーを動かします。

WebDriverの目的は、できるだけブラウザーに近づけて実際のユーザーのインタラクションを模倣することです。
これは、ブラウザーによって異なる水準となる可能性があります。
さまざまなドライバーの特異性の詳細については、 _[ドライバーの特異性]({{<ref "/driver_idiosyncrasies/_index.md">}})_ をご覧ください。

ブラウザーを制御するためすべてのドライバーが単一のユーザー向けインターフェイスを共有している場合でも、
ブラウザーのセッションを設定する方法が少し異なります。
ドライバーの実装の多くはサードパーティによって提供されているため、
標準のSeleniumディストリビューションには含まれていません。

ドライバーのインスタンス化、プロファイル管理、およびブラウザー固有のさまざまな設定は、ブラウザーに応じて異なる要件を持つパラメーターの例です。
このセクションでは、さまざまなブラウザーを使い始めるための基本的な要件について説明します。

### 実行可能ファイルをパスに追加する
ほとんどのドライバーでは、ブラウザーと通信するためにSeleniumの追加の実行可能ファイルが必要です。
WebDriverを起動する前に実行可能ファイルの場所を手動で指定できますが、これによりテストの移植性が低下します。
実行可能ファイルはすべてのマシンの同じ場所にあるか、テストコードリポジトリに含まれている必要があるためです。

WebDriverのバイナリを含むフォルダーをシステムのパスに追加することで、Seleniumはドライバーの正確な場所を見つけるためにテストコードを要求することなく、追加のバイナリを見つけることができます。

* 実行可能ファイルを配置するディレクトリをこのように作成します。
_C:\WebDriver\bin_ or _/opt/WebDriver/bin_
* PATHにディレクトリを追加します。
  * Windows - 管理者権限でコマンドプロンプトを開いて
     次のコマンドを実行して、マシン上のすべてのユーザー向けにディレクトリをPATHに永続的に追加します。

```shell
setx /m path "%path%;C:\WebDriver\bin\"
```
  * macOS、Linux で bashを使う場合は、terminalを開いて次のコマンドを実行します。

```shell
export PATH=$PATH:/opt/WebDriver/bin >> ~/.profile
```

* これで、変更をテストする準備ができました。
  開いているすべてのコマンドプロンプトを閉じて、新しいプロンプトを開きます。
  前の手順で作成したフォルダー内のバイナリのいずれかの名前を入力します。例：

  ```shell
  chromedriver
  ```
* `PATH`が正しく設定されている場合、ドライバーの起動に関連する出力が表示されます。

```text
Starting ChromeDriver 2.25.426935 (820a95b0b81d33e42712f9198c215f703412e1a1) on port 9515
Only local connections are allowed.
```

<kbd>Ctrl + C</kbd> を押すと、コマンドプロンプトの制御を取り戻すことができます。

### クイックリファレンス

| ブラウザー | サポートOS | メンテナ | ダウンロード | イシュートラッカー |
| ------- | ------------ | ------------- | -------- | ------------- |
| Chromium/Chrome | Windows/macOS/Linux | Google | [ダウンロード](//chromedriver.storage.googleapis.com/index.html) | [イシュートラッカー](//bugs.chromium.org/p/chromedriver/issues/list) |
| Firefox | Windows/macOS/Linux | Mozilla | [ダウンロード](//github.com/mozilla/geckodriver/releases) | [イシュートラッカー](//github.com/mozilla/geckodriver/issues) |
| Edge | Windows 10 | Microsoft | [ダウンロード](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) | [イシュートラッカー](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&amp;q=webdriver) |
| Internet Explorer | Windows | Selenium Project | [ダウンロード](//selenium-release.storage.googleapis.com/index.html) | [イシュートラッカー](//github.com/SeleniumHQ/selenium/labels/D-IE) |
| Safari | macOS El Capitan and newer | Apple | ビルトイン | [イシュートラッカー](//bugreport.apple.com/logon) |
| Opera | Windows/macOS/Linux | Opera | [ダウンロード](//github.com/operasoftware/operachromiumdriver/releases) | [イシュートラッカー](//github.com/operasoftware/operachromiumdriver/issues) |

### Chromium/Chrome

Chrome または Chromium を動かす場合、[chromedriver](//sites.google.com/a/chromium.org/chromedriver/downloads) をダウンロードして、システムパスのフォルダに置いてください。

LinuxまたはmacOSでは、これは `PATH` 環境変数を変更することを意味します。
次のコマンドを実行すると、コロンで区切られたディレクトリがシステムのパスを構成していることがわかります。

```shell
$ echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
```

まだパスを追加していない場合にchromedriverをパスに含めるには、chromedriverバイナリの親ディレクトリを必ず含めてください。
次の行は、 `PATH` 環境変数の現在のコンテンツに加えて、コロンの後に追加のパスを設定します。

```shell
$ export PATH="$PATH:/path/to/chromedriver"
```

パス上でchromedriverが使用可能な場合、任意のディレクトリから_chromedriver_実行可能ファイルを実行できるはずです。

Chrome / Chromiumセッションをインスタンス化するには、次のとおり。

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

WebDriver driver = new ChromeDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Chrome

driver = Chrome()

#Or use the context manager
from selenium.webdriver import Chrome

with Chrome() as driver:
    #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

IWebDriver driver = new ChromeDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :chrome
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
    let driver = await new Builder().forBrowser('chrome').build();
    //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

val driver: WebDriver = ChromeDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

chromedriver実行可能ファイルへのパスを設定する必要があることに注意してください。
これは、次の行を使えば可能です。

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Chrome(executable_path='/path/to/chromedriver')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new ChromeDriver("/path/to/chromedriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Chrome.driver_path = "/path/to/chromedriver"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
chrome.setDefaultService(new chrome.ServiceBuilder('path/to/chromedriver').build());
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver")
  {{< / code-panel >}}
{{< / code-tab >}}

chromedriverは、Chromeの内部自動化プロキシインターフェイスを公開することにより、ブラウザーに処理を指示するWebDriverリモートサーバーとして実装されています。

### Firefox

Selenium 3以降、MozillaはFirefoxドライバーの実装である [geckodriver](//github.com/mozilla/geckodriver) を引き継ぎました。
Firefox用の新しいドライバーはgeckodriverと呼ばれ、Firefox 48以降で動作します。
Firefox WebDriverは開発中であるため、Firefoxバージョンが新しいほどサポートが向上します。

geckodriverはFirefoxを起動する新しいデフォルトの方法であるため、
Selenium 2と同じ方法でFirefoxをインスタンス化できます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

WebDriver driver = new FirefoxDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Firefox

driver = Firefox()
#Or use the context manager
from selenium.webdriver import Firefox

with Firefox() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;

IWebDriver driver = new FirefoxDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :firefox
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('firefox').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Firefox.FirefoxDriver

val driver: WebDriver = FirefoxDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

PATHを使用してgeckodriverの場所を設定しない場合は、
geckodriverバイナリの場所をプログラムで設定します。

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Firefox(executable_path='/path/to/geckodriver')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new FirefoxDriver("/path/to/geckodriver");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Firefox.driver_path = "/path/to/geckodriver"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const firefox = require('selenium-webdriver/firefox');

const serviceBuilder = new firefox.ServiceBuilder("/path/to/geckodriver");

(async function myFunction() {
    let driver = await new Builder()
        .forBrowser('firefox')
        .setFirefoxService(serviceBuilder)
        .build();
        //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver")
  {{< / code-panel >}}
{{< / code-tab >}}

実行時にプロパティを設定することも可能です。

```shell
mvn test -Dwebdriver.gecko.driver=/path/to/geckodriver
```

Firefox [47.0.1](//ftp.mozilla.org/pub/firefox/releases/47.0.1/) または [45 ESR](//ftp.mozilla.org/pub/firefox/releases/45.0esr/) をインストール、および **marionette** の必要な機能を **false** として指定することにより、多くの機能が完了した古いFirefoxドライバーに戻すことが可能です。
以降のFirefoxのリリースには互換性がありません。

### Edge

Edgeは、Microsoftの最新のブラウザーで、Windows 10およびServer 2016に含まれています。
Edgeの更新は、主要なWindowsUpdateプログラムにバンドルされているため、現在インストールされているWindowsビルドのビルド番号に一致するバイナリをダウンロードする必要があります。 
[Edge Developer サイト](//developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)には、利用可能なすべてのバイナリへのリンクが含まれています。 
EdgeDriverの実装に対するバグは、[Microsoft](//developer.microsoft.com/en-us/microsoft-edge/platform/issues/?page=1&q=webdriver)に起票する可能性があります。 
Edgeに対してテストを実行したいがWindows 10を実行していない場合、MicrosoftはEdge [Edge Developer サイト](//developer.microsoft.com/en-us/microsoft-edge/tools/vms/)でテスター向けに無料のVMを提供しています。

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

WebDriver driver = new EdgeDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Edge

driver = Edge()
#Or use the context manager
from selenium.webdriver import Edge

with Edge() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Edge;

IWebDriver driver = new EdgeDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :edge
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('MicrosoftEdge').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver

val driver: WebDriver = EdgeDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

Edgeドライバーがパスに存在しない場合、次の行を使用してパスを設定できます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.edge.driver", "C:/path/to/MicrosoftWebDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Edge(executable_path='/path/to/MicrosoftWebDriver.exe')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new EdgeDriver("/path/to/MicrosoftWebDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::Edge.driver_path = "C:/path/to/MicrosoftWebDriver.exe"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const edge = require('selenium-webdriver/edge');
let service = new edge.ServiceBuilder("/path/to/msedgedriver.exe");
(async function test() {
    let driver = await new Builder()
                .setEdgeService(service)
                .forBrowser('MicrosoftEdge')
                .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.edge.driver", "C:/path/to/MicrosoftWebDriver.exe")
  {{< / code-panel >}}
{{< / code-tab >}}

### Internet Explorer
Internet Explorerは、Windows 10まではMicrosoftのデフォルトのブラウザーでしたが、Windows 10にはまだ含まれています。
Internet ExplorerドライバーはSeleniumプロジェクトは、同じリリースの[Microsoftの現在の考慮](//support.microsoft.com/en-gb/help/17454/lifecycle-support-policy-faq-internet-explorer)をサポートすることを目指す唯一のドライバーです。
古いリリースは動作する可能性がありますが、サポートされません。

SeleniumプロジェクトはInternet Explorerの32ビット版と64ビット版の両方のバイナリを提供しますが、64ビットドライバーを使用するInternet Explorer 10および11にはいくつかの[制限](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)がありますが、32ビットドライバーの使うことでうまく機能します。
ログインしたユーザーのアカウントに対してInternet Explorerの設定が保存されるため、[追加の設定が必要になること](//github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver#required-configuration)に注意してください。

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

WebDriver driver = new InternetExplorerDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Ie

driver = Ie()
#Or use the context manager
from selenium.webdriver import Ie

with Ie() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

IWebDriver driver = new InternetExplorerDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :internet_explorer
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('internet explorer').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.ie.InternetExplorerDriver

val driver: WebDriver = InternetExplorerDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

Internet Explorerドライバーがパスに存在しない場合、次の行を使用してパスを設定できます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
System.setProperty("webdriver.ie.driver", "C:/path/to/IEDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
Ie(executable_path='/path/to/IEDriverServer.exe')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
new InternetExplorerDriver("C:/path/to/IEDriver.exe");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
Selenium::WebDriver::IE.driver_path = "C:/path/to/IEDriver.exe"
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const ie = require('selenium-webdriver/ie');
let service = new ie.ServiceBuilder("/path/to/IEDriverServer.exe");
(async function test() {
    let driver = await new Builder()
                .setIeService(service)
                .forBrowser('internet explorer')
                .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
System.setProperty("webdriver.ie.driver", "C:/path/to/IEDriver.exe")
  {{< / code-panel >}}
{{< / code-tab >}}

Microsoftは、[Internet Explorer 11 on Windows 7 & 8.1](//www.microsoft.com/en-gb/download/details.aspx?id=44069)上のInternet Explorer 11用のWebDriverバイナリも提供しています。 
2014年以降更新されておらず、W3仕様のドラフトバージョンに基づいています。 [ジム エバンス](//jimevansmusic.blogspot.co.uk/2014/09/using-internet-explorer-webdriver.html)は、Microsoftの実装に関する優れた記事を持っています。

### Opera
Operaの現在のリリースはChromiumエンジンの上に構築されており、WebDriverは[PATHに追加](#adding-executables-to-your-path)したりシステムプロパティとして追加したりできるクローズドソースの[Opera Chromium Driver](//github.com/operasoftware/operachromiumdriver/releases)を介してサポートされるようになりました。

ドライバーセッションのインスタンス化は、FirefoxとChromiumに似ています。

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

WebDriver driver = new OperaDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Opera

driver = Opera()
#Or use the context manager
from selenium.webdriver import Opera

with Opera() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Opera;

IWebDriver driver = new OperaDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :opera
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require("selenium-webdriver");
const opera = require('selenium-webdriver/opera');
(async function test() {
    let driver = await new Builder()
        .forBrowser('opera')
        .build();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.opera.OperaDriver

val driver: WebDriver = OperaDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

### Safari

High Sierra 以降 :
* まず端末から次のコマンドを実行し、プロンプトでパスワードを入力してWebDriverを認証します
```shell
safaridriver --enable
```

El Capitan と Sierra :

* Safariの設定から開発メニューを有効にします
* 開発メニューから _リモート オートメーションを許可_ オプションをオンにします
* まずターミナルから次のコマンドを実行し、プロンプトでパスワードを入力してWebDriverを認証します

```shell
/usr/bin/safaridriver -p 1337</
```

その後、次を使用してドライバーセッションを開始できます。

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

WebDriver driver = new SafariDriver();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#Simple assignment
from selenium.webdriver import Safari

driver = Safari()
#Or use the context manager
from selenium.webdriver import Safari

with Safari() as driver:
   #your code inside this indent
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Safari;

IWebDriver driver = new SafariDriver();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require "selenium-webdriver"

driver = Selenium::WebDriver.for :safari
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');

(async function myFunction() {
   let driver = await new Builder().forBrowser('safari').build();
   //your code inside this block
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.safari.SafariDriver

val driver: WebDriver = SafariDriver()
  {{< / code-panel >}}
{{< / code-tab >}}

iOSでSafariを自動化する場合は、[Appium project](//appium.io/)をご覧ください。
Safariは以前はWindowsで利用可能でしたが、Appleは長い間サポートを終了しており、テストプラットフォームの選択肢としては不適切です。

## モック ブラウザー

### HtmlUnit

HtmlUnitは "Javaプログラム用のGUIレスブラウザー" です。 HTMLドキュメントをモデル化し、ページの呼び出し、フォームへの入力、リンクのクリックなどを可能にするAPIを提供します。
JavaScriptをサポートしており、AJAXライブラリを使用して、使用する設定に応じてChrome、Firefox、またはInternet Explorerをシミュレートできます。
[新しい場所](http://htmlunit.sourceforge.net/gettingStarted.html)に移動しました。
ソースはsvnで管理されています。

### PhantomJS

PhantomJSは、Google ChromeまたはSafariで使用されているバージョンよりもはるかに古いバージョンですが、Webkitベースのヘッドレスブラウザーです。
歴史的に人気のある選択肢でしたが、今ではPhantomJSを避ける​​のが賢明です。
プロジェクトは[8月5日以降](//groups.google.com/forum/#!topic/phantomjs/9aI5d-LDuNE)メンテナンスされていないため、Webの変更は継続されますが、PhantomJSは更新されません。これは、GoogleがChromeをヘッドレスで実行する機能を発表した後のことで、MozillaのFirefoxでも提供されています。
