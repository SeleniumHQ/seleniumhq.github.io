# Network

URL: https://www.selenium.dev/ja/documentation/webdriver/bidi/w3c/network/

---

> <p class="lead">
>    <i class="fas fa-language d-4"></i>
>    Page being translated from
>    English to Japanese. Do you speak Japanese? Help us to translate
>    it by sending us pull requests!
> 

# Commands

This section contains the APIs related to network commands.

### Add network intercept

**Java**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```java
String intercept =
        network.addIntercept(new AddInterceptParameters(InterceptPhase.BEFORE_REQUEST_SENT));
Assertions.assertNotNull(intercept);
```

**Python**

_Requires [Selenium v4.32](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.32.0) or later._

```python
@pytest.mark.driver_type("bidi")
def test_add_intercept(driver):
    # _add_intercept is currently the available Python API for BiDi network intercepts.
    # This will be updated when a public API is stabilized.
    intercept = driver.network._add_intercept()
    assert intercept is not None
    driver.network._remove_intercept(intercept["intercept"])
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```javascript
assert.notEqual(intercept, null)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Remove network intercept

**Java**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```java
                network.addIntercept(new AddInterceptParameters(InterceptPhase.BEFORE_REQUEST_SENT));
        Assertions.assertNotNull(intercept);
        network.removeIntercept(intercept);
    }
}
```

**Python**

_Requires [Selenium v4.32](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.32.0) or later._

```python
@pytest.mark.driver_type("bidi")
def test_remove_intercept(driver):
    # _add_intercept/_remove_intercept are currently the available Python APIs.
    # These will be updated when a public API is stabilized.
    intercept = driver.network._add_intercept()
    driver.network._remove_intercept(intercept["intercept"])
    assert driver.network.intercepts == []
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```javascript
const intercept = await network.addIntercept(new AddInterceptParameters(InterceptPhase.BEFORE_REQUEST_SENT))
assert.notEqual(intercept, null)
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Continue request blocked at authRequired phase with credentials

**Java**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```java
        responseDetails ->
                network.continueWithAuth(
                        responseDetails.getRequest().getRequestId(),
                        new UsernameAndPassword("admin", "admin")));
driver.get("https://the-internet.herokuapp.com/basic_auth");
String successMessage = "Congratulations! You must have the proper credentials.";
WebElement elementMessage = driver.findElement(By.tagName("p"));
Assertions.assertEquals(successMessage, elementMessage.getText());
```

**Python**

_Requires [Selenium v4.46](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.46.0) or later._

```python
@pytest.mark.driver_type("firefox_bidi")
def test_continue_with_auth_credentials(driver):
    callback_id = driver.network.add_auth_handler("admin", "admin")

    try:
        driver.get("https://the-internet.herokuapp.com/basic_auth")
        success_message = "Congratulations! You must have the proper credentials."
        assert driver.find_element(By.TAG_NAME, "p").text == success_message
    finally:
        driver.network.remove_auth_handler(callback_id)
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```javascript

await network.authRequired(async (event) => {
  await network.continueWithAuth(event.request.request, 'admin','admin')
})
await driver.get('https://the-internet.herokuapp.com/basic_auth')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Continue request blocked at authRequired phase without credentials

**Java**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```java
                    // Does not handle the alert
                    network.continueWithAuthNoCredentials(responseDetails.getRequest().getRequestId()));
    driver.get("https://the-internet.herokuapp.com/basic_auth");
    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    alert.dismiss();
    Assertions.assertTrue(driver.getPageSource().contains("Not authorized"));
}
```

**Python**

_Requires [Selenium v4.46](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.46.0) or later._

```python
@pytest.mark.driver_type("firefox_bidi")
def test_continue_without_auth_credentials(driver):
    from selenium.webdriver.support import expected_conditions as EC
    from selenium.webdriver.support.wait import WebDriverWait

    def callback(request):
        pass  # Neither provide_credentials() nor cancel(): falls back to the browser's default handling.

    handler_id = driver.network.add_authentication_handler(callback)

    try:
        driver.get("https://the-internet.herokuapp.com/basic_auth")
        alert = WebDriverWait(driver, 5).until(EC.alert_is_present())
        alert.dismiss()
        WebDriverWait(driver, 5).until(lambda d: "Not authorized" in d.page_source)
    finally:
        driver.network.remove_authentication_handler(handler_id)
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```javascript

await network.authRequired(async (event) => {
  await network.continueWithAuthNoCredentials(event.request.request)
})
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Cancel request blocked at authRequired phase

**Java**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```java
                        network.cancelAuth(responseDetails.getRequest().getRequestId()));
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        Assertions.assertTrue(driver.getPageSource().contains("Not authorized"));
    }
}

@Test
```

**Python**

_Requires [Selenium v4.46](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.46.0) or later._

```python
@pytest.mark.driver_type("firefox_bidi")
def test_cancel_auth(driver):
    def callback(request):
        request.cancel()

    handler_id = driver.network.add_authentication_handler(callback)

    try:
        driver.get("https://the-internet.herokuapp.com/basic_auth")
        assert "Not authorized" in driver.page_source
    finally:
        driver.network.remove_authentication_handler(handler_id)
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```javascript

await network.authRequired(async (event) => {
  await network.cancelAuth(event.request.request)
})
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Fail request

**Java**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```java
            }
    }
}
```

**Python**

_Requires [Selenium v4.46](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.46.0) or later._

```python
@pytest.mark.driver_type("bidi")
def test_fail_request(driver):
    from selenium.webdriver.common.bidi.browsing_context import ReadinessState
    from selenium.webdriver.common.bidi.network import Request

    def block_request(request: Request):
        request.fail()

    driver.network.add_request_handler(["**/blank.html"], block_request)

    try:
        with pytest.raises(WebDriverException):
            driver.browsing_context.navigate(
                context=driver.current_window_handle,
                url="https://www.selenium.dev/selenium/web/blank.html",
                wait=ReadinessState.COMPLETE,
            )
    finally:
        driver.network.clear_request_handlers()
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

## Events

This section contains the APIs related to network events.

### Before Request Sent

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (Network network = new Network(driver)) {
    CompletableFuture<BeforeRequestSent> future = new CompletableFuture<>();
    network.onBeforeRequestSent(future::complete);
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

    BeforeRequestSent requestSent = future.get(5, TimeUnit.SECONDS);
```

**Python**

_Requires [Selenium v4.32](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.32.0) or later._

```python
@pytest.mark.driver_type("bidi")
def test_add_and_remove_request_handler(driver):
    from selenium.webdriver.common.bidi.network import Request

    requests = []

    def callback(request: Request):
        requests.append(request)

    callback_id = driver.network.add_request_handler("before_request", callback)
    assert callback_id is not None

    driver.network.remove_request_handler("before_request", callback_id)

    driver.get("https://www.selenium.dev/selenium/web/blank.html")
    assert not requests
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```javascript
let beforeRequestEvent = []
const network = await Network(driver)
await network.beforeRequestSent(function (event) {
  beforeRequestEvent.push(event)
})

await driver.get('https://www.selenium.dev/selenium/web/blank.html')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Response Started

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (Network network = new Network(driver)) {
    CompletableFuture<ResponseDetails> future = new CompletableFuture<>();
    network.onResponseStarted(future::complete);
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

    ResponseDetails response = future.get(5, TimeUnit.SECONDS);
    String windowHandle = driver.getWindowHandle();
```

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```javascript
let onResponseStarted = []
const network = await Network(driver)
await network.responseStarted(function (event) {
  onResponseStarted.push(event)
})

await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Response Completed

**Java**

_Requires [Selenium v4.15](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.15.0) or later._

```java
try (Network network = new Network(driver)) {
    CompletableFuture<ResponseDetails> future = new CompletableFuture<>();
    network.onResponseCompleted(future::complete);
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

    ResponseDetails response = future.get(5, TimeUnit.SECONDS);
    String windowHandle = driver.getWindowHandle();
```

**Python**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_Requires [Selenium v4.18](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.18.0) or later._

```javascript
let onResponseCompleted = []
const network = await Network(driver)
await network.responseCompleted(function (event) {
  onResponseCompleted.push(event)
})

await driver.get('https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html')
```

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

### Auth Required

**Java**

_Requires [Selenium v4.17](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.17.0) or later._

```java
try (Network network = new Network(driver)) {
    CompletableFuture<ResponseDetails> future = new CompletableFuture<>();
    network.onAuthRequired(future::complete);
    driver.get("https://the-internet.herokuapp.com/basic_auth");

    ResponseDetails response = future.get(5, TimeUnit.SECONDS);
```

**Python**

_Requires [Selenium v4.46](https://github.com/SeleniumHQ/selenium/releases/tag/selenium-4.46.0) or later._

```python
@pytest.mark.driver_type("firefox_bidi")
def test_auth_required_event(driver):
    from selenium.webdriver.common.bidi.network import AuthenticationRequest

    challenges = []

    def callback(request: AuthenticationRequest):
        challenges.append(request)
        request.provide_credentials("admin", "admin")

    handler_id = driver.network.add_authentication_handler(callback)

    try:
        driver.get("https://the-internet.herokuapp.com/basic_auth")
        assert len(challenges) == 1
        assert challenges[0].scheme == "Basic"
        assert challenges[0].realm == "Restricted Area"
    finally:
        driver.network.remove_authentication_handler(handler_id)
```

**Ruby**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/ja/documentation/about/contributing/code_examples/index.md#creating-examples))._
