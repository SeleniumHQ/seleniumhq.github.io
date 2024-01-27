---
title: "Ações API"
linkTitle: "Ações API"
weight: 14
description: >
    Uma interface de baixo nível para fornecer ações de entrada de dispositivo virtualizadas para o navegador da web..
---
Além das [interações de alto nível]({{< ref "/documentation/webdriver/elements/interactions.md" >}}), a [API de Ações](https://w3c.github.io/webdriver/#dfn-actions) oferece controle detalhado sobre o que dispositivos de entrada designados podem fazer. O Selenium fornece uma interface para 3 tipos de fontes de entrada: entrada de teclado para dispositivos de teclado, entrada de ponteiro para mouse, caneta ou dispositivos de toque, e entrada de roda para dispositivos de roda de rolagem (introduzida no Selenium 4.2). O Selenium permite que você construa comandos de ação individuais atribuídos a entradas específicas, encadeie-os e chame o método de execução associado para executá-los todos de uma vez.

## Construtor de Ações

Na transição do antigo Protocolo JSON Wire para o novo Protocolo W3C WebDriver, os componentes de construção de ações de baixo nível se tornaram especialmente detalhados. Isso é extremamente poderoso, mas cada dispositivo de entrada possui várias maneiras de ser utilizado e, se você precisa gerenciar mais de um dispositivo, é responsável por garantir a sincronização adequada entre eles.

Felizmente, provavelmente você não precisa aprender a usar os comandos de baixo nível diretamente, uma vez que quase tudo o que você pode querer fazer foi fornecido com um método de conveniência que combina os comandos de nível inferior para você. Todos esses métodos estão documentados nas páginas de [teclado]({{< ref "keyboard" >}}), [mouse]({{< ref "mouse" >}}), [caneta]({{< ref "pen" >}}) e [roda]({{< ref "wheel" >}}).

## Pausa

Movimentos de ponteiro e rolagem da roda permitem que o usuário defina uma duração para a ação, mas às vezes você só precisa esperar um momento entre as ações para que as coisas funcionem corretamente.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/ActionsTest.java#L21-L28" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_actions.py#L13-L20" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/ActionsTest.cs#L18-L25" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/actions_spec.rb#L12-L19" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/actionsTest.spec.js#L20-L27" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/ActionsTest.kt#L22-L29" >}}
{{< /tab >}}
{{< /tabpane >}}

## Liberar Todas as Ações

Um ponto importante a ser observado é que o driver lembra o estado de todos os itens de entrada ao longo de uma sessão. Mesmo se você criar uma nova instância de uma classe de ações, as teclas pressionadas e a posição do ponteiro permanecerão no estado em que uma ação previamente executada os deixou.

Existe um método especial para liberar todas as teclas pressionadas e botões do ponteiro atualmente pressionados. Esse método é implementado de maneira diferente em cada uma das linguagens porque não é executado com o método de execução (perform).

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/ActionsTest.java#L46" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_actions.py#L37" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/ActionsTest.cs#L44" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/actions_spec.rb#L36" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/actionsTest.spec.js#L44" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/ActionsTest.kt#L47" >}}
{{< /tab >}}
{{< /tabpane >}}
