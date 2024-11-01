---
title: "Edge固有の機能"
linkTitle: "Edge"
weight: 5
description: >-
    これらは、Microsoft Edgeブラウザに固有のCapabilityです。
---

Microsoft EdgeはChromiumで実装されており、サポートされている最も古いバージョンはv79です。
Chromeと同様に、edgedriverのメジャー バージョン番号は、Edgeブラウザのメジャーバージョンと一致する必要があります。

[Chromeページ]({{< ref "chrome.md" >}}) にあるすべての機能とオプションは、Edgeでも機能します。

## オプション

すべてのブラウザに共通する機能は[オプション ページ]({{< ref "../drivers/options.md" >}})に記載されています。

Chromiumに特有の機能は、Googleの[Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)ページに文書化されています。

基本的な定義済みオプションを使用して Edgeセッションを開始すると、次のようになります。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L37-L38" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_edge.py#L9-L10" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/EdgeTest.cs#L30-L31" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L10-L11" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openEdgeTest.spec.js#L11-L15">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 引数

`args` パラメータは、ブラウザを起動する際に使用されるコマンドラインスイッチのリストです。これらの引数を調査するための優れたリソースが2つあります：
* [ツール用のChromeフラグ](https://github.com/GoogleChrome/chrome-launcher/blob/main/docs/chrome-flags-for-tools.md)
* [Chromiumコマンドラインスイッチのリスト](https://peter.sh/experiments/chromium-command-line-switches/)

一般的に使用される引数には、`--start-maximized` および `--headless=new` が含まれます。 and `--user-data-dir=...`

オプションに引数を追加します。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L45" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_edge.py#L18" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/EdgeTest.cs#L39" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L17" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/edgeSpecificCaps.spec.js#L12">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 指定された場所でブラウザを起動する

`binary` パラメータは、使用するブラウザの代替位置のパスを指定します。このパラメータを使用すると、chromedriverを利用してさまざまなChromiumベースのブラウザを操作できます。

オプションにブラウザの場所を追加する：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L54" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_edge.py#L29">}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/EdgeTest.cs#L49" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L25" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 拡張機能を追加する

`extensions` パラメータは、crxファイルを受け入れます。展開されたディレクトリについては、`load-extension` 引数を使用してください。このことは[この投稿](https://chromedriver.chromium.org/extensions)で言及されています。

オプションに拡張機能を追加する：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L65" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_edge.py#L40" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/EdgeTest.cs#L61" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L34" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/edgeSpecificCaps.spec.js#L55">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ブラウザを開いたままにする

`detach` パラメータをtrue に設定すると、プロセスが終了した後でもブラウザが開いたままになります。ただし、quit コマンドがドライバーに送信されない限り、ブラウザは開いたままになります。

{{< tabpane text=true >}}
{{% tab header="Java" %}}
**注意**: これはすでにJavaのデフォルトの動作です。
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_edge.py#L51" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
**注意**: これはすでに.NETのデフォルトの動作です。
{{% /tab %}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L45" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/browser/edgeSpecificCaps.spec.js#L32">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 引数を除外する

MSEdgedriverには、ブラウザを起動するために使用されるいくつかのデフォルト引数があります。それらの引数を追加したくない場合は、`excludeSwitches`に渡してください。一般的な例は、ポップアップブロッカーを再度オンにすることです。デフォルト引数の完全なリストは[Chromium Source Code](https://source.chromium.org/chromium/chromium/src/+/main:chrome/test/chromedriver/chrome_launcher.cc)から解析できます。

オプションに除外された引数を設定する:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L78" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_edge.py#L62" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Browsers/EdgeTest.cs#L76" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L53" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/getting_started/openEdgeTest.spec.js#L20-L23">}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## サービス

デフォルトのサービスオブジェクトを作成するための例や、ドライバの場所とポートを設定する例は、[Driver Service]({{< ref "../drivers/service.md" >}}) ページにあります。

### ログ出力

ドライバのログを取得することは、問題をデバッグするのに役立ちます。サービスクラスを使用すると、ログの出力先を指定できます。ユーザーがどこかにログを指示しない限り、ログ出力は無視されます。

#### ファイル出力

特定のファイルに保存するようにログ出力を変更するには、以下のようにします:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L100" >}}
**注意**: Javaでもシステムプロパティを使用してファイル出力を設定できます:\
プロパティキー: `EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY`\
プロパティ値: ログファイルのパスを表す文字列
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_edge.py#L71" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Browsers/EdgeTest.cs#L86" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/edge_spec.rb#L67" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

#### コンソール出力

ログ出力をコンソールにSTDOUTとして表示するには:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L113" >}}
**注**: Javaでは、システムプロパティを使用してコンソール出力を設定することもできます。\
プロパティキー: `EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY`\
プロパティ値:`DriverService.LOG_STDOUT` または  `DriverService.LOG_STDERR`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{% tab header="Ruby" %}}
`$stdout` と `$stderr`はどちらも有効な値です。
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/edge_spec.rb#L76" >}}
{{% /tab %}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ログレベル
利用可能なログレベルは6つあります：`ALL`, `DEBUG`, `INFO`, `WARNING`, `SEVERE`および  `OFF`。`--verbose` は `--log-level=ALL` と同等であり、`--silent` は`--log-level=OFF`と同等です。したがって、この例ではログレベルを一般的に設定しています：

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L126-L127" >}}
**注意**: Javaでは、システムプロパティを使用してログレベルを設定することもできます：\
プロパティキー: `EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY`\
プロパティ値:`ChromiumDriverLogLevel` 列挙型の文字列表現
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_edge.py#L93" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/edge_spec.rb#L87" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ログファイルの機能
ファイルにログを記録する際にのみ利用可能な2つの機能があります：
* ログの追加
* 読みやすいタイムスタンプ

これらを使用するには、ログパスとログレベルも明示的に指定する必要があります。ログ出力はプロセスではなくドライバによって管理されるため、若干の違いが見られることがあります。

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L142-L143" >}}
**注意**: Javaでは、これらの機能をSystem Propertyによって切り替えることもできます：\
プロパティキー:`EdgeDriverService.EDGE_DRIVER_APPEND_LOG_PROPERTY` および `EdgeDriverService.EDGE_DRIVER_READABLE_TIMESTAMP`\
プロパティ値: `"true"` または  `"false"`
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_edge.py#L104" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/edge_spec.rb#L97-L98" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ビルドチェックの無効化

Edge ブラウザとmsedgedriverのバージョンは一致する必要があり、一致しない場合はドライバにエラーが表示されます。ビルドチェックを無効にすると、任意のバージョンのEdgeでドライバを強制的に使用できます。
この機能はサポートされていないことに注意してください。バグは調査されません。

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L160-L161" >}}
**注**: Javaでは、システムプロパティを使用してビルドチェックを無効にすることもできます：\
プロパティキー:`EdgeDriverService.EDGE_DRIVER_DISABLE_BUILD_CHECK`\
プロパティ値: `"true"` または  `"false"`
{{% /tab %}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_edge.py#L115" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Browsers/EdgeTest.cs#L155" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/ruby/spec/browsers/edge_spec.rb#L108" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## Internet Explorer Compatibility モード

Microsoft Edge は、Internet Explorer ドライバークラスを Microsoft Edgeと組み合わせて使用する 
"Internet Explorer 互換モード"で動かすことができます。 
詳細については、[Internet Explorerページ]({{< ref "internet_explorer.md" >}})を参照してください。


## 特別な機能
一部のブラウザは、それぞれ特有の追加機能を実装しています。

### キャスティング

Edge を使用して Chrome Cast デバイスを操作し、タブを共有することができます。

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
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L119-L123" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ネットワーク条件

さまざまなネットワーク条件をシミュレートすることができます。

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
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L129" >}}
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
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L141" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 権限

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
{{< gh-codeblock path="/examples/ruby/spec/browsers/edge_spec.rb#L149-L150" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### DevTools

EdgeでDevToolsを使用する際の詳細については、[Chrome DevTools]セクションを参照してください。