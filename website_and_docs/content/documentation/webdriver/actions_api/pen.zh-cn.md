---
title: "触控笔操作"
linkTitle: "触控笔"
weight: 5
description: >
  一种用于与网页交互的类似笔尖的指针输入设备的表示.
---

{{< badge-browser browser=Chromium wpt="perform_actions/pointer.py" >}}

触控笔是一种指针输入设备，其行为大多与鼠标相同，
但也可以具有触控笔特有的事件属性。
此外，鼠标有 5 个按钮，而触控笔有 3 种等效的按钮状态:

* 0 - 触摸接触（默认设置；相当于左键单击）
* 2 - 桶形按钮/侧键（相当于右键点击）
* 5 - 橡皮擦按钮（当前驱动程序不支持）

## 使用触控笔

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/PenTest.java#L26-L33" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_pen.py#L12-L20" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/PenTest.cs#L19-L28" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/pen_spec.rb#L11-L17" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/actions_api/PenTest.kt#L23-L30" >}}
{{< /tab >}}
{{< /tabpane >}}

## 添加指针事件属性

{{< badge-version version="4.2" >}}

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/PenTest.java#L67-L81" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_pen.py#L53-L61" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/PenTest.cs#L64-L77" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/pen_spec.rb#L50-L56" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/actions_api/PenTest.kt#L64-L78" >}}
{{< /tab >}}
{{< /tabpane >}}

