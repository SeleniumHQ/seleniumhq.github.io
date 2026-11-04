{{- /* Markdown output of Docsy's alert shortcode: a blockquote. */ -}}
{{- $inner := partial "markdown/from-html.html" (strings.TrimSpace .Inner) }}
{{ with .Get "title" }}> **{{ . }}**
>
{{ end -}}
> {{ replace $inner "\n" "\n> " }}
