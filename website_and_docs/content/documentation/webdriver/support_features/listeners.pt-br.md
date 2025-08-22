---
title: "Ouvintes de Comando"
linkTitle: "Ouvintes"
weight: 2
aliases: [
  "/documentation/webdriver/drivers/listeners",
]
---

isso permite que você execute ações personalizadas sempre que comandos específicos do Selenium são enviados.


## Definir

Um Listener personalizado precisa herdar de AbstractEventListener no Selenium e sobrescrever os métodos em AbstractEventListener.
Aqui está um exemplo simples para imprimir e registrar eventos:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/support/test_listener.py#L1-L33" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

## Uso

Crie um driver de ouvinte de eventos através do EventFiringWebDriver:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-code >}}
{{< /tab >}}
{{% tab header="Python" %}}
{{< gh-codeblock path="examples/python/tests/support/test_listener.py#L87-L96" >}}
{{% /tab %}}
{{< tab header="CSharp" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

