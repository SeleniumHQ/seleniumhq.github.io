# Esperas

URL: https://www.selenium.dev/pt-br/documentation/webdriver/waits/

---

Perhaps the most common challenge for browser automation is ensuring
that the web application is in a state to execute a particular
Selenium command as desired. The processes often end up in
a _race condition_ where sometimes the browser gets into the right
state first (things work as intended) and sometimes the Selenium code
executes first (things do not work as intended). This is one of the
primary causes of _flaky tests_.

All navigation commands wait for a specific `readyState` value
based on the [page load strategy](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/options/index.md#pageloadstrategy) (the
default value to wait for is `"complete"`) before the driver returns control to the code.
The `readyState` only concerns itself with loading assets defined in the HTML, 
but loaded JavaScript assets often result in changes to the site,
and elements that need to be interacted with may not yet be on the page
when the code is ready to execute the next Selenium command.

Similarly, in a lot of single page applications, elements get dynamically
added to a page or change visibility based on a click.
An element must be both present and
[displayed](https://www.selenium.dev/pt-br/documentation/webdriver/elements/information/index.md#is-displayed) on the page
in order for Selenium to interact with it.

Take this page for example: https://www.selenium.dev/selenium/web/dynamic.html
When the "Add a box!" button is clicked, a "div" element that does not exist is created.
When the "Reveal a new input" button is clicked, a hidden text field element is displayed.
In both cases the transition takes a couple seconds.
If the Selenium code is to click one of these buttons and interact with the resulting element,
it will do so before that element is ready and fail.

The first solution many people turn to is adding a sleep statement to
pause the code execution for a set period of time.
Because the code can't know exactly how long it needs to wait, this
can fail when it doesn't sleep long enough. Alternately, if the value is set too high
and a sleep statement is added in every place it is needed, the duration of
the session can become prohibitive.

Selenium provides two different mechanisms for synchronization that are better.

## Implicit waits
Selenium has a built-in way to automatically wait for elements called an _implicit wait_.
An implicit wait value can be set either with the [timeouts](https://www.selenium.dev/pt-br/documentation/webdriver/drivers/options/index.md#timeouts)
capability in the browser options, or with a driver method (as shown below).

This is a global setting that applies to every element location call for the entire session.
The default value is `0`, which means that if the element is not found, it will
immediately return an error. If an implicit wait is set, the driver will wait for the 
duration of the provided value before returning the error. Note that as soon as the 
element is located, the driver will return the element reference and the code will continue executing, 
so a larger implicit wait value won't necessarily increase the duration of the session.

*Warning:*
Do not mix implicit and explicit waits.
Doing so can cause unpredictable wait times.
For example, setting an implicit wait of 10 seconds
and an explicit wait of 15 seconds
could cause a timeout to occur after 20 seconds.

Solving our example with an implicit wait looks like this:

**Java**

```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
```

**Python**

```python
driver.implicitly_wait(2)
```

**CSharp**

```csharp
driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(2);
```

**Ruby**

```ruby
driver.manage.timeouts.implicit_wait = 2
```

**JavaScript**

```javascript
await driver.manage().setTimeouts({ implicit: 2000 });
```

**Kotlin**

```kotlin
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2))
```

## Explicit waits

_Explicit waits_ are loops added to the code that poll the application
for a specific condition to evaluate as true before it exits the loop and
continues to the next command in the code. If the condition is not met before a designated timeout value,
the code will give a timeout error. Since there are many ways for the application not to be in the desired state,
explicit waits are a great choice to specify the exact condition to wait for
in each place it is needed.
Another nice feature is that, by default, the Selenium Wait class automatically waits for the designated element to exist.

**Java**

This example shows the condition being waited for as a _lambda_. Java also supports
[Expected Conditions](https://www.selenium.dev/pt-br/documentation/webdriver/support_features/expected_conditions/index.md)

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
wait.until(d -> revealed.isDisplayed());
```

**Python**

This example shows the condition being waited for as a _lambda_. Python also supports
[Expected Conditions](https://www.selenium.dev/pt-br/documentation/webdriver/support_features/expected_conditions/index.md)

```python
wait = WebDriverWait(driver, timeout=2)
wait.until(lambda _ : revealed.is_displayed())
```

**CSharp**

```csharp
WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(2));
wait.Until(d => revealed.Displayed);
```

**Ruby**

```ruby
wait = Selenium::WebDriver::Wait.new
wait.until { revealed.displayed? }
```

**JavaScript**

JavaScript also supports [Expected Conditions](https://www.selenium.dev/pt-br/documentation/webdriver/support_features/expected_conditions/index.md)

```javascript
await driver.wait(until.elementIsVisible(revealed), 2000);
```

**Kotlin**

```kotlin
val wait =  WebDriverWait(driver, Duration.ofSeconds(2))
wait.until { revealed.isDisplayed }
```

### Built-in conditions

Most bindings include a set of ready-made conditions
for the situations that come up most often in waits.
You don't have to write a custom lambda for each one.
Conditions that take a locator search for the element on every poll,
so they also wait for it to appear.
Many of them hand back the element (or elements) once the condition is met,
letting you use the result of the wait directly.

This example locates an element, waits for it to be visible,
and then waits for its text, using only built-in conditions:

**Java**

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
wait.until(ExpectedConditions.titleIs("Web form - target page"));
WebElement message = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));
wait.until(ExpectedConditions.visibilityOf(message));
wait.until(ExpectedConditions.textToBePresentInElement(message, "Received!"));
```

**Python**

```python
wait = WebDriverWait(driver, timeout=2)
wait.until(EC.title_is("Web form - target page"))
message = wait.until(EC.presence_of_element_located((By.ID, "message")))
wait.until(EC.visibility_of(message))
wait.until(EC.text_to_be_present_in_element((By.ID, "message"), "Received!"))
```

**CSharp**

.NET does not ship built-in conditions. Pass a lambda to `WebDriverWait.Until()`, as in the
[explicit wait](/pt-br/documentation/webdriver/waits/index.md#explicit-waits) example.

**Ruby**

Ruby does not ship built-in conditions. Pass a block to `Selenium::WebDriver::Wait#until`, as in the
[explicit wait](/pt-br/documentation/webdriver/waits/index.md#explicit-waits) example.

**JavaScript**

```javascript
await driver.wait(until.titleIs("Web form - target page"), 2000);
let message = await driver.wait(until.elementLocated(By.id("message")), 2000);
await driver.wait(until.elementIsVisible(message), 2000);
await driver.wait(until.elementTextIs(message, "Received!"), 2000);
```

**Kotlin**

Kotlin uses the Java `ExpectedConditions` class listed in the Java tab below.

The complete list of built-in conditions for each binding:

**Java**

Class `org.openqa.selenium.support.ui.ExpectedConditions`
([API docs](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html)).
Use with `new WebDriverWait(driver, timeout).until(...)`.

<table>
	<thead>
			<tr>
					<th>Condition</th>
					<th>Waits until</th>
			</tr>
	</thead>
	<tbody>
			<tr>
					<td>`titleIs(title)`</td>
					<td>The page title equals the value</td>
			</tr>
			<tr>
					<td>`titleContains(title)`</td>
					<td>The page title contains the value</td>
			</tr>
			<tr>
					<td>`urlToBe(url)`</td>
					<td>The current URL equals the value</td>
			</tr>
			<tr>
					<td>`urlContains(fraction)`</td>
					<td>The current URL contains the value</td>
			</tr>
			<tr>
					<td>`urlMatches(regex)`</td>
					<td>The current URL matches the regular expression</td>
			</tr>
			<tr>
					<td>`presenceOfElementLocated(By)`</td>
					<td>An element is in the DOM (not necessarily visible); returns it</td>
			</tr>
			<tr>
					<td>`presenceOfAllElementsLocatedBy(By)`</td>
					<td>At least one element is in the DOM; returns them all</td>
			</tr>
			<tr>
					<td>`presenceOfNestedElementLocatedBy(By or WebElement, By)`</td>
					<td>A child element is in the DOM under a parent; returns it</td>
			</tr>
			<tr>
					<td>`presenceOfNestedElementsLocatedBy(By, By)`</td>
					<td>Child elements are in the DOM under a parent; returns them</td>
			</tr>
			<tr>
					<td>`visibilityOfElementLocated(By)`</td>
					<td>An element is in the DOM and displayed; returns it</td>
			</tr>
			<tr>
					<td>`visibilityOf(WebElement)`</td>
					<td>A known element is displayed; returns it</td>
			</tr>
			<tr>
					<td>`visibilityOfAllElementsLocatedBy(By)`</td>
					<td>All matching elements are displayed; returns them</td>
			</tr>
			<tr>
					<td>`visibilityOfAllElements(WebElement...)`</td>
					<td>All given elements are displayed; returns them</td>
			</tr>
			<tr>
					<td>`visibilityOfNestedElementsLocatedBy(By or WebElement, By)`</td>
					<td>Child elements under a parent are displayed; returns them</td>
			</tr>
			<tr>
					<td>`invisibilityOfElementLocated(By)`</td>
					<td>An element is hidden or not in the DOM</td>
			</tr>
			<tr>
					<td>`invisibilityOf(WebElement)`</td>
					<td>A known element is hidden or no longer in the DOM</td>
			</tr>
			<tr>
					<td>`invisibilityOfAllElements(WebElement...)`</td>
					<td>All given elements are hidden</td>
			</tr>
			<tr>
					<td>`invisibilityOfElementWithText(By, text)`</td>
					<td>No displayed element matching the locator has the given text</td>
			</tr>
			<tr>
					<td>`elementToBeClickable(By or WebElement)`</td>
					<td>An element is displayed and enabled; returns it</td>
			</tr>
			<tr>
					<td>`elementToBeSelected(By or WebElement)`</td>
					<td>An element is selected</td>
			</tr>
			<tr>
					<td>`elementSelectionStateToBe(By or WebElement, selected)`</td>
					<td>An element’s selected state matches the value</td>
			</tr>
			<tr>
					<td>`stalenessOf(WebElement)`</td>
					<td>An element is no longer attached to the DOM</td>
			</tr>
			<tr>
					<td>`textToBe(By, text)`</td>
					<td>An element’s text equals the value</td>
			</tr>
			<tr>
					<td>`textToBePresentInElement(WebElement, text)`</td>
					<td>An element’s text contains the value</td>
			</tr>
			<tr>
					<td>`textToBePresentInElementLocated(By, text)`</td>
					<td>A located element’s text contains the value</td>
			</tr>
			<tr>
					<td>`textToBePresentInElementValue(By or WebElement, text)`</td>
					<td>An element’s `value` contains the value</td>
			</tr>
			<tr>
					<td>`textMatches(By, Pattern)`</td>
					<td>An element’s text matches the regular expression</td>
			</tr>
			<tr>
					<td>`attributeToBe(By or WebElement, attribute, value)`</td>
					<td>An element’s attribute or CSS property equals the value</td>
			</tr>
			<tr>
					<td>`attributeContains(By or WebElement, attribute, value)`</td>
					<td>An element’s attribute or CSS property contains the value</td>
			</tr>
			<tr>
					<td>`attributeToBeNotEmpty(WebElement, attribute)`</td>
					<td>An element’s attribute or CSS property is not empty</td>
			</tr>
			<tr>
					<td>`domAttributeToBe(WebElement, attribute, value)`</td>
					<td>An element’s DOM attribute equals the value</td>
			</tr>
			<tr>
					<td>`domPropertyToBe(WebElement, property, value)`</td>
					<td>An element’s DOM property equals the value</td>
			</tr>
			<tr>
					<td>`numberOfElementsToBe(By, number)`</td>
					<td>The number of matching elements equals the value; returns them</td>
			</tr>
			<tr>
					<td>`numberOfElementsToBeMoreThan(By, number)`</td>
					<td>The number of matching elements is more than the value; returns them</td>
			</tr>
			<tr>
					<td>`numberOfElementsToBeLessThan(By, number)`</td>
					<td>The number of matching elements is less than the value; returns them</td>
			</tr>
			<tr>
					<td>`numberOfWindowsToBe(number)`</td>
					<td>The number of open windows or tabs equals the value</td>
			</tr>
			<tr>
					<td>`frameToBeAvailableAndSwitchToIt(frame)`</td>
					<td>A frame, given as a `By`, name or ID, index, or `WebElement`, is available; then switches to it</td>
			</tr>
			<tr>
					<td>`alertIsPresent()`</td>
					<td>An alert is open; returns it</td>
			</tr>
			<tr>
					<td>`jsReturnsValue(script)`</td>
					<td>The JavaScript returns a value; returns it</td>
			</tr>
			<tr>
					<td>`javaScriptThrowsNoExceptions(script)`</td>
					<td>The JavaScript runs without throwing</td>
			</tr>
			<tr>
					<td>`and(conditions...)`</td>
					<td>All of the conditions are true</td>
			</tr>
			<tr>
					<td>`or(conditions...)`</td>
					<td>Any of the conditions is true</td>
			</tr>
			<tr>
					<td>`not(condition)`</td>
					<td>The condition is false</td>
			</tr>
			<tr>
					<td>`refreshed(condition)`</td>
					<td>The condition is true, retrying it after a stale element error</td>
			</tr>
	</tbody>
</table>

**Python**

Module `selenium.webdriver.support.expected_conditions`
([API docs](https://www.selenium.dev/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html)),
usually imported as `EC`.
Use with `WebDriverWait(driver, timeout).until(...)`.
Locators are tuples, such as `(By.ID, "message")`.

<table>
	<thead>
			<tr>
					<th>Condition</th>
					<th>Waits until</th>
			</tr>
	</thead>
	<tbody>
			<tr>
					<td>`title_is(title)`</td>
					<td>The page title equals the value</td>
			</tr>
			<tr>
					<td>`title_contains(title)`</td>
					<td>The page title contains the value</td>
			</tr>
			<tr>
					<td>`url_to_be(url)`</td>
					<td>The current URL equals the value</td>
			</tr>
			<tr>
					<td>`url_contains(url)`</td>
					<td>The current URL contains the value</td>
			</tr>
			<tr>
					<td>`url_matches(pattern)`</td>
					<td>The current URL matches the regular expression</td>
			</tr>
			<tr>
					<td>`url_changes(url)`</td>
					<td>The current URL is different from the value</td>
			</tr>
			<tr>
					<td>`presence_of_element_located(locator)`</td>
					<td>An element is in the DOM (not necessarily visible); returns it</td>
			</tr>
			<tr>
					<td>`presence_of_all_elements_located(locator)`</td>
					<td>At least one element is in the DOM; returns them all</td>
			</tr>
			<tr>
					<td>`visibility_of_element_located(locator)`</td>
					<td>An element is in the DOM and displayed; returns it</td>
			</tr>
			<tr>
					<td>`visibility_of(element)`</td>
					<td>A known element is displayed; returns it</td>
			</tr>
			<tr>
					<td>`visibility_of_any_elements_located(locator)`</td>
					<td>At least one matching element is displayed; returns the displayed ones</td>
			</tr>
			<tr>
					<td>`visibility_of_all_elements_located(locator)`</td>
					<td>All matching elements are displayed; returns them</td>
			</tr>
			<tr>
					<td>`invisibility_of_element_located(locator or element)`</td>
					<td>An element is hidden or not in the DOM</td>
			</tr>
			<tr>
					<td>`invisibility_of_element(element or locator)`</td>
					<td>An element is hidden or not in the DOM</td>
			</tr>
			<tr>
					<td>`element_to_be_clickable(locator or element)`</td>
					<td>An element is displayed and enabled; returns it</td>
			</tr>
			<tr>
					<td>`element_to_be_selected(element)`</td>
					<td>A known element is selected</td>
			</tr>
			<tr>
					<td>`element_located_to_be_selected(locator)`</td>
					<td>A located element is selected</td>
			</tr>
			<tr>
					<td>`element_selection_state_to_be(element, is_selected)`</td>
					<td>A known element’s selected state matches the value</td>
			</tr>
			<tr>
					<td>`element_located_selection_state_to_be(locator, is_selected)`</td>
					<td>A located element’s selected state matches the value</td>
			</tr>
			<tr>
					<td>`staleness_of(element)`</td>
					<td>An element is no longer attached to the DOM</td>
			</tr>
			<tr>
					<td>`text_to_be_present_in_element(locator, text)`</td>
					<td>An element’s text contains the value</td>
			</tr>
			<tr>
					<td>`text_to_be_present_in_element_value(locator, text)`</td>
					<td>An element’s `value` contains the value</td>
			</tr>
			<tr>
					<td>`text_to_be_present_in_element_attribute(locator, attribute, text)`</td>
					<td>An element’s attribute contains the value</td>
			</tr>
			<tr>
					<td>`element_attribute_to_include(locator, attribute)`</td>
					<td>An element has the attribute</td>
			</tr>
			<tr>
					<td>`number_of_windows_to_be(num_windows)`</td>
					<td>The number of open windows or tabs equals the value</td>
			</tr>
			<tr>
					<td>`new_window_is_opened(current_handles)`</td>
					<td>A window or tab has opened since the handles were read</td>
			</tr>
			<tr>
					<td>`frame_to_be_available_and_switch_to_it(frame)`</td>
					<td>A frame, given as a locator, name or ID, or element, is available; then switches to it</td>
			</tr>
			<tr>
					<td>`alert_is_present()`</td>
					<td>An alert is open; returns it</td>
			</tr>
			<tr>
					<td>`all_of(*conditions)`</td>
					<td>All of the conditions are true</td>
			</tr>
			<tr>
					<td>`any_of(*conditions)`</td>
					<td>Any of the conditions is true</td>
			</tr>
			<tr>
					<td>`none_of(*conditions)`</td>
					<td>None of the conditions is true</td>
			</tr>
	</tbody>
</table>

**CSharp**

.NET does not ship built-in conditions.
The `ExpectedConditions` class was removed from the bindings in Selenium 4.
Pass a lambda to `WebDriverWait.Until()` that returns a non-null value or `true`
when the condition is met.

**Ruby**

Ruby does not ship built-in conditions.
Pass a block to `Selenium::WebDriver::Wait#until` that returns a truthy value
when the condition is met.

**JavaScript**

Module `until` from `selenium-webdriver`
([API docs](https://www.selenium.dev/selenium/docs/api/javascript/index.html)).
Use with `await driver.wait(until.condition(...), timeout)`.
Conditions that check an element resolve to that element once they are met.

<table>
	<thead>
			<tr>
					<th>Condition</th>
					<th>Waits until</th>
			</tr>
	</thead>
	<tbody>
			<tr>
					<td>`titleIs(title)`</td>
					<td>The page title equals the value</td>
			</tr>
			<tr>
					<td>`titleContains(substr)`</td>
					<td>The page title contains the value</td>
			</tr>
			<tr>
					<td>`titleMatches(regex)`</td>
					<td>The page title matches the regular expression</td>
			</tr>
			<tr>
					<td>`urlIs(url)`</td>
					<td>The current URL equals the value</td>
			</tr>
			<tr>
					<td>`urlContains(substrUrl)`</td>
					<td>The current URL contains the value</td>
			</tr>
			<tr>
					<td>`urlMatches(regex)`</td>
					<td>The current URL matches the regular expression</td>
			</tr>
			<tr>
					<td>`elementLocated(locator)`</td>
					<td>An element is in the DOM; returns it</td>
			</tr>
			<tr>
					<td>`elementsLocated(locator)`</td>
					<td>At least one element is in the DOM; returns them all</td>
			</tr>
			<tr>
					<td>`elementIsVisible(element)`</td>
					<td>An element is displayed</td>
			</tr>
			<tr>
					<td>`elementIsNotVisible(element)`</td>
					<td>An element is not displayed</td>
			</tr>
			<tr>
					<td>`elementIsEnabled(element)`</td>
					<td>An element is enabled</td>
			</tr>
			<tr>
					<td>`elementIsDisabled(element)`</td>
					<td>An element is disabled</td>
			</tr>
			<tr>
					<td>`elementIsSelected(element)`</td>
					<td>An element is selected</td>
			</tr>
			<tr>
					<td>`elementIsNotSelected(element)`</td>
					<td>An element is not selected</td>
			</tr>
			<tr>
					<td>`elementTextIs(element, text)`</td>
					<td>An element’s text equals the value</td>
			</tr>
			<tr>
					<td>`elementTextContains(element, substr)`</td>
					<td>An element’s text contains the value</td>
			</tr>
			<tr>
					<td>`elementTextMatches(element, regex)`</td>
					<td>An element’s text matches the regular expression</td>
			</tr>
			<tr>
					<td>`stalenessOf(element)`</td>
					<td>An element is no longer attached to the DOM</td>
			</tr>
			<tr>
					<td>`ableToSwitchToFrame(frame)`</td>
					<td>A frame is available, then switches to it</td>
			</tr>
			<tr>
					<td>`alertIsPresent()`</td>
					<td>An alert is open; returns it</td>
			</tr>
	</tbody>
</table>

**Kotlin**

Kotlin uses the Java `ExpectedConditions` class. See the Java tab.

### Customization

The Wait class can be instantiated with various parameters that will change how the conditions are evaluated.

This can include:
* Changing how often the code is evaluated (polling interval)
* Specifying which exceptions should be handled automatically
* Changing the total timeout length
* Customizing the timeout message

For instance, if the _element not interactable_ error is retried by default, then we can
add an action on a method inside the code getting executed (we just need to 
make sure that the code returns `true` when it is successful):

**Java**

The easiest way to customize Waits in Java is to use the `FluentWait` class:

```java
Wait<WebDriver> wait =
    new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(2))
        .pollingEvery(Duration.ofMillis(300))
        .ignoring(ElementNotInteractableException.class);

wait.until(
    d -> {
      revealed.sendKeys("Displayed");
      return true;
    });
```

**Python**

```python
errors = [NoSuchElementException, ElementNotInteractableException]
wait = WebDriverWait(driver, timeout=2, poll_frequency=.2, ignored_exceptions=errors)
wait.until(lambda _ : revealed.send_keys("Displayed") or True)
```

**CSharp**

```csharp
WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(2))
{
    PollingInterval = TimeSpan.FromMilliseconds(300),
};
wait.IgnoreExceptionTypes(typeof(ElementNotInteractableException));

wait.Until(d => {
    revealed.SendKeys("Displayed");
    return true;
});
```

**Ruby**

```ruby
errors = [Selenium::WebDriver::Error::NoSuchElementError,
          Selenium::WebDriver::Error::ElementNotInteractableError]
wait = Selenium::WebDriver::Wait.new(timeout: 2,
                                     interval: 0.3,
                                     ignore: errors)

wait.until { revealed.send_keys('Displayed') || true }
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

```kotlin
val wait: Wait<WebDriver> =
    FluentWait(driver)
        .withTimeout(Duration.ofSeconds(2))
        .pollingEvery(Duration.ofMillis(300))
        .ignoring(ElementNotInteractableException::class.java)

wait.until {
    revealed.sendKeys("Displayed")
    true
}
```
