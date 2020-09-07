---
title: "Components"
weight: 1
---

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

## SessionMap
* Datastore mapping for session id and the node.

__SessionMap__ is data store used to store the map of session id and the node on which the session is running.

## Nodes

* Where the browsers live
* Registers itself to the hub and communicates its capabilities
* Receives requests from the hub and executes them

__Nodes__ are different Selenium instances
that will execute tests on individual computer systems.
There can be many nodes in a grid.
The machines which are nodes do not need to be the same platform
or have the same browser selection as that of the hub or the other nodes.
A node on Windows might have the capability of
offering Internet Explorer as a browser option,
whereas this wouldn't be possible on Linux or Mac.

## Hub
* Accepts requests to run tests
* Acts as a Router
* Acts as a Distributor
* Acts as a SessionMap
* Takes instructions from client and executes them remotely on the nodes

A __Hub__ is a central point where all your tests are sent.
It allows you to run a Router, Distributor and a SessionMap on the same machine using a single command. 
The hub needs to be reachable from the respective clients (i.e. CI server, Developer machine etc.). 
It communicates with the node to run your selenium sessions. Instead of running each component separately, 
you can use _hub_ role to run all 3 together.

## Standalone

A __Standalone__ version of Grid consists of all components running as single process, including the node.
You can directly start running sessions after starting the standalone version of the Grid.
