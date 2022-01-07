---
title: "Style guide for Selenium documentation"
linkTitle: "Style"
description: >-
  Conventions for contributing to the Selenium documenation and code examples
weight: 3
---

## Capitalisation of titles

One should avoid title capitalisation,
such as _A Very Fine Heading_,
and instead go for _A very fine heading_.
Gratuitous capitalisation, or title case,
often show a misunderstanding of – or a disregard for –
orthographic conventions.
We prefer what is known as _sentence case_,
with a single initial capital to start headers.

## Line length

When editing the documentation’s source,
which is written in plain HTML,
limit your line lengths to around 72 characters.

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

The docs are translated into several languages, and translations are based on
the English content. When you are changing a file, **be sure** to make your
changes in all the other translated files as well. This might differ depending
on the change, for example:

* If you add a code example to the `browser_manipulation.en.md` file,
  also add it to `browser_manipulation.es.md`, `browser_manipulation.ef.md`,
  `browser_manipulation.ja.md`, and all other translated files.
* If you find a translation that can be improved, only change the translated
  file.
* If you are adding a new language translation, add the new files with the
  appropriate suffix. There is no need to have everything translated to submit a
  PR, it can be done iteratively. Don't forget to check some needed configuration
  values in the `config.toml` file.
* If you make text changes in the English version, replace the same section in
  the translated files with your change (yes, in English), and add the following
  notice at the top of the file.


```
{{%/* pageinfo color="warning" */%}}
<p class="lead">
   <i class="fas fa-language display-4"></i> 
   Page being translated from 
   English to {LANGUAGE}. Do you speak {LANGUAGE}? Help us to translate
   it by sending us pull requests!
</p>
{{%/* /pageinfo */%}}
```

## Code examples

All references to code should be language independent,
and the code itself should be placed inside code tabs.

### Default Code Tabs

The Docsy code tabs look like this:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
    WebDriver driver = new ChromeDriver();
    driver.get("https://selenium.dev");
    driver.quit();
  {{< /tab >}}
  {{< tab header="Python" >}}
    driver = webdriver.Chrome()
    driver.get("http://selenium.dev")
    driver.quit()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
    var driver = new ChromeDriver();
    driver.Navigate().GoToUrl("https://selenium.dev");
    driver.Quit();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    driver = Selenium::WebDriver.for :chrome
    driver.get 'https://selenium.dev'
    driver.quit
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
    let driver = await new Builder().forBrowser('chrome').build();
    await driver.get('https://selenium.dev');
    await driver.quit();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
    val driver = ChromeDriver()
    driver.get("https://selenium.dev")
    driver.quit()
  {{< /tab >}}
{{< /tabpane >}}

To generate the above tabs, this is what you need to write.
Note that the tabpane includes `langEqualsHeader=true`.
This auto-formats the code in each tab to match the header name.

    {{</* tabpane langEqualsHeader=true */>}}
      {{</* tab header="Java" */>}}
        WebDriver driver = new ChromeDriver();
        driver.get("https://selenium.dev");
        driver.quit();
      {{</* /tab */>}}
      {{</* tab header="Python" */>}}
        driver = webdriver.Chrome()
        driver.get("http://selenium.dev")
        driver.quit()
      {{</* /tab */>}}
      {{</* tab header="CSharp" */>}}
        var driver = new ChromeDriver();
        driver.Navigate().GoToUrl("https://selenium.dev");
        driver.Quit();
      {{</* /tab */>}}
      {{</* tab header="Ruby" */>}}
        driver = Selenium::WebDriver.for :chrome
        driver.get 'https://selenium.dev'
        driver.quit
      {{</* /tab */>}}
      {{</* tab header="JavaScript" */>}}
        let driver = await new Builder().forBrowser('chrome').build();
        await driver.get('https://selenium.dev');
        await driver.quit();
      {{</* /tab */>}}
      {{</* tab header="Kotlin" */>}}
        val driver = ChromeDriver()
        driver.get("https://selenium.dev")
        driver.quit()
      {{</* /tab */>}}
    {{</* /tabpane */>}}

### Disabling Code Block

If you want your example to include both text and code, you
need to disable the default of everything being put in a code block

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

### Link to GitHub

If you disable code blocks, you can create whatever kind of link anywhere you want.
But say you want to add a GitHub link associated with a given tab when using the default code block:
{{< tabpane >}}
  {{< tab header="Link" github="seleniumhq/seleniumhq.github.io/blob/dev/website_and_docs/content/documentation/about/style.en.md#L205" >}}
    Content in automatic code block that refers to something on GitHub
  {{< /tab >}}
  {{< tab header="No Link" >}}
    This content should not get linked to GitHub
  {{< /tab >}}
{{< /tabpane >}}

To add a link to a particular tab, pass in a `github` value
to that tab. Note that only the tabs that have this value will have the link added.

    {{</* tabpane */>}}
      {{</* tab header="Link" github="seleniumhq/seleniumhq.github.io/blob/dev/website_and_docs/content/documentation/about/style.en.md#L205" */>}}
        Content in automatic code block that refers to something on GitHub
      {{</* /tab */>}}
      {{</* tab header="No Link" */>}}
        This content should not get linked to GitHub
      {{</* /tab */>}}
    {{</* /tabpane */>}}


### Code Comments

Minimize code comments because they are difficult to translate.
Further, try to avoid over-explaining each line of code unless it is
directly related to the page you are writing.
