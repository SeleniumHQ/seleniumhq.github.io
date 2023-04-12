using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.Waits
{
    [TestClass]
    public class WaitsTest : BaseChromeTest
    {
        [TestMethod]
        public void Explicit()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/dynamic.html";
            driver.FindElement(By.Id("adder")).Click();

            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
            IWebElement added = wait.Until(d => d.FindElement(By.Id("box0")));

            Assert.AreEqual("redbox", added.GetDomAttribute("class"));
        }

        [TestMethod]
        public void Implicit()
        {
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(5);
            
            driver.Url = "https://www.selenium.dev/selenium/web/dynamic.html";
            driver.FindElement(By.Id("adder")).Click();

            IWebElement added = driver.FindElement(By.Id("box0"));

            Assert.AreEqual("redbox", added.GetDomAttribute("class"));
        }
    }
}