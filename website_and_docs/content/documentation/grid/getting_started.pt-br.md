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
      * [Selenium Manager]({{< ref "../selenium_manager/" >}}) will configure the drivers automatically if you add `--selenium-manager true`.
      * [Installed and on the `PATH`]({{< ref "../webdriver/troubleshooting/errors/driver_location.md#use-the-path-environment-variable" >}})
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
[`PATH`]({{< ref "../webdriver/troubleshooting/errors/driver_location.md#3-a-variável-de-ambiente--path" >}}).

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
[`PATH`]({{< ref "../webdriver/troubleshooting/errors/driver_location.md#3-a-variável-de-ambiente--path" >}}). 

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
// Outros tipos de metadara podem ser visualizados na Grid UI 
// ao clicar na informação de sessão ou via GraphQL
chromeOptions.setCapability("se:sampleMetadata", "Valor exemplo de Metadata"); 
WebDriver driver = new RemoteWebDriver(new URL("http://gridUrl:4444"), chromeOptions);
driver.get("http://www.google.com");
driver.quit();
```

## Questionando a Selenium Grid

Após iniciar a Gris, existem duas formas de saber o seu estado, através da Grid UI ou
por chamada API.

A Grid UI pode ser acedida pelo seu navegador preferido em [http://localhost:4444](http://localhost:4444). 
As chamadas API podem ser feitas para o endpoint [http://localhost:4444/status](http://localhost:4444/status) ou
através de [GraphQL]({{< ref "advanced_features/graphql_support.md" >}}).

{{% pageinfo color="primary" %}}
Para simplificar, todos os exemplos apresentados assumem que os componentes estão a ser executados localmente.
Exemplos mais detalhados podem ser encontrados na secção [Configurando Componentes]({{< ref "/configuration" >}}).
{{% /pageinfo %}}

## Usando o cliente HTTP nativo Java 11 {{% badge-version version="4.5" %}}

Por omissão, a Grid irá usar [AsyncHttpClient](https://github.com/AsyncHttpClient/async-http-client). 
AsyncHttpClient é uma biblioteca open-source library criada em cima do Netty. Isto permite a
execução de pedidos e respostas HTTP de forma assíncrona. Esta biblioteca é uma boa escolha
pois além de permitir pedidos assíncronos, também suporta WebSockets.

No entanto, a biblioteca AsyncHttpClient não é mantida activamente desde Junho de 2021. Isto coincide com
o facto de que a partir do Java 11, a JVM tem um cliente nativo que suporta camadas assíncronas
e contém um cliente WebSocket.

Atualmente, o projecto Selenium tem planos de atualizar a versão mínima suportada para Java 11. 
No entanto, isto é um esforço considerável. Alinhá-lo com os principais lançamentos e anúncios
acompanhados é crucial para garantir que a experiência do usuário esteja intacta.

Para usar o cliente Java 11, terá que baixar o ficheiro jar `selenium-http-jdk-client` e usar
a flag `--ext` para funcionar na Grid.

Este ficheiro pode ser obtido directamente de [repo1.maven.org](https://repo1.maven.org/maven2/org/seleniumhq/selenium/selenium-http-jdk-client/)
e depois pode iniciar a Grid com:

```bash
java -Dwebdriver.http.factory=jdk-http-client -jar selenium-server-<version>.jar --ext selenium-http-jdk-client-<version>.jar standalone
```

Uma alternativa a baixar o ficheiro jar `selenium-http-jdk-client` é usar [Coursier](https://get-coursier.io/docs/cli-installation).

```bash
java -Dwebdriver.http.factory=jdk-http-client -jar selenium-server-<version>.jar --ext $(coursier fetch -p org.seleniumhq.selenium:selenium-http-jdk-client:<version>) standalone
```

Se está a usar a Grid em modo **Hub/Node** ou **Distributed**, terá que usar as flags 
`-Dwebdriver.http.factory=jdk-http-client` e `--ext` em cada um dos componentes.

## Dimensionar Grid

A escolha de Grid depende de quais sistemas operacionais e navegadores precisam ser suportados,
quantas sessões paralelas precisam ser executadas, a quantidade de máquinas disponíveis e quão
poderosas (CPU, RAM) essas máquinas são.

A criação de sessões simultaneas depende dos processadores disponíveis para o **Distributor**.
Por exemplo, se uma máquina tiver 4 CPUs, o **Distributor** só poderá criar quatro sessões
em simultâneo.

Por omissão, a quantidade máxima de sessões simultâneas que um **Node** suporta é limitada pelo
número de CPUs disponíveis. Por exemplo, se a máquina **Node** tiver 8 CPUs, ela poderá executar
até 8 sessões de navegador simultâneas (com exceção do Safari, que é sempre uma).
Além disso, espera-se que cada sessão do navegador use cerca de 1 GB de RAM.

Em geral, é recomendado ter **Nodes** o mais pequenos possíveis. Em vez de ter
uma máquina com 32CPUs e 32GB de RAM para executar 32 sessões de navegador simultâneas, é melhor
tem 32 pequenos **Nodes** para isolar melhor os processos. Com isso, se um **Node**
falhar, será de forma isolada. Docker é uma boa ferramenta para alcançar essa abordagem.

Note que os valores 1CPU/1GB RAM por navegador são uma recomendação e podem não ser os mais indicados
para o seu contexto. Recomenda-se que use estea valores como referência, mas meça o desempenho 
continuamente para ajudar a determinar os valores ideais para o seu ambiente.

Os tamanhos da Grid são relativos à quantidade de sessões simultâneas suportadas e à quantidade de
**Nodes**, não existindo um "tamanho único". Os tamanhos mencionados abaixo são estimativas aproximadas
que podem variar entre diferentes ambientes. Por exemplo, um **Hub/Node** com 120 **Nodes**
pode funcionar bem quando o **Hub** tiver recursos suficientes. Os valores abaixo não são gravados em pedra,
e comentários são bem-vindos!

### Pequena

**Standalone** e **Hub/Node** com cinco **Nodes** ou menos.

### Média

**Hub/Node** entre 6 e 60 **Nodes**.

### Grande

**Hub/Node** entre 60 e 100 **Nodes**. **Distributed** com mais de 100 **Nodes**.

## AVISO

Deve proteger a Selenium Grid de acesso externo, usando regras de firewall apropriadas.

Se falhar em proteger a Grid uma ou mais coisas poderão ocorrer:

* Permite acesso aberto à sua infraestrutura da Grid
* Permitir acesso de terceiros a aplicativos web e a ficheiros
* Permitir execução remota de ficheiros binários por terceiros

Leia este artigo (em Inglês) em [Detectify](//labs.detectify.com), que dá um bom resumo
de como uma Grid exposta publicamente pode ser abusada:
[Don't Leave your Grid Wide Open](//labs.detectify.com/2017/10/06/guest-blog-dont-leave-your-grid-wide-open/)

## Leituras adicionais

* [Componentes]({{< ref "components.md" >}}): compreender como usar os componentes da Grid
* [Configuração]({{< ref "/configuration" >}}): personalize a sua configuração Grid.
* [Arquitectura]({{< ref "architecture.md" >}}): entenda conceitos chave da Grid.
* [Advanced Features]({{< ref "/advanced_features" >}}): explore mais possibilidades da Grid.
