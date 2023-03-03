---
title: "Browsing Context"
linkTitle: "Browsing Context"
weight: 1
---

{{% pageinfo color="warning" %}}
<p class="lead">
   <i class="fas fa-language display-4"></i>
   Page being translated from
   English to Japanese. Do you speak Japanese? Help us to translate
   it by sending us pull requests!
</p>
{{% /pageinfo %}}

This section contains the APIs related to browsing context commands. 

## Open a new window

Creates a new browsing context in a new window.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/browsingcontext/BrowsingContextTest.java#L44-44" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Open a new tab

Creates a new browsing context in a new tab.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/browsingcontext/BrowsingContextTest.java#L58-58" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Use existing window handle

Creates a browsing context for the existing tab/window to run commands.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/browsingcontext/BrowsingContextTest.java#L37-38" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}


## Open a window with a reference browsing context
A reference browsing context is a [top-level browsing context](https://html.spec.whatwg.org/multipage/document-sequences.html#top-level-browsing-context). 
The API allows to pass the reference browsing context, which is used to create a new window. The implementation is operating system specific.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/browsingcontext/BrowsingContextTest.java#L50-52" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Open a tab with a reference browsing context
A reference browsing context is a [top-level browsing context](https://html.spec.whatwg.org/multipage/document-sequences.html#top-level-browsing-context). 
The API allows to pass the reference browsing context, which is used to create a new tab. The implementation is operating system specific.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/browsingcontext/BrowsingContextTest.java#L64-66" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Navigate to a URL

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/browsingcontext/BrowsingContextTest.java#L72-78" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Navigate to a URL with readiness state

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/browsingcontext/BrowsingContextTest.java#L83-90" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Get browsing context tree

Provides a tree of all browsing contexts descending from the parent browsing context, including the parent browsing context.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/browsingcontext/BrowsingContextTest.java#L95-106" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Get browsing context tree with depth

Provides a tree of all browsing contexts descending from the parent browsing context, including the parent browsing context upto the depth value passed.

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/browsingcontext/BrowsingContextTest.java#L111-121" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Get All Top level browsing contexts

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/browsingcontext/BrowsingContextTest.java#L126-131" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}

## Close a tab/window

{{< tabpane text=true langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/bidirectional/browsingcontext/BrowsingContextTest.java#L136-141" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< /tab >}}
{{< /tabpane >}}