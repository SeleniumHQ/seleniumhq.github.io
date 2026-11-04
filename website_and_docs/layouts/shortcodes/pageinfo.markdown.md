{{- /* Markdown output of Docsy's pageinfo shortcode: a blockquote. */ -}}
{{- $inner := partial "markdown/from-html.html" (strings.TrimSpace .Inner) }}
> {{ replace $inner "\n" "\n> " }}
