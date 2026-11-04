# Network interception and mocking

> Watching the requests a page makes, blocking or failing some of them, and answering authentication challenges, using the W3C WebDriver BiDi network module.

URL: https://www.selenium.dev/documentation/guides/network/
Site index: https://www.selenium.dev/llms.txt

---

You want to assert that the page called an API, fail a request to see how the application
copes, block the analytics beacon that slows every test down, or supply credentials to a
basic authentication challenge. Plain WebDriver only sees the DOM; it has no view of the
traffic behind it.

## Recommended approach

Use the network module of WebDriver BiDi. It is part of the W3C standard, it works in every
browser that implements BiDi, and it is the Selenium project's supported route for anything
that needs to observe or change network traffic.

The module has two halves. _Events_ (`beforeRequestSent`, `responseStarted`,
`responseCompleted`, `authRequired`) let you subscribe to traffic and record or assert on it.
_Intercepts_ pause requests at a chosen phase so your handler can continue them, fail them,
or, at the `authRequired` phase, continue them with a username and password. Enable BiDi in
the browser options when starting the session; the reference page shows the capability for
each binding.

Keep interception narrow. Match the URL patterns you care about, remove the intercept when
the test is done, and prefer failing or stubbing an external dependency over letting the test
talk to a live third-party service.

The Chrome DevTools Protocol integration that Selenium offered before BiDi existed is
Chromium-only, tied to specific browser versions, and being phased out. Do not start new work
on it.

Subscribe to requests as they are sent:

**Java**

```java
try (Network network = new Network(driver)) {
    CompletableFuture<BeforeRequestSent> future = new CompletableFuture<>();
    network.onBeforeRequestSent(future::complete);
    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

    BeforeRequestSent requestSent = future.get(5, TimeUnit.SECONDS);
```

**Python**

```python
def callback(request: Request):
    requests.append(request)

callback_id = driver.network.add_request_handler("before_request", callback)
```

**JavaScript**

```javascript
const network = await Network(driver)
await network.beforeRequestSent(function (event) {
  beforeRequestEvent.push(event)
})
```

Intercept a request and fail it:

**Java**

```java
network.addIntercept(new AddInterceptParameters(InterceptPhase.BEFORE_REQUEST_SENT));
network.onBeforeRequestSent(
        responseDetails -> network.failRequest(responseDetails.getRequest().getRequestId()));
```

**Python**

```python
def block_request(request: Request):
    request.fail()

driver.network.add_request_handler(["**/blank.html"], block_request)
```

Answer a basic authentication challenge:

**Java**

```java
network.addIntercept(new AddInterceptParameters(InterceptPhase.AUTH_REQUIRED));
network.onAuthRequired(
        responseDetails ->
                network.continueWithAuth(
                        responseDetails.getRequest().getRequestId(),
                        new UsernameAndPassword("admin", "admin")));
```

**Python**

```python
callback_id = driver.network.add_auth_handler("admin", "admin")
```

**JavaScript**

```javascript
await network.authRequired(async (event) => {
  await network.continueWithAuth(event.request.request, 'admin','admin')
})
```

## Common errors

* [TimeoutException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#timeoutexception):
  an intercepted request was never continued or failed, so navigation hung until the page load
  timeout. Every intercepted request needs a handler that resolves it.
* [UnsupportedCommandException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#unsupportedcommandexception):
  BiDi was not enabled for the session, or the browser does not implement the command yet.

## Reference

* [BiDi network](https://www.selenium.dev/documentation/webdriver/bidi/w3c/network/index.md): all commands and events
* [Enabling BiDi](https://www.selenium.dev/documentation/webdriver/bidi/index.md#enabling-bidi-in-selenium)
* [Mock external services](https://www.selenium.dev/documentation/test_practices/encouraged/mock_external_services/index.md)
* [HTTP response codes](https://www.selenium.dev/documentation/test_practices/discouraged/http_response_codes/index.md): why WebDriver does not expose them directly
