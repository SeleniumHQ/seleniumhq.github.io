---
title: "Ações de Caneta"
linkTitle: "Caneta"
weight: 5
description: >
  Uma representação de uma entrada de ponteiro do tipo caneta stylus para interagir com uma página da web.
---

{{< badge-browser browser=Chromium wpt="perform_actions/pointer.py" >}}
Uma caneta é um tipo de entrada de ponteiro que possui a maior parte do mesmo comportamento que um mouse, mas também pode ter propriedades de evento únicas para uma caneta stylus. Além disso, enquanto um mouse possui 5 botões, uma caneta possui 3 estados equivalentes de botão:

* 0 — Contato por Toque (o padrão; equivalente a um clique com o botão esquerdo)
* 2 — Botão do Barril (equivalente a um clique com o botão direito)
* 5 — Botão de Borracha (atualmente não suportado pelos drivers)


## Usando uma Caneta

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/PenTest.java#L26-L33" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_pen.py#L12-L20" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/PenTest.cs#L19-L28" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/pen_spec.rb#L11-L17" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/PenTest.kt#L23-L30" >}}
{{< /tab >}}
{{< /tabpane >}}

## Adicionando Atributos de Evento de Ponteiro

{{< badge-version version="4.2" >}}

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/PenTest.java#L67-L81" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_pen.py#L53-L61" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/PenTest.cs#L64-L77" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/pen_spec.rb#L50-L56" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/PenTest.kt#L64-L78" >}}
{{< /tab >}}
{{< /tabpane >}}

