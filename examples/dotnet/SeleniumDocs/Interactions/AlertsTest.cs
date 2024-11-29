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
using OpenQA.Selenium.Support.UI;
using SeleniumExtras.WaitHelpers;

namespace SeleniumDocs.Interactions
{
    [TestClass]
    public class AlertsTest
    {
        [TestMethod]
        public void TestAlertCommands()
        {  
            WebDriver driver = new ChromeDriver();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

            // Navigate to Url
            driver.Url= "https://www.selenium.dev/documentation/webdriver/interactions/alerts/";
            
            // Simple Alert
            // Click the link to activate the alert
            IJavaScriptExecutor js = (IJavaScriptExecutor)driver;
            // Execute JS for alert
            js.ExecuteScript("alert('Sample Alert');");
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(30));
            // Wait for the alert to be displayed and store it in a variable
            wait.Until(ExpectedConditions.AlertIsPresent());
            IAlert alert = driver.SwitchTo().Alert();
            // Store the alert text in a variable and verify it
            string text = alert.Text;
            Assert.AreEqual(text, "Sample Alert");
            alert.Accept();
            
            
            // Confirm Alert
            // Execute JS for confirm
            js.ExecuteScript("confirm('Are you sure?');");
            // Wait for the alert to be displayed
            wait.Until(ExpectedConditions.AlertIsPresent());
            alert = driver.SwitchTo().Alert();
            // Store the alert text in a variable and verify it
            text = alert.Text;
            Assert.AreEqual(text, "Are you sure?");
            // Press the Cancel button
            alert.Dismiss();

            // Prompt Alert
            // Execute JS for prompt
            js.ExecuteScript("prompt('What is your name?');");
            // Wait for the alert to be displayed
            wait.Until(ExpectedConditions.AlertIsPresent());
            alert = driver.SwitchTo().Alert();
            // Store the alert text in a variable and verify it
            text = alert.Text;
            Assert.AreEqual(text, "What is your name?");
            // Type your message
            alert.SendKeys("Selenium");
            // Press the OK button
            alert.Accept();
            
            //quitting driver
            driver.Quit(); //close all windows
        }
    }
}