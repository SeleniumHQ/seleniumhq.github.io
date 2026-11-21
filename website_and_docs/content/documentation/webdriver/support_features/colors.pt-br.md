---
title: "Trabalhando com cores"
linkTitle: "Trabalhando com cores"
weight: 3
aliases: [
"/documentation/support_packages/working_with_colours/",
"/documentation/webdriver/additional_features/working_with_colours/"
]
---

Ocasionalmente, você desejará validar a cor de algo como parte de seus testes;
o problema é que as definições de cores na web não são constantes.
Não seria bom se houvesse uma maneira fácil de comparar
uma representação HEX de uma cor com uma representação RGB de uma cor,
ou uma representação RGBA de uma cor com uma representação HSLA de uma cor?

Não se preocupe. Existe uma solução: a classe _Color_!

Em primeiro lugar, você precisará importar a classe:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L7" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L2" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L8" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L7" >}}
  {{< /tab >}}
{{< /tabpane >}}

Agora você pode começar a criar objetos coloridos.
Cada objeto de cor precisará ser criado a partir de uma representação de string de
sua cor.
As representações de cores com suporte são:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L13-L19" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L6-L12" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L11-L17" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L13-L19" >}}
  {{< /tab >}}
{{< /tabpane >}}

A classe Color também suporta todas as definições de cores básicas
especificadas em
[http://www.w3.org/TR/css3-color/#html4](//www.w3.org/TR/css3-color/#html4).

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L32-L34" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L24-L26" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L32-L34" >}}
  {{< /tab >}}
{{< /tabpane >}}

Às vezes, os navegadores retornam um valor de cor "transparent"
se nenhuma cor foi definida em um elemento.
A classe Color também oferece suporte para isso:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L43" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L34" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< badge-implementation >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L43" >}}
  {{< /tab >}}
{{< /tabpane >}}

Agora você pode consultar com segurança um elemento
para obter sua cor / cor de fundo sabendo que
qualquer resposta será analisada corretamente
e convertido em um objeto Color válido:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L54-L55" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L43-L44" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L32-L33" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L53-L54" >}}
  {{< /tab >}}
{{< /tabpane >}}

Você pode então comparar diretamente os objetos coloridos:


{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L59" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L48" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L37" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L58" >}}
  {{< /tab >}}
{{< /tabpane >}}

Ou você pode converter a cor em um dos seguintes formatos
e realizar uma validação estática:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/support/ColorsTest.java#L61-L63" >}}
  {{< /tab >}}
  {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/support/test_colors.py#L50-L52" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/support/color_spec.rb#L39-L41" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Essa funcionalidade não está implementada - Nos ajude enviando um PR implementando essa funcionalidade
  {{< /tab >}}
  {{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/support/ColorsTest.kt#L60-L62" >}}
  {{< /tab >}}
{{< /tabpane >}}

As cores não são mais um problema.
