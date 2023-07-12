---
title: "可观测性"
linkTitle: "可观测性"
weight: 1
aliases: ["/documentation/zh-cn/grid/grid_4/advanced_features/observability/"]
---


{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language d-4"></i> 
   Page being translated from 
   English to Chinese. Do you speak Chinese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}


## Table of Contents
 - [Selenium Grid](#selenium-grid)
 - [Observability](#observability)
	 - [Distributed tracing](#distributed-tracing) 	
	 -  [Event logging](#event-logging)
  - [Grid Observability](#grid-observability)
	  - [Visualizing Traces](#visualizing-traces)
	  - [Leveraging event logs](#leveraging-event-logs)
  - [References](#references)

## Selenium Grid

Grid aids in scaling and distributing tests by executing tests on various browser and operating system combinations.

## Observability

Observability has three pillars: traces, metrics and logs. Since Selenium Grid 4 is designed to be fully distributed, observability will make it easier to understand and debug the internals. 

## Distributed tracing
A single request or transaction spans multiple services and components.  Tracing tracks the request lifecycle as each service executes the request. It is useful in debugging in an error scenario.
Some key terms used in tracing context are: 

**Trace**
Tracing allows one to trace a request through multiple services, starting from its origin to its final destination. This request's journey helps in debugging, monitoring the end-to-end flow, and identifying failures. A trace depicts the end-to-end request flow. Each trace has a unique id as its identifier.

**Span**
Each trace is made up of timed operations called spans. A span has a start and end time and it represents operations done by a service. The granularity of span depends on how it is instrumented. Each span has a unique identifier.  All spans within a trace have the same trace id.

**Span Attributes**
Span attributes are key-value pairs which provide additional information about each span.

**Events**
Events are timed-stamped logs within a span. They provide additional context to the existing spans. Events also contain key-value pairs as event attributes.

## Event logging

Logging is essential to debug an application. Logging is often done in a human-readable format. But for machines to search and analyze the logs, it has to have a well-defined format. Structured logging is a common practice of recording logs consistently in a fixed format. It commonly contains fields like:
 * Timestamp
 * Logging level
 * Logger class
 * Log message (This is further broken down into fields relevant to the operation where the log was recorded)

Logs and events are closely related. Events encapsulate all the possible information available to do a single unit of work. Logs are essentially subsets of an event. At the crux, both aid in debugging.
Refer following resources for detailed understanding:
 1. [https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/](https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/)
 2. [https://charity.wtf/2019/02/05/logs-vs-structured-events/](https://charity.wtf/2019/02/05/logs-vs-structured-events/)

## Grid Observability

Selenium server is instrumented with tracing using OpenTelemetry. Every request to the server is traced from start to end. Each trace consists of a series of spans as a request is executed within the server. 
Most spans in the Selenium server consist of two events:
1. Normal event - records all information about a unit of work and marks successful completion of the work.
2. Error event - records all information till the error occurs and then records the error information. Marks an exception event.

Running Selenium server 
 1. [Standalone](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#standalone-mode)
 2. [Hub and Node](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#hub-and-node)
 3. [Fully Distributed](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#fully-distributed)
 4. [Docker](https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4#using-docker)

## Visualizing Traces
All spans, events and their respective attributes are part of a trace. Tracing works while running the server in all of the above-mentioned modes.

By default, tracing is enabled in the Selenium server. Selenium server exports the traces via two exporters:
1. Console - Logs all traces and their included spans at FINE level. By default, Selenium server prints logs at INFO level and above. 
The **log-level** flag can be used to pass a logging level of choice while running the Selenium Grid jar/s.
```shell
java -jar selenium-server-4.0.0-<selenium-version>.jar standalone --log-level FINE
```
2. Jaeger UI - OpenTelemetry provides the APIs and SDKs to instrument traces in the code. Whereas Jaeger is a tracing backend, that aids in collecting the tracing telemetry data and providing querying, filtering and visualizing features for the data.

Detailed instructions of visualizing traces using Jaeger UI can be obtained by running the command :

```shell
java -jar selenium-server-4.0.0-<selenium-version>.jar info tracing
```

[A very good example and scripts to run the server and send traces to Jaeger](https://github.com/manoj9788/tracing-selenium-grid)

## Leveraging event logs
Tracing has to be enabled for event logging as well, even if one does not wish to export traces to visualize them.  
**By default, tracing is enabled. No additional parameters need to be passed to see logs on the console.**
All events within a span are logged at FINE level. Error events are logged at WARN level.

All event logs have the following fields :
 | Field | Field value | Description |
|-|-|-|
| Event time | eventId | Timestamp of the event record in epoch nanoseconds. |
| Trace Id  | tracedId | Each trace is uniquely identified by a trace id. |
| Span Id  | spanId | Each span within a trace is uniquely identified by a span id. |
| Span Kind | spanKind | Span kind is a property of span indicating the type of span. It helps in understanding the nature of the unit of work done by the Span. |
| Event name | eventName | This maps to the log message. |
| Event attributes | eventAttributes | This forms the crux of the event logs, based on the operation executed, it has JSON formatted key-value pairs. This also includes a handler class attribute, to show the logger class. |

 Sample log  

 

    FINE [LoggingOptions$1.lambda$export$1] - {
      "traceId": "fc8aef1d44b3cc8bc09eb8e581c4a8eb",
      "spanId": "b7d3b9865d3ddd45",
      "spanKind": "INTERNAL",
      "eventTime": 1597819675128886121,
      "eventName": "Session request execution complete",
      "attributes": {
        "http.status_code": 200,
        "http.handler_class": "org.openqa.selenium.grid.router.HandleSession",
        "http.url": "\u002fsession\u002fdd35257f104bb43fdfb06242953f4c85",
        "http.method": "DELETE",
        "session.id": "dd35257f104bb43fdfb06242953f4c85"
      }
    }
    
In addition to the above fields, based on [OpenTelemetry specification](https://github.com/open-telemetry/opentelemetry-specification/blob/master/specification/trace/semantic_conventions/exceptions.md) error logs consist of :
| Field | Field value | Description |
|-|-|-|
| Exception type  | exception.type | The class name of the exception. |
| Exception message  | exception.message | Reason for the exception. |
| Exception stacktrace | exception.stacktrace | Prints the call stack at the point of time when the exception was thrown. Helps in understanding the origin of the exception. |
 

Sample error log 
  

    WARN [LoggingOptions$1.lambda$export$1] - {
      "traceId": "7efa5ea57e02f89cdf8de586fe09f564",
      "spanId": "914df6bc9a1f6e2b",
      "spanKind": "INTERNAL",
      "eventTime": 1597820253450580272,
      "eventName": "exception",
      "attributes": {
        "exception.type": "org.openqa.selenium.ScriptTimeoutException",
        "exception.message": "Unable to execute request: java.sql.SQLSyntaxErrorException: Table 'mysql.sessions_mappa' doesn't exist ..." (full message will be printed),
        "exception.stacktrace": "org.openqa.selenium.ScriptTimeoutException: java.sql.SQLSyntaxErrorException: Table 'mysql.sessions_mappa' doesn't exist\nBuild info: version: '4.0.0-alpha-7', revision: 'Unknown'\nSystem info: host: 'XYZ-MacBook-Pro.local', ip: 'fe80:0:0:0:10d5:b63a:bdc6:1aff%en0', os.name: 'Mac OS X', os.arch: 'x86_64', os.version: '10.13.6', java.version: '11.0.7'\nDriver info: driver.version: unknown ...." (full stack will be printed),
        "http.handler_class": "org.openqa.selenium.grid.distributor.remote.RemoteDistributor",
        "http.url": "\u002fsession",
        "http.method": "POST"
      }
    }

Note: Logs are pretty printed above for readability. Pretty printing for logs is turned off in Selenium server.

The steps above should set you up for seeing traces and logs.
 
## References 
1. [Understanding Tracing](https://lightstep.com/blog/opentelemetry-101-what-is-tracing/)
2. [OpenTelemetry Tracing API Specification](https://github.com/open-telemetry/opentelemetry-specification/blob/master/specification/trace/api.md#status)
3. [Selenium Wiki](https://github.com/SeleniumHQ/selenium/wiki)
4. [Structured logs vs events](https://www.honeycomb.io/blog/how-are-structured-logs-different-from-events/)
5. [Jaeger framework](https://github.com/jaegertracing/jaeger)
