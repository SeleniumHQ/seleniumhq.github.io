---
title: "Remote WebDriver"
linkTitle: "Remote WebDriver"
weight: 10
aliases: [
"/documentation/ja/remote_webdriver/",
"/documentation/ja/remote_webdriver/remote_webdriver_client/",
"/ja/documentation/webdriver/remote_webdriver/",
]

---

Selenium lets you automate browsers on remote computers if
there is a [Selenium Grid]({{< ref "../../grid" >}}) running on them. The computer that
executes the code is referred to as the client computer, and the computer with the browser and driver is 
referred to as the remote computer or sometimes as an end-node.
To direct Selenium tests to the remote computer, you need to use a Remote WebDriver class
and pass the URL including the port of the grid on that machine. Please see the grid documentation
for all the various ways the grid can be configured.

## Basic Example

The driver needs to know where to send commands to and which browser to start on the Remote computer. So an address
and an options instance are both required.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L38-L39" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#L10-L11" >}}
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


## Uploads

[Uploading a file]() is more complicated for Remote WebDriver sessions because the file you want to 
upload is likely on the computer executing the code, but the driver on the
remote computer is looking for the provided path on its local file system.
The solution is to use a Local File Detector. When one is set, Selenium will bundle
the file, and send it to the remote machine, so the driver can see the reference to it.
Some bindings include a basic local file detector by default, and all of them allow 
for a custom file detector.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
Java does not include a Local File Detector by default, so you must always add one to do uploads.
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L49-L52" >}}
{{< /tab >}}
{{% tab header="Python" %}}
Python adds a local file detector to remote webdriver instances by default, but you can also create your own class.
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#L25-L28" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
.NET adds a local file detector to remote webdriver instances by default, but you can also create your own class.
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/RemoteWebDriverTest.cs#L47-L50" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
Ruby adds a local file detector to remote webdriver instances by default, but you can also create your own lambda:
{{< gh-codeblock path="examples/ruby/spec/drivers/remote_webdriver_spec.rb#L33-L36" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## Downloads

Chrome, Edge and Firefox each allow you to set the location of the download directory.
When you do this on a remote computer, though, the location is on the remote computer's local file system. 
Selenium allows you to enable downloads to get these files onto the client computer.

### Enable Downloads in the Grid

Regardless of the client, when starting the grid in node or standalone mode, 
you must add the flag: 
```
--enable-managed-downloads true
``` 

### Enable Downloads in the Client

The grid uses the `se:downloadsEnabled` capability to toggle whether to be responsible for managing the browser location.
Each of the bindings have a method in the options class to set this.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L60-L62" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#L37-L39" >}}
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

### List Downloadable Files

Be aware that Selenium is not waiting for files to finish downloading, 
so the list is an immediate snapshot of what file names are currently in the directory for the given session.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L73" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#L47" >}}
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

### Download a File

Selenium looks for the name of the provided file in the list and downloads it to the provided target directory.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L83" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#L53" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/RemoteWebDriverTest.cs#L78" >}}
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

### Delete Downloaded Files

By default, the download directory is deleted at the end of the applicable session,
but you can also delete all files during the session.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L88" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#L59" >}}
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


## Browser specific functionalities

Each [browser](({{< ref "../browsers/" >}})) has implemented special functionality that is available only to that browser.
Each of the Selenium bindings has implemented a different way to use those features in a Remote Session

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Java requires you to use the Augmenter class, which allows it to automatically pull in implementations for
all interfaces that match the capabilities used with the RemoteWebDriver
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L98" >}}

Of interest, using the `RemoteWebDriverBuilder` automatically augments the driver, so it is a great way
to get all the functionality by default:

{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L106-L111" >}}
{{% /tab %}}
{{% tab header="Python" %}}
{{< badge-implementation >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
.NET uses a custom command executor for executing commands that are valid for the given browser in the remote driver.
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Drivers/RemoteWebDriverTest.cs#L96-L100" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
Ruby uses mixins to add applicable browser specific methods to the Remote WebDriver session; 
the methods should always just work for you.
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
* Configuring OpenTelemetry:
    https://github.com/open-telemetry/opentelemetry-java/tree/main/sdk-extensions/autoconfigure
* Jaeger: https://www.jaegertracing.io
* [Selenium Grid 可観測性]({{< ref "observability.md" >}}) 

