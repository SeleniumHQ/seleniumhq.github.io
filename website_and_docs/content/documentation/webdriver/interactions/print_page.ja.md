---
title: "Print Page"
linkTitle: "Print Page"
weight: 7
aliases: [
"/documentation/ja/support_packages/print_page/",
]
---

Printing a webpage is a common task, whether for sharing information or maintaining archives. 
Selenium simplifies this process through its PrintOptions, PrintsPage, and browsingContext 
classes, which provide a flexible and intuitive interface for automating the printing of web pages. 
These classes enable you to configure printing preferences, such as page layout, margins, and scaling, 
ensuring that the output meets your specific requirements.

## Configuring

### Orientation
Using the `getOrientation()` and `setOrientation()` methods, you can get/set the page orientation --- either `PORTRAIT` or `LANDSCAPE`.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L14-L17" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L12-L19" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L12-L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### Range
Using the `getPageRanges()` and `setPageRanges()` methods, you can get/set the range of pages to print --- e.g. "2-4".

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L23-L26" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L22-L29" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L18-L20" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### Size
Using the `getPageSize()` and `setPageSize()` methods, you can get/set the paper size to print --- e.g. "A0", "A6", "Legal", "Tabloid", etc.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L32-L35" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L32-L38" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L24-L26" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### Margins
Using the `getPageMargin()` and `setPageMargin()` methods, you can set the margin sizes of the page you wish to print --- i.e. top, bottom, left, and right margins.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L41-L48" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L51-L57" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L30-L35" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### Scale
Using `getScale()` and `setScale()` methods, you can get/set the scale of the page you wish to print --- e.g. 1.0 is 100% or default, 0.25 is 25%, etc.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L53-L57" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L61-L68" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L42-L45" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### Background
Using `getBackground()` and `setBackground()` methods, you can get/set whether background colors and images appear --- boolean `true` or `false`.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L63-L66" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L41-L48" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L49-L51" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

### ShrinkToFit
Using `getShrinkToFit()` and `setShrinkToFit()` methods, you can get/set whether the page will shrink-to-fit content on the page --- boolean `true` or `false`.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L72-L75" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L71-L78" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L55-L57" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

## Printing

Once you've configured your PrintOptions, you're ready to print the page. To do this, 
you can invoke the print function, which generates a PDF representation of the web page. 
The resulting PDF can be saved to your local storage for further use or distribution. 
Using `PrintsPage()`, the print command will return the PDF data in base64-encoded format, which can be decoded 
and written to a file in your desired location, and using `BrowsingContext()` will return a String. 

There may currently be multiple implementations depending on your language of choice. For example, with Java you
have the ability to print using either `BrowingContext()` or `PrintsPage()`. Both take `PrintOptions()` objects as a
parameter.

Note: `BrowsingContext()` is part of Selenium's BiDi implementation. To enable BiDi see [Enabling Bidi]({{< ref "bidi/" >}}) 

{{< tabpane text=true >}}
{{% tab header="Java" %}}
**PrintsPage()**
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintsPageTest.java#L27-L31" >}}
**BrowsingContext()**
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintsPageTest.java#L37-L41" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L81-L88" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{% tab header="Python" %}}
**print_page()**
{{< gh-codeblock path="examples/python/tests/interactions/test_prints_page.py#L12-L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}
