package dev.selenium.interactions;

import dev.selenium.BaseChromeTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import java.util.Set;

public class CookiesTest extends BaseChromeTest {
  @Test
  public void addCookie() {
    try {
      driver.get("https://www.selenium.dev/selenium/web/blank.html");
      // Add cookie into current browser context
      driver.manage().addCookie(new Cookie("key", "value"));
    } finally {
      driver.quit();
    }
  }
    @Test
    public void getNamedCookie() {
      try {
        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        // Add cookie into current browser context
        driver.manage().addCookie(new Cookie("foo", "bar"));
        // Get cookie details with named cookie 'foo'
        Cookie cookie = driver.manage().getCookieNamed("foo");
        Assertions.assertEquals(cookie.getValue(), "bar");
      } finally {
        driver.quit();
      }
    }

    @Test
    public void getAllCookies() {
      try {
        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        // Add cookies into current browser context
        driver.manage().addCookie(new Cookie("test1", "cookie1"));
        driver.manage().addCookie(new Cookie("test2", "cookie2"));
        // Get cookies
        Set<Cookie> cookies = driver.manage().getCookies();
         for (Cookie cookie : cookies) {
            if (cookie.getName().equals("test1")) {
                Assertions.assertEquals(cookie.getValue(), "cookie1");
            }

            if (cookie.getName().equals("test2")) {
                Assertions.assertEquals(cookie.getValue(), "cookie2");
            }
         }
      } finally {
        driver.quit();
      }
    }

    @Test
    public void deleteCookieNamed() {
      try {
        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        driver.manage().addCookie(new Cookie("test1", "cookie1"));
        // delete cookie named
        driver.manage().deleteCookieNamed("test1");
      } finally {
        driver.quit();
      }
    }

    @Test
    public void deleteCookieObject() {
      try {
        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        Cookie cookie = new Cookie("test2", "cookie2");
        driver.manage().addCookie(cookie);
        /*
        Selenium Java bindings also provides a way to delete
        cookie by passing cookie object of current browsing context
        */
        driver.manage().deleteCookie(cookie);
      } finally {
        driver.quit();
      }
    }

    @Test
    public void deleteAllCookies() {
      try {
        driver.get("https://www.selenium.dev/selenium/web/blank.html");
        // Add cookies into current browser context
        driver.manage().addCookie(new Cookie("test1", "cookie1"));
        driver.manage().addCookie(new Cookie("test2", "cookie2"));
        // Delete All cookies
        driver.manage().deleteAllCookies();
      } finally {
        driver.quit();
      }
    }
}
