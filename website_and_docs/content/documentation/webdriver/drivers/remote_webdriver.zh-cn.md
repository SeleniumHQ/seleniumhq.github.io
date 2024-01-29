---
title: "远程WebDriver"
linkTitle: "远程WebDriver"
weight: 10
aliases: [
"/documentation/zh-cn/remote_webdriver/",
"/documentation/zh-cn/remote_webdriver/remote_webdriver_client/",
"/zh-cn/documentation/webdriver/remote_webdriver/",
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

## 追踪客户端请求

此功能仅适用于Java客户端绑定 (Beta版以后).
远程WebDriver客户端向Selenium网格服务器发送请求, 
后者将请求传递给WebDriver.
应该在服务器端和客户端启用跟踪, 
以便端到端地追踪HTTP请求.
两端都应该有一个指向可视化框架的追踪导出器设置.
默认情况下, 
对客户端和服务器都启用追踪.
若设置可视化框架Jaeger UI及Selenium Grid 4, 
请参阅所需版本的[追踪设置](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) .

对于客户端设置, 请执行以下步骤.

### 添加所需依赖

可以使用Maven安装追踪导出器的外部库.
在项目pom.xml中添加 _opentelemetry-exporter-jaeger_
和 _grpc-netty_ 的依赖项：

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
 
### 在运行客户端时添加/传递所需的系统属性

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

有关所需Selenium版本
及其外部依赖关系版本等更多信息, 
请参阅[追踪设置](https://github.com/SeleniumHQ/selenium/blob/selenium-4.0.0-beta-1/java/server/src/org/openqa/selenium/grid/commands/tracing.txt) .

更多信息请访问:

* OpenTelemetry: https://opentelemetry.io
* 配置OpenTelemetry:
    https://github.com/open-telemetry/opentelemetry-java/tree/main/sdk-extensions/autoconfigure
* Jaeger: https://www.jaegertracing.io
* [Selenium Grid 可观测性]({{< ref "observability.md" >}}) 
