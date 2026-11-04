# Waiting Strategies

URL: https://www.selenium.dev/documentation/webdriver/waits/
Site index: https://www.selenium.dev/llms.txt

---

Perhaps the most common challenge for browser automation is ensuring
that the web application is in a state to execute a particular
Selenium command as desired. The processes often end up in
a _race condition_ where sometimes the browser gets into the right
state first (things work as intended) and sometimes the Selenium code
executes first (things do not work as intended). This is one of the
primary causes of _flaky tests_.

All navigation commands wait for a specific `readyState` value
based on the [page load strategy](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md#pageloadstrategy) (the 
default value to wait for is `"complete"`) before the driver returns control to the code.
The `readyState` only concerns itself with loading assets defined in the HTML, 
but loaded JavaScript assets often result in changes to the site,
and elements that need to be interacted with may not yet be on the page
when the code is ready to execute the next Selenium command.

Similarly, in a lot of single page applications, elements get dynamically
added to a page or change visibility based on a click.
An element must be both present and
[displayed](https://www.selenium.dev/documentation/webdriver/elements/information/index.md#is-displayed) on the page
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
An implicit wait value can be set either with the [timeouts](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md#timeouts)
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
[Expected Conditions](https://www.selenium.dev/documentation/webdriver/support_features/expected_conditions/index.md)

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
wait.until(d -> revealed.isDisplayed());
```

**Python**

This example shows the condition being waited for as a _lambda_. Python also supports
[Expected Conditions](https://www.selenium.dev/documentation/webdriver/support_features/expected_conditions/index.md)

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

JavaScript also supports [Expected Conditions](https://www.selenium.dev/documentation/webdriver/support_features/expected_conditions/index.md)

```javascript
await driver.wait(until.elementIsVisible(revealed), 2000);
```

**Kotlin**

```kotlin
val wait =  WebDriverWait(driver, Duration.ofSeconds(2))
wait.until { revealed.isDisplayed }
```

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

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

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
