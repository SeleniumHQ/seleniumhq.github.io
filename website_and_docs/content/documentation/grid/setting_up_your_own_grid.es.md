---
title: "Setting up your own"
linkTitle: "Setting up your own"
weight: 2
description: >
  Instructions, step by step, showing how to run a simple Selenium Grid.
aliases: ["/documentation/es/grid/grid_4/setting_up_your_own_grid/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to Spanish. Do you speak Spanish? Help us to translate
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
[Advanced Features]({{< ref "/advanced_features.md" >}}) section.
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
