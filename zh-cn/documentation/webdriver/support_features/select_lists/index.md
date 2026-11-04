# 使用选择列表元素

> 与其他元素相比，选择列表具有特殊的行为.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/support_features/select_lists/

---

Select对象现在将为您提供一系列命令,
用于允许您与 `<select>` 元素进行交互.

如果您使用的是 Java 或 .NET，
请确保您在代码中已正确加载所需的包.
您可以通过GitHub查看下面示例的完整代码.

请注意，此类仅适用于 HTML 元素 `select` 和 `option`.
这个类将不适用于那些通过 `div` 或 `li` 
并使用JavaScript遮罩层设计的下拉列表.

## 类型

选择方法的行为可能会有所不同，
具体取决于正在使用的 `<select>` 元素的类型.

### 单选

这是标准的下拉对象，其只能选定一个选项.

```html
<select name="selectomatic">
    <option selected="selected" id="non_multi_option" value="one">One</option>
    <option value="two">Two</option>
    <option value="four">Four</option>
    <option value="still learning how to count, apparently">Still learning how to count, apparently</option>
</select>
```

### 复选

此选择列表允许同时选定和取消选择多个选项.
这仅适用于具有 `multiple` 属性的 `<select>`元素.

```html
<select name="multi" id="multi" multiple="multiple">
    <option selected="selected" value="eggs">Eggs</option>
    <option value="ham">Ham</option>
    <option selected="selected" value="sausages">Sausages</option>
    <option value="onion gravy">Onion gravy</option>
</select>
```

## 构建类

首先定位一个 `<select>` 元素,
然后借助其初始化一个`Select` 对象.
请注意, 从 Selenium 4.5 开始,
您无法针对禁用的 `<select>` 元素构建 `Select` 对象.

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

## 选项列表

共有两种列表可以被获取:

### 全部选项

获取 `<select>` 元素中所有选项列表:

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

### 选中的选项

获取 `<select>` 元素中所选中的选项列表.
对于标准选择列表这将只是一个包含一个元素的列表, 
对于复选列表则表示包含的零个或多个元素.

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

## 选项

Select类提供了三种选择选项的方法.
请注意, 对于复选类型的选择列, 
对于要选择的每个元素可以重复使用这些方法.

### 文本

根据其可见文本选择选项

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

### 值

根据其值属性选择选项

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

### 序号

根据其在列表中的位置选择选项

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

### 禁用的选项

_Requires [Selenium v4.5](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.5.0) or later._

具有 `disabled` 属性的选项可能无法被选择.

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

## 取消选择选项

只有复选类型的选择列表才能取消选择选项.
您可以对要选择的每个元素重复使用这些方法.

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
