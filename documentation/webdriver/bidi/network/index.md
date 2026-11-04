# WebDriver BiDi Network Features

> These features are related to networking, and are made available via a "network" namespace.

URL: https://www.selenium.dev/documentation/webdriver/bidi/network/
Site index: https://www.selenium.dev/llms.txt

---

The implementation of these features is being tracked here: [#13993](https://github.com/SeleniumHQ/selenium/issues/13993)

Remember that to use WebDriver BiDi, you must enable it in Options.
For more details, see [Enabling BiDi](https://www.selenium.dev/documentation/webdriver/bidi/index.md)

## Authentication Handlers

Authentication handlers enable you to intercept authentication requests that occur during a network interaction. 
These handlers are useful for automating scenarios involving authentication prompts, such as Basic Auth or Digest Auth. 
They allow you to programmatically provide credentials or modify the authentication flow.

### Add Handler

**Java**

**Python**

**CSharp**

**Ruby**

```ruby
let(:driver) { start_bidi_session }
let(:wait) { Selenium::WebDriver::Wait.new(timeout: 2) }

it 'adds an auth handler', skip: 'Do not execute BiDi test' do
  driver.network.add_authentication_handler('test', 'test')
```

**JavaScript**

**Kotlin**

## Request Handlers

Request handlers allow you to intercept and manipulate outgoing network requests before they are sent to the server. 
This can be used to modify request headers, change the request body, or block specific requests. 
Request handlers are essential for testing and debugging scenarios where you need control over outgoing traffic.

### Add Handler

**Java**

**Python**

**CSharp**

**Ruby**

**JavaScript**

**Kotlin**

## Response Handlers

Response handlers enable you to intercept and manipulate incoming responses from the server. 
They are particularly useful for testing scenarios involving response data, such as verifying or modifying response headers, status codes, or content before it reaches the browser.

**Java**

**Python**

**CSharp**

**Ruby**

**JavaScript**

**Kotlin**

## Remove Handler

**Java**

**Python**

**CSharp**

**Ruby**

**JavaScript**

**Kotlin**

## Clear Handlers

**Java**

**Python**

**CSharp**

**Ruby**

**JavaScript**

**Kotlin**
