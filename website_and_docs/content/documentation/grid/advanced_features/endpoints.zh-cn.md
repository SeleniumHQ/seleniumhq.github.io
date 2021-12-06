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
cURL GET 'http://localhost:4444/status'
```

在独立模式下, Grid URL是独立服务器的地址.

在集线器节点模式下, Grid URL是集线器服务器的地址.

在完全分布式模式下, Grid URL是路由服务器的地址.

以上所有模式的默认URL皆为http://localhost:4444.

## 分发器

### 删除节点

要从Grid中删除节点, 
请使用下面列出的cURL命令.
它不会停止在该节点上运行的任何持续中的会话.
除非显式终止, 否则节点将继续按原样运行.
分发器不再知晓该节点,
因此任何匹配的新会话请求
也不会转发到该节点.

在独立模式下, 分发器URL是独立服务器的地址.

在集线器节点模式下, 分发器URL是集线器服务器的地址. 

```shell
cURL --request DELETE 'http://localhost:4444/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET: <secret> '
```
在完全分布式模式下, URL是分发器的地址.

```shell
cURL --request DELETE 'http://localhost:5553/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
如果在设置Grid时未配置注册密码, 
则使用
```shell
cURL --request DELETE 'http://<Distributor-URL>/se/grid/distributor/node/<node-id>' --header 'X-REGISTRATION-SECRET;'
```

### 放空节点

节点放空命令用于优雅地关闭节点.
放空节点将在所有持续中的会话完成后停止节点.
但是, 它不接受任何新的会话请求.

在独立模式下, 分发器URL是独立服务器的地址.

在集线器节点模式下, 分发器URL是集线器服务器的地址.
```shell
cURL --request POST 'http://localhost:4444/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET: <secret> '
```
在完全分布式模式下, URL是分发服务器的地址.
```shell
cURL --request POST 'http://localhost:5553/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET: <secret>'
```
如果在设置Grid时未配置注册密码, 
则使用
```shell
cURL --request POST 'http://<Distributor-URL>/se/grid/distributor/node/<node-id>/drain' --header 'X-REGISTRATION-SECRET;'
```

## 节点

本节中的端点适用于
集线器节点模式和
节点独立运行的完全分布式网格模式.
在一个节点的情况下, 默认节点的URL为 http://localhost:5555 .
如果有多个节点,
请使用 [Grid 状态](#grid-状态) 获取所有节点详细信息
以及定位地址.

### 状态 

节点状态本质上是节点的运行状况检查.
分发器定期ping节点状态，
并相应地更新Grid模型.
状态包括相关的可用性, 会话和插槽的信息.

```shell
cURL --request GET 'http://localhost:5555/status'
```

### 放空

分发器将 [放空](#放空节点) 命令传递给
由node-id标识的相应节点.
要直接放空节点,
请使用下面列出的cuRL命令.
两个端点都有效并产生相同的结果.
放空会等待持续中的会话完成后
才停止节点.

```shell
cURL --request POST 'http://localhost:5555/se/grid/node/drain' --header 'X-REGISTRATION-SECRET: <secret>'
```
如果在设置Grid时未配置注册密码,
则使用
```shell
cURL --request POST 'http://<node-URL>/se/grid/node/drain' --header 'X-REGISTRATION-SECRET;'
```

### 检查会话所有者

要检查会话是否属于某一节点, 请使用下面列出的cURL命令. 

```shell
cURL --request GET 'http://localhost:5555/se/grid/node/owner/<session-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
如果在设置Grid时未配置注册密码, 
则使用
```shell
cURL --request GET 'http://<node-URL>/se/grid/node/owner/<session-id>' --header 'X-REGISTRATION-SECRET;'
```

如果会话属于该节点, 
则返回true, 
否则返回false.

### 删除会话

删除会话将终止WebDriver会话,
退出驱动程序
并将其从活动会话集合中删除. 
任何使用删除的会话id
或重用驱动程序实例的请求
都将抛出错误. 

```shell
cURL --request DELETE 'http://localhost:5555/se/grid/node/session/<session-id>' --header 'X-REGISTRATION-SECRET: <secret>'
```
如果在设置Grid时未配置注册密码, 
则使用
```shell
cURL --request DELETE 'http://<node-URL>/se/grid/node/session/<session-id>' --header 'X-REGISTRATION-SECRET;'
```

## 新会话队列

### 清除新会话队列

新会话请求队列保存新会话请求.
要清除队列, 
请使用下面列出的cURL命令.
清除队列将拒绝队列中的所有请求.
对于每个这样的请求, 
服务器都会向相应的客户端返回一个错误响应.
清除命令的返回结果是
已删除请求的总数.

在独立模式下, 队列URL是独立服务器的地址. 
在集线器节点模式下, 队列URL是集线器服务器的地址. 

```shell
cURL --request DELETE 'http://localhost:4444/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET: <secret>'
```

在完全分布式模式下,
队列URL是新会话队列服务器的地址.
```shell
cURL --request DELETE 'http://localhost:5559/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET: <secret>'
```

如果在设置Grid时未配置注册密码, 
则使用
```shell
cURL --request DELETE 'http://<URL>/se/grid/newsessionqueue/queue' --header 'X-REGISTRATION-SECRET;'
```

### 获取新会话队列请求

New Session Request Queue holds the new session requests. 
To get the current requests in the queue, use the cURL command enlisted below. 
The response returns the total number of requests in the queue and the request payloads.
新会话请求队列保存新会话请求.
要获取队列中的当前请求,
请使用下面列出的cURL命令.
响应会返回队列中的请求总数以及请求内容.

在独立模式下, 队列URL是独立服务器的地址.
在集线器节点模式下, 队列URL是集线器服务器的地址.

```shell
cURL --request GET 'http://localhost:4444/se/grid/newsessionqueue/queue'
```

在完全分布式模式下,
队列URL是新会话队列服务器的地址.
```shell
cURL --request GET 'http://localhost:5559/se/grid/newsessionqueue/queue'
