using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Safari;
using SeleniumDocs.TestSupport;

namespace SeleniumDocs.Browsers
{
    [TestClassCustom]
    [EnabledOnOs("OSX")]
    public class SafariTest
    {
        [TestMethod]
        public void BasicOptions()
        {
            var options = new SafariOptions();
            var driver = new SafariDriver(options);

            driver.Quit();
        }
    }
}