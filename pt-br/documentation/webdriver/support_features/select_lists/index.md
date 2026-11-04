# Trabalhando com elementos select

> Select lists have special behaviors compared to other elements.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/support_features/select_lists/

---

The Select object will now give you a series of commands
that allow you to interact with a `<select>` element.

If you are using Java or .NET make sure that you've properly required the support package
in your code. See the full code from GitHub in any of the examples below.

Note that this class only works for HTML elements `select` and `option`.
It is possible to design drop-downs with JavaScript overlays using `div` or `li`,
and this class will not work for those.

## Types

Select methods may behave differently depending on which type of `<select>` element is being worked with.

### Single select

This is the standard drop-down object where one and only one option may be selected.

```html
<select name="selectomatic">
    <option selected="selected" id="non_multi_option" value="one">One</option>
    <option value="two">Two</option>
    <option value="four">Four</option>
    <option value="still learning how to count, apparently">Still learning how to count, apparently</option>
</select>
```

### Multiple select

This select list allows selecting and deselecting more than one option at a time.
This only applies to `<select>` elements with the `multiple` attribute.

```html
<select name="multi" id="multi" multiple="multiple">
    <option selected="selected" value="eggs">Eggs</option>
    <option value="ham">Ham</option>
    <option selected="selected" value="sausages">Sausages</option>
    <option value="onion gravy">Onion gravy</option>
</select>
```

## Create class

First locate a `<select>` element, then use it to initialize a `Select` object.
Note that as of Selenium 4.5, you can't create a `Select` object if the `<select>` element is disabled.

**Java**

```java
WebElement selectElement = driver.findElement(By.name("selectomatic"));
Select select = new Select(selectElement);
```

**Python**

```python
select_element = driver.find_element(By.NAME, 'selectomatic')
select = Select(select_element)
```

**CSharp**

```csharp
var selectElement = driver.FindElement(By.Name("selectomatic"));
var select = new SelectElement(selectElement);
```

**Ruby**

```ruby
select_element = driver.find_element(name: 'selectomatic')
select = Selenium::WebDriver::Support::Select.new(select_element)
```

**JavaScript**

```javascript

it('Select an option', async function () {
```

**Kotlin**

```kotlin
val selectElement = driver.findElement(By.name("selectomatic"))
val select = Select(selectElement)
```

## List options

There are two lists that can be obtained:

### All options

Get a list of all options in the `<select>` element:

**Java**

```java
List<WebElement> optionList = select.getOptions();
```

**Python**

```python
option_list = select.options
```

**CSharp**

```csharp
IList<IWebElement> optionList = select.Options;
```

**Ruby**

```ruby
option_list = select.options
```

**JavaScript**

```javascript

```

**Kotlin**

```kotlin
val optionList = select.getOptions()
```

### Selected options

Get a list of selected options in the `<select>` element. For a standard select list
this will only be a list with one element, for a multiple select list it can contain
zero or many elements.

**Java**

```java
List<WebElement> selectedOptionList = select.getAllSelectedOptions();
```

**Python**

```python
selected_option_list = select.all_selected_options
```

**CSharp**

```csharp
IList<IWebElement> selectedOptionList = select.AllSelectedOptions;
```

**Ruby**

```ruby
selected_option_list = select.selected_options
```

**JavaScript**

```javascript
}
```

**Kotlin**

```kotlin
val selectedOptionList = select.getAllSelectedOptions()
```

## Select option

The Select class provides three ways to select an option.
Note that for multiple select type Select lists, you can repeat these methods
for each element you want to select.

### Text

Select the option based on its visible text

**Java**

```java
select.selectByVisibleText("Four");
```

**Python**

```python
select.select_by_visible_text('Four')
```

**CSharp**

```csharp
select.SelectByText("Four");
```

**Ruby**

```ruby
select.select_by(:text, 'Four')
```

**JavaScript**

```javascript
const countElement = await driver.findElement(By.css("option[value='still learning how to count, apparently']"))
```

**Kotlin**

```kotlin
select.selectByVisibleText("Four")
```

### Value

Select the option based on its value attribute

**Java**

```java
select.selectByValue("two");
```

**Python**

```python
select.select_by_value('two')
```

**CSharp**

```csharp
select.SelectByValue("two");
```

**Ruby**

```ruby
select.select_by(:value, 'two')
```

**JavaScript**

```javascript
assert.equal(true, await fourElement.isSelected())
```

**Kotlin**

```kotlin
select.selectByValue("two")
```

### Index

Select the option based on its position in the list

**Java**

```java
select.selectByIndex(3);
```

**Python**

```python
select.select_by_index(3)
```

**CSharp**

```csharp
select.SelectByIndex(3);
```

**Ruby**

```ruby
select.select_by(:index, 3)
```

**JavaScript**

```javascript
assert.equal(true, await twoElement.isSelected())
```

**Kotlin**

```kotlin
select.selectByIndex(3)
```

### Disabled options

_Requires [Selenium v4.5](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.5.0) or later._

Options with a `disabled` attribute may not be selected.

```html
    <select name="single_disabled">
      <option id="sinlge_disabled_1" value="enabled">Enabled</option>
      <option id="sinlge_disabled_2" value="disabled" disabled="disabled">Disabled</option>
    </select>
```

**Java**

```java
Assertions.assertThrows(UnsupportedOperationException.class, () -> {
    select.selectByValue("disabled");
});
```

**Python**

```python
with pytest.raises(NotImplementedError):
    select.select_by_value('disabled')
```

**CSharp**

```csharp
Assert.ThrowsException<InvalidOperationException>(() => select.SelectByValue("disabled"));
```

**Ruby**

```ruby
expect {
  select.select_by(:value, 'disabled')
}.to raise_exception(Selenium::WebDriver::Error::UnsupportedOperationError)
```

**JavaScript**

```javascript
const select = await new Select(selectElement)

await assert.rejects(async () => {
  await select.selectByValue("disabled")
```

**Kotlin**

```kotlin
Assertions.assertThrows(UnsupportedOperationException::class.java) {
  select.selectByValue("disabled")
}
```

## De-select option

Only multiple select type select lists can have options de-selected. 
You can repeat these methods for each element you want to select.

**Java**

```java
select.deselectByValue("eggs");
```

**Python**

```python
select.deselect_by_value('eggs')
```

**CSharp**

```csharp
select.DeselectByValue("eggs");
```

**Ruby**

```ruby
select.deselect_by(:value, 'eggs')
```

**JavaScript**

```javascript
assert.equal(true, await gravyElement.isSelected())
```

**Kotlin**

```kotlin
select.deselectByValue("eggs")
```
