---
title: "Types of testing"
weight: 3
---

{{% notice info %}}
<i class="fas fa-language"></i> ページは英語から日本語へ訳されています。
日本語は話せますか？プルリクエストをして翻訳を手伝ってください!
{{% /notice %}}

## Acceptance testing
This type of test is done in order to determine if a product's
feature matches its requirements.
This generally involves the customer's feedback or specification.

For web applications, the automation of this testing can be done
directly with Selenium by simulating user expected behavior.
This simulation could be done by record/playback or through the
different supported languages as explained in this documentation.
Note: Acceptance testing is a subtype of **_functional testing_**,
which some people might also refer to.

### Functional testing
This type of test is done in order to determine if a product's
feature functions properly, without issues.

This generally include: the tests work without errors
(404, exceptions...), in an usable way (right redirections),   
in an accessible way and matching its specifications            
(see **_acceptance testing_** above).

For web applications, the automation of this testing can be
done directly with Selenium by simulating expected returns.     
This simulation could be done by record/playback or through     
the different supported languages as explainedin this documentation.

### Performance testing
As its name indicates, performance tests are done in order
to measure how well an application is performing.

There are two main sub-types for performance testing:

#### Load testing
Load testing is done in order to verify how well the
application works under different defined loads
(usually a particular number of users connected at once)

#### Stress testing
Stress testing is done in order to verify how well the
application works under stress (or above the maximum supported load).

Generally, performance tests are done by executing a
number of Selenium written tests simulating different users
hitting a particular function on the web app and
retrieving some meaningful measurements.

This is generally done by other tools that retrieve the metrics.
One such tools is **_JMeter_**.

For a web application, details to measure include:
throughput, latency, data loss, individual component loading times...

Note: All browsers have a performance tab in their
developers' tools section (accessible by pressing F12)

Note 2: is a subtype of **_non-functional testing_**
as this is generally measured per system and not per function/feature.

### Regression testing
This testing is generally done after a change, fix or feature addition.

In order to ensure that the change has not broken any of the existing
functionality, some already executed tests are executed again.

The set of re-executed test can be full or partial
and can include several different types, depending
on the application and development team.

### Test driven development (TDD)
Rather than a test type per se, TDD is an iterative
development methodology in which tests drive the design of a feature.

Each cycle starts by creating a set of unit tests that
the feature should pass (which should fail their first time executed).

After this, development takes place in order to make the tests pass.
The tests are executed again starting another cycle
and this process continues until all tests are passing.

This aims to speed up the development of an application
based on the fact that defects are less costly the earlier they are found.

### Behavior-driven development (BDD)
BDD is also an iterative development methodology
based on above (TDD) in which the goal is to involve
all the parties in the development of an application.

Each cycle starts by creating some specification
(which should fail). Then create the failing unit
tests (which should also fail) and then create the development.

This cycle is repeated until all type of tests are passing.

In order to do so, a specification language is
used. It should be understandable by all parties and
simple, standard and explicit.
Most tools use **_Gherkin_** as this language.

The goal is to be able to detect even more errors
than TDD by targeting potential acceptance errors
too and make communication between parties smoother.

A set of tools are currently available in order
to write the specifications and match them with code functions,
such as **_Cucumber_** or **_SpecFlow._**

A set of tools are built on top of Selenium to make this process
even faster by directly transform the BDD specifications into
executable code.
Some of these are: **_JBehave, Capybara and Robot Framework_**.
