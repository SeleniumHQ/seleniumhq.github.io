---
title: "Working with cookies"
weight: 6
---

{{% notice info %}}
<i class="fas fa-language"></i> Page being translated from 
English to Chinese. Do you speak Chinese? Help us to translate
it by sending us pull requests!
{{% /notice %}}

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

{{< code-tab >}}
  {{< code-panel language="java" >}}
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
  {{< / code-panel >}}
{{< code-panel language="python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

driver.get("http://www.example.com")

# Adds the cookie into current browser context
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
                // Navigate to Url
                driver.Navigate().GoToUrl("https://example.com");

                // Adds the cookie into current browser context
                driver.Manage().Cookies.AddCookie(new Cookie("key", "value"));
            }
            finally {
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
  
  # Adds the cookie into current browser context
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

    // set a cookie on the current domain
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

        // Adds the cookie into current browser context
        driver.manage().addCookie(Cookie("key", "value"));
    } finally {
        driver.quit()
    }
}  
  {{< / code-panel >}}
{{< / code-tab >}}

## Get Named Cookie

It returns the serialized cookie data matching with the cookie name among all associated cookies.

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

            // Get cookie details with named cookie 'foo'
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

# Navigate to url
driver.get("http://www.example.com")

# Adds the cookie into current browser context
driver.add_cookie({"name": "foo", "value": "bar"})

# Get cookie details with named cookie 'foo'
print driver.get_cookie("foo")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // set a cookie on the current domain
    await driver.manage().addCookie({name:'foo', value: 'bar'});

    // Get cookie details with named cookie 'foo' 
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

        // Get cookie details with named cookie 'foo'
        val cookie = driver.manage().getCookieNamed("foo");
        println(cookie);
    } finally {
        driver.quit()
    }
}  
  {{< / code-panel >}}
{{< / code-tab >}}

## Get All Cookies

It returns a ‘successful serialized cookie data’ for current browsing context. 
If browser is no longer available it returns error. 

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
  {{< / code-panel >}}
 {{< code-panel language="python" >}}
from selenium import webdriver

driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")

driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

# Get all available cookies
print driver.get_cookies()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // Add few cookies
    await driver.manage().addCookie({name:'test1', value:'cookie1'});
    await driver.manage().addCookie({name:'test2', value:'cookie2'});

    // Get all Available cookies
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

        // Get All available cookies
        val cookies = driver.manage().cookies;
        println(cookies);
    } finally {
        driver.quit()
    }
}  
  {{< / code-panel >}}
{{< / code-tab >}}


## Delete Cookie

It deletes the cookie data matching with the provided cookie name.

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
  {{< / code-panel >}}
 {{< code-panel language="python" >}}
from selenium import webdriver
driver = webdriver.Chrome()

# Navigate to url
driver.get("http://www.example.com")
driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

# Delete a cookie with name 'test1'
driver.delete_cookie("test1")
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // Add few cookies
    await driver.manage().addCookie({name:'test1', value:'cookie1'});
    await driver.manage().addCookie({name:'test2', value:'cookie2'});

    // Delete a cookie with name 'test1'
    await driver.manage().deleteCookie('test1');
    
    // Get all Available cookies
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

        // delete a cookie with name 'test1'
        driver.manage().deleteCookieNamed("test1");
        /*
         Selenium Java bindings also provides a way to delete
         cookie by passing cookie object of current browsing context.
         This feature also applies for kotlin as it uses selenium jar java bindings
        */
        driver.manage().deleteCookie(cookie1);
    } finally {
        driver.quit()
    }
}  
  {{< / code-panel >}}
{{< / code-tab >}}


## Delete All Cookies

It deletes all the cookies of the current browsing context.

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

            // deletes all cookies
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

# Navigate to url
driver.get("http://www.example.com")
driver.add_cookie({"name": "test1", "value": "cookie1"})
driver.add_cookie({"name": "test2", "value": "cookie2"})

#  Deletes all cookies
driver.delete_all_cookies()
  {{< / code-panel >}}
  {{< code-panel language="csharp" >}}
// Please raise a PR
  {{< / code-panel >}}
  {{< code-panel language="ruby" >}}
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
  {{< / code-panel >}}
  {{< code-panel language="javascript" >}}
const {Builder} = require('selenium-webdriver');
(async function example() {
    let driver = new Builder()
        .forBrowser('chrome')
        .build();

    await driver.get('https://www.example.com');

    // Add few cookies
    await driver.manage().addCookie({name:'test1', value:'cookie1'});
    await driver.manage().addCookie({name:'test2', value:'cookie2'});

    // Delete all cookies
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

        // deletes all cookies
        driver.manage().deleteAllCookies();
    } finally {
        driver.quit()
    }
}
  {{< / code-panel >}}
{{< / code-tab >}}