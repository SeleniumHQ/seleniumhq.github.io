{{- /*
  Markdown output of gh-codeblock (see gh-codeblock.html for the HTML one).

  The HTML version fetches the example from the GitHub API at build time, which
  needs SELENIUM_CI_TOKEN and otherwise renders a placeholder. The examples live
  in this repository, so the Markdown version reads the file from the checkout
  instead (../examples is mounted at assets/examples in hugo.toml): it always matches the branch being built and needs no token or
  network. The line range after "#" (L10 or L10-L20) is applied the same way.

  The shared indentation of the excerpt is removed. The result is a fenced code
  block tagged with the language, so the page's
  Markdown carries the code an agent would otherwise have to fetch separately.
  If the file is missing, a link to it on GitHub is emitted instead.
*/ -}}
{{- $fullPath := .Get "path" -}}
{{- $path := index (split $fullPath "#") 0 -}}
{{- $path = printf "/%s" (strings.TrimPrefix "/" $path) -}}
{{- $fragment := "" -}}
{{- if in $fullPath "#" }}{{ $fragment = index (split $fullPath "#") 1 }}{{ end -}}
{{- $webUrl := printf "%s/blob/trunk%s" (site.Params.github_repo | default "https://github.com/SeleniumHQ/seleniumhq.github.io") $path -}}
{{- with $fragment }}{{ $webUrl = printf "%s#%s" $webUrl . }}{{ end -}}

{{- $fileName := index (last 1 (split $path "/")) 0 -}}
{{- $ext := lower (index (last 1 (split $fileName ".")) 0) -}}
{{- $languages := dict
      "java" "java" "py" "python" "cs" "csharp" "rb" "ruby" "js" "javascript"
      "mjs" "javascript" "ts" "typescript" "kt" "kotlin" "kts" "kotlin"
      "csproj" "xml" "md" "markdown" "yml" "yaml" "txt" "text" "gemfile" "ruby" -}}
{{- $language := .Get "language" | default (index $languages $ext) | default $ext -}}

{{- with resources.Get (strings.TrimPrefix "/" $path) -}}
  {{- $lines := split .Content "\n" -}}
  {{- with $fragment -}}
    {{- $range := split (replace . "L" "") "-" -}}
    {{- $from := int (index $range 0) -}}
    {{- $to := int (index $range (sub (len $range) 1)) -}}
    {{- $lines = first (add (sub $to $from) 1) (after (sub $from 1) $lines) -}}
  {{- end -}}
  {{- /* Remove the indentation the lines share, as an excerpt from inside a
         method or class would otherwise start several levels deep. */ -}}
  {{- $indent := -1 -}}
  {{- range $lines -}}
    {{- if strings.TrimSpace . -}}
      {{- $n := len (index (findRE `^[ \t]*` . 1) 0) -}}
      {{- if or (lt $indent 0) (lt $n $indent) }}{{ $indent = $n }}{{ end -}}
    {{- end -}}
  {{- end -}}
  {{- $dedented := slice -}}
  {{- range $lines -}}
    {{- $dedented = $dedented | append (cond (ge (len .) $indent) (substr . (math.Max $indent 0)) "") -}}
  {{- end -}}
  {{- $code := strings.TrimRight "\n\r\t " (delimit $dedented "\n") -}}
  {{- $fence := "```" -}}
  {{- range findRE "`{3,}" $code -}}
    {{- if ge (len .) (len $fence) }}{{ $fence = printf "%s`" . }}{{ end -}}
  {{- end }}
{{ $fence }}{{ $language }}
{{ $code }}
{{ $fence }}
{{ else }}
[View example on GitHub]({{ $webUrl }})
{{ end -}}
