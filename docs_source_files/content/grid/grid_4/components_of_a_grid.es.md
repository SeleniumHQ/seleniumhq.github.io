---
title: "Componentes"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from
English to Spanish. Do you speak Spanish? Help us to translate
it by sending us pull requests!
{{% /notice %}}

![Grid](/images/grid_4.png)

## Router

The Router takes care of forwarding the request to the correct component.

It is the entry point of the Grid, all external requests will be received by it.
The Router behaves differently depending on the request. If it is a new session
request, the Router will forward it to the Distributor (where the new session 
creation will be handled). If the request belongs to an existing session, the
Router will send the session id to the Session Map, and the Session Map will 
return the Node where the session is running. After this, the Router will
forward the request to the Node.

The Router aims to balance the load in the Grid by sending the requests to the
component that is able to handle them better, without overloading any component
that is not needed in the process.

## Distributor

The Distributor is aware of all the Nodes and their capabilities. Its main role is
to receive a new session request and find a suitable Node where the session can be
created. After the session is created, the Distributor stores in the Session Map
the relation between the session id and Node where the session is being executed. 

## Node

A Node can be present several times in a Grid. Each Node takes care of managing
the slots for the available browsers of the machine where it is running.

The Node registers itself to the Distributor through the Event Bus, and its
configuration is sent as part of the registration message.

By default, the Node auto-registers all browser drivers available on the path of
the machine where it runs. It also creates one slot per available CPU for Chromium
based browsers and Firefox. For Safari and Internet Explorer, only one slot is created.
Through a specific configuration, it can run sessions in Docker containers. You can see
more configuration details in the next [section]({{< ref "/grid/grid_4/setting_up_your_own_grid.es.md" >}}).

A Node only executes the received commands, it does not evaluate, make judgments,
or control anything. The machines where the Node is running does not need to have
the same operating system as the other components. For example, A Windows Node 
might have the capability of offering Internet Explorer as a browser option,
whereas this would not be possible on Linux or Mac.

## Session Map

The Session Map is a data store that keeps the information of the session id and the Node 
where the session is running. It serves as a support for the Router in the process of 
forwarding a request to the Node. The Router will ask the Session Map for the Node 
associated to a session id.

## Event Bus

The Event Bus serves as a communication path between the Nodes, Distributor, and Session Map. 
The Grid does most of its internal communication through messages, avoiding expensive HTTP calls. 
When starting the Grid in its fully distributed mode, the Event Bus is the first component that should be started. 

## Roles in Grid

In Grid 3, the components were Hub and Node, and it was possible to run them together by starting the
Grid in Standalone mode. The same concept is available in Grid 4, it is possible to run a Hub by
grouping some of the components described above, and it is also possible to run all components
together in a Standalone mode. 

### Hub

Hub is the union of the following components:

* Router
* Distributor
* Session Map
* Event Bus

It enables the classic Hub & Node(s) setup.

### Standalone

As mentioned before, Standalone is the union of all components, and to the user's eyes, they are
executed as one. This includes all the components which are part of the Hub, plus one Node. A fully
functional Grid of one is available after starting it in the Standalone mode.

