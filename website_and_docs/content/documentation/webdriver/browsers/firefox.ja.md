---
title: "Firefox 固有のCapability"
linkTitle: "Firefox"
weight: 6
description: >-
    Mozilla Firefox ブラウザーに固有のCapabilityです。
aliases: [
"/ja/documentation/capabilities/firefox"
]
---

Selenium 4 には Firefox 78 以降が必要です。 
常に最新バージョンの geckodriver を使用することをお勧めします。

## オプション

全ブラウザに共通のCapabilityについては、[オプションページ]({{< ref "../drivers/options.md" >}})で説明しています。

Firefox に固有のCapabilityは、Mozilla のページの [firefoxOptions](https://developer.mozilla.org/en-US/docs/Web/WebDriver/Capabilities/firefoxOptions) にあります。

基本的な定義済みのオプションを使用して Firefox セッションを開始すると、以下のようになります。

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L24-L25" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L9-L10" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L23-L24" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L9-L10" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openFirefoxTest.spec.js#L10-L14">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

さまざまなCapabilityを備えた一般的な使用例をいくつか示します。

### 引数

`args` パラメータは、ブラウザの起動時に使用するコマンドラインスイッチのリストです。
一般的に使用される引数には、 `-headless` と `"-profile"` 、`"/path/to/profile"` が含まれます。

オプションに引数を追加します。

<div>
{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L63-L64" >}}
{{< /tab >}}
{{< tab header="Python" code=true >}}
options=Options()
options.add_argument("-profile")
options.add_argument("/path/to/profile")
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L74-L76" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/firefoxSpecificFunctionalities.js#L7-L10">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
</div>

### 指定したロケーションでブラウザを起動する

`binary` パラメーターは、使用するブラウザーの別のロケーションのパスを取ります。
たとえば、このパラメーターを使用すると、geckodriver を使用して、製品版とFirefox Nightlyの両方がコンピューターに存在する場合、
製品版の代わりに Firefox Nightly を駆動できます 。

オプションにブラウザーのロケーションを追加します。

{{< alert-code />}}

### プロファイル

Firefoxプロファイルを操作するにはいくつかの方法があります。

<div>
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
FirefoxProfile profile = new FirefoxProfile();
FirefoxOptions options = new FirefoxOptions();
options.setProfile(profile);
driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.firefox.firefox_profile import FirefoxProfile
options=Options()
firefox_profile = FirefoxProfile()
firefox_profile.set_preference("javascript.enabled", False)
options.profile = firefox_profile
  {{< /tab >}}
  {{< tab header="CSharp" >}}
var options = new FirefoxOptions();
var profile = new FirefoxProfile();
options.Profile = profile;
var driver = new RemoteWebDriver(options);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
profile = Selenium::WebDriver::Firefox::Profile.new
profile['browser.download.dir'] = "/tmp/webdriver-downloads"
options = Selenium::WebDriver::Firefox::Options.new(profile: profile)
driver = Selenium::WebDriver.for :firefox, options: options
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const { Builder } = require("selenium-webdriver");
const firefox = require('selenium-webdriver/firefox');

const options = new firefox.Options();
let profile = '/path to custom profile';
options.setProfile(profile);
const driver = new Builder()
    .forBrowser('firefox')
    .setFirefoxOptions(options)
    .build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val options = FirefoxOptions()
options.profile = FirefoxProfile()
driver = RemoteWebDriver(options)
  {{< /tab >}}
{{< /tabpane >}}
</div>

## アドオン

Chromeとは異なり、Firefoxの拡張機能はCapabilityの一部として追加されるのではなく、ドライバーの起動後に作成されます。

Unlike Chrome, Firefox extensions are not added as part of capabilities as mentioned in
[this issue](https://github.com/mozilla/geckodriver/issues/1476),
they are created after starting the driver.

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}}) page.

### インストール

[Mozilla Add-Onsページ](https://addons.mozilla.org/ja/firefox/) から取得する署名付きxpiファイル

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L31-L32" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L17-L18" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L32-L34" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L15-L16" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### アンインストール

アドオンをアンインストールするには、そのIDを知る必要があります。
IDはアドオンインストール時の戻り値から取得できます。

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L42-L44" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L29-L31" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L47-L50" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L24-L26" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 署名なしのインストール

未完成または未公開の拡張機能を使用する場合、署名されていない可能性があります。
そのため、"一時的なもの" としてのみインストールできます。
これは、zipファイルまたはディレクトリを渡すことで実行できます。ディレクトリの例を次に示します。

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L53-L54" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L40-L41" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L61-L63" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L33-L34" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## ページ全体のスクリーンショット

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}}) page.

{{< alert-code />}}

## コンテキスト

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}}) page.

{{< alert-code />}}
