{{- /*
  Markdown version of the home page (/index.md), for AI agents and other tools.
  English only, like llms.txt: hugo.toml adds the markdown output to the home
  page under [languages.en.outputs].

  The HTML home page is a hand-built landing page, so this does not convert it.
  It writes what an agent needs from it: what Selenium is, then the per-language
  quickstart from data/quickstart.toml with each example's code read from the
  checkout (../examples is mounted at assets/examples), each under a heading
  that names its language, and where to go next.
*/ -}}
{{- $gettingStarted := site.GetPage "/documentation/webdriver/getting_started" -}}
# {{ .Title | strings.TrimSpace }}
{{ with .Description | strings.TrimSpace }}
> {{ replace . "\n" "\n> " }}
{{ end }}
URL: {{ .Permalink }}
{{- with .OutputFormats.Get "llms" }}
Site index: {{ .Permalink }}
{{- end }}

---

## Install Selenium and run your first script

Each example below opens Chrome, loads a page and closes the browser again.
Selenium Manager, part of Selenium 4.6 and later, downloads the browser driver,
so there is nothing else to install. Every example is run in CI against current
Selenium releases.
{{ range site.Data.quickstart.languages }}
{{- $lang := . }}
{{- $path := strings.TrimPrefix "/" .example }}
### {{ .label }}

{{ with .install -}}
Install the package:

```shell
{{ . }}
```
{{- else -}}
{{ .install_text }}
{{- end }}

Then run [{{ path.Base $path }}]({{ site.Params.github_repo }}/blob/trunk/{{ $path }}):

{{ with resources.Get $path -}}
```{{ $lang.key }}
{{ strings.TrimRight "\n\r\t " (replace .Content "\r\n" "\n") }}
```
{{- else -}}
{{ errorf "layouts/home.markdown.md: example %q from data/quickstart.toml not found" $path }}
{{- end }}
{{ end }}
## Show the examples in one language

On selenium.dev, add `?lang=` to a page's URL to show its code examples in one
language, using one of
{{ range $i, $l := site.Data.quickstart.languages }}{{ if $i }}, {{ end }}`{{ $l.key }}`{{ end }}.
For example, {{ $gettingStarted.Permalink }}?lang=python opens the Getting Started
guide with the Python examples selected, and the choice carries over to other pages.
The Markdown version of each documentation page (`index.md`) always includes
every language.

## Next steps
{{ range slice "/documentation/webdriver/getting_started" "/documentation/webdriver" "/documentation/grid" "/documentation/ai_agents" }}
{{- with site.GetPage . }}
{{- $link := .Permalink }}
{{- with .OutputFormats.Get "markdown" }}{{ $link = .Permalink }}{{ end }}
- [{{ .Title | strings.TrimSpace }}]({{ $link }})
  {{- with .Description | strings.TrimSpace }}: {{ . | replaceRE `\s+` " " }}{{ end }}
{{- end }}
{{- end }}
