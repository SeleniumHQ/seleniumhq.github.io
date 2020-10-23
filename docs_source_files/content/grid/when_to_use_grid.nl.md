---
title: "When to use Grid"
weight: 2
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Dutch. Do you speak Dutch? Help us to translate
it by sending us pull requests!
{{% /notice %}}


Generally speaking, there’s two reasons why you might want to use Grid.

* To run your tests against multiple browsers, multiple versions of browser,
and browsers running on different operating systems.
* To reduce the time it takes for the test suite to complete a test pass.

Grid is used to speed up the execution of a test pass by using
multiple machines to run tests in parallel. For example, if you have a suite of
100 tests, but you set up Grid to support 4 different machines (VMs or
separate physical machines) to run those tests, your test suite will complete
in (roughly) one-fourth the time as it would if you ran your tests sequentially
on a single machine. For large test suites, and long-running test suite such as
those performing large amounts of data-validation, this can be a significant
time-saver. Some test suites can take hours to run. Another reason to boost the
time spent running the suite is to shorten the turnaround time for test results
after developers check-in code for the AUT. Increasingly software teams
practicing Agile software development want test feedback as immediately as
possible as opposed to wait overnight for an overnight test pass.

Grid is also used to support running tests against multiple runtime
environments, specifically, against different browsers at the same time. For
example, a ‘grid’ of virtual machines can be setup with each supporting a
different browser that the application to be tested must support. So, machine 1
has Internet Explorer 8, machine 2, Internet Explorer 9, machine 3 the latest
Chrome, and machine 4 the latest Firefox. When the test suite is run,
Selenium-Grid receives each test-browser combination and assigns each test to
run against its required browser.

In addition, one can have a grid of all the same browser, type and version. For
instance, one could have a grid of 4 machines each running 3 instances of
Firefox 70, allowing for a ‘server-farm’ (in a sense) of available Firefox
instances. When the suite runs, each test is passed to Grid which
assigns the test to the next available Firefox instance. In this manner one
gets test pass where conceivably 12 tests are all running at the same time in
parallel, significantly reducing the time required to complete a test pass.

Grid is very flexible. These two examples can be combined to allow
multiple instances of each browser type and version. A configuration such as
this would provide both, parallel execution for fast test pass completion and
support for multiple browser types and versions simultaneously.
