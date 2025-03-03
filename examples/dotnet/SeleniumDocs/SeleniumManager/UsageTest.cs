using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Chrome;

namespace SeleniumDocs.SeleniumManager
{
    [TestClass]
    public class UsageTest
    {
        [TestMethod]
        public void TestWithSeleniumManager()
        {
            // Before
            // using var driver = new ChromeDriver("path/to/chromedriver");

            // Now
            using var driver = new ChromeDriver();
            driver.Navigate().GoToUrl("https://www.selenium.dev/documentation/selenium_manager/");
        }
    }
}
