{{- /*
  Markdown output of tabpane (see tabpane.html for the HTML one).

  Each tab becomes a bold label followed by its content, in source order, so
  every language's example is present in the page's Markdown. Docsy's tab
  shortcode stores the tabs on this shortcode's scratchpad, as for HTML. Tabs
  that hold code rather than Markdown (no text=true) become fenced code blocks,
  tagged with the language the HTML version would highlight them in.
*/ -}}
{{- .Inner -}}
{{- $langPane := .Get "lang" | default "" -}}
{{- $textPane := .Get "text" | default false -}}
{{- $langEqualsHeader := .Get "langEqualsHeader" | default false -}}
{{- range $index, $tab := .Scratch.Get "tabs" -}}
  {{- $lang := $langPane -}}
  {{- if $langEqualsHeader }}{{ $lang = $tab.header }}{{ end -}}
  {{- with $tab.language }}{{ $lang = . }}{{ end -}}
  {{- $text := $textPane -}}
  {{- with $tab.text }}{{ $text = . }}{{ end -}}
  {{- $content := strings.Trim (string $tab.content) "\r\n" -}}
  {{- if $text }}{{ $content = strings.TrimSpace $content }}{{ end }}

**{{ $tab.header | strings.TrimSpace }}**

{{ if $text -}}
{{ $content }}
{{ else if strings.TrimSpace $content -}}
{{ $fence := "```" -}}
{{ range findRE "`{3,}" $content -}}
  {{ if ge (len .) (len $fence) }}{{ $fence = printf "%s`" . }}{{ end -}}
{{ end -}}
{{ $fence }}{{ lower $lang }}
{{ $content }}
{{ $fence }}
{{ end -}}
{{- end }}
