---
title: "Contributing to the Selenium documentation"
linkTitle: "Documentation"
weight: 2
description: >-
    How the Selenium documentation is organized and how to change it
---

The documentation lives in `website_and_docs/content/documentation`. Follow the
[contribution mechanics]({{< ref "contributing#contribution-mechanics" >}}) to set up your
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
See [Capitalization of titles]({{< ref "style.md#capitalization-of-titles" >}}) for the
`title` and `linkTitle` conventions.

## Writing

* Keep the prose language independent. Anything specific to a language binding goes inside
  code tabs.
* Follow the [style guide]({{< ref "style.md" >}}) for line length, alerts, and code tabs.
* Mark missing content with the alerts described in the
  [style guide]({{< ref "style.md#alerts" >}}), so others know where help is needed.
* Link to other documentation pages with the `ref` shortcode, e.g.,
  `[Waits]({{</* ref "waits.md" */>}})`, so broken links fail the build.

## Code in the documentation

Code shown in the documentation must come from runnable examples. See
[Code examples]({{< ref "code_examples.md" >}}) for how to create them and render them on a page.

## Translations

See [Translations]({{< ref "style.md#translations" >}}) in the style guide.
