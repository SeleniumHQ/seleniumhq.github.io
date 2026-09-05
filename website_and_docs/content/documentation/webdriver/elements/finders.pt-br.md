---
title: "Encontrando Elementos Web"
linkTitle: "Finders"
weight: 2
needsTranslation: true
aliases: [
"/documentation/pt-br/webdriver/locating_elements/",
"/pt-br/documentation/webdriver/locating_elements/"
]
description: >
  Localizando elementos com base nos valores providenciados pelo localizador.
---
Um dos aspectos mais fundamentais do uso do Selenium é obter referências de elementos para trabalhar.
O Selenium oferece várias [estratégias de localizador]({{< ref "locators.md" >}}) para identificar exclusivamente um elemento.
Há muitas maneiras de usar os localizadores em cenários complexos. Para os propósitos desta documentação,
use a [página de teste de localizadores do Selenium](https://www.selenium.dev/selenium/web/locators_tests/locators.html).

## Primeiro Elemento correspondente
Muitos localizadores irão corresponder a vários elementos na página.
O método de elemento de localização singular retornará uma referência ao
primeiro elemento encontrado dentro de um determinado contexto.

### Avaliando o DOM inteiro
Quando o metodo find element é chamado na instância do driver, ele
retorna uma referência ao primeiro elemento no DOM que corresponde ao localizador fornecido.
Esse valor pode ser guardado e usado para ações futuras do elemento. Na página de teste de localizadores do Selenium, existem
dois elementos com o nome de classe `information`, então este método retorna o primeiro campo de texto.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L22-L23">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L7-L8">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L15-L16">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L10-L11" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L9-L10">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L19-L20">}}
  {{< /tab >}}
{{< /tabpane >}}


### Avaliando um subconjunto do DOM
Ao em vez de tentar encontrar um localizador unico no DOM inteiro, normalmente é útil restringir a busca ao escopo de outro elemento
já localizado.

Uma possível solução seria localizar um ancestral do elemento desejado, então invoque o find element nesse objeto:

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L31-L33">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L14-L16">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L25-L27">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L17-L19" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L18-L20">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L27-L29">}}
  {{< /tab >}}
{{< /tabpane >}}

{{% pageinfo color="info" %}}
**Java e C#**<br>
As classes `WebDriver`, `WebElement` e `ShadowRoot` todas implementam o `SearchContext` interface, que é
considerada uma _role-based interface_(interface baseada em função). As interfaces baseadas em função permitem determinar se uma determinada
implementação de driver suporta um recurso específico. Essas interfaces são claramente definidas e tentam
aderir a ter apenas um único papel de responsabilidade.
{{% /pageinfo %}}

### Evaluating the Shadow DOM

The Shadow DOM is an encapsulated DOM tree hidden inside an element. 
With the release of v96 in Chromium Browsers, Selenium can now allow you to access this tree 
with easy-to-use shadow root methods. NOTE: These methods require Selenium 4.0 or greater.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
{{< tab header="Java" >}}
WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"));
SearchContext shadowRoot = shadowHost.getShadowRoot();
WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#shadow_content"));
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L39-L42">}}
{{< /tab >}}
{{< tab header="CSharp" >}}
var shadowHost = _driver.FindElement(By.CssSelector("#shadow_host"));
var shadowRoot = shadowHost.GetShadowRoot();
var shadowContent = shadowRoot.FindElement(By.CssSelector("#shadow_content"));
{{< /tab >}}
{{< tab header="Ruby" >}}
shadow_host = @driver.find_element(css: '#shadow_host')
shadow_root = shadow_host.shadow_root
shadow_content = shadow_root.find_element(css: '#shadow_content')
{{< /tab >}}
{{< tab header="JavaScript" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< badge-code >}}
{{< /tab >}}
{{< /tabpane >}}

### Localizador otimizado
Uma pesquisa aninhada pode não ser a estratégia de localização mais eficaz, pois requer dois
comandos separados a serem emitidos para o navegador.

Para melhorar um pouco o desempenho, podemos usar CSS ou XPath para encontrar esse elemento com um único comando.
Veja as [sugestões de estratégia do localizador]({{< ref "/documentation/test_practices/encouraged/locators" >}}) na nossa sessão de
[Práticas de teste incentivadas]({{< ref "/documentation/test_practices/encouraged" >}}).

Para esse exemplo, utilizaremos um seletor CSS:

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L41-L42">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L22-L23">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L36-L37">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L25-L26" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L28-L29">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L36-L37">}}
  {{< /tab >}}
{{< /tabpane >}}


## Todos os elementos correspondentes
Existem vários casos de uso para a necessidade de obter referências a todos os elementos que correspondem a um localizador, em vez
do que apenas o primeiro. Os métodos plurais find elements retornam uma coleção de referências de elementos.
Se não houver correspondências, uma lista vazia será retornada. Nesse caso,
referências a todos os elementos input serão devolvidas em uma coleção.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L50-L51">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L29-L30">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L46-L47">}}
  {{< /tab >}}
{{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L32-L33" >}}
{{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L37-L38">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L44-L45">}}
  {{< /tab >}}
{{< /tabpane >}}

### Obter Elemento
Muitas vezes você obterá uma coleção de elementos, mas quer trabalhar apenas com um elemento específico, o que significa que você
precisa iterar sobre a coleção e identificar o que você deseja.


{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L61-L64">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L51-L53">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L58-L62">}}
  {{< /tab >}}
   {{< tab header="Ruby" >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L40-L42" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L47-L50">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L53-L56">}}
  {{< /tab >}}
{{< /tabpane >}}

## Localizar Elementos em um Elemento

Ele é usado para localizar a lista de WebElements filhos correspondentes dentro do contexto do elemento pai.
Para realizar isso, o WebElement pai é encadeado com o 'findElements' para acessar seus elementos filhos.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L74-L78">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L61-L64">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L73-L78">}}
  {{< /tab >}}
   {{< tab header="Ruby" >}}
   {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L48-L51" >}}
   {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L59-L63">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L64-L68">}}
  {{< /tab >}}
{{< /tabpane >}}

## Obter elemento ativo

Ele é usado para rastrear (ou) encontrar um elemento DOM que tem o foco no contexto de navegação atual.

{{< tabpane langEqualsHeader=true text=true >}}
  {{< tab header="Java" >}}
  {{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/elements/FindersTest.java#L88-L89">}}
  {{< /tab >}}
  {{< tab header="Python" >}}
  {{< gh-codeblock path="/examples/python/tests/elements/test_finders.py#L72-L73">}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
  {{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Elements/FindersTest.cs#L89-L90">}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
  {{< gh-codeblock path="/examples/ruby/spec/elements/finders_spec.rb#L58-L60" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< gh-codeblock path="/examples/javascript/test/elements/finders.spec.js#L72-L73">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
  {{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/elements/FindersTest.kt#L76-L77">}}
  {{< /tab >}}
{{< /tabpane >}}


