---
title: "Selenium Grid快速起步"
linkTitle: "快速起步"
weight: 2
needsTranslation: true
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
   Page being translated from English to Chinese. 
   Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

## Quick start

1. Prerequisites
    * Java 11 or higher installed
    * Browser(s) installed
    * Browser driver(s) [installed and on the `PATH`]({{< ref "../webdriver/getting_started/install_drivers.md#2-the-path-environment-variable" >}})
    * Download the Selenium Server jar file from the [latest release](https://github.com/SeleniumHQ/selenium/releases/latest)
1. Start the Grid
    * `java -jar selenium-server-<version>.jar standalone`
1. Point* your WebDriver tests to [http://localhost:4444](http://localhost:4444)
1. (Optional) Check running tests and available capabilities by opening your browser at [http://localhost:4444](http://localhost:4444)

*Wondering how to point your tests to [http://localhost:4444](http://localhost:4444)? 
Check the [`RemoteWebDriver` section]({{< ref "../webdriver/remote_webdriver" >}}).

To learn more about the different configuration options, go through the sections below.

## Grid roles

Several [components]({{< ref "components.md" >}}) compose a Selenium Grid. Depending 
on your needs, you can start each one of them on its own, or a few at the same time by using a
Grid role.

### Standalone

**Standalone** combines all Grid components seamlessly into one. 
Running a Grid in **Standalone** mode gives you a fully functional Grid with a single command, 
within a single process.

**Standalone** is also the easiest mode to spin up a Selenium Grid. By default, the server 
will listen for `RemoteWebDriver` requests on `http://localhost:4444/wd/hub`. By default, the
server will detect the available drivers that it can use from the System `PATH`.

```shell
java -jar selenium-server-<version>.jar standalone
```
You can still pass a `--config` file, or pass specific 
[configuration flags on the CLI]({{< ref "cli_options.md" >}})

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

By default, the server will listen for `RemoteWebDriver` requests on `http://localhost:4444/wd/hub`.

#### Node

One or more **Nodes** can be started in this setup, and the server will detect the available 
drivers that it can use from the System `PATH`. 

```shell
java -jar selenium-server-<version>.jar node
```

### Distributed 

When using a distributed Grid, each component needs is started separately. This setup is more suitable
for large Grids.

{{% alert color="primary" %}}
The startup order of the components is not important, however, we recommend following these
steps when starting a distributed Grid.
{{% /alert %}}


1. **Event Bus**: a communication path to other Grid components

```shell
java -jar selenium-server-<version>.jar  event-bus
```

2. **Session Map**: maps session IDs to the **Node** where the session is running

```shell
java -jar selenium-server-<version>.jar sessions
```

3. **New Session Queue**: adds the new session request to a queue for the distributor to processes

```shell
java -jar selenium-server-<version>.jar sessionqueue
```

4. **Distributor**: assigns a **Node** for a session request. Other **Nodes** register to the **Distributor**
the way they would have registered to the **Hub** in a non-distributed Grid

```shell
java -jar selenium-server-<version>.jar distributor --sessions http://localhost:5556 --sessionqueue http://localhost:5559 --bind-bus false
```

5. **Router**: Redirects requests to the right component

```shell
java -jar selenium-server-<version>.jar router --sessions http://localhost:5556 --distributor http://localhost:5553 --sessionqueue http://localhost:5559
```

6. Node(s)

```shell
java -jar selenium-server-<version>.jar node 
```

## Metadata in tests

You can add metadata to your tests and consume it via [GraphQL]({{< ref "advanced_features/graphql_support.md" >}})
or visualize parts of it through the Selenium Grid UI. Metadata can be added by prefixing the metadata with `se:`.

Here is a quick example in Java showing that.

```java
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setCapability("browserVersion", "100");
chromeOptions.setCapability("platformName", "Windows");
// Showing a test name instead of the session id in the Grid UI
chromeOptions.setCapability("se:name", "My simple test"); 
// Other type of metadata can be seen in the Grid UI by clicking on the 
// session info or via GraphQL
chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value"); 
WebDriver driver = new RemoteWebDriver(new URL("http://gridUrl:4444"), chromeOptions);
driver.get("http://www.google.com");
driver.quit();
```

## Querying Selenium Grid

After starting a Grid, there are mainly two ways of querying its status, through the Grid 
UI or via an API call.

The Grid UI can be reached by opening your preferred browser and heading to 
[http://localhost:4444](http://localhost:4444).

API calls can be done through the [http://localhost:4444/status](http://localhost:4444/status)
endpoint or using [GraphQL]({{< ref "advanced_features/graphql_support.md" >}})

{{% pageinfo color="primary" %}}
For simplicity, all command examples shown in this page assume that components are running
locally. More detailed examples and usages can be found in the
[Configuring Components]({{< ref "/configuration" >}}) section.
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
