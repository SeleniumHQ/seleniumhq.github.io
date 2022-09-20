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
  {{< tab header="Java" >}}driver.getTitle();{{< /tab >}}
  {{< tab header="Python" >}}driver.title{{< /tab >}}
  {{< tab header="CSharp" >}}driver.Title;{{< /tab >}}
  {{< tab header="Ruby" >}}driver.title{{< /tab >}}
  {{< tab header="JavaScript" >}}await driver.getTitle();{{< /tab >}}
  {{< tab header="Kotlin" >}}driver.title{{< /tab >}}
{{< /tabpane >}}


### Coletar a URL atual

Você pode ler a URL atual na barra de endereço do navegador usando:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}driver.getCurrentUrl();{{< /tab >}}
{{< tab header="Python" >}}driver.current_url{{< /tab >}}
{{< tab header="CSharp" >}}driver.Url;{{< /tab >}}
{{< tab header="Ruby" >}}driver.current_url{{< /tab >}}
{{< tab header="JavaScript" >}}await driver.getCurrentUrl();{{< /tab >}}
{{< tab header="Kotlin" >}}driver.currentUrl{{< /tab >}}
{{< /tabpane >}}
