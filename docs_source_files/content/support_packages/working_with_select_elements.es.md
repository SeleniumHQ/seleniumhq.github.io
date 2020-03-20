---
title: "Trabajando con elementos select"
weight: 3
---


A la hora de seleccionar elementos puede ser necesario código repetitivo 
para poder ser automatizado.
Para reducir esto y hacer tus test mas limpios, existe un clase _Select_ en los
paquetes de soporte de Selenium.
Para usarla, necesitarás importarla de la siguiente forma:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.support.ui.Select;
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.select import Select
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium.Support.UI
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
include Selenium::WebDriver::Support
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// No disponemos del ejemplo de código en Javascript aun - Ayudanos a ello abriendo un PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.support.ui.Select
  {{< / code-panel >}}
{{< / code-tab >}}

Una vez importado, ya podrás crear un objeto _Select_ usando un WebElement que
referencie a un elemento `<select>`.

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement selectElement = driver.findElement(By.id("selectElementID"));
Select selectObject = new Select(selectElement);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
select_element = driver.find_element_by_id('selectElementID')
select_object = Select(select_element)
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
IWebElement selectElement = driver.FindElement(By.Id("selectElementID"));
var selectObject = new SelectElement(selectElement);
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
select_element = driver.find_element(id: 'selectElementID')
select_object = Select(select_element)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// No disponemos del ejemplo de código en Javascript aun - Ayudanos a ello abriendo un PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val selectElement = driver.findElement(By.id("selectElementID"))
val selectObject = new Select(selectElement)
  {{< / code-panel >}}
{{< / code-tab >}}

El objeto _Select_ te proporcionará una serie de comandos que te permitirán
interactuar con los elementos `<select>`.
Lo primero de todo, existen diferentes formas de seleccionar una opción de un
elemento `<select>`.

```html
<select>
 <option value=value1>Bread</option>
 <option value=value2 selected>Milk</option>
 <option value=value3>Cheese</option>
</select>
```

Existen tres formas de seleccionar la primera opción del ejemplo que se muestra
arriba.

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Selecciona una <option> basándose en el indice interno del elemento <select>
selectObject.selectByIndex(1);

// Selecciona una <option> basándose en su atributo value
selectObject.selectByValue("value1");

// Selecciona una <option> basándose en el texto que muestra
selectObject.selectByVisibleText("Bread");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Selecciona una <option> basándose en el indice interno del elemento <select>
select_object.select_by_index(1)

# Selecciona una <option> basándose en su atributo value
select_object.select_by_value('value1')

# Selecciona una <option> basándose en el texto que muestra
select_object.select_by_visible_text('Bread')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Selecciona una <option> basándose en el indice interno del elemento <select>
selectObject.SelectByIndex(1);

// Selecciona una <option> basándose en su atributo value
selectObject.SelectByValue("value1");

// Selecciona una <option> basándose en el texto que muestra
selectObject.SelectByText("Bread");
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Selecciona una <option> basándose en el indice interno del elemento <select>
select_object.select_by(:index, 1)

# Selecciona una <option> basándose en su atributo value
select_object.select_by(:value, 'value1')

# Selecciona una <option> basándose en el texto que muestra
select_object.select_by(:text, 'Bread')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// No disponemos del ejemplo de código en Javascript aun - Ayudanos a ello abriendo un PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Selecciona una <option> basándose en el indice interno del elemento <select>
selectObject.selectByIndex(1)

// Selecciona una <option> basándose en su atributo value
selectObject.selectByValue("value1")

// Selecciona una <option> basándose en el texto que muestra
selectObject.selectByVisibleText("Bread")
  {{< / code-panel >}}
{{< / code-tab >}}

Puedes revisar que opciones están seleccionadas usando:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Devuelve una Lista de <WebElements> con las opciones que han sido seleccionadas
List<WebElement> allSelectedOptions = selectObject.getAllSelectedOptions();

// Devuelve un WebElement que referencia a la primera opción seleccionada que se encontró en el DOM
WebElement firstSelectedOption = selectObject.getFirstSelectedOption();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Devuelve una Lista de [WebElements] con las opciones que han sido seleccionadas
all_selected_options = select_object.all_selected_options

# Devuelve un WebElement que referencia a la primera opción seleccionada que se encontró en el DOM
first_selected_option = select_object.first_selected_option
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// No disponemos del ejemplo de código en C# aun - Ayudanos a ello abriendo un PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Devuelve un Array de <WebElements> con las opciones que han sido seleccionadas
all_selected_options = select_object.selected_options

# Devuelve un WebElement que referencia a la primera opción seleccionada que se encontró en el DOM
first_selected_option = select_object.first_selected_option
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// No disponemos del ejemplo de código en Javascript aun - Ayudanos a ello abriendo un PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Devuelve una Lista de <WebElements> con las opciones que han sido seleccionadas
val allSelectedOptions = selectObject.allSelectedOptions

// Devuelve un WebElement que referencia a la primera opción seleccionada que se encontró en el DOM
val firstSelectedOption = selectObject.firstSelectedOption
  {{< / code-panel >}}
{{< / code-tab >}}


Tambien existe una forma de obtener que elementos `<option>` contiene un 
`<select>`: 

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Devuelve una lista de <WebElements> que contiene las opciones de un elemento <select>
List<WebElement> allAvailableOptions = selectObject.getOptions();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Devuelve una lista de [WebElements] que contiene las opciones de un elemento &lt;select&gt;
all_available_options = select_object.options
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Devuelve una IList de <IWebElements> que contiene las opciones de un elemento <select>
IList<IWebElement> allAvailableOptions = selectObject.Options;
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Devuelve un array de [WebElements] que contiene las opciones de un elemento &lt;select&gt;
all_available_options = select_object.options
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// No disponemos del ejemplo de código en Javascript aun - Ayudanos a ello abriendo un PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Devuelve una lista de <WebElements> que contiene las opciones de un elemento <select>
val allAvailableOptions = selectObject.options
  {{< / code-panel >}}
{{< / code-tab >}}

A la hora de deseleccionar elementos dispones de cuatro opciones:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Deseleccionar una <option> basándose en el indice interno de un elemento <select>
selectObject.deselectByIndex(1);

// Deseleccionar una <option> basándose en su atributo `value`
selectObject.deselectByValue("value1");

// Deseleccionar una <option> basándose en el texto que muestra
selectObject.deselectByVisibleText("Bread");

// Deseleccionar todos los elementos <option> que estan seleccionados
selectObject.deselectAll();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Deseleccionar una <option> basándose en el indice interno de un elemento <select>
select_object.deselect_by_index(1)

# Deseleccionar una <option> basándose en su atributo `value`
select_object.deselect_by_value('value1')

# Deseleccionar una <option> basándose en el texto que muestra
select_object.deselect_by_visible_text('Bread')

# Deseleccionar todos los elementos <option> que estan seleccionados
select_object.deselect_all()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Deseleccionar una <option> basándose en el indice interno de un elemento <select>
selectObject.DeselectByIndex(1);

// Deseleccionar una <option> basándose en su atributo `value`
selectObject.DeselectByValue("value1");

// Deseleccionar una <option> basándose en el texto que muestra
selectObject.DeselectByText("Bread");

// Deseleccionar todos los elementos <option> que estan seleccionados
selectObject.DeselectAll();
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Deseleccionar una <option> basándose en el indice interno de un elemento <select>
select_object.deselect_by(:index, 1)

# Deseleccionar una <option> basándose en su atributo `value`
select_object.deselect_by(:value, 'value1')

# Deseleccionar una <option> basándose en el texto que muestra
select_object.deselect_by(:text, 'Bread')

# Deseleccionar todos los elementos <option> que estan seleccionados
select_object.deselect_all
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// No disponemos del ejemplo de código en Javascript aun - Ayudanos a ello abriendo un PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Deseleccionar una <option> basándose en el indice interno de un elemento <select>
selectObject.deselectByIndex(1)

// Deseleccionar una <option> basándose en su atributo `value`
selectObject.deselectByValue("value1")

// Deseleccionar una <option> basándose en el texto que muestra
selectObject.deselectByVisibleText("Bread")

// Deseleccionar todos los elementos <option> que estan seleccionados
selectObject.deselectAll()
  {{< / code-panel >}}
{{< / code-tab >}}

Finalmente, existen algunos elementos `<select>` que te permiten seleccionar
mas de una opción.
Puedes comprobar si tu elemento `<select>` es uno de estos usando: 

{{< code-tab >}}
  {{< code-panel language="java" >}}
Boolean doesThisAllowMultipleSelections = selectObject.isMultiple();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
does_this_allow_multiple_selections = select_object.is_multiple
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
bool doesThisAllowMultipleSelections = selectObject.IsMultiple;
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
does_this_allow_multiple_selections = select_object.multiple?
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// No disponemos del ejemplo de código en Javascript aun - Ayudanos a ello abriendo un PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val doesThisAllowMultipleSelections = selectObject.isMultiple
  {{< / code-panel >}}
{{< / code-tab >}}
