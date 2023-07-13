---
title: "Selenium Grid快速起步"
linkTitle: "快速起步"
weight: 2
needsTranslation: true
description: >
  一步一步地说明如何运行简单的Selenium Grid.
aliases: [
"/documentation/zh-cn/grid/grid_4/setting_up_your_own_grid/",
"/zh-cn/documentation/grid/setting_up_your_own_grid/"
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language d-4"></i> 
   Page being translated from English to Chinese. 
   Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

## 快速开始

1. 先决条件
    * 需要安装 Java 11 或更高版本
    * 需要安装浏览器
    * 需要安装浏览器驱动程序
      * [Selenium Manager]({{< ref "../selenium_manager/" >}}) will configure the drivers automatically if you add `--selenium-manager true`.
      * [需要已经安装并配置了 PATH 环境变量]({{< ref "../webdriver/troubleshooting/errors/driver_location.md#3-path-环境变量" >}})
    * 从[最新的发布版本](https://github.com/SeleniumHQ/selenium/releases/latest)下载 Selenium Server jar 文件
1. 启动 Grid
    * `java -jar selenium-server-<version>.jar standalone`
1. 将您的 WebDriver 测试指向 [http://localhost:4444](http://localhost:4444)
1. (可选) 通过在浏览器中打开 [http://localhost:4444](http://localhost:4444) 检查正在运行的测试和可用的功能

*想知道如何将您的测试指向 [http://localhost:4444](http://localhost:4444)吗? 
请查看 [`RemoteWebDriver` section]({{< ref "../webdriver/drivers/#远程驱动" >}})。

要了解更多不同的配置选项，请查看以下各小节。

## Grid 角色

Grid由六个不同的[组件]({{< ref "components.md" >}})组成，这使您可以以不同的方式部署它。

根据您的需求，您可以单独启动每个组件（分布式），将它们分组为Hub和Node，或者全部在单个机器上运行（独立）。

### 单机部署（Standalone）

**Standalone** 可以将所有 Grid [组件]({{< ref "components.md" >}}) 无缝地整合成一个单独的实体。在 **Standalone** 模式下运行 Grid，只需一个命令即可获得一个完整的 Grid，并在一个进程中运行。**Standalone** 只能在一台机器上运行。

**Standalone** 模式也是最容易启动 Selenium Grid 的模式。默认情况下，服务器将在 [http://localhost:4444](http://localhost:4444) 上监听 `RemoteWebDriver` 请求，并且服务器将从系统 [PATH]({{< ref "../webdriver/troubleshooting/errors/driver_location.md#3-path-环境变量" >}}) 中检测可以使用的驱动程序。

```shell
java -jar selenium-server-<version>.jar standalone
```

成功启动 Standalone 模式的 Grid 后，将你的 WebDriver 测试指向  [http://localhost:4444](http://localhost:4444)。

**Standalone** 的常见用途包括：
* 本地使用 `RemoteWebDriver` 开发或调试测试
* 推送代码之前运行快速测试套件
* 在 CI/CD 工具（GitHub Actions、Jenkins 等）中设置简单的 Grid

### Hub and Node

**Hub和Node**是最常用的角色，因为它允许：
* 将不同的机器组合成一个单一的Grid
  * 例如拥有不同操作系统和/或浏览器版本的机器
* 在不同的环境中运行WebDriver测试有一个单一的入口点
* 在不破坏Grid的情况下增加或减少容量。

#### Hub

一个Hub由以下[组件]({{< ref "components.md" >}})组成：
路由器（Router）、分发器（Distributor）、会话映射（Session Map）、新会话队列（New Session Queue）和事件总线（Event Bus）。

```shell
java -jar selenium-server-<version>.jar hub
```

默认情况下，服务器将在 [http://localhost:4444](http://localhost:4444) 上监听`RemoteWebDriver`请求。

#### Node

在启动时，**Node**将从系统的[`PATH`]({{< ref "../webdriver/troubleshooting/errors/driver_location.md#3-path-环境变量" >}})中检测可用的驱动程序。

以下命令假设**Node**正在运行的机器与**Hub**在同一台机器上。

```shell
java -jar selenium-server-<version>.jar node
```

##### 同一台机器上的多个Node

**Node** 1
```shell
java -jar selenium-server-<version>.jar node --port 5555
```

**Node** 2
```shell
java -jar selenium-server-<version>.jar node --port 6666
```

##### 不同机器上的Node和Hub

**Hub**和**Nodes**通过HTTP和[**事件总线**]({{< ref "components.md#event-bus" >}})（**事件总线**位于**Hub**内部）进行通信。

**Node**通过事件总线向**Hub**发送消息以开始注册过程。当**Hub**收到消息时，通过HTTP与**Node**联系以确认其存在。

要成功将**Node**注册到**Hub**，重要的是要在**Hub**机器上公开**事件总线**端口（默认为4442和4443）。这也适用于**Node**端口。有了这个，**Hub**和**Node**都能够通信。

如果**Hub**使用默认端口，则可以使用 `--hub` 注册**Node**。
```shell
java -jar selenium-server-<version>.jar node --hub http://<hub-ip>:4444
```

当**Hub**未使用默认端口时，需要使用`--publish-events`和`--subscribe-events`。

例如，如果**Hub**使用端口8886、8887和8888。
```shell
java -jar selenium-server-<version>.jar hub --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887 --port 8888
```
**Node**需要使用这些端口才能成功注册。
```shell
java -jar selenium-server-<version>.jar node --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887
```

### 分部署部署（Distributed） 

在使用分布式Grid时，每个组件都需要单独启动，并且理想情况下应该在不同的机器上。

{{% alert color="primary" %}}
重要的是要正确暴露所有端口，以允许所有组件之间的流畅通信。
{{% /alert %}}

1. **事件总线（Event Bus）**: 使不同网格组件之间的内部通信成为可能。

默认端口为：`4442`、`4443`和`5557`。
```shell
java -jar selenium-server-<version>.jar event-bus --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --port 5557
```

2. **新会话队列（New Session Queue）**: 将新的会话请求添加到一个队列中，Distributor将查询该队列。

默认端口为`5559`。
```shell
java -jar selenium-server-<version>.jar sessionqueue --port 5559
```

3. **会话映射（Session Map）**: 将会话ID映射到运行该会话的节点。

默认**会话映射**端口为`5556`。**会话映射**与**事件总线**进行交互。

```shell
java -jar selenium-server-<version>.jar sessions --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --port 5556
```

4. **分配器（Distributor）**: 查询新 **会话队列（New Session Queue）** 以获取新会话请求，并在能力匹配时将其分配给 **Node**。 **Nodes** 注册到 **Distributor** 的方式与在 **Hub/Node** 网格中注册到 **Hub** 相同。

默认**分配器**端口为`5553`。**分配器** 与 **新会话队列**、**会话映射**、**事件总线** 和 **Node(s)** 进行交互。

```shell
java -jar selenium-server-<version>.jar distributor --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --sessions http://<sessions-ip>:5556 --sessionqueue http://<new-session-queue-ip>:5559 --port 5553 --bind-bus false
```

5. **路由器（Router）**: 将新会话请求重定向到队列，并将正在运行的会话请求重定向到运行该会话的**Node**。

默认**路由器**端口为`4444`。**路由器** 与 **新会话队列**、**会话映射**和**分配器** 进行交互。

```shell
java -jar selenium-server-<version>.jar router --sessions http://<sessions-ip>:5556 --distributor http://<distributor-ip>:5553 --sessionqueue http://<new-session-queue-ip>:5559 --port 4444
```

6. **Node(s)**

默认 **Node** 端口是 `5555`.
```shell
java -jar selenium-server-<version>.jar node --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443
```

## 测试中的 Metadata

向测试中添加 `Metadata` 并通过[GraphQL]({{< ref "advanced_features/graphql_support.md" >}})进行消费，或通过 `Selenium Grid UI` 可视化其部分内容（例如`se:name`）。

可以通过在 `capability` 前加上 `se:` 来添加元数据。以下是一个Java的快速示例。

```java
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setCapability("browserVersion", "100");
chromeOptions.setCapability("platformName", "Windows");
// Showing a test name instead of the session id in the Grid UI
chromeOptions.setCapability("se:name", "My simple test"); 
// Other type of metadata can be seen in the Grid UI by clicking on the 
// session info or via GraphQL
chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value"); 
WebDriver driver = new RemoteWebDriver(new URL("http://gridUrl:4444"), chromeOptions);
driver.get("http://www.google.com");
driver.quit();
```

## 查询 Selenium Grid 相关状态

启动 `Grid` 后，主要有两种方式查询其状态，通过 `Grid UI` 或通过 `API` 调用。

可以通过打开您喜欢的浏览器并前往[http://localhost:4444](http://localhost:4444)。

`API` 调用可以通过 [http://localhost:4444/status](http://localhost:4444/status) 端点或使用 [GraphQL]({{< ref "advanced_features/graphql_support.md" >}})

{{% pageinfo color="primary" %}}
为简单起见，本页中显示的所有命令示例均假定组件正在运行在本地。更详细的示例和用法可以在[配置组件]({{< ref "/configuration" >}}) 部分。
{{% /pageinfo %}}

## 使用 Java 11 中的 HTTP Client {{% badge-version version="4.5" %}}

默认情况下，Grid 将使用 [AsyncHttpClient](https://github.com/AsyncHttpClient/async-http-client)。 AsyncHttpClient 是一个建立在 Netty 之上的开源库。 它允许异步执行 HTTP 请求和响应。 此外，它还提供 WebSocket 支持。 因此它很合适。

然而，AsyncHttpClient 从 2021 年 6 月开始就没有主动维护了。恰逢 Java 11+ 提供了内置的 HTTP 和 WebSocket 客户端。

目前，Selenium 计划将支持的最低版本升级到 Java 11。然而，这需要大量的工作。为了确保用户体验不受影响，将其与主要发布版本和相应的公告对齐是至关重要的。

要使用 Java 11 客户端，您需要下载 `selenium-http-jdk-client` jar文件并使用 `--ext` 参数使其在 Grid jar 的类路径中可用。

jar文件可以直接从 [repo1.maven.org](https://repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-http-jdk-client/) 下载，然后使用以下方式启动Grid：

```bash
java -Dwebdriver.http.factory=jdk-http-client -jar selenium-server-<version>.jar --ext selenium-http-jdk-client-<version>.jar standalone
```

下载 `selenium-http-jdk-client` jar 文件的替代方法是使用 [Coursier](https://get-coursier.io/docs/cli-installation)。

```bash
java -Dwebdriver.http.factory=jdk-http-client -jar selenium-server-<version>.jar --ext $(coursier fetch -p org.seleniumhq.selenium:selenium-http-jdk-client:<version>) standalone
```

如果您使用的是集线器/节点模式或分布式模式，则需要为每个组件设置 `-Dwebdriver.http.factory=jdk-http-client` 和 `--ext` 参数。

## Grid 的规模

选择 `Grid` 角色取决于需要支持什么操作系统和浏览器、需要执行多少个并行会话、可用机器的数量以及这些机器的配置（CPU、RAM）。

并发创建会话依赖于 **分配器** 的可用处理器。 例如，如果一台机器有 4 个 CPU，则 **分配器** 最多只能同时创建 4 个会话。

默认情况下，**Node** 支持的最大并发会话数受可用 CPU 数量的限制。 例如，如果 **Node** 机器有 8 个 CPU，它最多可以运行 8 个并发浏览器会话（Safari 除外，它始终是一个）。 此外，预计每个浏览器会话应使用大约 1GB 的 RAM。

通常，建议 **Nodes** 尽可能小。 与其让机器有 32 个 CPU 和 32GB RAM 来运行 32 个并发浏览器会话，不如有 32 个小的 **Node**，以便更好地隔离进程。 有了这个，如果一个 **Node** 发生故障，它将以孤立的方式进行。 Docker 是实现这种方法的好工具。

请注意，默认值（每个浏览器 1 个 CPU/1GB RAM）是建议值，它们不适用于您的上下文。 建议将它们用作参考，但持续测量性能将有助于确定您的环境的理想值。

`Grid` 大小与支持的并发会话数量和 **Node** 数量有关，没有“一刀切”的说法。 下面提到的尺寸是粗略的估计，不同环境之间可能会有所不同。 例如，当 **Hub** 具有足够的资源时，具有 120 个 **Nodes** 的 **Hub/Node** 可能运行良好。 以下值并非一成不变，欢迎提供反馈！

### 小规模

**Standalone** 或 **Hub/Node** 不超过5个 **Nodes**.

### 中等规模

**Hub/Node** 介于6到60个 **Nodes** 之间。

### 大规模

**Hub/Node** 介于60到100个 **Nodes** 之间， **Distributed** 超过100个 **Nodes**。

## 请注意

必须使用适当的防火墙权限保护Selenium Grid免受外部访问。

以下一种或多种情况可能会导致你的 `Grid` 处于一个不安全的状态：

* 您提供对您的 `Grid` 基础设施的开放访问
* 您允许第三方访问内部网络应用程序和文件
* 您允许第三方运行自定义二进制文件

请参阅 [Detectify](//labs.detectify.com) 上的这篇博文，它提供了一个很好的公开暴露的 `Grid` 如何被滥用的概述：[不要让你的 `Grid` 暴露在外](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/)

## 延伸阅读

* [Components]({{< ref "components.md" >}})：了解 `Grid` 的内部组件如何相互关联。
* [Configuration]({{< ref "/configuration" >}}): 自定义您的 `Grid` 设置。
* [Architecture]({{< ref "architecture.md" >}}): 理解 `Grid` 中的关键概念。
* [Advanced Features]({{< ref "/advanced_features" >}}): 通过Grid的特性探索更多的可能性。
