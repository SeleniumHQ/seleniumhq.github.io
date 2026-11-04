# Authentication and sessions

> Getting past a login once, answering HTTP basic authentication prompts, and reusing a signed-in state across tests without driving the login form every time.

URL: https://www.selenium.dev/documentation/guides/authentication/
Site index: https://www.selenium.dev/llms.txt

---

Almost every test starts behind a login. Driving the login form in each test is slow, it makes
every test depend on the login page working, and some forms (two-factor prompts, third-party
identity providers, CAPTCHAs) cannot be automated at all. HTTP basic authentication is a
different problem again: the browser shows a native dialog that WebDriver cannot see.

## Recommended approach

Log in through the user interface in exactly one test, the one that tests the login form.
Everywhere else, establish the session some other way and start the test already signed in.

For cookie-based sessions, navigate to the application's domain, add the session cookie with
WebDriver's cookie API, and reload. The cookie must be added while the browser is on a page in
the domain the cookie belongs to, otherwise the browser rejects it. Where the cookie comes from
is up to you: an API call that logs in, a fixture created on the server, or a value captured
from a single interactive login.

For HTTP basic authentication, use the network module of WebDriver BiDi. Register a handler
for the `authRequired` phase and answer it with the credentials; the browser never shows the
dialog. Embedding credentials in the URL (`https://user:pass@host/`) is blocked by modern
browsers and should not be relied on.

Keep tests independent: each test should set up the state it needs rather than inheriting it
from the one before, and a fresh browser per test is the simplest way to guarantee that.

Add a cookie to the current browsing context:

**Java**

```java
driver.get("https://www.selenium.dev/selenium/web/blank.html");
// Add cookie into current browser context
driver.manage().addCookie(new Cookie("key", "value"));
```

**Python**

```python
driver = webdriver.Chrome()
driver.get("http://www.example.com")

# Adds the cookie into current browser context
driver.add_cookie({"name": "key", "value": "value"})
```

**CSharp**

```csharp
driver.Url="https://www.selenium.dev/selenium/web/blank.html";
// Add cookie into current browser context
driver.Manage().Cookies.AddCookie(new Cookie("key", "value"));
```

**Ruby**

```ruby
driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
# Add cookie into current browser context
driver.manage.add_cookie(name: 'key', value: 'value')
```

**JavaScript**

```javascript
await driver.get('https://www.selenium.dev/selenium/web/blank.html');

// set a cookie on the current domain
await driver.manage().addCookie({ name: 'key', value: 'value' });
```

Answer a basic authentication challenge with BiDi:

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

* [InvalidCookieDomainException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#invalidcookiedomainexception):
  the browser is not on a page in the cookie's domain. Navigate there first, then add the cookie.
* [UnableToSetCookieException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#unabletosetcookieexception):
  the cookie attributes were rejected, for example a `Secure` cookie on an `http://` page.
* [UnhandledAlertException](https://www.selenium.dev/documentation/webdriver/troubleshooting/errors/index.md#unhandledalertexception):
  a basic authentication dialog is open and blocking the session. Handle it with BiDi before navigating.

## Reference

* [Working with cookies](https://www.selenium.dev/documentation/webdriver/interactions/cookies/index.md)
* [BiDi network](https://www.selenium.dev/documentation/webdriver/bidi/w3c/network/index.md): intercepts and the `authRequired` phase
* [Virtual authenticator](https://www.selenium.dev/documentation/webdriver/interactions/virtual_authenticator/index.md): testing WebAuthn and passkeys
* [Two-factor authentication](https://www.selenium.dev/documentation/test_practices/discouraged/two_factor_authentication/index.md) and
  [third-party logins](https://www.selenium.dev/documentation/test_practices/discouraged/gmail_email_and_facebook_logins/index.md): what not to automate
* [Avoid sharing state](https://www.selenium.dev/documentation/test_practices/encouraged/avoid_sharing_state/index.md) and
  [fresh browser per test](https://www.selenium.dev/documentation/test_practices/encouraged/fresh_browser_per_test/index.md)
