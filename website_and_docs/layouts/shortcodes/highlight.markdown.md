{{- /* Markdown output of Hugo's highlight shortcode: a fenced code block. */ -}}
{{- $inner := strings.Trim (string .Inner) "\r\n" -}}
{{- $fence := "```" -}}
{{- range findRE "`{3,}" $inner -}}
  {{- if ge (len .) (len $fence) }}{{ $fence = printf "%s`" . }}{{ end -}}
{{- end }}
{{ $fence }}{{ .Get 0 }}
{{ $inner }}
{{ $fence }}
