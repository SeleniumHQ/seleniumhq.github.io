---
title: "服务网格的组件"
linkTitle: "服务网格的组件"
weight: 6
description: >
    Description of Hub and Nodes for Grid 3.
aliases: ["/documentation/zh-cn/grid/grid_3/components_of_a_grid/"]
---

{{< figure src="/images/documentation/legacy/grid_3/grid.png" class="img-responsive text-center" alt="Grid 3 Components">}}

## 转发器(hub)
* 中间人和管理者
* 接受请求 执行测试任务
* 接受客户端的指示并在远程节点上执行任务
* 管理进程

_转发器(hub)_ 是一个接受所有所有测试任务的中心节点。
每个Selenium服务网格包含一个转发器(hub)。转发器(hub)需要能被所有的客户机（比如：持续集成服务器，开发机等等）访问到。
转发器(hub)会连接1个或者多个节点，这些节点会代理执行测试任务。

## 节点

* 浏览器会被安装在节点上
* 节点会把自己注册在转发器(hub)上并申报自己作为测试代理的能力(有些什么浏览器，每个浏览器可以运行几个实例等等)
* 接受转发器(hub)的指示并执行这些指示

_节点_ 和不同的Selenium实例，他们能够在特定的计算机系统上执行测试。
一个服务网格中可以有很多节点。
这些终端设备并不需要使用统一的平台(或者说操作系统)也不需要选择相同的浏览器。
一个Windows节点可以提供IE作为一个浏览器选项来执行测试，然而Linux和MAC是不可能提供的。

