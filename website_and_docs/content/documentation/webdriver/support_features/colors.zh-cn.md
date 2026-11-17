---
title: "同颜色一起工作"
linkTitle: "颜色"
weight: 3
aliases: [
"/documentation/support_packages/working_with_colours/",
"/documentation/webdriver/additional_features/working_with_colours/"
]
---
 
在测试中, 您偶尔会需要验证某事物的颜色；问题是网络上的颜色定义不是个常量.
如果有一种简单的方法可以比较颜色的十六进制与RGB呈现, 或者颜色的RGBA与HSLA呈现, 岂不美哉?

不用担心有一个解决方案：_Color_ 类!

首先, 您需要导入该类:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L7" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L2" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L8" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L7" >}}
  {{< /tab >}}
{{< /tabpane >}}

您现在可以开始创建颜色对象. 每个颜色对象都需要使用您颜色的字符串定义来创建. 支持的颜色定义如下:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L13-L19" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L6-L12" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L11-L17" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L13-L19" >}}
  {{< /tab >}}
{{< /tabpane >}}

Color类还支持在以下网址中指定的所有基本颜色定义
[http://www.w3.org/TR/css3-color/#html4](//www.w3.org/TR/css3-color/#html4).

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L32-L34" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L24-L26" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L32-L34" >}}
  {{< /tab >}}
{{< /tabpane >}}

如果元素上未设置颜色, 则有时浏览器会返回“透明”的颜色值. Color类也支持此功能:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L43" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L34" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L43" >}}
  {{< /tab >}}
{{< /tabpane >}}

现在, 您可以安全地查询元素以获取其颜色/背景色, 任何响应都将被正确解析并转换为有效的Color对象:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L54-L55" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L43-L44" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L32-L33" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L53-L54" >}}
  {{< /tab >}}
{{< /tabpane >}}

然后, 您可以直接比较颜色对象:


{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L59" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L48" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L37" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L58" >}}
  {{< /tab >}}
{{< /tabpane >}}

或者, 您可以将颜色转换为以下格式之一并执行静态验证:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L61-L63" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L50-L52" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L39-L41" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L60-L62" >}}
  {{< /tab >}}
{{< /tabpane >}}

颜色不再是问题.
