{{- /*
  Markdown output of Docsy's tab shortcode: records the tab on the enclosing
  tabpane's scratchpad, as the HTML version does, for tabpane.markdown.md to write out.
  Content from a {{% tab %}} arrives as HTML, so highlighted code in it is turned
  back into fenced code blocks.
*/ -}}
{{- $header := "" -}}
{{- if and (not .IsNamedParams) (.Get 0) -}}
  {{- $header = .Get 0 -}}
{{- else -}}
  {{- $header = .Get "header" | default (printf "Tab %v" .Ordinal) -}}
{{- end -}}
{{- $tab := dict "header" $header "content" (partial "markdown/from-html.html" (string .Inner)) -}}
{{- with .Get "lang" }}{{ $tab = merge $tab (dict "language" .) }}{{ end -}}
{{- with .Get "text" }}{{ $tab = merge $tab (dict "text" .) }}{{ end -}}
{{- with .Parent -}}
  {{- .Scratch.SetInMap "tabs" (printf "%02v" $.Ordinal) $tab -}}
{{- end -}}
