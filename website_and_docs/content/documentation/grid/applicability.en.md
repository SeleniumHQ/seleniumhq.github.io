---
title: "When to Use Grid"
linkTitle: "When to Use Grid"
weight: 4
description: >
  Is Grid right for you?
aliases: [
"/documentation/en/grid/when_to_use_grid/",
"/documentation/grid/when_to_use_grid"
]
---

When would you use a Selenium Grid?

* To run your tests in parallel, against different browser types, browser versions, operating systems
* To reduce the time needed to execute a test suite

Selenium Grid runs test suites in parallel against multiple machines (called Nodes). 
For large and long-running test suites, this can save minutes, hours, or perhaps days. 
This shortens the turnaround time for test results as your application under test (AUT) 
changes.

Grid can run tests (in parallel) against multiple different browsers, and it can
run against multiple instances of the same browser. As an example, let's imagine
a Grid with six Nodes. The first machine has Firefox's latest version,
the second has Firefox "latest minus one", the third gets the latest Chrome, and
the remaining three machines are Mac Minis, which allows for three tests to run in
parallel on the latest version of Safari.

Execution time can be expressed as a simple formula:

```Number of Tests * Average Test Time / Number of Nodes = Total Execution Time```

       15      *       45s        /        1        =      11m 15s   // Without Grid
       15      *       45s        /        5        =      2m 15s    // Grid with 5 Nodes
       15      *       45s        /        15       =      45s       // Grid with 15 Nodes
      100      *       120s       /        15       =      13m 20s   // Would take over 3 hours without Grid

As the test suite is executing, the Grid allocates the tests to run against these 
browsers as configured in the tests.

A configuration such as this can greatly speed up the execution time of even the largest Selenium test suites.

Selenium Grid is a completely native part of the Selenium project, and is maintained in parallel by the same team 
of committers who work in the core Selenium development. Recognizing the importance of test execution speed, Grid
has been a critical part of the Selenium project since the earliest days.
