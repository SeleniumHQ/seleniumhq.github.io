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
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L44-L47" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L33-L35" >}}
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
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L58-L61" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L48-L50" >}}
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
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L37-L41" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L25-L28" >}}
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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L40-L43" >}}
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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L55-L58" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Navigate to a URL

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L72-L80" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L67" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Navigate to a URL with readiness state

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L83-L92" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L79-L82" >}}
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
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L90-L96" >}}
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
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L126-L133" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.20.0" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L426-L431" >}}
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
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L115-L118" >}}
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
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L192-L194" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L204" >}}
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
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L351" >}}
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
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Dom Content loaded Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L54-L63" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.9.2" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContextInspector.spec.js#L53-L62" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Browsing Context Loaded Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.10" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L81-88" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.9.2" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContextInspector.spec.js#L70-L78" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Navigated Started Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L97-104" >}}
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

### Fragment Navigated Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L113-123" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.15.0" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContextInspector.spec.js#L86-L97" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### User Prompt Opened Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L113-123" >}}
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

### User Prompt Closed Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.15" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L150-163" >}}
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

### Browsing Context Destroyed Event

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.18" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextInspectorTest.java#L170-L181" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.18.0" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContextInspector.spec.js#L105-L113" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
