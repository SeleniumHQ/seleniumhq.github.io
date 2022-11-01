using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Chrome;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;

namespace SeleniumDocs.Browsers
{
    [TestClass]
    public class ChromeTest
    {
        [TestMethod]
        public void BasicOptions()
        {
            new DriverManager().SetUpDriver(new ChromeConfig());

            var options = new ChromeOptions();
            var driver = new ChromeDriver(options);

            driver.Quit();
        }
    }
}