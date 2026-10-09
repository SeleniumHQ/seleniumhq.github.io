---
title: "Contributing code examples"
linkTitle: "Code Examples"
weight: 3
description: >-
    How to create runnable code examples and render them in the documentation
---

We want to be able to run all of our code examples in the CI to ensure that people can copy, paste and
execute everything on the site. So the code lives in the
[examples directory](https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/),
and the documentation renders it from there.

## Creating examples

Examples that need to be added are marked with: {{% badge-code %}}

Each page in the documentation correlates to a test file in each of the languages, and should follow naming conventions.
For instance examples for this page https://www.selenium.dev/documentation/webdriver/browsers/chrome/ get added in these
files:
* `"/examples/java/src/test/java/dev/selenium/browsers/ChromeTest.java"`
* `"/examples/python/tests/browsers/test_chrome.py"`
* `"/examples/dotnet/SeleniumDocs/Browsers/ChromeTest.cs"`
* `"/examples/ruby/spec/browsers/chrome_spec.rb"`
* `"/examples/javascript/test/browser/chromeSpecificCaps.spec.js"`
* `"/examples/kotlin/src/test/kotlin/dev/selenium/browsers/ChromeTest.kt"`

Follow these guidelines when writing an example:

* **Each example gets its own test.** This keeps the example focused and lets the
  documentation point to exactly the lines that matter.
* **Use the Selenium test pages.** Examples need a web page to work against. Use the pages available at
  https://www.selenium.dev/selenium/web/ instead of third party sites, which can change or go offline
  and break the examples.
* **Assert the outcome.** Each test should have an assertion that verifies the code works as intended,
  so that a broken example fails the CI.
* **Keep the shown code free of test noise.** Write the test so that the lines shown in the documentation
  are only the Selenium code the reader needs. Assertions and test setup are not shown, unless the
  assertion is the clearest way to show the result of the command (e.g., the value returned by a getter).
* **Run the tests.** Run the tests for each language you changed locally, and make sure they pass in the CI.
  Each language directory in the
  [examples directory](https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/)
  has a README with the instructions to run its tests.

## Moving examples

Examples that need to be moved are marked with: {{% badge-examples %}}

These are code examples that are written directly in the Markdown file. Everything from
[Creating examples](#creating-examples) applies: move the code into a test, then render it with
`gh-codeblock` as described below.

## Rendering examples

Once the code is in its own test, it needs to be referenced in the Markdown file with the `gh-codeblock`
shortcode. For example, the tab in Ruby would look like this:

        {{</* tab header="Ruby" */>}}
        {{</* gh-codeblock path="/examples/ruby/spec/browsers/chrome_spec.rb#L8-L9" */>}}
        {{</* /tab */>}}

See [Reference GitHub Examples]({{< ref "style.md#reference-github-examples" >}}) in the style guide for
the complete `tabpane` syntax. Keep in mind the following:

* **Show only the relevant lines.** The line numbers at the end of the path (`#L8-L9`) select the lines
  that are displayed. Use a single line (`#L8`) or a range of lines (`#L8-L9`). Readers can click
  "View Complete Code" or "View on GitHub" to see the full test.
* **One range per code block.** A `gh-codeblock` displays one continuous range of lines.
  If the lines you want to show are not next to each other, reorganize the test so they are,
  or use one `gh-codeblock` for each range.
* **Use `text=true`.** By default, the tabs get formatted for code, so to use markdown or other shortcode
  statements (like `gh-codeblock`) it needs to be declared as text.
  For most examples, the `tabpane` declares the `text=true`, but if some of the tabs have code examples,
  the `tabpane` cannot specify it, and it must be specified in the tabs that do not need automatic code formatting.
* **Do not indent the `gh-codeblock` line.** An indented line is rendered as a Markdown code block
  instead of running the shortcode.
* **Keep line numbers up to date.** When you add, remove or move lines in an example file, every
  `gh-codeblock` that points to that file below the change needs new line numbers. Search
  `website_and_docs/content` for the file path, and check all languages of the page.
* **Add the example to all translations.** Update the `gh-codeblock` references in the `.ja.md`, `.pt-br.md`
  and `.zh-cn.md` files of the page too.
