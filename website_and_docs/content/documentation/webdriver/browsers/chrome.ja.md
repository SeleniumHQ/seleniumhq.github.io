---
title: "Chrome固有の機能"
linkTitle: "Chrome"
weight: 4
description: >-
    これらは、Google Chromeブラウザに特有の機能と機能です。
aliases: [
"/ja/documentation/capabilities/chromium"
]
---

これらは、Google Chromeブラウザに特有の機能と機能です。
デフォルトでは、Selenium 4はChrome v75以上と互換性があります。Chromeブラウザのバージョンとchromedriverのバージョンは、メジャーバージョンが一致する必要があることに注意してください。

## Options

すべてのブラウザに共通する機能は [オプション ページ]({{< ref "../drivers/options.md" >}})に記載されています。

ChromeおよびChromiumに特有の機能は、Googleの [Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)のページにドキュメントされています。

基本的に定義されたオプションでChromeセッションを開始する場合は、次のようになります：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L37-L38" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L9-L10" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L30-L31" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L10-L11" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L51-L55">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


### 引数

`args` パラメータは、ブラウザを起動する際に使用するコマンドラインスイッチのリストです。これらの引数を調査するための優れたリソースが2つあります：
* [Chromeツール用フラグ](https://github.com/GoogleChrome/chrome-launcher/blob/main/docs/chrome-flags-for-tools.md)
* [Chromiumコマンドラインスイッチの一覧](https://peter.sh/experiments/chromium-command-line-switches/)

一般的に使用されるargsには以下が含まれます：`--start-maximized`, `--headless=new` and `--user-data-dir=...`

オプションに引数を追加：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L45" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L18" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L39" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L17" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L9-L12">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 指定したロケーションでブラウザを起動する

`binary`パラメーターは、使用するブラウザの別のロケーションのパスを取ります。 
このパラメーターを使用すると、chromedriver を使用して、さまざまな Chromium ベースのブラウザを駆動できます。

オプションにブラウザのロケーションを追加します。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L54" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L29">}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L49" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L25" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L41-L44">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 拡張機能を追加する

`extensions` パラメーターはcrxファイルを受け入れます

The `extensions` パラメータはcrxファイルを受け入れます。解凍されたディレクトリについては、代わりに `load-extension` 引数を使用してください。[この投稿](https://chromedriver.chromium.org/extensions)で述べたように。

オプションに拡張機能を追加します。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L65" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L40">}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L61" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L34" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L62-L66">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ブラウザを開いたままにする

`detach` パラメータをtrueに設定すると、ドライバープロセスが終了した後もブラウザを開いたままにできます。

{{< tabpane text=true >}}
{{% tab header="Java" %}}
**注意**: これはすでにJavaのデフォルトの動作です。
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L51" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
**注意**: これはすでに.NETのデフォルトの動作です。
{{% /tab %}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L45" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L29-L32">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 引数を除外する

Chrome はさまざまな引数を追加します。
これらの引数を追加したくない場合は、それらを `excludeSwitches` に渡します。 
一般的な例は、ポップアップブロッカーをオンに設定することです。

デフォルトの引数の完全なリストは、
[Chromium Source Code](https://source.chromium.org/chromium/chromium/src/+/main:chrome/test/chromedriver/chrome_launcher.cc)から解析できます。

オプションに除外された引数を設定します。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L78" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L62" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L76" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L53" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/chromeSpecificCaps.spec.js#L19-L22">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## サービス

デフォルトのServiceオブジェクトを作成するための例や、ドライバーの場所とポートを設定する方法は、[Driver Service]({{< ref "../drivers/service.md" >}})ページにあります。

### ログ出力

ドライバーログを取得することは、問題のデバッグに役立ちます。Serviceクラスを使用すると、ログの出力先を指定できます。ユーザーがどこかにログを指示しない限り、ログ出力は無視されます。

#### ファイル出力

ログ出力を特定のファイルに保存するように変更するには：

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L100-L101" >}}
**注意**: Javaでは、システムプロパティによってファイル出力を設定することもできます：\
プロパティキー: `ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY`\
プロパティ値: ログファイルへのパスを表す文字列
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_chrome.py#L71" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L86" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/chrome_spec.rb#L67" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### コンソール出力

ログ出力をコンソールにSTDOUTとして表示するように変更するには：

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L114-L115" >}}
**注意**: Javaでは、システムプロパティによってコンソール出力を設定することもできます。\
プロパティキー: `ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY`\
プロパティ値: `DriverService.LOG_STDOUT` または `DriverService.LOG_STDERR`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_chrome.py#L82" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{% tab header="Ruby" %}}
`$stdout` と `$stderr` はどちらも有効な値です。
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/chrome_spec.rb#L76" >}}
{{% /tab %}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ログレベル
利用可能なログレベルは6つあります：`ALL`, `DEBUG`, `INFO`, `WARNING`, `SEVERE`, そして `OFF`。`--verbose` は `--log-level=ALL` と同等であり、`--silent` は `--log-level=OFF`と同等であることに注意してください。このため、この例ではログレベルを一般的に設定しています：

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L129-L130" >}}
**注意**: Javaでは、システムプロパティによってログレベルを設定することもできます：\
プロパティキー: `ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY`\
プロパティ値: `ChromiumDriverLogLevel` 列挙型の文字列表現
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_chrome.py#L93" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/chrome_spec.rb#L87" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ログファイル機能
ファイルにログを記録する際にのみ利用できる2つの機能があります：
* ログの追加
* 読みやすいタイムスタンプ

これらを使用するには、ログパスとログレベルも明示的に指定する必要があります。ログ出力はプロセスではなくドライバーによって管理されるため、若干の違いが見られる場合があります。

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L147-L148" >}}
**注意**: Javaでは、これらの機能をシステムプロパティによって切り替えることもできます：\
プロパティキー: `ChromeDriverService.CHROME_DRIVER_APPEND_LOG_PROPERTY` および`ChromeDriverService.CHROME_DRIVER_READABLE_TIMESTAMP`\
プロパティ値: `"true"` または `"false"`
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_chrome.py#L104" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/chrome_spec.rb#L97-L98" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ビルドチェックの無効化

ChromedriverとChromeブラウザのバージョンは一致する必要があり、一致しない場合、ドライバーはエラーを返します。ビルドチェックを無効にすると、任意のバージョンのChromeでドライバーを強制的に使用できます。ただし、これはサポートされていない機能であり、バグは調査されません。

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L166-L167" >}}
**注意**: Javaでは、システムプロパティによってビルドチェックを無効にすることもできます：\
プロパティキー: `ChromeDriverService.CHROME_DRIVER_DISABLE_BUILD_CHECK`\
プロパティ値: `"true"` または `"false"`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_chrome.py#L115" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs#L155" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/chrome_spec.rb#L108" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## 特別な機能

一部のブラウザは、それぞれに特有の追加機能を実装しています。

### キャスティング

Chrome Castデバイスを操作することができ、タブの共有も含まれます。

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
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L119-L124" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ネットワークの状態

さまざまなネットワークの状態をシミュレートできます。

以下の例はローカルWebDriver用です。リモートWebDriverについては、[リモートWebDriver]({{< ref "../drivers/remote_webdriver" >}})ページを参照してください。

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
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L129" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ログ

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
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L141" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### パーミッション

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
{{< gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L149-L150" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### デベロッパー ツール

Chromeデベロッパーツールの使用に関する詳細については、[Chromeデベロッパー ツール] セクションを参照してください。
