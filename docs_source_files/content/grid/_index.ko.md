---
title: "Grid"
chapter: true
weight: 9
---

# Grid

_Selenium Grid_ is a smart proxy server
that allows Selenium tests to route commands to remote web browser instances.
Its aim is to provide an easy way to run tests in parallel on multiple machines.

With Selenium Grid,
one server acts as the hub that routes JSON formatted test commands
to one or more registered Grid nodes.
Tests contact the hub to obtain access to remote browser instances.
The hub has a list of registered servers that it provides access to,
and allows control of these instances.

Selenium Grid allows us to run tests in parallel on multiple machines,
and to manage different browser versions and browser configurations centrally
(instead of in each individual test).

Selenium Grid is not a silver bullet.
It solves a subset of common delegation and distribution problems,
but will for example not manage your infrastructure,
and might not suit your specific needs.
