using System;
using System.Threading;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;

namespace SeleniumDocs.Waits
{
    [TestClass]
    public class WaitsTest : BaseChromeTest
    {
        [TestMethod]
        public void Fails()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/dynamic.html";
            driver.FindElement(By.Id("adder")).Click();

            Assert.ThrowsException<NoSuchElementException>(
                () => driver.FindElement(By.Id("box0"))
            );
        }

        [TestMethod]
        public void Sleep()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/dynamic.html";
            driver.FindElement(By.Id("adder")).Click();

            Thread.Sleep(1000);

            IWebElement added = driver.FindElement(By.Id("box0"));

            Assert.AreEqual("redbox", added.GetDomAttribute("class"));
        }

        [TestMethod]
        public void Implicit()
        {
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(2);
            
            driver.Url = "https://www.selenium.dev/selenium/web/dynamic.html";
            driver.FindElement(By.Id("adder")).Click();

            IWebElement added = driver.FindElement(By.Id("box0"));

            Assert.AreEqual("redbox", added.GetDomAttribute("class"));
        }

        [TestMethod]
        public void Explicit()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/dynamic.html";
            IWebElement revealed = driver.FindElement(By.Id("revealed"));
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(2));

            driver.FindElement(By.Id("reveal")).Click();
            wait.Until(d => revealed.Displayed);

            revealed.SendKeys("Displayed");
            Assert.AreEqual("Displayed", revealed.GetDomProperty("value"));
        }

        [TestMethod]
        public void ExplicitOptions()
        {
            driver.Url = "https://www.selenium.dev/selenium/web/dynamic.html";
            IWebElement revealed = driver.FindElement(By.Id("revealed"));
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(2))
            {
                PollingInterval = TimeSpan.FromMilliseconds(300),
            };
            wait.IgnoreExceptionTypes(typeof(ElementNotInteractableException));

            driver.FindElement(By.Id("reveal")).Click();
            wait.Until(d => {
                revealed.SendKeys("Displayed");
                return true;
            });

            Assert.AreEqual("input", revealed.TagName);
        }
    }
}