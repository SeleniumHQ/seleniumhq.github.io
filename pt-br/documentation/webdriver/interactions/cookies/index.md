# Trabalhando com cookies

URL: https://www.selenium.dev/pt-br/documentation/webdriver/interactions/cookies/

---

Um cookie é um pequeno pedaço de dado enviado de um site e armazenado no seu computador.
Os cookies são usados principalmente para reconhecer o usuário e carregar as informações armazenadas.

A API WebDriver fornece uma maneira de interagir com cookies com métodos integrados:

## Add Cookie
É usado para adicionar um cookie ao contexto de navegação atual.
Add Cookie aceita apenas um conjunto de objetos JSON serializáveis definidos. <a href="https://www.w3.org/TR/webdriver1/#cookies"> Aqui </a>
é o link para a lista de valores-chave JSON aceitos.

Em primeiro lugar, você precisa estar no domínio para qual o cookie será
valido. Se você está tentando predefinir cookies antes
de começar a interagir com um site e sua página inicial é grande / demora um pouco para carregar
uma alternativa é encontrar uma página menor no site (normalmente a página 404 é pequena,
por exemplo http://example.com/some404page)

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

Retorna os dados do cookie serializado correspondentes ao nome do cookie entre todos os cookies associados.

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

Retorna 'dados de cookie serializados com sucesso' para o contexto de navegação atual.
Se o navegador não estiver mais disponível, ele retornará um erro. 

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

Exclui os dados do cookie que correspondem ao nome do cookie fornecido.

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

Exclui todos os cookies do contexto de navegação atual.

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

Permite que um usuário instrua os navegadores a controlar se os cookies
são enviados junto com a solicitação iniciada por sites de terceiros.
É usado para evitar ataques CSRF (Cross-Site Request Forgery).

O atributo de cookie Same-Site aceita dois parâmetros como instruções

## Strict:
Quando o atributo sameSite é definido como **Strict**,
o cookie não será enviado junto com
solicitações iniciadas por sites de terceiros.

## Lax:
Quando você define um atributo cookie sameSite como **Lax**,
o cookie será enviado junto com uma solicitação GET
iniciada por um site de terceiros.

**Nota**: **a partir de agora, esse recurso está disponível no Chrome (versão 80+),
Firefox (versão 79+) e funciona com Selenium 4 e versões posteriores.**

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
