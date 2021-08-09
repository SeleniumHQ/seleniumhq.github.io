---
title: "服务网格的组件"
linkTitle: "服务网格的组件"
weight: 1
aliases: ["/documentation/zh-cn/grid/grid_4/components_of_a_grid/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Chinese. Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

{{< figure src="/images/documentation/grid/components.png" class="img-responsive text-center" alt="Grid">}}


## Router

The Router takes care of forwarding the request to the correct component.

It is the entry point of the Grid, all external requests will be received by it.
The Router behaves differently depending on the request.
If it is a new session request, the Router will add it to the New Session Queue. 
The Distributor regularly checks if there is a free slot. 
If so, the first matching request is removed from the New Session Queue.
If the request belongs to an existing session, the
Router will send the session id to the Session Map, and the Session Map will 
return the Node where the session is running. After this, the Router will
forward the request to the Node.

The Router aims to balance the load in the Grid by sending the requests to the
component that is able to handle them better, without overloading any component
that is not needed in the process.

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
则仅创建一个插槽.
通过特定的配置, 
它可以在Docker容器中运行会话.
您可以在下一 [章节]({{< ref "setting_up_your_own_grid.md" >}}) 
中查看更多配置详细信息. 

节点仅执行接收到的命令, 
它不进行评估、做出判断或控制任何事情.
运行节点的计算机不需要与其他组件具有相同的操作系统.
例如, Windows节点可以具有将Internet Explorer作为浏览器选项的功能,
而在Linux或Mac上则无法实现.

## 会话表

会话表是一种数据存储的方式, 
用于保存会话id和会话运行的节点的信息.
它作为路由支持, 
在向节点转发请求的过程中起作用.
路由将通过会话表获取与会话id关联的节点.

## New Session Queue

New Session Queue holds all the new session requests in a FIFO order. 
It has configurable parameters for setting the request timeout and request retry interval.

The Router adds the new session request to the New Session Queue and waits for the response.
The New Session Queue regularly checks if any request in the queue has timed out, 
if so the request is rejected and removed immediately.

The Distributor regularly checks if a slot is available. If so, the Distributor requests the
New Session Queue for the first matching request. The Distributor then attempts to create
a new session.

Once the requested capabilities match the capabilities of any of the free Node slots, the Distributor attempts to get the
available slot. If all the slots are busy, the Distributor will ask the queue to add the request to the front of the queue. 
If request times out while retrying or adding to the front of the queue it is rejected.

After a session is created successfully, the Distributor sends the session information to the New Session Queue.
The New Session Queue sends the response back to the client.

## 事件总线

事件总线充当节点、分发服务器、新的会话队列器和会话表之间的通信路径.
网格通过消息进行大部分内部通信, 避免了昂贵的HTTP调用.
当以完全分布式模式启动网格时, 事件总线是应该启动的第一个组件.


## 网格中的角色

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
