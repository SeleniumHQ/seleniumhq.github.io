# クッキーの使用

URL: https://www.selenium.dev/ja/documentation/webdriver/interactions/cookies/

---

Cookieは、Webサイトから送信され、コンピューターに保存される小さなデータです。
Cookieは、主にユーザーを認識し、保存されている情報を読み込むために使用されます。

WebDriver APIは、組み込みメソッドでCookieと対話するメソッドを提供します。

## クッキーの追加
現在のブラウジングコンテキストにCookieを追加するために使用されます。
Cookieの追加では、一連の定義済みのシリアル化可能なJSONオブジェクトのみを受け入れます。
受け入れられたJSONキー値のリストへのリンクは<a href="https://www.w3.org/TR/webdriver1/#cookies">こちら</a>にあります。

まず、Cookieが有効になるドメインにいる必要があります。
サイトとの対話を開始する前にCookieを事前設定しようとしていて、ホームページが大きい場合/代替の読み込みに時間がかかる場合は、サイトで小さいページを見つけることです。（通常、たとえば http://example.com/some404page のような、404ページは小さいです。）

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

## 命名されたクッキーの取得

関連付けられているすべてのCookieの中で、Cookie名と一致するシリアル化されたCookieデータを返します。

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

## 全てのクッキーの取得

現在のブラウジングコンテキストの '成功したシリアル化されたCookieデータ' を返します。
ブラウザが使用できなくなった場合、エラーが返されます。

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

## クッキーの削除

指定されたCookie名と一致するCookieデータを削除します。

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

## 全てのクッキーの削除

現在のブラウジングコンテキストの全てのCookieを削除します。

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

## SameSite Cookie属性

これにより、ユーザーは、サードパーティのサイトによって開始されたリクエストとともに
Cookieを送信するかどうかをブラウザに指示できます。
CSRF（クロスサイトリクエストフォージェリ）攻撃を防ぐために導入されました。

SameSite Cookie属性は、2つのパラメーターを命令として受け入れます。

## Strict:
SameSite属性が **Strict** に設定されている場合、CookieはサードパーティのWebサイトによって
開始されたリクエストとともに送信されません。

## Lax:
CookieのSameSite属性を **Lax** に設定すると、CookieはサードパーティのWebサイトによって
開始されたGETリクエストとともに送信されます。

**注**: **現在、この機能は Chrome（バージョン 80 以降）および Firefox（バージョン 79 以降）で
実装されており、Selenium 4 以降で動作します。**

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
