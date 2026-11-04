# Web元素交互

> 用于操纵表单的高级指令集.

URL: https://www.selenium.dev/zh-cn/documentation/webdriver/elements/interactions/

---

仅有五种基本命令可用于元素的操作:
* [点击](https://w3c.github.io/webdriver/#element-click) (适用于任何元素)
* [发送键位](https://w3c.github.io/webdriver/#element-send-keys) (仅适用于文本字段和内容可编辑元素)
* [清除](https://w3c.github.io/webdriver/#element-send-keys) (仅适用于文本字段和内容可编辑元素)
* 提交 (仅适用于表单元素)
* 选择 (参见 [选择列表元素](https://www.selenium.dev/zh-cn/documentation/webdriver/support_features/select_lists/index.md))

## 附加验证

这些方法的设计目的是尽量模拟用户体验, 所以,
与 [Actions接口](https://www.selenium.dev/zh-cn/documentation/webdriver/actions_api/index.md) 不同, 
在指定制定操作之前, 
会尝试执行两件事.
1. 如果它确定元素在视口之外, 
   [则会将元素滚动到视图中](https://w3c.github.io/webdriver/#dfn-scrolls-into-view), 
   特别是将元素底部与视口底部对齐.
2. 确保元素在执行操作之前是[可交互的](https://w3c.github.io/webdriver/#interactability) .
   这可能意味着滚动不成功, 
   或者该元素没有以其他方式显示.  
   确定某个元素是否显示在页面上太难了
   [无法直接在webdriver规范中定义](https://w3c.github.io/webdriver/#element-displayedness),
   因此Selenium发送一个带有JavaScript原子的执行命令, 
   检查是否有可能阻止该元素显示.
   如果确定某个元素不在视口中, 不显示, 不可
   [键盘交互](https://w3c.github.io/webdriver/#dfn-keyboard-interactable), 
   或不可
   [指针交互](https://w3c.github.io/webdriver/#dfn-pointer-interactable),
   则返回一个[元素不可交互](https://w3c.github.io/webdriver/#dfn-element-not-interactable) 错误.

## 点击

[元素点击命令](https://w3c.github.io/webdriver/#dfn-element-click) 执行在 [元素中央](https://w3c.github.io/webdriver/#dfn-center-point).
如果元素中央由于某些原因被 [遮挡](https://w3c.github.io/webdriver/#dfn-obscuring) ,
Selenium将返回一个 [元素点击中断](https://w3c.github.io/webdriver/#dfn-element-click-intercepted) 错误.

**Java**

```java
driver.get("https://www.selenium.dev/selenium/web/inputs.html");

// Click on the element
WebElement checkInput = driver.findElement(By.name("checkbox_input"));
checkInput.click();
```

**Python**

```python
# Navigate to URL
driver.get("https://www.selenium.dev/selenium/web/inputs.html")

# Click on the checkbox
check_input = driver.find_element(By.NAME, "checkbox_input")
check_input.click()
```

**CSharp**

```csharp
	// Navigate to Url
	driver.Navigate().GoToUrl("https://www.selenium.dev/selenium/web/inputs.html");
	// Click on the element 
	IWebElement checkInput = driver.FindElement(By.Name("checkbox_input"));
	checkInput.Click();
	```

**Ruby**

```ruby
driver.find_element(name: 'color_input').click
```

**JavaScript**

```javascript
  await submitButton.click();
  ```

**Kotlin**

```kotlin
    // Navigate to Url
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

    // Click the element
    driver.findElement(By.name("color_input")).click();

```

## 发送键位

[元素发送键位命令](https://w3c.github.io/webdriver/#dfn-element-send-keys)
将录入提供的键位到 [可编辑的](https://w3c.github.io/webdriver/#dfn-editable) 元素.
通常, 这意味着元素是具有 `文本` 类型的表单的输入元素或具有 `内容可编辑` 属性的元素.
如果不可编辑, 则返回
[无效元素状态](https://w3c.github.io/webdriver/#dfn-invalid-element-state) 错误.

[以下](https://www.w3.org/TR/webdriver/#keyboard-actions) 
是WebDriver支持的按键列表.

**Java**

```java
// Clear field to empty it from any previous data
WebElement emailInput = driver.findElement(By.name("email_input"));
emailInput.clear();
// Enter Text
String email = "admin@localhost.dev";
emailInput.sendKeys(email);
```

**Python**

```python
# Handle the email input field
email_input = driver.find_element(By.NAME, "email_input")
email_input.clear()  # Clear field

email = "admin@localhost.dev"
email_input.send_keys(email)  # Enter text
```

**CSharp**

```csharp
	//SendKeys
	// Clear field to empty it from any previous data
	IWebElement emailInput = driver.FindElement(By.Name("email_input"));
	emailInput.Clear();
	//Enter Text
	String email = "admin@localhost.dev";
	emailInput.SendKeys(email);
	```

**Ruby**

```ruby
driver.find_element(name: 'email_input').send_keys 'admin@localhost.dev'
```

**JavaScript**

```javascript
  let inputField = await driver.findElement(By.name('no_type'));
  ```

**Kotlin**

```kotlin

    // Navigate to Url
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

	//Clear field to empty it from any previous data
	driver.findElement(By.name("email_input")).clear()

    // Enter text 
    driver.findElement(By.name("email_input")).sendKeys("admin@localhost.dev")

```

## 清除

[元素清除命令](https://w3c.github.io/webdriver/#dfn-element-clear) 
重置元素的内容.
这要求元素 [可编辑](https://w3c.github.io/webdriver/#dfn-editable),
且 [可重置](https://w3c.github.io/webdriver/#dfn-resettable-elements).
通常, 这意味着元素是具有 `文本` 类型的表单的输入元素或具有 `内容可编辑` 属性的元素.
如果不满足这些条件, 将返回
[无效元素状态](https://w3c.github.io/webdriver/#dfn-invalid-element-state) 错误.

**Java**

```java
// Clear field to empty it from any previous data
emailInput.clear();
data = emailInput.getAttribute("value");
```

**Python**

```python
email_input.clear()
```

**CSharp**

```csharp
	//Clear Element
	// Clear field to empty it from any previous data
	emailInput.Clear();
	data = emailInput.GetAttribute("value");
	```

**Ruby**

```ruby
driver.find_element(name: 'email_input').clear
```

**JavaScript**

```javascript
  await driver.get('https://www.selenium.dev/selenium/web/inputs.html');
  ```

**Kotlin**

```kotlin

    // Navigate to Url
    driver.get("https://www.selenium.dev/selenium/web/inputs.html")

	//Clear field to empty it from any previous data
	driver.findElement(By.name("email_input")).clear()

```

## 提交

在Selenium 4中, 
不再通过单独的端点以及脚本执行的方法来实现. 
因此, 建议不要使用此方法, 
而是单击相应的表单提交按钮.
