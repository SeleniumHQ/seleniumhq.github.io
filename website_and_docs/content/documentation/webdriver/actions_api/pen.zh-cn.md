---
title: "Pen actions"
linkTitle: "Pen"
weight: 5
description: >
  A representation of a pen stylus kind of pointer input for interacting with a web page.
---

{{< badge-browser browser=Chromium wpt="perform_actions/pointer.py" >}}

A Pen is a type of pointer input that has most of the same behavior as a mouse, but can
also have event properties unique to a stylus. Additionally, while a mouse
has 5 buttons, a pen has 3 equivalent button states:

* 0 — Touch Contact (the default; equivalent to a left click)
* 2 — Barrel Button (equivalent to a right click)
* 5 — Eraser Button (currently unsupported by drivers)

## Using a Pen

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/PenTest.java#L26-L33" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_pen.py#L12-L20" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/PenTest.cs#L19-L28" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/pen_spec.rb#L11-L17" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/PenTest.kt#L23-L30" >}}
{{< /tab >}}
{{< /tabpane >}}

## Adding Pointer Event Attributes

{{< badge-version version="4.2" >}}

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/PenTest.java#L67-L81" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_pen.py#L53-L61" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/PenTest.cs#L64-L77" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/pen_spec.rb#L50-L56" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/PenTest.kt#L64-L78" >}}
{{< /tab >}}
{{< /tabpane >}}

