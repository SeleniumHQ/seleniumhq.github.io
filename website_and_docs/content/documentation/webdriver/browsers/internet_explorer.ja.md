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

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/InternetExplorerTest.java#28-L31" >}}
{{< /tab >}}
{{% tab header="Python" %}}
Note that Python must specify service class to use [Driver Manager]({{< ref "../getting_started/install_drivers.md" >}})
{{< gh-codeblock path="/examples/python/tests/browsers/test_internet_explorer.py#L14-L17" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
Note that the .NET [Driver Manager]({{< ref "../getting_started/install_drivers#1-driver-management-software" >}})
does not support Internet Explorer, so the location must be in a
[directory on PATH]({{< ref "../getting_started/install_drivers#2-the-path-environment-variable" >}}),
or specified explicitly as in this example.
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/InternetExplorerTest.cs#L24-L32" >}}
{{% /tab %}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/internet_explorer_spec.rb#L8-L10" >}}
{{< /tab >}}
{{< tab header="JavaScript" code=true >}}
  let driver = await new Builder()
    .forBrowser('internet explorer')
    .setIEOptions(options)
    .build();
{{< /tab >}}
{{< tab header="Kotlin" code=true >}}
  val options = InternetExplorerOptions()
  options.attachToEdgeChrome()
  options.withEdgeExecutablePath("/path/to/edge/browser")
  val driver = InternetExplorerDriver(options)
{{< /tab >}}
{{< /tabpane >}}

As of Internet Explorer Driver v4.5.0:
* If IE is not present on the system (default in Windows 11), you do not need to 
use the two parameters above. IE Driver will use Edge and will automatically locate it. 
* If IE and Edge are both present on the system, you only need to set attaching to Edge,
IE Driver will automatically locate Edge on your system.

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
