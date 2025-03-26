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
using System.Collections.Generic;

namespace SeleniumDocs.Interactions
{
  [TestClass]
    public class FramesTest
    {
        [TestMethod]
        public void TestFrames()
        {
            WebDriver driver = new ChromeDriver();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

            // Navigate to Url
            driver.Url= "https://www.selenium.dev/selenium/web/iframes.html";
            //switch To IFrame using Web Element
            IWebElement iframe = driver.FindElement(By.Id("iframe1"));
            //Switch to the frame
            driver.SwitchTo().Frame(iframe);
            Assert.AreEqual(true, driver.PageSource.Contains("We Leave From Here"));
            //Now we can type text into email field
            IWebElement emailE = driver.FindElement(By.Id("email"));
            emailE.SendKeys("admin@selenium.dev");
            emailE.Clear();
            driver.SwitchTo().DefaultContent();


            //switch To IFrame using name or id
            driver.FindElement(By.Name("iframe1-name"));
            //Switch to the frame
            driver.SwitchTo().Frame(iframe);
            Assert.AreEqual(true, driver.PageSource.Contains("We Leave From Here"));
            IWebElement email = driver.FindElement(By.Id("email"));
            //Now we can type text into email field
            email.SendKeys("admin@selenium.dev");
            email.Clear();
            driver.SwitchTo().DefaultContent();


            //switch To IFrame using index
            driver.SwitchTo().Frame(0);
            Assert.AreEqual(true, driver.PageSource.Contains("We Leave From Here"));

            //leave frame
            driver.SwitchTo().DefaultContent();
            Assert.AreEqual(true, driver.PageSource.Contains("This page has iframes"));

            //quit the browser
            driver.Quit();
        }
    }
}