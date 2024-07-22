using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.BiDi.CDP
{
    [TestClass]
    public class CDPTest : BaseChromeTest
    {
        [TestMethod]
        public void SetCookie()
        {
            var cookie = new Dictionary<string, object>
            {
                { "name", "cheese" },
                { "value", "gouda" },
                { "domain", "www.selenium.dev" },
                { "secure", true }
            };
            ((ChromeDriver)driver).ExecuteCdpCommand("Network.setCookie", cookie);
            
            driver.Url = "https://www.selenium.dev";
            Cookie cheese = driver.Manage().Cookies.GetCookieNamed("cheese");
            Assert.AreEqual("gouda", cheese.Value);

        }
    }
}