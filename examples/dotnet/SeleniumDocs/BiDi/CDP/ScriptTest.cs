using System;
using System.Collections;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.IdentityModel.Tokens;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.BiDi.CDP
{
    [TestClass]
    public class ScriptTest : BaseChromeTest
    {
        [TestMethod]
        public async Task PinScript()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/xhtmlTest.html";
            var element = driver.FindElement(By.Id("id1"));

            var key = await new JavaScriptEngine(driver).PinScript("return arguments;");
            var arguments = ((WebDriver)driver).ExecuteScript(key, 1, true, element);

            var expected = new List<object>
            {
                1L,
                true,
                element
            };
            CollectionAssert.AreEqual(expected, (ICollection)arguments);
        }

        [TestMethod]
        public async Task MutatedElements()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/dynamic.html";
            var mutations = new List<IWebElement>();

            using IJavaScriptEngine monitor = new JavaScriptEngine(driver);
            monitor.DomMutated += (_, e) =>
            {
                var locator = By.CssSelector($"*[data-__webdriver_id='{e.AttributeData.TargetId}']");
                mutations.Add(driver.FindElement(locator));
            };
            await monitor.StartEventMonitoring();
            await monitor.EnableDomMutationMonitoring();

            driver.FindElement(By.Id("reveal")).Click();
            
            new WebDriverWait(driver, TimeSpan.FromSeconds(5)).Until(_ => !mutations.IsNullOrEmpty());
            await monitor.DisableDomMutationMonitoring();
            monitor.StopEventMonitoring();

            var revealed = driver.FindElement(By.Id("revealed")); 
            Assert.AreEqual(revealed, mutations[0]);
        }
    }
}