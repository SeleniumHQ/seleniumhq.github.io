---
title: "Trabajando con elementos select"
linkTitle: "Trabajando con elementos select"
weight: 2
---


A la hora de seleccionar elementos puede ser necesario código repetitivo 
para poder ser automatizado.
Para reducir esto y hacer tus test mas limpios, existe un clase _Select_ en los
paquetes de soporte de Selenium.
Para usarla, necesitarás importarla de la siguiente forma:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.support.ui.Select;
  {{< /tab >}}
  {{< tab header="Python" >}}
from selenium.webdriver.support.select import Select
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium.Support.UI
  {{< /tab >}}
  {{< tab header="Ruby" >}}
include Selenium::WebDriver::Support
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.support.ui.Select
  {{< /tab >}}
{{< /tabpane >}}

Una vez importado, ya podrás crear un objeto _Select_ usando un WebElement que
referencie a un elemento `<select>`.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
WebElement selectElement = driver.findElement(By.id("selectElementID"));
Select selectObject = new Select(selectElement);
  {{< /tab >}}
  {{< tab header="Python" >}}
select_element = driver.find_element(By.ID,'selectElementID')
select_object = Select(select_element)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
IWebElement selectElement = driver.FindElement(By.Id("selectElementID"));
var selectObject = new SelectElement(selectElement);
  {{< /tab >}}
  {{< tab header="Ruby" >}}
select_element = driver.find_element(id: 'selectElementID')
select_object = Select(select_element)
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val selectElement = driver.findElement(By.id("selectElementID"))
val selectObject = new Select(selectElement)
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Selecciona una <option> basándose en el indice interno del elemento <select>
selectObject.selectByIndex(1);

// Selecciona una <option> basándose en su atributo value
selectObject.selectByValue("value1");

// Selecciona una <option> basándose en el texto que muestra
selectObject.selectByVisibleText("Bread");
  {{< /tab >}}
  {{< tab header="Python" >}}
# Selecciona una <option> basándose en el indice interno del elemento <select>
select_object.select_by_index(1)

# Selecciona una <option> basándose en su atributo value
select_object.select_by_value('value1')

# Selecciona una <option> basándose en el texto que muestra
select_object.select_by_visible_text('Bread')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Selecciona una <option> basándose en el indice interno del elemento <select>
selectObject.SelectByIndex(1);

// Selecciona una <option> basándose en su atributo value
selectObject.SelectByValue("value1");

// Selecciona una <option> basándose en el texto que muestra
selectObject.SelectByText("Bread");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Selecciona una <option> basándose en el indice interno del elemento <select>
select_object.select_by(:index, 1)

# Selecciona una <option> basándose en su atributo value
select_object.select_by(:value, 'value1')

# Selecciona una <option> basándose en el texto que muestra
select_object.select_by(:text, 'Bread')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Selecciona una <option> basándose en el indice interno del elemento <select>
selectObject.selectByIndex(1)

// Selecciona una <option> basándose en su atributo value
selectObject.selectByValue("value1")

// Selecciona una <option> basándose en el texto que muestra
selectObject.selectByVisibleText("Bread")
  {{< /tab >}}
{{< /tabpane >}}

Puedes revisar que opciones están seleccionadas usando:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Devuelve una Lista de <WebElements> con las opciones que han sido seleccionadas
List<WebElement> allSelectedOptions = selectObject.getAllSelectedOptions();

// Devuelve un WebElement que referencia a la primera opción seleccionada que se encontró en el DOM
WebElement firstSelectedOption = selectObject.getFirstSelectedOption();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Devuelve una Lista de [WebElements] con las opciones que han sido seleccionadas
all_selected_options = select_object.all_selected_options

# Devuelve un WebElement que referencia a la primera opción seleccionada que se encontró en el DOM
first_selected_option = select_object.first_selected_option
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Return a List<WebElement> of options that have been selected
var allSelectedOptions = selectObject.AllSelectedOptions;

// Return a WebElement referencing the first selection option found by walking down the DOM
var firstSelectedOption = selectObject.AllSelectedOptions.FirstOrDefault();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Devuelve un Array de <WebElements> con las opciones que han sido seleccionadas
all_selected_options = select_object.selected_options

# Devuelve un WebElement que referencia a la primera opción seleccionada que se encontró en el DOM
first_selected_option = select_object.first_selected_option
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Devuelve una Lista de <WebElements> con las opciones que han sido seleccionadas
val allSelectedOptions = selectObject.allSelectedOptions

// Devuelve un WebElement que referencia a la primera opción seleccionada que se encontró en el DOM
val firstSelectedOption = selectObject.firstSelectedOption
  {{< /tab >}}
{{< /tabpane >}}


Tambien existe una forma de obtener que elementos `<option>` contiene un 
`<select>`: 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Devuelve una lista de <WebElements> que contiene las opciones de un elemento <select>
List<WebElement> allAvailableOptions = selectObject.getOptions();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Devuelve una lista de [WebElements] que contiene las opciones de un elemento &lt;select&gt;
all_available_options = select_object.options
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Devuelve una IList de <IWebElements> que contiene las opciones de un elemento <select>
IList<IWebElement> allAvailableOptions = selectObject.Options;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Devuelve un array de [WebElements] que contiene las opciones de un elemento &lt;select&gt;
all_available_options = select_object.options
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Devuelve una lista de <WebElements> que contiene las opciones de un elemento <select>
val allAvailableOptions = selectObject.options
  {{< /tab >}}
{{< /tabpane >}}

A la hora de deseleccionar elementos dispones de cuatro opciones:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Deseleccionar una <option> basándose en el indice interno de un elemento <select>
selectObject.deselectByIndex(1);

// Deseleccionar una <option> basándose en su atributo `value`
selectObject.deselectByValue("value1");

// Deseleccionar una <option> basándose en el texto que muestra
selectObject.deselectByVisibleText("Bread");

// Deseleccionar todos los elementos <option> que estan seleccionados
selectObject.deselectAll();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Deseleccionar una <option> basándose en el indice interno de un elemento <select>
select_object.deselect_by_index(1)

# Deseleccionar una <option> basándose en su atributo `value`
select_object.deselect_by_value('value1')

# Deseleccionar una <option> basándose en el texto que muestra
select_object.deselect_by_visible_text('Bread')

# Deseleccionar todos los elementos <option> que estan seleccionados
select_object.deselect_all()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Deseleccionar una <option> basándose en el indice interno de un elemento <select>
selectObject.DeselectByIndex(1);

// Deseleccionar una <option> basándose en su atributo `value`
selectObject.DeselectByValue("value1");

// Deseleccionar una <option> basándose en el texto que muestra
selectObject.DeselectByText("Bread");

// Deseleccionar todos los elementos <option> que estan seleccionados
selectObject.DeselectAll();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Deseleccionar una <option> basándose en el indice interno de un elemento <select>
select_object.deselect_by(:index, 1)

# Deseleccionar una <option> basándose en su atributo `value`
select_object.deselect_by(:value, 'value1')

# Deseleccionar una <option> basándose en el texto que muestra
select_object.deselect_by(:text, 'Bread')

# Deseleccionar todos los elementos <option> que estan seleccionados
select_object.deselect_all
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Deseleccionar una <option> basándose en el indice interno de un elemento <select>
selectObject.deselectByIndex(1)

// Deseleccionar una <option> basándose en su atributo `value`
selectObject.deselectByValue("value1")

// Deseleccionar una <option> basándose en el texto que muestra
selectObject.deselectByVisibleText("Bread")

// Deseleccionar todos los elementos <option> que estan seleccionados
selectObject.deselectAll()
  {{< /tab >}}
{{< /tabpane >}}

Finalmente, existen algunos elementos `<select>` que te permiten seleccionar
mas de una opción.
Puedes comprobar si tu elemento `<select>` es uno de estos usando: 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
Boolean doesThisAllowMultipleSelections = selectObject.isMultiple();
  {{< /tab >}}
  {{< tab header="Python" >}}
does_this_allow_multiple_selections = select_object.is_multiple
  {{< /tab >}}
  {{< tab header="CSharp" >}}
bool doesThisAllowMultipleSelections = selectObject.IsMultiple;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
does_this_allow_multiple_selections = select_object.multiple?
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
// This feature is not implemented - Help us by sending a pr to implement this feature
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val doesThisAllowMultipleSelections = selectObject.isMultiple
  {{< /tab >}}
{{< /tabpane >}}
