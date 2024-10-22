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

Capabilities common to all browsers are described on the [Options page]({{< ref "../drivers/options.md" >}}).

Capabilities unique to Chromium are documented at Google's page for
[Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)

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

The `args` parameter is for a list of command line switches to be used when starting the browser.
There are two excellent resources for investigating these arguments:
* [Chrome Flags for Tooling](https://github.com/GoogleChrome/chrome-launcher/blob/main/docs/chrome-flags-for-tools.md)
* [List of Chromium Command Line Switches](https://peter.sh/experiments/chromium-command-line-switches/)

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

### Start browser in a specified location

The `binary` parameter takes the path of an alternate location of browser to use. With this parameter you can
use chromedriver to drive various Chromium based browsers.

Add a browser location to options:

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

### Add extensions

The `extensions` parameter accepts crx files. As for unpacked directories,
please use the `load-extension` argument instead, as mentioned in
[this post](https://chromedriver.chromium.org/extensions).

Add an extension to options:

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

### Keeping browser open

Setting the `detach` parameter to true will keep the browser open after the process has ended,
so long as the quit command is not sent to the driver.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
**Note**: This is already the default behavior in Java.
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_edge.py#L51" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
**Note**: This is already the default behavior in .NET.
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

### Excluding arguments

MSEdgedriver has several default arguments it uses to start the browser.
If you do not want those arguments added, pass them into `excludeSwitches`.
A common example is to turn the popup blocker back on. A full list of default arguments
can be parsed from the
[Chromium Source Code](https://source.chromium.org/chromium/chromium/src/+/main:chrome/test/chromedriver/chrome_launcher.cc)

Set excluded arguments on options:

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


## Service

Examples for creating a default Service object, and for setting driver location and port
can be found on the [Driver Service]({{< ref "../drivers/service.md" >}}) page.

### Log output

Getting driver logs can be helpful for debugging issues. The Service class lets you
direct where the logs will go. Logging output is ignored unless the user directs it somewhere.

#### File output

To change the logging output to save to a specific file:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L100" >}}
**Note**: Java also allows setting file output by System Property:\
Property key: `EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY`\
Property value: String representing path to log file
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

#### Console output

To change the logging output to display in the console as STDOUT:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L113" >}}
**Note**: Java also allows setting console output by System Property;\
Property key: `EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY`\
Property value: `DriverService.LOG_STDOUT` or `DriverService.LOG_STDERR`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{% tab header="Ruby" %}}
`$stdout` and `$stderr` are both valid values
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

### Log level
There are 6 available log levels: `ALL`, `DEBUG`, `INFO`, `WARNING`, `SEVERE`, and `OFF`.
Note that `--verbose` is equivalent to `--log-level=ALL` and `--silent` is equivalent to `--log-level=OFF`,
so this example is just setting the log level generically:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L126-L127" >}}
**Note**: Java also allows setting log level by System Property:\
Property key: `EdgeDriverService.EDGE_DRIVER_LOG_LEVEL_PROPERTY`\
Property value: String representation of `ChromiumDriverLogLevel` enum
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

### Log file features
There are 2 features that are only available when logging to a file:
* append log
* readable timestamps

To use them, you need to also explicitly specify the log path and log level.
The log output will be managed by the driver, not the process, so minor differences may be seen.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L142-L143" >}}
**Note**: Java also allows toggling these features by System Property:\
Property keys: `EdgeDriverService.EDGE_DRIVER_APPEND_LOG_PROPERTY` and `EdgeDriverService.EDGE_DRIVER_READABLE_TIMESTAMP`\
Property value: `"true"` or `"false"`
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

### Disabling build check

Edge browser and msedgedriver versions should match, and if they don't the driver will error.
If you disable the build check, you can force the driver to be used with any version of Edge.
Note that this is an unsupported feature, and bugs will not be investigated.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/EdgeTest.java#L160-L161" >}}
**Note**: Java also allows disabling build checks by System Property:\
Property key: `EdgeDriverService.EDGE_DRIVER_DISABLE_BUILD_CHECK`\
Property value: `"true"` or `"false"`
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


## Special Features
Some browsers have implemented additional features that are unique to them.

### Casting

You can drive Chrome Cast devices with Edge, including sharing tabs

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/browsers/test_edge.py#L158-L162" >}}
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

### Network conditions

You can simulate various network conditions.

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

### Logs

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

### Permissions

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

See the [Chrome DevTools] section for more information about using DevTools in Edge
