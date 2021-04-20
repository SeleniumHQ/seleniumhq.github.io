---
title: "服务网格的组件"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> There are certain paragaraphs needs translation from 
English to Chinese. Do you speak Chinese? Help us to translate
it by sending us pull requests!
{{% /notice %}}


![Grid](/images/grid_4.png)

## Router

路由器负责将请求转发到正确的组件.

它是网格的入口,所有外部请求都将借此被网格接收.
路由器的行为取决于请求.
如果是新的会话请求,
则路由器会将其转发到分发服务器 (将在其中处理并创建新的会话). 
如果请求属于存量会话,则路由器会将会话ID发送到会话集合,
会话集合将返回会话正在运行的节点.
此后, 路由器会将请求转发到节点.

路由器旨在通过将请求发送到能够更好地处理请求的组件,
来平衡网格中的负载,
从而避免过程中任何组件无谓地过载.

## Distributor

分发器知道所有节点及其功能. 
它的主要作用是接收新的会话请求
并找到可以在其中创建会话的适当节点. 
创建会话后, 分发器在会话集合中存储会话ID与正在执行会话的节点之间的关系. 

## Node

一个节点可以在网格中出现多次.
每个节点负责管理其运行机器的可用浏览器的插槽.

节点通过事件总线将其自身注册到分发服务器,
并且将其配置作为注册消息的组成部分一起发送.

默认情况下, 
节点会自动注册运行它的计算机路径上所有可用的浏览器驱动程序.
它还为基于Chromium的浏览器和Firefox的每个可用的CPU都创建插槽.
对于Safari和Internet Explorer,
则仅创建一个插槽。
通过特定的配置, 
它可以在Docker容器中运行会话.
您可以在下一 [章节]({{< ref "/grid/grid_4/setting_up_your_own_grid.zh-cn.md" >}}) 
中查看更多配置详细信息. 

节点仅执行接收到的命令, 
它不进行评估、做出判断或控制任何事情.
运行节点的计算机不需要与其他组件具有相同的操作系统.
例如, Windows节点可以具有将Internet Explorer作为浏览器选项的功能,
而在Linux或Mac上则无法实现.

## Session Map

The Session Map is a data store that keeps the information of the session id and the Node 
where the session is running. It serves as a support for the Router in the process of 
forwarding a request to the Node. The Router will ask the Session Map for the Node 
associated to a session id.

## New Session Queuer, New Session Queue

The New Session Queuer is the only
component which can communicate with the New Session Queue. It handles all queue operations like
add to manipulate the queue. It has configurable parameters for setting 
the request timeout and request retry interval.

The New Session Queuer receives the new session request from the Router and adds it to the queue. 
The queuer waits until it receives the response for the request. 
If the request times out, the request is rejected immediately and not added to the queue. 

Upon successfully adding the request to the queue, Event Bus triggers an event. 
The Distributor picks up this event and polls the queue. It now attempts to create a session.

If the requested capabilities do not exist in any of the registered Nodes, then the request is rejected
immediately and the client receives a response.

If the requested capabilities match the capabilities of any of Node slots, Distributor attempts to get the
available slot. If all the slots are busy, the Distributor will ask the queuer to add the request 
to the front of the queue. The Distributor receives the request again after the request retry interval. 
It will attempt retries until the request is successful or has timed out. 
If request times out while retrying or adding to the front of the queue its rejected.

After getting an available slot and session creation, the Distributor passes the new session response 
to the New Session Queuer via the Event Bus. The New Session Queuer will respond to the client when it
receives the event.

## Event Bus

The Event Bus serves as a communication path between the Nodes, Distributor, New Session Queuer, and Session Map. 
The Grid does most of its internal communication through messages, avoiding expensive HTTP calls. 
When starting the Grid in its fully distributed mode, the Event Bus is the first component that should be started. 

## Roles in Grid

在网格3中, 组件是集线器和节点, 
可以通过以独立模式启动网格来一起运行它们.
Grid 4中提供了相同的概念, 
可以通过对上述某些组件进行分组来运行集线器, 
也可以在独立模式下一起运行所有组件.

### Hub

集线器是以下组件的结合:

* 路由器
* 分发器
* 会话集合
* 事件总线

它启用传统集线器和节点(们)的设置.

### Standalone

如前所述, 独立模式是所有组件的结合, 
并且在用户看来, 它们作为一个组件执行.
这包括集线器的部分组件, 
再加上一个节点.
在独立模式下启动后, 
可以使用一个功能齐全的网格.
