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
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.Interactions
{
    [TestClass]
    public class AlertsTest : BaseTest
    {
        private WebDriverWait wait;

        [TestInitialize]
        public void Setup()
        {
            driver = new OpenQA.Selenium.Chrome.ChromeDriver();
            wait = new WebDriverWait(driver, TimeSpan.FromSeconds(2));
        }

        [TestMethod]
        public void AlertInformationTest()
        {
            driver.Url = "https://www.selenium.dev/documentation/webdriver/interactions/alerts/";

            // Click the link to activate the alert
            driver.FindElement(By.LinkText("See an example alert")).Click();

            // Wait for the alert to be displayed and store it in a variable
            IAlert alert = wait.Until(d => d.SwitchTo().Alert());

            // Store the alert text in a variable
            string text = alert.Text;

            // Press the OK button
            alert.Accept();

            Assert.AreEqual("Sample alert", text);
        }

        [TestMethod]
        public void ConfirmationAlertTest()
        {
            driver.Url = "https://www.selenium.dev/documentation/webdriver/interactions/alerts/";

            // Click the link to activate the alert
            IWebElement link = driver.FindElement(By.LinkText("See a sample confirm"));
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", link);

            // Wait for the alert to be displayed
            wait.Until(d => d.SwitchTo().Alert());

            // Store the alert in a variable
            IAlert alert = driver.SwitchTo().Alert();

            // Store the alert in a variable for reuse
            string text = alert.Text;

            // Press the Cancel button
            alert.Dismiss();

            Assert.AreEqual("Are you sure?", text);
        }

        [TestMethod]
        public void PromptDisplayAndInputTest()
        {
            driver.Url = "https://www.selenium.dev/documentation/webdriver/interactions/alerts/";

            // Click the link to activate the alert
            IWebElement link = driver.FindElement(By.LinkText("See a sample prompt"));
            ((IJavaScriptExecutor)driver).ExecuteScript("arguments[0].click();", link);

            // Wait for the alert to be displayed and store it in a variable
            IAlert alert = wait.Until(d => d.SwitchTo().Alert());

            // Store the alert text in a variable for reuse
            string text = alert.Text;

            // Type your message
            alert.SendKeys("Selenium");

            // Press the OK button
            alert.Accept();

            Assert.AreEqual("What is your tool of choice?", text);
        }
    }
}