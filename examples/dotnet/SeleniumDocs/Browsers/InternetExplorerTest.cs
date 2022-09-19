using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.IE;
using SeleniumDocs.TestSupport;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;

namespace SeleniumDocs.Browsers
{
    [TestClassCustom]
    [EnabledOnOs("WINDOWS")]
    public class InternetExplorerTest
    {
        [TestMethod]
        public void BasicOptions()
        {
            new DriverManager().SetUpDriver(new InternetExplorerConfig());

            var options = new InternetExplorerOptions
            {
                AttachToEdgeChrome = true,
                EdgeExecutablePath = System.Environment.GetEnvironmentVariable("EDGE_PATH")
            };
            var driver = new InternetExplorerDriver(options);

            driver.Quit();
        }
    }
}