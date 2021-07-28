---
title: "Observability in Selenium 4"
linkTitle: "Observability in Selenium 4"
date: 2021-04-26
tags: ["selenium", "grid"]
categories: ["technical"]
author: Puja Jagani ([@pujagani](https://twitter.com/pujagani))
description: >
  Diagnosing problems when the Selenium server isn't working has never been easy.
---


Diagnosing problems when the Selenium server isn't working has never been easy. With Selenium 4, we have integrated [OpenTelemetry](https://opentelemetry.io/) to help you troubleshoot issues, optimize performance, and provide visibility into the system. By making the Selenium server observable, we are putting more power into your hands. 

### Need for Observability 

Selenium server enables distributed testing. Instead of running the browsers for tests locally, the tests use a remote browser driver that points to a server. The server makes “**smart**” decisions to run tests on different remote servers. Every such remote server is capable of hosting different types of browsers and browser versions. 

Selenium 3 uses Hub as the coordinator. It receives new session requests and directs them to the appropriate Node. The Node is the remote end where the browser itself runs. 

Selenium 4 extends this to provide users with a way to set up a full-blown distributed system. Essentially, [Selenium 4](https://www.selenium.dev/documentation/en/grid/grid_4/components_of_a_grid/) splits the Hub into different components with additional enhancements. However, the Node’s role remains the same. To ensure the backward compatibility and simplicity of setting up Grid, standalone and hub-node mode are also available in the new Selenium server. 

Now picture running a full-blown distributed Selenium Grid with hundreds of Nodes with different browsers and browser versions. Testing at scale in such an infrastructure will involve large volumes of requests. How can one keep track of these requests? Enter Selenium Observability! 

### How Does Selenium Provide Observability? 

Selenium uses OpenTelemetry to instrument tracing and event logs. Tracing keeps track of a request’s lifecycle. As a request moves through the distributed system, the trace of the request will contain all the information of its crucial operations performed along the way. Such operations are known as spans. A span can record timed logs called events, which ideally encapsulate the current state of the system. These are event logs. 

By default, the Selenium server enables tracing. Selenium server can run in different modes:
* [Standalone](https://www.selenium.dev/documentation/en/grid/grid_4/setting_up_your_own_grid/#standalone-mode)
* [Hub and Node](https://www.selenium.dev/documentation/en/grid/grid_4/setting_up_your_own_grid/#hub-and-node-mode)
* [Fully Distributed](https://www.selenium.dev/documentation/en/grid/grid_4/setting_up_your_own_grid/#distributed-mode)
* [Docker](https://github.com/SeleniumHQ/docker-selenium#readme)

Tracing and event logs are available for all the modes. The simplest way to see traces is in the form of console logs. By default, the Selenium server prints logs at the INFO level and above. To pass a logging level of choice while running a Selenium Grid use the [log-level](https://www.selenium.dev/documentation/en/grid/grid_4/advanced_features/observability/#visualizing-traces) flag. Setting the log-level to FINE will display traces and event logs as console logs.

For a large scale system, consuming traces as logs might not be efficient.
Visualizing and querying traces will quickly help troubleshoot a request failure easily. [Jaeger](https://www.jaegertracing.io/) seamlessly integrates with OpenTelemetry to provide a rich experience of querying, visualizing and collecting request traces. 

Run ``` java -jar selenium-server-<selenium-version>.jar info tracing```. 

It provides detailed and updated instructions to set up Jaeger with the Selenium server. 
Visualizing the traces makes it easy to interpret a trace and understand request flow. Now Grid users can effortlessly trace a request, drill down into an error or query them to observe what is happening.

{{<figure src="/images/blog/2021/trace.png" alt="Trace example">}}


Refer to [Selenium Observability](https://www.selenium.dev/documentation/en/grid/grid_4/advanced_features/observability/) for details. 

### Full-Stack Tracing
The Java client binding supports tracing. Full-stack tracing allows tracing a request from the client to the server and back. Though the client is a single component, the client trace will contain spans that help build the request. It is easier to locate a client-side problem and fix the test if needed. 

We already saw how the server supports tracing and how to leverage it. For the client-side, add the Opentelemetry dependency to your project setup and add the necessary system properties to export the traces to Jaeger. Refer to [RemoteWebdriver client](https://www.selenium.dev/documentation/en/remote_webdriver/remote_webdriver_client/#tracing-client-requests) for detailed instructions.  

### Leveraging Traces and Event Logs

The key to fixing a problem lies in knowing the error. For a known error situation, the event logs have you covered. It provides detailed error information and stack traces. Query the collected traces for a time range by the error code to identify the frequency of the error. It will help determine if the error is transient or not and accordingly take action. 

{{<figure src="/images/blog/2021/error-event-log.png" alt="Error event log example">}}


Observe the request latency to identify potential bottlenecks. Each trace will also contain time taken by each span. The issue could be in the underlying infrastructure setup, the network latency, or the code itself. Irrespective of the cause, it is easier to identify the problem area.

Observability is slowly becoming a must-have property of a system. Selenium is now observable! Go ahead try it out. Let us know if you find anything that requires improvement on our end.


