---
title: "Grid"
linkTitle: "Grid"
weight: 9
description: >
  Want to run tests in parallel across multiple machines? Then, Grid is for you.
aliases: 
        [
          "/documentation/en/selenium_installation/installing_standalone_server/",
          "/documentation/en/grid/",
          "/documentation/en/grid/grid_4/",
          "/documentation/en/grid/purposes_and_main_functionalities/"
        ]
---

Selenium Grid allows the execution of WebDriver scripts on remote machines (virtual
or real) by routing commands sent by the client to remote browser instances.
It aims to provide an easy way to run tests in parallel on multiple machines.

Selenium Grid allows us to run tests in parallel on multiple machines,
and to manage different browser versions and browser configurations centrally
(instead of in each individual test).

Selenium Grid is not a silver bullet.
It solves a subset of common delegation and distribution problems,
but will for example not manage your infrastructure,
and might not suit your specific needs.

## Purposes and main functionalities

* Central entry point for all tests
* Management and control of the nodes / environment where the browsers run
* Scaling
* Running tests in parallel
* Cross platform testing
* Load balancing

_Selenium Grid 4_ is a fresh implementation and does not share the codebase
the previous version had.

Grid 4 has an approach to take advantage of a number of new technologies in order 
to facilitate scaling up, while still allowing local execution.

To get all the details of Grid 4 components, understand how it works, and how to set
up you own, please browse thorough the following sections.