# Interacting with web elements

> A high-level instruction set for manipulating form controls.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/elements/interactions/

---

There are only 5 basic commands that can be executed on an element:
* [click](https://w3c.github.io/webdriver/#element-click) (applies to any element)
* [send keys](https://w3c.github.io/webdriver/#element-send-keys) (only applies to text fields and keyboard interactable elements)
* [clear](https://w3c.github.io/webdriver/#element-send-keys) (only applies to text fields and content editable elements)
* submit (only applies to form elements)
* select (see [Select List Elements](https://www.selenium.dev/pt-br/documentation/webdriver/support_features/select_lists/index.md))

## Additional validations

These methods are designed to closely emulate a user's experience, so,
unlike the [Actions API](https://www.selenium.dev/pt-br/documentation/webdriver/actions_api/index.md), it attempts to perform two things
before attempting the specified action.
1. If it determines the element is outside the viewport, it
   [scrolls the element into view](https://w3c.github.io/webdriver/#dfn-scrolls-into-view), specifically
   it will align the bottom of the element with the bottom of the viewport.
2. It ensures the element is [interactable](https://w3c.github.io/webdriver/#interactability)
   before taking the action. This could mean that the scrolling was unsuccessful, or that the
   element is not otherwise displayed.  Determining if an element is displayed on a page was too difficult to
   [define directly in the webdriver specification](https://w3c.github.io/webdriver/#element-displayedness),
   so Selenium sends an execute command with a JavaScript atom that checks for things that would keep
   the element from being displayed. If it determines an element is not in the viewport, not displayed, not
   [keyboard-interactable](https://w3c.github.io/webdriver/#dfn-keyboard-interactable), or not
   [pointer-interactable](https://w3c.github.io/webdriver/#dfn-pointer-interactable),
   it returns an [element not interactable](https://w3c.github.io/webdriver/#dfn-element-not-interactable) error.

## Click

The [element click command](https://w3c.github.io/webdriver/#dfn-element-click) is executed on
the [center of the element](https://w3c.github.io/webdriver/#dfn-center-point).
If the center of the element is [obscured](https://w3c.github.io/webdriver/#dfn-obscuring) for some reason,
Selenium will return an [element click intercepted](https://w3c.github.io/webdriver/#dfn-element-click-intercepted) error.

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

## Send keys

The [element send keys command](https://w3c.github.io/webdriver/#dfn-element-send-keys)
types the provided keys into an [keyboard interactable](https://w3c.github.io/webdriver/#dfn-keyboard-interactable) element.
Typically, this means an element is an input element of a form with a `text` type or an element
with a `content-editable` attribute. If it is not editable,
[an invalid element state](https://w3c.github.io/webdriver/#dfn-invalid-element-state) error is returned.

[Here](https://www.w3.org/TR/webdriver/#keyboard-actions) is the list of
possible keystrokes that WebDriver Supports.

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

## Clear

The [element clear command](https://w3c.github.io/webdriver/#dfn-element-clear) resets the content of an element.
This requires an element to be [editable](https://w3c.github.io/webdriver/#dfn-editable),
and [resettable](https://w3c.github.io/webdriver/#dfn-resettable-elements). Typically,
this means an element is an input element of a form with a `text` type or an element
with a`content-editable` attribute. If these conditions are not met,
[an invalid element state](https://w3c.github.io/webdriver/#dfn-invalid-element-state) error is returned.

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

## Submit

In Selenium 4 this is no longer implemented with a separate endpoint and functions by executing a script. As
such, it is recommended not to use this method and to click the applicable form submission button instead.
