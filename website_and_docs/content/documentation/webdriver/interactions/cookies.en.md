---
title: "Working with cookies"
linkTitle: "Cookies"
weight: 4
aliases: [
"/documentation/en/support_packages/working_with_cookies/",
"/documentation/support_packages/working_with_cookies/",
"/documentation/webdriver/browser/cookies"
]
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

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
{{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/CookiesTest.java#L30-L32" >}}
  {{< /tab >}}
{{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_cookies.py#L5-L9" >}}
  {{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/CookiesTest.cs#L32-L34" >}}
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
  {{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/cookies.spec.js#L18">}}
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

It returns the serialized cookie data matching with the cookie name among all associated cookies.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
 {{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/CookiesTest.java#L38-L42" >}}
  {{< /tab >}}
 {{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_cookies.py#L13-L20" >}}
  {{< /tab >}}
  {{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/CookiesTest.cs#L40-L44" >}}
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
  {{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/cookies.spec.js#L35-L38">}}
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

It returns a ‘successful serialized cookie data’ for current browsing context. 
If browser is no longer available it returns error. 

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
      {{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/CookiesTest.java#L52-L66" >}}
  {{< /tab >}}
 {{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_cookies.py#L24-L32" >}}
  {{< /tab >}}
    {{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/CookiesTest.cs#L51-L64" >}}
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
  {{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/cookies.spec.js#L49-L51">}}
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

It deletes the cookie data matching with the provided cookie name.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
        {{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/CookiesTest.java#L74-L77" >}}
  {{< /tab >}}
 {{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_cookies.py#L35-L43" text=true >}}
  {{< /tab >}}
          {{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/CookiesTest.cs#L70-L73" >}}
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
  {{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/cookies.spec.js#L61-L62">}}
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

It deletes all the cookies of the current browsing context.

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
              {{< tab header="Java" text=true >}}
{{< gh-codeblock path="examples/java/src/test/java/dev/selenium/interactions/CookiesTest.java#L100-L105" >}}
  {{< /tab >}}
 {{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_cookies.py#L47-L55" text=true >}}
  {{< /tab >}}
{{< tab header="CSharp" text=true >}}
{{< gh-codeblock path="examples/dotnet/SeleniumDocs/Interactions/CookiesTest.cs#L92-L97" >}}
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
  {{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/cookies.spec.js#L77-L78">}}
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

{{< tabpane langEqualsHeader=true >}}
{{< badge-examples >}}
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
 {{< tab header="Python" text=true >}}
{{< gh-codeblock path="examples/python/tests/interactions/test_cookies.py#L59-L71" text=true >}}
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
  {{< tab header="JavaScript" text=true >}}
{{< gh-codeblock path="/examples/javascript/test/interactions/cookies.spec.js#L24-L26">}}
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
