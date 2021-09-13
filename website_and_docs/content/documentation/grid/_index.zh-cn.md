---
title: "Grid"
linkTitle: "Grid"
weight: 9
description: >
  要在多台计算机上并行运行测试吗? 那么, Grid正是为你准备的.
aliases: 
        [
          "/documentation/zh-cn/selenium_installation/installing_standalone_server/",
          "/documentation/zh-cn/grid/",
          "/documentation/zh-cn/grid/grid_4/",
          "/documentation/zh-cn/grid/purposes_and_main_functionalities/"
        ]
---


通过将客户端命令发送到远程浏览器的实例,
Selenium Grid 允许在远程计算机 (虚拟或真实) 上执行WebDriver脚本.
它旨在提供一种在多台计算机上并行运行测试的简便方法.

Selenium Grid允许我们在多台计算机上并行运行测试,
并集中管理不同的浏览器版本和浏览器配置
(而不是在每个独立的测试中).

Selenium Grid不是银弹.
它解决了一些常见的委派和分布式问题,
但是例如无法管理基础架构这样的问题,
可能不适合您的特定需求.

## 目的和主要功能

* 为所有的测试提供统一的入口
* 管理和控制运行着浏览器的节点/环境
* 扩展
* 并行测试
* 跨平台(操作系统)测试
* 负载测试


_Selenium Grid 4_ 是全新的实现, 
并且不共享之前版本的代码库.

Grid 4有一种实现可以利用许多新技术来促进扩展, 
同时仍然允许本地执行.

关于Grid 4组件的所有详细信息, 
了解其工作原理以及如何设置自己的组件, 
请浏览以下章节.
