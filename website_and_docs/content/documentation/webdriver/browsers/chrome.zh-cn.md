---
title: "Chrome specific functionality"
linkTitle: "Chrome"
weight: 4
description: >-
    These are capabilities and features specific to Google Chrome browsers.
aliases: [
"/zh-cn/documentation/capabilities/chromium"
]
---

默认情况下，Selenium 4与Chrome v75及更高版本兼容. 但是请注意Chrome浏览器的版本与chromedriver的主版本需要匹配.

## Options

所有浏览器的通用功能请看这 [Options page]({{< ref "../drivers/options.md" >}}).

Chrome浏览器的特有功能可以在谷歌的页面找到: [Capabilities & ChromeOptions](https://chromedriver.chromium.org/capabilities)

基于默认选项的Chrome浏览器会话看起来是这样:

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

下面是一些不同功能的常见示例:

### 参数

The `args` parameter is for a list of command line switches to be used when starting the browser.
There are two excellent resources for investigating these arguments:
* [Chrome Flags for Tooling](https://github.com/GoogleChrome/chrome-launcher/blob/main/docs/chrome-flags-for-tools.md)
* [List of Chromium Command Line Switches](https://peter.sh/experiments/chromium-command-line-switches/)

Commonly used args include `--start-maximized`, `--headless=new` and `--user-data-dir=...`

Add an argument to options:

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

### 从指定位置启动浏览器

`binary` 参数接收一个使用浏览器的备用路径,通过这个参数你可以使用chromedriver 去驱动各种基于Chromium 内核的浏览器.

添加一个浏览器地址到选项中:

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

### 添加扩展程序

`extensions` 参数接受crx文件. As for unpacked directories,
please use the `load-extension` argument instead, as mentioned in
[this post](https://chromedriver.chromium.org/extensions).

添加一个扩展程序到选项中:

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

### 保持浏览器的打开状态

将 `detach` 参数设置为true将在驱动过程结束后保持浏览器的打开状态.

添加一个布尔值到选项中:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
**Note**: This is already the default behavior in Java.
{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_chrome.py#L51" >}}
{{% /tab %}}
{{% tab header="CSharp" %}}
**Note**: This is already the default behavior in .NET.
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

### 排除的参数

Chrome 添加了各种参数，如果你不希望添加某些参数，可以将其传入 `excludeSwitches`.
一个常见的例子是重新打开弹出窗口阻止程序.
A full list of default arguments
can be parsed from the
[Chromium Source Code](https://source.chromium.org/chromium/chromium/src/+/main:chrome/test/chromedriver/chrome_launcher.cc)

设置排除参数至选项中:

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
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L100-L101" >}}
**Note**: Java also allows setting file output by System Property:\
Property key: `ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY`\
Property value: String representing path to log file
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

#### Console output

To change the logging output to display in the console as STDOUT:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L114-L115" >}}
**Note**: Java also allows setting console output by System Property;\
Property key: `ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY`\
Property value: `DriverService.LOG_STDOUT` or `DriverService.LOG_STDERR`
{{% /tab %}}
{{< tab header="Python" >}}
{{< badge-version version="4.11" >}}
{{< gh-codeblock path="examples/python/tests/browsers/test_chrome.py#L82" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{% tab header="Ruby" %}}
`$stdout` and `$stderr` are both valid values
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

### Log level
There are 6 available log levels: `ALL`, `DEBUG`, `INFO`, `WARNING`, `SEVERE`, and `OFF`.
Note that `--verbose` is equivalent to `--log-level=ALL` and `--silent` is equivalent to `--log-level=OFF`,
so this example is just setting the log level generically:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L129-L130" >}}
**Note**: Java also allows setting log level by System Property:\
Property key: `ChromeDriverService.CHROME_DRIVER_LOG_LEVEL_PROPERTY`\
Property value: String representation of `ChromiumDriverLogLevel` enum
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

### Log file features
There are 2 features that are only available when logging to a file:
* append log
* readable timestamps

To use them, you need to also explicitly specify the log path and log level.
The log output will be managed by the driver, not the process, so minor differences may be seen.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L147-L148" >}}
**Note**: Java also allows toggling these features by System Property:\
Property keys: `ChromeDriverService.CHROME_DRIVER_APPEND_LOG_PROPERTY` and `ChromeDriverService.CHROME_DRIVER_READABLE_TIMESTAMP`\
Property value: `"true"` or `"false"`
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

### Disabling build check

Chromedriver and Chrome browser versions should match, and if they don't the driver will error.
If you disable the build check, you can force the driver to be used with any version of Chrome.
Note that this is an unsupported feature, and bugs will not be investigated.

{{< tabpane text=true >}}
{{% tab header="Java" %}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java#L166-L167" >}}
**Note**: Java also allows disabling build checks by System Property:\
Property key: `ChromeDriverService.CHROME_DRIVER_DISABLE_BUILD_CHECK`\
Property value: `"true"` or `"false"`
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


## Special Features

### Casting

你可以驱动 Chrome Cast 设备，包括共享选项卡

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
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 网络条件

您可以模拟各种网络条件.

The following examples are for local webdrivers. For remote webdrivers,
please refer to the
[Remote WebDriver]({{< ref "../drivers/remote_webdriver" >}}) page.

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
{{< badge-code >}}
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
{{< badge-code >}}
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
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### DevTools

See the [Chrome DevTools]({{< ref "../bidirectional/chrome_devtools/cdp_api.md" >}}) section for more information about using Chrome DevTools
