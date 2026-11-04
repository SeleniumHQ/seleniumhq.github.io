# Contributing to the Selenium site

> How to update the Selenium site pages outside of the documentation

URL: https://www.selenium.dev/zh-cn/documentation/about/contributing/site/

---

The Selenium site is built with [Hugo](https://gohugo.io/) and the [Docsy theme](https://www.docsy.dev/).
The site root is the `website_and_docs` directory. Follow the
[contribution mechanics](https://www.selenium.dev/zh-cn/documentation/about/contributing/index.md#contribution-mechanics) to set up your
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
| Markdown version of each documentation page | `layouts/all.md`, `layouts/shortcodes/*.markdown.md` |

Some pages have most of their content in the template instead of the Markdown file.
For example, the content of the [Downloads](/downloads/) page lives in `layouts/downloads/list.html`.

## Markdown versions of documentation pages

Every documentation page is also published as Markdown, at `index.md` beside its `index.html`
(for example, `/documentation/webdriver/waits/index.md`), for AI agents and other tools that read
the site. Each shortcode used in the documentation needs a Markdown template next to its HTML one,
named `<shortcode>.markdown.md` (the `markdown` part keeps Hugo from also using it for HTML).
If you add a shortcode to the documentation, add its Markdown template too, and check the page's
`index.md` in your preview. `build-site.sh` fails when a page's `index.md` has HTML from a shortcode
that has no Markdown template.

## Blog posts

Add a new Markdown file under `content/blog/<year>/`. Copy the front matter of a recent post
and update the `title`, `linkTitle`, `date`, `tags`, `categories`, `author`, and `description`.
To use a custom image when the post is shared on social media, add it under
`static/images/blog/<year>/` and reference it in the `images` front matter key.

## Translations

The home page and the documentation are translated. Most of the other site pages, including the
blog, are only available in English. When you change text on a translated page, see
[Translations](https://www.selenium.dev/zh-cn/documentation/about/style/index.md#translations) in the style guide.

## Checking your changes

Site changes are not covered by tests, so preview every page you changed with `hugo server`.
Every pull request gets a Netlify deploy preview; check it before asking for a review.
