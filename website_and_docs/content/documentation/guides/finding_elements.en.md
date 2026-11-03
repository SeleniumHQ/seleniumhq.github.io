---
title: "Finding elements reliably"
linkTitle: "Finding Elements"
weight: 2
description: >
  Choosing a locator strategy that survives the next release of the application, and avoiding
  the ones that do not.
---

A locator copied out of the browser's developer tools works today and breaks the first time a
designer moves a `div`. Tests then fail for reasons that have nothing to do with the behaviour
they were written to check, and the suite stops being trusted.

## Recommended approach

Prefer locators that describe what an element _is_ over locators that describe where it
happens to sit in the document, roughly in this order:

1. **id**, when the page has stable, unique ids. This is the fastest and most readable choice.
2. **name**, for form fields, which tend to keep their names because the server depends on them.
3. **CSS selector**, for everything else. A short selector anchored on an id, a data attribute,
   or a meaningful class is both fast and expressive.
4. **Relative locators**, when the target has no stable attributes of its own but a nearby
   element does: find the input _below_ a given label, or the button _to the right of_ a field.

Avoid absolute XPath (`/html/body/div[3]/...`). It encodes the entire structure of the page, so
any change above the element breaks it, and it is slow in some browsers. XPath itself is fine
when you need it, for example to match on text content, but keep the expression short and
anchored.

If the application does not expose stable attributes, ask for them. A `data-test` attribute
costs the developers nothing and removes an entire class of failures.

Locate the first matching element:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L23-L24" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L7-L8" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L15-L16" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L10-L11" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L9-L10" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L19-L20" >}}
{{< /tab >}}
{{< /tabpane >}}

Locate by id, the preferred strategy when one is available:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/LocatorTest.java#L48" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/elements/test_locators.py#L29" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/locators_spec.rb#L15" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/elements/locators.spec.js#L37" >}}
{{< /tab >}}
{{< /tabpane >}}

Locate relative to another element:

{{< tabpane text=true >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/locators_spec.rb#L40" >}}
{{< /tab >}}
{{< /tabpane >}}

## Common errors

* [NoSuchElementException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#nosuchelementexception" >}}):
  nothing matched. Check the locator, check that you are in the right frame, and check that the
  element exists yet (see [Waiting]({{< ref "waiting.md" >}})).
* [InvalidSelectorException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#invalidselectorexception" >}}):
  the CSS or XPath expression is malformed, or an XPath expression returns something that is not an element.
* [StaleElementReferenceException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#staleelementreferenceexception" >}}):
  you held on to an element across a page update. Locate it again rather than caching it.
* [NoSuchShadowRootException]({{< ref "/documentation/webdriver/troubleshooting/errors/_index.md#nosuchshadowrootexception" >}}):
  the element lives inside a shadow DOM; search from the shadow root instead of the document.

## Reference

* [Locator strategies]({{< ref "/documentation/webdriver/elements/locators.md" >}}): every strategy, including relative locators
* [Finding elements]({{< ref "/documentation/webdriver/elements/finders.md" >}}): first match, all matches, nested searches, shadow DOM
* [Tips on working with locators]({{< ref "/documentation/test_practices/encouraged/locators.md" >}})
* [Page object models]({{< ref "/documentation/test_practices/encouraged/page_object_models.md" >}}): keeping locators in one place
