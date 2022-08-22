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
Note that the tabpane includes `langEqualsHeader=true`.
This auto-formats the code in each tab to match the header name.

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

#### Link to GitHub

All code examples should be present and linked to in our example 
[repos](https://github.com/SeleniumHQ/seleniumhq.github.io/tree/dev/examples).

With the `gh-codeblock` shortcode, it is possible to render code hosted in a GitHub
repository. In this case, `langEqualsHeader=true` is only needed if the `tabpane` mixes 
more than one `tab` with code hosted on GitHub and code examples added directly in the 
markdown file (which should not happen often).

However, `disableCodeBlock=true` is needed at the `tab` level when using the `gh-codeblock`
shortcode. This is an example of the `gh-codeblock` shortcode usage:

    {{</* tabpane */>}}
      {{</* tab header="Link" disableCodeBlock=true */>}}
        {{</* gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L37-L55" */>}}
      {{</* /tab */>}}
      {{</* tab header="No Link" */>}}
        This content should not get linked to GitHub
      {{</* /tab */>}}
    {{</* /tabpane */>}}

Which looks like this:

{{< tabpane >}}
  {{< tab header="Link" disableCodeBlock=true >}}
    {{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L37-L55" >}}
  {{< /tab >}}
  {{< tab header="No Link" >}}
    This content should not get linked to GitHub
  {{< /tab >}}
{{< /tabpane >}}

### Disabling Code Block

If you want your example to include both text and code, you
need to disable the default behavior that puts everything inside a code block

Maybe you want something like this:

{{< tabpane disableCodeBlock=true >}}
{{< tab header="Java" >}}
1. Start the driver
  ```java
    WebDriver driver = new ChromeDriver();
  ```
2. Navigate to a page
  ```java
  driver.get("https://selenium.dev");
  ```
3. Quit the driver
  ```java
  driver.quit();
  ``` 
{{< /tab >}}
{{< /tabpane >}}

For this you need to use `disableCodeBlock=true` instead of `langEqualsHeader=true` 

You need to specify which parts are code and which are not yourself now, like this:

    {{</* tabpane disableCodeBlock=true */>}}
    {{</* tab header="Java" */>}}
    1. Start the driver
      ```java
        WebDriver driver = new ChromeDriver();
      ```
    2. Navigate to a page
      ```java
      driver.get("https://selenium.dev");
      ```
    3. Quit the driver
      ```java
      driver.quit();
      ``` 
    {{</* /tab */>}}
    < ... >
    {{</* /tabpane */>}}

#### Link to GitHub

All code examples should be present and linked to in our example 
[repos](https://github.com/SeleniumHQ/seleniumhq.github.io/tree/dev/examples).

With the `gh-codeblock` shortcode, it is possible to render code hosted in a GitHub
repository. This is an example of the `gh-codeblock` shortcode usage:

    {{</* gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L37-L55" */>}}

Which looks like this:

<span class="tab-pane">
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/getting_started/FirstScriptTest.java#L37-L55" >}}
</span>

### Code Comments

Minimize code comments because they are difficult to translate.
Further, try to avoid over-explaining each line of code unless it is
directly related to the page you are writing.
