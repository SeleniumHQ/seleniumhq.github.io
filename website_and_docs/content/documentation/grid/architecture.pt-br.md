---
title: "Arquitectura da Grid"
linkTitle: "Arquitectura da Grid"
weight: 10
aliases: [
"/pt-br/documentation/grid/grid_architecture"
]
---

A Grid está desenhada como um conjunto de componentes, em que cada tem
o seu papel crucial em manter a Grid. Isto pode parecer um pouco complicado,
mas esperamos que este documento ajude a esclarecer alguma confusão.

## Os componentes chave

Os componentes principais da Grid são:

<dl>
<dt>Event Bus
<dd>Usado para enviar mensagens que podem ser recebidas de forma assíncrona
    entre os outros componentes.

<dt>New Session Queue
<dd>Mantém uma lista de pedidos de sessão que serão assignadas a um Node
    pelo Distributor.

<dt>Distributor
<dd>Responsável por manter um modelo das localizações da Grid (slots) onde uma 
    sessão pode ser lançada e também por aceitar novos 
    <a href="https://w3c.github.io/webdriver/#new-session">pedidos de sessão</a> 
    e assignar a um slot livre.

<dt>Node
<dd>Executa uma <a href="https://w3c.github.io/webdriver/#dfn-sessions"> 
    sessão WebDriver</a>. Cada sessão é assignada a um slot e cada Node tem
    um ou mais slots.

<dt>Session Map
<dd>Mantém um mapeamento entre um <a
    href="https://w3c.github.io/webdriver/#dfn-session-id">ID de sessão</a>
    e o endereço do Node onde a sessão está a ser executada.

<dt>Router
<dd>Este é o ponto de entrada da Grid. É também a única parte da Grid
    que <i>poderá</i> estar exposta à Internet (embora nós não recomendemos).
    Este componente reencaminha novos pedidos para New Session Queue ou 
    para o Node onde a sessão esteja a ser executada
</dl>

Ao falar da Grid, há alguns conceitos úteis a ter em mente:

 * Um **slot** é o sítio onde uma sessão pode ser executada
 * Cada slot tem um **stereotype**. Isto é um conjunto mínimo de capacidades
   que um pedido de [nova sessão][new session] terá que corresponder antes que o 
   Distributor envie esse pedido ao Node que tenha esse slot
 * O **Grid Model** é como o Distributor mantém o estado actual da Grid.
   Como o nome sugere, este modelo pode perder o sincronismo com a realidade.
   Este mecanismo é preferível do que estar a questionar cada Node, e
   desta forma, o Distributor rapidamente consegue alocar uma nova sessão a um slot.


## Chamadas Síncronas e Assíncronas

Existem duas formas de comunicação dentro da Grid:

 1. Chamadas Síncronas "REST-ish" que usam JSON sobre pedidos HTTP.
 2. Eventos Assíncronos enviados para o Event Bus.

Como é que escolhemos que tipo de mecanismo de comunicação a usar?
Afinal, podiamos ter escolhido usar apenas comunicação baseada em eventos
e tudo iria funcionar sem problemas.

No entanto a resposta correcta é, se a acção em curso é síncrona, por exemplo
a maioria das chamadas WebDriver, ou se perder uma resposta é problemático,
a Grid usa chamadas síncronas. Se quisermos propagar informação que pode ter
várias partes interessadas, ou se perder a mensagem não for crítico, 
a Grid usará o event bus.

Um facto interessante a notar é que as chamadas assíncronas estão menos
"presas" aos processos que as executam do que todas as chamadas síncronas.

## Sequência de início e dependencias entre componentes

Embora a Grid seja desenhada para permitir que os componentes possam iniciar
em qualquer ordem, conceptualmente é esperado que a ordem de início seja:

1. O Event Bus e o Session Map iniciam primeiro. Estes componentes não tem qualquer
   dependencia, nem mesmo entre eles e como tal, podem iniciar em paralelo.
2. A Session Queue inicia de seguida
3. O Distributor inicia. Irá periodicamente procurar novos pedidos de sessão 
   na Session Queue, embora possa também receber um evento de um pedidos de sessão.
4. O Router pode ser agora iniciado. Novos pedidos de sessão são direccionados para
   a Session Queue, o Distributor tentará encontrar um slot onde a sessão possa ser 
   executada.
5. O Node pode ser iniciado, veja mais abaixo os detalhes de como o Node se 
   regista na Grid. Uma vez que o registo esteja concluído, a Grid estará 
   pronta a receber pedidos.

Nesta tabela pode ser visualizada a dependencia ente os vários componentes.
Um "✅" indica que a dependência é síncrona.


|               | Event Bus | Distributor | Node | Router | Session Map | Session Queue |
|---------------|-----------|-------------|------|--------|-------------|---------------|
| Event Bus     |    X      |             |      |        |             |               |
| Distributor   |    ✅     |      X      |  ✅  |        |             |      ✅       |
| Node          |    ✅     |             |  X   |        |             |               |
| Router        |           |             |  ✅  |   X    |     ✅      |               |
| Session Map   |           |             |      |        |     X       |               |
| Session Queue |    ✅     |             |      |        |             |      X        |

## Registo de Node

O processo de registar um Node na Grid é um processo "leve".

  1. Quando um Node inicia, vai publicar um evento "heart beat" numa
     base regular. Este evento contém o estado do Node.
  2. O Distributor escuta os eventos "heart beat" e quando obtém um,
     tenta um `GET` ao endpoint `/status` do Node. A Grid é 
     preparada com base nesta informação.

O Distributor irá usar regularmente o endpoint `/status` para continuar
a obter o estado do Node. Por seu lado, o Node continua a publicar um 
evento "heart beat" mesmo depois do registo ter sido concluído com 
sucesso.
Isto é feito para que mesmo que um Distributor não tenha um estado
da Grid possa reiniciar e assim obter novamente uma visão do estado
da Grid e assim ficar actualizado.

### Objecto Node Status

O Node Status é um blob JSON com os seguintes campos:

| Nome | Tipo | Descrição |
|------|------|-------------|
| availability | string | Uma string com `up`, `draining`, ou `down`. A mais importante é `draining`, que indica que não devem ser enviados novos pedidos de sessão para o Node e assim que a última sessão termine, o Node irá reiniciar ou concluir. |
| externalUrl | string | Uma URI que os outros componentes da Grid se devem ligar. |
| lastSessionCreated | integer | Um timestamp da última sessão que foi criada neste Node. O Distributor irá tentar enviar novos pedidos de sessão para o Node que esteja parado há mais tempo. |
| maxSessionCount | integer | Embora seja possível inferir o número máximo de sessões a partir da lista de slots disponíveis, este número é usado para determinar qual é o máximo de sessões que este Node pode executar em simultâneo antes que se considere que está "cheio". |
| nodeId | string | Um identificador UUID para esta instância do Node. |
| osInfo | object | Um objecto contendo os campos `arch`, `name`, e `version`. Isto é usado pela Grid UI e pelas queries GraphQL. |
| slots | array | Um array de objectos Slot (descritos na secção seguinte) |
| version | string | A versão do Node (para Selenium, será igual à versão do Selenium) |

É recomendado que todos os campos tenham valores.

### O Objecto Slot

O objecto Slot representa um slot dentro de um Node. Um "slot" é onde uma sessão consegue ser executada. É possível que um Node tenha mais do que um Slot capaz de executar ao mesmo tempo.
Por exemplo, um Node pode ser capaz de executar até 10 sessões em simultâneo, mas podem ser uma qualquer combinação de Chrome, Firefox ou Edge e neste caso, o Node irá indicar 10 como o
número máximo de sessões, indicando que podem ser 10 Chrome, 10 Firefox e 10 Edge.

| Nome | Tipo | Descrição |
|------|------|-------------|
| id | string | Um identificador UUID para este slot |
| lastStarted | string | timestamp no formato ISO-8601 contendo a data em que a última sessão iniciou |
| stereotype | object | Conjunto mínimo de [capacidades][capabilities] que fazem match com este slot. O exemplo mínimo será por exemplo `{"browserName": "firefox"}` |
| session | object | O objecto Session (descrito na secção seguinte) |

### O Objecto Session

Representa uma sessão em execução dentro de um Slot

| Nome | Tipo | Descrição |
|------|------|-------------|
| capabilities | object | A lista de capacidades fornecidas pela sessão. Irá coincidir com o valor obtido pelo comando [nova sessão][new session] |
| startTime | string | timestamp no formato ISO-8601 contendo a data em que a última sessão iniciou |
| stereotype | object | Conjunto mínimo de [capacidades][capabilities] que fazem match com este slot. O exemplo mínimo será por exemplo `{"browserName": "firefox"}` |
| uri | string | A URI usada pelo Node para comunicar com a sessão |

[capabilities]: https://w3c.github.io/webdriver/#dfn-merging-capabilities
[new session]: https://w3c.github.io/webdriver/#new-session
[node status]: https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/grid/data/NodeStatus.html
[session]: https://w3c.github.io/webdriver/#dfn-sessions
[session id]: https://w3c.github.io/webdriver/#dfn-session-id
