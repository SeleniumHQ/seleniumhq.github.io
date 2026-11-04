# Browser interactions

URL: https://www.selenium.dev/documentation/webdriver/interactions/
Site index: https://www.selenium.dev/llms.txt

---

## Get browser information

### Get title

You can read the current page title from the browser:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
String title = driver.getTitle();
```

**Python**

```python
title = driver.title
```

**CSharp**

```csharp
String title = driver.Title;
```

**Ruby**

```ruby
it 'gets the current title' do
```

**JavaScript**

```javascript
let title = await driver.getTitle();
```

**Kotlin**

```kotlin
driver.title
```

### Get current URL

You can read the current URL from the browser's address bar using:

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
String url = driver.getCurrentUrl();
```

**Python**

```python
url = driver.current_url
```

**CSharp**

```csharp
String url = driver.Url;
```

**Ruby**

```ruby
it 'gets the current url' do
```

**JavaScript**

```javascript
let currentUrl = await driver.getCurrentUrl();
```

**Kotlin**

```kotlin
driver.currentUrl
```

---

Section pages:

- [Browser navigation](https://www.selenium.dev/documentation/webdriver/interactions/navigation/index.md)
- [JavaScript alerts, prompts and confirmations](https://www.selenium.dev/documentation/webdriver/interactions/alerts/index.md)
- [Working with cookies](https://www.selenium.dev/documentation/webdriver/interactions/cookies/index.md)
- [Working with IFrames and frames](https://www.selenium.dev/documentation/webdriver/interactions/frames/index.md)
- [Print Page](https://www.selenium.dev/documentation/webdriver/interactions/print_page/index.md)
- [Working with windows and tabs](https://www.selenium.dev/documentation/webdriver/interactions/windows/index.md)
- [Virtual Authenticator](https://www.selenium.dev/documentation/webdriver/interactions/virtual_authenticator/index.md): A representation of the Web Authenticator model.
