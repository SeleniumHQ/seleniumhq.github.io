# WebDriver BiDi

> WebDriver BiDi is the W3C standard bidirectional protocol for browser automation, created by the Selenium project together with the browser vendors. It adds a WebSocket connection to WebDriver so scripts can stream and react to browser events — network requests, console messages, JavaScript errors — and it is the cross-browser replacement for the Chrome DevTools Protocol.

URL: https://www.selenium.dev/documentation/webdriver/bidi/
Site index: https://www.selenium.dev/llms.txt

---

BiDirectional means that communication is happening in two directions simultaneously.
The traditional WebDriver model involves strict request/response commands which only allows for communication to
happen in one direction at any given time. In most cases this is what you want; it ensures that the browser is
doing the expected things in the right order, but there are a number of interesting things that can be done with 
asynchronous interactions.

This functionality is currently available in a limited fashion with the [Chrome DevTools Protocol] (CDP), 
but to address some of its drawbacks, the Selenium team, along with the major
browser vendors, have worked to create the new [WebDriver BiDi Protocol](https://w3c.github.io/webdriver-bidi/).
This specification aims to create a stable, cross-browser API that leverages bidirectional
communication for enhanced browser automation and testing functionality, 
including streaming events from the user agent to the controlling software via WebSockets.
Users will be able to listen for and record or manipulate events as they happen during the course of a Selenium session.

### Enabling BiDi in Selenium

In order to use WebDriver BiDi, setting the capability in the browser options will enable the required functionality:

**Java**

```java
options.setCapability("webSocketUrl", true);
```

**Python**

```python
options.enable_bidi = True
```

**CSharp**

```csharp
UseWebSocketUrl = true,
```

**Ruby**

```ruby
options.web_socket_url = true
```

**JavaScript**

```javascript
Options().enableBidi();
```

**Kotlin**

```kotlin
options.setCapability("webSocketUrl", true);
```

This enables the WebSocket connection for bidirectional communication, 
unlocking the full potential of the WebDriver BiDi protocol.

Note that Selenium is updating its entire implementation from WebDriver Classic to WebDriver BiDi (while
maintaining backwards compatibility as much as possible), but this section of documentation focuses on the new
functionality that bidirectional communication allows. 
The low-level BiDi domains will be accessible in the code to the end user, but the goal is to provide
high-level APIs that are straightforward methods of real-world use cases. As such, the low-level
components will not be documented, and this section will focus only on the user-friendly
features that we encourage users to take advantage of.

If there is additional functionality you'd like to see, please raise a
[feature request](https://github.com/SeleniumHQ/selenium/issues/new?assignees=&labels=&template=feature.md).

---

Section pages:

- [WebDriver BiDi Logging Features](https://www.selenium.dev/documentation/webdriver/bidi/logging/index.md): These features are related to logging. Because "logging" can refer to so many different things, these methods are made available via a "script" namespace.
- [WebDriver BiDi Network Features](https://www.selenium.dev/documentation/webdriver/bidi/network/index.md): These features are related to networking, and are made available via a "network" namespace.
- [WebDriver BiDi Script Features](https://www.selenium.dev/documentation/webdriver/bidi/script/index.md): These features are related to scripts, and are made available via a "script" namespace.
- [Chrome DevTools Protocol](https://www.selenium.dev/documentation/webdriver/bidi/cdp/index.md): Examples of working with Chrome DevTools Protocol in Selenium. CDP support is temporary until WebDriver BiDi has been implemented.
- [BiDirectional API (W3C compliant)](https://www.selenium.dev/documentation/webdriver/bidi/w3c/index.md): Examples of working with the W3C WebDriver BiDi protocol in Selenium — the standard, cross-browser replacement for the Chrome DevTools Protocol. These APIs stream events from the browser over a WebSocket, for network interception, console logs, and browser event handling.
