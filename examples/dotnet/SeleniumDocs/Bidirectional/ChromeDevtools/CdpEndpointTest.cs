using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Bidirectional.ChromeDevtools
{
    [TestClass]
    public class CdpEndpointTest : BaseTest
    {
        private readonly Dictionary<string, object> emptyDictionary = new(); 

        [TestInitialize]
        public void Startup()
        {
            StartDriver();
        }        

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
        
        [TestMethod]
        public void PerformanceMetrics()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/frameset.html";

            ((ChromeDriver)driver).ExecuteCdpCommand("Performance.enable", emptyDictionary);

            Dictionary<string, object> response = (Dictionary<string, object>)((ChromeDriver)driver)
                .ExecuteCdpCommand("Performance.getMetrics", emptyDictionary);

            Object[] metricList = (object[])response["metrics"];
            var metrics = metricList.OfType<Dictionary<string, object>>()
                .ToDictionary(
                    dict => (string)dict["name"], 
                    dict => dict["value"]
                );

            Assert.IsTrue((double)metrics["DevToolsCommandDuration"] > 0);
            Assert.AreEqual((long)12, metrics["Frames"]);
        }

        [TestMethod]
        public void BasicAuth()
        {
            ((ChromeDriver)driver).ExecuteCdpCommand("Network.enable", emptyDictionary);
            
            string encodedAuth = Convert.ToBase64String(Encoding.Default.GetBytes("admin:admin"));
            var headers = new Dictionary<string, object>
            {
                { "headers", new Dictionary<string, string> { { "authorization", "Basic " + encodedAuth } } }
            };

            ((ChromeDriver)driver).ExecuteCdpCommand("Network.setExtraHTTPHeaders", headers);

            driver.Url = "https://the-internet.herokuapp.com/basic_auth";

            var element = driver.FindElement(By.TagName("p"));
            Assert.AreEqual("Congratulations! You must have the proper credentials.", element.Text);
        }
    }
}