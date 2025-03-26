---
title: "组织和执行Selenium代码"
linkTitle: "使用Selenium"
weight: 10
description: >
  使用IDE和Test Runner库组织Selenium的执行
---

如果你不仅仅只是想执行一小撮的一次性脚本，你需要能组织和安排好你的代码。这一页会启发你如何真正地使用 Selenium 代码做高效的事情。

## 常见用法

大部分人使用 Selenium 执行针对 Web 应用的自动化测试，但是 Selenium 其实可以支持任何场景的浏览器自动化。

### 重复性任务

有时候你需要往网站记录日志或者下载一些东西，或者提交一个表单，你可以在预设的时间创建一个 Selenium 脚本去执行一个服务。

### 网页爬虫

你是否期望从一个不提供 API 的网站收集数据？Selenium 可以满足你，但是请确保你了解该网站的服务条例，因为有些网站不允许你这样做，甚至有些网站会屏蔽 Selenium。

### 测试

使用 Selenium 做测试需要在 Selenium 执行操作后进行断言，所以一个好的断言类库是很有必要的。至于组织测试用例结构的一些额外特性则需要[Test Runner](#test-runner)来完成。

## IDEs

不管你要用 Selenium 来做什么，没有一个好的集成开发环境，你的工作肯定不会高效。以下是一些常见的 IDE 选择：

- [Eclipse](https://www.eclipse.org/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [PyCharm](https://www.jetbrains.com/pycharm/)
- [RubyMine](https://www.jetbrains.com/ruby/)
- [Rider](https://www.jetbrains.com/rider/)
- [WebStorm](https://www.jetbrains.com/webstorm/)
- [VS Code](https://code.visualstudio.com/)

## Test Runner

即使不使用 Selenium 做测试，如果你有高级用例，使用一个 test runner 去更好地组织你的代码是很有意义的。学会使用 before/after hooks 和分组执行或者并行执行将会非常有用。

### 待选

有非常多不同的 test runner 可供选择。

这个教程中所有使用到 test runner 的代码示例都可以在我们的示例目录中找到（或者正在被迁移过去），而且这些示例在每一次发版都会被执行，以确保代码是正确的和最新的。下面是一份包含对应链接的 test runner 清单，其中第一项是被这个仓库和本页所有用例所使用的。

{{< tabpane text=true >}}
{{% tab header="Java" %}}

- [JUnit](https://junit.org/junit5/) - 个广泛使用的用于基于 Java 的 Selenium 测试的测试框架。
- [TestNG](https://testng.org/) - 提供诸如并行测试执行和参数化测试等额外功能。
  {{% /tab %}}

{{% tab header="Python" %}}

- [pytest](https://pytest.org/) - 由于其简单性和强大的插件，它成为许多人的首选。
- [unittest](https://docs.python.org/3/library/unittest.html) - Python 的标准测试库
  {{% /tab %}}

{{% tab header="CSharp" %}}

- [NUnit](https://nunit.org/) - .NET的流行单元测试框架
- [MS Test](https://docs.microsoft.com/en-us/visualstudio/test/getting-started-with-unit-testing?view=vs-2019) - 微软自己的单元测试框架
  {{% /tab %}}

{{% tab header="Ruby" %}}

- [RSpec](https://rspec.info/) - Ruby中运行Selenium测试最广泛使用的测试库
- [Minitest](https://github.com/seattlerb/minitest) - 一个随Ruby标准库附带的轻量级测试框架
  {{% /tab %}}

{{% tab header="JavaScript" %}}

- [Jest](https://jestjs.io/) - 主要作为React的测试框架而闻名，但也可以用于Selenium测试
- [Mocha](https://mochajs.org/) -最常用的运行Selenium测试的JavaScript库。
  {{% /tab %}}

{{% tab header="Kotlin" %}}
- [Kotest](https://kotest.io/) - 个灵活且全面的测试框架，专为 Kotlin 设计。
- [JUnit5](https://junit.org/junit5/) -标准的 Java 测试框架，完全兼容 Kotlin。
{{% /tab %}}
{{< /tabpane >}}

### 安装

在[安装 Selenium 类库]({{< ref "install_library.md" >}})一节中详细说明了需要哪些东西。这里的代码只展示在我们的文档示例项目中用到的示例。

{{< tabpane text=true >}}
{{% tab header="Java" %}}

**Maven**

**Gradle**

{{% /tab %}}
{{% tab header="Python" %}}

To use it in a project, add it to the `requirements.txt` file:

{{% /tab %}}
{{% tab header="CSharp" %}}
in the project's `csproj` file, specify the dependency as a `PackageReference` in `ItemGroup`:

{{% /tab %}}
{{% tab header="Ruby" %}}

Add to project's gemfile

{{% /tab %}}
{{% tab header="JavaScript" %}}
In your project's `package.json`, add requirement to `dependencies`:

{{% /tab %}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

### 断言

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/UsingSeleniumTest.java#L30-L31" >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/getting_started/using_selenium_tests.py#L8-L9" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/UsingSeleniumTest.cs#L19-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/using_selenium_spec.rb#L14-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/runningTests.spec.js#L14-L15" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L20-21" >}}
{{< /tab >}}
{{< /tabpane >}}

### Setting Up and Tearing Down

{{< tabpane text=true >}}
{{% tab header="Java" %}}

### Set Up

{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/UsingSeleniumTest.java#L19-L22" >}}

### Tear Down

{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/UsingSeleniumTest.java#L45-L48" >}}

{{% /tab %}}
{{% tab header="Python" %}}

### Set Up

{{< gh-codeblock path="examples/python/tests/getting_started/using_selenium_tests.py#L25-L28" >}}

### Tear Down

{{< gh-codeblock path="examples/python/tests/getting_started/using_selenium_tests.py#L30-31" >}}

{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Ruby" %}}

### Set Up

{{< gh-codeblock path="examples/ruby/spec/getting_started/using_selenium_spec.rb#L7-L9" >}}

### Tear Down

{{< gh-codeblock path="examples/ruby/spec/spec_helper.rb#L28" >}}
{{% /tab %}}
{{< tab header="JavaScript" >}}

### Set Up

{{< gh-codeblock path="examples/javascript/test/getting_started/runningTests.spec.js#L7-L9" >}}

### Tear Down

{{< gh-codeblock path="examples/javascript/test/getting_started/runningTests.spec.js#L30" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 执行

{{< tabpane text=true >}}
{{% tab header="Java" %}}

### Maven

```shell
mvn clean test
```

### Gradle

```shell
gradle clean test
```

{{% /tab %}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/README.md#L35" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Ruby" %}}
{{< gh-codeblock path="examples/ruby/README.md#L26" >}}
{{% /tab %}}
{{% tab header="JavaScript" %}}

### Mocha

```shell
mocha runningTests.spec.js
```

### npx

```shell
npx mocha runningTests.spec.js
```

{{% /tab %}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### 示例

在[第一个脚本]({{< ref "first_script.md" >}})一节中，我们了解了 Selenium 脚本的每一个组件。这里是使用 test runner 重新组织那个脚本的一个示例：

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/UsingSeleniumTest.java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/using_selenium_tests.py" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/UsingSeleniumTest.cs" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/using_selenium_spec.rb" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/runningTests.spec.js" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## 下一步

使用你目前所学到的知识建立你自己的 Selenium 代码吧！

想要了解更多的功能特性，请继续阅读我们接下来的[WebDriver 教程]({{< ref "/documentation/webdriver/" >}})
