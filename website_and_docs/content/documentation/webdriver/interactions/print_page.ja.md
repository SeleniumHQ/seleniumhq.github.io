---
title: "Print Page"
linkTitle: "Print Page"
weight: 7
aliases: [
"/documentation/ja/support_packages/print_page/",
]
---

Printing a website is a common requirement, whether for sharing information or archiving records. 
Selenium offers a straightforward way to automate this process through the `PrintOptions()` class.
This class provides an intuitive and multifaceted way of printing webpages.

## Orientation
Using the `getOrientation()` and `setOrientation()` methods, you can get/set the page orientation --- either `PORTRAIT` or `LANDSCAPE`.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L12-L18" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L12-L19" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L11-L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

## Range
Using the `getPageRanges()` and `setPageRanges()` methods, you can get/set the range of pages to print --- e.g. "2-4".

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L21-L27" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L22-L29" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L17-L21" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

## Size
Using the `getPaperSize()` and `setPaperSize()` methods, you can get/set the paper size to print --- e.g. "A0", "A6", "Legal", "Tabloid", etc.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L30-L36" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L32-L38" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L23-L27" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

## Margins
Using the `getPageMargin()` and `setPageMargin()` methods, you can set the margin sizes of the page you wish to print --- i.e. top, bottom, left, and right margins.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L40-L49" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L51-L57" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L29-L39" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

## Scale
Using `getScale()` and `setScale()` methods, you can get/set the scale of the page you wish to print --- e.g. 1.0 is 100% or default, 0.25 is 25%, etc.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L52-L58" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L61-L68" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L41-L46" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

## Background
Using `getBackground()` and `setBackground()` methods, you can get/set whether background colors and images appear --- boolean `true` or `false`.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L61-L67" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L41-L48" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L48-L52" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}

## ShrinkToFit
Using `getBackground()` and `setBackground()` methods, you can get/set whether the page will shrink-to-fit content on the page --- boolean `true` or `false`.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/PrintOptionsTest.java#L70-L76" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/PrintOptionsTest.cs#L71-L78" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_print_options.py#L54-L58" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-implementation >}}
{{< /tab >}}
{{< /tabpane >}}