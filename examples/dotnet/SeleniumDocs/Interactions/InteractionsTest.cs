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
namespace SeleniumDocumentation.SeleniumInteractions
{
    [TestClass]
    public class InteractionsTest
    {
        [TestMethod]
        public void TestInteractions()
        {
            WebDriver driver = new ChromeDriver();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

            // Navigate to Url
            driver.Url="https://www.selenium.dev/";
            //GetTitle
            String title = driver.Title;
            Assert.AreEqual(title, "Selenium");

            //GetCurrentURL
            String url = driver.Url;
            Assert.AreEqual(url, "https://www.selenium.dev/");

            //quitting driver
            driver.Quit(); //close all windows
        }
    }
}