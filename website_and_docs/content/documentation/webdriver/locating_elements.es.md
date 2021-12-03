---
title: "Localizando elementos"
linkTitle: "Localizando elementos"
weight: 3
aliases: ["/documentation/es/webdriver/locating_elements/"]
---

### Localizando un elemento

Una de las técnicas más fundamentales para aprender al usar WebDriver
es cómo encontrar elementos en la página. WebDriver ofrece varios
tipos de selectores integrados, entre ellos encontrar un elemento por
su atributo ID:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
  {{< /tab >}}
  {{< tab header="Python" >}}
driver.find_element(By.ID, "cheese")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebElement element = driver.FindElement(By.Id("cheese"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
cheese = driver.find_element(id: 'cheese')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheese = driver.findElement(By.id('cheese'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
WebElement cheddar = cheese.findElement(By.id("cheddar"));
  {{< /tab >}}
  {{< tab header="Python" >}}
cheese = driver.find_element(By.ID, "cheese")
cheddar = cheese.find_elements_by_id("cheddar")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebElement cheese = driver.FindElement(By.Id("cheese"));
IWebElement cheddar = cheese.FindElement(By.Id("cheddar"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
cheese = driver.find_element(id: 'cheese')
cheddar = cheese.find_element(id: 'cheddar')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheese = driver.findElement(By.id('cheese'));
const cheddar = cheese.findElement(By.id('cheddar'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"));
  {{< /tab >}}
  {{< tab header="Python" >}}
cheddar = driver.find_element_by_css_selector("#cheese #cheddar")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
driver.FindElement(By.CssSelector("#cheese #cheddar"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
driver.find_element(css: '#cheese #cheddar')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const cheddar = driver.findElement(By.css('#cheese #cheddar'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< /tab >}}
{{< /tabpane >}}

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
{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
List<WebElement> muchoCheese = driver.findElements(By.cssSelector("#cheese li"));
  {{< /tab >}}
  {{< tab header="Python" >}}
mucho_cheese = driver.find_elements_by_css_selector("#cheese li")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IReadOnlyList<IWebElement> muchoCheese = driver.FindElements(By.CssSelector("#cheese li"));
  {{< /tab >}}
  {{< tab header="Ruby" >}}
mucho_cheese = driver.find_elements(css: '#cheese li')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
const muchoCheese = driver.findElements(By.css('#cheese li'));
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val muchoCheese: List<WebElement>  = driver.findElements(By.cssSelector("#cheese li"))
  {{< /tab >}}
{{< /tabpane >}}

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

* `above`
* `below`
* `toLeftOf`
* `toRightOf`
* `near`

The `findElement` method accepts a new method `with(By)` which returns
a `RelativeLocator`. Users can pick a locator of their choice like
`By.id`, `By.cssSelector`, etc.

### How does it work

Selenium uses the JavaScript function
[getBoundingClientRect()](https://developer.mozilla.org/en-US/docs/Web/API/Element/getBoundingClientRect)
to find the relative elements. This function returns
properties of an element such as
right, left, bottom, and top.

Let us consider the below example for understanding the relative locators.

{{< figure src="/images/documentation/webdriver/relative_locators.png" class="img-responsive text-center" alt="Relative Locators">}}

### above()

Returns the WebElement, which appears
above to the specified element

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement passwordField = driver.findElement(By.id("password"));
WebElement emailAddressField = driver.findElement(with(By.tagName("input"))
.above(passwordField));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

passwordField = driver.find_element(By.ID, "password")
emailAddressField = driver.find_element(locate_with(By.TAG_NAME, "input").above(passwordField))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement passwordField = driver.FindElement(By.Id("password"));
IWebElement emailAddressField = driver.FindElement(RelativeBy.WithLocator(By.TagName("input")).Above(passwordField));
{{< /tab >}}
{{< tab header="Ruby" >}}
password_field= driver.find_element(:id, "password")
email_address_field = driver.find_element(relative: {tag_name: 'input', above:password_field})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let passwordField = driver.findElement(By.id('password'));
let emailAddressField = await driver.findElement(locateWith(By.tagName('input')).above(passwordField));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val passwordField = driver.findElement(By.id("password"))
val emailAddressField = driver.findElement(with(By.tagName("input")).above(passwordField))
{{< /tab >}}
{{< /tabpane >}}


### below()

Returns the WebElement, which appears
below to the specified element

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement emailAddressField = driver.findElement(By.id("email"));
WebElement passwordField = driver.findElement(with(By.tagName("input"))
.below(emailAddressField));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

emailAddressField = driver.find_element(By.ID, "email")
passwordField = driver.find_element(locate_with(By.TAG_NAME, "input").below(emailAddressField))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement emailAddressField = driver.FindElement(By.Id("email"));
IWebElement passwordField = driver.FindElement(RelativeBy.WithLocator(By.TagName("input")).Below(emailAddressField));
{{< /tab >}}
{{< tab header="Ruby" >}}
email_address_field = driver.find_element(:id, "email")
password_field = driver.find_element(relative: {tag_name: 'input', below: email_address_field})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let emailAddressField = driver.findElement(By.id('email'));
let passwordField = await driver.findElement(locateWith(By.tagName('input')).below(emailAddressField));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val emailAddressField = driver.findElement(By.id("email"))
val passwordField = driver.findElement(with(By.tagName("input")).below(emailAddressField))
{{< /tab >}}
{{< /tabpane >}}



### toLeftOf()

Returns the WebElement, which appears
to left of specified element

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement submitButton = driver.findElement(By.id("submit"));
WebElement cancelButton = driver.findElement(with(By.tagName("button"))
.toLeftOf(submitButton));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

submitButton = driver.find_element(By.ID, "submit")
cancelButton = driver.find_element(locate_with(By.TAG_NAME, "button").
to_left_of(submitButton))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement submitButton = driver.FindElement(By.Id("submit"));
IWebElement cancelButton = driver.FindElement(RelativeBy.WithLocator(By.TagName("button")).LeftOf(submitButton));
{{< /tab >}}
{{< tab header="Ruby" >}}
submit_button= driver.find_element(:id, "submit")
cancel_button = driver.find_element(relative: {tag_name: 'button', left:submit_button})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let submitButton = driver.findElement(By.id('submit'));
let cancelButton = await driver.findElement(locateWith(By.tagName('button')).toLeftOf(submitButton));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val submitButton = driver.findElement(By.id("submit"))
val cancelButton = driver.findElement(with(By.tagName("button")).toLeftOf(submitButton))
{{< /tab >}}
{{< /tabpane >}}


### toRightOf()

Returns the WebElement, which appears
to right of the specified element

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement cancelButton = driver.findElement(By.id("cancel"));
WebElement submitButton = driver.findElement(with(By.tagName("button")).toRightOf(cancelButton));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

cancelButton = driver.find_element(By.ID, "cancel")
submitButton = driver.find_element(locate_with(By.TAG_NAME, "button").
to_right_of(cancelButton))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement cancelButton = driver.FindElement(By.Id("cancel"));
IWebElement submitButton = driver.FindElement(RelativeBy.WithLocator(By.TagName("button")).RightOf(cancelButton));
{{< /tab >}}
{{< tab header="Ruby" >}}
cancel_button = driver.find_element(:id, "cancel")
submit_button = driver.find_element(relative: {tag_name: 'button', right:cancel_button})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let cancelButton = driver.findElement(By.id('cancel'));
let submitButton = await driver.findElement(locateWith(By.tagName('button')).toRightOf(cancelButton));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val cancelButton = driver.findElement(By.id("cancel"))
val submitButton = driver.findElement(with(By.tagName("button")).toRightOf(cancelButton))
{{< /tab >}}
{{< /tabpane >}}

### near()

Returns the WebElement, which is
at most `50px` away from the specified element.

{{< tabpane langEqualsHeader=true >}}
{{< tab header="Java" >}}
import static org.openqa.selenium.support.locators.RelativeLocator.with;

WebElement emailAddressLabel = driver.findElement(By.id("lbl-email"));
WebElement emailAddressField = driver.findElement(with(By.tagName("input")).near(emailAddressLabel));
{{< /tab >}}
{{< tab header="Python" >}}
from selenium.webdriver.common.by import By
from selenium.webdriver.support.relative_locator import locate_with

emailAddressLabel = driver.find_element(By.ID, "lbl-email")
emailAddressField = driver.find_element(locate_with(By.TAG_NAME, "input").
near(emailAddressLabel))
{{< /tab >}}
{{< tab header="CSharp" >}}
using static OpenQA.Selenium.RelativeBy;

IWebElement emailAddressLabel = driver.FindElement(By.Id("lbl-email"));
IWebElement emailAddressField = driver.FindElement(RelativeBy.WithLocator(By.TagName("input")).Near(emailAddressLabel));
{{< /tab >}}
{{< tab header="Ruby" >}}
email_address_label = driver.find_element(:id, "lbl-email")
email_address_field = driver.find_element(relative: {tag_name: 'input', near: email_address_label})
{{< /tab >}}
{{< tab header="JavaScript" >}}
let emailAddressLabel = driver.findElement(By.id("lbl-email"));
let emailAddressField = await driver.findElement(locateWith(By.tagName("input")).near(emailAddressLabel));
{{< /tab >}}
{{< tab header="Kotlin" >}}
val emailAddressLabel = driver.findElement(By.id("lbl-email"))
val emailAddressField = driver.findElement(with(By.tagName("input")).near(emailAddressLabel))
{{< /tab >}}
{{< /tabpane >}}
