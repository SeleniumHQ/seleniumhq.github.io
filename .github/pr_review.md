# PR Review Guidelines

Use these guidelines when reviewing pull requests for this documentation repository.

## Priorities

- First look for changes that can break rendered documentation: malformed Markdown, Hugo
  shortcodes, `tabpane` / `tab` pairs, code fences, front matter, anchors, or translated page
  structure. Small whitespace and delimiter changes can break code blocks or entire sections.
- Treat `gh-codeblock` paths and line ranges as correctness-sensitive. If `examples/` changed,
  search `website_and_docs/content` for references to the changed files. If docs changed a
  `gh-codeblock`, verify the path still exists and the line range shows exactly the intended
  snippet.
- Check translation maintenance against `website_and_docs/content/documentation/about/style.en.md`.
  English text changes do not require translated files in the same PR, but changed translation
  files must preserve working front matter, shortcodes, tabs, code blocks, and example references.
  When the style guide requires translated code examples, verify the matching translated files were
  updated.
- For examples, review code correctness only as it affects the published docs: runnable snippets,
  assertions where practical, stable demo pages, and matching documentation references.

## Documentation checks

- Front matter should match the surrounding page style.
- `linkTitle` should be short and title-cased; `title` should use sentence case.
- General prose should be language-independent. Put binding-specific code or behavior in language
  tabs.
- When all tabs use `gh-codeblock`, the `tabpane` should use `text=true`. When only some tabs use
  shortcodes or markdown, those tabs need `text=true`.
- `gh-codeblock` lines must not be indented. Plain text in markdown tabs also must not be
  accidentally indented into a code block.

## Do not comment on

- Missing translated files for ordinary English text changes.
- Subjective wording preferences unless the text is inaccurate, confusing, or inconsistent with
  the Selenium docs style guide.
- Formatting nits that do not affect rendering, code block structure, shortcode parsing, or
  maintainability.
- CI status without a clear connection to the diff.

## Review style

- Label severity as blocking, important, or minor.
- Make each comment actionable: state the risk, why it matters, and the smallest fix.
- Include file and line context when possible.
- Do not leave duplicate comments for the same root cause.
- It is acceptable to report no findings.
