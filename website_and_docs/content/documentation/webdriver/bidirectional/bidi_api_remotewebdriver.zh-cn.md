---
title: "远程网络驱动 双向 API（RemoteWebDriver BiDirectional API）"
linkTitle: "远程网络驱动 BiDi API"
weight: 1
---



下面的例子显示如何使用 [Remote WebDriver](/documentation/webdriver/remote_webdriver/).

## 注册基本认证（Register Basic Auth）

一些应用程序使用浏览器身份验证来保护页面。使用 Selenium，您可以在基本身份验证出现时自动输入它们。

{{< tabpane disableCodeBlock=true >}}
{{< tab header="Java" >}}
    {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/augmenter/CdpRemoteWebDriverTest.java#L79-94" >}}
{{< /tab >}}
{{< /tabpane >}}

## DOM动态变化观察 （Mutation Observation）

DOM动态变化观察是当 DOM 中的特定元素上存在 DOM 突变时，通过 WebDriver BiDi 捕获事件的能力。

{{< tabpane disableCodeBlock=true >}}
{{< tab header="Java" >}}
    {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/augmenter/CdpRemoteWebDriverTest.java#L111-133" >}}
{{< /tab >}}
{{< /tabpane >}}

## 监听控制台日志事件 （Listen to `console.log` events）

侦听 console. log 事件并注册回调以处理该事件。

{{< tabpane disableCodeBlock=true >}}
{{< tab header="Java" >}}
    {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/augmenter/CdpRemoteWebDriverTest.java#L149-170" >}}
{{< /tab >}}
{{< /tabpane >}}

## JS异常处理 （Actions causing JS exceptions）

{{< tabpane disableCodeBlock=true >}}
{{< tab header="Java" >}}
    {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/augmenter/CdpRemoteWebDriverTest.java#L179-185" >}}
{{< /tab >}}
{{< /tabpane >}}

## 监听网络 （Network Interception）

如果你希望捕获进入浏览器的网络事件并对其进行操作，可以使用以下示例进行操作。

{{< tabpane disableCodeBlock=true >}}
{{< tab header="Java" >}}
    {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/augmenter/CdpRemoteWebDriverTest.java#L201-214" >}}
{{< /tab >}}
{{< /tabpane >}}

