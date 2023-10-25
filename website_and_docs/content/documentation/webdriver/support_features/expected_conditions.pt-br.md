---
title: "Aguardando com Condições Esperadas"
linkTitle: "Condições Esperadas"
weight: 1
description: >
  Essas são classes usadas para descrever o que deve ser aguardado.
---

As "Condições Esperadas" são usadas com [Esperas Explícitas]({{< ref "../waits#explicit-waits" >}}). Em vez de definir um bloco de código a ser executado com um _lambda_, um método de condição esperada pode ser criado para representar coisas comuns que precisam ser aguardadas. Alguns métodos recebem localizadores como argumentos, enquanto outros recebem elementos como argumentos.

Esses métodos podem incluir condições como:

* elemento existe
* elemento está obsoleto
* elemento está visível
* texto está visível
* título contém o valor especificado


{{< tabpane text=true langEqualsHeader=true >}}
{{% tab header="Java" %}}
[Expected Conditions Documentation](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html)
{{< badge-code >}}
{{% /tab %}}
{{< tab header="Python" >}}
[Expected Conditions Documentation](https://www.selenium.dev/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html)
{{< badge-code >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
.NET stopped supporting Expected Conditions in Selenium 4 to minimize maintenance hassle and redundancy.
{{< /tab >}}
{{< tab header="Ruby" >}}
Ruby makes frequent use of blocks, procs and lambdas and does not need Expected Conditions classes
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}
