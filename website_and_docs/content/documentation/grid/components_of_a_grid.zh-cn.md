---
title: "服务网格的组件"
linkTitle: "服务网格的组件"
weight: 1
description: >
    Check the different Grid components to understand how to use them.
aliases: ["/documentation/zh-cn/grid/grid_4/components_of_a_grid/"]
---


{{< card header="**Grid Components**" footer="Grid components shown in the fully distributed mode" >}}
![Selenium Grid 4 Components](/images/documentation/grid/components.png "Selenium Grid 4 Components")
{{< /card >}}


## 路由

路由负责将请求转发到正确的组件.

它是Grid的入口点, 所有外部请求都将由其接收.
路由的行为因请求而异.
当请求一个新的会话时, 路由将把它添加到新的会话队列中.
分发器定期检查是否有空闲槽.
若有, 则从新会话队列中删除第一个匹配请求.
如果请求属于存量会话, 
这个路由将会话id发送到会话表, 
会话表将返回正在运行会话的节点.
在此之后, 路由将
将请求转发到节点.

为了更好地发挥效力, 
路由通过将请求发送到组件的方式, 
来平衡Grid的负载,
从而使处理过程中不会有任何的过载组件.

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

By default, the Node auto-registers all browser drivers available on the path of
the machine where it runs. It also creates one slot per available CPU for Chromium
based browsers and Firefox. For Safari and Internet Explorer, only one slot is created.
Through a specific configuration, it can run sessions in Docker containers or relay commands.
You can see more configuration details in the next [section]({{< ref "setting_up_your_own_grid.md" >}}).

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

## 新会话队列

新会话队列以先进先出的顺序保存所有新会话请求.
其具有用于设置请求超时和请求重试间隔的可配置参数.

路由将新会话请求添加到新会话队列并等待响应.
新会话队列定期检查队列中的任何请求是否已超时,
若有，则请求将被拒绝并立即删除.

分发器定期检查是否有可用槽. 
若有, 分发器将为第一个匹配的请求索取新会话队列.
然后分发器会尝试创建新的会话.

一旦请求的功能与任何空闲节点槽匹配, 
分发器将尝试获取可用槽. 
如果没有空闲槽, 
分发器会要求队列将请求添加到队列前面.
如果请求在重试或添加到队列头时超时, 
则该请求将被拒绝.

成功创建会话后, 
分发器将会话信息发送到新会话队列.
新会话队列将响应发送回客户端.

## 事件总线

事件总线充当节点、分发服务器、新的会话队列器和会话表之间的通信路径.
网格通过消息进行大部分内部通信, 避免了昂贵的HTTP调用.
当以完全分布式模式启动网格时, 事件总线是应该启动的第一个组件.


{{% alert title="Running your own Grid" color="primary" %}}
Looking forward to using all these components and run your own Grid?
Head to the ["Setting up your own"]({{< ref "setting_up_your_own_grid.md" >}})
section to understand how to put all these pieces together.
{{% /alert %}}
