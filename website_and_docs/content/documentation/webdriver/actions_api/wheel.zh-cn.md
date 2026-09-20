---
title: "滚轮操作"
linkTitle: "滚轮"
weight: 6
description: >
  一种用于与网页交互的滚轮输入设备的示意.
---

{{< badge-version version="4.2" >}}
{{< badge-browser browser=Chromium wpt="perform_actions/wheel.py" >}}

在页面上滚动有 5 种情况.

## 滚动到元素

这是最常见的场景. 
与传统的点击和发送按键的方法不同, 
动作类不会自动将目标元素滚动到视图中, 
因此如果元素不在视口内, 
就需要使用此方法.

此方法仅将一个网页元素作为参数.

无论该元素是在当前视图屏幕的上方还是下方, 
视口都会滚动, 使该元素的底部位于屏幕底部.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L17-L20" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_wheel.py#L11-L14" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/WheelTest.cs#L17-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/wheel_spec.rb#L11-L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/wheelTest.spec.js#L16-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/actions_api/WheelTest.kt#L18-L21" >}}
{{< /tab >}}
{{< /tabpane >}}

## 按给定的量进行滚动

这是第二种最常见的滚动场景. 
传入一个 x 方向的偏移量和一个 y 方向的偏移量, 
表示向右和向下滚动的距离. 负值分别表示向左和向上滚动.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L29-L33" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_wheel.py#L22-L26" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/WheelTest.cs#L31-L35" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/wheel_spec.rb#L22-L26" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/wheelTest.spec.js#L26-L31" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/actions_api/WheelTest.kt#L30-L34" >}}
{{< /tab >}}
{{< /tabpane >}}

## 将元素按照给定的量进行滚动

这种情形实际上是上述两种方法的结合.

要执行此操作, 请使用“从...滚动”方法, 
该方法需要 3 个参数.  
第一个参数表示起始点, 我们将其指定为元素,  
后两个参数分别是 x 偏移量和 y 偏移量的值.

如果元素不在视口内, 它将滚动到屏幕底部, 
然后页面将根据提供的 x 和 y 偏移量进行滚动.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L42-L46" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_wheel.py#L35-L39" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/WheelTest.cs#L46-L53" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/wheel_spec.rb#L34-L38" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/wheelTest.spec.js#L40-L44" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/actions_api/WheelTest.kt#L43-L47" >}}
{{< /tab >}}
{{< /tabpane >}}

## 从具有偏移量的元素进行滚动

当您需要滚动屏幕的某一部分, 而该部分位于视口之外, 
或者位于视口之内但必须滚动的屏幕部分与特定元素之间存在已知偏移量时, 
会使用此场景.

这再次使用了“从...滚动”的方法, 并且除了指定元素之外, 
还指定了一个偏移量来表明滚动的起始点. 
该偏移量是从所提供的元素的中心计算得出的.

如果元素不在视口内, 它首先会被滚动到屏幕底部, 
然后滚动的原点将通过将偏移量添加到元素中心的坐标来确定, 
最后页面将根据提供的 x 和 y 偏移量值进行滚动.

请注意, 如果元素中心的偏移量超出视口范围, 
将会导致异常.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L57-L61" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_wheel.py#L50-L54" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/WheelTest.cs#L66-L75" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/wheel_spec.rb#L57-L61" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/wheelTest.spec.js#L62-L66" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/actions_api/WheelTest.kt#L59-L63" >}}
{{< /tab >}}
{{< /tabpane >}}

## 从给定元素的原点偏移量处滚动指定的距离

最后一种情况适用于您需要滚动屏幕的某一部分, 
而该部分已处于视口内的情况.

这再次使用了“从...滚动”的方法, 但这次指定的是视口而非某个元素. 
从当前视口的左上角开始指定偏移量. 
确定原点后, 页面将根据提供的 x 偏移量和 y 偏移量进行滚动.

请注意, 如果从视口左上角算起的偏移量超出屏幕范围, 
将会导致异常.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L73-L76" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_wheel.py#L66-L70" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/WheelTest.cs#L89-L97" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/wheel_spec.rb#L63-L66" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/wheelTest.spec.js#L75-L77" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/actions_api/WheelTest.kt#L75-L78" >}}
{{< /tab >}}
{{< /tabpane >}}
