---
title: "Actions API"
linkTitle: "Actions API"
weight: 14
description: >
    A low-level interface for providing virtualized device input actions to the web browser.
---

In addition to the high-level [element interactions]({{< ref "/documentation/webdriver/elements/interactions.md" >}}), 
the [Actions API](https://w3c.github.io/webdriver/#dfn-actions) provides granular control over
exactly what designated input devices can do. Selenium provides an interface for 3 kinds of input sources: 
a key input for keyboard devices, a pointer input for a mouse, pen or touch devices, 
and wheel inputs for scroll wheel devices (introduced in Selenium 4.2). 
Selenium allows you to construct individual action commands assigned to specific
inputs and chain them together and call the associated perform method to execute them all at once.

## Action Builder

In the move from the legacy JSON Wire Protocol to the new W3C WebDriver Protocol,
the low level building blocks of actions became especially detailed. It is extremely
powerful, but each input device has a number of ways to use it and if you need to 
manage more than one device, you are responsible for ensuring proper synchronization between them.

Thankfully, you likely do not need to learn how to use the low level commands directly, since
almost everything you might want to do has been given a convenience method that combines the 
lower level commands for you. These are all documented in 
[keyboard]({{< ref "keyboard" >}}), [mouse]({{< ref "mouse" >}}), [pen]({{< ref "pen" >}}), and [wheel]({{< ref "wheel" >}}) pages.

## Pause

Pointer movements and Wheel scrolling allow the user to set a duration for the action, but sometimes you just need
to wait a beat between actions for things to work correctly.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/ActionsTest.java#L21-L28" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_actions.py#L13-L20" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/ActionsTest.cs#L18-L25" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/actions_spec.rb#L12-L19" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/actionsTest.spec.js#L20-L27" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/ActionsTest.kt#L22-L29" >}}
{{< /tab >}}
{{< /tabpane >}}

## Release All Actions

An important thing to note is that the driver remembers the state of all the input
items throughout a session. Even if you create a new instance of an actions class, the depressed keys and
the location of the pointer will be in whatever state a previously performed action left them.

There is a special method to release all currently depressed keys and pointer buttons.
This method is implemented differently in each of the languages because
it does not get executed with the perform method.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/ActionsTest.java#L46" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_actions.py#L37" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/ActionsTest.cs#L44" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/actions_spec.rb#L36" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/actionsTest.spec.js#L44" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/ActionsTest.kt#L47" >}}
{{< /tab >}}
{{< /tabpane >}}
