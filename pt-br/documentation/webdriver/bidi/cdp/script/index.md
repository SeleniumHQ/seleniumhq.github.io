# Chrome DevTools Script Features

> Script features using CDP.

URL: https://www.selenium.dev/pt-br/documentation/webdriver/bidi/cdp/script/

---

> <p class="lead">
>    <i class="fas fa-language d-4"></i>
>    Page being translated from
>    English to Japanese. Do you speak Japanese? Help us to translate
>    it by sending us pull requests!
> 

> While Selenium 4 provides direct access to the Chrome DevTools Protocol, these
> methods will eventually be removed when WebDriver BiDi implemented.

## Script Pinning

**Java**

```java
ScriptKey key = ((JavascriptExecutor) driver).pin("return arguments;");
List<Object> arguments =
    (List<Object>) ((JavascriptExecutor) driver).executeScript(key, 1, true, element);
```

**Python**

_Not available in this language._

**CSharp**

```csharp
var key = await new JavaScriptEngine(driver).PinScript("return arguments;");
var arguments = ((WebDriver)driver).ExecuteScript(key, 1, true, element);
```

**Ruby**

```ruby
key = driver.pin_script('return arguments;')
arguments = driver.execute_script(key, 1, true, element)
```

**JavaScript**

_Not available in this language._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._

## DOM Mutation Handlers

**Java**

```java
@Test
```

**Python**

```python
async with driver.bidi_connection() as session:
    async with Log(driver, session).mutation_events() as event:
```

**CSharp**

```csharp
using IJavaScriptEngine monitor = new JavaScriptEngine(driver);
monitor.DomMutated += (_, e) =>
{
    var locator = By.CssSelector($"*[data-__webdriver_id='{e.AttributeData.TargetId}']");
    mutations.Add(driver.FindElement(locator));
};
await monitor.StartEventMonitoring();
await monitor.EnableDomMutationMonitoring();
```

**Ruby**

```ruby
driver.on_log_event(:mutation) { |mutation| mutations << mutation.element }
```

**JavaScript**

_Not available in this language._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/pt-br/documentation/about/contributing/code_examples/index.md#creating-examples))._
