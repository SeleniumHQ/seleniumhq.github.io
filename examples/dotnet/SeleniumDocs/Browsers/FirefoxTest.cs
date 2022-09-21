using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Firefox;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;

namespace SeleniumDocs.Browsers
{
    [TestClass]
    public class FirefoxTest
    {
        [TestMethod]
        public void BasicOptions()
        {
            new DriverManager().SetUpDriver(new FirefoxConfig());

            var options = new FirefoxOptions();
            var driver = new FirefoxDriver(options);

            driver.Quit();
        }
    }
}