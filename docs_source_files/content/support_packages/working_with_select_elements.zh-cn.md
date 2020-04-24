---
title: "同选择元素一起工作"
weight: 3
---
 
选择元素可能需要大量样板代码才能自动化.
为了减少这种情况并使您的测试更干净, 在Selenium的support包中有一个
`Select` 类.
要使用它，您将需要以下导入语句:

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
// We don't have a JavaScript code sample yet -  Help us out and raise a PR  
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.support.ui.Select
  {{< / code-panel >}}
{{< / code-tab >}}

然后，您能够参考 `<select>` 元素，基于WebElement创建一个Select对象。 

{{< code-tab >}}
  {{< code-panel language="java" >}}
WebElement selectElement = driver.findElement(By.id("selectElementID"));
Select selectObject = new Select(selectElement);
  {{< / code-panel >}}
  {{< code-panel language="python" >}}
select_element = driver.find_element(By.ID,'selectElementID')
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
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val selectElement = driver.findElement(By.id("selectElementID"))
val selectObject = new Select(selectElement)
  {{< / code-panel >}}
{{< / code-tab >}}

Select对象现在将为您提供一系列命令，使您可以与 `<select>` 元素进行交互.
首先，有多种方法可以从 `<select>` 元素中选择一个选项.

```html
<select>
 <option value=value1>Bread</option>
 <option value=value2 selected>Milk</option>
 <option value=value3>Cheese</option>
</select>
```

有三种方法可以从上述元素中选择第一个选项:

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
// Select an <option> based upon the <select> element's internal index
selectObject.SelectByIndex(1);

// Select an <option> based upon its value attribute
selectObject.SelectByValue("value1");

// Select an <option> based upon its text
 selectObject.SelectByText("Bread");
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

然后，您可以检视所有被选择的选项:

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


或者您可能只对 `<select>` 元素包含哪些 `<option>` 元素感兴趣:

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
// Return a IList<IWebElement> of options that the <select> element contains
IList<IWebElement> allAvailableOptions = selectObject.Options;
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

如果要取消选择任何元素，现在有四个选项:

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
// Deselect an <option> based upon the <select> element's internal index
selectObject.DeselectByIndex(1);

// Deselect an <option> based upon its value attribute
selectObject.DeselectByValue("value1");

// Deselect an <option> based upon its text
selectObject.DeselectByText("Bread");

// Deselect all selected <option> elements
selectObject.DeselectAll();
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

最后，一些 `<select>` 元素允许您选择多个选项.
您可以通过使用以下命令确定您的 `<select>` 元素是否允许多选:

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
// We don't have a JavaScript code sample yet -  Help us out and raise a PR
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
val doesThisAllowMultipleSelections = selectObject.isMultiple
  {{< / code-panel >}}
{{< / code-tab >}}
