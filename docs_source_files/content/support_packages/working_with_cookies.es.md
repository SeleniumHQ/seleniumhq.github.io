---
title: "Trabajando con las cookies"
weight: 6
---
 
Una cookie es una pequeña pieza de datos que es enviada desde el sitio web y es
almacenada en el ordenador.
Las cookies son usadas principalmente para reconocer al usuario y cargar la información
almacenada.

El API de WebDriver proporciona una forma de interactuar con las cookies a través
de métodos incorporados como:

## Añadir una Cookie
Este método es usado para añadir una cookie al contexto actual del navegador.
Este método solo acepta un conjunto de objetos JSON serializables definidos.
En este <a href="https://www.w3.org/TR/webdriver1/#cookies">enlace </a> esta la 
lista de claves valor JSON que son aceptadas.

Lo primero de todo, necesitas estar en el dominio para el que la cookie es valida.
Si intentas añadir un conjunto de cookies preestablecidas antes de empezar a 
interactuar con el sitio web y la pagina de inicio es muy pesada o tarda demasiado
en cargar una alternativa es encontrar una pagina mas pequeña en el sitio (típicamente
la pagina del error 404 es liviana ej. http://example.com/some404page)

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class addCookie {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://www.example.com");

            // Añade una cookie al contexto actual del navegador
            driver.manage().addCookie(new Cookie("key", "value"));
        } finally {
            driver.quit();
        }
    }
}
  {{< / code-panel >}}
{{< code-panel language="python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

driver.get("http://www.example.com")

# Añade una cookie al contexto actual del navegador
driver.add_cookie({"name": "key", "value": "value"})
  {{< / code-panel >}}
{{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace AddCookie {
 class AddCookie {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    // Navega a la URL
    driver.Navigate().GoToUrl("https://example.com");

    // Añade una cookie al contexto actual del navegador
    driver.Manage().Cookies.AddCookie(new Cookie("key", "value"));
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< / code-panel >}}
{{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.example.com'
  
  # Añade una cookie al contexto actual del navegador
  driver.manage.add_cookie(name: "key", value: "value")
ensure
  driver.quit
end
  {{< / code-panel >}}
{{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // Añade una cookie al contexto actual del navegador
    await driver.manage().addCookie({name:'key', value: 'value'});
})();
  {{< / code-panel >}}
{{< code-panel language="kotlin" >}}
import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    try {
        driver.get("https://example.com")

        // Añade una cookie al contexto actual del navegador
        driver.manage().addCookie(Cookie("key", "value"));
    } finally {
        driver.quit()
    }
} 
  {{< / code-panel >}}
{{< / code-tab >}}

## Obtener una cookie por nombre

Devuelve la información de la cookie serializada que concuerda con el nombre de
la cookie entre todas las cookies asociadas.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class getCookieNamed {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://www.example.com");
            driver.manage().addCookie(new Cookie("foo", "bar"));

            // Obtiene los detalles de la cookie con el nombre 'foo'
            Cookie cookie1 = driver.manage().getCookieNamed("foo");
            System.out.println(cookie1);
        } finally {
            driver.quit();
        }
    }
}
  {{< / code-panel >}}
 {{< code-panel language="python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

# Navega a la URL
driver.get("http://www.example.com")

# Añade una cookie al contexto actual del navegador
driver.add_cookie({"name": "foo", "value": "bar"})

# Obtiene los detalles de la cookie con el nombre 'foo'
print driver.get_cookie("foo")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace GetCookieNamed {
 class GetCookieNamed {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    // Navega a la URL
    driver.Navigate().GoToUrl("https://example.com");
    driver.Manage().Cookies.AddCookie(new Cookie("foo", "bar"));

    // Obtiene los detalles de la cookie con el nombre 'foo'
    var cookie = driver.Manage().Cookies.GetCookieNamed("foo");
    System.Console.WriteLine(cookie);
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.example.com'
  driver.manage.add_cookie(name: "foo", value: "bar")

  # Obtiene los detalles de la cookie con el nombre 'foo'
  puts driver.manage.cookie_named('foo')
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // Añade una cookie en el dominio actual
    await driver.manage().addCookie({name:'foo', value: 'bar'});

    // Obtiene los detalles de la cookie con el nombre 'foo' 
    driver.manage().getCookie('foo').then(function (cookie) {
        console.log('cookie details => ', cookie);
    });
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    try {
        driver.get("https://example.com")
        driver.manage().addCookie(Cookie("foo", "bar"));

        // Obtiene los detalles de la cookie con el nombre 'foo'
        val cookie = driver.manage().getCookieNamed("foo");
        println(cookie);
    } finally {
        driver.quit()
    }
}  
  {{< / code-panel >}}
{{< / code-tab >}}

## Obtener todas las cookies

Devuelve la información serializada de las cookies de manera satisfactoria para
el contexto actual del navegador.
Si el navegador no esta disponible devolverá un error.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;

public class getAllCookies {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://www.example.com");
            // Añade varias cookies
            driver.manage().addCookie(new Cookie("test1", "cookie1"));
            driver.manage().addCookie(new Cookie("test2", "cookie2"));

            // Obtiene todas las cookies disponibles
            Set<Cookie> cookies = driver.manage().getCookies();
            System.out.println(cookies);
        } finally {
            driver.quit();
        }
    }
}
  {{< / code-panel >}}
 {{< code-panel language="python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

# Navega a la URL
driver.get("http://www.example.com")

driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

# Obtiene todas las cookies disponibles
print driver.get_cookies()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace GetAllCookies {
 class GetAllCookies {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    // Navega a la URL
    driver.Navigate().GoToUrl("https://example.com");
    driver.Manage().Cookies.AddCookie(new Cookie("test1", "cookie1"));
    driver.Manage().Cookies.AddCookie(new Cookie("test2", "cookie2"));

    // Obtiene todas las cookies disponibles
    var cookies = driver.Manage().Cookies.AllCookies;
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.example.com'
  driver.manage.add_cookie(name: "test1", value: "cookie1")
  driver.manage.add_cookie(name: "test2", value: "cookie2")

  # Obtiene todas las cookies disponibles
  puts driver.manage.all_cookies
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // Añade varias cookies
    await driver.manage().addCookie({name:'test1', value:'cookie1'});
    await driver.manage().addCookie({name:'test2', value:'cookie2'});

    // Obtiene todas las cookies disponibles
    driver.manage().getCookies().then(function (cookies) {
        console.log('cookie details => ', cookies);
    });
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    try {
        driver.get("https://example.com")
        driver.manage().addCookie(Cookie("test1", "cookie1"));
        driver.manage().addCookie(Cookie("test2", "cookie2"));

        // Obtiene todas las cookies disponibles
        val cookies = driver.manage().cookies;
        println(cookies);
    } finally {
        driver.quit()
    }
} 
  {{< / code-panel >}}
{{< / code-tab >}}


## Borrado de una Cookie

Borra la información de la cookie que coincida con el nombre de la cookie proporcionado.

{{< code-tab >}}
  {{< code-panel language="java" >}}
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

            // Borra la cookie con el nombre 'test1'
            driver.manage().deleteCookieNamed("test1");

            /*
             Selenium Java tambien proporciona una forma de borrar cookies
             pasando un objeto cookie del contexto actual del navegador.
             */
            driver.manage().deleteCookie(cookie1);
        } finally {
            driver.quit();
        }
    }
}
  {{< / code-panel >}}
 {{< code-panel language="python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

# Navega a la URL
driver.get("http://www.example.com")
driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

# Borra la cookie con el nombre 'test1'
driver.delete_cookie("test1")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace DeleteCookie {
 class DeleteCookie {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    // Navega a la URL
    driver.Navigate().GoToUrl("https://example.com");
    driver.Manage().Cookies.AddCookie(new Cookie("test1", "cookie1"));
    var cookie = new Cookie("test2", "cookie2");
    driver.Manage().Cookies.AddCookie(cookie);

    // Borra la cookie con el nombre 'test1'
    driver.Manage().Cookies.DeleteCookieNamed("test1");

    // Selenium .Net tambien proporciona una forma de borrar cookies
    // pasando un objeto cookie del contexto actual del navegador.
    driver.Manage().Cookies.DeleteCookie(cookie);
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.example.com'
  driver.manage.add_cookie(name: "test1", value: "cookie1")
  driver.manage.add_cookie(name: "test2", value: "cookie2")

  # Borra la cookie con el nombre 'test1'
  driver.manage.delete_cookie('test1')
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // Añade varias cookies
    await driver.manage().addCookie({name:'test1', value:'cookie1'});
    await driver.manage().addCookie({name:'test2', value:'cookie2'});

    // Borra la cookie con el nombre 'test1'
    await driver.manage().deleteCookie('test1');
    
    // Muestra todas las cookies disponibles
    driver.manage().getCookies().then(function (cookies) {
        console.log('cookie details => ', cookies);
    });
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    try {
        driver.get("https://example.com")
        driver.manage().addCookie(Cookie("test1", "cookie1"));
        val cookie1 = Cookie("test2", "cookie2")
        driver.manage().addCookie(cookie1);

        // Borra la cookie con el nombre 'test1'
        driver.manage().deleteCookieNamed("test1");

        // Borra una cookie pasando un objeto cookie del contexto actual del navegador. 
        driver.manage().deleteCookie(cookie1);
    } finally {
        driver.quit()
    }
}R  
  {{< / code-panel >}}
{{< / code-tab >}}


## Borra todas las Cookies

Borra todas las cookies del contexto actual del navegador.

{{< code-tab >}}
  {{< code-panel language="java" >}}
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class deleteAllCookies {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://www.example.com");
            driver.manage().addCookie(new Cookie("test1", "cookie1"));
            driver.manage().addCookie(new Cookie("test2", "cookie2"));

            // Borra todas las cookies
            driver.manage().deleteAllCookies();
        } finally {
            driver.quit();
        }
    }
}
  {{< / code-panel >}}
 {{< code-panel language="python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

# Navega a la URL
driver.get("http://www.example.com")
driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

#  Borra todas las cookies
driver.delete_all_cookies()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace DeleteAllCookies {
 class DeleteAllCookies {
  public static void Main(string[] args) {
   IWebDriver driver = new ChromeDriver();
   try {
    // Navega a la URL
    driver.Navigate().GoToUrl("https://example.com");
    driver.Manage().Cookies.AddCookie(new Cookie("test1", "cookie1"));
    driver.Manage().Cookies.AddCookie(new Cookie("test2", "cookie2"));

    // Borra todas las cookies
    driver.Manage().Cookies.DeleteAllCookies();
   } finally {
    driver.Quit();
   }
  }
 }
}
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

begin
  driver.get 'https://www.example.com'
  driver.manage.add_cookie(name: "test1", value: "cookie1")
  driver.manage.add_cookie(name: "test2", value: "cookie2")

  # Borra todas las cookies
  driver.manage.delete_all_cookies
ensure
  driver.quit
end
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // Añade varias cookies
    await driver.manage().addCookie({name:'test1', value:'cookie1'});
    await driver.manage().addCookie({name:'test2', value:'cookie2'});

    // Borra todas las cookies
    await driver.manage().deleteAllCookies();
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
import org.openqa.selenium.Cookie
import org.openqa.selenium.chrome.ChromeDriver

fun main() {
    val driver = ChromeDriver()
    try {
        driver.get("https://example.com")
        driver.manage().addCookie(Cookie("test1", "cookie1"));
        driver.manage().addCookie(Cookie("test2", "cookie2"));

        // Borra todas las cookies
        driver.manage().deleteAllCookies();
    } finally {
        driver.quit()
    }
}  
  {{< / code-panel >}}
{{< / code-tab >}}


## El Atributo Same-Site cookie

Este atributo permite al usuario enseñar a los navegadores a controlar que cookies
son mandadas junto con las peticiones iniciadas por servicios externos.
Esto introducido con la intención de prevenir ataques CSRF (_Cross-Site Request Forgery_)
El atributo Same-Site acepta dos parámetros como instrucciones:

## Estricto:
Cuando el atributo sameSite esta fijado como **Strict** (estricto en español),
la cookie no será enviada junto a las peticiones iniciadas por paginas web externas.

## Laxo:
Cuando el atributo sameSite se fija como **Lax** (Laxo en español),
la cookie será enviada junto con la petición GET iniciada por paginas web externas.


**Nota**: **Ahora mismo esta característica esta disponible en la versión 80 y 
superiores de chrome funcionando con Selenium 4 y versiones posteriores.**

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
 {{< code-panel language="python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

driver.get("http://www.example.com")
# Añade la cookie en el contexto actual del navegador con el parámetro 
# sameSite como 'Strict' (o) 'Lax'
driver.add_cookie({"name": "foo", "value": "value", 'sameSite': 'Strict'})
driver.add_cookie({"name": "foo1", "value": "value", 'sameSite': 'Lax'})
cookie1 = driver.get_cookie('foo')
cookie2 = driver.get_cookie('foo1')
print cookie1
print cookie2
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// No disponemos del ejemplo de código en C# aun - Ayudanos a ello abriendo un PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
# No disponemos del ejemplo de código en Ruby aun - Ayudanos a ello abriendo un PR
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();
        
    await driver.get('https://www.example.com');
    
    // Añade la cookie en el contexto actual del navegador con el parámetro 
    // sameSite como 'Strict' (o) 'Lax'
    await driver.manage().addCookie({name:'key', value: 'value', sameSite:'Strict'});
    await driver.manage().addCookie({name:'key', value: 'value', sameSite:'Lax'});
    console.log(await driver.manage().getCookie('key'));
})();
  {{< / code-panel >}}
  {{< code-panel language="kotlin" >}}
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
  {{< / code-panel >}}
{{< / code-tab >}}
