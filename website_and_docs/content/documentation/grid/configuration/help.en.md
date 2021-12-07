---
title: "Configuration help"
linkTitle: "Help"
weight: 1
description: Get information about all the available options to configure Grid.
aliases: [
"/documentation/en/grid/grid_4/configuring_components/config_help/",
"/documentation/grid/configuring_components/config_help/"
]
---

{{% pageinfo color="primary" %}}
The help commands display information based on the current code implementation.
Hence, it will provide accurate information in case the documentation is not updated.
It is the easiest way to learn about Grid 4 configuration for any new version.
{{% /pageinfo %}}

## Info Command

The info command provides detailed docs on the following topics:
* Configuring Selenium
* Security
* Session Map setup
* Tracing

### Config help 

Quick config help and overview is provided by running:

```shell
java -jar selenium-server-<version>.jar info config
```

### Security

To get details on setting up the Grid servers for secure communication and node registration:

```shell
java -jar selenium-server-<version>.jar info security
```

### Session Map setup

By default, Grid uses a local session map to store session information. 
Grid supports additional storage options like Redis and JDBC - SQL supported databases. 
To set up different session storage, use the following command to get setup steps:

```shell
java -jar selenium-server-<version>.jar info sessionmap
```

### Setting up tracing with OpenTelemetry and Jaeger

By default, tracing is enabled. To export traces and visualize them via Jaeger, use the following command for instructions:

```shell
java -jar selenium-server-<version>.jar info tracing
```

## List the Selenium Grid commands  
 

```shell
java -jar selenium-server-<version>.jar --config-help
```

It will show all the available commands and description for each one.

## Component help commands

Pass --help config option after the Selenium role to get component-specific config information.

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

### New Session Queue

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



