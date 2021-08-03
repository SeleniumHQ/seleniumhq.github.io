---
title: "Grid"
linkTitle: "Grid"
weight: 9
description: >
  Want to run tests in parallel across multiple machines? Then, Grid is for you.
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to German. Do you speak German? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

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
