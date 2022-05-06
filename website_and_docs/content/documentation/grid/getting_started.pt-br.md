---
title: "Configurando a sua"
linkTitle: "Configurando a sua"
weight: 2
needsTranslation: true
description: >
  Instructions, step by step, showing how to run a simple Selenium Grid.
aliases: [
"/documentation/pt-br/grid/grid_4/setting_up_your_own_grid/",
"/pt-br/documentation/grid/setting_up_your_own_grid/"
]
---

## Grid roles

Several [components]({{< ref "components.md" >}}) compose a Selenium Grid. Depending
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

1. Event Bus: serve como um caminho de comunicação para outros componentes da rede nas etapas subsequentes.

```shell
java -jar selenium-server-<version>.jar  event-bus
```

2. Session Map: responsável por mapear os IDs da sessão para o nó em que a sessão está sendo executada:

```shell
java -jar selenium-server-<version>.jar sessions
```

3. New Session Queue: Inicie o novo enfileirador de sessão, ele adiciona a nova solicitação de sessão a uma fila local. O distribuidor atende o pedido da fila.

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

## Running tests

To run tests after starting successfully Selenium Grid, you can use a `RemoteWebDriver`.
Head to the [`RemoteWebDriver` section]({{< ref "../webdriver/remote_webdriver.md" >}}) for
more details.

### Tests metadata

You can add metadata to your tests and consume it via [GraphQL]({{< ref "advanced_features/graphql_support.md" >}})
or visualize parts of it through the Selenium Grid UI. Metadata can be added by prefixing the metadata with `se:`.

Here is a quick example in Java showing how to do that.

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
[Configuring Components]({{< ref "components.md" >}}) section.
{{% /pageinfo %}}

## Aviso

A Selenium Grid deve ser protegida do acesso externo usando
permissões de firewall.

A falha em proteger sua rede pode resultar em um ou mais dos seguintes eventos:

* Você fornece acesso aberto à sua infraestrutura de rede
* Você permite que terceiros acessem aplicativos e arquivos internos da web
* Você permite que terceiros executem binários personalizados

Veja esta postagem do blog em [Detectify](//labs.detectify.com), que dá uma boa
visão geral de como uma rede exposta publicamente pode ser mal utilizada:
[Não deixe sua grade totalmente aberta](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/).
