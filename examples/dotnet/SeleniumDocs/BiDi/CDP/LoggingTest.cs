using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.IdentityModel.Tokens;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.BiDi.CDP
{
    [TestClass]
    public class LoggingTest : BaseChromeTest
    {
        [TestMethod]
        public async Task ConsoleLogs()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";

            using IJavaScriptEngine monitor = new JavaScriptEngine(driver);
            var messages = new List<string>();
            monitor.JavaScriptConsoleApiCalled += (_, e) =>
            {
                messages.Add(e.MessageContent);
            };
            await monitor.StartEventMonitoring();

            driver.FindElement(By.Id("consoleLog")).Click();
            driver.FindElement(By.Id("consoleError")).Click();
            new WebDriverWait(driver, TimeSpan.FromSeconds(5)).Until(_ => messages.Count > 1);
            monitor.StopEventMonitoring();

            Assert.IsTrue(messages.Contains("Hello, world!"));
            Assert.IsTrue(messages.Contains("I am console error"));
        }
        
        [TestMethod]
        public async Task JsErrors()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";

            using IJavaScriptEngine monitor = new JavaScriptEngine(driver);
            var messages = new List<string>();
            monitor.JavaScriptExceptionThrown += (_, e) =>
            {
                messages.Add(e.Message);
            };
            await monitor.StartEventMonitoring();

            driver.FindElement(By.Id("jsException")).Click();
            new WebDriverWait(driver, TimeSpan.FromSeconds(5)).Until(_ => !messages.IsNullOrEmpty());
            monitor.StopEventMonitoring();

            Assert.IsTrue(messages.Contains("Uncaught"));
        }
    }
}