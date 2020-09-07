---
title: "Komponenten"
weight: 1
---

{{% notice info %}}
<i class="fas fa-language"></i> Diese Seite wird von Englisch
auf Deutsch übersetzt. Sprichst Du Deutsch? Hilf uns die Seite
zu übersetzen indem Du uns einen Pull Reqeust schickst!
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
