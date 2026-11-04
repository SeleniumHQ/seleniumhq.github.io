{{- /* Markdown output of badge-examples: see badge-examples.html. The examples
  that follow are inline in the page and not yet in the examples directory, so
  unlike the other examples they are not run in CI. */ -}}
{{- $guide := "/documentation/about/contributing/code_examples/#moving-examples" -}}
{{- with site.GetPage "/documentation/about/contributing/code_examples" }}{{ $guide = printf "%s#moving-examples" .Permalink }}{{ end -}}
_These examples are not yet tested in CI ([move them to the examples directory]({{ $guide }}))._
