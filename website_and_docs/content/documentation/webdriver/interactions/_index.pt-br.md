---
title: "Browser interactions"
linkTitle: "Interactions"
weight: 10
requiresTranslation: true
aliases: [
"/documentation/pt-br/webdriver/browser_manipulation/",
"/pt-br/documentation/webdriver/browser_manipulation/",
"/pt-br/documentation/webdriver/browser/"
]
---

## Get browser information

### Coletar título

Você pode ler o título da página atual no navegador:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/InteractionsTest.java#L15" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_interactions.py#L7" >}}
{{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/InteractionsTest.cs#L37" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/interactions/browser_spec.rb#L8" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/interactionsIndex.spec.js#L20" >}}
{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.title{{< /tab >}}
{{< /tabpane >}}


### Coletar a URL atual

Você pode ler a URL atual na barra de endereço do navegador usando:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/InteractionsTest.java#L26" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_interactions.py#L10" >}}
{{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/InteractionsTest.cs#L41" >}}
{{< /tab >}}
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="examples/ruby/spec/interactions/browser_spec.rb#L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="examples/javascript/test/interactions/interactionsIndex.spec.js#L24" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}driver.currentUrl{{< /tab >}}
{{< /tabpane >}}
