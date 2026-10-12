---
title: "Screenshots and PDFs"
linkTitle: "Screenshots and PDFs"
weight: 7
description: >
  Capturing the viewport, a single element or the whole page as an image, and printing a page to
  PDF.
---

A test fails on the CI server and the stack trace says an element was not found. What was on
the screen at the time? Without a picture, the first debugging step is to re-run the test
locally and hope it fails the same way. A screenshot taken at the moment of failure turns
that guess into evidence, and the same API is the basis of visual comparison and archiving.

## Recommended approach

WebDriver's screenshot command returns the current viewport as a PNG, Base64-encoded. Every
binding wraps that so you can save it straight to a file. Calling the same command on an
element rather than the driver crops the image to that element's bounding box, which is the
right choice when you want to compare a component rather than a whole page.

A viewport screenshot captures only what is visible without scrolling. Firefox supports a
full-page screenshot through its own driver API; with WebDriver BiDi enabled, the browsing
context module's `captureScreenshot` command works across browsers and can also capture a box
or an element.

To produce a PDF rather than an image, use the print command. It takes a `PrintOptions`
object for orientation, page ranges, size, margins, scale and whether to print background
graphics, and returns the document Base64-encoded, ready to decode and write to a file.

Attach a screenshot to every failure in your test framework's teardown hook: it is cheap,
and the one time you need it you will be glad it is there.

Take a screenshot of the viewport:

{{< tabpane text=true >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/windows.spec.js#L57" >}}
{{< /tab >}}
{{< /tabpane >}}

Take a screenshot of one element:

{{< tabpane text=true >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/windows.spec.js#L44-L46" >}}
{{< /tab >}}
{{< /tabpane >}}

Take a full-page screenshot in Firefox:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/browsers/FirefoxTest.java#L180" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/browsers/test_firefox.py#L139" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/browsers/firefox_spec.rb#L125" >}}
{{< /tab >}}
{{< /tabpane >}}

Capture a screenshot through BiDi:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/bidirectional/webdriver_bidi/BrowsingContextTest.java#L242" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/BiDi/BrowsingContext/BrowsingContextTest.CaptureScreenshot.cs#L12" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/bidirectional/browsingContext.spec.js#L364" >}}
{{< /tab >}}
{{< /tabpane >}}

Print the page to PDF:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/PrintsPageTest.java#L27-L29" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_prints_page.py#L13-L14" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L92-L93" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/windows.spec.js#L23" >}}
{{< /tab >}}
{{< /tabpane >}}

## Common errors

* [ScreenshotException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#screenshotexception" >}}):
  the browser could not capture the screen, usually because the window is minimised or the
  element is not rendered. Maximise the window or scroll the element into view.
* [StaleElementReferenceException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#staleelementreferenceexception" >}}):
  the element being captured was re-rendered between locating it and taking the screenshot.

## Reference

* [Browser window screenshots]({{< ref "/documentation/webdriver/interactions/windows.md#takescreenshot" >}}) and
  [element screenshots]({{< ref "/documentation/webdriver/interactions/windows.md#takeelementscreenshot" >}})
* [Firefox full-page screenshots]({{< ref "/documentation/webdriver/browsers/firefox.md#full-page-screenshots" >}})
* [BiDi browsing context]({{< ref "/documentation/webdriver/bidi/w3c/browsing_context.md#capture-screenshot" >}}): screenshot and print commands
* [Print page]({{< ref "/documentation/webdriver/interactions/print_page.md" >}}): every print option
