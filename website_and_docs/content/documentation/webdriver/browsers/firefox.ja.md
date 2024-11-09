---
title: "Firefox特有の機能"
linkTitle: "Firefox"
weight: 6
description: >-
    これらは、Mozilla Firefoxブラウザに特有の機能と機能です。
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

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L36-L37" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L10-L11" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L34-L35" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L10-L11" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openFirefoxTest.spec.js#L10-L13">}}
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

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L44" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L19" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L43" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L17" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/firefoxSpecificFunctionalities.spec.js#L12-L14">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 指定したロケーションでブラウザを起動する

`binary` パラメーターは、使用するブラウザーの別のロケーションのパスを取ります。
たとえば、このパラメーターを使用すると、geckodriver を使用して、製品版とFirefox Nightlyの両方がコンピューターに存在する場合、
製品版の代わりに Firefox Nightly を駆動できます 。

オプションにブラウザーのロケーションを追加します。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L54" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L28" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L53" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L25" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

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
  {{< tab header="Ruby" text=true >}}
  {{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L139-L141" >}}
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


## サービス

すべてのブラウザに共通するサービス設定は、[Service page]({{< ref "../drivers/service.md" >}})に記載されています。

### ログ出力

ドライバーログを取得することは、さまざまな問題のデバッグに役立ちます。サービスクラスを使用すると、ログの保存先を指定できます。ログ出力は、ユーザーがどこかに指定しない限り無視されます。

#### ファイル出力

特定のファイルにログ出力を保存するには:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L62-L63" >}}
**注**: Java では、システムプロパティによってファイル出力を設定することもできます。\
プロパティキー:`GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY`\
プロパティ値: ログファイルへのパスを表す文字列
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L36" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L43" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### コンソール出力

ログ出力をコンソールに表示するには、以下のようにします:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L76-L77" >}}
**注意**: Javaは、システムプロパティを使用してコンソール出力を設定することもできます;\
プロパティキー: `GeckoDriverService.GECKO_DRIVER_LOG_PROPERTY`\
プロパティ値: `DriverService.LOG_STDOUT` または `DriverService.LOG_STDERR`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L48" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L52" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ログレベル
利用可能なログレベルは7つあります: `fatal`, `error`, `warn`, `info`, `config`, `debug`, `trace`。
ロギングが指定されている場合、デフォルトのレベルは `info`になります。

`-v` iは `-log debug` と同等であり、`-vv` は `log trace`と同等です。
したがって、この例は一般的にログレベルを設定するためのものです:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L90-L91" >}}
**注意**: Javaは、システムプロパティによってログレベルの設定も可能です:\
プロパティキー: `GeckoDriverService.GECKO_DRIVER_LOG_LEVEL_PROPERTY`\
プロパティ値:`FirefoxDriverLogLevel`列挙型の文字列表現
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L59" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L63" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### トランケートログ

ドライバーは、大きなバイナリの文字列表現を含む、送信されたすべてのものをログに記録します。そのため、Firefoxではデフォルトで行が切り捨てられます。切り捨てを無効にするには:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L106-L107" >}}
**注意**: Javaでは、システムプロパティによってログレベルを設定することもできます。\
プロパティキー: `GeckoDriverService.GECKO_DRIVER_LOG_NO_TRUNCATE`\
プロパティ値: `"true"` または `"false"`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L70" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L72" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### プロファイルルート

プロファイルのデフォルトディレクトリは、システムの一時ディレクトリです。そのディレクトリにアクセスできない場合や、特定の場所にプロファイルを作成したい場合は、プロファイルルートディレクトリを変更できます:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L118-L119" >}}
**注意**: Javaでは、システムプロパティを使用してログレベルを設定することもできます：  \
プロパティキー: `GeckoDriverService.GECKO_DRIVER_PROFILE_ROOT`\
プロパティ値: プロファイルルートディレクトリへのパスを表す文字列
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_firefox.py#L81" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/firefox_spec.rb#L81" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## 特別な機能

### アドオン

Chromeとは異なり、Firefoxの拡張機能はCapabilityの一部として追加されるのではなく、ドライバーの起動後に作成されます。

Chromeとは異なり、Firefoxの拡張機能は[この問題](https://github.com/mozilla/geckodriver/issues/1476)に記載されているように、機能の一部として追加されるのではなく、ドライバーの起動後に作成されます。

T以下の例はローカルWebDriver用です。リモートWebDriverについては、[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}})ページを参照してください。

#### インストール

[Mozilla Add-Onsページ](https://addons.mozilla.org/ja/firefox/) から取得する署名付きxpiファイル

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L133" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L94" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L137" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L95" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/firefoxSpecificFunctionalities.spec.js#L25">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### アンインストール

アドオンをアンインストールするには、そのIDを知る必要があります。
IDはアドオンインストール時の戻り値から取得できます。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L148" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L106" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L152" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L106" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/firefoxSpecificFunctionalities.spec.js#L26">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### 署名なしのインストール

未完成または未公開の拡張機能を使用する場合、署名されていない可能性があります。
そのため、"一時的なもの" としてのみインストールできます。
これは、zipファイルまたはディレクトリを渡すことで実行できます。ディレクトリの例を次に示します。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L160" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L115" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/FirefoxTest.cs#L165" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.5" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L115" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/firefoxSpecificFunctionalities.spec.js#L41">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ページ全体のスクリーンショット

以下の例はローカルWebDriver用です。リモートWebDriverについては、[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}})ページを参照してください。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L139" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L125" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### コンテキスト

以下の例はローカルWebDriver用です。リモートWebDriverについては、[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}})ページを参照してください。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L149-L150" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L132" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
