---
title: "Grid architecture"
linkTitle: "Architecture"
weight: 10
aliases: [
"/documentation/grid/grid_architecture"
]
---

The Grid is designed as a set of components that all fulfill a role in
maintaining the Grid. It can seem quite complicated, but hopefully
this document can help clear up any confusion.

## The Key Components

The main components of the Grid are:

<dl>
<dt>Event Bus
<dd>Used for sending messages which may be received asynchronously
    between the other components.

<dt>New Session Queue
<dd>Maintains a list of incoming sessions which have yet to be
    assigned to a Node by the Distributor.

<dt>Distributor
<dd>Responsible for maintaining a model of the available locations in
    the Grid where a session may run (known as "slots") and taking any
    incoming <a
    href="https://w3c.github.io/webdriver/#new-session">new
    session</a> requests and assigning them to a slot.

<dt>Node
<dd>Runs a <a
    href="https://w3c.github.io/webdriver/#dfn-sessions">WebDriver
    session</a>. Each session is assigned to a slot, and each node has
    one or more slots.

<dt>Session Map
<dd>Maintains a mapping between the <a
    href="https://w3c.github.io/webdriver/#dfn-session-id">session
    ID</a> and the address of the Node the session is running on.

<dt>Router
<dd>Acts as the front-end of the Grid. This is the only part of the
    Grid which <i>may</i> be exposed to the wider Web (though we strongly
    caution against it). This routes incoming requests to either the
    New Session Queue or the Node on which the session is running.
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
