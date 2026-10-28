---
title: "Logging in WebDriver"
linkTitle: "Logging"
weight: 9
description: >
    The original design for log types, levels and retrieval in the JSON Wire Protocol.
---

This documentation previously located [on the wiki](https://github.com/SeleniumHQ/selenium/wiki/Logging) \
For current information, read the [logging documentation]({{< ref "/documentation/webdriver/troubleshooting/logging" >}}).

Logging is important both for developers and users of WebDriver. Users might need to be 
able to find details about why a test failed, while developers might need to be able to 
find details of why WebDriver in itself failed. Good logging support can make these 
debugging tasks much easier.

## Log Types

WebDriver may be used in different kinds of configurations. For instance, a client running 
a test may be connected _directly_ to a driver controlling a browser, or a client may be 
connected _indirectly_ to a driver via a server. Depending on the configuration different 
kinds of logs may be available. With this in mind, the system should provide means to 
query for available log types.

In general, to debug a certain configuration it is useful to have access to logs from each 
of the _nodes_ in the configuration. That is, from the **client**, **server**, **driver**, 
and the **browser**. Still, logs for all these nodes may not be available in all 
configurations. The server log being the simple example, but logs may also not be 
supported, or possible to retrieve due to the browser. In addition, there may be more logs 
available than the mentioned baseline. For instance, there may be support for logs 
specifically collecting performance data.

The known log types are:

| **Log type**    | **Meaning** |
|:----------------|:------------|
| browser         | Javascript console logs from the browser |
| client          | Logs from the client side implementation of the WebDriver protocol (e.g. the Java bindings) |
| driver          | Logs from the internals of the driver (e.g. FirefoxDriver internals) |
| performance     | Logs relating to the performance characteristics of the page under test (e.g. resource load timings) |
| server          | Logs from within the selenium server. |

For more information on which command to use to retrieve available log types, and a 
baseline of general log types, please view the 
[wire protocol]({{< ref "../json_wire_protocol" >}}).

## Logs

A log corresponds to a list of log entries, where each log entry is a tuple containing a 
timestamp, a log level, and a message.

### Log Levels

The purpose of log levels is to provide a means to filter the messages in the log based on 
level of interest. For instance, if the purpose is debugging then more or less all messages 
may be of interest, while if the purpose is general monitoring then less information may 
be needed.

For WebDriver the following log levels have been selected (ordered from highest to lowest):

* **OFF**: Turns off logging
* **SEVERE**: Messages about things that went wrong. For instance, an unknown command.
* **WARNING**: Messages about things that may be wrong but was handled. For instance, a handled exception.
* **INFO**: Messages of an informative nature. For instance, information about received commands.
* **DEBUG**: Messages for debugging. For instance, information about the state of the driver.
* **ALL**: All log messages. A way to collect all information regardless of which log levels that are supported.

Languages like Java and Python typically provide a logging API with its own log levels, 
each with a name and a number, while the WebDriver log levels described above are 
described with names in a certain order (and no numbers). Driver implementors may use the 
log levels of their host language, but should translate the log levels of the host 
language to WebDriver log levels before sending logs over the wire.

Please see the [wire protocol]({{< ref "../json_wire_protocol" >}}) for more details.

### Log Message

In general, a log message is just a string. Still, some log types may have a need for more 
structured information, for instance, to separate between different kinds of log messages. 
For such log types, the message part of a log entry may be structured as a JSON object. 
For cases where this is the practice it should be clear from the documentation.

## Retrieval of Logs

Provided that a log type is supported, it should be possible to retrieve the corresponding 
log. For remote logs on a different node, retrieval involves the use of the 
[wire protocol]({{< ref "../json_wire_protocol" >}}). In the scenario where a log is 
retrieved several times the same log entries should not be retrieved more than once. 
For this reason, and to save memory, after each retrieval of a log the log buffer of that 
log is reset.

## Configuration of Logging Behavior

There may be cases where logging should be turned off entirely, or the number of logged 
messages should be fewer. For instance, a driver may be running on a device with limited 
resources. To support such cases it should be possible to configure logging behavior as a 
capability. A logging configuration of this kind corresponds to a list of pairs where log 
types are mapped to log levels. Since OFF is included as a log level a means for turning 
off logging is provided.

The default behavior should be to collect all log messages and let the client do filtering 
as needed. For cases where a different log level has been configured for a log type, 
messages under that log level should not be collected.

Please see the [capabilities page]({{< ref "../desired_capabilities" >}}) for more 
information about logging configuration.

## Browser Specific Behavior

### Firefox

#### Merging of the Browser and Driver Logs

The Firefox driver provides an additional capability to configure whether the browser and 
driver log should be merged. In practice, this means that the driver log entries are 
printed to the error console of the browser, in addition to being collected elsewhere.

The default behavior is to merge the logs, the reason being that the error console of the 
browser provides a simple way to get a merged graphical view of the joined logs.

Please see the [capabilities page]({{< ref "../desired_capabilities" >}}) for more 
information about Firefox specific capabilities.
