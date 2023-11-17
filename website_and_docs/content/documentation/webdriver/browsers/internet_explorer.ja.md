---
title: "IE specific functionality"
linkTitle: "Internet Explorer"
weight: 8
description: >-
    These are capabilities and features specific to Microsoft Internet Explorer browsers.
aliases: [
"/ja/documentation/capabilities/internet_explorer"
]
---

As of June 2022, Selenium officially no longer supports standalone Internet Explorer.
The Internet Explorer driver still supports running Microsoft Edge in "IE Compatibility Mode."

## Special considerations

The IE Driver is the only driver maintained by the Selenium Project directly.
While binaries for both the 32-bit and 64-bit
versions of Internet Explorer are available, there are some
[known limitations](//jimevansmusic.blogspot.co.uk/2014/09/screenshots-sendkeys-and-sixty-four.html)
with the 64-bit driver. As such it is recommended to use the 32-bit driver.

Additional information about using Internet Explorer can be found on the
[IE Driver Server page]({{< ref "/documentation/ie_driver_server/" >}})

## Options

Starting a Microsoft Edge browser in Internet Explorer Compatibility mode with basic defined options looks like this:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#38-L41" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L11-L14" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L35-L38" >}}
{{% /tab %}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L10-L13" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

As of Internet Explorer Driver v4.5.0:
* If IE is not present on the system (default in Windows 11), you do not need to 
use the two parameters above. IE Driver will use Edge and will automatically locate it. 
* If IE and Edge are both present on the system, you only need to set attaching to Edge,
IE Driver will automatically locate Edge on your system.

So, if IE is not on the system, you only need:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#46-L47" >}}
{{< /tab >}}
{{% tab header="Python" text=true %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L21-L22" >}}
{{% /tab %}}
{{% tab header="CSharp" text=true %}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L44-L45" >}}
{{% /tab %}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L17-L18" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
let driver = await new Builder()
.forBrowser('internet explorer')
.setIEOptions(options)
.build();
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-examples >}}
val options = InternetExplorerOptions()
val driver = InternetExplorerDriver(options)
{{< /tab >}}
{{< /tabpane >}}

Here are a few common use cases with different capabilities:

### fileUploadDialogTimeout

環境によっては、ファイルアップロードダイアログを開くときにInternet Explorerがタイムアウトする場合があります。 IEDriverのデフォルトのタイムアウトは1000ミリ秒ですが、fileUploadDialogTimeout capabilityを使用してタイムアウトを増やすことができます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.waitForUploadDialogUpTo(Duration.ofSeconds(2));
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.file_upload_dialog_timeout = 2000
driver = webdriver.Ie(options=options)

driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.FileUploadDialogTimeout = TimeSpan.FromMilliseconds(2000);
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.file_upload_dialog_timeout = 2000
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().fileUploadDialogTimeout(2000);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.waitForUploadDialogUpTo(Duration.ofSeconds(2))
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### ensureCleanSession

この機能を `true` に設定すると、手動またはドライバーによって開始されたものを含め、
InternetExplorerの実行中のすべてのインスタンスのキャッシュ、ブラウザー履歴、およびCookieがクリアされます。
デフォルトでは、`false` に設定されています。

この機能を使用すると、ドライバーがIEブラウザーを起動する前にキャッシュがクリアされるまで待機するため、
ブラウザーの起動中にパフォーマンスが低下します。

このケイパビリティは、ブール値をパラメーターとして受け入れます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.destructivelyEnsureCleanSession();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ensure_clean_session = True
driver = webdriver.Ie(options=options)

driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.EnsureCleanSession = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ensure_clean_session = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ensureCleanSession(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.destructivelyEnsureCleanSession()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### ignoreZoomSetting

InternetExplorerドライバーは、ブラウザーのズームレベルが100％であることを想定しています。
それ以外の場合、ドライバーは例外をスローします。
このデフォルトの動作は、 _ignoreZoomSetting_ を _true_ に設定することで無効にできます。

このケイパビリティは、ブール値をパラメーターとして受け入れます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.ignoreZoomSettings();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ignore_zoom_level = True
driver = webdriver.Ie(options=options)

driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.IgnoreZoomLevel = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ignore_zoom_level = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().ignoreZoomSetting(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.ignoreZoomSettings()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### ignoreProtectedModeSettings

新しいIEセッションの起動中に _保護モード_ チェックをスキップするかどうか。

設定されておらず、 _保護モード_ 設定がすべてのゾーンで同じでない場合、
ドライバーによって例外がスローされます。

ケイパビリティを `true` に設定すると、テストが不安定になったり、応答しなくなったり、
ブラウザがハングしたりする場合があります。
ただし、これはまだ2番目に良い選択であり、最初の選択は *常に* 
各ゾーンの保護モード設定を手動で実際に設定することです。
ユーザーがこのプロパティを使用している場合、「ベストエフォート」のみがサポートされます。

このケイパビリティは、ブール値をパラメーターとして受け入れます。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.introduceFlakinessByIgnoringSecurityDomains();
WebDriver driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.ignore_protected_mode_settings = True
driver = webdriver.Ie(options=options)

driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new InternetExplorerOptions();
options.IntroduceInstabilityByIgnoringProtectedModeSettings = true;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
options = Selenium::WebDriver::IE::Options.new
options.ignore_protected_mode_settings = true
driver = Selenium::WebDriver.for(:ie, options: options)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options().introduceFlakinessByIgnoringProtectedModeSettings(true);
let driver = await Builder()
          .setIeOptions(options)
          .build(); 
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = InternetExplorerOptions()
options.introduceFlakinessByIgnoringSecurityDomains()
val driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}

### silent

`true` に設定すると、このケイパビリティはIEDriverServerの診断出力を抑制します。

このケイパビリティは、ブール値をパラメーターとして受け入れます。
 
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.setCapability("silent", true);
WebDriver driver = new InternetExplorerDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.set_capability("silent", True)
driver = webdriver.Ie(options=options)

driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
InternetExplorerOptions options = new InternetExplorerOptions();
options.AddAdditionalInternetExplorerOption("silent", true);
IWebDriver driver = new InternetExplorerDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    {{< badge-code >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const {Builder,By, Capabilities} = require('selenium-webdriver');
let caps = Capabilities.ie();
caps.set('silent', true);

(async function example() {
    let driver = await new Builder()
        .forBrowser('internet explorer')
        .withCapabilities(caps)
        .build();
    try {
        await driver.get('http://www.google.com/ncr');
    }
    finally {
        await driver.quit();
    }
})();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.setCapability("silent", true)
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps)
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

### IE Command-Line Options

Internet Explorerには、ブラウザーのトラブルシューティングと構成を可能にするいくつかのコマンドラインオプションが含まれています。

次に、サポートされているいくつかのコマンドラインオプションについて説明します。

* _-private_ : IEをプライベートブラウジングモードで起動するために使用されます。
これはIE 8以降のバージョンで機能します。

* _-k_ : Internet Explorerをキオスクモードで起動します。
ブラウザは、アドレスバー、ナビゲーションボタン、またはステータスバーを表示しない最大化されたウィンドウで開きます。

* _-extoff_ : アドオンなしモードでIEを起動します。
このオプションは、ブラウザーのアドオンに関する問題のトラブルシューティングに特に使用されます。
IE 7以降のバージョンで動作します。

注：コマンドライン引数が機能するためには、 __forceCreateProcessApi__ を順番に有効にする必要があります。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class ieTest {
    public static void main(String[] args) {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.useCreateProcessApiToLaunchIe();
        options.addCommandSwitches("-k");
        InternetExplorerDriver driver = new InternetExplorerDriver(options);
        try {
            driver.get("https://google.com/ncr");
            Capabilities caps = driver.getCapabilities();
            System.out.println(caps);
        } finally {
            driver.quit();
        }
    }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.add_argument('-private')
options.force_create_process_api = True
driver = webdriver.Ie(options=options)

driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

namespace ieTest {
 class Program {
  static void Main(string[] args) {
   InternetExplorerOptions options = new InternetExplorerOptions();
   options.ForceCreateProcessApi = true;
   options.BrowserCommandLineArguments = "-k";
   IWebDriver driver = new InternetExplorerDriver(options);
   driver.Url = "https://google.com/ncr";
  }
 }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
options = Selenium::WebDriver::IE::Options.new
options.force_create_process_api = true
options.add_argument('-k')
driver = Selenium::WebDriver.for(:ie, options: options)

begin
  driver.get 'https://google.com'
  puts(driver.capabilities.to_json)
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.addBrowserCommandSwitches('-k');
options.addBrowserCommandSwitches('-private');
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}

import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.useCreateProcessApiToLaunchIe()
    options.addCommandSwitches("-k")
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps);
    } finally {
        driver.quit()
    }
}
  {{< /tab >}}
{{< /tabpane >}}

### forceCreateProcessApi

CreateProcess APIを使用してInternet Explorerを強制的に起動します。
デフォルト値はfalseです。

IE 8以降の場合、このオプションでは "TabProcGrowth" レジストリの値を0に設定する必要があります。

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class ieTest {
    public static void main(String[] args) {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.useCreateProcessApiToLaunchIe();
        InternetExplorerDriver driver = new InternetExplorerDriver(options);
        try {
            driver.get("https://google.com/ncr");
            Capabilities caps = driver.getCapabilities();
            System.out.println(caps);
        } finally {
            driver.quit();
        }
    }
}
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium import webdriver

options = webdriver.IeOptions()
options.force_create_process_api = True
driver = webdriver.Ie(options=options)

driver.get("http://www.google.com")

driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using System;
using OpenQA.Selenium;
using OpenQA.Selenium.IE;

namespace ieTest {
 class Program {
  static void Main(string[] args) {
   InternetExplorerOptions options = new InternetExplorerOptions();
   options.ForceCreateProcessApi = true;
   IWebDriver driver = new InternetExplorerDriver(options);
   driver.Url = "https://google.com/ncr";
  }
 }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
options = Selenium::WebDriver::IE::Options.new
options.force_create_process_api = true
driver = Selenium::WebDriver.for(:ie, options: options)

begin
  driver.get 'https://google.com'
  puts(driver.capabilities.to_json)
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const ie = require('selenium-webdriver/ie');
let options = new ie.Options();
options.forceCreateProcessApi(true);

driver = await env.builder()
          .setIeOptions(options)
          .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.Capabilities
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions

fun main() {
    val options = InternetExplorerOptions()
    options.useCreateProcessApiToLaunchIe()
    val driver = InternetExplorerDriver(options)
    try {
        driver.get("https://google.com/ncr")
        val caps = driver.getCapabilities()
        println(caps)
    } finally {
        driver.quit()
    }
}

  {{< /tab >}}
{{< /tabpane >}}



## Service

Service settings common to all browsers are described on the [Service page]({{< ref "../drivers/service.md" >}}).

### Log output

Getting driver logs can be helpful for debugging various issues. The Service class lets you
direct where the logs will go. Logging output is ignored unless the user directs it somewhere.

#### File output

To change the logging output to save to a specific file:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L53" >}}
**Note**: Java also allows setting file output by System Property:\
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY`\
Property value: String representing path to log file
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L29" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L34" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### Console output

To change the logging output to display in the console as STDOUT:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L67" >}}
**Note**: Java also allows setting console output by System Property;\
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGFILE_PROPERTY`\
Property value: `DriverService.LOG_STDOUT` or `DriverService.LOG_STDERR`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L41" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L43" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Log Level
There are 6 available log levels: `FATAL`, `ERROR`, `WARN`, `INFO`, `DEBUG`, and `TRACE`
If logging output is specified, the default level is `FATAL`

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L82" >}}
**Note**: Java also allows setting log level by System Property:\
Property key: `InternetExplorerDriverService.IE_DRIVER_LOGLEVEL_PROPERTY`\
Property value: String representation of `InternetExplorerDriverLogLevel.DEBUG.toString()` enum
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L53" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L85" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L54" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Supporting Files Path

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#L94" >}}
**Note**: Java also allows setting log level by System Property:\
Property key: `InternetExplorerDriverService.IE_DRIVER_EXTRACT_PATH_PROPERTY`\
Property value: String representing path to supporting files directory
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_internet_explorer.py#L65" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L98" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/internet_explorer_spec.rb#L64" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

