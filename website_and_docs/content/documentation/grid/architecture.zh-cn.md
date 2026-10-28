---
title: "Grid 架构"
linkTitle: "Grid 架构"
weight: 10
aliases: [
"/documentation/grid/grid_architecture"
]
---

Grid 由一组各司其职的组件构成，共同维护整个 Grid。其架构看起来可能比较复杂，
希望本文能帮助你理清其中的概念。

## 主要组件

Grid 的主要组件如下：

<dl>
<dt>事件总线（Event Bus）
<dd>用于在其他组件之间发送消息，接收方可以异步处理这些消息。

<dt>新会话队列（New Session Queue）
<dd>保存尚未由分发器分配给节点的新会话请求。

<dt>分发器（Distributor）
<dd>负责维护 Grid 中可运行会话的位置模型（称为“插槽”），并接收<a
    href="https://w3c.github.io/webdriver/#new-session">新会话</a>请求，
    将请求分配给一个插槽。

<dt>节点（Node）
<dd>运行<a
    href="https://w3c.github.io/webdriver/#dfn-sessions">WebDriver
    会话</a>。每个会话都会分配到一个插槽，每个节点至少有一个插槽。

<dt>会话映射（Session Map）
<dd>维护<a
    href="https://w3c.github.io/webdriver/#dfn-session-id">会话 ID</a>
    与运行该会话的节点地址之间的映射。

<dt>路由器（Router）
<dd>作为 Grid 的前端。它是 Grid 中唯一<i>可能</i>暴露给更广泛 Web 的部分，
    但我们强烈不建议这样做。路由器会将收到的请求转发到新会话队列，
    或者转发到正在运行相应会话的节点。
</dl>

讨论 Grid 时，还需要了解以下几个概念：

* **插槽（slot）**是可运行一个会话的位置。
* 每个插槽都有一个**特征（stereotype）**，即一组最小的能力。
  [新会话][new session]请求必须满足这些能力要求，分发器才会将请求发送到
  拥有该插槽的节点。
* **Grid 模型（Grid Model）**是分发器跟踪 Grid 状态的模型。顾名思义，
  模型有时可能与实际状态不同步（例如，分发器刚刚启动时）。
  分发器使用该模型而不是逐一查询节点，从而能快速为新会话请求分配插槽。

## 同步调用与异步调用

Grid 内部主要使用两种通信机制：

1. 通过 HTTP 发送同步的、近似 REST 风格的 JSON 请求。
2. 向事件总线发送异步事件。

应该如何选择通信机制？毕竟，我们也可以将整个 Grid 设计成事件驱动架构，
而且这种设计同样可行。

如果要执行的操作本身是同步的（例如，大多数 WebDriver 调用），或者遗漏响应会造成问题，
Grid 就会使用同步调用。相反，如果要向所有感兴趣的接收方广播信息，
或者遗漏响应也无关紧要，则优先使用事件总线。

值得注意的是，与同步调用相比，异步调用与其监听方的耦合更松散。

## 启动顺序与组件依赖关系

Grid 允许组件按任意顺序启动，但从概念上讲，组件的启动顺序如下：

1. 首先启动事件总线和会话映射。它们不依赖其他组件，彼此之间也没有依赖关系，
   因此可以并行启动。
2. 接下来启动会话队列。
3. 此时可以启动分发器。分发器会定期连接会话队列并轮询任务；
   轮询既可以由事件触发（有新会话加入队列时），也可以按固定时间间隔执行。
4. 然后可以启动路由器。新会话请求会被转发到会话队列，
   分发器则会尝试找到可运行会话的插槽。
5. 此时可以启动节点。关于节点如何注册到 Grid，请参见下文。
   注册完成后，Grid 即可处理流量。

下表展示了组件之间的依赖关系。“✅”表示组件之间存在同步依赖关系。

|               | 事件总线 | 分发器 | 节点 | 路由器 | 会话映射 | 会话队列 |
|---------------|----------|--------|------|--------|----------|----------|
| 事件总线      |    X     |        |      |        |          |          |
| 分发器        |    ✅    |   X    |  ✅  |        |          |    ✅    |
| 节点          |    ✅    |        |  X   |        |          |          |
| 路由器        |          |        |  ✅  |   X    |    ✅    |          |
| 会话映射      |          |        |      |        |    X     |          |
| 会话队列      |    ✅    |        |      |        |          |    X     |

## 节点注册

将新节点注册到 Grid 的过程很轻量。

1. 节点启动后，应定期发送“心跳”事件。心跳中包含[节点状态][node status]。
2. 分发器会监听心跳事件。收到心跳后，分发器会尝试向节点的 `/status` 端点发送
   `GET` 请求，并根据返回的信息配置 Grid。

分发器也会定期使用同一个 `/status` 端点检查节点状态。节点启动后仍应持续发送心跳事件，
这样，即使分发器没有持久化保存 Grid 状态，也能在重启后通过心跳逐步恢复并更新状态。

### 节点状态对象

节点状态是一个包含以下字段的 JSON 对象：

| 名称 | 类型 | 描述 |
|------|------|------|
| availability | string | 可取 `up`、`draining` 或 `down`。其中，`draining` 表示不应再向该节点分配新会话；该节点上的最后一个会话结束后，节点进程将退出或重启。 |
| externalUrl | string | Grid 中其他组件连接此节点时使用的 URI。 |
| lastSessionCreated | integer | 此节点上最近一次创建会话时的 Unix 纪元时间戳。在其他条件相同的情况下，分发器会优先向空闲时间最长的节点分配新会话。 |
| maxSessionCount | integer | 虽然可以通过统计可用插槽数推算会话数，但此整数值用于确定节点被视为“已满”之前可同时运行的最大会话数。 |
| nodeId | string | 用于标识此节点实例的 UUID。 |
| osInfo | object | 包含 `arch`、`name` 和 `version` 字段的对象，供 Grid UI 和 GraphQL 查询使用。 |
| slots | array | 插槽对象数组（见下文）。 |
| version | string | 节点的版本号（对于 Selenium，此版本号与 Selenium 版本号一致）。 |

建议为所有字段都设置值。

### 插槽对象

插槽对象表示节点中的一个插槽。一个插槽可运行一个会话。
节点的插槽数可能多于其可同时运行的会话数。例如，某个节点最多可以运行 10 个会话，
但会话可以使用 Chrome、Edge 或 Firefox 的任意组合。此时，该节点会将“最大会话数”
设为 10，同时声明有 10 个 Chrome 插槽、10 个 Edge 插槽和 10 个 Firefox 插槽。

| 名称 | 类型 | 描述 |
|------|------|------|
| id | string | 用于标识插槽的 UUID。 |
| lastStarted | string | 插槽最近一次启动会话的时间，采用 ISO-8601 格式。 |
| stereotype | object | 此插槽用于匹配的最小[能力][capabilities]集合。例如：`{"browserName": "firefox"}`。 |
| session | object | 会话对象（见下文）。 |

### 会话对象

此对象表示插槽中正在运行的会话。

| 名称 | 类型 | 描述 |
|------|------|------|
| capabilities | object | 会话实际提供的能力；其值与[新会话][new session]命令的返回值一致。 |
| startTime | string | 会话的启动时间，采用 ISO-8601 格式。 |
| stereotype | object | 此插槽用于匹配的最小[能力][capabilities]集合。例如：`{"browserName": "firefox"}`。 |
| uri | string | 节点用于与会话通信的 URI。 |

[capabilities]: https://w3c.github.io/webdriver/#dfn-merging-capabilities
[new session]: https://w3c.github.io/webdriver/#new-session
[node status]: https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/grid/data/NodeStatus.html
[session]: https://w3c.github.io/webdriver/#dfn-sessions
[session id]: https://w3c.github.io/webdriver/#dfn-session-id
