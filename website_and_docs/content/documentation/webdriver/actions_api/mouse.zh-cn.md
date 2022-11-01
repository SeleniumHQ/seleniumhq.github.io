---
title: "Mouse actions"
linkTitle: "Mouse"
weight: 4
needsTranslation: true
description: >
  A representation of any pointer device for interacting with a web page.
aliases: [
"/documentation/zh-cn/support_packages/mouse_and_keyboard_actions_in_detail/",
"/zh-cn/documentation/support_packages/mouse_and_keyboard_actions_in_detail/"
]
---

There are only 3 actions that can be accomplished with a mouse:
pressing down on a button, releasing a pressed button, and moving the mouse.
Selenium provides convenience methods that combine these actions in the most common ways.

## Click and hold

This method combines moving the mouse to the center of an element with pressing the left mouse button.
This is useful for focusing a specific element:

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L22-L25" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L12-L15" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L17-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L11-L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/clickAndHold.spec.js#L16-L18" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L23-L26" >}}
{{< /tab >}}
{{< /tabpane >}}

## Click and release

This method combines moving to the center of an element with pressing and releasing the left mouse button.
This is otherwise known as "clicking":

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L34-L37" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L24-L27" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L30-L33" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L22-L25" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/clickAndRelease.spec.js#L16-L18" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L35-L38" >}}
{{< /tab >}}
{{< /tabpane >}}

## Alternate Button Clicks

There are a total of 5 defined buttons for a Mouse:
* 0 — Left Button (the default)
* 1 — Middle Button (currently unsupported)
* 2 — Right Button
* 3 — X1 (Back) Button
* 4 — X2 (Forward) Button

### Context Click

This method combines moving to the center of an element with pressing and releasing the right mouse button (button 2).
This is otherwise known as "right-clicking":

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L46-L49" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L35-L38" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L43-L46" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L34-L37" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/rightClick.spec.js#L17-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L47-L50" >}}
{{< /tab >}}
{{< /tabpane >}}

### Back Click

There is no convenience method for this, it is just pressing and releasing mouse button 3

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L60-L66" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L49-L52" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L59-L63" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L47-L50" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.5.0" >}}     
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/backAndForwardClick.spec.js#L21-L22" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L61-L67" >}}
{{< /tab >}}
{{< /tabpane >}}

### Forward Click

There is no convenience method for this, it is just pressing and releasing mouse button 4

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L78-L84" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L63-L66" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L77-L81" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L61-L64" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.5.0" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/backAndForwardClick.spec.js#L34-L35" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L79-L85" >}}
{{< /tab >}}
{{< /tabpane >}}

## Double click

This method combines moving to the center of an element with pressing and releasing the left mouse button twice.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L93-L96" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L74-L77" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L91-L94" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L73-L76" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/doubleClick.spec.js#L17-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L94-L97" >}}
{{< /tab >}}
{{< /tabpane >}}

## Move to element

This method moves the mouse to the in-view center point of the element. 
This is otherwise known as "hovering."
Note that the element must be in the viewport or else the command will error.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L105-L108" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L85-L88" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L104-L107" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L84-L87" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/moveToElement.spec.js#L17-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L106-L109" >}}
{{< /tab >}}
{{< /tabpane >}}

## Move by offset

These methods first move the mouse to the designated origin and then
by the number of pixels in the provided offset.
Note that the position of the mouse must be in the viewport or else the command will error.

### Offset from Element (Top Left Origin)

This method moves the mouse to the in-view center point of the element
then attempts to move to the upper left corner of the element and then moves by the
provided offset.

This will be removed as an option in Selenium 4.3, and only an offset from center of the element
will be supported. As of Selenium 4.2, this is the default behavior for Ruby, .NET and Python in order
to be backwards compatible with previous versions of Selenium.
This approach does not work correctly when the element is not entirely inside the viewport.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
**Not Implemented in Selenium 4**
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L96-L99" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L118-L121" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L97-L100" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/moveByOffset.spec.js#L18-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Offset from Element (Center Origin)

This method moves to the in-view center point of the element, 
then moves the mouse by the provided offset

This is the default behavior in Java as of Selenium 4.0, and will be the default
for the remaining languages as of Selenium 4.3.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L118-L121" >}}
{{< /tab >}}
{{< tab header="Python" >}}
**Coming in Selenium 4.3**
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L132-L135" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
**Coming in Selenium 4.3**
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L119-L122" >}}
{{< /tab >}}
{{< /tabpane >}}

### Offset from Viewport

This method moves the mouse from the upper left corner of the current viewport by the provided
offset.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L131-L136" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L108-L110" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L146-L150" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L114-L116" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/moveByOffset.spec.js#L29-L30" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L132-L137" >}}
{{< /tab >}}
{{< /tabpane >}}

### Offset from Current Pointer Location

This method moves the mouse from its current position by the offset provided by the user.
If the mouse has not previously been moved, the position will be in the upper left
corner of the viewport.
Note that the pointer position does not change when the page is scrolled.

Note that the first argument X specifies to move right when positive, while the second argument
Y specifies to move down when positive. So `moveByOffset(30, -10)` moves right 30 and up 10 from
the current mouse position.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L153-L155" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L124-L126" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L167-L169" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L128-L130" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/moveByOffset.spec.js#L42" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L154-L156" >}}
{{< /tab >}}
{{< /tabpane >}}

## Drag and Drop on Element

This method firstly performs a click-and-hold on the source element, 
moves to the location of the target element and then releases the mouse.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L166-L170" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L137-L141" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L181-L185" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L141-L145" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/dragAndDrop.spec.js#L29-L32" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L167-L171" >}}
{{< /tab >}}
{{< /tabpane >}}

## Drag and Drop by Offset

This method firstly performs a click-and-hold on the source element, moves to the given offset and then releases the mouse.

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L179-L184" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L149-L154" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L195-L200" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L153-L158" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/dragAndDrop.spec.js#L17-L21" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L180-L185" >}}
{{< /tab >}}
{{< /tabpane >}}
