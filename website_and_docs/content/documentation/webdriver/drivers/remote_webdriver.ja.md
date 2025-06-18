---
title: "リモートWebDriver"
linkTitle: "リモートWebDriver"
weight: 10
aliases: [
"/documentation/ja/remote_webdriver/",
"/documentation/ja/remote_webdriver/remote_webdriver_client/",
"/ja/documentation/webdriver/remote_webdriver/",
]

---

Seleniumは、リモートコンピュータ上でブラウザを自動化することができます。これには、リモートコンピュータ上で [Selenium Grid]({{< ref "../../grid" >}}) が実行されている必要があります。コードを実行するコンピュータはクライアントコンピュータと呼ばれ、ブラウザとドライバーがあるコンピュータはリモートコンピュータまたは時々エンドノードと呼ばれます。Seleniumテストをリモートコンピュータに向けるには、Remote WebDriverクラスを使用し、そのマシンのグリッドのポートを含むURLを渡す必要があります。グリッドの設定方法については、グリッドのドキュメントを参照してください。 

## 基本的な例

ドライバーは、コマンドを送信する場所と、リモートコンピュータ上で開始するブラウザを知る必要があります。そのため、アドレスとオプションインスタンスの両方が必要です。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L38-L39" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#L13-L14" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/RemoteWebDriverTest.cs#L28-L29" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/remote_webdriver_spec.rb#L20-L21" >}} 
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## アップロード

[ファイルのアップロード]() は、リモートWebDriverセッションではより複雑です。アップロードしたいファイルはコードを実行しているコンピュータ上にあることが多いですが、リモートコンピュータ上のドライバーはそのローカルファイルシステム上で指定されたパスを探しています。この解決策として、ローカルファイルディテクターを使用します。これを設定すると、Seleniumはファイルをパッケージ化し、リモートマシンに送信するため、ドライバーはその参照を認識できるようになります。一部のバインディングでは、デフォルトで基本的なローカルファイルディテクターが含まれており、すべてのバインディングでカスタムファイルディテクターを設定できます。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
Javaにはデフォルトでローカルファイルディテクターが含まれていないため、アップロードを行う際には必ず追加する必要があります。
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L49-L52" >}}
{{< /tab >}}
{{% tab header="Python" %}}
Pythonでは、リモートWebDriverインスタンスにデフォルトでローカルファイルディテクターが追加されますが、独自のクラスを作成することも可能です。
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#LL29-L32" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
.NETでは、リモートWebDriverインスタンスにデフォルトでローカルファイルディテクターが追加されますが、独自のクラスを作成することも可能です。
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/RemoteWebDriverTest.cs#L47-L50" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
Rubyでは、リモートWebDriverインスタンスにデフォルトでローカルファイルディテクターが追加されますが、独自のラムダを作成することも可能です。
{{< gh-codeblock path="examples/ruby/spec/drivers/remote_webdriver_spec.rb#L33-L36" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## ダウンロード

Chrome、Edge、およびFirefoxでは、それぞれダウンロードディレクトリの場所を設定できます。 ただし、リモートコンピュータでこれを行う場合、その場所はリモートコンピュータのローカルファイルシステム上にあります。Seleniumを使用すると、クライアントコンピュータにこれらのファイルをダウンロードできるように設定することが可能です。

### グリッドでのダウンロードを有効化

クライアントに関係なく、ノードまたはスタンドアロンモードでグリッドを起動する際には、次のフラグを追加する必要があります:
```
--enable-managed-downloads true
``` 

### クライアントでのダウンロードを有効化

グリッドは、`se:downloadsEnabled` 機能を使用して、ブラウザの場所を管理する責任を持つかどうかを切り替えます。各バインディングには、これを設定するためのオプションクラスのメソッドがあります。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L60-L62" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#L42-L44" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/RemoteWebDriverTest.cs#L59-L64" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/remote_webdriver_spec.rb#L43-L44" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ダウンロード可能なファイルの一覧

Seleniumはファイルのダウンロードが完了するのを待たないため、リストは指定されたセッションのディレクトリに現在存在するファイル名の即時スナップショットであることに注意してください。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L73" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#L52" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/RemoteWebDriverTest.cs#L72" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/remote_webdriver_spec.rb#L52" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ファイルをダウンロード

Seleniumは、提供されたファイルの名前をリストの中で探し、指定されたターゲットディレクトリにダウンロードします。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L83" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#L58" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/RemoteWebDriverTest.cs#L79" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/remote_webdriver_spec.rb#L57" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### ダウンロードしたファイルの削除

デフォルトでは、ダウンロードディレクトリは該当するセッションの終了時に削除されますが、セッション中にすべてのファイルを削除することもできます。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L88" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#L64" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/RemoteWebDriverTest.cs#L84" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/drivers/remote_webdriver_spec.rb#L62" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## ブラウザ特有の機能

各 [ブラウザ]({{< ref "../browsers/" >}}) は、そのブラウザにのみ利用可能な特別な機能を実装しています。各Seleniumバインディングは、リモートセッションでそれらの機能を使用するための異なる方法を実装しています。

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Javaでは、Augmenterクラスを使用する必要があります。これにより、RemoteWebDriverで使用される機能に一致するすべてのインターフェースの実装を自動的に取り込むことができます。
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L98" >}}

興味深いことに、RemoteWebDriverBuilderを使用すると、ドライバーが自動的に拡張されるため、デフォルトで全ての機能を取得するのに最適な方法です。

{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L106-L111" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{< tab header="CSharp" >}}

.NETでは、リモートドライバーで指定されたブラウザに対して有効なコマンドを実行するために、カスタムコマンドエグゼキュータを使用します。
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/RemoteWebDriverTest.cs#L96-L100" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
Rubyでは、ミキシンを使用してリモートWebDriverセッションに適用可能なブラウザ特有のメソッドを追加します。これらのメソッドは常にそのまま機能するはずです。
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

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

### 必要な依存関係を追加する

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
 
### クライアントの実行中に必要なシステムプロパティを追加/渡す

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
System.setProperty("otel.traces.exporter", "jaeger");
System.setProperty("otel.exporter.jaeger.endpoint", "http://localhost:14250");
System.setProperty("otel.resource.attributes", "service.name=selenium-java-client");

ImmutableCapabilities capabilities = new ImmutableCapabilities("browserName", "chrome");

WebDriver driver = new RemoteWebDriver(new URL("http://www.example.com"), capabilities);

driver.get("http://www.google.com");

driver.quit();

  {{< /tab >}}
{{< /tabpane >}}

ご希望のSeleniumのバージョンに必要な外部依存関係のバージョンの詳細については、
[トレースのセットアップ](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) を参照してください。

詳細については、下記URLを参照してください。

* OpenTelemetry: https://opentelemetry.io
* OpenTelemetryの構成::
    https://github.com/open-telemetry/opentelemetry-java/tree/main/sdk-extensions/autoconfigure
* Jaeger: https://www.jaegertracing.io
* [Selenium Grid 可観測性]({{< ref "observability.md" >}}) 

