---
title: "JavaScript 警告框,提示框和确认框"
linkTitle: "警告框"
weight: 2
aliases: [
"/documentation/webdriver/js_alerts_prompts_and_confirmations/",
"/documentation/webdriver/browser/alerts/"
]
---

WebDriver提供了一个API, 用于处理JavaScript提供的三种类型的原生弹窗消息. 这些弹窗由浏览器提供限定的样式.

## Alerts 警告框

其中最基本的称为警告框, 它显示一条自定义消息, 以及一个用于关闭该警告的按钮, 在大多数浏览器中标记为"确定"(OK). 在大多数浏览器中, 也可以通过按"关闭"(close)按钮将其关闭, 但这始终与“确定”按钮具有相同的作用. <a onclick="window.alert('Sample alert')">查看样例警告框</a>.

WebDriver可以从弹窗获取文本并接受或关闭这些警告.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L36-L41" >}}
{{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L13-L19" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L43-L53" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L15-L22" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L19-L21" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L24-L34" >}}
{{< /tab >}}
{{< /tabpane >}}

## Confirm 确认框

确认框类似于警告框, 不同之处在于用户还可以选择取消消息. <a onclick="window.confirm('Are you sure?')">查看样例确认框</a>.

此示例还呈现了警告的另一种实现:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L131-L138" >}}
{{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L27-L33" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L63-L76" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L28-L35" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L30-L32" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L43-L56" >}}
{{< /tab >}}
{{< /tabpane >}}

## Prompt 提示框

提示框与确认框相似, 不同之处在于它们还包括文本输入. 与处理表单元素类似, 您可以使用WebDriver的sendKeys来填写响应. 这将完全替换占位符文本. 按下取消按钮将不会提交任何文本.
<a onclick="window.prompt('What is your tool of choice?',navigator.appName)">
查看样例提示框</a>.


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/AlertsTest.java#L79-L84" >}}
{{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_alerts.py#L41-L48" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/AlertsTest.cs#L87-L97" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/alerts_spec.rb#L41-L48" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/alert.spec.js#L42-L45" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/AlertsTest.kt#L66-L76" >}}
{{< /tab >}}
{{< /tabpane >}}

## beforeunload

较新版本的驱动默认会自动关闭 `beforeunload` 提示框。
如果需要恢复旧的行为，
请参阅 [unhandledPromptBehavior]({{< ref "../drivers/options#unhandledpromptbehavior" >}})。
