# Waiting for things to happen

> Synchronising your code with the page using explicit waits and expected conditions, and why a fixed sleep is the wrong tool.

URL: https://www.selenium.dev/documentation/guides/waiting/
Site index: https://www.selenium.dev/llms.txt

---

Your script clicks a button, and the next line fails because the element it needs has not
appeared yet. Run it again and it passes. Modern applications update the page asynchronously,
so the browser and your code are in a race, and the loser is a flaky test.

## Recommended approach

Use an _explicit wait_: a loop that polls the application for a specific condition and
continues as soon as that condition is true, or raises a timeout error if it never is. You
decide what to wait for at each point where synchronisation matters, so the wait is exactly as
long as it needs to be and no longer.

Most bindings ship a library of ready-made _expected conditions_ (element visible, title
contains, alert present, and so on) that you can pass to the wait instead of writing your own
lambda. Each of them is a function that is called repeatedly until it returns a truthy value.

A fixed sleep is the first thing many people reach for, and it is nearly always wrong. It is
either too short, and the test fails on a slow machine, or too long, and every test in the
suite pays the full price every time. Likewise, do not combine an implicit wait with explicit
waits: the two timeouts add up in ways that are hard to predict.

This example waits for an element that is revealed a couple of seconds after a click:

**Java**

```java
Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
wait.until(d -> revealed.isDisplayed());
```

**Python**

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

```javascript
await driver.wait(until.elementIsVisible(revealed), 2000);
```

**Kotlin**

```kotlin
val wait =  WebDriverWait(driver, Duration.ofSeconds(2))
wait.until { revealed.isDisplayed }
```

The same wait expressed with a built-in expected condition:

**Python**

```python
wait = WebDriverWait(driver, timeout=2)
wait.until(EC.visibility_of_element_located((By.ID, "revealed")))
```

## Common errors

* [NoSuchElementException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#nosuchelementexception):
  the element was looked up before it existed. Wait for it to be present or visible first.
* [StaleElementReferenceException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#staleelementreferenceexception):
  the element was found, then the page re-rendered it. Locate it again, inside the wait if necessary.
* [TimeoutException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#timeoutexception):
  the condition never became true. Check the condition and the locator before raising the timeout.
* [ElementClickInterceptedException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#elementclickinterceptedexception):
  something (often an overlay or spinner) is still covering the element. Wait for it to disappear.

## Reference

* [Waiting strategies](https://www.selenium.dev/documentation/webdriver/waits/index.md): implicit and explicit waits, and how to customise them
* [Expected conditions](https://www.selenium.dev/documentation/webdriver/support_features/expected_conditions/index.md)
* [Page load strategy](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md#pageloadstrategy): what navigation already waits for
* [Timeouts](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md#timeouts): the session-level timeout capabilities
