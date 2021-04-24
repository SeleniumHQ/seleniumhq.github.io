---
title: "Localizando elementos"
weight: 3
---

### Localizando un elemento

Una de las técnicas más fundamentales para aprender al usar WebDriver
es cómo encontrar elementos en la página. WebDriver ofrece varios
tipos de selectores integrados, entre ellos encontrar un elemento por
su atributo ID:

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

Como se ve en el ejemplo, localizar elementos en WebDriver
se realiza en la instancia del objeto `WebDriver`. El método
`findElement(By)` devuelve otro tipo de objeto fundamental,
el `WebElement`.

* `WebDriver` representa el navegador
* `WebElement` representa un
nodo particular del DOM (un control, por ejemplo un enlace o campo de
entrada, etc.)

Una vez que tengas una referencia a un elemento web que se ha
"encontrado", puedes reducir el alcance de tu búsqueda utilizando la
misma llamada en la instancia de ese objeto:

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

Puedes hacer esto porque los tipos _WebDriver_ y _WebElement_
implementan la interfaz  [_SearchContext_](//seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/SearchContext.html).
En WebDriver, esto se conoce como _interfaz basada en roles_.
Las interfaces basadas en roles te permiten determinar si
la implementación del controlador admite una característica dada.
Estas interfaces están claramente definidas y tratan de cumplir con
tener un solo rol de responsabilidad. Puede leer más sobre el diseño
de WebDriver y qué roles son compatibles con qué controladores en [Otra sección](#).
<!-- TODO: A new section needs to be created for the above.-->

En consecuencia, la interfaz _By_ utilizada anteriormente también permite una serie de
estrategias adicionales de localización. Una búsqueda anidada podría no ser la
estrategia mas efectiva para localizar _cheese_ ya que requiere dos
comandos que se emitirán al navegador; primero buscando en el DOM un
elemento con ID "cheese", luego una búsqueda de "cheddar" en un contexto reducido.

Para mejorar ligeramente el rendimiento, deberíamos intentar utilizar un
localizador más específico: WebDriver permite buscar elementos por localizadores CSS,
lo que nos permite combinar los dos localizadores anteriores en una sola búsqueda:

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

### Localizando múltiples elementos

Es posible que el documento con el que estamos trabajando contenga
una lista ordenada del queso que más nos gusta:

```html
<ol id=cheese>
 <li id=cheddar>…
 <li id=brie>…
 <li id=rochefort>…
 <li id=camembert>…
</ol>
```
Dado que más queso es indiscutiblemente mejor, y sería engorroso
tener que recuperar cada uno de los elementos individualmente,
una técnica superior para recuperar _cheese_ es hacer uso de la
versión pluralizada `findElements(By)`. Este método devuelve una
colección de elementos web. Si solo se encuentra un elemento, aún devolverá una
colección (de un elemento). Si ningún elemento coincide con el localizador,
se devolverá la lista vacía.
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

### Estrategias de localización de elementos

Hay ocho estrategias diferentes de ubicación de elementos integradas en WebDriver:

| Localizador | Descripción |
| -------- | ---------- |
| class name | Localiza elementos en el que el nombre de su clase contiene el valor de la búsqueda (no se permiten nombres de clase compuestos) |
| css selector | Localiza elementos que coinciden con un selector CSS |
| id | Localiza elementos cuyo atributo ID coincide con el valor de la búsqueda |
| name | Localiza elementos cuyo atributo NAME coincide con el valor de la búsqueda |
| link text | Localiza elementos de anclaje cuyo texto visible coincide con el valor de búsqueda |
| partial link text | Localiza elementos de anclaje cuyo texto visible coincide con el valor de búsqueda. Si varios elementos coinciden, solo se seleccionará el primero. |
| tag name | Localiza elementos cuyo nombre de etiqueta (tagName) coincide con el valor de búsqueda |
| xpath | Localiza elementos que coinciden con una expresión XPath |

### Consejos sobre el uso de selectores

En general, si los ID del HTML están disponibles, son únicos y
consistentemente predecibles, son el método preferido para ubicar un
elemento en una página. Tienden a trabajar muy rápido y renuncian al
mucho procesamiento que viene con recorridos DOM complicados.

Si las ID únicas no están disponibles, un selector CSS bien escrito
es el método preferido para localizar un elemento. XPath funciona tan
bien como los selectores CSS, pero la sintaxis es complicada y con
frecuencia difícil de depurar. Aunque los selectores XPath son muy
flexibles, generalmente su desempeño no es probado por lo proveedores
de navegadores y tienden a ser bastante lentos.

Las estrategias de selección basadas en enlaces de texto y enlaces de
texto parciales tienen el inconveniente en que solo funcionan en
elementos de enlace. Además, internamente en WebDriver llaman a los
selectores XPath.

El nombre de la etiqueta puede ser una forma peligrosa de localizar
elementos. Existen frecuentemente múltiples elementos con la misma
etiqueta presentes en la página. Esto es mayormente útil cuando se
llama al método _findElements(By)_ que devuelve una colección de
elementos.

La recomendación es mantener tus localizadores tan compactos y
legibles como sea posible. Pedirle a WebDriver que atraviese la
estructura del DOM es una operación costosa, y cuanto más se pueda
reducir el alcance de tu búsqueda, mejor.

## Relative Locators

**Selenium 4** brings Relative Locators which are previously
called as _Friendly Locators_. This functionality was
added to help you locate elements that are nearby other elements.
The Available Relative Locators are:

* *above*
* *below*
* *toLeftOf*
* *toRightOf*
* *near*

_findElement_ method now accepts a new method `withTagName()`
which returns a RelativeLocator.

### How does it work

Selenium uses the JavaScript function
[getBoundingClientRect()](https://developer.mozilla.org/en-US/docs/Web/API/Element/getBoundingClientRect)
to find the relative elements. This function returns
properties of an element such as
right, left, bottom, and top.

Let us consider the below example for understanding the relative locators.

![Relative Locators](/images/relative_locators.png?width=400px)

### above()

Returns the WebElement, which appears
above to the specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement passwordField= driver.findElement(By.id("password"));
WebElement emailAddressField = driver.findElement(withTagName("input")
                                                  .above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

passwordField = driver.find_element(By.ID, "password")
emailAddressField = driver.find_element(with_tag_name("input").above(passwordField))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement passwordField = driver.FindElement(By.Id("password"));
IWebElement emailAddressField = driver.FindElement(WithTagName("input")
                                                   .Above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
password_field= driver.find_element(:id, "password")
email_address_field = driver.find_element(relative: {tag_name: 'input', above:password_field})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let passwordField = driver.findElement(By.id('password'));
let emailAddressField = await driver.findElement(withTagName('input').above(passwordField));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val passwordField = driver.findElement(By.id("password"))
val emailAddressField = driver.findElement(withTagName("input").above(passwordField))
  {{< / code-panel >}}
{{< / code-tab >}}


### below()

Returns the WebElement, which appears
below to the specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement emailAddressField= driver.findElement(By.id("email"));
WebElement passwordField = driver.findElement(withTagName("input")
	                                          .below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

emailAddressField = driver.find_element(By.ID, "email")
passwordField = driver.find_element(with_tag_name("input").below(emailAddressField))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;  

IWebElement emailAddressField = driver.FindElement(By.Id("email"));
IWebElement passwordField = driver.FindElement(WithTagName("input")
                                               .Below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
email_address_field= driver.find_element(:id, "email")
password_field = driver.find_element(relative: {tag_name: 'input', below: email_address_field})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let emailAddressField = driver.findElement(By.id('email'));
let passwordField = await driver.findElement(withTagName('input').below(emailAddressField));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val emailAddressField = driver.findElement(By.id("email"))
val passwordField = driver.findElement(withTagName("input").below(emailAddressField))
  {{< / code-panel >}}
{{< / code-tab >}}


### toLeftOf()

Returns the WebElement, which appears
to left of specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement submitButton= driver.findElement(By.id("submit"));
WebElement cancelButton= driver.findElement(withTagName("button")
                                            .toLeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

submitButton = driver.find_element(By.ID, "submit")
cancelButton = driver.find_element(with_tag_name("button").
                                   to_left_of(submitButton))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement submitButton = driver.FindElement(By.Id("submit"));
IWebElement cancelButton = driver.FindElement(WithTagName("button")
                                              .LeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
submit_button= driver.find_element(:id, "submit")
cancel_button = driver.find_element(relative: {tag_name: 'button', left:submit_button})
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
let submitButton = driver.findElement(By.id("submit"));
let cancelButton = await driver.findElement(withTagName("button").toLeftOf(submitButton));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val submitButton= driver.findElement(By.id("submit"))
val cancelButton= driver.findElement(withTagName("button").toLeftOf(submitButton))
  {{< / code-panel >}}
{{< / code-tab >}}


### toRightOf()

Returns the WebElement, which appears
to right of the specified element

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement cancelButton= driver.findElement(By.id("cancel"));
WebElement submitButton= driver.findElement(withTagName("button")
                                            .toRightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

cancelButton = driver.find_element(By.ID, "cancel")
submitButton = driver.find_element(with_tag_name("button").
                                   to_right_of(cancelButton))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

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
let submitButton = await driver.findElement(withTagName('button').toRightOf(cancelButton));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cancelButton= driver.findElement(By.id("cancel"))
val submitButton= driver.findElement(withTagName("button").toRightOf(cancelButton))
  {{< / code-panel >}}
{{< / code-tab >}}

### near()

Returns the WebElement, which is
at most `50px` away from the specified element.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

WebElement emailAddressLabel= driver.findElement(By.id("lbl-email"));
WebElement emailAddressField = driver.findElement(withTagName("input")
                                                  .near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.relative_locator import with_tag_name

emailAddressLabel = driver.find_element(By.ID, "lbl-email")
emailAddressField = driver.find_element(with_tag_name("input").
                                       near(emailAddressLabel))
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using static OpenQA.Selenium.RelativeBy;

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
let emailAddressField = await driver.findElement(withTagName("input").near(emailAddressLabel));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val emailAddressLabel = driver.findElement(By.id("lbl-email"))
val emailAddressField = driver.findElement(withTagName("input").near(emailAddressLabel))
  {{< / code-panel >}}
{{< / code-tab >}}
