---
title: "服务网格"
chapter: true
weight: 9
---

# Grid

通过将客户端命令发送到远程浏览器的实例,
_Selenium Grid_ 允许在远程计算机 (虚拟或真实) 上执行WebDriver脚本.
它旨在提供一种在多台计算机上并行运行测试的简便方法.

Selenium Grid允许我们在多台计算机上并行运行测试,
并集中管理不同的浏览器版本和浏览器配置
(而不是在每个独立的测试中).

Selenium Grid不是银弹.
它解决了一些常见的委派和分布式问题,
但是例如无法管理基础架构这样的问题,
可能不适合您的特定需求.

**请注意Grid 3不再被支持, Selenium项目建议使用[Grid 4]({{<ref "/grid/grid_4/_index.zh-cn.md">}})**
