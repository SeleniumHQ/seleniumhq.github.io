---
title: "Trabalhando com elementos select"
linkTitle: "Trabalhando com elementos select"
weight: 10
needsTranslation: true
aliases: [
"/documentation/pt-br/support_packages/working_with_select_elements/",
"/pt-br/documentation/support_packages/working_with_select_elements/",
"/pt-br/documentation/webdriver/elements/select_elements/"
]
description: >
  Select lists have special behaviors compared to other elements.
---

Os elementos *select* podem exigir um pouco de código padrão para serem automatizados.
Para reduzir isso e tornar seus testes mais limpos, existe uma
Classe `Select` no pacote de suporte do Selenium.
Para usá-lo, você precisará da seguinte instrução de importação:

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
  {{< badge-version version="4.5.0" >}}
const { Select } = require("selenium-webdriver")
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
import org.openqa.selenium.support.ui.Select
  {{< /tab >}}
{{< /tabpane >}}

Você pode então criar um objeto Select usando um WebElement que
faz referência a um elemento `<select>`.

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
  {{< badge-version version="4.5.0" >}}
const selectElement = await driver.findElement(By.id('selectElementID'))
const selectObject = new Select(selectElement)
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val selectElement = driver.findElement(By.id("selectElementID"))
val selectObject = new Select(selectElement)
  {{< /tab >}}
{{< /tabpane >}}

O objeto Select agora lhe dará uma série de comandos
que permitem que você interaja com um elemento `<select>`.
Em primeiro lugar, existem diferentes maneiras de selecionar uma opção
do elemento `<select>`.

```html
<select>
 <option value=value1>Bread</option>
 <option value=value2 selected>Milk</option>
 <option value=value3>Cheese</option>
</select>
```

Existem três maneiras de selecionar a primeira opção do elemento acima:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Select an <option> based upon the <select> element's internal index
selectObject.selectByIndex(1);

// Select an <option> based upon its value attribute
selectObject.selectByValue("value1");

// Select an <option> based upon its text
selectObject.selectByVisibleText("Bread");
  {{< /tab >}}
  {{< tab header="Python" >}}
# Select an <option> based upon the <select> element's internal index
select_object.select_by_index(1)

# Select an <option> based upon its value attribute
select_object.select_by_value('value1')

# Select an <option> based upon its text
select_object.select_by_visible_text('Bread')
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Select an <option> based upon the <select> element's internal index
selectObject.SelectByIndex(1);

// Select an <option> based upon its value attribute
selectObject.SelectByValue("value1");

// Select an <option> based upon its text
 selectObject.SelectByText("Bread");
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Select an <option> based upon the <select> element's internal index
select_object.select_by(:index, 1)

# Select an <option> based upon its value attribute
select_object.select_by(:value, 'value1')

# Select an <option> based upon its text
select_object.select_by(:text, 'Bread')
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< badge-version version="4.5.0" >}}
// Select an <option> based upon the <select> element's internal index
selectObject.selectByIndex(1)

// Select an <option> based upon its value attribute
selectObject.selectByValue('value1')

// Select an <option> based upon its text
selectObject.selectByVisibleText('Bread')
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Select an <option> based upon the <select> element's internal index
selectObject.selectByIndex(1)

// Select an <option> based upon its value attribute
selectObject.selectByValue("value1")

// Select an <option> based upon its text
selectObject.selectByVisibleText("Bread")
  {{< /tab >}}
{{< /tabpane >}}

You can then check which options are selected by using:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Return a List<WebElement> of options that have been selected
List<WebElement> allSelectedOptions = selectObject.getAllSelectedOptions();

// Return a WebElement referencing the first selection option found by walking down the DOM
WebElement firstSelectedOption = selectObject.getFirstSelectedOption();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Return a list[WebElement] of options that have been selected
all_selected_options = select_object.all_selected_options

# Return a WebElement referencing the first selection option found by walking down the DOM
first_selected_option = select_object.first_selected_option
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Return a List<WebElement> of options that have been selected
var allSelectedOptions = selectObject.AllSelectedOptions;

// Return a WebElement referencing the first selection option found by walking down the DOM
var firstSelectedOption = selectObject.AllSelectedOptions.FirstOrDefault();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Return an Array[Element] of options that have been selected
all_selected_options = select_object.selected_options

# Return a WebElement referencing the first selection option found by walking down the DOM
first_selected_option = select_object.first_selected_option
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< badge-version version="4.5.0" >}}
// Return a Array<WebElement> of options that have been selected
const allSelectedOptions = selectObject.getAllSelectedOptions()

// Return a WebElement referencing the first selection option found by walking down the DOM
const firstSelectedOption = selectObject.getFirstSelectedOption()
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Return a List<WebElement> of options that have been selected
val allSelectedOptions = selectObject.allSelectedOptions

// Return a WebElement referencing the first selection option found by walking down the DOM
val firstSelectedOption = selectObject.firstSelectedOption
  {{< /tab >}}
{{< /tabpane >}}


Ou você pode apenas estar interessado em quais elementos `<option>`
o elemento `<select>` contém:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Return a List<WebElement> of options that the <select> element contains
List<WebElement> allAvailableOptions = selectObject.getOptions();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Return a list[WebElement] of options that the <select> element contains
all_available_options = select_object.options
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Return a IList<IWebElement> of options that the <select> element contains
IList<IWebElement> allAvailableOptions = selectObject.Options;
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Return an Array[Element] of options that the <select> element contains
all_available_options = select_object.options
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< badge-version version="4.5.0" >}}
// Return a Array<WebElement> of options that the <select> element contains
const allAvailableOptions = await selectObject.getOptions();
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Return a List<WebElement> of options that the <select> element contains
val allAvailableOptions = selectObject.options
  {{< /tab >}}
{{< /tabpane >}}

Se você deseja desmarcar qualquer elemento, agora você tem quatro opções:

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
// Deselect an <option> based upon the <select> element's internal index
selectObject.deselectByIndex(1);

// Deselect an <option> based upon its value attribute
selectObject.deselectByValue("value1");

// Deselect an <option> based upon its text
selectObject.deselectByVisibleText("Bread");

// Deselect all selected <option> elements
selectObject.deselectAll();
  {{< /tab >}}
  {{< tab header="Python" >}}
# Deselect an <option> based upon the <select> element's internal index
select_object.deselect_by_index(1)

# Deselect an <option> based upon its value attribute
select_object.deselect_by_value('value1')

# Deselect an <option> based upon its text
select_object.deselect_by_visible_text('Bread')

# Deselect all selected <option> elements
select_object.deselect_all()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
// Deselect an <option> based upon the <select> element's internal index
selectObject.DeselectByIndex(1);

// Deselect an <option> based upon its value attribute
selectObject.DeselectByValue("value1");

// Deselect an <option> based upon its text
selectObject.DeselectByText("Bread");

// Deselect all selected <option> elements
selectObject.DeselectAll();
  {{< /tab >}}
  {{< tab header="Ruby" >}}
# Deselect an <option> based upon the <select> element's internal index
select_object.deselect_by(:index, 1)

# Deselect an <option> based upon its value attribute
select_object.deselect_by(:value, 'value1')

# Deselect an <option> based upon its text
select_object.deselect_by(:text, 'Bread')

# Deselect all selected <option> elements
select_object.deselect_all
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
  {{< badge-version version="4.5.0" >}}
// Deselect an <option> based upon the <select> element's internal index
selectObject.deselectByIndex(1)

// Deselect an <option> based upon its value attribute
selectObject.deselectByValue('value1')

// Deselect an <option> based upon its text
selectObject.deselectByVisibleText('Bread')

// Deselect all selected <option> elements
selectObject.deselectAll()
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
// Deselect an <option> based upon the <select> element's internal index
selectObject.deselectByIndex(1)

// Deselect an <option> based upon its value attribute
selectObject.deselectByValue("value1")

// Deselect an <option> based upon its text
selectObject.deselectByVisibleText("Bread")

// Deselect all selected <option> elements
selectObject.deselectAll()
  {{< /tab >}}
{{< /tabpane >}}

Finalmente, alguns elementos `<select>` permitem que você selecione mais de uma opção.
Você pode descobrir se o seu elemento `<select>` é um deles usando:

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
  {{< badge-version version="4.5.0" >}}
const doesThisAllowMultipleSelections = await selectObject.isMultiple()
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
val doesThisAllowMultipleSelections = selectObject.isMultiple
  {{< /tab >}}
{{< /tabpane >}}
