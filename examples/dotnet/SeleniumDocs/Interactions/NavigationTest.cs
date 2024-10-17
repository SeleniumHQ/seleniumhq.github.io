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

            //Convenient (driver.Url only accepts string arg)
            driver.Url = "https://selenium.dev";
            //Longer (driver.Navigate().GoToUrl(arg) accepts both string and Uri args)
            driver.Navigate().GoToUrl("https://selenium.dev");
            //or even this
            //driver.Navigate().GoToUrl(new Uri("https://selenium.dev"));
            
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
