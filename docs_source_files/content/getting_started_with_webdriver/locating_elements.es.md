---
title: "Localizando elementos"
weight: 3
---


## Localizando un elemento

Una de las técnicas más fundamentales por aprender al usar WebDriver es cómo encontrar elementos en la página. WebDriver ofrece varios tipos de selectores integrados, entre ellos encontrar un elemento por su atributo ID:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));  
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
driver.find_element_by_id("cheese")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement element = driver.FindElement(By.Id("cheese"));  
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
driver.find_element(id: "cheese")  
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = await driver.findElement(By.id('cheese'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese: WebElement = driver.findElement(By.id("cheese"))
  {{< / code-panel >}}
{{< / code-tab >}}

Como se ve en el ejemplo, la localización de elementos en WebDriver se realiza en el objeto de instancia `WebDriver`. El método `findElement(By)` devuelve otro tipo de objeto fundamental, el `WebElement`.

* `WebDriver` representa al navegador.
* `WebElement` representa a un nodo DOM particular (un control, por ejemplo, un enlace o campo de entrada, etc.).

Una vez que tenga la referencia a un elemento web que se ha "encontrado", puede reducir el alcance de su búsqueda utilizando la misma llamada en esa instancia de objeto:

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement cheese = driver.findElement(By.id("cheese"));
WebElement cheddar = cheese.findElement(By.id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
cheese = driver.find_element_by_id("cheese")
cheddar = cheese.find_elements_by_id("cheddar")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement cheese = driver.FindElement(By.Id("cheese"));
IWebElement cheddar = cheese.FindElement(By.Id("cheddar"));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
cheese = driver.find_element(id: "cheese")
cheddar = cheese.find_elements(id: "cheddar")
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheese = await driver.findElement(By.id('cheese'));
const cheddar = await cheese.findElement(By.id('cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val cheese = driver.findElement(By.id("cheese"))
val cheddar = cheese.findElement(By.id("cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

Usted puede hacer esto porque los tipos _WebDriver_ y _WebElement_ implementan la interfaz [_SearchContext_](// seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/SearchContext.html>SearchContext). En WebDriver, esto se conoce como _interfaz basada en roles_. Las interfaces basadas en roles le permiten determinar si una implementación de controlador particular admite una característica determinada. Estas interfaces están claramente definidas y tratan de cumplir con tener un solo rol de responsabilidad. Puede leer más sobre el diseño de WebDriver y qué roles se admiten en qué controladores en [Alguna otra sección que debe nombrarse](#).
<!-- TODO: se debe crear una nueva sección para lo anterior.-->

En consecuencia, la interfaz _By_ utilizada anteriormente también admite una serie de estrategias de localización adicionales. Una búsqueda anidada podría no ser la estrategia de ubicación más efectiva, ya que requiere que se envíen dos comandos separados al navegador; primero buscando en el DOM un elemento con ID "_cheese_", luego una búsqueda de "cheddar" en un contexto restringido.

Para mejorar ligeramente el rendimiento, deberíamos tratar de usar un localizador más específico: WebDriver admite la búsqueda de elementos mediante localizadores CSS, lo que nos permite combinar los dos localizadores anteriores en una sola búsqueda:

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
mucho_cheese = driver.find_elements(css: "#cheese #cheddar")
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const cheddar = await driver.findElement(By.css('#cheese #cheddar'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
driver.findElement(By.cssSelector("#cheese #cheddar"))
  {{< / code-panel >}}
{{< / code-tab >}}

## Localizando múltiples elementos

Es posible que el documento con el que estamos trabajando tenga una lista ordenada de quesos que más nos gustan:

```html
<ol id=cheese>
 <li id=cheddar>…
 <li id=brie>…
 <li id=rochefort>…
 <li id=camembert>…
</ul>
```

Dado que más queso es indiscutiblemente mejor, y sería engorroso tener que recuperar cada uno de los elementos individualmente, una técnica superior para recuperar queso es utilizar la versión pluralizada `findElements(By)`. Este método devuelve una colección de elementos web. Si solo se encuentra un elemento, aún devolverá una colección (de un elemento). Si ningún elemento coincide con el localizador, se devolverá una lista vacía.

{{< code-tab >}}
  {{< code-panel language="java" >}}
List<WebElement> muchoCheese = driver.findElements(By.cssSelector("#cheese li"));
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
mucho_cheese = driver.find_elements_by_css_selector("#cheese li")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IReadOnlyList<IWebElement> muchoCheese = driver.FindElements(By.CssSelector(“#cheese li”));
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
mucho_cheese = driver.find_elements(css: "#cheese li")
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const muchoCheese = await driver.findElements(By.css('#cheese li'));
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val muchoCheese: List<WebElement>  = driver.findElements(By.cssSelector("#cheese li"))
  {{< / code-panel >}}
{{< / code-tab >}}


## Estrategias de localización de elementos

Hay ocho estrategias diferentes de ubicación de elementos integrados en WebDriver:

| Localizador | Descripción |
| -------- | ---------- |
| class name | Localiza elementos cuyo nombre de clase contiene el valor de búsqueda (no se permiten nombres de clase compuestos) |
| css selector | Localiza elementos que coinciden con un selector CSS |
| id | Localiza elementos cuyo atributo ID coincide con el valor de búsqueda |
| name | Localiza elementos cuyo atributo NAME coincide con el valor de búsqueda |
| link text | Localiza elementos de hipervínculo cuyo texto visible coincide con el valor de búsqueda |
| partial link text | Localiza elementos de vínculo o enlace cuyo texto visible coincide con el valor de búsqueda |
| tag name | Localiza elementos cuyo nombre de etiqueta coincide con el valor de búsqueda |
| xpath | Localiza elementos que coinciden con una expresión XPath |


## Consejos para usar selectores

En general, si las ID de HTML están disponibles, son únicas y predecibles de manera consistente, son el método preferido para ubicar un elemento en una página. Tienden a funcionar muy rápidamente y renuncian a mucho procesamiento que viene con recorridos DOM complicados.

Si las ID únicas no están disponibles, un selector CSS bien escrito es el método preferido para localizar un elemento. XPath funciona tan bien como los selectores CSS, pero la sintaxis es complicada y con frecuencia difícil de depurar. Aunque los selectores XPath son muy flexibles, generalmente no son probados por los proveedores de navegadores y tienden a ser bastante lentos.

Las estrategias de selección basadas en texto de enlace y texto parcial de enlace tienen inconvenientes en el sentido de que solo funcionan en elementos de enlace. Además, llaman a los selectores XPath internamente en WebDriver.

La etiqueta nombre (_name_) puede ser una forma peligrosa de localizar elementos. Con frecuencia hay varios elementos de la misma etiqueta presentes en la página. Esto es principalmente útil cuando se llama al método _findElements(By)_ que devuelve una colección de elementos.

La recomendación es mantener sus localizadores lo más compactos y legibles posible. Pedirle al WebDriver que atraviese la estructura DOM es una operación costosa, y cuanto más pueda reducir el alcance de su búsqueda, mejor.

