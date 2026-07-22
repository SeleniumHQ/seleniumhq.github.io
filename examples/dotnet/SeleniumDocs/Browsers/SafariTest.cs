using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Safari;
using SeleniumDocs.TestSupport;

namespace SeleniumDocs.Browsers
{
    [TestClassCustom]
    [EnabledOnOs("OSX")]
    public class SafariTest
    {
        private SafariDriver driver;

        [TestCleanup]
        public void QuitDriver()
        {
            driver.Quit();
        }

        [TestMethod]
        public void BasicOptions()
        {
            var options = new SafariOptions();
            driver = new SafariDriver(options);
        }
    }
}