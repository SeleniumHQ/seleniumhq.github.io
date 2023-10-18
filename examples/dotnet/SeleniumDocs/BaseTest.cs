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
        
        public void StartDriver(string browserVersion)
        {
            ChromeOptions options = new ChromeOptions
            {
                BrowserVersion = browserVersion
            };
            driver = new ChromeDriver(options);
        }
        
        [TestCleanup]
        public void QuitDriver()
        {
            driver.Quit();
        }
    }
}