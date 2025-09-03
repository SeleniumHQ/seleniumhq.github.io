---
title: "Grid架构"
linkTitle: "Grid架构"
weight: 10
aliases: [
"/zh-cn/documentation/grid/grid_architecture"
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language d-4"></i> 
   Page being translated from 
   English to Chinese. Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

Grid 被设计为一组组件，这些组件共同维护 Grid。它可能看起来相当复杂，但希望本文档能帮助澄清任何疑问。

## The Key Components

 Grid的主要组件是：

<dl>
<dt>事件总线
<dd>用于在其他组件之间异步发送和接收消息。

<dt>新会话队列
<dd>维护一个尚未分配给节点的传入会话列表。

<dt>分配器
<dd>负责维护 Grid 中可用位置的模型（称为“槽”），并接收任何传入的新会话请求，将其分配给一个槽。

<dt>节点
<dd>运行一个<a
    href="https://w3c.github.io/webdriver/#dfn-sessions">WebDriver
    session</a> 会话。每个会话分配给一个槽，每个节点有一个或多个槽。

<dt>会话映射
<dd>维护会话ID <a
    href="https://w3c.github.io/webdriver/#dfn-session-id">session
    ID</a> 和运行会话的节点地址之间的映射。

<dt>路由器
<dd>作为 Grid 的前端。这是唯一可能暴露给外部 Web 的组件（尽管我们强烈建议不要这样做）。它将传入请求路由到新会话队列或运行会话的节点。
</dl>

在讨论 Grid 时，还需要记住一些其他有用的概念：

 * 槽（slot）是会话可以运行的地方。
 * 每个槽有一个能力集（stereotype）。这是一个最小能力集，传入的会话请求必须匹配，然后分配器才会将请求发送到拥有该槽的节点。
 * 分配器跟踪 Grid 的状态。顾名思义，有时可能会因为现实（也许分配器刚刚启动）而出现同步问题。如果优先查询每个节点，以便分配器可以快速为新会话请求分配槽。

## 同步和异步调用

Grid 中使用了两种主要的通信机制：

 1. 通过 HTTP 请求的同步“REST-ish” JSON。
 2. 发送到事件总线的异步事件。

我们如何选择同步机制（例如，大多数 WebDriver 调用）或异步机制？之后，我们可以将整个 Grid 建模为事件驱动的，它将正常工作。

答案是，如果响应丢失将是问题，我们希望将信息广播给任何感兴趣的人，或者我们不在乎响应，我们更喜欢使用事件总线。

一个有趣的现象是，同步调用比异步调用更解耦。

## 组件间的启动顺序和依赖关系

尽管 Grid 设计为允许组件以任何顺序启动，但组件启动的顺序如下：

1. 事件总线和会话映射首先启动。这些没有其他依赖项，甚至彼此之间也没有，因此可以安全地并行启动。
2. 接下来启动新会话队列。
3. 现在可以启动分配器。这将定期连接到新会话队列并轮询作业，尽管这种轮询可以是初始化的（即查询）或定期的。
4. 路由器可以启动。新会话请求将被定向到新会话队列，分配器将尝试找到一个槽来运行会话。
5. 我们现在可以启动节点。请参阅下面的详细信息，了解节点如何与 Grid 注册。注册完成后，Grid 准备提供服务。

您可以这样想象组件之间的依赖关系，其中 √ 表示组件之间存在同步依赖关系。

|               | 事件总线| 分配器 | 节点 | 路由器 | 会话映射 | 会话队列 |  
|---------------|-----------|-------------|------|--------|-------------|---------------|
| Event Bus     |    X      |             |      |        |             |               |
| Distributor   |    ✅     |      X      |  ✅  |        |             |      ✅       |
| Node          |    ✅     |             |  X   |        |             |               |
| Router        |           |             |  ✅  |   X    |     ✅      |               |
| Session Map   |           |             |      |        |     X       |               |
| Session Queue |    ✅     |             |      |        |             |      X        |

## 节点注册

向 Grid 注册新节点的过程是轻量级的。
1. 当节点启动时，它应该定期发送“心跳”事件。这个心跳包含节点状态。
2. 分配器监听心跳事件。当它看到时，它尝试获取节点的 /status 端点。这是 Grid 设置的信息。
分配器将使用相同的 /status 端点定期检查节点，但节点应该在启动后继续发送心跳事件，以便在没有持久存储 Grid 状态的情况下，分配器可以重启并（最终）更新和正确。

### 节点状态对象

节点状态是一个 JSON blob，具有以下字段：

| 名称 | 类型 | 描述 |
|------|------|-------------|
| availability | string | 一个字符串，可以是 up、draining 或 down。重要的是 draining，它表示不应再向节点发送新会话，一旦最后一个会话关闭，节点将退出或重启。|
| externalUrl | string | 网格的其他组件应该连接的 URL。 |
| lastSessionCreated | integer | 最后一次会话创建的纪元时间戳。分配器将尝试将新会话发送到具有最长空闲时间的节点（如果所有其他条件都相等）。 |
| maxSessionCount | integer | 尽管可以通过计算可用槽的数量来推断会话计数，但这个整数值用于确定在节点上同时运行的最大会话数，然后才被认为是“满”。 |
| nodeId | string | 用于标识此节点实例的 UUID。 |
| osInfo | object | 具有 arch、name 和 version 字段的对象。这用于 Grid UI 和 GraphQL 查询。 |
| slots | array | 槽对象数组（下面描述）。|
| version | string | 节点的版本（对于 Selenium，这将与 Selenium 版本号匹配） |

建议在所有字段中填写值。

### 槽对象

槽对象表示节点内的单个槽。“槽”是可以在节点上运行的单个会话的地方。一个节点可能有比它可以同时运行的更多槽。例如，一个节点可能能够运行多达 10 个会话，但它们可能是 Chrome、Edge 或 Firefox 的任何组合。在这种情况下，节点将指示“最大会话数为 10”，然后还说它有 10 个 Chrome 槽，10 个 Edge 槽和 10 个 Firefox 槽。

| 名称 | 类型 | 描述 |
|------|------|-------------|
| id | string | UUID 和槽进行匹配 |
| lastStarted | string |  当槽上次启动会话时，在 ISO-8601 格式。 |
| stereotype | object | 此槽将匹配的最小能力集。一个最小示例是 {"browserName": "firefox"}|
| session | object | 会话对象（见下文） |

### 会话对象

这表示槽中的运行会话。

| 名称 | 类型 | 描述 |
|------|------|-------------|
| capabilities | object | 会话提供的实际功能。将与 new session 命令的返回值匹配。 |
| startTime | string |会话的开始时间，ISO-8601 格式。 |
| stereotype | object | 此槽将匹配的最小能力集。一个最小示例是 {"browserName": "firefox"} |
| uri | string | 节点用于与会话通信的 URI。 |

[capabilities]: https://w3c.github.io/webdriver/#dfn-merging-capabilities
[new session]: https://w3c.github.io/webdriver/#new-session
[node status]: https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/grid/data/NodeStatus.html
[session]: https://w3c.github.io/webdriver/#dfn-sessions
[session id]: https://w3c.github.io/webdriver/#dfn-session-id
