---
title: "Ações do Mouse"
linkTitle: "Mouse"
weight: 4
needsTranslation: true
description: >
  Uma representação de qualquer dispositivo de ponteiro para interagir com uma página da web.
aliases: [
"/documentation/pt-br/support_packages/mouse_and_keyboard_actions_in_detail/",
"/pt-br/documentation/support_packages/mouse_and_keyboard_actions_in_detail/"
]
---

Existem apenas 3 ações que podem ser realizadas com um mouse: pressionar um botão, liberar um botão pressionado e mover o mouse. O Selenium fornece métodos de conveniência que combinam essas ações da maneira mais comum.

## Clicar e Manter Pressionado

Este método combina mover o mouse para o centro de um elemento com a pressão do botão esquerdo do mouse. Isso é útil para focar em um elemento específico:

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L22-L25" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L12-L15" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L17-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L11-L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/clickAndHold.spec.js#L16-L18" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L23-L26" >}}
{{< /tab >}}
{{< /tabpane >}}

## Clicar e Liberar

Este método combina mover o mouse para o centro de um elemento com a pressão e liberação do botão esquerdo do mouse. Isso é conhecido como "clicar":

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L34-L37" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L24-L27" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L30-L33" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L22-L25" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/clickAndRelease.spec.js#L16-L18" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L35-L38" >}}
{{< /tab >}}
{{< /tabpane >}}

## Clique com o Botão Alternativo

Existem um total de 5 botões definidos para um mouse:
- 0 — Botão Esquerdo (o padrão)
- 1 — Botão do Meio (atualmente não suportado)
- 2 — Botão Direito
- 3 — Botão X1 (Voltar)
- 4 — Botão X2 (Avançar)


###  Context Click

Este método combina mover o mouse para o centro de um elemento com a pressão e liberação do botão direito do mouse (botão 2). Isso é conhecido como "clicar com o botão direito" ou "menu de contexto"


{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L46-L49" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L35-L38" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L43-L46" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L34-L37" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/rightClick.spec.js#L17-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L47-L50" >}}
{{< /tab >}}
{{< /tabpane >}}

### Click botão de voltar do mouse

Este termo pode se referir a um clique com o botão X1 (botão de voltar) do mouse. No entanto, essa terminologia específica pode variar dependendo do contexto.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L60-L66" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L49-L52" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L59-L63" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L47-L50" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.5.0" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/backAndForwardClick.spec.js#L21-L22" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L61-L67" >}}
{{< /tab >}}
{{< /tabpane >}}

### botão de avançar) do mouse

Este termo se refere a um clique com o botão X2 (botão de avançar) do mouse. Não existe um método de conveniência específico para essa ação, sendo apenas a pressão e liberação do botão do mouse de número 4.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L78-L84" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L63-L66" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L77-L81" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< badge-version version="4.2" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L61-L64" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-version version="4.5.0" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/backAndForwardClick.spec.js#L34-L35" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L79-L85" >}}
{{< /tab >}}
{{< /tabpane >}}

## Duplo click

Este método combina mover o mouse para o centro de um elemento com a pressão e liberação do botão esquerdo do mouse duas vezes. Isso é conhecido como "duplo clique".

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L93-L96" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L74-L77" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L91-L94" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L73-L76" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/doubleClick.spec.js#L17-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L94-L97" >}}
{{< /tab >}}
{{< /tabpane >}}

## Mover para o Elemento

Este método move o mouse para o ponto central do elemento que está visível na tela. Isso é conhecido como "hovering" ou "pairar". É importante observar que o elemento deve estar no viewport (área visível na tela) ou então o comando resultará em erro.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L105-L108" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L85-L88" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L104-L107" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L84-L87" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/moveToElement.spec.js#L17-L19" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L106-L109" >}}
{{< /tab >}}
{{< /tabpane >}}

## Mover por Deslocamento

Esses métodos primeiro movem o mouse para a origem designada e, em seguida, pelo número de pixels especificado no deslocamento fornecido. É importante observar que a posição do mouse deve estar dentro da janela de visualização (viewport) ou, caso contrário, o comando resultará em erro.

### Deslocamento a partir do Elemento

Este método move o mouse para o ponto central do elemento visível na tela e, em seguida, move o mouse pelo deslocamento fornecido.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L118-L121" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L96-L99" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L132-L135" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L97-L100" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L119-L122" >}}
{{< /tab >}}
{{< /tabpane >}}

### Deslocamento a partir da Janela de Visualização

Este método move o mouse a partir do canto superior esquerdo da janela de visualização atual pelo deslocamento fornecido.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L131-L136" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L108-L110" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L146-L150" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L114-L116" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/moveByOffset.spec.js#L29-L30" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L132-L137" >}}
{{< /tab >}}
{{< /tabpane >}}

### Deslocamento a partir da Localização Atual do Ponteiro
Este método move o mouse a partir de sua posição atual pelo deslocamento fornecido pelo usuário. Se o mouse não tiver sido movido anteriormente, a posição será no canto superior esquerdo da janela de visualização. É importante notar que a posição do ponteiro não muda quando a página é rolada.

Observe que o primeiro argumento, X, especifica o movimento para a direita quando positivo, enquanto o segundo argumento, Y, especifica o movimento para baixo quando positivo. Portanto, `moveByOffset(30, -10)` move o mouse 30 unidades para a direita e 10 unidades para cima a partir da posição atual do mouse.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L153-L155" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L124-L126" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L167-L169" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L128-L130" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/moveByOffset.spec.js#L42" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L154-L156" >}}
{{< /tab >}}
{{< /tabpane >}}

## Arrastar e Soltar no Elemento

Este método primeiro realiza um clique e mantém pressionado no elemento de origem, move para a localização do elemento de destino e, em seguida, libera o botão do mouse.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L166-L170" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L137-L141" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L181-L185" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L141-L145" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/dragAndDrop.spec.js#L29-L32" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L167-L171" >}}
{{< /tab >}}
{{< /tabpane >}}

## Arrastar e Soltar pelo Deslocamento

Este método primeiro realiza um clique e mantém pressionado no elemento de origem, move para o deslocamento fornecido e, em seguida, libera o botão do mouse.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/actions_api/MouseTest.java#L179-L184" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/actions_api/test_mouse.py#L149-L154" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/ActionsAPI/MouseTest.cs#L195-L200" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/actions_api/mouse_spec.rb#L153-L158" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/actionsApi/mouse/dragAndDrop.spec.js#L17-L21" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/MouseTest.kt#L180-L185" >}}
{{< /tab >}}
{{< /tabpane >}}
