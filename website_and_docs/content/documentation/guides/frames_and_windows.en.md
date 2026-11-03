---
title: "Frames and windows"
linkTitle: "Frames and Windows"
weight: 6
description: >
  Reaching elements inside an iframe, following a link that opens a new tab or window, and
  getting back to where you started.
---

The element is plainly visible on the page, the locator is correct, and `findElement` still
reports that it does not exist. The element is inside an `<iframe>`, or on a tab the link just
opened, and WebDriver is still looking at the document it started in.

## Recommended approach

WebDriver always has exactly one _current browsing context_: one window or tab, and within it
one frame. Commands only see that context. Switching is explicit, and you are responsible for
switching back.

**Frames.** Locate the `<iframe>` element like any other element and switch to it. You can also
switch by the frame's `name` or `id`, or by index, but a located element is the least
ambiguous. When you are done, switch to the default content to return to the top-level
document. A frame reference goes stale when the page reloads, so switch again after navigation.

**Windows and tabs.** Each window or tab has a _handle_, an opaque string. Record the current
handle before clicking a link that opens a new one, wait until the number of handles grows,
switch to the handle you have not seen before, and switch back to the original handle when
you are finished. WebDriver makes no distinction between a tab and a window. If you just need
a clean tab, open one yourself with `newWindow`; it is created already switched to.

Closing the current window leaves the session pointing at nothing, so switch to another
handle immediately after `close`. Call `quit`, not `close`, at the end of the test; it closes
every window and ends the session.

Switch into a frame using a located element:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/FramesTest.java#L39-L41" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_frames.py#L25-L26" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/FramesTest.cs#L39-L41" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/frames_spec.rb#L26-L27" >}}
{{< /tab >}}
{{< /tabpane >}}

Return to the top-level document:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/FramesTest.java#L67" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_frames.py#L49" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/FramesTest.cs#L67" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/frames_spec.rb#L49" >}}
{{< /tab >}}
{{< /tabpane >}}

Follow a link into a new window and switch to it:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/WindowsTest.java#L22-L29" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_windows.py#L25-L31" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/WindowsTest.cs#L23-L30" >}}
{{< /tab >}}
{{< /tabpane >}}

Open a fresh tab and switch to it in one step:

{{< tabpane text=true >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/windows_spec.rb#L9" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/windows.spec.js#L70" >}}
{{< /tab >}}
{{< /tabpane >}}

## Common errors

* [NoSuchElementException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#nosuchelementexception" >}}):
  the element exists but in a different frame or window from the current one.
* [NoSuchFrameException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#nosuchframeexception" >}}):
  the frame name, id or index did not match, or the frame was removed by a navigation.
* [NoSuchWindowException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#nosuchwindowexception" >}}):
  the window you were in was closed. Switch to a handle that still exists.
* [StaleElementReferenceException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#staleelementreferenceexception" >}}):
  an element from the old frame or page is being used after a switch or reload.

## Reference

* [Working with iframes and frames]({{< ref "/documentation/webdriver/interactions/frames.md" >}})
* [Working with windows and tabs]({{< ref "/documentation/webdriver/interactions/windows.md" >}})
* [BiDi browsing context]({{< ref "/documentation/webdriver/bidi/w3c/browsing_context.md" >}}): creating, inspecting and closing contexts with events
