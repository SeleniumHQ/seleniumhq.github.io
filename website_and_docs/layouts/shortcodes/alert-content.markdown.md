{{- /* Markdown output of alert-content: see alert-content.html. */ -}}
{{- $inner := strings.TrimSpace .Inner }}
{{- $guide := "/documentation/about/contributing/" -}}
{{- with site.GetPage "/documentation/about/contributing" }}{{ $guide = .Permalink }}{{ end }}
> **Note:** This section needs additional and/or updated content.
{{- with $inner }} {{ replace . "\n" "\n> " }}{{ end }}
>
> Check our [contribution guidelines]({{ $guide }}) if you'd like to help.
