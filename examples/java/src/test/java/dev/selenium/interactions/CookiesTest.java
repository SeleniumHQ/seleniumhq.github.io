// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;

public class CookiesTest {

	WebDriver driver = new ChromeDriver();
	@Test
	  public void addCookie() {
	      driver.get("https://www.selenium.dev/selenium/web/blank.html");
	      // Add cookie into current browser context
	      driver.manage().addCookie(new Cookie("key", "value"));
	      driver.quit();
	}
	    @Test
	    public void getNamedCookie() {
	     
	        driver.get("https://www.selenium.dev/selenium/web/blank.html");
	        // Add cookie into current browser context
	        driver.manage().addCookie(new Cookie("foo", "bar"));
	        // Get cookie details with named cookie 'foo'
	        Cookie cookie = driver.manage().getCookieNamed("foo");
	        Assertions.assertEquals(cookie.getValue(), "bar");
	     
	        driver.quit();
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
	            if (cookie.getName().equals("test1")) {
	                Assertions.assertEquals(cookie.getValue(), "cookie1");
	            }

	            if (cookie.getName().equals("test2")) {
	                Assertions.assertEquals(cookie.getValue(), "cookie2");
	            }
	         }
	         driver.quit();
	      }
	   

	    @Test
	    public void deleteCookieNamed() {
	     
	        driver.get("https://www.selenium.dev/selenium/web/blank.html");
	        driver.manage().addCookie(new Cookie("test1", "cookie1"));
	        // delete cookie named
	        driver.manage().deleteCookieNamed("test1");
	        driver.quit();
	    }

	    @Test
	    public void deleteCookieObject() {
	     
	        driver.get("https://www.selenium.dev/selenium/web/blank.html");
	        Cookie cookie = new Cookie("test2", "cookie2");
	        driver.manage().addCookie(cookie);
	        /*
	        Selenium Java bindings also provides a way to delete
	        cookie by passing cookie object of current browsing context
	        */
	        driver.manage().deleteCookie(cookie);
	      
	        driver.quit();
	      }
	  

	    @Test
	    public void deleteAllCookies() {
	     
	        driver.get("https://www.selenium.dev/selenium/web/blank.html");
	        // Add cookies into current browser context
	        driver.manage().addCookie(new Cookie("test1", "cookie1"));
	        driver.manage().addCookie(new Cookie("test2", "cookie2"));
	        // Delete All cookies
	        driver.manage().deleteAllCookies();
	     
	        driver.quit();
	      }
}
