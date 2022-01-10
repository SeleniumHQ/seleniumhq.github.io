---
title: "配置自己的服务网格"
linkTitle: "配置自己的服务网格"
weight: 4
description: >
    Quick start guide for setting up Grid 3.
aliases: [
"/documentation/zh-cn/grid/grid_3/setting_up_your_own_grid/",
"/zh-cn/documentation/legacy/grid_3/setting_up_your_own_grid/"
]
---

使用Selenium网格，
你需要维护你自己的基础设置来作为节点使用，
这将是一个繁重的紧张的工作，很多组织使用IaaS供应商比如Amazon EC2或者Google来提供这些基础设施。

使用Sauce Labs或者Testing Bot这类提供了Selenium网格作为云服务的供应商也是一个选择。
当然，在你自己的硬件群运行节点也是可行的。
这一章会深入探讨如何用你自己的基础设施来运行你的服务网格，

## 快速开始

这个例子会向你展示如何开始Selenium 2服务网格的转发器(hub),
然后注册WebDriver节点和Selenium 1 RC节点。
我们也会向你展示如何使用Java来使用Selenium服务网格。
这个例子里转发器和节点被运行在了同一台终端机上，当然你也可以服务selenium-server-standalone到
多台终端机。

`selenium-server-standalone` 包含了运行网格所需要的转发器(hub),WebDriver和legacy RC needed, _ant_已经不是必须的了.
你可以在[https://selenium.dev/downloads/](https://selenium.dev/downloads/).下载
`selenium-server-standalone.jar`

### 第一步: 启动转发器(hub)

转发器(hub)是接受测试请求并分发到合适的节点的中心点。
分发是基于节点的能力的，这就意味着一个有特定需求的测试仅会被分发到能提供这个需求的节点上。

因为一个测试所期望的能力，就如字面意思，期望，并不代表转发器(hub)能够找到一个真正满足所有期望的节点。

打开命令行窗口，来到存放`selenium-server-standalone.jar`文件的地方。
启动转发器(hub)并传入`-role hub`作为参数来启动一个独立的服务：

```shell
java -jar selenium-server-standalone.jar -role hub
```

转发器(hub)默认会监听4444端口，你也可以通过打开浏览器访问[http://localhost:4444/grid/console](http://localhost:4444/grid/console)来查看转发器(hub)的状态。

如果需要改变默认端口，你可以添加`-port`加上一个数字作为参数来代表你期望监听的端口，
同时，所有其他的可选参数都可以在下面这个JSON配置文件里找到。

你已经在上面获得了一个简单命令，当然如果你希望一些更高级的配置，
方便起见，你也可以指定一个JSON格式的配置文件来配置并启动你的转发器(hub)。
你可以这么做：

```shell
java -jar selenium-server-standalone.jar -role hub -hubConfig hubConfig.json -debug
```

下面你可以看到一个配置文件`hubConfig.json`的例子。
我们会在第二步深入探讨怎么来提供节点配置文件。

```json
{
  "_comment" : "Configuration for Hub - hubConfig.json",
  "host": ip,
  "maxSession": 5,
  "port": 4444,
  "cleanupCycle": 5000,
  "timeout": 300000,
  "newSessionWaitTimeout": -1,
  "servlets": [],
  "prioritizer": null,
  "capabilityMatcher": "org.openqa.grid.internal.utils.DefaultCapabilityMatcher",
  "throwOnCapabilityNotPresent": true,
  "nodePolling": 180000,
  "platform": "WINDOWS"}
```


### 第二部: 启动节点

无论你期望你的服务网格使用新的WebDriver的功能，还是Selenium 1 RC的功能，或者2者皆有。
你都只需要使用`selenium-server-standalone.jar`来启动节点。

```shell
java -jar selenium-server-standalone.jar -role node -hub http://localhost:4444
```

如果不通过`-port`来指定端口，会选一个可用端口。你也可以在一个终端机上运行多个节点，
但是如果你这么做了，你需要意识到当你的测试使用截屏会引发你的系统内存资源和问题。

#### 配置节点的可选参数

正如前面提到的，作为一个向下兼容，"wd"和”rc”这两个角色都是节点角色的合法的自己。
当节点同时允许RC饿WebDriver的远程链接时，这些角色限制了远程连接使用的API。

通过在命令行中设置JVM属性(_在-jar参数前_使用`-D`参数)，会被传递到节点里：
`-Dwebdriver.chrome.driver=chromedriver.exe`

#### 使用JSON配置节点

你也可以使用JSON配置文件来启动服务网格节点

```shell
java -Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone.jar -role node -nodeConfig node1Config.json
```
这里是一个配置文件`nodeConfig.json`的例子:

```json
{
  "capabilities": [
    {
      "browserName": "firefox",
      "acceptSslCerts": true,
      "javascriptEnabled": true,
      "takesScreenshot": false,
      "firefox_profile": "",
      "browser-version": "27",
      "platform": "WINDOWS",
      "maxInstances": 5,
      "firefox_binary": "",
      "cleanSession": true
    },
    {
      "browserName": "chrome",
      "maxInstances": 5,
      "platform": "WINDOWS",
      "webdriver.chrome.driver": "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe"
    },
    {
      "browserName": "internet explorer",
      "maxInstances": 1,
      "platform": "WINDOWS",
      "webdriver.ie.driver": "C:/Program Files (x86)/Internet Explorer/iexplore.exe"
    }
  ],
  "configuration": {
    "_comment" : "Configuration for Node",
    "cleanUpCycle": 2000,
    "timeout": 30000,
    "proxy": "org.openqa.grid.selenium.proxy.WebDriverRemoteProxy",
    "port": 5555,
    "host": ip,
    "register": true,
    "hubPort": 4444,
    "maxSession": 5
  }
}
```

有关于`-host`参数的注解

无论是转发器还是节点，如果不指定`-host`参数，会默认使用`0.0.0.0`，
这么做会绑定终端机的所有的公共IPv4接口。如果你有一些特殊网络配置或者一些组件创建的网络接口，
建议设置`-host`参数，来使你的转发器或节点能够被其他终端机访问。

#### 指定端口

转发器默认使用TCP/IP端口4444.如果你希望改端口，请用上面提到的配置方法。

## 故障排查

### 使用日志文件

如果需要进行高级故障排查你可以指定一个日志文件来记录系统信息。
启动Selenium服务网格的转发器(hub)或节点的时候使用-log参数。下面是一个例子：

```shell
java -jar selenium-server-standalone.jar -role hub -log log.txt
```

使用你习惯的文本编辑器来打开日志文件(例子里用的log.txt)，查找"ERROR"日志来定位你的问题。

### 使用 `-debug` 参数

同时，你也可以通过使用`-debug`来向控制台打印debug日志。
启动Selenium服务网格的转发器(hub)或节点的时候使用`-debug`参数。下面是一个例子：

```shell
java -jar selenium-server-standalone.jar -role hub -debug
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

### 前提
创建服务网格的唯一的前提是安装了Docker并能正常运行
[Install Docker](//www.docker.com/products/docker-desktop).
