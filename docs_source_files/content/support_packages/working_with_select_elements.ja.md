---
title: "選択要素の操作"
weight: 3
---


一部の要素では、自動化するためにかなりのボイラープレートコードが必要になる場合があります。
これを減らしてテストをきれいにするために、Seleniumサポートパッケージに `Select` クラスがあります。
それを使用するには、次のimportステートメントが必要です。

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

そして、 `<select>` 要素を参照するWebElementを使用してSelectオブジェクトを作成できます。

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

Selectオブジェクトは、 `<select>` 要素とやり取りできる一連のコマンドを提供します。
まず、 `<select>` 要素からオプションを選択するさまざまな方法があります。

```html
<select>
 <option value=value1>Bread</option>
 <option value=value2 selected>Milk</option>
 <option value=value3>Cheese</option>
</select>
```

上記の要素から最初のオプションを選択するには、3つの方法があります。

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

以下を使用して、選択されているオプションを確認できます。

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
// Return a List<WebElement> of options that have been selected
var allSelectedOptions = selectObject.AllSelectedOptions;

// Return a WebElement referencing the first selection option found by walking down the DOM
var firstSelectedOption = selectObject.AllSelectedOptions.FirstOrDefault();
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

または、 `<select>` 要素に含まれる `<option>` 要素に興味があるかもしれません。

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

要素の選択を解除する場合は、4つの選択肢があります。

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

最後に、一部の `<select>` 要素を使用すると、複数のオプションを選択できます。
`<select>` 要素がこれらの1つであるかどうかを調べるには、下記のように記述します。

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
