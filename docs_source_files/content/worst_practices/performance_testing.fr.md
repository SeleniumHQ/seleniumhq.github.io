---
title: "Test de performance"
weight: 6
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to French. Do you speak French? Help us to translate
it by sending us pull requests!
{{% /notice %}}

Performance testing using Selenium and WebDriver
is generally not advised.
Not because it is incapable
but because it is not optimised for the job
and you are unlikely to get good results.

It may seem ideal to performance test
in the context of the user but a suite of WebDriver tests
are subjected to many points of external and internal fragility
which are beyond your control;
for example browser startup speed,
speed of HTTP servers,
response of third party servers that host JavaScript or CSS,
and the instrumentation penalty
of the WebDriver implementation itself.
Variation at these points will cause variation in your results.
It is difficult to separate the difference
between the performance of your website
and the performance of external resources,
and it is also hard to tell what the performance penalty is
for using WebDriver in the browser,
especially if you are injecting scripts.

The other potential attraction is "saving time" —
carrying out functional and performance tests at the same time.
However, functional and performance tests have opposing objectives.
To test functionality, a tester may need to be patient
and wait for loading,
but this will cloud the performance testing results and vice versa.

To improve the performance of your website,
you will need to be able to analyse overall performance
independent of environment differences,
identify poor code practices,
breakdown of performance of individual resources
(i.e. CSS or JavaScript)
in order to know what to improve.
There are performance testing tools available
that can do this job already,
and which provide reporting and analysis
which can even make improvement suggestions.

Example (open source) packages to use are: [JMeter](//jmeter.apache.org/)
