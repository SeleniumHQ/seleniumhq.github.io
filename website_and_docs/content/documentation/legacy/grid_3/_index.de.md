---
title: "Grid 3"
linkTitle: "Grid 3"
weight: 6
aliases: ["/documentation/de/grid/grid_3/"]
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Most of the documentation found in this section is still in English.
   Please note we are not accepting pull requests to translate this content
   as translating documentation of legacy components does not add value to
   the community nor the project.
</p>
{{% /pageinfo %}}

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
