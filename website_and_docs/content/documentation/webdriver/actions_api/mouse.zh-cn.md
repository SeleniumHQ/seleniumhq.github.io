---
title: "Mouse actions"
linkTitle: "Mouse"
weight: 4
needsTranslation: true
description: >
  A representation of any pointer device for interacting with a web page.
aliases: [
"/documentation/zh-cn/support_packages/mouse_and_keyboard_actions_in_detail/",
"/zh-cn/documentation/support_packages/mouse_and_keyboard_actions_in_detail/"
]
---

一个鼠标仅可以完成3个操作：
按住按钮，松开按钮，还有移动光标。
Selenium组合了常见的操作并提供了方便的方法。


## 按住鼠标左键

这个方法包含2个操作，首先将光标移动到被操作元素的正中心，然后按下鼠标左键不松开。
这对于聚焦一个特殊元素很有用：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L22-L25" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L12-L15" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L17-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L11-L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/clickAndHold.spec.js#L16-L18" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L23-L26" >}}
{{< /tab >}}
{{< /tabpane >}}

## 点击鼠标左键

这个方法包含2个操作，首先将光标移动到被操作元素的正中心，然后按下鼠标左键后再松开。
另一种叫法“点击”：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L34-L37" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L24-L27" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L30-L33" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L22-L25" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/clickAndRelease.spec.js#L16-L18" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L35-L38" >}}
{{< /tab >}}
{{< /tabpane >}}

## 点击鼠标备用按钮

鼠标一共有5个定义好的按钮:
* 0 — 左键 (默认值)
* 1 — 中间键 (当前不支持)
* 2 — 右键
* 3 — X1 (返回) 按钮
* 4 — X2 (前进) 按钮

### 点击鼠标右键

这个方法包含2个操作，首先将光标移动到被操作元素的正中心，然后点击鼠标右键。
另一种叫法“点击右键”：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L46-L49" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L35-L38" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L43-L46" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L34-L37" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/rightClick.spec.js#L17-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L47-L50" >}}
{{< /tab >}}
{{< /tabpane >}}

### 点击鼠标回退键

除了这个没有更方便的方法，只是点击鼠标回退按钮

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L60-L66" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L49-L52" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L59-L63" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L47-L50" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.5.0" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/backAndForwardClick.spec.js#L21-L22" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L61-L67" >}}
{{< /tab >}}
{{< /tabpane >}}

### 点击鼠标前进键

除了这个没有更方便的方法，只是点击鼠标前进按钮

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L78-L84" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L63-L66" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L77-L81" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L61-L64" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.5.0" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/backAndForwardClick.spec.js#L34-L35" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L79-L85" >}}
{{< /tab >}}
{{< /tabpane >}}

## 双击鼠标左键

这个方法包含2个操作，首先将光标移动到被操作元素的正中心，然后双击鼠标左键。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L93-L96" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L74-L77" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L91-L94" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L73-L76" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/doubleClick.spec.js#L17-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L94-L97" >}}
{{< /tab >}}
{{< /tabpane >}}

## 移动光标到元素上

这个方法是将光标移动到元素的中心点。
另一种叫法“悬停”。
元素必须在可视窗口范围内否则这条命令将会报错。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L105-L108" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L85-L88" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L104-L107" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L84-L87" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/moveToElement.spec.js#L17-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L106-L109" >}}
{{< /tab >}}
{{< /tabpane >}}

## 通过偏移量移动动光标

这些方法让光标先移动到指定的坐标原点，然后通过单位为px的偏移量进行光标相对原点的偏移移动。
注意光标位置必须在可视窗口区域否则会报错。

### 从元素中心点（原点）偏移

这个方法是指先将光标移动到元素中心点（原点），然后通过偏移量进行光标相对原点的偏移。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L118-L121" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L96-L99" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L132-L135" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L97-L100" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L119-L122" >}}
{{< /tab >}}
{{< /tabpane >}}

### 从视窗左上角（原点）偏移

这个方法是指先将光标移动到视窗左上角（原点），然后通过偏移量进行光标相对原点的偏移。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L131-L136" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L108-L110" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L146-L150" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L114-L116" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/moveByOffset.spec.js#L29-L30" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L132-L137" >}}
{{< /tab >}}
{{< /tabpane >}}

### 从当前光标位置（原点）偏移

这个方法是指光标位于当前位置（原点），然后通过偏移量进行光标相对原点的偏移。
如果之前没有移动过光标位置，则这个位置是视窗左上角（原点）。
注意当页面发生滚动后光标位置不会发生变化。

注意第一个参数指定为正数时向右移动，第二个参数指定为正数时向下移动。所以 `moveByOffset(30, -10)` 是指从当前光标位置向右移动30个像素位置和向上移动10个像素位置。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L153-L155" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L124-L126" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L167-L169" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L128-L130" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/moveByOffset.spec.js#L42" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L154-L156" >}}
{{< /tab >}}
{{< /tabpane >}}

## 拖放元素

这个方法首先在原元素上提交执行按下鼠标左键，移动到目标元素位置后是释放鼠标左键。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L166-L170" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L137-L141" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L181-L185" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L141-L145" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/dragAndDrop.spec.js#L29-L32" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L167-L171" >}}
{{< /tab >}}
{{< /tabpane >}}

## 通过偏移量拖放元素
这个方法首先在原元素上提交执行按下鼠标左键，通过给出的偏移量移动元素后释放鼠标左键。

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L179-L184" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L149-L154" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L195-L200" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L153-L158" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/dragAndDrop.spec.js#L17-L21" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L180-L185" >}}
{{< /tab >}}
{{< /tabpane >}}
