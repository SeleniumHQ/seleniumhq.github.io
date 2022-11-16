---
title: "Configurando a sua"
linkTitle: "Configurando a sua"
weight: 2
needsTranslation: true
description: >
  Instruções para criar uma Selenium Grid simples
aliases: [
"/documentation/pt-br/grid/grid_4/setting_up_your_own_grid/",
"/pt-br/documentation/grid/setting_up_your_own_grid/"
]
---

## Início rápido

1. Pré-requisitos
    * Java 11 ou superior instalado
    * Navegador(es) instalados
    * Drivers do(s) navegador(es)
      * Se usar o Selenium 4.6, o Selenium Manager irá configurar os navegadores Chrome, Firefox e Edge [se não forem encontrados no `PATH`]({{< ref "../webdriver/getting_started/install_drivers.md#1-selenium-manager-beta" >}}).
    * Obter o ficheiro Selenium Server Jar a partir da [última release](https://github.com/SeleniumHQ/selenium/releases/latest)
1. Iniciar a Grid
    * `java -jar selenium-server-<version>.jar standalone`
1. Aponte* os seus testes WebDriver para [http://localhost:4444](http://localhost:4444)
1. (Opcional) Verifique os testes que estão em execução abrindo o navegador em [http://localhost:4444](http://localhost:4444)

*Se quer saber como direcionar os seus testes para [http://localhost:4444](http://localhost:4444), veja a secção [`RemoteWebDriver`]({{< ref "../webdriver/drivers/#remote-webdriver" >}}).

Para aprender mais sobre as diferentes opções de configuração, veja as secções seguintes.

## Grid roles

A Grid é composta de seis [componentes]({{< ref "components.md" >}}) diferentes, o que permite ser
instalada de várias formas.

Dependendo das necessidades, podemos iniciar cada um dos componentes (Distribuido) ou agrupar no formato
Hub e Node ou ainda numa única máquina (Standalone).

### Standalone

**Standalone** combina todos os [componentes]({{< ref "components.md" >}}) num só sitio.
Executar uma Grid em modo **Standalone** permite uma Grid totalmente funcionar com um único
comando, num único processo. **Standalone** só funcionará numa única máquina.

**Standalone** é também a forma mais simples de colocar uma Selenium Grid em funcionamento.
Por omissão, o servidor irá escutar por pedidos `RemoteWebDriver` em [http://localhost:4444](http://localhost:4444).
O servidor irá também detectar os drivers disponíveis no 
[`PATH`]({{< ref "../webdriver/getting_started/install_drivers.md#2-the-path-environment-variable" >}}).

```shell
java -jar selenium-server-<version>.jar standalone
```

Após iniciar a Selenium Grid no modo Standalone, aponte os seus WebDriver tests para
[http://localhost:4444](http://localhost:4444).

Alguns casos de uso para **Standalone** são:
* Desenvolver ou debugar testes usando `RemoteWebDriver` localmente
* Executar baterias de testes rapidamente antes de fazer commit de código
* Ter uma Grid simples de montar numa ferramenta CI/CD (GitHub Actions, Jenkins, etc...)

### Hub e Node

**Hub e Node** é uma das formas mais usadas porque permite:
* Combinar máquinas diferentes na mesma Grid
  * Máquinas com sistemas operativos diferentes e/ou versões de navegadores diferentes
* Ter um ponto único de entrada para executar testes WebDriver em ambientes diferentes
* Escalar a capacidade para cima ou para baixo sem ter que parar a Grid

#### Hub

O Hub é composto pelos seguintes [componentes]({{< ref "components.md" >}}):
Router, Distributor, Session Map, New Session Queue, e Event Bus.

```shell
java -jar selenium-server-<version>.jar hub
```

Por omissão, o servidor irá estar à escuta por pedidos de sessão `RemoteWebDriver` em [http://localhost:4444](http://localhost:4444).

#### Node

Ao iniciar, o **Node** irá detectar os drivers disponíveis através do 
[`PATH`]({{< ref "../webdriver/getting_started/install_drivers.md#2-the-path-environment-variable" >}}). 

O comando exemplo seguinte assume que o **Node** está a executar na mesma máquina onde o **Hub** está em execução.
```shell
java -jar selenium-server-<version>.jar node
```

##### Mais do que um Node na mesma máquina

**Node** 1
```shell
java -jar selenium-server-<version>.jar node --port 5555
```

**Node** 2
```shell
java -jar selenium-server-<version>.jar node --port 6666
```

##### Node e Hub em máquinas diferentes

A comunicação entre **Hub** e **Nodes** ocorre via HTTP. Para iniciar o processo de registo, o **Node** envia
uma mensagem para o **Hub** através do [**Event Bus**]({{< ref "components.md#event-bus" >}}) 
(o **Event Bus** reside dentro do **Hub**). Quando o **Hub** recebe a mensagem, tenta comunicar com o **Node**
para confirmar a sua existencia.

Para que um **Node** se consiga registar no **Hub**, é importante que as portas do **Event Bus** sejam expostas 
na máquina **Hub**. As portas por omissão são 4442 e 4443 para o **Event Bus** e 4444 para o **Hub**.

Se o **Hub** estiver a usar as portas por omissão, pode usar a flag `--hub` para registar o **Node**
```shell
java -jar selenium-server-<version>.jar node --hub http://<hub-ip>:4444
```

Quando o **Hub** não estiver a usar as portas por omissão, necessita usar as flags`--publish-events` e `--subscribe-events`.

Por exemplo, se o **Hub** usar as portas`8886`, `8887`, e `8888`
```shell
java -jar selenium-server-<version>.jar hub --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887 --port 8888
```
O **Node** necessita de especificar as portas para conseguir registar-se com sucesso
```shell
java -jar selenium-server-<version>.jar node --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887
```

### Distribuida

Quando usar uma Grid distribuida, cada componente é iniciado separadamente e preferencialmente em máquinas diferentes.

{{% alert color="primary" %}}
É de extrema importância expor todas as portas necessárias de forma a que a comunicação flua correctamente entre todos os componentes.
{{% /alert %}}

1. **Event Bus**: permite comunicação interna entre os diferentes componentes da Grid.

As portas por omissão são: `4442`, `4443`, and `5557`.
```shell
java -jar selenium-server-<version>.jar event-bus --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --port 5557
```

2. **New Session Queue**: adiciona novos pedidos de sessão a uma queue, que serão consultadas pelo Distributor

A porta por omissão é `5559`.
```shell
java -jar selenium-server-<version>.jar sessionqueue --port 5559
```

3. **Session Map**: estabelece um mapa entre id de sessão e o **Node** onde a sessão está a executar

A porta por omissão é `5556`. **Session Map** interage com o **Event Bus**. 
```shell
java -jar selenium-server-<version>.jar sessions --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --port 5556
```

4. **Distributor**: consulta **New Session Queue** para novos pedidos de sessão, que entrega ao um **Node** quando encontra um capacidade 
correspondente. **Nodes** registam-se no **Distributor** da mesma forma como numa Grid do tipo **Hub/Node**.

A porta por omissão é `5553`. **Distributor** interage com **New Session Queue**, **Session Map**, **Event Bus**, e **Node(s)**.

```shell
java -jar selenium-server-<version>.jar distributor --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443 --sessions http://<sessions-ip>:5556 --sessionqueue http://<new-session-queue-ip>:5559 --port 5553 --bind-bus false
```

5. **Router**: redirecciona novos pedidos de sessão para a queue, e redirecciona pedidos de sessões para o **Node** que estiver a executar a sessão.

A porta por omissão é `4444`. **Router** interage com **New Session Queue**, **Session Map**, e **Distributor**.
```shell
java -jar selenium-server-<version>.jar router --sessions http://<sessions-ip>:5556 --distributor http://<distributor-ip>:5553 --sessionqueue http://<new-session-queue-ip>:5559 --port 4444
```

6. **Node(s)**

A porta por omissão é `5555`.
```shell
java -jar selenium-server-<version>.jar node --publish-events tcp://<event-bus-ip>:4442 --subscribe-events tcp://<event-bus-ip>:4443
```

## Adicionar Metadata nos testes

Adicione Metadata aos testes, através de [GraphQL]({{< ref "advanced_features/graphql_support.md" >}})
ou visualize parcialmente (como `se:name`) através da Selenium Grid UI. 

Metadata pode ser adicionada como uma capacidade com o prefixo `se:`. Eis um pequeno exemplo em Java.

```java
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setCapability("browserVersion", "100");
chromeOptions.setCapability("platformName", "Windows");
// Mostrando na Grid UI o nome de um teste ao invés de uma session id
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

## Using the Java 11 HTTP Client {{% badge-version version="4.5" %}}

By default, Grid will use [AsyncHttpClient](https://github.com/AsyncHttpClient/async-http-client). 
AsyncHttpClient is an open-source library built on top of Netty. It allows the execution of HTTP 
requests and responses asynchronously. Additionally it also provides WebSocket support. Hence it 
is a good fit. 

However, AsyncHttpClient is not been actively maintained since June 2021. It coincides with the 
fact that Java 11+ provides a built-in HTTP and WebSocket client. Currently, Selenium 
has plans to upgrade the minimum version supported to Java 11. However, it is a sizeable effort. 
Aligning it with major releases and accompanied announcements  is crucial to ensure the user 
experience is intact.

To do use the Java 11 client, you will need to download the `selenium-http-jdk-client` jar file 
and use the `--ext` flag to make it available in the Grid jar's classpath.

The jar file can be downloaded directly from [repo1.maven.org](https://repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-http-jdk-client/)
and then start the Grid in the following way:

```bash
java -Dwebdriver.http.factory=jdk-http-client -jar selenium-server-<version>.jar -—ext selenium-http-jdk-client-<version>.jar standalone
```

An alternative to downloading the `selenium-http-jdk-client` jar file is to use [Coursier](https://get-coursier.io/docs/cli-installation).

```bash
java -Dwebdriver.http.factory=jdk-http-client -jar selenium-server-<version>.jar —-ext $(coursier fetch -p org.seleniumhq.selenium:selenium-http-jdk-client:<version>) standalone
```

If you are using the Hub/Node(s) mode or the Distributed mode, setting the `-Dwebdriver.http.factory=jdk-http-client` 
and `—-ext` flags needs to be done for each one of the components.

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