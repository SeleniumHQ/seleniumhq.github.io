# Driver Sessions

URL: https://www.selenium.dev/documentation/webdriver/drivers/
Site index: https://www.selenium.dev/llms.txt

---

Starting and stopping a session is for opening and closing a browser.

## Creating Sessions

Creating a new session corresponds with the W3C command for [New session](https://w3c.github.io/webdriver/#new-session)

The session is created automatically by initializing a new Driver class object.

Each language allows a session to be created with arguments from one of these classes (or equivalent):

* [Options](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md) to describe the kind of session you want; default values are used for local, but
  this is required for remote
* Some form of [HTTP Client configuration](https://www.selenium.dev/documentation/webdriver/drivers/http_client/index.md) (the implementation varies between languages)
* [Listeners](https://www.selenium.dev/documentation/webdriver/support_features/listeners/index.md)

### Local Driver

The primary unique argument for starting a local driver includes information about starting the required driver service
on the local machine.

* [Service](https://www.selenium.dev/documentation/webdriver/drivers/service/index.md) object applies only to local drivers and provides information about the browser
  driver

**Java**

```java
WebDriver driver = new ChromeDriver(chromeOptions);
```

**Python**

```python
driver = webdriver.Chrome(options=options)
```

**CSharp**

```csharp
driver = new ChromeDriver(options);
```

**Ruby**

```ruby
driver = Selenium::WebDriver.for :chrome, options: options
```

**JavaScript**

```javascript
driver = new Builder()
  .forBrowser(Browser.CHROME)
  .setChromeOptions(options)
  .setChromeService(service)
  .build();
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Remote Driver

The primary unique argument for starting a remote driver includes information about where to execute the code.
Read the details in the [Remote Driver Section](https://www.selenium.dev/documentation/webdriver/drivers/remote_webdriver/index.md)

## Quitting Sessions

Quitting a session corresponds to W3C command for [Deleting a Session](https://w3c.github.io/webdriver/#delete-session).

Important note: the `quit` method is different from the `close` method,
and it is recommended to always use `quit` to end the session

**Java**

```java
driver.quit();
```

**Python**

```python
driver.quit()
```

**CSharp**

```csharp
driver.Quit();
```

**Ruby**

```ruby
driver.quit
```

**JavaScript**

```javascript
await driver.quit();
```

**Kotlin**

```kotlin
driver.quit()
```

---

Section pages:

- [Browser Options](https://www.selenium.dev/documentation/webdriver/drivers/options/index.md): These capabilities are shared by all browsers.
- [HTTP Client Configuration](https://www.selenium.dev/documentation/webdriver/drivers/http_client/index.md)
- [Driver Service Class](https://www.selenium.dev/documentation/webdriver/drivers/service/index.md)
- [Remote WebDriver](https://www.selenium.dev/documentation/webdriver/drivers/remote_webdriver/index.md)
