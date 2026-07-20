---
title: "设计模式和开发策略"
linkTitle: "设计策略"
weight: 1
---


（原位置： https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests）

## 概览 

随着时间推移, 项目往往会积累大量测试. 
随着测试总数的增加, 对代码库进行改动会变得更困难 —— 即便只是一次“简单”的改动, 
也可能导致大量测试失败, 而应用本身仍然能正常工作. 
有时这种问题不可避免, 但一旦发生, 你希望能尽快恢复运行. 
下面介绍的一些设计模式与策略在 WebDriver 场景下被证明可以简化测试编写与维护, 
可能也对你有帮助. 

[领域驱动设计]({{< ref "encouraged/domain_specific_language.md" >}}): 以最终用户的语言来表达测试.

[页面对象]({{< ref "encouraged/page_object_models.md" >}}): 对 Web 应用 UI 的一种简单抽象. 

LoadableComponent: 把页面对象建模为可加载的组件. 

BotStyleTests: 使用以命令为导向的自动化测试方式, 而不是页面对象更偏向的对象化方法


## Loadable Component 

### 其是什么?

LoadableComponent 是一个基类, 目标是让编写页面对象更轻松. 
它通过提供一套标准机制来确保页面被正确加载, 并提供在页面加载失败时用于调试的钩子. 
你可以利用它来减少测试中的样板代码, 从而降低维护测试的工作量.

目前在 Selenium 中包含了一个 Java 实现, 
但该方法足够简单, 可在任何语言中实现.

### 简单用法

举个我们想要建模的 UI 的例子, 看看 [new issue](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=I-defect%2Cneeds-triaging&projects=&template=bug-report.yml&title=%5B%F0%9F%90%9B+Bug%5D%3A+) 页面.
对于测试作者而言, 该页面的作用是提供一个提交新 issue 的功能. 
一个基本的页面对象看起来像:


{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L14-L76" >}}

为了把它变成一个 LoadableComponent, 
我们所要做的就是将其设为基类:

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L161" >}}

这个签名看起来有些不寻常, 但它的含义很简单:
该类表示一个用于加载 EditIssue 页面 的 LoadableComponent.


通过继承这个基类, 我们需要实现两个新方法:

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L187-L200" >}}


`load` 方法用于导航到页面, 
而 `isLoaded` 方法用于判断我们是否位于正确的页面. 
虽然该方法看起来像是应该返回 boolean, 
但实际上它通过 JUnit 的 Assert 类执行一系列断言. 
断言可以多也可以少. 通过这些断言, 
可以为类的使用者提供清晰的调试信息, 
用于定位测试失败的原因. 

稍作改造后, 我们的页面对象如下：

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L161-L246" >}}

看起来似乎没带来太多好处, 对吧？
不过它确实把如何导航到该页面的信息封装进了页面本身, 
这意味着这些信息不会散落在代码库各处. 
这也意味着我们可以在测试中这样做：

```java
EditIssue page = new EditIssue(driver).get();
```

上述调用会在必要时促使 driver 导航到该页面. 

### 嵌套组件

当 LoadableComponent 与其他 LoadableComponent 配合使用时, 
会更加有用. 以我们的例子为例, 
我们可以把“编辑 issue”页面视为项目网站中的一个组件（毕竟我们是通过该网站的某个选项卡访问它）. 
并且提交 issue 需要登录. 我们可以把它建模为一棵嵌套组件树：

```
 + ProjectPage
 +---+ SecuredPage
     +---+ EditIssue
```

这在代码中会是什么样子？
首先, 每个逻辑组件都有自己的类. 
每个类的 `load` 方法会调用父组件的 `get` 方法. 
最终效果, 除了上面的 EditIssue 类外, 还包括：

ProjectPage.java:

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L97-L118" >}}
 
以及 SecuredPage.java:

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L120-L159" >}}

EditIssue 中的 `load` 方法现在如下：

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L187-L194" >}}


这表明这些组件都是相互 "嵌套" 的. 
在 EditIssue 中调用 `get()` 会导致它的所有依赖组件也被加载. 
示例用法：

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L276-L298" >}}

如果在测试中使用像 [Guiceberry](https://github.com/zorzella/guiceberry) 这样的库,
设置页面对象的前置步骤可以省略, 从而使测试更简洁、可读.


## Bot 模式


（原位置： https://github.com/SeleniumHQ/selenium/wiki/Bot-Style-Tests）


尽管页面对象是减少测试重复的一种有效方式, 
但并非所有团队都愿意或适应这种模式. 
另一种可选的方式是采用更加 "命令式" 的测试风格. 


“bot” 是对底层 Selenium API 的一种面向动作的抽象. 
这意味着如果发现某些命令不适合你的应用, 可以轻松修改它们. 例如：

{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/design_strategies/BestPractices.java#L248-L274" >}}


一旦构建了这些抽象并找出测试中的重复, 
就可以在 bot 之上再封装 页面对象. 


## 示例

{{< tabpane text=true >}}
{{< tab header="Python" >}}
一个用例 使用 `python + pytest + selenium` 实现了设计策略 "**Action Bot**, **Loadable Component** 和 **页面对象**".

A `pytest` fixture `chrome_driver`.

{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L6-L26" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


"**Action Bot**" 实现.

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L28-L65" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


"**Loadable Component** 定义.

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L67-L80" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


"**Loadable Component** 以及 **页面对象**" 实现. 

{{< tabpane text=true >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L82-L172" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

测试用例实现.

{{< tabpane text=true >}}
{{< tab header="Python" >}}
Test cases implementation with `pytest`.

{{< gh-codeblock path="/examples/python/tests/design_strategy/using_best_practice.py#L174-L240" >}}
{{< /tab >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
