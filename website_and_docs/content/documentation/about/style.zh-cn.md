---
title: "Style guide for Selenium documentation"
linkTitle: "Style"
weight: 6
requiresTranslation: true
description: >-
  Conventions for contributions to the Selenium documentation and code examples
---

Read our [contributing documentation]({{< ref contributing.md >}}) for complete instructions on 
how to add content to this documentation.

## Alerts

Alerts have been added to direct potential contributors to where specific help is needed.

When code examples are needed, this code has been added to the site:

{{< highlight html >}}
{{</* alert-code /*/>}}
{{< /highlight >}}

Which gets displayed like this:
{{< alert-code />}}

To specify what code is needed, you can pass information inside the tag:

{{< highlight html >}}
{{</* alert-code */>}}
specifically code that does this one thing.
{{</* /alert-code */>}}
{{< /highlight >}}

Which looks like this:

{{< alert-code >}}
specifically code that does this one thing.
{{< /alert-code >}}

Similarly, for additional content you can use:

{{< highlight html >}}
{{</* alert-content /*/>}}
{{< /highlight >}}

or

{{< highlight html >}}
{{</* alert-content */>}}
Additional information about what specific content is needed
{{</* /alert-content */>}}
{{< /highlight >}}

Which gets displayed like this:
{{< alert-content >}}
Additional information about what specific content is needed
{{< /alert-content >}}

## Capitalization of titles

Our documentation uses Title Capitalization for `linkTitle` which should be short
and Sentence capitalization for `title` which can be longer and more descriptive.
For example, a `linkTitle` of  _Special Heading_ might have a `title` of
_The importance of a special heading in documentation_

## Line length

When editing the documentation’s source,
which is written in plain HTML,
limit your line lengths to around 100 characters.

Some of us take this one step further
and use what is called
[_semantic linefeeds_](//rhodesmill.org/brandon/2012/one-sentence-per-line),
which is a technique whereby the HTML source lines,
which are not read by the public,
are split at ‘natural breaks’ in the prose.
In other words, sentences are split
at natural breaks between clauses.
Instead of fussing with the lines of each paragraph
so that they all end near the right margin,
linefeeds can be added anywhere
that there is a break between ideas.

This can make diffs very easy to read
when collaborating through git,
but it is not something we enforce contributors to use.

## Translations

Selenium now has official translators for each of the supported languages.

* If you add a code example to the `important_documentation.en.md` file,
  also add it to `important_documentation.ja.md`, `important_documentation.pt-br.md`,
  `important_documentation.zh-cn.md`.
* If you make text changes in the English version, just make a Pull Request.
  The new process is for issues to be created and tagged as needs translation based on 
  changes made in a given PR.

## Code examples

All references to code should be language independent,
and the code itself should be placed inside code tabs.

### Default Code Tabs

The Docsy code tabs look like this:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    WebDriver driver = new ChromeDriver();
  {{< /tab >}}
  {{< tab header="Python" >}}
    driver = webdriver.Chrome()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    var driver = new ChromeDriver();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    driver = Selenium::WebDriver.for :chrome
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
    let driver = await new Builder().forBrowser('chrome').build();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    val driver = ChromeDriver()
  {{< /tab >}}
{{< /tabpane >}}

To generate the above tabs, this is what you need to write.
Note that the `tabpane` includes `langEqualsHeader=true`.
This auto-formats the code in each tab to match the header name
and is also used to synchronize the tabs on the page.

    {{</* tabpane langEqualsHeader=true */>}}
      {{</* tab header="Java" */>}}
        WebDriver driver = new ChromeDriver();
      {{</* /tab */>}}
      {{</* tab header="Python" */>}}
        driver = webdriver.Chrome()
      {{</* /tab */>}}
      {{</* tab header="CSharp" */>}}
        var driver = new ChromeDriver();
      {{</* /tab */>}}
      {{</* tab header="Ruby" */>}}
        driver = Selenium::WebDriver.for :chrome
      {{</* /tab */>}}
      {{</* tab header="JavaScript" */>}}
        let driver = await new Builder().forBrowser('chrome').build();
      {{</* /tab */>}}
      {{</* tab header="Kotlin" */>}}
        val driver = ChromeDriver()
      {{</* /tab */>}}
    {{</* /tabpane */>}}

#### Reference Github Examples

To ensure that all code is kept up to date, our goal is to write the code in the repo where it
can be executed when Selenium versions are updated to ensure that everything is correct.

All code examples to be in our 
[example directories](https://github.com/SeleniumHQ/seleniumhq.github.io/tree/dev/examples).

This code can be automatically displayed in the documentation using the `gh-codeblock` shortcode.
The shortcode automatically generates its own html, so set `code=false` to prevent the auto-formatting.
We still need `langEqualsHeader=true` to keep the language tabs synchronized throughout the page.
Note that the `gh-codeblock` line can not be indented at all.

One great thing about using `gh-codeblock` is that it adds a link to the full example.
This means you don't have to include any additional context code, just the line(s) that
are needed, and the user can navigate to the repo to see how to use it.

A basic comparison of code looks like:

    {{</* tabpane code=false langEqualsHeader=true */>}}
    {{</* tab header="Java" */>}}
    {{</* gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L46-L47" */>}}
    {{</* /tab */>}}
    {{</* tab header="Python" */>}}
    {{</* gh-codeblock path="examples/python/tests/getting_started/test_first_script.py#L17-L18" */>}}
    {{</* /tab */>}}
    {{</* tab header="CSharp" */>}}
    {{</* gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScriptTest.cs#L39-L40" */>}}
    {{</* /tab */>}}
    {{</* tab header="Ruby" */>}}
    {{</* gh-codeblock path="examples/ruby/spec/getting_started/first_script_spec.rb#L16-L17" */>}}
    {{</* /tab */>}}
    {{</* tab header="JavaScript" */>}}
    {{</* gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L23-L24" */>}}
    {{</* /tab */>}}
    {{</* tab header="Kotlin" */>}}
    {{</* gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L39-L40" */>}}
    {{</* /tab */>}}
    {{</* /tabpane */>}}

Which looks like this:

{{< tabpane code=false langEqualsHeader=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L46-L47" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/getting_started/test_first_script.py#L17-L18" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/GettingStarted/FirstScriptTest.cs#L39-L40" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/getting_started/first_script_spec.rb#L16-L17" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/getting_started/firstScript.spec.js#L23-L24" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/getting_started/FirstScriptTest.kt#L39-L40" >}}
{{< /tab >}}
{{< /tabpane >}}

### Using Markdown in a Tab

If you want your example to include something other than code (default) or html (from `gh-codeblock`),
you need to first set `code=false`, 
then change the Hugo syntax for the `tab`to use `%` instead of `<` and `>` with curly braces:

    {{</* tabpane code=false */>}}
    {{%/* tab header="Java" */%}}
    1. Start the driver
    {{</* gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L29" */>}}
    2. Navigate to a page
    {{</* gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L39" */>}}
    3. Quit the driver
    {{</* gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L34" */>}}
    {{%/* /tab */%}}
    < ... >
    {{</* /tabpane */>}}

This produces:

{{< tabpane code=false langEqualsHeader=true >}}
{{% tab header="Java" %}}
1. Start the driver
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L29" >}}
2. Navigate to a page
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L39" >}}
3. Quit the driver
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L34" >}}
{{% /tab %}}
{{< /tabpane >}}

This is preferred to writing code comments because those will not be translated.
Only include the code that is needed for the documentation, and avoid over-explaining.
Finally, remember not to indent plain text or it will rendered as a codeblock.
