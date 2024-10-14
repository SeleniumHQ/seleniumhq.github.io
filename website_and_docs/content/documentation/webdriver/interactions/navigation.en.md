---
title: "Browser navigation"
linkTitle: "Navigation"
weight: 1
requiresTranslation: true
aliases: [
"/documentation/webdriver/browser/navigation",
]
---

## Navigate to

The first thing you will want to do after launching a browser is to
open your website. This can be achieved in a single line:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L14-L18" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_navigation.py#L6" >}}
{{< /tab >}}
  {{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/NavigationTest.cs#L17-L22" >}}
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/interactions/navigation_spec.rb#L7-L9" >}}
{{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
  {{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L16-L20" >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Convenient
driver.get("https://selenium.dev")

//Longer way
driver.navigate().to("https://selenium.dev")
  {{< /tab >}}
{{< /tabpane >}}

## Back

Pressing the browser's back button:


{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L22-L23" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_navigation.py#L11" >}}
{{< /tab >}}
  {{< tab header="CSharp" text=true >}}
 {{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/NavigationTest.cs#L24-L25" >}}
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/interactions/navigation_spec.rb#L15" >}}
{{< /tab >}}
  {{< tab header="JavaScript" text=true >}}
  {{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L24-L25" >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().back() {{< /tab >}}
{{< /tabpane >}}

## Forward
Pressing the browser's forward button:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L27-L28" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_navigation.py#L15" >}}
{{< /tab >}}
   {{< tab header="CSharp" text=true >}}
 {{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/NavigationTest.cs#L29-L30" >}}
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/interactions/navigation_spec.rb#L23" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L29-L30" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().forward(){{< /tab >}}
{{< /tabpane >}}

## Refresh

Refresh the current page:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/NavigationTest.java#L32-L33" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_navigation.py#L19" >}}
{{< /tab >}}
  {{< tab header="CSharp" text=true >}}
 {{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/NavigationTest.cs#L34-L35" >}}
  {{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/interactions/navigation_spec.rb#L29" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/navigation.spec.js#L34-L35" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.navigate().refresh(){{< /tab >}}
{{< /tabpane >}}
