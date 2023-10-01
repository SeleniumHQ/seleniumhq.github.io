using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs
{
    public class BaseTest
    {
        protected IWebDriver driver;

        public void StartDriver()
        {
            driver = new ChromeDriver();
        }
        
        [TestCleanup]
        public void QuitDriver()
        {
            driver.Quit();
        }
    }
}