---
title: "Network"
linkTitle: "Network"
weight: 1
aliases: [
  "/documentation/en/webdriver/bidirectional/bidirectional_w3c/network",
]
---
## Commands

This section contains the APIs related to network commands. 

### Add network intercept

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkCommandsTest.java#L34-L41" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/network_spec.rb#L39-44" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/network_commands.spec.js#L28-L31" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_network.py#L6-20" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Remove network intercept

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkCommandsTest.java#L43-L50" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/network_commands.spec.js#L33-L39" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_network.py#L6-20" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Continue request blocked at authRequired phase with credentials

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkCommandsTest.java#L52-L66" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/network_commands.spec.js#L41-L53" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_network.py#L41-47" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Continue request blocked at authRequired phase without credentials

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkCommandsTest.java#L68-L82" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/network_commands.spec.js#L55-L68" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Cancel request blocked at authRequired phase

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkCommandsTest.java#L84-L95" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/network_commands.spec.js#L70-L81" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Fail request

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkCommandsTest.java#L97-L106" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Events

This section contains the APIs related to network events. 

### Before Request Sent

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkEventsTest.java#L28-L41" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/network_spec.rb#L11-23" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/network_events.spec.js#L22-L34" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_network.py#L6-20" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Response Started

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkEventsTest.java#L43-L58" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/network_spec.rb#L25-37" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/network_events.spec.js#L82-L93" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_network.py#L23-38" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Response Completed

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkEventsTest.java#L60-L75" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/network_events.spec.js#L95-L106" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_network.py#L6-20" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Auth Required

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/NetworkEventsTest.java#L99-L113" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/network_spec.rb#L50-57" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_network.py#L41-47" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


