---
title: "驱动会话"
linkTitle: "驱动"
weight: 3
---

启动和停止会话, 用于打开和关闭浏览器.

## 创建会话

创建会话对应于W3C的命令 [新建会话](https://w3c.github.io/webdriver/#new-session) 

会话是通过初始化新的驱动类对象自动创建的.

每种语言都允许使用来自这些类 (或等效类) 之一的参数创建会话:

* [选项]({{< ref "options.md" >}}) 描述您想要的会话类型; 默认值为local，但是对于remote则是必须设置的
* 各种形式的 [命令执行器]({{< ref "executors.md" >}}) (实现因语言而异)
* [监听器]({{< ref "listeners.md" >}})

### 本地驱动

启动本地驱动的首要唯一参数
包括在本地计算机上有关启动所需驱动服务的信息.

* [服务]({{< ref "service.md" >}}) 对象仅适用于本地驱动，并提供有关浏览器驱动的信息

{{< alert-code >}}
显示具有多个参数的启动本地驱动方式.
{{< /alert-code >}}

### 远程驱动

用于启动远程驱动的首要唯一参数包括有关在何处执行代码的信息.
请浏览 [远程驱动章节]({{< ref "remote_webdriver.md" >}})中的详细信息


## 退出会话

退出会话对应于W3C的命令 [删除会话](https://w3c.github.io/webdriver/#delete-session).

重要提示: `quit` 方法与 `close` 方法不同,
建议始终使用 `quit` 来结束会话

{{< alert-code >}}
显示退出会话.
{{< /alert-code >}}
