# Working with IFrames and frames

URL: https://www.selenium.dev/documentation/webdriver/interactions/frames/
Site index: https://www.selenium.dev/llms.txt

---

Frames are a now deprecated means of building a site layout from
multiple documents on the same domain. You are unlikely to work with
them unless you are working with an pre HTML5 webapp.  Iframes allow
the insertion of a document from an entirely different domain, and are
still commonly used.

If you need to work with frames or iframes, WebDriver allows you to
work with them in the same way. Consider a button within an iframe.
If we inspect the element using the browser development tools, we might
see the following:

```html
<div id="modal">
  <iframe id="buttonframe" name="myframe"  src="https://seleniumhq.github.io">
   <button>Click here</button>
 </iframe>
</div>
```

If it was not for the iframe we would expect to click on the button
using something like:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
//This won't work
driver.findElement(By.tagName("button")).click();

```

**Python**

```python
    # This Wont work
driver.find_element(By.TAG_NAME, 'button').click()

```

**CSharp**

```csharp
//This won't work
driver.FindElement(By.TagName("button")).Click();

```

**Ruby**

```ruby
    # This won't work
driver.find_element(:tag_name,'button').click

```

**JavaScript**

```javascript
// This won't work
await driver.findElement(By.css('button')).click();

```

**Kotlin**

```kotlin
//This won't work
driver.findElement(By.tagName("button")).click()

```

However, if there are no buttons outside of the iframe, you might
instead get a _no such element_ error. This happens because Selenium is
only aware of the elements in the top level document. To interact with
the button, we will need to first switch to the frame, in a similar way
to how we switch windows. 
WebDriver offers three ways of switching to a frame. Following example code
shows how we can do that, using a live web example. 

## Using a WebElement

Switching using a WebElement is the most flexible option. You can
find the frame using your preferred selector and switch to it.

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
//switch To IFrame using Web Element
WebElement iframe = driver.findElement(By.id("iframe1"));
//Switch to the frame
driver.switchTo().frame(iframe);
assertEquals(true, driver.getPageSource().contains("We Leave From Here"));
//Now we can type text into email field
WebElement emailE = driver.findElement(By.id("email"));
emailE.sendKeys("admin@selenium.dev");
emailE.clear();
```

**Python**

```python
# --- Switch to iframe using WebElement ---
iframe = driver.find_element(By.ID, "iframe1")
driver.switch_to.frame(iframe)
assert "We Leave From Here" in driver.page_source

email_element = driver.find_element(By.ID, "email")
email_element.send_keys("admin@selenium.dev")
email_element.clear()
driver.switch_to.default_content()
```

**CSharp**

```csharp
//switch To IFrame using Web Element
IWebElement iframe = driver.FindElement(By.Id("iframe1"));
//Switch to the frame
driver.SwitchTo().Frame(iframe);
Assert.AreEqual(true, driver.PageSource.Contains("We Leave From Here"));
//Now we can type text into email field
IWebElement emailE = driver.FindElement(By.Id("email"));
emailE.SendKeys("admin@selenium.dev");
emailE.Clear();
```

**Ruby**

```ruby
iframe = driver.find_element(:id, 'iframe1')
driver.switch_to.frame(iframe)
expect(driver.page_source).to include('We Leave From Here')

email_element = driver.find_element(:id, 'email')
email_element.send_keys('admin@selenium.dev')
email_element.clear
driver.switch_to.default_content
```

**JavaScript**

```javascript
// Store the web element
const iframe = driver.findElement(By.css('#modal > iframe'));

// Switch to the frame
await driver.switchTo().frame(iframe);

// Now we can click the button
await driver.findElement(By.css('button')).click();

```

**Kotlin**

```kotlin
//Store the web element
val iframe = driver.findElement(By.cssSelector("#modal>iframe"))

//Switch to the frame
driver.switchTo().frame(iframe)

//Now we can click the button
driver.findElement(By.tagName("button")).click()

```

## Using a name or ID
If your frame or iframe has an id or name attribute, this can be used
instead.  If the name or ID is not unique on the page, then the first
one found will be switched to.

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
//switch To IFrame using name or id
WebElement iframe1=driver.findElement(By.name("iframe1-name"));
//Switch to the frame
driver.switchTo().frame(iframe1);
assertEquals(true, driver.getPageSource().contains("We Leave From Here"));
WebElement email = driver.findElement(By.id("email"));
//Now we can type text into email field
email.sendKeys("admin@selenium.dev");
email.clear();
```

**Python**

```python
# --- Switch to iframe using name or ID ---
iframe1=driver.find_element(By.NAME, "iframe1-name")  # (This line doesn't switch, just locates)
driver.switch_to.frame(iframe1)
assert "We Leave From Here" in driver.page_source

email = driver.find_element(By.ID, "email")
email.send_keys("admin@selenium.dev")
email.clear()
driver.switch_to.default_content()
```

**CSharp**

```csharp
//switch To IFrame using name or id
IWebElement iframe1=driver.FindElement(By.Name("iframe1-name"));
//Switch to the frame
driver.SwitchTo().Frame(iframe1);
Assert.AreEqual(true, driver.PageSource.Contains("We Leave From Here"));
IWebElement email = driver.FindElement(By.Id("email"));
//Now we can type text into email field
email.SendKeys("admin@selenium.dev");
email.Clear();
```

**Ruby**

```ruby
iframe1 = driver.find_element(:name, 'iframe1-name')
driver.switch_to.frame(iframe1)
expect(driver.page_source).to include('We Leave From Here')

email = driver.find_element(:id, 'email')
email.send_keys('admin@selenium.dev')
email.clear
driver.switch_to.default_content
```

**JavaScript**

```javascript
// Using the ID
await driver.switchTo().frame('buttonframe');

// Or using the name instead
await driver.switchTo().frame('myframe');

// Now we can click the button
await driver.findElement(By.css('button')).click();

```

**Kotlin**

```kotlin
//Using the ID
driver.switchTo().frame("buttonframe")

//Or using the name instead
driver.switchTo().frame("myframe")

//Now we can click the button
driver.findElement(By.tagName("button")).click()

```

## Using an index

It is also possible to use the index of the frame, such as can be
queried using _window.frames_ in JavaScript.

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
//switch To IFrame using index
driver.switchTo().frame(0);
```

**Python**

```python
driver.switch_to.frame(0)
assert "We Leave From Here" in driver.page_source
```

**CSharp**

```csharp
//switch To IFrame using index
driver.SwitchTo().Frame(0);
```

**Ruby**

```ruby
driver.switch_to.frame(0)
expect(driver.page_source).to include('We Leave From Here')
```

**JavaScript**

```javascript
// Switches to the second frame
await driver.switchTo().frame(1);

```

**Kotlin**

```kotlin
// Switches to the second frame
driver.switchTo().frame(1)

```

## Leaving a frame

To leave an iframe or frameset, switch back to the default content
like so:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
//leave frame
driver.switchTo().defaultContent();
```

**Python**

```python
driver.switch_to.default_content()
assert "This page has iframes" in driver.page_source
```

**CSharp**

```csharp
//leave frame
driver.SwitchTo().DefaultContent();
```

**Ruby**

```ruby
driver.switch_to.default_content
expect(driver.page_source).to include('This page has iframes')
```

**JavaScript**

```javascript
// Return to the top level
await driver.switchTo().defaultContent();

```

**Kotlin**

```kotlin
// Return to the top level
driver.switchTo().defaultContent()

```
