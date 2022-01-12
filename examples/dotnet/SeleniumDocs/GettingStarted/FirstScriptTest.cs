using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs
{
    [TestClass]
    public class FirstScriptTest
    {

        [TestMethod]
        public void ChromeSession()
        {
            var driver = new ChromeDriver();

            driver.Navigate().GoToUrl("https://google.com");

            Assert.AreEqual("Google", driver.Title);

            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(500);

            var searchBox = driver.FindElement(By.Name("q"));
            var searchButton = driver.FindElement(By.Name("btnK"));
            
            searchBox.SendKeys("Selenium");
            searchButton.Click();
            
            searchBox = driver.FindElement(By.Name("q"));
            Assert.AreEqual("Selenium", searchBox.GetAttribute("value"));

            driver.Quit();
        }
    }
}