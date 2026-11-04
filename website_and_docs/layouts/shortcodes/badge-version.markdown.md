{{- /* Markdown output of badge-version: see badge-version.html. */ -}}
{{- $version := .Get "version" -}}
_Requires [Selenium v{{ $version }}](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-{{ $version }}.0) or later._
