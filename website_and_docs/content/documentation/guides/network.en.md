---
title: "Network interception and mocking"
linkTitle: "Network"
weight: 8
description: >
  Watching the requests a page makes, blocking or failing some of them, and answering
  authentication challenges, using the W3C WebDriver BiDi network module.
---

You want to assert that the page called an API, fail a request to see how the application
copes, block the analytics beacon that slows every test down, or supply credentials to a
basic authentication challenge. Plain WebDriver only sees the DOM; it has no view of the
traffic behind it.

## Recommended approach

Use the network module of WebDriver BiDi. It is part of the W3C standard, it works in every
browser that implements BiDi, and it is the Selenium project's supported route for anything
that needs to observe or change network traffic.

The module has two halves. _Events_ (`beforeRequestSent`, `responseStarted`,
`responseCompleted`, `authRequired`) let you subscribe to traffic and record or assert on it.
_Intercepts_ pause requests at a chosen phase so your handler can continue them, fail them,
or, at the `authRequired` phase, continue them with a username and password. Enable BiDi in
the browser options when starting the session; the reference page shows the capability for
each binding.

Keep interception narrow. Match the URL patterns you care about, remove the intercept when
the test is done, and prefer failing or stubbing an external dependency over letting the test
talk to a live third-party service.

The Chrome DevTools Protocol integration that Selenium offered before BiDi existed is
Chromium-only, tied to specific browser versions, and being phased out. Do not start new work
on it.

Subscribe to requests as they are sent:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkEventsTest.java#L30-L35" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_network_commands.py#L68-L71" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/network_events.spec.js#L24-L27" >}}
{{< /tab >}}
{{< /tabpane >}}

Intercept a request and fail it:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkCommandsTest.java#L99-L101" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_network_commands.py#L46-L49" >}}
{{< /tab >}}
{{< /tabpane >}}

Answer a basic authentication challenge:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkCommandsTest.java#L55-L60" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_network_commands.py#L82" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/network_commands.spec.js#L43-L45" >}}
{{< /tab >}}
{{< /tabpane >}}

## Common errors

* [TimeoutException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#timeoutexception" >}}):
  an intercepted request was never continued or failed, so navigation hung until the page load
  timeout. Every intercepted request needs a handler that resolves it.
* [UnsupportedCommandException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#unsupportedcommandexception" >}}):
  BiDi was not enabled for the session, or the browser does not implement the command yet.

## Reference

* [BiDi network]({{< ref "/documentation/webdriver/bidi/w3c/network.md" >}}): all commands and events
* [Enabling BiDi]({{< ref "/documentation/webdriver/bidi/_index.md#enabling-bidi-in-selenium" >}})
* [Mock external services]({{< ref "/documentation/test_practices/encouraged/mock_external_services.md" >}})
* [HTTP response codes]({{< ref "/documentation/test_practices/discouraged/http_response_codes.md" >}}): why WebDriver does not expose them directly
