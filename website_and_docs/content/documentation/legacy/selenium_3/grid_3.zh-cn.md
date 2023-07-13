---
title: "服务网格 3"
linkTitle: "服务网格 3"
weight: 2
description: >
    Selenium Grid 3 supported WebDriver without Selenium RC code.
    Grid 3 was completely rewritten for the new Grid 4.
aliases: [
"/documentation/zh-cn/grid/grid_3/",
"/zh-cn/documentation/legacy/grid_3"
]
---

[服务网格 4]({{< ref "/documentation/grid" >}})

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language d-4"></i> 
   Most of the documentation found in this section is still in English.
   Please note we are not accepting pull requests to translate this content
   as translating documentation of legacy components does not add value to
   the community nor the project.
</p>
{{% /pageinfo %}}


_Selenium服务网格_ 是一个能够让Selenium的测试把命令传送到一个远程浏览器实例的职能代理服务器。
他的目的是提供一个简便的方法来在多台终端上并行的执行测试任务。

在Selenium服务网格,
一台服务器作为转发器(hub)将JSON格式的测试命令转发到1台或多台注册的节点。
测试任务通过跟转发器(hub)的交互来操作远端浏览器实例。
转发器(hub)维护了一个可供使用的注册服务器列表，也允许我们通过转发器(hub)来控制这些实例。

Selenium服务网格允许我们在多台节点服务器上并行执行测试，
同时也中心化的管理多个浏览器版本，多种浏览器的配置。（以替代传统的基于个人的测试）

Selenium服务网格并不是万能的(silver bullet)。
它能够解决一些通用的代理问题和分布式的问题，但是并不能管理你的硬件，也可能不适合你的一些特殊需求。
