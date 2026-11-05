---
title: "Waiting Strategies"
linkTitle: "Waits"
weight: 6
aliases: ["/documentation/en/webdriver/waits/", "/documentation/ja/webdriver/waits/", "/documentation/pt-br/webdriver/waits/", "/documentation/zh-cn/webdriver/waits/",
"/ja/documentation/webdriver/waits/",
"/pt-br/documentation/webdriver/waits/",
"/zh-cn/documentation/webdriver/waits/"
]
---

Perhaps the most common challenge for browser automation is ensuring
that the web application is in a state to execute a particular
Selenium command as desired. The processes often end up in
a _race condition_ where sometimes the browser gets into the right
state first (things work as intended) and sometimes the Selenium code
executes first (things do not work as intended). This is one of the
primary causes of _flaky tests_.

All navigation commands wait for a specific `readyState` value
based on the [page load strategy]({{< ref "drivers/options#pageloadstrategy" >}}) (the 
default value to wait for is `"complete"`) before the driver returns control to the code.
The `readyState` only concerns itself with loading assets defined in the HTML, 
but loaded JavaScript assets often result in changes to the site,
and elements that need to be interacted with may not yet be on the page
when the code is ready to execute the next Selenium command.

Similarly, in a lot of single page applications, elements get dynamically
added to a page or change visibility based on a click.
An element must be both present and
[displayed]({{< ref "elements/information/#is-displayed" >}}) on the page
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
An implicit wait value can be set either with the [timeouts]({{< ref "drivers/options#timeouts" >}})
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

{{< tabpane text=true >}}
  {{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L51" >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/waits/test_waits.py#L28" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L39" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/waits/waits_spec.rb#L28" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/waits/waits.spec.js#L39" >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/waits/WaitsTest.kt#L19" >}}
  {{< /tab >}}
{{< /tabpane >}}

## Explicit waits

_Explicit waits_ are loops added to the code that poll the application 
for a specific condition to evaluate as true before it exits the loop and
continues to the next command in the code. If the condition is not met before a designated timeout value, 
the code will give a timeout error. Since there are many ways for the application not to be in the desired state,
explicit waits are a great choice to specify the exact condition to wait for
in each place it is needed. 
Another nice feature is that, by default, the Selenium Wait class automatically waits for the designated element to exist.

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
This example shows the condition being waited for as a _lambda_. Java also supports
[Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L68-L69" >}}
  {{% /tab %}}
  {{% tab header="Python" %}}
This example shows the condition being waited for as a _lambda_. Python also supports
[Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="/examples/python/tests/waits/test_waits.py#L42-L43" >}}
  {{% /tab %}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L56-L57" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/waits/waits_spec.rb#L42-L43" >}}
  {{< /tab >}}
  {{% tab header="JavaScript" %}}
JavaScript also supports [Expected Conditions]({{< ref "support_features/expected_conditions" >}})
{{< gh-codeblock path="/examples/javascript/test/waits/waits.spec.js#L52" >}}
  {{% /tab %}}
  {{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/waits/WaitsTest.kt#L36-L37" >}}
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane text=true >}}
  {{< tab header="Java" >}}
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L106-L110" >}}
  {{< /tab >}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/waits/test_waits.py#L66-L70" >}}
  {{< /tab >}}
  {{% tab header="CSharp" %}}
.NET does not ship built-in conditions. Pass a lambda to `WebDriverWait.Until()`, as in the
[explicit wait](#explicit-waits) example.
  {{% /tab %}}
  {{% tab header="Ruby" %}}
Ruby does not ship built-in conditions. Pass a block to `Selenium::WebDriver::Wait#until`, as in the
[explicit wait](#explicit-waits) example.
  {{% /tab %}}
  {{< tab header="JavaScript" >}}
{{< gh-codeblock path="/examples/javascript/test/waits/waits.spec.js#L62-L65" >}}
  {{< /tab >}}
  {{% tab header="Kotlin" %}}
Kotlin uses the Java `ExpectedConditions` class listed in the Java tab below.
  {{% /tab %}}
{{< /tabpane >}}

The complete list of built-in conditions for each binding:

{{< tabpane text=true >}}
{{% tab header="Java" %}}
Class `org.openqa.selenium.support.ui.ExpectedConditions`
([API docs](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html)).
Use with `new WebDriverWait(driver, timeout).until(...)`.

| Condition | Waits until |
|-----------|-------------|
| `titleIs(title)` | The page title equals the value |
| `titleContains(title)` | The page title contains the value |
| `urlToBe(url)` | The current URL equals the value |
| `urlContains(fraction)` | The current URL contains the value |
| `urlMatches(regex)` | The current URL matches the regular expression |
| `presenceOfElementLocated(By)` | An element is in the DOM (not necessarily visible); returns it |
| `presenceOfAllElementsLocatedBy(By)` | At least one element is in the DOM; returns them all |
| `presenceOfNestedElementLocatedBy(By or WebElement, By)` | A child element is in the DOM under a parent; returns it |
| `presenceOfNestedElementsLocatedBy(By, By)` | Child elements are in the DOM under a parent; returns them |
| `visibilityOfElementLocated(By)` | An element is in the DOM and displayed; returns it |
| `visibilityOf(WebElement)` | A known element is displayed; returns it |
| `visibilityOfAllElementsLocatedBy(By)` | All matching elements are displayed; returns them |
| `visibilityOfAllElements(WebElement...)` | All given elements are displayed; returns them |
| `visibilityOfNestedElementsLocatedBy(By or WebElement, By)` | Child elements under a parent are displayed; returns them |
| `invisibilityOfElementLocated(By)` | An element is hidden or not in the DOM |
| `invisibilityOf(WebElement)` | A known element is hidden or no longer in the DOM |
| `invisibilityOfAllElements(WebElement...)` | All given elements are hidden |
| `invisibilityOfElementWithText(By, text)` | No displayed element matching the locator has the given text |
| `elementToBeClickable(By or WebElement)` | An element is displayed and enabled; returns it |
| `elementToBeSelected(By or WebElement)` | An element is selected |
| `elementSelectionStateToBe(By or WebElement, selected)` | An element's selected state matches the value |
| `stalenessOf(WebElement)` | An element is no longer attached to the DOM |
| `textToBe(By, text)` | An element's text equals the value |
| `textToBePresentInElement(WebElement, text)` | An element's text contains the value |
| `textToBePresentInElementLocated(By, text)` | A located element's text contains the value |
| `textToBePresentInElementValue(By or WebElement, text)` | An element's `value` contains the value |
| `textMatches(By, Pattern)` | An element's text matches the regular expression |
| `attributeToBe(By or WebElement, attribute, value)` | An element's attribute or CSS property equals the value |
| `attributeContains(By or WebElement, attribute, value)` | An element's attribute or CSS property contains the value |
| `attributeToBeNotEmpty(WebElement, attribute)` | An element's attribute or CSS property is not empty |
| `domAttributeToBe(WebElement, attribute, value)` | An element's DOM attribute equals the value |
| `domPropertyToBe(WebElement, property, value)` | An element's DOM property equals the value |
| `numberOfElementsToBe(By, number)` | The number of matching elements equals the value; returns them |
| `numberOfElementsToBeMoreThan(By, number)` | The number of matching elements is more than the value; returns them |
| `numberOfElementsToBeLessThan(By, number)` | The number of matching elements is less than the value; returns them |
| `numberOfWindowsToBe(number)` | The number of open windows or tabs equals the value |
| `frameToBeAvailableAndSwitchToIt(frame)` | A frame, given as a `By`, name or ID, index, or `WebElement`, is available; then switches to it |
| `alertIsPresent()` | An alert is open; returns it |
| `jsReturnsValue(script)` | The JavaScript returns a value; returns it |
| `javaScriptThrowsNoExceptions(script)` | The JavaScript runs without throwing |
| `and(conditions...)` | All of the conditions are true |
| `or(conditions...)` | Any of the conditions is true |
| `not(condition)` | The condition is false |
| `refreshed(condition)` | The condition is true, retrying it after a stale element error |
{{% /tab %}}
{{% tab header="Python" %}}
Module `selenium.webdriver.support.expected_conditions`
([API docs](https://www.selenium.dev/selenium/docs/api/py/webdriver_support/selenium.webdriver.support.expected_conditions.html)),
usually imported as `EC`.
Use with `WebDriverWait(driver, timeout).until(...)`.
Locators are tuples, such as `(By.ID, "message")`.

| Condition | Waits until |
|-----------|-------------|
| `title_is(title)` | The page title equals the value |
| `title_contains(title)` | The page title contains the value |
| `url_to_be(url)` | The current URL equals the value |
| `url_contains(url)` | The current URL contains the value |
| `url_matches(pattern)` | The current URL matches the regular expression |
| `url_changes(url)` | The current URL is different from the value |
| `presence_of_element_located(locator)` | An element is in the DOM (not necessarily visible); returns it |
| `presence_of_all_elements_located(locator)` | At least one element is in the DOM; returns them all |
| `visibility_of_element_located(locator)` | An element is in the DOM and displayed; returns it |
| `visibility_of(element)` | A known element is displayed; returns it |
| `visibility_of_any_elements_located(locator)` | At least one matching element is displayed; returns the displayed ones |
| `visibility_of_all_elements_located(locator)` | All matching elements are displayed; returns them |
| `invisibility_of_element_located(locator or element)` | An element is hidden or not in the DOM |
| `invisibility_of_element(element or locator)` | An element is hidden or not in the DOM |
| `element_to_be_clickable(locator or element)` | An element is displayed and enabled; returns it |
| `element_to_be_selected(element)` | A known element is selected |
| `element_located_to_be_selected(locator)` | A located element is selected |
| `element_selection_state_to_be(element, is_selected)` | A known element's selected state matches the value |
| `element_located_selection_state_to_be(locator, is_selected)` | A located element's selected state matches the value |
| `staleness_of(element)` | An element is no longer attached to the DOM |
| `text_to_be_present_in_element(locator, text)` | An element's text contains the value |
| `text_to_be_present_in_element_value(locator, text)` | An element's `value` contains the value |
| `text_to_be_present_in_element_attribute(locator, attribute, text)` | An element's attribute contains the value |
| `element_attribute_to_include(locator, attribute)` | An element has the attribute |
| `number_of_windows_to_be(num_windows)` | The number of open windows or tabs equals the value |
| `new_window_is_opened(current_handles)` | A window or tab has opened since the handles were read |
| `frame_to_be_available_and_switch_to_it(frame)` | A frame, given as a locator, name or ID, or element, is available; then switches to it |
| `alert_is_present()` | An alert is open; returns it |
| `all_of(*conditions)` | All of the conditions are true |
| `any_of(*conditions)` | Any of the conditions is true |
| `none_of(*conditions)` | None of the conditions is true |
{{% /tab %}}
{{% tab header="CSharp" %}}
.NET does not ship built-in conditions.
The `ExpectedConditions` class was removed from the bindings in Selenium 4.
Pass a lambda to `WebDriverWait.Until()` that returns a non-null value or `true`
when the condition is met.
{{% /tab %}}
{{% tab header="Ruby" %}}
Ruby does not ship built-in conditions.
Pass a block to `Selenium::WebDriver::Wait#until` that returns a truthy value
when the condition is met.
{{% /tab %}}
{{% tab header="JavaScript" %}}
Module `until` from `selenium-webdriver`
([API docs](https://www.selenium.dev/selenium/docs/api/javascript/index.html)).
Use with `await driver.wait(until.condition(...), timeout)`.
Conditions that check an element resolve to that element once they are met.

| Condition | Waits until |
|-----------|-------------|
| `titleIs(title)` | The page title equals the value |
| `titleContains(substr)` | The page title contains the value |
| `titleMatches(regex)` | The page title matches the regular expression |
| `urlIs(url)` | The current URL equals the value |
| `urlContains(substrUrl)` | The current URL contains the value |
| `urlMatches(regex)` | The current URL matches the regular expression |
| `elementLocated(locator)` | An element is in the DOM; returns it |
| `elementsLocated(locator)` | At least one element is in the DOM; returns them all |
| `elementIsVisible(element)` | An element is displayed |
| `elementIsNotVisible(element)` | An element is not displayed |
| `elementIsEnabled(element)` | An element is enabled |
| `elementIsDisabled(element)` | An element is disabled |
| `elementIsSelected(element)` | An element is selected |
| `elementIsNotSelected(element)` | An element is not selected |
| `elementTextIs(element, text)` | An element's text equals the value |
| `elementTextContains(element, substr)` | An element's text contains the value |
| `elementTextMatches(element, regex)` | An element's text matches the regular expression |
| `stalenessOf(element)` | An element is no longer attached to the DOM |
| `ableToSwitchToFrame(frame)` | A frame is available, then switches to it |
| `alertIsPresent()` | An alert is open; returns it |
{{% /tab %}}
{{% tab header="Kotlin" %}}
Kotlin uses the Java `ExpectedConditions` class. See the Java tab.
{{% /tab %}}
{{< /tabpane >}}

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

{{< tabpane text=true >}}
  {{% tab header="Java" %}}
The easiest way to customize Waits in Java is to use the `FluentWait` class:
{{< gh-codeblock path="/examples/java/src/test/java/dev/selenium/waits/WaitsTest.java#L83-L93" >}}
  {{% /tab %}}
  {{< tab header="Python" >}}
{{< gh-codeblock path="/examples/python/tests/waits/test_waits.py#L54-L56" >}}
  {{< /tab >}}
  {{< tab header="CSharp" >}}
{{< gh-codeblock path="/examples/dotnet/SeleniumDocs/Waits/WaitsTest.cs#L70-L79" >}}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
{{< gh-codeblock path="/examples/ruby/spec/waits/waits_spec.rb#L54-L60" >}}
  {{< /tab >}}
  {{< tab header="JavaScript" >}}
{{< badge-code >}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
{{< gh-codeblock path="examples/kotlin/src/test/kotlin/dev/selenium/waits/WaitsTest.kt#L51-L60" >}}
  {{< /tab >}}
{{< /tabpane >}}
