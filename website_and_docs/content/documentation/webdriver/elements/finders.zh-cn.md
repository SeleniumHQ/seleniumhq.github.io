---
title: "查询网络元素"
linkTitle: "查询器"
weight: 2
aliases: [
"/documentation/zh-cn/webdriver/locating_elements/",
"/zh-cn/documentation/webdriver/locating_elements/"
]
description: >
  根据提供的定位值定位元素.
---


使用 Selenium 最基本的特点之一是获取可用于操作的元素引用。
Selenium 提供了许多内置的 [定位策略]({{< ref "locators.md" >}})，用于唯一标识元素。
在更复杂的场景中，可以用多种方式使用这些定位器。为了本篇文档的目的，
请使用 [Selenium 定位器测试页面](https://www.selenium.dev/selenium/web/locators_tests/locators.html)。

## 第一个匹配的元素

许多定位器会匹配页面上的多个元素。
单个的 find element 方法会返回在给定上下文中找到的第一个元素的引用。

### 在整个 DOM 中查找

当在 driver 实例上调用 find element 方法时，
它会返回 DOM 中与所提供定位器匹配的第一个元素的引用。
该引用可以被保存并用于后续的元素操作。
在 Selenium 定位器测试页面上，有两个 class 名称为 `information` 的元素，
因此此方法会返回第一个文本输入框。

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L22-L23">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L7-L8">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L15-L16">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L10-L11" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L9-L10">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L19-L20">}}
  {{< /tab >}}
{{< /tabpane >}}



### 在 DOM 的子集内评估

与其在整个 DOM 中寻找唯一的定位器，
通常更有用的是将搜索范围缩小到另一个已定位元素的作用域内。

一种解决办法是先定位目标元素的祖先，
然后在该对象上调用 `find element`：

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L31-L33">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L14-L16">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L25-L27">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L17-L19" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L18-L20">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L27-L29">}}
  {{< /tab >}}
{{< /tabpane >}}

{{% pageinfo color="info" %}}
**Java 和 C#**<br>
`WebDriver`、`WebElement` 和 `ShadowRoot` 类都实现了 `SearchContext` 接口，  
该接口被视为一种 _基于角色的接口_。基于角色的接口可以让你判断特定的驱动实现是否支持某项功能。  
这些接口定义清晰，并尽量遵循单一职责原则。  
{{% /pageinfo %}}

### 评估 Shadow DOM

Shadow DOM 是隐藏在元素内部的封装 DOM 树。  
自 Chromium 浏览器在 v96 发布后，Selenium 已支持通过易用的 shadow root 方法访问该树。注意：这些方法需要 Selenium 4.0 或更高版本。

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
{{< tab header="Java" >}}
WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"));
SearchContext shadowRoot = shadowHost.getShadowRoot();
WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#shadow_content"));
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L39-L42">}}
{{< /tab >}}
{{< tab header="CSharp" >}}
var shadowHost = _driver.FindElement(By.CssSelector("#shadow_host"));
var shadowRoot = shadowHost.GetShadowRoot();
var shadowContent = shadowRoot.FindElement(By.CssSelector("#shadow_content"));
{{< /tab >}}
{{< tab header="Ruby" >}}
shadow_host = @driver.find_element(css: '#shadow_host')
shadow_root = shadow_host.shadow_root
shadow_content = shadow_root.find_element(css: '#shadow_content')
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 优化后的定位器

嵌套查找可能不是最有效的定位策略，
因为它需要向浏览器发送两次独立的命令。

为略微提升性能，我们可以使用 CSS 或 XPath，在一次命令中定位到该元素。
请参阅本节中关于[定位策略建议]({{< ref "/documentation/test_practices/encouraged/locators" >}})的说明
以及[推荐的测试实践]({{< ref "/documentation/test_practices/encouraged" >}})。

在本例中，我们将使用 CSS 选择器：

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L41-L42">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L22-L23">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L36-L37">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L25-L26" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L28-L29">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L36-L37">}}
  {{< /tab >}}
{{< /tabpane >}}



## 所有匹配的元素

在某些情况下，需要获取与定位器匹配的所有元素的引用，而不是仅获取第一个。
复数形式的 `find elements` 方法会返回一组元素引用。如果没有匹配项，则返回空列表。
在本例中，将返回所有 input 元素的引用集合。

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L50-L51">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L29-L30">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L46-L47">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L32-L33" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L37-L38">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L44-L45">}}
  {{< /tab >}}
{{< /tabpane >}}

### 获取元素
有时你会得到一组元素，但想操作其中某个特定元素，
这意味着需要遍历该集合并找到目标元素。


{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L61-L64">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L51-L53">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L58-L62">}}
  {{< /tab >}}
   {{< tab header="Ruby" >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L40-L42" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L47-L50">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L53-L56">}}
  {{< /tab >}}
{{< /tabpane >}}

## 从元素查找子元素

用于在父元素的上下文中查找匹配的子 WebElement 列表。
为此，可在父 WebElement 上链式调用 `findElements` 来访问子元素。

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L74-L78">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L61-L64">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L73-L78">}}
  {{< /tab >}}
   {{< tab header="Ruby" >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L48-L51" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L59-L63">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L64-L68">}}
  {{< /tab >}}
{{< /tabpane >}}

## 获取活动元素

用于跟踪或查找当前浏览上下文中具有焦点的 DOM 元素。

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L88-L89">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L72-L73">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L89-L90">}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
  {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L58-L60" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L72-L73">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L76-L77">}}
  {{< /tab >}}
{{< /tabpane >}}


