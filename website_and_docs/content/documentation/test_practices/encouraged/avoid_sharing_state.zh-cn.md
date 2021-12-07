---
title: "避免共享状态"
linkTitle: "避免共享状态"
weight: 8
aliases: [
"/documentation/zh-cn/guidelines_and_recommendations/avoid_sharing_state/",
"/zh-cn/documentation/guidelines/avoid_sharing_state/"
]
---

尽管在多个地方都提到过, 但这点仍值得被再次提及. 确保测试相互隔离.

* 不要共享测试数据. 
想象一下有几个测试, 每个测试都会在选择操作执行之前查询数据库中的有效订单. 
如果两个测试采用相同的顺序, 则很可能会出现意外行为.

* 清理应用程序中过时的数据, 这些数据可能会被其他测试. 
例如无效的订单记录.

* 每次测试都创建一个新的WebDriver实例. 
这在确保测试隔离的同时可以保障并行化更为简单.
