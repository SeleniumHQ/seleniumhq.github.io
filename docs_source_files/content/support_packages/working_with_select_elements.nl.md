---
title: "Working with select elements"
weight: 3
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Dutch. Do you speak Dutch? Help us to translate
it by sending us pull requests!
{{% /notice %}}

Select elements can require quite a bit of boiler plate code to automate.
To reduce this and make your tests cleaner, there is a
`Select` class in the Selenium support package.
To use it, you will need the following import statement:

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.support.ui.Select;
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
from selenium.webdriver.support.select import Select
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
include Selenium::WebDriver::Support
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.support.ui.Select
  {{< / code-panel >}}
{{< / code-tab >}}

You are then able to create a Select object using a WebElement that
references a `<select>` element.

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
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
select_element = driver.find_element(id: 'selectElementID')
select_object = Select(select_element)
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val selectElement = driver.findElement(By.id("selectElementID"))
val selectObject = new Select(selectElement)
  {{< / code-panel >}}
{{< / code-tab >}}

The Select object will now give you a series of commands
that allow you to interact with a `<select>` element.
First of all, there are different ways of selecting an option
from the `<select>` element.

```html
<select>
 <option value=value1>Bread</option>
 <option value=value2 selected>Milk</option>
 <option value=value3>Cheese</option>
</select>
```

There are three ways to select the first option from the above element:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Select an <option> based upon the <select> element's internal index
selectObject.selectByIndex(1);

// Select an <option> based upon its value attribute
selectObject.selectByValue("value1");

// Select an <option> based upon its text
selectObject.selectByVisibleText("Bread");
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Select an <option> based upon the <select> element's internal index
select_object.select_by_index(1)

# Select an <option> based upon its value attribute
select_object.select_by_value('value1')

# Select an <option> based upon its text
select_object.select_by_visible_text('Bread')
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Select an <option> based upon the <select> element's internal index
select_object.select_by(:index, 1)

# Select an <option> based upon its value attribute
select_object.select_by(:value, 'value1')

# Select an <option> based upon its text
select_object.select_by(:text, 'Bread')
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Select an <option> based upon the <select> element's internal index
selectObject.selectByIndex(1)

// Select an <option> based upon its value attribute
selectObject.selectByValue("value1")

// Select an <option> based upon its text
selectObject.selectByVisibleText("Bread")
  {{< / code-panel >}}
{{< / code-tab >}}

You can then check which options are selected by using:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Return a List<WebElement> of options that have been selected
List<WebElement> allSelectedOptions = selectObject.getAllSelectedOptions();

// Return a WebElement referencing the first selection option found by walking down the DOM
WebElement firstSelectedOption = selectObject.getFirstSelectedOption();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Return a list[WebElement] of options that have been selected
all_selected_options = select_object.all_selected_options

# Return a WebElement referencing the first selection option found by walking down the DOM
first_selected_option = select_object.first_selected_option
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Return an Array[Element] of options that have been selected
all_selected_options = select_object.selected_options

# Return a WebElement referencing the first selection option found by walking down the DOM
first_selected_option = select_object.first_selected_option
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Return a List<WebElement> of options that have been selected
val allSelectedOptions = selectObject.allSelectedOptions

// Return a WebElement referencing the first selection option found by walking down the DOM
val firstSelectedOption = selectObject.firstSelectedOption
  {{< / code-panel >}}
{{< / code-tab >}}


Or you may just be interested in what `<option>` elements
the `<select>` element contains:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Return a List<WebElement> of options that the <select> element contains
List<WebElement> allAvailableOptions = selectObject.getOptions();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Return a list[WebElement] of options that the &lt;select&gt; element contains
all_available_options = select_object.options
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Return an Array[Element] of options that the &lt;select&gt; element contains
all_available_options = select_object.options
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Return a List<WebElement> of options that the <select> element contains
val allAvailableOptions = selectObject.options
  {{< / code-panel >}}
{{< / code-tab >}}

If you want to deselect any elements, you now have four options:

{{< code-tab >}}
  {{< code-panel language="java" >}}
// Deselect an <option> based upon the <select> element's internal index
selectObject.deselectByIndex(1);

// Deselect an <option> based upon its value attribute
selectObject.deselectByValue("value1");

// Deselect an <option> based upon its text
selectObject.deselectByVisibleText("Bread");

// Deselect all selected <option> elements
selectObject.deselectAll();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
# Deselect an <option> based upon the <select> element's internal index
select_object.deselect_by_index(1)

# Deselect an <option> based upon its value attribute
select_object.deselect_by_value('value1')

# Deselect an <option> based upon its text
select_object.deselect_by_visible_text('Bread')

# Deselect all selected <option> elements
select_object.deselect_all()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# Deselect an <option> based upon the <select> element's internal index
select_object.deselect_by(:index, 1)

# Deselect an <option> based upon its value attribute
select_object.deselect_by(:value, 'value1')

# Deselect an <option> based upon its text
select_object.deselect_by(:text, 'Bread')

# Deselect all selected <option> elements
select_object.deselect_all
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
// Deselect an <option> based upon the <select> element's internal index
selectObject.deselectByIndex(1)

// Deselect an <option> based upon its value attribute
selectObject.deselectByValue("value1")

// Deselect an <option> based upon its text
selectObject.deselectByVisibleText("Bread")

// Deselect all selected <option> elements
selectObject.deselectAll()
  {{< / code-panel >}}
{{< / code-tab >}}

Finally, some `<select>` elements allow you to select more than one option.
You can find out if your `<select>` element is one of these by using:

{{< code-tab >}}
  {{< code-panel language="java" >}}
Boolean doesThisAllowMultipleSelections = selectObject.isMultiple();
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
does_this_allow_multiple_selections = select_object.is_multiple
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// We don't have a C# code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
does_this_allow_multiple_selections = select_object.multiple?
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val doesThisAllowMultipleSelections = selectObject.isMultiple
  {{< / code-panel >}}
{{< / code-tab >}}