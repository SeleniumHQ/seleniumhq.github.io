---
title: "Grid"
chapter: true
weight: 9
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to French. Do you speak French? Help us to translate
it by sending us pull requests!
{{% /notice %}}

# Grid

_Selenium Grid_ allows the execution of WebDriver scripts on remote machines (virtual
or real) by routing commands sent by the client to remote browser instances.
It aims to provide an easy way to run tests in parallel on multiple machines.

Selenium Grid allows us to run tests in parallel on multiple machines,
and to manage different browser versions and browser configurations centrally
(instead of in each individual test).

Selenium Grid is not a silver bullet.
It solves a subset of common delegation and distribution problems,
but will for example not manage your infrastructure,
and might not suit your specific needs.

**Please note Grid 3 is not supported anymore and the Selenium project
recommends to use [Grid 4]({{<ref "/grid/grid_4/_index.fr.md">}})**
