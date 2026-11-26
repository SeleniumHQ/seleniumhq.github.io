---
title: "Working with IFrames and frames"
linkTitle: "Frames"
weight: 6
aliases: [
"/documentation/webdriver/browser/frames/"
]
---

Frames são um meio obsoleto de construir um layout de site a partir de
vários documentos no mesmo domínio. É improvável que você trabalhe com eles
a menos que você esteja trabalhando com um webapp pré-HTML5. Iframes permitem
a inserção de um documento de um domínio totalmente diferente, e são
ainda comumente usado.

Se você precisa trabalhar com frames ou iframes, o WebDriver permite que você
trabalhe com eles da mesma maneira. Considere um botão dentro de um iframe.
Se inspecionarmos o elemento usando as ferramentas de desenvolvimento do navegador, podemos
ver o seguinte:

```html
<div id="modal">
  <iframe id="buttonframe" name="myframe"  src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
```

Se não fosse pelo iframe, esperaríamos clicar no botão
usando algo como:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//This won't work
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # This Wont work
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//This won't work
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # This won't work
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This won't work
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//This won't work
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

No entanto, se não houver botões fora do iframe, você pode
em vez disso, obter um erro _no such element_. Isso acontece porque o Selenium é
ciente apenas dos elementos no documento de nível superior. Para interagir com
o botão, precisamos primeiro mudar para o quadro, de forma semelhante
a como alternamos janelas. WebDriver oferece três maneiras de mudar para
um frame.

## Usando um WebElement

Alternar usando um WebElement é a opção mais flexível. Você pode
encontrar o quadro usando seu seletor preferido e mudar para ele.

{{< tabpane langEqualsHeader=true >}}
 {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/FramesTest.java#L38-L46" >}}
{{< /tab >}}
     {{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_frames.py#L24-L32" >}}
{{< /tab >}}
      {{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/FramesTest.cs#L38-L46" >}}
{{< /tab >}}
  
{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/frames_spec.rb#L26-L33" >}}
{{< /tab >}}


{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/frames.spec.js#L17-L25" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/FramesTest.kt#L15-L23" >}}
{{< /tab >}}
{{< /tabpane >}}

## Usando um *name* ou ID
Se o seu frame ou iframe tiver um atributo id ou name, ele pode ser
usado alternativamente. Se o name ou ID não for exclusivo na página, o
primeiro encontrado será utilizado.

{{< tabpane langEqualsHeader=true >}}
 {{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/FramesTest.java#L50-L58" >}}
  {{< /tab >}}

{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_frames.py#L34-L42" >}}
{{< /tab >}}

   {{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/FramesTest.cs#L50-L58" >}}
{{< /tab >}}
 

{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/frames_spec.rb#L36-L43" >}}
{{< /tab >}}



{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/frames.spec.js#L34-L43" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/FramesTest.kt#L33-L42" >}}
{{< /tab >}}
{{< /tabpane >}}

## Usando um índice

Também é possível usar o índice do frame, podendo ser
consultado usando _window.frames_ em JavaScript.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/FramesTest.java#L62-L63" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_frames.py#L45-L46" >}}
{{< /tab >}}

{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/FramesTest.cs#L62-L63" >}}
{{< /tab >}}

{{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/frames_spec.rb#L46-L47" >}}
{{< /tab >}}


{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/frames.spec.js#L52-L53" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/FramesTest.kt#L52-L53" >}}
{{< /tab >}}
{{< /tabpane >}}


## Deixando um frame

Para deixar um iframe ou frameset, volte para o conteúdo padrão
como a seguir:

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/interactions/FramesTest.java#L66-L67" >}}
{{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="/examples/python/tests/interactions/test_frames.py#L49-L50" >}}
{{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Interactions/FramesTest.cs#L66-L67" >}}
{{< /tab >}}

 {{< tab header="Ruby" text=true >}}
{{< gh-codeblock path="/examples/ruby/spec/interactions/frames_spec.rb#L49-L50" >}}
{{< /tab >}}


{{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/frames.spec.js#L62-L63" >}}
{{< /tab >}}
{{< tab header="Kotlin" text=true >}}
{{< gh-codeblock path="/examples/kotlin/src/test/kotlin/dev/selenium/interactions/FramesTest.kt#L63-L64" >}}
{{< /tab >}}
{{< /tabpane >}}
