{{- /* Markdown output of Docsy's card shortcode: the header in bold, then the
  body, as a fenced code block when the card is code=true. */ -}}
{{- $inner := strings.Trim (string .Inner) "\r\n" }}
{{ with .Get "header" }}
**{{ . }}**
{{ end -}}
{{ with .Get "title" }}
{{ . }}
{{ end }}
{{ if .Get "code" -}}
{{ $fence := "```" -}}
{{ range findRE "`{3,}" $inner -}}
  {{ if ge (len .) (len $fence) }}{{ $fence = printf "%s`" . }}{{ end -}}
{{ end -}}
{{ $fence }}{{ lower (.Get "lang" | default "") }}
{{ $inner }}
{{ $fence }}
{{- else -}}
{{ partial "markdown/from-html.html" (strings.TrimSpace $inner) }}
{{- end }}
{{ with .Get "footer" }}
{{ . }}
{{ end -}}
