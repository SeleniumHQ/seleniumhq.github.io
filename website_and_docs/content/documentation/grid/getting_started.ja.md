---
title: "独自のグリッドを設定する"
linkTitle: "独自のグリッドを設定する"
weight: 2
needsTranslation: true
description: >
  Instructions for a simple Selenium Grid
aliases: [
"/documentation/ja/grid/grid_4/setting_up_your_own_grid/",
"/ja/documentation/grid/setting_up_your_own_grid/"
]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from English to Japanese. 
   Do you speak Japanese? Help us to translate
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
Check the [`RemoteWebDriver` section]({{< ref "../webdriver/drivers/#remote-webdriver" >}}).

To learn more about the different configuration options, go through the sections below.

## Grid roles

Grid is composed by six different [components]({{< ref "components.md" >}}), which gives
you the option to deploy it in different ways.

Depending on your needs, you can start each one of them on its own (Distributed), group
them in Hub & Node, or all in one on a single machine (Standalone).

### Standalone

**Standalone** combines all Grid [components]({{< ref "components.md" >}}) seamlessly 
into one. Running a Grid in **Standalone** mode gives you a fully functional Grid 
with a single command, within a single process. **Standalone** can only run on a 
single machine.

**Standalone** is also the easiest mode to spin up a Selenium Grid. By default, the server 
will listen for `RemoteWebDriver` requests on [http://localhost:4444](http://localhost:4444). 
By default, the server will detect the available drivers that it can use from the System 
[`PATH`]({{< ref "../webdriver/getting_started/install_drivers.md#2-the-path-environment-variable" >}}).

```shell
java -jar selenium-server-<version>.jar standalone
```

After starting successfully the Grid in Standalone mode, point your WebDriver tests 
to [http://localhost:4444](http://localhost:4444).

Common use cases for **Standalone** are:
* Develop or debug tests using `RemoteWebDriver` locally
* Running quick test suites before pushing code
* Have a easy to setup Grid in a CI/CD tool (GitHub Actions, Jenkins, etc...)


### Hub and Node

**Hub and Node** is the most used role because it allows to:
* Combine different machines in a single Grid
  * Machines with different operating systems and/or browser versions, for example
* Have a single entry point to run WebDriver tests in different environments
* Scaling capacity up or down without tearing down the Grid

#### Hub

A Hub is composed by the following [components]({{< ref "components.md" >}}):
Router, Distributor, Session Map, New Session Queue, and Event Bus.

```shell
java -jar selenium-server-<version>.jar hub
```

By default, the server will listen for `RemoteWebDriver` requests on [http://localhost:4444](http://localhost:4444).

#### Node

During startup time, the **Node** will detect the available drivers that it can use from the System 
[`PATH`]({{< ref "../webdriver/getting_started/install_drivers.md#2-the-path-environment-variable" >}}). 

The command below assumes the **Node** is running on the same machine where the **Hub** is running.
```shell
java -jar selenium-server-<version>.jar node
```

##### More than one Node on the same machine

**Node** 1
```shell
java -jar selenium-server-<version>.jar node --port 5555
```

**Node** 2
```shell
java -jar selenium-server-<version>.jar node --port 6666
```

##### Node and Hub on different machines

**Hub** and **Nodes** talk to each other via HTTP and the [**Event Bus**]({{< ref "components.md#event-bus" >}})
(the **Event Bus** lives inside the **Hub**). A **Node** sends a message to the **Hub** via the **Event Bus** to 
start the registration process. When the **Hub** receives the message, reaches out to the **Node** via HTTP to 
confirm its existence.

To successfully register a **Node** to a **Hub**, it is important to expose the **Event Bus** ports (4442 and 4443 by 
default) on the **Hub** machine. This also applies for the **Node** port. With that, both **Hub** and **Node** will
be able to communicate.

If the **Hub** is using the default ports, the `--hub` flag can be used to register the **Node**
```shell
java -jar selenium-server-<version>.jar node --hub http://<hub-ip>:4444
```

When the **Hub** is not using the default ports, the `--publish-events` and `--subscribe-events` flags are needed.

For example, if the **Hub** uses ports `8886`, `8887`, and `8888`
```shell
java -jar selenium-server-<version>.jar hub --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887 --port 8888
```
The **Node** needs to use those ports to register successfully
```shell
java -jar selenium-server-<version>.jar node --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887
```

### Distributed 

When using a Distributed Grid, each component is started separately, and ideally on different machines.

{{% alert color="primary" %}}
It is important to expose all ports properly in order to allow fluent communication between all components.
{{% /alert %}}

1. **Event Bus**: enables internal communication between different Grid components.

Default ports are: `4442`, `4443`, and `5557`.
```shell
java -jar selenium-server-<version>.jar event-bus --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --port 5557
```

2. **New Session Queue**: adds new session requests to a queue, which will be queried by the Distributor

Default port is `5559`.
```shell
java -jar selenium-server-<version>.jar sessionqueue --port 5559
```

3. **Session Map**: maps session IDs to the **Node** where the session is running

Default **Session Map** port is `5556`. **Session Map** interacts with the **Event Bus**. 
```shell
java -jar selenium-server-<version>.jar sessions --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --port 5556
```

4. **Distributor**: queries the **New Session Queue** for new session requests, and assigns them to a **Node** when the capabilities match. **Nodes** register to the **Distributor** the way they register to the **Hub** in a **Hub/Node** Grid.

Default **Distributor** port is `5553`. **Distributor** interacts with **New Session Queue**, **Session Map**, **Event Bus**, and the **Node(s)**.

```shell
java -jar selenium-server-<version>.jar distributor --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --sessions http://<sessions-ip>:5556 --sessionqueue http://<new-session-queue-ip>:5559 --port 5553 --bind-bus false
```

5. **Router**: redirects new session requests to the queue, and redirects running sessions requests to the **Node** running that session.

Default **Router** port is `4444`. **Router** interacts with **New Session Queue**, **Session Map**, and **Distributor**.
```shell
java -jar selenium-server-<version>.jar router --sessions http://<sessions-ip>:5556 --distributor http://<distributor-ip>:5553 --sessionqueue http://<new-session-queue-ip>:5559 --port 4444
```

6. **Node(s)**

Default **Node** port is `5555`.
```shell
java -jar selenium-server-<version>.jar node --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443
```

## Metadata in tests

Add metadata to your tests and consume it via [GraphQL]({{< ref "advanced_features/graphql_support.md" >}})
or visualize parts of it (like `se:name`) through the Selenium Grid UI. 

Metadata can be added by prefixing a capability with `se:`. Here is a quick example in Java showing that.

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

## Grid sizes

Choosing a Grid role depends on what operating systems and browsers need to be supported, 
how many parallel sessions need to be executed, the amount of available machines, and how 
powerful (CPU, RAM) those machines are.

Creating sessions concurrently relies on the available processors to the **Distributor**. 
For example, if a machine has 4 CPUs, the **Distributor** will only be able to create up
to 4 sessions concurrently.

By default, the maximum amount of concurrent sessions a **Node** supports is limited by
the number of CPUs available. For example, if the **Node** machine has 8CPUs, it can run
up to 8 concurrent browser sessions (with the exception of Safari, which is always one).
Additionally, it is expected that each browser session should use around 1GB RAM. 

In general, it is a recommended to have **Nodes** as small as possible. Instead of having
a machine with 32CPUs and 32GB RAM to run 32 concurrent browser sessions, it is better to
have 32 small **Nodes** in order to better isolate processes. With this, if a **Node**
fails, it will do it in an isolated way. Docker is a good tool to achieve this approach.

Note that the default values (1CPU/1GB RAM per browser) are a recommendation and they could
not apply to your context. It is recommended to use them as a reference, but measuring 
performance continuously will help to determine the ideal values for your environment.

Grid sizes are relative to the amount of supported concurrent sessions and amount of 
**Nodes**, and there is no "one size fits all". Sizes mentioned below are rough estimations
thay can vary between different environments. For example a **Hub/Node** with 120 **Nodes**
might work well when the **Hub** has enough resources. Values below are not set on stone,
and feedback is welcomed!

### Small

**Standalone** or **Hub/Node** with 5 or less **Nodes**.

### Middle

**Hub/Node** between 6 and 60 **Nodes**.

### Large

**Hub/Node** between 60 and 100 **Nodes**. **Distributed** with over 100 **Nodes**.

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

## Further reading

* [Components]({{< ref "components.md" >}}): learn how Grid's internal components relate to each other.
* [Configuration]({{< ref "/configuration" >}}): customize your Grid setup.
* [Architecture]({{< ref "architecture.md" >}}): understand key concepts in Grid.
* [Advanced Features]({{< ref "/advanced_features" >}}): explore more possibilities through Grid's features.