---
title: "Selenium Grid快速起步"
linkTitle: "快速起步"
weight: 2
description: >
  一步一步地说明如何运行简单的Selenium Grid.
aliases: [
"/documentation/zh-cn/grid/grid_4/setting_up_your_own_grid/",
"/zh-cn/documentation/grid/setting_up_your_own_grid/"
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Chinese. Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

## Grid roles

Several [components]({{< ref "components_of_a_grid.md" >}}) compose a Selenium Grid. Depending
on your needs, you can start each one of them on its own, or a few at the same time by using a
Grid role.

### Standalone

Standalone is the union of all components, and to the user's eyes, they are executed as one.
A fully functional Grid of one is available after starting it in the Standalone mode.

Standalone is also the easiest mode to spin up a Selenium Grid. By default, the server
will be listening on `http://localhost:4444`, and that's the URL you should point your
`RemoteWebDriver` tests. The server will detect the available drivers that it can use
from the System `PATH`.

```shell
java -jar selenium-server-<version>.jar standalone
```


### Hub and Node(s)

It enables the classic Hub & Node(s) setup. These roles are suitable for small
and middle-sized Grids.

#### Hub

A Hub is the union of the following components:

* Router
* Distributor
* Session Map
* New Session Queue
* Event Bus

```shell
java -jar selenium-server-<version>.jar hub
```

By default, the server will be listening on `http://localhost:4444`, and that's the URL
you should point your `RemoteWebDriver` tests.

#### Node(s)

One or more Nodes can be started in this setup, and the server will detect the available
drivers that it can use from the System `PATH`.

```shell
java -jar selenium-server-<version>.jar node
```

### Distributed

On Distributed mode, each component needs to be started on its own. This setup is more suitable
for large Grids.

{{% alert color="primary" %}}
The startup order of the components is not important, however, we recommend following these
steps when starting a distributed Grid.
{{% /alert %}}


1. Event Bus: serves as a communication path to other Grid components in subsequent steps.

```shell
java -jar selenium-server-<version>.jar  event-bus
```

2. Session Map: responsible for mapping session IDs to the Node where the session is running.

```shell
java -jar selenium-server-<version>.jar sessions
```

3. New Session Queue: adds the new session request to a queue, then the distributor processes it.

```shell
java -jar selenium-server-<version>.jar sessionqueue
```

4. Distributor: Nodes register to it, and assigns a Node for a session request.

```shell
java -jar selenium-server-<version>.jar distributor --sessions http://localhost:5556 --sessionqueue http://localhost:5559 --bind-bus false
```

5. Router: the Grid entrypoint, in charge of redirecting requests to the right component.

```shell
java -jar selenium-server-<version>.jar router --sessions http://localhost:5556 --distributor http://localhost:5553 --sessionqueue http://localhost:5559
```

6. Node(s)

```shell
java -jar selenium-server-<version>.jar node 
```

## Querying Selenium Grid

After starting a Grid, there are mainly two ways of querying its status, through the Grid
UI or via an API call.

The Grid UI can be reached by opening your preferred browser and heading to
[http://localhost:4444](http://localhost:4444).

API calls can be done through the [http://localhost:4444/status](http://localhost:4444/status)
endpoint or using GraphQL:

```shell
curl -X POST -H "Content-Type: application/json" --data '{ "query": "{grid{uri}}" }' -s http://localhost:4444/graphql | jq .
```

{{% pageinfo color="primary" %}}
For simplicity, all command examples shown in this page assume that components are running
locally. More detailed examples and usages can be found in the
[Configuring Components]({{< ref "components.md" >}}) section.
{{% /pageinfo %}}

## Warning

Selenium Grid must be protected from external access using appropriate
firewall permissions.

Failure to protect your Grid could result in one or more of the following occurring:

* You provide open access to your Grid infrastructure
* You allow third parties to access internal web applications and files
* You allow third parties to run custom binaries

See this blog post on [Detectify](//labs.detectify.com), which gives a good
overview of how a publicly exposed Grid could be misused:
[Don't Leave your Grid Wide Open](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/)


## 提醒

Selenium服务网格需要使用合适的防火墙许可来隔离外部访问。

如果不能有效的保护你的服务网格，可能会导致以下问题：

* 提供了一个开发的接口来访问服务网格的基础设施
* 你将会允许第三方来访问内部web服务和文件
* 你将会允许第三方来执行定制的二进制文件

请参考这篇文章[Detectify](//labs.detectify.com), 这里给了一个很好的概要，
关于暴露一个服务网格后会如何被滥用：[Don't Leave your Grid Wide Open](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/).
