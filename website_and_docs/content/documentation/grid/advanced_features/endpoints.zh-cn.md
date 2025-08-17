---
title: "Grid端点"
linkTitle: "Grid端点"
weight: 3
aliases: [
"/documentation/zh-cn/grid/grid_4/grid_endpoints/",
"/zh-cn/documentation/grid/advanced_features/grid_endpoints/"
]
---

## Grid 

### Grid 状态

Grid状态提供Grid的当前状态. 
它包含每个注册节点的详细信息. 
对于每个节点, 
状态包括有关节点可用性、会话和插槽的信息. 

```shell
curl --request GET 'http://localhost:4444/status'
```

### 删除会话

删除会话会终止 WebDriver 会话、退出驱动程序并将其从活动会话映射中删除。任何使用删除的会话标识或重新使用驱动程序实例的请求都会出错。

```shell
curl --request DELETE 'http://localhost:4444/session/<session-id>'
```

### 我应该使用哪一个URL?

在 Standalone 模式下, Grid URL是独立服务器的地址.

在 Hub-Node 模式下, Grid URL是集线器服务器的地址.

在完全分布式模式下, Grid URL是路由服务器的地址.

以上所有模式的默认URL皆为http://localhost:4444.

## 分发器

### 删除节点

要从网格中删除节点，请使用下面列出的 curl 命令。该命令不会停止正在该节点上运行的任何会话。除非显式终止, 否则节点将继续运行。分发器不再知道该节点，因此任何匹配的新会话请求都不会转发到该节点。

在 Standalone 模式下，分发器 URL 是独立服务器地址。

在 Hub-Node 模式下, 分发器 URL 是 Hub 服务器的地址。

```shell
curl --request DELETE 'http://localhost:4444/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET: <secret> '
```
在完全分布式模式下, URL是分发器的地址。

```shell
curl --request DELETE 'http://localhost:4444/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
如果在设置Grid时未配置注册密码, 则使用
```shell
curl --request DELETE 'http://<Router-URL>/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET;'
```

### 释放节点

节点释放命令用于优雅地关闭节点。在所有正在进行的会话结束后，会停止该节点。并且，它不会接受任何新的会话请求。

在 Standalone 模式下，分发器 URL 是独立服务器地址。

在 Hub-Node 模式下, 分发器 URL 是 Hub 服务器的地址。
```shell
curl --request POST 'http://localhost:4444/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET: <secret> '
```
在完全分布式模式下, URL是分发服务器的地址。
```shell
curl --request POST 'http://localhost:4444/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET: <secret>'
```
如果在设置Grid时未配置注册密码, 则使用
```shell
curl --request POST 'http://<Router-URL>/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET;'
```

## 节点

本节中的端点适用于 Hub-Node 模式和节点独立运行的完全分布式网格模式。在一个节点的情况下, 默认节点的URL为 http://localhost:5555 。
如果有多个节点,请使用 [Grid 状态](#grid-状态) 获取所有节点的详细信息并查找节点地址。

### 状态 

节点状态本质上是节点的健康检查。分发程序会定期 ping 节点状态，并相应地更新 Grid 模型。状态包括有关可用性、会话和插槽的信息。

```shell
curl --request GET 'http://localhost:5555/status'
```

### 释放

分发器将 [释放](#释放节点) 命令传递给由node-id标识的相应节点。要直接释放节点,请使用下面列出的curl命令。
两个端点都有效并产生相同的结果。释放会等待持续中的会话完成后才停止节点。

```shell
curl --request POST 'http://localhost:5555/se/grid/node/drain' --header 'X-REGISTRATION-SECRET: <secret>'
```
如果在设置Grid时未配置注册密码,则使用
```shell
curl --request POST 'http://<node-URL>/se/grid/node/drain' --header 'X-REGISTRATION-SECRET;'
```

### 检查会话所有者

要检查会话是否属于某一节点, 请使用下面列出的curl命令. 

```shell
curl --request GET 'http://localhost:5555/se/grid/node/owner/<session-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
如果在设置Grid时未配置注册密码, 则使用
```shell
curl --request GET 'http://<node-URL>/se/grid/node/owner/<session-id>' --header 'X-REGISTRATION-SECRET;'
```

如果会话属于该节点, 
则返回true, 
否则返回false。

### 删除会话

删除会话会终止 WebDriver 会话、退出驱动程序并将其从活动会话映射中删除。任何使用删除的会话标识或重新使用驱动程序实例的请求都会出错。

```shell
curl --request DELETE 'http://localhost:5555/se/grid/node/session/<session-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
如果在设置Grid时未配置注册密码, 则使用
```shell
curl --request DELETE 'http://<node-URL>/se/grid/node/session/<session-id>' --header 'X-REGISTRATION-SECRET;'
```

## 新会话队列

### 清除新会话队列

新会话请求队列保存新会话请求。要清除队列，请使用下面列出的 curl 命令。清除队列会拒绝队列中的所有请求。对于每个此类请求，服务器都会向相应的客户端返回错误响应。清除命令的结果是被删除请求的总数。

在 Standalone 模式下, 队列URL是独立服务器的地址。
在 Hub-Node 模式下, 队列URL是集线器服务器的地址。

```shell
curl --request DELETE 'http://localhost:4444/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET: <secret>'
```

在完全分布式模式下,
队列URL是新会话队列服务器的地址。
```shell
curl --request DELETE 'http://localhost:4444/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET: <secret>'
```

如果在设置Grid时未配置注册密码, 则使用
```shell
curl --request DELETE 'http://<Router-URL>/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET;'
```

### 获取新会话队列请求

新会话请求队列保存新会话请求。
要获取队列中的当前请求,
请使用下面列出的curl命令。
响应会返回队列中的请求总数以及请求内容。

在 Standalone 模式下, 队列URL是独立服务器的地址。
在 Hub-Node 模式下, 队列URL是集线器服务器的地址。

```shell
curl --request GET 'http://localhost:4444/se/grid/newsessionqueue/queue'
```

在完全分布式模式下,
队列URL是新会话队列服务器的地址。
```shell
curl --request GET 'http://localhost:4444/se/grid/newsessionqueue/queue'
