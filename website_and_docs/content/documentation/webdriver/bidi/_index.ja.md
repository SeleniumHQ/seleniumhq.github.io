---
title: "双方向機能"
linkTitle: "BiDi"
weight: 16
aliases: [
"/documentation/en/webdriver/bidi_apis/",
"/documentation/webdriver/bidi_apis/",
"/documentation/webdriver/bidirectional/bidi_api_remotewebdriver",
"/documentation/webdriver/bidirectional/webdriver_bidi",
"/documentation/webdriver/bidirectional/webdriver_bidi/browsing_context",
"/documentation/webdriver/bidirectional/webdriver_bidi/input"
]
---

BiDirectional means that communication is happening in two directions simultaneously.
The traditional WebDriver model involves strict request/response commands which only allows for communication to
happen in one direction at any given time. In most cases this is what you want; it ensures that the browser is
doing the expected things in the right order, but there are a number of interesting things that can be done with 
asynchronous interactions.

This functionality is currently available in a limited fashion with the [Chrome DevTools Protocol] (CDP), 
but to address some of its drawbacks, the Selenium team, along with the major
browser vendors, have worked to create the new [WebDriver BiDi Protocol](https://w3c.github.io/webdriver-bidi/).
This specification aims to create a stable, cross-browser API that leverages bidirectional
communication for enhanced browser automation and testing functionality, 
including streaming events from the user agent to the controlling software via WebSockets.
Users will be able to listen for and record or manipulate events as they happen during the course of a Selenium session.

### Enabling BiDi in Selenium

In order to use WebDriver BiDi, setting the capability in the browser options will enable the required functionality:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
options.setCapability("webSocketUrl", true);
{{< /tab >}}
{{< tab header="Python" >}}
options.enable_bidi = True
{{% /tab %}}
{{< tab header="CSharp" >}}
UseWebSocketUrl = true,
{{< /tab >}}
{{< tab header="Ruby" >}}
options.web_socket_url = true
{{< /tab >}}
{{< tab header="JavaScript" >}}
Options().enableBidi();
{{< /tab >}}
{{< tab header="Kotlin" >}}
options.setCapability("webSocketUrl", true);
{{< /tab >}}
{{< /tabpane >}}

This enables the WebSocket connection for bidirectional communication, 
unlocking the full potential of the WebDriver BiDi protocol.

Note that Selenium is updating its entire implementation from WebDriver Classic to WebDriver BiDi (while
maintaining backwards compatibility as much as possible), but this section of documentation focuses on the new
functionality that bidirectional communication allows. 
The low-level BiDi domains will be accessible in the code to the end user, but the goal is to provide
high-level APIs that are straightforward methods of real-world use cases. As such, the low-level
components will not be documented, and this section will focus only on the user-friendly
features that we encourage users to take advantage of.

If there is additional functionality you'd like to see, please raise a
[feature request](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=&template=feature.md).

