---
title: "了解组件"
linkTitle: "了解组件"
weight: 1
aliases: [
"/documentation/zh-cn/webdriver/understanding_the_components/",
"/zh-cn/documentation/webdriver/understanding_the_components"
]
---

使用 WebDriver 构建测试套件需要您理解并有效地使用许多不同的组件。就像软件中的一切一样，
不同的人对同一个想法使用不同的术语。下面是在这个描述中如何使用术语的细分。

### 专业术语

* **API:** 应用程序编程接口。这是一组用来操作 WebDriver 的 “命令”。
* **库:** 一个代码模块，它包含 api 和实现这些 api 所需的代码。库是对应于具体的语言的，例如 Java 的 .jar 文件，.NET 的 .dll 文件，等等。
* **驱动程序:** 负责控制实际的浏览器。大多数驱动程序是由浏览器厂商自己创建的。
驱动程序通常是与浏览器一起在系统上运行的可执行模块，而不是在执行测试套件的系统上。
(尽管它们可能是同一个系统。) _注意: 有些人把驱动称为代理。_
* **框架:** 用于支持 WebDriver 套件的附加库。这些框架可能是测试框架，如 JUnit 或 NUnit。
它们也可能是支持自然语言特性的框架，如 Cucumber 或 Robotium。还可以编写和使用框架来操作或配置被测试的系统、
数据创建、测试预言等等。

### 组成部分
至少，WebDriver 通过一个驱动程序与浏览器对话。通信有两种方式: WebDriver 通过驱动程序向浏览器传递命令，
然后通过相同的路径接收信息。

{{< figure src="/images/documentation/webdriver/basic_comms.png" class="img-responsive text-center" alt="基本通信">}}

驱动程序是特定于浏览器的，例如 ChromeDriver 对应于谷歌的 Chrome/Chromium，
GeckoDriver 对应于 Mozilla 的 Firefox 的，等等。驱动程序在与浏览器相同的系统上运行。
这可能与执行测试本身的系统相同，也可能不同。

上面这个简单的例子就是 _直接_通信。与浏览器的通信也可以是通过 Selenium 服务器或 RemoteWebDriver 
进行的 _远程_通信。RemoteWebDriver 与驱动程序和浏览器运行在同一个系统上。

{{< figure src="/images/documentation/webdriver/remote_comms.png" class="img-responsive text-center" alt="远程通信">}}

远程通信也可以使用 Selenium Server 或 Selenium Grid 进行，这两者依次与主机系统上的驱动程序进行通信

{{< figure src="/images/documentation/webdriver/remote_comms_server.png" class="img-responsive text-center" alt="通过 Grid 远程通信">}}

## 应用框架

WebDriver 有且只有一个任务: 通过上面的任何方法与浏览器通信。WebDriver 对测试一窍不通：它不知道如何比较事物、
断言通过或失败，当然它也不知道报告或 Given/When/Then 语法。 

这就是各种框架发挥作用的地方。至少你需要一个与绑定语言相匹配的测试框架，比如. NET 的 NUnit, Java 的 JUnit, 
Ruby 的 RSpec 等等。

测试框架负责运行和执行 WebDriver 以及测试中相关步骤。因此，您可以认为它看起来类似于下图。

{{< figure src="/images/documentation/webdriver/test_framework.png" class="img-responsive text-center" alt="测试框架">}}

像 Cucumber 这样的自然语言框架/工具可能作为上图中测试框架框的一部分存在，
或者它们可能将测试框架完全封装在它们自己的实现中。
