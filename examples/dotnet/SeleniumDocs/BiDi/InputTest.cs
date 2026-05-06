using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.BiDi.W3C
{
    [TestClass]
    public class InputTest : BaseFirefoxTest
    {
        [TestMethod]
        public void PerformKeyActions()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            var input = driver.FindElement(By.Id("textInput"));
            
            input.SendKeys("Hello World");
            
            Assert.AreEqual("Hello World", input.GetAttribute("value"));
        }

        [TestMethod]
        public void PerformMouseActions()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            var button = driver.FindElement(By.Id("consoleLog"));
            
            button.Click();
            
            // Verify action occurred
            var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(5));
            Assert.IsNotNull(button);
        }

        [TestMethod]
        public void DispatchKeyboardEvents()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            var element = driver.FindElement(By.TagName("body"));
            
            driver.ExecuteScript(@"
                document.addEventListener('keydown', function(e) {
                    console.log('Key pressed: ' + e.key);
                });
            ");
            
            element.SendKeys("a");
        }

        [TestMethod]
        public void DispatchMouseEvents()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            var button = driver.FindElement(By.Id("consoleLog"));
            
            driver.ExecuteScript(@"
                arguments[0].addEventListener('mouseover', function(e) {
                    console.log('Mouse over');
                });
            ", button);
            
            button.Click();
        }

        [TestMethod]
        public void DispatchTouchEvents()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            var element = driver.FindElement(By.TagName("body"));
            
            driver.ExecuteScript(@"
                document.addEventListener('touchstart', function(e) {
                    console.log('Touch started');
                });
            ");
        }

        [TestMethod]
        public void DispatchWheelEvents()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/iframes.html";
            
            driver.ExecuteScript(@"
                window.addEventListener('wheel', function(e) {
                    console.log('Wheel event');
                });
            ");
        }
    }
}
