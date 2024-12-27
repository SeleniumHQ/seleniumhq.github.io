---
title: "打印页面"
linkTitle: "打印页面"
weight: 7
aliases: [
"/documentation/zh-cn/support_packages/print_page/",
]
---

无论是共享信息还是维护档案，打印网页都是一项常见任务。
Selenium 通过其 PrintOptions、PrintsPage 和 browsingContext 类简化了这一过程，这些类为网页自动打印提供了灵活直观的接口。
这些类使得用户可以配置打印首选项，如页面布局、页边距和缩放比例，以确保输出满足特定要求。

## 配置

### 方向
通过 `getOrientation()` 和 `setOrientation()` 方法，可以获取/设置页面方向（`PORTRAIT` 或 `LANDSCAPE`）。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L12-L18" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L12-L19" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L11-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### 范围
通过 `getPageRanges()` 和 `setPageRanges()` 方法，可以获取设置要打印页面的范围（如 "2-4"）。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L21-L27" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L22-L29" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L17-L21" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### 尺寸
通过 `getPaperSize()` 和 `setPaperSize()` 方法，可以获取/设置要打印页面的纸张尺寸（如"A0"、"A6"、"Legal"、"Tabloid" 等）。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L30-L36" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L32-L38" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L23-L27" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### 边距
通过 `getPageMargin()` 和 `setPageMargin()` 方法，可以获取/设置要打印页面的边距大小（也就是上、下、左右边距）。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L40-L49" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L51-L57" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L29-L39" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### 缩放
通过 `getScale()` 和 `setScale()` 方法，可以获取/设置要打印页面的缩放尺寸（如 1.0 为 100% 或默认缩放，0.25 为 25% 等）。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L52-L58" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L61-L68" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L41-L46" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### 背景
通过 `getBackground()` 和 `setBackground()` 方法，可以获取/设置背景色和图片出现，其为布尔值 `true` 或 `false`。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L61-L67" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L41-L48" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L48-L52" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### 缩放至合适大小
通过 `getShrinkToFit()` 和 `setShrinkToFit()` 方法，可以获取/设置页面是否会根据页面内容缩小，其为布尔值 `true` 或 `false`。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L70-L76" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L71-L78" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L54-L58" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

## 打印

配置好打印选项后，就可以打印页面了。为此，您可以调用打印功能，生成网页的 PDF 表示形式。
生成的 PDF 文件可以保存到本地存储器中，以便进一步使用或分发。
使用 `PrintsPage()` 时，打印命令将以 base64 编码格式返回 PDF
数据，该格式可以解码并写入所需位置的文件，而使用 `BrowsingContext()` 时将返回字符串。

目前可能有多种实现方式，这取决于您所选择的语言。例如，Java 可以使用 `BrowingContext()`
或 `PrintsPage()` 进行打印。两者都将 `PrintOptions()` 对象作为一个参数。

注意：`BrowsingContext()` 是 Selenium BiDi 实现的一部分。为启用 BiDi，请参见[启用 Bidi]({{< ref "bidi/" >}}) 

{{< tabpane text=true >}}
{{% tab header="Java" %}}
**PrintsPage()**
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintsPageTest.java#L25-L32" >}}
**BrowsingContext()**
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintsPageTest.java#L35-L42" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L81-L88" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{% tab header="Python" %}}
**print_page()**
{{< gh-codeblock path="examples/python/tests/interactions/test_prints_page.py#L11-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}
