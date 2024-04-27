using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocumentation.SeleniumInteractions
{
    [TestClass]
    public class NavigationTest
    {
        [TestMethod]
        public void TestNavigationCommands()
        {
            IWebDriver driver = new ChromeDriver();
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

            //Convenient
            driver.Url = "https://selenium.dev";
            //Longer
            driver.Navigate().GoToUrl("https://selenium.dev");
            var title = driver.Title;
            Assert.AreEqual("Selenium", title);

            //Back
            driver.Navigate().Back();
            title = driver.Title;
            Assert.AreEqual("Selenium", title);

            //Forward
            driver.Navigate().Forward();
            title = driver.Title;
            Assert.AreEqual("Selenium", title);

            //Refresh
            driver.Navigate().Refresh();
            title = driver.Title;
            Assert.AreEqual("Selenium", title);

            //Quit the browser
            driver.Quit();
        }
    }
}
