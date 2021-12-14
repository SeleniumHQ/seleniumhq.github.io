---
title: "配置帮助"
linkTitle: "帮助"
weight: 1
description: 获取有关配置网格的所有可用选项的信息.
aliases: [
"/documentation/zh-cn/grid/grid_4/configuring_components/config_help/",
"/zh-cn/documentation/grid/configuring_components/config_help/"
]
---

{{% pageinfo color="primary" %}}
Help命令显示基于当前代码实现的信息.
因此, 如果文档没有更新, 它将提供准确的信息.
这是了解任何新版本Grid4配置的最便捷方法.
{{% /pageinfo %}}


## 信息命令

Info命令提供以下主题的详细文档:
* 配置Selenium
* 安全
* 会话表配置
* 追踪

### 配置帮助 

通过运行以下命令快速获取配置帮助:

```shell
java -jar selenium-server-<version>.jar info config
```

### 安全

获取构建网格服务器的详细信息, 
用于安全通信和节点注册.


```shell
java -jar selenium-server-<version>.jar info security
```

### 会话表配置

默认情况下, 
网格使用本地会话表来存储会话信息. 
网格支持额外的存储选项, 
比如Redis和JDBC-SQL支持的数据库. 
要设置不同的会话存储, 
请使用以下命令获取设置步骤:

```shell
java -jar selenium-server-<version>.jar info sessionmap
```

### 基于OpenTelemetry和Jaeger的追踪配置

默认情况下, 追踪是启用的. 
要通过Jaeger导出追踪并将其可视化, 
请使用以下命令进行说明:

```shell
java -jar selenium-server-<version>.jar info tracing
```

## 列出Selenium网格的命令  
 

```shell
java -jar selenium-server-<version>.jar --config-help
```

上述命令将显示所有可用的命令及其描述.

## 组件帮助命令

在Selenium后面键入--help的配置选项, 
以获取特定组件的配置信息.

### Standalone 

```shell
java -jar selenium-server-<version>.jar standalone --help
```
### Hub 

```shell
java -jar selenium-server-<version>.jar hub --help
```

### Sessions 

```shell
java -jar selenium-server-<version>.jar sessions --help
```

### 队列器

```shell
java -jar selenium-server-<version>.jar sessionqueue --help
```

### Distributor 

```shell
java -jar selenium-server-<version>.jar distributor --help
```

### Router 

```shell
java -jar selenium-server-<version>.jar router --help
```

### Node 

```shell
java -jar selenium-server-<version>.jar node --help
```
