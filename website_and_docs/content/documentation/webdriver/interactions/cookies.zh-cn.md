---
title: "同cookies一起工作"
linkTitle: "Cookies"
weight: 4
aliases: [
"/documentation/zh-cn/support_packages/working_with_cookies/",
"/zh-cn/documentation/support_packages/working_with_cookies/",
"/zh-cn/documentation/webdriver/browser/cookies/"
]
---
 

Cookie是从网站发送并存储在您的计算机中的一小段数据. Cookies主要用于识别用户并加载存储的信息. 

WebDriver API提供了一种使用内置的方法与Cookie进行交互: 

## 添加 Cookie
这个方法常常用于将cookie添加到当前访问的上下文中. 添加Cookie仅接受一组已定义的可序列化JSON对象. <a href="https://www.w3.org/TR/webdriver1/#cookies"> 这里 </a>
是一个链接, 用于描述可接受的JSON键值的列表

首先, 您需要位于有效Cookie的域上. 如果您在开始与网站进行交互之前尝试预设cookie, 并且您的首页很大或需要一段时间才能加载完毕, 则可以选择在网站上找到一个较小的页面 (通常404页很小, 
例如 http://example.com/some404page)

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

## 获取命名的 Cookie

此方法返回与cookie名称匹配的序列化cookie数据中所有关联的cookie.

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

## 获取全部 Cookies

此方法会针对当前访问上下文返回“成功的序列化cookie数据”. 如果浏览器不再可用, 则返回错误. 

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


## 删除 Cookie

此方法删除与提供的cookie名称匹配的cookie数据.

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


## 删除所有 Cookies

此方法删除当前访问上下文的所有cookie.

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

## Same-Site Cookie属性

此属性允许用户引导浏览器控制cookie, 是否与第三方站点发起的请求一起发送.
引入其是为了防止CSRF（跨站请求伪造）攻击.

Same-Site cookie属性接受以下两种参数作为指令

## Strict:
当sameSite属性设置为 **Strict**, 
cookie不会与来自第三方网站的请求一起发送.

## Lax:
当您将cookie sameSite属性设置为 **Lax**,
cookie将与第三方网站发起的GET请求一起发送.

**注意**: **到目前为止, 
此功能已在Chrome(80+版本), 
Firefox(79+版本)中提供,
并适用于Selenium 4以及更高版本.**

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
