---
title: "Config help"
linkTitle: "Config help"
weight: 1
aliases: ["/documentation/de/grid/grid_4/configuring_components/config_help/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to German. Do you speak German? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

The help commands display information based on the current code implementation.
Hence, it will provide accurate information in case the documentation is not updated. 
It is the easiest way to learn about Grid 4 configuration for any new version.

## Info Command

The info command provides detailed docs on the following topics:
* Configuring Selenium
* Security
* Session Map setup
* Tracing

### Config help 

Quick config help and overview is provided by running:

```shell
java -jar selenium-server-4.0.0-alpha-7.jar info config
```

### Security

To get details on setting up the Grid servers for secure communication and node registration:

```shell
java -jar selenium-server-4.0.0-alpha-7.jar info security
```

### Session Map setup

By default, Grid uses a local session map to store session information. 
Grid supports additional storage options like Redis and JDBC - SQL supported databases. 
To set up different session storage, use the following command to get setup steps:

```shell
java -jar selenium-server-4.0.0-alpha-7.jar info sessionmap
```

### Setting up tracing with OpenTelemetry and Jaeger

By default, tracing is enabled. To export traces and visualize them via Jaeger, use the following command for instructions:

```shell
java -jar selenium-server-4.0.0-alpha-7.jar info tracing
```

## List the Selenium Grid commands  
 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar --config-help
```

It will show all the available commands and description for each one.

## Component help commands

Pass --help config option after the Selenium role to get component-specific config information.

### Standalone 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar standalone --help
```
### Hub 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar hub --help
```

### Sessions 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar sessions --help
```

### New Session Queuer

```shell
java -jar selenium-server-4.0.0-alpha-7.jar sessionqueuer --help
```

### Distributor 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar distributor --help
```

### Router 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar router --help
```

### Node 

```shell
java -jar selenium-server-4.0.0-alpha-7.jar node --help
```



