{{- /* Markdown output of gh-contributors: a link to the repository's
  contributors on GitHub, rather than the avatar grid the HTML fetches. */ -}}
{{- $repo := replaceRE `^https://api\.github\.com/repos/([^/]+/[^/]+)/.*$` "$1" (.Get 0) }}
[Contributors to {{ $repo }}](https://github.com/{{ $repo }}/graphs/contributors)
