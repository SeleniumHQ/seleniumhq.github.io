---
title: "Browsing Context"
linkTitle: "Browsing Context"
weight: 1
aliases: [
  "/documentation/en/webdriver/bidirectional/bidirectional_w3c/browsing_context",
]
---

## Commands 
This section contains the APIs related to browsing context commands. 

### Open a new window

Creates a new browsing context in a new window.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L48-L52" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/browsing_context_spec.rb#L9-12" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L32-L37" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_browsing_context.py#L5-8" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Open a new tab

Creates a new browsing context in a new tab.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L54-L58" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/browsing_context_spec.rb#L14-17" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L47-L52" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_browsing_context.py#L11-14" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Use existing window handle

Creates a browsing context for the existing tab/window to run commands.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L41-L46" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/browsing_context_spec.rb#L5-12" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L24-L30" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_browsing_context.py#L32-41" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


### Open a window with a reference browsing context
A reference browsing context is a [top-level browsing context](https://html.spec.whatwg.org/multipage/document-sequences.html#top-level-browsing-context). 
The API allows to pass the reference browsing context, which is used to create a new window. The implementation is operating system specific.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L50-L55" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L39-L45" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Open a tab with a reference browsing context
A reference browsing context is a [top-level browsing context](https://html.spec.whatwg.org/multipage/document-sequences.html#top-level-browsing-context). 
The API allows to pass the reference browsing context, which is used to create a new tab. The implementation is operating system specific.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L64-L69" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L54-L60" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Navigate to a URL

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L60-L70" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/browsing_context_spec.rb#L19-30" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L62-L72" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_browsing_context.py#L17-29" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Navigate to a URL with readiness state

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L71-L82" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L74-L87" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Get browsing context tree

Provides a tree of all browsing contexts descending from the parent browsing context, including the parent browsing context.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L95-L108" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/browsing_context_spec.rb#L59-70" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L90-L96" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_browsing_context.py#L32-41" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Get browsing context tree with depth

Provides a tree of all browsing contexts descending from the parent browsing context, including the parent browsing context upto the depth value passed.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L111-L123" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L103-L109" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Get All Top level browsing contexts

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L114-L122" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.20.0" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L425-L432" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Close a tab/window

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L136-L153" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/browsing_context_spec.rb#L45-53" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L115-L118" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_browsing_context.py#L44-47" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Activate a browsing context

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.14.1" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L157-L161" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.14.1" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/browsing_context_spec.rb#L55-58" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L192-L194" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L204" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.14.1" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_browsing_context.py#L50-53" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


### Reload a browsing context

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.13.0" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L169-L173" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.13.0" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/browsing_context_spec.rb#L60-66" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L351" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.13.0" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_browsing_context.py#L56-66" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Handle user prompt

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.13.0" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L220-L228" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L301" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Capture Screenshot

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.13.0" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L248-L252" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L362" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Capture Viewport Screenshot

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.14.0" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L259-L268" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L165-L169" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Capture Element Screenshot

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.14.0" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L275-L280" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L184" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Set Viewport

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.14.1" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L304-L307" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L335" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


### Print page

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.14.1" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L317-L322" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L145-L157" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Navigate back

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.16.0" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L330-L336" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L396" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Navigate forward

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.16.0" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L342-L352" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L420" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Traverse history

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.16.0" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L359-L365" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L378" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Locate nodes

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/LocateNodesTest.java#L44-L53" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/locate_nodes_spec.rb#L9-15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/locateNodes.spec.js#L23-33" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_locate_nodes.py#L6-17" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Locate nodes with start nodes

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/LocateNodesTest.java#L137-L170" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/ruby/spec/bidi/locate_nodes_spec.rb#L25-36" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/locateNodes.spec.js#L136-L175" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.17" >}}
{{< gh-codeblock path="/examples/python/tests/bidi/test_bidi_locate_nodes.py#L32-48" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Events 
This section contains the APIs related to browsing context events. 

### Browsing Context Created Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L34-L41" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.9.2" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContextInspector.spec.js#L23-L28" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Dom Content loaded Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L65-L75" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.9.2" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContextInspector.spec.js#L53-L62" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Browsing Context Loaded Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L81-L91" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.9.2" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContextInspector.spec.js#L70-L78" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Navigated Started Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L97-L107" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Fragment Navigated Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L113-L126" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.15.0" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContextInspector.spec.js#L86-L97" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### User Prompt Opened Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L132-L144" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### User Prompt Closed Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L150-L164" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Browsing Context Destroyed Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L170-L183" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.18.0" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContextInspector.spec.js#L105-L113" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
