---
title: "Ações de Roda de Rolagem"
linkTitle: "Roda"
weight: 6
description: >
    "Uma representação de um dispositivo de entrada de roda de rolagem para interagir com uma página da web."
---

{{< badge-version version="4.2" >}}
{{< badge-browser browser=Chromium wpt="perform_actions/wheel.py" >}}

Existem 5 cenários para rolagem em uma página.

## Rolagem até o Elemento

Este é o cenário mais comum. Diferentemente dos métodos tradicionais de clique e envio de teclas, a classe de ações não rolará automaticamente o elemento de destino para a visualização, portanto, este método precisará ser usado se os elementos não estiverem dentro da janela de visualização.

Este método recebe um elemento da web como único argumento.

Independentemente de o elemento estar acima ou abaixo da tela de visualização atual, a janela de visualização será rolada de forma que a parte inferior do elemento esteja na parte inferior da tela.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L17-L20" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_wheel.py#L11-L14" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/WheelTest.cs#L17-L20" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/wheel_spec.rb#L11-L14" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/wheelTest.spec.js#L18-L22" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/WheelTest.kt#L18-L21" >}}
{{< /tab >}}
{{< /tabpane >}}

## Rolar por uma Quantidade Especificada

Este é o segundo cenário mais comum para a rolagem. Passe um valor delta x e um valor delta y para o quanto rolar nas direções direita e para baixo. Valores negativos representam esquerda e para cima, respectivamente.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L29-L33" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_wheel.py#L22-L26" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/WheelTest.cs#L31-L35" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/wheel_spec.rb#L22-L26" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/wheelTest.spec.js#L30-L35" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/WheelTest.kt#L30-L34" >}}
{{< /tab >}}
{{< /tabpane >}}

## Rolagem a partir de um Elemento por uma Quantidade Especificada"

Este cenário é efetivamente uma combinação dos dois métodos mencionados anteriormente.

Para executar isso, use o método "Rolar a Partir de", que recebe 3 argumentos. O primeiro representa o ponto de origem, que designamos como o elemento, e os dois seguintes são os valores delta x e delta y.

Se o elemento estiver fora da janela de visualização, ele será rolado para a parte inferior da tela e, em seguida, a página será rolada pelos valores delta x e delta y fornecidos.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L42-L46" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_wheel.py#L35-L39" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/WheelTest.cs#L46-L53" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/wheel_spec.rb#L34-L38" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/wheelTest.spec.js#L45-L49" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/WheelTest.kt#L43-L47" >}}
{{< /tab >}}
{{< /tabpane >}}

## Rolagem a partir de um Elemento com um Deslocamento

Este cenário é usado quando você precisa rolar apenas uma parte da tela que está fora da janela de visualização ou dentro da janela de visualização, mas a parte da tela que deve ser rolada está a uma distância conhecida de um elemento específico.

Isso utiliza novamente o método "Rolar a Partir", e além de especificar o elemento, é especificado um deslocamento para indicar o ponto de origem da rolagem. O deslocamento é calculado a partir do centro do elemento fornecido.

Se o elemento estiver fora da janela de visualização, primeiro ele será rolado até a parte inferior da tela. Em seguida, a origem da rolagem será determinada adicionando o deslocamento às coordenadas do centro do elemento, e, finalmente, a página será rolada pelos valores delta x e delta y fornecidos.

Observe que se o deslocamento a partir do centro do elemento estiver fora da janela de visualização, isso resultará em uma exceção.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L57-L61" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_wheel.py#L50-L54" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/WheelTest.cs#L66-L75" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/wheel_spec.rb#L48-L52" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/wheelTest.spec.js#L62-L66" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/WheelTest.kt#L59-L63" >}}
{{< /tab >}}
{{< /tabpane >}}

## Rolar a partir de um Deslocamento de Origem (Elemento) por uma Quantidade Especificada

O cenário final é usado quando você precisa rolar apenas uma parte da tela que já está dentro da janela de visualização.

Isso utiliza novamente o método "Rolar a Partir", mas a janela de visualização é designada em vez de um elemento. Um deslocamento é especificado a partir do canto superior esquerdo da janela de visualização atual. Após determinar o ponto de origem, a página será rolada pelos valores delta x e delta y fornecidos.

Observe que se o deslocamento a partir do canto superior esquerdo da janela de visualização sair da tela, isso resultará em uma exceção.

{{< tabpane text=true >}}
{{< tab header="Java" >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L73-L76" >}}
{{< /tab >}}
{{< tab header="Python" >}}
{{< gh-codeblock path="examples/python/tests/actions_api/test_wheel.py#L66-L70" >}}
{{< /tab >}}
{{< tab header="CSharp" >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/ActionsAPI/WheelTest.cs#L89-L97" >}}
{{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="examples/ruby/spec/actions_api/wheel_spec.rb#L63-L66" >}}
{{< /tab >}}
{{< tab header="JavaScript" >}}
{{< gh-codeblock path="examples/javascript/test/actionsApi/wheelTest.spec.js#L80-L82" >}}
{{< /tab >}}
{{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/actions_api/WheelTest.kt#L75-L78" >}}
{{< /tab >}}
{{< /tabpane >}}
