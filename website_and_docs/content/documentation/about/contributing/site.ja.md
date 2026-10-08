---
title: "Contributing to the Selenium site"
linkTitle: "Site"
weight: 1
description: >-
    How to update the Selenium site pages outside of the documentation
---

The Selenium site is built with [Hugo](https://gohugo.io/) and the [Docsy theme](https://www.docsy.dev/).
The site root is the `website_and_docs` directory. Follow the
[contribution mechanics]({{< ref "contributing#contribution-mechanics" >}}) to set up your
environment and preview your changes with `hugo server`.

## Where things live

| What | Where |
|------|-------|
| Home page | `content/_index.<language>.html` |
| Top level pages (downloads, support, projects, sponsors, etc.) | `content/<page>/_index.html` |
| Blog posts | `content/blog/<year>/<post>.md` |
| Page templates (e.g., the downloads page) | `layouts/<section>/list.html` |
| Shortcodes | `layouts/shortcodes/` |
| Partials (navbar, footer, announcement banner, etc.) | `layouts/partials/` |
| Images and other static files | `static/` (e.g., `static/images/`) |
| Styles | `assets/scss/` |
| Structured data (e.g., sponsors) | `data/` |
| Site configuration and menus | `hugo.toml` |

Some pages have most of their content in the template instead of the Markdown file.
For example, the content of the [Downloads](/downloads/) page lives in `layouts/downloads/list.html`.

## Blog posts

Add a new Markdown file under `content/blog/<year>/`. Copy the front matter of a recent post
and update the `title`, `linkTitle`, `date`, `tags`, `categories`, `author`, and `description`.
To use a custom image when the post is shared on social media, add it under
`static/images/blog/<year>/` and reference it in the `images` front matter key.

## Translations

The home page and the documentation are translated. Most of the other site pages, including the
blog, are only available in English. When you change text on a translated page, see
[Translations]({{< ref "style.md#translations" >}}) in the style guide.

## Checking your changes

Site changes are not covered by tests, so preview every page you changed with `hugo server`.
Every pull request gets a Netlify deploy preview; check it before asking for a review.
