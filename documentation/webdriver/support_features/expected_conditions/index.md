# Waiting with Expected Conditions

> These are classes used to describe what needs to be waited for.

URL: https://www.selenium.dev/documentation/webdriver/support_features/expected_conditions/
Site index: https://www.selenium.dev/llms.txt

---

Expected Conditions are used with [Explicit Waits](https://www.selenium.dev/documentation/webdriver/waits/index.md#explicit-waits).
Instead of defining the block of code to be executed with a _lambda_, an expected
conditions method can be created to represent common things that get waited on. Some
methods take locators as arguments, others take elements as arguments.

These methods can include conditions such as:

* element exists
* element is stale
* element is visible
* text is visible
* title contains specified value

**Java**

[Expected Conditions Documentation](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html)
_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Python**

```python
wait = WebDriverWait(driver, timeout=2)
wait.until(EC.visibility_of_element_located((By.ID, "revealed")))
```

**CSharp**

.NET stopped supporting Expected Conditions in Selenium 4 to minimize maintenance hassle and redundancy.

**Ruby**

Ruby makes frequent use of blocks, procs and lambdas and does not need Expected Conditions classes

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._
