using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;

namespace SeleniumDocs
{
    public class BaseTest
    {
        protected IWebDriver driver;

        [TestCleanup]
        public void QuitDriver()
        {
            driver.Quit();
        }
    }
}