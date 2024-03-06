---
title: "Browsing Context"
linkTitle: "Browsing Context"
weight: 1
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language d-4"></i>
   Page being translated from
   English to Japanese. Do you speak Japanese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

This section contains the APIs related to browsing context commands. 

## Open a new window

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L31-L33" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Open a new tab

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L46-L47" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Use existing window handle

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L23-L26" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## Open a window with a reference browsing context
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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L38-L41" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Open a tab with a reference browsing context
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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L53-L56" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Navigate to a URL

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L65-L65" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Navigate to a URL with readiness state

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L76-L79" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Get browsing context tree

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L89-L94" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Get browsing context tree with depth

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L101-L107" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Get All Top level browsing contexts

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.8" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L126-L133" >}}
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

## Close a tab/window

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L113-L116" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Activate a browsing context

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L189-L202" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## Reload a browsing context

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L353-L353" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Handle user prompt

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L299-L299" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Capture Screenshot

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L364-L364" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Capture Viewport Screenshot

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L163-L167" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Capture Element Screenshot

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L182-L182" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Set Viewport

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L333-L333" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}


## Print page

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L143-L155" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Navigate back

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L398-L398" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Navigate forward

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L422-L422" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Traverse history

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
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L380-L380" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}