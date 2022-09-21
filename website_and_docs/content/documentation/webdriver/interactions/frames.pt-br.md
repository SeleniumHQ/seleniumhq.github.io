---
title: "Working with iFrames and frames"
linkTitle: "Frames"
weight: 6
aliases: [
"/pt-br/documentation/webdriver/browser/frames/"
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
  {{< tab header="Java" >}}
//Store the web element
WebElement iframe = driver.findElement(By.cssSelector("#modal>iframe"));

//Switch to the frame
driver.switchTo().frame(iframe);

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # Store iframe web element
iframe = driver.find_element(By.CSS_SELECTOR, "#modal > iframe")

    # switch to selected iframe
driver.switch_to.frame(iframe)

    # Now click on button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Store the web element
IWebElement iframe = driver.FindElement(By.CssSelector("#modal>iframe"));

//Switch to the frame
driver.SwitchTo().Frame(iframe);

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Store iframe web element
iframe = driver.find_element(:css,'#modal > iframe')

    # Switch to the frame
driver.switch_to.frame iframe

    # Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Store the web element
const iframe = driver.findElement(By.css('#modal > iframe'));

// Switch to the frame
await driver.switchTo().frame(iframe);

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Store the web element
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

//Switch to the frame
driver.switchTo().frame(iframe)

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

## Usando um *name* ou ID
Se o seu frame ou iframe tiver um atributo id ou name, ele pode ser
usado alternativamente. Se o name ou ID não for exclusivo na página, o
primeiro encontrado será utilizado.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
//Using the ID
driver.switchTo().frame("buttonframe");

//Or using the name instead
driver.switchTo().frame("myframe");

//Now we can click the button
driver.findElement(By.tagName("button")).click();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # Switch frame by id
driver.switch_to.frame('buttonframe')

    # Now, Click on the button
driver.find_element(By.TAG_NAME, 'button').click()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
//Using the ID
driver.SwitchTo().Frame("buttonframe");

//Or using the name instead
driver.SwitchTo().Frame("myframe");

//Now we can click the button
driver.FindElement(By.TagName("button")).Click();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Switch by ID
driver.switch_to.frame 'buttonframe'

    # Now, Click on the button
driver.find_element(:tag_name,'button').click
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Using the ID
await driver.switchTo().frame('buttonframe');

// Or using the name instead
await driver.switchTo().frame('myframe');

// Now we can click the button
await driver.findElement(By.css('button')).click();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
//Using the ID
driver.switchTo().frame("buttonframe")

//Or using the name instead
driver.switchTo().frame("myframe")

//Now we can click the button
driver.findElement(By.tagName("button")).click()
  {{< /tab >}}
{{< /tabpane >}}

## Usando um índice

Também é possível usar o índice do frame, podendo ser
consultado usando _window.frames_ em JavaScript.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Switches to the second frame
driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Switch to the second frame
driver.switch_to.frame(1)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Switches to the second frame
driver.SwitchTo().Frame(1);
  {{< /tab >}}
  {{< tab header="Python" >}}
    # switching to second iframe based on index
iframe = driver.find_elements(By.TAG_NAME,'iframe')[1]

    # switch to selected iframe
driver.switch_to.frame(iframe)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Switches to the second frame
await driver.switchTo().frame(1);
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Switches to the second frame
driver.switchTo().frame(1)
  {{< /tab >}}
{{< /tabpane >}}


## Deixando um frame

Para deixar um iframe ou frameset, volte para o conteúdo padrão
como a seguir:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Return to the top level
driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Python" >}}
    # switch back to default content
driver.switch_to.default_content()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Return to the top level
driver.SwitchTo().DefaultContent();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
    # Return to the top level
driver.switch_to.default_content
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// Return to the top level
await driver.switchTo().defaultContent();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Return to the top level
driver.switchTo().defaultContent()
  {{< /tab >}}
{{< /tabpane >}}
