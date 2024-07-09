---
title: "键盘操作"
linkTitle: "键盘"
weight: 2
description: >
  一种适用于任何与网页交互的按键输入设备的表现形式.
aliases: [
"/documentation/zh-cn/webdriver/keyboard/",
"/zh-cn/documentation/webdriver/keyboard/"
]
---

只有 2 个操作可以使用键盘完成:
按下某个键，以及释放一个按下的键.
除了支持 ASCII 字符外，每个键盘按键还具有
可以按特定顺序按下或释放的表现形式.

## 按键

除了由常规unicode表示的按键，
其他键盘按键被分配了一些unicode值以用于操作Selenium
每种语言都有自己的方式来援引这些按键;
[这里](https://www.w3.org/TR/webdriver/#keyboard-actions)
可以找到完整列表

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Use the [Java Keys enum](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/java/src/org/openqa/selenium/Keys.java#L28)
{{% /tab %}}
{{% tab header="Python" %}}
Use the [Python Keys class](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/py/selenium/webdriver/common/keys.py#L23)
{{% /tab %}}
{{% tab header="CSharp" %}}
Use the [.NET static Keys class](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/dotnet/src/webdriver/Keys.cs#L28)
{{% /tab %}}
{{% tab header="Ruby" %}}
Use the [Ruby KEYS constant](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/rb/lib/selenium/webdriver/common/keys.rb#L28)
{{% /tab %}}
{{% tab header="JavaScript" %}}
Use the [JavaScript KEYS constant](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/javascript/node/selenium-webdriver/lib/input.js#L44)
{{% /tab %}}
{{% tab header="Kotlin" %}}
Use the [Java Keys enum](https://github.com/SeleniumHQ/selenium/blob/selenium-4.2.0/java/src/org/openqa/selenium/Keys.java#L28)
{{% /tab %}}
{{< /tabpane >}}

## 按下按键

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L17-L20" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L10-L13" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L17-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L13-L16" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L19-L22" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L19-L22" >}}
{{< /tab >}}
{{< /tabpane >}}

## 释放按键

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L30-L35" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L21-L26" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L30-L35" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L25-L30" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L34-L39" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L32-L37" >}}
{{< /tab >}}
{{< /tabpane >}}

## 键入

这是Actions API的一种便捷方法, 
它将 keyDown 和 keyUp 命令组合在一个操作中.
执行此命令与使用 element 方法略有不同, 
但这主要用于，需要在其他操作之间键入多个字符时使用.

### 活跃元素

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L45-L47" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L34-L36" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L45-L47" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L39-L41" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L47-L48" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L47-L49" >}}
{{< /tab >}}
{{< /tabpane >}}

### 指定元素

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L59-L62" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L45-L48" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L58-L61" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L51-L54" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.5.0" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L61-L65" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L60-L63" >}}
{{< /tab >}}
{{< /tabpane >}}

## 复制粘贴

下面是使用上述所有方法执行复制/粘贴操作的示例.
请注意, 用于此操作的键位会有所不同, 具体取决于它是否是 Mac OS.
此代码将以文本收尾: `SeleniumSelenium!`

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/KeysTest.java#L70-L84" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_keys.py#L56-L67" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/KeysTest.cs#L72-L82" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/keys_spec.rb#L64-L74" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/keysTest.spec.js#L75-L87" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/KeysTest.kt#L74-L86" >}}
{{< /tab >}}
{{< /tabpane >}}
