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

如果远程计算机上正在运行 [Selenium Grid]({{< ref "../../grid" >}}), 
则 Selenium 允许您自动化远程计算机上的浏览器.
执行代码的计算机称为客户端计算机, 
具有浏览器和驱动程序的计算机称为远程计算机, 
有时也称为终端节点.
要将 Selenium 测试指向到远程计算机, 
您需要使用 Remote WebDriver 类并传递包含该机器上网格端口的URL.
请参阅网格文档, 了解配置网格的全部方式.

## 基本样例

驱动程序需要知道在远程计算机上向何处发送命令, 
以及启动哪个浏览器.
所以地址和选项实例都是必需的.

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


## 上传

对于远程WebDriver会话, [上传文件]({{< ref "../elements/file_upload" >}}) 更为复杂, 
因为要上传的文件可能在执行代码的计算机上, 
但远程计算机上的驱动程序正在其本地文件系统上查找提供的路径.
解决方案是使用本地文件检测器.
设置一个后, Selenium将捆绑文件, 
并将其发送到远程计算机, 以便驱动程序可以看到对它的引用.
默认情况下, 某些实现包含一个基本的本地文件检测器, 
并且所有这些实现都允许自定义文件检测器.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
Java does not include a Local File Detector by default, so you must always add one to do uploads.
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/drivers/RemoteWebDriverTest.java#L49-L52" >}}
{{< /tab >}}
{{% tab header="Python" %}}
Python adds a local file detector to remote webdriver instances by default, but you can also create your own class.
{{< gh-codeblock path="examples/python/tests/drivers/test_remote_webdriver.py#LL29-L32" >}}
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


## 下载

Chrome、Edge和Firefox都允许您设置下载目录的位置.
但是, 当您在远程计算机上执行此操作时, 位置在远程计算机的本地文件系统上.
Selenium允许您启用下载功能, 将这些文件下载到客户端计算机上.

### 在网格中启用下载

当以节点或独立模式启动网格时, 
你必须添加参数: 
```
--enable-managed-downloads true
``` 

### 在客户端中启用下载

网格使用 `se:downloadsEnabled` 功能来切换是否负责管理浏览器位置.
每个实现在options类中都有一个方法来设置.

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

### 列出可下载文件

请注意, Selenium不会等待文件下载完成, 
因此, 该列表是给定会话目录中当前文件名的即时快照.

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

### 下载文件

Selenium在列表中查找提供的文件的名称, 
并将其下载到提供的目标目录.

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

### 删除已下载的文件

默认情况下, 下载目录在可用会话结束时被删除, 
但您也可以在会话期间删除所有文件.

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


## 浏览器特定功能

每个[浏览器]({{< ref "../browsers/" >}}) 都实现了仅对该浏览器可用的特殊功能.
每种Selenium实现都实现了在远程会话中使用这些功能的不同方式

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
