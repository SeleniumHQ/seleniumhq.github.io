---
title: "Style guide for Selenium documentation"
linkTitle: "Style"
weight: 6
description: >-
  Conventions for contributing to the Selenium documentation and code examples
---

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


## Code Tabs

When adding code examples, you want to use tab panes as follows.
You do not need to provide examples in all languages,
but add placeholders like this for any language you do not implement:

{{< highlight html >}}
{{</* tabpane langEqualsHeader=true */>}}
{{</* tab header="Java" */>}}Java code not implemented, please make a Pull Request if you can add this code{{</* /tab */>}}
{{</* tab header="Python" */>}}Python code not implemented, please make a Pull Request if you can add this code{{</* /tab */>}}
{{</* tab header="CSharp" */>}}C# code not implemented, please make a Pull Request if you can add this code{{</* /tab */>}}
{{</* tab header="Ruby" */>}}Ruby code not implemented, please make a Pull Request if you can add this code{{</* /tab */>}}
{{</* tab header="JavaScript" */>}}JS code not implemented, please make a Pull Request if you can add this code{{</* /tab */>}}
{{</* tab header="Kotlin" */>}}Kotlin code not implemented, please make a Pull Request if you can add this code{{</* /tab */>}}
{{</* /tabpane */>}}
{{< /highlight >}}
