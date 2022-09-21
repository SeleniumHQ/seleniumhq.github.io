using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Edge;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;

namespace SeleniumDocs.Browsers
{
    [TestClass]
    public class EdgeTest
    {
        [TestMethod]
        public void BasicOptions()
        {
            new DriverManager().SetUpDriver(new EdgeConfig());

            var options = new EdgeOptions();
            var driver = new EdgeDriver(options);

            driver.Quit();
        }
    }
}