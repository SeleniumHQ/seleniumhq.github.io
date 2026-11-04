{{- /* Markdown output of alert-content: see alert-content.html. */ -}}
{{- $inner := strings.TrimSpace .Inner }}
> **Note:** This section needs additional and/or updated content.
{{- with $inner }} {{ replace . "\n" "\n> " }}{{ end }}
