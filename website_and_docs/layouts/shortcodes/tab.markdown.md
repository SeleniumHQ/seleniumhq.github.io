{{- /*
  Markdown output of Docsy's tab shortcode: records the tab on the enclosing
  tabpane's scratchpad, as the HTML version does, for tabpane.markdown.md to write out.
  Content from a {{% tab %}} arrives as HTML, so the content of text tabs is
  turned back into Markdown. Code tabs are kept as they are, so HTML in a code
  example is not rewritten.
*/ -}}
{{- $header := "" -}}
{{- if and (not .IsNamedParams) (.Get 0) -}}
  {{- $header = .Get 0 -}}
{{- else -}}
  {{- $header = .Get "header" | default (printf "Tab %v" .Ordinal) -}}
{{- end -}}
{{- $text := false -}}
{{- with .Parent }}{{ $text = .Get "text" | default false }}{{ end -}}
{{- with .Get "text" }}{{ $text = . }}{{ end -}}
{{- $content := string .Inner -}}
{{- if $text }}{{ $content = partial "markdown/from-html.html" $content }}{{ end -}}
{{- $tab := dict "header" $header "content" $content -}}
{{- with .Get "lang" }}{{ $tab = merge $tab (dict "language" .) }}{{ end -}}
{{- with .Get "text" }}{{ $tab = merge $tab (dict "text" .) }}{{ end -}}
{{- with .Parent -}}
  {{- .Scratch.SetInMap "tabs" (printf "%02v" $.Ordinal) $tab -}}
{{- end -}}
