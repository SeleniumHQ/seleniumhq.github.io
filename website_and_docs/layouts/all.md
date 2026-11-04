{{- /*
  Markdown output of a page: the documentation section enables it through the
  cascade in hugo.toml. Based on Docsy's layouts/all.md, with these changes:

  - The page's HTML URL is given under the title, so the Markdown can be cited.
  - Runs of blank lines are collapsed. Shortcode templates leave whitespace-only
    lines behind, and a Markdown version an agent reads should not carry them.
  - Links to documentation pages, in the content and in the list of section
    pages, point to their Markdown versions (see markdown/doc-links.html).

  Shortcodes are rendered with their *.markdown.md templates in layouts/shortcodes.
*/ -}}
# {{ .Title | strings.TrimSpace }}
{{ with .Description | strings.TrimSpace }}
> {{ replace . "\n" "\n> " }}
{{ end }}
URL: {{ .Permalink }}
{{- with (partialCached "td/root-page.html" site).OutputFormats.Get "LLMS" }}
Site index: {{ .Permalink }}
{{- end }}
{{ with .RenderShortcodes | replaceRE `(?m)^[ \t]+$` "" | replaceRE `\n{3,}` "\n\n" | strings.TrimSpace | partial "markdown/doc-links.html" }}
---

{{ . }}
{{ end -}}
{{ with .Pages }}
---

Section pages:

{{ range . -}}
{{ $link := .Permalink -}}
{{ with .OutputFormats.Get "markdown" }}{{ $link = .Permalink }}{{ end -}}
- [{{ .Title | strings.TrimSpace }}]({{ $link }})
  {{- with .Description | strings.TrimSpace }}: {{ . | replaceRE `\s+` " " }}{{ end }}
{{ end -}}
{{ end -}}
