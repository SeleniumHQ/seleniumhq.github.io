---
title: "什么时候应该使用Grid"
linkTitle: "适用性"
weight: 4
description: >
  Is Grid right for you?
aliases: [
"/documentation/zh-cn/grid/when_to_use_grid/",
"/zh-cn/documentation/grid/when_to_use_grid"
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language d-4"></i> 
   Page being translated from English to Chinese. 
   Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

什么情况下可以使用 `Selenium Grid` ？

* 想要在不同的浏览器类型、浏览器版本和操作系统上并行运行测试时
* 想要缩短执行测试案例所需的时间

`Selenium Grid` 可以并行地在多台计算机（称为节点）上运行测试案例。对于大型和长时间运行的测试案例，这可以节省几分钟、几小时甚至几天的时间。

这有效的缩短了测试结果的反馈时间，使得在测试的应用程序发生变化时能够更快地得到测试结果。

`Grid` 可以并行地运行测试，支持多种不同的浏览器类型，并且可以同时运行多个相同浏览器的实例。

举个例子，假设一个拥有六个节点的Grid。第一台计算机拥有Firefox的最新版本，第二台拥有Firefox的上一个版本，第三台运行最新版Chrome，而其余三台机器是Mac Mini，允许在最新版本的Safari上并行运行三个测试。

执行时间可以用一个简单的公式来表示：

```测试次数 × 平均测试时间 / 节点数 = 总执行时间```

       15      *       45s        /        1        =      11m 15s   // Without Grid
       15      *       45s        /        5        =      2m 15s    // Grid with 5 Nodes
       15      *       45s        /        15       =      45s       // Grid with 15 Nodes
      100      *       120s       /        15       =      13m 20s   // Would take over 3 hours without Grid

在测试案例执行时，`Grid` 会按照测试配置将测试分配到相应的浏览器上运行。

即使对于比较复杂的 `Selenium` 测试案例，这样的配置也可以极大地加快执行时间。

`Selenium Grid` 是 `Selenium` 项目中的重要组成部分，由同一团队的核心Selenium开发人员并行维护。由于意识到测试执行速度的重要性，`Grid` 自设计之初就成为 `Selenium` 项目的关键部分。
