---
title: "Localizando elementos"
weight: 3
---

### Localizando elementos

Uma das técnicas mais fundamentais para aprender ao usar o WebDriver é
como encontrar elementos na página. WebDriver oferece uma série de tipos de seletores embutidos,
entre eles encontrar um elemento por seu atributo de ID:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));  
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.find_element(By.ID, "cheese")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement element = driver.FindElement(By.Id("cheese"));  
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: 'cheese')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = driver.findElement(By.id('cheese'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< / code-panel >}}
{{< / code-tab >}}

Como visto no exemplo, a localização de elementos no WebDriver é feita no
objeto da instância `WebDriver`. O método `findElement(By)` retorna
outro tipo de objeto fundamental, o `WebElement`.

* `WebDriver` representa o navegador
* `WebElement` representa um nó DOM específico
   (um controle, por exemplo, um link ou input, etc.)

Depois de ter uma referência a um elemento da web que foi "encontrado",
você pode restringir o escopo de sua pesquisa
usando a mesma chamada nessa instância do objeto:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
WebElement cheddar = cheese.findElement(By.id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheese = driver.find_element(By.ID, "cheese")
cheddar = cheese.find_elements_by_id("cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement cheese = driver.FindElement(By.Id("cheese"));
IWebElement cheddar = cheese.FindElement(By.Id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: 'cheese')
cheddar = cheese.find_element(id: 'cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = driver.findElement(By.id('cheese'));
const cheddar = cheese.findElement(By.id('cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

Você pode fazer isso porque ambos os tipos _WebDriver_ e _WebElement_
implementam a interface [_SearchContext_](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/SearchContext.html). No WebDriver, isso é conhecido como _interface baseada em função_.
As interfaces baseadas em funções permitem que você determine se uma implementação do driver suporta um determinado recurso. Essas interfaces são
claramente definidas e tentam aderir a ter apenas uma única função de
responsabilidade. Você pode ler mais sobre o design do WebDriver e quais
funções são suportadas em quais drivers em [Alguma outra seção que
deve ser nomeada](#).
<!-- TODO: A new section needs to be created for the above.-->

Consequentemente, a interface _By_ usada acima também suporta um
número de estratégias de localização adicionais. Uma pesquisa aninhada pode não ser
a estratégia de localização de *cheese* mais eficaz, pois requer dois
comandos separados a serem emitidos para o navegador; primeiro pesquisando o DOM
para um elemento com o ID "cheese", em seguida, uma pesquisa por "cheddar" em um
contexto restrito.

Para melhorar um pouco o desempenho, devemos tentar usar um
localizador mais específico: WebDriver suporta a procura de elementos
por localizadores CSS, o que nos permite combinar os dois localizadores anteriores em
uma única pesquisa:

{{< code-tab >}}
  {{< code-panel language="java" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheddar = driver.find_element_by_css_selector("#cheese #cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
driver.FindElement(By.CssSelector("#cheese #cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.find_element(css: '#cheese #cheddar')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheddar = driver.findElement(By.css('#cheese #cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

### Localizando mútliplos elementos

É possível que o documento com o qual estamos trabalhando tenha uma
lista ordenada dos queijos que mais gostamos:

```html
<ol id=cheese>
 <li id=cheddar>…
 <li id=brie>…
 <li id=rochefort>…
 <li id=camembert>…
</ol>
```

Uma vez que mais queijo é indiscutivelmente melhor, e seria complicado
ter que recuperar cada um dos itens individualmente, uma
técnica melhor para buscar *cheese* é fazer uso da versão pluralizada `findElements(By)`.
Este método retorna uma coleção de elementos web.
Se apenas um elemento for encontrado, ele ainda retornará uma
coleção (de um elemento). Se nenhum elemento corresponder ao localizador, um
lista vazia será retornada.

{{< code-tab >}}
  {{< code-panel language="java" >}}
List<WebElement> muchoCheese = driver.findElements(By.cssSelector("#cheese li"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
mucho_cheese = driver.find_elements_by_css_selector("#cheese li")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IReadOnlyList<IWebElement> muchoCheese = driver.FindElements(By.CssSelector("#cheese li"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
mucho_cheese = driver.find_elements(css: '#cheese li')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const muchoCheese = driver.findElements(By.css('#cheese li'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val muchoCheese: List<WebElement>  = driver.findElements(By.cssSelector("#cheese li"))
  {{< / code-panel >}}
{{< / code-tab >}}

### Estratégias de seleção de elemento

Existem oito estratégias diferentes de localização de elementos embutidas no WebDriver:

| Localizador | Descrição |
| -------- | ---------- |
| class name | Localiza elementos cujo nome de classe contém o valor de pesquisa (nomes de classes compostas não são permitidos) |
| css selector | Localiza elementos que correspondem a um seletor CSS |
| id | Localiza elementos cujo atributo de ID corresponde ao valor de pesquisa |
| name | Localiza elementos cujo atributo NAME corresponde ao valor de pesquisa |
| link text | Localiza elementos âncora cujo texto visível corresponde ao valor de pesquisa |
| partial link text | Localiza elementos âncora cujo texto visível contém o valor da pesquisa. Se vários elementos forem correspondentes, apenas o primeiro será selecionado. |
| tag name | Localiza elementos cujo nome de tag corresponde ao valor de pesquisa |
| xpath | Localiza elementos que correspondem a uma expressão XPath |

### Dicas sobre como usar seletores

No geral, se os IDs de HTML estiverem disponíveis, únicos e consistentemente
previsíveis, eles são o método preferido para localizar um elemento
uma página. Eles tendem a trabalhar muito rapidamente e dispensar muito processamento
que vem com travessias de DOM complicadas.

Se IDs exclusivos não estiverem disponíveis, um seletor CSS bem escrito é o
método preferido de localização de um elemento. XPath funciona bem como CSS
seletores, mas a sintaxe é complicada e frequentemente difícil de
depurar. Embora os seletores XPath sejam muito flexíveis, eles não são tipicamente testados em performance por fornecedores de navegadores e tendem a ser bastante lentos.

As estratégias de seleção baseadas em _linkText_ e _partialLinkText_ têm
desvantagens porque eles só funcionam em elementos de link. Além disso, eles
chamam seletores XPath internamente no WebDriver.

O nome da tag pode ser uma maneira perigosa de localizar elementos. tem
frequentemente, vários elementos da mesma tag presentes na página.
Isso é útil principalmente ao chamar o método _findElements(By) _ que
retorna uma coleção de elementos.

A recomendação é manter seus localizadores compactos e
legíveis quanto possível. Pedir ao WebDriver para percorrer a estrutura DOM
é uma operação cara, e quanto mais você pode restringir o escopo de
sua pesquisa, melhor.

## Localizadores relativos

**Selenium 4** traz localizadores relativos que eram anteriormente
chamado de _Localizadores Amigáveis_. Esta funcionalidade foi
adicionada para ajudá-lo a localizar elementos que estão próximos a outros elementos.
Os localizadores relativos disponíveis são:

* *above*
* *below*
* *toLeftOf*
* *toRightOf*
* *near*

O método _findElement_ agora aceita um novo método `withTagName()`
que retorna um RelativeLocator.

### Como funciona

Selenium usa a função JavaScript
[getBoundingClientRect()](https://developer.mozilla.org/en-US/docs/Web/API/Element/getBoundingClientRect)
para encontrar os elementos relativos. Esta função retorna
propriedades de um elemento, como
right, left, bottom, and top.

Vamos considerar o exemplo abaixo para entender os localizadores relativos.

![Relative Locators](/images/relative_locators.png?width=400px)

### above()

Retorna o WebElement, que aparece
acima do elemento especificado

{{< code-tab >}}
  {{< code-panel language="java" >}}
//import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;
WebElement passwordField = driver.findElement(By.id("password"));
WebElement emailAddressField = driver.findElement(withTagName("input")
                                                  .above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#from selenium.webdriver.support.relative_locator import with_tag_name
passwordField = driver.find_element(By.ID, "password")
emailAddressField = driver.find_element(with_tag_name("input").above(passwordField))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//using static OpenQA.Selenium.RelativeBy;
IWebElement passwordField = driver.FindElement(By.Id("password"));
IWebElement emailAddressField = driver.FindElement(WithTagName("input")
                                                   .Above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
password_field = driver.find_element(:id, "password")
email_address_field = driver.find_element(relative: {tag_name: 'input', above:password_field})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let passwordField = driver.findElement(By.id('password'));
let emailAddressField = await driver.findElements(withTagName('input').above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val passwordField = driver.findElement(By.id("password"))
val emailAddressField = driver.findElement(withTagName("input").above(passwordField))
  {{< / code-panel >}}
{{< / code-tab >}}


### below()

Retorna o WebElement, que aparece
abaixo do elemento especificado

{{< code-tab >}}
  {{< code-panel language="java" >}}
//import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;
WebElement emailAddressField = driver.findElement(By.id("email"));
WebElement passwordField = driver.findElement(withTagName("input")
	                                          .below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#from selenium.webdriver.support.relative_locator import with_tag_name
emailAddressField = driver.find_element(By.ID, "email")
passwordField = driver.find_element(with_tag_name("input").below(emailAddressField))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//using static OpenQA.Selenium.RelativeBy;  
IWebElement emailAddressField = driver.FindElement(By.Id("email"));
IWebElement passwordField = driver.FindElement(WithTagName("input")
                                               .Below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
email_address_field = driver.find_element(:id, "email")
password_field = driver.find_element(relative: {tag_name: 'input', below: email_address_field})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let emailAddressField = driver.findElement(By.id('email'));
let passwordField = await driver.findElements(withTagName('input').below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val emailAddressField = driver.findElement(By.id("email"))
val passwordField = driver.findElement(withTagName("input").below(emailAddressField))
  {{< / code-panel >}}
{{< / code-tab >}}


### toLeftOf()

Retorna o WebElement, que aparece
à esquerda do elemento especificado

{{< code-tab >}}
  {{< code-panel language="java" >}}
//import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;
WebElement submitButton = driver.findElement(By.id("submit"));
WebElement cancelButton = driver.findElement(withTagName("button")
                                            .toLeftOf(submitButton));   
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#from selenium.webdriver.support.relative_locator import with_tag_name
submitButton = driver.find_element(By.ID, "submit")
cancelButton = driver.find_element(with_tag_name("button").
                                   to_left_of(submitButton))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//using static OpenQA.Selenium.RelativeBy;
IWebElement submitButton = driver.FindElement(By.Id("submit"));
IWebElement cancelButton = driver.FindElement(WithTagName("button")
                                              .LeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
submit_button = driver.find_element(:id, "submit")
cancel_button = driver.find_element(relative: {tag_name: 'button', left:submit_button})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let submitButton = driver.findElement(By.id("submit"));
let cancelButton = await driver.findElements(withTagName("button").toLeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val submitButton = driver.findElement(By.id("submit"))
val cancelButton = driver.findElement(withTagName("button").toLeftOf(submitButton))
  {{< / code-panel >}}
{{< / code-tab >}}


### toRightOf()

Retorna o WebElement, que aparece
à direita do elemento especificado

{{< code-tab >}}
  {{< code-panel language="java" >}}
//import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;
WebElement cancelButton = driver.findElement(By.id("cancel"));
WebElement submitButton = driver.findElement(withTagName("button")
                                            .toRightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#from selenium.webdriver.support.relative_locator import with_tag_name
cancelButton = driver.find_element(By.ID, "cancel")
submitButton = driver.find_element(with_tag_name("button").
                                   to_right_of(cancelButton))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//using static OpenQA.Selenium.RelativeBy;
IWebElement cancelButton = driver.FindElement(By.Id("cancel"));
IWebElement submitButton = driver.FindElement(WithTagName("button")
                                              .RightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cancel_button = driver.find_element(:id, "cancel")
submit_button = driver.find_element(relative: {tag_name: 'button', right:cancel_button})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let cancelButton = driver.findElement(By.id('cancel'));
let submitButton = await driver.findElements(withTagName('button').toRightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cancelButton = driver.findElement(By.id("cancel"))
val submitButton = driver.findElement(withTagName("button").toRightOf(cancelButton))
  {{< / code-panel >}}
{{< / code-tab >}}

### near()

Retorna o WebElement, que está
no máximo a `50px` de distância do elemento especificado.

{{< code-tab >}}
  {{< code-panel language="java" >}}
//import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;
WebElement emailAddressLabel = driver.findElement(By.id("lbl-email"));
WebElement emailAddressField = driver.findElement(withTagName("input")
                                                  .near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
#from selenium.webdriver.support.relative_locator import with_tag_name
emailAddressLabel = driver.find_element(By.ID, "lbl-email")    
emailAddressField = driver.find_element(with_tag_name("input").
                                       near(emailAddressLabel))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
//using static OpenQA.Selenium.RelativeBy;
IWebElement emailAddressLabel = driver.FindElement(By.Id("lbl-email"));
IWebElement emailAddressField = driver.FindElement(WithTagName("input")
                                                   .Near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
email_address_label = driver.find_element(:id, "lbl-email")
email_address_field = driver.find_element(relative: {tag_name: 'input', near: email_address_label})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let emailAddressLabel = driver.findElement(By.id("lbl-email"));
let emailAddressField = await driver.findElements(withTagName("input").near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val emailAddressLabel = driver.findElement(By.id("lbl-email"))
val emailAddressField = driver.findElement(withTagName("input").near(emailAddressLabel))
  {{< / code-panel >}}
{{< / code-tab >}}
