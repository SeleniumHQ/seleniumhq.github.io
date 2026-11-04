# Chrome DevTools Protocol

> Examples of working with Chrome DevTools Protocol in Selenium. CDP support is temporary until WebDriver BiDi has been implemented.

URL: https://www.selenium.dev/documentation/webdriver/bidi/cdp/
Site index: https://www.selenium.dev/llms.txt

---

Many browsers provide "DevTools" -- a set of tools that are integrated with the browser that 
developers can use to debug web apps and explore the performance of their pages. Google Chrome's 
DevTools make use of a protocol called the Chrome DevTools Protocol (or "CDP" for short). 
As the name suggests, this is not designed for testing, nor to have a stable API, so functionality 
is highly dependent on the version of the browser.

Selenium is working to implement a standards-based, cross-browser, stable alternative to CDP called
[WebDriver BiDi]. Until the support for this new protocol has finished, Selenium plans to provide access
to CDP features where applicable.

### Using Chrome DevTools Protocol with Selenium

Chrome and Edge have a method to send basic CDP commands. 
This does not work for features that require bidirectional communication, and you need to know what domains to enable when
and the exact names and types of domains/methods/parameters. 

**Java**

```java
Map<String, Object> cookie = new HashMap<>();
cookie.put("name", "cheese");
cookie.put("value", "gouda");
cookie.put("domain", "www.selenium.dev");
cookie.put("secure", true);
((HasCdp) driver).executeCdpCommand("Network.setCookie", cookie);
```

**Python**

```python
cookie = {'name': 'cheese',
          'value': 'gouda',
          'domain': 'www.selenium.dev',
          'secure': True}

driver.execute_cdp_cmd('Network.setCookie', cookie)
```

**CSharp**

```csharp
var cookie = new Dictionary<string, object>
{
    { "name", "cheese" },
    { "value", "gouda" },
    { "domain", "www.selenium.dev" },
    { "secure", true }
};
((ChromeDriver)driver).ExecuteCdpCommand("Network.setCookie", cookie);
```

**Ruby**

```ruby
driver.execute_cdp('Network.setCookie',
                   name: 'cheese',
                   value: 'gouda',
                   domain: 'www.selenium.dev',
                   secure: true)
```

**JavaScript**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

**Kotlin**

_No example for this language yet ([add one](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#creating-examples))._

To make working with CDP easier, and to provide access to the more advanced features, Selenium bindings
automatically generate classes and methods for the most common domains. 
CDP methods and implementations can change from version to version, though, so you want to keep the
version of Chrome and the version of DevTools matching. Selenium supports the 3 most
recent versions of Chrome at any given time, 
and tries to time releases to ensure that access to the latest versions are available.

This limitation provides additional challenges for several bindings, where dynamically
generated CDP support requires users to regularly update their code to reference the proper version of CDP.
In some cases an idealized implementation has been created that should work for any version of CDP without the
user needing to change their code, but that is not always available.

Examples of how to use CDP in your Selenium tests can be found on the following pages, but 
we want to call out a couple commonly cited examples that are of limited practical value.
* **Geo Location** — almost all sites use the IP address to determine physical location, 
so setting an emulated geolocation rarely has the desired effect.
* **Overriding Device Metrics** — Chrome provides a great API for setting [Mobile Emulation](https://chromedriver.chromium.org/mobile-emulation)
in the Options classes, which is generally superior to attempting to do this with CDP.

---

Section pages:

- [Chrome DevTools Logging Features](https://www.selenium.dev/documentation/webdriver/bidi/cdp/logging/index.md): Logging features using CDP.
- [Chrome DevTools Network Features](https://www.selenium.dev/documentation/webdriver/bidi/cdp/network/index.md): Network features using CDP.
- [Chrome DevTools Script Features](https://www.selenium.dev/documentation/webdriver/bidi/cdp/script/index.md): Script features using CDP.
