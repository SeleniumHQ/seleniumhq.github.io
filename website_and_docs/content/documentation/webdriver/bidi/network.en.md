---
title: "WebDriver BiDi Network Features"
linkTitle: "Network"
weight: 1
description: >
  These features are related to networking, and are made available via a "network" namespace.
aliases: [
  "/documentation/en/webdriver/bidirectional/bidirectional_w3c/network",
  "/documentation/webdriver/bidirectional/webdriver_bidi/network"
]
---

The implementation of these features is being tracked here: [#13993](https://github.com/SeleniumHQ/selenium/issues/13993)

Remember that to use WebDriver BiDi, you must enable it in Options.
For more details, see [Enabling BiDi]({{< ref "BiDi" >}})

## Authentication Handlers

Authentication handlers enable you to intercept authentication requests that occur during a network interaction. 
These handlers are useful for automating scenarios involving authentication prompts, such as Basic Auth or Digest Auth. 
They allow you to programmatically provide credentials or modify the authentication flow.

### Add Handler

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/network_spec.rb#L7-L11" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Request Handlers

Request handlers allow you to intercept and manipulate outgoing network requests before they are sent to the server. 
This can be used to modify request headers, change the request body, or block specific requests. 
Request handlers are essential for testing and debugging scenarios where you need control over outgoing traffic.

### Add Handler

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Response Handlers

Response handlers enable you to intercept and manipulate incoming responses from the server. 
They are particularly useful for testing scenarios involving response data, such as verifying or modifying response headers, status codes, or content before it reaches the browser.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Remove Handler

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Clear Handlers

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}
