---
title: "WebDriver BiDi Logging Features"
linkTitle: "Logging"
weight: 1
description: >
  These features are related to logging. Because "logging" can refer to so many
  different things, these methods are made available via a "script" namespace.
aliases: [
  "/documentation/zh-cn/webdriver/bidirectional/bidirectional_w3c/log",
  "/documentation/webdriver/bidirectional/webdriver_bidi/log"
]
---

Remember that to use WebDriver BiDi, you must enable it in Options. 
For more details, see [Enabling BiDi]({{< ref "BiDi" >}})

## Console Message Handlers

Record or take actions on `console.log` events.

### Add Handler

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

### Remove Handler

You need to store the ID returned when adding the handler to delete it.

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

## JavaScript Exception Handlers

Record or take actions on JavaScript exception events.

### Add Handler

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

### Remove Handler

You need to store the ID returned when adding the handler to delete it.

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
