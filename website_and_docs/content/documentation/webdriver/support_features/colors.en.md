---
title: "Working With Colors"
linkTitle: "Colors"
weight: 3
aliases: [
"/documentation/en/support_packages/working_with_colours/",
"/documentation/support_packages/working_with_colours/",
"/documentation/webdriver/additional_features/working_with_colours/",
"/documentation/ja/support_packages/working_with_colours/",
"/documentation/pt-br/support_packages/working_with_colours/",
"/documentation/zh-cn/support_packages/working_with_colours/",
"/ja/documentation/support_packages/working_with_colours/",
"/ja/documentation/webdriver/additional_features/working_with_colours/",
"/ja/documentation/webdriver/support_features/colors/",
"/pt-br/documentation/support_packages/working_with_colours/",
"/pt-br/documentation/webdriver/additional_features/working_with_colours/",
"/pt-br/documentation/webdriver/support_features/colors/",
"/zh-cn/documentation/support_packages/working_with_colours/",
"/zh-cn/documentation/webdriver/additional_features/working_with_colours/",
"/zh-cn/documentation/webdriver/support_features/colors/"
]
---

You will occasionally want to validate the colour of something as part of your tests;
the problem is that colour definitions on the web are not constant.
Would it not be nice if there was an easy way to compare
a HEX representation of a colour with a RGB representation of a colour,
or a RGBA representation of a colour with a HSLA representation of a colour?

Worry not. There is a solution: the _Color_ class!

First of all, you will need to import the class:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L7" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L2" >}}
  {{< /tab >}}
  {{< tab header="CSharp" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L8" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L7" >}}
  {{< /tab >}}
{{< /tabpane >}}

You can now start creating colour objects.
Every colour object will need to be created from a string representation of
your colour.
Supported colour representations are:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L13-L19" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L6-L12" >}}
  {{< /tab >}}
  {{< tab header="CSharp" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L11-L17" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L13-L19" >}}
  {{< /tab >}}
{{< /tabpane >}}

The Color class also supports all of the base colour definitions
specified in
[http://www.w3.org/TR/css3-color/#html4](//www.w3.org/TR/css3-color/#html4).

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L32-L34" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L24-L26" >}}
  {{< /tab >}}
  {{< tab header="CSharp" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L32-L34" >}}
  {{< /tab >}}
{{< /tabpane >}}

Sometimes browsers will return a colour value of "transparent"
if no colour has been set on an element.
The Color class also supports this:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L43" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L34" >}}
  {{< /tab >}}
  {{< tab header="CSharp" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L43" >}}
  {{< /tab >}}
{{< /tabpane >}}

You can now safely query an element
to get its colour/background colour knowing that
any response will be correctly parsed
and converted into a valid Color object:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L54-L55" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L43-L44" >}}
  {{< /tab >}}
  {{< tab header="CSharp" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L32-L33" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L53-L54" >}}
  {{< /tab >}}
{{< /tabpane >}}

You can then directly compare colour objects:


{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L59" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L48" >}}
  {{< /tab >}}
  {{< tab header="CSharp" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L37" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L58" >}}
  {{< /tab >}}
{{< /tabpane >}}

Or you can convert the colour into one of the following formats
and perform a static validation:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L61-L63" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L50-L52" >}}
  {{< /tab >}}
  {{< tab header="CSharp" text=true text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L39-L41" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L60-L62" >}}
  {{< /tab >}}
{{< /tabpane >}}

Colours are no longer a problem.
