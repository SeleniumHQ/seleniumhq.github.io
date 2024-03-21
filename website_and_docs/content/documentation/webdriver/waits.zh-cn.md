---
title: "等待策略"
linkTitle: "等待策略"
weight: 6
aliases: ["/documentation/zh-cn/webdriver/waits/"]
---

浏览器自动处理最大的挑战可能是确保网页在某个确定的、期望的状态下执行 Selenium 命令。
这些过程通常处于 混乱竞争状态, 有时候网页先处理好，有时候 Selenium 命令先执行。
这就是为什么需要稳定性测试(flaky tests)。

所有导航命令都需要一个准确的 `Ready状态` 值, 这取决于 [网页加载策略]({{< ref "drivers/options#pageloadstrategy" >}}) (一个默认的完成值)，之后`浏览器 Driver`将控制权交回给代码进行处理。
这个 `Ready状态` 只关心在html 中定义的 css js 是否被加载，但 js 加载通常导致网页资源的变动，执行下一个 Selenium 命令时，网页元素在交互后可能还没有出现在网页中，。

类似地，在一个单网页中，元素根据点击会动态地添加到网页中或者显示/隐藏。
这个元素必须存在且被[显示]({{< ref "elements/information/#is-displayed" >}}) 在网页中以确保Selenium 能够正确操作它。

参考这个例子: https://www.selenium.dev/selenium/web/dynamic.html
当"Add a box" 这个按钮被点击时，"div" 这个元素当时还没有被创建。
当"Reveal a new input" 这个按钮被点击时，一个隐藏的 text 字段元素正常显示。
在这两个动作中发生变化用了几秒钟，如果此时Selenium命令去执行点击这其中的按钮并和预期显示的元素交互，
在元素加载(处理)完成之前将会失败。

大多数人想到的解决办法是添加一个 sleep 时间，暂停后续执行。
因为代码并不知道具体需要等待多久，当没有暂停足够久时，依旧会失败。最终这个值会被设置为很高，且需要在几乎每个动作之间添加，
整个处理过程就会变得非常缓慢。

Selenium 提供了两种方式，来帮助解决这些问题

## 隐形等待(Implicit waits)

Selenium 通过内建的机制自动等待元素被称为_隐形等待_。
这个隐形等待值可以设置在 浏览器选项中或者 Selenium Driver 方法中(如下)。

这是一个全局值，将会影响所有元素位置调用。
初始值为0, 这意味着元素没被找到，将会直接报错。如果该值被设置了，浏览器 Driver 将会等待一段时间后报错。
注意只要元素被找到了，浏览器 Driver 会返回元素定位且代码会继续执行。
所以设置一个很大的 Implicit 值并不会明显增加整个过程的操作时间。

*警告*
不要混淆 隐性和显性等待，这样做会导致预期之外的等待时间。
例如，同时设置10 秒Implicit 和 15秒的 Explicit 等待会在 20 秒后超时

使用 Implicit 解决我们的问题，如下：
{{< tabpane text=true >}}
  {{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L50" >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/waits/test_waits.py#L27" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L39" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/waits/waits_spec.rb#L28" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/waits/waits.spec.js#L37-L41" >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}

## 显性等待(Explicit waits)

_Explicit waits_ 使用循环等待应用中某个特定条件时生效，将会退出该循环继续执行命令
如果循环时间到了但条件未被触发，将会报错。
鉴于有许多可能会导致不能处于期望的状态，所以Explicit 等待是一个不错的选择，可以指定某个确定的条件去等待。
Explicit 另一个很好的方面是Selenium 能自动等待该元素出现。

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
This example shows the condition being waited for as a _lambda_. Java also supports
[Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L67-L68" >}}
  {{% /tab %}}
  {{% tab header="Python" %}}
This example shows the condition being waited for as a _lambda_. Python also supports
[Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="examples/python/tests/waits/test_waits.py#L41-L42" >}}
  {{% /tab %}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L56-L57" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/waits/waits_spec.rb#L42-L43" >}}
  {{< /tab >}}
  {{% tab header="JavaScript" %}}
JavaScript also supports [Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="examples/javascript/test/waits/waits.spec.js#L47-L51" >}}
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}

### 自定义选项

Explicit 等待类有几种参数能够使得该条件被如何执行

包括以下方面:
* 修改间隔多久检查一次
* 指定哪种异常应该被自动处理
* 修改整体超时时间
* 自定义超时报错信息

举个栗子, 如果 _element not interactable_(元素不能交互) 这个错误默认重试，我们可以在代码被执行时执行一个动作(假设这个动作是成功时返回 true)

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
The easiest way to customize Waits in Java is to use the `FluentWait` class:
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L82-L92" >}}
  {{% /tab %}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/waits/test_waits.py#L53-L55" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L70-L79" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/waits/waits_spec.rb#L54-L60" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< badge-code >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}
