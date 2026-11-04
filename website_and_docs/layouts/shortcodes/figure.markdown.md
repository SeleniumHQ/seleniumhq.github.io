{{- /* Markdown output of Hugo's figure shortcode: an image, with its caption. */ -}}
{{- $alt := .Get "alt" | default (.Get "caption") | default "" }}
![{{ $alt }}]({{ .Get "src" | absURL }})
{{ with .Get "caption" }}
_{{ . }}_
{{ end -}}
