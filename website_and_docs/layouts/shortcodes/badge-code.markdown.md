{{- /* Markdown output of badge-code: see badge-code.html. */ -}}
{{- $guide := "/documentation/about/contributing/code_examples/#creating-examples" -}}
{{- with site.GetPage "/documentation/about/contributing/code_examples" }}{{ $guide = printf "%s#creating-examples" .Permalink }}{{ end -}}
_No example for this language yet ([add one]({{ $guide }}))._
