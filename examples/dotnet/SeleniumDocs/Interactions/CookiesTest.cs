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

using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Interactions{

[TestClass]
public class CookiesTest{
     
    WebDriver driver = new ChromeDriver();

     [TestMethod]
      public void addCookie(){
         driver.Url="https://www.selenium.dev/selenium/web/blank.html";
         // Add cookie into current browser context
         driver.Manage().Cookies.AddCookie(new Cookie("key", "value"));
         driver.Quit();
     }
     
     [TestMethod]
     public void getNamedCookie(){  
         driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
         // Add cookie into current browser context
         driver.Manage().Cookies.AddCookie(new Cookie("foo", "bar"));
         // Get cookie details with named cookie 'foo'
         Cookie cookie = driver.Manage().Cookies.GetCookieNamed("foo");
         Assert.AreEqual(cookie.Value, "bar");
         driver.Quit();
     }

     [TestMethod]
     public void getAllCookies(){
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
         driver.Quit();
     }
     
     [TestMethod]
     public void deleteCookieNamed(){
         driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
         driver.Manage().Cookies.AddCookie(new Cookie("test1", "cookie1"));
         // delete cookie named
         driver.Manage().Cookies.DeleteCookieNamed("test1");
         driver.Quit();
     }

     [TestMethod]
     public void deleteCookieObject(){
         driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
         Cookie cookie = new Cookie("test2", "cookie2");
         driver.Manage().Cookies.AddCookie(cookie);
         /*
      Selenium CSharp bindings also provides a way to delete
      cookie by passing cookie object of current browsing context
      */
         driver.Manage().Cookies.DeleteCookie(cookie);
         driver.Quit();
     }
     
     [TestMethod]
     public void deleteAllCookies(){
         driver.Url = "https://www.selenium.dev/selenium/web/blank.html";
         // Add cookies into current browser context
         driver.Manage().Cookies.AddCookie(new Cookie("test1", "cookie1"));
         driver.Manage().Cookies.AddCookie(new Cookie("test2", "cookie2"));
         // Delete All cookies
         driver.Manage().Cookies.DeleteAllCookies();
         driver.Quit();
     }
    }
}