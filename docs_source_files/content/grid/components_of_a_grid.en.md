---
title: "Components of a Grid"
weight: 2
---

![Grid](/images/grid.png)

## Hub
* Intermediary and manager
* Accepts requests to run tests
* Takes instructions from client and executes them remotely on the nodes
* Manages threads

A _Hub_ is a central point where all your tests are sent.
Each Selenium Grid consists of exactly one hub. The hub needs to be reachable
from the respective clients (i.e. CI server, Developer machine etc.)
The hub will connect one or more nodes
that tests will be delegated to.

## Nodes

* Where the browsers live
* Registers itself to the hub and communicates its capabilities
* Receives requests from the hub and executes them

_Nodes_ are different Selenium instances
that will execute tests on individual computer systems.
There can be many nodes in a grid.
The machines which are nodes do not need to be the same platform
or have the same browser selection as that of the hub or the other nodes.
A node on Windows might have the capability of
offering Internet Explorer as a browser option,
whereas this wouldn't be possible on Linux or Mac.

