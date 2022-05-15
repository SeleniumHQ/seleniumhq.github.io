---
title: "Scroll wheel actions"
linkTitle: "Wheel"
weight: 6
description: >
    A representation of a scroll wheel input device for interacting with a web page.
---

<p>
<span class="selenium-badge-version" data-toggle="tooltip" data-placement="right" 
title="This feature was introduced in Selenium 4.2">
Selenium v4.2</span></p>

<p><a href="https://wpt.fyi/results/webdriver/tests/perform_actions/wheel.py">
<span class="selenium-badge-browser" data-toggle="tooltip" data-placement="right" 
data-html="true" title="<p>This feature is not currently supported in all browsers</p>
<p>Click to see what's supported</p>">Chromium</span></a></p>

There are 5 main scenarios for scrolling on a page.

## Scroll to element 

This is the most common scenario. Unlike traditional click and send keys methods, 
the actions class does not automatically scroll the target element into view,
so this method will need to be used if elements are not already inside the viewport.

This method takes a web element as the sole argument.

Regardless of whether the element is above or below the current viewscreen, 
the viewport will be scrolled so the bottom of the element is at the bottom of the screen.

{{< tabpane disableCodeBlock=true >}}
    {{< tab header="Java" >}}
        {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L37" >}}
    {{< /tab >}}
    {{< tab header="Python" >}}
        {{< gh-codeblock path="examples/python/tests/actions_api/test_wheel.py#L12" >}}
    {{< /tab >}}
    {{< tab header="CSharp" >}}
        {{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsApi/WheelTest.cs#L35" >}}
    {{< /tab >}}
    {{< tab header="Ruby" >}}
        {{< gh-codeblock path="examples/ruby/spec/actions_api/wheel_spec.rb#L16" >}}
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
        Not implemented yet
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
        Not implemented yet
    {{< /tab >}}
{{< /tabpane >}}

## Scroll by given amount

This is the second most common scenario for scrolling. Pass in an delta x and a delta y value for how much to scroll
in the right and down directions. Negative values represent left and up, respectively. 

{{< tabpane disableCodeBlock=true >}}
    {{< tab header="Java" >}}
        {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L48" >}}
    {{< /tab >}}
    {{< tab header="Python" >}}
        {{< gh-codeblock path="examples/python/tests/actions_api/test_wheel.py#L22" >}}
    {{< /tab >}}
    {{< tab header="CSharp" >}}
        {{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsApi/WheelTest.cs#L47" >}}
    {{< /tab >}}
    {{< tab header="Ruby" >}}
        {{< gh-codeblock path="examples/ruby/spec/actions_api/wheel_spec.rb#L26" >}}
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
        Not implemented yet
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
        Not implemented yet
    {{< /tab >}}
{{< /tabpane >}}

## Scroll from an element by a given amount

This scenario is effectively a combination of the above two methods.

To execute this use the "Scroll From" method, which takes 3 arguments.
The first represents the origination point, which we designate as the element,
and the second two are the delta x and delta y values.

If the element is out of the viewport,
it will be scrolled to the bottom of the screen, then the page will be scrolled by the provided
delta x and delta y values.

{{< tabpane disableCodeBlock=true >}}
    {{< tab header="Java" >}}
        {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L57-L59" >}}
    {{< /tab >}}
    {{< tab header="Python" >}}
        {{< gh-codeblock path="examples/python/tests/actions_api/test_wheel.py#L30-L32" >}}
    {{< /tab >}}
    {{< tab header="CSharp" >}}
        {{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsApi/WheelTest.cs#L57-L62" >}}
    {{< /tab >}}
    {{< tab header="Ruby" >}}
        {{< gh-codeblock path="examples/ruby/spec/actions_api/wheel_spec.rb#L34-L36" >}}
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
        Not implemented yet
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
        Not implemented yet
    {{< /tab >}}
{{< /tabpane >}}

## Scroll from an element with an offset

This scenario is used when you need to scroll only a portion of the screen, and it is outside the viewport.
Or is inside the viewport and the portion of the screen that must be scrolled
is a known offset away from a specific element.

This uses the "Scroll From" method again, and in addition to specifying the element,
an offset is specified to indicate the origin point of the scroll. The offset is
calculated from the center of the provided element.

If the element is out of the viewport,
it first will be scrolled to the bottom of the screen, then the origin of the scroll will be determined
by adding the offset to the coordinates of the center of the element, and finally
the page will be scrolled by the provided delta x and delta y values.

Note that if the offset from the center of the element falls outside of the viewport,
it will result in an exception.

{{< tabpane disableCodeBlock=true >}}
    {{< tab header="Java" >}}
        {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L70-L72" >}}
    {{< /tab >}}
    {{< tab header="Python" >}}
        {{< gh-codeblock path="examples/python/tests/actions_api/test_wheel.py#L42-L44" >}}
    {{< /tab >}}
    {{< tab header="CSharp" >}}
        {{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsApi/WheelTest.cs#L74-L81" >}}
    {{< /tab >}}
    {{< tab header="Ruby" >}}
        {{< gh-codeblock path="examples/ruby/spec/actions_api/wheel_spec.rb#L46-L48" >}}
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
        Not implemented yet
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
        Not implemented yet
    {{< /tab >}}
{{< /tabpane >}}

## Scroll from a offset of origin (element) by given amount

The final scenario is used when you need to scroll only a portion of the screen,
and it is already inside the viewport.

This uses the "Scroll From" method again, but the viewport is designated instead
of an element. An offset is specified from the upper left corner of the
current viewport. After the origin point is determined, 
the page will be scrolled by the provided delta x and delta y values.

Note that if the offset from the upper left corner of the viewport falls outside of the screen,
it will result in an exception.

{{< tabpane disableCodeBlock=true >}}
    {{< tab header="Java" >}}
        {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L83-L85" >}}
    {{< /tab >}}
    {{< tab header="Python" >}}
        {{< gh-codeblock path="examples/python/tests/actions_api/test_wheel.py#L54-L56" >}}
    {{< /tab >}}
    {{< tab header="CSharp" >}}
        {{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsApi/WheelTest.cs#L93-L100" >}}
    {{< /tab >}}
    {{< tab header="Ruby" >}}
        {{< gh-codeblock path="examples/ruby/spec/actions_api/wheel_spec.rb#L58-L60" >}}
    {{< /tab >}}
    {{< tab header="JavaScript" >}}
        Not implemented yet
    {{< /tab >}}
    {{< tab header="Kotlin" >}}
        Not implemented yet
    {{< /tab >}}
{{< /tabpane >}}
