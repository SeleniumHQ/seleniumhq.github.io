---
title: "配置自己的服务网格"
weight: 2
---


## Selenium 4中不同的网格设置模式:
* 单机
* Hub和Node
* 分布式
* Docker

## 单机模式:
新的Selenium服务器Jar
包含运行网格所需的全部内容.
这也是最简单的模式来玩转Selenium网格.
默认情况下, 服务器将监听http://localhost:4444,
这是您应该指向的RemoteWebDriver测试URL.
服务器将从系统路径中检测可用的驱动程序.

```shell
java -jar selenium-server-4.0.0-alpha-7.jar standalone
```

## Hub和Node模式:

### 启动Hub:
```shell
java -jar selenium-server-4.0.0-alpha-7.jar hub
```

### 注册Node:

```shell
java -jar selenium-server-4.0.0-alpha-7.jar node --detect-drivers true
```

### 查询Selenium网格:

In Selenium 4, we've added GraphQL, a new way to query the necessary data easily and get exactly the same.

```shell
curl -X POST -H "Content-Type: application/json" --data '{ "query": "{grid{uri}}" }' -s http://localhost:4444/graphql | jq .
```
<br><br>

## 分布式模式:

* 第1步: 首先, 启动事件总线,
  它在后续步骤中充当到其他网格组件的通信路径.

    ```shell
    java -jar selenium-server-4.0.0-alpha-7.jar  event-bus
    ```

* 第2步: 启动会话映射,
  其负责将会话ID到会话运行节点的映射.

    ```shell
        java -jar selenium-server-4.0.0-alpha-7.jar sessions
    ```

* 第3步: 启动新的会话队列,
  它将新的会话请求添加到本地队列中.
  分发服务器从队列中接收请求.

    ```shell
        java -jar selenium-server-4.0.0-alpha-7.jar sessionqueuer
    ```

* 第4步: 启动分配器.
  所有节点都附加到发进程上, 作为其组成的一部分,
  负责在会话的创建时分配节点.

    ```shell
        java -jar selenium-server-4.0.0-alpha-7.jar distributor --sessions http://localhost:5556 --sessionqueuer http://localhost:5559 --bind-bus false
    ```

* 第5步:下一步是启动路由器,
  你将暴露给网络一个地址.

    ```shell
        java -jar selenium-server-4.0.0-alpha-7.jar router --sessions http://localhost:5556 --distributor http://localhost:5553 --sessionqueuer http://localhost:5559
    ```

* 第6步: 最终，添加节点.

    ```shell
        java -jar selenium-server-4.0.0-alpha-7.jar node --detect-drivers true
    ```

## 通过Docker映像启动独立网格

您可以通过以下命令启动一个节点:

```shell
    java -jar selenium-server-4.0.0-alpha-7.jar node -D selenium/standalone-firefox:latest '{"browserName": "firefox"}'
```

您可以启动Selenium服务器
并将其委托给docker以创建新实例:

```shell
     java -jar selenium-server-4.0.0-alpha-7.jar standalone -D selenium/standalone-firefox:latest '{"browserName": "firefox"}' --detect-drivers false
```

## 提醒

Selenium服务网格需要使用合适的防火墙许可来隔离外部访问。

如果不能有效的保护你的服务网格，可能会导致以下问题：

* 提供了一个开发的接口来访问服务网格的基础设施
* 你将会允许第三方来访问内部web服务和文件
* 你将会允许第三方来执行定制的二进制文件

请参考这篇文章[Detectify](//labs.detectify.com), 这里给了一个很好的概要，
关于暴露一个服务网格后会如何被滥用：[Don't Leave your Grid Wide Open](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/).


## Docker Selenium
[Docker](//www.docker.com/)提供了一个方便的途径来在容器中构建一个可扩张的Selenium服务网格基础设置，
容器是软件的标准单元，包含了所有执行应用程序需要的东西，包括所有的依赖，它以一种可靠的，可以复制的方法来在不同的终端机上运行。

Selenium项目维护了一组Docker镜像，你可以下载并运行来快速的获得一个可用的服务网格。
Firefox和Chrome都提供了可用的镜像。你可以在[Docker Selenium](//github.com/SeleniumHQ/docker-selenium) 找到关于如何启动服务网格的详细信息.
