# Working with cookies

URL: https://www.selenium.dev/documentation/webdriver/interactions/cookies/
Site index: https://www.selenium.dev/llms.txt

---

A cookie is a small piece of data that is sent from a website and stored in your computer. 
Cookies are mostly used to recognise the user and load the stored information. 

WebDriver API provides a way to interact with cookies with built-in methods: 

## Add Cookie
It is used to add a cookie to the current browsing context. 
Add Cookie only accepts a set of defined serializable JSON object. <a href="https://www.w3.org/TR/webdriver1/#cookies"> Here </a>
is the link to the list of accepted JSON key values

First of all, you need to be on the domain that the cookie will be
valid for. If you are trying to preset cookies before
you start interacting with a site and your homepage is large / takes a while to load
an alternative is to find a smaller page on the site (typically the 404 page is small, 
e.g. http://example.com/some404page)

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java

WebDriver driver = new ChromeDriver();
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
```

**Kotlin**

```kotlin
import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    try {
        driver.get("https://example.com")

        // Adds the cookie into current browser context
        driver.manage().addCookie(Cookie("key", "value"))
    } finally {
        driver.quit()
    }
}

```

## Get Named Cookie

It returns the serialized cookie data matching with the cookie name among all associated cookies.

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
}

@Test
public void addCookie() {
    driver.get("https://www.selenium.dev/selenium/web/blank.html");
```

**Python**

```python
driver = webdriver.Chrome()
driver.get("http://www.example.com")

# Adds the cookie into current browser context
driver.add_cookie({"name": "foo", "value": "bar"})

# Get cookie details with named cookie 'foo'
print(driver.get_cookie("foo"))
```

**CSharp**

```csharp
driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
// Add cookie into current browser context
driver.Manage().Cookies.AddCookie(new Cookie("foo", "bar"));
// Get cookie details with named cookie 'foo'
Cookie cookie = driver.Manage().Cookies.GetCookieNamed("foo");
```

**Ruby**

```ruby
driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
# Add cookie into current browser context
driver.manage.add_cookie(name: 'foo', value: 'bar')
# Get cookie details with named cookie 'foo'
cookie = driver.manage.cookie_named('foo')
```

**JavaScript**

```javascript
// set a cookie on the current domain
await driver.manage().addCookie({ name: 'foo', value: 'bar' });

// Get cookie details with named cookie 'foo'
```

**Kotlin**

```kotlin
import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    try {
        driver.get("https://example.com")
        driver.manage().addCookie(Cookie("foo", "bar"))

        // Get cookie details with named cookie 'foo'
        val cookie = driver.manage().getCookieNamed("foo")
        println(cookie)
    } finally {
        driver.quit()
    }
}  

```

## Get All Cookies

It returns a ‘successful serialized cookie data’ for current browsing context. 
If browser is no longer available it returns error. 

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
    // Get cookie details with named cookie 'foo'
    Cookie cookie = driver.manage().getCookieNamed("foo");
    Assertions.assertEquals(cookie.getValue(), "bar");
}

@Test
public void getAllCookies() {
    driver.get("https://www.selenium.dev/selenium/web/blank.html");
    // Add cookies into current browser context
    driver.manage().addCookie(new Cookie("test1", "cookie1"));
    driver.manage().addCookie(new Cookie("test2", "cookie2"));
    // Get cookies
    Set<Cookie> cookies = driver.manage().getCookies();
    for (Cookie cookie : cookies) {
```

**Python**

```python
driver = webdriver.Chrome()

driver.get("http://www.example.com")

driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

# Get all available cookies
print(driver.get_cookies())
```

**CSharp**

```csharp
driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
// Add cookies into current browser context
driver.Manage().Cookies.AddCookie(new Cookie("test1", "cookie1"));
driver.Manage().Cookies.AddCookie(new Cookie("test2", "cookie2"));
// Get cookies
var cookies = driver.Manage().Cookies.AllCookies;
foreach (var cookie in cookies){
    if (cookie.Name.Equals("test1")){
        Assert.AreEqual("cookie1", cookie.Value);
    }
    if (cookie.Name.Equals("test2")){
        Assert.AreEqual("cookie2", cookie.Value);
    }
}
```

**Ruby**

```ruby
driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
# Add cookies into current browser context
driver.manage.add_cookie(name: 'test1', value: 'cookie1')
driver.manage.add_cookie(name: 'test2', value: 'cookie2')
# Get cookies
cookies = driver.manage.all_cookies
```

**JavaScript**

```javascript
await driver.manage().addCookie({ name: 'test2', value: 'cookie2' });

// Get all Available cookies
```

**Kotlin**

```kotlin
import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    try {
        driver.get("https://example.com")
        driver.manage().addCookie(Cookie("test1", "cookie1"))
        driver.manage().addCookie(Cookie("test2", "cookie2"))

        // Get All available cookies
        val cookies = driver.manage().cookies
        println(cookies)
    } finally {
        driver.quit()
    }
}  

```

## Delete Cookie

It deletes the cookie data matching with the provided cookie name.

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
    }
}
```

**Python**

```python
driver = webdriver.Chrome()

driver.get("http://www.example.com")

driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

# Delete cookie with name 'test1'
driver.delete_cookie("test1")
```

**CSharp**

```csharp
driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
driver.Manage().Cookies.AddCookie(new Cookie("test1", "cookie1"));
// delete cookie named
driver.Manage().Cookies.DeleteCookieNamed("test1");
```

**Ruby**

```ruby
driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
driver.manage.add_cookie(name: 'test1', value: 'cookie1')
# Delete cookie named
driver.manage.delete_cookie('test1')
```

**JavaScript**

```javascript
await driver.manage().addCookie({ name: 'test1', value: 'cookie1' });
await driver.manage().addCookie({ name: 'test2', value: 'cookie2' });
```

**Kotlin**

```kotlin
import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    try {
        driver.get("https://example.com")
        driver.manage().addCookie(Cookie("test1", "cookie1"))
        val cookie1 = Cookie("test2", "cookie2")
        driver.manage().addCookie(cookie1)

        // delete a cookie with name 'test1'
        driver.manage().deleteCookieNamed("test1")

        // delete cookie by passing cookie object of current browsing context.
        driver.manage().deleteCookie(cookie1)
    } finally {
        driver.quit()
    }
}  

```

## Delete All Cookies

It deletes all the cookies of the current browsing context.

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java
public void deleteAllCookies() {
    driver.get("https://www.selenium.dev/selenium/web/blank.html");
    // Add cookies into current browser context
    driver.manage().addCookie(new Cookie("test1", "cookie1"));
    driver.manage().addCookie(new Cookie("test2", "cookie2"));
    // Delete All cookies
```

**Python**

```python
driver = webdriver.Chrome()

driver.get("http://www.example.com")

driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

# Delete all cookies
driver.delete_all_cookies()
```

**CSharp**

```csharp
driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
// Add cookies into current browser context
driver.Manage().Cookies.AddCookie(new Cookie("test1", "cookie1"));
driver.Manage().Cookies.AddCookie(new Cookie("test2", "cookie2"));
// Delete All cookies
driver.Manage().Cookies.DeleteAllCookies();
```

**Ruby**

```ruby
driver.navigate.to 'https://www.selenium.dev/selenium/web/blank.html'
# Add cookies into current browser context
driver.manage.add_cookie(name: 'test1', value: 'cookie1')
driver.manage.add_cookie(name: 'test2', value: 'cookie2')
# Delete All cookies
driver.manage.delete_all_cookies
```

**JavaScript**

```javascript
await driver.manage().addCookie({ name: 'test1', value: 'cookie1' });
await driver.manage().addCookie({ name: 'test2', value: 'cookie2' });
```

**Kotlin**

```kotlin
import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    try {
        driver.get("https://example.com")
        driver.manage().addCookie(Cookie("test1", "cookie1"))
        driver.manage().addCookie(Cookie("test2", "cookie2"))

        // deletes all cookies
        driver.manage().deleteAllCookies()
    } finally {
        driver.quit()
    }
}  

```

## Same-Site Cookie Attribute

It allows a user to instruct browsers to control whether cookies 
are sent along with the request initiated by third party sites. 
It is introduced to prevent CSRF (Cross-Site Request Forgery) attacks.

Same-Site cookie attribute accepts two parameters as instructions

## Strict:
When the sameSite attribute is set as **Strict**, 
the cookie will not be sent along with 
requests initiated by third party websites.

## Lax:
When you set a cookie sameSite attribute to **Lax**, 
the cookie will be sent along with the GET 
request initiated by third party website.

**Note**: **As of now this feature is landed in chrome(80+version), 
Firefox(79+version) and works with Selenium 4 and later versions.**

_These examples are not yet tested in CI ([move them to the examples directory](https://www.selenium.dev/documentation/about/contributing/code_examples/index.md#moving-examples))._

**Java**

```java

    Cookie cookie = new Cookie.Builder("key", "value").sameSite("Strict").build();
    Cookie cookie1 = new Cookie.Builder("key", "value").sameSite("Lax").build();

    driver.manage().addCookie(cookie);
    driver.manage().addCookie(cookie1);

    System.out.println(cookie.getSameSite());
    System.out.println(cookie1.getSameSite());
}
```

**Python**

```python
driver = webdriver.Chrome()

driver.get("http://www.example.com")

# Adds the cookie into current browser context with sameSite 'Strict' (or) 'Lax'
driver.add_cookie({"name": "foo", "value": "value", "sameSite": "Strict"})
driver.add_cookie({"name": "foo1", "value": "value", "sameSite": "Lax"})

cookie1 = driver.get_cookie("foo")
cookie2 = driver.get_cookie("foo1")

print(cookie1)
print(cookie2)
```

**CSharp**

```csharp
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SameSiteCookie {
  class SameSiteCookie {
    static void Main(string[] args) {
      IWebDriver driver = new ChromeDriver();
      try {
        driver.Navigate().GoToUrl("http://www.example.com");

        var cookie1Dictionary = new System.Collections.Generic.Dictionary<string, object>() {
          { "name", "test1" }, { "value", "cookie1" }, { "sameSite", "Strict" } };
        var cookie1 = Cookie.FromDictionary(cookie1Dictionary);

        var cookie2Dictionary = new System.Collections.Generic.Dictionary<string, object>() {
          { "name", "test2" }, { "value", "cookie2" }, { "sameSite", "Lax" } };
        var cookie2 = Cookie.FromDictionary(cookie2Dictionary);

        driver.Manage().Cookies.AddCookie(cookie1);
        driver.Manage().Cookies.AddCookie(cookie2);

        System.Console.WriteLine(cookie1.SameSite);
        System.Console.WriteLine(cookie2.SameSite);
      } finally {
        driver.Quit();
      }
    }
  }
}

```

**Ruby**

```ruby
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.example.com'
  # Adds the cookie into current browser context with sameSite 'Strict' (or) 'Lax'
  driver.manage.add_cookie(name: "foo", value: "bar", same_site: "Strict")
  driver.manage.add_cookie(name: "foo1", value: "bar", same_site: "Lax")
  puts driver.manage.cookie_named('foo')
  puts driver.manage.cookie_named('foo1')
ensure
  driver.quit
end

```

**JavaScript**

```javascript
it('Create cookies with sameSite', async function() {
  await driver.get('https://www.selenium.dev/selenium/web/blank.html');
```

**Kotlin**

```kotlin
import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    try {
        driver.get("http://www.example.com")
        val cookie = Cookie.Builder("key", "value").sameSite("Strict").build()
        val cookie1 = Cookie.Builder("key", "value").sameSite("Lax").build()
        driver.manage().addCookie(cookie)
        driver.manage().addCookie(cookie1)
        println(cookie.getSameSite())
        println(cookie1.getSameSite())
    } finally {
        driver.quit()
    }
}

```
