---
title: "Types of Testing"
linkTitle: "Testing Types"
weight: 2
aliases: [
"/documentation/en/introduction/types_of_testing/",
"/documentation/guidelines/types_of_testing/"
]
---

### Acceptance testing
This type of testing is done to determine if a feature or system
meets the customer expectations and requirements. 
This type of testing generally involves the customer's 
cooperation or feedback, being a validation activity that
answers the question:
>Are we building the **_right_** product?

For web applications, the automation of this testing can be done
directly with Selenium by simulating user expected behaviour.
This simulation could be done by record/playback or through the
different supported languages as explained in this documentation.
Note: Acceptance testing is a subtype of **_functional testing_**,
which some people might also refer to.
            
### Functional testing
This type of testing is done to determine if a
feature or system functions properly without issues. It checks
the system at different levels to ensure that all scenarios
are covered and that the system does _what_ it's 
supposed to do. It's a verification activity that
answers the question:
>Are we building the product **_right?_**
             
This generally includes: the tests work without errors 
(404, exceptions...), in a usable way (correct redirections),   
in an accessible way and matching its specifications 
(see **_acceptance testing_** above).

For web applications, the automation of this testing can be
done directly with Selenium by simulating expected returns.     
This simulation could be done by record/playback or through 
the different supported languages as explained in this documentation.

### Integration Tests

Integration tests verify the interactions between different components or modules of a system. Several modules are together tested. The purpose of Integration tests is to make sure that all modules integrate and work together as expected. Automated integration tests help ensure that these interactions work as expected and that integrated components function properly together.
>For example,  **_Testing the flow of placing the order for an item in an ecommerce website along with payment._**

### System Tests

System Testing is a complete fully integrated product Testing. It is an end-to-end testing where in testing environment is similar to the production environment. Here, we navigate through all the features of the software and test if the end business / end feature works. We just test the end feature and don’t check for data flow or do functional testing and all.
>For example,  **_Testing the end to end flow from login to placing an order and rechecking the order in My Orders page and logoff from an ecommerce website._**

### Performance testing
As its name indicates, performance tests are done 
to measure how well an application is performing.

There are two main sub-types for performance testing:

#### Load testing
Load testing is done to verify how well the 
application works under different defined loads 
(usually a particular number of users connected at once).

#### Stress testing
Stress testing is done to verify how well the
application works under stress (or above the maximum supported load).

Generally, performance tests are done by executing some 
Selenium written tests simulating different users 
hitting a particular function on the web app and 
retrieving some meaningful measurements. 

This is generally done by other tools that retrieve the metrics.
One such tool is **_JMeter_**.

For a web application, details to measure include 
throughput, latency, data loss, individual component loading times, etc.

Note 1: All browsers have a performance tab in their
developers' tools section (accessible by pressing F12)

Note 2: is a subtype of **_non-functional testing_**
as this is generally measured per system and not per function/feature.
            
### Regression testing
This testing is generally done after a change, fix or feature addition. 

To ensure that the change has not broken any of the existing 
functionality, some already executed tests are executed again. 
            
The set of re-executed tests can be full or partial
and can include several different types, depending
on the application and development team.
            
### Test driven development (TDD)
Rather than a test type _per se_, TDD is an iterative
development methodology in which tests drive the design of a feature.

Each cycle starts by creating a set of unit tests that
the feature should eventually pass (they should fail their first time executed).

After this, development takes place to make the tests pass. 
The tests are executed again, starting another cycle 
and this process continues until all tests are passing.

This aims to speed up the development of an application
based on the fact that defects are less costly the earlier they are found.

### Behavior-driven development (BDD)
BDD is also an iterative development methodology
based on the above TDD, in which the goal is to involve
all the parties in the development of an application.

Each cycle starts by creating some specifications 
(which should fail). Then create the failing unit 
tests (which should also fail) and then do the development. 

This cycle is repeated until all types of tests are passing.

In order to do so, a specification language is 
used. It should be understandable by all parties and 
simple, standard and explicit. 
Most tools use **_Gherkin_** as this language.

The goal is to be able to detect even more errors
than TDD, by targeting potential acceptance errors
too and make communication between parties smoother.

A set of tools are currently available 
to write the specifications and match them with code functions,
such as **_Cucumber_** or **_SpecFlow._**

A set of tools are built on top of Selenium to make this process
even faster by directly transforming the BDD specifications into 
executable code.
Some of these are **_JBehave, Capybara and Robot Framework_**.
            
