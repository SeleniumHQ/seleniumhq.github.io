---
title: "等待策略"
linkTitle: "等待"
weight: 6
aliases: ["/documentation/zh-cn/webdriver/waits/"]
---


或许浏览器自动化面临的最常见挑战在于,
确保网络应用程序处于能够按预期执行特定 Selenium 命令的状态. 
这些过程常常陷入一种 _竞态条件_ , 
有时浏览器会先达到正确状态 (一切按预期运行) , 
有时 Selenium 代码会先执行 (一切未按预期运行) . 
这是导致 _不稳定测试_ 的主要原因之一. 


所有导航命令都会等待特定基于 [页面加载策略]({{< ref "drivers/options#pageloadstrategy">}}) 的值 `readyState` 
 (默认等待的值为 `"complete"` ) , 
然后驱动程序才会将控制权交还给代码. 
`readyState`  仅关注 HTML 中定义的资源加载, 
但加载的 JavaScript 资源常常会导致网站发生变化, 
而当代码准备执行下一个 Selenium 命令时, 
需要交互的元素可能尚未出现在页面上. 


同样, 在许多单页应用程序中, 
元素会根据点击操作动态添加到页面上或改变可见性.  
对于 Selenium 能够与之交互, 
该元素必须既存在于页面上又处于[displayed]({{< ref "elements/information/#is-displayed">}}) 状态. 


以这个页面为例: https://www.selenium.dev/selenium/web/dynamic.html 
当点击 "Add a box!" 按钮时, 
会创建一个原本不存在的 "div" 元素.  
当点击 "Reveal a new input" 按钮时, 
一个隐藏的文本字段元素会被显示出来.  
在这两种情况下, 过渡都需要几秒钟.  
如果 Selenium 代码要点击其中一个按钮并与生成的元素进行交互,  
它会在该元素准备好之前就执行操作, 从而导致失败. 


许多人首先想到的解决办法是在代码中添加一个睡眠语句, 
让代码暂停执行一段设定的时间. 
由于代码无法确切知道需要等待多久, 
如果设置的睡眠时间不够长, 
这种方法可能会失败. 
相反, 如果睡眠时间设置得过高, 并且在每个需要的地方都添加睡眠语句, 
那么会话的持续时间可能会变得难以接受. 

Selenium 提供了更好的两种不同的同步机制, 


## 隐式等待
Selenium 内置了一种自动等待元素出现的方式, 称为 _隐式等待_ .  
隐式等待的值可以通过浏览器选项中的 [timeouts]({{< ref "drivers/options#timeouts">}}) 设置来设定, 
也可以通过驱动程序的方法来设定 (如下所示) . 

这是一个全局设置, 适用于整个会话期间的每个元素定位调用. 
默认值为 `0` , 
这意味着如果未找到元素, 
将立即返回错误. 
如果设置了隐式等待, 
驱动程序将在返回错误之前等待所提供的时长. 
请注意, 一旦定位到元素, 
驱动程序将返回元素引用, 
代码将继续执行, 
因此较大的隐式等待值不一定增加会话的持续时间. 

*警告:*
请勿混合使用隐式等待和显式等待.  
这样做可能会导致等待时间不可预测. 
例如, 设置 10 秒的隐式等待和 15 秒的显式等待,  
可能会导致在 20 秒后发生超时. 

使用隐式等待解决我们的示例代码如下: 


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
{{< gh-codeblock path="examples/javascript/test/waits/waits.spec.js#L39" >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}



## 显式等待

_显式等待_ 是在代码中添加的, 用于轮询应用程序的循环, 
直到特定条件评估为真时, 才退出循环并继续执行代码中的下一个命令. 
如果在指定的超时值之前条件未满足, 
代码将给出超时错误. 
由于应用程序未处于所需状态的方式有很多, 
因此显式等待是为每个需要等待的地方指定确切等待条件的绝佳选择.  
另一个不错的特性是, 默认情况下, 
Selenium 等待类会自动等待指定的元素存在. 



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
{{< gh-codeblock path="examples/javascript/test/waits/waits.spec.js#L52" >}}
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
{{< badge-code >}}
  {{< /tab >}}
{{< /tabpane >}}



### 定制

Wait 类可以通过各种参数进行实例化, 
这些参数会改变条件的评估方式. 

这可以包括：
* 更改代码的评估频率 (轮询间隔) 
* 指定哪些异常应自动处理
* 更改总超时时长
* 自定义超时消息

例如, 如果默认情况下对 _元素不可交互_ 错误进行重试, 
那么我们可以在执行中的代码里的某个方法内添加一个操作
 (我们只需要确保代码在成功时返回  `true`  即可)：



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

