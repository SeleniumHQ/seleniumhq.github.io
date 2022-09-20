---
title: "Trabalhando com cookies"
linkTitle: "Trabalhando com cookies"
weight: 4
aliases: [
"/documentation/pt-br/support_packages/working_with_cookies/",
"/pt-br/documentation/support_packages/working_with_cookies/",
"/pt-br/documentation/webdriver/browser/cookies/"
]
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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class addCookie {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://www.example.com");

            // Adds the cookie into current browser context
            driver.manage().addCookie(new Cookie("key", "value"));
        } finally {
            driver.quit();
        }
    }
}
  {{< /tab >}}
{{< tab header="Python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

driver.get("http://www.example.com")

# Adds the cookie into current browser context
driver.add_cookie({"name": "key", "value": "value"})
  {{< /tab >}}
{{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace AddCookie {
 class AddCookie {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    // Navigate to Url
    driver.Navigate().GoToUrl("https://example.com");

    // Adds the cookie into current browser context
    driver.Manage().Cookies.AddCookie(new Cookie("key", "value"));
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< /tab >}}
{{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.example.com'
  
  # Adds the cookie into current browser context
  driver.manage.add_cookie(name: "key", value: "value")
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" code=false >}}
{{< gh-codeblock path="/examples/javascript/test/browser/cookies.spec.js#L13-L18">}}
  {{< /tab >}}
{{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}

## Get Named Cookie

Retorna os dados do cookie serializado correspondentes ao nome do cookie entre todos os cookies associados.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class getCookieNamed {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://www.example.com");
            driver.manage().addCookie(new Cookie("foo", "bar"));

            // Get cookie details with named cookie 'foo'
            Cookie cookie1 = driver.manage().getCookieNamed("foo");
            System.out.println(cookie1);
        } finally {
            driver.quit();
        }
    }
}
  {{< /tab >}}
 {{< tab header="Python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")

# Adds the cookie into current browser context
driver.add_cookie({"name": "foo", "value": "bar"})

# Get cookie details with named cookie 'foo'
print(driver.get_cookie("foo"))
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace GetCookieNamed {
 class GetCookieNamed {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    // Navigate to Url
    driver.Navigate().GoToUrl("https://example.com");
    driver.Manage().Cookies.AddCookie(new Cookie("foo", "bar"));

    // Get cookie details with named cookie 'foo'
    var cookie = driver.Manage().Cookies.GetCookieNamed("foo");
    System.Console.WriteLine(cookie);
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.example.com'
  driver.manage.add_cookie(name: "foo", value: "bar")

  # Get cookie details with named cookie 'foo'
  puts driver.manage.cookie_named('foo')
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" code=false >}}
{{< gh-codeblock path="/examples/javascript/test/browser/cookies.spec.js#L28-L38">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}

## Get All Cookies

Retorna 'dados de cookie serializados com sucesso' para o contexto de navegação atual.
Se o navegador não estiver mais disponível, ele retornará um erro. 

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;

public class getAllCookies {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://www.example.com");
            // Add few cookies
            driver.manage().addCookie(new Cookie("test1", "cookie1"));
            driver.manage().addCookie(new Cookie("test2", "cookie2"));

            // Get All available cookies
            Set<Cookie> cookies = driver.manage().getCookies();
            System.out.println(cookies);
        } finally {
            driver.quit();
        }
    }
}
  {{< /tab >}}
 {{< tab header="Python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")

driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

# Get all available cookies
print(driver.get_cookies())
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace GetAllCookies {
 class GetAllCookies {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    // Navigate to Url
    driver.Navigate().GoToUrl("https://example.com");
    driver.Manage().Cookies.AddCookie(new Cookie("test1", "cookie1"));
    driver.Manage().Cookies.AddCookie(new Cookie("test2", "cookie2"));

    // Get All available cookies
    var cookies = driver.Manage().Cookies.AllCookies;
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.example.com'
  driver.manage.add_cookie(name: "test1", value: "cookie1")
  driver.manage.add_cookie(name: "test2", value: "cookie2")

  # Get all available cookies
  puts driver.manage.all_cookies
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" code=false >}}
{{< gh-codeblock path="/examples/javascript/test/browser/cookies.spec.js#L40-L51">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}


## Delete Cookie

Exclui os dados do cookie que correspondem ao nome do cookie fornecido.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class deleteCookie {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://www.example.com");
            driver.manage().addCookie(new Cookie("test1", "cookie1"));
            Cookie cookie1 = new Cookie("test2", "cookie2");
            driver.manage().addCookie(cookie1);

            // delete a cookie with name 'test1'
            driver.manage().deleteCookieNamed("test1");

            /*
             Selenium Java bindings also provides a way to delete
             cookie by passing cookie object of current browsing context
             */
            driver.manage().deleteCookie(cookie1);
        } finally {
            driver.quit();
        }
    }
}
  {{< /tab >}}
 {{< tab header="Python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")
driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

# Delete a cookie with name 'test1'
driver.delete_cookie("test1")
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace DeleteCookie {
 class DeleteCookie {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    // Navigate to Url
    driver.Navigate().GoToUrl("https://example.com");
    driver.Manage().Cookies.AddCookie(new Cookie("test1", "cookie1"));
    var cookie = new Cookie("test2", "cookie2");
    driver.Manage().Cookies.AddCookie(cookie);

    // delete a cookie with name 'test1'	
    driver.Manage().Cookies.DeleteCookieNamed("test1");

    // Selenium .net bindings also provides a way to delete
    // cookie by passing cookie object of current browsing context
    driver.Manage().Cookies.DeleteCookie(cookie);
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.example.com'
  driver.manage.add_cookie(name: "test1", value: "cookie1")
  driver.manage.add_cookie(name: "test2", value: "cookie2")

  # delete a cookie with name 'test1'
  driver.manage.delete_cookie('test1')
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" code=false >}}
{{< gh-codeblock path="/examples/javascript/test/browser/cookies.spec.js#L53-L67">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}


## Delete All Cookies

Exclui todos os cookies do contexto de navegação atual.

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class deleteAllCookies {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://www.example.com");
            driver.manage().addCookie(new Cookie("test1", "cookie1"));
            driver.manage().addCookie(new Cookie("test2", "cookie2"));

            // deletes all cookies
            driver.manage().deleteAllCookies();
        } finally {
            driver.quit();
        }
    }
}
  {{< /tab >}}
 {{< tab header="Python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")
driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

#  Deletes all cookies
driver.delete_all_cookies()
  {{< /tab >}}
  {{< tab header="CSharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace DeleteAllCookies {
 class DeleteAllCookies {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    // Navigate to Url
    driver.Navigate().GoToUrl("https://example.com");
    driver.Manage().Cookies.AddCookie(new Cookie("test1", "cookie1"));
    driver.Manage().Cookies.AddCookie(new Cookie("test2", "cookie2"));

    // deletes all cookies
    driver.Manage().Cookies.DeleteAllCookies();
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< /tab >}}
  {{< tab header="Ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.example.com'
  driver.manage.add_cookie(name: "test1", value: "cookie1")
  driver.manage.add_cookie(name: "test2", value: "cookie2")

  # deletes all cookies
  driver.manage.delete_all_cookies
ensure
  driver.quit
end
  {{< /tab >}}
  {{< tab header="JavaScript" code=false >}}
{{< gh-codeblock path="/examples/javascript/test/browser/cookies.spec.js#L69-L78">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}

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

{{< tabpane langEqualsHeader=true >}}
  {{< tab header="Java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class cookieTest {
  public static void main(String[] args) {
    WebDriver driver = new ChromeDriver();
    try {
      driver.get("http://www.example.com");
      Cookie cookie = new Cookie.Builder("key", "value").sameSite("Strict").build();
      Cookie cookie1 = new Cookie.Builder("key", "value").sameSite("Lax").build();
      driver.manage().addCookie(cookie);
      driver.manage().addCookie(cookie1);
      System.out.println(cookie.getSameSite());
      System.out.println(cookie1.getSameSite());
    } finally {
      driver.quit();
    }
  }
}
  {{< /tab >}}
 {{< tab header="Python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

driver.get("http://www.example.com")
# Adds the cookie into current browser context with sameSite 'Strict' (or) 'Lax'
driver.add_cookie({"name": "foo", "value": "value", 'sameSite': 'Strict'})
driver.add_cookie({"name": "foo1", "value": "value", 'sameSite': 'Lax'})
cookie1 = driver.get_cookie('foo')
cookie2 = driver.get_cookie('foo1')
print(cookie1)
print(cookie2)
  {{< /tab >}}
  {{< tab header="CSharp" >}}
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
  {{< /tab >}}
  {{< tab header="Ruby" >}}
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
  {{< /tab >}}
  {{< tab header="JavaScript" code=false >}}
{{< gh-codeblock path="/examples/javascript/test/browser/cookies.spec.js#L20-L26">}}
  {{< /tab >}}
  {{< tab header="Kotlin" >}}
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
  {{< /tab >}}
{{< /tabpane >}}
