# Contributing to the Selenium documentation

> How the Selenium documentation is organized and how to change it

URL: https://www.selenium.dev/zh-cn/documentation/about/contributing/documentation/

---

The documentation lives in `website_and_docs/content/documentation`. Follow the
[contribution mechanics](https://www.selenium.dev/zh-cn/documentation/about/contributing/index.md#contribution-mechanics) to set up your
environment and preview your changes with `hugo server`.

## Structure

Each directory is a section of the documentation, and its `_index.<language>.md` file is the
section landing page. Each page has one file per language:

* `<page>.en.md`
* `<page>.ja.md`
* `<page>.pt-br.md`
* `<page>.zh-cn.md`

When you add a page, add a file for each language. If you are not translating the content,
copy the English text into the other language files.

Each page starts with front matter like this:

```yaml
---
title: "Sentence capitalization title that describes the page"
linkTitle: "Short Title"
weight: 4
description: >
  One sentence summary of the page.
---
```

`weight` defines the order of the page in the navigation.
See [Capitalization of titles](https://www.selenium.dev/zh-cn/documentation/about/style/index.md#capitalization-of-titles) for the
`title` and `linkTitle` conventions.

## Writing

* Keep the prose language independent. Anything specific to a language binding goes inside
  code tabs.
* Follow the [style guide](https://www.selenium.dev/zh-cn/documentation/about/style/index.md) for line length, alerts, and code tabs.
* Mark missing content with the alerts described in the
  [style guide](https://www.selenium.dev/zh-cn/documentation/about/style/index.md#alerts), so others know where help is needed.
* Link to other documentation pages with the `ref` shortcode, e.g.,
  `[Waits]({{< ref "waits.md" >}})`, so broken links fail the build.

## Code in the documentation

Code shown in the documentation must come from runnable examples. See
[Code examples](https://www.selenium.dev/zh-cn/documentation/about/contributing/code_examples/index.md) for how to create them and render them on a page.

## Translations

See [Translations](https://www.selenium.dev/zh-cn/documentation/about/style/index.md#translations) in the style guide.
