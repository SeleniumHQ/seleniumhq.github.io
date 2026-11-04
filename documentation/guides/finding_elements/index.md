# Finding elements reliably

> Choosing a locator strategy that survives the next release of the application, and avoiding the ones that do not.

URL: https://www.selenium.dev/documentation/guides/finding_elements/
Site index: https://www.selenium.dev/llms.txt

---

A locator copied out of the browser's developer tools works today and breaks the first time a
designer moves a `div`. Tests then fail for reasons that have nothing to do with the behaviour
they were written to check, and the suite stops being trusted.

## Recommended approach

Prefer locators that describe what an element _is_ over locators that describe where it
happens to sit in the document, roughly in this order:

1. **id**, when the page has stable, unique ids. This is the fastest and most readable choice.
2. **name**, for form fields, which tend to keep their names because the server depends on them.
3. **CSS selector**, for everything else. A short selector anchored on an id, a data attribute,
   or a meaningful class is both fast and expressive.
4. **Relative locators**, when the target has no stable attributes of its own but a nearby
   element does: find the input _below_ a given label, or the button _to the right of_ a field.

Avoid absolute XPath (`/html/body/div[3]/...`). It encodes the entire structure of the page, so
any change above the element breaks it, and it is slow in some browsers. XPath itself is fine
when you need it, for example to match on text content, but keep the expression short and
anchored.

If the application does not expose stable attributes, ask for them. A `data-test` attribute
costs the developers nothing and removes an entire class of failures.

Locate the first matching element:

**Java**

```java
driver.get(LOCATORS_PAGE);
WebElement firstInput = driver.findElement(By.className("information"));
```

**Python**

```python
driver.get(LOCATORS_PAGE)
first_input = driver.find_element(By.CLASS_NAME, "information")
```

**CSharp**

```csharp
driver.Url = LocatorsPage;
IWebElement firstInput = driver.FindElement(By.ClassName("information"));
```

**Ruby**

```ruby
driver.navigate.to locators_page
first_input = driver.find_element(class: 'information')
```

**JavaScript**

```javascript
await driver.get(LOCATORS_PAGE);
const firstInput = await driver.findElement(By.className('information'));
```

**Kotlin**

```kotlin
driver.get(locatorsPage)
val firstInput = driver.findElement(By.className("information"))
```

Locate by id, the preferred strategy when one is available:

**Java**

```java
WebElement element = driver.findElement(By.id("lname"));
```

**Python**

```python
element = driver.find_element(By.ID, "lname")
```

**Ruby**

```ruby
driver.find_element(id: 'lname')
```

**JavaScript**

```javascript
const element = await driver.findElement(By.id('lname'));
```

Locate relative to another element:

**Ruby**

```ruby
driver.find_element({relative: {tag_name: 'input', above: {id: 'password'}}})
```

## Common errors

* [NoSuchElementException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#nosuchelementexception):
  nothing matched. Check the locator, check that you are in the right frame, and check that the
  element exists yet (see [Waiting](https://www.selenium.dev/documentation/guides/waiting/index.md)).
* [InvalidSelectorException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#invalidselectorexception):
  the CSS or XPath expression is malformed, or an XPath expression returns something that is not an element.
* [StaleElementReferenceException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#staleelementreferenceexception):
  you held on to an element across a page update. Locate it again rather than caching it.
* [NoSuchShadowRootException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#nosuchshadowrootexception):
  the element lives inside a shadow DOM; search from the shadow root instead of the document.

## Reference

* [Locator strategies](https://www.selenium.dev/documentation/webdriver/elements/locators/index.md): every strategy, including relative locators
* [Finding elements](https://www.selenium.dev/documentation/webdriver/elements/finders/index.md): first match, all matches, nested searches, shadow DOM
* [Tips on working with locators](https://www.selenium.dev/documentation/test_practices/encouraged/locators/index.md)
* [Page object models](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/index.md): keeping locators in one place
