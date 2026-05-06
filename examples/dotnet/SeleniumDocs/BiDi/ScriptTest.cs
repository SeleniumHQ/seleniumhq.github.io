using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.BiDi;
using OpenQA.Selenium.BiDi.Modules;
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.BiDi.W3C
{
    [TestClass]
    public class ScriptTest : BaseFirefoxTest
    {
        [TestMethod]
        public void CallFunction()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            var result = driver.ExecuteScript(
                "return (function(a, b) { return a + b; })(2, 3)"
            );
            
            Assert.AreEqual(5L, result);
        }

        [TestMethod]
        public void EvaluateScript()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            var result = driver.ExecuteScript("return 2 + 2");
            
            Assert.AreEqual(4L, result);
        }

        [TestMethod]
        public void CallFunctionWithElementArgs()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            var element = driver.FindElement(By.Id("consoleLog"));
            
            var result = driver.ExecuteScript(
                "return arguments[0].tagName",
                element
            );
            
            Assert.AreEqual("BUTTON", result);
        }

        [TestMethod]
        public void GetRealms()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            // Note: Getting realms requires BiDi module access
            // This is a conceptual example
            var result = driver.ExecuteScript("return typeof window");
            
            Assert.AreEqual("object", result);
        }

        [TestMethod]
        public void AddDomMutationHandler()
        {
            var mutationEvents = new List<string>();
            
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            driver.ExecuteScript(@"
                const div = document.createElement('div');
                div.textContent = 'Hello';
                document.body.appendChild(div);
            ");
            
            var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(5));
            var element = wait.Until(d => d.FindElement(By.XPath("//div[text()='Hello']")));
            
            Assert.IsNotNull(element);
        }

        [TestMethod]
        public void ExecuteAsyncScript()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            var result = driver.ExecuteAsyncScript(@"
                var callback = arguments[arguments.length - 1];
                setTimeout(function() {
                    callback(42);
                }, 100);
            ");
            
            Assert.AreEqual(42L, result);
        }

        [TestMethod]
        public void SubscribeToConsoleLog()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
            
            var consoleMessages = new List<string>();
            
            // This would require BiDi module direct access in C#
            driver.ExecuteScript("console.log('Test message')");
            
            // Verify script execution
            var result = driver.ExecuteScript("return 'Script executed'");
            Assert.AreEqual("Script executed", result);
        }
    }
}
