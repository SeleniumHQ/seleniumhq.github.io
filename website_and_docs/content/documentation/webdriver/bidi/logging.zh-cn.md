---
title: "WebDriver BiDi 日志功能"
linkTitle: "日志"
weight: 1
description: >
  这些功能与日志记录有关。
  由于"logging"可以指代许多不同的事物,
  因此这些方法通过"script"命名空间提供.
aliases: [
  "/documentation/zh-cn/webdriver/bidirectional/bidirectional_w3c/log",
  "/documentation/webdriver/bidirectional/webdriver_bidi/log"
]
---

请记住, 要使用 WebDriver BiDi,
您必须在选项中启用它. 
更多详情, 请参阅 [启用 BiDi]({{< ref "BiDi" >}}) .

## 控制台消息处理程序

记录或对 `console.log` 事件采取行动.

### 添加处理程序

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_logging.py#L11" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/logging_spec.rb#L11" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### 删除处理程序

您需要存储添加处理程序时返回的 ID 以便将其删除.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_logging.py#L23-24" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/logging_spec.rb#L22-L23" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

## JavaScript 异常处理程序

记录或对 JavaScript 异常事件采取行动.

### 添加处理程序

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_logging.py#L35" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/logging_spec.rb#L33" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### 删除处理程序

您需要存储添加处理程序时返回的 ID 以便将其删除.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_logging.py#L47-48" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/logging_spec.rb#L44-L45" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}
