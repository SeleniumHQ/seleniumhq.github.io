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
        [TestMethod]
        public void SetUnhandledPromptBehavior()
        {
            var chromeOptions = new ChromeOptions();
            chromeOptions.UnhandledPromptBehavior = UnhandledPromptBehavior.DismissAndNotify;
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
        public void SetsProxy()
        {
            ChromeOptions options = new ChromeOptions();
            Proxy proxy = new Proxy();
            proxy.Kind = ProxyKind.Manual;
            proxy.IsAutoDetect = false;
            proxy.SslProxy = "myproxy.com:8080";
            options.Proxy = proxy;
            options.AddArgument("ignore-certificate-errors");

            Assert.IsNotNull(options.Proxy);
            Assert.AreEqual("myproxy.com:8080", options.Proxy.SslProxy);
        }
    }
}
