using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.Drivers
{
    [TestClass]
    public class OptionsTest : BaseTest
    {
        [TestMethod]
        public void SetPageLoadStrategyNormal()
        {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.PageLoadStrategy = PageLoadStrategy.Normal;
            IWebDriver driver = new ChromeDriver(chromeOptions);
            try
            {
                // Navigate to Url
                driver.Navigate().GoToUrl("https://selenium.dev");
            }
            finally
            {
                driver.Quit();
            }
        }
        [TestMethod]
        public void SetPageLoadStrategyEager()
        {
            var chromeOptions = new ChromeOptions();
            chromeOptions.PageLoadStrategy = PageLoadStrategy.Eager;
            IWebDriver driver = new ChromeDriver(chromeOptions);
            try
            {
                driver.Navigate().GoToUrl("https://selenium.dev");
            }
            finally
            {
                driver.Quit();
            }
        }
        [TestMethod]
        public void SetPageLoadStrategyNone()
        {
            var chromeOptions = new ChromeOptions();
            chromeOptions.PageLoadStrategy = PageLoadStrategy.None;
            IWebDriver driver = new ChromeDriver(chromeOptions);
            try
            {
                driver.Navigate().GoToUrl("https://selenium.dev");
            }
            finally
            {
                driver.Quit();
            }
        }
    }
}