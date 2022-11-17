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

While discussing the Grid, there are some other useful concepts to
keep in mind:

 * A **slot** is the place where a session can run.
 * Each slot has a **stereotype**. This is the minimal set of
   capabilities that a [new session][] session request must match
   before the Distributor will send that request to the Node owning
   the slot.
 * The **Grid Model** is how the Distributor tracks the state of the
   Grid. As the name suggests, this may sometimes fall out of sync
   with reality (perhaps because the Distributor has only just
   started). It is used in preference to querying each Node so that
   the Distributor can quickly assign a slot to a New Session request.

## Synchronous and Asynchronous Calls

There are two main communication mechanisms used within the Grid:

 1. Synchronous "REST-ish" JSON over HTTP requests.
 2. Asynchronous events sent to the Event Bus.

How do we pick which communication mechanism to use? After all, we
could model the entire Grid in an event-based way, and it would work
out just fine.

The answer is that if the action being performed is synchronous
(eg. most WebDriver calls), or if missing the response would be
problematic, the Grid uses a synchronous call. If, instead, we want to
broadcast information to anyone who's interested, or if missing the
response doesn't matter, then we prefer to use the event bus.

One interesting thing to note is that the async calls are more
decoupled from their listeners than the synchronous calls are.

## Start Up Sequence and Dependencies Between Components

Although the Grid is designed to allow components to start up in any
order, conceptually the order in which components starts is:

1. The Event Bus and Session Map start first. These have no other
   dependencies, not even on each other, and so are safe to start in
   parallel.
2. The Session Queue starts next.
3. It is now possible to start the Distributor. This will periodically
   connect to the Session Queue and poll for jobs, though this polling
   might be initiated either by an event (that a New Session has been
   added to the queue) or at regular intervals.
4. The Router(s) can be started. New Session requests will be directed
   to the Session Queue, and the Distributor will attempt to find a
   slot to run the session on.
5. We are now able to start a Node. See below for details about how
   the Node is registered with the Grid. Once registration is
   complete, the Grid is ready to serve traffic.

You can picture the dependencies between components this way, where a
"✅" indicates that there is a synchronous dependency between the
components.

|               | Event Bus | Distributor | Node | Router | Session Map | Session Queue |
|---------------|-----------|-------------|------|--------|-------------|---------------|
| Event Bus     |    X      |             |      |        |             |               |
| Distributor   |    ✅     |      X      |  ✅  |        |             |      ✅       |
| Node          |    ✅     |             |  X   |        |             |               |
| Router        |           |             |  ✅  |   X    |     ✅      |               |
| Session Map   |           |             |      |        |     X       |               |
| Session Queue |    ✅     |             |      |        |             |      X        |

## Node Registration

The process of registering a new Node to the Grid is lightweight.

  1. When the Node starts, it should emit a "heart beat" event on a
    regular basis. This heartbeat contains the [node status].
  2. The Distributor listens for the heart beat events. When it sees
    one, it attempts to `GET` the `/status` endpoint of the Node. It
    is from this information that the Grid is set up.

The Distributor will use the same `/status` endpoint to check the Node
on a regular basis, but the Node should continue sending heart beat
events even after started so that a Distributor without a persistent
store of the Grid state can be restarted and will (eventually) be up
to date and correct.

### The Node Status Object

The Node Status is a JSON blob with the following fields:

| Name | Type | Description |
|------|------|-------------|
| availability | string | A string which is one of `up`, `draining`, or `down`. The important one is `draining`, which indicates that no new sessions should be sent to the Node, and once the last session on it closes, the Node will exit or restart. |
| externalUrl | string | The URI that the other components in the Grid should connect to. |
| lastSessionCreated | integer | The epoch timestamp of when the last session was created on this Node. The Distributor will attempt to send new sessions to the Node that has been idle longest if all other things are equal. |
| maxSessionCount | integer | Although a session count can be inferred by counting the number of available slots, this integer value is used to determine the maximum number of sessions that should be running simultaneously on the Node before it is considered "full". |
| nodeId | string | A UUID used to identify this instance of the Node. |
| osInfo | object | An object with `arch`, `name`, and `version` fields. This is used by the Grid UI and the GraphQL queries. |
| slots | array | An array of Slot objects (described below) |
| version | string | The version of the Node (for Selenium, this will match the Selenium version number) |

It is recommended to put values in all fields.

### The Slot Object

The Slot object represents a single slot within a Node. A "slot" is
where a single session may be run. It is possible that a Node will
have more slots than it can run concurrently. For example, a node may
be able to run up 10 sessions, but they could be any combination of
Chrome, Edge, or Firefox; in this case, the Node would indicate a "max
session count" of 10, and then also say it has 10 slots for Chrome, 10
for Edge, and 10 for Firefox.

| Name | Type | Description |
|------|------|-------------|
| id | string | UUID to refer to the slot |
| lastStarted | string | When the slot last had a session started, in ISO-8601 format |
| stereotype | object | The minimal set of [capabilities][capabilities] this slot will match against. A minimal example is `{"browserName": "firefox"}` |
| session | object | The Session object (see below) |

### The Session Object

This represents a running session within a slot

| Name | Type | Description |
|------|------|-------------|
| capabilities | object | The actual capabilities provided by the session. Will match the return value from the [new session][new session] command |
| startTime | string | The start time of the session in ISO-8601 format |
| stereotype | object | The minimal set of [capabilities][capabilities] this slot will match against. A minimal example is `{"browserName": "firefox"}` |
| uri | string | The URI used by the Node to communicate with the session |

[capabilities]: https://w3c.github.io/webdriver/#dfn-merging-capabilities
[new session]: https://w3c.github.io/webdriver/#new-session
[node status]: https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/grid/data/NodeStatus.html
[session]: https://w3c.github.io/webdriver/#dfn-sessions
[session id]: https://w3c.github.io/webdriver/#dfn-session-id
